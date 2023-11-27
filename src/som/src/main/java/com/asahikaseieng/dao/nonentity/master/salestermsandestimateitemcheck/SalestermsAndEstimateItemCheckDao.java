/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck;

import java.math.BigDecimal;

/**
 * SalestermsAndEstimateItemCheckDaoクラス
 * @author t0011036
 */
public interface SalestermsAndEstimateItemCheckDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheck.class;

	/** ARGSアノテーション getSalestermsItemCount */
	String getSalestermsItemCount_ARGS = "itemCd";

	/**
	 * SalestermsAndEstimateItemCheckメソッド
	 * 
	 * @param itemCd itemCd
	 * @return SalestermsAndEstimateItemCheck
	 */
	SalestermsAndEstimateItemCheck getSalestermsItemCount(final String itemCd);
	
	/** ARGSアノテーション getEstimateItemCount */
	String getEstimateItemCount_ARGS = "itemCd";

	/**
	 * SalestermsAndEstimateItemCheckメソッド
	 * 
	 * @param itemCd itemCd
	 * @return SalestermsAndEstimateItemCheck
	 */
	SalestermsAndEstimateItemCheck getEstimateItemCount(final String itemCd);
	
	
	/**
	 * 販売条件マスタの保存用テーブルの件数を取得する
	 * 
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 */
	BigDecimal getSalesTermsSaveCount(final String pkNo);

	/**
	 * 見積単価の保存用テーブルの件数を取得する
	 * 
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 */
	BigDecimal getEstimateSaveCount(final String pkNo);

	/**
	 * 販売条件マスタのテーブルの件数を取得する
	 * 
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 */
	BigDecimal getSalesTermsCount(final String itemCd);

	/**
	 * 見積単価のテーブルの件数を取得する
	 * 
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 */
	BigDecimal getEstimateCount(final String itemCd);


}