/*
 * Created on Thu Feb 12 18:54:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dailyreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DailyReportBaseクラス.
 * @author kanri-user
 */
public class DailyReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DailyReportBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DAILY_REPORT";

	/** COLUMNアノテーション productionLine. */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション productionDate. */
	public static final String productionDate_COLUMN = "PRODUCTION_DATE";

	/** COLUMNアノテーション tantoDivision. */
	public static final String tantoDivision_COLUMN = "TANTO_DIVISION";

	/** COLUMNアノテーション tantoCd. */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション directionNo. */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション jobTime. */
	public static final String jobTime_COLUMN = "JOB_TIME";

	/** COLUMNアノテーション jobTime. */
	public static final String jobTimeHhmm_COLUMN = "JOB_TIME_HHMM";

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

	private String productionLine;

	private java.sql.Timestamp productionDate;

	private java.math.BigDecimal tantoDivision;

	private String tantoCd;

	private String directionNo;

	private java.math.BigDecimal jobTime;
	
	private String jobTimeHhmm;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

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
	 * jobTime取得.
	 * @return jobTime
	 */
	public java.math.BigDecimal getJobTime() {
		return this.jobTime;
	}

	/**
	 * jobTime設定.
	 * @param jobTime jobTime
	 */
	public void setJobTime(final java.math.BigDecimal jobTime) {
		this.jobTime = jobTime;
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

	/**
	 * jobTimeHhmmを取得します。
	 * @return jobTimeHhmm
	 */
	public String getJobTimeHhmm() {
		return jobTimeHhmm;
	}

	/**
	 * jobTimeHhmmを設定します。
	 * @param jobTimeHhmm jobTimeHhmm
	 */
	public void setJobTimeHhmm(String jobTimeHhmm) {
		this.jobTimeHhmm = jobTimeHhmm;
	}
}
