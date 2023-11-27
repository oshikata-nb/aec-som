/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.repMaterialDirectionDetail.RepMaterialDirectionDetail;
import com.asahikaseieng.dao.nonentity.repMaterialDirectionDetail.RepMaterialDirectionDetailDao;
import com.asahikaseieng.dao.nonentity.repMaterialDirectionHeader.RepMaterialDirectionHeader;
import com.asahikaseieng.dao.nonentity.repMaterialDirectionHeader.RepMaterialDirectionHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 原材料使用品リストＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class DirectionListMaterialExcelDecoratorImpl implements
		DirectionListMaterialExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepMaterialDirectionHeaderDao repMaterialDirectionHeaderDao;

	private RepMaterialDirectionDetailDao repMaterialDirectionDetailDao;

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
	public DirectionListMaterialExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ArrayList<String> directionNoArray, final String tantoName,
			final Timestamp currentDate, final HttpServletRequest request) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("material_direction_s");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 製造指図ヘッダ取得用ヴューを検索
		List<RepMaterialDirectionHeader> headerList = repMaterialDirectionHeaderDao
				.getMaterialDirectionHeaderList(directionNoArray);

		// 製造指図明細取得用ヴューを検索
		List<RepMaterialDirectionDetail> detailList = repMaterialDirectionDetailDao
				.getMaterialDirectionDetailList(directionNoArray);

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
	private void setHeader(final List<RepMaterialDirectionHeader> headerList,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepMaterialDirectionHeader bean : headerList) {
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
			// 調合タンクNO
			builder.setCellValue(sRow, sCol++, bean.getCompoundTankNo());
			// ホールドタンクNO
			builder.setCellValue(sRow, sCol++, bean.getHoldTankNo());
			// 予備溶解タンクNO
			builder.setCellValue(sRow, sCol++, bean.getDissolutionTankNo());
			// 充填タンクNO
			builder.setCellValue(sRow, sCol++, bean.getFilltankNo());
			// 包装ライン
			builder.setCellValue(sRow, sCol++, bean.getPackageLine());
			// 現工程
			builder.setCellValue(sRow, sCol++, bean.getCurrentStepNo());
			// 主要製品コード
			builder.setCellValue(sRow, sCol++, bean.getItemCd());
			// 予定生産量|仕込数量
			if (bean.getPlanedQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPlanedQty()
						.toString());
			}
			// 実績生産量
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
			// 製品名
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 荷姿
			builder.setCellValue(sRow, sCol++, bean.getStyleOfPacking());

			sRow++;
		}
	}

	/**
	 * 詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepMaterialDirectionDetail> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepMaterialDirectionDetail bean : detailList) {
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
			// ステップNO.
			if (bean.getStepNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStepNo().toString());
			}
			// 行NO.
			if (bean.getLineNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLineNo().toString());
			}
			// 投入順
			if (bean.getSeq() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSeq().toString());
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
			// 実績数量
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
			builder.setCellValue(sRow, sCol++, bean.getNotes());
			// ロケーションコード（第1タンクNo 兼 実績タンクNo）
			builder.setCellValue(sRow, sCol++, bean.getLocationCd());
			// 第2タンクNo
			builder.setCellValue(sRow, sCol++, bean.getNextLocationCd());
			// 第3タンクNo
			builder.setCellValue(sRow, sCol++, bean.getNextAfterLocationCd());
			// 入荷ロット番号
			builder.setCellValue(sRow, sCol++, bean.getLotNo());
			// メーカーロット番号
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
			// 資材名
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 荷姿
			builder.setCellValue(sRow, sCol++, bean.getStyleOfPacking());
			// ロケーションコード
			builder.setCellValue(sRow, sCol++, bean.getInvLocationCd());
			// 入荷ロット番号
			builder.setCellValue(sRow, sCol++, bean.getInvLotNo());
			// 在庫数 荷姿数
			if (bean.getInvInoutQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getInvInoutQty()
						.toString());
			}
			// 在庫数 端数
			if (bean.getInvFraction() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getInvFraction()
						.toString());
			}
			// 在庫数 総量
			if (bean.getInvQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getInvQty().toString());
			}
			// 使用予定数 荷姿数
			if (bean.getPlanInoutQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPlanInoutQty()
						.toString());
			}
			// 使用予定数 端数
			if (bean.getPlanFraction() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPlanFraction()
						.toString());
			}
			// 使用予定数 総量
			if (bean.getPlanQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder
						.setCellValue(sRow, sCol++, bean.getPlanQty()
								.toString());
			}
			// 引当残
			if (bean.getAssignQty() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAssignQty()
						.toString());
			}

			sRow++;

		}
	}

	/**
	 * 原材料使用品リストヘッダ検索用Dao
	 * @param repMaterialDirectionHeaderDao 原材料使用品リストヘッダ検索用Dao
	 */
	public void setRepMaterialDirectionHeaderDao(
			final RepMaterialDirectionHeaderDao repMaterialDirectionHeaderDao) {
		this.repMaterialDirectionHeaderDao = repMaterialDirectionHeaderDao;
	}

	/**
	 * 原材料使用品リスト詳細検索用Dao
	 * @param repMaterialDirectionDetailDao 原材料使用品リストヘッダ検索用Dao
	 */
	public void setRepMaterialDirectionDetailDao(
			final RepMaterialDirectionDetailDao repMaterialDirectionDetailDao) {
		this.repMaterialDirectionDetailDao = repMaterialDirectionDetailDao;
	}
}
