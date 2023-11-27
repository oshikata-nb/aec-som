/*
 * Created on 2020/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderrecieve.ordervenderdelivery;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderVenderDeliveryBaseクラス.
 * @author 
 */
public class OrderVenderDeliveryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderVenderDeliveryBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ORDER_VENDER_DELIVERY";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_GROUP_CD";

	/** COLUMNアノテーション somDeliveryCd */
	public static final String somDeliveryCd_COLUMN = "SOM_DELIVERY_CD";
	
	/** COLUMNアノテーション ctmDeliveryCd1 */
	public static final String ctmDeliveryCd1_COLUMN = "CTM_DELIVERY_CD_01";
	
	/** COLUMNアノテーション ctmDeliveryCd2 */
	public static final String ctmDeliveryCd2_COLUMN = "CTM_DELIVERY_CD_02";
	
	/** COLUMNアノテーション ctmDeliveryCd3 */
	public static final String ctmDeliveryCd3_COLUMN = "CTM_DELIVERY_CD_03";
	
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
	
	private String venderGroupCd;
	
	private String somDeliveryCd;
	
	private String ctmDeliveryCd1;
	
	private String ctmDeliveryCd2;
	
	private String ctmDeliveryCd3;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * venderGroupCd取得.
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return this.venderGroupCd;
	}

	/**
	 * venderGroupCd設定.
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(final String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}
	

	/**
	 * somDeliveryCdを取得します。
	 * @return somDeliveryCd
	 */
	public String getSomDeliveryCd() {
		return somDeliveryCd;
	}

	/**
	 * somDeliveryCdを設定します。
	 * @param somDeliveryCd somDeliveryCd
	 */
	public void setSomDeliveryCd(String somDeliveryCd) {
		this.somDeliveryCd = somDeliveryCd;
	}

	/**
	 * ctmDeliveryCd1を取得します。
	 * @return ctmDeliveryCd1
	 */
	public String getCtmDeliveryCd1() {
		return ctmDeliveryCd1;
	}

	/**
	 * ctmDeliveryCd1を設定します。
	 * @param ctmDeliveryCd1 ctmDeliveryCd1
	 */
	public void setCtmDeliveryCd1(String ctmDeliveryCd1) {
		this.ctmDeliveryCd1 = ctmDeliveryCd1;
	}

	/**
	 * ctmDeliveryCd2を取得します。
	 * @return ctmDeliveryCd2
	 */
	public String getCtmDeliveryCd2() {
		return ctmDeliveryCd2;
	}

	/**
	 * ctmDeliveryCd2を設定します。
	 * @param ctmDeliveryCd2 ctmDeliveryCd2
	 */
	public void setCtmDeliveryCd2(String ctmDeliveryCd2) {
		this.ctmDeliveryCd2 = ctmDeliveryCd2;
	}

	/**
	 * ctmDeliveryCd3を取得します。
	 * @return ctmDeliveryCd3
	 */
	public String getCtmDeliveryCd3() {
		return ctmDeliveryCd3;
	}

	/**
	 * ctmDeliveryCd3を設定します。
	 * @param ctmDeliveryCd3 ctmDeliveryCd3
	 */
	public void setCtmDeliveryCd3(String ctmDeliveryCd3) {
		this.ctmDeliveryCd3 = ctmDeliveryCd3;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
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
