/*
 * Created on Tue Mar 27 15:06:30 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menu;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.asahikaseieng.Constants;

import org.apache.commons.lang.StringUtils;

/**
 * Menuクラス.
 * @author jbd
 */
public class Menu extends MenuBase {

	private static final long serialVersionUID = 1L;

	private SortedSet<Menu> menus = new TreeSet<Menu>(createSortComparator());

	private Menu parent;

	private String auth;

	/**
	 * Comparatorを取得
	 * @return Comparator<Menu>
	 */
	public static Comparator<Menu> createSortComparator() {
		return new MyComparator();
	}

	private static class MyComparator implements Comparator<Menu>, Serializable {

		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		/**
		 * コンストラクタ.
		 */
		public MyComparator() {
			super();
		}

		public int compare(final Menu o1, final Menu o2) {
			int i = o1.getSortNo().compareTo(o2.getSortNo());
			if (i != 0) {
				return i;
			}
			return o1.getMenuId().compareTo(o2.getMenuId());
		}
	};

	/**
	 * コンストラクタ.
	 */
	public Menu() {
		super();
	}

	/**
	 * 表示用のIDを取得
	 * @return "menu" + ID
	 */
	public String getDispId() {
		return Constants.MENU_ID_PREFIX + getMenuId();
	}

	/**
	 * menusを取得します。	 * @return menus
	 */
	public SortedSet<Menu> getMenus() {
		return menus;
	}

	/**
	 * parentを取得します。
	 * @return parent
	 */
	public Menu getParent() {
		return parent;
	}

	/**
	 * parentを設定します。
	 * @param parent parent
	 */
	public void setParent(final Menu parent) {
		this.parent = parent;
	}

	/**
	 * authを取得します。
	 * @return auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * authを設定します。
	 * @param auth auth
	 */
	public void setAuth(final String auth) {
		this.auth = auth;
	}

	/**
	 * 子供の要素を追加する
	 * @param child Menu
	 */
	public void addChild(final Menu child) {
		if (null == child) {
			throw new IllegalArgumentException("Child is null.");
		}
		getMenus().add(child);
		child.setParent(this);
	}

	/** --↓↓-- javascriptを生成 --↓↓-- */

	/**
	 * javascript を生成
	 * @return javascript
	 */
	public String getJs() {

		StringBuffer sb = new StringBuffer();

		sb.append("{ \n");
		sb.append("'id':'" + this.getDispId() + "', \n");
		sb.append("'txt':'" + this.escape(this.getMenuName()) + "', \n");
		sb.append("'editable' : false, \n");
		sb.append("'draggable' : false \n");

		// ファイルならアクションをする
		// ディレクトリなら子の要素を再帰的に見る
		if (Constants.IS_FILE.equals(this.getFileKbn())) {
			sb.append(",'img' : '" + this.getImgName() + "' \n");
			sb.append(", 'onclick' : function(e) { " + this.getAction()
					+ " } \n");

		} else if (!this.menus.isEmpty()) {
			sb.append(",'imgopen' : '" + this.getOpenImgName() + "' \n");
			sb.append(",'imgclose' : '" + this.getCloseImgName() + "' \n");
			sb.append(", 'items':[ \n");
			List<String> list = new ArrayList<String>();
			for (Menu menuBean : this.getMenus()) {
				list.add(menuBean.getJs());
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
		// nullか空文字ならそのまま返す
		if (StringUtils.isEmpty(st)) {
			return st;
		}
		return StringUtils.replace(st, "\"", "\\\"");
	}

	/**
	 * メニューSetよりメニューを探す
	 * @param id 対象ID
	 * @param menus メニューSet
	 * @return Menu
	 */
	public static Menu findMenu(final BigDecimal id, final SortedSet<Menu> menus) {
		for (Menu menu : menus) {

			if (menu.getMenuId().equals(id)) {
				return menu;
			}

			Menu m = findMenu(id, menu.getMenus());
			if (m != null) {
				return m;
			}
		}
		return null;
	}
}
