/*
 * Created on 2009/11/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.monthlyvenderforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepMonthlyVenderクラス.
 * @author kanri-user
 */
public class RepMonthlyVenderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepMonthlyVenderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション zenzan */
	public static final String zenzan_COLUMN = "ZENZAN";

	/** COLUMNアノテーション creditNyukin */
	public static final String creditNyukin_COLUMN = "CREDIT_NYUKIN";

	/** COLUMNアノテーション creditNyukinSonota */
	public static final String creditNyukinSonota_COLUMN = "CREDIT_NYUKIN_SONOTA";

	/** COLUMNアノテーション creditNyukinAll */
	public static final String creditNyukinAll_COLUMN = "CREDIT_NYUKIN_ALL";

	/** COLUMNアノテーション salesUriage */
	public static final String salesUriage_COLUMN = "SALES_URIAGE";

	/** COLUMNアノテーション salesUriageHenpin */
	public static final String salesUriageHenpin_COLUMN = "SALES_URIAGE_HENPIN";

	/** COLUMNアノテーション salesUriageTax */
	public static final String salesUriageTax_COLUMN = "SALES_URIAGE_TAX";

	/** COLUMNアノテーション salesUriageAll */
	public static final String salesUriageAll_COLUMN = "SALES_URIAGE_ALL";

	/** COLUMNアノテーション salesUriageMi */
	public static final String salesUriageMi_COLUMN = "SALES_URIAGE_MI";

	/** COLUMNアノテーション salesUriageHenpinMi */
	public static final String salesUriageHenpinMi_COLUMN = "SALES_URIAGE_HENPIN_MI";

	/** COLUMNアノテーション salesUriageTaxMi */
	public static final String salesUriageTaxMi_COLUMN = "SALES_URIAGE_TAX_MI";

	/** COLUMNアノテーション salesUriageAllMi */
	public static final String salesUriageAllMi_COLUMN = "SALES_URIAGE_ALL_MI";

	/** COLUMNアノテーション salesAll */
	public static final String salesAll_COLUMN = "SALES_ALL";

	/** COLUMNアノテーション touzan */
	public static final String touzan_COLUMN = "TOUZAN";

	/** COLUMNアノテーション maeukezan */
	public static final String maeukezan_COLUMN = "MAEUKEZAN";

	//
	// インスタンスフィールド
	//

	private String venderCd;

	private String venderName1;

	private String venderName2;

	private String organizationCd;

	private java.math.BigDecimal zenzan;

	private java.math.BigDecimal creditNyukin;

	private java.math.BigDecimal creditNyukinSonota;

	private java.math.BigDecimal creditNyukinAll;

	private java.math.BigDecimal salesUriage;

	private java.math.BigDecimal salesUriageHenpin;

	private java.math.BigDecimal salesUriageTax;

	private java.math.BigDecimal salesUriageAll;

	private java.math.BigDecimal salesUriageMi;

	private java.math.BigDecimal salesUriageHenpinMi;

	private java.math.BigDecimal salesUriageTaxMi;

	private java.math.BigDecimal salesUriageAllMi;

	private java.math.BigDecimal salesAll;

	private java.math.BigDecimal touzan;

	private java.math.BigDecimal maeukezan;

	//
	// インスタンスメソッド
	//

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2取得.
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * zenzan取得.
	 * @return zenzan
	 */
	public java.math.BigDecimal getZenzan() {
		return this.zenzan;
	}

	/**
	 * zenzan設定.
	 * @param zenzan zenzan
	 */
	public void setZenzan(final java.math.BigDecimal zenzan) {
		this.zenzan = zenzan;
	}

	/**
	 * creditNyukin取得.
	 * @return creditNyukin
	 */
	public java.math.BigDecimal getCreditNyukin() {
		return this.creditNyukin;
	}

	/**
	 * creditNyukin設定.
	 * @param creditNyukin creditNyukin
	 */
	public void setCreditNyukin(final java.math.BigDecimal creditNyukin) {
		this.creditNyukin = creditNyukin;
	}

	/**
	 * creditNyukinSonota取得.
	 * @return creditNyukinSonota
	 */
	public java.math.BigDecimal getCreditNyukinSonota() {
		return this.creditNyukinSonota;
	}

	/**
	 * creditNyukinSonota設定.
	 * @param creditNyukinSonota creditNyukinSonota
	 */
	public void setCreditNyukinSonota(final java.math.BigDecimal creditNyukinSonota) {
		this.creditNyukinSonota = creditNyukinSonota;
	}

	/**
	 * creditNyukinAll取得.
	 * @return creditNyukinAll
	 */
	public java.math.BigDecimal getCreditNyukinAll() {
		return this.creditNyukinAll;
	}

	/**
	 * creditNyukinAll設定.
	 * @param creditNyukinAll creditNyukinAll
	 */
	public void setCreditNyukinAll(final java.math.BigDecimal creditNyukinAll) {
		this.creditNyukinAll = creditNyukinAll;
	}

	/**
	 * salesUriage取得.
	 * @return salesUriage
	 */
	public java.math.BigDecimal getSalesUriage() {
		return this.salesUriage;
	}

	/**
	 * salesUriage設定.
	 * @param salesUriage salesUriage
	 */
	public void setSalesUriage(final java.math.BigDecimal salesUriage) {
		this.salesUriage = salesUriage;
	}

	/**
	 * salesUriageHenpin取得.
	 * @return salesUriageHenpin
	 */
	public java.math.BigDecimal getSalesUriageHenpin() {
		return this.salesUriageHenpin;
	}

	/**
	 * salesUriageHenpin設定.
	 * @param salesUriageHenpin salesUriageHenpin
	 */
	public void setSalesUriageHenpin(final java.math.BigDecimal salesUriageHenpin) {
		this.salesUriageHenpin = salesUriageHenpin;
	}

	/**
	 * salesUriageTax取得.
	 * @return salesUriageTax
	 */
	public java.math.BigDecimal getSalesUriageTax() {
		return this.salesUriageTax;
	}

	/**
	 * salesUriageTax設定.
	 * @param salesUriageTax salesUriageTax
	 */
	public void setSalesUriageTax(final java.math.BigDecimal salesUriageTax) {
		this.salesUriageTax = salesUriageTax;
	}

	/**
	 * salesUriageAll取得.
	 * @return salesUriageAll
	 */
	public java.math.BigDecimal getSalesUriageAll() {
		return this.salesUriageAll;
	}

	/**
	 * salesUriageAll設定.
	 * @param salesUriageAll salesUriageAll
	 */
	public void setSalesUriageAll(final java.math.BigDecimal salesUriageAll) {
		this.salesUriageAll = salesUriageAll;
	}

	/**
	 * salesUriageMi取得.
	 * @return salesUriageMi
	 */
	public java.math.BigDecimal getSalesUriageMi() {
		return this.salesUriageMi;
	}

	/**
	 * salesUriageMi設定.
	 * @param salesUriageMi salesUriageMi
	 */
	public void setSalesUriageMi(final java.math.BigDecimal salesUriageMi) {
		this.salesUriageMi = salesUriageMi;
	}

	/**
	 * salesUriageHenpinMi取得.
	 * @return salesUriageHenpinMi
	 */
	public java.math.BigDecimal getSalesUriageHenpinMi() {
		return this.salesUriageHenpinMi;
	}

	/**
	 * salesUriageHenpinMi設定.
	 * @param salesUriageHenpinMi salesUriageHenpinMi
	 */
	public void setSalesUriageHenpinMi(final java.math.BigDecimal salesUriageHenpinMi) {
		this.salesUriageHenpinMi = salesUriageHenpinMi;
	}

	/**
	 * salesUriageTaxMi取得.
	 * @return salesUriageTaxMi
	 */
	public java.math.BigDecimal getSalesUriageTaxMi() {
		return this.salesUriageTaxMi;
	}

	/**
	 * salesUriageTaxMi設定.
	 * @param salesUriageTaxMi salesUriageTaxMi
	 */
	public void setSalesUriageTaxMi(final java.math.BigDecimal salesUriageTaxMi) {
		this.salesUriageTaxMi = salesUriageTaxMi;
	}

	/**
	 * salesUriageAllMi取得.
	 * @return salesUriageAllMi
	 */
	public java.math.BigDecimal getSalesUriageAllMi() {
		return this.salesUriageAllMi;
	}

	/**
	 * salesUriageAllMi設定.
	 * @param salesUriageAllMi salesUriageAllMi
	 */
	public void setSalesUriageAllMi(final java.math.BigDecimal salesUriageAllMi) {
		this.salesUriageAllMi = salesUriageAllMi;
	}

	/**
	 * salesAll取得.
	 * @return salesAll
	 */
	public java.math.BigDecimal getSalesAll() {
		return this.salesAll;
	}

	/**
	 * salesAll設定.
	 * @param salesAll salesAll
	 */
	public void setSalesAll(final java.math.BigDecimal salesAll) {
		this.salesAll = salesAll;
	}

	/**
	 * touzan取得.
	 * @return touzan
	 */
	public java.math.BigDecimal getTouzan() {
		return this.touzan;
	}

	/**
	 * touzan設定.
	 * @param touzan touzan
	 */
	public void setTouzan(final java.math.BigDecimal touzan) {
		this.touzan = touzan;
	}

	/**
	 * maeukezan取得.
	 * @return maeukezan
	 */
	public java.math.BigDecimal getMaeukezan() {
		return this.maeukezan;
	}

	/**
	 * maeukezan設定.
	 * @param maeukezan maeukezan
	 */
	public void setMaeukezan(final java.math.BigDecimal maeukezan) {
		this.maeukezan = maeukezan;
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

