/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.offset;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListForReport;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.RepOffsetDetail;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.RepOffsetDetailDao;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.RepOffsetHeader;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.RepOffsetHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 仕訳伝票（相殺伝票）ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class OffsetListExcelDecoratorImpl implements OffsetListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepOffsetHeaderDao repOffsetHeaderDao;

	private RepOffsetDetailDao repOffsetDetailDao;

	private OffsetListLogic offsetListLogic;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 一覧用テンポラリシート開始行位置 */
	protected static final short TEMP_LIST_START_ROW = 1;

	/** 一覧用テンポラリシート開始列位置 */
	protected static final short TEMP_LIST_START_COL = 0;

	/** 仕訳伝票（相殺伝票）ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 仕訳伝票（相殺伝票）詳細用データシート名 */
	protected static final String DETAIL_DEBIT_SHEET_NAME = "detail_debit";

	/** 仕訳伝票（相殺伝票）詳細用データシート名 */
	protected static final String DETAIL_CREDIT_SHEET_NAME = "detail_credit";

	/**
	 * コンストラクタ
	 */
	public OffsetListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final ArrayList<String> offsetNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("offset");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 仕訳伝票（相殺伝票）ヘッダを取得
		List<RepOffsetHeader> headerList = repOffsetHeaderDao
				.getListForReport(offsetNo);

		// 仕訳伝票（相殺伝票）詳細を取得
		List<RepOffsetDetail> detailList = repOffsetDetailDao
				.getListForReport(offsetNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, tantoName, currentDate);
		/* 明細（貸方）をセット */
		setDetailDebit(detailList);
		/* 明細（借方）をセット */
		setDetailCredit(detailList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final OffsetListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("offsetlist");

		/* 明細をセット */
		setDetailList(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 仕訳伝票（相殺伝票）ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepOffsetHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		for (RepOffsetHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// 相殺番号
			builder.setExcelDataString(sRow, sCol++, bean.getOffsetNo());

			// 担当部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());

			// 担当部署名
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName());

			// 処理日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getOffsetDate());
			// 会社名
			builder.setExcelDataString(sRow, sCol++, bean.getHomeName());

			// 担当者名
			builder.setExcelDataString(sRow, sCol++, tantoName);

			sRow++;
		}

	}

	/**
	 * 仕訳伝票（相殺伝票）詳細（貸方）をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailDebit(final List<RepOffsetDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DEBIT_SHEET_NAME);

		for (RepOffsetDetail bean : detailList) {
			sCol = TEMP_START_COL;

			if (!bean.getVenderDivision().equals("SI")) {
				continue;
			}

			// 相殺番号
			builder.setExcelDataString(sRow, sCol++, bean.getOffsetNo());
			// ﾃﾞｰﾀ種別 5:グループ間相殺（’5’固定）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataType());
			// ﾃﾞｰﾀ集計区分（1：相殺、9：その他）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDataTotalDivision());
			// 分類コード
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCategoryDivision());
			// 部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 相殺グループコード
			builder.setExcelDataString(sRow, sCol++, bean.getOffsetGroupCd());
			// 相殺日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getOffsetDate());
			// 取引先区分==TS:得意先 SI:仕入先
			builder.setExcelDataString(sRow, sCol++, bean.getVenderDivision());
			// 請求先/支払先コード
			builder.setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 買掛金、未払金相殺金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPayableOffsetAmount());
			// 売掛金、未集金相殺金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositOffsetAmount());
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
			// 消込額
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getEraserAmount());
			// 入金消込残
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCreditEraserAmount());
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
			// 届先名１
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			// 届先名２
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName2());
			// 摘要名
			builder.setExcelDataString(sRow, sCol++, bean.getSummary());

			sRow++;

		}

	}

	/**
	 * 仕訳伝票（相殺伝票）詳細（借方）をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailCredit(final List<RepOffsetDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_CREDIT_SHEET_NAME);

		for (RepOffsetDetail bean : detailList) {
			sCol = TEMP_START_COL;

			if (!bean.getVenderDivision().equals("TS")) {
				continue;
			}

			// 相殺番号
			builder.setExcelDataString(sRow, sCol++, bean.getOffsetNo());
			// ﾃﾞｰﾀ種別 5:グループ間相殺（’5’固定）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataType());
			// ﾃﾞｰﾀ集計区分（1：相殺、9：その他）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDataTotalDivision());
			// 分類コード
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCategoryDivision());
			// 部署コード
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			// 相殺グループコード
			builder.setExcelDataString(sRow, sCol++, bean.getOffsetGroupCd());
			// 相殺日付
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getOffsetDate());
			// 取引先区分==TS:得意先 SI:仕入先
			builder.setExcelDataString(sRow, sCol++, bean.getVenderDivision());
			// 請求先/支払先コード
			builder.setExcelDataString(sRow, sCol++, bean.getVenderCd());
			// 買掛金、未払金相殺金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPayableOffsetAmount());
			// 売掛金、未集金相殺金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositOffsetAmount());
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
			// 消込額
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getEraserAmount());
			// 入金消込残
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCreditEraserAmount());
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
			// 届先名１
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			// 届先名２
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName2());
			// 摘要名
			builder.setExcelDataString(sRow, sCol++, bean.getSummary());

			sRow++;

		}

	}

	/**
	 * 明細をセット
	 * @param condition 検索条件
	 */
	private void setDetailList(final OffsetListConditionForReport condition) {
		short rowNum = TEMP_LIST_START_ROW;
		short colNum = TEMP_LIST_START_COL;

		List<OffsetListForReport> list = offsetListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("offsetlist");

		/* リスト部 */
		for (OffsetListForReport bean : list) {
			colNum = TEMP_LIST_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getOffsetNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOffsetGroupCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOffsetGroupName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getOffsetDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOffsetAmount());
			builder.setExcelDataString(rowNum, colNum++, bean.getSummaryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSummary());
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
	 * 仕訳伝票（相殺伝票）ヘッダ検索用Dao
	 * @param repOffsetHeaderDao 仕訳伝票（相殺伝票）ヘッダ検索用Dao
	 */
	public void setRepOffsetHeaderDao(
			final RepOffsetHeaderDao repOffsetHeaderDao) {
		this.repOffsetHeaderDao = repOffsetHeaderDao;
	}

	/**
	 * 仕訳伝票（相殺伝票）明細検索用Dao
	 * @param repOffsetDetailDao 仕訳伝票（相殺伝票）明細検索用Dao
	 */
	public void setRepOffsetDetailDao(
			final RepOffsetDetailDao repOffsetDetailDao) {
		this.repOffsetDetailDao = repOffsetDetailDao;
	}

	/**
	 * offsetListLogicを設定します。
	 * @param offsetListLogic offsetListLogic
	 */
	public void setOffsetListLogic(final OffsetListLogic offsetListLogic) {
		this.offsetListLogic = offsetListLogic;
	}
}
