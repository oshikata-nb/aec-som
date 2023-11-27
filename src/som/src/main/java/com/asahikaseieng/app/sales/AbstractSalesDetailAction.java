/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.tax.GetTaxRatioLogic;
import com.asahikaseieng.app.unitprice.GetValidUnitpriceLogic;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailEntity;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 売上詳細の抽象親クラス Actionクラス.
 * @author tosco
 */
public abstract class AbstractSalesDetailAction extends AbstractAction {

	/** 月次更新 処理済 */
	private static final BigDecimal UPDATE_DONE = BigDecimal.ONE;

	/** フラグ ON */
	private static final int FLAG_ON = 1;

	/** フラグ OFF */
	private static final int FLAG_OFF = 0;

	/** 取消元データの売上区分名F */
	private static final String CATEGORY_NAME_CANCEL_ORGIN = "取消済";

	/** 有効単価取得処理ロジッククラス */
	private GetValidUnitpriceLogic getValidUnitpriceLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	// 2014/2/3 ->
	private GetTaxRatioLogic getTaxRatioLogic;

	// 2014/2/3 <-
	/**
	 * コンストラクタ.
	 */
	public AbstractSalesDetailAction() {
		super();
	}

	/**
	 * 画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		AbstractSalesDetailForm frm = (AbstractSalesDetailForm) form;

		// 修正とする
		frm.setInsertFlg(0);

		frm.setDirtyFlg(null);

		// 2014/2/3 新消費税対応->
		frm.setTaxCombo(getTaxRatioLogic.createTaxRatioCombobox());
		// 2014/2/3 新消費税対応<-


		// 各子クラスの初期表示処理
		return initProcess(mapping, form, request, response);
	}

	/**
	 * 各子クラスの画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected abstract ActionForward initProcess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 新規登録画面表示処理(一覧画面の自社ロットボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		AbstractSalesDetailForm frm = (AbstractSalesDetailForm) form;

		// 新規とする
		frm.setInsertFlg(1);

		// 売上日のデフォルトを現在日付とする
		frm.setStrSalesDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));

		// 2014/1/29 -> 新消費税対応
		taxProc(frm);
		frm.setTaxCombo(getTaxRatioLogic.createTaxRatioCombobox());
		// 2014/1/29 <- 新消費税対応
		// 勘定年月算出処理を組み込んだので勘定年月のデフォルト表示を無くす
		// //勘定年月のデフォルトを現在の年月とする
		// frm.setStrAccountYears(
		// AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(),
		// "yyyy/MM"));

		frm.setDirtyFlg(null);

		return initNewProcess(mapping, frm, request, response);
	}

	/**
	 * 新規登録画面表示処理(一覧画面の自社ロットボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initContinue(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initContinue.");
		}

		AbstractSalesDetailForm frm = (AbstractSalesDetailForm) form;

		// 新規とする
		frm.setInsertFlg(1);

		frm.setDirtyFlg(null);

		return initNewProcess(mapping, frm, request, response);
	}

	/**
	 * 各子クラスの新規画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected abstract ActionForward initNewProcess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 戻る処理(戻るボタン押下時)
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
	 * 売上詳細共通項目を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param bean 売上詳細情報
	 * @return AbstractSalesDetailForm
	 */
	protected AbstractSalesDetailForm setSalesCommonInfo(
			final AbstractSalesDetailForm form, final SalesDetailEntity bean) {

		form.setSalesNo(bean.getSalesNo()); // 売上番号
		form.setStrSalesDate(bean.getStrSalesDate()); // 売上日
		form.setCancelSalesNo(bean.getCancelSalesNo()); // 売上-取消 売上番号
		if (SalesConst.SLIP_PUBLISH_COMP_ISSUED.equals(bean
				.getSlipPublishComp())) {
			form
					.setSlipPublishCompName(SalesConst.SLIP_PUBLISH_COMP_NAME_ISSUED); // 発行済
		}
		form.setOrderNo(bean.getOrderNo()); // 受注番号
		form.setCategoryDivision(bean.getCategoryDivision()); // 売上区分
		form.setCategoryName(bean.getCategoryName()); // 売上区分名称
		form.setStrAccountYears(bean.getStrAccountYears()); // 勘定年月
		form.setVenderCd(bean.getVenderCd()); // 得意先コード
		form.setVenderName1(bean.getVenderName1()); // 得意先名称
		form.setVenderShortedName(bean.getVenderShortedName()); // 得意先略称
		form.setChargeOrganizationCd(bean.getChargeOrganizationCd()); // 担当部署コード
		form.setChargeOrganizationName(bean.getChargeOrganizationName()); // 担当部署名称
		form.setItemCd(bean.getItemCd()); // 品目コード
		form.setItemName(bean.getItemName()); // 品目名称
		form.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード1
		form.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		form.setUnitDivision(bean.getUnitDivision()); // 単位区分
		String venderCd = bean.getVenderCd();
		form.setStrSalesQuantity(checker.format(form.getUnitDivision(),
			SalesConst.VENDER_DIVISION_TS, venderCd, bean.getSalesQuantity())); // 数量
		form.setStrStandardUnitprice(checker.format(form
				.getUnitDivisionUrTanka(), SalesConst.VENDER_DIVISION_TS,
			venderCd, bean.getStandardUnitprice())); // 標準単価
		form.setStrStandardDiscount(checker.format(form
				.getUnitDivisionUrTanka(), SalesConst.VENDER_DIVISION_TS,
			venderCd, bean.getStandardDiscount())); // 標準値引
		form.setStrSpecialDiscount(checker.format(
			form.getUnitDivisionUrTanka(), SalesConst.VENDER_DIVISION_TS,
			venderCd, bean.getSpecialDiscount())); // 特別値引
		form.setStrSalesAmount(checker.format(form.getUnitDivisionUrKingaku(),
			SalesConst.VENDER_DIVISION_TS, venderCd, bean.getSalesAmount())); // 金額
		form.setStrSalesUnitprice(checker.format(form.getUnitDivisionUrTanka(),
			SalesConst.VENDER_DIVISION_TS, venderCd, bean.getSalesUnitprice())); // 売上単価

		form.setTmpUnitpriceFlg(SalesConst.TMP_UNITPRICE_FLG_TMP.equals(bean
				.getTmpUnitpriceFlg())); // 仮単価
		form.setDeliveryCd(bean.getDeliveryCd()); // 納入先コード
		form.setDeliveryName1(bean.getDeliveryName1()); // 納入先名称
		form.setSearchKana(bean.getSearchKana()); // 納入先略称

		form.setAddress(bean.getAddress()); // 納入先住所
		form.setTelNo(bean.getTelNo()); // 電話番号

		// 仕訳(借方)
		form.setAccountDebitSectionCd(bean.getAccountDebitSectionCd()); // 部門コード
		form.setAccountDebitSectionName(bean.getAccountDebitSectionName()); // 部門名称
		form.setDebitTitleCd(bean.getDebitTitleCd()); // 科目コード
		form.setDebitTitleName(bean.getDebitTitleName()); // 科目名称

		// 仕訳(貸方)
		form.setAccountCreditSectionCd(bean.getAccountCreditSectionCd()); // 部門コード
		form.setAccountCreditSectionName(bean.getAccountCreditSectionName()); // 部門名称
		form.setCreditTitleCd(bean.getCreditTitleCd()); // 科目コード
		form.setCreditTitleName(bean.getCreditTitleName()); // 科目名称

		form.setHousingLocationCd(bean.getHousingLocationCd()); // 入庫ロケーション
		form.setPackageDirectionNo(bean.getPackageDirectionNo()); // 包装指図番号
		form.setProductLotno(bean.getProductLotno()); // 製品ロット番号
		form.setRyCd(bean.getRyCd()); // 理由コード
		form.setRyDescription(bean.getRyDescription()); // 理由
		form.setRemark(bean.getRemark()); // 備考
		form.setSummary(bean.getSummary()); // 適用
		form.setUpdateDate(bean.getUpdateDate()); // 更新日時

		// 月次更新済みフラグ
		if (UPDATE_DONE.equals(bean.getDepositUpdateDivision())
				|| UPDATE_DONE.equals(bean.getClaimUpdateDivision())) {
			form.setMonthlyUpdateDivision(FLAG_ON);
		} else {
			form.setMonthlyUpdateDivision(FLAG_OFF);
		}

		// 取消元データフラグ
		if (StringUtils.isNotEmpty(form.getCancelSalesNo())
				&& Integer.parseInt(form.getCategoryDivision()) > 0) {
			form.setCancelOriginFlag(FLAG_ON);
			form.setCategoryName(form.getCategoryName() + " "
					+ CATEGORY_NAME_CANCEL_ORGIN);
		} else {
			form.setCancelOriginFlag(FLAG_OFF);
		}

		// 2014/2/3 -> 消費税対応
		form.setStrTaxAmount(checker.format(form.getUnitDivisionUrKingaku(),
			SalesConst.VENDER_DIVISION_TS, venderCd, bean.getTaxAmount())); // 金額

		form.setStrTaxDivision(bean.getTaxDivision().toString());
		form.setStrTaxRatio(bean.getTaxRatio().toString());
		// 2014/2/3 <- 消費税対応
		return form;
	}

	/**
	 * 有効単価を取得
	 * @param form 売上詳細画面FORM
	 * @return SalesDetailKeepForm
	 */
	protected AbstractSalesDetailForm getValidUnitprice(
			final AbstractSalesDetailForm form) {
		String salesDate = AecDateUtils.dateFormat(AecDateUtils
				.getTimestampYmdHmFormat(form.getStrSalesDate(), "yyyy/MM/dd"),
			"yyyy/MM/dd");
		VaridUnitprice bean = null;
		if (salesDate != null) {
			// 有効単価取得
			bean = getValidUnitpriceLogic.getValidUnitprice(
				form.getBalanceCd(), form.getItemCd(), salesDate);
		}
		setValidUnitprice(bean, form);

		// 数値フォーマット
		form.setStrStandardUnitprice(checker.format(form
				.getUnitDivisionUrTanka(), SalesConst.VENDER_DIVISION_TS, form
				.getVenderCd(), form.getStandardUnitprice())); // 標準単価
		form.setStrStandardDiscount(checker.format(form
				.getUnitDivisionUrTanka(), SalesConst.VENDER_DIVISION_TS, form
				.getVenderCd(), form.getStandardDiscount())); // 標準値引
		form.setStrSpecialDiscount(checker.format(
			form.getUnitDivisionUrTanka(), SalesConst.VENDER_DIVISION_TS, form
					.getVenderCd(), form.getSpecialDiscount())); // 特別値引

		// 丸め処理
		BigDecimal standardUnitpriceRd = checker.round(
			SalesConst.UNIT_DIVISION_URTANKA, SalesConst.VENDER_DIVISION_TS,
			form.getVenderCd(), form.getStandardUnitprice());
		BigDecimal standardDiscountRd = checker.round(
			SalesConst.UNIT_DIVISION_URTANKA, SalesConst.VENDER_DIVISION_TS,
			form.getVenderCd(), form.getStandardDiscount());
		BigDecimal specialDiscountRd = checker.round(
			SalesConst.UNIT_DIVISION_URTANKA, SalesConst.VENDER_DIVISION_TS,
			form.getVenderCd(), form.getSpecialDiscount());
		// 売上単価 = 標準単価 - 標準値引 - 特別値引
		BigDecimal unitPrice = standardUnitpriceRd.subtract(standardDiscountRd)
				.subtract(specialDiscountRd);
		form.setStrSalesUnitprice(checker.format(form.getUnitDivisionUrTanka(),
			SalesConst.VENDER_DIVISION_TS, form.getVenderCd(), unitPrice));

		// 金額計算
		BigDecimal salesAmount = unitPrice.multiply(convertBigDecimal(form
				.getStrSalesQuantity()));
		form.setStrSalesAmount(checker.format(form.getUnitDivisionUrKingaku(),
			SalesConst.VENDER_DIVISION_TS, form.getVenderCd(), salesAmount));

		// 2014/2/4 新消費税対応 ->
		form
				.setStrTaxAmount(checker.format(
					form.getUnitDivisionUrKingaku(),
					SalesConst.VENDER_DIVISION_TS, form.getVenderCd(),
					BigDecimal.ZERO));

		// 2014/2/4 新消費税対応 <-
		return form;
	}

	// 2014/1/29 新消費税対応 ->
	/**
	 * 消費税率が有効か確認する
	 * @param date date
	 * @param taxRatio taxRatio
	 * @return true:有効 false:無効
	 */
	public boolean isValidTaxRatio(final String date, final String taxRatio) {
		return getTaxRatioLogic.isValidTax(date, taxRatio);
	}

	/**
	 * 売上日から消費税率を取得する
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getTaxRatio(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getTaxRatio.");
		}
		AbstractSalesDetailForm frm = (AbstractSalesDetailForm) form;

		taxProc(frm);

		calcSalesAmountAndTax(mapping, form, request, response);

		return mapping.findForward("success");
	}

	/**
	 * 消費税を取得しフォームにセットする
	 * @param frm フォーム
	 */
	public void taxProc(final AbstractSalesDetailForm frm) {

		frm.setStrTaxRatio(getTaxRatioLogic.getTaxRatio(frm.getTaxCd()));
	}

	/**
	 *
	 * 品目から消費税区分を取得する
	 * @param itemCd 品目コード
	 * @return 消費税区分
	 */
	public String getTaxDivisionFromItem(final String itemCd) {

		return getTaxRatioLogic.getTaxDivisionFromItem(itemCd, "0");
	}

	/**
	 * 消費税計算を行う
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calcSalesAmountAndTax(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("calcSalesAmountAndTax.");
		}
		AbstractSalesDetailForm frm = (AbstractSalesDetailForm) form;

		BigDecimal unitPrice = BigDecimal.ZERO;
		BigDecimal salesQty = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;
		// 単価が未入力の場合処理しない
		if (frm.getStrSalesUnitprice() == null
				|| frm.getStrSalesUnitprice().equals("")
				|| !isNumber(frm.getStrSalesUnitprice())) {
			return mapping.findForward("success");
		} else {
			unitPrice = AecNumberUtils.convertNullToZero(AecNumberUtils
					.convertBigDecimal(frm.getStrSalesUnitprice()));
		}

		// 数量が未入力の場合処理しない
		if (frm.getStrSalesQuantity() == null
				|| frm.getStrSalesQuantity().equals("")
				|| !isNumber(frm.getStrSalesQuantity())) {
			return mapping.findForward("success");
		} else {
			salesQty = AecNumberUtils.convertNullToZero(AecNumberUtils
					.convertBigDecimal(frm.getStrSalesQuantity()));
		}

		// 売上金額 = 売上単価 × 数量
		BigDecimal salesAmount = unitPrice.multiply(salesQty);
		// 丸め処理
		BigDecimal salesAmountRd = checker.round(
			SalesConst.UNIT_DIVISION_URKINGAKU, SalesConst.VENDER_DIVISION_TS,
			frm.getVenderCd(), salesAmount);

		//軽減税率対応　消費税率を税コードから取得　20190823
		String	   taxCd    = getTaxRatioLogic.getTaxCd(frm.getStrSalesDate(), frm.getItemCd(), SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(), SalesConst.SALES_CATEGORY, null, null);
		BigDecimal taxRatio = null;
		if (frm.getTaxCdChangeFlg().equals("true")){
			taxRatio = (BigDecimal) getTaxRatioLogic.getTaxRatioFromTaxCd(frm.getTaxCd());
		}else {
			taxRatio = (BigDecimal) getTaxRatioLogic.getTaxRatioFromTaxCd(taxCd);
		}

		// 消費税課税区分=1:外税または2:内税
		if (SalesConst.TAX_DIVISION_OUT.equals(frm.getStrTaxDivision())) {
			// 消費税率は％のため100で割る
			/*BigDecimal taxRatio;*/
			if (taxRatio == null || taxRatio.equals("")) {
				taxRatio = BigDecimal.ZERO;
			} else {
				taxRatio = taxRatio
						.divide(new BigDecimal(100));
			}
			// 消費税額＝売上金額×消費税率
			taxAmount = salesAmountRd.multiply(taxRatio);
			// 丸め処理
			taxAmount = checker.round(SalesConst.UNIT_DIVISION_TAX_AMOUNT,
				SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(), taxAmount);
		} else if (SalesConst.TAX_DIVISION_IN.equals(frm.getStrTaxDivision())) {
			// 消費税課税区分=2:内税
			double dblSalesAmount = 0;
			double dblTaxRate = 0;
			double dblTalAmount = 0;

			if (salesAmount != null) {
				dblSalesAmount = salesAmount.doubleValue();
			} else {
				dblSalesAmount = 0;
			}
			if (taxRatio == null || taxRatio.equals("")) {
				dblTaxRate = 0;
			} else {
				dblTaxRate = taxRatio.doubleValue();
			}


			// 消費税課税区分=1:外税または2:内税
/*			if (SalesConst.TAX_DIVISION_OUT.equals(frm.getStrTaxDivision())) {
				// 消費税率は％のため100で割る
				BigDecimal taxRatio;
				if (frm.getStrTaxRatio() == null || frm.getStrTaxRatio().equals("")) {
					taxRatio = BigDecimal.ZERO;
				} else {
					taxRatio = new BigDecimal(frm.getStrTaxRatio())
							.divide(new BigDecimal(100));
				}
				// 消費税額＝売上金額×消費税率
				taxAmount = salesAmountRd.multiply(taxRatio);
				// 丸め処理
				taxAmount = checker.round(SalesConst.UNIT_DIVISION_TAX_AMOUNT,
					SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(), taxAmount);
			} else if (SalesConst.TAX_DIVISION_IN.equals(frm.getStrTaxDivision())) {
				// 消費税課税区分=2:内税
				double dblSalesAmount = 0;
				double dblTaxRate = 0;
				double dblTalAmount = 0;

				if (salesAmount != null) {
					dblSalesAmount = salesAmount.doubleValue();
				} else {
					dblSalesAmount = 0;
				}
				if (frm.getStrTaxRatio() == null || frm.getStrTaxRatio().equals("")) {
					dblTaxRate = 0;
				} else {
					dblTaxRate = new BigDecimal(frm.getStrTaxRatio()).doubleValue();
				}*/
			// 2014/1/29 ->新消費税対応

			// 内税計算 = 金額 * 消費税率 /( 100 + 消費税率)
			dblTalAmount = dblSalesAmount * dblTaxRate / (100 + dblTaxRate);

			taxAmount = new BigDecimal(dblTalAmount);

			// 丸め処理
			taxAmount = checker.round(SalesConst.UNIT_DIVISION_TAX_AMOUNT,
				SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(), taxAmount);

			if (taxAmount != null) {
				dblSalesAmount = dblSalesAmount - taxAmount.doubleValue();
			}
			salesAmountRd = checker.round(SalesConst.UNIT_DIVISION_URKINGAKU,
				SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(),
				new BigDecimal(dblSalesAmount));

			taxAmount = checker.round(SalesConst.UNIT_DIVISION_TAX_AMOUNT,
				SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(), taxAmount);

		}
		frm.setStrSalesAmount(checker.format(frm.getUnitDivisionUrKingaku(),
			SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(), salesAmountRd)); // 金額

		frm.setStrTaxAmount(checker.format(frm.getUnitDivisionUrKingaku(),
			SalesConst.VENDER_DIVISION_TS, frm.getVenderCd(), taxAmount)); // 金額

		//軽減税率対応
		if (frm.getTaxCdChangeFlg().equals("true")){
			frm.setTaxCd(frm.getTaxCd());
		}else {
			frm.setTaxCd(taxCd);
		}
		frm.setTaxCdChangeFlg("false");

		return mapping.findForward("success");
	}

	/**
	 *
	 * 文字列を数値であるかチェックを行う
	 * @param val チェック文字列
	 * @return true:数値 false:数値以外
	 */
	public boolean isNumber(final String val) {
		try {
			AecNumberUtils.convertBigDecimal(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}

	// 2014/1/29 新消費税対応 <-

	/**
	 * 各子クラスの有効単価設定処理
	 * @param form AbstractSalesDetailForm
	 * @param bean 有効単価検索結果
	 * @return AbstractSalesDetailForm
	 */
	protected abstract AbstractSalesDetailForm setValidUnitprice(
			VaridUnitprice bean, AbstractSalesDetailForm form);

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	protected String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * StringからBigDecimalへ型変換を行う
	 * @param strVal String値
	 * @return BigDecimal BigDecimal型に変換した値
	 */
	protected BigDecimal convertBigDecimal(final String strVal) {
		BigDecimal val = AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(strVal));
		return val;
	}

	/**
	 * 数値桁数チェック ロジッククラス取得
	 * @return CheckDigitUtilsLogic 数値桁数チェック ロジッククラス
	 */
	protected CheckDigitUtilsLogic getChecker() {
		return this.checker;
	}

	/* -------------------- setter -------------------- */
	/**
	 * 有効単価取得処理ロジッククラスを設定します。
	 * @param getValidUnitpriceLogic 有効単価取得処理ロジッククラス
	 */
	public void setGetValidUnitpriceLogic(
			final GetValidUnitpriceLogic getValidUnitpriceLogic) {
		this.getValidUnitpriceLogic = getValidUnitpriceLogic;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 消費税ロジッククラスを設定します。
	 * @param getTaxRatioLogic getTaxRatioLogic
	 */
	public void setGetTaxRatioLogic(final GetTaxRatioLogic getTaxRatioLogic) {
		this.getTaxRatioLogic = getTaxRatioLogic;
	}
}
