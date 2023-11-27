/*
 * Created on 2008/04/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.check;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.check.PasswordCheckDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 自社マスタ検索 ロジック実装クラス
 * @author tosco
 */
public class PasswordCheckLogicImpl implements PasswordCheckLogic {

	private static final int DAY_MILLI_SECOND = 1000 * 60 * 60 * 24;

	private PasswordCheckDao passwordCheckDao;

	//パスワード有効期限
	private BigDecimal passwordValidTerm;

	/** 
	 * コンストラクタ
	 */
	public PasswordCheckLogicImpl() {
	}

	/**
	 * パスワード一致チェック
	 * 
	 * @param password パスワード
	 * @param inputPassword 入力パスワード
	 * @return boolean true:パスワード一致 false:パスワード不一致
	 * @throws NoDataException データ無しの例外
	 */
	public boolean checkPassword(final String password, final String inputPassword) throws NoDataException {
		boolean res = true;

		if (!password.equals(inputPassword)) {
			res = false;
		}
		return res;
	}

	/**
	 * 自社マスタ情報(パスワード有効期限、パスワード桁数下限、上限)を取得し、
	 * セッションに格納する。
	 * 
	 * @param  request HttpServletRequest
	 * @throws NoDataException データ無しの例外
	 */
	public void getCompanyInfo(final HttpServletRequest request) throws NoDataException {
		//自社マスタ検索
		PasswordCheck bean = passwordCheckDao.getEntity();
		if (bean == null) {
			throw new NoDataException();
		}

		//パスワード有効期限
		setPasswordValidTerm(bean.getPasswordValidTerm());

		//自社マスタ情報をセッションに保持
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute(Constants.COMPANY_INFO_KEY, bean);
		}
	}

	/**
	 * パスワード有効／無効チェック
	 * 
	 * @param updatePass パスワード変更日時
	 * @return boolean true:パスワード有効 false:パスワード無効
	 * @throws NoDataException データ無しの例外
	 */
	public boolean checkPasswordValid(final Timestamp updatePass) throws NoDataException {
		boolean res = true;

		//パスワード有効期限取得
		BigDecimal passValidTerm = getPasswordValidTerm();
		if (passValidTerm.compareTo(new BigDecimal(0)) == 0) {
			//無期限の場合
			return res;
		}

		//本日日付取得
		Calendar today = Calendar.getInstance();

		//パスワード変更日時から本日までの日数計算
		long day = (today.getTimeInMillis() - updatePass.getTime()) / DAY_MILLI_SECOND;

		if (day >= passValidTerm.longValue()) {
			res = false;
		}

		return res;
	}

	/**
	 * ログインユーザ有効／失効チェック
	 * 
	 * @param activeFlg 有効フラグ
	 * @return boolean true:有効 false:無効
	 * @throws NoDataException データ無しの例外
	 */
	public boolean checkActiveFlg(final String activeFlg) throws NoDataException {
		boolean res = true;

		if (Constants.FLG_OFF.equals(activeFlg)) {
			res = false;
		}

		return res;
	}

	/**
	 * パスワード有効期限切れ日を算出
	 * 
	 * @param updatePass パスワード変更日時
	 * @return String パスワード有効期限切れ日
	 * @throws NoDataException データ無しの例外
	 */
	public String calcPasswordInvalidDate(final Timestamp updatePass) throws NoDataException {
		String date = "";

		//パスワード有効期間取得
		BigDecimal passValidTerm = getPasswordValidTerm();
		if (passValidTerm.compareTo(new BigDecimal(0)) == 0) {
			//無期限の場合
			return date;
		}

		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(updatePass.getTime());
		//パスワード変更日時＋パスワード有効期間＝パスワード有効期限切れ日
		today.add(Calendar.DATE, passValidTerm.intValue());

		int y = today.get(Calendar.YEAR);
		int m = today.get(Calendar.MONTH) + 1;
		int d = today.get(Calendar.DATE);

		date = y + "/" + m + "/" + d;

		return date;
	}

	/**
	 * パスワード残り有効日数を算出
	 * 
	 * @param updatePass パスワード変更日時
	 * @return String パスワード有効期限切れ日
	 * @throws NoDataException データ無しの例外
	 */
	public Long calcPasswordValidDays(final Timestamp updatePass) throws NoDataException {
		long days = 0;

		if (!checkPasswordValid(updatePass)) {
			//パスワード無効の場合
			return days;
		}

		//パスワード有効期限切れ日取得
		String date = calcPasswordInvalidDate(updatePass);
		if (date.equals("")) {
			//無期限の場合
			return days;
		}

		int first = date.indexOf("/");
		int last = date.lastIndexOf("/");
		int year = Integer.parseInt(date.substring(0, first));
		int month = Integer.parseInt(date.substring(first + 1, last));
		int day = Integer.parseInt(date.substring(last + 1));

		//パスワード有効期限切れ日設定
		Calendar inValidDate = Calendar.getInstance();
		inValidDate.set(year, month, day);
		inValidDate.add(Calendar.MONTH, -1);

		//本日
		Calendar today = Calendar.getInstance();

		//パスワード残り有効日数＝パスワード有効期限切れ日－本日
		days = (inValidDate.getTimeInMillis() - today.getTimeInMillis()) / DAY_MILLI_SECOND;

		return days;
	}

	/**
	 * ログインエラー回数をカウントしてセッションに保持。
	 * エラー回数が５回になった場合、ログインユーザ定義マスタの有効フラグを無効に更新する。
	 * 
	 * @param request HttpServletRequest
	 * @param userId ログインユーザID
	 */
	public void countError(final HttpServletRequest request, final String userId) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}

		@SuppressWarnings("unchecked")
		Map<String, Integer> map =
			(Map<String, Integer>) (session.getAttribute(Constants.LOGIN_ERROR_COUNT_KEY));

		if (map == null) {
			//初回ログイン失敗
			map = new HashMap<String, Integer>();
			map.put(userId, new Integer(1));
		} else {
			//２回目以降
			if (map.get(userId) == null) {
				//該当ユーザでは初回
				map.put(userId, new Integer(1));
			} else {
				//該当ユーザで２回目以降
				Integer count = map.get(userId);
				count = new Integer(count.intValue() + 1);

				if (count == Constants.LOGIN_ERROR_COUNT_MAX) {
					//５回連続でログイン失敗した場合、失効処理
					passwordCheckDao.updateLogin(userId);
					//セッションからログインエラー回数情報をクリア
					request.getSession(false).removeAttribute(Constants.LOGIN_ERROR_COUNT_KEY);
					return;
				} else {
					map.put(userId,  count);
				}
			}
		}

		//セッションにエラー回数を設定
		session.setAttribute(Constants.LOGIN_ERROR_COUNT_KEY, map);
	}

	/* -------------------- setter -------------------- */

	/**
	 * passwordCheckDaoを設定します。
	 * @param passwordCheckDao passwordCheckDao
	 */
	public void setPasswordCheckDao(final PasswordCheckDao passwordCheckDao) {
		this.passwordCheckDao = passwordCheckDao;

	}

	/**
	 * パスワード有効期限を取得
	 * @return BigDecimal パスワード有効期限
	 */
	public BigDecimal getPasswordValidTerm() {
		return passwordValidTerm;
	}

	/**
	 * パスワード有効期限を設定
	 * @param passwordValidTerm パスワード有効期限
	 */
	public void setPasswordValidTerm(final BigDecimal passwordValidTerm) {
		this.passwordValidTerm =  passwordValidTerm;
	}

}
