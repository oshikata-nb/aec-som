/*
 * Created on 2008/09/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.payment;

/**
 * 
 * PaymentVenderDao．支払い入力－仕入れ先検索用DAO
 * @author tosco
 */
public interface AltPaymentVenderDao {

	/** BEANアノテーション. */
	Class BEAN = AltPaymentVender.class;

	/** ARGSアノテーション getSupplierAutoComplete(). */
	// String getSupplierAutoComplete_ARGS = "venderDivision,venderCd";
	/**
	 * 支払先のAutoComplete用
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return 取引先のリスト
	 */
	// List<AltPaymentVender> getSupplierAutoComplete(final String
	// venderDivision, final String venderCd);
	/** ARGSアノテーション getSupplier(). */
	String getSupplier_ARGS = "venderDivision,venderCd";

	/**
	 * 支払先取得用
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return 取引先データ
	 */
	AltPaymentVender getSupplier(final String venderDivision,
			final String venderCd);

}
