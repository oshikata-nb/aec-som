/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectShippingResultShippingStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 出荷実績詳細画面_出荷ヘッダー表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingResultDetailEntity extends ShippingResultDetailEntityBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemCd */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション searchKana */
	public static final String searchKana_COLUMN = "SEARCH_KANA";

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション carry_name1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carry_name2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** 出荷指図日(文字列) */
	private String strShippingInstructDate;

	/** 納入先名称 */
	private String deliveryName;

	/** 納入先略称 */
	private String searchKana;

	/** 納入先宛先 */
	private String deliveryAddress;

	/** 品名名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 帳合コード */
	private String balanceCd;

	/** 希望納期(文字列) */
	private String strSuggestedDeliverlimit;

	/** 出荷予定日(文字列) */
	private String strScheduledShippingDate;

	/** 出荷完了日(文字列) */
	private String strShippingResultDate;

	/** 運送会社名称1 */
	private String carryName1;

	/** 運送会社名称2 */
	private String carryName2;

	/** 受注数量 */
	private BigDecimal orderQty;

	/** 増付数 */
	private BigDecimal matss;

	/** 単位区分 */
	private String unitDivision;

	/** 納入予定日(文字列) */
	private String strDeliveryExpectedDate;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultDetailEntity() {
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
		setStrShippingResultDate(AecDateUtils.dateFormat(
			getShippingResultDate(), "yyyy/MM/dd"));

		setStrDeliveryExpectedDate(AecDateUtils.dateFormat(
			getDeliveryExpectedDate(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 出荷ステータス(文字列)を取得します。
	 * @return String 出荷ステータス(文字列)
	 */
	public String getStrShippingStatus() {
		return SelectShippingResultShippingStatus
				.getLabelName(getShippingStatus().toString());
	}

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
	 * searchKana取得
	 * @return searchKana searchKana
	 */
	public String getSearchKana() {
		return searchKana;
	}

	/**
	 * searchKana設定
	 * @param searchKana searchKana
	 */
	public void setSearchKana(final String searchKana) {
		this.searchKana = searchKana;
	}

	/**
	 * 納入先宛先取得
	 * @return String 納入先宛先
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * 納入先宛先設定
	 * @param deliveryAddress 納入先宛先
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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
	 * 帳合コード取得
	 * @return String 帳合コード
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * 帳合コード設定
	 * @param balanceCd 帳合コード
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
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
	 * 出荷完了日(文字列)を取得します。
	 * @return String 出荷完了日(文字列)
	 */
	public String getStrShippingResultDate() {
		return strShippingResultDate;
	}

	/**
	 * 出荷完了日(文字列)を設定します。
	 * @param strShippingResultDate 出荷完了日(文字列)
	 */
	public void setStrShippingResultDate(final String strShippingResultDate) {
		this.strShippingResultDate = strShippingResultDate;
	}

	/**
	 * 運送会社名称1取得
	 * @return String 運送会社名称1
	 */
	public String getCarryName1() {
		return this.carryName1;
	}

	/**
	 * 運送会社名称1設定
	 * @param carryName1 運送会社名称1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * 運送会社名称2取得
	 * @return String 運送会社名称2
	 */
	public String getCarryName2() {
		return this.carryName2;
	}

	/**
	 * 運送会社名称2設定
	 * @param carryName2 運送会社名称2
	 */
	public void setCarryName2(final String carryName2) {
		this.carryName2 = carryName2;
	}

	/**
	 * 運送会社名称取得
	 * @return String 運送会社名称
	 */
	public String getCarryName() {
		return this.carryName1;
	}

	/**
	 * 受注数量取得
	 * @return BigDecimal 受注数量
	 */
	public BigDecimal getOrderQty() {
		return this.orderQty;
	}

	/**
	 * 受注数量設定
	 * @param orderQty 受注数量
	 */
	public void setOrderQty(final BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * 増付数取得
	 * @return BigDecimal 増付数
	 */
	public BigDecimal getMatss() {
		return this.matss;
	}

	/**
	 * 増付数設定
	 * @param matss 増付数
	 */
	public void setMatss(final BigDecimal matss) {
		this.matss = matss;
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
	 * 納入予定日設定
	 * @return strDeliveryExpectedDate 納入予定日
	 */
	public String getStrDeliveryExpectedDate() {
		return strDeliveryExpectedDate;
	}

	/**
	 * 納入予定日設定
	 * @param strDeliveryExpectedDate 納入予定日
	 */
	public void setStrDeliveryExpectedDate(final String strDeliveryExpectedDate) {
		this.strDeliveryExpectedDate = strDeliveryExpectedDate;
	}

}
