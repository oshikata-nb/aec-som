/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.fbdatamaking;

import java.util.List;


/**
 * ＦＢデータ作成 ＦＢデータ用Daoクラス
 * 
 * @author tosco
 */
public interface FbdataMakingDataDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingData.class;

	/** ARGSアノテーション getSearchPaymentInfo(). */
	String getSearchPaymentInfo_ARGS = "paymentDate,bankMasterCd";
	/**
	 * 振込み情報検索メソッド
	 * 
	 * @param  paymentDate 支払日付
	 * @param	bankMasterCd 支払用銀行
	 * @return List<FbdataMakingData> 振込み情報
	 */
	List<FbdataMakingData> getSearchPaymentInfo(final String paymentDate, final String bankMasterCd);

	/**
	 * ＦＢデータ検索メソッド
	 * 
	 * @param  paymentDate 支払日付
	 * @return List<FbdataMakingData> ＦＢデータリスト
	 */
	List<FbdataMakingData> getSearchFbData(final String paymentDate);

}
