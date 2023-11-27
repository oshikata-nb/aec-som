/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.productinspectcomp;

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
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompList;
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompPagerCondition;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製品検査完了入力一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class ProductInspectCompListAction extends AbstractSearchAction {

	/** 製品検査完了入力一覧画面のロジッククラス */
	private ProductInspectCompListLogic productInspectCompListLogic;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59:59";

	private ProductInspectCompListExcelDecorator productInspectCompListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ProductInspectCompListAction() {
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
		ProductInspectCompListForm frm = (ProductInspectCompListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_PRODUCT_INSPECT_COMP,
			Constants.TAB_ID_PRODUCT_INSPECT_COMP_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 生産工場コンボボックスを作成し、セット
		frm.setLineCombo(productInspectCompListLogic.createLineCombobox(true));

		// システム日時取得を取得し、日付-1を包装開始日時のデフォルトとしてセットする
		Calendar cal1 = Calendar.getInstance(); // システム日時を取得
		cal1.add(Calendar.DAY_OF_MONTH, -1); // 日付-1
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		frm.setSrhResultSDayFrom(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット
		frm.setSrhResultEDayFrom(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット

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
		ProductInspectCompListForm frm = (ProductInspectCompListForm) form;

		// 検索結果のクリア
		frm.setSearchList(new ArrayList<ProductInspectCompList>());

		// 検索条件を取得
		ProductInspectCompPagerCondition condition = (ProductInspectCompPagerCondition) frm
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
		condition.setSrhPackageLine(frm.getSrhPackageLine()); // 包装ライン
		condition.setSrhCertificationDate(frm.getSrhCertificationDate()); // 検査合格日
		condition.setSrhLotNo(frm.getSrhLotNo()); // ロット番号
		condition.setSrhDirectionStatus(frm.getSrhDirectionStatus()); // 指図ステータス

		// 日と時をTimestmp型に変換してセット
		// 包装開始実績日時(FROM)
		String strDay = frm.getSrhResultSDayFrom();
		String strTime = frm.getSrhResultSTimeFrom();
		condition.setSrhResultSdateFrom(getTimestampYmdHmsFormat(strDay,
			strTime, STR_MIN_TIME));

		// 包装開始実績日時(TO)
		strDay = frm.getSrhResultSDayTo();
		strTime = frm.getSrhResultSTimeTo();
		condition.setSrhResultSdateTo(getTimestampYmdHmsFormat(strDay, strTime,
			STR_MAX_TIME));

		// 包装終了実績日時(FROM)
		strDay = frm.getSrhResultEDayFrom();
		strTime = frm.getSrhResultETimeFrom();
		condition.setSrhResultEdateFrom(getTimestampYmdHmsFormat(strDay,
			strTime, STR_MIN_TIME));

		// 包装終了実績日時(TO)
		strDay = frm.getSrhResultEDayTo();
		strTime = frm.getSrhResultETimeTo();
		condition.setSrhResultEdateTo(getTimestampYmdHmsFormat(strDay, strTime,
			STR_MAX_TIME));

		/* 帳票(Excel)用に検索条件を保持 */
		ProductInspectCompListConditionForReport reportCondition = new ProductInspectCompListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(productInspectCompListLogic.getSearchList(condition));
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

		ProductInspectCompListForm frm = (ProductInspectCompListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = productInspectCompListExcelDecorator
				.createReport(frm.getReportCondition());
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
		ProductInspectCompListForm frm = (ProductInspectCompListForm) form;

		// フォームより検索時の検索結果取得
		List<ProductInspectCompList> searchList = frm.getSearchList();

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			// 製品検査完了入力 更新処理
			productInspectCompListLogic.update(searchList, loginUserId);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (ProductInspectCompLogicException e) {
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
			return mapping.getInputForward();
		}
		// 更新完了メッセージを登録
		saveMessage(request, "message.complete.update");

		return mapping.findForward("reSearch");
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
		ProductInspectCompListForm frm = (ProductInspectCompListForm) form;

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

	/* -------------------- setter -------------------- */

	/**
	 * 製品検査完了入力一覧画面ロジッククラスを設定します。
	 * @param productInspectCompListLogic 製品検査完了入力一覧画面ロジッククラス
	 */
	public void setProductInspectCompListLogic(
			final ProductInspectCompListLogic productInspectCompListLogic) {
		this.productInspectCompListLogic = productInspectCompListLogic;
	}

	/**
	 * productInspectCompListExcelDecoratorを設定します。
	 * @param productInspectCompListExcelDecorator
	 *            productInspectCompListExcelDecorator
	 */
	public void setProductInspectCompListExcelDecorator(
			final ProductInspectCompListExcelDecorator productInspectCompListExcelDecorator) {
		this.productInspectCompListExcelDecorator = productInspectCompListExcelDecorator;
	}
}
