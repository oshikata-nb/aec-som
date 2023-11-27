/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import org.apache.commons.lang.StringUtils;

/**
 * 出荷実績 共通ロジック実装クラス
 * @author tosco
 */
public class ShippingResultCommonsLogicImpl implements ShippingResultCommonsLogic {

	/**
	 * コンストラクタ
	 */
	public ShippingResultCommonsLogicImpl() {
	}

	/**
	 * 運送会社名称取得
	 * @param carryName1 運送会社名称1
	 * @param carryName2 運送会社名称2
	 * @return String 運送会社名称
	 */
	public String getCarryName(final String carryName1, final String carryName2) {
		//運送会社名称は運送会社名称１_運送会社名称２とする
		StringBuffer nameBuf = new StringBuffer("");
		nameBuf.append(carryName1);
		if (StringUtils.isNotEmpty(carryName2)) {
			nameBuf.append("_").append(carryName2);
		}
		return nameBuf.toString();
	}

	/* -------------------- setter -------------------- */
}
