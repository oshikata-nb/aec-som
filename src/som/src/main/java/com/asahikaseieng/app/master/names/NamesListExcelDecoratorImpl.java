/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.名称マスタ一覧
 * @author t0011036
 */
public class NamesListExcelDecoratorImpl implements NamesListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private NamesListLogic namesListLogic;

	/**
	 * コンストラクタ
	 */
	public NamesListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final NamesListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("nameslist");

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
	private void setDetail(final NamesListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<NamesListForReport> list = namesListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("nameslist");

		/* リスト部 */
		for (NamesListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getNameDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getNameDivisionName());
			builder.setExcelDataString(rowNum, colNum++, bean.getNameCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getName01());
			builder.setExcelDataString(rowNum, colNum++, bean.getName02());
			builder.setExcelDataString(rowNum, colNum++, bean.getName03());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getQuantityRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getQuantityRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmqty01());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmqty02());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmqty03());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmeflg1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmeflg2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmeflg3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmeflg4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmeflg5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmekbn1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmekbn2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmekbn3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmekbn4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmekbn5());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmedate1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmedate2());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmedate3());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmedate4());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmedate5());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmetime1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmetime2());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmetime3());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmetime4());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getNmetime5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmenum1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmenum2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmenum3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmenum4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNmenum5());
			builder.setExcelDataString(rowNum, colNum++, bean.getNmevalue1());
			builder.setExcelDataString(rowNum, colNum++, bean.getNmevalue2());
			builder.setExcelDataString(rowNum, colNum++, bean.getNmevalue3());
			builder.setExcelDataString(rowNum, colNum++, bean.getNmevalue4());
			builder.setExcelDataString(rowNum, colNum++, bean.getNmevalue5());
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
	 * namesListLogicを設定します。
	 * @param namesListLogic namesListLogic
	 */
	public void setNamesListLogic(final NamesListLogic namesListLogic) {
		this.namesListLogic = namesListLogic;
	}
}
