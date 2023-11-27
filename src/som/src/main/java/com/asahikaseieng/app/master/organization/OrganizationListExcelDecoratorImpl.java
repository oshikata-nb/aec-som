/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.部署マスタ一覧
 * @author t0011036
 */
public class OrganizationListExcelDecoratorImpl implements
		OrganizationListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private OrganizationListLogic organizationListLogic;

	/**
	 * コンストラクタ
	 */
	public OrganizationListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final OrganizationListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("organizationlist");

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
	private void setDetail(final OrganizationListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<OrganizationListForReport> list = organizationListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("organizationlist");

		/* リスト部 */
		for (OrganizationListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getParentOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getParentOrganizationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBillDescriptionMatter());
			builder.setExcelDataString(rowNum, colNum++, bean.getZipcodeNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
			builder.setExcelDataString(rowNum, colNum++, bean.getFromMailAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getFromMailAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getFromMailAddress3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getBccSendFlg());
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
	 * organizationListLogicを設定します。
	 * @param organizationListLogic organizationListLogic
	 */
	public void setOrganizationListLogic(
			final OrganizationListLogic organizationListLogic) {
		this.organizationListLogic = organizationListLogic;
	}
}
