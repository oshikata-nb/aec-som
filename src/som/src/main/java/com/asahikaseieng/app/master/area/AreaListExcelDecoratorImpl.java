/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.area;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.地区マスタ一覧
 * @author t0011036
 */
public class AreaListExcelDecoratorImpl implements AreaListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private AreaListLogic areaListLogic;

	/**
	 * コンストラクタ
	 */
	public AreaListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final AreaListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("arealist");

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
	private void setDetail(final AreaListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<AreaListForReport> list = areaListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("arealist");

		/* リスト部 */
		for (AreaListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getAreaCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getAreaName());
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
	 * areaListLogicを設定します。
	 * @param areaListLogic areaListLogic
	 */
	public void setAreaListLogic(final AreaListLogic areaListLogic) {
		this.areaListLogic = areaListLogic;
	}
}
