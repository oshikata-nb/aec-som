/*
 * Created on 2009/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BalanceListForReportクラス.
 * @author t0011036
 */
public class BalanceListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BalanceListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション venderCd1 */
	public static final String venderCd1_COLUMN = "VENDER_CD1";

	/** COLUMNアノテーション venderCd2 */
	public static final String venderCd2_COLUMN = "VENDER_CD2";

	/** COLUMNアノテーション venderCd3 */
	public static final String venderCd3_COLUMN = "VENDER_CD3";

	/** COLUMNアノテーション venderCd4 */
	public static final String venderCd4_COLUMN = "VENDER_CD4";

	/** COLUMNアノテーション venderCd5 */
	public static final String venderCd5_COLUMN = "VENDER_CD5";

	/** COLUMNアノテーション shopLevel */
	public static final String shopLevel_COLUMN = "SHOP_LEVEL";

	/** COLUMNアノテーション balanceType */
	public static final String balanceType_COLUMN = "BALANCE_TYPE";

	/** COLUMNアノテーション balanceTypeName */
	public static final String balanceTypeName_COLUMN = "BALANCE_TYPE_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション venderName3 */
	public static final String venderName3_COLUMN = "VENDER_NAME3";

	/** COLUMNアノテーション venderName4 */
	public static final String venderName4_COLUMN = "VENDER_NAME4";

	/** COLUMNアノテーション venderName5 */
	public static final String venderName5_COLUMN = "VENDER_NAME5";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String balanceCd;

	private String venderCd1;

	private String venderCd2;

	private String venderCd3;

	private String venderCd4;

	private String venderCd5;

	private java.math.BigDecimal shopLevel;

	private java.math.BigDecimal balanceType;

	private String balanceTypeName;

	private String venderName1;

	private String venderName2;

	private String venderName3;

	private String venderName4;

	private String venderName5;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

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
	 * venderCd1取得.
	 * @return venderCd1
	 */
	public String getVenderCd1() {
		return this.venderCd1;
	}

	/**
	 * venderCd1設定.
	 * @param venderCd1 venderCd1
	 */
	public void setVenderCd1(final String venderCd1) {
		this.venderCd1 = venderCd1;
	}

	/**
	 * venderCd2取得.
	 * @return venderCd2
	 */
	public String getVenderCd2() {
		return this.venderCd2;
	}

	/**
	 * venderCd2設定.
	 * @param venderCd2 venderCd2
	 */
	public void setVenderCd2(final String venderCd2) {
		this.venderCd2 = venderCd2;
	}

	/**
	 * venderCd3取得.
	 * @return venderCd3
	 */
	public String getVenderCd3() {
		return this.venderCd3;
	}

	/**
	 * venderCd3設定.
	 * @param venderCd3 venderCd3
	 */
	public void setVenderCd3(final String venderCd3) {
		this.venderCd3 = venderCd3;
	}

	/**
	 * venderCd4取得.
	 * @return venderCd4
	 */
	public String getVenderCd4() {
		return this.venderCd4;
	}

	/**
	 * venderCd4設定.
	 * @param venderCd4 venderCd4
	 */
	public void setVenderCd4(final String venderCd4) {
		this.venderCd4 = venderCd4;
	}

	/**
	 * venderCd5取得.
	 * @return venderCd5
	 */
	public String getVenderCd5() {
		return this.venderCd5;
	}

	/**
	 * venderCd5設定.
	 * @param venderCd5 venderCd5
	 */
	public void setVenderCd5(final String venderCd5) {
		this.venderCd5 = venderCd5;
	}

	/**
	 * shopLevel取得.
	 * @return shopLevel
	 */
	public java.math.BigDecimal getShopLevel() {
		return this.shopLevel;
	}

	/**
	 * shopLevel設定.
	 * @param shopLevel shopLevel
	 */
	public void setShopLevel(final java.math.BigDecimal shopLevel) {
		this.shopLevel = shopLevel;
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
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
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
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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

