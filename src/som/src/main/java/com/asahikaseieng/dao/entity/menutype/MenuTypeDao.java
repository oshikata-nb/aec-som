/*
 * Created on Mon Apr 02 20:09:58 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menutype;
import java.util.List;

/**
 * MenuTypeDaoインターフェース.
 * @author jbd
 */
public interface MenuTypeDao {

	/** BEANアノテーション. */
	Class BEAN = MenuType.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MenuType
	 * @return Insert件数
	 */
	int insert(MenuType bean);

	/**
	 * Update.
	 * @param bean MenuType
	 * @return Update件数
	 */
	int update(MenuType bean);

	/**
	 * Delete.
	 * @param bean MenuType
	 * @return Delete件数
	 */
	int delete(MenuType bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "MENU_TYPE_ID";

	/**
	 * エンティティ取得.
	 * @param menuTypeId menuTypeId
	 * @return MenuType
	 */
	MenuType getEntity(java.math.BigDecimal menuTypeId);

	/**
	 * リスト取得.
	 * @return MenuTypeのリスト
	 */
	List<MenuType> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

