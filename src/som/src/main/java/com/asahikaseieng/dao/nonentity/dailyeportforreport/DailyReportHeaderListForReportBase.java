/*
 * Created on 2009/09/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyeportforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DailyReportHeaderListForReportクラス.
 * @author kanri-user
 */
public class DailyReportHeaderListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DailyReportHeaderListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション productionDate */
	public static final String productionDate_COLUMN = "PRODUCTION_DATE";

	/** COLUMNアノテーション tantoDivision */
	public static final String tantoDivision_COLUMN = "TANTO_DIVISION";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション insideTotal */
	public static final String insideTotal_COLUMN = "INSIDE_TOTAL";

	/** COLUMNアノテーション outsideTotal */
	public static final String outsideTotal_COLUMN = "OUTSIDE_TOTAL";

	/** COLUMNアノテーション employTime */
	public static final String employTime_COLUMN = "EMPLOY_TIME";

	/** COLUMNアノテーション indirectTime */
	public static final String indirectTime_COLUMN = "INDIRECT_TIME";
	
	
	/** COLUMNアノテーション */
	public static final String insideTotalHhmm_COLUMN = "INSIDE_TOTAL_HHMM";
	/** COLUMNアノテーション */
	public static final String outsideTotalHhmm_COLUMN = "OUTSIDE_TOTAL_HHMM";
	/** COLUMNアノテーション */
	public static final String employTimeHhmm_COLUMN = "EMPLOY_TIME_HHMM";
	/** COLUMNアノテーション */
	public static final String indirectTimeHhmm_COLUMN = "INDIRECT_TIME_HHMM";


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

	private String productionLine;

	private String productionLineName;

	private java.sql.Timestamp productionDate;

	private java.math.BigDecimal tantoDivision;

	private String tantoCd;

	private String tantoNm;

	private java.math.BigDecimal seq;

	private java.math.BigDecimal insideTotal;

	private java.math.BigDecimal outsideTotal;

	private java.math.BigDecimal employTime;

	private java.math.BigDecimal indirectTime;
	
	
	private String insideTotalHhmm;
	private String outsideTotalHhmm;
	private String employTimeHhmm;
	private String indirectTimeHhmm;


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
	 * productionLine取得.
	 * @return productionLine
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * productionLine設定.
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * productionLineName取得.
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * productionLineName設定.
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * productionDate取得.
	 * @return productionDate
	 */
	public java.sql.Timestamp getProductionDate() {
		return this.productionDate;
	}

	/**
	 * productionDate設定.
	 * @param productionDate productionDate
	 */
	public void setProductionDate(final java.sql.Timestamp productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * tantoDivision取得.
	 * @return tantoDivision
	 */
	public java.math.BigDecimal getTantoDivision() {
		return this.tantoDivision;
	}

	/**
	 * tantoDivision設定.
	 * @param tantoDivision tantoDivision
	 */
	public void setTantoDivision(final java.math.BigDecimal tantoDivision) {
		this.tantoDivision = tantoDivision;
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
	 * insideTotal取得.
	 * @return insideTotal
	 */
	public java.math.BigDecimal getInsideTotal() {
		return this.insideTotal;
	}

	/**
	 * insideTotal設定.
	 * @param insideTotal insideTotal
	 */
	public void setInsideTotal(final java.math.BigDecimal insideTotal) {
		this.insideTotal = insideTotal;
	}

	/**
	 * outsideTotal取得.
	 * @return outsideTotal
	 */
	public java.math.BigDecimal getOutsideTotal() {
		return this.outsideTotal;
	}

	/**
	 * outsideTotal設定.
	 * @param outsideTotal outsideTotal
	 */
	public void setOutsideTotal(final java.math.BigDecimal outsideTotal) {
		this.outsideTotal = outsideTotal;
	}

	/**
	 * employTime取得.
	 * @return employTime
	 */
	public java.math.BigDecimal getEmployTime() {
		return this.employTime;
	}

	/**
	 * employTime設定.
	 * @param employTime employTime
	 */
	public void setEmployTime(final java.math.BigDecimal employTime) {
		this.employTime = employTime;
	}

	/**
	 * indirectTime取得.
	 * @return indirectTime
	 */
	public java.math.BigDecimal getIndirectTime() {
		return this.indirectTime;
	}

	/**
	 * indirectTime設定.
	 * @param indirectTime indirectTime
	 */
	public void setIndirectTime(final java.math.BigDecimal indirectTime) {
		this.indirectTime = indirectTime;
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

	/**
	 * insideTotalHhmmを取得します。
	 * @return insideTotalHhmm
	 */
	public String getInsideTotalHhmm() {
		return insideTotalHhmm;
	}

	/**
	 * insideTotalHhmmを設定します。
	 * @param insideTotalHhmm insideTotalHhmm
	 */
	public void setInsideTotalHhmm(String insideTotalHhmm) {
		this.insideTotalHhmm = insideTotalHhmm;
	}

	/**
	 * outsideTotalHhmmを取得します。
	 * @return outsideTotalHhmm
	 */
	public String getOutsideTotalHhmm() {
		return outsideTotalHhmm;
	}

	/**
	 * outsideTotalHhmmを設定します。
	 * @param outsideTotalHhmm outsideTotalHhmm
	 */
	public void setOutsideTotalHhmm(String outsideTotalHhmm) {
		this.outsideTotalHhmm = outsideTotalHhmm;
	}

	/**
	 * employTimeHhmmを取得します。
	 * @return employTimeHhmm
	 */
	public String getEmployTimeHhmm() {
		return employTimeHhmm;
	}

	/**
	 * employTimeHhmmを設定します。
	 * @param employTimeHhmm employTimeHhmm
	 */
	public void setEmployTimeHhmm(String employTimeHhmm) {
		this.employTimeHhmm = employTimeHhmm;
	}

	/**
	 * indirectTimeHhmmを取得します。
	 * @return indirectTimeHhmm
	 */
	public String getIndirectTimeHhmm() {
		return indirectTimeHhmm;
	}

	/**
	 * indirectTimeHhmmを設定します。
	 * @param indirectTimeHhmm indirectTimeHhmm
	 */
	public void setIndirectTimeHhmm(String indirectTimeHhmm) {
		this.indirectTimeHhmm = indirectTimeHhmm;
	}
}

