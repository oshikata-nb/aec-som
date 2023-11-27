/*
 * Created on 2007/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * 売掛残高一覧 対象区分
 * @author t1344224
 */
public class RadioNormalTemp {

	/**
	 * コンストラクタ
	 */
	public RadioNormalTemp() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "本締め");
		map.put("1", "仮締め");
		return map;
	}
}
