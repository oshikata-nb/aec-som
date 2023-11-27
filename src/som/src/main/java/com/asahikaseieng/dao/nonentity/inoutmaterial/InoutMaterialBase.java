/*
 * Created on 2009/11/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmaterial;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InoutMaterialtestクラス.
 * @author kanri-user
 */
public class InoutMaterialBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InoutMaterialBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション stockDivision */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション zenzanQty */
	public static final String zenzanQty_COLUMN = "ZENZAN_QTY";

	/** COLUMNアノテーション zenzanAmount */
	public static final String zenzanAmount_COLUMN = "ZENZAN_AMOUNT";

	/** COLUMNアノテーション tougetuUkeireQty */
	public static final String tougetuUkeireQty_COLUMN = "TOUGETU_UKEIRE_QTY";

	/** COLUMNアノテーション tougetuUkeireAmount */
	public static final String tougetuUkeireAmount_COLUMN = "TOUGETU_UKEIRE_AMOUNT";

	/** COLUMNアノテーション henpinQty */
	public static final String henpinQty_COLUMN = "HENPIN_QTY";

	/** COLUMNアノテーション henpinAmount */
	public static final String henpinAmount_COLUMN = "HENPIN_AMOUNT";

	/** COLUMNアノテーション ry401ZisyaQty */
	public static final String ry401ZisyaQty_COLUMN = "RY_401_ZISYA_QTY";

	/** COLUMNアノテーション ry401KaoQty */
	public static final String ry401KaoQty_COLUMN = "RY_401_KAO_QTY";

	/** COLUMNアノテーション ry402Qty */
	public static final String ry402Qty_COLUMN = "RY_402_QTY";

	/** COLUMNアノテーション ry403Qty */
	public static final String ry403Qty_COLUMN = "RY_403_QTY";

	/** COLUMNアノテーション ry404Qty */
	public static final String ry404Qty_COLUMN = "RY_404_QTY";

	/** COLUMNアノテーション ry405Qty */
	public static final String ry405Qty_COLUMN = "RY_405_QTY";

	/** COLUMNアノテーション ry406Qty */
	public static final String ry406Qty_COLUMN = "RY_406_QTY";

	/** COLUMNアノテーション ry407Qty */
	public static final String ry407Qty_COLUMN = "RY_407_QTY";

	/** COLUMNアノテーション ry408Qty */
	public static final String ry408Qty_COLUMN = "RY_408_QTY";

	/** COLUMNアノテーション ry409Qty */
	public static final String ry409Qty_COLUMN = "RY_409_QTY";

	/** COLUMNアノテーション ry410Qty */
	public static final String ry410Qty_COLUMN = "RY_410_QTY";

	/** COLUMNアノテーション ry999Qty */
	public static final String ry999Qty_COLUMN = "RY_999_QTY";

	/** COLUMNアノテーション ry302Qty */
	public static final String ry302Qty_COLUMN = "RY_302_QTY";

	/** COLUMNアノテーション ry302Amount */
	public static final String ry302Amount_COLUMN = "RY_302_AMOUNT";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private String stockDivision;

	private String typeDivision;

	private String itemName;

	private String styleOfPacking;

	private java.math.BigDecimal zenzanQty;

	private java.math.BigDecimal zenzanAmount;

	private java.math.BigDecimal tougetuUkeireQty;

	private java.math.BigDecimal tougetuUkeireAmount;

	private java.math.BigDecimal henpinQty;

	private java.math.BigDecimal henpinAmount;

	private java.math.BigDecimal ry401ZisyaQty;

	private java.math.BigDecimal ry401KaoQty;

	private java.math.BigDecimal ry402Qty;

	private java.math.BigDecimal ry403Qty;

	private java.math.BigDecimal ry404Qty;

	private java.math.BigDecimal ry405Qty;

	private java.math.BigDecimal ry406Qty;

	private java.math.BigDecimal ry407Qty;

	private java.math.BigDecimal ry408Qty;

	private java.math.BigDecimal ry409Qty;

	private java.math.BigDecimal ry410Qty;

	private java.math.BigDecimal ry999Qty;

	private java.math.BigDecimal ry302Qty;

	private java.math.BigDecimal ry302Amount;

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
	 * stockDivision取得.
	 * @return stockDivision
	 */
	public String getStockDivision() {
		return this.stockDivision;
	}

	/**
	 * stockDivision設定.
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final String stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * typeDivision取得.
	 * @return typeDivision
	 */
	public String getTypeDivision() {
		return this.typeDivision;
	}

	/**
	 * typeDivision設定.
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final String typeDivision) {
		this.typeDivision = typeDivision;
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
	 * zenzanQty取得.
	 * @return zenzanQty
	 */
	public java.math.BigDecimal getZenzanQty() {
		return this.zenzanQty;
	}

	/**
	 * zenzanQty設定.
	 * @param zenzanQty zenzanQty
	 */
	public void setZenzanQty(final java.math.BigDecimal zenzanQty) {
		this.zenzanQty = zenzanQty;
	}

	/**
	 * zenzanAmount取得.
	 * @return zenzanAmount
	 */
	public java.math.BigDecimal getZenzanAmount() {
		return this.zenzanAmount;
	}

	/**
	 * zenzanAmount設定.
	 * @param zenzanAmount zenzanAmount
	 */
	public void setZenzanAmount(final java.math.BigDecimal zenzanAmount) {
		this.zenzanAmount = zenzanAmount;
	}

	/**
	 * tougetuUkeireQty取得.
	 * @return tougetuUkeireQty
	 */
	public java.math.BigDecimal getTougetuUkeireQty() {
		return this.tougetuUkeireQty;
	}

	/**
	 * tougetuUkeireQty設定.
	 * @param tougetuUkeireQty tougetuUkeireQty
	 */
	public void setTougetuUkeireQty(final java.math.BigDecimal tougetuUkeireQty) {
		this.tougetuUkeireQty = tougetuUkeireQty;
	}

	/**
	 * tougetuUkeireAmount取得.
	 * @return tougetuUkeireAmount
	 */
	public java.math.BigDecimal getTougetuUkeireAmount() {
		return this.tougetuUkeireAmount;
	}

	/**
	 * tougetuUkeireAmount設定.
	 * @param tougetuUkeireAmount tougetuUkeireAmount
	 */
	public void setTougetuUkeireAmount(final java.math.BigDecimal tougetuUkeireAmount) {
		this.tougetuUkeireAmount = tougetuUkeireAmount;
	}

	/**
	 * henpinQty取得.
	 * @return henpinQty
	 */
	public java.math.BigDecimal getHenpinQty() {
		return this.henpinQty;
	}

	/**
	 * henpinQty設定.
	 * @param henpinQty henpinQty
	 */
	public void setHenpinQty(final java.math.BigDecimal henpinQty) {
		this.henpinQty = henpinQty;
	}

	/**
	 * henpinAmount取得.
	 * @return henpinAmount
	 */
	public java.math.BigDecimal getHenpinAmount() {
		return this.henpinAmount;
	}

	/**
	 * henpinAmount設定.
	 * @param henpinAmount henpinAmount
	 */
	public void setHenpinAmount(final java.math.BigDecimal henpinAmount) {
		this.henpinAmount = henpinAmount;
	}

	/**
	 * ry401ZisyaQty取得.
	 * @return ry401ZisyaQty
	 */
	public java.math.BigDecimal getRy401ZisyaQty() {
		return this.ry401ZisyaQty;
	}

	/**
	 * ry401ZisyaQty設定.
	 * @param ry401ZisyaQty ry401ZisyaQty
	 */
	public void setRy401ZisyaQty(final java.math.BigDecimal ry401ZisyaQty) {
		this.ry401ZisyaQty = ry401ZisyaQty;
	}

	/**
	 * ry401KaoQty取得.
	 * @return ry401KaoQty
	 */
	public java.math.BigDecimal getRy401KaoQty() {
		return this.ry401KaoQty;
	}

	/**
	 * ry401KaoQty設定.
	 * @param ry401KaoQty ry401KaoQty
	 */
	public void setRy401KaoQty(final java.math.BigDecimal ry401KaoQty) {
		this.ry401KaoQty = ry401KaoQty;
	}

	/**
	 * ry402Qty取得.
	 * @return ry402Qty
	 */
	public java.math.BigDecimal getRy402Qty() {
		return this.ry402Qty;
	}

	/**
	 * ry402Qty設定.
	 * @param ry402Qty ry402Qty
	 */
	public void setRy402Qty(final java.math.BigDecimal ry402Qty) {
		this.ry402Qty = ry402Qty;
	}

	/**
	 * ry403Qty取得.
	 * @return ry403Qty
	 */
	public java.math.BigDecimal getRy403Qty() {
		return this.ry403Qty;
	}

	/**
	 * ry403Qty設定.
	 * @param ry403Qty ry403Qty
	 */
	public void setRy403Qty(final java.math.BigDecimal ry403Qty) {
		this.ry403Qty = ry403Qty;
	}

	/**
	 * ry404Qty取得.
	 * @return ry404Qty
	 */
	public java.math.BigDecimal getRy404Qty() {
		return this.ry404Qty;
	}

	/**
	 * ry404Qty設定.
	 * @param ry404Qty ry404Qty
	 */
	public void setRy404Qty(final java.math.BigDecimal ry404Qty) {
		this.ry404Qty = ry404Qty;
	}

	/**
	 * ry405Qty取得.
	 * @return ry405Qty
	 */
	public java.math.BigDecimal getRy405Qty() {
		return this.ry405Qty;
	}

	/**
	 * ry405Qty設定.
	 * @param ry405Qty ry405Qty
	 */
	public void setRy405Qty(final java.math.BigDecimal ry405Qty) {
		this.ry405Qty = ry405Qty;
	}

	/**
	 * ry406Qty取得.
	 * @return ry406Qty
	 */
	public java.math.BigDecimal getRy406Qty() {
		return this.ry406Qty;
	}

	/**
	 * ry406Qty設定.
	 * @param ry406Qty ry406Qty
	 */
	public void setRy406Qty(final java.math.BigDecimal ry406Qty) {
		this.ry406Qty = ry406Qty;
	}

	/**
	 * ry407Qty取得.
	 * @return ry407Qty
	 */
	public java.math.BigDecimal getRy407Qty() {
		return this.ry407Qty;
	}

	/**
	 * ry407Qty設定.
	 * @param ry407Qty ry407Qty
	 */
	public void setRy407Qty(final java.math.BigDecimal ry407Qty) {
		this.ry407Qty = ry407Qty;
	}

	/**
	 * ry408Qty取得.
	 * @return ry408Qty
	 */
	public java.math.BigDecimal getRy408Qty() {
		return this.ry408Qty;
	}

	/**
	 * ry408Qty設定.
	 * @param ry408Qty ry408Qty
	 */
	public void setRy408Qty(final java.math.BigDecimal ry408Qty) {
		this.ry408Qty = ry408Qty;
	}

	/**
	 * ry409Qty取得.
	 * @return ry409Qty
	 */
	public java.math.BigDecimal getRy409Qty() {
		return this.ry409Qty;
	}

	/**
	 * ry409Qty設定.
	 * @param ry409Qty ry409Qty
	 */
	public void setRy409Qty(final java.math.BigDecimal ry409Qty) {
		this.ry409Qty = ry409Qty;
	}

	/**
	 * ry410Qty取得.
	 * @return ry410Qty
	 */
	public java.math.BigDecimal getRy410Qty() {
		return this.ry410Qty;
	}

	/**
	 * ry410Qty設定.
	 * @param ry410Qty ry410Qty
	 */
	public void setRy410Qty(final java.math.BigDecimal ry410Qty) {
		this.ry410Qty = ry410Qty;
	}

	/**
	 * ry999Qty取得.
	 * @return ry999Qty
	 */
	public java.math.BigDecimal getRy999Qty() {
		return this.ry999Qty;
	}

	/**
	 * ry999Qty設定.
	 * @param ry999Qty ry999Qty
	 */
	public void setRy999Qty(final java.math.BigDecimal ry999Qty) {
		this.ry999Qty = ry999Qty;
	}

	/**
	 * ry302Qty取得.
	 * @return ry302Qty
	 */
	public java.math.BigDecimal getRy302Qty() {
		return this.ry302Qty;
	}

	/**
	 * ry302Qty設定.
	 * @param ry302Qty ry302Qty
	 */
	public void setRy302Qty(final java.math.BigDecimal ry302Qty) {
		this.ry302Qty = ry302Qty;
	}

	/**
	 * ry302Amount取得.
	 * @return ry302Amount
	 */
	public java.math.BigDecimal getRy302Amount() {
		return this.ry302Amount;
	}

	/**
	 * ry302Amount設定.
	 * @param ry302Amount ry302Amount
	 */
	public void setRy302Amount(final java.math.BigDecimal ry302Amount) {
		this.ry302Amount = ry302Amount;
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

