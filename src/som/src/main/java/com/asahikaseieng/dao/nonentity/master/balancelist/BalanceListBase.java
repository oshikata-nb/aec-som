/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BalanceListクラス.
 * @author t0011036
 */
public class BalanceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BalanceListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション balanceType */
	public static final String balanceType_COLUMN = "BALANCE_TYPE";

	/** COLUMNアノテーション balanceTypeName */
	public static final String balanceTypeName_COLUMN = "BALANCE_TYPE_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション dispVenderName1 */
	public static final String dispVenderName1_COLUMN = "DISP_VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション dispVenderName2 */
	public static final String dispVenderName2_COLUMN = "DISP_VENDER_NAME2";

	/** COLUMNアノテーション venderName3 */
	public static final String venderName3_COLUMN = "VENDER_NAME3";

	/** COLUMNアノテーション dispVenderName3 */
	public static final String dispVenderName3_COLUMN = "DISP_VENDER_NAME3";

	/** COLUMNアノテーション venderName4 */
	public static final String venderName4_COLUMN = "VENDER_NAME4";

	/** COLUMNアノテーション dispVenderName4 */
	public static final String dispVenderName4_COLUMN = "DISP_VENDER_NAME4";

	/** COLUMNアノテーション venderName5 */
	public static final String venderName5_COLUMN = "VENDER_NAME5";

	/** COLUMNアノテーション dispVenderName5 */
	public static final String dispVenderName5_COLUMN = "DISP_VENDER_NAME5";

	//
	// インスタンスフィールド
	//

	private String balanceCd;

	private java.math.BigDecimal balanceType;

	private String balanceTypeName;

	private String venderName1;

	private String dispVenderName1;

	private String venderName2;

	private String dispVenderName2;

	private String venderName3;

	private String dispVenderName3;

	private String venderName4;

	private String dispVenderName4;

	private String venderName5;

	private String dispVenderName5;

	//
	// インスタンスメソッド
	//

	/**
	 * balanceCd取得.
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * balanceCd設定.
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * balanceType取得.
	 * @return balanceType
	 */
	public java.math.BigDecimal getBalanceType() {
		return this.balanceType;
	}

	/**
	 * balanceType設定.
	 * @param balanceType balanceType
	 */
	public void setBalanceType(final java.math.BigDecimal balanceType) {
		this.balanceType = balanceType;
	}

	/**
	 * balanceTypeName取得.
	 * @return balanceTypeName
	 */
	public String getBalanceTypeName() {
		return this.balanceTypeName;
	}

	/**
	 * balanceTypeName設定.
	 * @param balanceTypeName balanceTypeName
	 */
	public void setBalanceTypeName(final String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * dispVenderName1取得.
	 * @return dispVenderName1
	 */
	public String getDispVenderName1() {
		return this.dispVenderName1;
	}

	/**
	 * dispVenderName1設定.
	 * @param dispVenderName1 dispVenderName1
	 */
	public void setDispVenderName1(final String dispVenderName1) {
		this.dispVenderName1 = dispVenderName1;
	}

	/**
	 * venderName2取得.
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * dispVenderName2取得.
	 * @return dispVenderName2
	 */
	public String getDispVenderName2() {
		return this.dispVenderName2;
	}

	/**
	 * dispVenderName2設定.
	 * @param dispVenderName2 dispVenderName2
	 */
	public void setDispVenderName2(final String dispVenderName2) {
		this.dispVenderName2 = dispVenderName2;
	}

	/**
	 * venderName3取得.
	 * @return venderName3
	 */
	public String getVenderName3() {
		return this.venderName3;
	}

	/**
	 * venderName3設定.
	 * @param venderName3 venderName3
	 */
	public void setVenderName3(final String venderName3) {
		this.venderName3 = venderName3;
	}

	/**
	 * dispVenderName3取得.
	 * @return dispVenderName3
	 */
	public String getDispVenderName3() {
		return this.dispVenderName3;
	}

	/**
	 * dispVenderName3設定.
	 * @param dispVenderName3 dispVenderName3
	 */
	public void setDispVenderName3(final String dispVenderName3) {
		this.dispVenderName3 = dispVenderName3;
	}

	/**
	 * venderName4取得.
	 * @return venderName4
	 */
	public String getVenderName4() {
		return this.venderName4;
	}

	/**
	 * venderName4設定.
	 * @param venderName4 venderName4
	 */
	public void setVenderName4(final String venderName4) {
		this.venderName4 = venderName4;
	}

	/**
	 * dispVenderName4取得.
	 * @return dispVenderName4
	 */
	public String getDispVenderName4() {
		return this.dispVenderName4;
	}

	/**
	 * dispVenderName4設定.
	 * @param dispVenderName4 dispVenderName4
	 */
	public void setDispVenderName4(final String dispVenderName4) {
		this.dispVenderName4 = dispVenderName4;
	}

	/**
	 * venderName5取得.
	 * @return venderName5
	 */
	public String getVenderName5() {
		return this.venderName5;
	}

	/**
	 * venderName5設定.
	 * @param venderName5 venderName5
	 */
	public void setVenderName5(final String venderName5) {
		this.venderName5 = venderName5;
	}

	/**
	 * dispVenderName5取得.
	 * @return dispVenderName5
	 */
	public String getDispVenderName5() {
		return this.dispVenderName5;
	}

	/**
	 * dispVenderName5設定.
	 * @param dispVenderName5 dispVenderName5
	 */
	public void setDispVenderName5(final String dispVenderName5) {
		this.dispVenderName5 = dispVenderName5;
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

