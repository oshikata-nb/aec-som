/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail;

/**
 * PurchaseAttributeQueueDetailクラス.
 * @author t0011036
 */
public class PurchaseAttributeQueueDetail extends
		PurchaseAttributeQueueDetailBase {

	private static final long serialVersionUID = 1L;

	private String strPurchaseLeadTime;

	private String strPurchasePrice;

	private String strPurchaseOrderPoint;

	private String strOrderQty;

	/**
	 * コンストラクタ.
	 */
	public PurchaseAttributeQueueDetail() {
		super();
	}

	/**
	 * strOrderQtyを取得します。
	 * @return strOrderQty
	 */
	public String getStrOrderQty() {
		return strOrderQty;
	}

	/**
	 * strOrderQtyを設定します。
	 * @param strOrderQty strOrderQty
	 */
	public void setStrOrderQty(final String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	/**
	 * strPurchaseLeadTimeを取得します。
	 * @return strPurchaseLeadTime
	 */
	public String getStrPurchaseLeadTime() {
		return strPurchaseLeadTime;
	}

	/**
	 * strPurchaseLeadTimeを設定します。
	 * @param strPurchaseLeadTime strPurchaseLeadTime
	 */
	public void setStrPurchaseLeadTime(final String strPurchaseLeadTime) {
		this.strPurchaseLeadTime = strPurchaseLeadTime;
	}

	/**
	 * strPurchaseOrderPointを取得します。
	 * @return strPurchaseOrderPoint
	 */
	public String getStrPurchaseOrderPoint() {
		return strPurchaseOrderPoint;
	}

	/**
	 * strPurchaseOrderPointを設定します。
	 * @param strPurchaseOrderPoint strPurchaseOrderPoint
	 */
	public void setStrPurchaseOrderPoint(final String strPurchaseOrderPoint) {
		this.strPurchaseOrderPoint = strPurchaseOrderPoint;
	}

	/**
	 * strPurchasePriceを取得します。
	 * @return strPurchasePrice
	 */
	public String getStrPurchasePrice() {
		return strPurchasePrice;
	}

	/**
	 * strPurchasePriceを設定します。
	 * @param strPurchasePrice strPurchasePrice
	 */
	public void setStrPurchasePrice(final String strPurchasePrice) {
		this.strPurchasePrice = strPurchasePrice;
	}
}
