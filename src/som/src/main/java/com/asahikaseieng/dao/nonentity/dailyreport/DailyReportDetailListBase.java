/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DailyReportHeaderListクラス.
 * @author fml
 */
public class DailyReportDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DailyReportDetailListBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション */
	public static final String line_COLUMN = "PRODUCTION_LINE";
	/** COLUMNアノテーション */
	public static final String date_COLUMN = "PRODUCTION_DATE";
	/** COLUMNアノテーション */
	public static final String tantoDiv_COLUMN = "TANTO_DIVISION";
	/** COLUMNアノテーション */
	public static final String tantoCd_COLUMN = "TANTO_CD";
	/** COLUMNアノテーション */
	public static final String directionDiv_COLUMN = "DIRECTION_DIVISION";
	/** COLUMNアノテーション */
	public static final String directionNo_COLUMN = "DIRECTION_NO";
	/** COLUMNアノテーション */
	public static final String jobTime_COLUMN = "JOB_TIME";
	
	/** COLUMNアノテーション */
	public static final String jobTimeHhmm_COLUMN = "JOB_TIME_HHMM";
	
	
	/** COLUMNアノテーション */
	public static final String seq_COLUMN = "SEQ";
	/** COLUMNアノテーション */
	public static final String itemNm_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション */
	public static final String subTotal_COLUMN = "SUB_TOTAL";
	/** COLUMNアノテーション */
	public static final String areaFlg_COLUMN = "AREA_FLG";

	//
	// インスタンスフィールド
	//
	private String line;
	private String date;
	private String tantoDiv;
	private String tantoCd;
	private String directionDiv;
	private String directionNo;
	private java.math.BigDecimal jobTime;
	
	private String jobTimeHhmm;
	private String seq;
	private String itemNm;

	private java.math.BigDecimal subTotal;
	private String subTotalHhmm;
	private String areaFlg;

	//
	// インスタンスメソッド
	//
	/**
	 * line取得.
	 * @return line
	 */
	public String getLine() {
		return this.line;
	}
	/**
	 * line設定.
	 * @param line line
	 */
	public void setLine(final String line) {
		this.line = line;
	}

	/**
	 * date取得.
	 * @return date
	 */
	public String getDate() {
		return this.date;
	}
	/**
	 * date設定.
	 * @param date date
	 */
	public void setDate(final String date) {
		this.date = date;
	}

	/**
	 * tantoDiv取得.
	 * @return date
	 */
	public String getTantoDiv() {
		return this.tantoDiv;
	}
	/**
	 * tantoDiv設定.
	 * @param tantoDiv tantoDiv
	 */
	public void setTantoDiv(final String tantoDiv) {
		this.tantoDiv = tantoDiv;
	}

	/**
	 * tanotCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}
	/**
	 * tanotCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * directionDiv取得.
	 * @return directionDiv
	 */
	public String getDirectionDiv() {
		return this.directionDiv;
	}
	/**
	 * directionDiv設定.
	 * @param directionDiv directionDiv
	 */
	public void setDirectionDiv(final String directionDiv) {
		this.directionDiv = directionDiv;
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
	 * seq取得.
	 * @return seq
	 */
	public String getSeq() {
		return this.seq;
	}
	/**
	 * seq設定.
	 * @param seq seq
	 */
	public void setSeq(final String seq) {
		this.seq = seq;
	}

	/**
	 * itemNm取得.
	 * @return itemNm
	 */
	public String getItemNm() {
		return this.itemNm;
	}
	/**
	 * itemNm設定.
	 * @param itemNm itemNm
	 */
	public void setItemNm(final String itemNm) {
		this.itemNm = itemNm;
	}


	/**
	 * subTotal取得.
	 * @return subTotal BigDecimal
	 */
	public java.math.BigDecimal getSubTotal() {
		return this.subTotal;
	}
	/**
	 * subTotal設定.
	 * @param subTotal BigDecimal
	 */
	public void setSubTotal(final java.math.BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * areaFlg取得.
	 * @return areaFlg String
	 */
	public String getAreaFlg() {
		return this.areaFlg;
	}
	/**
	 * areaFlg設定.
	 * @param areaFlg String
	 */
	public void setAreaFlg(final String areaFlg) {
		this.areaFlg = areaFlg;
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
	/**
	 * subTotalHhmmを取得します。
	 * @return subTotalHhmm
	 */
	public String getSubTotalHhmm() {
		return subTotalHhmm;
	}
	/**
	 * subTotalHhmmを設定します。
	 * @param subTotalHhmm subTotalHhmm
	 */
	public void setSubTotalHhmm(String subTotalHhmm) {
		this.subTotalHhmm = subTotalHhmm;
	}

}
