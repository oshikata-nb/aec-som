/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productinspectcomp;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectProductInspectCompDirectionStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 製品検査完了入力一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ProductInspectCompList extends ProductInspectCompListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductInspectCompList() {
		super();
	}

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	/** COLUMNアノテーション lineName */
	public static final String lineName_COLUMN = "LINE_NAME";

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** COLUMNアノテーション lotInventoryQty */
	public static final String lotInventoryQty_COLUMN = "LOT_INVENTORY_QTY";

	/** 品目名称 */
	private String itemName;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 生産ライン名称 */
	private String productionLineName;

	/** 工程名称 */
	private String operationName;

	/** 単位 */
	private String unitName;

	/** 生産ライン名称 */
	private String lineName;

	/** 使用資源名 */
	private String resouceName;

	/** 実績生産量(String) */
	private String strResultQty;

	/** チェックボックス */
	private boolean productInspectCompCheckBox;

	/** 実績ロット在庫量 */
	private BigDecimal lotInventoryQty;

	/** 実績ロット在庫量(String) */
	private String strLotInventoryQty;

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
	 * 指図ステータス名称取得
	 * @return String 指図ステータス名称
	 */
	public String getStrDirectionStatus() {
		String ret = null;
		if (getDirectionStatus() != null) {
			ret = SelectProductInspectCompDirectionStatus
					.getLabelName(getDirectionStatus().toString());
		}
		return ret;
	}

	/**
	 * 包装開始実績日(String)を取得します。
	 * @return strResultSdate
	 */
	public String getStrResultEdate() {
		// 取得した包装開始実績日をyyyy/MM/ddに変換
		return AecDateUtils.dateFormat(getResultEdate(), "yyyy/MM/dd");
	}

	/**
	 * 検査合格日(String)を取得します。
	 * @return strCertificationDate
	 */
	public String getStrCertificationDate() {
		// 取得した検査合格日をyyyy/MM/ddに変換
		return AecDateUtils.dateFormat(getCertificationDate(), "yyyy/MM/dd");
	}

	/**
	 * チェックボックス取得
	 * @return productInspectCompCheckBox
	 */
	public boolean isProductInspectCompCheckBox() {
		return productInspectCompCheckBox;
	}

	/**
	 * チェックボックス設定
	 * @param productInspectCompCheckBox チェックボックス
	 */
	public void setProductInspectCompCheckBox(
			final boolean productInspectCompCheckBox) {
		this.productInspectCompCheckBox = productInspectCompCheckBox;
	}

	/**
	 * 品目名称を取得します。
	 * @return itemName
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
	 * 運用管理単位を取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 生産ライン名称を取得します。
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 生産ライン名称を設定します。
	 * @param productionLineName 生産ライン名称
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
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
	 * 生産ライン名称を取得します。
	 * @return lineName
	 */
	public String getLineName() {
		return lineName;
	}

	/**
	 * 生産ライン名称を設定します。
	 * @param lineName 生産ライン名称
	 */
	public void setLineName(final String lineName) {
		this.lineName = lineName;
	}

	/**
	 * 使用資源名を取得します。
	 * @return resouceName
	 */
	public String getResouceName() {
		return resouceName;
	}

	/**
	 * 使用資源名を設定します。
	 * @param resouceName 使用資源名
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

	/**
	 * 単位を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位を設定します。
	 * @param unitName 単位
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 実績生産量(String)を取得します。
	 * @return strResultQty
	 */
	public String getStrResultQty() {
		return strResultQty;
	}

	/**
	 * 実績生産量(String)を設定します。
	 * @param strResultQty 実績生産量(String)
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * 実績ロット在庫量取得
	 * @return BigDecimal 実績ロット在庫量
	 */
	public BigDecimal getLotInventoryQty() {
		return this.lotInventoryQty;
	}

	/**
	 * 実績ロット在庫量設定
	 * @param lotInventoryQty 実績ロット在庫量
	 */
	public void setLotInventoryQty(final BigDecimal lotInventoryQty) {
		this.lotInventoryQty = lotInventoryQty;
	}

	/**
	 * 実績ロット在庫量(String)取得
	 * @return String 実績ロット在庫量(String)
	 */
	public String getStrLotInventoryQty() {
		return this.strLotInventoryQty;
	}

	/**
	 * 実績ロット在庫量(String)設定
	 * @param strLotInventoryQty 実績ロット在庫量(String)
	 */
	public void setStrLotInventoryQty(final String strLotInventoryQty) {
		this.strLotInventoryQty = strLotInventoryQty;
	}
}
