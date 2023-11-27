/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.repManufactDirectionDetail.RepManufactDirectionDetail;
import com.asahikaseieng.dao.nonentity.repManufactDirectionDetail.RepManufactDirectionDetailDao;
import com.asahikaseieng.dao.nonentity.repManufactDirectionHeader.RepManufactDirectionHeader;
import com.asahikaseieng.dao.nonentity.repManufactDirectionHeader.RepManufactDirectionHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製造指図一覧ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class DirectionListExcelDecoratorImpl implements
		DirectionListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepManufactDirectionHeaderDao repManufactDirectionHeaderDao;

	private RepManufactDirectionDetailDao repManufactDirectionDetailDao;

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
	public DirectionListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final String[] directionNo,
			final String tantoName, final Timestamp currentDate,
			final HttpServletRequest request) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("manufucture_direction");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 製造指図ヘッダ取得用ヴューを検索
		List<RepManufactDirectionHeader> headerList = repManufactDirectionHeaderDao
				.getDirectionList(directionNo);

		// 製造指図明細取得用ヴューを検索
		List<RepManufactDirectionDetail> detailList = repManufactDirectionDetailDao
				.getDirectionDetailList(directionNo);

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
	private void setHeader(final List<RepManufactDirectionHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepManufactDirectionHeader bean : headerList) {
			sCol = TEMP_START_COL;
			// 製造指図区分
			if (bean.getDirectionDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDirectionDivision()
						.toString());
			}
			// 製造指図番号
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
			// 調合タンクNO★
			builder.setCellValue(sRow, sCol++, bean.getCompoundTankNo());
			// ホールドタンクNO★
			builder.setCellValue(sRow, sCol++, bean.getHoldTankNo());
			// 予備溶解タンクNO★
			builder.setCellValue(sRow, sCol++, bean.getDissolutionTankNo());
			// 充填タンクNO
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
			// ロット番号★
			builder.setCellValue(sRow, sCol++, bean.getLotNo());
			// 開始予定日時★
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
			// 開始実績日時★
			if (bean.getResultSdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultSdate());
			}
			// 終了実績日時★
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
			// 滅菌タンク内最小温度★
			if (bean.getMekkinTankTempMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMekkinTankTempMin()
						.toString());
			}
			// 滅菌タンク内最大温度★
			if (bean.getMekkinTankTempMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMekkinTankTempMax()
						.toString());
			}
			// 滅菌排水確認★
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
			// 摘要★
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
			// 調合タンク内部状態★
			builder.setCellValue(sRow, sCol++, bean.getChogotankcondi());
			// 予備溶解槽内部状態★
			builder.setCellValue(sRow, sCol++, bean.getYobiyokaicondi());
			// ホールドタンク内部状態★
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
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 製造担当者★
			builder.setCellValue(sRow, sCol++, bean.getSeizotantoname());
			// 調合タンク洗浄担当者★
			builder.setCellValue(sRow, sCol++, bean.getSenjotantoname());
			// 調合タンク滅菌担当者★
			builder.setCellValue(sRow, sCol++, bean.getMekkintantoname());

			// 指図書パターン
			builder.setExcelDataString(sRow, sCol++, bean.getOrderPattern());

			sRow++;
		}
	}

	/**
	 * 詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepManufactDirectionDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepManufactDirectionDetail bean : detailList) {
			sCol = TEMP_START_COL;
			// 製造指図区分
			if (bean.getDirectionDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDirectionDivision()
						.toString());
			}
			// 製造指図番号
			builder.setCellValue(sRow, sCol++, bean.getDirectionNo());
			// ｽﾃｯﾌﾟ
			if (bean.getStepNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStepNo().toString());
			}
			// SEQ
			if (bean.getSeqProcedure() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSeqProcedure()
						.toString());
			}
			// 工程コード
			builder.setCellValue(sRow, sCol++, bean.getOperationCd());
			// 工程条件
			builder.setCellValue(sRow, sCol++, bean.getCondition());
			// 備考
			builder.setCellValue(sRow, sCol++, bean.getRemarkProcedure());
			// 注釈
			builder.setCellValue(sRow, sCol++, bean.getNotesProcedure());
			// リードタイム（分）
			if (bean.getLeadtime() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLeadtime()
						.toString());
			}
			// 開始日時
			if (bean.getStartDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStartDate());
			}
			// 終了日時
			if (bean.getEndDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getEndDate());
			}
			// 開始実績日時★
			if (bean.getResultSdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultSdate());
			}
			// 終了実績日時★
			if (bean.getResultEdate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultEdate());
			}
			// 温度
			if (bean.getConditionTemp() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getConditionTemp()
						.toString());
			}
			// 時間
			if (bean.getConditionTime() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getConditionTime()
						.toString());
			}
			// 攪拌速度1
			if (bean.getStirSpeed1() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStirSpeed1()
						.toString());
			}
			// 攪拌速度2
			if (bean.getStirSpeed2() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStirSpeed2()
						.toString());
			}
			// 洗浄水絶対重量
			if (bean.getWaterWeight() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getWaterWeight()
						.toString());
			}
			// 本流/予備溶解
			if (bean.getMainStream() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMainStream()
						.toString());
			}
			// ｐＨ
			if (bean.getPh() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPh().toString());
			}
			// 実績温度★
			if (bean.getResultConditionTemp() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getResultConditionTemp().toString());
			}
			// 実績攪拌速度★
			if (bean.getResultStirSpeed() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultStirSpeed()
						.toString());
			}
			// 実績ｐＨ
			if (bean.getResultPh() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultPh()
						.toString());
			}
			// 充填予定数量
			if (bean.getFillingQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getFillingQty()
						.toString());
			}
			// 充填単位
			builder.setCellValue(sRow, sCol++, bean.getFillingUnit());
			// 正味質量
			if (bean.getNet() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getNet().toString());
			}
			// 量目管理幅最小
			if (bean.getWeightMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getWeightMin()
						.toString());
			}
			// 量目管理幅最大
			if (bean.getWeightMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getWeightMax()
						.toString());
			}
			// 濾過用フィルター
			if (bean.getFilter() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getFilter().toString());
			}
			// オートチェッカー最小
			if (bean.getAutoCheckerMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAutoCheckerMin()
						.toString());
			}
			// オートチェッカー最大
			if (bean.getAutoCheckerMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAutoCheckerMax()
						.toString());
			}
			// グロスチェッカー最小
			if (bean.getGrossCheckerMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getGrossCheckerMin()
						.toString());
			}
			// グロスチェッカー最大
			if (bean.getGrossCheckerMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getGrossCheckerMax()
						.toString());
			}
			// 開きトルク最小
			if (bean.getOpeningTorqueMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOpeningTorqueMin()
						.toString());
			}
			// 開きトルク最大
			if (bean.getOpeningTorqueMax() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOpeningTorqueMax()
						.toString());
			}
			// ホットエアー設定温度
			if (bean.getHotAirPresetTemp() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getHotAirPresetTemp()
						.toString());
			}
			// ホットエアー吹き出し圧力
			if (bean.getHotAirPressure() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getHotAirPressure()
						.toString());
			}
			// 濾過用メッシュ
			if (bean.getMesh() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMesh().toString());
			}
			// オートチェッカー中心
			if (bean.getAutoCheckerAve() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAutoCheckerAve()
						.toString());
			}
			// グロスチェッカー中心
			if (bean.getGrossCheckerAve() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getGrossCheckerAve()
						.toString());
			}
			// 閉めトルク最小
			if (bean.getMesh() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMesh().toString());
			}
			// 閉めトルク最大
			if (bean.getClosingTorqueMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getClosingTorqueMin()
						.toString());
			}
			// トルク圧
			if (bean.getTorquePressure() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTorquePressure()
						.toString());
			}
			// エアー圧
			if (bean.getAirPressure() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAirPressure()
						.toString());
			}
			// 巻閉め時間
			if (bean.getVcloseTime() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getVcloseTime()
						.toString());
			}
			// 第一ヒートシール設定温度
			if (bean.getClosingTorqueMin() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getClosingTorqueMin()
						.toString());
			}
			// 第二ヒートシール設定温度
			if (bean.getFirstHeatSeal() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getFirstHeatSeal()
						.toString());
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
			// 行NO.★
			if (bean.getLineNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLineNo().toString());
			}
			// 投入順★
			if (bean.getSeqFormula() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSeqFormula()
						.toString());
			}
			// 品目タイプ
			if (bean.getLineType() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLineType()
						.toString());
			}
			// 品目コード
			builder.setCellValue(sRow, sCol++, bean.getItemCd());
			// 投入方法
			if (bean.getTonyu() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTonyu().toString());
			}
			// データ読み取り
			if (bean.getDataread() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDataread()
						.toString());
			}
			// 投入速度
			if (bean.getTonyusokudo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTonyusokudo()
						.toString());
			}
			// 数量
			if (bean.getQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getQty().toString());
			}
			// 在庫引落量
			if (bean.getStockpdQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStockpdQty()
						.toString());
			}
			// 実績数量★
			if (bean.getResultQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getResultQty()
						.toString());
			}
			// サンプル
			if (bean.getSampleQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSampleQty()
						.toString());
			}
			// ロス数量
			if (bean.getLossQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder
						.setCellValue(sRow, sCol++, bean.getLossQty()
								.toString());
			}
			// 不良
			if (bean.getDefectQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDefectQty()
						.toString());
			}
			// 調整数量
			if (bean.getAdjustQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAdjustQty()
						.toString());
			}
			// 原価
			if (bean.getCost() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCost().toString());
			}
			// 条件
			builder.setCellValue(sRow, sCol++, bean.getStepCondition());
			// 注釈
			builder.setCellValue(sRow, sCol++, bean.getNotesFormula());
			// ロケーションコード（第1タンクNo 兼 実績タンクNo）
			builder.setCellValue(sRow, sCol++, bean.getLocationCd());
			// 第2タンクNo
			builder.setCellValue(sRow, sCol++, bean.getNextLocationCd());
			// 第3タンクNo
			builder.setCellValue(sRow, sCol++, bean.getNextAfterLocationCd());
			// 入荷ロット番号
			builder.setCellValue(sRow, sCol++, bean.getLotNo());
			// メーカーロット番号★
			builder.setCellValue(sRow, sCol++, bean.getManufacturerLotNo());
			// 充填予定数
			if (bean.getFillQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder
						.setCellValue(sRow, sCol++, bean.getFillQty()
								.toString());
			}
			// 充填実績数
			if (bean.getFillResultQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getFillResultQty()
						.toString());
			}
			// 備考
			builder.setCellValue(sRow, sCol++, "");
			// 作業内容★
			builder.setCellValue(sRow, sCol++, bean.getOperationName());
			// 原料名★
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 備考（工程+配合）★
			builder.setCellValue(sRow, sCol++, bean.getRemark());
			// 基準値・設定値
			if (bean.getConditionSettei() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getConditionSettei()
						.toString());
			}
			// 基準値・設定値（単位）
			builder.setCellValue(sRow, sCol++, bean.getConditionSetteiUnit());

			sRow++;
		}
	}

	/**
	 * 製造指図ヘッダ検索用Dao
	 * @param repManufactDirectionHeaderDao 製造指図ヘッダ検索用Dao
	 */
	public void setRepManufactDirectionHeaderDao(
			final RepManufactDirectionHeaderDao repManufactDirectionHeaderDao) {
		this.repManufactDirectionHeaderDao = repManufactDirectionHeaderDao;
	}

	/**
	 * 製造指図詳細検索用Dao
	 * @param repManufactDirectionDetailDao 製造指図ヘッダ検索用Dao
	 */
	public void setRepManufactDirectionDetailDao(
			final RepManufactDirectionDetailDao repManufactDirectionDetailDao) {
		this.repManufactDirectionDetailDao = repManufactDirectionDetailDao;
	}
}
