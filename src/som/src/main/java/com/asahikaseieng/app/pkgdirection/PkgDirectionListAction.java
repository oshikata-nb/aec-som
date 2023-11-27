/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.asahikaseieng.dao.entity.keikakuhoso.KeikakuHoso;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 包装指図－検索画面 Actionクラス
 * @author tosco
 * 
 */
public final class PkgDirectionListAction extends AbstractSearchAction {

	/** 包装指図共通ロジッククラス */
	private PkgDirectionCommonsLogic pkgDirectionCommonsLogic;

	/** 包装指図－検索画面のロジッククラス */
	private PkgDirectionListLogic pkgDirectionListLogic;

	/** 包装指図一覧ＥＸＣＥＬファイル作成ロジッククラス */
	private PkgDirectionListExcelDecorator pkgDirectionListExcelDecorator;

	/** 製品ラベルＥＸＣＥＬファイル作成ロジッククラス */
	private PkgDirectionListLabelExcelDecorator pkgDirectionListLabelExcelDecorator;

	/** 原材料使用品リストＥＸＣＥＬファイル作成ロジッククラス */
	private PkgDirectionListMaterialExcelDecorator pkgDirectionListMaterialExcelDecorator;

	/** 「％」 */
	private static final String STR_PERCENT;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59:59";

	/** 指図ステータス 2:指図書発行済 */
	private static final BigDecimal DIRECTION_STATUS_ISSUED = new BigDecimal(2);

	static {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		STR_PERCENT = rb.getString("item.tani.percent");
	}

	/** 製造指図帳票ＥＸＣＥＬファイル作成ロジッククラス */
	private PkgDirectionOrderListExcelDecorator pkgDirectionOrderListExcelDecorator;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionListAction() {
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

		PkgDirectionListForm listForm = (PkgDirectionListForm) form;
		listForm.clear();

		PkgDirectionListPagerCondition condition = (PkgDirectionListPagerCondition) listForm
				.getPager().getPagerCondition();

		// 生産工場コンボボックス
		listForm
				.setLineCombo(pkgDirectionCommonsLogic.createLineCombobox(true));

		// コンボボックスの初期選択値を設定
		SelectPkgDirectionDirectionDivision directDivCmb = new SelectPkgDirectionDirectionDivision(
				false, true);
		for (ComboBoxItems item : directDivCmb.getComboBoxList()) {
			listForm.setDirectionDivision(item.getValues());
			condition.setDirectionDivision(new BigDecimal(item.getValues()));
			break;
		}
		listForm.setDirectionStatus(PkgDirectionConst.COMBO_ALL_VALUE
				.toString());
		condition.setDirectionStatus(PkgDirectionConst.COMBO_ALL_VALUE);
		for (ComboBoxItems item : listForm.getLineCombo()) {
			listForm.setProductionLine(item.getValues());
			condition.setProductionLine(item.getValues());
			break;
		}
		return mapping.findForward("success");
	}

	/**
	 * 包装指図検索処理(検索ボタン押下時)
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

		PkgDirectionListForm listForm = (PkgDirectionListForm) form;

		// クリア
		listForm.setSearchList(new ArrayList<PkgDirectionList>());
		if (!listForm.getOp().equals("reFresh")) {
			listForm.setExcelDownloadFlg(Boolean.FALSE);
		}

		// 検索条件を取得
		PkgDirectionListPagerCondition condition = (PkgDirectionListPagerCondition) listForm
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
				listForm.setProductionLine(PkgDirectionConst.COMBO_ALL_VALUE
						.toString());
			} else {
				// 生産工場=0:すべて以外の場合
				listForm.setProductionLine(condition.getProductionLine());
			}

			listForm.setItemCd(removeLikeString(condition.getItemCd()));
			listForm.setItemName(condition.getItemName());
			listForm.setOtherCompanyCd1(removeLikeString(condition
					.getOtherCompanyCd1()));
			listForm.setPlanedSDayFrom(condition.getPlanedSDayFrom());
			listForm.setPlanedSTimeFrom(condition.getPlanedSTimeFrom());
			listForm.setPlanedSDayTo(condition.getPlanedSDayTo());
			listForm.setPlanedSTimeTo(condition.getPlanedSTimeTo());
			listForm.setPlanedEDayFrom(condition.getPlanedEDayFrom());
			listForm.setPlanedETimeFrom(condition.getPlanedETimeFrom());
			listForm.setPlanedEDayTo(condition.getPlanedEDayTo());
			listForm.setPlanedETimeTo(condition.getPlanedETimeTo());
			listForm.setAspOrderNo(removeLikeString(condition.getAspOrderNo()));
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

			if (PkgDirectionConst.COMBO_ALL_VALUE.toString().equals(
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
			String strDay = listForm.getPlanedSDayFrom();
			String strTime = listForm.getPlanedSTimeFrom();
			condition.setPlanedSdateFrom(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MIN_TIME));
			condition.setPlanedSDayFrom(strDay);
			condition.setPlanedSTimeFrom(strTime);

			/** 包装開始予定日時(TO) */
			strDay = listForm.getPlanedSDayTo();
			strTime = listForm.getPlanedSTimeTo();
			condition.setPlanedSdateTo(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MAX_TIME));
			condition.setPlanedSDayTo(strDay);
			condition.setPlanedSTimeTo(strTime);

			/** 包装終了予定日時(FROM) */
			strDay = listForm.getPlanedEDayFrom();
			strTime = listForm.getPlanedETimeFrom();
			condition.setPlanedEdateFrom(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MIN_TIME));
			condition.setPlanedEDayFrom(strDay);
			condition.setPlanedETimeFrom(strTime);

			/** 包装終了予定日時(TO) */
			strDay = listForm.getPlanedEDayTo();
			strTime = listForm.getPlanedETimeTo();
			condition.setPlanedEdateTo(getTimestampYmdHmsFormat(strDay,
				strTime, STR_MAX_TIME));
			condition.setPlanedEDayTo(strDay);
			condition.setPlanedETimeTo(strTime);
			condition.setAspOrderNo(listForm.getAspOrderNo()); // ASPオーダーコード
			condition.setPackageLine(listForm.getPackageLine());
		}

		/* 帳票(Excel)用に検索条件を保持 */
		PkgDirectionOrderListConditionForReport reportCondition = new PkgDirectionOrderListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		listForm.setReportCondition(reportCondition);

		// 製造指図ヘッダーを検索
		List<PkgDirectionList> result = pkgDirectionListLogic
				.getSearchList(condition);
		listForm.setSearchList(result);

		listForm.setInsufficientFlag("false");

		return mapping.findForward("success");
	}

	/**
	 * 再描画処理
	 * 
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reDraw(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		PkgDirectionListForm listForm = (PkgDirectionListForm) form;
		listForm.setExcelDownloadFlg(Boolean.FALSE);
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

		PkgDirectionListForm frm = (PkgDirectionListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = pkgDirectionOrderListExcelDecorator
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
	 * 包装指図書発行処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issueDirection(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issueDirection.");
		}

		PkgDirectionListForm frm = (PkgDirectionListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		String forward = "success";
		ArrayList<String> directionNoList = new ArrayList<String>();
		frm.setPrintDirectionNoList(null);
		frm.setExcelDownloadFlg(false);

		try {
			for (PkgDirectionList bean : frm.getSearchList()) {
				if (!bean.isCheckFlg()) {
					// チェック無
					continue;
				}
				// 更新処理を実行
				KeikakuHoso hosoBean = pkgDirectionListLogic.issueDirection(
					bean, tantoCd);

				// 計装I/Fテーブルの登録
				if (hosoBean != null) {
					pkgDirectionListLogic.insertIfTable(hosoBean);
				}

				// 正常に処理された製造指図番号を保存する
				directionNoList.add(bean.getDirectionNo());
			}
			// 出力対象の製造指図番号を設定
			frm.setPrintDirectionNoList(directionNoList);

			// 包装指図書作成
			createReport(frm, request);

			/* メッセージ */
			saveMessage(request, "message.complete.update");
			// 再検索処理
			forward = "reFresh";

		} catch (PkgDirectionLogicException e) {
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
			frm.setErrMsg(getErrorMessage(request, e.getKey(), replacements));

			// 出力対象の製造指図番号を設定
			frm.setPrintDirectionNoList(directionNoList);

			// 包装指図書作成
			createReport(frm, request);
		}
		return mapping.findForward(forward);
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
		PkgDirectionListForm frm = (PkgDirectionListForm) form;
		frm.setPrintDirectionNoList(null);
		frm.setExcelDownloadFlg(false);
		frm.setInsufficientFlag("false");
		boolean okflg = true;
		try {
			for (PkgDirectionList bean : frm.getSearchList()) {
				if (!bean.isCheckFlg()) {
					continue;
				}
				if (bean.getDirectionStatus()
						.compareTo(DIRECTION_STATUS_ISSUED) == 0) {
					continue;
				}
				List<String> eList = pkgDirectionListLogic
						.checkInventoryFormula(bean.getDirectionNo(), bean
								.getDirectionDivision());
				if (!eList.isEmpty()) {
					okflg = false;
					for (String mes : eList) {
						msgList.add(mes);
					}
				}
			}
		} catch (PkgDirectionLogicException e) {
			throw e;
		}
		if (okflg) {
			// return mapping.findForward("success");
			return issueDirection(mapping, form, request, response);
		}
		frm.setInsufficientFlag("true");
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

		PkgDirectionListForm frm = (PkgDirectionListForm) form;
		frm.setExcelDownloadFlg(false);
		String tantoCd = getLoginInfo(request).getTantoCd();
		TreeMap<String, String> tMap = new TreeMap<String, String>();

		/* 更新処理を実行 */
		pkgDirectionListLogic.issueLabel(frm.getSearchList(), tantoCd);

		// 出力対象のデータを保持
		for (PkgDirectionList bean : frm.getSearchList()) {

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
	 * 包装指図書作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 */
	private void createReport(final PkgDirectionListForm frm,
			final HttpServletRequest request) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;
		ArrayList<String> directionNoList = frm.getPrintDirectionNoList();
		if (directionNoList.isEmpty()) {
			frm.setPrintDirectionNoList(null);
			frm.setExcelDownloadFlg(false);
			return;
		}
		String[] directionNoArray = new String[directionNoList.size()];
		int idx = 0;
		for (String directionNo : directionNoList) {
			directionNoArray[idx] = directionNo;
			idx++;
		}

		// 包装指図書を作成
		info = pkgDirectionListExcelDecorator.createReport(directionNoArray,
			tantoNm, AecDateUtils.getCurrentTimestamp(), request
					.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(true);
		frm.setPrintDirectionNoList(null);
	}

	/**
	 * 製品ラベル作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 */
	private void createLabel(final PkgDirectionListForm frm,
			final HttpServletRequest request, final TreeMap<String, String> tMap) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		// 製品ラベルを作成
		info = pkgDirectionListLabelExcelDecorator.createReport(tMap, tantoNm,
			AecDateUtils.getCurrentTimestamp(), request.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(true);
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
	public ActionForward issueMaterial(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issueMaterial.");
		}
		PkgDirectionListForm frm = (PkgDirectionListForm) form;
		TreeMap<String, String> tMap = new TreeMap<String, String>();
		frm.setExcelDownloadFlg(Boolean.FALSE);
		ArrayList<String> directionNoArray = new ArrayList<String>();
		FileDownloadInfo info = null;
		String tantoNm = getLoginInfo(request).getTantoNm();

		for (PkgDirectionList bean : frm.getSearchList()) {
			if (!bean.isCheckFlg()) {
				continue;
			}
			// 出力対象の製造指図番号を保持する
			directionNoArray.add(bean.getDirectionNo());
		}

		// 原材料出庫指図書を作成
		info = pkgDirectionListMaterialExcelDecorator.createReport(
			directionNoArray, tantoNm, AecDateUtils.getCurrentTimestamp(),
			request.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(Boolean.TRUE);
		tMap.clear();

		return mapping.findForward("reFresh");
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
	 * 包装指図共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 包装指図共通ロジッククラス
	 */
	public void setPkgDirectionCommonsLogic(
			final PkgDirectionCommonsLogic pkgDirectionCommonsLogic) {
		this.pkgDirectionCommonsLogic = pkgDirectionCommonsLogic;
	}

	/**
	 * 基本処方検索のロジッククラスを設定します。
	 * @param pkgDirectionListLogic 基本処方検索のロジッククラス
	 */
	public void setPkgDirectionListLogic(
			final PkgDirectionListLogic pkgDirectionListLogic) {
		this.pkgDirectionListLogic = pkgDirectionListLogic;
	}

	/**
	 * 包装指図一覧ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param pkgDirectionListExcelDecorator 包装指図一覧ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setPkgDirectionListExcelDecorator(
			final PkgDirectionListExcelDecorator pkgDirectionListExcelDecorator) {
		this.pkgDirectionListExcelDecorator = pkgDirectionListExcelDecorator;
	}

	/**
	 * 製品ラベルＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param pkgDirectionListLabelExcelDecorator 製品ラベルＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setPkgDirectionListLabelExcelDecorator(
			final PkgDirectionListLabelExcelDecorator pkgDirectionListLabelExcelDecorator) {
		this.pkgDirectionListLabelExcelDecorator = pkgDirectionListLabelExcelDecorator;
	}

	/**
	 * 原材料使用品リストＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param pkgDirectionListMaterialExcelDecorator 原材料使用品リストＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setPkgDirectionListMaterialExcelDecorator(
			final PkgDirectionListMaterialExcelDecorator pkgDirectionListMaterialExcelDecorator) {
		this.pkgDirectionListMaterialExcelDecorator = pkgDirectionListMaterialExcelDecorator;
	}

	/**
	 * 原材料使用品リスト帳票ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param pkgDirectionOrderListExcelDecorator 原材料使用品リスト帳票ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setPkgDirectionOrderListExcelDecorator(
			final PkgDirectionOrderListExcelDecorator pkgDirectionOrderListExcelDecorator) {
		this.pkgDirectionOrderListExcelDecorator = pkgDirectionOrderListExcelDecorator;
	}
}
