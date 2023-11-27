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
public class RadioInspectionValueType {

	/**
	 * コンストラクタ
	 */
	public RadioInspectionValueType() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "数値");
		map.put("2", "文字列");
		return map;
	}
}
