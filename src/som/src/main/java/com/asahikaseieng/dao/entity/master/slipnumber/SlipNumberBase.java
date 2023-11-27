/*
 * Created on Thu Jan 22 19:42:04 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.slipnumber;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SlipNumberBaseクラス.
 * @author kanri-user
 */
public class SlipNumberBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipNumberBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SLIP_NUMBER";


//	/** IDアノテーション key. */
//	public static final String key_ID = "assigned";

	/** COLUMNアノテーション key. */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション fixedChar. */
	public static final String fixedChar_COLUMN = "FIXED_CHAR";

	/** COLUMNアノテーション fixedCharNm. */
	public static final String fixedCharNm_COLUMN = "FIXED_CHAR_NM";

	/** COLUMNアノテーション minConsecutiveNo. */
	public static final String minConsecutiveNo_COLUMN = "MIN_CONSECUTIVE_NO";

	/** COLUMNアノテーション maxConsecutiveNo. */
	public static final String maxConsecutiveNo_COLUMN = "MAX_CONSECUTIVE_NO";

	/** COLUMNアノテーション currentConsecutiveNo. */
	public static final String currentConsecutiveNo_COLUMN = "CURRENT_CONSECUTIVE_NO";

	/** COLUMNアノテーション companyCd. */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	/** COLUMNアノテーション centralBranchCd. */
	public static final String centralBranchCd_COLUMN = "CENTRAL_BRANCH_CD";

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

	private java.math.BigDecimal key;

	private java.math.BigDecimal fixedChar;

	private String fixedCharNm;

	private java.math.BigDecimal minConsecutiveNo;

	private java.math.BigDecimal maxConsecutiveNo;

	private java.math.BigDecimal currentConsecutiveNo;

	private String companyCd;

	private String centralBranchCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * key取得.
	 * @return key
	 */
	public java.math.BigDecimal getKey() {
		return this.key;
	}

	/**
	 * key設定.
	 * @param key key
	 */
	public void setKey(final java.math.BigDecimal key) {
		this.key = key;
	}

	/**
	 * fixedChar取得.
	 * @return fixedChar
	 */
	public java.math.BigDecimal getFixedChar() {
		return this.fixedChar;
	}

	/**
	 * fixedChar設定.
	 * @param fixedChar fixedChar
	 */
	public void setFixedChar(final java.math.BigDecimal fixedChar) {
		this.fixedChar = fixedChar;
	}

	/**
	 * fixedCharNm取得.
	 * @return fixedCharNm
	 */
	public String getFixedCharNm() {
		return this.fixedCharNm;
	}

	/**
	 * fixedCharNm設定.
	 * @param fixedCharNm fixedCharNm
	 */
	public void setFixedCharNm(final String fixedCharNm) {
		this.fixedCharNm = fixedCharNm;
	}

	/**
	 * minConsecutiveNo取得.
	 * @return minConsecutiveNo
	 */
	public java.math.BigDecimal getMinConsecutiveNo() {
		return this.minConsecutiveNo;
	}

	/**
	 * minConsecutiveNo設定.
	 * @param minConsecutiveNo minConsecutiveNo
	 */
	public void setMinConsecutiveNo(final java.math.BigDecimal minConsecutiveNo) {
		this.minConsecutiveNo = minConsecutiveNo;
	}

	/**
	 * maxConsecutiveNo取得.
	 * @return maxConsecutiveNo
	 */
	public java.math.BigDecimal getMaxConsecutiveNo() {
		return this.maxConsecutiveNo;
	}

	/**
	 * maxConsecutiveNo設定.
	 * @param maxConsecutiveNo maxConsecutiveNo
	 */
	public void setMaxConsecutiveNo(final java.math.BigDecimal maxConsecutiveNo) {
		this.maxConsecutiveNo = maxConsecutiveNo;
	}

	/**
	 * currentConsecutiveNo取得.
	 * @return currentConsecutiveNo
	 */
	public java.math.BigDecimal getCurrentConsecutiveNo() {
		return this.currentConsecutiveNo;
	}

	/**
	 * currentConsecutiveNo設定.
	 * @param currentConsecutiveNo currentConsecutiveNo
	 */
	public void setCurrentConsecutiveNo(final java.math.BigDecimal currentConsecutiveNo) {
		this.currentConsecutiveNo = currentConsecutiveNo;
	}

	/**
	 * companyCd取得.
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return this.companyCd;
	}

	/**
	 * companyCd設定.
	 * @param companyCd companyCd
	 */
	public void setCompanyCd(final String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * centralBranchCd取得.
	 * @return centralBranchCd
	 */
	public String getCentralBranchCd() {
		return this.centralBranchCd;
	}

	/**
	 * centralBranchCd設定.
	 * @param centralBranchCd centralBranchCd
	 */
	public void setCentralBranchCd(final String centralBranchCd) {
		this.centralBranchCd = centralBranchCd;
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
