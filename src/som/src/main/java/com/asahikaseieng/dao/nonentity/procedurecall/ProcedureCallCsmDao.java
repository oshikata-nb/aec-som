/*
 * Created on 2008/10/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

/**
 * ProcedureCallCsmDaoクラス.DAO(カスタマイズ)
 * @author tosco
 */
public interface ProcedureCallCsmDao extends ProcedureCallDao {

	/** proClUpdateCsm_PROCEDURE_CALL */
	String proClUpdateCsm_PROCEDURE_CALL = "PRO_CLAIM_UPDATE_CSM";

	/** proClRollbackCsm_PROCEDURE_CALL */
	String proClRollbackCsm_PROCEDURE_CALL = "PRO_CLAIM_UPDATEROLLBACK_CSM";

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

}
