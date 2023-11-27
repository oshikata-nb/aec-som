/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.fbdatamaking;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingData;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingHeader;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingTrailer;
import com.asahikaseieng.exception.NoDataException;

/**
 * ＦＢデータ作成 ロジッククラス interface. 
 * @author tosco
 */
public interface FbdataMakingLogic {

	/**
	 * ＦＢヘッダー取得（ＦＢヘッダー用）
	 * 
	 * @param  paymentDate 支払日付(YYYYMMDD)
	 * @return FbdataMakingHeader ＦＢヘッダーデータ
	 */
	FbdataMakingHeader getSearchFbHeader(final String paymentDate);

	/**
	 * 銀行情報取得（ＦＢヘッダー用）
	 * 
	 * @param  paymentDate 支払日付(YYYY/MM/DD)
	 * @return FbdataMakingHeader 銀行情報
	 * @throws NoDataException 対象データなし
	 */
	FbdataMakingHeader getSearchBankInfo(final String paymentDate) throws NoDataException;

	/**
	 * 振込み情報取得（ＦＢデータ用）
	 * 
	 * @param  paymentDate 支払日付(YYYY/MM/DD)
	 * @param  bankMasterCd 支払用銀行
	 * @param  check 数値項目用表示ロジッククラス
	 * @param  flg 0:ＦＢデータ検索 1:支払トラン検索
	 * @return List<FbdataMakingData> 振込み情報
	 * @throws NoDataException 対象データなし
	 */
	List<FbdataMakingData> getSearchPaymentInfo(
								final String paymentDate, final String bankMasterCd
								, final CheckDigitUtilsLogic check
								, final int flg) throws NoDataException;

	/**
	 * 合計件数、合計金額情報取得（ＦＢトレーラー用）
	 * 
	 * @param  paymentDate 支払日付(YYYY/MM/DD)
	 * @param  bankMasterCd 支払用銀行
	 * @param check 数値項目用表示ロジッククラス
	 * @param  flg 0:ＦＢデータ検索 1:支払トラン検索
	 * @return FbdataMakingTrailer 合計件数、合計金額情報
	 * @throws NoDataException 対象データなし
	 */
	FbdataMakingTrailer getSearchTotalInfo(
								final String paymentDate, final String bankMasterCd
								, final CheckDigitUtilsLogic check
								, final int flg) throws NoDataException;

	/**
	 * ＦＢデータ登録処理を行う.
	 * @param frm  	   ＦＢデータ作成画面Form
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void insert(final FbdataMakingForm frm
				, final String tantoCd) throws NoDataException, Exception;

	/**
	 * ＦＢデータ削除処理を行う.
	 * @param frm  	   ＦＢデータ作成画面Form
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void delete(final FbdataMakingForm frm) throws NoDataException, Exception;

	/**
	 * ＦＢデータリスト取得
	 * 
	 * @param  paymentDate 支払日付
	 * @return List<String> ＦＢデータリスト
	 */
	List<String> getFbdataList(final String paymentDate);


}
