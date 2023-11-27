/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

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
import com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate.InventoryDetailLotDuplicate;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 在庫入庫入力 Actionクラス.
 * @author t0011036
 */
public final class InventoryDetailAction extends AbstractAction {

	private InventoryDetailLogic inventoryDetailLogic;

	private ConvInventoryLogic convInventoryLogic;

	/** 製品ラベルＥＸＣＥＬファイル作成ロジッククラス */
	private InventoryDetailExcelDecorator inventoryDetailExcelDecorator;

	/** ローリー区分 2:ローリー */
	private static final BigDecimal LORRY_DIVISION_LORRY = new BigDecimal("2");

	/**
	 * コンストラクタ.
	 */
	public InventoryDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		InventoryDetailForm frm = (InventoryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_INVENTORY,
			Constants.TAB_ID_INVENTORY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
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

		InventoryDetailForm frm = (InventoryDetailForm) form;

		String checkLorryItemCd = null;
		boolean isLorry = false;

		ItemDetail beanItem = inventoryDetailLogic.getItemEntity(frm
				.getItemCd());

		if (beanItem == null) {
			/* データが存在しない場合 */
			saveError(request, "errors.nodata.inventory.item.cd");
			return mapping.findForward("success");
		} else {
			PurchaseAttributeQueueDetail beanPurchase = inventoryDetailLogic
					.getPurchaseEntity(beanItem.getItemCd(), beanItem
							.getVersion());

			if (beanPurchase != null) {
				/* ローリー原料の場合 */
				if (beanPurchase.getLorryDivision()
						.equals(LORRY_DIVISION_LORRY)) {
					checkLorryItemCd = beanItem.getParentItemCd(); /* 親品目コード */
					isLorry = true;
				}
			}
		}

		frm.setLotDivision(beanItem.getLotDivision());
		frm.setTypeDivision(beanItem.getTypeDivision());

		LocationDetail beanLocation = inventoryDetailLogic
				.getLocationEntity(frm.getLocationCd());

		if (beanLocation == null) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.inventory.location.cd");
			return mapping.findForward("success");
		}		
		
		// 入出庫不可ロケーションチェック
		if(beanLocation.getAvailableFlg().compareTo(BigDecimal.ZERO) == 0){
			/* エラーメッセージ */
			saveError(request, "errors.inventory.impossible.location");
			return mapping.findForward("success");
		}
		
		// ローリー原料品目コードチェック
		if (StringUtils.isNotEmpty(checkLorryItemCd)) {
			if (!checkLorryItemCd.equals(beanLocation.getItemCd())) {
				/* エラーメッセージ */
				saveError(request, "errors.inventory.no.location.item.cd");
				return mapping.findForward("success");
			}
		} else {
			if (isLorry) {
				/* エラーメッセージ */
				saveError(request, "errors.inventory.no.location.item.cd");
				return mapping.findForward("success");
			} else {
				if (StringUtils.isNotEmpty(beanLocation.getItemCd())
						&& !beanLocation.getItemCd().equals(frm.getItemCd())) {
					/* エラーメッセージ */
					saveError(request, "errors.inventory.no.location.item.cd");
					return mapping.findForward("success");
				}
			}
		}

		if (!StringUtils.isEmpty(frm.getRyCd())) {
			/* 理由コードチェック */
			ReasonDetail beanReason = inventoryDetailLogic.getReasonEntity(frm
					.getRyCd());

			if (beanReason == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.inventory.ry.cd");
				return mapping.findForward("success");
			}

			frm.setRyDescription(beanReason.getRyDescription());
		}

		frm.setTypeDivision(beanItem.getTypeDivision());

		/* 種別が原料 or 包材の場合別のロケーションに同じロットは存在出来ない */
		/* ただしロット管理する品目が対象 */
		if (frm.getLotDivision().equals(LORRY_DIVISION_LORRY)
				&& (frm.getTypeDivision().equals(new BigDecimal("1")) || frm
						.getTypeDivision().equals(new BigDecimal("2")))) {
			/* 入荷ロット番号/包装指図番号の重複チェック */
			InventoryDetailLotDuplicate beanDuplicate = inventoryDetailLogic
					.getLotDuplicate(frm.getLotNo());

			if (beanDuplicate != null) {
				if (!beanDuplicate.getLocationCd().equals(frm.getLocationCd())) {
					/* エラーメッセージ */
					saveError(request, "errors.inventory.duplicate.lot.no");
					return mapping.findForward("success");
				}
			}
		}

		/* 入荷ロット番号/包装指図番号 必須チェック */
		if (frm.getLotDivision().equals(LORRY_DIVISION_LORRY)) {
			if (StringUtils.isEmpty(frm.getLotNo())) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.required",
					"inventory.lot.no");
				return mapping.findForward("success");
			}
		}

		frm.setLastInDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrLastInDate()));
		frm.setPackQty(AecNumberUtils.convertBigDecimal(frm.getStrPackQty())); /* 荷姿数 */
		frm.setFraction(AecNumberUtils.convertBigDecimal(frm.getStrFraction())); /* 端数 */
		frm.setInventoryCost(AecNumberUtils.convertBigDecimal(frm
				.getStrInventoryCost())); /* 単価 */
		frm.setLabelCount(AecNumberUtils.convertBigDecimal(frm
				.getStrLabelCount())); /* ラベル枚数 */

		/* null ---> 数値変換 */
		BigDecimal packQty = AecNumberUtils.convertNullToZero(frm.getPackQty());
		BigDecimal fraction = AecNumberUtils.convertNullToZero(frm
				.getFraction());
		BigDecimal inventoryCost = AecNumberUtils.convertNullToZero(frm
				.getInventoryCost());
		BigDecimal labelCount = AecNumberUtils.convertNullToZero(frm
				.getLabelCount());
		// BigDecimal kgOfFractionManagement = AecNumberUtils
		// .convertNullToZero(frm.getKgOfFractionManagement());
		// BigDecimal kgConversionCoefficient = AecNumberUtils
		// .convertNullToZero(frm.getKgConversionCoefficient());

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

		if (StringUtils.isEmpty(frm.getUnitDivisionUnit())) {
			frm.setInventoryCost(inventoryCost);
		} else {
			frm.setInventoryCost(check.round(frm.getUnitDivisionUnit(), null,
				null, inventoryCost));
		}

		if (StringUtils.isEmpty(frm.getUnitDivisionOther())) {
			frm.setLabelCount(labelCount);
		} else {
			frm.setLabelCount(check.round(frm.getUnitDivisionOther(), null,
				null, labelCount));
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

		// if (result.getCode().equals(new BigDecimal("1"))) {
		// frm.setInventoryQty(new BigDecimal("0"));
		// } else {
		// frm.setInventoryQty(result.getInventoryQty());
		// }
		//
		// frm.setStrInventoryQty(frm.getInventoryQty().toString());

		BigDecimal inventoryQty = BigDecimal.ZERO; /* 在庫重量 */

		if (result.getCode().equals(new BigDecimal("1"))) {
			inventoryQty = BigDecimal.ZERO;
		} else {
			inventoryQty = result.getInventoryQty();
		}

		/* 総量 = 荷姿数 * Kg換算係数(在庫) + 端数 * Kg換算係数(端数) */
		// frm.setInventoryQty(packQty.multiply(kgOfFractionManagement).add(
		// fraction.multiply(kgConversionCoefficient)));
		// frm.setStrInventoryQty(frm.getInventoryQty().toString());
		try {
			/* 追加処理を実行 */
			inventoryDetailLogic.stockUpdate(frm, result.getTypeDivision(),
				inventoryQty, getLoginInfo(request).getTantoCd());
			// inventoryDetailLogic.stockUpdate(frm, frm.getTypeDivision(),
			// getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			/* エラーメッセージ */
			saveError(request, "errors.inventory.stock.update");
			return mapping.findForward("success");
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward calcTotalPlus(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("calcTotalPlus.");
		}

		InventoryDetailForm frm = (InventoryDetailForm) form;

		/* 在庫数量計算 */
		calcQty(frm, request);

		if (frm.getLotDivision().equals(new BigDecimal("1"))) {
			/* ロット管理しない品目 */
			frm.setLotNo("999999");
			frm.setAliasLotNo(null);
		} else if (frm.getLotDivision().equals(new BigDecimal("2"))) {
			/* ロット管理する品目 */
			frm.setLotNo(null);
			frm.setAliasLotNo(null);
		}

		return mapping.findForward("success");
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

		InventoryDetailForm frm = (InventoryDetailForm) form;

		/* 在庫数量計算 */
		calcQty(frm, request);

		return mapping.findForward("success");
	}

	/**
	 * 在庫数量計算
	 * @param frm 画面データ
	 */
	private void calcQty(final InventoryDetailForm frm,
			final HttpServletRequest request) {
		/* 品目取得 */
		ItemDetail bean = inventoryDetailLogic.getItemEntity(frm.getItemCd());

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

			/* 在庫数量計算 */
			// ConvInventoryResult result =
			// convInventoryLogic.packToInventory(frm
			// .getItemCd(), frm.getPackQty(), frm.getFraction());
			//
			// if (result.getCode().equals(new BigDecimal("1"))) {
			// frm.setInventoryQty(new BigDecimal("0"));
			// } else {
			// frm.setInventoryQty(result.getInventoryQty());
			// }
			// frm.setStrInventoryQty(frm.getInventoryQty().toString());
			/* 総量 = 荷姿数 * Kg換算係数(在庫) + 端数 * Kg換算係数(端数) */
			frm.setInventoryQty(packQty.multiply(kgOfFractionManagement).add(
				fraction.multiply(kgConversionCoefficient)));
			frm.setStrInventoryQty(frm.getInventoryQty().toString());

			/* 数値桁数チェック部品呼び出し */
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			/* 演算結果を丸める */
			frm.setInventoryQty(check.round(
				bean.getUnitOfOperationManagement(), null, null, frm
						.getInventoryQty()));
			frm.setStrInventoryQty(check.format(bean
					.getUnitOfOperationManagement(), frm.getInventoryQty()));
		}
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
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		InventoryDetailForm frm = (InventoryDetailForm) form;

		// 製品ラベル作成
		createLabel(frm, request);

		return mapping.findForward("success");
	}

	/**
	 * 製品ラベル作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 */
	private void createLabel(final InventoryDetailForm frm,
			final HttpServletRequest request) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		if (!frm.getStrLabelCount().equals("") && !frm.getLotNo().equals("")
				&& !frm.getItemCd().equals("")) {

			// 製品ラベルを作成
			info = inventoryDetailExcelDecorator.createReport(frm, tantoNm,
				AecDateUtils.getCurrentTimestamp(), request.getRemoteAddr());
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			frm.setExcelDownloadFlg(true);

		}

	}

	/* -------------------- setter -------------------- */

	/**
	 * InventoryDetailLogicを設定します。
	 * @param inventoryDetailLogic inventoryDetailLogic
	 */
	public void setInventoryDetailLogic(
			final InventoryDetailLogic inventoryDetailLogic) {
		this.inventoryDetailLogic = inventoryDetailLogic;
	}

	/**
	 * convInventoryLogicを設定します。
	 * @param convInventoryLogic convInventoryLogic
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}

	/**
	 * 製品ラベルＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param inventoryDetailExcelDecorator 製品ラベルＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setInventoryDetailExcelDecorator(
			final InventoryDetailExcelDecorator inventoryDetailExcelDecorator) {
		this.inventoryDetailExcelDecorator = inventoryDetailExcelDecorator;
	}
}
