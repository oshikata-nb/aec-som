/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class SalesList extends SalesListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** 分類名 */
	private String categoryName;

	/** 勘定年月(文字列) */
	private String strAccountYears;

	/** 売上日(文字列) */
	private String strSalesDate;

	/** 得意先名称 */
	private String venderName1;

	/** 支払先略称 */
	private String venderShortedName;

	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 数量(文字列) */
	private String strSalesQuantity;

	/** 金額(文字列) */
	private String strSalesAmount;

	/** 単位区分 */
	private String unitDivision;

	/** 仮単価フラグ（チェックボックス) */
	private boolean tmpUnitpriceFlgCheck;

	/** 発行済フラグ(チェックボックス) */
	private boolean slipPublishCompCheck;

	/** 客先注文番号 */
	private String customerOrderNo;

	/** 納入先コード */
	private String searchKana;

	/**
	 * コンストラクタ.
	 */
	public SalesList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		// 売上日
		setStrSalesDate(AecDateUtils.dateFormat(getSalesDate(), "yyyy/MM/dd"));
		// 勘定年月
		setStrAccountYears(AecDateUtils.dateFormat(
			AecDateUtils.getTimestampYmdHmFormat(getAccountYears().toString(),
				"yyyyMM"), "yyyy/MM"));
		// 仮単価フラグ
		boolean tmpUnitpriceFlg = getTmpUnitpriceFlg().toString().equals("1");
		setTmpUnitpriceFlgCheck(tmpUnitpriceFlg);
		// 発行済フラグ
		boolean slipPublishCompFlg = getSlipPublishComp().toString()
				.equals("1");
		setSlipPublishCompCheck(slipPublishCompFlg);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */
	/**
	 * 売上区分（分類名）取得
	 * @return String 売上区分（分類名）
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * 売上区分（分類名）設定
	 * @param categoryName 売上区分（分類名）
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 勘定年月(文字列)取得
	 * @return String 勘定年月(文字列)
	 */
	public String getStrAccountYears() {
		return this.strAccountYears;
	}

	/**
	 * 勘定年月(文字列)設定
	 * @param strAccountYears 勘定年月(文字列)
	 */
	public void setStrAccountYears(final String strAccountYears) {
		this.strAccountYears = strAccountYears;
	}

	/**
	 * 売上日(文字列)取得
	 * @return String 売上日(文字列)
	 */
	public String getStrSalesDate() {
		return this.strSalesDate;
	}

	/**
	 * 売上日(文字列)設定
	 * @param strSalesDate 売上日(文字列)
	 */
	public void setStrSalesDate(final String strSalesDate) {
		this.strSalesDate = strSalesDate;
	}

	/**
	 * 得意先名称取得
	 * @return String 得意先名称
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * 得意先名称設定
	 * @param venderName1 得意先名称
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
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
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
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 数量(文字列)取得
	 * @return String 数量(文字列)
	 */
	public String getStrSalesQuantity() {
		return this.strSalesQuantity;
	}

	/**
	 * 数量(文字列)設定
	 * @param strSalesQuantity 数量(文字列)
	 */
	public void setStrSalesQuantity(final String strSalesQuantity) {
		this.strSalesQuantity = strSalesQuantity;
	}

	/**
	 * 金額(文字列)取得
	 * @return String 金額(文字列)
	 */
	public String getStrSalesAmount() {
		return this.strSalesAmount;
	}

	/**
	 * 金額(文字列)設定
	 * @param strSalesAmount 金額(文字列)
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
	}

	/**
	 * 単位区分
	 * @return String 単位区分
	 */
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * 単位区分
	 * @param unitDivision 単位区分
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * 仮単価フラグ（チェックボックス)取得
	 * @return String 仮単価フラグ（チェックボックス)
	 */
	public boolean getTmpUnitpriceFlgCheck() {
		return this.tmpUnitpriceFlgCheck;
	}

	/**
	 * 仮単価フラグ（チェックボックス)設定
	 * @param tmpUnitpriceFlgCheck 仮単価フラグ（チェックボックス)
	 */
	public void setTmpUnitpriceFlgCheck(final boolean tmpUnitpriceFlgCheck) {
		this.tmpUnitpriceFlgCheck = tmpUnitpriceFlgCheck;
	}

	/**
	 * 発行済フラグ（チェックボックス)取得
	 * @return String 発行済フラグ（チェックボックス)
	 */
	public boolean getSlipPublishCompCheck() {
		return this.slipPublishCompCheck;
	}

	/**
	 * 発行済フラグ（チェックボックス)設定
	 * @param slipPublishCompCheck 発行済フラグ（チェックボックス)
	 */
	public void setSlipPublishCompCheck(final boolean slipPublishCompCheck) {
		this.slipPublishCompCheck = slipPublishCompCheck;
	}

	/**
	 * customerOrderNo取得
	 * @return customerOrderNo customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return customerOrderNo;
	}

	/**
	 * customerOrderNo設定
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(final String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * searchKana取得
	 * @return searchKana searchKana
	 */
	public String getSearchKana() {
		return searchKana;
	}

	/**
	 * searchKana設定
	 * @param searchKana searchKana
	 */
	public void setSearchKana(final String searchKana) {
		this.searchKana = searchKana;
	}
}
