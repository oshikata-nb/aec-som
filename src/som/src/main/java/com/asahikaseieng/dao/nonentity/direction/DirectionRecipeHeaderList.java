/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 処方ヘッダデータ格納クラス.
 *
 * @author tosco
 */
public class DirectionRecipeHeaderList extends DirectionRecipeHeaderListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";
	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";
	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";
	/** COLUMNアノテーション sumQty */
	public static final String sumQty_COLUMN = "SUM_QTY";
	/** COLUMNアノテーション waterWeight */
	public static final String waterWeight_COLUMN = "WATER_WEIGHT";
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";
	/** COLUMNアノテーション nameCd */
	public static final String nameCd_COLUMN = "NAME_CD";
	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";
	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";
	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";


	/** 品目名称 */
	private String itemName;
	/** ステータス名 */
	private String statusName;
	/** 生産工場名 */
	private String productionLineName;
	/** 単位名 */
	private String unitName;
	/** 配合量 */
	private BigDecimal sumQty;
	/** 洗浄水絶対重量計 */
	private BigDecimal waterWeight;
	/** 荷姿 */
	private String styleOfPacking;
	/** 名称コード */
	private String nameCd;
	/** 単位区分 */
	private String unitDivision;
	/** 工程コード */
	private String operationCd;
	/** 工程名称 */
	private String operationName;

	/**
	 * コンストラクタ.
	 */
	public DirectionRecipeHeaderList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}
	/* ---------- getter ,setter ---------- */

	/**
	 * 品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 生産工場名を取得します。
	 * @return 生産工場名
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 生産工場名を設定します。
	 * @param productionLineName 生産工場名
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * ステータス名を取得します。
	 * @return ステータス名
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * ステータス名を設定します。
	 * @param statusName ステータス名
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * 単位名を取得します。
	 * @return 単位名
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位名を設定します。
	 * @param unitName 単位名
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * sumQtyを取得します。
	 * @return sumQty
	 */
	public BigDecimal getSumQty() {
		return sumQty;
	}

	/**
	 * sumQtyを設定します。
	 * @param sumQty sumQty
	 */
	public void setSumQty(final BigDecimal sumQty) {
		this.sumQty = sumQty;
	}

	/**
	 * 配合量を取得します。
	 * @return 配合量
	 */
	public BigDecimal getWaterWeight() {
		return waterWeight;
	}

	/**
	 * 配合量を設定します。
	 * @param waterWeight 配合量
	 */
	public void setWaterWeight(final BigDecimal waterWeight) {
		this.waterWeight = waterWeight;
	}

	/**
	 * 荷姿を取得します。
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 名称コードを取得します。
	 * @return 名称コード
	 */
	public String getNameCd() {
		return nameCd;
	}

	/**
	 * 名称コードを設定します。
	 * @param nameCd 名称コード
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * 工程コード取得
	 * @return String 工程コード
	*/
	public String getOperationCd() {
		return this.operationCd;
	}

	/**
	 * 工程コード設定
	 * @param operationCd 工程コード
	*/
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 工程名称取得
	 * @return String 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称設定
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 単位区分を取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * 単位区分を設定します。
	 * @param unitDivision 単位区分
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

}
