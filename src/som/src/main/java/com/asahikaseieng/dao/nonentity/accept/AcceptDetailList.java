/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 受入入力詳細画面_表示用データ格納クラス.
 *
 * @author tosco
 */
public class AcceptDetailList extends AcceptDetailListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション chargeOrganizationName */
	public static final String chargeOrganizationName_COLUMN = "CHARGE_ORGANIZATION_NAME";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション taxFreeCalcFlg */
	public static final String taxFreeCalcFlg_COLUMN = "REDUCED_TAX_TARGET_FLG";

	/** 品目名称 */
	private String itemQueueName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** Kg換算係数(在庫) */
	private BigDecimal kgOfFractionManagement;

	/** 仕入先 */
	private String venderName;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入先 */
	private String locationName;

	/** 部署名称 */
	private String organizationName;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 単位 */
	private String unit;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 種別 */
	private BigDecimal typeDivision;

	/** チェックフラグ */
	private boolean checkFlg;

	/**
	 * 区分(A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 * ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 *
	 * validation.xmlでの入庫ロケーション必須チェック用
	 */
	private String kbn;

	/** 行番号 */
	private String strRowNo;

	/** 発注日(スラッシュ編集) */
	private String strOrderDate;

	/** 納品希望日(スラッシュ編集) */
	private String strSuggestedDeliverlimitDate;

	/** 受入日付(スラッシュ編集) */
	private String strAcceptDate;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/** 受入数量(カンマ編集) */
	private String strAcceptQuantity;

	/** 増付数量(カンマ編集) */
	private String strIncreaseQuantity;

	/** 次回納品希望日(スラッシュ編集) */
	private String strNextDeliverlimitDate;

	/** 次回納品希望日(時間)(スラッシュ編集) */
	private String strNextDeliverlimitDateTime;

	/** 入荷予定数量(カンマ編集) */
	private String strArrivalQuantity;

	/** 免税計算対象フラグ */
	private String reducedTaxTargetFlg;

	/**
	 * コンストラクタ.
	 */
	public AcceptDetailList() {
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
		setStrAcceptDate(AecDateUtils.dateFormat(getAcceptDate(), "yyyy/MM/dd"));
		setStrNextDeliverlimitDate(AecDateUtils.dateFormat(
			getNextDeliverlimitDate(), "yyyy/MM/dd"));
		String tmptime = AecDateUtils.dateFormat(getNextDeliverlimitDate(),
			"HH:mm");
		if (StringUtils.isEmpty(tmptime) || tmptime.equals("00:00")) {
			tmptime = null;
		}
		setStrNextDeliverlimitDateTime(tmptime);

		// 行番号
		if (getRowNo() != null && !getRowNo().equals(BigDecimal.ZERO)) {
			setStrRowNo(getRowNo().toString());
		} else {
			setRowNo(new BigDecimal(1));
			setStrRowNo("1");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setAcceptQuantity(AecNumberUtils
				.convertBigDecimal(getStrAcceptQuantity()));
		setIncreaseQuantity(AecNumberUtils
				.convertBigDecimal(getStrIncreaseQuantity()));
		setAcceptDate(AecDateUtils.getTimestampYmdFormat(getStrAcceptDate()));
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
	 * 部署名称取得
	 * @return String 部署名称
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
	 * 担当部署名称取得
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
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
	 * 区分取得 (A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 * ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 * @return String 区分
	 */
	public String getKbn() {
		return kbn;
	}

	/**
	 * 区分設定 (A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 * ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 * @param kbn 区分
	 */
	public void setKbn(final String kbn) {
		this.kbn = kbn;
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
	 * 受入日付(スラッシュ編集)を取得します。
	 * @return String 受入日付(スラッシュ編集)
	 */
	public String getStrAcceptDate() {
		return strAcceptDate;
	}

	/**
	 * 受入日付(スラッシュ編集)を設定します。
	 * @param strAcceptDate 受入日付(スラッシュ編集)
	 */
	public void setStrAcceptDate(final String strAcceptDate) {
		this.strAcceptDate = strAcceptDate;
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
	 * 受入数量(カンマ編集)を取得します。
	 * @return String 受入数量(カンマ編集)
	 */
	public String getStrAcceptQuantity() {
		return strAcceptQuantity;
	}

	/**
	 * 受入数量(カンマ編集)を設定します。
	 * @param strAcceptQuantity 受入数量(カンマ編集)
	 */
	public void setStrAcceptQuantity(final String strAcceptQuantity) {
		this.strAcceptQuantity = strAcceptQuantity;
	}

	/**
	 * 増付数量(カンマ編集)を取得します。
	 * @return String 増付数量(カンマ編集)
	 */
	public String getStrIncreaseQuantity() {
		return strIncreaseQuantity;
	}

	/**
	 * 増付数量(カンマ編集)を設定します。
	 * @param strIncreaseQuantity 増付数量(カンマ編集)
	 */
	public void setStrIncreaseQuantity(final String strIncreaseQuantity) {
		this.strIncreaseQuantity = strIncreaseQuantity;
	}

	/**
	 * 次回納品希望日(スラッシュ編集)を取得します。
	 * @return String 次回納品希望日(スラッシュ編集)
	 */
	public String getStrNextDeliverlimitDate() {
		return strNextDeliverlimitDate;
	}

	/**
	 * 次回納品希望日(スラッシュ編集)を設定します。
	 * @param strNextDeliverlimitDate 次回納品希望日(スラッシュ編集)
	 */
	public void setStrNextDeliverlimitDate(final String strNextDeliverlimitDate) {
		this.strNextDeliverlimitDate = strNextDeliverlimitDate;
	}

	/**
	 * 次回納品希望日(時間)(スラッシュ編集)を取得します。
	 * @return String 次回納品希望日(時間)(スラッシュ編集)
	 */
	public String getStrNextDeliverlimitDateTime() {
		return strNextDeliverlimitDateTime;
	}

	/**
	 * 次回納品希望日(時間)(スラッシュ編集)を設定します。
	 * @param strNextDeliverlimitDateTime 次回納品希望日(時間)(スラッシュ編集)
	 */
	public void setStrNextDeliverlimitDateTime(
			final String strNextDeliverlimitDateTime) {
		this.strNextDeliverlimitDateTime = strNextDeliverlimitDateTime;
	}

	/**
	 * strArrivalQuantityを取得します。
	 * @return strArrivalQuantity
	 */
	public String getStrArrivalQuantity() {
		return strArrivalQuantity;
	}

	/**
	 * strArrivalQuantityを設定します。
	 * @param strArrivalQuantity strArrivalQuantity
	 */
	public void setStrArrivalQuantity(final String strArrivalQuantity) {
		this.strArrivalQuantity = strArrivalQuantity;
	}

	/**
	 * reducedTaxTargetFlgを取得します。
	 * @return reducedTaxTargetFlg
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
