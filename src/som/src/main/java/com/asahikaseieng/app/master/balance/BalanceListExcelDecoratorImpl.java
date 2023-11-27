/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.balance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetail;
import com.asahikaseieng.dao.nonentity.master.balancelistforreport.BalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.balancelistforreport.BalanceListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.帳合マスタ一覧
 * @author t0011036
 */
public class BalanceListExcelDecoratorImpl implements BalanceListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private BalanceListLogic balanceListLogic;

	/**
	 * コンストラクタ
	 */
	public BalanceListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final BalanceListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("balancelist");

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
	private void setDetail(final BalanceListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<BalanceListForReport> list = balanceListLogic
				.getListForReport(condition);

		BalanceListDetail beanDetail = new BalanceListDetail();

		/* 明細詳細取得 */
		for (int i = 0; i < list.size(); i++) {
			/* 帳合一覧詳細取得 */
			beanDetail = balanceListLogic.getEntity(list.get(i).getBalanceCd(),
				list.get(i).getBalanceType());

			if (beanDetail == null) {
				list.get(i).setBalanceTypeName("未設定");
			} else {
				list.get(i).setBalanceTypeName(beanDetail.getBalanceTypeName());
				list.get(i).setShopLevel(beanDetail.getShopLevel());
				list.get(i).setVenderCd1(beanDetail.getVenderCd1());
				list.get(i).setVenderCd2(beanDetail.getVenderCd2());
				list.get(i).setVenderCd3(beanDetail.getVenderCd3());
				list.get(i).setVenderCd4(beanDetail.getVenderCd4());
				list.get(i).setVenderCd5(beanDetail.getVenderCd5());
				list.get(i).setVenderName1(beanDetail.getVenderName1());
				list.get(i).setVenderName2(beanDetail.getVenderName2());
				list.get(i).setVenderName3(beanDetail.getVenderName3());
				list.get(i).setVenderName4(beanDetail.getVenderName4());
				list.get(i).setVenderName5(beanDetail.getVenderName5());
				list.get(i).setInputDate(beanDetail.getInputDate());
				list.get(i).setInputorCd(beanDetail.getInputorCd());
				list.get(i).setInputorName(beanDetail.getInputorName());
				list.get(i).setUpdateDate(beanDetail.getUpdateDate());
				list.get(i).setUpdatorCd(beanDetail.getUpdatorCd());
				list.get(i).setUpdatorName(beanDetail.getUpdatorName());
			}
		}

		/* シートをセット */
		builder.setSheet("balancelist");

		/* リスト部 */
		for (BalanceListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getBalanceCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBalanceType());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBalanceTypeName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd1());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd2());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName2());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd3());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName3());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd4());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName4());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd5());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShopLevel());
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
	 * balanceListLogicを設定します。
	 * @param balanceListLogic balanceListLogic
	 */
	public void setBalanceListLogic(final BalanceListLogic balanceListLogic) {
		this.balanceListLogic = balanceListLogic;
	}
}
