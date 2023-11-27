/*
 * Created on 2009/06/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.repLabelProductDirection.RepLabelProductDirection;
import com.asahikaseieng.dao.nonentity.repLabelProductDirection.RepLabelProductDirectionDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製品ラベルＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class PkgDirectionListLabelExcelDecoratorImpl implements
		PkgDirectionListLabelExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepLabelProductDirectionDao repLabelProductDirectionDao;

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
	public PkgDirectionListLabelExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final TreeMap<String, String> tMap,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("label_product_direction");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/* 包装指図番号とラベル枚数をセット */
		String[] directionNo = new String[tMap.size()];
		String[] labelCount = new String[tMap.size()];
		int idx = 0;
		Iterator ite = tMap.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			directionNo[idx] = key;
			labelCount[idx] = tMap.get(key);
			idx++;
		}

		// 製品ラベル取得用ヴューを検索
		List<RepLabelProductDirection> headerList = repLabelProductDirectionDao
				.getLabelProductDirectionList(directionNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, labelCount, tantoName, currentDate);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepLabelProductDirection> headerList,
			final String[] labelCount, final String tantoName,
			final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		short sRowCnt = 0;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepLabelProductDirection bean : headerList) {
			sCol = TEMP_START_COL;

			// ラベル枚数
			builder.setCellValue(sRow, sCol++, labelCount[sRowCnt]);
			// ラベル種別（指図/実績）
			builder.setCellValue(sRow, sCol++, "指図");
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
			// 製品名
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 荷姿
			builder.setCellValue(sRow, sCol++, bean.getStyleOfPacking());
			// 他社コード
			builder.setCellValue(sRow, sCol++, bean.getOtherCompanyCd1());

			sRow++;
			sRowCnt++;
		}
	}

	/**
	 * 製品ラベル検索用Dao
	 * @param repLabelProductDirectionDao 製品ラベル検索用Dao
	 */
	public void setRepLabelProductDirectionDao(
			final RepLabelProductDirectionDao repLabelProductDirectionDao) {
		this.repLabelProductDirectionDao = repLabelProductDirectionDao;
	}
}
