/*
 * Created on Tue Apr 28 09:03:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbdata;

/**
 * FbDataDaoインターフェース.
 * @author t0011036
 */
public interface FbDataDao {

	/** BEANアノテーション. */
	Class BEAN = FbData.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean FbData
	 * @return Insert件数
	 */
	int insert(FbData bean);

	/**
	 * Update.
	 * @param bean FbData
	 * @return Update件数
	 */
	int update(FbData bean);

	/**
	 * Delete.
	 * @param bean FbData
	 * @return Delete件数
	 */
	int delete(FbData bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ACCOUNT_NO,BANK_MASTER_CD,BRANCH_CD,DEPOSIT_DIVISION,PAYMENT_DATE";

	/**
	 * エンティティ取得.
	 * @param accountNo accountNo
	 * @param bankMasterCd bankMasterCd
	 * @param branchCd branchCd
	 * @param depositDivision depositDivision
	 * @param paymentDate paymentDate
	 * @return FbData
	 */
	FbData getEntity(String accountNo, String bankMasterCd, String branchCd,
			String depositDivision, String paymentDate);
}
