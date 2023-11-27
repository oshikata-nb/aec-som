/*
 * Created on 2009/05/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ComponentInformationQueueListクラス.
 * @author t0011036
 */
public class ComponentInformationQueueListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ComponentInformationQueueListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション indicateOrder */
	public static final String indicateOrder_COLUMN = "INDICATE_ORDER";

	/** COLUMNアノテーション componentCd */
	public static final String componentCd_COLUMN = "COMPONENT_CD";

	/** COLUMNアノテーション calcValue */
	public static final String calcValue_COLUMN = "CALC_VALUE";

	/** COLUMNアノテーション indicateValue */
	public static final String indicateValue_COLUMN = "INDICATE_VALUE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション componentName */
	public static final String componentName_COLUMN = "COMPONENT_NAME";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.math.BigDecimal indicateOrder;

	private String componentCd;

	private java.math.BigDecimal calcValue;

	private String indicateValue;

	private java.sql.Timestamp updateDate;

	private String componentName;

	private String unitDivision;

	//
	// インスタンスメソッド
	//

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
	 * indicateOrder取得.
	 * @return indicateOrder
	 */
	public java.math.BigDecimal getIndicateOrder() {
		return this.indicateOrder;
	}

	/**
	 * indicateOrder設定.
	 * @param indicateOrder indicateOrder
	 */
	public void setIndicateOrder(final java.math.BigDecimal indicateOrder) {
		this.indicateOrder = indicateOrder;
	}

	/**
	 * componentCd取得.
	 * @return componentCd
	 */
	public String getComponentCd() {
		return this.componentCd;
	}

	/**
	 * componentCd設定.
	 * @param componentCd componentCd
	 */
	public void setComponentCd(final String componentCd) {
		this.componentCd = componentCd;
	}

	/**
	 * calcValue取得.
	 * @return calcValue
	 */
	public java.math.BigDecimal getCalcValue() {
		return this.calcValue;
	}

	/**
	 * calcValue設定.
	 * @param calcValue calcValue
	 */
	public void setCalcValue(final java.math.BigDecimal calcValue) {
		this.calcValue = calcValue;
	}

	/**
	 * indicateValue取得.
	 * @return indicateValue
	 */
	public String getIndicateValue() {
		return this.indicateValue;
	}

	/**
	 * indicateValue設定.
	 * @param indicateValue indicateValue
	 */
	public void setIndicateValue(final String indicateValue) {
		this.indicateValue = indicateValue;
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
	 * componentName取得.
	 * @return componentName
	 */
	public String getComponentName() {
		return this.componentName;
	}

	/**
	 * componentName設定.
	 * @param componentName componentName
	 */
	public void setComponentName(final String componentName) {
		this.componentName = componentName;
	}

	/**
	 * unitDivision取得.
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * unitDivision設定.
	 * @param unitDivision unitDivision
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
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

