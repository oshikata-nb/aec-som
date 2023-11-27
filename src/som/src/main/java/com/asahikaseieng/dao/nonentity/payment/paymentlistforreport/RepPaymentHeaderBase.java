/*
 * Created on 2009/07/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepPaymentHeaderクラス.
 * @author kanri-user
 */
public class RepPaymentHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepPaymentHeaderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション paymentDate */
	public static final String paymentDate_COLUMN = "PAYMENT_DATE";

	/** COLUMNアノテーション homeName */
	public static final String homeName_COLUMN = "HOME_NAME";

	//
	// インスタンスフィールド
	//

	private String slipNo;

	private String organizationCd;

	private String organizationName;

	private java.sql.Timestamp paymentDate;

	private String homeName;

	//
	// インスタンスメソッド
	//

	/**
	 * slipNo取得.
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * paymentDate取得.
	 * @return paymentDate
	 */
	public java.sql.Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	/**
	 * paymentDate設定.
	 * @param paymentDate paymentDate
	 */
	public void setPaymentDate(final java.sql.Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * homeName取得.
	 * @return homeName
	 */
	public String getHomeName() {
		return this.homeName;
	}

	/**
	 * homeName設定.
	 * @param homeName homeName
	 */
	public void setHomeName(final String homeName) {
		this.homeName = homeName;
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

