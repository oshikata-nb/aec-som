/*
 * Created on Wed Feb 04 18:16:43 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.login;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LoginBaseクラス.
 * @author t0011036
 */
public class LoginBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoginBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LOGIN";


//	/** IDアノテーション tantoCd. */
//	public static final String tantoCd_ID = "assigned";

	/** COLUMNアノテーション tantoCd. */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm. */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション password. */
	public static final String password_COLUMN = "PASSWORD";

	/** COLUMNアノテーション activeFlg. */
	public static final String activeFlg_COLUMN = "ACTIVE_FLG";

	/** COLUMNアノテーション deleteFlg. */
	public static final String deleteFlg_COLUMN = "DELETE_FLG";

	/** COLUMNアノテーション adminFlg. */
	public static final String adminFlg_COLUMN = "ADMIN_FLG";

	/** COLUMNアノテーション updatePass. */
	public static final String updatePass_COLUMN = "UPDATE_PASS";
	
	/** COLUMNアノテーション mailAddress. */
	public static final String mailAddress_COLUMN = "MAIL_ADDRESS";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	//
	// インスタンスフィールド
	//

	private String tantoCd;

	private String tantoNm;

	private String password;

	private String activeFlg;

	private String deleteFlg;

	private String adminFlg;

	private java.sql.Timestamp updatePass;
	
	private String mailAddress;

	private String updatorCd;

	private java.sql.Timestamp updateDate;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	//
	// インスタンスメソッド
	//

	/**
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNm取得.
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return this.tantoNm;
	}

	/**
	 * tantoNm設定.
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * password取得.
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * password設定.
	 * @param password password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * activeFlg取得.
	 * @return activeFlg
	 */
	public String getActiveFlg() {
		return this.activeFlg;
	}

	/**
	 * activeFlg設定.
	 * @param activeFlg activeFlg
	 */
	public void setActiveFlg(final String activeFlg) {
		this.activeFlg = activeFlg;
	}

	/**
	 * deleteFlg取得.
	 * @return deleteFlg
	 */
	public String getDeleteFlg() {
		return this.deleteFlg;
	}

	/**
	 * deleteFlg設定.
	 * @param deleteFlg deleteFlg
	 */
	public void setDeleteFlg(final String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * adminFlg取得.
	 * @return adminFlg
	 */
	public String getAdminFlg() {
		return this.adminFlg;
	}

	/**
	 * adminFlg設定.
	 * @param adminFlg adminFlg
	 */
	public void setAdminFlg(final String adminFlg) {
		this.adminFlg = adminFlg;
	}

	/**
	 * updatePass取得.
	 * @return updatePass
	 */
	public java.sql.Timestamp getUpdatePass() {
		return this.updatePass;
	}

	/**
	 * updatePass設定.
	 * @param updatePass updatePass
	 */
	public void setUpdatePass(final java.sql.Timestamp updatePass) {
		this.updatePass = updatePass;
	}

	/**
	 * mailAddressを取得します。
	 * @return mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * mailAddressを設定します。
	 * @param mailAddress mailAddress
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
