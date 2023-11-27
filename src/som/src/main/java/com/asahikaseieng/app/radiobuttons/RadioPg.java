/*
 * Created on 2007/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * PGラジオ
 * @author t1344224
 */
public class RadioPg {

	/**
	 * コンストラクタ
	 */
	public RadioPg() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "調査中");
		map.put("1", "無し");
		map.put("2", "有り");
		return map;
	}
}
