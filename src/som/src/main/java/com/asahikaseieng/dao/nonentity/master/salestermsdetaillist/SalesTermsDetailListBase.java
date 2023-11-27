/*
 * Created on 2009/08/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsdetaillist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SalesTermsDetailListクラス.
 * @author t0011036
 */
public class SalesTermsDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsDetailListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション balanceType */
	public static final String balanceType_COLUMN = "BALANCE_TYPE";

	/** COLUMNアノテーション balanceTypeName */
	public static final String balanceTypeName_COLUMN = "BALANCE_TYPE_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	
	/** COLUMNアノテーション leadTime */
	public static final String leadTime_COLUMN = "LEAD_TIME";
	
	/** COLUMNアノテーション deliveryTime */
	public static final String deliveryTime_COLUMN = "DELIVERY_TIME";

	/** COLUMNアノテーション carryName */
	public static final String carryName_COLUMN = "CARRY_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal seq;

	private String deliveryCd;

	private String deliveryName1;

	private String balanceCd;

	private java.math.BigDecimal balanceType;

	private String balanceTypeName;

	private String venderCd;

	private String venderName1;

	private String itemCd;

	private java.math.BigDecimal version;

	private String otherCompanyCd1;

	private String itemName;

	private String styleOfPacking;

	private String tantoCd;

	private String tantoNm;

	private java.sql.Timestamp updateDate;
	
	private String leadTime;
	
	private String deliveryTime;
	
	private String carryName;

	//
	// インスタンスメソッド
	//

	/**
	 * seq取得.
	 * @return seq
	 */
	public java.math.BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * seq設定.
	 * @param seq seq
	 */
	public void setSeq(final java.math.BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * deliveryCd取得.
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * deliveryCd設定.
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryName1取得.
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * deliveryName1設定.
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

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
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
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
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

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

	/**
	 * carryNameを取得します。
	 * @return carryName
	 */
	public String getCarryName() {
		return carryName;
	}

	/**
	 * carryNameを設定します。
	 * @param carryName carryName
	 */
	public void setCarryName(final String carryName) {
		this.carryName = carryName;
	}

	/**
	 * deliveryTimeを取得します。
	 * @return deliveryTime
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * deliveryTimeを設定します。
	 * @param deliveryTime deliveryTime
	 */
	public void setDeliveryTime(final String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * leadTimeを取得します。
	 * @return leadTime
	 */
	public String getLeadTime() {
		return leadTime;
	}

	/**
	 * leadTimeを設定します。
	 * @param leadTime leadTime
	 */
	public void setLeadTime(final String leadTime) {
		this.leadTime = leadTime;
	}
}

