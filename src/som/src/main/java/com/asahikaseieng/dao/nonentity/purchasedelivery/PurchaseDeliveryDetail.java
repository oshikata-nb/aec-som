/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchasedelivery;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 納期回答入力 表示用データ格納クラス.
 * 
 * @author tosco
 */
public class PurchaseDeliveryDetail extends PurchaseDeliveryDetailBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション chargeOrganizationName */
	public static final String chargeOrganizationName_COLUMN = "CHARGE_ORGANIZATION_NAME";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** 品目名称 */
	private String itemQueueName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 仕入先 */
	private String venderName;

	/** 納入先 */
	private String locationName;

	/** 部署名称 */
	private String organizationName;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 発注者名称 */
	private String tantoNm;

	/** 単位 */
	private String unit;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 発注日(スラッシュ編集) */
	private String strOrderDate;

	/** 納品希望日(スラッシュ編集) */
	private String strSuggestedDeliverlimitDate;

	/** 納品希望時刻(コロン編集) */
	private String strSuggestedDeliverlimitDateTime;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/** 購買ステータス(文字列) */
	private String strStatus;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliveryDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 日付のスラッシュ編集
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimitDateTime(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "HH:mm"));
		if (getStatus() != null) {
			setStrStatus(getStatus().toString());
		}
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
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 仕入先取得
	 * @return String 仕入先
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先設定
	 * @param venderName 仕入先
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 納入先取得
	 * @return String 納入先
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先設定
	 * @param locationName 納入先
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 部署名称取得
	 * @return String 部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称設定
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 担当部署名称取得
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
	}

	/**
	 * 発注者名称取得
	 * @return String 発注者名称
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 発注者名称設定
	 * @param tantoNm 発注者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
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
	 * 発注日(スラッシュ編集)を取得します。
	 * @return String 発注日(スラッシュ編集)
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 発注日(スラッシュ編集)を設定します。
	 * @param strOrderDate 発注日(スラッシュ編集)
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
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
	 * 購買ステータス(文字列)を取得します。
	 * @return String 購買ステータス(文字列)
	 */
	public String getStrStatus() {
		return strStatus;
	}

	/**
	 * 購買ステータス(文字列)を設定します。
	 * @param strStatus 購買ステータス(文字列)
	 */
	public void setStrStatus(final String strStatus) {
		this.strStatus = strStatus;
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
