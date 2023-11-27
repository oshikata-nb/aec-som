/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.shortcutset;

import java.util.List;

import com.asahikaseieng.dao.entity.menu.Menu;
import com.asahikaseieng.dao.nonentity.mypage.shortcutset.ShortCutSet;
import com.asahikaseieng.exception.NoDataException;

/**
 * ショートカット設定ロジック
 * @author tosco
 */
public interface ShortCutSetLogic {

	/**
	 * 検索処理を行う.ロケーションマスタ
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return ShortCutSet
	 */
	List<ShortCutSet> getEntity(final String tantoCd) throws NoDataException;

	/**
	 * 全メニューを取得する
	 * @param tantoRoleIds 担当者に基づくロール
	 * @param belongRoleIds 所属に基づくロール
	 * @return List<Menu>
	 */
	List<Menu> getMenus(final String[] tantoRoleIds,
			final String[] belongRoleIds);

	/**
	 * 登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	void insert(final ShortCutSet bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象ビーン
	 */
	void delete(final ShortCutSet bean);
}
