/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.勘定科目マスタ一覧
 * @author t0011036
 */
public class AccountsListExcelDecoratorImpl implements
		AccountsListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private AccountsListLogic accountsListLogic;

	/**
	 * コンストラクタ
	 */
	public AccountsListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final AccountsListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("accountslist");

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
	private void setDetail(final AccountsListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<AccountsListForReport> list = accountsListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("accountslist");

		/* リスト部 */
		for (AccountsListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getAccountsCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountsSubCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAccountsName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountsSubName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getTaxationDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCostAccounts());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseAccounts());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getArticleAccounts());
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
	 * accountsListLogicを設定します。
	 * @param accountsListLogic accountsListLogic
	 */
	public void setAccountsListLogic(final AccountsListLogic accountsListLogic) {
		this.accountsListLogic = accountsListLogic;
	}
}
