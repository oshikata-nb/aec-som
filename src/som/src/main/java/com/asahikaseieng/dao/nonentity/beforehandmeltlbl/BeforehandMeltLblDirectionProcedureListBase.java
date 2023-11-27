/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.beforehandmeltlbl;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 製造指図プロシージャ用 データ格納クラス.
 * 
 * @author tosco
 */
public class BeforehandMeltLblDirectionProcedureListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BeforehandMeltLblDirectionProcedureListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション mainStream */
	public static final String mainStream_COLUMN = "MAIN_STREAM";

	//
	// インスタンスフィールド
	//

	/** 指図区分 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** 本流/予備溶解 */
	private BigDecimal mainStream;


	//
	// インスタンスメソッド
	//

	/**
	 * 指図区分取得
	 * @return BigDecimal 指図区分
	*/
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分設定
	 * @param directionDivision 指図区分
	*/
	public void setDirectionDivision(final BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 指図番号取得
	 * @return String 指図番号
	*/
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 指図番号設定
	 * @param directionNo 指図番号
	*/
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 本流/予備溶解取得
	 * @return BigDecimal 本流/予備溶解
	*/
	public BigDecimal getMainStream() {
		return this.mainStream;
	}

	/**
	 * 本流/予備溶解設定
	 * @param mainStream 本流/予備溶解
	*/
	public void setMainStream(final BigDecimal mainStream) {
		this.mainStream = mainStream;
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
