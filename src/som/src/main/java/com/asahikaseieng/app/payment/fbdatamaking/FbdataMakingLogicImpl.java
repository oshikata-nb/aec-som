/*
 * Created on 2008/06/26
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.payment.fbdatamaking;

import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.fbdata.FbData;
import com.asahikaseieng.dao.entity.fbdata.FbDataDao;
import com.asahikaseieng.dao.entity.fbheader.FbHeader;
import com.asahikaseieng.dao.entity.fbheader.FbHeaderDao;
import com.asahikaseieng.dao.entity.fbtrailer.FbTrailer;
import com.asahikaseieng.dao.entity.fbtrailer.FbTrailerDao;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingData;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingDataDao;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingHeader;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingHeaderDao;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingTrailer;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingTrailerDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * ＦＢデータ作成 ロジック実装クラス
 * @author tosco
 */
public class FbdataMakingLogicImpl implements FbdataMakingLogic {

	/** 区分 仕入金額 */
	public static final String UNIT_DIVISION_SIKINGAKU = "SIKINGAKU";

	/** 取引先区分 支払先 */
	public static final String VENDER_DIV_SI = "SI";

	/** ＦＢエンドレコード データ区分 */
	private static final String FB_END_RECORD_DATA_DIVISION = "9";
	/** ＦＢエンドレコード ダミーサイズ */
	private static final int FB_END_RECORD_DUMMY_SIZE = 119;

	/** ＦＢデータ作成 ＦＢヘッダー用Dao */
	private FbdataMakingHeaderDao fbdataMakingHeaderDao;

	/** ＦＢデータ作成 ＦＢデータ用Dao */
	private FbdataMakingDataDao fbdataMakingDataDao;

	/** ＦＢデータ作成 ＦＢトレイラー用Dao */
	private FbdataMakingTrailerDao fbdataMakingTrailerDao;

	/** ＦＢヘッダー更新用Dao */
	private FbHeaderDao fbHeaderDao;

	/** ＦＢデータ更新用Dao */
	private FbDataDao fbDataDao;

	/** ＦＢトレイラー更新用Dao */
	private FbTrailerDao fbTrailerDao;

	/**
	 * コンストラクタ.支払更新
	 */
	public FbdataMakingLogicImpl() {
	}

	/**
	 * ＦＢヘッダー取得（ＦＢヘッダー用）
	 * 
	 * @param  paymentDate 支払日付
	 * @return FbdataMakingHeader ＦＢヘッダーデータ
	 */
	public FbdataMakingHeader getSearchFbHeader(final String paymentDate) {
		FbdataMakingHeader bean = fbdataMakingHeaderDao.getSearchFbHeader(paymentDate);
		return bean;
	}

	/**
	 * 銀行情報取得（ＦＢヘッダー用）
	 * 
	 * @param  paymentDate 支払日付
	 * @return FbdataMakingHeader 銀行情報
	 * @throws NoDataException 対象データなし
	 */
	public FbdataMakingHeader getSearchBankInfo(final String paymentDate) throws NoDataException {
		FbdataMakingHeader bean = fbdataMakingHeaderDao.getSearchBankInfo(paymentDate);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}

	/**
	 * 振込み情報取得（ＦＢデータ用）
	 * 
	 * @param  paymentDate 支払日付
	 * @param  bankMasterCd 支払用銀行
	 * @param  check 数値項目用表示ロジッククラス
	 * @param  flg 0:ＦＢデータ検索 1:支払トラン検索
	 * @return List<FbdataMakingData> 振込み情報
	 * @throws NoDataException 対象データなし
	 */
	public List<FbdataMakingData> getSearchPaymentInfo(
		final String paymentDate, final String bankMasterCd
		, final CheckDigitUtilsLogic check
		, final int flg) throws NoDataException {

		List<FbdataMakingData> list = null;
		String strTrasferAmountN = null;

		if (flg == 0) {
			// ＦＢデータ検索
			list = fbdataMakingDataDao.getSearchFbData(paymentDate.replace("/", ""));
		} else {
			// 支払トラン検索
			list = fbdataMakingDataDao.getSearchPaymentInfo(paymentDate, bankMasterCd);
		}

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (FbdataMakingData bean : list) {
			// 振込金額
			strTrasferAmountN = check.format(UNIT_DIVISION_SIKINGAKU
											, VENDER_DIV_SI
											, null
											, bean.getTransferAmountN());
			bean.setStrTransferAmountN(strTrasferAmountN);
		}

		return list;
	}

	/**
	 * 合計件数、合計金額情報取得（ＦＢトレーラー用）
	 * 
	 * @param  paymentDate 支払日付
	 * @param  bankMasterCd 支払用銀行
	 * @param  check 数値項目用表示ロジッククラス
	 * @param  flg 0:ＦＢデータ検索 1:支払トラン検索
	 * @return FbdataMakingTrailer 合計件数、合計金額情報
	 * @throws NoDataException 対象データなし
	 */
	public FbdataMakingTrailer getSearchTotalInfo(
			final String paymentDate, final String bankMasterCd
			, final CheckDigitUtilsLogic check
			, final int flg) throws NoDataException {

		FbdataMakingTrailer bean = null;

		if (flg == 0) {
			// ＦＢトレーラー検索
			bean = fbdataMakingTrailerDao.getSearchFbTrailer(paymentDate.replace("/", ""));
		} else {
			// 支払トラン検索
			bean = fbdataMakingTrailerDao.getSearchTotalInfo(paymentDate, bankMasterCd);
		}

		if (bean == null) {
			throw new NoDataException();
		}

		// 数値フォーマット(合計振込金額)
		String strTransferTotalAmountN = check.format(UNIT_DIVISION_SIKINGAKU
											, VENDER_DIV_SI
											, null
											, bean.getTransferTotalAmountN());
		bean.setStrTransferTotalAmountN(strTransferTotalAmountN);

		return bean;
	}

	/**
	 * ＦＢデータ登録処理を行う.
	 * @param frm  	   ＦＢデータ作成画面Form
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void insert(final FbdataMakingForm frm
						, final String tantoCd) throws NoDataException, Exception {

		try {
			//-- ＦＢヘッダー登録処理(DEL/INS)
			FbHeader fbHeader = new FbHeader();
			IgnoreCaseBeanUtils.copyProperties(fbHeader, frm.getFbHeader());
			try {
				fbHeaderDao.delete(fbHeader);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 対象データなしの場合あり
			}

			fbHeader.setDlDate(AecDateUtils.getCurrentTimestamp()); 	// ファイル作成日時
			fbHeader.setInputDate(AecDateUtils.getCurrentTimestamp());	// 登録日時
			fbHeader.setInputorCd(tantoCd); // 登録者
			fbHeader.setUpdatorCd(tantoCd); // 更新者
			fbHeaderDao.insert(fbHeader);

			//-- ＦＢデータ登録処理(DEL/INS)
			for (FbdataMakingData bean : frm.getFbDataMakingList()) {
				FbData fbData = new FbData();
				IgnoreCaseBeanUtils.copyProperties(fbData, bean);
				try {
					fbDataDao.delete(fbData);
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 対象データなしの場合あり
				}

				if (!bean.isCheckFlg()) {
					continue;
				}

				fbData.setTrasferAmount(
					AecStringUtils.zeroPadding(fbData.getTransferAmountN(), 10));	// 振込金額
				fbData.setInputDate(AecDateUtils.getCurrentTimestamp());	// 登録日時
				fbData.setInputorCd(tantoCd); // 登録者
				fbData.setUpdatorCd(tantoCd); // 更新者
				fbDataDao.insert(fbData);
			}

			//-- ＦＢトレーラー登録処理(DEL/INS)
			FbdataMakingTrailer trailerBean = frm.getFbTrailer();
			trailerBean.setTransferTotalNumberN(
				AecNumberUtils.convertBigDecimal(frm.getTransferTotalNumber()));
			trailerBean.setTransferTotalAmountN(
				AecNumberUtils.convertBigDecimal(frm.getTransferTotalAmount()));

			FbTrailer fbTrailer = new FbTrailer();
			IgnoreCaseBeanUtils.copyProperties(fbTrailer, trailerBean);
			try {
				fbTrailerDao.delete(fbTrailer);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 対象データなしの場合あり
			}

			fbTrailer.setTrasferTotalNumber(
				AecStringUtils.zeroPadding(fbTrailer.getTransferTotalNumberN(), 6));	// 合計件数
			fbTrailer.setTrasferTotalAmount(
				AecStringUtils.zeroPadding(fbTrailer.getTransferTotalAmountN(), 12));	// 合計金額
			fbTrailer.setInputDate(AecDateUtils.getCurrentTimestamp());	// 登録日時
			fbTrailer.setInputorCd(tantoCd); // 登録者
			fbTrailer.setUpdatorCd(tantoCd); // 更新者
			fbTrailerDao.insert(fbTrailer);

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * ＦＢデータ削除処理を行う.
	 * @param frm  	   ＦＢデータ作成画面Form
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final FbdataMakingForm frm) throws NoDataException, Exception {

		try {
			//-- ＦＢヘッダー削除処理
			FbHeader fbHeader = new FbHeader();
			IgnoreCaseBeanUtils.copyProperties(fbHeader, frm.getFbHeader());
			fbHeaderDao.delete(fbHeader);

			//-- ＦＢデータ削除処理
			for (FbdataMakingData bean : frm.getFbDataMakingList()) {
				FbData fbData = new FbData();
				IgnoreCaseBeanUtils.copyProperties(fbData, bean);
				fbDataDao.delete(fbData);
			}

			//-- ＦＢトレーラー削除処理
			FbdataMakingTrailer trailerBean = frm.getFbTrailer();
			FbTrailer fbTrailer = new FbTrailer();
			IgnoreCaseBeanUtils.copyProperties(fbTrailer, trailerBean);
			fbTrailerDao.delete(fbTrailer);

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * ＦＢデータリスト取得
	 * 
	 * @param  paymentDate 支払日付
	 * @return List<String> ＦＢデータリスト
	 */
	public List<String> getFbdataList(final String paymentDate) {
		List<String> fbdataList = new ArrayList<String>();
		String rcString = null;

		// ＦＢヘッダー取得
		FbdataMakingHeader headerBean = fbdataMakingHeaderDao.getSearchFbHeader(paymentDate);
		if (headerBean != null) {
			rcString = headerBean.getDataDivision();		// データ区分
			rcString += headerBean.getDivisionCd();			// 種別コード
			rcString += headerBean.getCategoryDivision();	// コード区分
			rcString += headerBean.getTransferClientCd();	// 依頼人コード
			rcString += headerBean.getTransferClientName();	// 依頼人名		
			rcString += headerBean.getTrasferDate();		// 振込指定日
			rcString += headerBean.getBankMasterCd();		// 仕向金融機関番号
			rcString += headerBean.getBankKanaName();		// 仕向金融機関名		
			rcString += headerBean.getBranchCd();			// 仕向支店番号
			rcString += headerBean.getBranchKanaName();		// 仕向支店名
			rcString += headerBean.getAccountDivision();	// 預金種目（依頼人）
			rcString += headerBean.getAccountNo();			// 口座番号（依頼人）
			rcString += headerBean.getDummy();				// スペース
			fbdataList.add(rcString);

			// ＦＢデータ取得
			List<FbdataMakingData> list = fbdataMakingDataDao.getSearchFbData(paymentDate);
			if (list != null && list.size() > 0) {
				for (FbdataMakingData bean : list) {
					rcString = bean.getDataDivision();			// データ区分
					rcString += bean.getBankMasterCd();			// 被仕向金融機関番号
					rcString += bean.getBankKanaName();			// 被仕向金融機関名
					rcString += bean.getBranchCd();				// 被仕向支店番号
					rcString += bean.getBranchKanaName();		// 被仕向支店名
					rcString += bean.getClearinghouse();		// 手形交換所番号
					rcString += bean.getDepositDivision();		// 預金種目
					rcString += bean.getAccountNo();			// 口座番号
					rcString += bean.getAccountStockhold();		// 受取人名
					rcString += bean.getTrasferAmount();		// 振込金額
					rcString += bean.getNewCd();				// 新規コード(省略)
					rcString += bean.getCustomerCd1();			// 顧客コード１(省略)
					rcString += bean.getCustomerCd2();			// 顧客コード２(省略)
					rcString += bean.getTransferDivision();		// 振込指定
					rcString += bean.getDummy();				// ダミー
					fbdataList.add(rcString);
				}

				// ＦＢトレーラー取得
				FbdataMakingTrailer trailerBean = fbdataMakingTrailerDao.getSearchFbTrailer(paymentDate);
				if (trailerBean != null) {
					rcString = trailerBean.getDataDivision();			// データ区分
					rcString += trailerBean.getTrasferTotalNumber();	// 合計件数
					rcString += trailerBean.getTrasferTotalAmount();	// 合計金額
					rcString += trailerBean.getDummy();					// ダミー
					fbdataList.add(rcString);
				} else {
					fbdataList.clear();
				}
			} else {
				fbdataList.clear();
			}
			if (fbdataList.size() > 0) {
				// ＦＢエンドレコード設定（固定）				
				rcString = FB_END_RECORD_DATA_DIVISION;								// データ区分
				rcString += AecStringUtils.rightPad("", FB_END_RECORD_DUMMY_SIZE);	// ダミー
				fbdataList.add(rcString);
			}
		}
		return fbdataList;
	}

	/* -------------------- setter -------------------- */

	/**
	 * ＦＢデータ作成 ＦＢヘッダー用Daoを設定します。
	 * @param fbdataMakingHeaderDao ＦＢデータ作成 ＦＢヘッダー用Dao
	 */
	public void setFbdataMakingHeaderDao(final FbdataMakingHeaderDao fbdataMakingHeaderDao) {
		this.fbdataMakingHeaderDao = fbdataMakingHeaderDao;
	}

	/**
	 * ＦＢデータ作成 ＦＢデータ用Daoを設定します。
	 * @param fbdataMakingDataDao ＦＢデータ作成 ＦＢデータ用Dao
	 */
	public void setFbdataMakingDataDao(final FbdataMakingDataDao fbdataMakingDataDao) {
		this.fbdataMakingDataDao = fbdataMakingDataDao;
	}

	/**
	 * ＦＢデータ作成 ＦＢトレイラー用Daoを設定します。
	 * @param fbdataMakingTrailerDao ＦＢデータ作成 ＦＢトレイラー用Dao
	 */
	public void setFbdataMakingTrailerDao(
			final FbdataMakingTrailerDao fbdataMakingTrailerDao) {
		this.fbdataMakingTrailerDao = fbdataMakingTrailerDao;
	}

	/**
	 * ＦＢヘッダー更新用Daoを設定します。
	 * @param fbHeaderDao ＦＢヘッダー更新用Dao
	 */
	public void setFbHeaderDao(final FbHeaderDao fbHeaderDao) {
		this.fbHeaderDao = fbHeaderDao;
	}

	/**
	 * ＦＢデータ更新用Daoを設定します。
	 * @param fbDataDao ＦＢデータ更新用Dao
	 */
	public void setFbDataDao(final FbDataDao fbDataDao) {
		this.fbDataDao = fbDataDao;
	}

	/**
	 * ＦＢトレイラー更新用Daoを設定します。
	 * @param fbTrailerDao ＦＢトレイラー更新用Dao
	 */
	public void setFbTrailerDao(final FbTrailerDao fbTrailerDao) {
		this.fbTrailerDao = fbTrailerDao;
	}

}
