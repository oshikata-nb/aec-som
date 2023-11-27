/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

/**
 * 債務残高一覧画面の対象区分用ラジオボタンクラス
 * @author tosco
 */
public class RadioClosingPattern {

	/**
	 * コンストラクタ
	 */
	public RadioClosingPattern() {
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
