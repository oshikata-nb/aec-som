/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchase;

import com.asahikaseieng.app.comboboxes.SelectPurchaseStatusPurchase;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 発注一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class PurchaseList extends PurchaseListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 発注日 */
	private String strOrderDate;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 不足量 */
	private String strCheckQuantity;

	/**
	 * コンストラクタ.
	 */
	public PurchaseList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */
	/**
	 * 購買ステータス取得
	 * @return String 購買ステータス
	 */
	public String getStrStatus() {
		String ret = null;
		if (getStatus() != null) {
			ret = SelectPurchaseStatusPurchase.getLabelName(getStatus()
					.toString());
		}
		return ret;
	}

	/**
	 * 発注日を取得します。
	 * @return strOrderDate
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 発注日を設定します。
	 * @param strOrderDate 発注日
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 発注数量を取得します。
	 * @return strOrderDate
	 */
	public String getStrOrderQuantity() {
		return strOrderQuantity;
	}

	/**
	 * 発注数量を設定します。
	 * @param strOrderQuantity 発注数量
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 重量を取得します。
	 * @return strOrderConvertQuantity
	 */
	public String getStrOrderConvertQuantity() {
		return strOrderConvertQuantity;
	}

	/**
	 * 重量を設定します。
	 * @param strOrderConvertQuantity 重量
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 納品希望日を取得します。
	 * @return strSuggestedDeliverlimitDate
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日を設定します。
	 * @param strSuggestedDeliverlimitDate 納品希望日
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * strCheckQuantityを取得します。
	 * @return strCheckQuantity
	 */
	public String getStrCheckQuantity() {
		return strCheckQuantity;
	}

	/**
	 * strCheckQuantityを設定します。
	 * @param strCheckQuantity strCheckQuantity
	 */
	public void setStrCheckQuantity(final String strCheckQuantity) {
		this.strCheckQuantity = strCheckQuantity;
	}
}
