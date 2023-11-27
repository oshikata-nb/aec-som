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
import com.asahikaseieng.dao.nonentity.inventorymovedetail.InventoryMoveDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 在庫移動入力詳細 Actionクラス.
 * @author tanaka
 */
public final class InventoryMoveDetailAction extends AbstractAction {

	private InventoryMoveDetailLogic inventoryMoveDetailLogic;

	private ConvInventoryLogic convInventoryLogic;

	/** ローリー区分 2:ローリー */
	private static final BigDecimal LORRY_DIVISION_LORRY = new BigDecimal("2");

	private static final String KG_CD = "1";

	/**
	 * コンストラクタ
	 */
	public InventoryMoveDetailAction() {
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

		InventoryMoveDetailForm frm = (InventoryMoveDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_INVENTORY_MOVE,
			Constants.TAB_ID_INVENTORY_MOVE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		InventoryMoveDetail bean = inventoryMoveDetailLogic.getDetailEntity(frm
				.getOutLocationCd(), frm.getItemCd(), frm.getLotNo());

		/* 理由マスタ検索 */
		ReasonDefaultDetail beanReason = inventoryMoveDetailLogic
				.getReasonDefaultEntity();

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);
		IgnoreCaseBeanUtils.copyProperties(frm, beanReason);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 入力チェック用に退避 */
		frm.setSavPackQty(frm.getDispPackQty());
		frm.setSavFraction(frm.getDispFraction());

		/* 製品は初期値なし */
		// if (!frm.getTypeDivision().equals(new BigDecimal("1"))
		// && !frm.getTypeDivision().equals(new BigDecimal("2"))) {
		// frm.setPackQty(null);
		// frm.setFraction(null);
		// }
		/* 数値文字列に変換 */
		frm.setStrDispPackQty(checker.format(
			frm.getUnitOfOperationManagement(), frm.getDispPackQty()));
		frm.setStrPackQty(checker.format(frm.getUnitOfOperationManagement(),
			frm.getPackQty()));
		frm.setStrFraction(checker.format(frm.getUnitOfFractionManagement(),
			frm.getFraction()));
		frm.setStrDispFraction(checker.format(
			frm.getUnitOfFractionManagement(), frm.getDispFraction()));

		/* 総量の単位はKgなのでKg桁数設定をする */
		// frm.setStrInventoryQty(checker.format(frm
		// .getUnitOfOperationManagement(), frm.getInventoryQty()));
		frm.setStrInventoryQty(checker.format(KG_CD, frm.getInventoryQty()));

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

		InventoryMoveDetailForm frm = (InventoryMoveDetailForm) form;

		Timestamp inDate = AecDateUtils.getTimestampYmdFormat(frm
				.getStrLastInDate()); /* 入力日付 */
		Timestamp nowDate = AecDateUtils.getCurrentTimestamp(); /* 現在日 */

		/* 日付チェック */
		if (0 < inDate.compareTo(nowDate)) {
			/* エラーメッセージ */
			saveError(request, "errors.inventory.move.future.last.in.date");
			return mapping.findForward("success");
		}

		String checkLorryItemCd = null;
		boolean isLorry = false;

		ItemDetail beanItem = inventoryMoveDetailLogic.getItemEntity(frm
				.getItemCd());

		if (beanItem == null) {
			/* データが存在しない場合 */
			saveError(request, "errors.nodata.inventory.move.item.cd");
			return mapping.findForward("success");
		} else {
			PurchaseAttributeQueueDetail beanPurchase = inventoryMoveDetailLogic
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

		LocationDetail beanLocation = inventoryMoveDetailLogic
				.getLocationEntity(frm.getInLocationCd());

		if (beanLocation == null) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.inventory.move.in.location.cd");
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
			ReasonDetail beanReason = inventoryMoveDetailLogic
					.getReasonEntity(frm.getRyCd());

			if (beanReason == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.inventory.move.ry.cd");
				return mapping.findForward("success");
			}

			frm.setRyDescription(beanReason.getRyDescription());
		}

		frm.setLastInDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrLastInDate()));
		frm.setPackQty(AecNumberUtils.convertBigDecimal(frm.getStrPackQty())); /* 荷姿数 */
		frm.setFraction(AecNumberUtils.convertBigDecimal(frm.getStrFraction())); /* 端数 */

		BigDecimal packQty = BigDecimal.ZERO;
		BigDecimal fraction = BigDecimal.ZERO;

		if (frm.getTypeDivision().equals(new BigDecimal("1"))
				|| frm.getTypeDivision().equals(new BigDecimal("2"))
				|| frm.getTypeDivision().equals(new BigDecimal("3"))) {
			/* 品目が原料 or 包材 or 中間品の場合 */
			packQty = AecNumberUtils.convertNullToZero(frm.getDispPackQty());
			fraction = AecNumberUtils.convertNullToZero(frm.getDispFraction());
		} else {
			packQty = AecNumberUtils.convertNullToZero(frm.getPackQty());
			fraction = AecNumberUtils.convertNullToZero(frm.getFraction());
		}

		/* 在庫量換算 */
		ConvInventoryResult inventory = convInventoryLogic.packToInventory(frm
				.getItemCd(), packQty, fraction);
		ConvInventoryResult dispInventory = convInventoryLogic.packToInventory(
			frm.getItemCd(), frm.getSavPackQty(), frm.getSavFraction());

		/* 在庫量チェック */
		if (dispInventory.getInventoryQty().compareTo(
			inventory.getInventoryQty()) < 0) {
			saveError(request, "errors.inventory.move.stock.over");
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
			inventoryMoveDetailLogic.stockUpdate(frm, result.getTypeDivision(),
				getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			/* エラーメッセージ */
			saveError(request, "errors.inventory.move.stock.update");
			return mapping.findForward("success");
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		frm.setDirtyFlg(null);

		return mapping.findForward("back");
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

		InventoryMoveDetailForm frm = (InventoryMoveDetailForm) form;

		/* 品目取得 */
		ItemDetail bean = inventoryMoveDetailLogic.getItemEntity(frm
				.getItemCd());

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
			/* 総量 = 荷姿数 * Kg換算係数(在庫) + 端数 * Kg換算係数(端数) */
			frm.setInventoryQty(packQty.multiply(kgOfFractionManagement).add(
				fraction.multiply(kgConversionCoefficient)));
			frm.setStrInventoryQty(frm.getInventoryQty().toString());

			/* null ---> 数値変換 */
			// BigDecimal packQty = AecNumberUtils.convertNullToZero(frm
			// .getPackQty());
			// BigDecimal kgOfFractionManagement = AecNumberUtils
			// .convertNullToZero(bean.getKgOfFractionManagement());
			// BigDecimal fraction = AecNumberUtils.convertNullToZero(frm
			// .getFraction());
			// BigDecimal kgConversionCoefficient = AecNumberUtils
			// .convertNullToZero(bean.getKgConversionCoefficient());
			//
			// /* 品目が原料 or 中間品の場合か */
			// if (frm.getTypeDivision().equals(new BigDecimal("1"))
			// || frm.getTypeDivision().equals(new BigDecimal("3"))) {
			// /* 総量 = 荷姿数 * Kg換算係数(在庫) + 端数 * Kg換算係数(端数) */
			// frm.setInventoryQty(packQty.multiply(kgOfFractionManagement)
			// .add(
			// fraction.divide(kgConversionCoefficient).setScale(
			// 0, RoundingMode.UP)));
			// frm.setStrInventoryQty(frm.getInventoryQty().toString());
			// } else {
			// /* 総量 = 荷姿数 / Kg換算係数(在庫) */
			// frm.setInventoryQty(packQty.multiply(kgOfFractionManagement));
			// frm.setStrInventoryQty(frm.getInventoryQty().toString());
			// }
			//
			// /* 文字列 ---> 数値変換 */
			// frm.setInventoryQty(AecNumberUtils.convertBigDecimal(frm
			// .getStrInventoryQty()));
			/* 数値桁数チェック部品呼び出し */
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			/* 演算結果を丸める */
			if (!StringUtils.isEmpty(bean.getUnitOfOperationManagement())) {
				frm.setInventoryQty(check.round(bean
						.getUnitOfOperationManagement(), null, null, frm
						.getInventoryQty()));
			}

			/* 数値 ---> 文字列変換 */
			// frm.setStrPackQty(check.format(bean.getUnitOfOperationManagement(),
			// AecNumberUtils.convertBigDecimal(frm.getStrPackQty())));
			// frm.setStrFraction(check.format(bean.getUnitOfFractionManagement(),
			// AecNumberUtils.convertBigDecimal(frm.getStrFraction())));
			frm.setStrInventoryQty(check.format(bean
					.getUnitOfOperationManagement(), frm.getInventoryQty()));
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryMoveDetailLogicを設定します。
	 * @param inventoryMoveDetailLogic inventoryMoveDetailLogic
	 */
	public void setInventoryMoveDetailLogic(
			final InventoryMoveDetailLogic inventoryMoveDetailLogic) {
		this.inventoryMoveDetailLogic = inventoryMoveDetailLogic;
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
