/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.accept;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.app.tax.GetTaxRatioLogic;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptBuyingDetailList;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 受入・仕入 仕入入力詳細 Actionクラス.
 * @author tosco
 */
public final class AcceptBuyingDetailAction extends AbstractAction {

	/** 仕入入力詳細ロジッククラス */
	private AcceptBuyingDetailLogic acceptBuyingDetailLogic;

	// 2014/2/3 新消費税対応 ->
	private PurchaseSubcontractDao purchaseSubcontractDao;

	private GetTaxRatioLogic getTaxRatioLogic;

	/** 軽減措置割合取得用Dao */
	private NamesDetailDao namesDetailDao;

	/** 算出区分 1:明細単位 */
	public static final String CALC_DIVISION_MEISAI = "1";

	/** 算出区分 4:自社ﾏｽﾀ */
	public static final String CALC_DIVISION_COMPANY = "4";

	/** 消費税課税区分 1:外税 */
	public static final String TAX_DIVISION_OUT = "1";

	/** 消費税課税区分 2:内税 */
	public static final String TAX_DIVISION_IN = "2";

	// 2014/2/3 新消費税対応 <-
	/**
	 * コンストラクタ.
	 */
	public AcceptBuyingDetailAction() {
		super();
	}

	/**
	 * 検索処理(一覧画面の仕入ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		AcceptBuyingDetailForm frm = (AcceptBuyingDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ACCEPT,	Constants.TAB_ID_ACCEPT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		try {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);
			/*// 2014/1/29 -> 新消費税対応
			taxProc(frm);
			frm.setTaxCombo(getTaxRatioLogic.createTaxRatioCombobox());
			// 2014/1/29 <- 新消費税対応
*/			// 仕入区分コンボボックスの作成
			acceptBuyingDetailLogic.createAcceptStockingDivisionCombobox(frm);

			/* 初期検索 */
			List<AcceptBuyingDetailList> beanList = acceptBuyingDetailLogic.getEntity(frm.getSlipNo(), check);

			// 共通情報セット
			setCommonInfo(frm, beanList);

			// 仕入単価取得処理 2010/03/05修正 仕入ステータス=[5:未登録]の場合単価取得
			if (frm.getStatus2() == null || frm.getStatus2().equals("5")) {

				BigDecimal sum = BigDecimal.ZERO;
				/* リスト部 */
				for (AcceptBuyingDetailList bean : beanList) {

					if (bean.getAcceptQuantity() != null) {
						sum = sum.add(bean.getAcceptQuantity());
					}
					if (bean.getIncreaseQuantity() != null) {
						sum = sum.add(bean.getIncreaseQuantity());
					}
				}
				frm.setStrHousingUnitprice(null);

				frm.setSumStockingQuantity(sum.toString());

				// 仕入単価を取得する
				acceptBuyingDetailLogic.getHousingUnitprice(frm, check);
			}

			// ロット分割データセット
			frm.setDetailList(beanList);

			// javascriptでの桁数丸め用に必要となる値取得
			getCheckDigit(frm, check, beanList.get(0));

			// 2014/1/29 -> 新消費税対応
			PurchaseSubcontract purchase = purchaseSubcontractDao.getEntity(frm.getPurchaseNo());
			// 消費税率が設定されていない場合、自社マスタから取得する
			if (purchase.getTaxRatio() == BigDecimal.ZERO) {
				frm.setStrTaxRatio(getTaxRatioLogic.getTaxRatio(frm.getStrStockingDate()));
			} else {
				frm.setStrTaxRatio(purchase.getTaxRatio().toString());
			}
			// 消費税課税区分が設定されていない場合、品目マスタから取得する
			if (purchase.getTaxDivision() == BigDecimal.ZERO) {
				frm.setTaxDivision(getTaxRatioLogic.getTaxDivisionFromItem(frm.getItemCd(), "1"));
			} else {
				frm.setTaxDivision(purchase.getTaxDivision().toString());
			}
			// 消費税額が設定されていない場合、再計算する
			if (purchase.getTaxAmount() == BigDecimal.ZERO) {
				frm.setStrStockingAmount(check.format("SIKINGAKU", "SI", frm.getVenderCd(), purchase.getStockingAmount()));
				calcStockingAmountAndTax(mapping, form, request, response);
			} else {
				frm.setStrTaxAmount(check.format("TAX_AMOUNT", "SI", frm.getVenderCd(), purchase.getTaxAmount()));
			}
			// 2014/1/29 <- 新消費税対応

			// 軽減措置金額計算用の税率を取得
			getReducedTaxRatio(frm);

			// 消費税のコンボボックスの設定
			setPurchaseTaxCombobox(frm);
			frm.setTaxCd(getTaxRatioLogic.getTaxCd(frm.getStrStockingDate(), frm.getItemCd(), "SI", frm.getVenderCd(), "STOCKING", null, frm.getReducedTaxTargetFlg()));
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}


		return mapping.findForward("success");

	}

	/**
	 *
	 * データ読込み
	 * @param mapping .
	 * @param frm .
	 * @param request .
	 * @param response .
	 * @throws Exception .
	 */
	private void readData(final ActionMapping mapping,	final AcceptBuyingDetailForm frm, final HttpServletRequest request,	final HttpServletResponse response) throws Exception {
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		// 仕入区分コンボボックスの作成
		acceptBuyingDetailLogic.createAcceptStockingDivisionCombobox(frm);

		/* 初期検索 */
		List<AcceptBuyingDetailList> beanList = acceptBuyingDetailLogic.getEntity(frm.getSlipNo(), check);

		// 共通情報セット
		setCommonInfo(frm, beanList);

		// ロット分割データセット
		frm.setDetailList(beanList);

		// javascriptでの桁数丸め用に必要となる値取得
		getCheckDigit(frm, check, beanList.get(0));

	}

	/**
	 * 更新処理(登録ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		AcceptBuyingDetailForm frm = (AcceptBuyingDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		// 更新前チェックを行う
		ActionMessages errors = acceptBuyingDetailLogic.checkForRegist(frm);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 2014/2/26 新消費税対応 <-
		// 消費税率が新消費税率適応開始日以前の場合エラーとする
		if (!isValidTaxRatio(frm.getStrStockingDate(), frm.getStrTaxRatio())) {
			errors.add("", new ActionMessage("errors.valid.tax.ratio"));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		// 2014/2/26 新消費税対応 ->
		// 更新処理を実行
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		acceptBuyingDetailLogic.update(frm, tantoCd, check);

		readData(mapping, frm, request, response);

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");

	}

	/**
	 * 承認依頼処理(承認依頼ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approve(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("approve.");
		}

		AcceptBuyingDetailForm frm = (AcceptBuyingDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		// 更新処理を実行
		acceptBuyingDetailLogic.approve(frm, tantoCd);

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");

	}

	/**
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
	 *
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,	final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");

	}

	/**
	 * 増付数量入力時連動変更項目(仕入単価)の検索を行う.
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getHousingUnitprice(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getHousingUnitprice.");
		}

		AcceptBuyingDetailForm frm = (AcceptBuyingDetailForm) form;
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		frm.setStrHousingUnitprice(null);
		// 仕入単価を取得する
		acceptBuyingDetailLogic.getHousingUnitprice(frm, check);

		return mapping.findForward("success");
	}

	/**
	 * 共通情報をFORMへセット
	 * @param frm 仕入入力FORM
	 * @param beanList 仕入入力表示データ
	 */
	private void setCommonInfo(final AcceptBuyingDetailForm frm,final List<AcceptBuyingDetailList> beanList) {
		AcceptBuyingDetailList bean = beanList.get(0);
		frm.setPurchaseNo(bean.getPurchaseNo()); // 購買NO
		frm.setBuySubcontractOrderNo(bean.getBuySubcontractOrderNo()); // 発注番号
		frm.setOrderDivideNo(bean.getOrderDivideNo()); // 発注番号分納枝番
		frm.setOrderSheetNo(bean.getOrderSheetNo()); // 発注書NO
		frm.setSlipNo(bean.getSlipNo()); // 仕入番号
		frm.setChargeOrganizationName(bean.getChargeOrganizationName()); // 担当部署名称
		frm.setStrOrderDate(bean.getStrOrderDate()); // 発注日
		frm.setSiOrderNo(bean.getSiOrderNo()); // 仕入先受注番号
		frm.setItemCd(bean.getItemCd()); // 品目コード
		frm.setItemQueueName(bean.getItemQueueName()); // 品目名称
		frm.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード１
		frm.setOrderQuantity(convertString(bean.getOrderQuantity())); // 発注数量
		frm.setStrOrderQuantity(bean.getStrOrderQuantity()); // 発注数量
		frm.setStrAcceptConvertQuantitySum(bean.getStrAcceptConvertQuantitySum()); // 受入重量合計
		frm.setUnit(bean.getUnit()); // 単位
		frm.setStrOrderConvertQuantity(bean.getStrOrderConvertQuantity()); // 重量
		frm.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		frm.setVenderCd(bean.getVenderCd()); // 仕入先コード
		frm.setVenderName(bean.getVenderName()); // 仕入先名称
		frm.setVenderShortedName(bean.getVenderShortedName()); // 支払先略称
		frm.setLocationCd(bean.getLocationCd()); // 納入ロケーション
		frm.setLocationName(bean.getLocationName()); // 納入先名称
		frm.setStrSuggestedDeliverlimitDate(bean.getStrSuggestedDeliverlimitDate()); // 納品希望日
		frm.setCategoryDivision(bean.getCategoryDivision()); // 分類コード
		frm.setStrHousingUnitprice(bean.getStrWherehousingUnitprice()); // 仕入単価
		frm.setUnitpriceUnit(bean.getUnitpriceUnit()); // 単価単位
		frm.setStrFareAmount(bean.getStrFareAmount()); // 運賃
		frm.setStrStockingAmount(bean.getStrStockingAmount()); // 仕入金額
		frm.setStrStockingDate(bean.getStrStockingDate()); // 仕入日

		// 仕入日が入力されていない場合は、受入日をデフォルトセット
		if (bean.getStrStockingDate() == null
				|| bean.getStrStockingDate().equals("")) {
			frm.setStrStockingDate(bean.getStrAcceptDate());
		}

		// 仕訳
		frm.setAccountDebitSectionCd(bean.getAccountDebitSectionCd()); // 会計部門借方コード
		frm.setAccountDebitSectionName(bean.getAccountDebitSectionName()); // 会計部門借方名称
		frm.setAccountCreditSectionCd(bean.getAccountCreditSectionCd()); // 会計部門貸方コード
		frm.setAccountCreditSectionName(bean.getAccountCreditSectionName()); // 会計部門貸方名称
		frm.setDebitTitleCd(bean.getDebitTitleCd()); // 借方科目コード
		frm.setDebitTitleName(bean.getDebitTitleName()); // 借方科目名称
		frm.setCreditTitleCd(bean.getCreditTitleCd()); // 貸方科目コード
		frm.setCreditTitleName(bean.getCreditTitleName()); // 貸方科目名称

		frm.setOrderSheetRemark2(bean.getOrderSheetRemark2()); // 発注書備考（入荷以降）
		frm.setRemark2(bean.getRemark2()); // 備考（入荷以降）
		frm.setStatus2(convertString(bean.getStatus2())); // 仕入ステータス
		frm.setUnitDiv(bean.getUnitDiv()); // 運用管理単位(区分)
		frm.setUnitpriceDivision(bean.getUnitpriceDivision()); // 単価区分
		// Kg換算係数(在庫)
		if (bean.getKgOfFractionManagement() != null) {
			frm.setKgOfFractionManagement(bean.getKgOfFractionManagement().toString());
		} else {
			frm.setKgOfFractionManagement("0");
		}
		frm.setTaxDivision(convertString(bean.getTaxDivision())); // 消費税課税区分
		frm.setCalcDivision(convertString(bean.getCalcDivision())); // 取引先マスタ.算出区分
		frm.setCompCalcDivision(convertString(bean.getCompCalcDivision())); // 自社マスタ.消費税算出区分
		frm.setTaxRatio(convertString(bean.getTaxRatio())); // 自社マスタ.消費税率

		frm.setReducedTaxTargetFlg(bean.getReducedTaxTargetFlg()); // 取引先マスタ.免税計算対象フラグ

	}

	/**
	 * BigDecimalからStringへ型変換を行う
	 * @param decimal BigDecimal値
	 * @return String String型に変換した値
	 */
	private String convertString(final BigDecimal decimal) {
		String ret = null;
		if (decimal != null) {
			ret = decimal.toString();
		}
		return ret;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm 仕入入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 * @param bean 仕入入力Bean
	 */
	private void getCheckDigit(final AcceptBuyingDetailForm frm,final CheckDigitUtilsLogic check, final AcceptBuyingDetailList bean) {
		// 仕入金額用
		NumberChkDisitDetail detail = check.getCheckDigit(
			AcceptBuyingDetailLogicImpl.UNIT_DIVISION_SIKINGAKU,AcceptBuyingDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPoint(detail.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail.getRoundDivision() != null) {
			frm.setRound(detail.getRoundDivision().toString()); // 端数区分
		}

		// 仕入数量用
		NumberChkDisitDetail detail2 = check.getCheckDigit(bean.getUnitDiv(),AcceptBuyingDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detail2.getSmallnumLength() != null) {
			frm.setDecimalPointQty(detail2.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail2.getRoundDivision() != null) {
			frm.setRoundQty(detail2.getRoundDivision().toString()); // 端数区分
		}

		// 仕入単価用
		NumberChkDisitDetail detail3 = check.getCheckDigit(	AcceptBuyingDetailLogicImpl.UNIT_DIVISION_SITANKA,AcceptBuyingDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detail3.getSmallnumLength() != null) {
			frm.setDecimalPointTanka(detail3.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail3.getRoundDivision() != null) {
			frm.setRoundTanka(detail3.getRoundDivision().toString()); // 端数区分
		}

		// 運賃用
		NumberChkDisitDetail detail4 = check.getCheckDigit(	AcceptBuyingDetailLogicImpl.UNIT_DIVISION_UNTIN,AcceptBuyingDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detail4.getSmallnumLength() != null) {
			frm.setDecimalPointUntin(detail4.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail4.getRoundDivision() != null) {
			frm.setRoundUntin(detail4.getRoundDivision().toString()); // 端数区分
		}

		// 消費税用
		NumberChkDisitDetail detail5 = check.getCheckDigit(	AcceptBuyingDetailLogicImpl.UNIT_DIVISION_TAX_AMOUNT,AcceptBuyingDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detail5.getSmallnumLength() != null) {
			frm.setDecimalPointTax(detail5.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail5.getRoundDivision() != null) {
			frm.setRoundTax(detail5.getRoundDivision().toString()); // 端数区分
		}

	}

	// 2014/1/29 新消費税対応 ->
	/**
	 * 仕入金額と消費税額を計算する
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calcStockingAmountAndTax(final ActionMapping mapping,	final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("calcStockingAmountAndTax.");
		}
		AcceptBuyingDetailForm frm = (AcceptBuyingDetailForm) form;

		if (frm.getSumStockingQuantity() == null
				|| frm.getSumStockingQuantity().equals("")
				|| !isNumber(frm.getSumStockingQuantity())) {
			frm.setSumStockingQuantity(BigDecimal.ZERO.toString());
		}

		if (frm.getStrStockingAmount() == null
				|| frm.getStrStockingAmount().equals("")
				|| !isNumber(frm.getStrStockingAmount())) {
			frm.setStrStockingAmount(BigDecimal.ZERO.toString());
		}
		if (frm.getStrHousingUnitprice() == null
				|| frm.getStrHousingUnitprice().equals("")
				|| !isNumber(frm.getStrHousingUnitprice())) {
			frm.setStrHousingUnitprice(BigDecimal.ZERO.toString());
		}
		frm.setTaxRatio(frm.getStrTaxRatio());

		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		//軽減税率対応　消費税率を税コードから取得　20190823
		//申請業者か未申請業者によって取得する税コードが変わる
		String	   taxCd    = getTaxRatioLogic.getTaxCd(frm.getStrStockingDate(), frm.getItemCd(), "SI", frm.getVenderCd(), "STOCKING", null, frm.getReducedTaxTargetFlg());
		BigDecimal taxRatio = null;
		if (frm.getTaxCdChangeFlg().equals("true")){
			taxRatio = (BigDecimal) getTaxRatioLogic.getTaxRatioFromTaxCd(frm.getTaxCd());
		}else {
			taxRatio = (BigDecimal) getTaxRatioLogic.getTaxRatioFromTaxCd(taxCd);
		}

		//20200904_AEC佐藤（追加）_消費税率をformのstrTaxRatio(購買トランザクション消費税率)へセット
		if (taxRatio == null || taxRatio.equals("")) {
			taxRatio = BigDecimal.ZERO; // 消費税率がnullの場合は0に置き換える
		}
		frm.setStrTaxRatio(taxRatio.toString());

		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(frm.getCalcDivision())
				|| (CALC_DIVISION_COMPANY.equals(frm.getCalcDivision()) && CALC_DIVISION_MEISAI.equals(frm.getCompCalcDivision()))) {

			// 消費税課税区分=1:外税または2:内税
			if (TAX_DIVISION_OUT.equals(frm.getTaxDivision())) {
				BigDecimal stockingAmount = AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(frm.getStrStockingAmount())); // 仕入金額
				taxRatio = taxRatio.divide(new BigDecimal(100)); // 消費税率は％のため100で割る
				// 消費税額＝仕入金額×消費税率
				BigDecimal taxAmount = stockingAmount.multiply(taxRatio);
				// 丸め処理
				frm.setStrTaxAmount(check.format("TAX_AMOUNT", "SI", frm.getVenderCd(), taxAmount));
			} else if (TAX_DIVISION_IN.equals(frm.getTaxDivision())) {
				BigDecimal stockingAmount = AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(frm.getStrStockingAmount())); // 仕入金額
				//20200904_AEC佐藤（追加）_消費税率が0以外の場合はそのまま100で割って、1を加算する
				if (taxRatio != BigDecimal.ZERO) {
					taxRatio = taxRatio.divide(new BigDecimal(100)).add(BigDecimal.ONE); // 消費税率は％のため100で割る
				}
				// 身代額＝仕入金額／（1+消費税率）
				BigDecimal taxAmount = stockingAmount.divide(taxRatio, 10,BigDecimal.ROUND_DOWN);
				// 消費税＝仕入金額－身代
				taxAmount = stockingAmount.subtract(taxAmount);
				// 丸め処理
				frm.setStrTaxAmount(check.format("TAX_AMOUNT", "SI", frm.getVenderCd(), taxAmount));

				// 身代＝仕入金額－消費税額
				stockingAmount = stockingAmount.subtract(taxAmount);
				frm.setStrStockingAmount(check.format("SIKINGAKU", "SI", frm.getVenderCd(), stockingAmount));

			} else {
				frm.setStrTaxAmount("0");
			}
		} else {
			frm.setStrTaxAmount("0");
		}
		//軽減税率対応
		if (frm.getTaxCdChangeFlg().equals("true")){
			frm.setTaxCd(frm.getTaxCd());
		}else {
			frm.setTaxCd(taxCd);
		}
		frm.setTaxCdChangeFlg("false");

		// 軽減措置金額計算用の税率を取得
		getReducedTaxRatio(frm);

		return mapping.findForward("success");
	}

	/**
	 * 軽減措置計算の税率を取得
	 * @param form AcceptBuyingDetailForm
	 */
	public void getReducedTaxRatio(final AcceptBuyingDetailForm frm) {
		String checkDate =  frm.getStrStockingDate();
		if( checkDate.length() == 6 ){
			checkDate = "20"+ checkDate;
		}
		// 税額控除割合を取得(名称マスタの期限と仕入日によって決定する)
		NamesDetail bean = namesDetailDao.getTaxFreeRatio(StringUtils.replace(checkDate, "/", ""));
		if(bean != null){
			frm.setInvoiceTaxRatio(bean.getNmqty01());
		} else {
			frm.setInvoiceTaxRatio(BigDecimal.ONE);
		}
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

	/**
	 * 仕入日から消費税率を取得する
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getTaxRatio(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getTaxRatio.");
		}
		AcceptBuyingDetailForm frm = (AcceptBuyingDetailForm) form;

		taxProc(frm);

		return mapping.findForward("success");
	}

	/**
	 * 消費税を取得しフォームにセットする
	 * @param frm フォーム
	 */
	public void taxProc(final AcceptBuyingDetailForm frm) {

		frm.setStrTaxRatio(getTaxRatioLogic.getTaxRatio(frm.getStrStockingDate()));
	}

	/**
	 *
	 * 品目から消費税区分を取得する
	 * @param itemCd 品目コード
	 * @return 消費税区分
	 */
	public String getTaxDivisionFromItem(final String itemCd) {

		return getTaxRatioLogic.getTaxDivisionFromItem(itemCd, "1");
	}

	/**
	 * 消費税率が有効か確認する
	 * @param date date
	 * @param taxRatio taxRatio
	 * @return true:有効 false:無効
	 */
	public boolean isValidTaxRatio(final String date, final String taxRatio) {
		return getTaxRatioLogic.isValidTax(date, taxRatio);
	}

	// 2014/1/29 新消費税対応 <-
	// 軽減税率対応
	/**
	 * 消費税率マスタコンボボックス取得
	 *
	 * @param frm
	 *            画面フォーム
	 * @param locale
	 *            言語コード
	 * @throws Exception
	 *            NoDataException
	 */
	private void setPurchaseTaxCombobox(AcceptBuyingDetailForm frm) throws NoDataException {
		// マスタのデータを取得(すべて)
		ComboboxesBean combBean = acceptBuyingDetailLogic.getPurchaseTaxCombobox();

		frm.setTaxLabels(combBean.getLabels());
		frm.setTaxValues(combBean.getValues());
		frm.setTaxKeys(combBean.getInvisibility());
	}


	/* -------------------- setter -------------------- */

	/**
	 * 仕入入力詳細ロジッククラスを設定します。
	 * @param acceptBuyingDetailLogic 仕入入力詳細ロジッククラス
	 */
	public void setAcceptBuyingDetailLogic(
			final AcceptBuyingDetailLogic acceptBuyingDetailLogic) {
		this.acceptBuyingDetailLogic = acceptBuyingDetailLogic;
	}

	// 2014/1/29 新消費税対応 ->
	/**
	 * 消費税ロジッククラスを設定します。
	 * @param getTaxRatioLogic getTaxRatioLogic
	 */
	public void setGetTaxRatioLogic(final GetTaxRatioLogic getTaxRatioLogic) {
		this.getTaxRatioLogic = getTaxRatioLogic;
	}

	/**
	 * purchaseSubcontractDaoを設定します。
	 * @param purchaseSubcontractDao purchaseSubcontractDao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}
	// 2014/1/29 新消費税対応 <-

	public void setNamesDetailDao(NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}
}
