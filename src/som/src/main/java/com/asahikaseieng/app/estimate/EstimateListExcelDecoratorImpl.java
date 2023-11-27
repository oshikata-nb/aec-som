/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.util.List;

import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListConditionForReport;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.見積/単価マスタ一覧
 * @author t0011036
 */
public class EstimateListExcelDecoratorImpl implements
		EstimateListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private EstimateListLogic estimateListLogic;

	/**
	 * コンストラクタ
	 */
	public EstimateListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final EstimateListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("estimatelist");

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
	private void setDetail(final EstimateListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<EstimateListForReport> list = estimateListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("estimatelist");

		/* リスト部 */
		for (EstimateListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getEstimateNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getEstimateInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getBalanceCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getStyleOfPacking());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOtherCompanyCd1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getConsecutiveNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStandardUnitPrice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStandardDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSpecialDiscount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStandardAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMatss());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getEstimateValidDateFrom());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getEstimateValidDateTo());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEstimateStatus());

			rowNum++;
		}
	}

	/**
	 * estimateListLogicを設定します。
	 * @param estimateListLogic estimateListLogic
	 */
	public void setEstimateListLogic(final EstimateListLogic estimateListLogic) {
		this.estimateListLogic = estimateListLogic;
	}
}
