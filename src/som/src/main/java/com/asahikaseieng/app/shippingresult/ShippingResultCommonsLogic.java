/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;


/**
 * 出荷実績共通 ロジッククラス interface.
 * @author tosco
 */
public interface ShippingResultCommonsLogic {

	/**
	 * 運送会社名称取得
	 * @param carryName1 運送会社名称1
	 * @param carryName2 運送会社名称2
	 * @return String 運送会社名称
	 */
	String getCarryName(final String carryName1, final String carryName2);

}
