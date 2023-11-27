/*
 * Created on 2008/09/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * ＦＫ情報　処方開示区分ラジオ
 * @author hongyo
 */
public class RadioFKDivision {
	/**
	 * コンストラクタ
	 */
	public RadioFKDivision() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "天");
		map.put("2", "Ｗ");
		map.put("3", "合");
		map.put("4", "未調査");
		return map;
	}
}
