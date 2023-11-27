/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.calculation;

import java.sql.Timestamp;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecCalculationCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;

/**
 * 
 * 原材料所要量計算LogicImpl
 * @author tosco
 */
public class CalculationLogicImpl implements CalculationLogic {

	/** 原材料所要量計算PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.原材料所要量計算
	 */
	public CalculationLogicImpl() {
	}

	/**
	 * PROCEDURE DTOセット
	 * @param frm 原材料所要量計算Form
	 * @return ProClUpdateCallDto 原材料所要量計算用Dto
	 */
	public ProNecCalculationCallDto setProcedureDto(final CalculationForm frm) {
		ProNecCalculationCallDto dto = new ProNecCalculationCallDto();

		// 展開開始日
		if (frm.getStartDate() != null) {
			dto.setPDteStartDate(new Timestamp(frm.getStartDate().getTime()));
		} else {
			dto.setPDteStartDate(null);
		}
		// 展開終了日
		if (frm.getEndDate() != null) {
			dto.setPDteEndDate(new Timestamp(frm.getEndDate().getTime()));
		} else {
			dto.setPDteEndDate(null);
		}

		// リードタイム
		dto.setPNumLeadTime(frm.getLeadTimeDivision());
		// 安全在庫確認
		dto.setPNumSafetyInventry(frm.getSafetyDivision());

		return dto;
	}

	/**
	 * 原材料所要量計算
	 * @param proDto 原材料所要量計算用Dto
	 * @return BigDecimal 処理結果メッセージ
	 */
	public String callProcedure(final ProNecCalculationCallDto proDto) {
		// 請求更新処理
		procedureCallDao.proNecCalculation(proDto);

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
