/*
 * Created on 2009/04/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.production;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectProductionShipperDivision;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 生産計画入力一覧画面_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class ProductionList extends ProductionListBase implements
		PropertyTransferCallbacker {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";


	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 荷主 */
	private BigDecimal shipperDivision;

	/** 社内製造品/外注品区分 */
	private BigDecimal typeDivision;

	/** 工場名称 */
	private String productionLineName;

	/** 単位 */
	private String unitName;

	/** 生産予定量合計(String) */
	private String strSumOrderQty;

	/**
	 * コンストラクタ.
	 */
	public ProductionList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 品目名称取得
	 * @return itemName 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 運用管理区分を取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理区分を設定します。
	 * @param unitOfOperationManagement 運用管理区分
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 工場名取得
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 工場名設定
	 * @param productionLineName 工場名
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 荷主取得
	 * @return shipperDivision
	 */
	public BigDecimal getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 荷主設定
	 * @param shipperDivision 荷主
	 */
	public void setShipperDivision(final BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 荷主(String)取得
	 * @return strShipperDivision
	 */
	public String getStrShipperDivision() {
		String sDiv = null;
		if (getShipperDivision() != null) {
			sDiv = SelectProductionShipperDivision
				.getLabelName(getShipperDivision().add(new BigDecimal(1)).toString());
		}
		return sDiv;
	}

	/**
	 * 社内製造品/外注品区分取得
	 * @return typeDivision
	 */
	public BigDecimal getTypeDivision() {
		return typeDivision;
	}

	/**
	 * 社内製造品/外注品区分設定
	 * @param typeDivision 社内製造品/外注品区分
	 */
	public void setTypeDivision(final BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}

	/**
	 * 社内製造品/外注品区分(String)取得
	 * @return strTypeDivision
	 */
	public String getStrTypeDivision() {
		String strTDiv = null;
		if (getTypeDivision() != null) {
			BigDecimal typeDivision = getTypeDivision();
			if (typeDivision.compareTo(new BigDecimal(6)) == 0
				|| typeDivision.compareTo(new BigDecimal(7)) == 0) {
				strTDiv = "外注品";
			} else {
				strTDiv = "社内製造品";
			}
		}
		return strTDiv;
	}

	/**
	 * 単位取得
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位設定
	 * @param unitName 単位
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 生産計画年月(yyyyMM)取得
	 * @return strOrderLet
	 */
	public String getStrOrderLet() {
		String strOLet = null;
		if (getOrderLet() != null) {
			strOLet = AecDateUtils.dateFormat(getOrderLet(), "yyyy/MM");
		}
		return strOLet;
	}

	/**
	 * 生産予定量合計取得
	 * @return sumOrderQty
	 */
	public BigDecimal getSumOrderQty() {
		BigDecimal exQty = getOrderExpectQty();
		BigDecimal acQty = getOrderAcceptQty();
		BigDecimal sumQty = null;

		if (exQty != null) {
			if (acQty != null) {
				sumQty = exQty.add(acQty);
			} else {
				sumQty = exQty;
			}
		} else {
			if (acQty != null) {
				sumQty = acQty;
			}
		}

		return sumQty;
	}

	/**
	 * 生産予定量合計取得(String)
	 * @return strSumOrderQty
	 */
	public String getStrSumOrderQty() {
		return strSumOrderQty;
	}

	/**
	 * 生産予定量合計取得(String)を設定します。
	 * @param strSumOrderQty 生産予定量合計取得(String)
	 */
	public void setStrSumOrderQty(final String strSumOrderQty) {
		this.strSumOrderQty = strSumOrderQty;
	}
}
