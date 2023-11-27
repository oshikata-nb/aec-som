/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;


/**
 * 製造指図一覧－指図書発行-計装インターフェイス用データ格納クラス.
 *
 * @author tosco
 */
public class DirectionProcedureFormulaList extends DirectionProcedureFormulaListBase {

	private static final long serialVersionUID = 1L;


	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";
	/** COLUMNアノテーション unitOfStockControl */
	public static final String unitOfStockControl_COLUMN = "UNIT_OF_STOCK_CONTROL";

	/** 品目名称 */
	private String itemName;
	/** 工程名称 */
	private String operationName;
	/** 在庫管理単位 */
	private String unitOfStockControl;

	/**
	 * コンストラクタ.
	 */
	public DirectionProcedureFormulaList() {
		super();
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
	 * 工程名称を取得します。
	 * @return 工程名称
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
	 * 在庫管理単位を取得します。
	 * @return 在庫管理単位
	 */
	public String getUnitOfStockControl() {
		return unitOfStockControl;
	}

	/**
	 * 在庫管理単位を設定します。
	 * @param unitOfStockControl 在庫管理単位
	 */
	public void setUnitOfStockControl(final String unitOfStockControl) {
		this.unitOfStockControl = unitOfStockControl;
	}


}
