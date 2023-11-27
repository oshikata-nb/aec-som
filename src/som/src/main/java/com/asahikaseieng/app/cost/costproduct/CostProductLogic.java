/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costproduct;

import com.asahikaseieng.dao.nonentity.procedurecall.ProCostProductCallDto;

/**
 * 
 * 原価積上処理Logic
 * @author tosco
 */
public interface CostProductLogic {

	/**
	 * 
	 * PROCEDURE DTOセット
	 * @param frm 原価積上処理frm
	 * @return ProNecCalculationCallDto
	 */
	ProCostProductCallDto setProcedureDto(final CostProductForm frm);

	/**
	 * 
	 * 原価積上処理
	 * @param proDto 原価積上処理Dto
	 * @return String
	 */
	String callProcedure(final ProCostProductCallDto proDto);

}
