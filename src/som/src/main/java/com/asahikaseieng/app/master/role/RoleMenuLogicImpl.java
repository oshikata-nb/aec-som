/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.rolemenulist.RoleMenuList;
import com.asahikaseieng.dao.nonentity.master.rolemenulist.RoleMenuListDao;

/**
 * メニューロジック 実装クラス.
 * @author t0011036
 */
public class RoleMenuLogicImpl implements RoleMenuLogic {

	private Log log = LogFactory.getLog(this.getClass());

	private RoleMenuListDao roleMenuListDao;

	/**
	 * コンストラクタ.
	 */
	public RoleMenuLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public SortedSet<RoleMenuList> getMenus(final String tantoCd) {
		/* 全メニューを取得 */
		List<RoleMenuList> list = roleMenuListDao.getMenuList();

		SortedSet<RoleMenuList> rootSet = new TreeSet<RoleMenuList>(RoleMenuList
				.createSortComparator());

		int size = 0;

		for (RoleMenuList bean : list) {
			size = size + 1;

			/* 親がいれば親を探して追加 */
			/* 親がいなければ、そのまま追加 */
			if (null == bean.getParentMenuId()
					&& !(Constants.KBN_TAB.equals(bean.getKbn()) || Constants.KBN_CTRL
							.equals(bean.getKbn()))) {
				/* ディレクトリ配下にない画面(親なし)はココを通らないように考慮 */
				rootSet.add(bean);
			} else {
				/* 親がいないことは、ありえないはず */
				RoleMenuList parent = findParent(list, bean);

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
	 * 親のBeanを再帰的に見つける.
	 */
	private RoleMenuList findParent(final Collection<RoleMenuList> col,
			final RoleMenuList childBean) {
		for (RoleMenuList bean : col) {
			/* childBeanの親のメニューIDとbeanのIDが一致すれば発見 */

			/* beanを返す */
			if (Constants.KBN_DIRECTROY.equals(childBean.getKbn())
					|| Constants.KBN_MENU.equals(childBean.getKbn())) {
				/* ディレクトリまたはメニュー */
				if (bean.getMenuId().equals(childBean.getParentMenuId())) {
					return bean;
				}
			} else if (Constants.KBN_TAB.equals(childBean.getKbn())) {
				/* タブ */
				if (bean.getMenuId().equals(childBean.getMenuId())) {
					return bean;
				}
			} else {
				/* 操作 */
				if (bean.getTabId() != null && childBean.getTabId() != null) {
					if (bean.getMenuId().equals(childBean.getMenuId())
							&& bean.getTabId().equals(childBean.getTabId())) {
						return bean;
					}
				}
			}

			/* Beanの子供も再帰的に検索 */
			RoleMenuList menu = findParent(bean.getMenus(), childBean);

			if (null != menu) {
				return menu;
			}
		}

		/* （ここを通るという事は）この枝にはいない */
		return null;
	}

	/**
	 * roleMenuListDaoを設定します。
	 * @param roleMenuListDao roleMenuListDao
	 */
	public void setRoleMenuListDao(final RoleMenuListDao roleMenuListDao) {
		this.roleMenuListDao = roleMenuListDao;
	}
}
