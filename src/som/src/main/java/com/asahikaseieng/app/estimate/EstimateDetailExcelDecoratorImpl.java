/*
 * Created on 2009/05/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.repEstimateDetail.RepEstimateDetail;
import com.asahikaseieng.dao.nonentity.repEstimateDetail.RepEstimateDetailDao;
import com.asahikaseieng.dao.nonentity.repEstimateHeader.RepEstimateHeader;
import com.asahikaseieng.dao.nonentity.repEstimateHeader.RepEstimateHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 見積書ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class EstimateDetailExcelDecoratorImpl implements
		EstimateDetailExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepEstimateHeaderDao repEstimateHeaderDao;

	private RepEstimateDetailDao repEstimateDetailDao;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/**
	 * コンストラクタ
	 */
	public EstimateDetailExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final String estimateNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("estimatedetail");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 見積書ヘッダ取得用ヴューを検索
		List<RepEstimateHeader> headerList = repEstimateHeaderDao
				.getEstimateHeaderList(estimateNo);

		// 見積書明細取得用ヴューを検索
		List<RepEstimateDetail> detailList = repEstimateDetailDao
				.getEstimateDetailList(estimateNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, tantoName, currentDate);

		/* 明細をセット */
		setDetail(detailList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepEstimateHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepEstimateHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// 見積番号
			builder.setCellValue(sRow, sCol++, bean.getEstimateNo());
			// 見積入力日
			if (bean.getEstimateInputDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getEstimateInputDate());
			}
			// 帳合コード
			builder.setCellValue(sRow, sCol++, bean.getBalanceCd());
			// 得意先コード
			builder.setCellValue(sRow, sCol++, bean.getVenderCd());
			// 品目コード
			builder.setCellValue(sRow, sCol++, bean.getItemCd());
			// 連番
			if (bean.getConsecutiveNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getConsecutiveNo()
						.toString());
			}
			// 標準単価
			if (bean.getStandardUnitPrice() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStandardUnitPrice()
						.toString());
			}
			// 標準値引
			if (bean.getStandardDiscount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStandardDiscount()
						.toString());
			}
			// 特別値引
			if (bean.getSpecialDiscount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSpecialDiscount()
						.toString());
			}
			// 単価
			if (bean.getUnitprice() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getUnitprice()
						.toString());
			}
			// 基準数量
			if (bean.getStandardAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStandardAmount()
						.toString());
			}
			// 増付数
			if (bean.getMatss() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMatss().toString());
			}
			// 開始見積有効期限
			if (bean.getEstimateValidDateFrom() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getEstimateValidDateFrom());
			}
			// 終了見積有効期限
			if (bean.getEstimateValidDateTo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getEstimateValidDateTo());
			}
			// 備考
			builder.setCellValue(sRow, sCol++, bean.getRemark());
			// 登録日時
			if (bean.getInputDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getInputDate());
			}
			// 登録者ID
			builder.setCellValue(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			if (bean.getUpdateDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getUpdateDate());
			}
			// 更新者ID
			builder.setCellValue(sRow, sCol++, bean.getUpdatorCd());
			// ステータス
			if (bean.getEstimateStatus() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getEstimateStatus()
						.toString());
			}
			// 取引先名称
			builder.setCellValue(sRow, sCol++, bean.getVenderName());
			// 自社名称
			builder.setCellValue(sRow, sCol++, bean.getHomeName());
			// 部署名称
			builder.setCellValue(sRow, sCol++, bean.getOrganizationName());
			// 住所
			builder.setCellValue(sRow, sCol++, bean.getAddress());
			// TEL番号
			builder.setCellValue(sRow, sCol++, bean.getTelNo());
			// FAX番号
			builder.setCellValue(sRow, sCol++, bean.getFaxNo());

			sRow++;
		}
	}

	/**
	 * 詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepEstimateDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepEstimateDetail bean : detailList) {
			sCol = TEMP_START_COL;

			// 見積番号
			builder.setCellValue(sRow, sCol++, bean.getEstimateNo());
			// 見積入力日
			if (bean.getEstimateInputDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getEstimateInputDate());
			}
			// 帳合コード
			builder.setCellValue(sRow, sCol++, bean.getBalanceCd());
			// 得意先コード
			builder.setCellValue(sRow, sCol++, bean.getVenderCd());
			// 品目コード
			builder.setCellValue(sRow, sCol++, bean.getItemCd());
			// 連番
			if (bean.getConsecutiveNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getConsecutiveNo()
						.toString());
			}
			// 標準単価
			if (bean.getStandardUnitPrice() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStandardUnitPrice()
						.toString());
			}
			// 標準値引
			if (bean.getStandardDiscount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStandardDiscount()
						.toString());
			}
			// 特別値引
			if (bean.getSpecialDiscount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSpecialDiscount()
						.toString());
			}
			// 単価
			if (bean.getUnitprice() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getUnitprice()
						.toString());
			}
			// 基準数量
			if (bean.getStandardAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStandardAmount()
						.toString());
			}
			// 増付数
			if (bean.getMatss() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMatss().toString());
			}
			// 開始見積有効期限
			if (bean.getEstimateValidDateFrom() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getEstimateValidDateFrom());
			}
			// 終了見積有効期限
			if (bean.getEstimateValidDateTo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getEstimateValidDateTo());
			}
			// 備考
			builder.setCellValue(sRow, sCol++, bean.getRemark());
			// 登録日時
			if (bean.getInputDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getInputDate());
			}
			// 登録者ID
			builder.setCellValue(sRow, sCol++, bean.getInputorCd());
			// 更新日時
			if (bean.getUpdateDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getUpdateDate());
			}
			// 更新者ID
			builder.setCellValue(sRow, sCol++, bean.getUpdatorCd());
			// ステータス
			if (bean.getEstimateStatus() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getEstimateStatus()
						.toString());
			}
			// 品目名称
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 荷姿
			builder.setCellValue(sRow, sCol++, bean.getStyleOfPacking());

			sRow++;

		}
	}

	/**
	 * 見積書ヘッダ検索用Dao
	 * @param repEstimateHeaderDao 見積書ヘッダ検索用Dao
	 */
	public void setRepEstimateHeaderDao(
			final RepEstimateHeaderDao repEstimateHeaderDao) {
		this.repEstimateHeaderDao = repEstimateHeaderDao;
	}

	/**
	 * 見積書詳細検索用Dao
	 * @param repEstimateDetailDao 見積書詳細検索用Dao
	 */
	public void setRepEstimateDetailDao(
			final RepEstimateDetailDao repEstimateDetailDao) {
		this.repEstimateDetailDao = repEstimateDetailDao;
	}
}
