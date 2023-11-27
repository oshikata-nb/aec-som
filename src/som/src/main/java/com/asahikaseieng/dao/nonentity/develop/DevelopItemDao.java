/*
 * Created on 2007/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

/**
 * DevelopItemDaoクラス
 * @author FPC
 */
public interface DevelopItemDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.develop.DevelopItem.class;

	/** ARGSアノテーション getDevelopItem */
	String getDevelopItemVersion_ARGS = "itemCd, version";

	/**
	 * DevelopItemVersionメソッド
	 *
     * @param itemCd itemCd
     * @param version version
	 * @return DevelopItem
	 */
	DevelopItem getDevelopItemVersion(final String itemCd, final java.math.BigDecimal version);

	/**
	 * 更新処理
	 * @param bean DevelopItem
	 * @throws Exception Exception
	 */
	void update(final DevelopItem bean) throws Exception;

}
