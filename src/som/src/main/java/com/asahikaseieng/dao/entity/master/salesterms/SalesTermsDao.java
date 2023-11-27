/*
 * Created on Thu Jan 22 19:58:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.salesterms;

/**
 * SalesTermsDaoインターフェース.
 * @author kanri-user
 */
public interface SalesTermsDao {

	/** BEANアノテーション. */
	Class BEAN = SalesTerms.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean SalesTerms
	 * @return Insert件数
	 */
	int insert(SalesTerms bean);

	/**
	 * Update.
	 * @param bean SalesTerms
	 * @return Update件数
	 */
	int update(SalesTerms bean);

	/**
	 * Delete.
	 * @param bean SalesTerms
	 * @return Delete件数
	 */
	int delete(SalesTerms bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DELIVERY_CD,BALANCE_CD,ITEM_CD,SEQ";

	/**
	 * エンティティ取得.
	 * @param balanceCd balanceCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @param seq seq
	 * @return SalesTerms
	 */
	SalesTerms getEntity(String deliveryCd, String balanceCd, String itemCd,
			java.math.BigDecimal seq);
}
