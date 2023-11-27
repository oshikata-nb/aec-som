/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchase;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.dao.nonentity.purchase.PurchaseDetail;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseRemarkList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 発注詳細 Formクラス.
 * @author tosco
 */
public final class PurchaseDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 入力チェック用：半角数字 */
	private static final String PTN_NUMERIC_NOCONMA = "0123456789";

	/** 変更フラグ */
	private String dirtyFlg;

	//
	// インスタンスフィールド

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注日 */
	private String strOrderDate;

	/** 担当部署コード */
	private String chargeOrganizationCd;

	/** 担当部署名称 */
	private String tantoSectionNm;

	/** 他社コード１ */
	private String otherCompanyCd;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String sectionName;

	/** 発注者コード */
	private String tantoCd;

	/** 発注者名称 */
	private String tantoNm;

	/** 複数者購買 */
	private String strMultiSupplierDivision;

	/** 支給品区分 */
	private String strSuppliedDoodsDivision;

	/** 仕入先コード */
	private String venderCd;

	/** 仕入先名称 */
	private String supplierName;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 発注数量の単位名称 */
	private String orderUnit;

	/** 荷姿 */
	private String styleOfPacking;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 発注単価 */
	private String strOrderUnitprice;

	/** 発注単価の単価名称(単価区分を名称にしたもの、画面表示で必要) */
	private String unitPriceUnit;

	/** 単価区分(hidden:仕入先別単価マスタの単価区分 金額計算で必要) */
	private String unitpriceDefineunit;

	/** 発注金額 */
	private String strSupplierOrdAmount;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 納品希望時間 */
	private String strSuggestedDeliverlimitTime;

	/** 発注書備考 */
	private String orderSheetRemark;

	/** 備考 */
	private String remark;

	/** 発注まとめ場所 */
	private String orderLocation;

	/** 発注まとめ場所名称 */
	private String orderLocationName;

	/** 納入先コード */
	private String locationCd;

	/** 納入先名称 */
	private String deliveryName;

	/** 購買ステータス */
	private String strStatus;

	/** ステータス変更 */
	private String status;

	/** 発注書NO */
	private String orderSheetNo;

	/** 登録日時 */
	private String inputDate;

	/** 登録者 */
	private String inputorCd;

	/** 更新日時 */
	private String updateDate;

	/** 更新者 */
	private String updatorCd;

	/** Kg換算係数(在庫) */
	private String kgOfFractionManagement;

	/** 種別 */
	private BigDecimal typeDivision;

	/** スポット区分 (hidden:品目名称入力可・不可で使用) */
	private String spotDivision;

	/** ローリー区分 */
	private BigDecimal lorryDivision;

	/** 新規フラグ */
	private String insertFlg;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 詳細Bean */
	private PurchaseDetail detailBean;

	/** 備考マスタリスト */
	private List<PurchaseRemarkList> remarkList;

	/** 購買ステータス(コンボボックス用) */
	private String cboStatus;

	/** オーダー区分 (hidden) */
	private BigDecimal orderDivision;

	/** オーダー区分 (hidden) */
	private String strOrderDivision;

	/** 小数点位置：発注数量 (hidden) */
	private String strOrderQuantityDecimalPoint;

	/** 小数点位置：重量 (hidden) */
	private String strOrderConvertQuantityDecimalPoint;

	/** 小数点位置：単価 (hidden) */
	private String strOrderUnitpriceDecimalPoint;

	/** 小数点位置：金額 (hidden) */
	private String strSupplierOrdAmountDecimalPoint;

	/** 端数区分：発注数量 (hidden) */
	private String strOrderQuantityRound;

	/** 端数区分：重量 (hidden) */
	private String strOrderConvertQuantityRound;

	/** 端数区分：単価 (hidden) */
	private String strOrderUnitpriceRound;

	/** 端数区分：金額 (hidden) */
	private String strSupplierOrdAmountRound;

	/** フォーカス位置保存 (hidden) */
	private String focusPosition;

	/** ASPROVA発注重量 */
	private String strCheckQuantity;

	/** 免税計算対象フラグ  */
	private String reducedTaxTargetFlg;

	/**
	 * コンストラクタ.詳細
	 */
	public PurchaseDetailForm() {
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
		if ("initNew".equals(getOp())) {
			clear();
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

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			/* 日付・時刻のチェック */
			dateFormatChk(errors);
		}
		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			/* 日付・時刻のチェック */
			dateFormatChk(errors);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		/** 発注番号 */
		setBuySubcontractOrderNo(null);

		/** 発注日 */
		setStrOrderDate(null);

		/** 担当部署コード */
		setChargeOrganizationCd(null);

		/** 担当部署名称 */
		setTantoSectionNm(null);

		/** 他社コード１ */
		setOtherCompanyCd(null);

		/** 複数社購買 */
		setStrMultiSupplierDivision(null);

		/** 支給品区分 */
		setStrSuppliedDoodsDivision(null);

		/** 部署コード */
		setOrganizationCd(null);

		/** 部署名称 */
		setSectionName(null);

		/** 発注者コード */
		setTantoCd(null);

		/** 発注者名称 */
		setTantoNm(null);

		/** 仕入先コード */
		setVenderCd(null);

		/** 仕入先名称 */
		setSupplierName(null);

		/** 品目コード */
		setItemCd(null);

		/** 品目名称 */
		setItemName(null);

		/** 発注数量 */
		setStrOrderQuantity(null);

		/** 発注数量単位 */
		setOrderUnit(null);

		/** 荷姿 */
		setStyleOfPacking(null);

		/** 重量 */
		setStrOrderConvertQuantity(null);

		/** 発注単価 */
		setStrOrderUnitprice(null);

		/** 発注単価の単価区分名称 */
		setUnitPriceUnit(null);

		/** 単価区分 単価の単価区分 金額計算で使用 */
		setUnitpriceDefineunit(null);

		/** 発注金額 */
		setStrSupplierOrdAmount(null);

		/** 納品希望日 */
		setStrSuggestedDeliverlimitDate(null);

		/** 納品希望時間 */
		setStrSuggestedDeliverlimitTime(null);

		/** 発注書備考 */
		setOrderSheetRemark(null);

		/** 備考 */
		setRemark(null);

		/** 発注まとめ場所 */
		setOrderLocation(null);

		/** 発注まとめ場所名称 */
		setOrderLocationName(null);

		/** 納入先コード */
		setLocationCd(null);

		/** 納入先名称 */
		setDeliveryName(null);

		/** 購買ステータス */
		setStrStatus(null);

		/** ステータス変更 */
		setStatus(null);

		/** 発注書NO */
		setOrderSheetNo(null);

		/** 登録日時 */
		setInputDate(null);

		/** 登録者 */
		setInputorCd(null);

		/** 更新日時 */
		setUpdateDate(null);

		/** 更新者 */
		setUpdatorCd(null);

		/** 購買NO */
		setPurchaseNo(null);

		/** 新規フラグ */
		setInsertFlg(null);

		/** 運用管理単位 */
		setUnitOfOperationManagement(null);

		/** Kg換算係数(在庫) */
		setKgOfFractionManagement(null);

		/** 種別 */
		setTypeDivision(null);

		/** ローリー区分 */
		setLorryDivision(null);

		/** スポット区分 */
		setSpotDivision(null);

		/** 詳細Bean */
		setDetailBean(null);

		/** 備考マスタリスト */
		setRemarkList(null);

		/** 購買ステータス(コンボボックス用) */
		setCboStatus(null);

		/** 小数点位置：発注数量 (hidden) */
		setStrOrderQuantityDecimalPoint(null);

		/** 小数点位置：重量 (hidden) */
		setStrOrderConvertQuantityDecimalPoint(null);

		/** 小数点位置：単価 (hidden) */
		setStrOrderUnitpriceDecimalPoint(null);

		/** 小数点位置：金額 (hidden) */
		setStrSupplierOrdAmountDecimalPoint(null);

		/** 端数区分：発注数量 (hidden) */
		setStrOrderQuantityRound(null);

		/** 端数区分：重量 (hidden) */
		setStrOrderConvertQuantityRound(null);

		/** 端数区分：単価 (hidden) */
		setStrOrderUnitpriceRound(null);

		/** 端数区分：金額 (hidden) */
		setStrSupplierOrdAmountRound(null);

		/** フォーカス位置 (hidden) */
		setFocusPosition(null);

		/** ASPROVA発注重量 */

		/* オーダー区分 */
		setStrOrderDivision(null);

		/** 免税計算対象フラグ */
		setReducedTaxTargetFlg(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NOを取得します。
	 * @return String 購買NO
	 */
	public String getPurchaseNo() {
		return purchaseNo;
	}

	/**
	 * 購買NOを設定します。
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
	 * 担当部署コード取得.
	 * @return String 担当部署コード
	 */
	public String getChargeOrganizationCd() {
		return this.chargeOrganizationCd;
	}

	/**
	 * 担当部署コード設定.
	 * @param chargeOrganizationCd 担当部署コード
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 担当部署名称を取得します。
	 * @return tantoSectionNm
	 */
	public String getTantoSectionNm() {
		return tantoSectionNm;
	}

	/**
	 * 担当部署名称を設定します。
	 * @param tantoSectionNm tantoSectionNm
	 */
	public void setTantoSectionNm(final String tantoSectionNm) {
		this.tantoSectionNm = tantoSectionNm;
	}

	/**
	 * 部署コード取得.
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称を取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部署名称を設定します。
	 * @param sectionName 部署名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 発注者コード取得.
	 * @return String 発注担当者コード
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 発注者コード設定.
	 * @param tantoCd 発注担当者コード
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 発注者名称を取得します。
	 * @return tantoName
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 発注者名称を設定します。
	 * @param tantoNm 発注担当者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 複数社購買取得.
	 * @return String 複数社購買
	 */
	public String getStrMultiSupplierDivision() {
		return strMultiSupplierDivision;
	}

	/**
	 * 複数社購買設定.
	 * @param strMultiSupplierDivision 複数社購買
	 */
	public void setStrMultiSupplierDivision(
			final String strMultiSupplierDivision) {
		this.strMultiSupplierDivision = strMultiSupplierDivision;
	}

	/**
	 * 支給品区分取得.
	 * @return String 支給品区分
	 */
	public String getStrSuppliedDoodsDivision() {
		return strSuppliedDoodsDivision;
	}

	/**
	 * 支給品区分設定.
	 * @param strSuppliedDoodsDivision 支給品区分
	 */
	public void setStrSuppliedDoodsDivision(
			final String strSuppliedDoodsDivision) {
		this.strSuppliedDoodsDivision = strSuppliedDoodsDivision;
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
	 * 仕入先名称取得.
	 * @return String 仕入先名称
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 仕入先名称設定.
	 * @param supplierName 仕入先名称
	 */
	public void setSupplierName(final String supplierName) {
		this.supplierName = supplierName;
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
	 * 品目名称取得.
	 * @return 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 発注数量取得.
	 * @return 発注数量
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
	 * 発注数量単位取得.
	 * @return 発注数量単位
	 */
	public String getOrderUnit() {
		return orderUnit;
	}

	/**
	 * 発注数量単位設定.
	 * @param orderUnit 発注数量単位
	 */
	public void setOrderUnit(final String orderUnit) {
		this.orderUnit = orderUnit;
	}

	/**
	 * 荷姿取得.
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿設定.
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 重量取得.
	 * @return 重量
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
	 * 発注単価取得.
	 * @return 発注単価
	 */
	public String getStrOrderUnitprice() {
		return this.strOrderUnitprice;
	}

	/**
	 * 発注単価設定.
	 * @param strOrderUnitprice 発注単価
	 */
	public void setStrOrderUnitprice(final String strOrderUnitprice) {
		this.strOrderUnitprice = strOrderUnitprice;
	}

	/**
	 * 発注単価の単価名称を取得します。
	 * @return unitPriceUnit
	 */
	public String getUnitPriceUnit() {
		return unitPriceUnit;
	}

	/**
	 * 発注単価の単価名称を設定します。
	 * @param unitPriceUnit 発注単価の単価名称
	 */
	public void setUnitPriceUnit(final String unitPriceUnit) {
		this.unitPriceUnit = unitPriceUnit;
	}

	/**
	 * 単価の単価区分を取得します。
	 * @return unitpriceDefineunit
	 */
	public String getUnitpriceDefineunit() {
		return unitpriceDefineunit;
	}

	/**
	 * 単価の単価区分を設定します。
	 * @param unitpriceDefineunit 単価区分
	 */
	public void setUnitpriceDefineunit(final String unitpriceDefineunit) {
		this.unitpriceDefineunit = unitpriceDefineunit;
	}

	/**
	 * 発注金額取得.
	 * @return 発注金額
	 */
	public String getStrSupplierOrdAmount() {
		return this.strSupplierOrdAmount;
	}

	/**
	 * 発注金額設定.
	 * @param strSupplierOrdAmount 発注金額
	 */
	public void setStrSupplierOrdAmount(final String strSupplierOrdAmount) {
		this.strSupplierOrdAmount = strSupplierOrdAmount;
	}

	/**
	 * 納品希望日取得.
	 * @return 納品希望日
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
	 * 納品希望時間を取得します。
	 * @return suggestedDeliverlimitTime
	 */
	public String getStrSuggestedDeliverlimitTime() {
		return strSuggestedDeliverlimitTime;
	}

	/**
	 * 納品希望時間を設定します。
	 * @param strSuggestedDeliverlimitTime 納品希望時間
	 */
	public void setStrSuggestedDeliverlimitTime(
			final String strSuggestedDeliverlimitTime) {
		this.strSuggestedDeliverlimitTime = strSuggestedDeliverlimitTime;
	}

	/**
	 * 納入先名称取得.
	 * @return 納入先名称
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * 納入先名称設定.
	 * @param deliveryName 納入先名称
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * 他社コード取得.
	 * @return 他社コード
	 */
	public String getOtherCompanyCd() {
		return otherCompanyCd;
	}

	/**
	 * 他社コード設定.
	 * @param otherCompanyCd 他社コード
	 */
	public void setOtherCompanyCd(final String otherCompanyCd) {
		this.otherCompanyCd = otherCompanyCd;
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
	 * 発注まとめ場所取得.
	 * @return String 発注まとめ場所
	 */
	public String getOrderLocation() {
		return orderLocation;
	}

	/**
	 * 発注まとめ場所設定.
	 * @param orderLocation 発注まとめ場所
	 */
	public void setOrderLocation(final String orderLocation) {
		this.orderLocation = orderLocation;
	}

	/**
	 * 発注まとめ場所名称を取得します。
	 * @return String 発注まとめ場所名称
	 */
	public String getOrderLocationName() {
		return orderLocationName;
	}

	/**
	 * 発注まとめ場所名称を設定します。
	 * @param orderLocationName 発注まとめ場所名称
	 */
	public void setOrderLocationName(final String orderLocationName) {
		this.orderLocationName = orderLocationName;
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
	 * 購買ステータス取得.
	 * @return String
	 */
	public String getStrStatus() {
		return this.strStatus;
	}

	/**
	 * 購買ステータス設定.
	 * @param strStatus 購買ステータス
	 */
	public void setStrStatus(final String strStatus) {
		this.strStatus = strStatus;
	}

	/**
	 * ステータス変更取得.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * ステータス変更設定.
	 * @param status ステータス変更
	 */
	public void setStatus(final String status) {
		this.status = status;
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
	 * 登録日時取得.
	 * @return String 登録日時
	 */
	public String getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定.
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final String inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者取得.
	 * @return String 登録者
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者設定.
	 * @param inputorCd 登録者
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得.
	 * @return String 更新日時
	 */
	public String getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者取得.
	 * @return String 更新者
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者設定.
	 * @param updatorCd 更新者
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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
	 * Kg換算係数(在庫)を取得します。
	 * @return kgOfFractionManagement
	 */
	public String getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数(在庫)を設定します。
	 * @param kgOfFractionManagement Kg換算係数(在庫)
	 */
	public void setKgOfFractionManagement(final String kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * ローリー区分を取得します。
	 * @return lorryDivision
	 */
	public BigDecimal getLorryDivision() {
		return lorryDivision;
	}

	/**
	 * ローリー区分を設定します。
	 * @param lorryDivision ローリー区分
	 */
	public void setLorryDivision(final BigDecimal lorryDivision) {
		this.lorryDivision = lorryDivision;
	}

	/**
	 * スポット区分を取得します。
	 * @return spotDivision
	 */
	public String getSpotDivision() {
		return spotDivision;
	}

	/**
	 * スポット区分を設定します。
	 * @param spotDivision スポット区分
	 */
	public void setSpotDivision(final String spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * 種別を取得します。
	 * @return typeDivision
	 */
	public BigDecimal getTypeDivision() {
		return typeDivision;
	}

	/**
	 * 種別を設定します。
	 * @param typeDivision 種別
	 */
	public void setTypeDivision(final BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}

	/**
	 * 新規フラグを取得します。
	 * @return insertFlg
	 */
	public String getInsertFlg() {
		return insertFlg;
	}

	/**
	 * 新規フラグを設定します。
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(final String insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * 詳細Beanを取得します。
	 * @return detailBean
	 */
	public PurchaseDetail getDetailBean() {
		return detailBean;
	}

	/**
	 * 詳細Beanを設定します。
	 * @param detailBean 詳細Bean
	 */
	public void setDetailBean(final PurchaseDetail detailBean) {
		this.detailBean = detailBean;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitOfOperationManagement 運用管理単位区分
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 備考マスタリストを取得します。
	 * @return searchList
	 */
	public List<PurchaseRemarkList> getRemarkList() {
		return remarkList;
	}

	/**
	 * 備考マスタリストを設定します。
	 *
	 * @param remarkList 一覧リスト
	 */
	public void setRemarkList(final List<PurchaseRemarkList> remarkList) {
		this.remarkList = remarkList;
	}

	/**
	 * 購買ステータス(コンボボックス用)を取得します。
	 * @return cboStatus
	 */
	public String getCboStatus() {
		return cboStatus;
	}

	/**
	 * 購買ステータス(コンボボックス用)を設定します。
	 * @param cboStatus 購買ステータス
	 */
	public void setCboStatus(final String cboStatus) {
		this.cboStatus = cboStatus;
	}

	/**
	 * オーダー区分取得
	 * @return BigDecimal オーダー区分
	 */
	public BigDecimal getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * オーダー区分設定
	 * @param orderDivision オーダー区分
	 */
	public void setOrderDivision(final BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * 小数点桁数：発注数量を取得します。
	 * @return strOrderConvertQuantityDecimalPoint
	 */
	public String getStrOrderConvertQuantityDecimalPoint() {
		return strOrderConvertQuantityDecimalPoint;
	}

	/**
	 * 小数点桁数：発注数量を設定します。
	 * @param strOrderConvertQuantityDecimalPoint
	 *            strOrderConvertQuantityDecimalPoint
	 */
	public void setStrOrderConvertQuantityDecimalPoint(
			final String strOrderConvertQuantityDecimalPoint) {
		this.strOrderConvertQuantityDecimalPoint = strOrderConvertQuantityDecimalPoint;
	}

	/**
	 * 端数区分：発注数量を取得します。
	 * @return strOrderConvertQuantityRound
	 */
	public String getStrOrderConvertQuantityRound() {
		return strOrderConvertQuantityRound;
	}

	/**
	 * 端数区分：発注数量を設定します。
	 * @param strOrderConvertQuantityRound strOrderConvertQuantityRound
	 */
	public void setStrOrderConvertQuantityRound(
			final String strOrderConvertQuantityRound) {
		this.strOrderConvertQuantityRound = strOrderConvertQuantityRound;
	}

	/**
	 * 小数点桁数：重量を取得します。
	 * @return strOrderQuantityDecimalPoint
	 */
	public String getStrOrderQuantityDecimalPoint() {
		return strOrderQuantityDecimalPoint;
	}

	/**
	 * 小数点桁数：重量を設定します。
	 * @param strOrderQuantityDecimalPoint strOrderQuantityDecimalPoint
	 */
	public void setStrOrderQuantityDecimalPoint(
			final String strOrderQuantityDecimalPoint) {
		this.strOrderQuantityDecimalPoint = strOrderQuantityDecimalPoint;
	}

	/**
	 * 端数区分：重量を取得します。
	 * @return strOrderQuantityRound
	 */
	public String getStrOrderQuantityRound() {
		return strOrderQuantityRound;
	}

	/**
	 * 端数区分：重量を設定します。
	 * @param strOrderQuantityRound strOrderQuantityRound
	 */
	public void setStrOrderQuantityRound(final String strOrderQuantityRound) {
		this.strOrderQuantityRound = strOrderQuantityRound;
	}

	/**
	 * 小数点桁数：単価を取得します。
	 * @return strOrderUnitpriceDecimalPoint
	 */
	public String getStrOrderUnitpriceDecimalPoint() {
		return strOrderUnitpriceDecimalPoint;
	}

	/**
	 * 小数点桁数：単価を設定します。
	 * @param strOrderUnitpriceDecimalPoint strOrderUnitpriceDecimalPoint
	 */
	public void setStrOrderUnitpriceDecimalPoint(
			final String strOrderUnitpriceDecimalPoint) {
		this.strOrderUnitpriceDecimalPoint = strOrderUnitpriceDecimalPoint;
	}

	/**
	 * 端数区分：単価を取得します。
	 * @return strOrderUnitpriceRound
	 */
	public String getStrOrderUnitpriceRound() {
		return strOrderUnitpriceRound;
	}

	/**
	 * 端数区分：単価を設定します。
	 * @param strOrderUnitpriceRound strOrderUnitpriceRound
	 */
	public void setStrOrderUnitpriceRound(final String strOrderUnitpriceRound) {
		this.strOrderUnitpriceRound = strOrderUnitpriceRound;
	}

	/**
	 * 小数点桁数：金額を取得します。
	 * @return strSupplierOrdAmountDecimalPoint
	 */
	public String getStrSupplierOrdAmountDecimalPoint() {
		return strSupplierOrdAmountDecimalPoint;
	}

	/**
	 * 小数点桁数：金額を設定します。
	 * @param strSupplierOrdAmountDecimalPoint strSupplierOrdAmountDecimalPoint
	 */
	public void setStrSupplierOrdAmountDecimalPoint(
			final String strSupplierOrdAmountDecimalPoint) {
		this.strSupplierOrdAmountDecimalPoint = strSupplierOrdAmountDecimalPoint;
	}

	/**
	 * 端数区分：金額を取得します。
	 * @return strSupplierOrdAmountRound
	 */
	public String getStrSupplierOrdAmountRound() {
		return strSupplierOrdAmountRound;
	}

	/**
	 * 端数区分：金額を設定します。
	 * @param strSupplierOrdAmountRound strSupplierOrdAmountRound
	 */
	public void setStrSupplierOrdAmountRound(
			final String strSupplierOrdAmountRound) {
		this.strSupplierOrdAmountRound = strSupplierOrdAmountRound;
	}

	/**
	 * フォーカス位置を取得します。
	 * @return focusPosition
	 */
	public String getFocusPosition() {
		return focusPosition;
	}

	/**
	 * フォーカス位置を設定します。
	 * @param focusPosition focusPosition
	 */
	public void setFocusPosition(final String focusPosition) {
		this.focusPosition = focusPosition;
	}

	/**
	 * 日付・時刻が正しいかどうかチェックする。
	 *
	 * @param errors 検証エラー内容
	 */
	private void dateFormatChk(final ActionErrors errors) {
		String date = this.getStrSuggestedDeliverlimitDate();
		String time = this.getStrSuggestedDeliverlimitTime();
		if (StringUtils.isEmpty(time)) {
			time = "00:00";
		}

		if (StringUtils.isEmpty(date) || StringUtils.isEmpty(time)) {
			return;
		}

		date = date.replace("/", "");
		time = time.replace(":", "");

		if (date.length() != 8 || time.length() != 4) {
			errors.add("", new ActionMessage("errors.purchase.datetime",
					"納品希望日時"));
		} else if (!isMojiChk(date, PTN_NUMERIC_NOCONMA)
				|| !isMojiChk(time, PTN_NUMERIC_NOCONMA)) {
			errors.add("", new ActionMessage("errors.purchase.datetime",
					"納品希望日時"));
		} else {
			Calendar cal1 = Calendar.getInstance();
			cal1.setLenient(false);
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(4, 6)) - 1;
			int day = Integer.parseInt(date.substring(6));
			int hour = Integer.parseInt(time.substring(0, 2));
			int minute = Integer.parseInt(time.substring(2));
			cal1.set(year, month, day, hour, minute);

			try {
				cal1.getTime();
				// 存在しない日付のため例外がスローされます。
			} catch (IllegalArgumentException e) {
				errors.add("", new ActionMessage("errors.purchase.datetime",
						"納品希望日時"));
			}
		}
	}

	/**
	 * 数字チェック<br>
	 * 引数の文字列が全て数字かどうか判定します。
	 * @param str 判定対象文字列
	 * @param ptn 判定パターン文字列
	 * @return 数字ならtrue
	 */
	private boolean isMojiChk(final String str, final String ptn) {

		if (StringUtils.isEmpty(str)) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (ptn.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}

		try {
			String targetStr = StringUtils.replace(str, ",", "");
			new BigDecimal(targetStr);
		} catch (NumberFormatException lexNfe) {
			// 入力された文字列が数値でない場合
			return false;
		}

		return true;
	}

	/**
	 * strCheckQuantityを取得します。
	 * @return strCheckQuantity
	 */
	public String getStrCheckQuantity() {
		return strCheckQuantity;
	}

	/**
	 * strCheckQuantityを設定します。
	 * @param strCheckQuantity strCheckQuantity
	 */
	public void setStrCheckQuantity(final String strCheckQuantity) {
		this.strCheckQuantity = strCheckQuantity;
	}

	/**
	 * strOrderDivisionを取得します。
	 * @return strOrderDivision
	 */
	public String getStrOrderDivision() {
		return strOrderDivision;
	}

	/**
	 * strOrderDivisionを設定します。
	 * @param strOrderDivision strOrderDivision
	 */
	public void setStrOrderDivision(final String strOrderDivision) {
		this.strOrderDivision = strOrderDivision;
	}

	/**
	 * reducedTaxTargetFlgを取得します。
	 * @return reducedTaxCalcFlg
	 */
	public String getReducedTaxTargetFlg() {
		return reducedTaxTargetFlg;
	}

	/**
	 * reducedTaxTargetFlgを設定します。
	 * @param reducedTaxTargetFlg reducedTaxTargetFlg
	 */
	public void setReducedTaxTargetFlg(String reducedTaxTargetFlg) {
		this.reducedTaxTargetFlg = reducedTaxTargetFlg;
	}

}
