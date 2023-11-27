/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.util.List;


import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.運送会社マスタ一覧
 * @author t0011036
 */
public class CarryFileListExcelDecoratorImpl implements CarryFileListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private CarryFileListLogic carryFileListLogic;
	

	/**
	 * コンストラクタ
	 */
	public CarryFileListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final CarryFileListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("carryfilelist");

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
	private void setDetail(final CarryFileListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<CarryFileListForReport> list = carryFileListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("carrylist");

		/* リスト部 */
		for (CarryFileListForReport bean : list) {
			colNum = TEMP_START_COL;
			
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSeq());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getHeaderFlg());
			builder.setExcelDataString(rowNum, colNum++, bean.getDataCls());
			builder.setExcelDataString(rowNum, colNum++, bean.getTableName());
			builder.setExcelDataString(rowNum, colNum++, bean.getTableComments());
			builder.setExcelDataString(rowNum, colNum++, bean.getColumnName());
			builder.setExcelDataString(rowNum, colNum++, bean.getColComments());
			builder.setExcelDataString(rowNum, colNum++, bean.getHeaderName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getLinkFlg());
			builder.setExcelDataString(rowNum, colNum++, bean.getDescription());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getInputDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getUpdateDate());
			rowNum++;
		}
	}

	/**
	 * carryListLogicを設定します。
	 * @param carryListLogic carryListLogic
	 */
	public void setCarryListLogic(final CarryFileListLogic carryFileListLogic) {
		this.carryFileListLogic = carryFileListLogic;
	}
}
