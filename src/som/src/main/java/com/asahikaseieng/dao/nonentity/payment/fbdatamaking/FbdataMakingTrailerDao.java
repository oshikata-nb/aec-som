/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.fbdatamaking;


/**
 * ＦＢデータ作成 ＦＢトレーラー用Daoクラス
 * 
 * @author tosco
 */
public interface FbdataMakingTrailerDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingTrailer.class;

	/** ARGSアノテーション getSearchTotalInfo(). */
	String getSearchTotalInfo_ARGS = "paymentDate,bankMasterCd";
	/**
	 * 合計件数、合計金額情報検索メソッド
	 * 
	 * @param  paymentDate 支払日付
	 * @param	bankMasterCd 支払用銀行
	 * @return FbdataMakingData 合計件数、合計金額情報
	 */
	FbdataMakingTrailer getSearchTotalInfo(final String paymentDate, final String bankMasterCd);

	/**
	 * ＦＢトレーラー検索メソッド
	 * 
	 * @param  paymentDate 支払日付
	 * @return FbdataMakingData ＦＢトレーラーデータ
	 */
	FbdataMakingTrailer getSearchFbTrailer(final String paymentDate);

}
