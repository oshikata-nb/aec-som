/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ArticleAttributeQueueDetailクラス.
 * @author t0011036
 */
public class ArticleAttributeQueueDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArticleAttributeQueueDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション articleStatus */
	public static final String articleStatus_COLUMN = "ARTICLE_STATUS";

	/** COLUMNアノテーション articlePriceCalcDivision */
	public static final String articlePriceCalcDivision_COLUMN = "ARTICLE_PRICE_CALC_DIVISION";

	/** COLUMNアノテーション sellingPrice */
	public static final String sellingPrice_COLUMN = "SELLING_PRICE";

	/** COLUMNアノテーション paletteProducts */
	public static final String paletteProducts_COLUMN = "PALETTE_PRODUCTS";

	/** COLUMNアノテーション janCd */
	public static final String janCd_COLUMN = "JAN_CD";

	/** COLUMNアノテーション itfCd */
	public static final String itfCd_COLUMN = "ITF_CD";

	/** COLUMNアノテーション safetyLeadTime */
	public static final String safetyLeadTime_COLUMN = "SAFETY_LEAD_TIME";

	/** COLUMNアノテーション articleSectionCd */
	public static final String articleSectionCd_COLUMN = "ARTICLE_SECTION_CD";

	/** COLUMNアノテーション articleSectionName */
	public static final String articleSectionName_COLUMN = "ARTICLE_SECTION_NAME";

	/** COLUMNアノテーション articleAccountsCd */
	public static final String articleAccountsCd_COLUMN = "ARTICLE_ACCOUNTS_CD";

	/** COLUMNアノテーション articleAccountsName */
	public static final String articleAccountsName_COLUMN = "ARTICLE_ACCOUNTS_NAME";

	/** COLUMNアノテーション articleTaxDivision */
	public static final String articleTaxDivision_COLUMN = "ARTICLE_TAX_DIVISION";

	/** COLUMNアノテーション financialClassCd */
	public static final String financialClassCd_COLUMN = "FINANCIAL_CLASS_CD";

	/** COLUMNアノテーション financialClassName */
	public static final String financialClassName_COLUMN = "FINANCIAL_CLASS_NAME";

	/** COLUMNアノテーション keepDivision */
	public static final String keepDivision_COLUMN = "KEEP_DIVISION";

	/** COLUMNアノテーション articleUpdateDate */
	public static final String articleUpdateDate_COLUMN = "ARTICLE_UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.math.BigDecimal articleStatus;

	private java.math.BigDecimal articlePriceCalcDivision;

	private java.math.BigDecimal sellingPrice;

	private java.math.BigDecimal paletteProducts;

	private String janCd;

	private String itfCd;

	private java.math.BigDecimal safetyLeadTime;

	private String articleSectionCd;

	private String articleSectionName;

	private String articleAccountsCd;

	private String articleAccountsName;

	private java.math.BigDecimal articleTaxDivision;

	private String financialClassCd;

	private String financialClassName;

	private java.math.BigDecimal keepDivision;

	private java.sql.Timestamp articleUpdateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * articleStatus取得.
	 * @return articleStatus
	 */
	public java.math.BigDecimal getArticleStatus() {
		return this.articleStatus;
	}

	/**
	 * articleStatus設定.
	 * @param articleStatus articleStatus
	 */
	public void setArticleStatus(final java.math.BigDecimal articleStatus) {
		this.articleStatus = articleStatus;
	}

	/**
	 * articlePriceCalcDivision取得.
	 * @return articlePriceCalcDivision
	 */
	public java.math.BigDecimal getArticlePriceCalcDivision() {
		return this.articlePriceCalcDivision;
	}

	/**
	 * articlePriceCalcDivision設定.
	 * @param articlePriceCalcDivision articlePriceCalcDivision
	 */
	public void setArticlePriceCalcDivision(final java.math.BigDecimal articlePriceCalcDivision) {
		this.articlePriceCalcDivision = articlePriceCalcDivision;
	}

	/**
	 * sellingPrice取得.
	 * @return sellingPrice
	 */
	public java.math.BigDecimal getSellingPrice() {
		return this.sellingPrice;
	}

	/**
	 * sellingPrice設定.
	 * @param sellingPrice sellingPrice
	 */
	public void setSellingPrice(final java.math.BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * paletteProducts取得.
	 * @return paletteProducts
	 */
	public java.math.BigDecimal getPaletteProducts() {
		return this.paletteProducts;
	}

	/**
	 * paletteProducts設定.
	 * @param paletteProducts paletteProducts
	 */
	public void setPaletteProducts(final java.math.BigDecimal paletteProducts) {
		this.paletteProducts = paletteProducts;
	}

	/**
	 * janCd取得.
	 * @return janCd
	 */
	public String getJanCd() {
		return this.janCd;
	}

	/**
	 * janCd設定.
	 * @param janCd janCd
	 */
	public void setJanCd(final String janCd) {
		this.janCd = janCd;
	}

	/**
	 * itfCd取得.
	 * @return itfCd
	 */
	public String getItfCd() {
		return this.itfCd;
	}

	/**
	 * itfCd設定.
	 * @param itfCd itfCd
	 */
	public void setItfCd(final String itfCd) {
		this.itfCd = itfCd;
	}

	/**
	 * safetyLeadTime取得.
	 * @return safetyLeadTime
	 */
	public java.math.BigDecimal getSafetyLeadTime() {
		return this.safetyLeadTime;
	}

	/**
	 * safetyLeadTime設定.
	 * @param safetyLeadTime safetyLeadTime
	 */
	public void setSafetyLeadTime(final java.math.BigDecimal safetyLeadTime) {
		this.safetyLeadTime = safetyLeadTime;
	}

	/**
	 * articleSectionCd取得.
	 * @return articleSectionCd
	 */
	public String getArticleSectionCd() {
		return this.articleSectionCd;
	}

	/**
	 * articleSectionCd設定.
	 * @param articleSectionCd articleSectionCd
	 */
	public void setArticleSectionCd(final String articleSectionCd) {
		this.articleSectionCd = articleSectionCd;
	}

	/**
	 * articleSectionName取得.
	 * @return articleSectionName
	 */
	public String getArticleSectionName() {
		return this.articleSectionName;
	}

	/**
	 * articleSectionName設定.
	 * @param articleSectionName articleSectionName
	 */
	public void setArticleSectionName(final String articleSectionName) {
		this.articleSectionName = articleSectionName;
	}

	/**
	 * articleAccountsCd取得.
	 * @return articleAccountsCd
	 */
	public String getArticleAccountsCd() {
		return this.articleAccountsCd;
	}

	/**
	 * articleAccountsCd設定.
	 * @param articleAccountsCd articleAccountsCd
	 */
	public void setArticleAccountsCd(final String articleAccountsCd) {
		this.articleAccountsCd = articleAccountsCd;
	}

	/**
	 * articleAccountsName取得.
	 * @return articleAccountsName
	 */
	public String getArticleAccountsName() {
		return this.articleAccountsName;
	}

	/**
	 * articleAccountsName設定.
	 * @param articleAccountsName articleAccountsName
	 */
	public void setArticleAccountsName(final String articleAccountsName) {
		this.articleAccountsName = articleAccountsName;
	}

	/**
	 * articleTaxDivision取得.
	 * @return articleTaxDivision
	 */
	public java.math.BigDecimal getArticleTaxDivision() {
		return this.articleTaxDivision;
	}

	/**
	 * articleTaxDivision設定.
	 * @param articleTaxDivision articleTaxDivision
	 */
	public void setArticleTaxDivision(final java.math.BigDecimal articleTaxDivision) {
		this.articleTaxDivision = articleTaxDivision;
	}

	/**
	 * financialClassCd取得.
	 * @return financialClassCd
	 */
	public String getFinancialClassCd() {
		return this.financialClassCd;
	}

	/**
	 * financialClassCd設定.
	 * @param financialClassCd financialClassCd
	 */
	public void setFinancialClassCd(final String financialClassCd) {
		this.financialClassCd = financialClassCd;
	}

	/**
	 * financialClassName取得.
	 * @return financialClassName
	 */
	public String getFinancialClassName() {
		return this.financialClassName;
	}

	/**
	 * financialClassName設定.
	 * @param financialClassName financialClassName
	 */
	public void setFinancialClassName(final String financialClassName) {
		this.financialClassName = financialClassName;
	}

	/**
	 * keepDivision取得.
	 * @return keepDivision
	 */
	public java.math.BigDecimal getKeepDivision() {
		return this.keepDivision;
	}

	/**
	 * keepDivision設定.
	 * @param keepDivision keepDivision
	 */
	public void setKeepDivision(final java.math.BigDecimal keepDivision) {
		this.keepDivision = keepDivision;
	}

	/**
	 * articleUpdateDate取得.
	 * @return articleUpdateDate
	 */
	public java.sql.Timestamp getArticleUpdateDate() {
		return this.articleUpdateDate;
	}

	/**
	 * articleUpdateDate設定.
	 * @param articleUpdateDate articleUpdateDate
	 */
	public void setArticleUpdateDate(final java.sql.Timestamp articleUpdateDate) {
		this.articleUpdateDate = articleUpdateDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

