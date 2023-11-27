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
public class RadioParameter05 {

	/**
	 * コンストラクタ
	 */
	public RadioParameter05() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "なし");
		map.put("1", "あり");
		return map;
	}
}
