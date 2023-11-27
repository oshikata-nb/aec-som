/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.repPackageDirectionDetail.RepPackageDirectionDetail;
import com.asahikaseieng.dao.nonentity.repPackageDirectionDetail.RepPackageDirectionDetailDao;
import com.asahikaseieng.dao.nonentity.repPackageDirectionHeader.RepPackageDirectionHeader;
import com.asahikaseieng.dao.nonentity.repPackageDirectionHeader.RepPackageDirectionHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装指図一覧ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class PkgDirectionListExcelDecoratorImpl implements
		PkgDirectionListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepPackageDirectionHeaderDao repPackageDirectionHeaderDao;

	private RepPackageDirectionDetailDao repPackageDirectionDetailDao;

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
	public PkgDirectionListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final String[] directionNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("package_direction");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 包装指図ヘッダ取得用ヴューを検索
		List<RepPackageDirectionHeader> headerList = repPackageDirectionHeaderDao
				.getHeaderList(directionNo);

		// 包装指図明細取得用ヴューを検索
		List<RepPackageDirectionDetail> detailList = repPackageDirectionDetailDao
				.getPackageDetailList(directionNo);

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
	private void setHeader(final List<RepPackageDirectionHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepPackageDirectionHeader bean : headerList) {
			sCol = TEMP_START_COL;
			// 製造指図区分
			if (bean.getDirectionDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDirectionDivision()
						.toString());
			}
			// 製造指図番号★
			builder.setCellValue(sRow, sCol++, bean.getDirectionNo());
			// 指図日時
			if (bean.getDirectionDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDirectionDate());
			}
			// アスプローバオーダーコード
			builder.setCellValue(sRow, sCol++, bean.getAspOrderNo());
			// 指図ステータス
			if (bean.getDirectionStatus() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDirectionStatus()
						.toString());
			}
			// レシピインデックス
			if (bean.getRecipeId() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getRecipeId()
						.toString());
			}
			// レシピコード
			builder.setCellValue(sRow, sCol++, bean.getRecipeCd());
			// レシピバージョン
			if (bean.getRecipeVersion() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getRecipeVersion()
						.toString());
			}
			// 生産ライン
			builder.setCellValue(sRow, sCol++, bean.getProductionLine());
			// 調合タンクNO
			builder.setCellValue(sRow, sCol++, bean.getCompoundTankNo());
			// ホールドタンクNO
			builder.setCellValue(sRow, sCol++, bean.getHoldTankNo());
			// 予備溶解タンクNO
			builder.setCellValue(sRow, sCol++, bean.getDissolutionTankNo());
			// 充填タンクNO★
			builder.setCellValue(sRow, sCol++, bean.getFilltankNo());
			// 包装ライン
			builder.setCellValue(sRow, sCol++, bean.getPackageLine());
			// 現工程
			builder.setCellValue(sRow, sCol++, bean.getCurrentStepNo());
			// 主要製品コード
			builder.setCellValue(sRow, sCol++, bean.getItemCd());
			// 予定生産量|仕込数量★
			if (bean.getPlanedQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPlanedQty()
						.toString());
			}
			// 実績生産量★
			if (bean.getResultQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultQty()
						.toString());
			}
			// 分納フラグ
			if (bean.getDivideFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDivideFlag()
						.toString());
			}
			// 次回予定数量
			if (bean.getNextPlanedQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getNextPlanedQty()
						.toString());
			}
			// 物流入庫実績
			if (bean.getPdwResult() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPdwResult()
						.toString());
			}
			// ロス率
			if (bean.getPeocessLoss() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPeocessLoss()
						.toString());
			}
			// ロット番号
			builder.setCellValue(sRow, sCol++, bean.getLotNo());
			// 開始予定日時
			if (bean.getPlanedSdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPlanedSdate());
			}
			// 終了予定日時
			if (bean.getPlanedEdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPlanedEdate());
			}
			// 開始実績日時
			if (bean.getResultSdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultSdate());
			}
			// 終了実績日時
			if (bean.getResultEdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultEdate());
			}
			// 滅菌作業開始日時
			if (bean.getSteritSdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSteritSdate());
			}
			// 滅菌作業終了日時
			if (bean.getSteritEdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSteritEdate());
			}
			// 滅菌タンク内最小温度
			if (bean.getMekkinTankTempMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMekkinTankTempMin()
						.toString());
			}
			// 滅菌タンク内最大温度
			if (bean.getMekkinTankTempMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMekkinTankTempMax()
						.toString());
			}
			// 滅菌排水確認
			builder.setCellValue(sRow, sCol++, bean.getHaisuiCheck());
			// 指図書発行フラグ
			if (bean.getStampFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStampFlag()
						.toString());
			}
			// 指図書発行日
			if (bean.getStampDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStampDate());
			}
			// 指図書発行者
			builder.setCellValue(sRow, sCol++, bean.getStampTantoCd());
			// 製品ラベル発行フラグ
			if (bean.getProductLabelFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getProductLabelFlag()
						.toString());
			}
			// 製品ラベル発行日
			if (bean.getProductLabelDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getProductLabelDate());
			}
			// 製品ラベル発行者
			builder.setCellValue(sRow, sCol++, bean.getProductLabelTantoCd());
			// 製造/包装記録発行フラグ
			if (bean.getProductRecordFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getProductRecordFlag()
						.toString());
			}
			// 製造/包装記録発行日
			if (bean.getProductRecordDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getProductRecordDate());
			}
			// 製造/包装記録発行者
			builder.setCellValue(sRow, sCol++, bean.getProductRecordTantoCd());
			// 予備溶解ラベル発行フラグ
			if (bean.getStockdissLabelFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStockdissLabelFlag()
						.toString());
			}
			// 予備溶解ラベル発行日
			if (bean.getStockdissLabelDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder
						.setCellValue(sRow, sCol++, bean
								.getStockdissLabelDate());
			}
			// 予備溶解ラベル発行者
			builder.setCellValue(sRow, sCol++, bean.getStockdissLabelTantoCd());
			// 検査判定フラグ
			if (bean.getCertificationFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCertificationFlag()
						.toString());
			}
			// 検査合格日
			if (bean.getCertificationDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCertificationDate());
			}
			// 摘要
			builder.setCellValue(sRow, sCol++, bean.getRemark());
			// 注釈
			builder.setCellValue(sRow, sCol++, bean.getNotes());
			// 削除フラグ
			if (bean.getDeleteFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDeleteFlag()
						.toString());
			}
			// 製造担当者
			builder.setCellValue(sRow, sCol++, bean.getSeizotantocode());
			// 調合タンク洗浄担当者
			builder.setCellValue(sRow, sCol++, bean.getSenjotantocode());
			// 調合タンク滅菌作業担当者
			builder.setCellValue(sRow, sCol++, bean.getMekkintantocode());
			// 調合タンク内部状態
			builder.setCellValue(sRow, sCol++, bean.getChogotankcondi());
			// 予備溶解槽内部状態
			builder.setCellValue(sRow, sCol++, bean.getYobiyokaicondi());
			// ホールドタンク内部状態
			builder.setCellValue(sRow, sCol++, bean.getHoldtankcondi());
			// 総作業時間
			if (bean.getTotalJobtime() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTotalJobtime()
						.toString());
			}
			// 社員作業時間
			if (bean.getEnployJobtime() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getEnployJobtime()
						.toString());
			}
			// 協力作業時間
			if (bean.getCooperJobtime() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCooperJobtime()
						.toString());
			}
			// 承認者ID
			builder.setCellValue(sRow, sCol++, bean.getAppTantoCd());
			// 承認日時
			if (bean.getAppDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAppDate());
			}
			// 最終承認者ID
			builder.setCellValue(sRow, sCol++, bean.getLastAppTantoCd());
			// 最終承認日時
			if (bean.getLastAppDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLastAppDate());
			}
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
			// 製品名★
			builder.setCellValue(sRow, sCol++, bean.getHItemName());
			// 荷姿★
			builder.setCellValue(sRow, sCol++, bean.getHStyleOfPacking());
			// 充填予定数★
			if (bean.getFillingQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getFillingQty()
						.toString());
			}
			// 正味質量(Net)★
			if (bean.getNet() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getNet().toString());
			}
			// 量目管理幅最小★
			if (bean.getWeightMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getWeightMin()
						.toString());
			}
			// 量目管理幅最大★
			if (bean.getWeightMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getWeightMax()
						.toString());
			}
			// 濾過用フィルター★
			if (bean.getFilter() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getFilter().toString());
			}
			// 充填年月日（開始日時）★
			if (bean.getStartDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStartDate());
			}
			// 充填年月日（開始実績日時）★
			if (bean.getPResultSdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPResultSdate());
			}
			// オートチェッカー設定値最小★
			if (bean.getAutoCheckerMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAutoCheckerMin()
						.toString());
			}
			// オートチェッカー設定値最大★
			if (bean.getAutoCheckerMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAutoCheckerMax()
						.toString());
			}
			// グロスチェッカー設定値最小★
			if (bean.getGrossCheckerMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getGrossCheckerMin()
						.toString());
			}
			// グロスチェッカー設定値最大★
			if (bean.getGrossCheckerMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getGrossCheckerMax()
						.toString());
			}
			// 製品名（仕上）★
			builder.setCellValue(sRow, sCol++, bean.getSItemName());
			// 製品荷姿（仕上）★
			builder.setCellValue(sRow, sCol++, bean.getSStyleOfPacking());
			// 充填実績（仕上）★
			if (bean.getSFillResultQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSFillResultQty()
						.toString());
			}
			// 製品出来高（仕上）★
			if (bean.getSResultQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSResultQty()
						.toString());
			}

			// 指図書ﾊﾟﾀｰﾝ
			if (bean.getOrderPattern() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderPattern());
			}

			sRow++;
		}
	}

	/**
	 * 詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepPackageDirectionDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepPackageDirectionDetail bean : detailList) {
			sCol = TEMP_START_COL;
			// 指図区分
			if (bean.getDirectionDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDirectionDivision()
						.toString());
			}
			// 指図番号
			builder.setCellValue(sRow, sCol++, bean.getDirectionNo());
			// 品目コード★
			builder.setCellValue(sRow, sCol++, bean.getItemCd());
			// 実績数量★
			if (bean.getSumResultQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumResultQty()
						.toString());
			}
			// サンプル★
			if (bean.getSumSampleQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumSampleQty()
						.toString());
			}
			// ロス数量★
			if (bean.getSumLossQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumLossQty()
						.toString());
			}
			// 不良★
			if (bean.getSumDefectQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumDefectQty()
						.toString());
			}
			// 調整数量★
			if (bean.getSumAdjustQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumAdjustQty()
						.toString());
			}
			// 合計使用数★
			if (bean.getSumQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumQty().toString());
			}

			// 資材名★
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 在庫（品目在庫）★
			if (bean.getInventoryQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getInventoryQty()
						.toString());
			}

			sRow++;
		}
	}

	/**
	 * 包装指図ヘッダ検索用Dao
	 * @param repPackageDirectionHeaderDao 包装指図ヘッダ検索用Dao
	 */
	public void setRepPackageDirectionHeaderDao(
			final RepPackageDirectionHeaderDao repPackageDirectionHeaderDao) {
		this.repPackageDirectionHeaderDao = repPackageDirectionHeaderDao;
	}

	/**
	 * 包装指図詳細検索用Dao
	 * @param repPackageDirectionDetailDao 包装指図詳細検索用Dao
	 */
	public void setRepPackageDirectionDetailDao(
			final RepPackageDirectionDetailDao repPackageDirectionDetailDao) {
		this.repPackageDirectionDetailDao = repPackageDirectionDetailDao;
	}
}
