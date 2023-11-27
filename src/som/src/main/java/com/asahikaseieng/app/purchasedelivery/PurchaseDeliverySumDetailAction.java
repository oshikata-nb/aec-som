/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetail;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetailList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 納期回答まとめ入力 Actionクラス.
 * @author tosco
 */
public final class PurchaseDeliverySumDetailAction extends AbstractAction {

	private static final String FORM_NAME = "purchaseDeliveryListForm";

	/** 納期回答まとめ入力ロジッククラス */
	private PurchaseDeliverySumDetailLogic purchaseDeliverySumDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliverySumDetailAction() {
		super();
	}

	/**
	 * 検索処理(検索画面のリンク・まとめ入力ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		PurchaseDeliverySumDetailForm frm = (PurchaseDeliverySumDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PURCHASE_DELIVERY,
			Constants.TAB_ID_PURCHASE_DELIVERY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		try {

			// ヘッダ情報取得
			PurchaseDeliverySumDetail headBean = purchaseDeliverySumDetailLogic
					.getHeader(frm.getOrderSheetNo());
			// ヘッダ情報設定
			setHeaderInfo(frm, headBean);

			// ヘッダ情報(納入先)取得
			String locationName = purchaseDeliverySumDetailLogic
					.getLocation(frm.getOrderSheetNo());
			frm.setLocationName(locationName);

			// 明細データ検索
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);
			List<PurchaseDeliverySumDetailList> beanList = purchaseDeliverySumDetailLogic
					.getEntity(frm.getOrderSheetNo(), check);
			// 明細部データセット
			frm.setDetailList(beanList);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 検索処理(まとめ入力ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward jump(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		PurchaseDeliverySumDetailForm frm = (PurchaseDeliverySumDetailForm) form;
		// 発注書Noを検索Formヘ設定
		setFormToSession(request, frm);

		// 発注書No必須チェック・存在チェック
		ActionMessages errors = purchaseDeliverySumDetailLogic
				.checkForExist(frm.getOrderSheetNo());
		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		return init(mapping, form, request, response);

	}

	/**
	 * 更新処理(納期回答登録ボタン押下時)
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

		PurchaseDeliverySumDetailForm frm = (PurchaseDeliverySumDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 更新処理
			purchaseDeliverySumDetailLogic.update(frm.getDetailList(), tantoCd);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");

	}

	/**
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
	 * 
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");

	}

	/**
	 * 検索画面で入力した発注書Noをセッションへ設定
	 * @param request リクエスト
	 * @param frm 納期回答まとめ入力画面Form
	 */
	private void setFormToSession(final HttpServletRequest request,
			final PurchaseDeliverySumDetailForm frm) {
		HttpSession session = request.getSession(false);
		PurchaseDeliveryListForm listFrm = (PurchaseDeliveryListForm) session
				.getAttribute(FORM_NAME);
		if (listFrm == null) {
			listFrm = new PurchaseDeliveryListForm();
			session.setAttribute(FORM_NAME, listFrm);
		}
		listFrm.setSrhOrderSheetNo(frm.getOrderSheetNo()); // 発注書No
	}

	/**
	 * ヘッダ部データ設定
	 * @param frm 納期回答まとめ入力Form
	 * @param headBean ヘッダ部データBean
	 */
	private void setHeaderInfo(final PurchaseDeliverySumDetailForm frm,
			final PurchaseDeliverySumDetail headBean) {
		frm.setOrderSheetNo(headBean.getOrderSheetNo()); // 発注書NO
		frm.setStrOrderDate(headBean.getStrOrderDate()); // 発注日
		frm.setVenderName(headBean.getVenderName()); // 仕入先名称
		frm.setLocationName(headBean.getLocationName()); // 納入先名称
		frm.setStrOrderCount(headBean.getStrOrderCount()); // 全オーダー件数
		frm.setStrIssuedCount(headBean.getStrIssuedCount()); // 発注書発行済件数
		frm.setStrAdjustCount(headBean.getStrAdjustCount()); // 納期調整中件数
		frm.setStrFixedCount(headBean.getStrFixedCount()); // 納期確定件数
		frm.setStrArrivedAcceptedCount(headBean.getStrArrivedAcceptedCount()); // 入荷・受入済件数
	}

	/* -------------------- setter -------------------- */

	/**
	 * 納期回答まとめ入力ロジッククラスを設定します。
	 * @param purchaseDeliverySumDetailLogic 納期回答まとめ入力ロジッククラス
	 */
	public void setPurchaseDeliverySumDetailLogic(
			final PurchaseDeliverySumDetailLogic purchaseDeliverySumDetailLogic) {
		this.purchaseDeliverySumDetailLogic = purchaseDeliverySumDetailLogic;
	}

}
