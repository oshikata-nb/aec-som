/*
 * Created on 2008/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.check;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * PasswordCheckBaseクラス.
 * @author tosco
 */
public class PasswordCheckBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PasswordCheckBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "COMPANY";

	/** COLUMNアノテーション passwordValidTerm */
	public static final String passwordValidTerm_COLUMN = "PASSWORD_VALID_TERM";

	/** COLUMNアノテーション passwordKetaLowerLimit */
	public static final String passwordKetaLowerLimit_COLUMN = "PASSWORD_KETA_LOWER_LIMIT";

	/** COLUMNアノテーション passwordKetaUpperLimit */
	public static final String passwordKetaUpperLimit_COLUMN = "PASSWORD_KETA_UPPER_LIMIT";

	/** COLUMNアノテーション companyCd */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	//
	// インスタンスフィールド

	//

	private BigDecimal passwordValidTerm;

	private BigDecimal passwordKetaLowerLimit;

	private BigDecimal passwordKetaUpperLimit;

	private String companyCd;

	//
	// インスタンスメソッド
	//

	/**
	 * パスワード有効期限取得.
	 * @return BigDecimal パスワード有効期限
	 */
	public BigDecimal getPasswordValidTerm() {
		return passwordValidTerm;
	}

	/**
	 * パスワード有効期限設定.
	 * @param passwordValidTerm パスワード有効期限
	 */
	public void setPasswordValidTerm(final BigDecimal passwordValidTerm) {
		this.passwordValidTerm = passwordValidTerm;
	}

	/**
	 * パスワード桁数下限取得.
	 * @return BigDecimal パスワード桁数下限
	 */
	public BigDecimal getPasswordKetaLowerLimit() {
		return passwordKetaLowerLimit;
	}

	/**
	 * パスワード桁数下限設定.
	 * @param passwordKetaLowerLimit パスワード桁数下限
	 */
	public void setPasswordKetaLowerLimit(
			final BigDecimal passwordKetaLowerLimit) {
		this.passwordKetaLowerLimit = passwordKetaLowerLimit;
	}

	/**
	 * パスワード桁数上限取得.
	 * @return BigDecimal パスワード桁数上限
	 */
	public BigDecimal getPasswordKetaUpperLimit() {
		return passwordKetaUpperLimit;
	}

	/**
	 * パスワード桁数上限設定.
	 * @param passwordKetaUpperLimit パスワード桁数上限
	 */
	public void setPasswordKetaUpperLimit(
			final BigDecimal passwordKetaUpperLimit) {
		this.passwordKetaUpperLimit = passwordKetaUpperLimit;
	}

	/**
	 * 会社コード取得.
	 * @return BigDecimal 会社コード
	 * 
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * 会社コード設定.
	 * @param companyCd 会社コード
	 * 
	 */
	public void setCompanyCd(final String companyCd) {
		this.companyCd = companyCd;
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
