/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.login;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.ロケーションマスタ一覧
 * @author t0011036
 */
public class LoginListExcelDecoratorImpl implements LoginListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private LoginListLogic loginListLogic;

	/**
	 * コンストラクタ
	 */
	public LoginListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final LoginListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("loginlist");

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
	private void setDetail(final LoginListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<LoginListForReport> list = loginListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("loginlist");

		/* リスト部 */
		for (LoginListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPostId());
			builder.setExcelDataString(rowNum, colNum++, bean.getPostName());
			builder.setExcelDataString(rowNum, colNum++, bean.getPassword());
			builder.setExcelDataString(rowNum, colNum++, bean.getActiveFlg());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeleteFlg());
			builder.setExcelDataString(rowNum, colNum++, bean.getAdminFlg());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdatePass());
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
	 * loginListLogicを設定します。
	 * @param loginListLogic loginListLogic
	 */
	public void setLoginListLogic(final LoginListLogic loginListLogic) {
		this.loginListLogic = loginListLogic;
	}
}
