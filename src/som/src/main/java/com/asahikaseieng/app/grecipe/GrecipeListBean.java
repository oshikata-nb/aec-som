/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;

/**
 * 原処方検索－画面表示用Bean
 * @author tosco
 */
public class GrecipeListBean extends GrecipeRecipeHeaderList {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** チェックボックス */
	private boolean selectedCheck;

	/**
	 * コンストラクタ
	 */
	public GrecipeListBean() {
	}

	//setter,getter-------------------------------------------------------
	/**
	 * チェックボックスを取得します。
	 * @return チェックボックス
	 */
	public boolean isSelectedCheck() {
		return selectedCheck;
	}

	/**
	 * チェックボックスを設定します。
	 * @param selectedCheck チェックボックス
	 */
	public void setSelectedCheck(final boolean selectedCheck) {
		this.selectedCheck = selectedCheck;
	}

}
