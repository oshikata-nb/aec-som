/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 受注検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class ShippingOrderSearchList extends ShippingOrderSearchListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション updateDateDetail */
	public static final String updateDateDetail_COLUMN = "UPDATE_DATE_DETAIL";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** 行番号(受注) */
	private BigDecimal rowNo;

	/** 納入先名称1 */
	private String deliveryName1;

	/** 得意先名称1 */
	private String venderName1;

	/** 運賃(文字列) */
	private String strCarryFare;

	/** 品目コード */
	private String itemCd;

	/** 受注数量 */
	private BigDecimal orderQty;

	/** 増付数 */
	private BigDecimal matss;

	/** 品名名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位区分 */
	private String unitDivision;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/** 受注数量(文字列) */
	private String strOrderQty;

	/** 増付数(文字列) */
	private String strMatss;

	/** 希望納期(文字列) */
	private String strSuggestedDeliverlimit;

	/** 出荷予定日(文字列) */
	private String strScheduledShippingDate;

	/** 受注詳細更新日時 */
	private Timestamp updateDateDetail;

	/** 納入予定日(文字列) */
	private String strDeliveryExpectedDate;

	/**
	 * コンストラクタ.
	 */
	public ShippingOrderSearchList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 日付・数値の編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrSuggestedDeliverlimit(AecDateUtils.dateFormat(
			getSuggestedDeliverlimit(), "yyyy/MM/dd"));
		setStrScheduledShippingDate(AecDateUtils.dateFormat(
			getScheduledShippingDate(), "yyyy/MM/dd"));

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
	 * 行番号(受注)取得
	 * @return BigDecimal 行番号(受注)
	 */
	public BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * 行番号(受注)設定
	 * @param rowNo 行番号(受注)
	 */
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 納入先名称1取得
	 * @return String 納入先名称1
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * 納入先名称1設定
	 * @param deliveryName1 納入先名称1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * 得意先名称1取得
	 * @return String 得意先名称1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * 得意先名称1設定
	 * @param venderName1 得意先名称1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * 運賃(文字列)取得
	 * @return String 運賃(文字列)
	 */
	public String getStrCarryFare() {
		return this.strCarryFare;
	}

	/**
	 * 運賃(文字列)設定
	 * @param strCarryFare 運賃(文字列)
	 */
	public void setStrCarryFare(final String strCarryFare) {
		this.strCarryFare = strCarryFare;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
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
	 * 他社コード取得
	 * @return String 他社コード
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード設定
	 * @param otherCompanyCd1 他社コード
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
	 * 小数点桁数取得
	 * @return String 小数点桁数
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数設定
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分取得
	 * @return String 端数区分
	 */
	public String getRound() {
		return round;
	}

	/**
	 * 端数区分設定
	 * @param round 端数区分
	 */
	public void setRound(final String round) {
		this.round = round;
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
	 * 受注数量(文字列)を取得します。
	 * @return String 受注数量(文字列)
	 */
	public String getStrOrderQty() {
		return strOrderQty;
	}

	/**
	 * 受注数量(文字列)を設定します。
	 * @param strOrderQty 受注数量(文字列)
	 */
	public void setStrOrderQty(final String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	/**
	 * 増付数(文字列)を取得します。
	 * @return String 増付数(文字列)
	 */
	public String getStrMatss() {
		return strMatss;
	}

	/**
	 * 増付数(文字列)を設定します。
	 * @param strMatss 増付数(文字列)
	 */
	public void setStrMatss(final String strMatss) {
		this.strMatss = strMatss;
	}

	/**
	 * 受注詳細更新日時取得
	 * @return Timestamp 受注詳細更新日時
	 */
	public Timestamp getUpdateDateDetail() {
		return this.updateDateDetail;
	}

	/**
	 * 受注詳細更新日時設定
	 * @param updateDateDetail 受注詳細更新日時
	 */
	public void setUpdateDateDetail(final Timestamp updateDateDetail) {
		this.updateDateDetail = updateDateDetail;
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
