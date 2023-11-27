/*
 * Created on 2009/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * InventoryDrawingTotalQtyクラス.
 * @author t0011036
 */
public class InventoryDrawingTotalQtyBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryDrawingTotalQtyBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション backorderQty */
	public static final String backorderQty_COLUMN = "BACKORDER_QTY";

	/** COLUMNアノテーション finishQty */
	public static final String finishQty_COLUMN = "FINISH_QTY";

	/** COLUMNアノテーション inspectionQty */
	public static final String inspectionQty_COLUMN = "INSPECTION_QTY";

	/** COLUMNアノテーション assignQty */
	public static final String assignQty_COLUMN = "ASSIGN_QTY";

	/** COLUMNアノテーション salesAssignQty */
	public static final String salesAssignQty_COLUMN = "SALES_ASSIGN_QTY";

	/** COLUMNアノテーション usepossibleQty */
	public static final String usepossibleQty_COLUMN = "USEPOSSIBLE_QTY";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal inventoryQty;

	private java.math.BigDecimal backorderQty;

	private java.math.BigDecimal finishQty;

	private java.math.BigDecimal inspectionQty;

	private java.math.BigDecimal assignQty;

	private java.math.BigDecimal salesAssignQty;

	private java.math.BigDecimal usepossibleQty;

	private String unitOfOperationManagement;

	private String name01;

	//
	// インスタンスメソッド
	//

	/**
	 * inventoryQty取得.
	 * @return inventoryQty
	 */
	public java.math.BigDecimal getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * inventoryQty設定.
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final java.math.BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * backorderQty取得.
	 * @return backorderQty
	 */
	public java.math.BigDecimal getBackorderQty() {
		return this.backorderQty;
	}

	/**
	 * backorderQty設定.
	 * @param backorderQty backorderQty
	 */
	public void setBackorderQty(final java.math.BigDecimal backorderQty) {
		this.backorderQty = backorderQty;
	}

	/**
	 * finishQty取得.
	 * @return finishQty
	 */
	public java.math.BigDecimal getFinishQty() {
		return this.finishQty;
	}

	/**
	 * finishQty設定.
	 * @param finishQty finishQty
	 */
	public void setFinishQty(final java.math.BigDecimal finishQty) {
		this.finishQty = finishQty;
	}

	/**
	 * inspectionQty取得.
	 * @return inspectionQty
	 */
	public java.math.BigDecimal getInspectionQty() {
		return this.inspectionQty;
	}

	/**
	 * inspectionQty設定.
	 * @param inspectionQty inspectionQty
	 */
	public void setInspectionQty(final java.math.BigDecimal inspectionQty) {
		this.inspectionQty = inspectionQty;
	}

	/**
	 * assignQty取得.
	 * @return assignQty
	 */
	public java.math.BigDecimal getAssignQty() {
		return this.assignQty;
	}

	/**
	 * assignQty設定.
	 * @param assignQty assignQty
	 */
	public void setAssignQty(final java.math.BigDecimal assignQty) {
		this.assignQty = assignQty;
	}

	/**
	 * salesAssignQty取得.
	 * @return salesAssignQty
	 */
	public java.math.BigDecimal getSalesAssignQty() {
		return salesAssignQty;
	}

	/**
	 * salesAssignQty設定.
	 * @param salesAssignQty salesAssignQty
	 */
	public void setSalesAssignQty(final java.math.BigDecimal salesAssignQty) {
		this.salesAssignQty = salesAssignQty;
	}

	/**
	 * usepossibleQty取得.
	 * @return usepossibleQty
	 */
	public java.math.BigDecimal getUsepossibleQty() {
		return this.usepossibleQty;
	}

	/**
	 * usepossibleQty設定.
	 * @param usepossibleQty usepossibleQty
	 */
	public void setUsepossibleQty(final java.math.BigDecimal usepossibleQty) {
		this.usepossibleQty = usepossibleQty;
	}

	/**
	 * unitOfOperationManagement取得.
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return this.unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagement設定.
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * name01取得.
	 * @return name01
	 */
	public String getName01() {
		return this.name01;
	}

	/**
	 * name01設定.
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
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
