/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipepegresouce.direction;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * ホールドタンクNOオートコンプリート用Form
 * @author tosco
 */
public class DirectionRecipePegResouceForAutoCompleteForm extends
		GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 調合タンクNO */
	private String compoundTankNo;

	/**
	 * コンストラクタ
	 */
	public DirectionRecipePegResouceForAutoCompleteForm() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public DirectionRecipePegResouceForAutoCompleteForm(final String code) {
		super(code);
	}
	//setter,getter-------------------------------------------------------
	/**
	 * 調合タンクNOを取得します。
	 * @return 調合タンクNO
	 */
	public String getCompoundTankNo() {
		return compoundTankNo;
	}

	/**
	 * 調合タンクNOを設定します。
	 * @param compoundTankNo 調合タンクNO
	 */
	public void setCompoundTankNo(final String compoundTankNo) {
		this.compoundTankNo = compoundTankNo;
	}

}
