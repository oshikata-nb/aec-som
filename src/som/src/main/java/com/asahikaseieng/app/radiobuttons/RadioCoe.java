/*
 * Created on 2008/09/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * CoEラジオボタン
 * @author 956
 */
public class RadioCoe {
	/**
	 * コンストラクタ
	 */
	public RadioCoe() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "無し");
		map.put("1", "有り");
		map.put("2", "調査中");
		return map;
	}
}
