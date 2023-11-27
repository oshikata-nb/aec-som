/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipsales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売上伝票出力画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class SlipSalesListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipSalesListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SALES";

	/** COLUMNアノテーション salesDate */
	public static final String salesDate_COLUMN = "SALES_DATE";

	/** COLUMNアノテーション salesNo */
	public static final String salesNo_COLUMN = "SALES_NO";

	/** COLUMNアノテーション salesSlipNo */
	public static final String salesSlipNo_COLUMN = "SALES_SLIP_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション searchKana */
	public static final String searchKana_COLUMN = "SEARCH_KANA";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション slipPublishComp */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryDivisionName */
	public static final String categoryDivisionName_COLUMN = "CATEGORY_DIVISION_NAME";

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	/** 売上番号 */
	private String salesNo;

	/** 売上伝票NO */
	private String salesSlipNo;

	/** 分類コード */
	private String categoryDivision;

	/** 売上区分名称 */
	private String categoryDivisionName;

	/** 勘定年月 */
	private String accountYears;

	/** 受注番号 */
	private String orderNo;

	/** 売上日 */
	private Timestamp salesDate;

	/** 得意先コード */
	private String venderCd;

	/** 得意先名称１ */
	private String venderName1;

	/** 得意先名称２ */
	private String venderName2;

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先名称１ */
	private String deliveryName1;

	/** 納入先名称２ */
	private String deliveryName2;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 伝票発行済区分 */
	private BigDecimal slipPublishComp;

	/** ロケーションコード(上位ロケーション ) */
	private String locationCd;

	private java.sql.Timestamp updateDate;

	/** 得意先略称 */
	private String venderShortedName;

	/** 納入先略称 */
	private String searchKana;

	//
	// インスタンスメソッド
	//

	/**
	 * 売上番号取得
	 * @return String 売上番号
	 */
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * 売上番号設定
	 * @param salesNo 売上番号
	 */
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * 分類コード取得
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 勘定年月取得
	 * @return String 勘定年月
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * 勘定年月設定
	 * @param accountYears 勘定年月
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
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
	 * 売上日取得
	 * @return Timestamp 売上日
	 */
	public Timestamp getSalesDate() {
		return this.salesDate;
	}

	/**
	 * 売上日設定
	 * @param salesDate 売上日
	 */
	public void setSalesDate(final Timestamp salesDate) {
		this.salesDate = salesDate;
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
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryName2を取得します。
	 * @return deliveryName2
	 */
	public String getDeliveryName2() {
		return deliveryName2;
	}

	/**
	 * deliveryName2を設定します。
	 * @param deliveryName2 deliveryName2
	 */
	public void setDeliveryName2(final String deliveryName2) {
		this.deliveryName2 = deliveryName2;
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
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * locationCdを取得します。
	 * @return locationCd
	 */
	public String getLocationCd() {
		return locationCd;
	}

	/**
	 * locationCdを設定します。
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * otherCompanyCd1を取得します。
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1を設定します。
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * styleOfPackingを取得します。
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * styleOfPackingを設定します。
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2を取得します。
	 * @return venderName2
	 */
	public String getVenderName2() {
		return venderName2;
	}

	/**
	 * venderName2を設定します。
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * categoryDivisionNameを取得します。
	 * @return categoryDivisionName
	 */
	public String getCategoryDivisionName() {
		return categoryDivisionName;
	}

	/**
	 * categoryDivisionNameを設定します。
	 * @param categoryDivisionName categoryDivisionName
	 */
	public void setCategoryDivisionName(final String categoryDivisionName) {
		this.categoryDivisionName = categoryDivisionName;
	}

	/**
	 * salesSlipNoを取得します。
	 * @return salesSlipNo
	 */
	public String getSalesSlipNo() {
		return salesSlipNo;
	}

	/**
	 * salesSlipNoを設定します。
	 * @param salesSlipNo salesSlipNo
	 */
	public void setSalesSlipNo(final String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
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
}
