/*
 * Created on 2007/08/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.pakcarryfile;

/**
 * ProcedureCallクラス.DAO
 * @author t0011036
 */
public interface PakCarryFileDao {
	/**
	 * 運送会社用連携ファイル出力
	 * @param dto dto
	 */
	void makeCarryFile(MakeCarryFileDto dto);

	/** entryPurchase_PROCEDURE_CALL */
	String makeCarryFile_PROCEDURE_CALL = "PAK_CARRY_FILE.MAKE_CARRY_FILE";

}
