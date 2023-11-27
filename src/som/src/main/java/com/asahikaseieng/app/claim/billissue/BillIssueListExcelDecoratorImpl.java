/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.billissue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListForReport;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepBillIssueDetail;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepBillIssueDetailDao;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepBillIssueHeader;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepBillIssueHeaderDao;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepTemporaryBillIssueDetailDao;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepTemporaryBillIssueHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 請求書ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class BillIssueListExcelDecoratorImpl implements
		BillIssueListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepBillIssueHeaderDao repBillIssueHeaderDao;

	private RepBillIssueDetailDao repBillIssueDetailDao;

	private RepTemporaryBillIssueHeaderDao repTemporaryBillIssueHeaderDao;

	private RepTemporaryBillIssueDetailDao repTemporaryBillIssueDetailDao;

	private BillIssueListLogic billIssueListLogic;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 一覧帳票用テンポラリシート開始行位置 */
	protected static final short TEMP_LIST_START_ROW = 1;

	/** 一覧帳票用テンポラリシート開始列位置 */
	protected static final short TEMP_LIST_START_COL = 0;

	/** 請求書ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 請求書詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/* 対象区分 本締め */
	private static final BigDecimal NORMAL = new BigDecimal("0");

	/* 請求書発行 */
	private static final BigDecimal ISSUE = new BigDecimal("1");

	/**
	 * コンストラクタ
	 */
	public BillIssueListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final ArrayList<String> claimNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress, final BigDecimal srhNormalTemp) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("billissue");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		List<RepBillIssueHeader> headerList = new ArrayList<RepBillIssueHeader>();
		List<RepBillIssueDetail> detailList = new ArrayList<RepBillIssueDetail>();
		// 請求書ヘッダを取得
		if(srhNormalTemp.equals(NORMAL)){
			headerList = repBillIssueHeaderDao
					.getListForReport(claimNo);

			// 請求書詳細を取得
			detailList = repBillIssueDetailDao
					.getListForReport(claimNo);
		} else {
			headerList = repTemporaryBillIssueHeaderDao
					.getListForReport(claimNo);

			// 請求書詳細を取得
			detailList = repTemporaryBillIssueDetailDao
					.getListForReport(claimNo);
		}

		/* ヘッダ情報をセット */
		setHeader(headerList, tantoName, currentDate);
		/* 明細をセット */
		setDetail(detailList);
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final BillIssueListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("billissuelist");

		/* 明細をセット */
		setDetailList(condition);

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
	 * 請求書ヘッダをセット
	 * @param headerList 請求書ヘッダーデータ
	 * @param tantoName 担当者名
	 * @param currentDate 現在日時
	 */
	private void setHeader(final List<RepBillIssueHeader> headerList,
			final String tantoName, final Timestamp currentDate) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		// シートをセット
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;

		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepBillIssueHeader bean : headerList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean.getClaimNo()); // 請求番号
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd()); // 部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getVenderCd()); // 請求先コード
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getCreditDate()); // 請求締め日
			builder.setExcelDataTimestamp(sRow, sCol++, bean // 入金予定日
					.getCreditScheduledDate());
			builder.setExcelDataString(sRow, sCol++, bean.getCreditDivision()); // 入金分類（決済方法）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNoteSight()); // 手形サイト
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getHolidayFlg()); // 休日指定フラグ
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimAmountForward()); // 前回請求額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCreditAmountForward()); // 前回入金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOtherCreditAmountForward()); // 前回その他入金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getBalanceForward()); // 差引繰越額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSalesAmount()); // 今回売上額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal0SalesAmount()); // 今回売上額０%
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal8SalesAmount()); // 今回売上額8%
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal10SalesAmount()); // 今回売上額10%
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getKeigen8SalesAmount()); // 今回売上額8%軽減
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getGoukeiSalesAmount()); // 今回売上額合計
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesReturnedAmount()); // 返品金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesDiscountAmount()); // 値引金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOtherSalesAmount()); // その他売上金額
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getOffsetAmount()); // 相殺
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxAmount()); // 消費税額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal8TaxAmount()); // 消費税額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal10TaxAmount()); // 消費税額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getKeigen8TaxAmount()); // 消費税額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getGoukeiTaxAmount()); // 消費税額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getClaimAmount()); // 今回請求額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimBalanceAmount()); // 未請求額
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getEraserAmount()); // 消込額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEraserBalanceAmount()); // 消込残
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getBillDivision()); // 請求書発行済フラグ（’0’：未発行、’1’：発行、’9’：発行不要）
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getIssueDate()); // 発行日
			builder.setExcelDataString(sRow, sCol++, bean.getIssuerCd()); // 発行者ID
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate()); // 登録日時
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd()); // 登録者ID
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate()); // 更新日時
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd()); // 更新者ID
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1()); // 請求先名称1
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName2()); // 請求先名称2
			builder.setExcelDataString(sRow, sCol++, bean.getVenZipcodeNo()); // 請求先郵便番号
			builder.setExcelDataString(sRow, sCol++, bean.getVenAddress1()); // 請求先住所1,2
			builder.setExcelDataString(sRow, sCol++, bean.getVenAddress2()); // 請求先住所3
			builder.setExcelDataString(sRow, sCol++, bean.getVenTelNo()); // 請求先TEL
			builder.setExcelDataString(sRow, sCol++, bean.getVenFaxNo()); // 請求先FAX
			builder.setExcelDataString(sRow, sCol++, bean.getHomeName()); // 自社名
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName()); // 部署名
			builder.setExcelDataString(sRow, sCol++, bean.getOrgZipcodeNo()); // 部署郵便番号
			builder.setExcelDataString(sRow, sCol++, bean.getOrgAddress()); // 部署住所
			builder.setExcelDataString(sRow, sCol++, bean.getOrgTelNo()); // 部署TEL
			builder.setExcelDataString(sRow, sCol++, bean.getOrgFaxNo()); // 部署FAX
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getHenpinSonota()); // 返品・その他
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal0HenpinSonota()); // 返品・その他
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal8HenpinSonota()); // 返品・その他
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNormal10HenpinSonota()); // 返品・その他
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getKeigen8HenpinSonota()); // 返品・その他
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getGoukeiHenpinSonota()); // 返品・その他
			setExcelDataString(sRow, sCol++, tantoName); // 担当者名
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTotalSalesAmount()); // 今回売上合計

			builder.setExcelDataString(sRow, sCol++, bean.getInvoiceNo()); // 登録インボイス番号

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInvoicePtnFlg()); // インボイスパターン

			sRow++;
		}
	}

	/**
	 * 請求書詳細をセット
	 * @param detailList 請求書詳細データ
	 */
	private void setDetail(final List<RepBillIssueDetail> detailList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		// シートをセット
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepBillIssueDetail bean : detailList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean.getClaimNo()); // 請求番号
			builder.setExcelDataString(sRow, sCol++, bean.getHeadVenderCd()); // 取引先
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getCreditDate()); // 請求締め日
			builder.setExcelDataString(sRow, sCol++, bean.getTranVenderCd()); // 届先コード
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1()); // 届先名１
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName2()); // 届先名２
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getTranDate()); // 日付
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo()); // 伝票番号
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRowNo()); // 行
			builder.setExcelDataString(sRow, sCol++, bean.getCategoryName()); // 区分
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd()); // 品目／摘要（コード）
			builder.setExcelDataString(sRow, sCol++, bean.getItemName()); // 品目／摘要
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking()); // 荷姿
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getQuantity()); // 数量
			builder.setExcelDataString(sRow, sCol++, bean.getUnit()); // 単位
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getUnitprice()); // 単価
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAmount()); // 金額
			builder.setExcelDataString(sRow, sCol++, bean.getTaxDisplayReport()); // 消費税率（帳票表示用）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxAmount()); // 消費税
			builder.setExcelDataString(sRow, sCol++, bean.getCustomerOrderNo()); // 先方発注No.
			builder.setExcelDataString(sRow, sCol++, bean.getRemark()); // 備考
			builder.setExcelDataString(sRow, sCol++, bean.getDeliveryAddress()); // 宛先
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEstimateMatss()); // 数量(増付)
			builder.setExcelDataString(sRow, sCol++, bean.getEstimateUnit()); // 単位(増付)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEstimateUnitprice()); // 単価(増付)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEstimateAmount()); // 金額(増付)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEstimateTaxAmount()); // 消費税(増付)
			builder.setExcelDataString(sRow, sCol++, bean.getSalesSlipNo()); // 売上伝票番号


			sRow++;
		}
	}

	/**
	 * 明細をセット
	 * @param condition 検索条件
	 */
	private void setDetailList(final BillIssueListConditionForReport condition) {
		short rowNum = TEMP_LIST_START_ROW;
		short colNum = TEMP_LIST_START_COL;

		List<BillIssueListForReport> list = billIssueListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("billissuelist");

		/* リスト部 */
		for (BillIssueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getClaimNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditScheduledDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteSight());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getHolidayFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimAmountForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditAmountForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherCreditAmountForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBalanceForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesReturnedAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesDiscountAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherSalesAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOffsetAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
				.getFunTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimBalanceAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserBalanceAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherCreditAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherSales());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBillDivision());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getIssueDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getIssuerCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getIssuerName());
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
	 * 請求書ヘッダ検索用Dao
	 * @param repBillIssueHeaderDao 請求書ヘッダ検索用Dao
	 */
	public void setRepBillIssueHeaderDao(
			final RepBillIssueHeaderDao repBillIssueHeaderDao) {
		this.repBillIssueHeaderDao = repBillIssueHeaderDao;
	}

	/**
	 * 請求書明細検索用Dao
	 * @param repBillIssueDetailDao 請求書明細検索用Dao
	 */
	public void setRepBillIssueDetailDao(
			final RepBillIssueDetailDao repBillIssueDetailDao) {
		this.repBillIssueDetailDao = repBillIssueDetailDao;
	}

	/**
	 * 請求書ヘッダ検索用Dao
	 * @param repTemporaryBillIssueHeaderDao 請求書ヘッダ検索用Dao
	 */
	public void setRepTemporaryBillIssueHeaderDao(
			final RepTemporaryBillIssueHeaderDao repTemporaryBillIssueHeaderDao) {
		this.repTemporaryBillIssueHeaderDao = repTemporaryBillIssueHeaderDao;
	}

	/**
	 * 請求書明細検索用Dao
	 * @param repTemporaryBillIssueDetailDao 請求書明細検索用Dao
	 */
	public void setRepTemporaryBillIssueDetailDao(
			final RepTemporaryBillIssueDetailDao repTemporaryBillIssueDetailDao) {
		this.repTemporaryBillIssueDetailDao = repTemporaryBillIssueDetailDao;
	}

	/**
	 * billIssueListLogicを設定します。
	 * @param billIssueListLogic billIssueListLogic
	 */
	public void setBillIssueListLogic(
			final BillIssueListLogic billIssueListLogic) {
		this.billIssueListLogic = billIssueListLogic;
	}
}
