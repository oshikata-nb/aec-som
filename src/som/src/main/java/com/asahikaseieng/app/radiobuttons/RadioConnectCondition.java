/*
 * Created on 2008/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * 奉行インターフェイスファイル出力用ラジオボタンクラス
 * @author tosco
 */
public class RadioConnectCondition {

	/**
	 * コンストラクタ
	 */
	public RadioConnectCondition() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<BigDecimal, String> getMap() {

		LinkedHashMap<BigDecimal, String> map = new LinkedHashMap<BigDecimal, String>();
		map.put(new BigDecimal("0"), "全データ");
		map.put(new BigDecimal("1"), "未連結データ");
		return map;
	}
}
