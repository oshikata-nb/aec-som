/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.master.search.recipeheader;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 基本処方検索Popup－処方ヘッダデータ格納クラス.
 *
 * @author tosco
 */
public class RecipeHeaderSearchList extends RecipeHeaderSearchListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";
	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";
	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";
	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";
	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";


	/** 品目名称 */
	private String itemName;
	/** 生産工場名 */
	private String productionLineName;
	/** 単位名 */
	private String unitName;
	/** 単位区分 */
	private String unitDivision;
	/** 自社・花王区分 */
	private String shipperDivision;
	/** 他社コード１*/
	private String otherCompanyCd1;
	/** 自社・花王区分名称 */
	private String shipperDivisionName;

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderSearchList() {
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
	 * 自社・花王区分取得
	 * @return String 自社・花王区分
	*/
	public String getShipperDivision() {
		return this.shipperDivision;
	}

	/**
	 * 自社・花王区分設定
	 * @param shipperDivision 自社・花王区分
	*/
	public void setShipperDivision(final String shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 他社コード１取得
	 * @return String 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１設定
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
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

	/**
	 * 自社・花王区分名称を取得します。
	 * @return 自社・花王区分名称
	 */
	public String getShipperDivisionName() {
		return shipperDivisionName;
	}

	/**
	 * 自社・花王区分名称を設定します。
	 * @param shipperDivisionName 自社・花王区分名称
	 */
	public void setShipperDivisionName(final String shipperDivisionName) {
		this.shipperDivisionName = shipperDivisionName;
	}

}
