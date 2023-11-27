/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchaseorderforreport;

import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;

/**
 * PurchaseOrderListForReporクラス.
 * @author kanri-user
 */
public class PurchaseOrderListForRepor extends PurchaseOrderListForReporBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseOrderListForRepor() {
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
