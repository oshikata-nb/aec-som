/*
 * Created on Thu Jan 22 18:29:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemremark;

/**
 * ItemRemarkDaoインターフェース.
 * @author t0011036
 */
public interface ItemRemarkDao {

	/** BEANアノテーション. */
	Class BEAN = ItemRemark.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ItemRemark
	 * @return Insert件数
	 */
	int insert(ItemRemark bean);

	/**
	 * Update.
	 * @param bean ItemRemark
	 * @return Update件数
	 */
	int update(ItemRemark bean);

	/**
	 * Delete.
	 * @param bean ItemRemark
	 * @return Delete件数
	 */
	int delete(ItemRemark bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,VERSION";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param version version
	 * @return ItemRemark
	 */
	ItemRemark getEntity(final String itemCd, final java.math.BigDecimal version);
}
