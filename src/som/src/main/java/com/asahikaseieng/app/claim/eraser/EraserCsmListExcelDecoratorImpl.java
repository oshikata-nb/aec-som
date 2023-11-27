/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.入金消込一覧
 * @author t0011036
 */
public class EraserCsmListExcelDecoratorImpl implements
		EraserCsmListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private EraserCsmListLogic eraserCsmListLogic;

	/**
	 * コンストラクタ
	 */
	public EraserCsmListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ClaimEraserCsmListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("erasercsmlist");

		/* 明細をセット */
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 明細をセット
	 * @param condition 検索条件
	 */
	private void setDetail(final ClaimEraserCsmListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ClaimEraserCsmListForReport> list = eraserCsmListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("erasercsmlist");

		/* リスト部 */
		for (ClaimEraserCsmListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getInvoiceCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderShortedName());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getDataType());
			builder.setExcelDataString(rowNum, colNum++, bean.getSlipNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getProcessingDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserObjectAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserBalanceAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditEraserAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserCompleteDivision());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getEraserCompleteDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getInvoiceUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getClaimNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getApprovalStatus());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getApprovalStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getApprovedby());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getApprovorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getApprovaldate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getEraserDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getEraserUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getEraserorCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getEraserorName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());

			rowNum++;
		}
	}

	/**
	 * eraserCsmListLogicを設定します。
	 * @param eraserCsmListLogic eraserCsmListLogic
	 */
	public void setEraserCsmListLogic(
			final EraserCsmListLogic eraserCsmListLogic) {
		this.eraserCsmListLogic = eraserCsmListLogic;
	}
}
