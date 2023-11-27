/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.util.List;


/**
 * 運送会社連携ファイルDaoインターフェース.
 *
 * @author kiguchi
 */
public interface CarryCheckDigitDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshipping.CarryCheckDigit.class;

	/** ARGSアノテーション getListForReport */
	String getCarryFile_ARGS = "condition";

	/**
	 * getNW7メソッド
	 * 
	 * @param inputorCd inputorCd
	 * @return CheckDigit
	 */
	List<CarryCheckDigit> get7DR(
			final CarryCheckDigitCondition condition);

}
