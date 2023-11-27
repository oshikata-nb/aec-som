/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buying;

import com.asahikaseieng.app.comboboxes.SelectStockingStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 仕入一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class BuyingList extends BuyingListBase implements
		PropertyTransferCallbacker {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** 取引先名称(仕入先名称) */
	private String venderName1;

	/** 取引先略称(仕入先略称) */
	private String venderShortedName;

	/** 品目名称 */
	private String itemQueueName;

	/** 部署名称 */
	private String organizationName;

	/** 担当者名称 */
	private String tantoNm;

	/** チェックボックス */
	private boolean slipBuyingCheckBox;

	/** 発行済チェックボックス(Bool) */
	private boolean boolSlipPublishComp;

	// /** ロケーション名称 */
	// private String locationName;
	// /** 仕入ステータス名称 */
	// private String status2Name;

	/** 分類コード名称 */
	private String categoryName;

	/** 仕入日 */
	private String strStockingDate;

	/** 数量 */
	private String strStockingQuantity;

	/** 単価 */
	private String strHousingUnitprice;

	/** 金額 */
	private String strStockingAmount;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/**
	 * コンストラクタ.
	 */
	public BuyingList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 取得した日付をyyyy/MM/ddに変換し、セット
		setStrStockingDate(AecDateUtils.dateFormat(getStockingDate(),
			"yyyy/MM/dd"));

		// 発行済フラグ
		boolean slipBuyingCheckBox = getSlipIssueDivision().toString().equals(
			"1");
		setBoolSlipPublishComp(slipBuyingCheckBox);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 仕入日取得(yyyy/MM/dd)
	 * @return strStockingDate 仕入日
	 */
	public String getStrStockingDate() {
		return strStockingDate;
	}

	/**
	 * 仕入日設定(yyyy/MM/dd)
	 * @param strStockingDate 仕入日
	 */
	public void setStrStockingDate(final String strStockingDate) {
		this.strStockingDate = strStockingDate;
	}

	/**
	 * 数量取得
	 * @return strStockingQuantity 数量
	 */
	public String getStrStockingQuantity() {
		return strStockingQuantity;
	}

	/**
	 * 数量設定
	 * @param strStockingQuantity 数量
	 */
	public void setStrStockingQuantity(final String strStockingQuantity) {
		this.strStockingQuantity = strStockingQuantity;
	}

	/**
	 * 単価取得
	 * @return strStockingAmount 単価
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 単価設定
	 * @param strStockingAmount 単価
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
	}

	/**
	 * 金額取得
	 * @return strHousingUnitprice 金額
	 */
	public String getStrHousingUnitprice() {
		return strHousingUnitprice;
	}

	/**
	 * 金額設定
	 * @param strHousingUnitprice 金額
	 */
	public void setStrHousingUnitprice(final String strHousingUnitprice) {
		this.strHousingUnitprice = strHousingUnitprice;
	}

	/**
	 * 品目名称取得
	 * @return itemQueueName 品目名称
	 */
	public String getItemQueueName() {
		return itemQueueName;
	}

	/**
	 * 品目名称設定
	 * @param itemQueueName 品目名称
	 */
	public void setItemQueueName(final String itemQueueName) {
		this.itemQueueName = itemQueueName;
	}

	/**
	 * 仕入ステータス取得
	 * @return String 仕入ステータスの名称
	 */
	public String getStatus2Name() {
		String ret = null;
		// 一覧の仕入ステータス列に表示する文字列のセット

		// 買掛更新フラグ、支払更新フラグのどちらかが1:処理済の場合、
		// 「月次更新済」と表示する
		if (getPayableUpdateDivision() != null) {
			if (getPayableUpdateDivision().equals("1")) {
				ret = "月次更新済";
			}
		}
		if (getPaymentUpdateDivision() != null) {
			if (getPaymentUpdateDivision().equals("1")) {
				ret = "月次更新済";
			}
		}
		if (ret == null) {
			if (getStatus2() != null) {
				// 上記以外は、仕入ステータスの値より名称を取得してセットする。
				ret = SelectStockingStatus
						.getLabelName(getStatus2().toString());
			}
		}
		return ret;
	}

	/**
	 * 分類コード名称取得
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類コード名称設定
	 * @param categoryName 分類コード名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 部署名称取得
	 * @return organizationName 部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称設定
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 担当者名称取得
	 * @return tantoNm 担当者名称
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 担当者名称設定
	 * @param tantoNm 担当者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 取引先名称取得(仕入先名称)
	 * @return venderName1 取引先名称
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 取引先名称設定(仕入先名称)
	 * @param venderName1 取引先名称
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
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
	 * 運用管理単位取得
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位設定
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * slipBuyingCheckBox取得
	 * @return slipBuyingCheckBox
	 */
	public boolean getSlipBuyingCheckBox() {
		return slipBuyingCheckBox;
	}

	/**
	 * slipBuyingCheckBox設定
	 * @param slipBuyingCheckBox slipBuyingCheckBox
	 */
	public void setSlipBuyingCheckBox(final boolean slipBuyingCheckBox) {
		this.slipBuyingCheckBox = slipBuyingCheckBox;
	}

	/**
	 * boolSlipPublishComp取得
	 * @return boolSlipPublishComp
	 */
	public boolean isBoolSlipPublishComp() {
		return boolSlipPublishComp;
	}

	/**
	 * boolSlipPublishComp設定
	 * @param boolSlipPublishComp boolSlipPublishComp
	 */
	public void setBoolSlipPublishComp(final boolean boolSlipPublishComp) {
		this.boolSlipPublishComp = boolSlipPublishComp;
	}

}
