/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlist;

import java.math.BigDecimal;


/**
 * OrderImportListクラス.
 * @author
 */
public class OrderImportList extends OrderImportListBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportList() {
		super();
	}

	private String strScheduledShippingDate;

	private String strDeliveryDate;

	private String strOrderQty;

	private String strMatss;

	private boolean orderImportCheckBox;

	/** 小数点桁数(数量) */
	private String smallnumLengthNum;

	/** 端数区分(数量) */
	private String roundDivisionNum;

	/** 受注数量(詳細画面遷移時の値保持用) */
	private BigDecimal beforeOrderQty;

	/** 増付数(詳細画面遷移時の値保持用) */
	private BigDecimal beforeMatss;

	/**
	 * strScheduledShippingDateを取得します。
	 * @return strScheduledShippingDate
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * strScheduledShippingDateを設定します。
	 * @param strScheduledShippingDate strScheduledShippingDate
	 */
	public void setStrScheduledShippingDate(String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * strDeliveryDateを取得します。
	 * @return strDeliveryDate
	 */
	public String getStrDeliveryDate() {
		return strDeliveryDate;
	}

	/**
	 * strDeliveryDateを設定します。
	 * @param strDeliveryDate strDeliveryDate
	 */
	public void setStrDeliveryDate(String strDeliveryDate) {
		this.strDeliveryDate = strDeliveryDate;
	}

	/**
	 * strOrderQtyを取得します。
	 * @return strOrderQty
	 */
	public String getStrOrderQty() {
		return strOrderQty;
	}

	/**
	 * strOrderQtyを設定します。
	 * @param strOrderQty strOrderQty
	 */
	public void setStrOrderQty(String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	/**
	 * strMatssを取得します。
	 * @return strMatss
	 */
	public String getStrMatss() {
		return strMatss;
	}

	/**
	 * strMatssを設定します。
	 * @param strMatss strMatss
	 */
	public void setStrMatss(String strMatss) {
		this.strMatss = strMatss;
	}

	/**
	 * orderImportCheckBoxを取得します。
	 * @return orderImportCheckBox
	 */
	public boolean isOrderImportCheckBox() {
		return orderImportCheckBox;
	}

	/**
	 * orderImportCheckBoxを設定します。
	 * @param orderImportCheckBox orderImportCheckBox
	 */
	public void setOrderImportCheckBox(boolean orderImportCheckBox) {
		this.orderImportCheckBox = orderImportCheckBox;
	}

	/**
	 * smallnumLengthNumを取得します。
	 * @return smallnumLengthNum
	 */
	public String getSmallnumLengthNum() {
		return smallnumLengthNum;
	}

	/**
	 * smallnumLengthNumを設定します。
	 * @param smallnumLengthNum smallnumLengthNum
	 */
	public void setSmallnumLengthNum(String smallnumLengthNum) {
		this.smallnumLengthNum = smallnumLengthNum;
	}

	/**
	 * roundDivisionNumを取得します。
	 * @return roundDivisionNum
	 */
	public String getRoundDivisionNum() {
		return roundDivisionNum;
	}

	/**
	 * roundDivisionNumを設定します。
	 * @param roundDivisionNum roundDivisionNum
	 */
	public void setRoundDivisionNum(String roundDivisionNum) {
		this.roundDivisionNum = roundDivisionNum;
	}

	/**
	 * beforeOrderQtyを取得します。
	 * @return beforeOrderQty
	 */
	public BigDecimal getBeforeOrderQty() {
		return beforeOrderQty;
	}

	/**
	 * beforeOrderQtyを設定します。
	 * @param beforeOrderQty beforeOrderQty
	 */
	public void setBeforeOrderQty(BigDecimal beforeOrderQty) {
		this.beforeOrderQty = beforeOrderQty;
	}

	/**
	 * beforeMatssを取得します。
	 * @return beforeMatss
	 */
	public BigDecimal getBeforeMatss() {
		return beforeMatss;
	}

	/**
	 * beforeMatssを設定します。
	 * @param beforeMatss beforeMatss
	 */
	public void setBeforeMatss(BigDecimal beforeMatss) {
		this.beforeMatss = beforeMatss;
	}

	/**
	 * 全ての備考を連結した結果を返す
	 * @return
	 */
	public String getAllRemarks(){
		String allRemarks = "";

		if( this.getRank().equals("1") ){
			if( this.getPrintSummery() != null && this.getPrintSummery().length() > 0 ){
				allRemarks = this.getPrintSummery();
			}

			if( this.getDeliverySlipSummery() != null && this.getDeliverySlipSummery().length() > 0 ){
				if( allRemarks.length() > 0 ){
					allRemarks =  allRemarks + "/";
				}
				allRemarks = allRemarks + this.getDeliverySlipSummery();
			}

			if( super.getOrderSummery() != null && super.getOrderSummery().length() > 0 ){
				if( allRemarks.length() > 0 ){
					allRemarks =  allRemarks + "/";
				}
				allRemarks = allRemarks + super.getOrderSummery();
			}
			// 20210909 Asclab Saita 納期連絡表専用備考追加対応
			if( super.getDeliverydateContactSummery() != null && super.getDeliverydateContactSummery().length() > 0 ){
				if( allRemarks.length() > 0 ){
					allRemarks =  allRemarks + "/";
				}
				allRemarks = allRemarks + super.getDeliverydateContactSummery();
			}
		}

		if( super.getCtmRemark() != null && super.getCtmRemark().length() > 0 ){
			if( allRemarks.length() > 0 ){
				allRemarks =  allRemarks + "/";
			}
			allRemarks = allRemarks + super.getCtmRemark();
		}



		return allRemarks;
	}

	/**
	 * 入力承認チェック
	 * @return
	 */
	public boolean getInputApprovaledCheck(){

		if( super.getInputApprovaled() != null && super.getInputApprovaled().intValue() == 1 ){
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * 納期承認チェック
	 * @return
	 */
	public boolean getDelDateApprovaledCheck(){

		if( super.getDelDateApprovaled() != null && super.getDelDateApprovaled().intValue() == 1 ){
			return true;
		}
		else
		{
			return false;
		}

	}

}
