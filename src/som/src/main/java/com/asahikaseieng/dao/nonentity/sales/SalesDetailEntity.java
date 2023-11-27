/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売上詳細画面表示用データ格納クラス.
 * 
 * @author tosco
 */
public class SalesDetailEntity extends SalesDetailEntityBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション searchKana */
	public static final String searchKana_COLUMN = "SEARCH_KANA";

	/** COLUMNアノテーション address */
	public static final String address_COLUMN = "ADDRESS";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション chargeOrganizationName */
	public static final String chargeOrganizationName_COLUMN = "CHARGE_ORGANIZATION_NAME";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション accountDebitSectionName */
	public static final String accountDebitSectionName_COLUMN = "ACCOUNTS_DEBIT_SECTION_NAME";

	/** COLUMNアノテーション debitTitleName */
	public static final String debitTitleName_COLUMN = "DEBIT_TITLE_NAME";

	/** COLUMNアノテーション accountCreditSectionName */
	public static final String accountCreditSectionName_COLUMN = "ACCOUNTS_CREDIT_SECTION_NAME";

	/** COLUMNアノテーション creditTitleName */
	public static final String creditTitleName_COLUMN = "CREDIT_TITLE_NAME";

	/** COLUMNアノテーション ryDescription */
	public static final String ryDescription_COLUMN = "RY_DESCRIPTION";

	/** COLUMNアノテーション calcDivision */
	public static final String calcDivision_COLUMN = "CALC_DIVISION";

	/** COLUMNアノテーション compCalcDivision */
	public static final String compCalcDivision_COLUMN = "COMP_CALC_DIVISION";

	/** COLUMNアノテーション articleDivision */
	public static final String articleDivision_COLUMN = "ARTICLE_DIVISION";

	/** 売上区分名称 */
	private String categoryName;

	/** 売上日(文字列) */
	private String strSalesDate;

	/** 勘定年月(表示用) */
	private String strAccountYears;

	/** 得意先名称 */
	private String venderName1;

	/** 得意先略称 */
	private String venderShortedName;

	/** 納入先名称 */
	private String deliveryName1;

	/** 納入先略称 */
	private String searchKana;

	/** 住所 */
	private String address;

	/** 電話番号 */
	private String telNo;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 会計部門借方部門名称 */
	private String accountDebitSectionName;

	/** 借方科目名称 */
	private String debitTitleName;

	/** 会計部門貸方部門名称 */
	private String accountCreditSectionName;

	/** 貸方科目名称 */
	private String creditTitleName;

	/** 品名名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位 */
	private String unitDivision;

	/** 理由 */
	private String ryDescription;

	/** 算出区分 */
	private BigDecimal calcDivision;

	/** 自社マスタ.消費税算出区分 */
	private BigDecimal compCalcDivision;

	/** 販売品区分 */
	private BigDecimal articleDivision;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailEntity() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 初期処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrSalesDate(AecDateUtils.dateFormat(getSalesDate(), "yyyy/MM/dd"));
		setStrAccountYears(AecDateUtils.dateFormat(AecDateUtils
				.getTimestampYmdHmFormat(getAccountYears(), "yyyyMM"),
			"yyyy/MM"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 売上区分名称を取得します。
	 * @return String 売上区分名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 売上区分名称を設定します。
	 * @param categoryName 売上区分名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 売上日(文字列)を取得します。
	 * @return String 売上日(文字列)
	 */
	public String getStrSalesDate() {
		return strSalesDate;
	}

	/**
	 * 売上日(文字列)を設定します。
	 * @param strSalesDate 売上日(文字列)
	 */
	public void setStrSalesDate(final String strSalesDate) {
		this.strSalesDate = strSalesDate;
	}

	/**
	 * 勘定年月(表示用)を取得します。
	 * @return String 勘定年月(表示用)
	 */
	public String getStrAccountYears() {
		return strAccountYears;
	}

	/**
	 * 勘定年月(表示用)を設定します。
	 * @param strAccountYears 勘定年月(表示用)
	 */
	public void setStrAccountYears(final String strAccountYears) {
		this.strAccountYears = strAccountYears;
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
	 * 納入先名称取得
	 * @return String 納入先名称
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * 納入先名称設定
	 * @param deliveryName1 納入先名称
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
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

	/**
	 * 納入先住所取得
	 * @return String 納入先住所
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 納入先住所設定
	 * @param address 納入先住所
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * 電話番号取得
	 * @return String 電話番号
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * 電話番号設定
	 * @param telNo 電話番号
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * 担当部署名称取得
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return this.chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
	}

	/**
	 * 会計部門借方部門名称取得.
	 * @return String 会計部門借方部門名称
	 */
	public String getAccountDebitSectionName() {
		return this.accountDebitSectionName;
	}

	/**
	 * 会計部門借方部門名称設定.
	 * @param accountDebitSectionName 会計部門借方部門名称
	 */
	public void setAccountDebitSectionName(final String accountDebitSectionName) {
		this.accountDebitSectionName = accountDebitSectionName;
	}

	/**
	 * 借方科目名称取得.
	 * @return String 借方科目名称
	 */
	public String getDebitTitleName() {
		return this.debitTitleName;
	}

	/**
	 * 借方科目名称設定.
	 * @param debitTitleName 借方科目名称
	 */
	public void setDebitTitleName(final String debitTitleName) {
		this.debitTitleName = debitTitleName;
	}

	/**
	 * 会計部門貸方部門名称取得.
	 * @return String 会計部門部門貸方名称
	 */
	public String getAccountCreditSectionName() {
		return this.accountCreditSectionName;
	}

	/**
	 * 会計部門貸方部門名称設定.
	 * @param accountCreditSectionName 会計部門貸方部門名称
	 */
	public void setAccountCreditSectionName(
			final String accountCreditSectionName) {
		this.accountCreditSectionName = accountCreditSectionName;
	}

	/**
	 * 貸方科目名称取得.
	 * @return String 貸方科目名称
	 */
	public String getCreditTitleName() {
		return this.creditTitleName;
	}

	/**
	 * 貸方科目名称設定.
	 * @param creditTitleName 貸方科目名称
	 */
	public void setCreditTitleName(final String creditTitleName) {
		this.creditTitleName = creditTitleName;
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
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
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
	 * 理由
	 * @return String 理由
	 */
	public String getRyDescription() {
		return this.ryDescription;
	}

	/**
	 * 理由
	 * @param ryDescription 理由
	 */
	public void setRyDescription(final String ryDescription) {
		this.ryDescription = ryDescription;
	}

	/**
	 * 算出区分取得
	 * @return BigDecimal 算出区分
	 */
	public BigDecimal getCalcDivision() {
		return calcDivision;
	}

	/**
	 * 算出区分設定
	 * @param calcDivision 算出区分
	 */
	public void setCalcDivision(final BigDecimal calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分取得
	 * @return BigDecimal 自社マスタ.消費税算出区分
	 */
	public BigDecimal getCompCalcDivision() {
		return compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分設定
	 * @param compCalcDivision 自社マスタ.消費税算出区分
	 */
	public void setCompCalcDivision(final BigDecimal compCalcDivision) {
		this.compCalcDivision = compCalcDivision;
	}

	/**
	 * 販売品区分取得
	 * @return BigDecimal 販売品区分
	 */
	public BigDecimal getArticleDivision() {
		return articleDivision;
	}

	/**
	 * 販売品区分設定
	 * @param articleDivision 販売品区分
	 */
	public void setArticleDivision(final BigDecimal articleDivision) {
		this.articleDivision = articleDivision;
	}
}
