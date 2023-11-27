/*
 * Created on 2009/06/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.buying;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.dao.nonentity.repBuyingOrderDetail.RepBuyingOrderDetail;
import com.asahikaseieng.dao.nonentity.repBuyingOrderDetail.RepBuyingOrderDetailDao;
import com.asahikaseieng.dao.nonentity.repBuyingOrderHeader.RepBuyingOrderHeader;
import com.asahikaseieng.dao.nonentity.repBuyingOrderHeader.RepBuyingOrderHeaderDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 仕入伝票ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class BuyingListExcelDecoratorImpl implements BuyingListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepBuyingOrderHeaderDao repBuyingOrderHeaderDao;

	private RepBuyingOrderDetailDao repBuyingOrderDetailDao;

	/** 軽減措置割合取得用Dao */
	private NamesDetailDao namesDetailDao;

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
	public BuyingListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final ArrayList<String> buyingNo,
			final String tantoName, final Timestamp currentDate,
			final String stockingDateFrom, final String stockingDateTo,
			final HttpServletRequest request) {

		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("buying_order");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 仕入伝票ヘッダ取得用ヴューを検索
		List<RepBuyingOrderHeader> headerList = repBuyingOrderHeaderDao
				.getBuyingOrderHeaderList(buyingNo);

		// 仕入伝票明細取得用ヴューを検索
		List<RepBuyingOrderDetail> detailList = repBuyingOrderDetailDao
				.getBuyingOrderDetailList(buyingNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, tantoName, currentDate, stockingDateFrom,
			stockingDateTo);

		/* 明細をセット */
		setDetail(detailList, check);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RepBuyingOrderHeader> headerList,
			final String tantoName, final Timestamp currentDate,
			final String stockingDateFrom, final String stockingDateTo) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		for (RepBuyingOrderHeader bean : headerList) {
			sCol = TEMP_START_COL;

			// キー（仕入先+支払先+借方科目+貸方科目+借方部門+貸方部門・・・集約用）
			builder.setCellValue(sRow, sCol++, bean.getKey());
			// 仕入先コード
			builder.setCellValue(sRow, sCol++, bean.getVenderCd());
			// 仕入先名称
			builder.setCellValue(sRow, sCol++, bean.getVenderName());
			// 支払先コード
			builder.setCellValue(sRow, sCol++, bean.getPayeeCd());
			// 支払先名称
			builder.setCellValue(sRow, sCol++, bean.getPayeeName());
			// 借方 科目コード
			builder.setCellValue(sRow, sCol++, bean.getDebitTitleCd());
			// 借方 科目名
			builder.setCellValue(sRow, sCol++, bean.getDebitTitleName());
			// 貸方 科目コード
			builder.setCellValue(sRow, sCol++, bean.getCreditTitleCd());
			// 貸方 科目名
			builder.setCellValue(sRow, sCol++, bean.getCreditTitleName());
			// 借方 部門コード
			builder.setCellValue(sRow, sCol++, bean.getAccountDebitSectionCd());
			// 借方 部門名
			builder.setCellValue(sRow, sCol++, bean
					.getAccountDebitSectionName());
			// 貸方 部門コード
			builder
					.setCellValue(sRow, sCol++, bean
							.getAccountCreditSectionCd());
			// 貸方 部門名
			builder.setCellValue(sRow, sCol++, bean
					.getAccountCreditSectionName());
			// ログイン者
			builder.setCellValue(sRow, sCol++, tantoName);
			// 出力日
			builder.setCellValue(sRow, sCol++, outputDate);

			// 抽出条件 仕入日From
			builder.setCellValue(sRow, sCol++, stockingDateFrom);
			// 抽出条件 仕入日To
			builder.setCellValue(sRow, sCol++, stockingDateTo);

			sRow++;
		}
	}

	/**
	 * 詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepBuyingOrderDetail> detailList, CheckDigitUtilsLogic check) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepBuyingOrderDetail bean : detailList) {
			sCol = TEMP_START_COL;

			BigDecimal stockAmount = BigDecimal.ZERO;  		// 金額(帳票の金額計算用)
			BigDecimal taxAmount = BigDecimal.ZERO;			// 消費税(帳票の消費税計算用)
			BigDecimal taxRatio = bean.getTaxRatio();		// 消費税率
			String remark2 = bean.getRemark2();				// 摘要

			// 金額 = 数量 * 単価
			stockAmount = AecNumberUtils.convertNullToZero(bean.getSumStockingQuantity()).multiply(AecNumberUtils.convertNullToZero(bean.getHousingUnitprice()));
			String strStockAmount = check.format("SIKINGAKU", "SI", bean.getVenderCd(), stockAmount);
			// 消費税 = 金額 * 税率
			taxAmount = stockAmount.multiply(AecNumberUtils.convertNullToZero(taxRatio).divide(BigDecimal.valueOf(100)));
			String strTaxAmount = check.format("TAX_AMOUNT", "SI", bean.getVenderCd(), taxAmount);

			// 帳票の表示用「金額(合計)」
			String strSumStockAmount = check.format("SIKINGAKU", "SI", bean.getVenderCd(), stockAmount);
			// 帳票の表示用「消費税(合計)」
			String strSumTaxAmount = check.format("TAX_AMOUNT", "SI", bean.getVenderCd(), taxAmount);

			// 軽減措置金額(帳票の合計金額計算用)
			String reducedStockAmount = check.format("SIKINGAKU", "SI", bean.getVenderCd(), bean.getStockingAmount());
			// 軽減措置消費税額(帳票の合計消費税計算用)
			String reducedTaxAmount = check.format("TAX_AMOUNT", "SI", bean.getVenderCd(), bean.getTaxAmount());

			/* 未申請事業者の設定 */
			if(StringUtils.equals(bean.getReducedTaxTargetFlg(), "1")) {

				// 金額と免税金額の間で改行する
				String newline = System.getProperty("line.separator");

				// 帳票の表示用に文字列結合
				strSumStockAmount = strSumStockAmount + newline + "(" + check.format("SIKINGAKU", "SI", bean.getVenderCd(), bean.getStockingAmount()) + ")";
				strSumTaxAmount = strSumTaxAmount + newline + "(" + check.format("TAX_AMOUNT", "SI", bean.getVenderCd(), bean.getTaxAmount()) + ")";

				// nullの場合空文字を代入
				if(StringUtils.isEmpty(remark2)) {
					remark2 = "";
				}

				// 時間も保持しているので日付に変換
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

				if(bean.getStockingDate() != null){
					// 仕入日をみて控除割合を取得
					NamesDetail reducedTaxRatio = namesDetailDao.getTaxFreeRatio(sdf.format(bean.getStockingDate()).toString());

					BigDecimal reducedTaxRatioQty = AecNumberUtils.convertNullToZero(reducedTaxRatio.getNmqty01());

					// 割合によって摘要に表示する内容を変える
					if(!StringUtils.equals(reducedTaxRatioQty.toString(), "0")) {
						remark2 =  AecNumberUtils.decimalFormat(reducedTaxRatioQty.multiply(BigDecimal.valueOf(100)), "###").toString() + "%控除" + newline + remark2;
					}
				}
			}

			// キー（仕入先+支払先+借方科目+貸方科目+借方部門+貸方部門・・・集約用）
			builder.setCellValue(sRow, sCol++, bean.getKey());
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
			// 発注日
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
			// 発注数量（個数）
			if (bean.getOrderQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getOrderQuantity()
						.toString());
			}
			// 重量（数量）
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
			builder.setCellValue(sRow, sCol++, "");
			// 納品希望日（納入指定日）
			if (bean.getSuggestedDeliverlimitDate() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getSuggestedDeliverlimitDate());
			}
			// 発注書備考（備考）
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
				builder.setCellValue(sRow, sCol++, strStockAmount);
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
			builder.setCellValue(sRow, sCol++, remark2);
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
				builder.setCellValue(sRow, sCol++, strTaxAmount);
			}
			// 税コード
			if (bean.getTaxCd() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getTaxCd());
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
			// ASPROVA発注重量
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
			// 受入数量(合計)
			if (bean.getSumAcceptQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumAcceptQuantity()
						.toString());
			}
			// 仕入数量(合計)
			if (bean.getSumStockingQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean
						.getSumStockingQuantity().toString());
			}
			// 入庫数(合計)
			if (bean.getSumInQuantity() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumInQuantity()
						.toString());
			}
			// 金額(合計)
			if (bean.getSumStockingAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, strSumStockAmount);
			}
			// 消費税(合計)
			if (bean.getSumTaxAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, strSumTaxAmount);
			}
			// 合計(合計)
			if (bean.getSumAmount() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getSumAmount()
						.toString());
			}
			// 単価
			if (bean.getPurchasePrice() == null) {
				builder.setCellValue(sRow, sCol++, "");
			} else {
				builder.setCellValue(sRow, sCol++, bean.getPurchasePrice()
						.toString());
			}
			// 品名
			builder.setCellValue(sRow, sCol++, bean.getItemItemName());
			// 荷姿
			builder.setCellValue(sRow, sCol++, bean.getStyleOfPacking());
			// 単位
			builder.setCellValue(sRow, sCol++, bean.getUnit());

			// 軽減措置金額
			builder.setCellValue(sRow, sCol++, reducedStockAmount);
			// 軽減措置消費税金額
			builder.setCellValue(sRow, sCol++, reducedTaxAmount);
			// 軽減措置計算フラグ
			builder.setCellValue(sRow, sCol++, bean.getReducedTaxTargetFlg());

			sRow++;

		}
	}

	/**
	 * 仕入伝票詳細検索用Dao
	 * @param repBuyingOrderDetailDao 仕入伝票ヘッダ検索用Dao
	 */
	public final void setRepBuyingOrderDetailDao(
			final RepBuyingOrderDetailDao repBuyingOrderDetailDao) {
		this.repBuyingOrderDetailDao = repBuyingOrderDetailDao;
	}

	/**
	 * 仕入伝票ヘッダ検索用Dao
	 * @param repBuyingOrderHeaderDao 発注書ヘッダ検索用Dao
	 */
	public final void setRepBuyingOrderHeaderDao(
			final RepBuyingOrderHeaderDao repBuyingOrderHeaderDao) {
		this.repBuyingOrderHeaderDao = repBuyingOrderHeaderDao;
	}

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}
}
