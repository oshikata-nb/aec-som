/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchase;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.app.comboboxes.SelectPurchaseStatusPurchase;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 発注詳細画面_表示用データ格納クラス.
 *
 * @author tosco
 */
public class PurchaseDetail extends PurchaseDetailBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション 他社コード１ */
	public static final String otherCompanyCd_COLUMN = "OTHER_COMPANY_CD";

	/** COLUMNアノテーション 仕入先名称 */
	public static final String supplierName_COLUMN = "SUPPLIER_NAME";

	/** COLUMNアノテーション 複数社購買 */
	public static final String multiSupplierDivision_COLUMN = "MULTI_SUPPLIER_DIVISION";

	/** COLUMNアノテーション 支給品区分 */
	public static final String suppliedDoodsDivision_COLUMN = "SUPPLIED_GOODS_DIVISION";

	/** COLUMNアノテーション 発注数量単位 */
	public static final String orderUnit_COLUMN = "ORDER_UNIT";

	/** COLUMNアノテーション 荷姿 */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション 納入先名称(DELIVERYから) */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション 納入先名称(LOCATIONから) */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション 発注者名称 */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション 担当部署名称 */
	public static final String tantoSectionNm_COLUMN = "TANTO_SECTION_NM";

	/** COLUMNアノテーション 部署名称 */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション 運用管理単位 */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション 基準保管場所 */
	public static final String defaultLocation_COLUMN = "DEFAULT_LOCATION";

	/** COLUMNアノテーション 種別 */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション スポット区分 */
	public static final String spotDivision_COLUMN = "SPOT_DIVISION";

	/** COLUMNアノテーション ローリー区分 */
	public static final String lorryDivision_COLUMN = "LORRY_DIVISION";

	/** COLUMNアノテーション Kg換算係数(在庫) */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション 単価の単位名称 */
	public static final String unitpriceUnit_COLUMN = "UNITPRICE_UNIT";

	/** COLUMNアノテーション 発注まとめ場所 */
	public static final String orderLocation_COLUMN = "ORDER_LOCATION";

	/** COLUMNアノテーション 発注まとめ場所 */
	public static final String orderLocationName_COLUMN = "ORDER_LOCATION_NAME";

	/** COLUMNアノテーション 免税計算対象フラグ */
	public static final String reducedTaxCalcFlg_COLUMN = "REDUCED_TAX_TARGET_FLG";

	/** 他社コード１ */
	private String otherCompanyCd;

	/** 仕入先名称 */
	private String supplierName;

	/** 複数社購買 */
	private String multiSupplierDivision;

	/** 支給品区分 */
	private String suppliedDoodsDivision;

	/** 発注数量の単位名称 */
	private String orderUnit;

	/** 荷姿 */
	private String styleOfPacking;

	/** 納入先名称(DELIVERYから) */
	private String deliveryName;

	/** 納入先名称(LOCATIONから) */
	private String locationName;

	/** 発注者名称 */
	private String tantoNm;

	/** 担当部署名称 */
	private String tantoSectionNm;

	/** 部署名称 */
	private String sectionName;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 基準保管場所 */
	private String defaultLocation;

	/** 種別 */
	private BigDecimal typeDivision;

	/** スポット区分 */
	private BigDecimal spotDivision;

	/** Kg換算係数(在庫) */
	private String kgOfFractionManagement;

	/** ローリー区分 */
	private BigDecimal lorryDivision;

	/** 単価区分の名称(画面用 NAMESマスタ検索NAME01が入っている) */
	private String unitpriceUnit;

	/** 発注まとめ場所 */
	private String orderLocation;

	/** 発注まとめ場所名称 */
	private String orderLocationName;

	// 画面に表示するために編集が必要なものなど(画面表示エリア)
	/** 発注日 */
	private String strOrderDate;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 納品希望時刻 */
	private String strSuggestedDeliverlimitTime;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 単価 */
	private String strOrderUnitprice;

	/** 金額 */
	private String strSupplierOrdAmount;

	/** 数量 */
	private String strOrderQuantity;

	/** 購買ステータス */
	private String strStatus;

	/** 免税計算対象フラグ */
	private String reducedTaxTargetFlg;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 発注日
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
		// 納品希望日時
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));
		String tmptime = AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "HH:mm");
		if (StringUtils.isEmpty(tmptime) || tmptime.equals("00:00")) {
			tmptime = null;
		}
		setStrSuggestedDeliverlimitTime(tmptime);
		// 購買ステータス
		if (getStatus() != null) {
			setStrStatus(SelectPurchaseStatusPurchase.getLabelName(getStatus()
					.toString()));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 他社コードを取得します。
	 * @return otherCompanyCd
	 */
	public String getOtherCompanyCd() {
		return otherCompanyCd;
	}

	/**
	 * 他社コードを設定します。
	 * @param otherCompanyCd 他社コード
	 */
	public void setOtherCompanyCd(final String otherCompanyCd) {
		this.otherCompanyCd = otherCompanyCd;
	}

	/**
	 * 仕入先名称取得
	 * @return String 仕入先名称
	 */
	public String getSupplierName() {
		return this.supplierName;
	}

	/**
	 * 仕入先名称設定
	 * @param supplierName 仕入先名称
	 */
	public void setSupplierName(final String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 複数社購買を取得します。
	 * @return multiSupplierDivision
	 */
	public String getMultiSupplierDivision() {
		return multiSupplierDivision;
	}

	/**
	 * 複数社購買を設定します。
	 * @param multiSupplierDivision 複数社購買
	 */
	public void setMultiSupplierDivision(final String multiSupplierDivision) {
		this.multiSupplierDivision = multiSupplierDivision;
	}

	/**
	 * 支給品区分を取得します。
	 * @return suppliedDoodsDivision
	 */
	public String getSuppliedDoodsDivision() {
		return suppliedDoodsDivision;
	}

	/**
	 * 支給品区分を設定します。
	 * @param suppliedDoodsDivision 支給品区分
	 */
	public void setSuppliedDoodsDivision(final String suppliedDoodsDivision) {
		this.suppliedDoodsDivision = suppliedDoodsDivision;
	}

	/**
	 * 発注数量単位を取得します。
	 * @return orderUnit
	 */
	public String getOrderUnit() {
		return orderUnit;
	}

	/**
	 * 発注数量単位を設定します。
	 * @param orderUnit 発注数量単位
	 */
	public void setOrderUnit(final String orderUnit) {
		this.orderUnit = orderUnit;
	}

	/**
	 * 荷姿を取得します。
	 * @return styleOfPacking
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
	 * 発注者名称を取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 発注者名称を設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 納入先名称を取得します。
	 * @return deliveryName
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
	 * 納入先名称を取得します。
	 * @return locationName
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
	 * 担当部署名称を取得します。
	 * @return tantoSectionNm
	 */
	public String getTantoSectionNm() {
		return tantoSectionNm;
	}

	/**
	 * 担当部署名称を設定します。
	 * @param tantoSectionNm 担当部署名称
	 */
	public void setTantoSectionNm(final String tantoSectionNm) {
		this.tantoSectionNm = tantoSectionNm;
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
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 基準保管場所を取得します。
	 * @return defaultLocation
	 */
	public String getDefaultLocation() {
		return defaultLocation;
	}

	/**
	 * 基準保管場所を設定します。
	 * @param defaultLocation 基準保管場所
	 */
	public void setDefaultLocation(final String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	/**
	 * 発注日を取得します。
	 * @return strOrderDate
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 発注日を設定します。
	 * @param strOrderDate 発注日
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 納品希望日を取得します。
	 * @return suggestedDeliverlimitDate
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日を設定します。
	 * @param strSuggestedDeliverlimitDate 納品希望日
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望時刻を取得します。
	 * @return strSuggestedDeliverlimitTime
	 */
	public String getStrSuggestedDeliverlimitTime() {
		return strSuggestedDeliverlimitTime;
	}

	/**
	 * 納品希望時刻を設定します。
	 * @param strSuggestedDeliverlimitTime 納品希望時刻
	 */
	public void setStrSuggestedDeliverlimitTime(
			final String strSuggestedDeliverlimitTime) {
		this.strSuggestedDeliverlimitTime = strSuggestedDeliverlimitTime;
	}

	/**
	 * 重量を取得します。
	 * @return strOrderConvertQuantity
	 */
	public String getStrOrderConvertQuantity() {
		return strOrderConvertQuantity;
	}

	/**
	 * 重量を設定します。
	 * @param strOrderConvertQuantity 重量
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 単価を取得します。
	 * @return strOrderUnitprice
	 */
	public String getStrOrderUnitprice() {
		return strOrderUnitprice;
	}

	/**
	 * 単価を設定します。
	 * @param strOrderUnitprice 単価
	 */
	public void setStrOrderUnitprice(final String strOrderUnitprice) {
		this.strOrderUnitprice = strOrderUnitprice;
	}

	/**
	 * 金額を取得します。
	 * @return strSupplierOrdAmount
	 */
	public String getStrSupplierOrdAmount() {
		return strSupplierOrdAmount;
	}

	/**
	 * 金額を設定します。
	 * @param strSupplierOrdAmount 金額
	 */
	public void setStrSupplierOrdAmount(final String strSupplierOrdAmount) {
		this.strSupplierOrdAmount = strSupplierOrdAmount;
	}

	/**
	 * 発注数量を取得します。
	 * @return strOrderQuantity
	 */
	public String getStrOrderQuantity() {
		return strOrderQuantity;
	}

	/**
	 * 発注数量を設定します。
	 * @param strOrderQuantity 発注数量
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 購買ステータスを取得します。
	 * @return strStatus
	 */
	public String getStrStatus() {
		return strStatus;
	}

	/**
	 * 購買ステータスを設定します。
	 * @param strStatus 購買ステータス
	 */
	public void setStrStatus(final String strStatus) {
		this.strStatus = strStatus;
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
	public BigDecimal getSpotDivision() {
		return spotDivision;
	}

	/**
	 * スポット区分を設定します。
	 * @param spotDivision スポット区分
	 */
	public void setSpotDivision(final BigDecimal spotDivision) {
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
	 * 単価単位名称(画面用)を取得します。
	 * @return unitpriceUnit
	 */
	public String getUnitpriceUnit() {
		return unitpriceUnit;
	}

	/**
	 * 単価単位名称(画面用)を設定します。
	 * @param unitpriceUnit 単価単位(画面用)
	 */
	public void setUnitpriceUnit(final String unitpriceUnit) {
		this.unitpriceUnit = unitpriceUnit;
	}

	/**
	 * 発注まとめ場所を取得します。
	 * @return String 発注まとめ場所
	 */
	public String getOrderLocation() {
		return orderLocation;
	}

	/**
	 * 発注まとめ場所を設定します。
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
	 * 複数社購買を取得します。
	 * @return strMultiSupplierDivision
	 */
	public String getStrMultiSupplierDivision() {

		String multi = null;

		if (getMultiSupplierDivision() == null) {
			return multi;
		}

		if ("1".equals(getMultiSupplierDivision())) {
			multi = "無";
		} else if ("2".equals(getMultiSupplierDivision())) {
			multi = "有";
		}
		return multi;
	}

	/**
	 * 支給品区分を取得します。
	 * @return strSuppliedDoodsDivision
	 */
	public String getStrSuppliedDoodsDivision() {
		String sup = null;
		if (getSuppliedDoodsDivision() == null) {
			return sup;
		}

		if (getSuppliedDoodsDivision().equals("1")) {
			sup = "なし";
		} else if (getSuppliedDoodsDivision().equals("2")) {
			sup = "無償";
		} else if (getSuppliedDoodsDivision().equals("3")) {
			sup = "有償";
		}
		return sup;
	}

	public String getReducedTaxTargetFlg() {
		return reducedTaxTargetFlg;
	}

	public void setReducedTaxTargetFlg(String reducedTaxTargetFlg) {
		this.reducedTaxTargetFlg = reducedTaxTargetFlg;
	}

}
