/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.販売条件マスタ一覧
 * @author t0011036
 */
public class SalesTermsListExcelDecoratorImpl implements
		SalesTermsListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private SalesTermsListLogic salesTermsListLogic;

	/**
	 * コンストラクタ
	 */
	public SalesTermsListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final SalesTermsListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("salestermslist");

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
	private void setDetail(final SalesTermsListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<SalesTermsListForReport> list = salesTermsListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("salestermslist");

		/* リスト部 */
		for (SalesTermsListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName1());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryAddress());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryTelNo());

			builder.setExcelDataString(rowNum, colNum++, bean.getBalanceCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getStyleOfPacking());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getLeadTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSeq());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
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
	 * salesTermsListLogicを設定します。
	 * @param salesTermsListLogic salesTermsListLogic
	 */
	public void setSalesTermsListLogic(
			final SalesTermsListLogic salesTermsListLogic) {
		this.salesTermsListLogic = salesTermsListLogic;
	}
}
