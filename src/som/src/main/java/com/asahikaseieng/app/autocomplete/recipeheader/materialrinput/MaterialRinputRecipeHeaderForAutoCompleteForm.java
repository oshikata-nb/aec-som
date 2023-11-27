/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.materialrinput;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoCompleteFormクラス
 * @author tosco
 */
public class MaterialRinputRecipeHeaderForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主要製品コード(品目コード） */
	private String srhItemCd;

	/**
	 * コンストラクタ
	 */
	public MaterialRinputRecipeHeaderForAutoCompleteForm() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public MaterialRinputRecipeHeaderForAutoCompleteForm(final String code) {
		super(code);
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

}
