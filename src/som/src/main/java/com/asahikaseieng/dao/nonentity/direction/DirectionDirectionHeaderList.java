/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 詳細画面_表示用データ格納クラス.
 *
 * @author tosco
 */
public class DirectionDirectionHeaderList extends DirectionDirectionHeaderListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";
	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";
	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";
	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";
	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";
	/** COLUMNアノテーション waterWeight */
	public static final String waterWeight_COLUMN = "WATER_WEIGHT";
	/** COLUMNアノテーション recipeName */
	public static final String recipeName_COLUMN = "RECIPE_NAME";
	/** COLUMNアノテーション RecipeUse */
	public static final String RecipeUse_COLUMN = "RECIPE_USE";
	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";
	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";
	/** COLUMNアノテーション orderPublishFlg */
	public static final String orderPublishFlg_COLUMN = "ORDER_PUBLISH_FLG";

	/** 品目名称 */
	private String itemName;
	/** 生産工場名 */
	private String productionLineName;
	/** 運用管理単位 */
	private String unitOfOperationManagement;
	/** 単位名 */
	private String unitName;
	/** 荷主 */
	private BigDecimal shipperDivision;
	/** 洗浄水絶対重量計 */
	private BigDecimal waterWeight;
	/** 基本処方名称 */
	private String recipeName;
	/** 用途 */
	private BigDecimal recipeUse;
	/** 工程コード */
	private String operationCd;
	/** 工程名称 */
	private String operationName;
	/** 指図書発行有無フラグ 1:あり 2:なし */
	private BigDecimal orderPublishFlg;

	//計装インターフェイス登録用------------------
	/** 他社コード１ */
	private String otherCompanyCd1;

	//表示用-------------
	/** ステータス名称 */
	private String statusName;
	/** 仕込予定数量 */
	private String plandQtyString;
	/** 選択チェックボックス */
	private boolean selectedCheck;



	/**
	 * コンストラクタ.
	 */
	public DirectionDirectionHeaderList() {
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
	 * ステータス名称を取得します。
	 * @return ステータス名称
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * ステータス名称を設定します。
	 * @param statusName ステータス名称
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return 運用管理単位
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 仕込予定数量を取得します。
	 * @return 仕込予定数量
	 */
	public String getPlandQtyString() {
		return plandQtyString;
	}

	/**
	 * 仕込予定数量を設定します。
	 * @param plandQtyString 仕込予定数量
	 */
	public void setPlandQtyString(final String plandQtyString) {
		this.plandQtyString = plandQtyString;
	}

	/**
	 * 選択チェックボックスを取得します。
	 * @return 選択チェックボックス
	 */
	public boolean isSelectedCheck() {
		return selectedCheck;
	}

	/**
	 * 選択チェックボックスを設定します。
	 * @param selectedCheck 選択チェックボックス
	 */
	public void setSelectedCheck(final boolean selectedCheck) {
		this.selectedCheck = selectedCheck;
	}

	/**
	 * 他社コード１を取得します。
	 * @return 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷主を取得します。
	 * @return 荷主
	 */
	public BigDecimal getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 荷主を設定します。
	 * @param shipperDivision 荷主
	 */
	public void setShipperDivision(final BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 洗浄水絶対重量計を取得します。
	 * @return 洗浄水絶対重量計
	 */
	public BigDecimal getWaterWeight() {
		return waterWeight;
	}

	/**
	 * 洗浄水絶対重量計を設定します。
	 * @param waterWeight 洗浄水絶対重量計
	 */
	public void setWaterWeight(final BigDecimal waterWeight) {
		this.waterWeight = waterWeight;
	}
	/**
	 * 基本処方名称を取得します。
	 * @return recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * 基本処方名称を設定します。
	 * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 用途を取得します。
	 * @return recipeUse
	 */
	public BigDecimal getRecipeUse() {
		return recipeUse;
	}

	/**
	 * 用途を設定します。
	 * @param recipeUse 用途
	 */
	public void setRecipeUse(final BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * 工程コードを取得します。
	 * @return operationCd
	 */
	public String getOperationCd() {
		return operationCd;
	}

	/**
	 * 工程コードを設定します。
	 * @param operationCd 工程コード
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 工程名称を取得します。
	 * @return operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称を設定します。
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 指図書発行有無フラグを取得します。
	 * @return orderPublishFlg 指図書発行有無フラグ 1:あり 2:なし
	 */
	public BigDecimal getOrderPublishFlg() {
		return orderPublishFlg;
	}

	/**
	 * 指図書発行有無フラグを設定します。
	 * @param orderPublishFlg 指図書発行有無フラグ 1:あり 2:なし
	 */
	public void setOrderPublishFlg(final BigDecimal orderPublishFlg) {
		this.orderPublishFlg = orderPublishFlg;
	}

}
