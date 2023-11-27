/*
 * Created on 2009/06/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repMaterialDirectionDetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepMaterialDirectionDetailクラス.
 * @author kanri-user
 */
public class RepMaterialDirectionDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepMaterialDirectionDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション lineNo */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション lineType */
	public static final String lineType_COLUMN = "LINE_TYPE";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション tonyu */
	public static final String tonyu_COLUMN = "TONYU";

	/** COLUMNアノテーション dataread */
	public static final String dataread_COLUMN = "DATAREAD";

	/** COLUMNアノテーション tonyusokudo */
	public static final String tonyusokudo_COLUMN = "TONYUSOKUDO";

	/** COLUMNアノテーション qty */
	public static final String qty_COLUMN = "QTY";

	/** COLUMNアノテーション stockpdQty */
	public static final String stockpdQty_COLUMN = "STOCKPD_QTY";

	/** COLUMNアノテーション resultQty */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション sampleQty */
	public static final String sampleQty_COLUMN = "SAMPLE_QTY";

	/** COLUMNアノテーション lossQty */
	public static final String lossQty_COLUMN = "LOSS_QTY";

	/** COLUMNアノテーション defectQty */
	public static final String defectQty_COLUMN = "DEFECT_QTY";

	/** COLUMNアノテーション adjustQty */
	public static final String adjustQty_COLUMN = "ADJUST_QTY";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション stepCondition */
	public static final String stepCondition_COLUMN = "STEP_CONDITION";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション nextLocationCd */
	public static final String nextLocationCd_COLUMN = "NEXT_LOCATION_CD";

	/** COLUMNアノテーション nextAfterLocationCd */
	public static final String nextAfterLocationCd_COLUMN = "NEXT_AFTER_LOCATION_CD";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション manufacturerLotNo */
	public static final String manufacturerLotNo_COLUMN = "MANUFACTURER_LOT_NO";

	/** COLUMNアノテーション fillQty */
	public static final String fillQty_COLUMN = "FILL_QTY";

	/** COLUMNアノテーション fillResultQty */
	public static final String fillResultQty_COLUMN = "FILL_RESULT_QTY";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション invLocationCd */
	public static final String invLocationCd_COLUMN = "INV_LOCATION_CD";

	/** COLUMNアノテーション invLotNo */
	public static final String invLotNo_COLUMN = "INV_LOT_NO";

	/** COLUMNアノテーション invInoutQty */
	public static final String invInoutQty_COLUMN = "INV_INOUT_QTY";

	/** COLUMNアノテーション invFraction */
	public static final String invFraction_COLUMN = "INV_FRACTION";

	/** COLUMNアノテーション invQty */
	public static final String invQty_COLUMN = "INV_QTY";

	/** COLUMNアノテーション planInoutQty */
	public static final String planInoutQty_COLUMN = "PLAN_INOUT_QTY";

	/** COLUMNアノテーション planFraction */
	public static final String planFraction_COLUMN = "PLAN_FRACTION";

	/** COLUMNアノテーション planQty */
	public static final String planQty_COLUMN = "PLAN_QTY";

	/** COLUMNアノテーション assignQty */
	public static final String assignQty_COLUMN = "ASSIGN_QTY";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal directionDivision;

	private String directionNo;

	private java.math.BigDecimal stepNo;

	private java.math.BigDecimal lineNo;

	private java.math.BigDecimal seq;

	private java.math.BigDecimal lineType;

	private String itemCd;

	private java.math.BigDecimal tonyu;

	private java.math.BigDecimal dataread;

	private java.math.BigDecimal tonyusokudo;

	private java.math.BigDecimal qty;

	private java.math.BigDecimal stockpdQty;

	private java.math.BigDecimal resultQty;

	private java.math.BigDecimal sampleQty;

	private java.math.BigDecimal lossQty;

	private java.math.BigDecimal defectQty;

	private java.math.BigDecimal adjustQty;

	private java.math.BigDecimal cost;

	private String stepCondition;

	private String notes;

	private String locationCd;

	private String nextLocationCd;

	private String nextAfterLocationCd;

	private String lotNo;

	private String manufacturerLotNo;

	private java.math.BigDecimal fillQty;

	private java.math.BigDecimal fillResultQty;

	private String remark;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String itemName;

	private String styleOfPacking;

	private String invLocationCd;

	private String invLotNo;

	private java.math.BigDecimal invInoutQty;

	private java.math.BigDecimal invFraction;

	private java.math.BigDecimal invQty;

	private java.math.BigDecimal planInoutQty;

	private java.math.BigDecimal planFraction;

	private java.math.BigDecimal planQty;

	private java.math.BigDecimal assignQty;

	//
	// インスタンスメソッド
	//

	/**
	 * directionDivision取得.
	 * @return directionDivision
	 */
	public java.math.BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * directionDivision設定.
	 * @param directionDivision directionDivision
	 */
	public void setDirectionDivision(final java.math.BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * directionNo取得.
	 * @return directionNo
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * directionNo設定.
	 * @param directionNo directionNo
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
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
	 * lineType取得.
	 * @return lineType
	 */
	public java.math.BigDecimal getLineType() {
		return this.lineType;
	}

	/**
	 * lineType設定.
	 * @param lineType lineType
	 */
	public void setLineType(final java.math.BigDecimal lineType) {
		this.lineType = lineType;
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
	 * tonyu取得.
	 * @return tonyu
	 */
	public java.math.BigDecimal getTonyu() {
		return this.tonyu;
	}

	/**
	 * tonyu設定.
	 * @param tonyu tonyu
	 */
	public void setTonyu(final java.math.BigDecimal tonyu) {
		this.tonyu = tonyu;
	}

	/**
	 * dataread取得.
	 * @return dataread
	 */
	public java.math.BigDecimal getDataread() {
		return this.dataread;
	}

	/**
	 * dataread設定.
	 * @param dataread dataread
	 */
	public void setDataread(final java.math.BigDecimal dataread) {
		this.dataread = dataread;
	}

	/**
	 * tonyusokudo取得.
	 * @return tonyusokudo
	 */
	public java.math.BigDecimal getTonyusokudo() {
		return this.tonyusokudo;
	}

	/**
	 * tonyusokudo設定.
	 * @param tonyusokudo tonyusokudo
	 */
	public void setTonyusokudo(final java.math.BigDecimal tonyusokudo) {
		this.tonyusokudo = tonyusokudo;
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
	 * cost取得.
	 * @return cost
	 */
	public java.math.BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * cost設定.
	 * @param cost cost
	 */
	public void setCost(final java.math.BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * stepCondition取得.
	 * @return stepCondition
	 */
	public String getStepCondition() {
		return this.stepCondition;
	}

	/**
	 * stepCondition設定.
	 * @param stepCondition stepCondition
	 */
	public void setStepCondition(final String stepCondition) {
		this.stepCondition = stepCondition;
	}

	/**
	 * notes取得.
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * notes設定.
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
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
	 * nextLocationCd取得.
	 * @return nextLocationCd
	 */
	public String getNextLocationCd() {
		return this.nextLocationCd;
	}

	/**
	 * nextLocationCd設定.
	 * @param nextLocationCd nextLocationCd
	 */
	public void setNextLocationCd(final String nextLocationCd) {
		this.nextLocationCd = nextLocationCd;
	}

	/**
	 * nextAfterLocationCd取得.
	 * @return nextAfterLocationCd
	 */
	public String getNextAfterLocationCd() {
		return this.nextAfterLocationCd;
	}

	/**
	 * nextAfterLocationCd設定.
	 * @param nextAfterLocationCd nextAfterLocationCd
	 */
	public void setNextAfterLocationCd(final String nextAfterLocationCd) {
		this.nextAfterLocationCd = nextAfterLocationCd;
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
	 * fillQty取得.
	 * @return fillQty
	 */
	public java.math.BigDecimal getFillQty() {
		return this.fillQty;
	}

	/**
	 * fillQty設定.
	 * @param fillQty fillQty
	 */
	public void setFillQty(final java.math.BigDecimal fillQty) {
		this.fillQty = fillQty;
	}

	/**
	 * fillResultQty取得.
	 * @return fillResultQty
	 */
	public java.math.BigDecimal getFillResultQty() {
		return this.fillResultQty;
	}

	/**
	 * fillResultQty設定.
	 * @param fillResultQty fillResultQty
	 */
	public void setFillResultQty(final java.math.BigDecimal fillResultQty) {
		this.fillResultQty = fillResultQty;
	}

	/**
	 * remark取得.
	 * @return remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * remark設定.
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
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
	 * invLocationCd取得.
	 * @return invLocationCd
	 */
	public String getInvLocationCd() {
		return this.invLocationCd;
	}

	/**
	 * invLocationCd設定.
	 * @param invLocationCd invLocationCd
	 */
	public void setInvLocationCd(final String invLocationCd) {
		this.invLocationCd = invLocationCd;
	}

	/**
	 * invLotNo取得.
	 * @return invLotNo
	 */
	public String getInvLotNo() {
		return this.invLotNo;
	}

	/**
	 * invLotNo設定.
	 * @param invLotNo invLotNo
	 */
	public void setInvLotNo(final String invLotNo) {
		this.invLotNo = invLotNo;
	}

	/**
	 * invInoutQty取得.
	 * @return invInoutQty
	 */
	public java.math.BigDecimal getInvInoutQty() {
		return this.invInoutQty;
	}

	/**
	 * invInoutQty設定.
	 * @param invInoutQty invInoutQty
	 */
	public void setInvInoutQty(final java.math.BigDecimal invInoutQty) {
		this.invInoutQty = invInoutQty;
	}

	/**
	 * invFraction取得.
	 * @return invFraction
	 */
	public java.math.BigDecimal getInvFraction() {
		return this.invFraction;
	}

	/**
	 * invFraction設定.
	 * @param invFraction invFraction
	 */
	public void setInvFraction(final java.math.BigDecimal invFraction) {
		this.invFraction = invFraction;
	}

	/**
	 * invQty取得.
	 * @return invQty
	 */
	public java.math.BigDecimal getInvQty() {
		return this.invQty;
	}

	/**
	 * invQty設定.
	 * @param invQty invQty
	 */
	public void setInvQty(final java.math.BigDecimal invQty) {
		this.invQty = invQty;
	}

	/**
	 * planInoutQty取得.
	 * @return planInoutQty
	 */
	public java.math.BigDecimal getPlanInoutQty() {
		return this.planInoutQty;
	}

	/**
	 * planInoutQty設定.
	 * @param planInoutQty planInoutQty
	 */
	public void setPlanInoutQty(final java.math.BigDecimal planInoutQty) {
		this.planInoutQty = planInoutQty;
	}

	/**
	 * planFraction取得.
	 * @return planFraction
	 */
	public java.math.BigDecimal getPlanFraction() {
		return this.planFraction;
	}

	/**
	 * planFraction設定.
	 * @param planFraction planFraction
	 */
	public void setPlanFraction(final java.math.BigDecimal planFraction) {
		this.planFraction = planFraction;
	}

	/**
	 * planQty取得.
	 * @return planQty
	 */
	public java.math.BigDecimal getPlanQty() {
		return this.planQty;
	}

	/**
	 * planQty設定.
	 * @param planQty planQty
	 */
	public void setPlanQty(final java.math.BigDecimal planQty) {
		this.planQty = planQty;
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

