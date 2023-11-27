/*
 * Created on 2007/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * ##ここにクラスの説明を書いてください##
 * @author t1344224
 */
public class RadioButtonItems {

	/**
	 * コンストラクタ
	 */
	public RadioButtonItems() {
	}



	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("key1", "val1");
		map.put("key2", "val2");
		map.put("key3", "val3");
		return map;
	}

}
