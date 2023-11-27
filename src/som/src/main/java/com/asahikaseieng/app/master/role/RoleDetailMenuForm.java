/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.master.rolemenulist.RoleMenuList;

/**
 * メニュー階層Beanクラス.ロールマスタ詳細
 * @author t0011036
 */
public final class RoleDetailMenuForm {

	private static final long serialVersionUID = 1L;

	/* メニュー階層 */
	private SortedSet<RoleMenuList> menus = new TreeSet<RoleMenuList>();

	/* メニューID＋タブID＋操作IDのマップ */
	private HashMap<String, String> map;

	/**
	 * コンストラクタ.
	 */
	public RoleDetailMenuForm() {
	}

	/**
	 * メニュー階層を取得します。
	 * @return menus
	 */
	public SortedSet<RoleMenuList> getMenus() {
		return menus;
	}

	/**
	 * メニュー階層を設定します。
	 * @param menus メニュー階層
	 */
	public void setMenus(final SortedSet<RoleMenuList> menus) {
		this.menus = menus;
	}

	/**
	 * メニューID＋タブID＋操作IDのマップを取得します。
	 * @return HashMap<String, String> メニューID＋タブID＋操作IDのマップ
	 */
	public HashMap<String, String> getMap() {
		return map;
	}

	/**
	 * メニューID＋タブID＋操作IDのマップを設定します。
	 * @param map メニューID＋タブID＋操作IDのマップ
	 */
	public void setMap(final HashMap<String, String> map) {
		this.map = map;
	}

	/**
	 * メニュー階層を作成したjavascriptを取得.
	 * @return javascript
	 */
	public String getJs() {

		/* メニュー構造のJSONを作成 */
		List<String> list = new ArrayList<String>();

		for (RoleMenuList menu : getMenus()) {
			list.add(menu.getJs(getMap()));
		}

		/* jsにレンダリングする形で文字列作成する */
		StringBuffer sb = new StringBuffer();
		sb.append("var struct = [ \n");
		sb.append(StringUtils.join(list.toArray(), ","));
		sb.append("]; \n");

		return sb.toString();
	}
}
