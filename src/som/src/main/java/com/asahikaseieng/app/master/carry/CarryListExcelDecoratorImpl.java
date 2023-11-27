/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.運送会社マスタ一覧
 * @author t0011036
 */
public class CarryListExcelDecoratorImpl implements CarryListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private CarryListLogic carryListLogic;
	

	/**
	 * コンストラクタ
	 */
	public CarryListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final CarryListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("carrylist");

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
	private void setDetail(final CarryListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<CarryListForReport> list = carryListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("carrylist");

		/* リスト部 */
		for (CarryListForReport bean : list) {
			colNum = TEMP_START_COL;
			
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName2());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAbbreviation());
			builder.setExcelDataString(rowNum, colNum++, bean.getZipcodeNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getFlanTransShopCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getBcPublishDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getBcHeader());
			builder.setExcelDataString(rowNum, colNum++, bean.getBcFooter());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getBcNumStart());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getBcNumEnd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getBcNumPresent());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getBcNumberOfDigit());
			builder.setExcelDataString(rowNum, colNum++, bean.getNextPublishBc());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemarks());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTranceportLeadTime());
			builder.setExcelDataString(rowNum, colNum++, bean.getCompanyCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getLabelPublish());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getLabelCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getReportOutputNum());

			rowNum++;
		}
	}

	/**
	 * carryListLogicを設定します。
	 * @param carryListLogic carryListLogic
	 */
	public void setCarryListLogic(final CarryListLogic carryListLogic) {
		this.carryListLogic = carryListLogic;
	}
}
