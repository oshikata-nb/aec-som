/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.arrival;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 入荷入力詳細画面_表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ArrivalDetailList extends ArrivalDetailListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション paletteProducts */
	public static final String paletteProducts_COLUMN = "PALETTE_PRODUCTS";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** 品目名称 */
	private String itemQueueName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** Kg換算係数(在庫) */
	private BigDecimal kgOfFractionManagement;

	/** 荷主 */
	private BigDecimal shipperDivision;

	/** パレット上製品数 */
	private BigDecimal paletteProducts;

	/** 仕入先 */
	private String venderName;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入先 */
	private String locationName;

	/** 担当部署 */
	private String organizationName;

	/** 単位 */
	private String unit;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 種別 */
	private BigDecimal typeDivision;

	/** チェックフラグ */
	private boolean checkFlg;

	/** 行番号 */
	private String strRowNo;

	/** ラベル数 */
	private String labelCount;

	/** 発注日(スラッシュ編集) */
	private String strOrderDate;

	/** 納品希望日(スラッシュ編集) */
	private String strSuggestedDeliverlimitDate;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/** 入荷予定数量(カンマ編集) */
	private String strArrivalQuantity;

	/**
	 * コンストラクタ.
	 */
	public ArrivalDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 日付のスラッシュ編集
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));

		// 行番号
		if (getRowNo() != null && !getRowNo().equals(BigDecimal.ZERO)) {
			setStrRowNo(getRowNo().toString());
		} else {
			setRowNo(new BigDecimal(1));
			setStrRowNo("1");
		}

		// 入荷済の場合
		if (getStatus().compareTo(PurchaseStatus.ARRIVALED) == 0) {
			// ラベル数
			initLabelCount();
		}
	}

	/**
	 * ラベル数の初期処理 ①原材料：入荷予定数量を設定 ②外注製品、仕入在庫品：入荷予定数量／パレット上製品数を設定
	 * 
	 * ※入荷予定数量 未入荷の場合は発注数量、入荷済の場合は入荷予定数量となる
	 */
	private void initLabelCount() {
		if (getTypeDivision().compareTo(new BigDecimal(3)) == -1) {
			// 原材料の場合、初期値計算しない
			return;
		} else if (getTypeDivision().compareTo(new BigDecimal(7)) == 0
				|| getTypeDivision().compareTo(new BigDecimal(5)) == 0) {
			// 外注製品または仕入在庫品の場合、入荷予定数量／パレット上製品数
			if (getPaletteProducts() != null
					&& !getPaletteProducts().equals(BigDecimal.ZERO)) {
				if (PurchaseStatus.isMinyuka(getStatus())) {
					// 未入荷
					if (getOrderQuantity() != null) {
						setLabelCount(getOrderQuantity().divide(
							getPaletteProducts(), 0, BigDecimal.ROUND_UP)
								.toString()); // 発注数量
					}
				} else {
					// 入荷済
					if (getArrivalQuantity() != null) {
						setLabelCount(getArrivalQuantity().divide(
							getPaletteProducts(), 0, BigDecimal.ROUND_UP)
								.toString()); // 入荷予定数量
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setArrivalQuantity(AecNumberUtils
				.convertBigDecimal(getStrArrivalQuantity()));
	}

	/* ---------- getter ,setter ---------- */
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
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * Kg換算係数(在庫)取得
	 * @return String Kg換算係数(在庫)
	 */
	public BigDecimal getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数(在庫)設定
	 * @param kgOfFractionManagement Kg換算係数(在庫)
	 */
	public void setKgOfFractionManagement(
			final BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * 荷主取得
	 * @return BigDecimal 荷主
	 */
	public BigDecimal getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 荷主設定
	 * @param shipperDivision 荷主
	 */
	public void setShipperDivision(final BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * パレット上製品数取得
	 * @return BigDecimal パレット上製品数
	 */
	public BigDecimal getPaletteProducts() {
		return paletteProducts;
	}

	/**
	 * パレット上製品数設定
	 * @param paletteProducts パレット上製品数
	 */
	public void setPaletteProducts(final BigDecimal paletteProducts) {
		this.paletteProducts = paletteProducts;
	}

	/**
	 * 仕入先取得
	 * @return String 仕入先
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先設定
	 * @param venderName 仕入先
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 納入先取得
	 * @return String 納入先
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先設定
	 * @param locationName 納入先
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 担当部署取得
	 * @return String 担当部署
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 担当部署設定
	 * @param organizationName 担当部署
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
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
	 * 運用管理単位(区分)取得
	 * @return String 運用管理単位(区分)
	 */
	public String getUnitDiv() {
		return unitDiv;
	}

	/**
	 * 運用管理単位(区分)設定
	 * @param unitDiv 運用管理単位(区分)
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
	}

	/**
	 * 種別取得
	 * @return BigDecimal 種別
	 */
	public BigDecimal getTypeDivision() {
		return typeDivision;
	}

	/**
	 * 種別設定
	 * @param typeDivision 種別
	 */
	public void setTypeDivision(final BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}

	/**
	 * チェックフラグを取得します。
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定します。
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 行番号を取得します。
	 * @return String 行番号
	 */
	public String getStrRowNo() {
		return strRowNo;
	}

	/**
	 * 行番号を設定します。
	 * @param strRowNo 行番号
	 */
	public void setStrRowNo(final String strRowNo) {
		this.strRowNo = strRowNo;
	}

	/**
	 * ラベル数を取得します。
	 * @return String ラベル数
	 */
	public String getLabelCount() {
		return labelCount;
	}

	/**
	 * ラベル数を設定します。
	 * @param labelCount ラベル数
	 */
	public void setLabelCount(final String labelCount) {
		this.labelCount = labelCount;
	}

	/**
	 * 発注日(スラッシュ編集)を取得します。
	 * @return String 発注日(スラッシュ編集)
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 発注日(スラッシュ編集)を設定します。
	 * @param strOrderDate 発注日(スラッシュ編集)
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 納品希望日(スラッシュ編集)を取得します。
	 * @return String 納品希望日(スラッシュ編集)
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日(スラッシュ編集)を設定します。
	 * @param strSuggestedDeliverlimitDate 納品希望日(スラッシュ編集)
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * 発注数量(カンマ編集)を取得します。
	 * @return String 発注数量(カンマ編集)
	 */
	public String getStrOrderQuantity() {
		return strOrderQuantity;
	}

	/**
	 * 発注数量(カンマ編集)を設定します。
	 * @param strOrderQuantity 発注数量(カンマ編集)
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 重量(カンマ編集)を取得します。
	 * @return String 重量(カンマ編集)
	 */
	public String getStrOrderConvertQuantity() {
		return strOrderConvertQuantity;
	}

	/**
	 * 重量(カンマ編集)を設定します。
	 * @param strOrderConvertQuantity 重量(カンマ編集)
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 入荷予定数量(カンマ編集)を取得します。
	 * @return String 入荷予定数量(カンマ編集)
	 */
	public String getStrArrivalQuantity() {
		return strArrivalQuantity;
	}

	/**
	 * 入荷予定数量(カンマ編集)を設定します。
	 * @param strArrivalQuantity 入荷予定数量(カンマ編集)
	 */
	public void setStrArrivalQuantity(final String strArrivalQuantity) {
		this.strArrivalQuantity = strArrivalQuantity;
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
	 * BigDecimal型からString型へ変換
	 * 
	 * @param bc 変換する値(String)
	 * @return String 変換後の値(BigDecimal)
	 */
	// private String comvertDecimalToStr(final BigDecimal bc) {
	// String ret = null;
	// if (bc == null) {
	// return ret;
	// }
	// ret = bc.toString();
	// return ret;
	// }
}
