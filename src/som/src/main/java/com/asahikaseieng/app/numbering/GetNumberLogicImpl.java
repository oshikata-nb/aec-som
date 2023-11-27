/*
 * Created on 2008/04/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.numbering;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.dailynumber.DailyNumber;
import com.asahikaseieng.dao.entity.dailynumber.DailyNumberDao;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.dao.nonentity.master.namesnolist.NamesNoListDao;
import com.asahikaseieng.dao.nonentity.numbering.NumberingDailyNumberList;
import com.asahikaseieng.dao.nonentity.numbering.NumberingDailyNumberListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 自社マスタ検索 ロジック実装クラス
 * @author tosco
 */
public class GetNumberLogicImpl implements GetNumberLogic {

	/** 名称区分 NUM:発番番号 */
	private static final String NAME_DIVISION = "NUM";

	/** 名称コード HT:発注番号 */
	private static final String NAME_CD_HT = "HT";

	/** 名称コード SI:仕入番号 */
	private static final String NAME_CD_SI = "SI";

	/** 名称コード JT:受注番号 */
	private static final String NAME_CD_JT = "JT";

	/** 名称コード SK:出荷番号 */
	private static final String NAME_CD_SK = "SK";

	/** 名称コード UR:売上番号 */
	private static final String NAME_CD_UR = "UR";

	/** 名称コード MI:見積番号 */
	private static final String NAME_CD_MI = "MI";

	/** 名称コード SLSK:出荷伝票番号 */
	private static final String NAME_CD_SLSK = "SLSK";

	/** 名称コード SLUR:売上伝票番号 */
	private static final String NAME_CD_SLUR = "SLUR";
	
	/** 名称コード PK:先付け受注番号 */
	private static final String NAME_CD_FO = "FO";
	
	/** 名称コード OI:一時取込番号 */
	private static final String NAME_CD_OI = "OI";

	/** 名称コード SAES:販売条件・見積単価　コピー・削除pkNo */
	private static final String NAME_CD_SAES = "SAES";	

	/** 番号桁数 8桁 */
	private static final int SIZE_8 = 8;

	/** 番号桁数 9桁 */
	private static final int SIZE_9 = 9;

	/** 原材料用入荷ロット番号発番用ＫＥＹコード */
	private static final String DAILY_NUMBER_KEY_NLOTNO = "NLOTNO";

	/** 外注製品用入荷ロット番号発番用ＫＥＹコード */
	private static final String DAILY_NUMBER_KEY_PLOTNO = "PLOTNO";

	/** 製造指図番号発番用ＫＥＹコード */
	private static final String DAILY_NUMBER_KEY_DIRECTIONNO = "DIRECTIONNO";

	/** 包装指図番号発番用ＫＥＹコード */
	private static final String DAILY_NUMBER_KEY_PKGDIRECTIONNO = "PKGDIRECTIONNO";

	/** 予備溶解バーコード発番用ＫＥＹコード */
	private static final String DAILY_NUMBER_KEY_YOBIYOKAIBCR = "YOBIYOKAIBCR";

	/** 日毎発番管理データ取得初期値西暦8桁 */
	private static final String DAILY_NUMBER_INIT_DATAKEY = "20000101";

	/** 日毎発番管理データ西暦8桁フォーマット */
	private static final String DAILY_NUMBER_DATEKEY_FORMAT = "yyyyMMdd";

	/** 日毎発番番号編集用西暦8桁フォーマット */
	private static final String DAILY_NUMBER_EDIT_DATEKEY_FORMAT = "yyMMdd";

	/** 日毎発番管理データ再取得までの待ち時間 */
	private static final long DAILY_NUMBER_RETRY_SLEEP_TIME = 1000;

	/** 日毎発番管理データ再取得回数 */
	private static final long DAILY_NUMBER_RETRY_COUNT = 5;

	private NamesNoListDao namesNoListDao;

	private NamesDetailDao namesDetailDao;

	/** 日毎発番管理テーブル発番データDao */
	private NumberingDailyNumberListDao numberingDailyNumberListDao;

	/** 日毎発番管理テーブルDao */
	private DailyNumberDao dailyNumberDao;

	/**
	 * コンストラクタ
	 */
	public GetNumberLogicImpl() {
	}

	/**
	 * 発注書NOを取得 ※発注書NOのみ先頭固定文字なし
	 * 
	 * @return String 発注書NO
	 * @throws NoDataException データが存在しない例外
	 */
	public String getOrderSheetNo() throws NoDataException {
		String no = null;

		// 発番処理
		no = getNumber(null, SIZE_8);

		return no;
	}

	/**
	 * 出荷伝票番号を取得
	 * 
	 * @return String 出荷伝票番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getSlipShippingNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		// String prefix = getPrefix(NAME_CD_SLSK);
		// 発番処理
		String number = getNumber(NAME_CD_SLSK, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = number;

		return no;
	}

	/**
	 * 売上伝票番号を取得
	 * 
	 * @return String 売上伝票番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getSlipSalesNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		// String prefix = getPrefix(NAME_CD_SLUR);
		// 発番処理
		String number = getNumber(NAME_CD_SLUR, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = number;

		return no;
	}

	/**
	 * 見積番号を取得
	 * 
	 * @return String 見積番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getEstimateNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_MI);
		// 発番処理
		String number = getNumber(NAME_CD_MI, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}

	/**
	 * 発注番号を取得
	 * 
	 * @return String 発注番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getBuySubcontractOrderNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_HT);
		// 発番処理
		String number = getNumber(NAME_CD_HT, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}

	/**
	 * 仕入番号を取得
	 * 
	 * @return String 仕入番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getSiSlipNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_SI);
		// 発番処理
		String number = getNumber(NAME_CD_SI, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}

	/**
	 * 受注番号を取得
	 * 
	 * @return String 受注番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getSupplierOrderNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_JT);
		// 発番処理
		String number = getNumber(NAME_CD_JT, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}

	/**
	 * 出荷番号を取得
	 * 
	 * @return String 出荷番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getShipping() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_SK);
		// 発番処理
		String number = getNumber(NAME_CD_SK, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}

	/**
	 * 売上番号を取得
	 * 
	 * @return String 売上番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getUrSlipNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_UR);
		// 発番処理
		String number = getNumber(NAME_CD_UR, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}
	
	/**
	 * 販売条件・見積単価　コピー・削除テーブル用のpkNoを取得
	 * 
	 * @return String pkNo
	 * @throws NoDataException データが存在しない例外
	 */
	public String getSalestermsAndEstimatePkNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		// String prefix = getPrefix(NAME_CD_SAES);
		// 発番処理
		String number = getNumber(NAME_CD_SAES, SIZE_9);
		// 番号＝発番番号
		no = number;

		return no;
	}


	/**
	 * 各種名称マスタから発番番号の先頭固定文字を取得
	 * @param nameCd 名称コード
	 * @return String 先頭固定文字
	 * @throws NoDataException
	 */
	private String getPrefix(final String nameCd) throws NoDataException {
		String prefix = null;

		// 各種名称マスタから先頭固定文字を取得
		NamesDetail bean = namesDetailDao.getEntity(NAME_DIVISION, nameCd);
		if (bean == null) {
			throw new NoDataException();
		}
		prefix = bean.getName01(); // 名称１

		return prefix;
	}

	/**
	 * オラクルシーケンスにて連番を発番
	 * @param nameCd 名称コード
	 * @param size 桁数
	 * @return String 先頭固定文字
	 * @throws NoDataException
	 */
	private String getNumber(final String nameCd, final int size)
			throws NoDataException {
		String number = null;

		// オラクルシーケンスにて連番を発番
		BigDecimal num = namesNoListDao.getNo(nameCd);
		if (num == null) {
			throw new NoDataException();
		}
		// ゼロサプレス
		number = StringUtils.leftPad(num.toString(), size, "0");

		return number;
	}
	
	/**
	 * 取込番号を取得
	 * 
	 * @return String 取込番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getFrstOrderNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_FO);
		// 発番処理
		String number = getNumber(NAME_CD_FO, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}
	
	/**
	 * 一時取込番号を取得
	 * 
	 * @return String 一時取込番号
	 * @throws NoDataException データが存在しない例外
	 */
	public String getTempNo() throws NoDataException {
		String no = null;

		// 先頭固定文字を取得
		String prefix = getPrefix(NAME_CD_OI);
		// 発番処理
		String number = getNumber(NAME_CD_OI, SIZE_9);
		// 番号＝先頭固定文字＋発番番号
		no = prefix + number;

		return no;
	}

	/**
	 * 原材料用入荷ロット番号を発番
	 * @param timestamp 発番日
	 * @return String 原材料用入荷ロット番号<br>
	 *         発番失敗時はNULLを返却
	 */
	public String getNLotNo(final Timestamp timestamp) {
		return numberingDailyNumber(DAILY_NUMBER_KEY_NLOTNO, timestamp);
	}

	/**
	 * 外注製品用入荷ロット番号を発番
	 * @param timestamp 発番日
	 * @return String 外注製品用入荷ロット番号<br>
	 *         発番失敗時はNULLを返却
	 */
	public String getPLotNo(final Timestamp timestamp) {
		return numberingDailyNumber(DAILY_NUMBER_KEY_PLOTNO, timestamp);
	}

	/**
	 * 製造指図番号を発番
	 * @param timestamp 発番日
	 * @return String 製造指図番号<br>
	 *         発番失敗時はNULLを返却
	 */
	public String getDirectionNo(final Timestamp timestamp) {
		return numberingDailyNumber(DAILY_NUMBER_KEY_DIRECTIONNO, timestamp);
	}

	/**
	 * 包装指図番号を発番
	 * @param timestamp 発番日
	 * @return String 包装指図番号<br>
	 *         発番失敗時はNULLを返却
	 */
	public String getPkgDirectionNo(final Timestamp timestamp) {
		return numberingDailyNumber(DAILY_NUMBER_KEY_PKGDIRECTIONNO, timestamp);
	}

	/**
	 * 予備溶解バーコードを発番
	 * @param timestamp 発番日
	 * @return String 予備溶解バーコード<br>
	 *         発番失敗時はNULLを返却
	 */
	public String getYobiYokaiBcr(final Timestamp timestamp) {
		return numberingDailyNumber(DAILY_NUMBER_KEY_YOBIYOKAIBCR, timestamp);
	}
	


	/**
	 * 日毎発番管理テーブルより発番を行う
	 * @param key 日毎発番管理テーブルより取得する際のＫＥＹコード
	 * @param timestamp 発番日
	 * @return String 発番した番号<br>
	 *         発番失敗時はNULLを返却
	 */
	private String numberingDailyNumber(final String key,
			final Timestamp timestamp) {
		String number = null;
		int retryCount = 0;
		while (retryCount < DAILY_NUMBER_RETRY_COUNT) {

			// 発番編集を行う
			number = editDailyNumber(key, timestamp);
			if (number != null) {
				break;
			}
			retryCount++;

			// 再取得までスリープする
			try {
				Thread.sleep(DAILY_NUMBER_RETRY_SLEEP_TIME);
			} catch (InterruptedException e) {
				continue;
			}
		}
		return number;
	}

	/**
	 * 日毎発番管理テーブルより発番編集を行う
	 * @param key 日毎発番管理テーブルより取得する際のＫＥＹコード
	 * @param timestamp 発番日
	 * @return String 発番した番号<br>
	 *         発番失敗時はNULLを返却
	 */
	private String editDailyNumber(final String key, final Timestamp timestamp) {
		String number = null;
		String datekey = null;
		Timestamp datekeyTimestamp = timestamp;
		List<NumberingDailyNumberList> list;

		// NULLの場合は、現在日時を取得する
		if (datekeyTimestamp == null) {
			datekeyTimestamp = AecDateUtils.getCurrentTimestamp();
		}

		// 西暦8桁を編集（検索用キー）
		datekey = AecDateUtils.dateFormat(datekeyTimestamp,
			DAILY_NUMBER_DATEKEY_FORMAT);

		// 発番用の日付を編集
		String numberDateKey = AecDateUtils.dateFormat(datekeyTimestamp,
			DAILY_NUMBER_EDIT_DATEKEY_FORMAT);

		try {

			// 日毎発番管理テーブル検索
			list = numberingDailyNumberListDao.getDailyNumber(key, datekey,
				DAILY_NUMBER_INIT_DATAKEY);
			if (list == null || list.size() == 0) {
				return null;
			}
			NumberingDailyNumberList bean = list.get(0);

			int noFigure = bean.getMaxConsecutiveNo().toString().length();

			if (list.size() == 1) {
				// 対象日の発番レコード無し

				number = bean.getFixedCharNm();
				number = number + numberDateKey;
				BigDecimal nowNo = bean.getCurrentConsecutiveNo();
				number = number
						+ StringUtils.leftPad(nowNo.toString(), noFigure, "0");

				// 日毎発番管理テーブルを登録
				DailyNumber newBean = new DailyNumber();
				newBean.setKey(bean.getKey());
				newBean.setDatekey(datekey);
				newBean.setFixedChar(bean.getFixedChar());
				newBean.setFixedCharNm(bean.getFixedCharNm());
				newBean.setMinConsecutiveNo(bean.getMinConsecutiveNo());
				newBean.setMaxConsecutiveNo(bean.getMaxConsecutiveNo());
				newBean.setCurrentConsecutiveNo(nowNo);
				Timestamp inputdate = AecDateUtils.getCurrentTimestamp();
				newBean.setInputDate(inputdate);
				newBean.setInputorCd(bean.getInputorCd());
				newBean.setUpdateDate(inputdate);
				newBean.setUpdatorCd(bean.getUpdatorCd());
				dailyNumberDao.insert(newBean);

			} else {
				// 対象日の発番レコード有り

				number = bean.getFixedCharNm();
				number = number + numberDateKey;
				BigDecimal nowNo = bean.getCurrentConsecutiveNo().add(
					BigDecimal.ONE);
				number = number
						+ StringUtils.leftPad(nowNo.toString(), noFigure, "0");

				// 日毎発番管理テーブルを更新
				DailyNumber newBean = new DailyNumber();
				newBean.setKey(bean.getKey());
				newBean.setDatekey(datekey);
				newBean.setFixedChar(bean.getFixedChar());
				newBean.setFixedCharNm(bean.getFixedCharNm());
				newBean.setMinConsecutiveNo(bean.getMinConsecutiveNo());
				newBean.setMaxConsecutiveNo(bean.getMaxConsecutiveNo());
				newBean.setCurrentConsecutiveNo(nowNo);
				newBean.setInputDate(bean.getInputDate());
				newBean.setInputorCd(bean.getInputorCd());
				newBean.setUpdateDate(bean.getUpdateDate());
				newBean.setUpdatorCd(bean.getUpdatorCd());
				dailyNumberDao.update(newBean);
			}
		} catch (SQLRuntimeException e) {
			return null;
		}
		return number;
	}

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(final NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}

	/**
	 * namesNoListDaoを設定します。
	 * @param namesNoListDao namesNoListDao
	 */
	public void setNamesNoListDao(final NamesNoListDao namesNoListDao) {
		this.namesNoListDao = namesNoListDao;
	}

	/**
	 * 日毎発番管理テーブル発番データDaoを設定します。
	 * @param numberingDailyNumberListDao 日毎発番管理テーブル発番データDao
	 */
	public void setNumberingDailyNumberListDao(
			final NumberingDailyNumberListDao numberingDailyNumberListDao) {
		this.numberingDailyNumberListDao = numberingDailyNumberListDao;
	}

	/**
	 * 日毎発番管理テーブルDaoを設定します。
	 * @param dailyNumberDao 日毎発番管理テーブルDao
	 */
	public void setDailyNumberDao(final DailyNumberDao dailyNumberDao) {
		this.dailyNumberDao = dailyNumberDao;
	}
}
