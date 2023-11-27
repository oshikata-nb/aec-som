/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListForReport;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.RepDepositDetail;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.RepDepositDetailDao;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.RepDepositHeader;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.RepDepositHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 仕分伝票（入金伝票）ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class DepositListExcelDecoratorImpl implements DepositListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepDepositHeaderDao repDepositHeaderDao;

	private RepDepositDetailDao repDepositDetailDao;

	private DepositListLogic depositListLogic;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 一覧用テンポラリシート開始行位置 */
	protected static final short TEMP_LIST_START_ROW = 1;

	/** 一覧用テンポラリシート開始列位置 */
	protected static final short TEMP_LIST_START_COL = 0;

	/** 仕分伝票（入金伝票）ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 仕分伝票（入金伝票）詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/**
	 * コンストラクタ
	 */
	public DepositListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final ArrayList<String> creditNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("deposit");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 仕分伝票（入金伝票）ヘッダを取得
		List<RepDepositHeader> headerList = repDepositHeaderDao
				.getListForReport(creditNo);

		// 仕分伝票（入金伝票）詳細を取得
		List<RepDepositDetail> detailList = repDepositDetailDao
				.getListForReport(creditNo);

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
			final DepositCreditListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("depositlist");

		/* 明細をセット */
		setDetailList(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 仕分伝票（入金）ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepDepositHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepDepositHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// 入金番号
			builder.setExcelDataString(sRow, sCol++, bean.getCreditNo());
			// 担当部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 担当部署名
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName());

			// 処理日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getCreditDate());
			// 会社名
			builder.setExcelDataString(sRow, sCol++, bean.getHomeName());
			// 担当者名
			builder.setExcelDataString(sRow, sCol++, tantoName);

			sRow++;
		}
	}

	/**
	 * 仕分伝票（入金）詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepDepositDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepDepositDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// 入金番号
			builder.setExcelDataString(sRow, sCol++, bean.getCreditNo());

			// ﾃﾞｰﾀ種別 2:入金（’2’固定）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataType());
			// ﾃﾞｰﾀ集計区分（1：入金、2：相殺、9:その他）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDataTotalDivision());
			// 分類コード
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCategoryDivision());
			// 入金日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getCreditDate());
			// 行番号
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRowNo());
			// 請求先コード
			builder.setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 入金金額
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getCreditAmount());
			// 消込額
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getEraserAmount());
			// 入金消込残
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCreditEraserAmount());
			// 銀行コード
			builder.setExcelDataString(sRow, sCol++, bean.getBankCd());
			// 預金種別
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAccountDivision());
			// 口座番号
			builder.setExcelDataString(sRow, sCol++, bean.getAccountNo());
			// 手形種別|1:約束手形 2:為替手形
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getNoteDivision());
			// 手形番号
			builder.setExcelDataString(sRow, sCol++, bean.getNoteNo());
			// 振出日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getDrawalDate());
			// 満期日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getNoteDate());
			// 借方部門コード
			builder.setExcelDataString(sRow, sCol++, bean.getDebitSectionCd());
			// 借方科目コード
			builder.setExcelDataString(sRow, sCol++, bean.getDebitTitleCd());
			// 借方補助科目コード
			builder.setExcelDataString(sRow, sCol++, bean.getDebitSubTitleCd());
			// 貸方部門コード
			builder.setExcelDataString(sRow, sCol++, bean.getCreditSectionCd());
			// 貸方科目コード
			builder.setExcelDataString(sRow, sCol++, bean.getCreditTitleCd());
			// 貸方補助科目コード
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCreditSubTitleCd());
			// 摘要名
			builder.setExcelDataString(sRow, sCol++, bean.getSummary());
			// 売掛対象(0：未処理、1：処理済、9：対象外)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositTargetDivision());
			// 請求対象(0：未処理、1：処理済、9：対象外)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimTargetDivision());
			// 買掛対象(0：未処理、1：処理済、9：対象外)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPayableTargetDivision());
			// 支払対象(0：未処理、1：処理済、9：対象外)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPaymentTargetDivision());
			// 伝票発行日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getIssueDate());
			// 伝票発行済フラグ(0：未発行、1：発行済、9：不要)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getIssuedDivision());
			// 売掛更新フラグ(0：未処理、1：処理済)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositUpdateDivision());
			// 売掛番号
			builder.setExcelDataString(sRow, sCol++, bean.getDepositNo());
			// 売掛締め日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getDeliveryUpdateDate());
			// 請求更新フラグ(0：未処理、1：処理済)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositUpdateDivision());
			// 請求番号
			builder.setExcelDataString(sRow, sCol++, bean.getClaimNo());
			// 請求締め日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getInvoiceUpdateDate());
			// 買掛更新フラグ(0：未処理、1：処理済)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPayableUpdateDivision());
			// 買掛番号
			builder.setExcelDataString(sRow, sCol++, bean.getPayableNo());
			// 買掛締め日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getPayableUpdateDate());
			// 支払更新フラグ(0：未処理、1：処理済)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPaymentUpdateDivision());
			// 支払番号
			builder.setExcelDataString(sRow, sCol++, bean.getPaymentNo());
			// 支払締め日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getPaymentUpdateDate());
			// 消込完了フラグ(0：未処理、1：処理済)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEraserCompleteDivision());
			// 入金消込完了日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getEraserCompleteDate());
			// 摘要
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			// 承認ステータス(1：入力中、2：承認依頼中、3：承認済み)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getApprovalStatus());
			// 承認者
			builder.setExcelDataString(sRow, sCol++, bean.getApprovedby());
			// 承認日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getApprovaldate());

			// 登録日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			// 借方部門
			builder.setExcelDataString(sRow, sCol++, bean.getDebitTitleName());
			// 借方科目
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getDebitSectionName());
			// 貸方部門
			builder.setExcelDataString(sRow, sCol++, bean.getCreditTitleName());
			// 貸方科目
			builder.setExcelDataString(sRow, sCol++, bean
					.getCreditSectionName());
			// 借方請求先コード
			builder.setExcelDataString(sRow, sCol++, bean.getDebitSeikyuCd());
			// 借方請求先名
			builder.setExcelDataString(sRow, sCol++, bean.getDebitSeikyuName());
			// 貸方請求先コード
			builder.setExcelDataString(sRow, sCol++, bean.getCreditSeikyuCd());
			// 貸方請求先名
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCreditSeikyuName());

			sRow++;

		}
	}

	/**
	 * 明細をセット
	 * @param condition 検索条件
	 */
	private void setDetailList(
			final DepositCreditListConditionForReport condition) {
		short rowNum = TEMP_LIST_START_ROW;
		short colNum = TEMP_LIST_START_COL;

		List<DepositCreditListForReport> list = depositListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("depositlist");

		/* リスト部 */
		for (DepositCreditListForReport bean : list) {
			colNum = TEMP_LIST_START_COL;

			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getDataType());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDataTotalDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCategoryDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getCategoryName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getCreditNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditEraserAmount());
			builder.setExcelDataString(rowNum, colNum++, bean.getBankCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountDivisionName());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getNoteNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getDrawalDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getNoteDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDebitSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDebitSectionName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getDebitTitleCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDebitTitleName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDebitSubTitleCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditTitleCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditTitleName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditSubTitleCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSummary());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDepositTargetDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimTargetDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayableTargetDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentTargetDivision());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getIssueDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getIssuedDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDepositUpdateDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getDepositNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getDeliveryUpdateDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimUpdateDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getClaimNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getInvoiceUpdateDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayableUpdateDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getPayableNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getPayableUpdateDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentUpdateDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getPaymentNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getPaymentUpdateDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserCompleteDivision());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getEraserCompleteDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getApprovalStatus());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getApprovalStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getApprovedby());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getApprovorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getApprovaldate());
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
	 * 仕分伝票（入金伝票）ヘッダ検索用Dao
	 * @param repDepositHeaderDao 仕分伝票（入金伝票）ヘッダ検索用Dao
	 */
	public void setRepDepositHeaderDao(
			final RepDepositHeaderDao repDepositHeaderDao) {
		this.repDepositHeaderDao = repDepositHeaderDao;
	}

	/**
	 * 仕分伝票（入金伝票）明細検索用Dao
	 * @param repDepositDetailDao 仕分伝票（入金伝票）明細検索用Dao
	 */
	public void setRepDepositDetailDao(
			final RepDepositDetailDao repDepositDetailDao) {
		this.repDepositDetailDao = repDepositDetailDao;
	}

	/**
	 * depositListLogicを設定します。
	 * @param depositListLogic depositListLogic
	 */
	public void setDepositListLogic(final DepositListLogic depositListLogic) {
		this.depositListLogic = depositListLogic;
	}
}
