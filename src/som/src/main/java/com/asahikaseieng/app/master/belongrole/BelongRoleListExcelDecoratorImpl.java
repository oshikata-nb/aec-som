/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.所属・ロール組合せマスタ一覧
 * @author t0011036
 */
public class BelongRoleListExcelDecoratorImpl implements
		BelongRoleListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private BelongRoleListLogic belongRoleListLogic;

	/**
	 * コンストラクタ
	 */
	public BelongRoleListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final BelongRoleListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("belongrolelist");

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
	private void setDetail(final BelongRoleListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<BelongRoleListForReport> list = belongRoleListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("belongrolelist");

		/* リスト部 */
		for (BelongRoleListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPostId());
			builder.setExcelDataString(rowNum, colNum++, bean.getPostName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRoleId());
			builder.setExcelDataString(rowNum, colNum++, bean.getRoleName());

			rowNum++;
		}
	}

	/**
	 * belongRoleListLogicを設定します。
	 * @param belongRoleListLogic belongRoleListLogic
	 */
	public void setBelongRoleListLogic(
			final BelongRoleListLogic belongRoleListLogic) {
		this.belongRoleListLogic = belongRoleListLogic;
	}
}
