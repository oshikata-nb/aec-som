/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 受注検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class ShippingOrderSearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingOrderSearchListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "ORDER_HEAD";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション inputTantoCd */
	public static final String inputTantoCd_COLUMN = "INPUT_TANTO_CD";

	/** COLUMNアノテーション salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";

	/** COLUMNアノテーション suggestedDeliverlimit */
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション leadTime */
	public static final String leadTime_COLUMN = "LEAD_TIME";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryFare */
	public static final String carryFare_COLUMN = "CARRY_FARE";

	/** COLUMNアノテーション carryInvoiceFlag */
	public static final String carryInvoiceFlag_COLUMN = "CARRY_INVOICE_FLAG";

	/** COLUMNアノテーション orderPicture */
	public static final String orderPicture_COLUMN = "ORDER_PICTURE";

	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";

	/** COLUMNアノテーション orderSummery */
	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";

	/** COLUMNアノテーション slipPublishComp */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション slipPublishDate */
	public static final String slipPublishDate_COLUMN = "SLIP_PUBLISH_DATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 受注番号 */
	private String orderNo;

	/** 受注区分 */
	private BigDecimal orderDivision;

	/** 受注日 */
	private Timestamp orderDate;

	/** 担当部署コード */
	private String organizationCd;

	/** 入力担当者コード */
	private String inputTantoCd;

	/** 営業担当者コード */
	private String salesTantoCd;

	/** 得意先コード */
	private String venderCd;

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先宛先 */
	private String deliveryAddress;

	/** 帳合コード */
	private String balanceCd;

	/** ステータス */
	private BigDecimal status;

	/** 客先注文番号 */
	private String customerOrderNo;

	/** 受注金額 */
	private BigDecimal orderAmount;

	/** 希望納期 */
	private Timestamp suggestedDeliverlimit;

	/** 出荷予定日 */
	private Timestamp scheduledShippingDate;

	/** リードタイム */
	private BigDecimal leadTime;

	/** 納入予定日 */
	private Timestamp deliveryExpectedDate;

	/** 納入時刻 */
	private String deliveryExpectedTime;

	/** 運送会社コード */
	private String carryCd;

	/** 運賃 */
	private BigDecimal carryFare;

	/** 運賃請求フラグ */
	private BigDecimal carryInvoiceFlag;

	/** 注文書画像 */
	private String orderPicture;

	/** 納品書備考 */
	private String deliverySlipSummery;

	/** 受注時備考 */
	private String orderSummery;

	/** 伝票発行済区分 */
	private BigDecimal slipPublishComp;

	/** 伝票発行日時 */
	private Timestamp slipPublishDate;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 受注番号取得
	 * @return String 受注番号
	*/
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 受注番号設定
	 * @param orderNo 受注番号
	*/
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 受注区分取得
	 * @return BigDecimal 受注区分
	*/
	public BigDecimal getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * 受注区分設定
	 * @param orderDivision 受注区分
	*/
	public void setOrderDivision(final BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * 受注日取得
	 * @return Timestamp 受注日
	*/
	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * 受注日設定
	 * @param orderDate 受注日
	*/
	public void setOrderDate(final Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 担当部署コード取得
	 * @return String 担当部署コード
	*/
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * 担当部署コード設定
	 * @param organizationCd 担当部署コード
	*/
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 入力担当者コード取得
	 * @return String 入力担当者コード
	*/
	public String getInputTantoCd() {
		return this.inputTantoCd;
	}

	/**
	 * 入力担当者コード設定
	 * @param inputTantoCd 入力担当者コード
	*/
	public void setInputTantoCd(final String inputTantoCd) {
		this.inputTantoCd = inputTantoCd;
	}

	/**
	 * 営業担当者コード取得
	 * @return String 営業担当者コード
	*/
	public String getSalesTantoCd() {
		return this.salesTantoCd;
	}

	/**
	 * 営業担当者コード設定
	 * @param salesTantoCd 営業担当者コード
	*/
	public void setSalesTantoCd(final String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * 得意先コード取得
	 * @return String 得意先コード
	*/
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 得意先コード設定
	 * @param venderCd 得意先コード
	*/
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 納入先コード取得
	 * @return String 納入先コード
	*/
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * 納入先コード設定
	 * @param deliveryCd 納入先コード
	*/
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
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
	 * ステータス取得
	 * @return BigDecimal ステータス
	*/
	public BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * ステータス設定
	 * @param status ステータス
	*/
	public void setStatus(final BigDecimal status) {
		this.status = status;
	}

	/**
	 * 客先注文番号取得
	 * @return String 客先注文番号
	*/
	public String getCustomerOrderNo() {
		return this.customerOrderNo;
	}

	/**
	 * 客先注文番号設定
	 * @param customerOrderNo 客先注文番号
	*/
	public void setCustomerOrderNo(final String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * 受注金額取得
	 * @return BigDecimal 受注金額
	*/
	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	/**
	 * 受注金額設定
	 * @param orderAmount 受注金額
	*/
	public void setOrderAmount(final BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 希望納期取得
	 * @return Timestamp 希望納期
	*/
	public Timestamp getSuggestedDeliverlimit() {
		return this.suggestedDeliverlimit;
	}

	/**
	 * 希望納期設定
	 * @param suggestedDeliverlimit 希望納期
	*/
	public void setSuggestedDeliverlimit(final Timestamp suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * 出荷予定日取得
	 * @return Timestamp 出荷予定日
	*/
	public Timestamp getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * 出荷予定日設定
	 * @param scheduledShippingDate 出荷予定日
	*/
	public void setScheduledShippingDate(final Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

	/**
	 * リードタイム取得
	 * @return BigDecimal リードタイム
	*/
	public BigDecimal getLeadTime() {
		return this.leadTime;
	}

	/**
	 * リードタイム設定
	 * @param leadTime リードタイム
	*/
	public void setLeadTime(final BigDecimal leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * 納入予定日取得
	 * @return Timestamp 納入予定日
	*/
	public Timestamp getDeliveryExpectedDate() {
		return this.deliveryExpectedDate;
	}

	/**
	 * 納入予定日設定
	 * @param deliveryExpectedDate 納入予定日
	*/
	public void setDeliveryExpectedDate(final Timestamp deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * 納入時刻取得
	 * @return String 納入時刻
	*/
	public String getDeliveryExpectedTime() {
		return this.deliveryExpectedTime;
	}

	/**
	 * 納入時刻設定
	 * @param deliveryExpectedTime 納入時刻
	*/
	public void setDeliveryExpectedTime(final String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * 運送会社コード取得
	 * @return String 運送会社コード
	*/
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * 運送会社コード設定
	 * @param carryCd 運送会社コード
	*/
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * 運賃取得
	 * @return BigDecimal 運賃
	*/
	public BigDecimal getCarryFare() {
		return this.carryFare;
	}

	/**
	 * 運賃設定
	 * @param carryFare 運賃
	*/
	public void setCarryFare(final BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * 運賃請求フラグ取得
	 * @return BigDecimal 運賃請求フラグ
	*/
	public BigDecimal getCarryInvoiceFlag() {
		return this.carryInvoiceFlag;
	}

	/**
	 * 運賃請求フラグ設定
	 * @param carryInvoiceFlag 運賃請求フラグ
	*/
	public void setCarryInvoiceFlag(final BigDecimal carryInvoiceFlag) {
		this.carryInvoiceFlag = carryInvoiceFlag;
	}

	/**
	 * 注文書画像取得
	 * @return String 注文書画像
	*/
	public String getOrderPicture() {
		return this.orderPicture;
	}

	/**
	 * 注文書画像設定
	 * @param orderPicture 注文書画像
	*/
	public void setOrderPicture(final String orderPicture) {
		this.orderPicture = orderPicture;
	}

	/**
	 * 納品書備考取得
	 * @return String 納品書備考
	*/
	public String getDeliverySlipSummery() {
		return this.deliverySlipSummery;
	}

	/**
	 * 納品書備考設定
	 * @param deliverySlipSummery 納品書備考
	*/
	public void setDeliverySlipSummery(final String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}

	/**
	 * 受注時備考取得
	 * @return String 受注時備考
	*/
	public String getOrderSummery() {
		return this.orderSummery;
	}

	/**
	 * 受注時備考設定
	 * @param orderSummery 受注時備考
	*/
	public void setOrderSummery(final String orderSummery) {
		this.orderSummery = orderSummery;
	}

	/**
	 * 伝票発行済区分取得
	 * @return BigDecimal 伝票発行済区分
	*/
	public BigDecimal getSlipPublishComp() {
		return this.slipPublishComp;
	}

	/**
	 * 伝票発行済区分設定
	 * @param slipPublishComp 伝票発行済区分
	*/
	public void setSlipPublishComp(final BigDecimal slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * 伝票発行日時取得
	 * @return Timestamp 伝票発行日時
	*/
	public Timestamp getSlipPublishDate() {
		return this.slipPublishDate;
	}

	/**
	 * 伝票発行日時設定
	 * @param slipPublishDate 伝票発行日時
	*/
	public void setSlipPublishDate(final Timestamp slipPublishDate) {
		this.slipPublishDate = slipPublishDate;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	*/
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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

