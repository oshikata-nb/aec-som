/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apledger;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApLedgerConditionForReport;
import com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApledgerDetail;
import com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApledgerDetailDao;
import com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApledgerHeader;
import com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApledgerHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 買掛元帳ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class ApLedgerListExcelDecoratorImpl implements
		ApLedgerListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepApledgerHeaderDao repApledgerHeaderDao;

	private RepApledgerDetailDao repApledgerDetailDao;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 買掛元帳ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 買掛元帳詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/**
	 * コンストラクタ
	 */
	public ApLedgerListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final RepApLedgerConditionForReport condition,
			final String targetKbn, final String targetMonth,
			final String tantoName, final Timestamp currentDate,
			final HttpServletRequest request) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("apledger");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 買掛元帳ヘッダを取得
		List<RepApledgerHeader> headerList = repApledgerHeaderDao
				.getListForReport(condition, targetKbn);

		List<RepApledgerDetail> detailList = repApledgerDetailDao
				.getListForReport(condition, targetKbn);

		/* ヘッダ情報をセット */
		setHeader(headerList, targetMonth, tantoName, currentDate);
		/* 明細をセット */
		setDetail(detailList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 行、列を指定してExcelシートにデータを貼り付け
	 * @param tantoName 担当者名
	 */
	private void setExcelDataString(final short sRow, final short sCol,
			final String value) {

		// nullでは無い場合のみデータをセット
		if (value != null) {
			// データをセット
			builder.setCellValue(sRow, sCol, value);
		}
	}

	/**
	 * 買掛元帳ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepApledgerHeader> headerList,
			final String targetMonth, final String tantoName,
			final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepApledgerHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// 部署コード
			setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 部署名
			setExcelDataString(sRow, sCol++, bean.getOrganizationName());
			// 対象年月
			setExcelDataString(sRow, sCol++, targetMonth + "/01");
			// 担当者名
			setExcelDataString(sRow, sCol++, tantoName);

			sRow++;
		}
	}

	/**
	 * 買掛元帳詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepApledgerDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepApledgerDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// 部署コード
			setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 買掛番号
			setExcelDataString(sRow, sCol++, bean.getPayableNo());
			// 支払先
			setExcelDataString(sRow, sCol++, bean.getSupplierCd());
			// 順番
			setExcelDataString(sRow, sCol++, bean.getOrderFlg());
			// 支払先コード（表示用）
			setExcelDataString(sRow, sCol++, bean.getSupplierCdDisp());
			// 支払先名称
			setExcelDataString(sRow, sCol++, bean.getSupplierName());
			// 日付
			setExcelDataString(sRow, sCol++, bean.getTranDate());
			// 区分
			setExcelDataString(sRow, sCol++, bean.getTranDivi());
			// 分類
			setExcelDataString(sRow, sCol++, bean.getCategoryName());
			// 伝票番号
			setExcelDataString(sRow, sCol++, bean.getSlipNo());
			// 行番号
			setExcelDataString(sRow, sCol++, bean.getRowNo());
			// 品目／摘要／科目
			setExcelDataString(sRow, sCol++, bean.getItemName());
			// 仕入金額
			setExcelDataString(sRow, sCol++, bean.getStocking());
			// 支払金額
			setExcelDataString(sRow, sCol++, bean.getPayment());
			// 残高
			setExcelDataString(sRow, sCol++, bean.getBalance());

			sRow++;

		}
	}

	/**
	 * 買掛元帳ヘッダ検索用Dao
	 * @param repApledgerHeaderDao 買掛元帳ヘッダ検索用Dao
	 */
	public void setRepApledgerHeaderDao(
			final RepApledgerHeaderDao repApledgerHeaderDao) {
		this.repApledgerHeaderDao = repApledgerHeaderDao;
	}

	/**
	 * 買掛元帳明細検索用Dao
	 * @param repApledgerDetailDao 買掛元帳明細検索用Dao
	 */
	public void setRepApledgerDetailDao(
			final RepApledgerDetailDao repApledgerDetailDao) {
		this.repApledgerDetailDao = repApledgerDetailDao;
	}

}
