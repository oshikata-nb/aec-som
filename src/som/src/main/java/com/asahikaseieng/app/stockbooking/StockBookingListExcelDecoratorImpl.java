/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.stockbooking;

import java.util.List;

import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingDetailListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingFormulaListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingHeaderListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingInspectionListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingListConditionForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingProcedureListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.検査待ち在庫計上一覧
 * @author tosco
 */
public class StockBookingListExcelDecoratorImpl implements
		StockBookingListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private StockBookingListLogic stockBookingListLogic;

	/**
	 * コンストラクタ
	 */
	public StockBookingListExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final StockBookingListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("stockbookinglist");

		/* ヘッダをセット */
		setHeaderReport(condition);

		/* 詳細をセット */
		setDetailReport(condition);

		/* 工程をセット */
		setProcedureReport(condition);

		/* 配合をセット */
		setFormulaReport(condition);

		/* 検査をセット */
		setInspectionReport(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setHeaderReport(
			final StockBookingListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("direction_header");

		List<StockBookingHeaderListForReport> list = stockBookingListLogic
				.getHeaderReportList(condition);

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (StockBookingHeaderListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDirectionDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getDirectionNo());
			builder
					.setExcelDataTimestamp(sRow, sCol++, bean
							.getDirectionDate());
			builder.setExcelDataString(sRow, sCol++, bean.getAspOrderNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDirectionStatus());
			builder.setExcelDataString(sRow, sCol++, bean
					.getDirectionStatusName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataString(sRow, sCol++, bean.getRecipeCd());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getRecipeVersion());
			builder.setExcelDataString(sRow, sCol++, bean.getProductionLine());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductionLineName());
			builder.setExcelDataString(sRow, sCol++, bean.getCompoundTankNo());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCompoundTankName());
			builder.setExcelDataString(sRow, sCol++, bean.getHoldTankNo());
			builder.setExcelDataString(sRow, sCol++, bean.getHoldTankName());

			builder.setExcelDataString(sRow, sCol++, bean
					.getDissolutionTankNo());
			builder.setExcelDataString(sRow, sCol++, bean
					.getDissolutionTankName());

			builder.setExcelDataString(sRow, sCol++, bean.getFilltankNo());
			builder.setExcelDataString(sRow, sCol++, bean.getFilltankName());

			builder.setExcelDataString(sRow, sCol++, bean.getPackageLine());
			builder.setExcelDataString(sRow, sCol++, bean.getPackageLineName());

			builder.setExcelDataString(sRow, sCol++, bean.getCurrentStepNo());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getName01());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlanedQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getResultQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDivideFlag());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getNextPlanedQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPdwResult());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPeocessLoss());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPlanedSdate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPlanedEdate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getResultSdate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getResultEdate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getSteritSdate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getSteritEdate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getMekkinTankTempMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getMekkinTankTempMax());
			builder.setExcelDataString(sRow, sCol++, bean.getHaisuiCheck());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStampFlag());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getStampDate());
			builder.setExcelDataString(sRow, sCol++, bean.getStampTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getStampTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getProductLabelFlag());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getProductLabelDate());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductLabelTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductLabelTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getProductRecordFlag());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getProductRecordDate());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductRecordTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductRecordTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockdissLabelFlag());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getStockdissLabelDate());
			builder.setExcelDataString(sRow, sCol++, bean
					.getStockdissLabelTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getStockdissLabelTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCertificationFlag());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getCertificationDate());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDeleteFlag());
			builder.setExcelDataString(sRow, sCol++, bean.getSeizotantocode());
			builder.setExcelDataString(sRow, sCol++, bean.getSeizotantoName());
			builder.setExcelDataString(sRow, sCol++, bean.getSenjotantocode());
			builder.setExcelDataString(sRow, sCol++, bean.getSenjotantoName());
			builder.setExcelDataString(sRow, sCol++, bean.getMekkintantocode());
			builder.setExcelDataString(sRow, sCol++, bean.getMekkintantoName());
			builder.setExcelDataString(sRow, sCol++, bean.getChogotankcondi());
			builder.setExcelDataString(sRow, sCol++, bean.getYobiyokaicondi());
			builder.setExcelDataString(sRow, sCol++, bean.getHoldtankcondi());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getTotalJobtime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEnployJobtime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCooperJobtime());
			builder.setExcelDataString(sRow, sCol++, bean.getAppTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getAppTantoName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getAppDate());
			builder.setExcelDataString(sRow, sCol++, bean.getLastAppTantoCd());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getLastAppTantoName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastAppDate());

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSumSuuryou());

			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setDetailReport(
			final StockBookingListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("direction_detail");

		List<StockBookingDetailListForReport> list = stockBookingListLogic
				.getDetailReportList(condition);

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (StockBookingDetailListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getPkgDirectionNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRowNo());
			builder.setExcelDataString(sRow, sCol++, bean.getDirectionNo());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setProcedureReport(
			final StockBookingListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("direction_procedure");

		List<StockBookingProcedureListForReport> list = stockBookingListLogic
				.getProcedureReportList(condition);

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (StockBookingProcedureListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDirectionDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getDirectionNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStepNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSeq());
			builder.setExcelDataString(sRow, sCol++, bean.getOperationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getOperationName());
			builder.setExcelDataString(sRow, sCol++, bean.getCondition());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLeadtime());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getStartDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getEndDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getResultSdate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getResultEdate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getConditionTemp());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getConditionTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStirSpeed1());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStirSpeed2());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getWaterWeight());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMainStream());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPh());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getResultConditionTemp());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getResultStirSpeed());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getResultPh());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFillingQty());
			builder.setExcelDataString(sRow, sCol++, bean.getFillingUnit());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNet());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getWeightMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getWeightMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFilter());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAutoCheckerMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAutoCheckerMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getGrossCheckerMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getGrossCheckerMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOpeningTorqueMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOpeningTorqueMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getHotAirPresetTemp());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getHotAirPressure());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMesh());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAutoCheckerAve());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getGrossCheckerAve());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClosingTorqueMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClosingTorqueMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTorquePressure());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAirPressure());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getVcloseTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getFirstHeatSeal());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSecondHeatSeal());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setFormulaReport(
			final StockBookingListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("direction_formula");

		List<StockBookingFormulaListForReport> list = stockBookingListLogic
				.getFormulaReportList(condition);

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (StockBookingFormulaListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDirectionDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getDirectionNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStepNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLineNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSeq());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLineType());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getName01());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTonyu());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataread());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTonyusokudo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStockpdQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getResultQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSampleQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLossQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDefectQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAdjustQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCost());
			builder.setExcelDataString(sRow, sCol++, bean.getStepCondition());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getFirstLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getNextLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getSecondLocationName());
			builder.setExcelDataString(sRow, sCol++, bean
					.getNextAfterLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getThirdLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataString(sRow, sCol++, bean
					.getManufacturerLotNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFillQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getFillResultQty());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setInspectionReport(
			final StockBookingListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("direction_inspection");

		List<StockBookingInspectionListForReport> list = stockBookingListLogic
				.getInspectionReportList(condition);

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (StockBookingInspectionListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDirectionDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getDirectionNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStepNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLineNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSeq());
			builder.setExcelDataString(sRow, sCol++, bean.getInspectionCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInspectionName());
			builder.setExcelDataString(sRow, sCol++, bean.getDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getValueType());
			builder.setExcelDataString(sRow, sCol++, bean.getValue1());
			builder.setExcelDataString(sRow, sCol++, bean.getResultValue1());
			builder.setExcelDataString(sRow, sCol++, bean.getValue2());
			builder.setExcelDataString(sRow, sCol++, bean.getResultValue2());
			builder.setExcelDataString(sRow, sCol++, bean.getCondition());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}

	/**
	 * stockBookingListLogicを設定します。
	 * @param stockBookingListLogic stockBookingListLogic
	 */
	public void setStockBookingListLogic(
			final StockBookingListLogic stockBookingListLogic) {
		this.stockBookingListLogic = stockBookingListLogic;
	}
}
