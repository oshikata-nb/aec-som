/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolemenulist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.Constants;

/**
 * RoleMenuListクラス.
 * @author t0011036
 */
public class RoleMenuList extends RoleMenuListBase {

	private static final long serialVersionUID = 1L;

	private SortedSet<RoleMenuList> roleMenuLists = new TreeSet<RoleMenuList>(
			createSortComparator());

	private RoleMenuList parent;

	/**
	 * Comparatorを取得
	 * 
	 * @return Comparator<RoleMenuList>
	 */
	public static Comparator<RoleMenuList> createSortComparator() {
		return new MyComparator();
	}

	private static class MyComparator implements Comparator<RoleMenuList>,
			Serializable {

		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		/**
		 * コンストラクタ.
		 */
		public MyComparator() {
			super();
		}

		public int compare(final RoleMenuList o1, final RoleMenuList o2) {
			int i = o1.getSortNo().compareTo(o2.getSortNo());

			if (i != 0) {
				return i;
			}

			i = o1.getMenuId().compareTo(o2.getMenuId());

			if (i != 0) {
				return i;
			}

			if (o1.getTabId() == null) {
				return -1;
			}

			if (o2.getTabId() == null) {
				return 1;
			}

			i = o1.getTabId().compareTo(o2.getTabId());

			if (i != 0) {
				return i;
			}

			if (o1.getControlId() == null) {
				return -1;
			}

			if (o2.getControlId() == null) {
				return 1;
			}

			return o1.getControlId().compareTo(o2.getControlId());
		}
	};

	/**
	 * コンストラクタ.
	 */
	public RoleMenuList() {
		super();
	}

	/**
	 * 表示用のIDを取得
	 * 
	 * @return "menu" + ID
	 */
	public String getDispId() {
		String menuId = Constants.MENU_ID_PREFIX2
				+ convertNullToBlank(getMenuId());
		String tabId = Constants.TAB_ID_PREFIX + convertNullToBlank(getTabId());
		String ctrlId = Constants.CTRL_ID_PREFIX
				+ convertNullToBlank(getControlId());
		return getKbn() + menuId + tabId + ctrlId;
	}

	/**
	 * menusを取得します。
	 * 
	 * @return menus
	 */
	public SortedSet<RoleMenuList> getMenus() {
		return roleMenuLists;
	}

	/**
	 * parentを取得します。
	 * 
	 * @return parent
	 */
	public RoleMenuList getParent() {
		return parent;
	}

	/**
	 * parentを設定します。
	 * 
	 * @param parent parent
	 */
	public void setParent(final RoleMenuList parent) {
		this.parent = parent;
	}

	/**
	 * 子供の要素を追加する
	 * @param child Menu
	 */
	public void addChild(final RoleMenuList child) {
		if (null == child) {
			throw new IllegalArgumentException("Child is null.");
		}

		getMenus().add(child);
		child.setParent(this);
	}

	/** --↓↓-- javascriptを生成 --↓↓-- */

	/**
	 * javascript を生成
	 * @param map メニューID＋タブID＋操作IDのマップ
	 * @return javascript
	 */
	public String getJs(final HashMap<String, String> map) {
		StringBuffer sb = new StringBuffer();

		sb.append("{ \n");
		sb.append("'id':'" + this.getDispId() + "', \n");

		if (Constants.KBN_DIRECTROY.equals(getKbn())
				|| Constants.KBN_MENU.equals(getKbn())) {
			/* メニュー */
			sb.append("'txt':'" + this.escape(this.getMenuName()) + "', \n");
		} else if (Constants.KBN_TAB.equals(getKbn())) {
			/* タブ */
			sb.append("'txt':'" + this.escape(this.getTabName()) + "', \n");
		} else {
			/* 操作 */
			sb.append("'txt':'" + this.escape(this.getControlName()) + "', \n");
		}

		sb.append("'editable' : false, \n");
		sb.append("'draggable' : false \n");

		/* ファイルならアクションをする */

		/* ディレクトリなら子の要素を再帰的に見る */
		if (Constants.IS_FILE.equals(getFileKbn())) {
			if (Constants.KBN_DIRECTROY.equals(getKbn())
					|| Constants.KBN_MENU.equals(getKbn())) {
				/* ディレクトリまたはメニュー */
				sb.append(",'img' : '" + getImgName() + "' \n");
			} else if (Constants.KBN_TAB.equals(getKbn())) {
				/* タブ */
				sb.append(",'img' : 'tab.png' \n");
			} else {
				/* 操作 */
				if (map.get(getDispId().substring(1)) != null) {
					sb.append(",'check' : 1 \n");
				}

				sb.append(",'img' : 'mouse.png' \n");
			}

			/* ファイルの配下に更にファイルがある場合 */
			if (this.getMenus().size() > 0) {
				sb.append(", 'items':[ \n");
				List<String> list = new ArrayList<String>();

				for (RoleMenuList menuBean : this.getMenus()) {
					list.add(menuBean.getJs(map));
				}

				sb.append(StringUtils.join(list.toArray(), ","));
				sb.append("] \n");
			}
		} else if (!this.roleMenuLists.isEmpty()) {
			sb.append(",'imgopen' : '" + getOpenImgName() + "' \n");
			sb.append(",'imgclose' : '" + getCloseImgName() + "' \n");
			sb.append(", 'items':[ \n");
			List<String> list = new ArrayList<String>();

			for (RoleMenuList menuBean : this.getMenus()) {
				list.add(menuBean.getJs(map));
			}

			sb.append(StringUtils.join(list.toArray(), ","));
			sb.append("] \n");
		} else {
			sb.append(",'img' : 'folder.gif' \n");
		}

		sb.append("} \n");

		return sb.toString();
	}

	/**
	 * ダブルコーテーションをエスケープ
	 * @param st 元の文字列
	 * @return エスケープした文字列
	 */
	private String escape(final String st) {
		/* nullか空文字ならそのまま返す */
		if (StringUtils.isEmpty(st)) {
			return st;
		}

		return StringUtils.replace(st, "\"", "\\\"");
	}

	/**
	 * NULL文字列をブランクに置換
	 * @param id 元の文字列
	 * @return ブランクに置換した文字列
	 */
	private String convertNullToBlank(final BigDecimal id) {
		String blank = "";

		/* nullならブランクを返す */
		if (id == null) {
			return blank;
		}

		return id.toString();
	}
}
