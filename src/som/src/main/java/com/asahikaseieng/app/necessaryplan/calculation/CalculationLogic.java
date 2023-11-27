/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.calculation;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecCalculationCallDto;

/**
 * 
 * 原材料所要量計算Logic
 * @author tosco
 */
public interface CalculationLogic {

	/**
	 * 
	 * PROCEDURE DTOセット
	 * @param frm 原材料所要量計算frm
	 * @return ProNecCalculationCallDto
	 */
	ProNecCalculationCallDto setProcedureDto(final CalculationForm frm);

	/**
	 * 
	 * 原材料所要量計算
	 * @param proDto 原材料所要量計算Dto
	 * @return String
	 */
	String callProcedure(final ProNecCalculationCallDto proDto);

}
