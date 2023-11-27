/*
 * Created on 2009/02/10
 *
 * $copyright$
 */
package com.asahikaseieng.app.checkdigit;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 数値桁数チェック－フォーマットクラス
 * @author tosco
 *
 */
public final class CheckDigitForm extends AbstractForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 区分 */
	private String unitDivision;
	/** 取引先区分 */
	private String venderDivision;
	/** 取引先コード */
	private String venderCd;
	/** 変換対象数値文字列 */
	private String value;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public CheckDigitForm() {
	}

	/**
	 * unitDivisionを取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}


	/**
	 * unitDivisionを設定します。
	 * @param unitDivision unitDivision
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}


	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}


	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}


	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}


	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}


	/**
	 * valueを取得します。
	 * @return value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * valueを設定します。
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
}
