/*
 * Created on 2009/05/28
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.directionstatuschange;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 製造指図ステータス変更画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class DirectionStatusChangeListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionStatusChangeListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション directionStatus */
	public static final String directionStatus_COLUMN = "DIRECTION_STATUS";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	/** 指図区分 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** 指図ステータス */
	private BigDecimal directionStatus;

	/** 生産ライン */
	private String productionLine;

	/** 品目コード */
	private String itemCd;

	/** 更新日時 */
	private Timestamp updateDate;

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
	 * 指図ステータス取得
	 * @return BigDecimal 指図ステータス
	*/
	public BigDecimal getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * 指図ステータス設定
	 * @param directionStatus 指図ステータス
	*/
	public void setDirectionStatus(final BigDecimal directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * 生産ライン取得
	 * @return String 生産ライン
	*/
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ライン設定
	 * @param productionLine 生産ライン
	*/
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
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
