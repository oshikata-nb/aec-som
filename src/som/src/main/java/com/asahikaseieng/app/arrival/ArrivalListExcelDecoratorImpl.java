/*
 * Created on 2009/06/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.arrival;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.repLabelArrival.RepLabelArrival;
import com.asahikaseieng.dao.nonentity.repLabelArrival.RepLabelArrivalDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製品・原材料ラベルＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class ArrivalListExcelDecoratorImpl implements ArrivalListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepLabelArrivalDao repLabelArrivalDao;

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
	public ArrivalListExcelDecoratorImpl() {
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
		builder.setWorkbookUrl("arrival_label");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/* ロット番号とラベル枚数をセット */
		String[] lotNo = new String[tMap.size()];
		String[] labelCount = new String[tMap.size()];

		int idx = 0;
		Iterator ite = tMap.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			lotNo[idx] = key;
			labelCount[idx] = tMap.get(key);
			idx++;
		}

		// 製品ラベル取得用ヴューを検索
		List<RepLabelArrival> headerList = repLabelArrivalDao
				.getRepLabelArrivalList(lotNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, labelCount, tantoName, currentDate);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダをセット
	 * @param headerList headerList
	 * @param labelCount labelCount
	 * @param tantoName 担当者名
	 * @param currentDate currentDate
	 */
	private void setHeader(final List<RepLabelArrival> headerList,
			final String[] labelCount, final String tantoName,
			final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		short sRowCount = 0;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepLabelArrival bean : headerList) {
			sCol = TEMP_START_COL;

			// ラベル枚数
			builder.setCellValue(sRow, sCol++, labelCount[sRowCount]);
			// ラベル種別
			builder.setCellValue(sRow, sCol++, bean.getLabelType().toString());
			// 購買NO
			builder.setCellValue(sRow, sCol++, bean.getPurchaseNo());
			// 発注番号
			builder.setCellValue(sRow, sCol++, bean.getBuySubcontractOrderNo());
			// 発注番号分納枝番
			builder.setCellValue(sRow, sCol++, bean.getOrderDivideNo());
			// 受注番号
			builder.setCellValue(sRow, sCol++, bean.getOrderNo());
			// 行番号(受注)
			if (bean.getOrderRowNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderRowNo()
						.toString());
			}
			// アスプローバオーダーコード
			builder.setCellValue(sRow, sCol++, bean.getAspOrderNo());
			// 外注原材料区分
			if (bean.getMaterialDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMaterialDivision()
						.toString());
			}
			// 仕入先受注番
			builder.setCellValue(sRow, sCol++, bean.getSiOrderNo());
			// 発注日(初回発注)
			if (bean.getOrderDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderDate());
			}
			// 担当部署コード
			builder.setCellValue(sRow, sCol++, bean.getChargeOrganizationCd());
			// 部署コード
			builder.setCellValue(sRow, sCol++, bean.getOrganizationCd());
			// 発注担当者コード
			builder.setCellValue(sRow, sCol++, bean.getTantoCd());
			// オーダー区分
			if (bean.getOrderDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderDivision()
						.toString());
			}
			// 仕入先コード
			builder.setCellValue(sRow, sCol++, bean.getVenderCd());
			// 品目コード
			builder.setCellValue(sRow, sCol++, bean.getItemCd());
			// 品目名称
			builder.setCellValue(sRow, sCol++, bean.getItemName());
			// 発注数量
			if (bean.getOrderQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderQuantity()
						.toString());
			}
			// 重量
			if (bean.getOrderConvertQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getOrderConvertQuantity().toString());
			}
			// 発注単価
			if (bean.getOrderUnitprice() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderUnitprice()
						.toString());
			}
			// 単価決定単位区分
			if (bean.getUnitpriceDefineunit() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getUnitpriceDefineunit().toString());
			}
			// 発注金額
			if (bean.getSupplierOrdAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSupplierOrdAmount()
						.toString());
			}
			// 納品希望日
			if (bean.getSuggestedDeliverlimitDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getSuggestedDeliverlimitDate());
			}
			// 発注書備考
			builder.setCellValue(sRow, sCol++, bean.getOrderSheetRemark());
			// 備考
			builder.setCellValue(sRow, sCol++, bean.getRemark());
			// 注釈
			builder.setCellValue(sRow, sCol++, bean.getNotes());
			// 納入ロケーションコード
			builder.setCellValue(sRow, sCol++, bean.getLocationCd());
			// 購買ステータス
			if (bean.getStatus() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStatus().toString());
			}
			// 発注書No
			builder.setCellValue(sRow, sCol++, bean.getOrderSheetNo());
			// 発注書発行フラグ
			if (bean.getOrderSheeFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderSheeFlag()
						.toString());
			}
			// 発注書発行日
			if (bean.getOrderSheeDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderSheeDate());
			}
			// 発注書発行者
			builder.setCellValue(sRow, sCol++, bean.getOrderSheelTantoCd());
			// 生産出来高
			if (bean.getGoodHousingSum() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getGoodHousingSum()
						.toString());
			}
			// 分納区分
			if (bean.getReplyContentsDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getReplyContentsDivision().toString());
			}
			// 完納区分
			if (bean.getDeliveryComp() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDeliveryComp()
						.toString());
			}
			// 次回納品希望日
			if (bean.getNextDeliverlimitDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getNextDeliverlimitDate());
			}
			// ﾃﾞｰﾀ種別
			if (bean.getDataType() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDataType()
						.toString());
			}
			// ﾃﾞｰﾀ集計区分
			if (bean.getDataTotalDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDataTotalDivision()
						.toString());
			}
			// 分類コード
			builder.setCellValue(sRow, sCol++, bean.getCategoryDivision());
			// 仕入日付
			if (bean.getStockingDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStockingDate());
			}
			// 勘定年月
			builder.setCellValue(sRow, sCol++, bean.getAccountYears());
			// 仕入番号
			builder.setCellValue(sRow, sCol++, bean.getSlipNo());
			// 行番号
			if (bean.getRowNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getRowNo().toString());
			}
			// 仕入-取消 仕入番号
			builder.setCellValue(sRow, sCol++, bean.getCancelSlipNo());
			// 仕入-取消 行番号
			if (bean.getCancelRowNo() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCancelRowNo()
						.toString());
			}
			// メーカーロット番号
			builder.setCellValue(sRow, sCol++, bean.getSupplierLotno());
			// 入荷ロット番号
			builder.setCellValue(sRow, sCol++, bean.getLotNo());
			// （未使用）入庫ロケーションコード(I/Fから登録)
			builder.setCellValue(sRow, sCol++, bean.getStockLocationCd());
			// 入庫ロケーションコード
			builder.setCellValue(sRow, sCol++, bean.getHousingLocationCd());
			// 入荷予定数量
			if (bean.getArrivalQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getArrivalQuantity()
						.toString());
			}
			// (未使用)入庫実績数(I/Fから登録)
			if (bean.getStockQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStockQuantity()
						.toString());
			}
			// 受入数量
			if (bean.getAcceptQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAcceptQuantity()
						.toString());
			}
			// 受入重量
			if (bean.getAcceptConvertQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getAcceptConvertQuantity().toString());
			}
			// 増付数量
			if (bean.getIncreaseQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getIncreaseQuantity()
						.toString());
			}
			// 仕入数量
			if (bean.getStockingQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStockingQuantity()
						.toString());
			}
			// 仕入単価
			if (bean.getHousingUnitprice() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getHousingUnitprice()
						.toString());
			}
			// 運賃
			if (bean.getFareAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getFareAmount()
						.toString());
			}
			// 仕入金額
			if (bean.getStockingAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getStockingAmount()
						.toString());
			}
			// 受入日付
			if (bean.getAcceptDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getAcceptDate());
			}
			// 発注書備考（入荷以降）
			builder.setCellValue(sRow, sCol++, bean.getOrderSheetRemark2());
			// 備考（入荷以降）
			builder.setCellValue(sRow, sCol++, bean.getRemark2());
			// 注釈（入荷以降）
			builder.setCellValue(sRow, sCol++, bean.getNotes2());
			// 仕入ステータス
			if (bean.getStatus2() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder
						.setCellValue(sRow, sCol++, bean.getStatus2()
								.toString());
			}
			// 消費税課税区分
			if (bean.getTaxDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTaxDivision()
						.toString());
			}
			// 消費税額
			if (bean.getTaxAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTaxAmount()
						.toString());
			}
			// 支払先コード
			builder.setCellValue(sRow, sCol++, bean.getPayeeCd());
			// 会計部門借方コード
			builder.setCellValue(sRow, sCol++, bean.getAccountDebitSectionCd());
			// 会計部門貸方コード
			builder
					.setCellValue(sRow, sCol++, bean
							.getAccountCreditSectionCd());
			// 借方科目コード
			builder.setCellValue(sRow, sCol++, bean.getDebitTitleCd());
			// 借方補助科目コード
			builder.setCellValue(sRow, sCol++, bean.getDebitSubTitleCd());
			// 貸方科目コード
			builder.setCellValue(sRow, sCol++, bean.getCreditTitleCd());
			// 貸方補助科目コード
			builder.setCellValue(sRow, sCol++, bean.getCreditSubTitleCd());
			// 買掛対象
			if (bean.getPayableTargetDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getPayableTargetDivision().toString());
			}
			// 支払対象
			if (bean.getPaymentTargetDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getPaymentTargetDivision().toString());
			}
			// 買掛更新フラグ
			if (bean.getPayableUpdateDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getPayableUpdateDivision().toString());
			}
			// 買掛番号
			builder.setCellValue(sRow, sCol++, bean.getPayableNo());
			// 支払更新フラグ
			if (bean.getPaymentUpdateDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getPaymentUpdateDivision().toString());
			}
			// 支払番号
			builder.setCellValue(sRow, sCol++, bean.getPaymentNo());
			// 摘要コード
			builder.setCellValue(sRow, sCol++, bean.getSummaryCd());
			// 摘要名
			builder.setCellValue(sRow, sCol++, bean.getSummary());
			// 支払締め日
			if (bean.getPaymentUpdateDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPaymentUpdateDate());
			}
			// 買掛締め日
			if (bean.getPayableUpdateDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPayableUpdateDate());
			}
			// データ転送日時
			if (bean.getTransmissionDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTransmissionDate());
			}
			// ラベル発行フラグ
			if (bean.getLabelFlag() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLabelFlag()
						.toString());
			}
			// ラベル発行日
			if (bean.getLabelDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLabelDate());
			}
			// ラベル発行者
			builder.setCellValue(sRow, sCol++, bean.getLabelTantoCd());
			// （未使用）仮単価FLG
			if (bean.getTmpUnitpriceFlg() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTmpUnitpriceFlg()
						.toString());
			}
			// （未使用）検査方法１
			if (bean.getInspectMethod() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getInspectMethod()
						.toString());
			}
			// （未使用）引当明細FLG
			if (bean.getMortgageDetailFlg() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getMortgageDetailFlg()
						.toString());
			}
			// （未使用）検収待ち数
			if (bean.getInspreceiptWaitQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getInspreceiptWaitQuantity().toString());
			}
			// （未使用）不良数１
			if (bean.getBadQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getBadQuantity()
						.toString());
			}
			// （未使用）原価項目
			if (bean.getCostEntry() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCostEntry()
						.toString());
			}
			// （未使用）内示・確定区分
			if (bean.getAdvPurNoticeDecideDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getAdvPurNoticeDecideDivision().toString());
			}
			// （未使用）オーダー引当済数
			if (bean.getOrderMortgagedQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getOrderMortgagedQuantity().toString());
			}
			// （未使用）累計入庫数換算量
			if (bean.getSumHousingConvertQuant() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getSumHousingConvertQuant().toString());
			}
			// （未使用）検査待数換算量
			if (bean.getInspectWaitConvertQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getInspectWaitConvertQuantity().toString());
			}
			// （未使用）検収抜取数
			if (bean.getExtractionsQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getExtractionsQuantity().toString());
			}
			// （未使用）不良数２
			if (bean.getDefectiveQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getDefectiveQuantity()
						.toString());
			}
			// （未使用）検収担当者コード
			builder.setCellValue(sRow, sCol++, bean.getCheckTantoCd());
			// （未使用）検査方法２
			if (bean.getLaboratoryMethod() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getLaboratoryMethod()
						.toString());
			}
			// （未使用）支給区分
			if (bean.getProvisionDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getProvisionDivision()
						.toString());
			}
			// （未使用）検収日付
			if (bean.getCheckDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCheckDate()
						.toString());
			}
			// （未使用）消費税率
			if (bean.getTaxRatio() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTaxRatio()
						.toString());
			}
			// （未使用）検収数
			if (bean.getCheckQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getCheckQuantity()
						.toString());
			}
			// （未使用）伝票発行済み区分
			if (bean.getSlipIssueDivision() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSlipIssueDivision()
						.toString());
			}
			// （未使用）伝票発行日
			if (bean.getSlipIssueDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSlipIssueDate());
			}
			// 承認ステータス
			if (bean.getApprovalStatus() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getApprovalStatus()
						.toString());
			}
			// 承認者
			builder.setCellValue(sRow, sCol++, bean.getApprovedby());
			// 承認日
			if (bean.getApprovaldate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getApprovaldate());
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
			// 品目名
			builder.setCellValue(sRow, sCol++, bean.getItemItemName());
			// 荷姿
			builder.setCellValue(sRow, sCol++, bean.getStyleOfPacking());
			// 他社コード
			builder.setCellValue(sRow, sCol++, bean.getOtherCompanyCd1());

			sRow++;
			sRowCount++;
		}
	}

	/**
	 * 製品・原材料ラベル検索用Dao
	 * @param repLabelArrivalDao 製品・原材料ラベル検索用Dao
	 */
	public void setRepLabelArrivalDao(
			final RepLabelArrivalDao repLabelArrivalDao) {
		this.repLabelArrivalDao = repLabelArrivalDao;
	}
}
