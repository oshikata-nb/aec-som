/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.direction;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 予備溶解タンクオートコンプリート用Form
 * @author tosco
 */
public class DirectionRecipeResouceForAutoCompleteForm extends
		GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 生産ライン */
	private String productionLine;

	/**
	 * コンストラクタ
	 */
	public DirectionRecipeResouceForAutoCompleteForm() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public DirectionRecipeResouceForAutoCompleteForm(final String code) {
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
