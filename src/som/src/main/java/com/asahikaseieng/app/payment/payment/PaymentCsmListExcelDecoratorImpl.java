/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentDetailDao;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentHeader;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentHeaderDao;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentInform;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentInformDao;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepTegataDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepTegataDetailDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 仕訳伝票（支払伝票）ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class PaymentCsmListExcelDecoratorImpl implements
		PaymentCsmListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepPaymentHeaderDao repPaymentHeaderDao;

	private RepPaymentDetailDao repPaymentDetailDao;

	private RepTegataDetailDao repTegataDetailDao;

	private RepPaymentInformDao repPaymentInformDao;

	private PaymentCsmListLogic paymentCsmListLogic;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 一覧用テンポラリシート開始行位置 */
	protected static final short TEMP_LIST_START_ROW = 1;

	/** 一覧用テンポラリシート開始列位置 */
	protected static final short TEMP_LIST_START_COL = 0;

	/** 仕訳伝票（支払伝票・手形振出指示書）ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 仕訳伝票（支払伝票・手形振出指示書）詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/** 仕訳伝票（お支払通知書）ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME2 = "header_data2";

	/** 仕訳伝票（お支払通知書）詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME2 = "detail_data2";

	/**
	 * コンストラクタ
	 */
	public PaymentCsmListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final ArrayList<String> slipNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("payment");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 仕訳伝票（支払伝票）ヘッダを取得
		List<RepPaymentHeader> headerList = repPaymentHeaderDao
				.getListForReport(slipNo);

		// 仕訳伝票（支払伝票）詳細を取得
		List<RepPaymentDetail> detailList = repPaymentDetailDao
				.getListForReport(slipNo);

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
	public FileDownloadInfo createReportTegata(final ArrayList<String> slipNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("tegata");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 手形振出指示書ヘッダを取得
		List<RepTegataDetail> headerList = repTegataDetailDao
				.getListForReport(slipNo);

		// 手形振出指示書詳細を取得
		List<RepTegataDetail> detailList = repTegataDetailDao
				.getListForReport(slipNo);

		/* ヘッダ情報をセット */
		setHeaderTegata(headerList, tantoName, currentDate);
		/* 明細をセット */
		setDetailTegata(detailList);
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportPaymentInform(
			final ArrayList<String> slipNo, final String tantoName,
			final String loginUserOrganizationName,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("payment_plan_inform");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// お支払通知書ヘッダを取得
		List<RepPaymentInform> headerList = repPaymentInformDao
				.getListForReport(slipNo);

		// お支払通知書詳細を取得
		List<RepPaymentInform> detailList = repPaymentInformDao
				.getListForReport(slipNo);

		/* ヘッダ情報をセット */
		setHeaderPaymentInform(headerList, tantoName,
			loginUserOrganizationName, currentDate);
		/* 明細をセット */
		setDetailPaymentInform(detailList);
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 仕訳伝票（支払伝票）ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepPaymentHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		for (RepPaymentHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// 入金番号
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo());
			// 担当部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 担当部署名
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName());
			// 処理日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPaymentDate());
			// 会社名
			builder.setExcelDataString(sRow, sCol++, bean.getHomeName());
			// 担当者名
			builder.setExcelDataString(sRow, sCol++, tantoName);

			sRow++;
		}

	}

	/**
	 * 仕訳伝票（支払伝票）詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepPaymentDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepPaymentDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// 伝票番号
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo());
			// ﾃﾞｰﾀ種別 4:支払（’4’固定）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataType());
			// ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDataTotalDivision());
			// 分類コード
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCategoryDivision());
			// 支払日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPaymentDate());
			// 行番号
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRowNo());
			// 仕入先コード
			builder.setExcelDataString(sRow, sCol++, bean.getSupplierCd());
			// 部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 支払金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPaymentAmount());
			// 銀行コード
			builder.setExcelDataString(sRow, sCol++, bean.getBankCd());
			// 預金種別
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAccountDivision());
			// 口座番号
			builder.setExcelDataString(sRow, sCol++, bean.getAccountNo());
			// 手形種別
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
			// 売掛更新フラグ(0：未処理、1：処理済)
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
			// 売掛対象(0：未処理、1：処理済、9：対象外)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositUpdateDivision());
			// 売掛番号
			builder.setExcelDataString(sRow, sCol++, bean.getDepositNo());
			// 売掛締め日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getDeliveryUpdateDate());
			// 請求更新フラグ(0：未処理、1：処理済)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimUpdateDivision());
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
			// 消込完了日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getEraserCompleteDate());
			// 承認ステータス(1：入力中、2：承認依頼中、3：承認済み)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getApprovalStatus());
			// 承認者
			builder.setExcelDataString(sRow, sCol++, bean.getApprovedby());
			// 承認日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getApprovaldate());
			// データ転送日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getTransmissionDate());
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
	 * 手形振出指示書ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderTegata(final List<RepTegataDetail> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		for (RepTegataDetail bean : headerList) {
			sCol = TEMP_START_COL;

			// キー
			builder.setExcelDataString(sRow, sCol++, bean.getKey());
			// 担当部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getBnaibuNo());
			// 担当部署名
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName());
			// 支払日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPaymentDate());
			// 出力日
			builder.setExcelDataTimestamp(sRow, sCol++, currentDate);
			// 担当者名
			builder.setExcelDataString(sRow, sCol++, tantoName);

			sRow++;
		}

	}

	/**
	 * 手形振出指示書詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailTegata(final List<RepTegataDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepTegataDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// キー
			builder.setExcelDataString(sRow, sCol++, bean.getKey());
			// 伝票番号
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo());
			// 行番号
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRowNo());
			// 支払日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPaymentDate());
			// 手形番号
			builder.setExcelDataString(sRow, sCol++, bean.getTegataNo());
			// 枝版
			builder.setExcelDataString(sRow, sCol++, bean.getEdaban());
			// 手形区分(1:受取手形 2:支払手形)
			builder.setExcelDataString(sRow, sCol++, bean.getKubun());
			// 手形種別(1約束手形 3:為替手形)
			builder.setExcelDataString(sRow, sCol++, bean.getShubetu());
			// 部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getBnaibuNo());
			// 振出日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getFuriYmd());
			// 満期日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getMankiYmd());
			// 決済予定日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getKessaiYmd());
			// 額面金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getKingaku());
			// サイト
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSaito());
			// 取引先コード
			builder.setExcelDataString(sRow, sCol++, bean.getToriCd());
			// 銀行コード
			builder.setExcelDataString(sRow, sCol++, bean.getBankCd());
			// 口座番号
			builder.setExcelDataString(sRow, sCol++, bean.getKozaNo());
			// 預金種別
			builder.setExcelDataString(sRow, sCol++, bean.getYShubetu());
			// 摘要名(使用しない)
			builder.setExcelDataString(sRow, sCol++, bean.getTekiyo());
			// 異動区分(11:受取受入 31:支手振出)
			builder.setExcelDataString(sRow, sCol++, bean.getIdoKubun1());
			// 自動仕訳区分(11:受手決済 31:支手決済)
			builder.setExcelDataString(sRow, sCol++, bean.getJSiwaKbn());
			// 自動仕訳番号(未設定)
			builder.setExcelDataString(sRow, sCol++, bean.getJSiwaNo());
			// 裏書譲渡先(使用しない)
			builder.setExcelDataString(sRow, sCol++, bean.getEitan());
			// 振出銀行コード(未設定)
			builder.setExcelDataString(sRow, sCol++, bean.getFuriBank());
			// 振出人名称(未設定)
			builder.setExcelDataString(sRow, sCol++, bean.getFuriNin());
			// データ転送日時|==CSVファイル作成
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getTransmissionDate());
			// 登録日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			// 部署名称
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName());
			// 銀行マスタ名称
			builder.setExcelDataString(sRow, sCol++, bean.getBankMasterName());
			// 部署名１
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());

			sRow++;

		}

	}

	/**
	 * お支払通知書ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderPaymentInform(
			final List<RepPaymentInform> headerList, final String tantoName,
			final String loginUserOrganizationName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME2);

		for (RepPaymentInform bean : headerList) {
			sCol = TEMP_START_COL;

			// 伝票番号
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo());
			// 担当部署
			builder.setExcelDataString(sRow, sCol++, loginUserOrganizationName);
			// 担当者
			builder.setExcelDataString(sRow, sCol++, tantoName);
			// 出力日
			builder.setExcelDataTimestamp(sRow, sCol++, currentDate);

			sRow++;
		}

	}

	/**
	 * お支払通知書詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailPaymentInform(final List<RepPaymentInform> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME2);

		for (RepPaymentInform bean : detailList) {
			sCol = TEMP_START_COL;

			// 伝票番号
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo());
			// ﾃﾞｰﾀ種別 4:支払（’4’固定）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataType());
			// ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDataTotalDivision());
			// 分類コード
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCategoryDivision());
			// 支払日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPaymentDate());
			// 行番号
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRowNo());
			// 仕入先コード
			builder.setExcelDataString(sRow, sCol++, bean.getSupplierCd());
			// 部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 支払金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPaymentAmount());
			// 銀行コード
			builder.setExcelDataString(sRow, sCol++, bean.getBankCd());
			// 預金種別
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAccountDivision());
			// 口座番号
			builder.setExcelDataString(sRow, sCol++, bean.getAccountNo());
			// 手形種別
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
			// 売掛更新フラグ(0：未処理、1：処理済)
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
			// 売掛対象(0：未処理、1：処理済、9：対象外)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositUpdateDivision());
			// 売掛番号
			builder.setExcelDataString(sRow, sCol++, bean.getDepositNo());
			// 売掛締め日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getDeliveryUpdateDate());
			// 請求更新フラグ(0：未処理、1：処理済)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimUpdateDivision());
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
			// 消込完了日
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getEraserCompleteDate());
			// 承認ステータス(1：入力中、2：承認依頼中、3：承認済み)
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getApprovalStatus());
			// 承認者
			builder.setExcelDataString(sRow, sCol++, bean.getApprovedby());
			// 承認日
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getApprovaldate());
			// データ転送日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getTransmissionDate());
			// 登録日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			// 登録者ID
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			// 更新者ID
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			// 支払金額合計
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSumPaymentAmount());
			// 今回仕入額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingAmount());
			// 仕入割引額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockReduction());
			// 相殺額
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getOffsetAmount());
			// 購入月
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getBuyMonth());
			// 銀行マスタ名称
			builder.setExcelDataString(sRow, sCol++, bean.getBankMasterName());
			// 取引先名称1
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			// 取引先名称2
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName2());
			// 口座区分
			builder.setExcelDataString(sRow, sCol++, bean
					.getAccountDivisionName());
			// 口座番号
			builder.setExcelDataString(sRow, sCol++, bean.getAccountNoVen());
			// 締日
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getClosingDate());
			// 振込手数料負担|1:相手先 2:自社
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTransferCommissionLoad());
			// 翌営業日フラグ|0:当日 1:翌日
			builder.setExcelDataString(sRow, sCol++, bean.getNextdayFlg());

			sRow++;

		}

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final PaymentListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("paymentlist");

		/* 明細をセット */
		setDetailList(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 明細をセット
	 * @param condition 検索条件
	 */
	private void setDetailList(final PaymentListConditionForReport condition) {
		short rowNum = TEMP_LIST_START_ROW;
		short colNum = TEMP_LIST_START_COL;

		List<PaymentListForReport> list = paymentCsmListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("paymentlist");

		/* リスト部 */
		for (PaymentListForReport bean : list) {
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
					.getPaymentDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getSlipNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getSupplierCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentAmount());
			builder.setExcelDataString(rowNum, colNum++, bean.getBankCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getNoteDivisionName());
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
					.getDebitAccountsName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDebitSubTitleCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditTitleCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditAccountsName());
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
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getTransmissionDate());
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
	 * 仕訳伝票（支払伝票）ヘッダ検索用Dao
	 * @param repPaymentHeaderDao 仕訳伝票（支払伝票）ヘッダ検索用Dao
	 */
	public void setRepPaymentHeaderDao(
			final RepPaymentHeaderDao repPaymentHeaderDao) {
		this.repPaymentHeaderDao = repPaymentHeaderDao;
	}

	/**
	 * 仕訳伝票（支払伝票）明細検索用Dao
	 * @param repPaymentDetailDao 仕訳伝票（支払伝票）明細検索用Dao
	 */
	public void setRepPaymentDetailDao(
			final RepPaymentDetailDao repPaymentDetailDao) {
		this.repPaymentDetailDao = repPaymentDetailDao;
	}

	/**
	 * 手形振出指示書明細検索用Dao
	 * @param repTegataDetailDao 手形振出指示書明細検索用Dao
	 */
	public void setRepTegataDetailDao(
			final RepTegataDetailDao repTegataDetailDao) {
		this.repTegataDetailDao = repTegataDetailDao;
	}

	/**
	 * お支払通知書検索用Dao
	 * @param repPaymentInformDao お支払通知書検索用Dao
	 */
	public void setRepPaymentInformDao(
			final RepPaymentInformDao repPaymentInformDao) {
		this.repPaymentInformDao = repPaymentInformDao;
	}

	/**
	 * paymentCsmListLogicを設定します。
	 * @param paymentCsmListLogic paymentCsmListLogic
	 */
	public void setPaymentCsmListLogic(
			final PaymentCsmListLogic paymentCsmListLogic) {
		this.paymentCsmListLogic = paymentCsmListLogic;
	}
}
