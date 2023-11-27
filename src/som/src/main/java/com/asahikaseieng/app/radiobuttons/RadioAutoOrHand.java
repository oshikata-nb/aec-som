/*
 * Created on 200/02/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * 自動 or 手動ラジオボタン
 * @author 956
 */
public class RadioAutoOrHand {
	/**
	 * コンストラクタ
	 */
	public RadioAutoOrHand() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "自動");
		map.put("1", "手動");
		return map;
	}
}
