package com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport;

/**
 * ##ItemのPagerConditionクラス：検索画面の検索条件##
 * @author t0011036
 */
public class ItemCategoryListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ItemCategoryListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhItemCategory;

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhItemCategoryを取得します。
	 * @return srhItemCategory
	 */
	public String getSrhItemCategory() {
		return srhItemCategory;
	}

	/**
	 * srhItemCategoryを設定します。
	 * @param srhItemCategory srhItemCategory
	 */
	public void setSrhItemCategory(final String srhItemCategory) {
		this.srhItemCategory = srhItemCategory;
	}
}
