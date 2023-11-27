/*
 * Created on 2007/08/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.pakorderfileimport;

/**
 * ProcedureCallクラス.DAO
 * @author t0011036
 */
public interface PakOrderFileImportDao {
	/**
	 * 受注取込基底データの作成
	 * @param dto dto
	 */
	void convertOrderBase(ConvertOrderBaseDto dto);

	/** convertOrderBase_PROCEDURE_CALL */
	String convertOrderBase_PROCEDURE_CALL = "PAK_ORDER_FILE_IMPORT.CONVERT_ORDER_BASE";

	/**
	 * 受注取込基底データの作成
	 * @param dto dto
	 */
	void convertFrstOrder(ConvertFrstOrderDto dto);

	/** convertFrstOrder_PROCEDURE_CALL */
	String convertFrstOrder_PROCEDURE_CALL = "PAK_ORDER_FILE_IMPORT.CONVERT_FRST_ORDER";
}
