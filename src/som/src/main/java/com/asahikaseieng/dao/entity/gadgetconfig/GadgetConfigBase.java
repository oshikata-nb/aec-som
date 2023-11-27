/*
 * Created on Mon Apr 09 13:53:49 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadgetconfig;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * GadgetConfigBaseクラス.
 * @author jbd
 */
public class GadgetConfigBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public GadgetConfigBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "GADGET_CONFIG";

	// /** IDアノテーション tantoCd. */
	// public static final String tantoCd_ID = "assigned";
	// /** IDアノテーション gadgetId. */
	// public static final String gadgetId_ID = "assigned";

	/** COLUMNアノテーション tantoCd. */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション menuId. */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション tabId. */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション gadgetId. */
	public static final String gadgetId_COLUMN = "GADGET_ID";

	/** COLUMNアノテーション laneNo. */
	public static final String laneNo_COLUMN = "LANE_NO";

	/** COLUMNアノテーション verticalOrder. */
	public static final String verticalOrder_COLUMN = "VERTICAL_ORDER";

	/** COLUMNアノテーション slideStatus. */
	public static final String slideStatus_COLUMN = "SLIDE_STATUS";

	//
	// インスタンスフィールド
	//

	private String tantoCd;

	private java.math.BigDecimal menuId;

	private java.math.BigDecimal tabId;

	private String gadgetId;

	private java.math.BigDecimal laneNo;

	private java.math.BigDecimal verticalOrder;

	private java.math.BigDecimal slideStatus;

	//
	// インスタンスメソッド
	//

	/**
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * メニューID取得.
	 * @return BigDecimal メニューID
	 */
	public java.math.BigDecimal getMenuId() {
		return menuId;
	}

	/**
	 * メニューID設定.
	 * @param menuId メニューID
	 */
	public void setMenuId(final java.math.BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * タブID取得.
	 * @return BigDecimal タブID
	 */
	public java.math.BigDecimal getTabId() {
		return tabId;
	}

	/**
	 * タブID設定.
	 * @param tabId タブID
	 */
	public void setTabId(final java.math.BigDecimal tabId) {
		this.tabId = tabId;
	}

	/**
	 * gadgetId取得.
	 * @return gadgetId
	 */
	public String getGadgetId() {
		return this.gadgetId;
	}

	/**
	 * gadgetId設定.
	 * @param gadgetId gadgetId
	 */
	public void setGadgetId(final String gadgetId) {
		this.gadgetId = gadgetId;
	}

	/**
	 * laneNo取得.
	 * @return laneNo
	 */
	public java.math.BigDecimal getLaneNo() {
		return this.laneNo;
	}

	/**
	 * laneNo設定.
	 * @param laneNo laneNo
	 */
	public void setLaneNo(final java.math.BigDecimal laneNo) {
		this.laneNo = laneNo;
	}

	/**
	 * verticalOrder取得.
	 * @return verticalOrder
	 */
	public java.math.BigDecimal getVerticalOrder() {
		return this.verticalOrder;
	}

	/**
	 * verticalOrder設定.
	 * @param verticalOrder verticalOrder
	 */
	public void setVerticalOrder(final java.math.BigDecimal verticalOrder) {
		this.verticalOrder = verticalOrder;
	}

	/**
	 * slideStatus取得.
	 * @return slideStatus
	 */
	public java.math.BigDecimal getSlideStatus() {
		return this.slideStatus;
	}

	/**
	 * slideStatus設定.
	 * @param slideStatus slideStatus
	 */
	public void setSlideStatus(final java.math.BigDecimal slideStatus) {
		this.slideStatus = slideStatus;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
