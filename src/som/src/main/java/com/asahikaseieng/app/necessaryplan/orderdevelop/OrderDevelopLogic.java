/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.orderdevelop;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecOrderDevepolCallDto;

/**
 * 
 * 発注点発注展開処理Logic
 * @author tosco
 */
public interface OrderDevelopLogic {

	/**
	 * 
	 * PROCEDURE DTOセット
	 * @param frm frm
	 * @return ProNecCalculationCallDto
	 */
	ProNecOrderDevepolCallDto setProcedureDto(final OrderDevelopForm frm);

	/**
	 * 
	 * 発注点発注展開処理
	 * @param proDto proDto
	 * @return String
	 */
	String callProcedure(final ProNecOrderDevepolCallDto proDto);

}
