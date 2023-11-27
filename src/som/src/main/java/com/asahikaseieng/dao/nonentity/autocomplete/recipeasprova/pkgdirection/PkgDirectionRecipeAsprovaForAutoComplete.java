/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.pkgdirection;


/**
 * 包装指図－処方ASPROVA_データ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionRecipeAsprovaForAutoComplete extends PkgDirectionRecipeAsprovaForAutoCompleteBase {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** 使用資源名 */
	private String resouceName;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeAsprovaForAutoComplete() {
		super();
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 使用資源名を取得します。
	 * @return resouceName
	 */
	public String getResouceName() {
		return resouceName;
	}

	/**
	 * 使用資源名を設定します。
	 * @param resouceName 使用資源名
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

}
