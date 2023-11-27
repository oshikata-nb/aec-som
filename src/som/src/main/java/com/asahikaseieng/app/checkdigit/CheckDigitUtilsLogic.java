/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 数値項目用表示・チェックユーティリティ
 * @author tosco
 */
public interface CheckDigitUtilsLogic {

	/** 端数区分-切り捨て */
	RoundingMode  ROUND_DOWN = RoundingMode.DOWN;
	/** 端数区分-四捨五入 */
	RoundingMode  ROUND_HALF_UP = RoundingMode.HALF_UP;
	/** 端数区分-切り上げ */
	RoundingMode  ROUND_UP = RoundingMode.UP;
	/** 端数区分値-切り捨て */
	int ROUND_DOWN_DOWN = 1;
	/** 端数区分値-四捨五入 */
	int ROUND_DOWN_HALF_UP = 2;
	/** 端数区分値-切り上げ */
	int ROUND_DOWN_UP = 3;
	/** 端数区分値-なし*/
	int ROUND_DOWN_NOT = 0;

	/** 入力チェックエラー：数値フォーマットエラー */
	int SUCCESS = 0;
	/** 入力チェックエラー：数値フォーマットエラー */
	int ERROR_NUMBER_FORMAT = 1;
	/** 入力チェックエラー：最大文字数エラー */
	int ERROR_NUMBER_MAX_LENGTH = 2;
	/** 入力チェックエラー：整数部桁数エラー */
	int ERROR_NUMBER_INTEGER_LENGTH = 3;
	/** 入力チェックエラー：小数部エラー */
	int ERROR_NUMBER_SMALLNUM_LENGTH = 4;
	/** 入力チェックエラー：レンジエラー */
	int ERROR_NUMBER_RANGE = 5;

	/**
	 * 数値桁数チェックマスタから主キーで検索
	 * (VENDER_DIVISION、VENDER_CD=" "(半角スペース)の代表データも一緒に取得)
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return List<NumberChkDisitDetail>
	 * @throws NoDataException データが存在しない場合
	 */
	List<NumberChkDisitDetail> findByPrimaryKey(
		String unitDivision, String venderDivision, String venderCd) throws NoDataException;
	/**
	 * 自社マスタの端数区分、小数点以下桁数に基づき、数値をフォーマットする
	 * @param unitDivision 区分
	 * @param value フォーマット対象BigDecimal
	 * @return 数値をフォーマットした文字列
	 */
	String format(String unitDivision, BigDecimal value);
	/**
	 * 自社マスタの端数区分、小数点以下桁数に基づき、数値をフォーマットする
	 * @param value フォーマット対象BigDecimal
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return 数値をフォーマットした文字列
	 */
	String format(String unitDivision, String venderDivision,
			String venderCd, BigDecimal value);

	/**
	 * 自社マスタの端数区分、小数点以下桁数に基づき、数値をフォーマットする
	 * @param value フォーマット対象BigDecimal
	 * @param checkDetail チェック詳細
	 * @return 数値をフォーマットした文字列
	 */
	public String format( NumberChkDisitDetail checkDetail , final BigDecimal value);
	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行う
	 * @param value チェック対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return [true:検証OK][false:検証NG]
	 */
	boolean isCheckDigit(String unitDivision, String venderDivision, String venderCd, String value);
	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行う
	 * @param value チェック対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return エラーコード[0:チェックOK][1:数値文字列エラー]
	 * [2:最大文字列長エラー][3:整数部桁数エラー][4:小数部桁数エラー][5:レンジエラー]
	 */
	CheckDigitResult checkDigit(String unitDivision, String venderDivision, String venderCd, String value);
	/**
	 * 数値桁数チェックマスタの端数区分設定に基づき、数値を丸める
	 * @param value 対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return 丸め処理を行ったBigDecimal
	 */
	BigDecimal round(String unitDivision, String venderDivision, String venderCd,  BigDecimal value);
	/**
	 * 数値桁数チェックマスタの端数区分設定に基づき、数値を丸める
	 * @param value 対象String
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @param roundDivision 強制端数区分(0:なし、1:切り捨て、2:四捨五入、3:切り上げ)
	 * @return 丸め処理を行ったBigDecimal
	 */
	BigDecimal round(String unitDivision, String venderDivision,
			String venderCd, BigDecimal value, BigDecimal roundDivision);
	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行い、エラーメッセージを返す
	 * @param value チェック対象String
	 * @param itemName 項目名称
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return [検証OK時はnull][検証NGはエラーメッセージ]
	 */
	ActionMessage checkDigitMessage(String unitDivision, String venderDivision,
			String venderCd, String value, String itemName);
	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行い、行番号付きのエラーメッセージを返す
	 * @param value チェック対象String
	 * @param itemName 項目名称
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @param row 行番号
	 * @return [検証OK時はnull][検証NGはエラーメッセージ]
	 */
	ActionMessage checkDigitMessage(String unitDivision, String venderDivision,
			String venderCd, String value, String itemName, int row);


	/**
	 * 数値桁数チェックマスタの設定に基づき、数値文字列の入力チェックを行う
	 * @param value チェック対象String
	 * @param unitDivision 区分
	 * @return エラーコード[0:チェックOK][1:数値文字列エラー][2:最大文字列長エラー]
	 * [3:整数部桁数エラー][4:小数部桁数エラー][5:レンジエラー]
	 */
	CheckDigitResult checkDigit(String unitDivision, String value);
	/**
	 * チェック設定を取得
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return NumberChkDisitDetail
	 */
	NumberChkDisitDetail getCheckDigit(String unitDivision, String venderDivision, String venderCd);
	/**
	 * 端数区分に基づいた端数区分により丸めモードを取得する
	 * @param roundUp 端数区分
	 * @return 丸めモード(0,4~の数値が来た場合はnull)
	 */
	RoundingMode getRoundingMode(int roundUp);

}
