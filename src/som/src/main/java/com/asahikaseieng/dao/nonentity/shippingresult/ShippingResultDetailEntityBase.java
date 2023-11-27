/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 出荷実績画面_出荷ヘッダーデータ格納クラス.
 * 
 * @author tosco
 */
public class ShippingResultDetailEntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultDetailEntityBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "SHIPPING";

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション shippingInstructDate */
	public static final String shippingInstructDate_COLUMN = "SHIPPING_INSTRUCT_DATE";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション summery */
	public static final String summery_COLUMN = "SUMMERY";

	/** COLUMNアノテーション deliveryComp */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション shippingResultDate */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション shippingSlipNo */
	public static final String shippingSlipNo_COLUMN = "SHIPPING_SLIP_NO";

	/** COLUMNアノテーション shippingSlipRowNo */
	public static final String shippingSlipRowNo_COLUMN = "SHIPPING_SLIP_ROW_NO";

	/** COLUMNアノテーション slipPublishComp */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション slipPublishDate */
	public static final String slipPublishDate_COLUMN = "SLIP_PUBLISH_DATE";

	/** COLUMNアノテーション slipShippingOrderComp */
	public static final String slipShippingOrderComp_COLUMN = "SLIP_SHIPPING_ORDER_COMP";

	/** COLUMNアノテーション slipShippingOrderDate */
	public static final String slipShippingOrderDate_COLUMN = "SLIP_SHIPPING_ORDER_DATE";

	/** COLUMNアノテーション slipShippingScheduleComp */
	public static final String slipShippingScheduleComp_COLUMN = "SLIP_SHIPPING_SCHEDULE_COMP";

	/** COLUMNアノテーション slipShippingScheduleDate */
	public static final String slipShippingScheduleDate_COLUMN = "SLIP_SHIPPING_SCHEDULE_DATE";

	/** COLUMNアノテーション slipShippingTagComp */
	public static final String slipShippingTagComp_COLUMN = "SLIP_SHIPPING_TAG_COMP";

	/** COLUMNアノテーション slipShippingTagDate */
	public static final String slipShippingTagDate_COLUMN = "SLIP_SHIPPING_TAG_DATE";

	/** COLUMNアノテーション slipShippingRequestComp. */
	public static final String slipShippingRequestComp_COLUMN = "SLIP_SHIPPING_REQUEST_COMP";

	/** COLUMNアノテーション slipShippingRequestDate. */
	public static final String slipShippingRequestDate_COLUMN = "SLIP_SHIPPING_REQUEST_DATE";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション suggestedDeliverlimit */
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";

	/** COLUMNアノテーション carryFare */
	public static final String carryFare_COLUMN = "CARRY_FARE";

	/** COLUMNアノテーション sendingOffNumber */
	public static final String sendingOffNumber_COLUMN = "SENDING_OFF_NUMBER";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション deliveryAllAddress */
	public static final String deliveryAllAddress_COLUMN = "DELIVERY_ALL_ADDRESS";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	//
	// インスタンスフィールド
	//

	/** 出荷番号 */
	private String shippingNo;

	/** 出荷指図日 */
	private Timestamp shippingInstructDate;

	/** 出荷予定日 */
	private Timestamp scheduledShippingDate;

	/** 出荷担当者コード */
	private String tantoCd;

	/** 受注番号 */
	private String orderNo;

	/** 行番号(受注) */
	private BigDecimal orderRowNo;

	/** 取引先区分:不要 */
	private String venderDivision;

	/** 取引先コード */
	private String venderCd;

	/** 納入先コード */
	private String deliveryCd;

	/** 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了 */
	private BigDecimal shippingStatus;

	/** 品目コード */
	private String itemCd;

	/** 摘要:未使用 */
	private String summery;

	/** 完納区分 */
	private BigDecimal deliveryComp;

	/** 出荷完了日 */
	private Timestamp shippingResultDate;

	/** 出荷伝票番号 */
	private String shippingSlipNo;

	/** 出荷伝票行番号 */
	private BigDecimal shippingSlipRowNo;

	/** 出荷伝票発行済区分(0:未発行 1:発行済) */
	private BigDecimal slipPublishComp;

	/** 出荷伝票発行日 */
	private Timestamp slipPublishDate;

	/** 出荷指図書発行済区分(0:未発行 1:発行済) */
	private BigDecimal slipShippingOrderComp;

	/** 出荷指図書発行日 */
	private Timestamp slipShippingOrderDate;

	/** 出荷予定表発行済区分(0:未発行 1:発行済) */
	private BigDecimal slipShippingScheduleComp;

	/** 出荷予定表発行日 */
	private Timestamp slipShippingScheduleDate;

	/** 出荷荷札,ペリカン伝票発行済区分(0:未発行 1:発行済) */
	private BigDecimal slipShippingTagComp;

	/** 出荷荷札,ペリカン伝票発行日 */
	private Timestamp slipShippingTagDate;

	/** 出荷依頼書発行済区分(0:未発行 1:発行済) */
	private java.math.BigDecimal slipShippingRequestComp;

	/** 出荷依頼書発行日 */
	private java.sql.Timestamp slipShippingRequestDate;

	/** 運送会社コード */
	private String carryCd;

	/** 希望納期 */
	private Timestamp suggestedDeliverlimit;

	/** 運賃 */
	private BigDecimal carryFare;

	/** 積出ナンバー */
	private String sendingOffNumber;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	private String deliveryAllAddress;

	/** 納入予定日 */
	private Timestamp deliveryExpectedDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 出荷番号取得
	 * @return String 出荷番号
	*/
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * 出荷番号設定
	 * @param shippingNo 出荷番号
	*/
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
	}

	/**
	 * 出荷指図日取得
	 * @return Timestamp 出荷指図日
	*/
	public Timestamp getShippingInstructDate() {
		return this.shippingInstructDate;
	}

	/**
	 * 出荷指図日設定
	 * @param shippingInstructDate 出荷指図日
	*/
	public void setShippingInstructDate(final Timestamp shippingInstructDate) {
		this.shippingInstructDate = shippingInstructDate;
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
	 * 出荷担当者コード取得
	 * @return String 出荷担当者コード
	*/
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 出荷担当者コード設定
	 * @param tantoCd 出荷担当者コード
	*/
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

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
	 * 行番号(受注)取得
	 * @return BigDecimal 行番号(受注)
	*/
	public BigDecimal getOrderRowNo() {
		return this.orderRowNo;
	}

	/**
	 * 行番号(受注)設定
	 * @param orderRowNo 行番号(受注)
	*/
	public void setOrderRowNo(final BigDecimal orderRowNo) {
		this.orderRowNo = orderRowNo;
	}

	/**
	 * 取引先区分:不要取得
	 * @return String 取引先区分:不要
	*/
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * 取引先区分:不要設定
	 * @param venderDivision 取引先区分:不要
	*/
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * 取引先コード取得
	 * @return String 取引先コード
	*/
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 取引先コード設定
	 * @param venderCd 取引先コード
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
	 * 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了取得
	 * @return BigDecimal 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
	*/
	public BigDecimal getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了設定
	 * @param shippingStatus 出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
	*/
	public void setShippingStatus(final BigDecimal shippingStatus) {
		this.shippingStatus = shippingStatus;
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
	 * 摘要:未使用取得
	 * @return String 摘要:未使用
	*/
	public String getSummery() {
		return this.summery;
	}

	/**
	 * 摘要:未使用設定
	 * @param summery 摘要:未使用
	*/
	public void setSummery(final String summery) {
		this.summery = summery;
	}

	/**
	 * 完納区分取得
	 * @return BigDecimal 完納区分
	*/
	public BigDecimal getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * 完納区分設定
	 * @param deliveryComp 完納区分
	*/
	public void setDeliveryComp(final BigDecimal deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * 出荷完了日取得
	 * @return Timestamp 出荷完了日
	*/
	public Timestamp getShippingResultDate() {
		return this.shippingResultDate;
	}

	/**
	 * 出荷完了日設定
	 * @param shippingResultDate 出荷完了日
	*/
	public void setShippingResultDate(final Timestamp shippingResultDate) {
		this.shippingResultDate = shippingResultDate;
	}

	/**
	 * 出荷伝票番号取得
	 * @return String 出荷伝票番号
	*/
	public String getShippingSlipNo() {
		return this.shippingSlipNo;
	}

	/**
	 * 出荷伝票番号設定
	 * @param shippingSlipNo 出荷伝票番号
	*/
	public void setShippingSlipNo(final String shippingSlipNo) {
		this.shippingSlipNo = shippingSlipNo;
	}

	/**
	 * 出荷伝票行番号取得
	 * @return BigDecimal 出荷伝票行番号
	*/
	public BigDecimal getShippingSlipRowNo() {
		return this.shippingSlipRowNo;
	}

	/**
	 * 出荷伝票行番号設定
	 * @param shippingSlipRowNo 出荷伝票行番号
	*/
	public void setShippingSlipRowNo(final BigDecimal shippingSlipRowNo) {
		this.shippingSlipRowNo = shippingSlipRowNo;
	}

	/**
	 * 出荷伝票発行済区分(0:未発行 1:発行済)取得
	 * @return BigDecimal 出荷伝票発行済区分(0:未発行 1:発行済)
	*/
	public BigDecimal getSlipPublishComp() {
		return this.slipPublishComp;
	}

	/**
	 * 出荷伝票発行済区分(0:未発行 1:発行済)設定
	 * @param slipPublishComp 出荷伝票発行済区分(0:未発行 1:発行済)
	*/
	public void setSlipPublishComp(final BigDecimal slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * 出荷伝票発行日取得
	 * @return Timestamp 出荷伝票発行日
	*/
	public Timestamp getSlipPublishDate() {
		return this.slipPublishDate;
	}

	/**
	 * 出荷伝票発行日設定
	 * @param slipPublishDate 出荷伝票発行日
	*/
	public void setSlipPublishDate(final Timestamp slipPublishDate) {
		this.slipPublishDate = slipPublishDate;
	}

	/**
	 * 出荷指図書発行済区分(0:未発行 1:発行済)取得
	 * @return BigDecimal 出荷指図書発行済区分(0:未発行 1:発行済)
	*/
	public BigDecimal getSlipShippingOrderComp() {
		return this.slipShippingOrderComp;
	}

	/**
	 * 出荷指図書発行済区分(0:未発行 1:発行済)設定
	 * @param slipShippingOrderComp 出荷指図書発行済区分(0:未発行 1:発行済)
	*/
	public void setSlipShippingOrderComp(final BigDecimal slipShippingOrderComp) {
		this.slipShippingOrderComp = slipShippingOrderComp;
	}

	/**
	 * 出荷指図書発行日取得
	 * @return Timestamp 出荷指図書発行日
	*/
	public Timestamp getSlipShippingOrderDate() {
		return this.slipShippingOrderDate;
	}

	/**
	 * 出荷指図書発行日設定
	 * @param slipShippingOrderDate 出荷指図書発行日
	*/
	public void setSlipShippingOrderDate(final Timestamp slipShippingOrderDate) {
		this.slipShippingOrderDate = slipShippingOrderDate;
	}

	/**
	 * 出荷予定表発行済区分(0:未発行 1:発行済)取得
	 * @return BigDecimal 出荷予定表発行済区分(0:未発行 1:発行済)
	*/
	public BigDecimal getSlipShippingScheduleComp() {
		return this.slipShippingScheduleComp;
	}

	/**
	 * 出荷予定表発行済区分(0:未発行 1:発行済)設定
	 * @param slipShippingScheduleComp 出荷予定表発行済区分(0:未発行 1:発行済)
	*/
	public void setSlipShippingScheduleComp(final BigDecimal slipShippingScheduleComp) {
		this.slipShippingScheduleComp = slipShippingScheduleComp;
	}

	/**
	 * 出荷予定表発行日取得
	 * @return Timestamp 出荷予定表発行日
	*/
	public Timestamp getSlipShippingScheduleDate() {
		return this.slipShippingScheduleDate;
	}

	/**
	 * 出荷予定表発行日設定
	 * @param slipShippingScheduleDate 出荷予定表発行日
	*/
	public void setSlipShippingScheduleDate(final Timestamp slipShippingScheduleDate) {
		this.slipShippingScheduleDate = slipShippingScheduleDate;
	}

	/**
	 * 出荷荷札,ペリカン伝票発行済区分(0:未発行 1:発行済)取得
	 * @return BigDecimal 出荷荷札,ペリカン伝票発行済区分(0:未発行 1:発行済)
	*/
	public BigDecimal getSlipShippingTagComp() {
		return this.slipShippingTagComp;
	}

	/**
	 * 出荷荷札,ペリカン伝票発行済区分(0:未発行 1:発行済)設定
	 * @param slipShippingTagComp 出荷荷札,ペリカン伝票発行済区分(0:未発行 1:発行済)
	*/
	public void setSlipShippingTagComp(final BigDecimal slipShippingTagComp) {
		this.slipShippingTagComp = slipShippingTagComp;
	}

	/**
	 * 出荷荷札,ペリカン伝票発行日取得
	 * @return Timestamp 出荷荷札,ペリカン伝票発行日
	*/
	public Timestamp getSlipShippingTagDate() {
		return this.slipShippingTagDate;
	}

	/**
	 * 出荷荷札,ペリカン伝票発行日設定
	 * @param slipShippingTagDate 出荷荷札,ペリカン伝票発行日
	*/
	public void setSlipShippingTagDate(final Timestamp slipShippingTagDate) {
		this.slipShippingTagDate = slipShippingTagDate;
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
	 * slipShippingRequestComp取得.
	 * @return slipShippingRequestComp
	 */
	public java.math.BigDecimal getSlipShippingRequestComp() {
		return this.slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestComp設定.
	 * @param slipShippingRequestComp slipShippingRequestComp
	 */
	public void setSlipShippingRequestComp(final java.math.BigDecimal slipShippingRequestComp) {
		this.slipShippingRequestComp = slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestDate取得.
	 * @return slipShippingRequestDate
	 */
	public java.sql.Timestamp getSlipShippingRequestDate() {
		return this.slipShippingRequestDate;
	}

	/**
	 * slipShippingRequestDate設定.
	 * @param slipShippingRequestDate slipShippingRequestDate
	 */
	public void setSlipShippingRequestDate(final java.sql.Timestamp slipShippingRequestDate) {
		this.slipShippingRequestDate = slipShippingRequestDate;
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
	 * 積出ナンバー取得
	 * @return String 積出ナンバー
	*/
	public String getSendingOffNumber() {
		return this.sendingOffNumber;
	}

	/**
	 * 積出ナンバー設定
	 * @param sendingOffNumber 積出ナンバー
	*/
	public void setSendingOffNumber(final String sendingOffNumber) {
		this.sendingOffNumber = sendingOffNumber;
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
