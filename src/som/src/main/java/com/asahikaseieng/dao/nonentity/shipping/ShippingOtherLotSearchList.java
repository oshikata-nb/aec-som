/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * ロット検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class ShippingOtherLotSearchList extends ShippingLotSearchListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** ロケーション名称 */
	private String locationName;

	/** 在庫量(文字列) */
	private String strInventoryQty;

	/** 発注残(文字列) */
	private String strBackorderQty;

	/** 検査待(文字列) */
	private String strInspectionQty;

	/** 引当残(文字列) */
	private String strAssignQty;

	/** 有効在庫 */
	private BigDecimal validInventory;

	/** 有効在庫(文字列) */
	private String strValidInventory;

	/** 完成日(文字列) */
	private String strIssueDate;

	/**
	 * コンストラクタ.
	 */
	public ShippingOtherLotSearchList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 日付・数値の編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrIssueDate(AecDateUtils.dateFormat(getIssueDate(), "yyyy/MM/dd"));

		// 有効在庫算出
		setValidInventory(calcValidInventory(getInventoryQty(),
			getBackorderQty(), getInspectionQty(), getAssignQty()));

	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * ロケーション名称取得
	 * @return String ロケーション名称
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * ロケーション名称設定
	 * @param locationName ロケーション名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 在庫量(文字列)を取得します。
	 * @return String 在庫量(文字列)
	 */
	public String getStrInventoryQty() {
		return strInventoryQty;
	}

	/**
	 * 在庫量(文字列)を設定します。
	 * @param strInventoryQty 在庫量(文字列)
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

	/**
	 * 発注残(文字列)を取得します。
	 * @return String 発注残(文字列)
	 */
	public String getStrBackorderQty() {
		return strBackorderQty;
	}

	/**
	 * 発注残(文字列)を設定します。
	 * @param strBackorderQty 発注残(文字列)
	 */
	public void setStrBackorderQty(final String strBackorderQty) {
		this.strBackorderQty = strBackorderQty;
	}

	/**
	 * 検査待(文字列)を取得します。
	 * @return String 検査待(文字列)
	 */
	public String getStrInspectionQty() {
		return strInspectionQty;
	}

	/**
	 * 検査待(文字列)を設定します。
	 * @param strInspectionQty 検査待(文字列)
	 */
	public void setStrInspectionQty(final String strInspectionQty) {
		this.strInspectionQty = strInspectionQty;
	}

	/**
	 * 引当残(文字列)を取得します。
	 * @return String 引当残(文字列)
	 */
	public String getStrAssignQty() {
		return strAssignQty;
	}

	/**
	 * 引当残(文字列)を設定します。
	 * @param strAssignQty 引当残(文字列)
	 */
	public void setStrAssignQty(final String strAssignQty) {
		this.strAssignQty = strAssignQty;
	}

	/**
	 * 有効在庫を取得します。
	 * @return String 有効在庫
	 */
	public BigDecimal getValidInventory() {
		return validInventory;
	}

	/**
	 * 有効在庫を設定します。
	 * @param validInventory 有効在庫
	 */
	public void setValidInventory(final BigDecimal validInventory) {
		this.validInventory = validInventory;
	}

	/**
	 * 有効在庫(文字列)を取得します。
	 * @return String 有効在庫(文字列)
	 */
	public String getStrValidInventory() {
		return strValidInventory;
	}

	/**
	 * 有効在庫(文字列)を設定します。
	 * @param strValidInventory 有効在庫(文字列)
	 */
	public void setStrValidInventory(final String strValidInventory) {
		this.strValidInventory = strValidInventory;
	}

	/**
	 * 完成日(文字列)を取得します。
	 * @return String 完成日(文字列)
	 */
	public String getStrIssueDate() {
		return strIssueDate;
	}

	/**
	 * 完成日(文字列)を設定します。
	 * @param strIssueDate 完成日(文字列)
	 */
	public void setStrIssueDate(final String strIssueDate) {
		this.strIssueDate = strIssueDate;
	}

	/**
	 * 有効在庫を計算する。
	 * @param inventoryQty 在庫量
	 * @param backorderQty 発注残
	 * @param inspectionQty 検査待
	 * @param assignQty 引当残
	 * @return BigDecimal 有効在庫
	 */
	private BigDecimal calcValidInventory(final BigDecimal inventoryQty,
			final BigDecimal backorderQty, final BigDecimal inspectionQty,
			final BigDecimal assignQty) {
		BigDecimal validInventory = new BigDecimal(0);

		if (inventoryQty == null || backorderQty == null
				|| inspectionQty == null || assignQty == null) {
			return null;
		}

		// 有効在庫 ＝ 在庫量 ＋ 発注残 ＋ 検査待 － 引当残
		validInventory = inventoryQty.add(backorderQty).add(inspectionQty).add(
			assignQty);
		return validInventory;
	}

}
