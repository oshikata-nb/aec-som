/*
 * Created on 2020/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderrecieve.ordervenderitem;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderVenderItemBaseクラス.
 * @author 
 */
public class OrderVenderItemBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderVenderItemBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ORDER_VENDER_ITEM";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_GROUP_CD";

	/** COLUMNアノテーション somItemCd */
	public static final String somItemCd_COLUMN = "SOM_ITEM_CD";
	
	/** COLUMNアノテーション ctmItemCd1 */
	public static final String ctmItemCd1_COLUMN = "CTM_ITEM_CD_01";
	
	/** COLUMNアノテーション ctmItemCd2 */
	public static final String ctmItemCd2_COLUMN = "CTM_ITEM_CD_02";
	
	/** COLUMNアノテーション ctmItemCd3 */
	public static final String ctmItemCd3_COLUMN = "CTM_ITEM_CD_03";
	
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
	
	private String somItemCd;
	
	private String ctmItemCd1;
	
	private String ctmItemCd2;
	
	private String ctmItemCd3;

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
	 * somItemCdを取得します。
	 * @return somItemCd
	 */
	public String getSomItemCd() {
		return somItemCd;
	}

	/**
	 * somItemCdを設定します。
	 * @param somItemCd somItemCd
	 */
	public void setSomItemCd(String somItemCd) {
		this.somItemCd = somItemCd;
	}

	/**
	 * ctmItemCd1を取得します。
	 * @return ctmItemCd1
	 */
	public String getCtmItemCd1() {
		return ctmItemCd1;
	}

	/**
	 * ctmItemCd1を設定します。
	 * @param ctmItemCd1 ctmItemCd1
	 */
	public void setCtmItemCd1(String ctmItemCd1) {
		this.ctmItemCd1 = ctmItemCd1;
	}

	/**
	 * ctmItemCd2を取得します。
	 * @return ctmItemCd2
	 */
	public String getCtmItemCd2() {
		return ctmItemCd2;
	}

	/**
	 * ctmItemCd2を設定します。
	 * @param ctmItemCd2 ctmItemCd2
	 */
	public void setCtmItemCd2(String ctmItemCd2) {
		this.ctmItemCd2 = ctmItemCd2;
	}

	/**
	 * ctmItemCd3を取得します。
	 * @return ctmItemCd3
	 */
	public String getCtmItemCd3() {
		return ctmItemCd3;
	}

	/**
	 * ctmItemCd3を設定します。
	 * @param ctmItemCd3 ctmItemCd3
	 */
	public void setCtmItemCd3(String ctmItemCd3) {
		this.ctmItemCd3 = ctmItemCd3;
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
