/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.deposit;

import java.math.BigDecimal;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 取引先(入金用)のオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class DepositVenderForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/* 部署コード */
	private String organizationCd;

	/* 部署名称 */
	private String organizationName;

	/* 前受金区分 */
	private BigDecimal advanceDivision;

	/**
	 * コンストラクタ
	 */
	public DepositVenderForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public DepositVenderForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * advanceDivisionを取得します。
	 * @return advanceDivision
	 */
	public BigDecimal getAdvanceDivision() {
		return advanceDivision;
	}

	/**
	 * advanceDivisionを設定します。
	 * @param advanceDivision advanceDivision
	 */
	public void setAdvanceDivision(final BigDecimal advanceDivision) {
		this.advanceDivision = advanceDivision;
	}
}
