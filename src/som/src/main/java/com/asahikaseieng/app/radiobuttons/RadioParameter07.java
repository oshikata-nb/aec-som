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
public class RadioParameter07 {

	/**
	 * コンストラクタ
	 */
	public RadioParameter07() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "濾紙");
		map.put("1", "アミ");
		map.put("2", "パルプ");
		map.put("3", "ケイソウ土");
		return map;
	}
}
