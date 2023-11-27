/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.銀行マスタ一覧
 * @author t0011036
 */
public class BankListExcelDecoratorImpl implements BankListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private BankListLogic bankListLogic;

	/**
	 * コンストラクタ
	 */
	public BankListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final BankListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("banklist");

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
	private void setDetail(final BankListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<BankListForReport> list = bankListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("banklist");

		/* リスト部 */
		for (BankListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getBankCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getBankKanaName());
			builder.setExcelDataString(rowNum, colNum++, bean.getBankName());
			builder.setExcelDataString(rowNum, colNum++, bean.getBranchCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBranchKanaName());
			builder.setExcelDataString(rowNum, colNum++, bean.getBranchName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getBankMasterCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterName());
			builder.setExcelDataString(rowNum, colNum++, bean.getCalCd());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getDataType());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCategoryDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getFee());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getHomeComDealCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getHomeComDealName());
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
	 * bankListLogicを設定します。
	 * @param bankListLogic bankListLogic
	 */
	public void setBankListLogic(final BankListLogic bankListLogic) {
		this.bankListLogic = bankListLogic;
	}
}
