/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.工程マスタ一覧
 * @author t0011036
 */
public class OperationListExcelDecoratorImpl implements
		OperationListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private OperationListLogic operationListLogic;

	/**
	 * コンストラクタ
	 */
	public OperationListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final OperationListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("operationlist");

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
	private void setDetail(final OperationListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<OperationListForReport> list = operationListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("operationlist");

		/* リスト部 */
		for (OperationListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getOperationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOperationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRecipeUse());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getRecipeUseName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOperationGroupCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOperationGroupName());
			builder.setExcelDataString(rowNum, colNum++, bean.getMemo());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());

			rowNum++;
		}
	}

	/**
	 * operationListLogicを設定します。
	 * @param operationListLogic operationListLogic
	 */
	public void setOperationListLogic(
			final OperationListLogic operationListLogic) {
		this.operationListLogic = operationListLogic;
	}
}
