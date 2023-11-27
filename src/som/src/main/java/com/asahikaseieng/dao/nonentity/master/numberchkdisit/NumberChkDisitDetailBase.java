/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.master.numberchkdisit;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 数値桁数チェックマスタ用データ格納クラス.
 *
 * @author tosco
 */
public class NumberChkDisitDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NumberChkDisitDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "NUMBER_CHKDISIT";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション maxLength */
	public static final String maxLength_COLUMN = "MAX_LENGTH";

	/** COLUMNアノテーション integerLength */
	public static final String integerLength_COLUMN = "INTEGER_LENGTH";

	/** COLUMNアノテーション smallnumLength */
	public static final String smallnumLength_COLUMN = "SMALLNUM_LENGTH";

	/** COLUMNアノテーション roundDivision */
	public static final String roundDivision_COLUMN = "ROUND_DIVISION";

	/** COLUMNアノテーション lowerLimit */
	public static final String lowerLimit_COLUMN = "LOWER_LIMIT";

	/** COLUMNアノテーション upperLimit */
	public static final String upperLimit_COLUMN = "UPPER_LIMIT";

	//
	// インスタンスフィールド
	//

	/** 区分  */
	private String unitDivision;

	/** SI/TS 自社の場合は半角1桁 */
	private String venderDivision;

	/** 取引先 自社の場合は半角1桁 */
	private String venderCd;

	/** 全体桁数（小数点、マイナス含む） */
	private BigDecimal maxLength;

	/** 整数部桁数 */
	private BigDecimal integerLength;

	/** 小数点以下桁数 */
	private BigDecimal smallnumLength;

	/** 端数区分 0:なし 1:切り捨て、2:四捨五入、3:切り上げ */
	private BigDecimal roundDivision;

	/** 下限値 */
	private BigDecimal lowerLimit;

	/** 上限値 */
	private BigDecimal upperLimit;


	//
	// インスタンスメソッド
	//

	/**
	 * 区分 取得
	 * @return String 区分
	*/
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * 区分 設定
	 * @param unitDivision 区分
	*/
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * SI/TS    自社の場合は半角1桁取得
	 * @return String SI/TS    自社の場合は半角1桁
	*/
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * SI/TS    自社の場合は半角1桁設定
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	*/
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * 取引先 自社の場合は半角1桁取得
	 * @return String 取引先 自社の場合は半角1桁
	*/
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 取引先 自社の場合は半角1桁設定
	 * @param venderCd 取引先 自社の場合は半角1桁
	*/
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 全体桁数（小数点、マイナス含む）取得
	 * @return BigDecimal 全体桁数（小数点、マイナス含む）
	*/
	public BigDecimal getMaxLength() {
		return this.maxLength;
	}

	/**
	 * 全体桁数（小数点、マイナス含む）設定
	 * @param maxLength 全体桁数（小数点、マイナス含む）
	*/
	public void setMaxLength(final BigDecimal maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * 整数部桁数取得
	 * @return BigDecimal 整数部桁数
	*/
	public BigDecimal getIntegerLength() {
		return this.integerLength;
	}

	/**
	 * 整数部桁数設定
	 * @param integerLength 整数部桁数
	*/
	public void setIntegerLength(final BigDecimal integerLength) {
		this.integerLength = integerLength;
	}

	/**
	 * 小数点以下桁数取得
	 * @return BigDecimal 小数点以下桁数
	*/
	public BigDecimal getSmallnumLength() {
		return this.smallnumLength;
	}

	/**
	 * 小数点以下桁数設定
	 * @param smallnumLength 小数点以下桁数
	*/
	public void setSmallnumLength(final BigDecimal smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * 端数区分0:なし1:切り捨て、2:四捨五入、3:切り上げ取得
	 * @return BigDecimal 端数区分0:なし1:切り捨て、2:四捨五入、3:切り上げ
	*/
	public BigDecimal getRoundDivision() {
		return this.roundDivision;
	}

	/**
	 * 端数区分0:なし1:切り捨て、2:四捨五入、3:切り上げ設定
	 * @param roundDivision 端数区分0:なし1:切り捨て、2:四捨五入、3:切り上げ
	*/
	public void setRoundDivision(final BigDecimal roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * 下限値取得
	 * @return BigDecimal 下限値
	*/
	public BigDecimal getLowerLimit() {
		return this.lowerLimit;
	}

	/**
	 * 下限値設定
	 * @param lowerLimit 下限値
	*/
	public void setLowerLimit(final BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * 上限値取得
	 * @return BigDecimal 上限値
	*/
	public BigDecimal getUpperLimit() {
		return this.upperLimit;
	}

	/**
	 * 上限値設定
	 * @param upperLimit 上限値
	*/
	public void setUpperLimit(final BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
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
