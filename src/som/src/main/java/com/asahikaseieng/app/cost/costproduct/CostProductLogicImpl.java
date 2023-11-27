/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costproduct;

import java.sql.Timestamp;

import com.asahikaseieng.dao.nonentity.procedurecall.ProCostProductCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;

/**
 * 
 * 原価積上処理LogicImpl
 * @author tosco
 */
public class CostProductLogicImpl implements CostProductLogic {

	/** 原価積上処理PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.原価積上処理
	 */
	public CostProductLogicImpl() {
	}

	/**
	 * PROCEDURE DTOセット
	 * @param frm 原価積上処理Form
	 * @return ProCostProductCallDto 原価積上処理用Dto
	 */
	public ProCostProductCallDto setProcedureDto(final CostProductForm frm) {
		ProCostProductCallDto dto = new ProCostProductCallDto();

		// 処方有効日
		if (frm.getRecipeDate() != null) {
			dto.setPDteRecipeDate(new Timestamp(frm.getRecipeDate().getTime()));
		} else {
			dto.setPDteRecipeDate(null);
		}

		return dto;
	}

	/**
	 * 原価積上処理
	 * @param proDto 原価積上処理用Dto
	 * @return BigDecimal 処理結果メッセージ
	 */
	public String callProcedure(final ProCostProductCallDto proDto) {
		// 請求更新処理
		procedureCallDao.proCostProductDecide(proDto);

		return proDto.getPStrRet();
	}


	/* -------------------- setter -------------------- */

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

}
