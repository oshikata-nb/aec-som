/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdataentity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrderImportListクラス.
 * @author 
 */
public class OrderImportDataEntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportDataEntityBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション frstOrderNo */
	public static final String frstOrderNo_COLUMN = "FRST_ORDER_NO";
	/** COLUMNアノテーション ctmOrderDate */
	public static final String ctmOrderDate_COLUMN = "CTM_ORDER_DATE";
	/** COLUMNアノテーション importDate */
	public static final String importDate_COLUMN = "IMPORT_DATE";
	/** COLUMNアノテーション ctmDeliveryCd  */
	public static final String ctmDeliveryCd_COLUMN = "CTM_DELIVERY_CD";
	/** COLUMNアノテーション ctmDeliveryName */
	public static final String ctmDeliveryName_COLUMN = "CTM_DELIVERY_NAME";
	/** COLUMNアノテーション ctmAddress */
	public static final String ctmAddress_COLUMN = "CTM_ADDRESS";
	/** COLUMNアノテーション ctmDeliveryAddress */
	public static final String ctmDeliveryAddress_COLUMN = "CTM_DELIVERY_ADDRESS";
	/** COLUMNアノテーション ctmDeliveryTelNo */
	public static final String ctmDeliveryTelNo_COLUMN = "CTM_DELIVERY_TEL_NO";
	/** COLUMNアノテーション ctmRemark */
	public static final String ctmRemark_COLUMN = "CTM_REMARK";
	/** COLUMNアノテーション ctmVenderName */
	public static final String ctmVenderName_COLUMN = "CTM_VENDER_NAME";
	/** COLUMNアノテーション errorMsg1 */
	public static final String errorMsg1_COLUMN = "ERROR_MSG1";
	/** COLUMNアノテーション errorMsg2 */
	public static final String errorMsg2_COLUMN = "ERROR_MSG2";
	/** COLUMNアノテーション errorMsg3 */
	public static final String errorMsg3_COLUMN = "ERROR_MSG3";

	
	
	

	// インスタンスフィールド
	//
	 private String frstOrderNo;
	 private String ctmOrderDate;
	 private String importDate;
	 private String ctmDeliveryCd;
	 private String ctmDeliveryName;
	 private String ctmAddress;
	 private String ctmDeliveryAddress;
	 private String ctmDeliveryTelNo;
	 private String ctmRemark;
	 private String ctmVenderName;
	 private String errorMsg1;
	 private String errorMsg2;
	 private String errorMsg3;
	 
	/**
	 * frstOrderNoを取得します。
	 * @return frstOrderNo
	 */
	public String getFrstOrderNo() {
		return frstOrderNo;
	}

	/**
	 * ctmOrderDateを取得します。
	 * @return ctmOrderDate
	 */
	public String getCtmOrderDate() {
		return ctmOrderDate;
	}

	/**
	 * importDateを取得します。
	 * @return importDate
	 */
	public String getImportDate() {
		return importDate;
	}

	/**
	 * ctmDeliveryNameを取得します。
	 * @return ctmDeliveryName
	 */
	public String getCtmDeliveryName() {
		return ctmDeliveryName;
	}

	/**
	 * ctmAddressを取得します。
	 * @return ctmAddress
	 */
	public String getCtmAddress() {
		return ctmAddress;
	}

	/**
	 * ctmDeliveryAddressを取得します。
	 * @return ctmDeliveryAddress
	 */
	public String getCtmDeliveryAddress() {
		return ctmDeliveryAddress;
	}

	/**
	 * ctmDeliveryTelNoを取得します。
	 * @return ctmDeliveryTelNo
	 */
	public String getCtmDeliveryTelNo() {
		return ctmDeliveryTelNo;
	}

	/**
	 * ctmRemarkを取得します。
	 * @return ctmRemark
	 */
	public String getCtmRemark() {
		return ctmRemark;
	}

	/**
	 * ctmVenderNameを取得します。
	 * @return ctmVenderName
	 */
	public String getCtmVenderName() {
		return ctmVenderName;
	}

	/**
	 * errorMsg1を取得します。
	 * @return errorMsg1
	 */
	public String getErrorMsg1() {
		return errorMsg1;
	}

	/**
	 * errorMsg2を取得します。
	 * @return errorMsg2
	 */
	public String getErrorMsg2() {
		return errorMsg2;
	}

	/**
	 * errorMsg3を取得します。
	 * @return errorMsg3
	 */
	public String getErrorMsg3() {
		return errorMsg3;
	}

	/**
	 * frstOrderNoを設定します。
	 * @param frstOrderNo frstOrderNo
	 */
	public void setFrstOrderNo(String frstOrderNo) {
		this.frstOrderNo = frstOrderNo;
	}

	/**
	 * ctmOrderDateを設定します。
	 * @param ctmOrderDate ctmOrderDate
	 */
	public void setCtmOrderDate(String ctmOrderDate) {
		this.ctmOrderDate = ctmOrderDate;
	}

	/**
	 * importDateを設定します。
	 * @param importDate importDate
	 */
	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	/**
	 * ctmDeliveryCdを取得します。
	 * @return ctmDeliveryCd
	 */
	public String getCtmDeliveryCd() {
		return ctmDeliveryCd;
	}

	/**
	 * ctmDeliveryCdを設定します。
	 * @param ctmDeliveryCd ctmDeliveryCd
	 */
	public void setCtmDeliveryCd(String ctmDeliveryCd) {
		this.ctmDeliveryCd = ctmDeliveryCd;
	}

	/**
	 * ctmDeliveryNameを設定します。
	 * @param ctmDeliveryName ctmDeliveryName
	 */
	public void setCtmDeliveryName(String ctmDeliveryName) {
		this.ctmDeliveryName = ctmDeliveryName;
	}

	/**
	 * ctmAddressを設定します。
	 * @param ctmAddress ctmAddress
	 */
	public void setCtmAddress(String ctmAddress) {
		this.ctmAddress = ctmAddress;
	}

	/**
	 * ctmDeliveryAddressを設定します。
	 * @param ctmDeliveryAddress ctmDeliveryAddress
	 */
	public void setCtmDeliveryAddress(String ctmDeliveryAddress) {
		this.ctmDeliveryAddress = ctmDeliveryAddress;
	}

	/**
	 * ctmDeliveryTelNoを設定します。
	 * @param ctmDeliveryTelNo ctmDeliveryTelNo
	 */
	public void setCtmDeliveryTelNo(String ctmDeliveryTelNo) {
		this.ctmDeliveryTelNo = ctmDeliveryTelNo;
	}

	/**
	 * ctmRemarkを設定します。
	 * @param ctmRemark ctmRemark
	 */
	public void setCtmRemark(String ctmRemark) {
		this.ctmRemark = ctmRemark;
	}

	/**
	 * ctmVenderNameを設定します。
	 * @param ctmVenderName ctmVenderName
	 */
	public void setCtmVenderName(String ctmVenderName) {
		this.ctmVenderName = ctmVenderName;
	}

	/**
	 * errorMsg1を設定します。
	 * @param errorMsg1 errorMsg1
	 */
	public void setErrorMsg1(String errorMsg1) {
		this.errorMsg1 = errorMsg1;
	}

	/**
	 * errorMsg2を設定します。
	 * @param errorMsg2 errorMsg2
	 */
	public void setErrorMsg2(String errorMsg2) {
		this.errorMsg2 = errorMsg2;
	}

	/**
	 * errorMsg3を設定します。
	 * @param errorMsg3 errorMsg3
	 */
	public void setErrorMsg3(String errorMsg3) {
		this.errorMsg3 = errorMsg3;
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

