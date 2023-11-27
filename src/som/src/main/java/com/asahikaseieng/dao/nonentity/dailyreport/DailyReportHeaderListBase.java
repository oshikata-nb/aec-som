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
public class DailyReportHeaderListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DailyReportHeaderListBase() {
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
	public static final String seq_COLUMN = "SEQ";
	/** COLUMNアノテーション */
	public static final String inTotal_COLUMN = "INSIDE_TOTAL";
	/** COLUMNアノテーション */
	public static final String outTotal_COLUMN = "OUTSIDE_TOTAL";
	/** COLUMNアノテーション */
	public static final String empTime_COLUMN = "EMPLOY_TIME";
	/** COLUMNアノテーション */
	public static final String indTime_COLUMN = "INDIRECT_TIME";
	
	
	/** COLUMNアノテーション */
	public static final String inTotalHhmm_COLUMN = "INSIDE_TOTAL_HHMM";
	/** COLUMNアノテーション */
	public static final String outTotalHhmm_COLUMN = "OUTSIDE_TOTAL_HHMM";
	/** COLUMNアノテーション */
	public static final String empTimeHhmm_COLUMN = "EMPLOY_TIME_HHMM";
	/** COLUMNアノテーション */
	public static final String indTimeHhmm_COLUMN = "INDIRECT_TIME_HHMM";

	
	/** COLUMNアノテーション */
	public static final String tantoNm_COLUMN = "TANTO_NM";
	/** COLUMNアノテーション */
	public static final String delFlg_COLUMN = "DELETE_FLG";

	//
	// インスタンスフィールド
	//
	private String line;
	private String date;
	private String tantoDiv;
	private String tantoCd;
	private String seq;
	private java.math.BigDecimal inTotal;
	private java.math.BigDecimal outTotal;
	private java.math.BigDecimal empTime;
	private java.math.BigDecimal indTime;
	
	private String inTotalHhmm;
	private String outTotalHhmm;
	private String empTimeHhmm;
	private String indTimeHhmm;
	
	// 付属
	private String tantoNm;
	private String delFlg;

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
	 * inTotal取得.
	 * @return inTotal
	 */
	public java.math.BigDecimal getInTotal() {
		return this.inTotal;
	}
	/**
	 * inTotal設定.
	 * @param inTotal inTotal
	 */
	public void setInTotal(final java.math.BigDecimal inTotal) {
		this.inTotal = inTotal;
	}

	/**
	 * outTotal取得.
	 * @return outTotal
	 */
	public java.math.BigDecimal getOutTotal() {
		return this.outTotal;
	}
	/**
	 * outTotal設定.
	 * @param outTotal outTotal
	 */
	public void setOutTotal(final java.math.BigDecimal outTotal) {
		this.outTotal = outTotal;
	}

	/**
	 * empTime取得.
	 * @return empTime
	 */
	public java.math.BigDecimal getEmpTime() {
		return this.empTime;
	}
	/**
	 * empTime設定.
	 * @param empTime empTime
	 */
	public void setEmpTime(final java.math.BigDecimal empTime) {
		this.empTime = empTime;
	}

	/**
	 * indTime取得.
	 * @return indTime
	 */
	public java.math.BigDecimal getIndTime() {
		return this.indTime;
	}
	/**
	 * indTime設定.
	 * @param indTime indTime
	 */
	public void setIndTime(final java.math.BigDecimal indTime) {
		this.indTime = indTime;
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
	 * delFlg取得.
	 * @return delFlg
	 */
	public String getDelFlg() {
		return this.delFlg;
	}
	/**
	 * delFlg設定.
	 * @param delFlg delFlg
	 */
	public void setDelFlg(final String delFlg) {
		this.delFlg = delFlg;
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
	 * inTotalHhmmを取得します。
	 * @return inTotalHhmm
	 */
	public String getInTotalHhmm() {
		return inTotalHhmm;
	}
	/**
	 * inTotalHhmmを設定します。
	 * @param inTotalHhmm inTotalHhmm
	 */
	public void setInTotalHhmm(String inTotalHhmm) {
		this.inTotalHhmm = inTotalHhmm;
	}
	/**
	 * outTotalHhmmを取得します。
	 * @return outTotalHhmm
	 */
	public String getOutTotalHhmm() {
		return outTotalHhmm;
	}
	/**
	 * outTotalHhmmを設定します。
	 * @param outTotalHhmm outTotalHhmm
	 */
	public void setOutTotalHhmm(String outTotalHhmm) {
		this.outTotalHhmm = outTotalHhmm;
	}
	/**
	 * empTimeHhmmを取得します。
	 * @return empTimeHhmm
	 */
	public String getEmpTimeHhmm() {
		return empTimeHhmm;
	}
	/**
	 * empTimeHhmmを設定します。
	 * @param empTimeHhmm empTimeHhmm
	 */
	public void setEmpTimeHhmm(String empTimeHhmm) {
		this.empTimeHhmm = empTimeHhmm;
	}
	/**
	 * indTimeHhmmを取得します。
	 * @return indTimeHhmm
	 */
	public String getIndTimeHhmm() {
		return indTimeHhmm;
	}
	/**
	 * indTimeHhmmを設定します。
	 * @param indTimeHhmm indTimeHhmm
	 */
	public void setIndTimeHhmm(String indTimeHhmm) {
		this.indTimeHhmm = indTimeHhmm;
	}
}
