/*
 * Created on Mon Apr 09 13:53:25 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadget;

import java.math.BigDecimal;

/**
 * GadgetDaoインターフェース.
 * @author jbd
 */
public interface GadgetDao {

	/** BEANアノテーション. */
	Class BEAN = Gadget.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "GADGET_ID";

	/**
	 * エンティティ取得.
	 * @param gadgetId gadgetId
	 * @return Gadget
	 */
	Gadget getEntity(String gadgetId);

	//
	// 追加メソッドはこの下に記述して下さい
	//

	/** ARGSアノテーション getSqlString(). */
	String getSqlString_ARGS = "GADGET_ID, MENU_ID, TAB_ID, CONTROL_ID";

	/**
	 * エンティティ取得.
	 * @param gadgetId gadgetId
     * @param menuId menuId
     * @param tabId tabId
     * @param controlId controlId
	 * @return sqlString
	 */
	Gadget getSqlString(final String gadgetId, final BigDecimal menuId,
			final BigDecimal tabId, final BigDecimal controlId);

}
