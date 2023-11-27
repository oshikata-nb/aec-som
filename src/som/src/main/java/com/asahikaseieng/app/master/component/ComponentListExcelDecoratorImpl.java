/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.component;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.成分マスタ一覧
 * @author t0011036
 */
public class ComponentListExcelDecoratorImpl implements
		ComponentListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ComponentListLogic componentListLogic;

	/**
	 * コンストラクタ
	 */
	public ComponentListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ComponentListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("componentlist");

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
	private void setDetail(final ComponentListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ComponentListForReport> list = componentListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("componentlist");

		/* リスト部 */
		for (ComponentListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getComponentCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getComponentName());
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
	 * componentListLogicを設定します。
	 * @param componentListLogic componentListLogic
	 */
	public void setComponentListLogic(
			final ComponentListLogic componentListLogic) {
		this.componentListLogic = componentListLogic;
	}
}
