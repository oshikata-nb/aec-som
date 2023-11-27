/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.menu.Menu;
import com.asahikaseieng.dao.entity.menu.MenuDao;

/**
 * メニューロジック 実装クラス.
 * @author jbd
 */
public class MenuLogicImpl implements MenuLogic {

	private Log log = LogFactory.getLog(this.getClass());

	private MenuDao menuDao;

	/**
	 * コンストラクタ.
	 */
	public MenuLogicImpl() {
	}

	/**
	 * menuDaoを設定します。
	 * @param menuDao menuDao
	 */
	public void setMenuDao(final MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public SortedSet<Menu> getMenus(final String[] tantoRoleIds,
			final String[] belongRoleIds) {
		List<Menu> list = new ArrayList<Menu>();

		if (0 < tantoRoleIds.length || 0 < belongRoleIds.length) {
			/* 全メニューを取得 */
			list = menuDao.getMenuList(tantoRoleIds, belongRoleIds);
		}

		SortedSet<Menu> rootSet = new TreeSet<Menu>(Menu.createSortComparator());

		for (Menu bean : list) {
			/* 不正なデータを除外する */
			if (isExclude(bean, list)) {
				log.error("MenuData(" + bean.getMenuId() + ") is illegal.");
				continue;
			}

			/* 親がいれば親を探して追加 */
			/* 親がいなければ、そのまま追加 */
			if (null == bean.getParentMenuId()) {
				rootSet.add(bean);
			} else {
				/* 親がいないことは、ありえないはず */
				Menu parent = findParent(list, bean);

				if (null == parent) {
					log.error("Parent(" + bean.getParentMenuId()
							+ ") is not found.");
					continue;
				}

				parent.addChild(bean);
			}
		}

		return rootSet;
	}

	/**
	 * 不正なデータか判定する.
	 * @param bean Menu
	 * @param menus menus
	 * @return true:不正 false:正
	 */
	private boolean isExclude(final Menu bean, final List<Menu> menus) {

		if (Constants.IS_FILE.equals(bean.getFileKbn())) {
			// ファイルでアクションがなければエラー
			if (StringUtils.isEmpty(bean.getAction())) {
				return true;
			}
			// ファイルで子供が存在すればエラー
			for (Menu menu : menus) {
				if (bean.getMenuId().equals(menu.getParentMenuId())) {
					return true;
				}
			}
		} else {
			// ディレクトリでアクションがあればエラー
			if (StringUtils.isNotEmpty(bean.getAction())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 親のBeanを再帰的に見つける.
	 */
	private Menu findParent(final Collection<Menu> col, final Menu childBean) {

		for (Menu bean : col) {

			// childBeanの親のメニューIDとbeanのIDが一致すれば発見
			// beanを返す
			if (bean.getMenuId().equals(childBean.getParentMenuId())) {
				return bean;
			}
			// Beanの子供も再帰的に検索
			Menu menu = findParent(bean.getMenus(), childBean);
			if (null != menu) {
				return menu;
			}
		}
		// （ここを通るという事は）この枝にはいない
		return null;
	}
}
