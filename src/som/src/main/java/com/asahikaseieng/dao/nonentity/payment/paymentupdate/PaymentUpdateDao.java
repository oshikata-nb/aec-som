/*
 * Created on 2008/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentupdate;

import java.sql.Date;
import java.util.List;

/**
 * 支払更新用Daoクラス
 * 
 * @author tosco
 */
public interface PaymentUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentupdate.PaymentUpdate.class;

	/** ARGSアノテーション getCreditDate */
	String getCreditDate_ARGS = "venderCd";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,venderCd,payableDate";

	/**
	 * MAX(支払締め日)の翌月取得メソッド
	 * @param venderCd 支払先コード
	 * @return PaymentUpdate 支払ヘッダーデータ(MAX(支払締め日の翌月)
	 */
	PaymentUpdate getPayableDate(final String venderCd);

	/**
	 * 支払ヘッダー検索メソッド
	 * @param organizationCd 部署コード
	 * @param venderCd 支払先コード
	 * @param payableDate 支払締め日
	 * @return List<PaymentUpdate> 支払ヘッダーデータ
	 */
	List<PaymentUpdate> getSearchList(final String organizationCd,
			final String venderCd, final Date payableDate);

}
