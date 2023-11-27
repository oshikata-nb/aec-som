/*
 * Created on 2007/12/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail.InventoryShippingoutDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 在庫出庫入力詳細 Actionクラス.
 * @author tanaka
 */
public final class InventoryShippingoutDetailAction extends AbstractAction {

	private InventoryShippingoutDetailLogic inventoryShippingoutDetailLogic;

	private ConvInventoryLogic convInventoryLogic;

	private static final String KG_CD = "1";

	/**
	 * コンストラクタ
	 */
	public InventoryShippingoutDetailAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		InventoryShippingoutDetailForm frm = (InventoryShippingoutDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_INVENTORY_SHIPPING_OUT,
			Constants.TAB_ID_INVENTORY_SHIPPING_OUT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		InventoryShippingoutDetail bean = inventoryShippingoutDetailLogic
				.getDetailEntity(frm.getLocationCd(), frm.getItemCd(), frm
						.getLotNo());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列に変換 */
		frm.setStrDispPackQty(checker.format(
			frm.getUnitOfOperationManagement(), frm.getDispPackQty()));
		frm.setStrPackQty(checker.format(frm.getUnitOfOperationManagement(),
			frm.getPackQty()));

		/* 総量の単位はKgなのでKg桁数設定をする */
		// frm.setStrInventoryQty(checker.format(frm
		// .getUnitOfOperationManagement(), frm.getInventoryQty()));
		frm.setStrInventoryQty(checker.format(KG_CD, frm.getInventoryQty()));

		frm.setStrDispFraction(checker.format(
			frm.getUnitOfFractionManagement(), frm.getDispFraction()));
		frm.setStrFraction(checker.format(frm.getUnitOfFractionManagement(),
			frm.getFraction()));

		return mapping.findForward("success");
	}

	/**
	 * 登録処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		InventoryShippingoutDetailForm frm = (InventoryShippingoutDetailForm) form;

		Timestamp inDate = AecDateUtils.getTimestampYmdFormat(frm
				.getStrLastInDate()); /* 入力日付 */
		Timestamp nowDate = AecDateUtils.getCurrentTimestamp(); /* 現在日 */

		/* 日付チェック */
		if (0 < inDate.compareTo(nowDate)) {
			/* エラーメッセージ */
			saveError(request,
				"errors.inventory.shippingout.future.last.in.date");
			return mapping.findForward("success");
		}

		if (!StringUtils.isEmpty(frm.getItemCd())) {
			/* 品目コードチェック */
			ItemDetail beanItem = inventoryShippingoutDetailLogic
					.getItemEntity(frm.getItemCd());

			if (beanItem == null) {
				/* エラーメッセージ */
				saveError(request,
					"errors.nodata.inventory.shippingout.item.cd");
				return mapping.findForward("success");
			}
		} else {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.inventory.shippingout.item.cd");
			return mapping.findForward("success");
		}

		if (!StringUtils.isEmpty(frm.getRyCd())) {
			/* 理由コードチェック */
			ReasonDetail beanReason = inventoryShippingoutDetailLogic
					.getReasonEntity(frm.getRyCd());

			if (beanReason == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.inventory.shippingout.ry.cd");
				return mapping.findForward("success");
			}

			frm.setRyDescription(beanReason.getRyDescription());
		}

		frm.setLastInDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrLastInDate()));
		frm.setPackQty(AecNumberUtils.convertBigDecimal(frm.getStrPackQty())); /* 荷姿数 */
		frm.setFraction(AecNumberUtils.convertBigDecimal(frm.getStrFraction())); /* 端数 */

		BigDecimal packQty = AecNumberUtils.convertNullToZero(frm.getPackQty());
		BigDecimal dispPackQty = AecNumberUtils.convertNullToZero(frm
				.getDispPackQty());
		BigDecimal fraction = AecNumberUtils.convertNullToZero(frm
				.getFraction());
		BigDecimal dispFraction = AecNumberUtils.convertNullToZero(frm
				.getDispFraction());

		// /* 荷姿数チェック */
		// if (dispPackQty.compareTo(packQty) < 0) {
		// saveErrorWithArgs(request, "errors.compare",
		// "inventory.shippingout.stock.pack.qty",
		// "inventory.shippingout.pack.qty");
		// return mapping.findForward("success");
		// }
		//
		// /* 端数チェック */
		// if (dispFraction.compareTo(fraction) < 0) {
		// saveErrorWithArgs(request, "errors.compare",
		// "inventory.shippingout.stock.fraction",
		// "inventory.shippingout.fraction");
		// return mapping.findForward("success");
		// }
		/* 在庫量換算 */
		ConvInventoryResult inventory = convInventoryLogic.packToInventory(frm
				.getItemCd(), packQty, fraction);
		ConvInventoryResult dispInventory = convInventoryLogic.packToInventory(
			frm.getItemCd(), dispPackQty, dispFraction);

		/* 在庫量チェック */
		if (dispInventory.getInventoryQty().compareTo(
			inventory.getInventoryQty()) < 0) {
			saveError(request, "errors.inventory.shippingout.stock.over");
			return mapping.findForward("success");
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		/* 演算結果を丸める */
		if (StringUtils.isEmpty(frm.getUnitOfOperationManagement())) {
			frm.setPackQty(packQty);
		} else {
			frm.setPackQty(check.round(frm.getUnitOfOperationManagement(),
				null, null, packQty));
		}

		if (StringUtils.isEmpty(frm.getUnitOfFractionManagement())) {
			frm.setFraction(fraction);
		} else {
			frm.setFraction(check.round(frm.getUnitOfFractionManagement(),
				null, null, fraction));
		}

		/* 端数入力チェック */
		ConvInventoryResult resultFraction = convInventoryLogic
				.checkInputFraction(frm.getItemCd(), frm.getFraction());

		if (resultFraction.getCode().equals(new BigDecimal("2"))) {
			/* エラーメッセージ */
			saveError(request, "errors.conv.inventory.input.fraction");
			return mapping.findForward("success");
		}

		/* 在庫数量計算 */
		ConvInventoryResult result = convInventoryLogic.packToInventory(frm
				.getItemCd(), frm.getPackQty(), frm.getFraction());

		if (result.getCode().equals(new BigDecimal("1"))) {
			/* エラーメッセージ */
			saveError(request, "errors.conv.inventory.calc");
			return mapping.findForward("success");
		}

		frm.setInventoryQty(result.getInventoryQty());

		try {
			/* 追加処理を実行 */
			inventoryShippingoutDetailLogic.stockUpdate(frm, result
					.getTypeDivision(), getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			/* エラーメッセージ */
			saveError(request, "errors.inventory.shippingout.stock.update");
			return mapping.findForward("success");
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		frm.setDirtyFlg(null);

		return mapping.findForward("success");
	}

	/**
	 * 戻る処理.
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
	 * {@inheritDoc}
	 */
	public ActionForward calcTotal(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("calcTotal.");
		}

		InventoryShippingoutDetailForm frm = (InventoryShippingoutDetailForm) form;

		/* 品目取得 */
		ItemQueueDetail bean = inventoryShippingoutDetailLogic
				.getItemQueueEntity(frm.getItemCd());

		if (bean == null) {
			frm.setStrInventoryQty(null);
		} else {
			/* 文字列 ---> 数値変換 */
			frm.setPackQty(AecNumberUtils
					.convertBigDecimal(frm.getStrPackQty()));
			frm.setFraction(AecNumberUtils.convertBigDecimal(frm
					.getStrFraction()));

			/* null ---> 数値変換 */
			BigDecimal packQty = AecNumberUtils.convertNullToZero(frm
					.getPackQty());
			BigDecimal kgOfFractionManagement = AecNumberUtils
					.convertNullToZero(bean.getKgOfFractionManagement());
			BigDecimal fraction = AecNumberUtils.convertNullToZero(frm
					.getFraction());
			BigDecimal kgConversionCoefficient = AecNumberUtils
					.convertNullToZero(bean.getKgConversionCoefficient());

			/* 総量 = 荷姿数 * Kg換算係数(在庫) + 端数 * Kg換算係数(端数) */
			frm.setInventoryQty(packQty.multiply(kgOfFractionManagement).add(
				fraction.multiply(kgConversionCoefficient)));
			frm.setStrInventoryQty(frm.getInventoryQty().toString());

			/* 文字列 ---> 数値変換 */
			frm.setInventoryQty(AecNumberUtils.convertBigDecimal(frm
					.getStrInventoryQty()));

			/* 数値桁数チェック部品呼び出し */
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			BigDecimal result = null;

			/* 演算結果を丸める */
			if (StringUtils.isEmpty(frm.getUnitOfOperationManagement())) {
				result = frm.getInventoryQty();
			} else {
				result = check.round(bean.getUnitOfOperationManagement(), null,
					null, frm.getInventoryQty());
				frm.setInventoryQty(result);
			}

			/* 数値 ---> 文字列変換 */
			frm.setStrPackQty(check.format(bean.getUnitOfOperationManagement(),
				AecNumberUtils.convertBigDecimal(frm.getStrPackQty())));
			frm.setStrFraction(check.format(bean.getUnitOfFractionManagement(),
				AecNumberUtils.convertBigDecimal(frm.getStrFraction())));
			frm.setStrInventoryQty(check.format(bean
					.getUnitOfOperationManagement(), result));
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryShippingoutDetailLogicを設定します。
	 * @param inventoryShippingoutDetailLogic inventoryShippingoutDetailLogic
	 */
	public void setInventoryShippingoutDetailLogic(
			final InventoryShippingoutDetailLogic inventoryShippingoutDetailLogic) {
		this.inventoryShippingoutDetailLogic = inventoryShippingoutDetailLogic;
	}

	/**
	 * convInventoryLogicを設定します。
	 * @param convInventoryLogic convInventoryLogic
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}
}
