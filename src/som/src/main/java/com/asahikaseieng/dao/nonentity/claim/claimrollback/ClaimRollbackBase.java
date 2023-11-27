/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.claimrollback;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 請求更新ロールバック用Entityクラス.
 * @author tosco
 */
public class ClaimRollbackBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimRollbackBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CLAIM_HEADER";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション strCreditDate */
	public static final String strCreditDate_COLUMN = "STR_CREDIT_DATE";

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 請求先コード */
	private String venderCd;

	/** 請求締め日 */
	private Date creditDate;

	/** 請求締め日(文字列) */
	private String strCreditDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 部署コード取得.
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 請求先コード取得.
	 * @return String 請求先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先コード設定.
	 * @param venderCd 請求先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 請求締め日取得.
	 * @return Date 請求締め日
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * 請求締め日設定.
	 * @param creditDate 請求締め日
	 */
	public void setCreditDate(final Date creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 請求締め日(文字列)取得.
	 * @return String 請求締め日(文字列)
	 */
	public String getStrCreditDate() {
		return strCreditDate;
	}

	/**
	 * 請求締め日(文字列)設定.
	 * @param strCreditDate 請求締め日(文字列)
	 */
	public void setStrCreditDate(final String strCreditDate) {
		this.strCreditDate = strCreditDate;
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

