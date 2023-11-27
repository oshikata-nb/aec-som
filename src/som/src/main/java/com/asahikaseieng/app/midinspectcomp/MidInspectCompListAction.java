/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.midinspectcomp;

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
import com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompList;
import com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompPagerCondition;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 中間品検査完了入力 Actionクラス.
 * @author tosco
 * 
 */
public final class MidInspectCompListAction extends AbstractSearchAction {

	/** 中間品検査完了入力のロジッククラス */
	private MidInspectCompListLogic midInspectCompListLogic;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59:59";

	/** 現工程 44:中間品最終検査 */
	private static final String PROCESS = "44";

	private MidInspectCompExcelDecorator midInspectCompExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public MidInspectCompListAction() {
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
		MidInspectCompListForm frm = (MidInspectCompListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_MID_INSPECT_COMP,
			Constants.TAB_ID_MID_INSPECT_COMP_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 生産工場コンボボックスを作成し、セット
		frm.setLineCombo(midInspectCompListLogic.createLineCombobox(true));

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
		MidInspectCompListForm frm = (MidInspectCompListForm) form;

		// 検索結果のクリア
		frm.setSearchList(new ArrayList<MidInspectCompList>());

		// 検索条件を取得
		MidInspectCompPagerCondition condition = (MidInspectCompPagerCondition) frm
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

		// 指図ステータス
		condition.setSrhDirectionStatus(frm.getSrhDirectionStatus());

		/* 帳票(Excel)用に検索条件を保持 */
		MidInspectCompListConditionForReport reportCondition = new MidInspectCompListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(midInspectCompListLogic.getSearchList(condition));
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

		MidInspectCompListForm frm = (MidInspectCompListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = midInspectCompExcelDecorator.createReport(frm
				.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 中間品検査完了入力 登録処理(登録ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issue.");
		}
		String forward = "success";

		// formを取得
		MidInspectCompListForm frm = (MidInspectCompListForm) form;

		// フォームより検索結果取得
		List<MidInspectCompList> searchList = frm.getSearchList();

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			for (MidInspectCompList bean : searchList) {
				if (!bean.isMidInspectCompCheckBox()) {
					continue;
				}

				// 中間品検査完了入力 更新処理
				midInspectCompListLogic.update(bean, loginUserId);

				// 設備マスタの指図書発行有無フラグが、2:なし以外の時
				if (!DirectionConst.ORDER_PUBLISH_FLG_OFF.equals(bean
						.getOrderPublishFlg())) {

					// 44:中間品最終検査の場合、インターロック解除データを送信
					if (bean.getCurrentStepNo() != null
							&& bean.getCurrentStepNo().equals(PROCESS)) {
						midInspectCompListLogic
								.updateIfTable(bean, loginUserId);
					}
				}
			}
			// 更新完了メッセージを登録
			saveMessage(request, "message.complete.update");
			forward = "reSearch";

		} catch (MidInspectCompLogicException e) {
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
		}
		return mapping.findForward(forward);
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
		MidInspectCompListForm frm = (MidInspectCompListForm) form;

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
	 * 中間品検査完了入力一覧画面ロジッククラスを設定します。
	 * @param midInspectCompListLogic 中間品検査完了入力一覧画面ロジッククラス
	 */
	public void setMIdInspectCompListLogic(
			final MidInspectCompListLogic midInspectCompListLogic) {
		this.midInspectCompListLogic = midInspectCompListLogic;
	}

	/**
	 * midInspectCompExcelDecoratorを設定します。
	 * @param midInspectCompExcelDecorator midInspectCompExcelDecorator
	 */
	public void setMidInspectCompExcelDecorator(
			final MidInspectCompExcelDecorator midInspectCompExcelDecorator) {
		this.midInspectCompExcelDecorator = midInspectCompExcelDecorator;
	}

}
