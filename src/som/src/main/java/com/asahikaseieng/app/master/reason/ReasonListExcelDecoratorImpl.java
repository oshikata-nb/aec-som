/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reason;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.理由マスタ一覧
 * @author t0011036
 */
public class ReasonListExcelDecoratorImpl implements ReasonListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ReasonListLogic reasonListLogic;

	/**
	 * コンストラクタ
	 */
	public ReasonListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ReasonListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("reasonlist");

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
	private void setDetail(final ReasonListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ReasonListForReport> list = reasonListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("reasonlist");

		/* リスト部 */
		for (ReasonListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getRyCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getRyDescription());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDefaultFlg());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDefaultFlgName());
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
	 * reasonListLogicを設定します。
	 * @param reasonListLogic reasonListLogic
	 */
	public void setReasonListLogic(final ReasonListLogic reasonListLogic) {
		this.reasonListLogic = reasonListLogic;
	}
}
