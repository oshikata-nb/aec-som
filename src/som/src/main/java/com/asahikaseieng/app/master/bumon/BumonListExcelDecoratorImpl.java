/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bumon;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 会計部門マスタ一覧ＥＸＣＥＬダウンロード用ファイル作成
 * @author jbd
 */
public class BumonListExcelDecoratorImpl implements BumonListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private BumonListLogic bumonListLogic;

	/**
	 * コンストラクタ
	 */
	public BumonListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final BumonListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("bumonlist");

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
	private void setDetail(final BumonListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<BumonListForReport> list = bumonListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("bumonlist");

		/* リスト部 */
		for (BumonListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSectionShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSectionKanaName());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountSectionCd());
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
	 * bumonListLogicを設定します。
	 * @param bumonListLogic bumonListLogic
	 */
	public void setBumonListLogic(final BumonListLogic bumonListLogic) {
		this.bumonListLogic = bumonListLogic;
	}
}
