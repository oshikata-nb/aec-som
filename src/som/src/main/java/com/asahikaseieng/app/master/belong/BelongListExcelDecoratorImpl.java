/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.所属マスタ一覧
 * @author t0011036
 */
public class BelongListExcelDecoratorImpl implements BelongListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private BelongListLogic belongListLogic;

	/**
	 * コンストラクタ
	 */
	public BelongListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final BelongListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("belonglist");

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
	private void setDetail(final BelongListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<BelongListForReport> list = belongListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("belonglist");

		/* リスト部 */
		for (BelongListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPostId());
			builder.setExcelDataString(rowNum, colNum++, bean.getPostName());
			builder.setExcelDataString(rowNum, colNum++, bean.getBelongKbn());

			rowNum++;
		}
	}

	/**
	 * belongListLogicを設定します。
	 * @param belongListLogic belongListLogic
	 */
	public void setBelongListLogic(final BelongListLogic belongListLogic) {
		this.belongListLogic = belongListLogic;
	}
}
