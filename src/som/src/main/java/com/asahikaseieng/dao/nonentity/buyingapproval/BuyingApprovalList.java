/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyingapproval;

import com.asahikaseieng.app.comboboxes.SelectStockingStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 仕入承認画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class BuyingApprovalList extends BuyingApprovalListBase implements
		PropertyTransferCallbacker {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション paymentInvoiceCd */
	public static final String paymentInvoiceCd_COLUMN = "PAYMENT_INVOICE_CD";

	/** COLUMNアノテーション venderPaymentName */
	public static final String venderPaymentName_COLUMN = "VENDER_PAYMENT_NAME";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** チェックボックス */
	private boolean approvalCheckBox;

	/** 品目名称 */
	private String itemQueueName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 仕入先 */
	private String venderName1;

	/** 支払先コード */
	private String paymentInvoiceCd;

	/** 支払先名称 */
	private String venderPaymentName;

	/** 仕入区分名称 */
	private String categoryName;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 仕入日(yyyy/MM/dd) */
	private String strStockingDate;

	/** 受入日付(yyyy/MM/dd) */
	private String strAcceptDate;

	/** 仕入単価 */
	private String strHousingUnitprice;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 受入数量 （仕入数量） */
	private String strStockingQuantity;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 仕入金額 */
	private String strStockingAmount;

	/** 品目コード */
	private String itemCd;

	/**
	 * コンストラクタ.
	 */
	public BuyingApprovalList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 取得した日付をyyyy/MM/ddに変換し、セット
		setStrStockingDate(AecDateUtils.dateFormat(getStockingDate(),
			"yyyy/MM/dd")); // 仕入日
		setStrAcceptDate(AecDateUtils.dateFormat(getAcceptDate(), "yyyy/MM/dd")); // 受入日付
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 仕入ステータス名称取得
	 * @return String 仕入区分
	 */
	public String getStrStatus2() {
		return SelectStockingStatus.getLabelName(getStatus2().toString());
	}

	/**
	 * 仕入日取得(yyyy/MM/dd)
	 * @return strStockingDate
	 */
	public String getStrStockingDate() {
		return strStockingDate;
	}

	/**
	 * 仕入日設定(yyyy/MM/dd)
	 * @param strStockingDate 仕入日(yyyy/MM/dd)
	 */
	public void setStrStockingDate(final String strStockingDate) {
		this.strStockingDate = strStockingDate;
	}

	/**
	 * 受入日付取得(yyyy/MM/dd)
	 * @return strStockingDate
	 */
	public String getStrAcceptDate() {
		return strAcceptDate;
	}

	/**
	 * 受入日付設定(yyyy/MM/dd)
	 * @param strAcceptDate 受入日付(yyyy/MM/dd)
	 */
	public void setStrAcceptDate(final String strAcceptDate) {
		this.strAcceptDate = strAcceptDate;
	}

	/**
	 * 仕入単価取得
	 * @return strHousingUnitprice
	 */
	public String getStrHousingUnitprice() {
		return strHousingUnitprice;
	}

	/**
	 * 仕入単価設定
	 * @param strHousingUnitprice 仕入単価
	 */
	public void setStrHousingUnitprice(final String strHousingUnitprice) {
		this.strHousingUnitprice = strHousingUnitprice;
	}

	/**
	 * 発注数量取得
	 * @return strOrderQuantity
	 */
	public String getStrOrderQuantity() {
		return strOrderQuantity;
	}

	/**
	 * 発注数量設定
	 * @param strOrderQuantity 発注数量
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 受入数量（仕入数量）取得
	 * @return strStockingQuantity
	 */
	public String getStrStockingQuantity() {
		return strStockingQuantity;
	}

	/**
	 * 受入数量（仕入数量）設定
	 * @param strStockingQuantity 受入数量（仕入数量）
	 */
	public void setStrStockingQuantity(final String strStockingQuantity) {
		this.strStockingQuantity = strStockingQuantity;
	}

	/**
	 * 重量
	 * @return strOrderConvertQuantity
	 */
	public String getStrOrderConvertQuantity() {
		return strOrderConvertQuantity;
	}

	/**
	 * 重量
	 * @param strOrderConvertQuantity 重量
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 仕入金額
	 * @return strStockingAmount
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 仕入金額
	 * @param strStockingAmount 仕入金額
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
	}

	/**
	 * チェックボックス取得
	 * @return approvalCheckBox
	 */
	public boolean isApprovalCheckBox() {
		return approvalCheckBox;
	}

	/**
	 * チェックボックス設定
	 * @param approvalCheckBox チェックボックス
	 */
	public void setApprovalCheckBox(final boolean approvalCheckBox) {
		this.approvalCheckBox = approvalCheckBox;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
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
	 * 支払先コード取得
	 * @return String 支払先コード
	 */
	public String getPaymentInvoiceCd() {
		return paymentInvoiceCd;
	}

	/**
	 * 支払先コード設定
	 * @param paymentInvoiceCd 支払先コード
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 仕入先名称取得
	 * @return String 仕入先名称
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 仕入先名称設定
	 * @param venderName1 仕入先名称
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * 支払先名称取得
	 * @return String 支払先名称
	 */
	public String getVenderPaymentName() {
		return venderPaymentName;
	}

	/**
	 * 支払先名称設定
	 * @param venderPaymentName 支払先名称
	 */
	public void setVenderPaymentName(final String venderPaymentName) {
		this.venderPaymentName = venderPaymentName;
	}

	/**
	 * 仕入区分名称取得
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 仕入区分名称設定
	 * @param categoryName 仕入区分名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
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
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

}
