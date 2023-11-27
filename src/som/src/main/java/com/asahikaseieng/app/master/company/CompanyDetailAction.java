/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisit;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 自社詳細 Actionクラス.
 * @author t0011036
 */
public final class CompanyDetailAction extends AbstractAction {

	private CompanyDetailLogic companyDetailLogic;

	/* 端数 */
	private static final String SONOTA = "SONOTA";

	/* 端数 */
	private static final String SONOTA3 = "SONOTA3";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/* 売上単価 */
	private static final String URTANKA = "URTANKA";

	/* 仕入単価 */
	private static final String SITANKA = "SITANKA";

	/* 単価 */
	private static final String TANKA = "TANKA";

	/* 配合量 */
	private static final String HAIGO = "HAIGO";

	/* 配合率 */
	private static final String HAIGO_RITU = "HAIGO_RITU";

	/* 配合調整 */
	private static final String HAIGO_ADJ = "HAIGO_ADJ";

	/* 全体桁数 */
	private static final BigDecimal MAX_LENGTH = new BigDecimal("22");

	/* インボイス番号 頭文字 */
	private static final String INVOICE_NO_HEAD = "T";


	/**
	 * コンストラクタ.
	 */
	public CompanyDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		CompanyDetailForm frm = (CompanyDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_COMPANY,
			Constants.TAB_ID_COMPANY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		CompanyDetail bean = companyDetailLogic.getDetailEntity(frm
				.getCompanyCd());

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列に変換 */
		bean.setStrSettlement(checker.format(SONOTA, bean.getSettlement())); /* 決算月 */
		bean.setStrClosingDay(checker.format(SONOTA, bean.getClosingDay())); /* 締日 */
		bean.setStrTaxRatio(checker.format(TAX_AMOUNT, bean.getTaxRatio())); /* 消費税率 */

		// 2014/1/16 新消費税対応 -->
		bean.setStrNewTaxRatio(checker
				.format(TAX_AMOUNT, bean.getNewTaxRatio())); /* 新消費税率 */
		bean.setStrNewTaxApllyDate(AecDateUtils.dateFormat(bean
				.getNewTaxApllyDate(), "yyyy/MM/dd")); /* 新消費税開始日 */
		// 2014/1/16 新消費税対応 <--

		bean.setStrStockDiscountRate(checker.format(TAX_AMOUNT, bean
				.getStockDiscountRate())); /* 在庫割引率 */
		bean.setStrPurchaseDiscountRate(checker.format(TAX_AMOUNT, bean
				.getPurchaseDiscountRate())); /* 仕入割引率 */
		bean.setStrPasswordValidTerm(checker.format(SONOTA, bean
				.getPasswordValidTerm())); /* パスワード有効期限 */
		bean.setStrPasswordKetaLowerLimit(checker.format(SONOTA, bean
				.getPasswordKetaLowerLimit())); /* 開始パスワード有効桁数 */
		bean.setStrPasswordKetaUpperLimit(checker.format(SONOTA, bean
				.getPasswordKetaUpperLimit())); /* 終了パスワード有効桁数 */
		bean.setStrPrime(checker.format(SONOTA3, bean.getPrime())); /* 短プラ+金利 */

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		CompanyDetailForm frm = (CompanyDetailForm) form;

		/* インボイス登録番号チェック */
		if(!StringUtils.isEmpty(frm.getInvoiceNo())) {

			if(!checkInoviceNo(frm.getInvoiceNo())) {
				/* エラーメッセージ */
				saveError(request, "errors.company.inoviceno.length");
				return mapping.findForward("success");
			}
		}

		/* 締日日数チェック */
		if (!AecNumberUtils.convertBigDecimal(frm.getStrClosingDay()).equals(
			new BigDecimal("99"))
				&& (AecNumberUtils.convertBigDecimal(frm.getStrClosingDay())
						.compareTo(new BigDecimal("1")) < 0 || 0 < AecNumberUtils
						.convertBigDecimal(frm.getStrClosingDay()).compareTo(
							new BigDecimal("31")))) {
			/* エラーメッセージ */
			saveError(request, "errors.company.closing.day");
			return mapping.findForward("success");
		}

		if (!StringUtils.isEmpty(frm.getBankMasterCd1())) {
			/* 入金銀行マスタコードチェック */
			BankDetail beanBank = companyDetailLogic.getBankEntity(frm
					.getBankMasterCd1());

			if (beanBank == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.company.bank.master.cd1");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getBankMasterCd2())) {
			/* 入金銀行マスタコードチェック */
			BankDetail beanBank = companyDetailLogic.getBankEntity(frm
					.getBankMasterCd2());

			if (beanBank == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.company.bank.master.cd2");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getBankMasterCd3())) {
			/* 入金銀行マスタコードチェック */
			BankDetail beanBank = companyDetailLogic.getBankEntity(frm
					.getBankMasterCd3());

			if (beanBank == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.company.bank.master.cd3");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getBankMasterCd4())) {
			/* 入金銀行マスタコードチェック */
			BankDetail beanBank = companyDetailLogic.getBankEntity(frm
					.getBankMasterCd4());

			if (beanBank == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.company.bank.master.cd4");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getBankMasterCd())) {
			/* 支払銀行マスタコードチェック */
			BankDetail beanBank = companyDetailLogic.getBankEntity(frm
					.getBankMasterCd());

			if (beanBank == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.company.bank.master.cd");
				return mapping.findForward("success");
			}
		}

		Company beanCompany = new Company();

		if (frm.getUpdateDate() == null) {
			/* 追加用データ作成 */
			beanCompany = insertCompany(frm, getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成 */
			beanCompany = updateCompany(frm, getLoginInfo(request).getTantoCd());
		}

		NumberChkdisit beanTax = null;

		/* 数値チェック検索(消費税) */
		beanTax = companyDetailLogic.getNumberEntity(TAX_AMOUNT, " ", " ");

		if (beanTax == null) {
			/* 追加用データ作成(消費税) */
			beanTax = insertTax(frm, getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成(消費税) */
			beanTax = updateTax(frm, getLoginInfo(request).getTantoCd());
		}

		NumberChkdisit beanRoundup = null;

		/* 数値チェック検索(端数) */
		beanRoundup = companyDetailLogic.getNumberEntity(SONOTA, " ", " ");

		if (beanRoundup == null) {
			/* 追加用データ作成(端数) */
			beanRoundup = insertRoundup(frm, getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成(端数) */
			beanRoundup = updateRoundup(frm, getLoginInfo(request).getTantoCd());
		}

		NumberChkdisit beanSales = null;

		/* 数値チェック検索(売上金額) */
		beanSales = companyDetailLogic.getNumberEntity(URKINGAKU, " ", " ");

		if (beanSales == null) {
			/* 追加用データ作成(売上金額) */
			beanSales = insertSales(frm, getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成(売上金額) */
			beanSales = updateSales(frm, getLoginInfo(request).getTantoCd());
		}

		NumberChkdisit beanPurchase = null;

		/* 数値チェック検索(仕入金額) */
		beanPurchase = companyDetailLogic.getNumberEntity(SIKINGAKU, " ", " ");

		if (beanPurchase == null) {
			/* 追加用データ作成(仕入金額) */
			beanPurchase = insertPurchase(frm, getLoginInfo(request)
					.getTantoCd());
		} else {
			/* 更新用データ作成(仕入金額) */
			beanPurchase = updatePurchase(frm, getLoginInfo(request)
					.getTantoCd());
		}

		NumberChkdisit beanSalesUnitprice = null;

		/* 数値チェック検索(売上単価) */
		beanSalesUnitprice = companyDetailLogic.getNumberEntity(URTANKA, " ",
			" ");

		if (beanSalesUnitprice == null) {
			/* 追加用データ作成(売上単価) */
			beanSalesUnitprice = insertSalesUnitprice(frm,
				getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成(売上単価) */
			beanSalesUnitprice = updateSalesUnitprice(frm,
				getLoginInfo(request).getTantoCd());
		}

		NumberChkdisit beanPurchaseUnitprice = null;

		/* 数値チェック検索(仕入単価) */
		beanPurchaseUnitprice = companyDetailLogic.getNumberEntity(SITANKA,
			" ", " ");

		if (beanPurchaseUnitprice == null) {
			/* 追加用データ作成(仕入単価) */
			beanPurchaseUnitprice = insertPurchaseUnitprice(frm, getLoginInfo(
				request).getTantoCd());
		} else {
			/* 更新用データ作成(仕入単価) */
			beanPurchaseUnitprice = updatePurchaseUnitprice(frm, getLoginInfo(
				request).getTantoCd());
		}

		NumberChkdisit beanUnitprice = null;

		/* 数値チェック検索(単価) */
		beanUnitprice = companyDetailLogic.getNumberEntity(TANKA, " ", " ");

		if (beanUnitprice == null) {
			/* 追加用データ作成(単価) */
			beanUnitprice = insertUnitprice(frm, getLoginInfo(request)
					.getTantoCd());
		} else {
			/* 更新用データ作成(単価) */
			beanUnitprice = updateUnitprice(frm, getLoginInfo(request)
					.getTantoCd());
		}

		NumberChkdisit beanBlendQty = null;

		/* 数値チェック検索(配合量) */
		beanBlendQty = companyDetailLogic.getNumberEntity(HAIGO, " ", " ");

		if (beanBlendQty == null) {
			/* 追加用データ作成(配合量) */
			beanBlendQty = insertBlendQty(frm, getLoginInfo(request)
					.getTantoCd());
		} else {
			/* 更新用データ作成(配合量) */
			beanBlendQty = updateBlendQty(frm, getLoginInfo(request)
					.getTantoCd());
		}

		NumberChkdisit beanMixRate = null;

		/* 数値チェック検索(配合率) */
		beanMixRate = companyDetailLogic.getNumberEntity(HAIGO_RITU, " ", " ");

		if (beanMixRate == null) {
			/* 追加用データ作成(配合率) */
			beanMixRate = insertMixRate(frm, getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成(配合率) */
			beanMixRate = updateMixRate(frm, getLoginInfo(request).getTantoCd());
		}

		NumberChkdisit beanAdj = null;

		/* 数値チェック検索(配合調整) */
		beanAdj = companyDetailLogic.getNumberEntity(HAIGO_ADJ, " ", " ");

		if (beanAdj == null) {
			/* 追加用データ作成(配合調整) */
			beanAdj = insertAdj(frm, getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成(配合調整) */
			beanAdj = updateAdj(frm, getLoginInfo(request).getTantoCd());
		}

		/* 登録処理を実行 */
		companyDetailLogic.regist(beanCompany, beanTax, beanRoundup, beanSales,
			beanPurchase, beanSalesUnitprice, beanPurchaseUnitprice,
			beanUnitprice, beanBlendQty, beanMixRate, beanAdj);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}


	/**
	 * インボイス登録番号の入力チェック.
	 * @param invoiceNo インボイス番号
	 * @return boolean
	 */
	private boolean checkInoviceNo(final String invoiceNo){
		boolean errorCheckFlg = false;

		/* NULLチェック */
		if(StringUtils.isEmpty(invoiceNo)){
			return errorCheckFlg;
		}

		/* 桁数チェック */
		if(invoiceNo.length() != 14) {
			return errorCheckFlg;
		}

		/* 頭文字チェック */
		if(!StringUtils.equals(StringUtils.substring(invoiceNo, 0, 1), INVOICE_NO_HEAD)) {
			return errorCheckFlg;
		}

		/* 半角数字チェック(2～14桁目) */
		if(!AecStringUtils.isNumeric(StringUtils.substring(invoiceNo, 1, 14))) {
			return errorCheckFlg;
		}

		errorCheckFlg = true;
		return errorCheckFlg;
	}

	/**
	 * 更新処理用のCompanyを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Company
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Company updateCompany(final CompanyDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Company newBean = new Company();

		try {
			newBean = companyDetailLogic.getEntity(frm.getCompanyCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 数値に変換 */
		frm.setSettlement(AecNumberUtils.convertBigDecimal(frm
				.getStrSettlement())); /* 決算月 */
		frm.setClosingDay(AecNumberUtils.convertBigDecimal(frm
				.getStrClosingDay())); /* 締日 */
		frm.setTaxRatio(AecNumberUtils.convertBigDecimal(frm.getStrTaxRatio())); /* 消費税率 */

		// 2014/1/16 新消費税対応 -->
		frm.setNewTaxRatio(AecNumberUtils.convertBigDecimal(frm
				.getStrNewTaxRatio())); /* 新消費税率 */
		frm.setNewTaxApllyDate(AecDateUtils.getTimestampYmdHmFormat(frm
				.getStrNewTaxApllyDate(), "yyyy/MM/dd")); /* 新消費税率開始日 */
		// 2014/1/16 新消費税対応 <--

		frm.setStockDiscountRate(AecNumberUtils.convertBigDecimal(frm
				.getStrStockDiscountRate())); /* 在庫割引率 */
		frm.setPurchaseDiscountRate(AecNumberUtils.convertBigDecimal(frm
				.getStrPurchaseDiscountRate())); /* 仕入割引率 */
		frm.setPasswordValidTerm(AecNumberUtils.convertBigDecimal(frm
				.getStrPasswordValidTerm())); /* パスワード有効期限 */
		frm.setPasswordKetaLowerLimit(AecNumberUtils.convertBigDecimal(frm
				.getStrPasswordKetaLowerLimit())); /* 開始パスワード有効桁数 */
		frm.setPasswordKetaUpperLimit(AecNumberUtils.convertBigDecimal(frm
				.getStrPasswordKetaUpperLimit())); /* 終了パスワード有効桁数 */
		frm.setPrime(AecNumberUtils.convertBigDecimal(frm.getStrPrime())); /* 短プラ+金利 */

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(消費税)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateTax(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(TAX_AMOUNT,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getTaxRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getTaxRoundupUnit());
			smallnumLength = frm.getTaxRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getTaxRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(端数)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateRoundup(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(SONOTA,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getRoundupUnit());
			smallnumLength = frm.getRoundupUnit().subtract(new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(売上金額)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateSales(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(URKINGAKU,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getSalesRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getSalesRoundupUnit());
			smallnumLength = frm.getSalesRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getSalesRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(仕入金額)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updatePurchase(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(SIKINGAKU,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getPurchaseRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getPurchaseRoundupUnit());
			smallnumLength = frm.getPurchaseRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getPurchaseRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(売上単価)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateSalesUnitprice(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(URTANKA,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getUnitpriceRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getUnitpriceRoundupUnit());
			smallnumLength = frm.getUnitpriceRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getUnitpriceRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(仕入単価)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updatePurchaseUnitprice(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(SITANKA,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getUnitpriceRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getUnitpriceRoundupUnit());
			smallnumLength = frm.getUnitpriceRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getUnitpriceRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(単価)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateUnitprice(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(TANKA, " ",
			" ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getUnitpriceRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getUnitpriceRoundupUnit());
			smallnumLength = frm.getUnitpriceRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getUnitpriceRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(配合量)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateBlendQty(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(HAIGO, " ",
			" ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getBlendQtyRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getBlendQtyRoundupUnit());
			smallnumLength = frm.getBlendQtyRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getBlendQtyRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(配合率)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateMixRate(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(HAIGO_RITU,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getMixRateRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getMixRateRoundupUnit());
			smallnumLength = frm.getMixRateRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getMixRateRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用(配合調整)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateAdj(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = companyDetailLogic.getNumberEntity(HAIGO_ADJ,
			" ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getAdjRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getAdjRoundupUnit());
			smallnumLength = frm.getAdjRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getAdjRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のCompanyを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Company
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Company insertCompany(final CompanyDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Company newBean = new Company();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setManagementStartDate(newBean.getCurrentTimestamp());
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(消費税)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertTax(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getTaxRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getTaxRoundupUnit());
			smallnumLength = frm.getTaxRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(TAX_AMOUNT);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getTaxRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(端数)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertRoundup(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getRoundupUnit());
			smallnumLength = frm.getRoundupUnit().subtract(new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(SONOTA);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(売上金額)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertSales(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getSalesRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getSalesRoundupUnit());
			smallnumLength = frm.getSalesRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(URKINGAKU);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getSalesRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(仕入金額)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertPurchase(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getPurchaseRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getPurchaseRoundupUnit());
			smallnumLength = frm.getPurchaseRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(SIKINGAKU);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getPurchaseRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(売上単価)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertSalesUnitprice(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getUnitpriceRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getUnitpriceRoundupUnit());
			smallnumLength = frm.getUnitpriceRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(URTANKA);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getUnitpriceRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(仕入単価)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertPurchaseUnitprice(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getUnitpriceRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getUnitpriceRoundupUnit());
			smallnumLength = frm.getUnitpriceRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(SITANKA);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getUnitpriceRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(単価)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertUnitprice(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getUnitpriceRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getUnitpriceRoundupUnit());
			smallnumLength = frm.getUnitpriceRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(TANKA);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getUnitpriceRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(配合量)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertBlendQty(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getBlendQtyRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getBlendQtyRoundupUnit());
			smallnumLength = frm.getBlendQtyRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(HAIGO);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getBlendQtyRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(配合率)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertMixRate(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getMixRateRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getMixRateRoundupUnit());
			smallnumLength = frm.getMixRateRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(HAIGO_RITU);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getMixRateRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用(配合調整)のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertAdj(final CompanyDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getAdjRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getAdjRoundupUnit());
			smallnumLength = frm.getAdjRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(HAIGO_ADJ);
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getAdjRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		CompanyDetailForm frm = (CompanyDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_AREA,
			Constants.TAB_ID_AREA_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * companyDetailLogicを設定します。
	 * @param companyDetailLogic companyDetailLogic
	 */
	public void setCompanyDetailLogic(
			final CompanyDetailLogic companyDetailLogic) {
		this.companyDetailLogic = companyDetailLogic;
	}
}
