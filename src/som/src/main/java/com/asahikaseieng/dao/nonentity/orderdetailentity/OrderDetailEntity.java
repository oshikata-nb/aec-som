/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailentity;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * OrderDetailEntityクラス.
 * @author kanri-user
 */
public class OrderDetailEntity extends OrderDetailEntityBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private String strOrderDate;

	private String strScheduledShippingDate;

	private String strSuggestedDeliverlimit;

	private String strDeliveryExpectedDate;

	/**
	 * コンストラクタ.
	 */
	public OrderDetailEntity() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		/* 日付にスラッシュを付与 */
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
		setStrDeliveryExpectedDate(AecDateUtils.dateFormat(
			getDeliveryExpectedDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimit(AecDateUtils.dateFormat(
			getSuggestedDeliverlimit(), "yyyy/MM/dd"));
		setStrScheduledShippingDate(AecDateUtils.dateFormat(
			getScheduledShippingDate(), "yyyy/MM/dd"));
	}

	/**
	 * strDeliveryExpectedDate取得.
	 * @return strDeliveryExpectedDate
	 */
	public String getStrDeliveryExpectedDate() {
		return strDeliveryExpectedDate;
	}

	/**
	 * strDeliveryExpectedDate設定.
	 * @param strDeliveryExpectedDate strDeliveryExpectedDate
	 */
	public void setStrDeliveryExpectedDate(final String strDeliveryExpectedDate) {
		this.strDeliveryExpectedDate = strDeliveryExpectedDate;
	}

	/**
	 * strOrderDate取得.
	 * @return strOrderDate
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * strOrderDate設定.
	 * @param strOrderDate strOrderDate
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * strScheduledShippingDate取得.
	 * @return strScheduledShippingDate
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * strScheduledShippingDate設定.
	 * @param strScheduledShippingDate strScheduledShippingDate
	 */
	public void setStrScheduledShippingDate(
			final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * strSuggestedDeliverlimit取得.
	 * @return strSuggestedDeliverlimit
	 */
	public String getStrSuggestedDeliverlimit() {
		return strSuggestedDeliverlimit;
	}

	/**
	 * strSuggestedDeliverlimit設定.
	 * @param strSuggestedDeliverlimit strSuggestedDeliverlimit
	 */
	public void setStrSuggestedDeliverlimit(
			final String strSuggestedDeliverlimit) {
		this.strSuggestedDeliverlimit = strSuggestedDeliverlimit;
	}
}
