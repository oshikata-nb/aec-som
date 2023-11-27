/*
 * Created on 2021/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportfileloglist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderImportFileLogBaseクラス.
 * @author 
 */
public class OrderImportFileLogListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportFileLogListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション pkNo. */
	public static final String pkNo_COLUMN = "PK_NO";

	/** COLUMNアノテーション pkRow. */
	public static final String pkRow_COLUMN = "PK_ROW";
	
	/** COLUMNアノテーション procCd. */
	public static final String procCd_COLUMN = "PROC_CD";
	
	/** COLUMNアノテーション procDate. */
	public static final String procDate_COLUMN = "PROC_DATE";
	
	/** COLUMNアノテーション procSeq. */
	public static final String procSeq_COLUMN = "PROC_SEQ";
	
	/** COLUMNアノテーション rowNo. */
	public static final String rowNo_COLUMN = "ROW_NO";
	
	/** COLUMNアノテーション FLG. */
	public static final String flg_COLUMN = "FLG";
	
	/** COLUMNアノテーション MSG. */
	public static final String msg_COLUMN = "MSG";
	
	/** COLUMNアノテーション checkFlg. */
	public static final String checkFlg_COLUMN = "CHECK_FLG";

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

	private String pkNo;
	
	private BigDecimal pkRow;
	
	private String procCd;
	
	private Timestamp procDate;
	
	private BigDecimal procSeq;
	
	private BigDecimal rowNo;
	
	private BigDecimal flg;
	
	private String msg;
	
	private BigDecimal checkFlg;
	
	private Timestamp inputDate;

	private String inputorCd;

	private Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}

	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * pkRowを取得します。
	 * @return pkRow
	 */
	public BigDecimal getPkRow() {
		return pkRow;
	}

	/**
	 * pkRowを設定します。
	 * @param pkRow pkRow
	 */
	public void setPkRow(BigDecimal pkRow) {
		this.pkRow = pkRow;
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
	public void setProcCd(String procCd) {
		this.procCd = procCd;
	}

	/**
	 * procDateを取得します。
	 * @return procDate
	 */
	public Timestamp getProcDate() {
		return procDate;
	}

	/**
	 * procDateを設定します。
	 * @param procDate procDate
	 */
	public void setProcDate(Timestamp procDate) {
		this.procDate = procDate;
	}

	/**
	 * procSeqを取得します。
	 * @return procSeq
	 */
	public BigDecimal getProcSeq() {
		return procSeq;
	}

	/**
	 * procSeqを設定します。
	 * @param procSeq procSeq
	 */
	public void setProcSeq(BigDecimal procSeq) {
		this.procSeq = procSeq;
	}

	/**
	 * rowNoを取得します。
	 * @return rowNo
	 */
	public BigDecimal getRowNo() {
		return rowNo;
	}

	/**
	 * rowNoを設定します。
	 * @param rowNo rowNo
	 */
	public void setRowNo(BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * flgを取得します。
	 * @return flg
	 */
	public BigDecimal getFlg() {
		return flg;
	}

	/**
	 * flgを設定します。
	 * @param flg flg
	 */
	public void setFlg(BigDecimal flg) {
		this.flg = flg;
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
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * checkFlgを取得します。
	 * @return checkFlg
	 */
	public BigDecimal getCheckFlg() {
		return checkFlg;
	}

	/**
	 * checkFlgを設定します。
	 * @param checkFlg checkFlg
	 */
	public void setCheckFlg(BigDecimal checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(String updatorCd) {
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
