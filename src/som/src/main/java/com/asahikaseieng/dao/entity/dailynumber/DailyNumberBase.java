/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.entity.dailynumber;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 日毎発番管理テーブルデータ格納クラス.
 * 
 * @author tosco
 */
public class DailyNumberBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DailyNumberBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "DAILY_NUMBER";

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション datekey */
	public static final String datekey_COLUMN = "DATEKEY";

	/** COLUMNアノテーション fixedChar */
	public static final String fixedChar_COLUMN = "FIXED_CHAR";

	/** COLUMNアノテーション fixedCharNm */
	public static final String fixedCharNm_COLUMN = "FIXED_CHAR_NM";

	/** COLUMNアノテーション minConsecutiveNo */
	public static final String minConsecutiveNo_COLUMN = "MIN_CONSECUTIVE_NO";

	/** COLUMNアノテーション maxConsecutiveNo */
	public static final String maxConsecutiveNo_COLUMN = "MAX_CONSECUTIVE_NO";

	/** COLUMNアノテーション currentConsecutiveNo */
	public static final String currentConsecutiveNo_COLUMN = "CURRENT_CONSECUTIVE_NO";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** ＫＥＹコード */
	private String key;

	/** 西暦８桁 */
	private String datekey;

	/** 固定文字数 */
	private BigDecimal fixedChar;

	/** 固定文字 */
	private String fixedCharNm;

	/** ＭＩＮ連番 */
	private BigDecimal minConsecutiveNo;

	/** ＭＡＸ連番 */
	private BigDecimal maxConsecutiveNo;

	/** 現在連番 */
	private BigDecimal currentConsecutiveNo;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * ＫＥＹコード取得
	 * @return String ＫＥＹコード
	*/
	public String getKey() {
		return this.key;
	}

	/**
	 * ＫＥＹコード設定
	 * @param key ＫＥＹコード
	*/
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * 西暦８桁取得
	 * @return String 西暦８桁
	*/
	public String getDatekey() {
		return this.datekey;
	}

	/**
	 * 西暦８桁設定
	 * @param datekey 西暦８桁
	*/
	public void setDatekey(final String datekey) {
		this.datekey = datekey;
	}

	/**
	 * 固定文字数取得
	 * @return BigDecimal 固定文字数
	*/
	public BigDecimal getFixedChar() {
		return this.fixedChar;
	}

	/**
	 * 固定文字数設定
	 * @param fixedChar 固定文字数
	*/
	public void setFixedChar(final BigDecimal fixedChar) {
		this.fixedChar = fixedChar;
	}

	/**
	 * 固定文字取得
	 * @return String 固定文字
	*/
	public String getFixedCharNm() {
		return this.fixedCharNm;
	}

	/**
	 * 固定文字設定
	 * @param fixedCharNm 固定文字
	*/
	public void setFixedCharNm(final String fixedCharNm) {
		this.fixedCharNm = fixedCharNm;
	}

	/**
	 * ＭＩＮ連番取得
	 * @return BigDecimal ＭＩＮ連番
	*/
	public BigDecimal getMinConsecutiveNo() {
		return this.minConsecutiveNo;
	}

	/**
	 * ＭＩＮ連番設定
	 * @param minConsecutiveNo ＭＩＮ連番
	*/
	public void setMinConsecutiveNo(final BigDecimal minConsecutiveNo) {
		this.minConsecutiveNo = minConsecutiveNo;
	}

	/**
	 * ＭＡＸ連番取得
	 * @return BigDecimal ＭＡＸ連番
	*/
	public BigDecimal getMaxConsecutiveNo() {
		return this.maxConsecutiveNo;
	}

	/**
	 * ＭＡＸ連番設定
	 * @param maxConsecutiveNo ＭＡＸ連番
	*/
	public void setMaxConsecutiveNo(final BigDecimal maxConsecutiveNo) {
		this.maxConsecutiveNo = maxConsecutiveNo;
	}

	/**
	 * 現在連番取得
	 * @return BigDecimal 現在連番
	*/
	public BigDecimal getCurrentConsecutiveNo() {
		return this.currentConsecutiveNo;
	}

	/**
	 * 現在連番設定
	 * @param currentConsecutiveNo 現在連番
	*/
	public void setCurrentConsecutiveNo(final BigDecimal currentConsecutiveNo) {
		this.currentConsecutiveNo = currentConsecutiveNo;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
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
