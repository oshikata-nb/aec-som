/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.purchasedecide;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecPurchaseDecideCallDto;

/**
 * 
 * 購買計画確定Logic
 * @author tosco
 */
public interface PurchaseDecideLogic {

	/**
	 * 
	 * PROCEDURE DTOセット
	 * @param frm 購買計画確定frm
	 * @return ProNecCalculationCallDto
	 */
	ProNecPurchaseDecideCallDto setProcedureDto(final PurchaseDecideForm frm);

	/**
	 * 
	 * 購買計画確定
	 * @param proDto 購買計画確定Dto
	 * @return String
	 */
	String callProcedure(final ProNecPurchaseDecideCallDto proDto);

}
