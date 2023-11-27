/*
 * Created on 2008/07/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.radiobuttons;

import java.util.LinkedHashMap;

import com.asahikaseieng.utils.ConstantFunction;

/**
 * 承認状態のラジオボタンクラス
 * @author tosco
 */
public class RadioApprovalDivision {

	/**
	 * コンストラクタ
	 */
	public RadioApprovalDivision() {
	}

	/**
	 * mapを取得します。
	 * @return map
	 */
	public LinkedHashMap<String, String> getMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put(ConstantFunction.APPROVAL_STATUS_ALL, ConstantFunction.APPROVAL_STATUS_ALL_LABEL);
		map.put(ConstantFunction.APPROVAL_STATUS_INPUT, ConstantFunction.APPROVAL_STATUS_INPUT_LABEL);
		map.put(ConstantFunction.APPROVAL_STATUS_REQUEST, ConstantFunction.APPROVAL_STATUS_REQUEST_LABEL);
		map.put(ConstantFunction.APPROVAL_STATUS_APPROVAL, ConstantFunction.APPROVAL_STATUS_APPROVAL_LABEL);
		return map;
	}
}
