/*
 * Created on Tue Feb 17 16:40:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shippingtemp;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ShippingTempBaseクラス.
 * @author kanri-user
 */
public class ShippingTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingTempBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SHIPPING_TEMP";


//	/** IDアノテーション sendingOffNumber. */
//	public static final String sendingOffNumber_ID = "assigned";
//	/** IDアノテーション shippingInstructDate. */
//	public static final String shippingInstructDate_ID = "assigned";

	/** COLUMNアノテーション shippingInstructDate. */
	public static final String shippingInstructDate_COLUMN = "SHIPPING_INSTRUCT_DATE";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd. */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName. */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション carryCd. */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション sendingOffNumber. */
	public static final String sendingOffNumber_COLUMN = "SENDING_OFF_NUMBER";

	/** COLUMNアノテーション shippingOrderSendCompFlag. */
	public static final String shippingOrderSendCompFlag_COLUMN = "SHIPPING_ORDER_SEND_COMP_FLAG";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName. */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション itemUnit. */
	public static final String itemUnit_COLUMN = "ITEM_UNIT";

	/** COLUMNアノテーション shippingResultDate. */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp shippingInstructDate;

	private String venderCd;

	private String deliveryCd;

	private String deliveryName;

	private String carryCd;

	private String sendingOffNumber;

	private java.math.BigDecimal shippingOrderSendCompFlag;

	private String itemCd;

	private String itemName;

	private String itemUnit;

	private java.sql.Timestamp shippingResultDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * shippingInstructDate取得.
	 * @return shippingInstructDate
	 */
	public java.sql.Timestamp getShippingInstructDate() {
		return this.shippingInstructDate;
	}

	/**
	 * shippingInstructDate設定.
	 * @param shippingInstructDate shippingInstructDate
	 */
	public void setShippingInstructDate(final java.sql.Timestamp shippingInstructDate) {
		this.shippingInstructDate = shippingInstructDate;
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
	 * deliveryName取得.
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return this.deliveryName;
	}

	/**
	 * deliveryName設定.
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * sendingOffNumber取得.
	 * @return sendingOffNumber
	 */
	public String getSendingOffNumber() {
		return this.sendingOffNumber;
	}

	/**
	 * sendingOffNumber設定.
	 * @param sendingOffNumber sendingOffNumber
	 */
	public void setSendingOffNumber(final String sendingOffNumber) {
		this.sendingOffNumber = sendingOffNumber;
	}

	/**
	 * shippingOrderSendCompFlag取得.
	 * @return shippingOrderSendCompFlag
	 */
	public java.math.BigDecimal getShippingOrderSendCompFlag() {
		return this.shippingOrderSendCompFlag;
	}

	/**
	 * shippingOrderSendCompFlag設定.
	 * @param shippingOrderSendCompFlag shippingOrderSendCompFlag
	 */
	public void setShippingOrderSendCompFlag(final java.math.BigDecimal shippingOrderSendCompFlag) {
		this.shippingOrderSendCompFlag = shippingOrderSendCompFlag;
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
	 * itemUnit取得.
	 * @return itemUnit
	 */
	public String getItemUnit() {
		return this.itemUnit;
	}

	/**
	 * itemUnit設定.
	 * @param itemUnit itemUnit
	 */
	public void setItemUnit(final String itemUnit) {
		this.itemUnit = itemUnit;
	}

	/**
	 * shippingResultDate取得.
	 * @return shippingResultDate
	 */
	public java.sql.Timestamp getShippingResultDate() {
		return this.shippingResultDate;
	}

	/**
	 * shippingResultDate設定.
	 * @param shippingResultDate shippingResultDate
	 */
	public void setShippingResultDate(final java.sql.Timestamp shippingResultDate) {
		this.shippingResultDate = shippingResultDate;
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
