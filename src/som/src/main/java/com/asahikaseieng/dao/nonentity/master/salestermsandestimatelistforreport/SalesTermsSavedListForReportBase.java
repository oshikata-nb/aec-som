package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesTermsListForReportクラス.
 */
public class SalesTermsSavedListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsSavedListForReportBase() {
	}

	//
	// 定数
	//
	public static final String pkNo_COLUMN = "PK_NO";
	public static final String procType_COLUMN = "PROC_TYPE";
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";
	public static final String balanceCd_COLUMN = "BALANCE_CD";
	public static final String itemCd_COLUMN = "ITEM_CD";
	public static final String itemName_COLUMN = "ITEM_NAME";
	public static final String seq_COLUMN = "SEQ";
	public static final String tantoCd_COLUMN = "TANTO_CD";
	public static final String tantoName_COLUMN = "TANTO_NAME";
	public static final String inputDate_COLUMN = "INPUT_DATE";
	public static final String inputorCd_COLUMN = "INPUTOR_CD";
	public static final String inputorName_COLUMN = "INPUTOR_NAME";
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	public static final String updatorCd_COLUMN = "UPDATOR_CD";
	public static final String updatorName_COLUMN = "UPDATOR_NAME";
	
	//
	// インスタンスフィールド
	//
	private String pkNo;
	private String procType;
	private String deliveryCd;
	private String deliveryName;
	private String balanceCd;
	private String itemCd;
	private String itemName;
	private String seq;
	private String tantoCd;
	private String tantoName;
	private Timestamp inputDate;
	private String inputorCd;
	private String inputorName;
	private Timestamp updateDate;
	private String updatorCd;
	private String updatorName;

	//
	// インスタンスメソッド
	//

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

	/**
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}

	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * procTypeを取得します。
	 * @return procType
	 */
	public String getProcType() {
		return procType;
	}

	/**
	 * procTypeを設定します。
	 * @param procType procType
	 */
	public void setProcType(String procType) {
		this.procType = procType;
	}

	/**
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryNameを取得します。
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * deliveryNameを設定します。
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * seqを取得します。
	 * @return seq
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNameを取得します。
	 * @return tantoName
	 */
	public String getTantoName() {
		return tantoName;
	}

	/**
	 * tantoNameを設定します。
	 * @param tantoName tantoName
	 */
	public void setTantoName(String tantoName) {
		this.tantoName = tantoName;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputorNameを取得します。
	 * @return inputorName
	 */
	public String getInputorName() {
		return inputorName;
	}

	/**
	 * inputorNameを設定します。
	 * @param inputorName inputorName
	 */
	public void setInputorName(String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updatorNameを取得します。
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return updatorName;
	}

	/**
	 * updatorNameを設定します。
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	
}

