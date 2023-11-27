/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 前後設備紐付けマスタ用データ格納クラス.
 *
 * @author tosco
 */
public class DirectionRecipePegResouceListBase implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionRecipePegResouceListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_PEG_RESOUCE";

	/** COLUMNアノテーション resouceCd */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション prevResouceCd */
	public static final String prevResouceCd_COLUMN = "PREV_RESOUCE_CD";

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

	/** 設備コード
 */
	private String resouceCd;

	/** SEQ */
	private BigDecimal seq;

	/** 前設備コード */
	private String prevResouceCd;

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
	 * 設備コード取得
	 * @return String 設備コード

	*/
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * 設備コード設定
	 * @param resouceCd 設備コード

	*/
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * SEQ取得
	 * @return BigDecimal SEQ
	*/
	public BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * SEQ設定
	 * @param seq SEQ
	*/
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * 前設備コード取得
	 * @return String 前設備コード

	*/
	public String getPrevResouceCd() {
		return this.prevResouceCd;
	}

	/**
	 * 前設備コード設定
	 * @param prevResouceCd 前設備コード

	*/
	public void setPrevResouceCd(final String prevResouceCd) {
		this.prevResouceCd = prevResouceCd;
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
