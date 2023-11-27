/*
 * Created on 2008/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.check;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.exception.NoDataException;

/**
 * 役職マスタ一覧 ロジッククラス interface. 
 * @author TanakaSatoru
 */
public interface PasswordCheckLogic {

	/** 
	 * パスワード一致チェック
	 * 
	 * @param password パスワード
	 * @param inputPassword 入力パスワード
	 * @return boolean true:パスワード一致 false:パスワード不一致
	 * @throws NoDataException データ無しの例外
	 */
	boolean checkPassword(final String password, final String inputPassword) throws NoDataException;

	/**
	 * 自社マスタ情報(パスワード有効期限、パスワード桁数下限、上限)を取得し、
	 * セッションに格納する。
	 * 
	 * @param  request HttpServletRequest
	 * @throws NoDataException データ無しの例外
	 */
	void getCompanyInfo(final HttpServletRequest request) throws NoDataException;

	/**
	 * パスワード有効期限を取得
	 * 
	 * @return BigDecimal パスワード有効期限
	 * @throws NoDataException データが存在しない例外
	 */
	BigDecimal getPasswordValidTerm() throws NoDataException;

	/**
	 * パスワード有効／無効チェック
	 * 
	 * @param updatePass パスワード変更日時
	 * @return boolean true:パスワード有効 false:パスワード無効
	 * @throws NoDataException データ無しの例外
	 */
	boolean checkPasswordValid(final Timestamp updatePass) throws NoDataException;

	/**
	 * ログインユーザ有効／失効チェック
	 * 
	 * @param activeFlg 有効フラグ
	 * @return boolean true:有効 false:無効
	 * @throws NoDataException データ無しの例外
	 */
	boolean checkActiveFlg(final String activeFlg) throws NoDataException;

	/**
	 * パスワード有効期限切れ日を算出
	 * 
	 * @param updatePass パスワード変更日時
	 * @return String パスワード有効期限切れ日
	 * @throws NoDataException データ無しの例外
	 */
	String calcPasswordInvalidDate(final Timestamp updatePass) throws NoDataException;

	/**
	 * パスワード残り有効日数を算出
	 * 
	 * @param updatePass パスワード変更日時
	 * @return String パスワード有効期限切れ日
	 * @throws NoDataException データ無しの例外
	 */
	Long calcPasswordValidDays(final Timestamp updatePass) throws NoDataException;

	/**
	 * ログインエラー回数をカウント。
	 * エラー回数が５回になった場合、ログインユーザ定義マスタの有効フラグを無効に更新する。
	 * 
	 * @param request HttpServletRequest
	 * @param userId ログインユーザID
	 */
	void countError(final HttpServletRequest request, final String userId);

}
