/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 包装指図－処方ASPROVAデータ格納クラス.
 * @author kanri-user
 */
public class PkgDirectionRecipeAsprovaListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeAsprovaListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "RECIPE_ASPROVA";

	/** COLUMNアノテーション recipeId. */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション resouceGroupCd. */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション operationGroupCd. */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** COLUMNアノテーション resouceCd. */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	//
	// インスタンスフィールド
	//
	/** レシピインデックス */
	private BigDecimal recipeId;

	/** 設備グループコード */
	private String resouceGroupCd;

	/** 工程グループコード */
	private String operationGroupCd;

	/** 設備コード */
	private String resouceCd;

	//
	// インスタンスメソッド
	//
	/**
	 * レシピインデックス取得.
	 * @return BigDecimal レシピインデックス
	 */
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定.
	 * @param recipeId レシピインデックス
	 */
	public void setRecipeId(final BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * 設備グループコード取得.
	 * @return String 設備グループコード
	 */
	public String getResouceGroupCd() {
		return this.resouceGroupCd;
	}

	/**
	 * 設備グループコード設定.
	 * @param resouceGroupCd 設備グループコード
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * 工程グループコード取得.
	 * @return String 工程グループコード
	 */
	public String getOperationGroupCd() {
		return this.operationGroupCd;
	}

	/**
	 * 工程グループコード設定.
	 * @param operationGroupCd 工程グループコード
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}

	/**
	 * 設備コード取得.
	 * @return String 設備コード
	 */
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * 設備コード設定.
	 * @param resouceCd 設備コード
	 */
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
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
