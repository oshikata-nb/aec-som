/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderVenderLinkListForReportクラス.
 * @author 
 */
public class OrderVenderLinkListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderVenderLinkListForReportBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション venderGroupCd */
	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";
	
	/** COLUMNアノテーション somDeliveryCd */
	public static final String somDeliveryCd_COLUMN = "SOM_DELIVERY_CD";
	
	/** COLUMNアノテーション deliveryName */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";
	
	/** COLUMNアノテーション deliveryFullName */
	public static final String deliveryFullName_COLUMN = "DELIVERY_FULL_NAME";
	
	/** COLUMNアノテーション address */
	public static final String address_COLUMN = "ADDRESS";
	
	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";
	
	/** COLUMNアノテーション zipcodeNo */
	public static final String zipcodeNo_COLUMN = "ZIPCODE_NO";
	
	/** COLUMNアノテーション carryName */
	public static final String carryName_COLUMN = "CARRY_NAME";
	
	/** COLUMNアノテーション deliveryCd1 */
	public static final String deliveryCd1_COLUMN = "DELIVERY_CD1";
	
	/** COLUMNアノテーション deliveryCd2 */
	public static final String deliveryCd2_COLUMN = "DELIVERY_CD2";
	
	/** COLUMNアノテーション deliveryCd3 */
	public static final String deliveryCd3_COLUMN = "DELIVERY_CD3";
	
	/** COLUMNアノテーション somItemCd */
	public static final String somItemCd_COLUMN = "SOM_ITEM_CD";
	
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";
	
	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";
	
	/** COLUMNアノテーション unitOfStockControl */
	public static final String unitOfStockControl_COLUMN = "UNIT_OF_STOCK_CONTROL";
	
	/** COLUMNアノテーション allUpWeight */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";
	
	/** COLUMNアノテーション numberOfInsertions */
	public static final String numberOfInsertions_COLUMN = "NUMBER_OF_INSERTIONS";
	
	/** COLUMNアノテーション itemCd1 */
	public static final String itemCd1_COLUMN = "ITEM_CD1";
	
	/** COLUMNアノテーション itemCd2 */
	public static final String itemCd2_COLUMN = "ITEM_CD2";
	
	/** COLUMNアノテーション itemCd3 */
	public static final String itemCd3_COLUMN = "ITEM_CD3";
	
	private String venderGroupCd;

	private String somDeliveryCd;
	
	private String deliveryName;
	
	private String deliveryFullName;
	
	private String address;
	
	private String telNo;
	
	private String zipcodeNo;
	
	private String carryName;
	
	private String deliveryCd1;
	
	private String deliveryCd2;
	
	private String deliveryCd3;
	
	private String somItemCd;
	
	private String itemName;
	
	private String styleOfPacking;
	
	private String otherCompanyCd1;
	
	private String unitOfStockControl;
	
	private String allUpWeight;	
	
	private String numberOfInsertions;
	
	private String itemCd1;
	
	private String itemCd2;
	
	private String itemCd3;
	
	/**
	 * venderGroupCd取得.
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return this.venderGroupCd;
	}

	/**
	 * venderGroupCd設定.
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(final String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}

	/**
	 * somDeliveryCd取得.
	 * @return somDeliveryCd
	 */
	public String getSomDeliveryCd() {
		return this.somDeliveryCd;
	}

	/**
	 * somDeliveryCd設定.
	 * @param somDeliveryCd somDeliveryCd
	 */
	public void setSomDeliveryCd(final String somDeliveryCd) {
		this.somDeliveryCd = somDeliveryCd;
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
	 * deliveryFullName取得.
	 * @return deliveryFullName
	 */
	public String getDeliveryFullName() {
		return this.deliveryFullName;
	}

	/**
	 * deliveryFullName設定.
	 * @param deliveryFullname deliveryFullName
	 */
	public void setDeliveryFullName(final String deliveryFullName) {
		this.deliveryFullName = deliveryFullName;
	}

	
	/**
	 * address取得.
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * address設定.
	 * @param address address
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	
	/**
	 * telNo取得.
	 * @return telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * telNo設定.
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	
	/**
	 * zipcodeNo取得.
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return this.zipcodeNo;
	}

	/**
	 * zipcodeNo設定.
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	
	/**
	 * carryName取得.
	 * @return carryName
	 */
	public String getCarryName() {
		return this.carryName;
	}

	/**
	 * carryName設定.
	 * @param carryName carryName
	 */
	public void setCarryName(final String carryName) {
		this.carryName = carryName;
	}

	
	/**
	 * deliveryCd1取得.
	 * @return deliveryCd1
	 */
	public String getDeliveryCd1() {
		return this.deliveryCd1;
	}

	/**
	 * deliveryCd1設定.
	 * @param deliveryCd1 deliveryCd1
	 */
	public void setDeliveryCd1(final String deliveryCd1) {
		this.deliveryCd1 = deliveryCd1;
	}

	
	/**
	 * deliveryCd2取得.
	 * @return deliveryCd2
	 */
	public String getDeliveryCd2() {
		return this.deliveryCd2;
	}

	/**
	 * deliveryCd2設定.
	 * @param deliveryCd2 deliveryCd2
	 */
	public void setDeliveryCd2(final String deliveryCd2) {
		this.deliveryCd2 = deliveryCd2;
	}

	
	/**
	 * deliveryCd3取得.
	 * @return deliveryCd3
	 */
	public String getDeliveryCd3() {
		return this.deliveryCd3;
	}

	/**
	 * deliveryCd3設定.
	 * @param deliveryCd3 deliveryCd3
	 */
	public void setDeliveryCd3(final String deliveryCd3) {
		this.deliveryCd3 = deliveryCd3;
	}

	
	/**
	 * somItemCd取得.
	 * @return somItemCd
	 */
	public String getSomItemCd() {
		return this.somItemCd;
	}

	/**
	 * somItemCd設定.
	 * @param somItemCd somItemCd
	 */
	public void setSomItemCd(final String somItemCd) {
		this.somItemCd = somItemCd;
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
	 * unitOfStockControl取得.
	 * @return unitOfStockControl
	 */
	public String getUnitOfStockControl() {
		return this.unitOfStockControl;
	}

	/**
	 * unitOfStockControl設定.
	 * @param unitOfStockControl unitOfStockControl
	 */
	public void setUnitOfStockControl(final String unitOfStockControl) {
		this.unitOfStockControl = unitOfStockControl;
	}
	
	/**
	 * allUpWeight取得.
	 * @return allUpWeight
	 */
	public String getAllUpWeight() {
		return this.allUpWeight;
	}

	/**
	 * allUpWeight設定.
	 * @param allUpWeight allUpWeight
	 */
	public void setAllUpWeight(final String allUpWeight) {
		this.allUpWeight = allUpWeight;
	}
	
	/**
	 * numberOfInsertions取得.
	 * @return numberOfInsertions
	 */
	public String getNumberOfInsertions() {
		return this.numberOfInsertions;
	}

	/**
	 * numberOfInsertions設定.
	 * @param numberOfInsertions numberOfInsertions
	 */
	public void setNumberOfInsertions(final String numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}
	
	/**
	 * itemCd1取得.
	 * @return itemCd1
	 */
	public String getItemCd1() {
		return this.itemCd1;
	}

	/**
	 * itemCd1設定.
	 * @param itemCd1 itemCd1
	 */
	public void setItemCd1(final String itemCd1) {
		this.itemCd1 = itemCd1;
	}
	
	/**
	 * itemCd2取得.
	 * @return itemCd2
	 */
	public String getItemCd2() {
		return this.itemCd2;
	}

	/**
	 * itemCd2設定.
	 * @param itemCd2 itemCd2
	 */
	public void setItemCd2(final String itemCd2) {
		this.itemCd2 = itemCd2;
	}
	
	/**
	 * itemCd3取得.
	 * @return itemCd3
	 */
	public String getItemCd3() {
		return this.itemCd3;
	}

	/**
	 * itemCd3設定.
	 * @param itemCd3 itemCd3
	 */
	public void setItemCd3(final String itemCd3) {
		this.itemCd3 = itemCd3;
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
