/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.shortcutset;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.entity.menu.Menu;
import com.asahikaseieng.dao.entity.menu.MenuDao;
import com.asahikaseieng.dao.nonentity.mypage.shortcutset.ShortCutSet;
import com.asahikaseieng.dao.nonentity.mypage.shortcutset.ShortCutSetDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロジック 実装クラス.
 * @author tosco
 */
public class ShortCutSetLogicImpl implements ShortCutSetLogic {

	private ShortCutSetDao shortCutSetDao;

	private MenuDao menuDao;

	/**
	 * コンストラクタ.
	 */
	public ShortCutSetLogicImpl() {
		super();
	}

	/**
	 * 検索処理を行う.ロケーションマスタ
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return Organization
	 */
	public List<ShortCutSet> getEntity(final String tantoCd)
			throws NoDataException {

		checkParams(tantoCd);

		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException("tantoCd is empty");
		}

		List<ShortCutSet> list = shortCutSetDao.getEntity(tantoCd);

		if (list == null) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getMenus(final String[] tantoRoleIds,
			final String[] belongRoleIds) {
		List<Menu> list = new ArrayList<Menu>();

		if (0 < tantoRoleIds.length || 0 < belongRoleIds.length) {
			/* 全メニューを取得 */
			list = menuDao.getMenuList(tantoRoleIds, belongRoleIds);
		}

		return list;
	}

	/**
	 * 更新処理を行う.
	 * @param bean 更新対象ビーン
	 */
	public void insert(final ShortCutSet bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		/* ショートカットマスタ 登録処理 */
		int updateNum = shortCutSetDao.insert(bean);

		if (updateNum != 1) {
			/* 登録エラー */
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象ビーン
	 */
	public void delete(final ShortCutSet bean) {

		/* ショートカットマスタ 削除処理 */
		shortCutSetDao.delete(bean);

	}

	/**
	 * 引数チェック
	 * @param cd
	 */
	private void checkParams(final String cd) {

		if (StringUtils.isEmpty(cd)) {
			throw new IllegalArgumentException("OrganizationDetailLogic "
					+ "IllegalArgumentException : Paramater is empty.");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * shortCutSetDaoを設定します。
	 * @param shortCutSetDao shortCutSetDao
	 */
	public void setShortCutSetDao(final ShortCutSetDao shortCutSetDao) {
		this.shortCutSetDao = shortCutSetDao;
	}

	/**
	 * menuDaoを設定します。
	 * @param menuDao menuDao
	 */
	public void setMenuDao(final MenuDao menuDao) {
		this.menuDao = menuDao;
	}

}
