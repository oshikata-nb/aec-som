/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.orderdevelop;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecOrderDevepolCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;

/**
 * 
 * 発注点発注展開処理LogicImpl
 * @author tosco
 */
public class OrderDevelopLogicImpl implements OrderDevelopLogic {

	/** 発注点発注展開処理PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.発注点発注展開処理
	 */
	public OrderDevelopLogicImpl() {
	}

	/**
	 * PROCEDURE DTOセット
	 * @param frm 発注点発注展開処理Form
	 * @return ProClUpdateCallDto 発注点発注展開処理用Dto
	 */
	public ProNecOrderDevepolCallDto setProcedureDto(final OrderDevelopForm frm) {

		ProNecOrderDevepolCallDto dto = new ProNecOrderDevepolCallDto();

		return dto;
	}

	/**
	 * 発注点発注展開処理
	 * @param proDto 発注点発注展開処理用Dto
	 * @return BigDecimal 処理結果メッセージ
	 */
	public String callProcedure(final ProNecOrderDevepolCallDto proDto) {
		// 請求更新処理
		procedureCallDao.proNecOrderDevepol(proDto);

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
