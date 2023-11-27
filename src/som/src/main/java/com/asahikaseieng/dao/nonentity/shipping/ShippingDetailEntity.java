/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectShippingShippingStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 出荷指図詳細画面_出荷指図ヘッダー表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingDetailEntity extends ShippingDetailEntityBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemCd */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション deliveryAllAddress */
	public static final String deliveryAllAddress_COLUMN = "DELIVERY_ALL_ADDRESS";

	/** 出荷指図日(文字列) */
	private String strShippingInstructDate;

	/** 納入先名称 */
	private String deliveryName;

	/** 出荷予定日(文字列) */
	private String strScheduledShippingDate;

	/** 希望納期(文字列) */
	private String strSuggestedDeliverlimit;

	/** 出荷指図数量(文字列) */
	private String strShippingInstruction;

	/** 品名名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 指図量累計 */
	private BigDecimal shippingInstructionSum;

	/** 指図量累計(文字列) */
	private String strShippingInstructionSum;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位区分 */
	private String unitDivision;

	private String deliveryAllAddress;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailEntity() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrShippingInstructDate(AecDateUtils.dateFormat(
			getShippingInstructDate(), "yyyy/MM/dd"));
		setStrScheduledShippingDate(AecDateUtils.dateFormat(
			getScheduledShippingDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimit(AecDateUtils.dateFormat(
			getSuggestedDeliverlimit(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 出荷指図日(文字列)を取得します。
	 * @return String 出荷指図日(文字列)
	 */
	public String getStrShippingInstructDate() {
		return strShippingInstructDate;
	}

	/**
	 * 出荷指図日(文字列)を設定します。
	 * @param strShippingInstructDate 出荷指図日(文字列)
	 */
	public void setStrShippingInstructDate(final String strShippingInstructDate) {
		this.strShippingInstructDate = strShippingInstructDate;
	}

	/**
	 * 納入先名称取得
	 * @return String 納入先名称
	 */
	public String getDeliveryName() {
		return this.deliveryName;
	}

	/**
	 * 納入先名称設定
	 * @param deliveryName 納入先名称
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * 出荷ステータス(文字列)を取得します。
	 * @return String 出荷ステータス(文字列)
	 */
	public String getStrShippingStatus() {
		return SelectShippingShippingStatus.getLabelName(getShippingStatus()
				.toString());
	}

	/**
	 * 出荷予定日(文字列)を取得します。
	 * @return String 出荷予定日(文字列)
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * 出荷予定日(文字列)を設定します。
	 * @param strScheduledShippingDate 出荷予定日(文字列)
	 */
	public void setStrScheduledShippingDate(
			final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * 希望納期(文字列)を取得します。
	 * @return String 希望納期(文字列)
	 */
	public String getStrSuggestedDeliverlimit() {
		return strSuggestedDeliverlimit;
	}

	/**
	 * 希望納期(文字列)を設定します。
	 * @param strSuggestedDeliverlimit 希望納期(文字列)
	 */
	public void setStrSuggestedDeliverlimit(
			final String strSuggestedDeliverlimit) {
		this.strSuggestedDeliverlimit = strSuggestedDeliverlimit;
	}

	/**
	 * 出荷指図数量(文字列)を取得します。
	 * @return String 出荷指図数量(文字列)
	 */
	public String getStrShippingInstruction() {
		return strShippingInstruction;
	}

	/**
	 * 出荷指図数量(文字列)を設定します。
	 * @param strShippingInstruction 出荷指図数量(文字列)
	 */
	public void setStrShippingInstruction(final String strShippingInstruction) {
		this.strShippingInstruction = strShippingInstruction;
	}

	/**
	 * 単位区分
	 * @return String 単位区分
	 */
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * 単位区分
	 * @param unitDivision 単位区分
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 指図量累計取得
	 * @return BigDecimal 指図量累計
	 */
	public BigDecimal getShippingInstructionSum() {
		return this.shippingInstructionSum;
	}

	/**
	 * 指図量累計設定
	 * @param shippingInstructionSum 指図量累計
	 */
	public void setShippingInstructionSum(
			final BigDecimal shippingInstructionSum) {
		this.shippingInstructionSum = shippingInstructionSum;
	}

	/**
	 * 指図量累計(文字列)取得
	 * @return BigDecimal 指図量累計(文字列)
	 */
	public String getStrShippingInstructionSum() {
		return this.strShippingInstructionSum;
	}

	/**
	 * 指図量累計(文字列)設定
	 * @param strShippingInstructionSum 指図量累計(文字列)
	 */
	public void setStrShippingInstructionSum(
			final String strShippingInstructionSum) {
		this.strShippingInstructionSum = strShippingInstructionSum;
	}

	/**
	 * deliveryAllAddress取得
	 * @return deliveryAllAddress deliveryAllAddress
	 */
	public String getDeliveryAllAddress() {
		return deliveryAllAddress;
	}

	/**
	 * deliveryAllAddress設定
	 * @param deliveryAllAddress deliveryAllAddress
	 */
	public void setDeliveryAllAddress(final String deliveryAllAddress) {
		this.deliveryAllAddress = deliveryAllAddress;
	}

}
