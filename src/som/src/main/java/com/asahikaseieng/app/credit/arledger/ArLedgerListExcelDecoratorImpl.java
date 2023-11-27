/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArLedgerConditionForReport;
import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArledgerDetail;
import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArledgerDetailDao;
import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArledgerHeader;
import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArledgerHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 売掛元帳ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class ArLedgerListExcelDecoratorImpl implements
		ArLedgerListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepArledgerHeaderDao repArledgerHeaderDao;

	private RepArledgerDetailDao repArledgerDetailDao;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 売掛元帳ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 売掛元帳詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/**
	 * コンストラクタ
	 */
	public ArLedgerListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final RepArLedgerConditionForReport condition,
			final String targetKbn, final String targetMonth,
			final String tantoName, final Timestamp currentDate,
			final HttpServletRequest request) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("arledger");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 売掛元帳ヘッダを取得
		List<RepArledgerHeader> headerList = repArledgerHeaderDao
				.getListForReport(condition, targetKbn);

		// 売掛元帳詳細を取得
		List<RepArledgerDetail> detailList = repArledgerDetailDao
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
	 * 売掛元帳ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepArledgerHeader> headerList,
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

		for (RepArledgerHeader bean : headerList) {
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
	 * 売掛元帳詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepArledgerDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepArledgerDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// 部署コード
			setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 売掛番号
			setExcelDataString(sRow, sCol++, bean.getDepositNo());
			// 支払先
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 順番
			setExcelDataString(sRow, sCol++, bean.getOrderFlg());
			// 得意先コード（表示用）
			setExcelDataString(sRow, sCol++, bean.getVenderCdDisp());
			// 得意先名称
			setExcelDataString(sRow, sCol++, bean.getVenderName());
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
			// 売上金額
			setExcelDataString(sRow, sCol++, bean.getSales());
			// 入金金額
			setExcelDataString(sRow, sCol++, bean.getCredit());
			// 残高
			setExcelDataString(sRow, sCol++, bean.getBalance());

			sRow++;

		}
	}

	/**
	 * 売掛元帳ヘッダ検索用Dao
	 * @param repArledgerHeaderDao 売掛元帳ヘッダ検索用Dao
	 */
	public void setRepArledgerHeaderDao(
			final RepArledgerHeaderDao repArledgerHeaderDao) {
		this.repArledgerHeaderDao = repArledgerHeaderDao;
	}

	/**
	 * 売掛元帳明細検索用Dao
	 * @param repArledgerDetailDao 売掛元帳明細検索用Dao
	 */
	public void setRepArledgerDetailDao(
			final RepArledgerDetailDao repArledgerDetailDao) {
		this.repArledgerDetailDao = repArledgerDetailDao;
	}

}
