/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.pkgdirection;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 包装指図－基本処方ヘッダ情報AutoCompleteFormクラス
 * @author tosco
 */
public class PkgDirectionRecipeHeaderForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 生産ライン */
	private String srhProductionLine;

	/** 主要製品コード(品目コード） */
	private String srhItemCd;

	/** 他社コード１ */
	private String srhOtherCompanyCd1;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeHeaderForAutoCompleteForm() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public PkgDirectionRecipeHeaderForAutoCompleteForm(final String code) {
		super(code);
	}

	/**
	 * 生産ライン取得
	 * @return String 生産ライン
	*/
	public String getSrhProductionLine() {
		return this.srhProductionLine;
	}

	/**
	 * 生産ライン設定
	 * @param srhProductionLine 生産ライン
	*/
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * 主要製品コード(品目コード）取得
	 * @return String 主要製品コード(品目コード）
	*/
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 主要製品コード(品目コード）設定
	 * @param srhItemCd 主要製品コード(品目コード）
	*/
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 他社コード１を取得します。
	 * @return 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}
}
