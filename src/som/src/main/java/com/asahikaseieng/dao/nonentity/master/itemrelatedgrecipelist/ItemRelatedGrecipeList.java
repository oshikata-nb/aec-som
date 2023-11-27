/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist;

/**
 * ItemRelatedGrecipeListクラス.
 * @author t0011036
 */
public class ItemRelatedGrecipeList extends ItemRelatedGrecipeListBase {

	private static final long serialVersionUID = 1L;

	private String strGrecipeUpdateDate;

	private String link;

	/**
	 * コンストラクタ.
	 */
	public ItemRelatedGrecipeList() {
		super();
	}

	/**
	 * strGrecipeUpdateDateを取得します。
	 * @return strGrecipeUpdateDate
	 */
	public String getStrGrecipeUpdateDate() {
		return strGrecipeUpdateDate;
	}

	/**
	 * strGrecipeUpdateDateを設定します。
	 * @param strGrecipeUpdateDate strGrecipeUpdateDate
	 */
	public void setStrGrecipeUpdateDate(final String strGrecipeUpdateDate) {
		this.strGrecipeUpdateDate = strGrecipeUpdateDate;
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
