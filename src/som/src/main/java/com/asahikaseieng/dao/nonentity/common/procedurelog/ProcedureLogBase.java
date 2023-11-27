/*
 * Created on 2014/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.procedurelog;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ログファイルの列定義
 * @author atts
 */
public class ProcedureLogBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション procCd */
	public static final String procCd_COLUMN = "PROC_CD";

	/** COLUMNアノテーション procDate */
	public static final String procDate_COLUMN = "PROC_DATE";

	/** COLUMNアノテーション procSeq */
	public static final String procSeq_COLUMN = "PROC_SEQ";

	/** COLUMNアノテーション flg */
	public static final String flg_COLUMN = "FLG";

	/** COLUMNアノテーション msg */
	public static final String msg_COLUMN = "MSG";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	// フィールド

	/**  */
	private String procCd;

	/**  */
	private java.sql.Timestamp procDate;

	/**  */
	private java.math.BigDecimal procSeq;

	/**  */
	private java.math.BigDecimal flg;

	/**  */
	private String msg;

	/**  */
	private String inputDate;

	/**
	 * コンストラクタ
	 */
	public ProcedureLogBase() {
	}

	/**
	 * flgを取得します。
	 * @return flg
	 */
	public java.math.BigDecimal getFlg() {
		return flg;
	}

	/**
	 * flgを設定します。
	 * @param flg flg
	 */
	public void setFlg(final java.math.BigDecimal flg) {
		this.flg = flg;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public String getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(final String inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * msgを取得します。
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * msgを設定します。
	 * @param msg msg
	 */
	public void setMsg(final String msg) {
		this.msg = msg;
	}

	/**
	 * procCdを取得します。
	 * @return procCd
	 */
	public String getProcCd() {
		return procCd;
	}

	/**
	 * procCdを設定します。
	 * @param procCd procCd
	 */
	public void setProcCd(final String procCd) {
		this.procCd = procCd;
	}

	/**
	 * procDateを取得します。
	 * @return procDate
	 */
	public java.sql.Timestamp getProcDate() {
		return procDate;
	}

	/**
	 * procDateを設定します。
	 * @param procDate procDate
	 */
	public void setProcDate(final java.sql.Timestamp procDate) {
		this.procDate = procDate;
	}

	/**
	 * procSeqを取得します。
	 * @return procSeq
	 */
	public java.math.BigDecimal getProcSeq() {
		return procSeq;
	}

	/**
	 * procSeqを設定します。
	 * @param procSeq procSeq
	 */
	public void setProcSeq(final java.math.BigDecimal procSeq) {
		this.procSeq = procSeq;
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
