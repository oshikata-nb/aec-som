/*
 * Created on Wed Apr 11 16:25:20 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadget;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * GadgetBaseクラス.
 * @author jbd
 */
public class GadgetBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public GadgetBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "GADGET";

//	/** IDアノテーション gadgetId. */
//	public static final String gadgetId_ID = "assigned";

	/** COLUMNアノテーション gadgetId. */
	public static final String gadgetId_COLUMN = "GADGET_ID";

	/** COLUMNアノテーション title. */
	public static final String title_COLUMN = "TITLE";

	/** COLUMNアノテーション titleUrl. */
	public static final String titleUrl_COLUMN = "TITLE_URL";

	/** COLUMNアノテーション actionUrl. */
	public static final String actionUrl_COLUMN = "ACTION_URL";

	/** COLUMNアノテーション menuId. */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション tabId. */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション controlId. */
	public static final String controlId_COLUMN = "CONTROL_ID";

	/** COLUMNアノテーション sqlCd. */
	public static final String sqlCd_COLUMN = "SQL_CD";

	/** COLUMNアノテーション maxLine. */
	public static final String maxLine_COLUMN = "MAX_LINE";

	/** COLUMNアノテーション forceDispFlg. */
	public static final String forceDispFlg_COLUMN = "FORCE_DISP_FLG";

	//
	// インスタンスフィールド	//

	private String gadgetId;

	private String title;

	private String titleUrl;

	private String actionUrl;

	private BigDecimal menuId;

	private BigDecimal tabId;

	private BigDecimal controlId;

	private String sqlCd;

	private BigDecimal maxLine;

	private String forceDispFlg;

	//
	// インスタンスメソッド
	//

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
	 * title取得.
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * title設定.
	 * @param title title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * titleUrl取得.
	 * @return titleUrl
	 */
	public String getTitleUrl() {
		return this.titleUrl;
	}

	/**
	 * titleUrl設定.
	 * @param titleUrl titleUrl
	 */
	public void setTitleUrl(final String titleUrl) {
		this.titleUrl = titleUrl;
	}

	/**
	 * actionUrl取得.
	 * @return actionUrl
	 */
	public String getActionUrl() {
		return this.actionUrl;
	}

	/**
	 * actionUrl設定.
	 * @param actionUrl actionUrl
	 */
	public void setActionUrl(final String actionUrl) {
		this.actionUrl = actionUrl;
	}

	/**
	 * メニューID取得.
	 * @return BigDecimal メニューID
	 */
	public BigDecimal getMenuId() {
		return this.menuId;
	}

	/**
	 * メニューID設定.
	 * @param menuId メニューID
	 */
	public void setMenuId(final BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * タブID取得.
	 * @return BigDecimal タブID
	 */
	public BigDecimal getTabId() {
		return this.tabId;
	}

	/**
	 * タブID設定.
	 * @param tabId タブID
	 */
	public void setTabId(final BigDecimal tabId) {
		this.tabId = tabId;
	}

	/**
	 * 操作ID取得.
	 * @return BigDecimal 操作ID
	 */
	public BigDecimal getControlId() {
		return this.controlId;
	}

	/**
	 * 操作ID設定.
	 * @param controlId 操作ID
	 */
	public void setControlId(final BigDecimal controlId) {
		this.controlId = controlId;
	}

	/**
	 * 検索実行SQL取得.
	 * @return String 検索実行SQL
	 */
	public String getSqlCd() {
		return this.sqlCd;
	}

	/**
	 * 検索実行SQL設定.
	 * @param sqlCd 検索実行SQL
	 */
	public void setSqlCd(final String sqlCd) {
		this.sqlCd = sqlCd;
	}

	/**
	 * ガジェットに表示する項目の最大値取得.
	 * @return BigDecimal ガジェットに表示する項目の最大値
	 */
	public BigDecimal getMaxLine() {
		return maxLine;
	}

	/**
	 * ガジェットに表示する項目の最大値設定.
	 * @param maxLine ガジェットに表示する項目の最大値
	 */
	public void setMaxLine(final BigDecimal maxLine) {
		this.maxLine = maxLine;
	}

	/**
	 * 強制表示フラグ取得.
	 * @return String 強制表示フラグ
	 */
	public String getForceDispFlg() {
		return forceDispFlg;
	}

	/**
	 * 強制表示フラグ設定.
	 * @param forceDispFlg 強制表示フラグ
	 */
	public void setForceDispFlg(final String forceDispFlg) {
		this.forceDispFlg = forceDispFlg;
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
