/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectPkgDirectionDirectionDivision;
import com.asahikaseieng.dao.nonentity.common.commonproc.CommonProcDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionListConditionForReport;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 包装実績－検索画面 Actionクラス
 * @author tosco
 * 
 */
public final class PkgRdirectionListAction extends AbstractSearchAction {

	/** 包装実績共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/** 包装実績－検索画面のロジッククラス */
	private PkgRdirectionListLogic pkgRdirectionListLogic;

	/** 包装記録ＥＸＣＥＬファイル作成ロジッククラス */
	private PkgRdirectionListExcelDecorator pkgRdirectionListExcelDecorator;

	/** 製品ラベルＥＸＣＥＬファイル作成ロジッククラス */
	private PkgRdirectionListLabelExcelDecorator pkgRdirectionListLabelExcelDecorator;

	private PkgRdirectionReportListExcelDecorator pkgRdirectionReportListExcelDecorator;

	/** 「％」 */
	private static final String STR_PERCENT;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59:59";

	private CommonProcDao commonProcDao;

	static {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		STR_PERCENT = rb.getString("item.tani.percent");
	}

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionListAction() {
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

		PkgRdirectionListForm listForm = (PkgRdirectionListForm) form;
		listForm.clear();

		PkgRdirectionListPagerCondition condition = (PkgRdirectionListPagerCondition) listForm
				.getPager().getPagerCondition();

		// 生産工場コンボボックス
		listForm.setLineCombo(pkgRdirectionCommonsLogic
				.createLineCombobox(true));

		// コンボボックスの初期選択値を設定
		SelectPkgDirectionDirectionDivision directDivCmb = new SelectPkgDirectionDirectionDivision(
				false, true);
		for (ComboBoxItems item : directDivCmb.getComboBoxList()) {
			listForm.setDirectionDivision(item.getValues());
			condition.setDirectionDivision(new BigDecimal(item.getValues()));
			break;
		}
		listForm.setDirectionStatus(PkgRdirectionConst.COMBO_ALL_VALUE
				.toString());
		condition.setDirectionStatus(PkgRdirectionConst.COMBO_ALL_VALUE);
		for (ComboBoxItems item : listForm.getLineCombo()) {
			listForm.setProductionLine(item.getValues());
			condition.setProductionLine(item.getValues());
			break;
		}
		listForm.setResultSDayFrom(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		listForm.setResultEDayFrom(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		return mapping.findForward("success");
	}

	/**
	 * 包装実績検索処理(検索ボタン押下時)
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

		PkgRdirectionListForm listForm = (PkgRdirectionListForm) form;

		// クリア
		listForm.setSearchList(new ArrayList<PkgRdirectionList>());
		if (!listForm.getOp().equals("reFresh")) {
			listForm.setExcelDownloadFlg(Boolean.FALSE);
		}

		// 検索条件を取得
		PkgRdirectionListPagerCondition condition = (PkgRdirectionListPagerCondition) listForm
				.getPager().getPagerCondition();

		if (listForm.getOp().equals("reSearch")
				|| listForm.getOp().equals("reFresh")) {
			// 他画面から戻ってきた場合
			listForm.setDirectionDivision(condition.getDirectionDivision()
					.toString());
			listForm.setDirectionNo(condition.getDirectionNo());
			listForm.setDirectionStatus(condition.getDirectionStatus()
					.toString());

			if (StringUtils.isEmpty(condition.getProductionLine())) {
				// 生産工場=0:すべての場合
				listForm.setProductionLine(PkgRdirectionConst.COMBO_ALL_VALUE
						.toString());
			} else {
				// 生産工場=0:すべて以外の場合
				listForm.setProductionLine(condition.getProductionLine());
			}

			listForm.setItemCd(removeLikeString(condition.getItemCd()));
			listForm.setItemName(condition.getItemName());
			listForm.setOtherCompanyCd1(removeLikeString(condition
					.getOtherCompanyCd1()));
			listForm.setResultSDayFrom(condition.getResultSDayFrom());
			listForm.setResultSTimeFrom(condition.getResultSTimeFrom());
			listForm.setResultSDayTo(condition.getResultSDayTo());
			listForm.setResultSTimeTo(condition.getResultSTimeTo());
			listForm.setResultEDayFrom(condition.getResultEDayFrom());
			listForm.setResultETimeFrom(condition.getResultETimeFrom());
			listForm.setResultEDayTo(condition.getResultEDayTo());
			listForm.setResultETimeTo(condition.getResultETimeTo());
			listForm.setLotNo(condition.getLotNo());
			listForm
					.setPackageLine(removeLikeString(condition.getPackageLine()));
		} else {
			// 通常検索

			// 検索条件をセット
			condition.setDirectionDivision(new BigDecimal(listForm
					.getDirectionDivision())); // 指図区分
			condition.setDirectionNo(listForm.getDirectionNo()); // 指図番号
			condition.setDirectionStatus(new BigDecimal(listForm
					.getDirectionStatus())); // 指図ステータス

			String productionLine = listForm.getProductionLine(); // 生産工場

			if (PkgRdirectionConst.COMBO_ALL_VALUE.toString().equals(
				productionLine)) {
				// 生産工場=0:すべての場合
				condition.setProductionLine(null);
			} else {
				// 生産工場=0:すべて以外の場合
				condition.setProductionLine(productionLine);
			}

			condition.setItemCd(listForm.getItemCd()); // 品目コード
			if (StringUtils.isEmpty(listForm.getItemCd())) {
				listForm.setItemName(null);
			}
			condition.setItemName(listForm.getItemName()); // 品目名称
			condition.setOtherCompanyCd1(listForm.getOtherCompanyCd1()); // 他社コード１

			/** 包装開始予定日時(FROM) */
			String strDay = listForm.getResultSDayFrom();
			String strTime = listForm.getResultSTimeFrom();
			condition.setResultSdateFrom(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MIN_TIME));
			condition.setResultSDayFrom(strDay);
			condition.setResultSTimeFrom(strTime);

			/** 包装開始予定日時(TO) */
			strDay = listForm.getResultSDayTo();
			strTime = listForm.getResultSTimeTo();
			condition.setResultSdateTo(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MAX_TIME));
			condition.setResultSDayTo(strDay);
			condition.setResultSTimeTo(strTime);

			/** 包装終了予定日時(FROM) */
			strDay = listForm.getResultEDayFrom();
			strTime = listForm.getResultETimeFrom();
			condition.setResultEdateFrom(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MIN_TIME));
			condition.setResultEDayFrom(strDay);
			condition.setResultETimeFrom(strTime);

			/** 包装終了予定日時(TO) */
			strDay = listForm.getResultEDayTo();
			strTime = listForm.getResultETimeTo();
			condition.setResultEdateTo(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MAX_TIME));
			condition.setResultEDayTo(strDay);
			condition.setResultETimeTo(strTime);
			condition.setPackageLine(listForm.getPackageLine());

			condition.setLotNo(listForm.getLotNo()); // ロット番号
		}
		/* 帳票(Excel)用に検索条件を保持 */
		PkgRdirectionListConditionForReport reportCondition = new PkgRdirectionListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		listForm.setReportCondition(reportCondition);

		// 製造指図ヘッダーを検索
		List<PkgRdirectionList> result = pkgRdirectionListLogic
				.getSearchList(condition);
		listForm.setSearchList(result);

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

		PkgRdirectionListForm frm = (PkgRdirectionListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = pkgRdirectionReportListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 日付文字列、時間文字列を結合してTimestamp型で返却する
	 * @param strDay 日付(yyyy/MM/dd)
	 * @param strTime 時分(HH:mm)
	 * @param strDefTime デフォルト時間(HH:mm:ss)
	 * @return Timestamp 文字列結合後のTimestamp
	 */
	private Timestamp getTimestampYmdHmsFormat(final String strDay,
			final String strTime, final String strDefTime) {
		Timestamp timestamp = null;
		String strFormat = "yyyy/MM/dd HH:mm:ss";
		if (StringUtils.isNotEmpty(strDay)) {
			String strDate = strDay;
			if (StringUtils.isNotEmpty(strTime)) {
				String[] strHms = strDefTime.split(":", 3);
				strDate = strDate + " " + strTime;
				if (strHms != null && strHms.length == 3) {
					strDate = strDate + ":" + strHms[2];
				} else {
					strFormat = "yyyy/MM/dd HH:mm";
				}
			} else {
				strDate = strDate + " " + strDefTime;
			}
			timestamp = AecDateUtils
					.getTimestampYmdHmFormat(strDate, strFormat);
		}
		return timestamp;
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
	 * 包装記録発行処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issueDoc(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		ActionMessages errMsgs = null;

		if (getLog().isDebugEnabled()) {
			getLog().debug("issue.");
		}

		PkgRdirectionListForm frm = (PkgRdirectionListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		TreeMap<String, String> tMap = null;

		frm.setExcelDownloadFlg(Boolean.FALSE);

		// 包装記録の発行可否チェック
		errMsgs = pkgRdirectionListLogic.checkDirectionStatus(frm
				.getSearchList());
		if (errMsgs != null && !errMsgs.isEmpty()) {
			saveErrors(request, errMsgs);
			return mapping.getInputForward();
		}
		
		int row = 1;
		Boolean error = Boolean.FALSE;

		//20230207 画面で警告を表示するまではチェックを行う。 reCheckNum 0:初期 1:画面確認 2:確認ＯＫ 
		if( frm.getReCheckNum() != 2 ){
			// 製造記録書発行時のチェック
			for(PkgRdirectionList bean:frm.getSearchList()){
				
				// 発行チェックがONである場合、実績日のチェックを行う
				if(bean.isCheckFlg()){
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
						frm.reCheckNum = 1; // reCheckNum 0:初期 1:画面確認 2:確認ＯＫ 
					}
				}
				row++;
			}
		}
		//20230207 画面で警告を表示するまではチェックを行う。 終了
		
		if(error){
			// エラーが発生した場合、エラーメッセージを表示し、発行処理を行わない。
			// 再検索処理
			return mapping.findForward("reFresh");
			
		}
		if(frm.reCheckNum == 1){
			// 20230207 再チェック処理実行
			return mapping.findForward("success");
		}

		/* 包装記録発行処理を実行 */
		tMap = pkgRdirectionListLogic.issueProductRecord(frm.getSearchList(),
			tantoCd);

		/* 包装記録発行 */
		createReport(frm, request, tMap);
		
		// ﾁｪｯｸフラグを初期化する。
		frm.setReCheckNum(0);

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("reFresh");
	}

	/**
	 * 包装記録発行
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 * @param tMap 発行対象
	 */
	private void createReport(final PkgRdirectionListForm frm,
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

		// 包装記録を作成
		info = pkgRdirectionListExcelDecorator.createReport(directionNoArray,
			tantoNm, AecDateUtils.getCurrentTimestamp(), request
					.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(Boolean.TRUE);
		tMap.clear();
	}

	/**
	 * 製品ラベル発行処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issueLabel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issueLabel.");
		}

		PkgRdirectionListForm frm = (PkgRdirectionListForm) form;
		frm.setExcelDownloadFlg(false);
		String tantoCd = getLoginInfo(request).getTantoCd();
		TreeMap<String, String> tMap = new TreeMap<String, String>();

		/* 更新処理を実行 */
		pkgRdirectionListLogic.issueLabel(frm.getSearchList(), tantoCd);

		// 出力対象のデータを保持
		for (PkgRdirectionList bean : frm.getSearchList()) {

			if (!bean.isCheckFlg()) {
				// チェック無
				continue;
			}

			// 製造指図番号とラベル枚数を保存する
			tMap.put(bean.getDirectionNo(), bean.getLabelCount());

		}

		// 製品ラベル作成
		createLabel(frm, request, tMap);

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		// return mapping.findForward("reSearch");
		return mapping.findForward("reFresh");
	}

	/**
	 * 製品ラベル作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 */
	private void createLabel(final PkgRdirectionListForm frm,
			final HttpServletRequest request, final TreeMap<String, String> tMap) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		// 製品ラベルを作成
		info = pkgRdirectionListLabelExcelDecorator.createReport(tMap, tantoNm,
			AecDateUtils.getCurrentTimestamp(), request.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(true);
	}

	/**
	 * 一括完了処理
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
		PkgRdirectionListForm frm = (PkgRdirectionListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		try {
			// 完了処理
			pkgRdirectionListLogic.complete(frm.getSearchList(), tantoCd);
			// 処理成功メッセージ
			saveMessage(request, "message.complete.update");
			// 再検索処理
			forward = "reSearch";
		} catch (PkgRdirectionLogicException e) {
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

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績共通ロジッククラスを設定
	 * @param pkgRdirectionCommonsLogic 包装指図共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(
			final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}

	/**
	 * 包装実績－検索画面のロジッククラス設定
	 * @param pkgRdirectionListLogic 包装実績－検索画面のロジッククラス
	 */
	public void setPkgRdirectionListLogic(
			final PkgRdirectionListLogic pkgRdirectionListLogic) {
		this.pkgRdirectionListLogic = pkgRdirectionListLogic;
	}

	/**
	 * 包装記録ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param pkgRdirectionListExcelDecorator 包装記録ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setPkgRdirectionListExcelDecorator(
			final PkgRdirectionListExcelDecorator pkgRdirectionListExcelDecorator) {
		this.pkgRdirectionListExcelDecorator = pkgRdirectionListExcelDecorator;
	}

	/**
	 * 製品ラベルＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param pkgRdirectionListLabelExcelDecorator 包装指図一覧ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setPkgRdirectionListLabelExcelDecorator(
			final PkgRdirectionListLabelExcelDecorator pkgRdirectionListLabelExcelDecorator) {
		this.pkgRdirectionListLabelExcelDecorator = pkgRdirectionListLabelExcelDecorator;
	}

	/**
	 * 帳票ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param pkgRdirectionReportListExcelDecorator 帳票ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setPkgRdirectionReportListExcelDecorator(
			final PkgRdirectionReportListExcelDecorator pkgRdirectionReportListExcelDecorator) {
		this.pkgRdirectionReportListExcelDecorator = pkgRdirectionReportListExcelDecorator;
	}

	/**
	 * commonProcDaoを設定します。
	 * @param commonProcDao commonProcDao
	 */
	public void setCommonProcDao(CommonProcDao commonProcDao) {
		this.commonProcDao = commonProcDao;
	}
}
