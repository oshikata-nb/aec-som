/*
 * Created on 2007/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * ##ここにクラスの説明を書いてください##
 * @author t1344224
 */
public class RadioButtonComponent {

	/**
	 * コンストラクタ
	 */
	public RadioButtonComponent() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<BigDecimal, String> getComponent() {

		LinkedHashMap<BigDecimal, String> map = new LinkedHashMap<BigDecimal, String>();
		map.put(new BigDecimal("0"), "×");
		map.put(new BigDecimal("1"), "○（該当）");
		return map;
	}

}
