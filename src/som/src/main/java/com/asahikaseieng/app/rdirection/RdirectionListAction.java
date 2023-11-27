/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectRdirectionListStatus;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.dao.nonentity.common.commonproc.CommonProcDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造実績検索 Actionクラス.
 * @author tosco
 * 
 */
public final class RdirectionListAction extends AbstractSearchAction {

	/** 基本処方検索のロジッククラス */
	private RdirectionListLogic rdirectionListLogic;

	/** 製造記録ＥＸＣＥＬファイル作成ロジッククラス */
	private RdirectionListExcelDecorator rdirectionListExcelDecorator;

	/* 「％」 */
	private static final String STR_PERCENT;

	private DirectionResultListExcelDecorator directionResultListExcelDecorator;
	
	private CommonProcDao commonProcDao;

	static {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		STR_PERCENT = rb.getString("item.tani.percent");
	}

	/**
	 * コンストラクタ.
	 */
	public RdirectionListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		RdirectionListForm listForm = (RdirectionListForm) form;
		listForm.clear();
		
		// -- S.Fujimaki add 表示権限の追加 20221006 -- //
		
		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_SEARCH);

		/* 表示権限確認 */
		if (!listForm.getViewAuthority()) {
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// -- S.Fujimaki add end 20221006 -- //
	
		// 生産ラインコンボボックス
		listForm.setLineCombo(rdirectionListLogic.createLineCombobox());
		// 初期検索設定
		RdirectionDirectionHeaderListPagerCondition condition = (RdirectionDirectionHeaderListPagerCondition) listForm
				.getPager().getPagerCondition();
		listForm.setDirectionStatus(Integer
				.parseInt(RdirectionConst.COMBO_ALL_VALUE));
		condition.setDirectionStatus(RdirectionConst.COMBO_ALL_VALUE);
		for (ComboBoxItems item : listForm.getLineCombo()) {
			// 最初の1件目の値を設定
			listForm.setProductionLine(item.getValues());
			condition.setProductionLine(item.getValues());
			break;
		}
		// 製造開始・終了実績日時の初期値を設定
		String date = AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd");
		listForm.setResultSdateDayFrom(date);
		listForm.setResultEdateDayFrom(date);
		String datetime = date + " 00:00";
		condition.setResultSdateDateFrom(datetime);
		condition.setResultEdateDateFrom(datetime);

		return mapping.findForward("success");
	}

	/**
	 * 基本処方検索－検索処理(検索ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}
		RdirectionListForm listForm = (RdirectionListForm) form;

		// クリア
		listForm.setSearchList(new ArrayList<RdirectionDirectionHeaderList>());
		if (!listForm.getOp().equals("reFresh")) {
			listForm.setExcelDownloadFlg(Boolean.FALSE);
		}

		// 検索条件を取得
		RdirectionDirectionHeaderListPagerCondition condition = (RdirectionDirectionHeaderListPagerCondition) listForm
				.getPager().getPagerCondition();
		if (listForm.getOp().equals("reSearch")
				|| listForm.getOp().equals("reFresh")) {
			// 他画面から戻ってきた場合
			listForm
					.setDirectionNo(removeLikeString(condition.getDirectionNo())); // 指図区分

			if (StringUtils.isEmpty(condition.getProductionLine())) {
				// 生産工場=0:すべての場合
				listForm.setProductionLine(RdirectionConst.COMBO_ALL_VALUE);
			} else {
				// 生産工場=0:すべて以外の場合
				listForm.setProductionLine(condition.getProductionLine());
			}

			listForm.setItemCd(removeLikeString(condition.getItemCd())); // 品目コード
			listForm.setItemName(null); // 品目名称
			listForm.setOtherCompanyCd1(removeLikeString(condition
					.getOtherCompanyCd1())); // 他社コード１
			listForm.setCompoundTankNo(removeLikeString(condition
					.getCompoundTankNo()));
			// ステータス
			String directionStatus = condition.getDirectionStatus();
			if (StringUtils.isNotEmpty(directionStatus)) {
				listForm.setDirectionStatus(Integer.parseInt(directionStatus));
			} else {
				listForm.setDirectionStatus(Integer
						.parseInt(RdirectionConst.COMBO_ALL_VALUE));
			}
		} else {
			// 通常検索
			// 検索条件をセット
			condition.setDirectionNo(listForm.getDirectionNo()); // 指図番号

			String productionLine = listForm.getProductionLine(); // 生産工場

			if (MgrecipeConst.COMBO_ALL_VALUE.equals(productionLine)) {
				// 生産工場=0:すべての場合
				condition.setProductionLine(null);
			} else {
				// 生産工場=0:すべて以外の場合
				condition.setProductionLine(productionLine);
			}

			condition.setItemCd(listForm.getItemCd()); // 主要製品コード
			condition.setResultSdateDateFrom(listForm.getPlanedSdateDateFrom()); // 開始予定日(From)
			condition.setResultEdateDateFrom(listForm.getPlanedEdateDateFrom()); // 終了予定日(From)
			condition.setResultSdateDateTo(listForm.getPlanedSdateDateTo()); // 開始予定日(To)
			condition.setResultEdateDateTo(listForm.getPlanedEdateDateTo()); // 終了予定日(To)
			condition.setOtherCompanyCd1(listForm.getOtherCompanyCd1()); // 他社コード１
			condition.setCompoundTankNo(listForm.getCompoundTankNo()); // 調合タンク

			// 指図ステータス
			condition.setDirectionStatus(String.valueOf(listForm
					.getDirectionStatus()));
		}

		// 処方ヘッダを検索
		List<RdirectionDirectionHeaderList> result = rdirectionListLogic
				.getSearchList(condition);
		// ステータス・数値の編集
		editResult(result, request);
		listForm.setSearchList(result);
		/* 帳票(Excel)用に検索条件を保持 */
		DirectionResultListConditionForReport reportCondition = new DirectionResultListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		listForm.setReportCondition(reportCondition);

		return mapping.findForward("success");
	}

	/**
	 * 帳票処理(検索画面の帳票(Excel)ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		RdirectionListForm frm = (RdirectionListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = directionResultListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 製造記録発行
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issuance(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issuance.");
		}
		String forward = "success";
		RdirectionListForm listForm = (RdirectionListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		TreeMap<String, String> tMap;
		
		listForm.setExcelDownloadFlg(Boolean.FALSE);

		try {
			int row = 1;
			Boolean error = Boolean.FALSE;
			
			//20230207 画面で警告を表示するまではチェックを行う。 reCheckNum 0:初期 1:画面確認 2:確認ＯＫ 
			if( listForm.getReCheckNum() != 2 ){
				// 製造記録書発行時のチェック
				for(RdirectionDirectionHeaderList bean:listForm.getSearchList()){
					
					// 発行チェックがONである場合、実績日のチェックを行う
					if(bean.isSelectedCheck()){
						BigDecimal ret = commonProcDao.getNumericData("1000", bean.getDirectionNo(), 
								AecDateUtils.dateFormat(bean.getResultSdate(), "yyyy/MM/dd HH:mm:ss") 
								, AecDateUtils.dateFormat(bean.getResultEdate(), "yyyy/MM/dd HH:mm:ss"), null, null, null, null, null, null, null);
						
						if(ret.equals(new BigDecimal(1))){
							// 1:製造終了実績日が未来    メッセージ：{0}行目 製造指図番号{1} 実績日付が未来です。実績日付を修正して再出力を行ってください。
							addError(request, "errors.rdirection.resultdate.future",row, bean.getDirectionNo());
							error = Boolean.TRUE;
							
						}else if(ret.equals(new BigDecimal(2))){
							// 2:実績日でカレンダーマスタ無し 対象年月でカレンダーコード1300のカレンダーが存在しません。
							addError(request, "errors.rdirection.no.calmaster");
							error = Boolean.TRUE;
							
						}else if(ret.equals(new BigDecimal(3)) || ret.equals(new BigDecimal(4))){
							// 3:カレンダーマスタ休日、製造日付が前月1日～当月当日以外   メッセージ：{0}行目 製造指図番号{1} 実績日付が誤っています。実績日付を修正して再出力を行ってください。
							// 4:カレンダーマスタ平日、製造日付が当日1日～当月当日以外   メッセージ：{0}行目 製造指図番号{1} 実績日付が誤っています。実績日付を修正して再出力を行ってください。
							// エラー表示は行わない。
							listForm.reCheckNum = 1; // reCheckNum 0:初期 1:画面確認 2:確認ＯＫ 
						}
					}
					row++;
				}
				
				if(error){
					// エラーが発生した場合、エラーメッセージを表示し、発行処理を行わない。
					// 再検索処理
					forward = "reFresh";
					return mapping.findForward(forward);
				}
				
				if(listForm.reCheckNum == 1){
					// 20230207 再チェック処理実行
					return mapping.findForward("success");
				}
			}
			//20230207 画面で警告を表示するまではチェックを行う。 終了
			
			// 製造記録発行処理
			tMap = rdirectionListLogic.issuance(listForm.getSearchList(),
				tantoCd);

			// 製造記録発行
			createReport(listForm, request, tMap);

			// ﾁｪｯｸフラグを初期化する。
			listForm.setReCheckNum(0);

			// 処理成功メッセージ
			saveMessage(request, "message.complete.update");
			// 再検索処理
			forward = "reFresh";
		} catch (RdirectionLogicException e) {
			// エラー発生時　初期化する。
			listForm.setReCheckNum(0);
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
		}
		return mapping.findForward(forward);
	}

	/**
	 * 製造記録発行
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 * @param tMap 発行対象
	 */
	private void createReport(final RdirectionListForm frm,
			final HttpServletRequest request, final TreeMap<String, String> tMap) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;
		if (tMap.isEmpty()) {
			frm.setExcelDownloadFlg(Boolean.FALSE);
			return;
		}
		String[] directionNoArray = new String[tMap.size()];
		int idx = 0;
		Iterator ite = tMap.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			directionNoArray[idx] = key;
			idx++;
		}

		// 製造記録を作成
		info = rdirectionListExcelDecorator.createReport(directionNoArray,
			tantoNm, AecDateUtils.getCurrentTimestamp(), request
					.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(Boolean.TRUE);
		tMap.clear();
	}

	/**
	 * 一括完了
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward complete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("complete.");
		}
		String forward = "success";
		RdirectionListForm listForm = (RdirectionListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		try {
			// 完了処理
			rdirectionListLogic.complete(listForm.getSearchList(), tantoCd);
			// 処理成功メッセージ
			saveMessage(request, "message.complete.update");
			// 再検索処理
			forward = "reSearch";
		} catch (RdirectionLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
		}
		return mapping.findForward(forward);
	}

	/**
	 * ステータス・仕込数量を表示用に編集する。
	 * @param result 検索結果
	 * @param request HttpServletRequest
	 */
	private void editResult(final List<RdirectionDirectionHeaderList> result,
			final HttpServletRequest request) {
		// 数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (RdirectionDirectionHeaderList bean : result) {
			// 指図ステータス名称
			bean.setStatusName(SelectRdirectionListStatus
					.getName(getBigDecimalString(bean.getDirectionStatus())));
			String unitDivision = bean.getUnitOfOperationManagement();
			// 仕込予定数量
			bean.setPlandQtyString(checker.format(unitDivision, bean
					.getPlanedQty()));
			// 仕込実績数量
			bean.setResultQtyString(checker.format(unitDivision, bean
					.getResultQty()));
		}
	}

	/**
	 * LIKE検索用の文字列を削除する
	 * @param s 検索用文字列
	 * @return LIKE用の文字列を削除したもの
	 */
	private String removeLikeString(final String s) {
		String res = s;
		if (StringUtils.isNotEmpty(s)) {
			StringBuilder buf = new StringBuilder(s);
			String left = buf.substring(0, 1);
			if (left.equals(STR_PERCENT)) {
				buf = buf.delete(0, 1);
			}
			int length = buf.length();
			String right = buf.substring(length - 1, length);
			if (right.equals(STR_PERCENT)) {
				buf = buf.delete(length - 1, length);
			}
			res = buf.toString();
		}
		return res;
	}

	/**
	 * BigDecimalの文字列表現を取得する BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	private String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	protected void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造実績検索のロジッククラスを設定します。
	 * @param rdirectionListLogic 製造実績検索のロジッククラス
	 */
	public void setRdirectionListLogic(
			final RdirectionListLogic rdirectionListLogic) {
		this.rdirectionListLogic = rdirectionListLogic;
	}

	/**
	 * 製造記録ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param rdirectionListExcelDecorator 製造記録ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setRdirectionListExcelDecorator(
			final RdirectionListExcelDecorator rdirectionListExcelDecorator) {
		this.rdirectionListExcelDecorator = rdirectionListExcelDecorator;
	}

	/**
	 * 帳票ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param directionResultListExcelDecorator 帳票ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setDirectionResultListExcelDecorator(
			final DirectionResultListExcelDecorator directionResultListExcelDecorator) {
		this.directionResultListExcelDecorator = directionResultListExcelDecorator;
	}

	/**
	 * commonProcDaoを設定します。
	 * @param commonProcDao commonProcDao
	 */
	public void setCommonProcDao(CommonProcDao commonProcDao) {
		this.commonProcDao = commonProcDao;
	}
}
