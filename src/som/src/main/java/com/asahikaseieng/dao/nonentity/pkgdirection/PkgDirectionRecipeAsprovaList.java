/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

/**
 * 包装指図－処方ASPROVAデータ格納クラス.
 * @author kanri-user
 */
public class PkgDirectionRecipeAsprovaList extends PkgDirectionRecipeAsprovaListBase {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** 設備名称 */
	private String resouceName;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeAsprovaList() {
		super();
	}

	/**
	 * 設備名称を取得.
	 * @return 設備名称
	 */
	public String getResouceName() {
		return resouceName;
	}

	/**
	 * 設備名称を設定.
	 * @param resouceName 設備名称
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

}
