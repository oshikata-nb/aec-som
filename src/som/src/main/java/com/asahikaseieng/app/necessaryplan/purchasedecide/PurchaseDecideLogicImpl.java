/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.purchasedecide;

import java.sql.Timestamp;

import com.asahikaseieng.dao.nonentity.procedurecall.ProNecPurchaseDecideCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;

/**
 * 
 * 購買計画確定LogicImpl
 * @author tosco
 */
public class PurchaseDecideLogicImpl implements PurchaseDecideLogic {

	/** 購買計画確定PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.購買計画確定
	 */
	public PurchaseDecideLogicImpl() {
	}

	/**
	 * PROCEDURE DTOセット
	 * @param frm 購買計画確定Form
	 * @return ProClUpdateCallDto 購買計画確定用Dto
	 */
	public ProNecPurchaseDecideCallDto setProcedureDto(final PurchaseDecideForm frm) {
		ProNecPurchaseDecideCallDto dto = new ProNecPurchaseDecideCallDto();

		// 発注開始日
		if (frm.getOrderStartDate() != null) {
			dto.setPDteOsDate(new Timestamp(frm.getOrderStartDate().getTime()));
		} else {
			dto.setPDteOsDate(null);
		}
		// 発注終了日
		if (frm.getOrderEndDate() != null) {
			dto.setPDteOeDate(new Timestamp(frm.getOrderEndDate().getTime()));
		} else {
			dto.setPDteOeDate(null);
		}
		// 納期開始日
		if (frm.getDeadlineStartDate() != null) {
			dto.setPDteDsDate(new Timestamp(frm.getDeadlineStartDate().getTime()));
		} else {
			dto.setPDteDsDate(null);
		}
		// 納期終了日
		if (frm.getDeadlineEndDate() != null) {
			dto.setPDteDeDate(new Timestamp(frm.getDeadlineEndDate().getTime()));
		} else {
			dto.setPDteDeDate(null);
		}

		// 取引先コード
		dto.setPStrVender(frm.getVenderCd());
		// 品目コード
		dto.setPStrItem(frm.getItemCd());

		return dto;
	}

	/**
	 * 購買計画確定
	 * @param proDto 購買計画確定用Dto
	 * @return BigDecimal 処理結果メッセージ
	 */
	public String callProcedure(final ProNecPurchaseDecideCallDto proDto) {
		// 請求更新処理
		procedureCallDao.proNecPurchaseDecide(proDto);

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
