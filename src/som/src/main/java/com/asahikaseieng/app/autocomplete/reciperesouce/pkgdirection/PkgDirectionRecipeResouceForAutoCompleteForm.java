/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.pkgdirection;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 包装指図－包装ラインオートコンプリート用Form
 * @author tosco
 */
public class PkgDirectionRecipeResouceForAutoCompleteForm extends
		GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 生産ライン */
	private String productionLine;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeResouceForAutoCompleteForm() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public PkgDirectionRecipeResouceForAutoCompleteForm(final String code) {
		super(code);
	}
	//setter,getter-------------------------------------------------------
	/**
	 * 生産ラインを取得します。
	 * @return 生産ライン
	 */
	public String getProductionLine() {
		return productionLine;
	}

	/**
	 * 生産ラインを設定します。
	 * @param productionLine 生産ライン
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

}
