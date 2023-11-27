/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.tanklock;

import com.asahikaseieng.app.comboboxes.SelectTankLockDirectionStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 調合タンク底弁インターロック解除/再設定_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class TankLockList extends TankLockListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TankLockList() {
		super();
	}

	/** チェックボックス */
	private boolean tankLockCheckBox;

	/** 品目名称 */
	private String itemName;

	/** 生産ライン名称 */
	private String productionLineName;

	/** 工程名称 */
	private String operationName;

	/** 解除・再設定 */
	private String interlock;

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
			//ステータスコンボボックスクラスから名称を取得
			ret = SelectTankLockDirectionStatus.getLabelName(getDirectionStatus().toString());
		}
		return ret;
	}

	/**
	 * チェックボックス取得
	 * @return tankLockCheckBox
	 */
	public boolean isTankLockCheckBox() {
		return tankLockCheckBox;
	}

	/**
	 * チェックボックス設定
	 * @param tankLockCheckBox チェックボックス
	 */
	public void setTankLockCheckBox(final boolean tankLockCheckBox) {
		this.tankLockCheckBox = tankLockCheckBox;
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

	/**
	 * 開始実績日(String)を取得します。
	 * @return strResultSdate
	 */
	public String getStrResultSdate() {
		//取得した製造予定日をyyyy/MM/ddに変換
		return AecDateUtils.dateFormat(getResultSdate(), "yyyy/MM/dd");
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
	 * 解除・再設定を取得します。
	 * @return interlock
	 */
	public String getInterlock() {
		return interlock;
	}

	/**
	 * 解除・再設定を設定します。
	 * @param interlock 解除・再設定
	 */
	public void setInterlock(final String interlock) {
		this.interlock = interlock;
	}

}
