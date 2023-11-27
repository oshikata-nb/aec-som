/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchaseforreport;

import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;

/**
 * PurchaseListForReportクラス.
 * @author kanri-user
 */
public class PurchaseListForReport extends PurchaseListForReportBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseListForReport() {
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
}
