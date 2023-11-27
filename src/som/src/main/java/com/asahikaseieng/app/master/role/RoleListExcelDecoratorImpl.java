/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.ロールマスタ一覧
 * @author t0011036
 */
public class RoleListExcelDecoratorImpl implements RoleListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RoleListLogic roleListLogic;

	/**
	 * コンストラクタ
	 */
	public RoleListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final RoleListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("rolelist");

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
	private void setDetail(final RoleListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<RoleListForReport> list = roleListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("rolelist");

		/* リスト部 */
		for (RoleListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRoleId());
			builder.setExcelDataString(rowNum, colNum++, bean.getRoleName());

			rowNum++;
		}
	}

	/**
	 * roleListLogicを設定します。
	 * @param roleListLogic roleListLogic
	 */
	public void setRoleListLogic(final RoleListLogic roleListLogic) {
		this.roleListLogic = roleListLogic;
	}
}
