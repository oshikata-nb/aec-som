/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.materialrinput;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 外注原材料投入実績一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class MaterialRinputList extends MaterialRinputListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 仕入先 */
	private String venderName1;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入先 */
	private String locationName;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 単位 */
	private String unit;

	/** 発注日(文字列) */
	private String strOrderDate;

	/** 納品希望日(文字列) */
	private String strSuggestedDeliverlimitDate;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputList() {
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
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
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

}
