/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

/**
 * 採番用プロシージャ呼び出しDAOインタフェース
 * @author 956
 */
public interface CreateNumberCallDao {

	/** BEANアノテーション */
	Class BEAN = ProcedureCall.class;

	/** createItemCd_PROCEDURE_CALL */
	String createItemCd_PROCEDURE_CALL = "FUN_CREATE_ITEM_CD";

	/** createCustomerNo_PROCEDURE_CALL */
	String createCustomerNo_PROCEDURE_CALL = "FUN_CREATE_CUSTOMER_NO";

	/** createItemTrialId_PROCEDURE_CALL */
	String createItemTrialId_PROCEDURE_CALL = "PRO_CREATE_ITEM_TRIAL_ID";

	/** createItemNameId_PROCEDURE_CALL */
	String createItemNameId_PROCEDURE_CALL = "PRO_CREATE_ITEM_NAME_ID";

	/** createCustomerId_PROCEDURE_CALL */
	String createCustomerId_PROCEDURE_CALL = "PRO_CREATE_CUSTOMER_ID";

	/** createBranchId_PROCEDURE_CALL */
	String createBranchId_PROCEDURE_CALL = "PRO_CREATE_BRANCH_ID";

	/** createBranchId_PROCEDURE_CALL */
	String createAplNameId_PROCEDURE_CALL = "PRO_CREATE_APL_NAME_ID";

	/**
	 * 品目コードを採番する
	 * @param dto dto
	 */
	void createItemCd(ItemCdDto dto);
	/**
	 * 客先番号を採番する
	 * @param dto dto
	 */
	void createCustomerNo(CustomerNoDto dto);

	/**
	 * 試作番号の採番処理
	 * @param dto dto
	 */
	void createItemTrialId(ItemTrialIdDto dto);
	/**
	 * 品目名称番号の採番処理
	 * @param dto dto
	 */
	void createItemNameId(ItemNameIdDto dto);
	/**
	 * 客先名称番号の採番処理
	 * @param dto dto
	 */
	void createCustomerId(CustomerIdDto dto);
	/**
	 * 試作番号枝版の採番処理
	 * @param dto dto
	 */
	void createBranchId(BranchIdDto dto);
	/**
	 * アプリケーション名称番号の採番処理
	 * @param dto dto
	 */
	void createAplNameId(AplNameIdDto dto);

}
