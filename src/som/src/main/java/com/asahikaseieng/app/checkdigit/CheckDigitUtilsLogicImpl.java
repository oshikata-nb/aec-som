/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetailDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 数値項目用表示・チェックユーティリティ
 * @author tosco
 */
public class CheckDigitUtilsLogicImpl implements CheckDigitUtilsLogic,
		Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Log */
	private static Log log = LogFactory.getLog(CheckDigitUtilsLogicImpl.class);

	/** 端数区分－0:小数部なし 数値フォーマット文字列 */
	private static final String DECIMAL_POINT_FORMAT_BASE = "#,##0.";

	/** チェック項目初期値－整数部桁数 */
	private static final BigDecimal INIT_INTEGER_LENGTH = new BigDecimal(12);

	/** チェック項目初期値－小数部桁数 */
	private static final BigDecimal INIT_SMALLNUM_LENGTH = new BigDecimal(9);

	/** チェック項目初期値－ 全体行桁 */
	private static final BigDecimal INIT_MAX_LENGTH = new BigDecimal(22);

	/** 数値桁数チェックマスタメンテDAO */
	private NumberChkDisitDetailDao numberChkDisitDetailDao;

	/** チェック設定格納Map */
	private Map<String, NumberChkDisitDetail> map = new HashMap<String, NumberChkDisitDetail>();

	/** チェック設定格納List */
	private List<String> array = new ArrayList<String>();

	/** キャッシュサイズ */
	private static final int CACHE_SIZE;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);
		// キャッシュ最大件数を取得
		CACHE_SIZE = Integer.parseInt(rb.getString("threshold.common"));
	}

	/**
	 * コンストラクタ
	 */
	public CheckDigitUtilsLogicImpl() {
	}

	/**
	 * 数値桁数チェックマスタの端数区分、小数点以下桁数に基づき、数値をフォーマットする
	 * @param unitDivision 区分
	 * @param value フォーマット対象BigDecimal
	 * @return 数値をフォーマットした文字列
	 */
	public String format(final String unitDivision, final BigDecimal value) {
		return format(unitDivision, null, null, value);
	}

	/**
	 * 数値桁数チェックマスタの端数区分、小数点以下桁数に基づき、数値をフォーマットする
	 * @param value フォーマット対象BigDecimal
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return 数値をフォーマットした文字列
	 */
	public String format(final String unitDivision,
			final String venderDivision, final String venderCd,
			final BigDecimal value) {
		String res = null;
		if (value != null) {
			// 区分は必須
			String unitDiv = unitDivision;
			if (StringUtils.isEmpty(unitDivision)) {
				// 必須なんだけど、無い場合は初期値を適用できるようにする。
				unitDiv = " ";
				// throw new IllegalArgumentException("unitDivision == null");
			}

			// 数値桁数チェックマスタメンテから設定を取得(VENDER_DIVISION、VENDER_CD="
			// "(半角スペース)の代表データも一緒に取得)
			NumberChkDisitDetail checkDetail = getCheckDigit(unitDiv,
				venderDivision, venderCd);

			// 端数区分、小数点以下桁数を取得
			BigDecimal round = checkDetail.getRoundDivision(); // 端数区分
			BigDecimal unit = checkDetail.getSmallnumLength(); // 小数点以下桁数
			if (unit == null) {
				// 小数点桁数が設定されていない場合は初期値を適用
				unit = INIT_SMALLNUM_LENGTH;
			}
			// 小数点以下桁数により、フォーマット文字列生成
			int precision = unit.intValue(); // 小数点桁数
			// フォーマットパターン
			String pattern = getPattern(precision);
			BigDecimal roudValue = value;
			// 丸めモード
			if (round != null) {
				// 端数区分が設定されていて、0:なし以外の場合のみ丸める
				RoundingMode roundMode = getRoundingMode(round.intValue());
				if (roundMode != null) {
					// 0:なし以外の場合のみ丸める
					// 指定の小数点位置と丸めモードで丸める
					roudValue = value.setScale(precision, roundMode);
				}
			}
			// 文字列にフォーマット
			DecimalFormat df = new DecimalFormat(pattern);
			res = df.format(roudValue);
		}
		return res;
	}

	/**
	 * 数値桁数チェックマスタの端数区分、小数点以下桁数に基づき、数値をフォーマットする
	 * @param value フォーマット対象BigDecimal
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return 数値をフォーマットした文字列
	 */
	public String format( NumberChkDisitDetail checkDetail , final BigDecimal value) {
		String res = null;
		if (value != null && checkDetail != null) {
			// 区分は必須
			// 端数区分、小数点以下桁数を取得
			BigDecimal round = checkDetail.getRoundDivision(); // 端数区分
			BigDecimal unit = checkDetail.getSmallnumLength(); // 小数点以下桁数
			if (unit == null) {
				// 小数点桁数が設定されていない場合は初期値を適用
				unit = INIT_SMALLNUM_LENGTH;
			}
			// 小数点以下桁数により、フォーマット文字列生成
			int precision = unit.intValue(); // 小数点桁数
			// フォーマットパターン
			String pattern = getPattern(precision);
			BigDecimal roudValue = value;
			// 丸めモード
			if (round != null) {
				// 端数区分が設定されていて、0:なし以外の場合のみ丸める
				RoundingMode roundMode = getRoundingMode(round.intValue());
				if (roundMode != null) {
					// 0:なし以外の場合のみ丸める
					// 指定の小数点位置と丸めモードで丸める
					roudValue = value.setScale(precision, roundMode);
				}
			}
			// 文字列にフォーマット
			DecimalFormat df = new DecimalFormat(pattern);
			res = df.format(roudValue);
		}
		return res;
	}
	
	/**
	 * チェック設定を取得
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return NumberChkDisitDetail
	 */
	public NumberChkDisitDetail getCheckDigit(final String unitDivision,
			final String venderDivision, final String venderCd) {
		// 取引先区分がnullの場合は半角スペースにする。（代表設定検索用)
		String venDivision = getAllCode(venderDivision);
		// 取引先コードがnullの場合は半角スペースにする。（代表設定検索用)
		String venCd = getAllCode(venderCd);

		// String key = generateKey(unitDivision, venDivision, venCd);
		// キャッシュから設定を取得
		// NumberChkDisitDetail checkDetail = map.get(key);
		// if (checkDetail == null) {
		// // キャッシュから取得できなかったので、数値桁数チェックマスタから取得
		// // 数値桁数チェックマスタメンテから設定を取得
		// checkDetail = getCheckDigitDetailDAO(unitDivision, venDivision,
		// venCd);
		// }
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();

		// 数値桁数チェックマスタメンテから設定を取得
		checkDetail = getCheckDigitDetailDAO(unitDivision, venDivision, venCd);

		return checkDetail;
	}

	/**
	 * チェック設定格納キーを生成する ##ここにメソッドの説明を書いてください##
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return チェック設定格納キー
	 */
	private String generateKey(final String unitDivision,
			final String venderDivision, final String venderCd) {
		StringBuilder buf = new StringBuilder();
		buf.append(StringUtils.rightPad(unitDivision, 10));
		buf.append(StringUtils.rightPad(venderDivision, 2));
		buf.append(StringUtils.rightPad(venderCd, 15));
		return buf.toString();
	}

	/**
	 * 値がnullの場合は代表取得用コード（半角スペース)を返す
	 * @param s 対象文字列
	 * @return 対象文字列ornullの場合は代表取得用コード
	 */
	private String getAllCode(final String s) {
		String res = s;
		if (StringUtils.isEmpty(s)) {
			res = NumberChkDisitDetailDao.ALL;
		}
		return res;
	}

	/**
	 * 数値桁数チェックマスタメンテから設定を取得
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return NumberChkDisitDetail
	 */
	private NumberChkDisitDetail getCheckDigitDetailDAO(
			final String unitDivision, final String venderDivision,
			final String venderCd) {
		NumberChkDisitDetail checkDetail = null;
		try {
			// 数値桁数チェックマスタメンテから設定を取得(VENDER_DIVISION、VENDER_CD="
			// "(半角スペース)の代表データも一緒に取得)
			List<NumberChkDisitDetail> list = findByPrimaryKey(unitDivision,
				venderDivision, venderCd);
			if (list.size() > 1) {
				// 2件以上ある場合は、代表設定が1行目に入っているので、2件目を取得
				checkDetail = list.get(1);
			} else {
				// 1件しかないので、代表設定を適用
				checkDetail = list.get(0);
			}
			// せっかくDBから取得したので、キャッシュする
			cache(list);
		} catch (NoDataException nde) {
			// 検索データが存在しない場合,基本チェック項目のみ適用
			checkDetail = new NumberChkDisitDetail();
			checkDetail.setIntegerLength(INIT_INTEGER_LENGTH); // 整数部桁数
			checkDetail.setSmallnumLength(INIT_SMALLNUM_LENGTH); // 小数部桁数
			checkDetail.setMaxLength(INIT_MAX_LENGTH); // 最大長
		}
		return checkDetail;
	}

	/**
	 * チェック設定をキャッシュする
	 * @param list DBから取得したチェック設定リスト
	 */
	private void cache(final List<NumberChkDisitDetail> list) {
		int cacheNum = array.size();
		// キャッシュ最大件数を超えている場合は、最大件数-1まで削除
		for (int i = cacheNum; i >= CACHE_SIZE; i--) {
			// 最古のキーを取得
			String key = array.get(0);
			// 配列から削除
			array.remove(0);
			// マップから削除
			map.remove(key);
		}
		for (NumberChkDisitDetail bean : list) {
			String key = generateKey(bean.getUnitDivision(), bean
					.getVenderDivision(), bean.getVenderCd());
			// 代表設定は既に登録されているかもしれないので、いったん取得して確認
			NumberChkDisitDetail buf = map.get(key);
			if (buf == null) {
				map.put(key, bean);
				array.add(key);
			}
		}
	}

	/**
	 * 端数区分に基づいた端数区分により丸めモードを取得する
	 * @param roundUp 端数区分
	 * @return 丸めモード(0,4~の数値が来た場合はnull)
	 */
	public RoundingMode getRoundingMode(final int roundUp) {
		RoundingMode res = null;
		switch (roundUp) {
		case CheckDigitUtilsLogic.ROUND_DOWN_DOWN:
			// 切り捨て
			res = CheckDigitUtilsLogic.ROUND_DOWN;
			break;
		case CheckDigitUtilsLogic.ROUND_DOWN_HALF_UP:
			// 四捨五入
			res = CheckDigitUtilsLogic.ROUND_HALF_UP;
			break;
		case CheckDigitUtilsLogic.ROUND_DOWN_UP:
			// 切り上げ
			res = CheckDigitUtilsLogic.ROUND_UP;
			break;
		default:
			// 上記以外は丸めない->なし
			res = null;
			break;
		}
		return res;
	}

	/**
	 * 小数点フォーマット文字列を作成
	 * @param decimalPoint 小数点位置(0～)
	 * @return 小数点フォーマット文字列
	 */
	private String getPattern(final int decimalPoint) {
		String res = "#,##0";
		if (decimalPoint > 0) {
			StringBuilder buf = new StringBuilder(DECIMAL_POINT_FORMAT_BASE);
			for (int i = 0; i < decimalPoint; i++) {
				buf.append("0");
			}
			res = buf.toString();
		}
		return res;
	}

	// 桁数チェック---------------------------------------------------------------------
	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行う
	 * @param value チェック対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return [true:検証OK][false:検証NG]
	 */
	public boolean isCheckDigit(final String unitDivision,
			final String venderDivision, final String venderCd,
			final String value) {
		CheckDigitResult result = checkDigit(unitDivision, venderDivision,
			venderCd, value);
		boolean res = false;
		if (result.getCode() == CheckDigitUtilsLogic.SUCCESS) {
			res = true;
		}
		return res;
	}

	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行う
	 * @param value チェック対象String
	 * @param unitDivision 区分
	 * @return エラーコード
	 *         [0:チェックOK][1:数値文字列エラー][2:最大文字列長エラー][3:整数部桁数エラー][4:小数部桁数エラー][5:レンジエラー]
	 */
	public CheckDigitResult checkDigit(final String unitDivision,
			final String value) {
		return checkDigit(unitDivision, null, null, value);
	}

	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行う
	 * @param value チェック対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return エラーコード
	 *         [0:チェックOK][1:数値文字列エラー][2:最大文字列長エラー][3:整数部桁数エラー][4:小数部桁数エラー][5:レンジエラー]
	 */
	public CheckDigitResult checkDigit(final String unitDivision,
			final String venderDivision, final String venderCd,
			final String value) {
		// チェック結果
		CheckDigitResult result = new CheckDigitResult(
				CheckDigitUtilsLogic.SUCCESS);
		if (StringUtils.isNotEmpty(value)) {
			// 数値が入力されている場合のみチェックを行う。
			NumberChkDisitDetail checkDetail = null;
			try {
				// カンマ編集されているかもしれないので、カンマを除去
				String buf = value.replace(",", "");

				// BigDecimalに変換できる、数値文字列か試す。
				BigDecimal dec = checkNumberString(buf);

				// 数値桁数チェックマスタメンテから設定を取得(VENDER_DIVISION、VENDER_CD="
				// "(半角スペース)の代表データも一緒に取得)
				checkDetail = getCheckDigit(unitDivision, venderDivision,
					venderCd);

				// 全体桁数チェック
				checkMaxLength(buf, checkDetail.getMaxLength());
				// 整数部桁数チェック
				checkIntegerPart(dec, checkDetail.getIntegerLength());
				// 小数点以下桁数を取得
				checkDecimalPart(dec, checkDetail.getSmallnumLength());
				// レンジチェック
				checkRange(dec, checkDetail.getLowerLimit(), checkDetail
						.getUpperLimit());

			} catch (CheckDigitException e) {
				// チェックエラー時
				result.setCode(e.getCode());
				result.setDetail(checkDetail);

				log.debug(e.toString());
			}
		}
		// 数値が入力されていない場合は、チェックしない->0:successで返す。
		return result;
	}

	/**
	 * 数値文字列チェック
	 * @param value チェック対象文字列
	 * @throws CheckDigitException チェックエラー時発生
	 */
	private BigDecimal checkNumberString(final String value)
			throws CheckDigitException {
		BigDecimal dec = null;
		try {
			// BigDecimalに変換できる、数値文字列か試す。（NumberFormatExceptionが発生する)
			dec = new BigDecimal(value);
		} catch (NumberFormatException e) {
			throw new CheckDigitException("数値フォーマットチェックエラー",
					CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT);
		}
		return dec;
	}

	/**
	 * 最大文字数チェック
	 * @param value チェック対象文字列
	 * @param maxlength 全体桁数設定値
	 * @throws CheckDigitException チェックエラー時発生
	 */
	private void checkMaxLength(final String value, final BigDecimal maxlength)
			throws CheckDigitException {
		// 全体桁数が設定されている場合のみ、最大文字数チェックを行う。
		if (maxlength != null) {
			int len = value.length();
			if (len > maxlength.intValue()) {
				throw new CheckDigitException("最大文字数チェックエラー",
						CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH);
			}
		}
	}

	/**
	 * 整数部桁数チェック
	 * @param value 検証対象BigDecimal
	 * @param length 整数部桁数設定BigDecimal
	 * @throws CheckDigitException チェックエラー時発生
	 */
	private void checkIntegerPart(final BigDecimal value,
			final BigDecimal length) throws CheckDigitException {
		if (length != null) {
			// 整数部桁数が入力されている場合のみチェックを行う。
			// 小数部を切り捨て
			BigDecimal integerPart = value.setScale(0, ROUND_DOWN);
			// 整数部桁数取得
			int precision = integerPart.precision();
			if (precision > length.intValue()) {
				// 整数部の桁数オーバ
				throw new CheckDigitException("整数部桁数チェックエラー",
						CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH);
			}
		}
	}

	/**
	 * 小数部桁数チェック
	 * @param value 検証対象BigDecimal
	 * @param length 小数部桁数設定BigDecimal
	 * @throws CheckDigitException チェックエラー時発生
	 */
	private void checkDecimalPart(final BigDecimal value,
			final BigDecimal length) throws CheckDigitException {
		// 小数部桁数が入力されていない場合は小数点桁数初期値を適用する
		int decimalPoint = INIT_SMALLNUM_LENGTH.intValue();
		if (length != null) {
			decimalPoint = length.intValue();
		}
		// 単純に.以下の文字数をカウントするのでもいいけど
		int scale = value.scale();
		if (scale > decimalPoint) {
			// 小数点以下桁数エラー
			throw new CheckDigitException("小数部桁数チェックエラー",
					CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH);
		}
	}

	/**
	 * 小数部桁数チェック
	 * @param value 検証対象BigDecimal
	 * @param lower レンジ下限設定BigDecimal
	 * @param upper レンジ上限設定BigDecimal
	 * @throws CheckDigitException チェックエラー時発生
	 */
	private void checkRange(final BigDecimal value, final BigDecimal lower,
			final BigDecimal upper) throws CheckDigitException {
		// 下限、上限ともに入力されていないとチェックを行わない
		if ((lower != null) && (upper != null)) {
			// 下限チェック
			if ((value.compareTo(lower) < 0) || (value.compareTo(upper) > 0)) {
				// 上下限チェックエラー
				throw new CheckDigitException("レンジチェックエラー",
						CheckDigitUtilsLogic.ERROR_NUMBER_RANGE);
			}
		}
	}

	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行い、エラーメッセージを返す
	 * @param value チェック対象String
	 * @param itemName 項目名称
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return [検証OK時はnull][検証NGはエラーメッセージ]
	 */
	public ActionMessage checkDigitMessage(final String unitDivision,
			final String venderDivision, final String venderCd,
			final String value, final String itemName) {
		CheckDigitResult result = checkDigit(unitDivision, venderDivision,
			venderCd, value);
		NumberChkDisitDetail detail = result.getDetail();
		ActionMessage message = null;
		switch (result.getCode()) {
		case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
			// 数値文字列エラー
			message = new ActionMessage("errors.number", itemName);
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
			// 最大文字列長エラー
			message = new ActionMessage("errors.maxlength", itemName, detail
					.getMaxLength().toString());
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
			// 整数部桁数エラー
			message = new ActionMessage("errors.digit.integer", itemName,
					detail.getIntegerLength().toString());
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
			// 小数部桁数エラー
			message = new ActionMessage("errors.digit.decimal", itemName,
					detail.getSmallnumLength().toString());
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
			// 範囲エラー
			message = new ActionMessage("errors.rang", itemName, detail
					.getLowerLimit().toString(), detail.getUpperLimit()
					.toString());
			break;
		default:
			message = null;
			break;
		}
		return message;
	}

	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行い、行番号付きのエラーメッセージを返す
	 * @param value チェック対象String
	 * @param itemName 項目名称
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @param row 行番号
	 * @return [検証OK時はnull][検証NGはエラーメッセージ]
	 */
	public ActionMessage checkDigitMessage(final String unitDivision,
			final String venderDivision, final String venderCd,
			final String value, final String itemName, final int row) {
		CheckDigitResult result = checkDigit(unitDivision, venderDivision,
			venderCd, value);
		NumberChkDisitDetail detail = result.getDetail();
		ActionMessage message = null;
		switch (result.getCode()) {
		case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
			// 数値文字列エラー
			message = new ActionMessage("errors.number.row", itemName, row);
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
			// 最大文字列長エラー
			message = new ActionMessage("errors.maxlength.row", itemName,
					detail.getMaxLength().toString(), row);
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
			// 整数部桁数エラー
			message = new ActionMessage("errors.digit.integer.row", itemName,
					detail.getIntegerLength().toString(), row);
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
			// 小数部桁数エラー
			message = new ActionMessage("errors.digit.decimal.row", itemName,
					detail.getSmallnumLength().toString(), row);
			break;
		case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
			// 範囲エラー
			message = new ActionMessage("errors.rang.row", itemName, detail
					.getLowerLimit().toString(), detail.getUpperLimit()
					.toString(), row);
			break;
		default:
			message = null;
			break;
		}
		return message;
	}

	// BigDecimalの小数点丸め------------------------------------------------------------------------------
	/**
	 * 数値桁数チェックマスタの端数区分設定に基づき、数値を丸める
	 * @param value 対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return 丸め処理を行ったBigDecimal
	 */
	public BigDecimal round(final String unitDivision,
			final String venderDivision, final String venderCd,
			final BigDecimal value) {
		return round(unitDivision, venderDivision, venderCd, value, null);
	}

	/**
	 * 数値桁数チェックマスタの端数区分設定に基づき、数値を丸める
	 * @param value 対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @param roundDivision 強制端数区分(0:なし、1:切り捨て、2:四捨五入、3:切り上げ)
	 * @return 丸め処理を行ったBigDecimal
	 */
	public BigDecimal round(final String unitDivision,
			final String venderDivision, final String venderCd,
			final BigDecimal value, final BigDecimal roundDivision) {
		BigDecimal res = value;
		if (value != null) {
			// 区分は必須
			if (StringUtils.isEmpty(unitDivision)) {
				throw new IllegalArgumentException("unitDivision == null");
			}
			// 数値桁数チェックマスタメンテから設定を取得(VENDER_DIVISION、VENDER_CD="
			// "(半角スペース)の代表データも一緒に取得)
			NumberChkDisitDetail checkDetail = getCheckDigit(unitDivision,
				venderDivision, venderCd);

			// 端数区分、小数点以下桁数を取得
			BigDecimal round = null; // 端数区分
			if (roundDivision == null) {
				round = checkDetail.getRoundDivision();
			} else {
				// 丸め強制時
				round = roundDivision;
			}
			BigDecimal unit = checkDetail.getSmallnumLength(); // 小数点以下桁数
			if ((unit != null) && (round != null)) {
				// 端数区分が設定されていて、0:なし以外の場合のみ丸める
				int precision = unit.intValue(); // 小数点桁数
				// 丸めモード
				RoundingMode roundMode = getRoundingMode(round.intValue());
				if (roundMode != null) {
					// 0:なし以外の場合のみ丸める
					// 指定の小数点位置と丸めモードで丸める
					res = value.setScale(precision, roundMode);
				} else {
					res = value.setScale(precision, RoundingMode.DOWN);
				}
			}
		}
		return res;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 数値桁数チェックマスタから主キーで検索 (VENDER_DIVISION、VENDER_CD=" "(半角スペース)の代表データも一緒に取得)
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS 自社の場合は半角1桁
	 * @param venderCd 取引先 自社の場合は半角1桁
	 * @return List<NumberChkDisitDetail>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<NumberChkDisitDetail> findByPrimaryKey(
			final String unitDivision, final String venderDivision,
			final String venderCd) throws NoDataException {
		// 数値桁数チェックマスタを検索
		List<NumberChkDisitDetail> list = numberChkDisitDetailDao
				.findByPrimaryKey(unitDivision, venderDivision, venderCd);
		if (list.isEmpty()) {
			// 検索結果が存在しない場合
			throw new NoDataException();
		}
		return list;
	}

	// setter------------------------------------------------------------------
	/**
	 * 数値桁数チェックマスタメンテDAOを設定します。
	 * @param numberChkDisitDetailDao 数値桁数チェックマスタメンテDAO
	 */
	public void setNumberChkDisitDetailDao(
			final NumberChkDisitDetailDao numberChkDisitDetailDao) {
		this.numberChkDisitDetailDao = numberChkDisitDetailDao;
	}

}
