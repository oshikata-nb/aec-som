/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.payment;

import java.util.List;

/**
 * 支払入力－取引先マスタのオートコンプリートDaoクラス
 * @author tosco
 */
public interface PaymentVenderForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.vender.payment.PaymentVenderForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,venderCd,rowlimit";

	/**
	 * getSearchListメソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 支払先コード
	 * @param rowlimit rowlimit
	 * @return PaymentVenderForAutoComplete
	 */
	List<PaymentVenderForAutoComplete> getSearchList(
			final String organizationCd, final String venderCd,
			final String rowlimit);
}
