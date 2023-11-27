/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchasedelivery;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 納期回答まとめ入力画面_明細部表示用データ格納クラス.
 * 
 * @author tosco
 */
public class PurchaseDeliverySumDetailList extends
		PurchaseDeliverySumDetailListBase implements PropertyTransferCallbacker {

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** 品目名称 */
	private String itemQueueName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位 */
	private String unit;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/** 納品希望日(スラッシュ編集) */
	private String strSuggestedDeliverlimitDate;

	/** 納品希望時刻(コロン編集) */
	private String strSuggestedDeliverlimitDateTime;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliverySumDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));
		String tmptime = AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "HH:mm");
		if (StringUtils.isEmpty(tmptime) || tmptime.equals("00:00")) {
			tmptime = null;
		}
		setStrSuggestedDeliverlimitDateTime(tmptime);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemQueueName() {
		return itemQueueName;
	}

	/**
	 * 品目名称設定
	 * @param itemQueueName 品目名称
	 */
	public void setItemQueueName(final String itemQueueName) {
		this.itemQueueName = itemQueueName;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 単位取得
	 * @return String 単位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位設定
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	/**
	 * 運用管理単位(区分)取得
	 * @return String 運用管理単位(区分)
	 */
	public String getUnitDiv() {
		return unitDiv;
	}

	/**
	 * 運用管理単位(区分)設定
	 * @param unitDiv 運用管理単位(区分)
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
	}

	/**
	 * 発注数量(カンマ編集)を取得します。
	 * @return String 発注数量(カンマ編集)
	 */
	public String getStrOrderQuantity() {
		return strOrderQuantity;
	}

	/**
	 * 発注数量(カンマ編集)を設定します。
	 * @param strOrderQuantity 発注数量(カンマ編集)
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 重量(カンマ編集)を取得します。
	 * @return String 重量(カンマ編集)
	 */
	public String getStrOrderConvertQuantity() {
		return strOrderConvertQuantity;
	}

	/**
	 * 重量(カンマ編集)を設定します。
	 * @param strOrderConvertQuantity 重量(カンマ編集)
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 納品希望日(スラッシュ編集)を取得します。
	 * @return String 納品希望日(スラッシュ編集)
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日(スラッシュ編集)を設定します。
	 * @param strSuggestedDeliverlimitDate 納品希望日(スラッシュ編集)
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望時刻(コロン編集)を取得します。
	 * @return String 納品希望時刻(コロン編集)
	 */
	public String getStrSuggestedDeliverlimitDateTime() {
		return strSuggestedDeliverlimitDateTime;
	}

	/**
	 * 納品希望時刻(コロン編集)を設定します。
	 * @param strSuggestedDeliverlimitDateTime 納品希望時刻(コロン編集)
	 */
	public void setStrSuggestedDeliverlimitDateTime(
			final String strSuggestedDeliverlimitDateTime) {
		this.strSuggestedDeliverlimitDateTime = strSuggestedDeliverlimitDateTime;
	}

	/**
	 * 分納区分(文字列)を取得します。
	 * @return String 分納区分(文字列)
	 */
	public String getStrReplyContentsDivision() {
		String ret = "";
		if (getReplyContentsDivision() != null) {
			if (getReplyContentsDivision().equals(BigDecimal.ZERO)) {
				ret = "無";
			}
			if (getReplyContentsDivision().equals(BigDecimal.ONE)) {
				ret = "有";
			}
		}
		return ret;
	}

	/**
	 * strBuySubcontractOrderNoを取得します。
	 * @return strBuySubcontractOrderNo
	 */
	public String getStrBuySubcontractOrderNo() {
		String strTmp = getBuySubcontractOrderNo();
		if (getOrderDevideNo() != null) {
			strTmp = strTmp + "-" + getOrderDevideNo();
		}
		return strTmp;
	}

	/**
	 * strBuySubcontractOrderNoを設定します。
	 * @param strBuySubcontractOrderNo strBuySubcontractOrderNo
	 */
	public void setStrBuySubcontractOrderNo(
			final String strBuySubcontractOrderNo) {
		String[] strTmp = strBuySubcontractOrderNo.split("-");
		if (strTmp.length > 1) {
			setBuySubcontractOrderNo(strTmp[0]);
			setOrderDevideNo(strTmp[1]);
		} else {
			setBuySubcontractOrderNo(strBuySubcontractOrderNo);
		}
	}

}
