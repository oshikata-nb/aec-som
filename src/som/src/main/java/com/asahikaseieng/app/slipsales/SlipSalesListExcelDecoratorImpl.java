/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipsales;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.salesextended.SalesExtended;
import com.asahikaseieng.dao.entity.salesextended.SalesExtendedDao;
import com.asahikaseieng.dao.nonentity.master.taxmasterlist.TaxMasterListDao;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipDetailListForReport;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipHeadListForReport;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipHeadListForReportDao;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipTaxRatioListForReport;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipTaxRatioListForReportDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 売上伝票ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class SlipSalesListExcelDecoratorImpl implements
		SlipSalesListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepSalesSlipHeadListForReportDao repSalesSlipHeadListForReportDao;

	private RepSalesSlipDetailListForReportDao repSalesSlipDetailListForReportDao;

	private RepSalesSlipTaxRatioListForReportDao repSalesSlipTaxRatioListForReportDao;

	private SalesExtendedDao salesExtendedDao;

	private TaxMasterListDao taxMasterListDao;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_DIVISION_ROW = 0;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_DIVISION_COL = 1;

	/** 売上伝票ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 売上伝票詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/** 売上番号毎、税率毎データシート名 */
	protected static final String TAX_RATIO_DATA_SHEET_NAME = "tax_ratio_data";

	private VenderDao venderDao;

	private BalanceDao balanceDao;

	private LoginDao loginDao;

	/**
	 * コンストラクタ
	 */
	public SlipSalesListExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final ArrayList<String> slipSalesNo,
			final ArrayList<String> salesNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress,
			final String division) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		// 仕入伝票の場合のみ処理を行う
		if (division.equals("PRINT_OLD")) {
			// 古いテンプレートで出力する
			builder.setWorkbookUrl("slip_sales_old");
		} else {
			// 新しいテンプレートで出力する
			builder.setWorkbookUrl("slip_sales");
		}

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 売上伝票ヘッダデータを取得
		List<RepSalesSlipHeadListForReport> headerList = repSalesSlipHeadListForReportDao
				.getHeadList(slipSalesNo);

		// 売上伝票詳細データを取得
		List<RepSalesSlipDetailListForReport> detailList = repSalesSlipDetailListForReportDao
				.getDetailList(slipSalesNo, salesNo);

		// 売上伝票毎、税率毎のデータを取得
		List<RepSalesSlipTaxRatioListForReport> taxRatioList = repSalesSlipTaxRatioListForReportDao
				.getTaxRatioList(slipSalesNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, tantoName, currentDate, division);

		/* 明細をセット */
		setDetail(detailList, tantoName, division);

		/* 売上番号毎の税率をセット */
		setByTaxRatioData(taxRatioList);

		//設定シートのマクロ実行フラグを1:実行するに変更
		builder.setSheet("setting");
		builder.setExcelDataBigDecimal((short)1, (short)0, BigDecimal.ONE);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final ArrayList<String> slipSalesNo,
			final ArrayList<String> salesNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress,
			final String division, BigDecimal seq) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		// 仕入伝票の場合のみ処理を行う
		if (division.equals("PRINT_OLD")) {
			// 古いテンプレートで出力する
			builder.setWorkbookUrl("slip_sales_old");
		} else {
			// 新しいテンプレートで出力する
			builder.setWorkbookUrl("slip_sales");
		}

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 売上伝票ヘッダデータを取得
		List<RepSalesSlipHeadListForReport> headerList = repSalesSlipHeadListForReportDao
				.getHeadList(slipSalesNo);

		// 売上伝票詳細データを取得
		List<RepSalesSlipDetailListForReport> detailList = repSalesSlipDetailListForReportDao
				.getDetailList(slipSalesNo, salesNo);

		// 売上伝票毎、税率毎のデータを取得
		List<RepSalesSlipTaxRatioListForReport> taxRatioList = repSalesSlipTaxRatioListForReportDao
				.getTaxRatioList(slipSalesNo);

		/* ヘッダ情報をセット */
		setHeader(headerList, tantoName, currentDate, division);

		/* 明細をセット */
		setDetail(detailList, tantoName, division);

		/* 売上番号毎の税率をセット */
		setByTaxRatioData(taxRatioList);

		//設定シートのマクロ実行フラグを1:実行するに変更
		builder.setSheet("setting");
		builder.setExcelDataBigDecimal((short)1, (short)0, BigDecimal.ONE);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder.createPdfExcel(seq));
	}

	/**
	 * 売上伝票ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(
			final List<RepSalesSlipHeadListForReport> headerList,
			final String tantoName, final Timestamp currentDate,
			final String division) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}
		setExcelDataString(TEMP_START_DIVISION_ROW, TEMP_START_DIVISION_COL,
			division);

		for (RepSalesSlipHeadListForReport bean : headerList) {
			sCol = TEMP_START_COL;
			setExcelDataString(sRow, sCol++, bean.getSalesSlipNo());
			setExcelDataString(sRow, sCol++, bean.getSalesDate());
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			setExcelDataString(sRow, sCol++, bean.getVenderName1());
			setExcelDataString(sRow, sCol++, bean.getVenderName2());
			setExcelDataString(sRow, sCol++, bean.getVenderTantoCd());
			setExcelDataString(sRow, sCol++, bean.getVenderTantoName());
			setExcelDataString(sRow, sCol++, bean.getOrganizationName());
			setExcelDataString(sRow, sCol++, bean.getOrganizationAddress1());
			setExcelDataString(sRow, sCol++, bean.getOrganizationAddress2());
			setExcelDataString(sRow, sCol++, bean.getOrganizationAddress3());
			setExcelDataString(sRow, sCol++, bean.getOrganizationAddressAll());
			setExcelDataString(sRow, sCol++, bean.getOrganizationTelNo());
			setExcelDataString(sRow, sCol++, bean.getOrganizationZipcodeNo());
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			setExcelDataString(sRow, sCol++, bean.getDeliveryName1());
			setExcelDataString(sRow, sCol++, bean.getDeliveryName2());
			setExcelDataString(sRow, sCol++, bean.getDeliveryNameAll());
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress1());
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress2());
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress3());
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddressAll());
			setExcelDataString(sRow, sCol++, bean.getDeliveryTelNo());
			setExcelDataString(sRow, sCol++, bean.getDeliveryTantoCd());
			setExcelDataString(sRow, sCol++, bean.getDeliveryTantoName());
			setExcelDataString(sRow, sCol++, bean.getCategoryDivision());
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			setExcelDataString(sRow, sCol++, bean.getCustomerOrderNo());
			setExcelDataString(sRow, sCol++, bean.getBalanceCd());
			setExcelDataString(sRow, sCol++, bean.getDeliveryAddress());
			setExcelDataString(sRow, sCol++, bean.getDeliverySlipSummery());
			setExcelDataString(sRow, sCol++, bean.getAccountYears());
			setExcelDataString(sRow, sCol++, bean.getUpperLocationCd());
			setExcelDataString(sRow, sCol++, bean.getLocationName());
			setExcelDataString(sRow, sCol++, bean.getCarryCd());
			setExcelDataString(sRow, sCol++, bean.getCarryName1());
			setExcelDataString(sRow, sCol++, bean.getCarryName2());
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedDate());
			setExcelDataString(sRow, sCol++, bean.getDeliveryExpectedTime());

			Vender vender = venderDao.getEntity(bean.getVenderCd(), "TS");
			Vender invoiced = new Vender();
			String paymentInvoiceCd = new String();
			if (vender != null) {

				if (vender.getPaymentInvoiceCd() != null) {

					// 請求先のデータを取得
					invoiced = venderDao.getEntity(paymentInvoiceCd, "TS");
					// 請求先コードがマスタにない場合
					if (invoiced == null) {
						invoiced = vender;
					}
				} else {
					invoiced = vender;
				}

				// 請求先名称
				setExcelDataString(sRow, sCol++, getVenderName(invoiced));

				// 請求先住所
				setExcelDataString(sRow, sCol++, getVenderAddress(invoiced));

				// 請求先客先所感１
				setExcelDataString(sRow, sCol++, invoiced
						.getCustomerImpression1());

				// 得意先がマスタにない場合
			} else {
				// 請求先名称
				setExcelDataString(sRow, sCol++, null);

				// 請求先住所
				setExcelDataString(sRow, sCol++, null);

				// 請求先客先所感１
				setExcelDataString(sRow, sCol++, null);
			}

			// 得意先２
			setExcelDataString(sRow, sCol++, getVenderName2(
				bean.getBalanceCd(), bean.getDeliveryCd()));

			// 担当者コード
			setExcelDataString(sRow, sCol++, tantoName);

			// 担当者名称
			Login loginBean = loginDao.getEntity(tantoName);

			if (loginBean != null) {
				setExcelDataString(sRow, sCol++, loginBean.getTantoNm());
			} else {
				setExcelDataString(sRow, sCol++, null);
			}

			// 受注担当者コード
			setExcelDataString(sRow, sCol++, bean.getOrderTantoCd());
			// 受注担当者名称
			setExcelDataString(sRow, sCol++, bean.getOrderTantoName());

			// 2015/9/7 自動FAX対応
			// 出力区分(SEND:売上伝票 PRINT:仕入伝票)
			// 得意先コード２
			setExcelDataString(sRow, sCol++, getVenderCd2(bean.getBalanceCd(),
				bean.getDeliveryCd()));

			setExcelDataString(sRow, sCol++, division);

			// FAX番号
			setExcelDataString(sRow, sCol++, bean.getFaxNo());
			// 送信区分
			setExcelDataString(sRow, sCol++, bean.getFaxOutput());

			// インボイス登録番号
			setExcelDataString(sRow, sCol++, bean.getOrganizationInvoiceNo());
			// 新テンプレートフラグ
			setExcelDataString(sRow, sCol++, bean.getTempNewFlg());
			// 売上伝票フラグ
			setExcelDataString(sRow, sCol++, bean.getTsInvoiceFlg());

			sRow++;
		}
	}

	/**
	 * 得意先の名称を返す
	 * @param bean 得意先データ
	 */
	private String getVenderName(final Vender bean) {

		String ret = "";

		if (bean == null) {
			return ret;
		}

		if (bean.getVenderName1() != null) {
			ret = bean.getVenderName1();
		}

		if (bean.getVenderName2() != null) {
			ret += " " + bean.getVenderName2();
		}

		if (!ret.equals("")) {
			ret += " 様";
		}

		return ret;
	}

	/**
	 * 得意先の住所を返す
	 * @param bean 得意先データ
	 */
	private String getVenderAddress(final Vender bean) {

		String ret = "";

		if (bean.getAddress1() != null) {
			ret = " " + bean.getAddress1();
		}

		if (bean.getAddress2() != null) {
			ret += " " + bean.getAddress2();
		}

		if (bean.getAddress3() != null) {
			ret += " " + bean.getAddress3();
		}

		return ret;
	}

	/**
	 * 得意先２の名称を返す
	 * @param balanceCd 帳合コード
	 * @param deliverCd 納入先コード
	 */
	private String getVenderName2(final String balanceCd, final String deliverCd) {

		String ret = null;

		// 帳合コードがない場合
		if (balanceCd == null) {
			return ret;
		}

		// 帳合コードを帳合マスタから検索
		Balance balance = balanceDao.getEntity(balanceCd);

		// 帳合マスタに帳合コードがない場合
		if (balance == null) {
			return ret;
		}

		while (true) {

			// 得意先と納入先が同一の場合
			if (balance.getVenderCd().equals(deliverCd)) {

				// 上位帳合がある場合
				if (balance.getUpperBalanceCd() != null) {
					Balance upperBalance = balanceDao.getEntity(balance
							.getUpperBalanceCd());

					// 上位帳合がある場合上位帳合の得意先名称
					if (upperBalance != null) {
						// Vender vender = venderDao.getEntity(upperBalance
						// .getVenderCd(), "TS");
						// ret = getVenderName(vender);
						balance = upperBalance;

						// 上位帳合がない場合帳合コードの得意先名称
					} else {
						Vender vender = venderDao.getEntity(balance
								.getVenderCd(), "TS");
						ret = getVenderName(vender);
						return ret;
					}

				} else {
					Vender vender = venderDao.getEntity(balance.getVenderCd(),
						"TS");
					ret = getVenderName(vender);
					return ret;
				}
			} else {
				Vender vender = venderDao
						.getEntity(balance.getVenderCd(), "TS");
				ret = getVenderName(vender);
				return ret;
			}
		}

	}

	/**
	 * 得意先２の名称を返す
	 * @param balanceCd 帳合コード
	 * @param deliverCd 納入先コード
	 */
	private String getVenderCd2(final String balanceCd, final String deliverCd) {

		String ret = null;

		// 帳合コードがない場合
		if (balanceCd == null) {
			return ret;
		}

		// 帳合コードを帳合マスタから検索
		Balance balance = balanceDao.getEntity(balanceCd);

		// 帳合マスタに帳合コードがない場合
		if (balance == null) {
			return ret;
		}

		while (true) {

			// 得意先と納入先が同一の場合
			if (balance.getVenderCd().equals(deliverCd)) {

				// 上位帳合がある場合
				if (balance.getUpperBalanceCd() != null) {
					Balance upperBalance = balanceDao.getEntity(balance
							.getUpperBalanceCd());

					// 上位帳合がある場合上位帳合の得意先名称
					if (upperBalance != null) {
						// Vender vender = venderDao.getEntity(upperBalance
						// .getVenderCd(), "TS");
						// ret = getVenderName(vender);
						balance = upperBalance;

						// 上位帳合がない場合帳合コードの得意先名称
					} else {
						return balance.getVenderCd();
					}

				} else {
					return balance.getVenderCd();
				}
			} else {
				return balance.getVenderCd();
			}
		}

	}

	/**
	 * 売上伝票詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(
			final List<RepSalesSlipDetailListForReport> detailList,
			final String tantoName, final String division) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepSalesSlipDetailListForReport bean : detailList) {
			sCol = TEMP_START_COL;

			setExcelDataString(sRow, sCol++, bean.getSalesSlipNo());
			setExcelDataString(sRow, sCol++, bean.getSalesSlipRowNo());
			setExcelDataString(sRow, sCol++, bean.getSalesDate());
			setExcelDataString(sRow, sCol++, bean.getSalesNo());
			setExcelDataString(sRow, sCol++, bean.getCancelSalesNo());
			setExcelDataString(sRow, sCol++, bean.getItemCd());
			setExcelDataString(sRow, sCol++, bean.getItemName());
			setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			setExcelDataString(sRow, sCol++, bean.getNumberOfInsertions());
			setExcelDataString(sRow, sCol++, bean.getJanCd());
			setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			setExcelDataString(sRow, sCol++, bean.getBalanceCd());
			setExcelDataString(sRow, sCol++, bean.getInputTantoCd());
			setExcelDataString(sRow, sCol++, bean.getSalesTantoCd());
			setExcelDataString(sRow, sCol++, bean.getProductLotno());
			setExcelDataString(sRow, sCol++, bean.getOrderNo());
			setExcelDataString(sRow, sCol++, bean.getOrderRowNo());
			setExcelDataString(sRow, sCol++, bean.getSalesQuantity());
			setExcelDataString(sRow, sCol++, bean.getQtyIns());
			setExcelDataString(sRow, sCol++, bean.getMatss());
			setExcelDataString(sRow, sCol++, bean.getMatssIns());
			setExcelDataString(sRow, sCol++, bean.getQtySum());
			setExcelDataString(sRow, sCol++, bean.getQtySumIns());
			setExcelDataString(sRow, sCol++, bean.getSalesUnitprice());
			setExcelDataString(sRow, sCol++, bean.getStandardUnitprice());
			setExcelDataString(sRow, sCol++, bean.getStandardDiscount());
			setExcelDataString(sRow, sCol++, bean.getSpecialDiscount());
			setExcelDataString(sRow, sCol++, bean.getSalesDiscount());
			setExcelDataString(sRow, sCol++, bean.getSalesPrice());
			setExcelDataString(sRow, sCol++, bean.getTmpUnitpriceFlg());

			// 仕入伝票と売上伝票で処理を分岐
			if (division.equals("PRINT_OLD")) {
				setExcelDataString(sRow, sCol++, bean.getRemark());
			} else {
				if (bean.getOtherCompanyCd1() == null
						|| bean.getOtherCompanyCd1().equals("")) {
					setExcelDataString(sRow, sCol++, bean.getRemark());
				} else {
					setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1()
							+ " " + bean.getRemark());
				}
			}

			setExcelDataString(sRow, sCol++, bean.getDisbursementLocationCd());
			setExcelDataString(sRow, sCol++, bean.getDeliveryComp());
			setExcelDataString(sRow, sCol++, bean.getDeliveryUpdateDate());
			setExcelDataString(sRow, sCol++, bean.getInvoiceUpdateDate());
			setExcelDataString(sRow, sCol++, bean.getInputDivision());
			setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			setExcelDataString(sRow, sCol++, bean.getTaxDivision());
			setExcelDataString(sRow, sCol++, bean.getTaxAmount());
			setExcelDataString(sRow, sCol++, bean.getTaxCd());
			setExcelDataString(sRow, sCol++, bean.getInvoiceCd());
			setExcelDataString(sRow, sCol++, bean.getSalesAmount());
			setExcelDataString(sRow, sCol++, bean.getSalesBasic());
			setExcelDataString(sRow, sCol++, bean.getTaxRatio());
			setExcelDataString(sRow, sCol++, bean.getUpdateFlg());
			setExcelDataString(sRow, sCol++, bean.getConfigExpRatio());
			setExcelDataString(sRow, sCol++, bean
					.getRatioNotapplySalesUnitprice());
			setExcelDataString(sRow, sCol++, bean.getShippingNo());
			setExcelDataString(sRow, sCol++, bean.getSlipPublishComp());
			setExcelDataString(sRow, sCol++, bean.getSlipPublishDate());
			setExcelDataString(sRow, sCol++, bean.getTransmissionDate());
			setExcelDataString(sRow, sCol++, bean.getLedgerPostDate());
			setExcelDataString(sRow, sCol++, bean.getLedgerPostNo());
			setExcelDataString(sRow, sCol++, bean.getSalesConvertQuantity());
			setExcelDataString(sRow, sCol++, bean.getAcceptConvertQuantity());
			setExcelDataString(sRow, sCol++, bean.getDataType());
			setExcelDataString(sRow, sCol++, bean.getDataTotalDivision());
			setExcelDataString(sRow, sCol++, bean.getCategoryDivision());
			setExcelDataString(sRow, sCol++, bean.getAccountYears());
			setExcelDataString(sRow, sCol++, bean.getAccountDebitSectionCd());
			setExcelDataString(sRow, sCol++, bean.getAccountCreditSectionCd());
			setExcelDataString(sRow, sCol++, bean.getDebitTitleCd());
			setExcelDataString(sRow, sCol++, bean.getDebitSubTitleCd());
			setExcelDataString(sRow, sCol++, bean.getCreditTitleCd());
			setExcelDataString(sRow, sCol++, bean.getCreditSubTitleCd());
			setExcelDataString(sRow, sCol++, bean.getDepositTargetDivision());
			setExcelDataString(sRow, sCol++, bean.getClaimTargetDivision());
			setExcelDataString(sRow, sCol++, bean.getSummaryCd());
			setExcelDataString(sRow, sCol++, bean.getSummary());
			setExcelDataString(sRow, sCol++, bean.getDepositUpDivision());
			setExcelDataString(sRow, sCol++, bean.getDepositNo());
			setExcelDataString(sRow, sCol++, bean.getClaimUpDivision());
			setExcelDataString(sRow, sCol++, bean.getClaimNo());
			setExcelDataString(sRow, sCol++, bean.getEraserCompleteDivision());
			setExcelDataString(sRow, sCol++, bean.getEraserCompleteDate());
			setExcelDataString(sRow, sCol++, bean.getApprovalStatus());
			setExcelDataString(sRow, sCol++, bean.getApprovedby());
			setExcelDataString(sRow, sCol++, bean.getApprovaldate());
			setExcelDataString(sRow, sCol++, bean.getChargeOrganizationCd());
			setExcelDataString(sRow, sCol++, bean.getKeepFlag());
			setExcelDataString(sRow, sCol++, bean.getRyCd());
			setExcelDataString(sRow, sCol++, bean.getHousingLocationCd());
			setExcelDataString(sRow, sCol++, bean.getPackageDirectionNo());
			setExcelDataString(sRow, sCol++, bean.getInputDate());
			setExcelDataString(sRow, sCol++, bean.getInputorCd());
			setExcelDataString(sRow, sCol++, bean.getUpdateDate());
			setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			sRow++;

			// 仕入伝票の場合のみ処理を行う
			if (division.equals("SEND")) {

				SalesExtended salesExtended = salesExtendedDao.getEntity(bean
						.getSalesNo());

				if (salesExtended == null) {
					SalesExtended newBean = new SalesExtended();
					newBean.setSalesNo(bean.getSalesNo());
					newBean.setSlipSendComp(BigDecimal.ONE);
					newBean.setSlipSendDate(AecDateUtils.getCurrentTimestamp());
					newBean.setInputDate(AecDateUtils.getCurrentTimestamp());
					newBean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
					newBean.setUpdatorCd(tantoName);
					newBean.setInputorCd(tantoName);
					salesExtendedDao.insert(newBean);
				} else {
					salesExtended.setSlipSendComp(BigDecimal.ONE);
					salesExtended.setSlipSendDate(AecDateUtils
							.getCurrentTimestamp());
					salesExtended.setUpdatorCd(tantoName);
					salesExtendedDao.update(salesExtended);
				}

			}

		}
	}

	/**
	 * 売上番号毎に税率をセット
	 * @param taxRatioList
	 */
	private void setByTaxRatioData(List<RepSalesSlipTaxRatioListForReport> taxRatioList){
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(TAX_RATIO_DATA_SHEET_NAME);

		for (RepSalesSlipTaxRatioListForReport bean : taxRatioList) {
			sCol = TEMP_START_COL;

			setExcelDataString(sRow, sCol++, bean.getSalesSlipNo());
			setExcelDataString(sRow, sCol++, bean.getVenderCd());
			setExcelDataString(sRow, sCol++, bean.getTaxRatio());
			setExcelDataString(sRow, sCol++, bean.getSumSalesAmount());
			setExcelDataString(sRow, sCol++, bean.getSumTaxAmount());

			sRow++;
		}
	}

	/**
	 * 行、列を指定してExcelシートにデータを貼り付け
	 * @param tantoName 担当者名
	 */
	private void setExcelDataString(final short sRow, final short sCol,
			final String value) {

		// nullでは無い場合のみデータをセット
		if (value != null) {
			// データをセット
			builder.setCellValue(sRow, sCol, value);
		}
	}

	/**
	 * 売上伝票ヘッダ用Daoを設定します。
	 * @param repSalesSlipDetailListForReportDao 売上伝票ヘッダ用Dao
	 */
	public void setRepSalesSlipDetailListForReportDao(
			final RepSalesSlipDetailListForReportDao repSalesSlipDetailListForReportDao) {
		this.repSalesSlipDetailListForReportDao = repSalesSlipDetailListForReportDao;
	}

	/**
	 * 売上伝票詳細用Daoを設定します。
	 * @param repSalesSlipHeadListForReportDao 売上伝票詳細用Dao
	 */
	public void setRepSalesSlipHeadListForReportDao(
			final RepSalesSlipHeadListForReportDao repSalesSlipHeadListForReportDao) {
		this.repSalesSlipHeadListForReportDao = repSalesSlipHeadListForReportDao;
	}


	public void setRepSalesSlipTaxRatioListForReportDao(
			RepSalesSlipTaxRatioListForReportDao repSalesSlipTaxRatioListForReportDao) {
		this.repSalesSlipTaxRatioListForReportDao = repSalesSlipTaxRatioListForReportDao;
	}

	/**
	 * 請求先用Daoを設定します。
	 * @param venderDao 請求先用Dao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * 帳合マスタ用Daoを設定します。
	 * @param balanceDao 帳合マスタ用Dao
	 */
	public void setBalanceDao(final BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	/**
	 * ログイン用Daoを設定します。
	 * @param loginDao ログイン用Dao
	 */
	public void setLoginDao(final LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * salesExtendedDaoを取得します。
	 * @return salesExtendedDao
	 */
	public SalesExtendedDao getSalesExtendedDao() {
		return salesExtendedDao;
	}

	/**
	 * salesExtendedDaoを設定します。
	 * @param salesExtendedDao salesExtendedDao
	 */
	public void setSalesExtendedDao(final SalesExtendedDao salesExtendedDao) {
		this.salesExtendedDao = salesExtendedDao;
	}

	/**
	 * taxMasterListDaoを設定します。
	 * @param taxMasterListDao taxMasterListDao
	 */
	public void setTaxMasterListDao(TaxMasterListDao taxMasterListDao) {
		this.taxMasterListDao = taxMasterListDao;
	}
}
