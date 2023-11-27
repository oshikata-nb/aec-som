/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection;

/**
 * PkgDirectionItemForAutoCompleteクラス.
 * @author tosco
 */
public class PkgDirectionItemForAutoComplete extends PkgDirectionItemForAutoCompleteBase {

	private static final long serialVersionUID = 1L;
	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/** 名称１ */
	private String name01;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionItemForAutoComplete() {
		super();
	}

	/**
	 * 名称１を取得します。
	 * @return 名称１
	 */
	public String getName01() {
		return name01;
	}

	/**
	 * 名称１を設定します。
	 * @param name01 名称１
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}
}
