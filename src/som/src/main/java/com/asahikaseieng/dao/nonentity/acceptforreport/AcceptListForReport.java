/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.acceptforreport;

import com.asahikaseieng.app.comboboxes.SelectStockingStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;

/**
 * AcceptListForReportクラス.
 * @author kanri-user
 */
public class AcceptListForReport extends AcceptListForReportBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AcceptListForReport() {
		super();
	}

	/**
	 * 購買ステータスを取得する
	 * @return 購買ステータス名称
	 */
	public String getStatusName() {
		String ret = null;
		if (getStatus() != null) {
			ret = PurchaseStatus.getName(getStatus());
		}
		return ret;
	}

	/**
	 * 仕入ステータス(名称)を取得します。
	 * @return String 仕入ステータス(名称)
	 */
	public String getStatus2Name() {
		String ret = null;
		if (getStatus2() != null) {
			ret = SelectStockingStatus.getLabelName(getStatus2().toString());
		}
		return ret;
	}

}
