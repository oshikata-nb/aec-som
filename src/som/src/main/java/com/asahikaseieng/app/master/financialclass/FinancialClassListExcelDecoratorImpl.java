/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.財務分類マスタ一覧
 * @author t0011036
 */
public class FinancialClassListExcelDecoratorImpl implements
		FinancialClassListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private FinancialClassListLogic financialClassListLogic;

	/**
	 * コンストラクタ
	 */
	public FinancialClassListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final FinancialClassListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("financialclasslist");

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
	private void setDetail(final FinancialClassListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<FinancialClassListForReport> list = financialClassListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("financiallist");

		/* リスト部 */
		for (FinancialClassListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getFinancialClassCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getFinancialClassName());
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

	/* -------------------- setter -------------------- */

	/**
	 * financialClassListLogicを設定します。
	 * @param financialClassListLogic financialClassListLogic
	 */
	public void setFinancialClassListLogic(
			final FinancialClassListLogic financialClassListLogic) {
		this.financialClassListLogic = financialClassListLogic;
	}
}
