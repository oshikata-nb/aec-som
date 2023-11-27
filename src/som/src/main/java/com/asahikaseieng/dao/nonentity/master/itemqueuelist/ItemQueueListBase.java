/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemQueueListクラス.
 * @author t0011036
 */
public class ItemQueueListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueListBase() {
	}

	//
	// 定数
	//

	/*  */
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/*  */
	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/*  */
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/*  */
	/** COLUMNアノテーション shortItemName */
	public static final String shortItemName_COLUMN = "SHORT_ITEM_NAME";

	/*  */
	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/*  */
	/** COLUMNアノテーション activeDate */
	public static final String activeDate_COLUMN = "ACTIVE_DATE";

	/*  */
	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/*  */
	/** COLUMNアノテーション detailStatusName */
	public static final String detailStatusName_COLUMN = "DETAIL_STATUS_NAME";

	/*  */
	/** COLUMNアノテーション activate */
	public static final String activate_COLUMN = "ACTIVATE";

	//
	// インスタンスフィールド
	//

	/**  */
	private String itemCd;

	/**  */
	private java.math.BigDecimal version;

	/**  */
	private String itemName;

	/**  */
	private String shortItemName;

	/**  */
	private java.math.BigDecimal status;

	/**  */
	private java.sql.Timestamp activeDate;

	/**  */
	private String statusName;

	/**  */
	private String detailStatusName;

	/**  */
	private String activate;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.
	 * 
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * 
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * 
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * 
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * itemName取得.
	 * 
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * 
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * shortItemName取得.
	 * 
	 * @return shortItemName
	 */
	public String getShortItemName() {
		return this.shortItemName;
	}

	/**
	 * shortItemName設定.
	 * 
	 * @param shortItemName shortItemName
	 */
	public void setShortItemName(final String shortItemName) {
		this.shortItemName = shortItemName;
	}

	/**
	 * status取得.
	 * 
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * 
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
	}

	/**
	 * activeDate取得.
	 * 
	 * @return activeDate
	 */
	public java.sql.Timestamp getActiveDate() {
		return this.activeDate;
	}

	/**
	 * activeDate設定.
	 * 
	 * @param activeDate activeDate
	 */
	public void setActiveDate(final java.sql.Timestamp activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * statusName取得.
	 * 
	 * @return statusName
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 * statusName設定.
	 * 
	 * @param statusName statusName
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * detailStatusName取得.
	 * 
	 * @return detailStatusName
	 */
	public String getDetailStatusName() {
		return this.detailStatusName;
	}

	/**
	 * detailStatusName設定.
	 * 
	 * @param detailStatusName detailStatusName
	 */
	public void setDetailStatusName(final String detailStatusName) {
		this.detailStatusName = detailStatusName;
	}

	/**
	 * activate取得.
	 * 
	 * @return activate
	 */
	public String getActivate() {
		return this.activate;
	}

	/**
	 * activate設定.
	 * 
	 * @param activate activate
	 */
	public void setActivate(final String activate) {
		this.activate = activate;
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

