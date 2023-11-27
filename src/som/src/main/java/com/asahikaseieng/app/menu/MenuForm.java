/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.asahikaseieng.dao.entity.menu.Menu;
import com.asahikaseieng.struts.AbstractForm;

import org.apache.commons.lang.StringUtils;

/**
 * メニュー Formクラス.
 * @author jbd
 */
public class MenuForm extends AbstractForm {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	private SortedSet<Menu> menus = new TreeSet<Menu>();

	/**
	 * コンストラクタ.
	 */
	public MenuForm() {
	}

	/**
	 * menusを取得します。
	 * @return menus
	 */
	public SortedSet<Menu> getMenus() {
		return menus;
	}

	/**
	 * menusを設定します。
	 * @param menus menus
	 */
	public void setMenus(final SortedSet<Menu> menus) {
		this.menus = menus;
	}

	/**
	 * javascriptを取得.
	 * @return javascript
	 */
	public String getJs() {

		// メニュー構造のJSONを作成
		List<String> list = new ArrayList<String>();
		for (Menu menu : getMenus()) {
			list.add(menu.getJs());
		}

		// jsにレンダリングする形で文字列作成する
		StringBuffer sb = new StringBuffer();
		sb.append("var struct = [ \n");
		sb.append(StringUtils.join(list.toArray(), ","));
		sb.append("]; \n");

		return sb.toString();

	}
}
