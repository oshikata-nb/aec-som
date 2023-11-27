/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist;

/**
 * ItemRelatedMgrecipeListクラス.
 * @author t0011036
 */
public class ItemRelatedMgrecipeList extends ItemRelatedMgrecipeListBase {

	private static final long serialVersionUID = 1L;

	private String strMgrecipeUpdateDate;

	private String link;

	/**
	 * コンストラクタ.
	 */
	public ItemRelatedMgrecipeList() {
		super();
	}

	/**
	 * strMgrecipeUpdateDateを取得します。
	 * @return strMgrecipeUpdateDate
	 */
	public String getStrMgrecipeUpdateDate() {
		return strMgrecipeUpdateDate;
	}

	/**
	 * strMgrecipeUpdateDateを設定します。
	 * @param strMgrecipeUpdateDate strMgrecipeUpdateDate
	 */
	public void setStrMgrecipeUpdateDate(final String strMgrecipeUpdateDate) {
		this.strMgrecipeUpdateDate = strMgrecipeUpdateDate;
	}

	/**
	 * linkを取得します。
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * linkを設定します。
	 * @param link link
	 */
	public void setLink(final String link) {
		this.link = link;
	}
}
