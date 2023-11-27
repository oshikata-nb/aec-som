/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costaccounting;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfy1CallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfy2CallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfy3CallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfy4CallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfyaCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfybCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfycCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfydCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfyeCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfyfCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateIfygCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateMonthlyInventoryCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 原価計算データ送信ロジック 実装クラス.
 * @author t0011036
 */
public class CostAccountingLogicImpl implements CostAccountingLogic {

	private ProcedureCallDao procedureCallDao;

	/** 更新エラーコード */
	private static final BigDecimal UPDATE_ERROR = new BigDecimal("99");

	/** 更新成功メッセージ */
	private static final String UPDATE_SUCCESS_MSG = "成功";

	/** 更新失敗メッセージ */
	private static final String UPDATE_FAILURE_MSG = "失敗";

	/**
	 * コンストラクタ.
	 */
	public CostAccountingLogicImpl() {
	}

	/**
	 * 実行
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 */
	public void execute(final CostAccountingForm frm, final String tantoCd) {
		String inputDate = frm.getSrhStrInputDate().replace("/", "");

		if (frm.getExecuteList().get(CostAccountingConst.OPERATION_IDX)
				.getChecked()) {
			/* 工程マスタ実行 */
			updateOperation(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.ITEM_IDX).getChecked()
				|| frm.getSrhSendDivision().equals(BigDecimal.ONE)) {
			/* 品目マスタ実行 */
			updateItem(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.PROCEDURE_IDX)
				.getChecked()
				|| frm.getSrhSendDivision().equals(BigDecimal.ONE)) {
			/* 作業手順マスタ実行 */
			updateProcedure(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.FORMATION_IDX)
				.getChecked()
				|| frm.getSrhSendDivision().equals(BigDecimal.ONE)) {
			/* 部品構成マスタ実行 */
			updateFormation(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.ORDER_IDX)
				.getChecked()) {
			/* 製造オーダーファイル実行 */
			updateOrder(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.REPORT_IDX)
				.getChecked()) {
			/* 作業日報ファイル実行 */
			updateReport(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.SALES_ORDER_IDX)
				.getChecked()) {
			/* 注文書ファイル実行 */
			updateSalesOrder(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.STOCKING_IDX)
				.getChecked()) {
			/* 仕入ファイル実行 */
			updateStocking(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.SALES_IDX)
				.getChecked()) {
			/* 売上ファイル実行 */
			updateSales(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.RECEIPT_IDX)
				.getChecked()) {
			/* 受払ファイル実行 */
			updateReceipt(frm, inputDate, tantoCd);
		}

		if (frm.getExecuteList().get(CostAccountingConst.LEDGER_IDX)
				.getChecked()) {
			/* 材料・製品元帳ファイル実行 */
			updateLedger(frm, inputDate, tantoCd);
		}
	}

	/**
	 * 工程マスタ実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateOperation(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfy1CallDto dto = new ProUpdateIfy1CallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfy1(dto);

			frm.getExecuteList().get(CostAccountingConst.OPERATION_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.OPERATION_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.OPERATION_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.OPERATION_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.OPERATION_IDX)
					.setResult(UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 品目マスタ実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateItem(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfy2CallDto dto = new ProUpdateIfy2CallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPNumSendDivision(frm.getSrhSendDivision());
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfy2(dto);

			frm.getExecuteList().get(CostAccountingConst.ITEM_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.ITEM_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.ITEM_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.ITEM_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.ITEM_IDX).setResult(
				UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 作業手順マスタ実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateProcedure(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfy3CallDto dto = new ProUpdateIfy3CallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPNumSendDivision(frm.getSrhSendDivision());
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfy3(dto);

			frm.getExecuteList().get(CostAccountingConst.PROCEDURE_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.PROCEDURE_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.PROCEDURE_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.PROCEDURE_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.PROCEDURE_IDX)
					.setResult(UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 部品構成マスタ実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateFormation(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfy4CallDto dto = new ProUpdateIfy4CallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPNumSendDivision(frm.getSrhSendDivision());
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfy4(dto);

			frm.getExecuteList().get(CostAccountingConst.FORMATION_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.FORMATION_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.FORMATION_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.FORMATION_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.FORMATION_IDX)
					.setResult(UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 製造オーダーファイル実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateOrder(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfyaCallDto dto = new ProUpdateIfyaCallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfya(dto);

			frm.getExecuteList().get(CostAccountingConst.ORDER_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.ORDER_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.ORDER_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.ORDER_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.ORDER_IDX).setResult(
				UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 作業日報ファイル実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateReport(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfybCallDto dto = new ProUpdateIfybCallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfyb(dto);

			frm.getExecuteList().get(CostAccountingConst.REPORT_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.REPORT_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.REPORT_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.REPORT_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.REPORT_IDX).setResult(
				UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 注文書ファイル実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateSalesOrder(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfycCallDto dto = new ProUpdateIfycCallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfyc(dto);

			frm.getExecuteList().get(CostAccountingConst.SALES_ORDER_IDX)
					.setCnt(dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.SALES_ORDER_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.SALES_ORDER_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.SALES_ORDER_IDX)
					.setCnt(BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.SALES_ORDER_IDX)
					.setResult(UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 仕入ファイル実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateStocking(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfydCallDto dto = new ProUpdateIfydCallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfyd(dto);

			frm.getExecuteList().get(CostAccountingConst.STOCKING_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.STOCKING_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.STOCKING_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.STOCKING_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.STOCKING_IDX)
					.setResult(UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 売上ファイル実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateSales(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfyeCallDto dto = new ProUpdateIfyeCallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfye(dto);

			frm.getExecuteList().get(CostAccountingConst.SALES_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.SALES_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.SALES_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.SALES_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.SALES_IDX).setResult(
				UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 受払ファイル実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateReceipt(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfyfCallDto dto = new ProUpdateIfyfCallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfyf(dto);

			frm.getExecuteList().get(CostAccountingConst.RECEIPT_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.RECEIPT_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.RECEIPT_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.RECEIPT_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.RECEIPT_IDX)
					.setResult(UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 材料・製品元帳ファイル実行
	 * @param frm 画面データ
	 * @param inputDate 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void updateLedger(final CostAccountingForm frm,
			final String inputDate, final String tantoCd) {
		ProUpdateIfygCallDto dto = new ProUpdateIfygCallDto();

		dto.setPStrTargetMonth(inputDate);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateIfyg(dto);

			frm.getExecuteList().get(CostAccountingConst.LEDGER_IDX).setCnt(
				dto.getPNumCnt());

			if (dto.getPNumRet().equals(UPDATE_ERROR)) {
				frm.getExecuteList().get(CostAccountingConst.LEDGER_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getExecuteList().get(CostAccountingConst.LEDGER_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getExecuteList().get(CostAccountingConst.LEDGER_IDX).setCnt(
				BigDecimal.ZERO);
			frm.getExecuteList().get(CostAccountingConst.LEDGER_IDX).setResult(
				UPDATE_FAILURE_MSG);
		}
	}

	/**
	 * 取込
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 */
	public void importData(final CostAccountingForm frm, final String tantoCd) {
		BigDecimal targetMonth = new BigDecimal(frm.getSrhStrInputDate()
				.replace("/", ""));

		if (frm.getIndex() == CostAccountingConst.IMPORT_LEDGER_IDX) {
			/* 材料・製品元帳ファイル実行 */
			importLedger(frm, targetMonth, tantoCd);
		}
	}

	/**
	 * 材料・製品元帳ファイル取込
	 * @param frm 画面データ
	 * @param targetMonth 対象年月
	 * @param tantoCd 担当者コード
	 */
	public void importLedger(final CostAccountingForm frm,
			final BigDecimal targetMonth, final String tantoCd) {
		ProUpdateMonthlyInventoryCallDto dto = new ProUpdateMonthlyInventoryCallDto();

		dto.setPNumTargetMonth(targetMonth);
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proUpdateMonthlyInventory(dto);

			frm.getImportList().get(CostAccountingConst.IMPORT_LEDGER_IDX)
					.setCnt(dto.getPNumCnt());

			if (StringUtils.isNotEmpty(dto.getPStrErrorReturnCd())) {
				frm.getImportList().get(CostAccountingConst.IMPORT_LEDGER_IDX)
						.setResult(UPDATE_FAILURE_MSG);
			} else {
				frm.getImportList().get(CostAccountingConst.IMPORT_LEDGER_IDX)
						.setResult(UPDATE_SUCCESS_MSG);
			}
		} catch (LogicExceptionEx e) {
			frm.getImportList().get(CostAccountingConst.IMPORT_LEDGER_IDX)
					.setCnt(BigDecimal.ZERO);
			frm.getImportList().get(CostAccountingConst.IMPORT_LEDGER_IDX)
					.setResult(UPDATE_FAILURE_MSG);
		}
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
