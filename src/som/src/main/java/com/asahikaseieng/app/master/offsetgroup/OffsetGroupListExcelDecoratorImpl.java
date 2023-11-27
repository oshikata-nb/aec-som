/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.相殺グループマスタ一覧
 * @author t0011036
 */
public class OffsetGroupListExcelDecoratorImpl implements
		OffsetGroupListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private OffsetGroupListLogic offsetGroupListLogic;

	/**
	 * コンストラクタ
	 */
	public OffsetGroupListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final OffsetGroupListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("offsetgrouplist");

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
	private void setDetail(final OffsetGroupListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<OffsetGroupListForReport> list = offsetGroupListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("offsetgrouplist");

		/* リスト部 */
		for (OffsetGroupListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getOffsetGroupCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOffsetGroupName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());

			rowNum++;
		}
	}

	/**
	 * offsetGroupListLogicを設定します。
	 * @param offsetGroupListLogic offsetGroupListLogic
	 */
	public void setOffsetGroupListLogic(
			final OffsetGroupListLogic offsetGroupListLogic) {
		this.offsetGroupListLogic = offsetGroupListLogic;
	}
}
