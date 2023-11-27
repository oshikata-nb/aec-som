/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty;

/**
 * InventoryDrawingTotalQtyクラス.
 * @author t0011036
 */
public class InventoryDrawingTotalQty extends InventoryDrawingTotalQtyBase {

	private static final long serialVersionUID = 1L;

	private String strInventoryQty;

	private String strBackorderQty;

	private String strFinishQty;

	private String strInspectionQty;

	private String strAssignQty;

	private String strSalesAssignQty;

	private String strUsepossibleQty;

	/**
	 * コンストラクタ.
	 */
	public InventoryDrawingTotalQty() {
		super();
	}

	/**
	 * strAssignQtyを取得します。
	 * @return strAssignQty
	 */
	public String getStrAssignQty() {
		return strAssignQty;
	}

	/**
	 * strAssignQtyを設定します。
	 * @param strAssignQty strAssignQty
	 */
	public void setStrAssignQty(final String strAssignQty) {
		this.strAssignQty = strAssignQty;
	}

	/**
	 * strSalesAssignQtyを取得します。
	 * @return strSalesAssignQty
	 */
	public String getStrSalesAssignQty() {
		return strSalesAssignQty;
	}

	/**
	 * strSalesAssignQtyを設定します。
	 * @param strSalesAssignQty strSalesAssignQty
	 */
	public void setStrSalesAssignQty(final String strSalesAssignQty) {
		this.strSalesAssignQty = strSalesAssignQty;
	}

	/**
	 * strBackorderQtyを取得します。
	 * @return strBackorderQty
	 */
	public String getStrBackorderQty() {
		return strBackorderQty;
	}

	/**
	 * strBackorderQtyを設定します。
	 * @param strBackorderQty strBackorderQty
	 */
	public void setStrBackorderQty(final String strBackorderQty) {
		this.strBackorderQty = strBackorderQty;
	}

	/**
	 * strInspectionQtyを取得します。
	 * @return strInspectionQty
	 */
	public String getStrInspectionQty() {
		return strInspectionQty;
	}

	/**
	 * strInspectionQtyを設定します。
	 * @param strInspectionQty strInspectionQty
	 */
	public void setStrInspectionQty(final String strInspectionQty) {
		this.strInspectionQty = strInspectionQty;
	}

	/**
	 * strInventoryQtyを取得します。
	 * @return strInventoryQty
	 */
	public String getStrInventoryQty() {
		return strInventoryQty;
	}

	/**
	 * strInventoryQtyを設定します。
	 * @param strInventoryQty strInventoryQty
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

	/**
	 * strUsepossibleQtyを取得します。
	 * @return strUsepossibleQty
	 */
	public String getStrUsepossibleQty() {
		return strUsepossibleQty;
	}

	/**
	 * strUsepossibleQtyを設定します。
	 * @param strUsepossibleQty strUsepossibleQty
	 */
	public void setStrUsepossibleQty(final String strUsepossibleQty) {
		this.strUsepossibleQty = strUsepossibleQty;
	}

	/**
	 * strFinishQtyを取得します。
	 * @return strFinishQty
	 */
	public String getStrFinishQty() {
		return strFinishQty;
	}

	/**
	 * strFinishQtyを設定します。
	 * @param strFinishQty strFinishQty
	 */
	public void setStrFinishQty(final String strFinishQty) {
		this.strFinishQty = strFinishQty;
	}
}
