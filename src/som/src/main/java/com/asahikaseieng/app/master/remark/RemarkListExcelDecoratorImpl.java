/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.備考マスタ一覧
 * @author t0011036
 */
public class RemarkListExcelDecoratorImpl implements RemarkListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RemarkListLogic remarkListLogic;

	/**
	 * コンストラクタ
	 */
	public RemarkListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final RemarkListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("remarklist");

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
	private void setDetail(final RemarkListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<RemarkListForReport> list = remarkListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("remarklist");

		/* リスト部 */
		for (RemarkListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getRemarkNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark01());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark02());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark03());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark04());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark05());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark06());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark07());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark08());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark09());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark10());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark11());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark12());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark13());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark14());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark15());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark16());
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
	 * remarkListLogicを設定します。
	 * @param remarkListLogic remarkListLogic
	 */
	public void setRemarkListLogic(final RemarkListLogic remarkListLogic) {
		this.remarkListLogic = remarkListLogic;
	}
}
