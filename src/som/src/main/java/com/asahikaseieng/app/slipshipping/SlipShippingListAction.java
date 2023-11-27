/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipshipping;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.sql.ARRAY;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingList;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecTextUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 出荷帳票検索画面 Actionクラス.
 * @author tosco
 * 
 */
public final class SlipShippingListAction extends AbstractSearchAction {

	private SlipShippingListLogic slipShippingListLogic;

	private SlipShippingOrderListExcelDecorator slipShippingOrderListExcelDecorator;

	private SlipShippingListExcelDecorator slipShippingListExcelDecorator;

	/** 最小時間文字列 */
	private static final String STR_MIN_TIME = "00:00:00";

	/** 最大時間文字列 */
	private static final String STR_MAX_TIME = "23:59:59";

	/**
	 * コンストラクタ.
	 */
	public SlipShippingListAction() {
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

		SlipShippingListForm frm = (SlipShippingListForm) form;

		// 運送会社コンボボックス
		frm.setCarryCombo(slipShippingListLogic.getCreateCarryCombobox());

		// ステータス 2（出荷指図確定済）をデフォルト選択
		frm.setSrhShippingStatus("2");

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 出荷帳票検索処理(検索ボタン押下時)
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

		SlipShippingListForm frm = (SlipShippingListForm) form;

		// クリア
		frm.setSearchList(new ArrayList<SlipShippingList>());
		// クリア
		if (!frm.getOp().equals("reFresh")) {
			frm.setExcelDownloadFlg(Boolean.FALSE);
		}

		// 検索条件を取得
		SlipShippingListPagerCondition condition = (SlipShippingListPagerCondition) frm
				.getPager().getPagerCondition();
		// 検索条件をセット
		// 上位ﾛｹｰｼｮﾝ
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		// 運送店
		condition.setSrhCarryCd(frm.getSrhCarryCd());
		// 出荷予定日
		condition.setSrhScheduledShippingDateFrom(getTimestampYmdHmsFormat(frm
				.getSrhScheduledShippingDateFrom(), "", STR_MIN_TIME));
		condition.setSrhScheduledShippingDateTo(getTimestampYmdHmsFormat(frm
				.getSrhScheduledShippingDateTo(), "", STR_MAX_TIME));

		// 受注番号
		condition.setSrhOrderNoFrom(frm.getSrhOrderNoFrom());
		condition.setSrhOrderNoTo(frm.getSrhOrderNoTo());
		// 出荷番号
		condition.setSrhShippingNo(frm.getSrhShippingNo());
		// ステータス
		condition.setSrhShippingStatus(frm.getSrhShippingStatus());

		// 出荷伝票発行済
		if (frm.getSrhSlipPublishComp() == Boolean.TRUE) {
			condition.setSrhSlipPublishComp(new BigDecimal(1));
		} else {
			condition.setSrhSlipPublishComp(new BigDecimal(0));
		}
		// 出荷指図書発行済
		if (frm.getSrhSlipShippingOrderComp() == Boolean.TRUE) {
			condition.setSrhSlipShippingOrderComp(new BigDecimal(1));
		} else {
			condition.setSrhSlipShippingOrderComp(new BigDecimal(0));
		}
		// 出荷予定表発行済
		if (frm.getSrhSlipShippingScheduleComp() == Boolean.TRUE) {
			condition.setSrhSlipShippingScheduleComp(new BigDecimal(1));
		} else {
			condition.setSrhSlipShippingScheduleComp(new BigDecimal(0));
		}
		// 荷札発行済
		if (frm.getSrhSlipShippingTagComp() == Boolean.TRUE) {
			condition.setSrhSlipShippingTagComp(new BigDecimal(1));
		} else {
			condition.setSrhSlipShippingTagComp(new BigDecimal(0));
		}
		// 出荷依頼書発行済
		if (frm.getSrhSlipShippingRequestComp() == Boolean.TRUE) {
			condition.setSrhSlipShippingRequestComp(new BigDecimal(1));
		} else {
			condition.setSrhSlipShippingRequestComp(new BigDecimal(0));
		}
		// 運賃表発行済
		if (frm.getSrhSlipShippingFareComp() == Boolean.TRUE) {
			condition.setSrhSlipShippingFareComp(new BigDecimal(1));
		} else {
			condition.setSrhSlipShippingFareComp(new BigDecimal(0));
		}
		// 納品伝票発行済
		if (frm.getSrhSlipDeliveryComp() == Boolean.TRUE) {
			condition.setSrhShippingSlipDeliveryComp(new BigDecimal(1));
		} else {
			condition.setSrhShippingSlipDeliveryComp(new BigDecimal(0));
		}
		// 新荷札発行済
		if (frm.getSrhSlipNewShippingTagComp() == Boolean.TRUE) {
			condition.setSrhShippingSlipNewShippingTagComp(new BigDecimal(1));
		} else {
			condition.setSrhShippingSlipNewShippingTagComp(new BigDecimal(0));
		}
		// 新郵政発行済
		if (frm.getSrhSlipPostalComp() == Boolean.TRUE) {
			condition.setSrhShippingSlipPostalComp(new BigDecimal(1));
		} else {
			condition.setSrhShippingSlipPostalComp(new BigDecimal(0));
		}

		// 荷札種別
		condition.setSrhLabelPublish(frm.getSrhLabelPublish());
		// 出荷伝票番号
		condition.setSrhShippingSlipNo(frm.getSrhShippingSlipNo());

		// 出荷バーコード
		condition.setSrhCarryBarcode(frm.getSrhCarryBarcode());

		/* 帳票(Excel)用に検索条件を保持 */
		SlipShippingListConditionForReport reportCondition = new SlipShippingListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(slipShippingListLogic.getSearchList(condition));
		
		// 郵政依頼主コンボボックス
		frm.setPostalClientCombo(slipShippingListLogic.getCreatePostalClintCombobox());
		
		return mapping.findForward("success");
	}

	/**
	 * 帳票処理(検索画面の帳票ボタン押下時)
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

		SlipShippingListForm frm = (SlipShippingListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = slipShippingListExcelDecorator.createReport(frm
				.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * 印刷処理(全印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward canselslippublish(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm frm = (SlipShippingListForm) form;

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			slipShippingListLogic.canselSlipPublish(frm, loginUserId);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}
		// 更新完了メッセージを登録
		saveMessage(request, "message.slipshipping.complete.cancel");

		return mapping.findForward("reSearch");
	}

	/**
	 * 印刷処理(全印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward allprint(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 出荷伝票番号ふばん処理
			ArrayList<String> shippingSlipNoList = slipShippingListLogic
					.getAddSlipShippingNo(shippingNoList, loginUserId);
			FileDownloadInfo info = null;

			/* 出荷伝票出力処理 */
			info = slipShippingOrderListExcelDecorator.createReport(
				shippingSlipNoList, shippingNoList, loginUserId, null, request
						.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
	 * 印刷処理(出荷伝票印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slippublish(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 出荷伝票番号ふばん処理
			ArrayList<String> shippingSlipNoList = slipShippingListLogic
					.getAddSlipShippingNo(shippingNoList, loginUserId);
			FileDownloadInfo info = null;

			/* 出荷伝票出力処理 */
			info = slipShippingOrderListExcelDecorator.createReport(
				shippingSlipNoList, shippingNoList, loginUserId, null, request
						.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
	 * 印刷処理(出荷指図書印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingorder(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログイン者部署コード取得
		// String organizationCd = getLoginInfo(request).getOrganizationCd();

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 出荷指図書発行済みフラグ、更新日時設定処理
			slipShippingListLogic.setPrintOutFlgDirection(shippingNoList,
				loginUserId);
			FileDownloadInfo info = null;

			/* 出荷指図書出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportDirection(
				shippingNoList, loginUserId, null, request.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
		// return mapping.findForward("success");
	}

	/**
	 * 印刷処理(出荷予定表印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingschedule(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 出荷予定表発行済みフラグ、更新日時設定処理
			slipShippingListLogic.setPrintOutFlgSchedule(shippingNoList,
				loginUserId);
			FileDownloadInfo info = null;

			/* 出荷予定表出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportSchedule(
				shippingNoList, loginUserId, null, request.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
		// return mapping.findForward("success");
	}

	/**
	 * 印刷処理(荷札印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingtag(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログイン者部署コード取得
		String organizationCd = getLoginInfo(request).getOrganizationCd();

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 荷札、ペリカン伝票発行済みフラグ、更新日時設定処理
			slipShippingListLogic
					.setPrintOutFlgTag(shippingNoList, loginUserId);
			FileDownloadInfo info = null;

			/* 荷札出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportTag(
				shippingNoList, loginUserId, organizationCd, null, request
						.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
		// return mapping.findForward("success");
	}

	/**
	 * 印刷処理(出荷依頼書印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingrequest(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;
		String forward = "success";

		// ログインユーザーID取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログインユーザー名取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ログイン者部署コード取得
		String organizationCd = getLoginInfo(request).getOrganizationCd();

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 出荷依頼書発行済みフラグ、更新日時設定処理
			slipShippingListLogic.setPrintOutFlgRequest(shippingNoList,
				loginUserId);
			FileDownloadInfo info = null;

			/* 出荷依頼書出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportRequest(
				shippingNoList, tantoNm, organizationCd, null, request
						.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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

		// return mapping.findForward("success");
	}
	
	/**
	 * 印刷処理(運賃表印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingfare(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログインユーザー名取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ログイン者部署コード取得
		String organizationCd = getLoginInfo(request).getOrganizationCd();
		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 運賃表発行済みフラグ、更新日時設定処理
			slipShippingListLogic.setPrintOutFlgFare(shippingNoList,loginUserId);
			FileDownloadInfo info = null;
			
			/* 運賃表出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportFare(shippingNoList, tantoNm, organizationCd, null, request
				.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			
			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
	 * 印刷処理(納品伝表印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingdelivery(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログインユーザー名取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ログイン者部署コード取得
		String organizationCd = getLoginInfo(request).getOrganizationCd();
		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 納品伝票発行済みフラグ、更新日時設定処理
			slipShippingListLogic.setPrintOutFlgDelivery(shippingNoList,loginUserId);
			FileDownloadInfo info = null;
			
			/* 出荷依頼書出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportDelivery(shippingNoList, tantoNm, organizationCd, null, request.getRemoteAddr() , loginUserId);

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			
			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
	 * 印刷処理(郵政印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingpostal(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログインユーザー名取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);
		// 郵政依頼主コード取得
		String postalClientCd = listForm.getPostalClientCd();
		
		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 納品伝票発行済みフラグ、更新日時設定処理
			slipShippingListLogic.setPrintOutFlgPostal(shippingNoList,loginUserId);
			FileDownloadInfo info = null;
			
			/* 出荷依頼書出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportPostal(shippingNoList, tantoNm, postalClientCd, null, request
				.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			
			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
	 * 印刷処理(新荷札ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipshippingnewtag(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログインユーザー名取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);
		
		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());
				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 新荷札発行済みフラグ、更新日時設定処理
			slipShippingListLogic.setPrintOutFlgNewTag(shippingNoList,loginUserId);
			FileDownloadInfo info = null;
			
			/* 出荷依頼書出力処理 */
			info = slipShippingOrderListExcelDecorator.createReportNewTag(shippingNoList, tantoNm, null, request
				.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			
			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
	 * バーコード解除処理(出荷BC解除ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clearbarcode(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {


		SlipShippingListForm listForm = (SlipShippingListForm) form;
		ArrayList<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		try {
			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());
				}
			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}

			// 納品伝票発行済みフラグ、更新日時設定処理
			slipShippingListLogic.clearCarryBC(shippingNoList,loginUserId);
			
			// ExcelダウンロードフラグＯＮ
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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

		// 更新完了メッセージを登録
		saveMessage(request, "carry.complete.bcclear");

		return mapping.findForward("reSearch");
	}

	/**
	 * 運送会社連携ファイル作成処理(運送会社連携ファイル作成ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward createcarryfile(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {


		SlipShippingListForm listForm = (SlipShippingListForm) form;
		List<String> shippingNoList = new ArrayList<String>();
		boolean isSelected = false;

		String forward = "success";
		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		// ログインユーザー名取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);
		// 郵政依頼主コード取得
		String postalClientCd = listForm.getPostalClientCd();
		
		// 運送会社コード用リスト
		List< String > carryCdList =  new ArrayList<String>();
		
		try {

			for (SlipShippingList bean : listForm.getSearchList()) {
				if (!bean.isSlipShippingCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					shippingNoList.add(bean.getShippingNo());
					
					// 運送会社コードの登録
					if( !carryCdList.contains(bean.getCarryCd()) ){
						carryCdList.add(bean.getCarryCd());
					}
					
				}
			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.selected.checkbox");
				throw ex;
			}


			// 運送会社が検索条件に含まれていないとエラー
			if( carryCdList.size() == 0 || carryCdList.size() >= 2){
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipshipping.inputed.carryCd");
				throw ex;
			}

			FileDownloadInfo info = null;
			
			/* 出荷依頼書出力処理 */
			info = slipShippingOrderListExcelDecorator.createCarryFile( carryCdList.get(0), shippingNoList, loginUserId, postalClientCd, null, request.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			
			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);
			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
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
	
	/* -------------------- setter -------------------- */

	/**
	 * 出荷帳票検索画面ロジッククラスを設定します。
	 * @param slipShippingListLogic 出荷帳票検索画面ロジッククラス
	 */
	public void setSlipShippingListLogic(
			final SlipShippingListLogic slipShippingListLogic) {
		this.slipShippingListLogic = slipShippingListLogic;
	}

	/**
	 * 出荷帳票検索画面ロジッククラスを設定します。
	 * @param slipShippingOrderListExcelDecorator 出荷帳票検索画面ロジッククラス
	 */
	public void setSlipShippingOrderListExcelDecorator(
			final SlipShippingOrderListExcelDecorator slipShippingOrderListExcelDecorator) {
		this.slipShippingOrderListExcelDecorator = slipShippingOrderListExcelDecorator;
	}

	/**
	 * 帳票Excel用ロジッククラスを設定します。
	 * @param slipShippingListExcelDecorator 出荷帳票検索画面ロジッククラス
	 */
	public void setSlipShippingListExcelDecorator(
			final SlipShippingListExcelDecorator slipShippingListExcelDecorator) {
		this.slipShippingListExcelDecorator = slipShippingListExcelDecorator;
	}
}
