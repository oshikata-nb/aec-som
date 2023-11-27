/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.beforehandmeltlbl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import com.asahikaseieng.app.direction.DirectionConst;
import com.asahikaseieng.dao.entity.labelyobiyokai.LabelYobiyokai;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblList;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblPagerCondition;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 予備溶解ラベル発行画面 Actionクラス.
 * @author tosco
 * 
 */
public final class BeforehandMeltLblListAction extends AbstractSearchAction {

	/** 予備溶解ラベル発行画面のロジッククラス */
	private BeforehandMeltLblListLogic beforehandMeltLblListLogic;

	/** 予備溶解ラベルＥＸＣＥＬファイル作成ロジッククラス */
	private BeforehandMeltLblListExcelDecorator beforehandMeltLblListExcelDecorator;

	/** 予備溶解ラベル帳票ＥＸＣＥＬファイル作成ロジッククラス */
	private BeforehandMeltLblReportListExcelDecorator beforehandMeltLblReportListExcelDecorator;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59:59";

	/**
	 * コンストラクタ.
	 */
	public BeforehandMeltLblListAction() {
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

		// フォーム取得
		BeforehandMeltLblListForm frm = (BeforehandMeltLblListForm) form;

		// 生産工場コンボボックス
		frm.setLineCombo(beforehandMeltLblListLogic.createLineCombobox(true));

		// システム日時取得を取得し、日付を製造開始予定日時のデフォルトとしてセットする
		Calendar cal1 = Calendar.getInstance(); // システム日時を取得
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		frm.setSrhPlanedSDayFrom(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット
		frm.setSrhPlanedEDayFrom(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット

		// 初期検索無し
		return mapping.findForward("success");
	}

	/**
	 * 検索処理(検索ボタン押下時)
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

		// フォーム取得
		BeforehandMeltLblListForm frm = (BeforehandMeltLblListForm) form;

		// クリア
		frm.setSearchList(new ArrayList<BeforehandMeltLblList>());

		// 検索条件を取得
		BeforehandMeltLblPagerCondition condition = (BeforehandMeltLblPagerCondition) frm
				.getPager().getPagerCondition();
		// 検索条件をセット
		condition.setSrhDirectionNo(frm.getSrhDirectionNo()); // 指図番号

		String productionLine = frm.getSrhProductionLine(); // 生産工場

		if (DirectionConst.COMBO_ALL_VALUE.equals(productionLine)) {
			// 生産工場=0:すべての場合
			condition.setSrhProductionLine(null);
		} else {
			// 生産工場=0:すべて以外の場合
			condition.setSrhProductionLine(productionLine);
		}

		condition.setSrhItemCd(frm.getSrhItemCd()); // 主要製品コード
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1()); // 他社コード１

		// 製造開始予定日時(FROM)
		String strDay = frm.getSrhPlanedSDayFrom();
		String strTime = frm.getSrhPlanedSTimeFrom();
		condition.setSrhPlanedSdateFrom(getTimestampYmdHmsFormat(strDay,
			strTime, STR_MIN_TIME));

		// 製造開始予定日時(TO)
		strDay = frm.getSrhPlanedSDayTo();
		strTime = frm.getSrhPlanedSTimeTo();
		condition.setSrhPlanedSdateTo(getTimestampYmdHmsFormat(strDay, strTime,
			STR_MAX_TIME));

		// 製造終了予定日時(FROM)
		strDay = frm.getSrhPlanedEDayFrom();
		strTime = frm.getSrhPlanedETimeFrom();
		condition.setSrhPlanedEdateFrom(getTimestampYmdHmsFormat(strDay,
			strTime, STR_MIN_TIME));

		// 製造終了予定日時(TO)
		strDay = frm.getSrhPlanedEDayTo();
		strTime = frm.getSrhPlanedETimeTo();
		condition.setSrhPlanedEdateTo(getTimestampYmdHmsFormat(strDay, strTime,
			STR_MAX_TIME));

		/* 帳票(Excel)用に検索条件を保持 */
		BeforehandMeltLblListConditionForReport reportCondition = new BeforehandMeltLblListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(beforehandMeltLblListLogic.getSearchList(condition));
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

		BeforehandMeltLblListForm frm = (BeforehandMeltLblListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = beforehandMeltLblReportListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * ラベル発行処理(ラベル発行ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issue(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issue.");
		}
		String forward = "success";

		// formを取得
		BeforehandMeltLblListForm frm = (BeforehandMeltLblListForm) form;

		// フォームより検索結果取得
		List<BeforehandMeltLblList> searchList = frm.getSearchList();

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		List<LabelYobiyokai> list = null;
		ArrayList<String> directionNoArray = new ArrayList<String>();

		try {

			// 予備溶解ラベル発行画面_検索結果分ループ
			for (BeforehandMeltLblList bean : searchList) {
				if (!bean.isBeforehandMeltLblCheckBox()) {
					continue;
				}

				// 予備溶解ラベル発行
				list = beforehandMeltLblListLogic.insertLabelYobiyokai(bean,
					loginUserId);

				if (list != null && list.size() > 0) {

					// 計装I/Fテーブルの登録
					beforehandMeltLblListLogic.insertIfTable(list, bean
							.getDirectionNo());

				}
				// 出力対象の製造指図番号を保持する
				directionNoArray.add(bean.getDirectionNo());
			}
			// 製品ラベル作成
			createLabel(frm, request, directionNoArray);

			// ラベル発行完了メッセージの登録
			saveMessage(request, "message.beforehandmeltlbl.complete.issue");
			forward = "reSearch";

		} catch (BeforehandMeltLblLogicException e) {
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
			// 製品ラベル作成
			createLabel(frm, request, directionNoArray);

			// 警告ダイアログ表示エラーメッセージを設定
			frm.setErrMsg(getErrorMessage(request, e.getKey(), replacements));

		}
		return mapping.findForward(forward);
	}

	/**
	 * 製品ラベル作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 */
	private void createLabel(final BeforehandMeltLblListForm frm,
			final HttpServletRequest request,
			final ArrayList<String> directionNoArray) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		if (directionNoArray.size() > 0) {

			// 製品ラベルを作成
			info = beforehandMeltLblListExcelDecorator.createReport(
				directionNoArray, tantoNm, AecDateUtils.getCurrentTimestamp(),
				request.getRemoteAddr());
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			frm.setExcelDownloadFlg(true);

		}
	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		// フォームの取得
		BeforehandMeltLblListForm frm = (BeforehandMeltLblListForm) form;

		// クリア
		frm.clear();

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
	 * 予備溶解ラベル発行画面ロジッククラスを設定します。
	 * @param beforehandMeltLblListLogic 予備溶解ラベル発行画面ロジッククラス
	 */
	public void setBeforehandMeltLblListLogic(
			final BeforehandMeltLblListLogic beforehandMeltLblListLogic) {
		this.beforehandMeltLblListLogic = beforehandMeltLblListLogic;
	}

	/**
	 * 予備溶解ラベルＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param beforehandMeltLblListExcelDecorator 予備溶解ラベルＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setBeforehandMeltLblListExcelDecorator(
			final BeforehandMeltLblListExcelDecorator beforehandMeltLblListExcelDecorator) {
		this.beforehandMeltLblListExcelDecorator = beforehandMeltLblListExcelDecorator;
	}

	/**
	 * 予備溶解ラベル帳票ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param beforehandMeltLblReportListExcelDecorator
	 *            予備溶解ラベル帳票ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setBeforehandMeltLblReportListExcelDecorator(
			final BeforehandMeltLblReportListExcelDecorator beforehandMeltLblReportListExcelDecorator) {
		this.beforehandMeltLblReportListExcelDecorator = beforehandMeltLblReportListExcelDecorator;
	}
}
