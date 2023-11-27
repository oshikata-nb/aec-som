/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import com.asahikaseieng.app.comboboxes.SelectDirectionListStatus;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造指図検索 Actionクラス.
 * @author tosco
 * 
 */
public final class DirectionListAction extends AbstractSearchAction {

	/** 基本処方検索のロジッククラス */
	private DirectionListLogic directionListLogic;

	/** 製造指図一覧ＥＸＣＥＬファイル作成ロジッククラス */
	private DirectionListExcelDecorator directionListExcelDecorator;

	/** 原材料使用品リストＥＸＣＥＬファイル作成ロジッククラス */
	private DirectionListMaterialExcelDecorator directionListMaterialExcelDecorator;

	/** 製造指図帳票ＥＸＣＥＬファイル作成ロジッククラス */
	private DirectionOrderListExcelDecorator directionOrderListExcelDecorator;

	/* 「％」 */
	private static final String STR_PERCENT;

	static {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		STR_PERCENT = rb.getString("item.tani.percent");
	}

	/**
	 * コンストラクタ.
	 */
	public DirectionListAction() {
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

		DirectionListForm listForm = (DirectionListForm) form;
		listForm.clear();
		// 生産ラインコンボボックス
		listForm.setLineCombo(directionListLogic.createLineCombobox());
		// 初期検索設定
		DirectionDirectionHeaderListPagerCondition condition = (DirectionDirectionHeaderListPagerCondition) listForm
				.getPager().getPagerCondition();
		listForm.setDirectionStatus(Integer
				.parseInt(DirectionConst.COMBO_ALL_VALUE));
		condition.setDirectionStatus(DirectionConst.COMBO_ALL_VALUE);
		for (ComboBoxItems item : listForm.getLineCombo()) {
			// 最初の1件目の値を設定
			listForm.setProductionLine(item.getValues());
			condition.setProductionLine(item.getValues());
			break;
		}
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
		DirectionListForm listForm = (DirectionListForm) form;

		// クリア
		listForm.setSearchList(new ArrayList<DirectionDirectionHeaderList>());
		if (!listForm.getOp().equals("reFresh")) {
			listForm.setExcelDownloadFlg(Boolean.FALSE);
		}

		// 検索条件を取得
		DirectionDirectionHeaderListPagerCondition condition = (DirectionDirectionHeaderListPagerCondition) listForm
				.getPager().getPagerCondition();
		if (listForm.getOp().equals("reSearch")
				|| listForm.getOp().equals("reFresh")) {
			// 他画面から戻ってきた場合
			listForm
					.setDirectionNo(removeLikeString(condition.getDirectionNo())); // 指図区分

			if (StringUtils.isEmpty(condition.getProductionLine())) {
				// 生産工場=0:すべての場合
				listForm.setProductionLine(MgrecipeConst.COMBO_ALL_VALUE);
			} else {
				// 生産工場=0:すべて以外の場合
				listForm.setProductionLine(condition.getProductionLine());
			}

			listForm.setItemCd(removeLikeString(condition.getItemCd())); // 品目コード
			listForm.setItemName(null); // 品目コード
			listForm.setOtherCompanyCd1(removeLikeString(condition
					.getOtherCompanyCd1())); // 他社コード１
			listForm.setAspOrderNo(removeLikeString(condition.getAspOrderNo()));
			listForm.setCompoundTankNo(removeLikeString(condition
					.getCompoundTankNo()));
			// ステータス
			String directionStatus = condition.getDirectionStatus();
			if (StringUtils.isNotEmpty(directionStatus)) {
				listForm.setDirectionStatus(Integer.parseInt(directionStatus));
			} else {
				listForm.setDirectionStatus(Integer
						.parseInt(DirectionConst.COMBO_ALL_VALUE));
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
			condition.setPlanedSdateDateFrom(listForm.getPlanedSdateDateFrom()); // 開始予定日(From)
			condition.setPlanedEdateDateFrom(listForm.getPlanedEdateDateFrom()); // 終了予定日(From)
			condition.setPlanedSdateDateTo(listForm.getPlanedSdateDateTo()); // 開始予定日(To)
			condition.setPlanedEdateDateTo(listForm.getPlanedEdateDateTo()); // 終了予定日(To)
			condition.setOtherCompanyCd1(listForm.getOtherCompanyCd1()); // 他社コード１
			condition.setAspOrderNo(listForm.getAspOrderNo()); // ASPオーダーコード
			condition.setCompoundTankNo(listForm.getCompoundTankNo()); // 調合タンク

			// 指図ステータス
			condition.setDirectionStatus(String.valueOf(listForm
					.getDirectionStatus()));
		}
		/* 帳票(Excel)用に検索条件を保持 */
		DirectionOrderListConditionForReport reportCondition = new DirectionOrderListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		listForm.setReportCondition(reportCondition);

		// 処方ヘッダを検索
		List<DirectionDirectionHeaderList> result = directionListLogic
				.getSearchList(condition);
		// ステータス・数値の編集
		editResult(result, request);
		listForm.setSearchList(result);
		listForm.setInsufficientFlag("false");
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

		DirectionListForm frm = (DirectionListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = directionOrderListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * ステータス・仕込数量を表示用に編集する。
	 * @param result 検索結果
	 * @param request HttpServletRequest
	 */
	private void editResult(final List<DirectionDirectionHeaderList> result,
			final HttpServletRequest request) {
		// 数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (DirectionDirectionHeaderList bean : result) {
			// 指図ステータス名称
			bean.setStatusName(SelectDirectionListStatus
					.getName(getBigDecimalString(bean.getDirectionStatus())));
			// String unitDivision = bean.getUnitOfOperationManagement();
			// 仕込数量
			bean.setPlandQtyString(checker.format(
				DirectionConst.UNIT_DIVISION_HAIGO, bean.getPlanedQty()));
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
	 * 製造指図書発行
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
		DirectionListForm listForm = (DirectionListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		boolean isSelected = false;
		TreeMap<String, String> tMap = new TreeMap<String, String>();
		listForm.setExcelDownloadFlg(Boolean.FALSE);
		try {
			for (DirectionDirectionHeaderList bean : listForm.getSearchList()) {
				if (!bean.isSelectedCheck()) {
					continue;
				}
				// 指図書発行対象データあり
				isSelected = true;
				String status = DirectionUtil.getBigDecimalString(bean
						.getDirectionStatus());
				// ステータス≠製造時以外、指図書を発行する。
				if (!SelectDirectionListStatus.MAKED.equals(status)) {

					// 製造指図書発行
					Map<String, List<String>> map = directionListLogic
							.issuance(bean, tantoCd);

					// 設備マスタの指図書発行有無フラグが、2:なし以外の時
					if (!DirectionConst.ORDER_PUBLISH_FLG_OFF.equals(bean
							.getOrderPublishFlg())) {

						// 計装I/Fテーブルの登録
						directionListLogic.insertIfTable(bean, map);
					}

					// 正常に処理された製造指図番号を保存する
					tMap.put(bean.getDirectionNo(), bean.getDirectionNo());
				}
			}
			if (!isSelected) {
				// 一つも選択されていない場合
				DirectionLogicException ex = new DirectionLogicException();
				ex.setKey("errors.direction.selected.checkbox");
				throw ex;
			}
			// 製造指図書作成
			createReport(listForm, request, tMap);

			// 処理成功メッセージ
			saveMessage(request, "message.complete.update");
			forward = "reFresh";

		} catch (DirectionLogicException e) {
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
			// 警告ダイアログ表示エラーメッセージを設定
			listForm.setErrMsg(getErrorMessage(request, e.getKey(),
				replacements));

			// 製造指図書作成
			createReport(listForm, request, tMap);
		}
		return mapping.findForward(forward);
	}

	/**
	 * 製造指図書作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 * @param tMap 発行対象
	 */
	private void createReport(final DirectionListForm frm,
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

		// 製造指図書を作成
		info = directionListExcelDecorator.createReport(directionNoArray,
			tantoNm, AecDateUtils.getCurrentTimestamp(), request);
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(Boolean.TRUE);
		tMap.clear();
	}

	/**
	 * 原材料出庫指図書発行
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issuanceMaterial(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issuanceMaterial.");
		}
		DirectionListForm listForm = (DirectionListForm) form;
		listForm.setExcelDownloadFlg(Boolean.FALSE);
		ArrayList<String> directionNoArray = new ArrayList<String>();
		FileDownloadInfo info = null;
		String tantoNm = getLoginInfo(request).getTantoNm();

		for (DirectionDirectionHeaderList bean : listForm.getSearchList()) {
			if (!bean.isSelectedCheck()) {
				continue;
			}

			// 出力対象の製造指図番号を保持する
			directionNoArray.add(bean.getDirectionNo());
		}

		// 原材料出庫指図書を作成
		info = directionListMaterialExcelDecorator.createReport(
			directionNoArray, tantoNm, AecDateUtils.getCurrentTimestamp(),
			request);
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		listForm.setExcelDownloadFlg(Boolean.TRUE);

		return mapping.findForward("reFresh");
	}

	/**
	 * 製造指図書発行準備処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward preIssuance(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("preIssuance.");
		}
		List<String> msgList = new ArrayList<String>();
		DirectionListForm listForm = (DirectionListForm) form;
		boolean isSelected = false;
		listForm.setExcelDownloadFlg(Boolean.FALSE);
		listForm.setInsufficientFlag("false");
		int sai = 0;
		int first = 0;
		boolean okflg = true;
		try {
			for (DirectionDirectionHeaderList bean : listForm.getSearchList()) {
				if (!bean.isSelectedCheck()) {
					continue;
				}
				// 指図書発行対象データあり
				isSelected = true;
				String status = DirectionUtil.getBigDecimalString(bean
						.getDirectionStatus());

				// 未確定時、チェックを行う
				if (SelectDirectionListStatus.UNFIXED.equals(status)) {

					first++;
					List<String> eList = directionListLogic
							.checkInventoryFormula(bean.getDirectionNo());
					if (!eList.isEmpty()) {
						okflg = false;
						for (String mes : eList) {
							msgList.add(mes);
						}
					}
				} else {
					sai++;
				}
			}
			if (!isSelected) {
				// 一つも選択されていない場合
				DirectionLogicException ex = new DirectionLogicException();
				ex.setKey("errors.direction.selected.checkbox");
				throw ex;
			}
		} catch (DirectionLogicException e) {
			throw e;
		}
		if (okflg) {
			// return mapping.findForward("success");
			return issuance(mapping, form, request, response);
		}
		listForm.setInsufficientFlag("true");
		ActionMessages messages = new ActionMessages();

		for (String mes : msgList) {
			messages.add("",
				new ActionMessage("errors.shipping.auto.make", mes));
		}
		messages.add("", new ActionMessage("errors.shipping.auto.make",
				"発行を継続するには、発行ボタンを再クリックしてください。"));

		saveErrors(request, messages);
		return mapping.findForward("success");
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

	/**
	 * 置換エラーメッセージを取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param replacements 置換パラメータ
	 */
	private String getErrorMessage(final HttpServletRequest request,
			final String key, final String[] replacements) {
		String errMsg;
		errMsg = getMessageResource(request, key);
		if (replacements != null) {
			int len = replacements.length;
			for (int i = 0; i < len; i++) {
				String repl = "{" + String.valueOf(i) + "}";
				errMsg = StringUtils.replace(errMsg, repl, replacements[i]);
			}
		}
		return errMsg;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図検索のロジッククラスを設定します。
	 * @param directionListLogic 製造指図検索のロジッククラス
	 */
	public void setDirectionListLogic(
			final DirectionListLogic directionListLogic) {
		this.directionListLogic = directionListLogic;
	}

	/**
	 * 製造指図一覧ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param directionListExcelDecorator 製造指図一覧ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setDirectionListExcelDecorator(
			final DirectionListExcelDecorator directionListExcelDecorator) {
		this.directionListExcelDecorator = directionListExcelDecorator;
	}

	/**
	 * 原材料使用品リストＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param directionListMaterialExcelDecorator 原材料使用品リストＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setDirectionListMaterialExcelDecorator(
			final DirectionListMaterialExcelDecorator directionListMaterialExcelDecorator) {
		this.directionListMaterialExcelDecorator = directionListMaterialExcelDecorator;
	}

	/**
	 * 製造指図帳票ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param directionOrderListExcelDecorator 製造指図帳票ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setDirectionOrderListExcelDecorator(
			final DirectionOrderListExcelDecorator directionOrderListExcelDecorator) {
		this.directionOrderListExcelDecorator = directionOrderListExcelDecorator;
	}
}
