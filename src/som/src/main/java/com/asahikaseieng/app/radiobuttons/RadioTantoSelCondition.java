/*
 * Created on 2008/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * 担当者マスタ検索画面の検索条件用ラジオボタンクラス
 * @author tosco
 */
public class RadioTantoSelCondition {

	/**
	 * コンストラクタ
	 */
	public RadioTantoSelCondition() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "全て");
		map.put("1", "未所属のみ");
		return map;
	}
}
