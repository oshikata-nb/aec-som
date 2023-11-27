/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ShippingDetailListForReportクラス.
 * @author kanri-user
 */
public class ShippingDetailListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション shippingRowNo */
	public static final String shippingRowNo_COLUMN = "SHIPPING_ROW_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション shippingInstruction */
	public static final String shippingInstruction_COLUMN = "SHIPPING_INSTRUCTION";

	/** COLUMNアノテーション shippingResultDate */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション shippingResultQuantity */
	public static final String shippingResultQuantity_COLUMN = "SHIPPING_RESULT_QUANTITY";

	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション summery */
	public static final String summery_COLUMN = "SUMMERY";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション deliveryComp */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション productOutOrderBc */
	public static final String productOutOrderBc_COLUMN = "PRODUCT_OUT_ORDER_BC";

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

	private String shippingNo;

	private java.math.BigDecimal shippingRowNo;

	private java.math.BigDecimal rowNo;

	private String lotNo;

	private java.math.BigDecimal shippingInstruction;

	private java.sql.Timestamp shippingResultDate;

	private java.math.BigDecimal shippingResultQuantity;

	private java.math.BigDecimal shippingStatus;

	private String summery;

	private String locationCd;

	private String locationName;

	private java.math.BigDecimal deliveryComp;

	private String productOutOrderBc;

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
	 * shippingNo取得.
	 * @return shippingNo
	 */
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * shippingNo設定.
	 * @param shippingNo shippingNo
	 */
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
	}

	/**
	 * shippingRowNo取得.
	 * @return shippingRowNo
	 */
	public java.math.BigDecimal getShippingRowNo() {
		return this.shippingRowNo;
	}

	/**
	 * shippingRowNo設定.
	 * @param shippingRowNo shippingRowNo
	 */
	public void setShippingRowNo(final java.math.BigDecimal shippingRowNo) {
		this.shippingRowNo = shippingRowNo;
	}

	/**
	 * rowNo取得.
	 * @return rowNo
	 */
	public java.math.BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 * @param rowNo rowNo
	 */
	public void setRowNo(final java.math.BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * lotNo取得.
	 * @return lotNo
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * lotNo設定.
	 * @param lotNo lotNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * shippingInstruction取得.
	 * @return shippingInstruction
	 */
	public java.math.BigDecimal getShippingInstruction() {
		return this.shippingInstruction;
	}

	/**
	 * shippingInstruction設定.
	 * @param shippingInstruction shippingInstruction
	 */
	public void setShippingInstruction(final java.math.BigDecimal shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
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
	 * shippingResultQuantity取得.
	 * @return shippingResultQuantity
	 */
	public java.math.BigDecimal getShippingResultQuantity() {
		return this.shippingResultQuantity;
	}

	/**
	 * shippingResultQuantity設定.
	 * @param shippingResultQuantity shippingResultQuantity
	 */
	public void setShippingResultQuantity(final java.math.BigDecimal shippingResultQuantity) {
		this.shippingResultQuantity = shippingResultQuantity;
	}

	/**
	 * shippingStatus取得.
	 * @return shippingStatus
	 */
	public java.math.BigDecimal getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * shippingStatus設定.
	 * @param shippingStatus shippingStatus
	 */
	public void setShippingStatus(final java.math.BigDecimal shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * summery取得.
	 * @return summery
	 */
	public String getSummery() {
		return this.summery;
	}

	/**
	 * summery設定.
	 * @param summery summery
	 */
	public void setSummery(final String summery) {
		this.summery = summery;
	}

	/**
	 * locationCd取得.
	 * @return locationCd
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * locationCd設定.
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * locationName取得.
	 * @return locationName
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * locationName設定.
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * deliveryComp取得.
	 * @return deliveryComp
	 */
	public java.math.BigDecimal getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * deliveryComp設定.
	 * @param deliveryComp deliveryComp
	 */
	public void setDeliveryComp(final java.math.BigDecimal deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * productOutOrderBc取得.
	 * @return productOutOrderBc
	 */
	public String getProductOutOrderBc() {
		return this.productOutOrderBc;
	}

	/**
	 * productOutOrderBc設定.
	 * @param productOutOrderBc productOutOrderBc
	 */
	public void setProductOutOrderBc(final String productOutOrderBc) {
		this.productOutOrderBc = productOutOrderBc;
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

