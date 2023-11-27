/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchasedelivery;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 納期回答入力 Formクラス.
 * @author tosco
 */
public final class PurchaseDeliveryDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注番号 */
	private String strBuySubcontractOrderNo;

	/** 発注書NO */
	private String orderSheetNo;

	/** 発注日 */
	private String strOrderDate;

	/** 発注者 */
	private String tantoNm;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemQueueName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 仕入先コード */
	private String venderCd;

	/** 仕入先名称 */
	private String venderName;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 荷姿 */
	private String styleOfPacking;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 納入ロケーションコード */
	private String locationCd;

	/** 納入先名称 */
	private String locationName;

	/** 購買ステータス */
	private String status;

	/** 仕入先受注番号 */
	private String siOrderNo;

	/** 担当部署コード */
	private String chargeOrganizationCd;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 納品希望時刻 */
	private String strSuggestedDeliverlimitDateTime;

	/** 分納区分 */
	private boolean replyContentsDivision;

	/** 発注書備考 */
	private String orderSheetRemark;

	/** 備考 */
	private String remark;

	/** 単位 */
	private String unit;

	/** 更新日時 */
	private Timestamp updateDate;

	/**
	 * コンストラクタ.詳細
	 */
	public PurchaseDeliveryDetailForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}

		if ("update".equals(getOp())) {
			// 分納区分
			setReplyContentsDivision(false);
		}
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		// 購買NO
		setPurchaseNo(null);
		// 発注番号
		setBuySubcontractOrderNo(null);
		// 発注書NO
		setOrderSheetNo(null);
		// 発注日
		setStrOrderDate(null);
		// 発注者
		setTantoNm(null);
		// 品目コード
		setItemCd(null);
		// 品目名称
		setItemQueueName(null);
		// 他社コード１
		setOtherCompanyCd1(null);
		// 仕入先コード
		setVenderCd(null);
		// 仕入先名称
		setVenderName(null);
		// 発注数量
		setStrOrderQuantity(null);
		// 荷姿
		setStyleOfPacking(null);
		// 重量
		setStrOrderConvertQuantity(null);
		// 納入ロケーションコード
		setLocationCd(null);
		// 納入先名称
		setLocationName(null);
		// 購買ステータス
		setStatus(null);
		// 仕入先受注番号
		setSiOrderNo(null);
		// 担当部署コード
		setChargeOrganizationCd(null);
		// 担当部署名称
		setChargeOrganizationName(null);
		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setOrganizationName(null);
		// 納品希望日
		setStrSuggestedDeliverlimitDate(null);
		// 納品希望時刻
		setStrSuggestedDeliverlimitDateTime(null);
		// 分納区分
		setReplyContentsDivision(false);
		// 発注書備考
		setOrderSheetRemark(null);
		// 備考
		setRemark(null);
		// 単位
		setUnit(null);
		// 更新日付
		setUpdateDate(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NO取得
	 * @return String 購買NO
	 */
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注番号取得.
	 * @return String 発注番号
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定.
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * 発注書NO取得.
	 * @return String 発注書NO
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定.
	 * @param orderSheetNo 発注書NO
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 発注日取得.
	 * @return String 発注日
	 */
	public String getStrOrderDate() {
		return this.strOrderDate;
	}

	/**
	 * 発注日設定.
	 * @param strOrderDate 発注日
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 発注者取得.
	 * @return String 発注者
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 発注者設定.
	 * @param tantoNm 発注者
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
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
	 * 他社コード１を取得します。
	 * @return String 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 仕入先コード取得.
	 * @return String 仕入先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 仕入先コード設定.
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 仕入先名称を取得します。
	 * @return String 仕入先名称
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
	 * 発注数量取得.
	 * @return String 発注数量
	 */
	public String getStrOrderQuantity() {
		return this.strOrderQuantity;
	}

	/**
	 * 発注数量設定.
	 * @param strOrderQuantity 発注数量
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 荷姿を取得します。
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 重量取得.
	 * @return String 重量
	 */
	public String getStrOrderConvertQuantity() {
		return this.strOrderConvertQuantity;
	}

	/**
	 * 重量設定.
	 * @param strOrderConvertQuantity 重量
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 納入ロケーションコード取得.
	 * @return String 納入ロケーションコード
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * 納入ロケーションコード設定.
	 * @param locationCd 納入ロケーションコード
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 納入先名称を取得します。
	 * @return String 納入先名称
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先名称を設定します。
	 * @param locationName 納入先名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 購買ステータス取得.
	 * @return String 購買ステータス
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 購買ステータス設定.
	 * @param status 購買ステータス
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * 仕入先受注番取得.
	 * @return String 仕入先受注番
	 */
	public String getSiOrderNo() {
		return this.siOrderNo;
	}

	/**
	 * 仕入先受注番設定.
	 * @param siOrderNo 仕入先受注番
	 */
	public void setSiOrderNo(final String siOrderNo) {
		this.siOrderNo = siOrderNo;
	}

	/**
	 * 担当部署コード取得.
	 * @return String 担当部署コード
	 */
	public String getChargeOrganizationCd() {
		return chargeOrganizationCd;
	}

	/**
	 * 担当部署コード設定.
	 * @param chargeOrganizationCd 担当部署コード
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 担当部署名称取得.
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定.
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
	}

	/**
	 * 部署コードを取得します。
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称を取得します。
	 * @return String 部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称を設定します。
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 納品希望日取得.
	 * @return String 納品希望日
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return this.strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日設定.
	 * @param strSuggestedDeliverlimitDate 納品希望日
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望時刻取得.
	 * @return String 納品希望時刻
	 */
	public String getStrSuggestedDeliverlimitDateTime() {
		return strSuggestedDeliverlimitDateTime;
	}

	/**
	 * 納品希望時刻設定.
	 * @param strSuggestedDeliverlimitDateTime 納品希望時刻
	 */
	public void setStrSuggestedDeliverlimitDateTime(
			final String strSuggestedDeliverlimitDateTime) {
		this.strSuggestedDeliverlimitDateTime = strSuggestedDeliverlimitDateTime;
	}

	/**
	 * 分納区分取得.
	 * @return String 分納区分
	 */
	public boolean isReplyContentsDivision() {
		return replyContentsDivision;
	}

	/**
	 * 分納区分設定.
	 * @param replyContentsDivision 分納区分
	 */
	public void setReplyContentsDivision(final boolean replyContentsDivision) {
		this.replyContentsDivision = replyContentsDivision;
	}

	/**
	 * 発注書備考取得.
	 * @return String 発注書備考
	 */
	public String getOrderSheetRemark() {
		return this.orderSheetRemark;
	}

	/**
	 * 発注書備考設定.
	 * @param orderSheetRemark 発注書備考
	 */
	public void setOrderSheetRemark(final String orderSheetRemark) {
		this.orderSheetRemark = orderSheetRemark;
	}

	/**
	 * 備考取得.
	 * @return String 備考
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考設定.
	 * @param remark 備考
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 単位取得
	 * @return String 単位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位設定
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	/**
	 * strBuySubcontractOrderNoを取得します。
	 * @return strBuySubcontractOrderNo
	 */
	public String getStrBuySubcontractOrderNo() {
		return strBuySubcontractOrderNo;
	}

	/**
	 * strBuySubcontractOrderNoを設定します。
	 * @param strBuySubcontractOrderNo strBuySubcontractOrderNo
	 */
	public void setStrBuySubcontractOrderNo(
			final String strBuySubcontractOrderNo) {
		this.strBuySubcontractOrderNo = strBuySubcontractOrderNo;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}
