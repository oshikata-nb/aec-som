/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectPkgDirectionDirectionStatus;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装指図－製造指図ヘッダー情報データ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionDirectionHeaderDetail extends PkgDirectionDirectionHeaderDetailBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";
	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";
	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";
	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";
	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";
	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";
	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";
	/** COLUMNアノテーション recipeCdVersion */
	public static final String recipeCdVersion_COLUMN = "RECIPE_CD_VERSION";
	/** COLUMNアノテーション recipeName */
	public static final String recipeName_COLUMN = "RECIPE_NAME";
	/** COLUMNアノテーション packageLineName */
	public static final String packageLineName_COLUMN = "PACKAGE_LINE_NAME";
	/** COLUMNアノテーション filltank */
	public static final String filltank_COLUMN = "FILL_TANK";
	/** COLUMNアノテーション filltankName */
	public static final String filltankName_COLUMN = "FILL_TANK_NAME";
	/** COLUMNアノテーション jissekiFlag */
	public static final String jissekiFlag_COLUMN = "JISSEKI_FLAG";
	/** COLUMNアノテーション RecipeUse */
	public static final String RecipeUse_COLUMN = "RECIPE_USE";
	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";
	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** 品目名称 */
	private String itemName;
	/** 荷姿 */
	private String styleOfPacking;
	/** 生産工場名 */
	private String productionLineName;
	/** 運用管理単位 */
	private String unitOfOperationManagement;
	/** 親品目コード */
	private String parentItemCd;
	/** 単位名 */
	private String unitName;
	/** 荷主 */
	private BigDecimal shipperDivision;
	/** 他社コード１ */
	private String otherCompanyCd1;
	/** 基本処方コード(コード+バージョン) */
	private String recipeCdVersion;
	/** 基本処方名称 */
	private String recipeName;
	/** 包装ライン名称 */
	private String packageLineName;
	/** 充填タンク */
	private String filltank;
	/** 充填タンク名称 */
	private String filltankName;
	/** 製品入出庫実績データ存在フラグ(0:無 1:有) */
	private String jissekiFlag;
	/** 用途 */
	private BigDecimal recipeUse;
	/** 工程コード */
	private String operationCd;
	/** 工程名称 */
	private String operationName;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionHeaderDetail() {
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
	 * ステータス名を取得します。
	 * @return ステータス名
	 */
	public String getStatusName() {
		String statusName = "";
		BigDecimal directionStatus = this.getDirectionStatus();
		if (directionStatus != null) {
			statusName = SelectPkgDirectionDirectionStatus.getLabelName(directionStatus.toString());
		}
		return statusName;
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
	 * 親品目コードを取得します。
	 * @return 親品目コード
	 */
	public String getParentItemCd() {
		return parentItemCd;
	}

	/**
	 * 親品目コードを設定します。
	 * @param parentItemCd 親品目コード
	 */
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
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
	 * 基本処方コード(コード+バージョン)取得.
	 * @return 基本処方コード(コード+バージョン)
	 */
	public String getRecipeCdVersion() {
		return this.recipeCdVersion;
	}

	/**
	 * 基本処方コード(コード+バージョン)設定.
	 * @param recipeCdVersion 基本処方コード(コード+バージョン)
	 */
	public void setRecipeCdVersion(final String recipeCdVersion) {
		this.recipeCdVersion = recipeCdVersion;
	}

	/**
	 * 基本処方名称取得.
	 * @return 基本処方名称
	 */
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * 基本処方名称設定.
	 * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 包装ライン名称取得.
	 * @return 包装ライン名称
	 */
	public String getPackageLineName() {
		return this.packageLineName;
	}

	/**
	 * 包装ライン名称設定.
	 * @param packageLineName 包装ライン名称
	 */
	public void setPackageLineName(final String packageLineName) {
		this.packageLineName = packageLineName;
	}

	/**
	 * 充填タンク取得.
	 * @return 充填タンク
	 */
	public String getFilltank() {
		return this.filltank;
	}

	/**
	 * 充填タンク設定.
	 * @param filltank 充填タンク
	 */
	public void setFilltank(final String filltank) {
		this.filltank = filltank;
	}

	/**
	 * 充填タンク名称取得.
	 * @return 充填タンク名称
	 */
	public String getFilltankName() {
		return this.filltankName;
	}

	/**
	 * 充填タンク名称設定.
	 * @param filltankName 充填タンク名称
	 */
	public void setFilltankName(final String filltankName) {
		this.filltankName = filltankName;
	}

	/**
	 * 製品入出庫実績データ存在フラグ(0:無 1:有)取得.
	 * @return ラベル枚数
	 */
	public String getJissekiFlag() {
		return jissekiFlag;
	}

	/**
	 * 製品入出庫実績データ存在フラグ(0:無 1:有)設定.
	 * @param jissekiFlag 製品入出庫実績データ存在フラグ
	 */
	public void setJissekiFlag(final String jissekiFlag) {
		this.jissekiFlag = jissekiFlag;
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
}
