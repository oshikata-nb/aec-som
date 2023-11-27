/*
 * Created on 2008/11/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.standardsheet;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 規格書マスタクラス.
 * @author tosco
 */
public class StandardSheetBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/** TABLEアノテーション. */
	public static final String TABLE = "STANDARD_SHEET";

	/**
	 * コンストラクタ.
	 */
	public StandardSheetBase() {
	}

	// 定数

	/** COLUMNアノテーション 規格書コード */
	public static final String stdSheetCd_COLUMN = "STD_SHEET_CD";

	/** COLUMNアノテーション 規格書名称. */
	public static final String stdSheetName_COLUMN = "STD_SHEET_NAME";

	/** COLUMNアノテーション 品目コード. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション 得意先コード. */
	public static final String customerCd_COLUMN = "CUSTOMER_CD";

	/** COLUMNアノテーション 納入先コード. */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション 規格書テンプレート. */
	public static final String labelCd_COLUMN = "LABEL_CD";

	/** COLUMNアノテーション 規格書貼付ラベル. */
	public static final String labelAddCd_COLUMN = "LABEL_ADD_CD";

	/** COLUMNアノテーション 登録者コード. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション 登録日 */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション 更新者コード. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション 更新日 */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション 品目名称 */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション 取引先名称 */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション 納入先名称 */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション ラベルテンプレートパス */
	public static final String labelPath_COLUMN = "LABEL_PATH";

	//フィールド

	/** 規格書コード */
	private String stdSheetCd;
	/** 規格書名称 */
	private String stdSheetName;
	/** 品目コード */
	private String itemCd;
	/** 得意先コード */
	private String customerCd;
	/** 納入先コード */
	private String deliveryCd;
	/** 規格書テンプレート */
	private String labelCd;
	/** 規格書貼付ラベル */
	private String labelAddCd;
	/** 登録者コード */
	private String inputorCd;
	/** 登録日 */
	private Timestamp inputDate;
	/** 更新者コード */
	private String updatorCd;
	/** 更新日 */
	private Timestamp updateDate;
	/** 品目名称 */
	private String itemName;
	/** 取引先名称 */
	private String venderName;
	/** 納入先名称 */
	private String deliveryName;
	/** ラベルテンプレートパス */
	private String labelPath;

	//メソッド


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
	 * 得意先コードを取得します。
	 * @return 得意先コード
	 */
	public String getCustomerCd() {
		return customerCd;
	}

	/**
	 * 得意先コードを設定します。
	 * @param customerCd 得意先コード
	 */
	public void setCustomerCd(final String customerCd) {
		this.customerCd = customerCd;
	}

	/**
	 * 納入先コードを取得します。
	 * @return 納入先コード
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * 納入先コードを設定します。
	 * @param deliveryCd 納入先コード
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * 登録日を取得します。
	 * @return 登録日
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * 登録日を設定します。
	 * @param inputDate 登録日
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者コードを取得します。
	 * @return 登録者コード
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * 登録者コードを設定します。
	 * @param inputorCd 登録者コード
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 品目コードを取得します。
	 * @return 品目コード
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 規格書貼付ラベルを取得します。
	 * @return 規格書貼付ラベル
	 */
	public String getLabelAddCd() {
		return labelAddCd;
	}

	/**
	 * 規格書貼付ラベルを設定します。
	 * @param labelAddCd 規格書貼付ラベル
	 */
	public void setLabelAddCd(final String labelAddCd) {
		this.labelAddCd = labelAddCd;
	}

	/**
	 * 規格書テンプレートを取得します。
	 * @return 規格書テンプレート
	 */
	public String getLabelCd() {
		return labelCd;
	}

	/**
	 * 規格書テンプレートを設定します。
	 * @param labelCd 規格書テンプレート
	 */
	public void setLabelCd(final String labelCd) {
		this.labelCd = labelCd;
	}

	/**
	 * 規格書コードを取得します。
	 * @return 規格書コード
	 */
	public String getStdSheetCd() {
		return stdSheetCd;
	}

	/**
	 * 規格書コードを設定します。
	 * @param stdSheetCd 規格書コード
	 */
	public void setStdSheetCd(final String stdSheetCd) {
		this.stdSheetCd = stdSheetCd;
	}

	/**
	 * 規格書名称を取得します。
	 * @return 規格書名称
	 */
	public String getStdSheetName() {
		return stdSheetName;
	}

	/**
	 * 規格書名称を設定します。
	 * @param stdSheetName 規格書名称
	 */
	public void setStdSheetName(final String stdSheetName) {
		this.stdSheetName = stdSheetName;
	}

	/**
	 * 更新日を取得します。
	 * @return 更新日
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日を設定します。
	 * @param updateDate 更新日
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者コードを取得します。
	 * @return 更新者コード
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * 更新者コードを設定します。
	 * @param updatorCd 更新者コード
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 納入先名称を取得します。
	 * @return 納入先名称
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * 納入先名称を設定します。
	 * @param deliveryName 納入先名称
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * 品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 取引先名称を取得します。
	 * @return 取引先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 取引先名称を設定します。
	 * @param venderName 取引先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * ラベルテンプレートパスを取得します。
	 * @return ラベルテンプレートパス
	 */
	public String getLabelPath() {
		return labelPath;
	}

	/**
	 * ラベルテンプレートパスを設定します。
	 * @param labelPath ラベルテンプレートパス
	 */
	public void setLabelPath(final String labelPath) {
		this.labelPath = labelPath;
	}

}

