/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.carryshipping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 運送店別出荷指図画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class CarryShippingListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryShippingListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SHIPPING_TEMP";

	/** COLUMNアノテーション shippingInstructDate. */
	public static final String shippingInstructDate_COLUMN = "SHIPPING_INSTRUCT_DATE";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd. */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName. */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション carryCd. */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション sendingOffNumber. */
	public static final String sendingOffNumber_COLUMN = "SENDING_OFF_NUMBER";

	/** COLUMNアノテーション shippingOrderSendCompFlag. */
	public static final String shippingOrderSendCompFlag_COLUMN = "SHIPPING_ORDER_SEND_COMP_FLAG";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName. */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション itemUnit. */
	public static final String itemUnit_COLUMN = "ITEM_UNIT";

	/** COLUMNアノテーション shippingResultDate. */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 出荷予定日 */
	private Timestamp shippingInstructDate;

	/** 取引先コード */
	private String venderCd;

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先名称 */
	private String deliveryName;

	/** 運送会社コード */
	private String carryCd;

	/** 積出ナンバー */
	private String sendingOffNumber;

	/** 指図送信済みフラグ */
	private BigDecimal shippingOrderSendCompFlag;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 単位 */
	private String itemUnit;

	/** 出荷完了日 */
	private Timestamp shippingResultDate;

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
	 * 出荷予定日取得.
	 * @return shippingInstructDate
	 */
	public Timestamp getShippingInstructDate() {
		return this.shippingInstructDate;
	}

	/**
	 * 出荷予定日設定.
	 * @param shippingInstructDate 出荷予定日
	 */
	public void setShippingInstructDate(final Timestamp shippingInstructDate) {
		this.shippingInstructDate = shippingInstructDate;
	}

	/**
	 * 取引先コード取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 取引先コード設定.
	 * @param venderCd 取引先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 納入先コード取得.
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * 納入先コード設定.
	 * @param deliveryCd 納入先コード
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * 納入先名称取得.
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return this.deliveryName;
	}

	/**
	 * 納入先名称設定.
	 * @param deliveryName 納入先名称
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * 運送会社コード取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * 運送会社コード設定.
	 * @param carryCd 運送会社コード
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * 積出ナンバー取得.
	 * @return sendingOffNumber
	 */
	public String getSendingOffNumber() {
		return this.sendingOffNumber;
	}

	/**
	 * 積出ナンバー設定.
	 * @param sendingOffNumber 積出ナンバー
	 */
	public void setSendingOffNumber(final String sendingOffNumber) {
		this.sendingOffNumber = sendingOffNumber;
	}

	/**
	 * 指図送信済みフラグ取得.
	 * @return shippingOrderSendCompFlag
	 */
	public BigDecimal getShippingOrderSendCompFlag() {
		return this.shippingOrderSendCompFlag;
	}

	/**
	 * 指図送信済みフラグ設定.
	 * @param shippingOrderSendCompFlag 指図送信済みフラグ
	 */
	public void setShippingOrderSendCompFlag(final BigDecimal shippingOrderSendCompFlag) {
		this.shippingOrderSendCompFlag = shippingOrderSendCompFlag;
	}

	/**
	 * 品目コード取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 単位取得.
	 * @return itemUnit
	 */
	public String getItemUnit() {
		return this.itemUnit;
	}

	/**
	 * 単位設定.
	 * @param itemUnit 単位
	 */
	public void setItemUnit(final String itemUnit) {
		this.itemUnit = itemUnit;
	}

	/**
	 * 出荷完了日取得.
	 * @return shippingResultDate
	 */
	public Timestamp getShippingResultDate() {
		return this.shippingResultDate;
	}

	/**
	 * 出荷完了日設定.
	 * @param shippingResultDate 出荷完了日
	 */
	public void setShippingResultDate(final Timestamp shippingResultDate) {
		this.shippingResultDate = shippingResultDate;
	}

	/**
	 * 登録日時取得.
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定.
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定.
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得.
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定.
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
