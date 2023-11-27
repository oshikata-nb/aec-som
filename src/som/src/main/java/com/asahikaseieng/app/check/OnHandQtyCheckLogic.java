/*
 * Created on 2009/06/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.check;

import java.math.BigDecimal;

import com.asahikaseieng.exception.NoDataException;

/**
 * 製造・包装指図用 手持ち在庫チェックinterface
 * @author a7710658
 */
public interface OnHandQtyCheckLogic {

	/**
	 * 製造・包装指図用 手持ち在庫チェックし<br>
	 * 不足する場合はメッセージを返す。
	 * @param directionDivision .
	 * @param directionNo .
	 * @throws NoDataException .
	 * @return エラーメッセージ
	 */
	String onHandQtyCheck(final BigDecimal directionDivision,
			final String directionNo) throws NoDataException;

}
