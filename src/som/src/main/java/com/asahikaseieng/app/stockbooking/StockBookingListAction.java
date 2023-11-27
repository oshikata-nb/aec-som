/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.stockbooking;

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
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingList;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingPagerCondition;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 検査待ち在庫計上画面 Actionクラス.
 * @author tosco
 * 
 */
public final class StockBookingListAction extends AbstractSearchAction {

	/** 検査待ち在庫計上画面のロジッククラス */
	private StockBookingListLogic stockBookingListLogic;

	private StockBookingListExcelDecorator stockBookingListExcelDecorator;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59:59";

	/**
	 * コンストラクタ.
	 */
	public StockBookingListAction() {
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
		StockBookingListForm frm = (StockBookingListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_STOCK_BOOKING,
			Constants.TAB_ID_STOCK_BOOKING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 生産工場コンボボックスを作成し、セット
		frm.setLineCombo(stockBookingListLogic.createLineCombobox(true));

		// システム日時取得を取得し、日付-1を包装日付のデフォルトとしてセットする
		Calendar cal1 = Calendar.getInstance(); // システム日時を取得
		cal1.add(Calendar.DAY_OF_MONTH, -1); // 日付-1
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		frm.setSrhResultEdateFrom(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット
		frm.setSrhResultEdateTo(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット

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
		StockBookingListForm frm = (StockBookingListForm) form;

		// 検索結果のクリア
		frm.setSearchList(new ArrayList<StockBookingList>());

		// 検索条件を取得
		StockBookingPagerCondition condition = (StockBookingPagerCondition) frm
				.getPager().getPagerCondition();

		// 検索条件をセット
		String productionLine = frm.getSrhProductionLine(); // 生産工場

		if (DirectionConst.COMBO_ALL_VALUE.equals(productionLine)) {
			// 生産工場=0:すべての場合
			condition.setSrhProductionLine(null);
		} else {
			// 生産工場=0:すべて以外の場合
			condition.setSrhProductionLine(productionLine);
		}

		// 包装日付をTimestamp型に変換してセット
		condition.setSrhResultEdateFrom(getTimestampYmdHmsFormat(frm
				.getSrhResultEdateFrom(), "", STR_MIN_TIME)); // 包装日付(From)
		condition.setSrhResultEdateTo(getTimestampYmdHmsFormat(frm
				.getSrhResultEdateTo(), "", STR_MAX_TIME)); // 包装日付(to)

		/* 帳票(Excel)用に検索条件を保持 */
		StockBookingListConditionForReport reportCondition = new StockBookingListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(stockBookingListLogic.getSearchList(condition));
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

		StockBookingListForm frm = (StockBookingListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = stockBookingListExcelDecorator.createReport(frm
				.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 登録処理(登録ボタン押下時)
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
			getLog().debug("update.");
		}

		// formを取得
		StockBookingListForm frm = (StockBookingListForm) form;

		// フォームより検索結果取得
		List<StockBookingList> searchList = frm.getSearchList();

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		String errorMessage = "";
		// 検索結果分ループ
		for (StockBookingList bean : searchList) {
			try {

				// チェックボックスにチェックが入っているもののみ
				if (!bean.isStockBookingCheckBox()) {
					continue;
				}
				// 検査待ち在庫計上 更新処理
				DirectionHeader header = stockBookingListLogic.update(bean,
					loginUserId);

				// PRO_IF_MATERIAL_IMPORT_RESULTを実行
				stockBookingListLogic.callProIf(header, loginUserId);
			} catch (StockBookingLogicException e) {
				String[] replacements = e.getReplacements();
				if (replacements != null) {
					int len = replacements.length;
					for (int i = 0; i < len; i++) {
						String buf = getMessageResource(request,
							replacements[i]);
						if (StringUtils.isNotEmpty(buf)) {
							replacements[i] = buf;
						}
					}
				}
				if (StringUtils.isEmpty(errorMessage)) {
					errorMessage = getErrorMessage(request, e.getKey(),
						replacements);
				} else {
					errorMessage = errorMessage
							+ "\n\r"
							+ getErrorMessage(request, e.getKey(), replacements);
				}
				if (StringUtils.isNotEmpty(e.getModuleCd())) {
					// エラーログに出力する
					stockBookingListLogic
							.outPutErrorLog(e.getModuleCd(),
								e.getInsideErrCd(), e.getInsideErrMsg(),
								loginUserId);
				}
			}
		}
		if (StringUtils.isEmpty(errorMessage)) {
			// 更新完了メッセージを登録
			saveMessage(request, "message.complete.update");

			return mapping.findForward("reSearch");

		} else {
			// 警告ダイアログ表示エラーメッセージを設定
			frm.setErrMsg(errorMessage);
			return mapping.getInputForward();

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
		StockBookingListForm frm = (StockBookingListForm) form;

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
	 * 検査待ち在庫計上一覧画面ロジッククラスを設定します。
	 * @param stockBookingListLogic 検査待ち在庫計上一覧画面ロジッククラス
	 */
	public void setStockBookingListLogic(
			final StockBookingListLogic stockBookingListLogic) {
		this.stockBookingListLogic = stockBookingListLogic;
	}

	/**
	 * 検査待ち在庫計上帳票一覧画面ロジッククラスを設定します。
	 * @param stockBookingListExcelDecorator 検査待ち在庫計上帳票一覧画面ロジッククラス
	 */
	public void setStockBookingListExcelDecorator(
			final StockBookingListExcelDecorator stockBookingListExcelDecorator) {
		this.stockBookingListExcelDecorator = stockBookingListExcelDecorator;
	}

}
