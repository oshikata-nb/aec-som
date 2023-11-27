/*
 * Created on 2022/08/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionfileimport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DirectionHeaderTempBaseクラス.
 * @author 
 */
public class DirectionHeaderTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionHeaderTempBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DIRECTION_HEADER_TEMP";


	/** COLUMNアノテーション tempNo. */
	public static final String tempNo_COLUMN = "TEMP_NO";

	/** COLUMNアノテーション rowNumber. */
	public static final String rowNumber_COLUMN = "ROW_NUMBER";

	/** COLUMNアノテーション directionDivision. */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo. */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション resultSdate. */
	public static final String resultSdate_COLUMN = "RESULT_SDATE";

	/** COLUMNアノテーション resultEdate. */
	public static final String resultEdate_COLUMN = "RESULT_EDATE";

	/** COLUMNアノテーション steritSdate. */
	public static final String steritSdate_COLUMN = "STERIT_SDATE";

	/** COLUMNアノテーション steritEdate. */
	public static final String steritEdate_COLUMN = "STERIT_EDATE";

	/** COLUMNアノテーション remark. */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes. */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション seizotantocode. */
	public static final String seizotantocode_COLUMN = "SEIZOTANTOCODE";

	/** COLUMNアノテーション senjotantocode. */
	public static final String senjotantocode_COLUMN = "SENJOTANTOCODE";

	/** COLUMNアノテーション mekkintantocode. */
	public static final String mekkintantocode_COLUMN = "MEKKINTANTOCODE";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String tempNo;

	private java.math.BigDecimal rowNumber;

	private java.math.BigDecimal directionDivision;

	private String directionNo;

	private String lotNo;

	private java.sql.Timestamp resultSdate;

	private java.sql.Timestamp resultEdate;

	private java.sql.Timestamp steritSdate;

	private java.sql.Timestamp steritEdate;

	private String remark;

	private String notes;

	private String seizotantocode;

	private String senjotantocode;

	private String mekkintantocode;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * tempNoを取得.
	 * @return tempNo
	 */
	public String getTempNo() {
		return tempNo;
	}

	/**
	 * tempNoを設定.
	 * @param tempNo tempNo
	 */
	public void setTempNo(String tempNo) {
		this.tempNo = tempNo;
	}

	/**
	 * rowNumberを取得.
	 * @return rowNumber
	 */
	public java.math.BigDecimal getRowNumber() {
		return rowNumber;
	}

	/**
	 * rowNumberを設定.
	 * @param rowNumber rowNumber
	 */
	public void setRowNumber(java.math.BigDecimal rowNumber) {
		this.rowNumber = rowNumber;
	}

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
	 * resultSdate取得.
	 * @return resultSdate
	 */
	public java.sql.Timestamp getResultSdate() {
		return this.resultSdate;
	}

	/**
	 * resultSdate設定.
	 * @param resultSdate resultSdate
	 */
	public void setResultSdate(final java.sql.Timestamp resultSdate) {
		this.resultSdate = resultSdate;
	}

	/**
	 * resultEdate取得.
	 * @return resultEdate
	 */
	public java.sql.Timestamp getResultEdate() {
		return this.resultEdate;
	}

	/**
	 * resultEdate設定.
	 * @param resultEdate resultEdate
	 */
	public void setResultEdate(final java.sql.Timestamp resultEdate) {
		this.resultEdate = resultEdate;
	}

	/**
	 * steritSdate取得.
	 * @return steritSdate
	 */
	public java.sql.Timestamp getSteritSdate() {
		return this.steritSdate;
	}

	/**
	 * steritSdate設定.
	 * @param steritSdate steritSdate
	 */
	public void setSteritSdate(final java.sql.Timestamp steritSdate) {
		this.steritSdate = steritSdate;
	}

	/**
	 * steritEdate取得.
	 * @return steritEdate
	 */
	public java.sql.Timestamp getSteritEdate() {
		return this.steritEdate;
	}

	/**
	 * steritEdate設定.
	 * @param steritEdate steritEdate
	 */
	public void setSteritEdate(final java.sql.Timestamp steritEdate) {
		this.steritEdate = steritEdate;
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
	 * seizotantocode取得.
	 * @return seizotantocode
	 */
	public String getSeizotantocode() {
		return this.seizotantocode;
	}

	/**
	 * seizotantocode設定.
	 * @param seizotantocode seizotantocode
	 */
	public void setSeizotantocode(final String seizotantocode) {
		this.seizotantocode = seizotantocode;
	}

	/**
	 * senjotantocode取得.
	 * @return senjotantocode
	 */
	public String getSenjotantocode() {
		return this.senjotantocode;
	}

	/**
	 * senjotantocode設定.
	 * @param senjotantocode senjotantocode
	 */
	public void setSenjotantocode(final String senjotantocode) {
		this.senjotantocode = senjotantocode;
	}

	/**
	 * mekkintantocode取得.
	 * @return mekkintantocode
	 */
	public String getMekkintantocode() {
		return this.mekkintantocode;
	}

	/**
	 * mekkintantocode設定.
	 * @param mekkintantocode mekkintantocode
	 */
	public void setMekkintantocode(final String mekkintantocode) {
		this.mekkintantocode = mekkintantocode;
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
