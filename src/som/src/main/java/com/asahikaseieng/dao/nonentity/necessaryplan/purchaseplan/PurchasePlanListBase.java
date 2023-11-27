/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.necessaryplan.purchaseplan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 購買計画テーブルの列定義
 * @author tosco
 */
public class PurchasePlanListBase implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** TABLEアノテーション. */
	public static final String TABLE = "PURCHASE_PLAN";

	//COLUMNアノテーション
	/** COLUMNアノテーション 購買計画番号 */
	public static final String planNo_COLUMN = "PLAN_NO";
	/** COLUMNアノテーション 品目コード */
	public static final String itemCd_COLUMN = "ITEM_CD";
	/** COLUMNアノテーション 計画発注日 */
	public static final String orderDate_COLUMN = "ORDER_DATE";
	/** COLUMNアノテーション 計画納期 */
	public static final String dueDate_COLUMN = "DUE_DATE";
	/** COLUMNアノテーション 仕入先コード */
	public static final String venderCd_COLUMN = "VENDER_CD";
	/** COLUMNアノテーション 発注計画数 */
	public static final String planedQty_COLUMN = "PLANED_QTY";
	/** COLUMNアノテーション 単位 */
	public static final String unit_COLUMN = "UNIT";
	/** COLUMNアノテーション 登録日 */
	public static final String inputDate_COLUMN = "INPUT_DATE";
	/** COLUMNアノテーション 登録者 */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";
	/** COLUMNアノテーション 更新日 */
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	/** COLUMNアノテーション 更新者 */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";
	/** COLUMNアノテーション 仕入先名称 */
	public static final String venderName_COLUMN = "VENDER_NAME";
	/** COLUMNアノテーション 品目名称 */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション 単位名称 */
	public static final String unitName_COLUMN = "UNIT_NAME";

	//フィールド
	/** 購買計画番号 */
	private String planNo;
	/** 品目コード */
	private String itemCd;
	/** 計画発注日 */
	private Timestamp orderDate;
	/** 計画納期 */
	private Timestamp dueDate;
	/** 仕入先コード */
	private String venderCd;
	/** 発注計画数 */
	private BigDecimal planedQty;
	/** 単位 */
	private String unit;
	/** 登録日 */
	private Timestamp inputDate;
	/** 登録者 */
	private String inputorCd;
	/** 更新日 */
	private Timestamp updateDate;
	/** 更新者 */
	private String updatorCd;
	/** 仕入先名称 */
	private String venderName;
	/** 品目名称 */
	private String itemName;
	/** 単位名称 */
	private String unitName;

	/**
	 * コンストラクタ
	 */
	public PurchasePlanListBase() {
	}

	/**
	 * 計画納期を取得します。
	 * @return 計画納期
	 */
	public Timestamp getDueDate() {
		return dueDate;
	}

	/**
	 * 計画納期を設定します。
	 * @param dueDate 計画納期
	 */
	public void setDueDate(final Timestamp dueDate) {
		this.dueDate = dueDate;
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
	 * 登録者を取得します。
	 * @return 登録者
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * 登録者を設定します。
	 * @param inputorCd 登録者
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
	 * 計画発注日を取得します。
	 * @return 計画発注日
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * 計画発注日を設定します。
	 * @param orderDate 計画発注日
	 */
	public void setOrderDate(final Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 発注計画数を取得します。
	 * @return 発注計画数
	 */
	public BigDecimal getPlanedQty() {
		return planedQty;
	}

	/**
	 * 発注計画数を設定します。
	 * @param planedQty 発注計画数
	 */
	public void setPlanedQty(final BigDecimal planedQty) {
		this.planedQty = planedQty;
	}

	/**
	 * 購買計画番号を取得します。
	 * @return 購買計画番号
	 */
	public String getPlanNo() {
		return planNo;
	}

	/**
	 * 購買計画番号を設定します。
	 * @param planNo 購買計画番号
	 */
	public void setPlanNo(final String planNo) {
		this.planNo = planNo;
	}

	/**
	 * 単位を取得します。
	 * @return 単位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位を設定します。
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
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
	 * 更新者を取得します。
	 * @return 更新者
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * 更新者を設定します。
	 * @param updatorCd 更新者
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 仕入先コードを取得します。
	 * @return 仕入先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 仕入先コードを設定します。
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
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
	 * 仕入先名称を取得します。
	 * @return 仕入先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先名称を設定します。
	 * @param venderName 仕入先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 単位名称を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位名称を設定します。
	 * @param unitName 単位名称
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}


}
