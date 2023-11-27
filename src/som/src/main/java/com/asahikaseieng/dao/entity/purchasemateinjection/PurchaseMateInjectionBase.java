/*
 * Created on Thu May 07 15:46:23 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.purchasemateinjection;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PurchaseMateInjectionBaseクラス.
 * @author kanri-user
 */
public class PurchaseMateInjectionBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseMateInjectionBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PURCHASE_MATE_INJECTION";


//	/** IDアノテーション buySubcontractOrderNo. */
//	public static final String buySubcontractOrderNo_ID = "assigned";
//	/** IDアノテーション lineNo. */
//	public static final String lineNo_ID = "assigned";
//	/** IDアノテーション recipeId. */
//	public static final String recipeId_ID = "assigned";
//	/** IDアノテーション seq. */
//	public static final String seq_ID = "assigned";
//	/** IDアノテーション stepNo. */
//	public static final String stepNo_ID = "assigned";

	/** COLUMNアノテーション buySubcontractOrderNo. */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション recipeId. */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd. */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion. */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション stepNo. */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション lineNo. */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション seq. */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション manufacturerLotNo. */
	public static final String manufacturerLotNo_COLUMN = "MANUFACTURER_LOT_NO";

	/** COLUMNアノテーション qty. */
	public static final String qty_COLUMN = "QTY";

	/** COLUMNアノテーション stockpdQty. */
	public static final String stockpdQty_COLUMN = "STOCKPD_QTY";

	/** COLUMNアノテーション resultQty. */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション sampleQty. */
	public static final String sampleQty_COLUMN = "SAMPLE_QTY";

	/** COLUMNアノテーション lossQty. */
	public static final String lossQty_COLUMN = "LOSS_QTY";

	/** COLUMNアノテーション defectQty. */
	public static final String defectQty_COLUMN = "DEFECT_QTY";

	/** COLUMNアノテーション adjustQty. */
	public static final String adjustQty_COLUMN = "ADJUST_QTY";

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

	private String buySubcontractOrderNo;

	private java.math.BigDecimal recipeId;

	private String recipeCd;

	private java.math.BigDecimal recipeVersion;

	private java.math.BigDecimal stepNo;

	private java.math.BigDecimal lineNo;

	private String itemCd;

	private java.math.BigDecimal seq;

	private String lotNo;

	private String locationCd;

	private String manufacturerLotNo;

	private java.math.BigDecimal qty;

	private java.math.BigDecimal stockpdQty;

	private java.math.BigDecimal resultQty;

	private java.math.BigDecimal sampleQty;

	private java.math.BigDecimal lossQty;

	private java.math.BigDecimal defectQty;

	private java.math.BigDecimal adjustQty;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * buySubcontractOrderNo取得.
	 * @return buySubcontractOrderNo
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * buySubcontractOrderNo設定.
	 * @param buySubcontractOrderNo buySubcontractOrderNo
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * recipeId取得.
	 * @return recipeId
	 */
	public java.math.BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * recipeId設定.
	 * @param recipeId recipeId
	 */
	public void setRecipeId(final java.math.BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * recipeCd取得.
	 * @return recipeCd
	 */
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * recipeCd設定.
	 * @param recipeCd recipeCd
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * recipeVersion取得.
	 * @return recipeVersion
	 */
	public java.math.BigDecimal getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * recipeVersion設定.
	 * @param recipeVersion recipeVersion
	 */
	public void setRecipeVersion(final java.math.BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * stepNo取得.
	 * @return stepNo
	 */
	public java.math.BigDecimal getStepNo() {
		return this.stepNo;
	}

	/**
	 * stepNo設定.
	 * @param stepNo stepNo
	 */
	public void setStepNo(final java.math.BigDecimal stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * lineNo取得.
	 * @return lineNo
	 */
	public java.math.BigDecimal getLineNo() {
		return this.lineNo;
	}

	/**
	 * lineNo設定.
	 * @param lineNo lineNo
	 */
	public void setLineNo(final java.math.BigDecimal lineNo) {
		this.lineNo = lineNo;
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
	 * manufacturerLotNo取得.
	 * @return manufacturerLotNo
	 */
	public String getManufacturerLotNo() {
		return this.manufacturerLotNo;
	}

	/**
	 * manufacturerLotNo設定.
	 * @param manufacturerLotNo manufacturerLotNo
	 */
	public void setManufacturerLotNo(final String manufacturerLotNo) {
		this.manufacturerLotNo = manufacturerLotNo;
	}

	/**
	 * qty取得.
	 * @return qty
	 */
	public java.math.BigDecimal getQty() {
		return this.qty;
	}

	/**
	 * qty設定.
	 * @param qty qty
	 */
	public void setQty(final java.math.BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * stockpdQty取得.
	 * @return stockpdQty
	 */
	public java.math.BigDecimal getStockpdQty() {
		return this.stockpdQty;
	}

	/**
	 * stockpdQty設定.
	 * @param stockpdQty stockpdQty
	 */
	public void setStockpdQty(final java.math.BigDecimal stockpdQty) {
		this.stockpdQty = stockpdQty;
	}

	/**
	 * resultQty取得.
	 * @return resultQty
	 */
	public java.math.BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * resultQty設定.
	 * @param resultQty resultQty
	 */
	public void setResultQty(final java.math.BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * sampleQty取得.
	 * @return sampleQty
	 */
	public java.math.BigDecimal getSampleQty() {
		return this.sampleQty;
	}

	/**
	 * sampleQty設定.
	 * @param sampleQty sampleQty
	 */
	public void setSampleQty(final java.math.BigDecimal sampleQty) {
		this.sampleQty = sampleQty;
	}

	/**
	 * lossQty取得.
	 * @return lossQty
	 */
	public java.math.BigDecimal getLossQty() {
		return this.lossQty;
	}

	/**
	 * lossQty設定.
	 * @param lossQty lossQty
	 */
	public void setLossQty(final java.math.BigDecimal lossQty) {
		this.lossQty = lossQty;
	}

	/**
	 * defectQty取得.
	 * @return defectQty
	 */
	public java.math.BigDecimal getDefectQty() {
		return this.defectQty;
	}

	/**
	 * defectQty設定.
	 * @param defectQty defectQty
	 */
	public void setDefectQty(final java.math.BigDecimal defectQty) {
		this.defectQty = defectQty;
	}

	/**
	 * adjustQty取得.
	 * @return adjustQty
	 */
	public java.math.BigDecimal getAdjustQty() {
		return this.adjustQty;
	}

	/**
	 * adjustQty設定.
	 * @param adjustQty adjustQty
	 */
	public void setAdjustQty(final java.math.BigDecimal adjustQty) {
		this.adjustQty = adjustQty;
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
