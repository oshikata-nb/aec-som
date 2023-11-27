/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * OrderListクラス.
 * @author kanri-user
 */
public class OrderList extends OrderListBase {

	private static final long serialVersionUID = 1L;

	private String shortDeliveryName;

	private String shortVenderName;

	private String shortItemName;

	/**
	 * コンストラクタ.
	 */
	public OrderList() {
		super();
	}

	/**
	 * 表示用deliveryName取得.
	 * @return deliveryName
	 */
	public String getDispDeliveryName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getDeliveryName(),
			getShortDeliveryName());
	}

	/**
	 * 表示用itemName取得.
	 * @return deliveryName
	 */
	public String getDispItemName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getItemName(), getShortItemName());
	}

	/**
	 * 表示用venderName取得.
	 * @return deliveryName
	 */
	public String getDispVenderName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getVenderName(), getShortVenderName());
	}

	/**
	 * shortDeliveryName1を取得します。
	 * @return shortDeliveryName1
	 */
	public String getShortDeliveryName() {
		return shortDeliveryName;
	}

	/**
	 * shortDeliveryNameを設定します。
	 * @param shortDeliveryName shortDeliveryName
	 */
	public void setShortDeliveryName(final String shortDeliveryName) {
		this.shortDeliveryName = shortDeliveryName;
	}

	/**
	 * shortItemNameを取得します。
	 * @return shortItemName
	 */
	public String getShortItemName() {
		return shortItemName;
	}

	/**
	 * shortItemNameを設定します。
	 * @param shortItemName shortItemName
	 */
	public void setShortItemName(final String shortItemName) {
		this.shortItemName = shortItemName;
	}

	/**
	 * shortVenderNameを取得します。
	 * @return shortVenderName
	 */
	public String getShortVenderName() {
		return shortVenderName;
	}

	/**
	 * shortVenderNameを設定します。
	 * @param shortVenderName shortVenderName
	 */
	public void setShortVenderName(final String shortVenderName) {
		this.shortVenderName = shortVenderName;
	}
}
