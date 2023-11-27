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
 * 入荷一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ArrivalList extends ArrivalListBase implements
		PropertyTransferCallbacker {

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	private static final long serialVersionUID = 1L;

	/** 区分 1:原材料 */
	private static final String KBN1_MATERIAL = "1";

	/** 区分 2:外注製品(非直送) */
	private static final String KBN2_OUTSOURCING = "2";

	/** 区分 3:仕入在庫品 */
	private static final String KBN3_STOCK = "3";

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション paletteProducts */
	public static final String paletteProducts_COLUMN = "PALETTE_PRODUCTS";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション tbn */
	public static final String tbn_COLUMN = "KBN";

	/** COLUMNアノテーション sumArrivalQuantity */
	public static final String sumArrivalQuantity_COLUMN = "SUM_ARRIVAL_QUANTITY";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** 品目名称 */
	private String itemQueueName;

	/** 荷姿 */
	private String styleOfPacking;

	/** Kg換算係数(在庫) */
	private BigDecimal kgOfFractionManagement;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 荷主 */
	private BigDecimal shipperDivision;

	/** パレット上製品数 */
	private BigDecimal paletteProducts;

	/** 仕入先 */
	private String venderName1;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入先 */
	private String locationName;

	/** 種別 */
	private BigDecimal typeDivision;

	/** 区分(1:原材料(自社使用) 2:外注製品(非直送) 3:仕入在庫品) */
	private String kbn;

	/** 入荷予定数量合計 */
	private BigDecimal sumArrivalQuantity;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** チェックフラグ */
	private boolean checkFlg;

	/** ラベル数 */
	private String labelCount;

	/** 発注日(文字列) */
	private String strOrderDate;

	/** 納品希望日(文字列) */
	private String strSuggestedDeliverlimitDate;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/** 入荷予定数量(カンマ編集) */
	private String strArrivalQuantity;

	/** 小数点桁数 */
	private BigDecimal decimalPoint;

	/** 端数区分 */
	private BigDecimal round;

	/**
	 * コンストラクタ.
	 */
	public ArrivalList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 日付・数値の編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));
		// ラベル数
		initLabelCount();
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
	 * @return String パレット上製品数
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
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 仕入先設定
	 * @param venderName1 仕入先
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
	 * 区分取得
	 * @return String 区分(1:原材料(自社使用) 2:外注製品(非直送) 3:仕入在庫品)
	 */
	public String getKbn() {
		return kbn;
	}

	/**
	 * 区分設定
	 * @param kbn 区分(1:原材料(自社使用) 2:外注製品(非直送) 3:仕入在庫品)
	 */
	public void setKbn(final String kbn) {
		this.kbn = kbn;
	}

	/**
	 * 入荷予定数量合計取得
	 * @return BigDecimal 入荷予定数量合計
	 */
	public BigDecimal getSumArrivalQuantity() {
		return sumArrivalQuantity;
	}

	/**
	 * 入荷予定数量合計設定
	 * @param sumArrivalQuantity 入荷予定数量合計
	 */
	public void setSumArrivalQuantity(final BigDecimal sumArrivalQuantity) {
		this.sumArrivalQuantity = sumArrivalQuantity;
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
	 * 発注日(文字列)を取得します。
	 * @return String 発注日(文字列)
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 発注日(文字列)を設定します。
	 * @param strOrderDate 発注日(文字列)
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 納品希望日(文字列)を取得します。
	 * @return String 納品希望日(文字列)
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日(文字列)を設定します。
	 * @param strSuggestedDeliverlimitDate 納品希望日(文字列)
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
	 * 小数点桁数を取得します。
	 * @return BigDecimal 小数点桁数
	 */
	public BigDecimal getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final BigDecimal decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分を取得します。
	 * @return BigDecimal 端数区分
	 */
	public BigDecimal getRound() {
		return round;
	}

	/**
	 * 端数区分を設定します。
	 * @param round 端数区分
	 */
	public void setRound(final BigDecimal round) {
		this.round = round;
	}

	/**
	 * 購買ステータス(文字列)を取得します。
	 * @return String 購買ステータス(文字列)
	 */
	public String getStrStatus() {
		String ret = null;
		if (getStatus() != null) {
			ret = PurchaseStatus.getName(getStatus());
		}
		return ret;
	}

	/**
	 * 分納区分(文字列)を取得します。
	 * @return String 分納区分(文字列)
	 */
	public String getStrReplyContentsDivision() {
		String ret = "";
		if (getReplyContentsDivision() != null) {
			if (getReplyContentsDivision().equals(BigDecimal.ZERO)) {
				ret = "無";
			}
			if (getReplyContentsDivision().equals(BigDecimal.ONE)) {
				ret = "有";
			}
		}
		return ret;
	}

	/**
	 * 未入荷の場合のデフォルト表示用の入荷予定数量を計算する。 分納データを考慮しないといけないため、 単純に発注数量を表示するのではなく
	 * 発注数量から同一の発注番号の入荷予定数量合計値を引いた値を表示する。
	 * 
	 * @param orderQty
	 * @param sumArrivalQty
	 * @return
	 */
	private BigDecimal calcArrivalQuantity(final BigDecimal orderQty,
			final BigDecimal sumArrivalQty) {
		BigDecimal arrivalQuantity = new BigDecimal(0);

		if (orderQty == null || sumArrivalQty == null) {
			return null;
		}

		// 入荷予定数量＝発注数量－入荷予定数量合計
		arrivalQuantity = orderQty.subtract(sumArrivalQty);
		if (arrivalQuantity.signum() == -1) {
			arrivalQuantity = new BigDecimal(0);
		}
		return arrivalQuantity;
	}

	/**
	 * ラベル数の初期処理 ①原材料：入荷予定数量を設定 ②外注製品、仕入在庫品：入荷予定数量／パレット上製品数を設定
	 * 
	 * ※入荷予定数量 未入荷の場合は発注数量、入荷済の場合は入荷予定数量となる
	 */
	private void initLabelCount() {
		if (KBN1_MATERIAL.equals(getKbn())) {
			// 原材料の場合、初期値計算しない
			return;
		} else if (KBN2_OUTSOURCING.equals(getKbn())
				|| KBN3_STOCK.equals(getKbn())) {
			// 外注製品または仕入在庫品の場合、入荷予定数量／パレット上製品数
			if (getPaletteProducts() != null
					&& !getPaletteProducts().equals(BigDecimal.ZERO)) {
				// 購買ステータス 7:発注書未発行を追加。未入荷判断変更
				BigDecimal status = getStatus();
				if (PurchaseStatus.isMinyuka(status)) {
					// 未入荷
					// 発注数量－入荷予定数量合計
					BigDecimal orderQty = calcArrivalQuantity(
						getOrderQuantity(), getSumArrivalQuantity());
					if (orderQty != null) {
						setLabelCount(orderQty.divide(getPaletteProducts(), 0,
							BigDecimal.ROUND_UP).toString()); // 発注数量
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
