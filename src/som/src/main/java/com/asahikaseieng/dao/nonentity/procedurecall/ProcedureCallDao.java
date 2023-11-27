/*
 * Created on 2007/08/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

/**
 * ProcedureCallクラス.DAO
 * @author t0011036
 */
public interface ProcedureCallDao {

	/** BEANアノテーション */
	Class<ProcedureCall> BEAN = com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCall.class;

	/** proZaiCtl_PROCEDURE_CALL */
	String proZaiCtl_PROCEDURE_CALL = "PRO_ZAICTL";

	/** proCrdir_PROCEDURE_CALL */
	String proCrdir_PROCEDURE_CALL = "PRO_CRDIR";

	/** ProNecCalculation_PROCEDURE_CALL */
	String proNecCalculation_PROCEDURE_CALL = "EXEC_MRP";

	/** ProNecCalculation_PROCEDURE_CALL */
	String proNecOrderDevepol_PROCEDURE_CALL = "CALL_EXEC_OP";

	/** proNecPurchaseDecide_PROCEDURE_CALL */
	String proNecPurchaseDecide_PROCEDURE_CALL = "CALL_FIX_OP";

	/** proCostProductDecide_PROCEDURE_CALL */
	String proCostProductDecide_PROCEDURE_CALL = "CALL_EXEC_COST_CALC";

	/** proArUpdate_PROCEDURE_CALL */
	String proArUpdate_PROCEDURE_CALL = "PRO_ARUPDATE";

	/** proArRollback_PROCEDURE_CALL */
	String proArRollback_PROCEDURE_CALL = "PRO_ARROLLBACK";

	/** proClUpdate_PROCEDURE_CALL */
	String proClUpdate_PROCEDURE_CALL = "PRO_CLAIM_UPDATE";

	/** proClRollback_PROCEDURE_CALL */
	String proClRollback_PROCEDURE_CALL = "PRO_CLAIM_UPDATEROLLBACK";

	/** proApUpdate_PROCEDURE_CALL */
	String proApUpdate_PROCEDURE_CALL = "PRO_APUPDATE";

	/** proApRollback_PROCEDURE_CALL */
	String proApRollback_PROCEDURE_CALL = "PRO_APROLLBACK";

	/** proPaymentUpdate_PROCEDURE_CALL */
	String proPaymentUpdate_PROCEDURE_CALL = "PRO_PAYMENTUPDATE";

	/** proPaymentRollback_PROCEDURE_CALL */
	String proPaymentRollback_PROCEDURE_CALL = "PRO_PAYMENTROLLBACK";

	/** funGetEraserNo_PROCEDURE_CALL */
	String funGetEraserNo_PROCEDURE_CALL = "FUN_GET_ERASER_NO";

	/** funGetCreditNo_PROCEDURE_CALL */
	String funGetCreditNo_PROCEDURE_CALL = "FUN_GET_CREDIT_NO";

	/** funGetOffsetNo_PROCEDURE_CALL */
	String funGetOffsetNo_PROCEDURE_CALL = "FUN_GET_OFFSET_NO";

	/** funGetPaymentNo_PROCEDURE_CALL */
	String funGetPaymentNo_PROCEDURE_CALL = "FUN_GET_PAYMENT_NO";

	/** proClUpdateCsm_PROCEDURE_CALL */
	String proClUpdateCsm_PROCEDURE_CALL = "PRO_CLAIM_UPDATE_CSM";

	/** proClRollbackCsm_PROCEDURE_CALL */
	String proClRollbackCsm_PROCEDURE_CALL = "PRO_CLAIM_UPDATEROLLBACK_CSM";

	/** funAutoMakeShippingOrder_PROCEDURE_CALL */
	String funAutoMakeShippingOrder_PROCEDURE_CALL = "PRO_AUTO_MAKE_SHIPPING_ORDER";

	/** funAutoMakeShippingOrder_PROCEDURE_CALL */
	String funCarryShippingOrderMake_PROCEDURE_CALL = "PRO_CARRY_SHIPPING_ORDER_MAKE";

	/** proCarryShippingOrderSend_PROCEDURE_CALL */
	String proCarryShippingOrderSend_PROCEDURE_CALL = "PRO_CARRY_SHIPPING_ORDER_SEND";

	/** proCarryShippingOrderSend_PROCEDURE_CALL */
	String proMakeSalesRecord_PROCEDURE_CALL = "PRO_MAKE_SALES_RECORD";

	/** proUpdateSalesUnitprice_PROCEDURE_CALL */
	String proUpdateSalesUnitprice_PROCEDURE_CALL = "PRO_UPDATE_SALES_UNITPRICE";

	/** proIfMaterialImportResult_PROCEDURE_CALL */
	String proIfMaterialImportResult_PROCEDURE_CALL = "PRO_IF_MATERIAL_IMPORT_RESULT";

	/** proIfPakageStoockBooking_PROCEDURE_CALL */
	String proIfPakageStoockBooking_PROCEDURE_CALL = "PRO_IF_PAKAGE_STOOCK_BOOKING";

	/** proJournalAr_PROCEDURE_CALL */
	String proJournalAr_PROCEDURE_CALL = "PRO_JOURNAL_AR";

	/** proJournalAp_PROCEDURE_CALL */
	String proJournalAp_PROCEDURE_CALL = "PRO_JOURNAL_AP";

	/** updateItemPrice_PROCEDURE_CALL */
	String updateItemPrice_PROCEDURE_CALL = "UPDATE_ITEM_PRICE";

	/** proGetStockReduction_PROCEDURE_CALL */
	String proGetStockReduction_PROCEDURE_CALL = "PRO_GET_STOCK_REDUCTION";

	/** proUpdateIfy1_PROCEDURE_CALL */
	String proUpdateIfy1_PROCEDURE_CALL = "PRO_UPDATE_IFY1";

	/** proUpdateIfy2_PROCEDURE_CALL */
	String proUpdateIfy2_PROCEDURE_CALL = "PRO_UPDATE_IFY2";

	/** proUpdateIfy3_PROCEDURE_CALL */
	String proUpdateIfy3_PROCEDURE_CALL = "PRO_UPDATE_IFY3";

	/** proUpdateIfy4_PROCEDURE_CALL */
	String proUpdateIfy4_PROCEDURE_CALL = "PRO_UPDATE_IFY4";

	/** proUpdateIfya_PROCEDURE_CALL */
	String proUpdateIfya_PROCEDURE_CALL = "PRO_UPDATE_IFYA";

	/** proUpdateIfyb_PROCEDURE_CALL */
	String proUpdateIfyb_PROCEDURE_CALL = "PRO_UPDATE_IFYB";

	/** proUpdateIfyc_PROCEDURE_CALL */
	String proUpdateIfyc_PROCEDURE_CALL = "PRO_UPDATE_IFYC";

	/** proUpdateIfyd_PROCEDURE_CALL */
	String proUpdateIfyd_PROCEDURE_CALL = "PRO_UPDATE_IFYD";

	/** proUpdateIfye_PROCEDURE_CALL */
	String proUpdateIfye_PROCEDURE_CALL = "PRO_UPDATE_IFYE";

	/** proUpdateIfyf_PROCEDURE_CALL */
	String proUpdateIfyf_PROCEDURE_CALL = "PRO_UPDATE_IFYF";

	/** proUpdateIfyg_PROCEDURE_CALL */
	String proUpdateIfyg_PROCEDURE_CALL = "PRO_UPDATE_IFYG";

	/** proUpdateMonthlyInventory_PROCEDURE_CALL */
	String proUpdateMonthlyInventory_PROCEDURE_CALL = "PRO_UPDATE_MONTHLY_INVENTORY";

	/** funGetTaxCdNew_PROCEDURE_CALL */
	String funGetTaxCdNew_PROCEDURE_CALL = "FUN_GET_TAX_CD_NEW";

	// --> 2014/1/28 新消費税対応

	/** funGetTaxRatio_PROCEDURE_CALL */
	String funGetTaxRatio_PROCEDURE_CALL = "FUN_GET_TAX_RATIO";


	/** funGetTaxCd_PROCEDURE_CALL */
	String proCalcDateFromCalendar_PROCEDURE_CALL = "PRO_CALC_DATE_FROM_CALENDAR";

	/** proCtlCarryBcSeq_PROCEDURE_CALL */
	String proCtlCarryBcSeq_PROCEDURE_CALL = "PRO_CTL_CARRY_BC_SEQ";

	/** proGetCarryBcNextVal_PROCEDURE_CALL */
	String proGetCarryBcNextVal_PROCEDURE_CALL = "PRO_GET_CARRY_BC_NEXT_VAL";

	/** funGetCarryFare_PROCEDURE_CALL */
	String funGetCarryFare_PROCEDURE_CALL = "FUN_GET_CARRY_FARE";

	/** proConSalestermsAndEstimate_PROCEDURE_CALL */
	String proConSalestermsAndEstimate_PROCEDURE_CALL = "PRO_CON_SALESTERMSANDESTIMATE";

	/** proGetFrstOrderNo_PROCEDURE_CALL */
	String proGetFrstOrderNo_PROCEDURE_CALL = "PRO_GET_ORDER_GROUP_NO";

	/** proCheckFrstOrderNo_PROCEDURE_CALL */
	String proCheckFrstOrderNo_PROCEDURE_CALL = "PRO_CHECK_ORDER_GROUP_NO";

	/**
	 * 消費税取得処理
	 * @param dto 消費税取得処理用DTO
	 */
	void funGetTaxRatio(FncGetTaxRatioCallDto dto);

	// <-- 2014/1/28 新消費税対応

	//　軽減税率対応
	void funGetTaxCdNew(FncGetTaxCdNewCallDto dto);


	//　休日考慮の日付計算
	void proCalcDateFromCalendar(ProCalcDateFromCalendarCallDto dto);

	/**
	 * エンティティ取得.Location
	 * @param dto dto
	 */
	void proZaiCtl(ProcedureCallDto dto);

	/**
	 * 製造指図作成処理:異常：-1～-5 正常：指図番号
	 * @param dto dto
	 */
	void proCrdir(ProCrdirCallDto dto);

	/**
	 * 原材料所要量計算
	 * @param dto dto
	 */
	void proNecCalculation(ProNecCalculationCallDto dto);

	/**
	 * 発注点発注展開処理
	 * @param dto dto
	 */
	void proNecOrderDevepol(ProNecOrderDevepolCallDto dto);

	/**
	 * 購買計画確定処理
	 * @param dto dto
	 */
	void proNecPurchaseDecide(ProNecPurchaseDecideCallDto dto);

	/**
	 * 原価積上処理
	 * @param dto dto
	 */
	void proCostProductDecide(ProCostProductCallDto dto);

	/**
	 * 売掛更新処理
	 * @param dto 売掛更新処理用Dto
	 */
	void proArUpdate(ProArUpdateCallDto dto);

	/**
	 * 売掛ロールバック処理
	 * @param dto 売掛ロールバック処理用Dto
	 */
	void proArRollback(ProArRollbackCallDto dto);

	/**
	 * 請求更新処理
	 * @param dto 請求更新処理用Dto
	 */
	void proClUpdate(ProClUpdateCallDto dto);

	/**
	 * 請求ロールバック処理
	 * @param dto 請求ロールバック処理用Dto
	 */
	void proClRollback(ProClRollbackCallDto dto);

	/**
	 * 買掛更新処理
	 * @param dto 買掛更新処理用Dto
	 */
	void proApUpdate(ProApUpdateCallDto dto);

	/**
	 * 買掛ロールバック処理
	 * @param dto 買掛ロールバック処理用Dto
	 */
	void proApRollback(ProApRollbackCallDto dto);

	/**
	 * 支払更新処理
	 * @param dto 支払更新処理用Dto
	 */
	void proPaymentUpdate(ProPaymentUpdateCallDto dto);

	/**
	 * 支払ロールバック処理
	 * @param dto 支払更新処理用Dto
	 */
	void proPaymentRollback(ProPaymentRollbackCallDto dto);

	/**
	 * 消込番号取得処理
	 * @param dto 伝票番号取得処理用Dto
	 */
	void funGetEraserNo(FunGetSlipNoCallDto dto);

	/**
	 * 入金番号取得処理
	 * @param dto 入金番号取得処理用DTO
	 */
	void funGetCreditNo(FunGetSlipNoCallDto dto);

	/**
	 * 相殺番号取得処理
	 * @param dto 相殺番号取得処理用DTO
	 */
	void funGetOffsetNo(FunGetSlipNoCallDto dto);

	/**
	 * 支払番号取得処理
	 * @param dto 支払番号取得処理用DTO
	 */
	void funGetPaymentNo(FunGetSlipNoCallDto dto);

	/**
	 * 請求更新処理(カスタマイズ)
	 * @param dto 請求更新処理用Dto
	 */
	void proClUpdateCsm(ProClUpdateCallDto dto);

	/**
	 * 請求ロールバック処理(カスタマイズ)
	 * @param dto 請求ロールバック処理用Dto
	 */
	void proClRollbackCsm(ProClRollbackCallDto dto);

	/**
	 * 出荷自動作成
	 * @param dto 出荷自動作成用Dto
	 */
	void funAutoMakeShippingOrder(ProAutoMakeShippingOrderCallDto dto);

	/**
	 * 運送店別出荷指図指図作成
	 * @param dto 運送店別出荷指図指図作成Dto
	 */
	void funCarryShippingOrderMake(ProCarryShippingOrderMakeCallDto dto);

	/**
	 * 運送店別出荷指図指図送信
	 * @param dto 運送店別出荷指図指図送信Dto
	 */
	void proCarryShippingOrderSend(ProCarryShippingOrderSendCallDto dto);

	/**
	 * 売上レコード新規作成
	 * @param dto 売上レコード新規作成Dto
	 */
	void proMakeSalesRecord(ProMakeSalesRecordCallDto dto);

	/**
	 * 売上単価更新処理
	 * @param dto 売上単価更新処理Dto
	 */
	void proUpdateSalesUnitprice(ProUpdateSalesUnitpriceCallDto dto);

	/**
	 * 購買の受入入力処理
	 * @param dto 購買の受入入力Dto
	 */
	void proIfMaterialImportResult(ProIfMaterialImportResultCallDto dto);

	/**
	 * 包装 検査待ち在庫計上処理
	 * @param dto 包装 検査待ち在庫計上処理Dto
	 */
	void proIfPakageStoockBooking(ProIfPakageStoockBookingCallDto dto);

	/**
	 * 債権会計送信
	 * @param dto 債権会計送信Dto
	 */
	void proJournalAr(ProJournalArCallDto dto);

	/**
	 * 債務会計送信
	 * @param dto 債務会計送信Dto
	 */
	void proJournalAp(ProJournalApCallDto dto);

	/**
	 * 標準販売単価更新
	 * @param dto 標準販売単価更新Dto
	 */
	void updateItemPrice(UpdateItemPriceCallDto dto);

	/**
	 * 仕入割引額取得
	 * @param dto 仕入割引額取得Dto
	 */
	void proGetStockReduction(ProGetStockReductionCallDto dto);

	/**
	 * 原価計算（工程マスタ）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfy1(ProUpdateIfy1CallDto dto);

	/**
	 * 原価計算（品目マスタ）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfy2(ProUpdateIfy2CallDto dto);

	/**
	 * 原価計算（作業手順マスタ）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfy3(ProUpdateIfy3CallDto dto);

	/**
	 * 原価計算（部品構成マスタ）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfy4(ProUpdateIfy4CallDto dto);

	/**
	 * 原価計算（製造オーダーファイル）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfya(ProUpdateIfyaCallDto dto);

	/**
	 * 原価計算（作業日報ファイル）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfyb(ProUpdateIfybCallDto dto);

	/**
	 * 原価計算（注文書ファイル）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfyc(ProUpdateIfycCallDto dto);

	/**
	 * 原価計算（仕入ファイル）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfyd(ProUpdateIfydCallDto dto);

	/**
	 * 原価計算（売上ファイル）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfye(ProUpdateIfyeCallDto dto);

	/**
	 * 原価計算（受払ファイル）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfyf(ProUpdateIfyfCallDto dto);

	/**
	 * 原価計算（材料・製品元帳ファイル）
	 * @param dto 原価計算Dto
	 */
	void proUpdateIfyg(ProUpdateIfygCallDto dto);

	/**
	 * 原価計算（材料・製品元帳ファイル）データ取込
	 * @param dto 原価計算Dto
	 */
	void proUpdateMonthlyInventory(ProUpdateMonthlyInventoryCallDto dto);

	/**
	 * 運送会社用シーケンスコントロール
	 * @param dto 運送会社用シーケンスコントロールDto
	 */
	void proCtlCarryBcSeq(ProCtlCarryBcSeqDto dto);

	/**
	 * 運送会社用シーケンス番号取得
	 * @param dto 運送会社用シーケンス番号取得Dto
	 */
	void proGetCarryBcNextVal(ProGetCarryBcNextValDto dto);

	/**
	 * 運賃取得
	 * @param dto 運賃取得dto
	 */
	void funGetCarryFare(FunGetCarryFareCallDto dto);

	/**
	 * 販売条件・見積単価コピー・削除確定or確定取消処理
	 * @param dto 販売条件・見積単価コピー・削除確定or確定取消処理Dto
	 */
	void proConSalestermsAndEstimate(ProConSalestermsAndEstimateCallDto dto);

}
