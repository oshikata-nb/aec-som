/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.EstimateSavedListForReport;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.EstimateSavedListForReportDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalesTermsSavedListForReport;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalesTermsSavedListForReportDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.販売条件&見積単価 コピー作成・削除処理管理テーブル一覧
 * @author t0011036
 */
public class SalestermsAndEstimateListExcelDecoratorImpl implements
		SalestermsAndEstimateListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	protected static final short aaaa = 30000;
	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private SalestermsAndEstimateListLogic salestermsAndEstimateListLogic;
	
	private EstimateSavedListForReportDao estimateSavedListForReportDao;
	
	private SalesTermsSavedListForReportDao salesTermsSavedListForReportDao;

	/**
	 * コンストラクタ
	 */
	public SalestermsAndEstimateListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final SalestermsAndEstimateListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("salestermsandestimatelist");

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
	private void setDetail(final SalestermsAndEstimateListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<SalestermsAndEstimateListForReport> list = salestermsAndEstimateListLogic
				.getListForReport(condition);

		short estimateRowNum = TEMP_START_ROW;
		short salesTermsRowNum = TEMP_START_ROW;

		/* リスト部 */
		for (SalestermsAndEstimateListForReport bean : list) {
			/* シートをセット */
			builder.setSheet("salestermsandestimatelist");
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getPkNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getProcessDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getProcessName());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCdFrom());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemNameFrom());
			builder.setExcelDataString(rowNum, colNum++, bean.getStyleOfPackingFrom());
			builder.setExcelDataString(rowNum, colNum++, bean.getOtherCompanyCd1From());
			builder.setExcelDataString(rowNum, colNum++, bean.getActivateFrom());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCdTo());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemNameTo());
			builder.setExcelDataString(rowNum, colNum++, bean.getStyleOfPackingTo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOtherCompanyCd1To());
			builder.setExcelDataString(rowNum, colNum++, bean.getActivateTo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			// =============================================================================================================
			// 保存テーブルの情報を別シートに出力する
			// =============================================================================================================
			// 見積単価
			List<EstimateSavedListForReport> estimate = estimateSavedListForReportDao.getListForReport(bean.getPkNo());
			/* シートをセット */
			builder.setSheet("estimatesaved");
			
			for (EstimateSavedListForReport data : estimate) {
				colNum = TEMP_START_COL;
				if(salesTermsRowNum < 65530){
				
					builder.setExcelDataString(estimateRowNum, colNum++, data.getPkNo());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getProcType());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getEstimateNo());
					builder.setExcelDataTimestamp(estimateRowNum, colNum++, data.getEstimateInputDate());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getBalanceCd());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getVenderCd());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getVenderName());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getItemCd());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getItemName());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getConsecutiveNo());
					builder.setExcelDataBigDecimal(estimateRowNum, colNum++, data.getStandard_unitPrice());
					builder.setExcelDataBigDecimal(estimateRowNum, colNum++, data.getStandardDiscount());
					builder.setExcelDataBigDecimal(estimateRowNum, colNum++, data.getSpecialDiscount());
					builder.setExcelDataBigDecimal(estimateRowNum, colNum++, data.getUnitprice());
					builder.setExcelDataBigDecimal(estimateRowNum, colNum++, data.getStandardAmount());
					builder.setExcelDataBigDecimal(estimateRowNum, colNum++, data.getMatss());
					builder.setExcelDataTimestamp(estimateRowNum, colNum++, data.getEstimateValidDateFrom());
					builder.setExcelDataTimestamp(estimateRowNum, colNum++, data.getEstimateValidDateTo());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getRemark());
					builder.setExcelDataTimestamp(estimateRowNum, colNum++, data.getInputDate());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getInputorCd());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getInputorName());
					builder.setExcelDataTimestamp(estimateRowNum, colNum++, data.getUpdateDate());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getUpdatorCd());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getUpdatorName());
					builder.setExcelDataString(estimateRowNum, colNum++, data.getEstimateStatus());
				}

				
				estimateRowNum++;
			}
			
			// 販売条件
			// 見積単価
			List<SalesTermsSavedListForReport> salesT = salesTermsSavedListForReportDao.getListForReport(bean.getPkNo());
			
			/* シートをセット */
			builder.setSheet("salestermssaved");

			for (SalesTermsSavedListForReport data : salesT) {
				colNum = TEMP_START_COL;
				
				if(salesTermsRowNum < 65530){
					
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getPkNo());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getProcType());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getDeliveryCd());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getDeliveryName());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getBalanceCd());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getItemCd());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getItemName());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getSeq());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getTantoCd());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getTantoName());
					builder.setExcelDataTimestamp(salesTermsRowNum, colNum++, data.getInputDate());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getInputorCd());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getInputorName());
					builder.setExcelDataTimestamp(salesTermsRowNum, colNum++, data.getUpdateDate());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getUpdatorCd());
					builder.setExcelDataString(salesTermsRowNum, colNum++, data.getUpdatorName());
				}
				salesTermsRowNum++;
			}
			
			
			rowNum++;
		}
	}

	/**
	 * salestermsAndEstimateListLogicを設定します。
	 * @param salestermsAndEstimateListLogic salestermsAndEstimateListLogic
	 */
	public void setSalestermsAndEstimateListLogic(
			final SalestermsAndEstimateListLogic salestermsAndEstimateListLogic) {
		this.salestermsAndEstimateListLogic = salestermsAndEstimateListLogic;
	}

	/**
	 * estimateSavedListForReportDaoを設定します。
	 * @param estimateSavedListForReportDao estimateSavedListForReportDao
	 */
	public void setEstimateSavedListForReportDao(
			EstimateSavedListForReportDao estimateSavedListForReportDao) {
		this.estimateSavedListForReportDao = estimateSavedListForReportDao;
	}

	/**
	 * salesTermsSavedListForReportDaoを設定します。
	 * @param salesTermsSavedListForReportDao salesTermsSavedListForReportDao
	 */
	public void setSalesTermsSavedListForReportDao(
			SalesTermsSavedListForReportDao salesTermsSavedListForReportDao) {
		this.salesTermsSavedListForReportDao = salesTermsSavedListForReportDao;
	}
}
