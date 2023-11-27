/*
 * Created on 2007/08/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.zaictrl;

/**
 * ProcedureCallクラス.DAO
 * @author t0011036
 */
public interface ZaiCtrlDao {

	/** BEANアノテーション */
	// Class BEAN =
	// com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrl.class;
	/** entryOrder_PROCEDURE_CALL */
	String entryOrder_PROCEDURE_CALL = "ZAIKOUKEHARAI.ENTRY_ORDER";

	/**
	 * 受注入力(一）
	 * @param dto dto
	 */
	void entryOrder(StrCodeAndNumberDto dto);

	/** entryPurchase_PROCEDURE_CALL */
	String entryPurchase_PROCEDURE_CALL = "ZAIKOUKEHARAI.ENTRY_PURCHASE";

	/**
	 * 購買入力(一）
	 * @param dto dto
	 */
	void entryPurchase(StrCodeDto dto);

	/** receivePurchase_PROCEDURE_CALL */
	String receivePurchase_PROCEDURE_CALL = "ZAIKOUKEHARAI.RECEIVE_PURCHASE";

	/**
	 * 受入入力(一）
	 * @param dto dto
	 */
	void receivePurchase(StrCodeDto dto);

	/** completePurchase_PROCEDURE_CALL */
	String completePurchase_PROCEDURE_CALL = "ZAIKOUKEHARAI.COMPLETE_PURCHASE";

	/**
	 * 受入入力(一）
	 * @param dto dto
	 */
	void completePurchase(StrCodeDto dto);

	/** entryDirection_PROCEDURE_CALL */
	String entryDirection_PROCEDURE_CALL = "ZAIKOUKEHARAI.ENTRY_DIRECTION";

	/**
	 * 製造指図入力
	 * @param dto dto
	 */
	void entryDirection(StrCodeAndNumberDto dto);

	/** entryFormula_PROCEDURE_CALL */
	String entryFormula_PROCEDURE_CALL = "ZAIKOUKEHARAI.ENTRY_FORMULA";

	/**
	 * 配合指図入力
	 * @param dto dto
	 */
	void entryFormula(StrCodeAnd3NumberDto dto);

	/** entryFormula_PROCEDURE_CALL */
	String resultFormula_PROCEDURE_CALL = "ZAIKOUKEHARAI.RESULT_FORMULA";

	/**
	 * 配合実績入力
	 * @param dto dto
	 */
	void resultFormula(StrCodeAnd3NumberDto dto);

	/** entryFormula_PROCEDURE_CALL */
	String resultInjection_PROCEDURE_CALL = "ZAIKOUKEHARAI.RESULT_INJECTION";

	/**
	 * 配合実績入力
	 * @param dto dto
	 */
	void resultInjection(StrCodeAnd3NumberDto dto);

	/** entryFormula_PROCEDURE_CALL */
	String resultDirection_PROCEDURE_CALL = "ZAIKOUKEHARAI.RESULT_DIRECTION";

	/**
	 * 製造実績入力
	 * @param dto dto
	 */
	void resultDirection(StrCodeAndNumberDto dto);

	/** entryFormula_PROCEDURE_CALL */
	String resultGrade_PROCEDURE_CALL = "ZAIKOUKEHARAI.RESULT_GRADE";

	/**
	 * 製造実績入力
	 * @param dto dto
	 */
	void resultGrade(StrCodeAndNumberDto dto);

	/** entryInout_PROCEDURE_CALL */
	String entryInout_PROCEDURE_CALL = "ZAIKOUKEHARAI.ENTRY_INOUT";

	/**
	 * 在庫入出庫入力
	 * @param dto dto
	 */
	void entryInout(InoutDto dto);

	/** entryShipping_PROCEDURE_CALL */
	String entryShipping_PROCEDURE_CALL = "ZAIKOUKEHARAI.ENTRY_SHIPPING";

	/**
	 * 出荷入力
	 * @param dto dto
	 */
	void entryShipping(StrCodeAndNumberDto dto);

	/** returnDelivery_PROCEDURE_CALL */
	String returnDelivery_PROCEDURE_CALL = "ZAIKOUKEHARAI.RETURN_DELIVERY";

	/**
	 * 返品入力
	 * @param dto dto
	 */
	void returnDelivery(StrCodeDto dto);

	/** entryInout_PROCEDURE_CALL */
	String entrySales_PROCEDURE_CALL = "ZAIKOUKEHARAI.ENTRY_SALES";

	/**
	 * 預品入力
	 * @param dto dto
	 */
	void entrySales(InoutDto dto);
}
