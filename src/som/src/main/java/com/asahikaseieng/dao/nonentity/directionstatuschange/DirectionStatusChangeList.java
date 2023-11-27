/*
 * Created on 2009/05/28
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.directionstatuschange;

import com.asahikaseieng.app.comboboxes.SelectDirectionStatusChangeDirectionStatus;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 製造指図ステータス変更画面_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class DirectionStatusChangeList extends DirectionStatusChangeListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionStatusChangeList() {
		super();
	}

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** 工程名称 */
	private String operationName;

	/** 品目名称 */
	private String itemName;

	/** 生産ライン名称 */
	private String productionLineName;

	/** 選択チェックボックス */
	private boolean selectedCheck;

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
	 * 指図ステータス取得
	 * @return String 指図ステータス
	 */
	public String getStrDirectionStatus() {
		String ret = null;
		if (getDirectionStatus() != null) {
			ret = SelectDirectionStatusChangeDirectionStatus.getLabelName(getDirectionStatus().toString());
		}
		return ret;
	}

	/**
	 * 選択チェックボックス取得
	 * @return selectedCheck 選択チェックボックス
	 */
	public boolean isSelectedCheck() {
		return selectedCheck;
	}

	/**
	 * 選択チェックボックス設定
	 * @param selectedCheck 選択チェックボックス
	 */
	public void setSelectedCheck(final boolean selectedCheck) {
		this.selectedCheck = selectedCheck;
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
}
