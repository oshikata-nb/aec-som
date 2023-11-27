/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchasedelivery;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryDetail;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 納期回答入力 Actionクラス.
 * @author tosco
 */
public final class PurchaseDeliveryDetailAction extends AbstractAction {

	/** 納期回答入力ロジッククラス */
	private PurchaseDeliveryDetailLogic purchaseDeliveryDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliveryDetailAction() {
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

		PurchaseDeliveryDetailForm frm = (PurchaseDeliveryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PURCHASE_DELIVERY,
			Constants.TAB_ID_PURCHASE_DELIVERY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// データ検索
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		PurchaseDeliveryDetail bean = purchaseDeliveryDetailLogic.getEntity(frm
				.getPurchaseNo(), check);

		// データ設定
		frm.setBuySubcontractOrderNo(bean.getBuySubcontractOrderNo()); // 発注番号
		frm.setStrBuySubcontractOrderNo(bean.getStrBuySubcontractOrderNo());
		frm.setOrderSheetNo(bean.getOrderSheetNo()); // 発注書NO
		frm.setStrOrderDate(bean.getStrOrderDate()); // 発注日
		frm.setTantoNm(bean.getTantoNm()); // 発注者
		frm.setItemCd(bean.getItemCd()); // 品目コード
		frm.setItemQueueName(bean.getItemQueueName()); // 品目名称
		frm.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード１
		frm.setVenderCd(bean.getVenderCd()); // 仕入先コード
		frm.setVenderName(bean.getVenderName()); // 仕入先名称
		frm.setStrOrderQuantity(bean.getStrOrderQuantity()); // 発注数量
		frm.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		frm.setStrOrderConvertQuantity(bean.getStrOrderConvertQuantity()); // 重量
		frm.setLocationCd(bean.getLocationCd()); // 納入ロケーションコード
		frm.setLocationName(bean.getLocationName()); // 納入先名称
		frm.setStatus(bean.getStrStatus()); // 購買ステータス
		frm.setSiOrderNo(bean.getSiOrderNo()); // 仕入先受注番号
		frm.setChargeOrganizationCd(bean.getChargeOrganizationCd()); // 担当部署コード
		frm.setChargeOrganizationName(bean.getChargeOrganizationName()); // 担当部署名称
		frm.setOrganizationCd(bean.getOrganizationCd()); // 部署コード
		frm.setOrganizationName(bean.getOrganizationName()); // 部署名称
		frm.setStrSuggestedDeliverlimitDate(bean
				.getStrSuggestedDeliverlimitDate()); // 納品希望日
		frm.setStrSuggestedDeliverlimitDateTime(bean
				.getStrSuggestedDeliverlimitDateTime()); // 納品希望時刻
		if (BigDecimal.ONE.equals(bean.getReplyContentsDivision())) {
			frm.setReplyContentsDivision(true); // 分納区分(分納有)
		} else {
			frm.setReplyContentsDivision(false); // 分納区分(分納無)
		}
		frm.setOrderSheetRemark(bean.getOrderSheetRemark()); // 発注書備考
		frm.setRemark(bean.getRemark()); // 備考
		frm.setUnit(bean.getUnit()); // 単位

		// 排他制御が無かった為追加（画面を開いたときの更新日付を保持）
		frm.setUpdateDate(bean.getUpdateDate());

		return mapping.findForward("success");

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

		PurchaseDeliveryDetailForm frm = (PurchaseDeliveryDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		// 購買外注オーダ検索
		PurchaseSubcontract bean = purchaseDeliveryDetailLogic.getEntity(frm
				.getPurchaseNo());

		// 排他制御が無かった為追加
		bean.setUpdateDate(frm.getUpdateDate());

		// 更新処理
		purchaseDeliveryDetailLogic.update(frm, tantoCd, bean);

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

	/* -------------------- setter -------------------- */

	/**
	 * 納期回答入力ロジッククラスを設定します。
	 * @param purchaseDeliveryDetailLogic 納期回答入力ロジッククラス
	 */
	public void setPurchaseDeliveryDetailLogic(
			final PurchaseDeliveryDetailLogic purchaseDeliveryDetailLogic) {
		this.purchaseDeliveryDetailLogic = purchaseDeliveryDetailLogic;
	}

}
