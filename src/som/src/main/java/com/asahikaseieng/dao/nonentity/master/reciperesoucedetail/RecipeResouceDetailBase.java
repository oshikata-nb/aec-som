/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucedetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeResouceDetailクラス.
 * @author t0011036
 */
public class RecipeResouceDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション resouceCd */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** COLUMNアノテーション shortName */
	public static final String shortName_COLUMN = "SHORT_NAME";

	/** COLUMNアノテーション costMachine */
	public static final String costMachine_COLUMN = "COST_MACHINE";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション resouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション orderPublishFlg */
	public static final String orderPublishFlg_COLUMN = "ORDER_PUBLISH_FLG";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション resouceGroupName */
	public static final String resouceGroupName_COLUMN = "RESOUCE_GROUP_NAME";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	//
	// インスタンスフィールド
	//

	private String resouceCd;

	private String resouceName;

	private String shortName;

	private java.math.BigDecimal costMachine;

	private java.math.BigDecimal cost;

	private String resouceGroupCd;

	private String productionLine;

	private java.math.BigDecimal orderPublishFlg;

	private java.sql.Timestamp updateDate;

	private String resouceGroupName;

	private String productionLineName;

	//
	// インスタンスメソッド
	//

	/**
	 * resouceCd取得.
	 * @return resouceCd
	 */
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * resouceCd設定.
	 * @param resouceCd resouceCd
	 */
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * resouceName取得.
	 * @return resouceName
	 */
	public String getResouceName() {
		return this.resouceName;
	}

	/**
	 * resouceName設定.
	 * @param resouceName resouceName
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

	/**
	 * shortName取得.
	 * @return shortName
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * shortName設定.
	 * @param shortName shortName
	 */
	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

	/**
	 * costMachine取得.
	 * @return costMachine
	 */
	public java.math.BigDecimal getCostMachine() {
		return this.costMachine;
	}

	/**
	 * costMachine設定.
	 * @param costMachine costMachine
	 */
	public void setCostMachine(final java.math.BigDecimal costMachine) {
		this.costMachine = costMachine;
	}

	/**
	 * cost取得.
	 * @return cost
	 */
	public java.math.BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * cost設定.
	 * @param cost cost
	 */
	public void setCost(final java.math.BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * resouceGroupCd取得.
	 * @return resouceGroupCd
	 */
	public String getResouceGroupCd() {
		return this.resouceGroupCd;
	}

	/**
	 * resouceGroupCd設定.
	 * @param resouceGroupCd resouceGroupCd
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * productionLine取得.
	 * @return productionLine
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * productionLine設定.
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * orderPublishFlg取得.
	 * @return orderPublishFlg
	 */
	public java.math.BigDecimal getOrderPublishFlg() {
		return this.orderPublishFlg;
	}

	/**
	 * orderPublishFlg設定.
	 * @param orderPublishFlg orderPublishFlg
	 */
	public void setOrderPublishFlg(final java.math.BigDecimal orderPublishFlg) {
		this.orderPublishFlg = orderPublishFlg;
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
	 * resouceGroupName取得.
	 * @return resouceGroupName
	 */
	public String getResouceGroupName() {
		return this.resouceGroupName;
	}

	/**
	 * resouceGroupName設定.
	 * @param resouceGroupName resouceGroupName
	 */
	public void setResouceGroupName(final String resouceGroupName) {
		this.resouceGroupName = resouceGroupName;
	}

	/**
	 * productionLineName取得.
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * productionLineName設定.
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
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

