/*
 * Created on Tue Sep 08 18:52:17 JST 2015
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.salesextended;
import java.util.List;

/**
 * SalesExtendedDaoインターフェース.
 * @author a1041072
 */
public interface SalesExtendedDao {

	/** BEANアノテーション. */
	Class BEAN = SalesExtended.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean SalesExtended
	 * @return Insert件数
	 */
	int insert(SalesExtended bean);

	/**
	 * Update.
	 * @param bean SalesExtended
	 * @return Update件数
	 */
	int update(SalesExtended bean);

	/**
	 * Delete.
	 * @param bean SalesExtended
	 * @return Delete件数
	 */
	int delete(SalesExtended bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SALES_NO";

	/**
	 * エンティティ取得.
	 * @param salesNo salesNo
	 * @return SalesExtended
	 */
	SalesExtended getEntity(String salesNo);

	/**
	 * リスト取得.
	 * @return SalesExtendedのリスト
	 */
	List<SalesExtended> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

