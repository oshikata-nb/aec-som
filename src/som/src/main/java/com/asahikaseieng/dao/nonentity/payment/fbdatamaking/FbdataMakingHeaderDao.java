/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.fbdatamaking;


/**
 * ＦＢデータ作成 ＦＢヘッダー用Daoクラス
 * 
 * @author tosco
 */
public interface FbdataMakingHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingHeader.class;

	/**
	 * 銀行マスタ検索メソッド
	 * 
	 * @param  paymentDate 支払日付
	 * @return FbdataMakingHeader 銀行情報
	 */
	FbdataMakingHeader getSearchBankInfo(final String paymentDate);

	/**
	 * ＦＢヘッダー検索メソッド
	 * 
	 * @param  paymentDate 支払日付
	 * @return FbdataMakingHeader ＦＢヘッダーデータ
	 */
	FbdataMakingHeader getSearchFbHeader(final String paymentDate);

}
