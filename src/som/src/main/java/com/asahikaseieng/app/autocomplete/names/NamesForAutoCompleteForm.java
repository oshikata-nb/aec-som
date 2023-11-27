/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.names;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 各種名称マスタのオートコンプリート用Form
 * @author tosco
 */
public class NamesForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public NamesForAutoCompleteForm() {
	}

	/** 名称区分 */
	private String nameDivision;

	/**
	 * 名称区分取得.
	 * @return String 名称区分
	 */
	public String getNameDivision() {
		return nameDivision;
	}

	/**
	 * 名称区分設定.
	 * @param nameDivision 名称区分
	 */
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}
}
