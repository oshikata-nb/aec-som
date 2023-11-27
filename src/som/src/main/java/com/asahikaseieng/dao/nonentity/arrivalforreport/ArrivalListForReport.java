/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.arrivalforreport;

import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;

/**
 * ArrivalListForReportクラス.
 * @author kanri-user
 */
public class ArrivalListForReport extends ArrivalListForReportBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArrivalListForReport() {
		super();
	}

	/**
	 * 購買ステータス(文字列)を取得します。
	 * @return String 購買ステータス(文字列)
	 */
	public String getStatusName() {
		String ret = null;
		if (getStatus() != null) {
			ret = PurchaseStatus.getName(getStatus());
		}
		return ret;
	}
}
