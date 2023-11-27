/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.app.common.CommonLogic;
import com.asahikaseieng.dao.entity.sales.Sales;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailEntity;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailStandardEntity;
import com.asahikaseieng.dao.nonentity.salescancelcheckdeliveryupdate.SalesCancelCheckDeliveryUpdate;
import com.asahikaseieng.dao.nonentity.salescancelcheckinvoiceupdate.SalesCancelCheckInvoiceUpdate;
import com.asahikaseieng.dao.nonentity.salesinout.SalesGetInoutData;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 売上詳細(標準) Actionクラス.
 * @author tosco
 */
public final class SalesDetailStandardAction extends AbstractSalesDetailAction {

	/** 売上共通ロジッククラス */
	private SalesCommonsLogic salesCommonsLogic;

	/** 売上詳細共通ロジッククラス */
	private AbstractSalesDetailLogic salesDetailCommonLogic;

	/** 売上詳細(通常)ロジッククラス */
	private SalesDetailStandardLogic salesDetailStandardLogic;

	private CommonLogic commonLogic;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailStandardAction() {
		super();
	}

	/**
	 * 売上詳細画面初期表示処理(一覧画面の売上番号(標準)リンク押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	@Override
	protected ActionForward initProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALES,
			Constants.TAB_ID_SALES_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期検索
		SalesDetailStandardEntity bean = salesDetailStandardLogic.getEntity(frm
				.getSalesNo());

		// BeanをFormにコピーする
		setSalesData(frm, bean);

		// 画面表示時の受払番号、売上区分を保持
		frm.setBeforeCategoryDivision(frm.getCategoryDivision());

		// 区分が売上の場合
		if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_SALES)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {

			// 売上番号から受払データを取得
			SalesGetInoutData inoutData = salesDetailStandardLogic
					.getInoutData(frm.getSalesNo());

			// 受払データが取得できた場合
			if (inoutData != null) {

				frm.setInoutNo(inoutData.getInoutNo());
				frm.setInoutLotNo(inoutData.getLotNo());

				// 受払の数量（KG換算係数で割った値）
				frm.setInoutQty(salesDetailStandardLogic.getExchangeQty(
					inoutData.getInoutQty(), frm.getUnitDivision(), frm
							.getVenderCd()));

				// 受払の重量
				frm.setInoutWeight(salesDetailStandardLogic.getExchangeQty(
					inoutData.getInoutWeight(), frm.getUnitDivision(), frm
							.getVenderCd()));

				frm.setInoutDate(inoutData.getInoutDate());
				frm.setRyName(inoutData.getRyName());
				frm.setInputorName(inoutData.getInputorName());
				frm.setBeforeInoutNo(inoutData.getInoutNo());

			}
		}

		if (frm.getInoutNo() == null) {
			frm.setInoutNo("");
		}
		frm.setBeforeInoutNo(frm.getInoutNo());

		// 取消時の比較用に変更前の勘定年月を保持
		frm.setStrBeforeAccountYears(bean.getStrAccountYears());

		// 取消時の比較用に変更前の売上日を保持
		frm.setStrBeforeSalesDate(bean.getStrSalesDate());

		// 入庫ロケーションコンボボックス
		frm.setHousingLocationCombo(salesDetailCommonLogic
				.createHousingLocationComboboxForStandard());

		// 取消データの場合、取消元売上データの更新日時を取得
		if (Integer.parseInt(frm.getCategoryDivision()) < 0) {
			frm.setUpdateDateOrigin(salesDetailCommonLogic
					.getTimestampBySaleNo(frm.getCancelSalesNo()));
		}

		// 消費税のコンボボックスの設定
		setSalesTaxCombobox(frm);
		frm.setTaxCd(bean.getTaxCd());

		return mapping.findForward("success");
	}

	/**
	 * 売上詳細画面初期表示処理(一覧画面の新規ボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	@Override
	protected ActionForward initNewProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALES,
			Constants.TAB_ID_SALES_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 標準とする
		frm.setKeepFlag(SalesConst.KEEP_DIVISION_STANDARD);

		// 売上コンボボックス
		salesCommonsLogic.createCategoryDivisionComboboxRelated(frm);

		// 入庫ロケーションコンボボックス
		frm.setHousingLocationCombo(salesDetailCommonLogic
				.createHousingLocationComboboxForStandard());

		// 消費税のコンボボックスの設定
		setSalesTaxCombobox(frm);

		return mapping.findForward("success");
	}

	/**
	 * 売上日変更時処理（勘定年月取得）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getSalesBySalesDate(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getSalesBySalesDate.");
		}

		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;

		// 品目コードと得意先コードが設定されている場合、消費税コードを取得する。
		if (frm.getItemCd()!=null && frm.getVenderCd() != null){

			// 消費税区分を取得する
			frm.setTaxCd(commonLogic.getTaxCd(frm.getStrSalesDate(), frm.getItemCd(), "TS", frm.getVenderCd(), "SALES", null, null, null, null, null));

		}

		// 2014/3/11 新消費税対応 ->
		super.getTaxRatio(mapping, form, request, response);
		// 2014/3/11 新消費税対応 <-


		try {
			String accountYears = salesDetailCommonLogic.getAccountYears(frm.getVenderCd(), frm.getStrSalesDate());

			frm.setStrAccountYears(accountYears);


		} catch (NoDataException e) {
			// 得意先マスタに存在しないのでエラーとする
			frm.setStrAccountYears("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 得意先会計情報取得処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getSalesByVender(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getAccountsByVender.");
		}

		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;
		try {

			// 前受け金区分を取得
			frm.setArDivision(salesCommonsLogic
					.getArDivision(frm.getVenderCd()));

			// 売上コンボボックス
			salesCommonsLogic.createCategoryDivisionComboboxRelated(frm);

			// 前受金区分に応じて分類コードを変える（コンボボックスの初期値移動対応）
			getCateroryDivision(frm);

			SalesDetailEntity detail = salesDetailCommonLogic
					.getSalesByVender(frm.getVenderCd());

			// 担当部署情報
			frm.setChargeOrganizationCd(detail.getChargeOrganizationCd());
			frm.setChargeOrganizationName(detail.getChargeOrganizationName());
			// 仕訳設定(借方)
			setReversalSiwake(frm, detail, "vender");
			// 品目が入力されている場合は、数値チェック設定取得
			if (frm.getItemCheckedFlag()) {
				salesDetailCommonLogic.setCheckDigit(frm);
				salesDetailCommonLogic.formatDetail(frm);
			}

			// 新規処理の場合のみ処理
			if (frm.getInsertFlg() == 1) {

				String accountYears = salesDetailCommonLogic.getAccountYears(
					frm.getVenderCd(), frm.getStrSalesDate());
				// 勘定年月を取得
				frm.setStrAccountYears(accountYears);
			}

		} catch (NoDataException e) {
			// 得意先マスタに存在しないのでエラーとする
			frm.setStrAccountYears("");
			frm.setChargeOrganizationCd("");
			frm.setChargeOrganizationName("");
		}

		return mapping.findForward("success");
	}

	/**
	 *
	 * 得意先変更時分類コードを取得する
	 * @param frm
	 * @return 分類コード
	 */
	private void getCateroryDivision(final SalesDetailStandardForm frm) {

		String ret = frm.getCategoryDivision();

		// 売上区分売上の場合
		if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_SALES)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {

			// 前受金区分で分岐
			if (frm.getArDivision().equals(BigDecimal.ONE)) {
				ret = SalesConst.CATEGOTRY_DIVISION_SALES;
			} else {
				ret = SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE;

			}
		} else if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_RETURNS)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE)) {
			// 売上区分返品の場合

			// 前受金区分で分岐
			if (frm.getArDivision().equals(BigDecimal.ONE)) {
				ret = SalesConst.CATEGOTRY_DIVISION_RETURNS;
			} else {
				ret = SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE;

			}
		} else if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_DISCOUNT)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_DISCOUNT_ADVANCE)) {
			// 売上区分値引の場合

			// 前受金区分で分岐
			if (frm.getArDivision().equals(BigDecimal.ONE)) {
				ret = SalesConst.CATEGOTRY_DIVISION_DISCOUNT;
			} else {
				ret = SalesConst.CATEGOTRY_DIVISION_DISCOUNT_ADVANCE;

			}
		} else if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_CARRY)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_CARRY_ADVANCE)) {
			// 売上区分運賃の場合

			// 前受金区分で分岐
			if (frm.getArDivision().equals(BigDecimal.ONE)) {
				ret = SalesConst.CATEGOTRY_DIVISION_CARRY;
			} else {
				ret = SalesConst.CATEGOTRY_DIVISION_CARRY_ADVANCE;

			}
		} else if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_OTHER)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_OTHER_ADVANCE)) {
			// 売上区分その他の場合

			// 前受金区分で分岐
			if (frm.getArDivision().equals(BigDecimal.ONE)) {
				ret = SalesConst.CATEGOTRY_DIVISION_OTHER;
			} else {
				ret = SalesConst.CATEGOTRY_DIVISION_OTHER_ADVANCE;

			}

		}

		frm.setCategoryDivision(ret);
	}

	/**
	 * 品目会計情報取得処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getSalesByItem(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getAccountsByItem.");
		}

		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;

		try {
			SalesDetailStandardEntity detail = salesDetailStandardLogic.getSalesByItem(frm.getItemCd());
			// 仕訳反転(貸方)
			setReversalSiwake(frm, (SalesDetailStandardEntity) detail, "item");
			// 品目チェック済みとする
			frm.setItemCheckedFlag(true);
			// 数値チェック設定取得
			salesDetailCommonLogic.setCheckDigit(frm);
			salesDetailCommonLogic.formatDetail(frm);

		} catch (NoDataException e) {
			frm.setItemCheckedFlag(false);
		}

		// 品目コードと得意先コードが設定されている場合、消費税コードを取得する。
		if (frm.getItemCd()!=null && frm.getVenderCd() != null){

			// 消費税区分を取得する
			frm.setTaxCd(commonLogic.getTaxCd(frm.getStrSalesDate(), frm.getItemCd(), "TS", frm.getVenderCd(), "SALES", null, null, null, null, null));

		}

		// 品目コードが存在する場合のみ単価取得処理を行う
		if (frm.getItemCd() != null) {

			// 有効単価取得
			super.getValidUnitprice(frm);


			// 2014/2/5 新消費税対応 ->
			frm.setStrTaxDivision(super.getTaxDivisionFromItem(frm.getItemCd()));
			// 2014/2/5 新消費税対応 <-
		}
		return mapping.findForward("success");
	}

	/**
	 * 有効単価設定処理
	 * @param form AbstractSalesDetailForm
	 * @param bean 有効単価検索結果
	 * @return AbstractSalesDetailForm
	 */
	@Override
	protected AbstractSalesDetailForm setValidUnitprice(
			final VaridUnitprice bean, final AbstractSalesDetailForm form) {

		// form.setStrSalesQuantity("0"); // 売上数量(文字列)

		if (bean == null || bean.getTmpUnitpriceFlg().equals("1")) {
			// 有効な単価が無い場合
			if (bean == null) {
				form.setStandardUnitprice(BigDecimal.ZERO); // 標準単価
			} else {
				form.setStandardUnitprice(bean.getStandardUnitPrice()); // 標準単価
			}
			form.setStandardDiscount(BigDecimal.ZERO); // 標準値引
			form.setSpecialDiscount(BigDecimal.ZERO); // 特別値引
		} else {
			// 有効な単価が在る場合
			form.setStandardUnitprice(bean.getStandardUnitPrice()); // 標準単価
			form.setStandardDiscount(bean.getStandardDiscount()); // 標準値引
			form.setSpecialDiscount(bean.getSpecialDiscount()); // 特別値引
		}

		// 区分が返品の場合標準単価無し
		if (form.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_RETURNS)
				|| form.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE)
				|| form.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_RETURNS_CANCEL)
				|| form.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_RETURNS_CANCEL_ADVANCE)) {
			form.setStandardUnitprice(BigDecimal.ZERO); // 標準単価
		}
		form.setTmpUnitpriceFlg(false); // 正単価
		return form;
	}

	/**
	 * 売上(標準)新規登録処理(詳細画面の登録ボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;

		ActionMessages errors = new ActionMessages();

		/* 2013/04/05 連続登録の不具合対応 */
		// 前受け金区分を取得
		frm.setArDivision(salesCommonsLogic.getArDivision(frm.getVenderCd()));

		getCateroryDivision(frm);
		/* 2013/04/05 連続登録の不具合対応 */
		// 2014/2/26 新消費税対応 <-
		// 消費税率が新消費税率適応開始日以前の場合エラーとする
		if (!super.isValidTaxRatio(frm.getStrSalesDate(), frm.getStrTaxRatio())) {
			errors.add("", new ActionMessage("errors.valid.tax.ratio"));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		// 2014/2/26 新消費税対応 ->

		// 売上区分が[1:売上]の場合
		if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_SALES)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {

			// 受払選択されている場合数量チェック
			if (frm.getInoutNo() != null && !frm.getInoutNo().equals("")) {
				// 売上数量と受払数量が異なる場合エラー
				BigDecimal stock = new BigDecimal(StringUtils.replace(frm
						.getStrSalesQuantity(), ",", "")); // 売上数量
				BigDecimal inout = new BigDecimal(StringUtils.replace(frm
						.getInoutQty(), ",", "")).multiply(new BigDecimal(-1)); // 受入数量

				if (stock.compareTo(inout) != 0) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.sales.qty.notsame"));
					saveErrors(request, errors);
					return mapping.findForward("error");
				}
			}

		}

		// マスタチェック
		if (!isCheckMasterForInsert(frm, errors)) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 数値チェックエラー
		if (!salesDetailStandardLogic.checkDigitForSales(frm, errors)) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 預り品フラグを取得（預り品品目を入力した場合、預り品フラグを立てたいが立てたら預り品の明細画面になってしまうのでコメントアウト
		// frm
		// .setKeepFlag(salesDetailCommonLogic.getKeepDivision(frm
		// .getItemCd()));

		LoginInfo loginInfo = getLoginInfo(request);

		try {
			// 登録処理を実行
			salesDetailStandardLogic.insert(frm, loginInfo);
		} catch (SalesLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				salesCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), loginInfo
						.getTantoCd());
			}
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("initContinue");
	}

	/**
	 * 売上(標準)更新処理(詳細画面の登録ボタン押下時)
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
			getLog().debug("update.");
		}

		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;

		ActionMessages errors = new ActionMessages();
		// 2014/2/26 新消費税対応 <-
		// 消費税率が新消費税率適応開始日以前の場合エラーとする
		if (!super.isValidTaxRatio(frm.getStrSalesDate(), frm.getStrTaxRatio())) {
			errors.add("", new ActionMessage("errors.valid.tax.ratio"));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		// 2014/2/26 新消費税対応 ->
		// 売上区分が[1:売上]の場合
		if (frm.getCategoryDivision().equals(
			SalesConst.CATEGOTRY_DIVISION_SALES)
				|| frm.getCategoryDivision().equals(
					SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE)) {

			// 受払が選択されている場合のみ数量のチェックを行う
			if (frm.getInoutNo() != null && !frm.getInoutNo().equals("")) {

				// 売上数量と受払数量が異なる場合エラー
				BigDecimal stock = new BigDecimal(StringUtils.replace(frm
						.getStrSalesQuantity(), ",", "")); // 売上数量
				BigDecimal inout = new BigDecimal(StringUtils.replace(frm
						.getInoutQty(), ",", "")).multiply(new BigDecimal(-1)); // 受入数量

				if (stock.compareTo(inout) != 0) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.sales.qty.notsame"));
					saveErrors(request, errors);
					return mapping.findForward("error");
				}
			}
		}

		// マスタチェック
		if (!isCheckMasterForUpdate(frm, errors)) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新時は品目が変更されないので、
		// 数値チェックはvalidateでのチェックのみとし、ここでは行わない

		// ログイン担当者コード取得
		LoginInfo loginInfo = getLoginInfo(request);

		try {
			// 更新処理を実行
			salesDetailStandardLogic.update(frm, loginInfo);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (SalesLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				salesCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), loginInfo
						.getTantoCd());
			}
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");
	}

	/**
	 * 売上削除処理(詳細画面の削除ボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}
		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;

		// ログイン担当者コード取得
		LoginInfo loginInfo = getLoginInfo(request);

		try {
			// 削除処理を実行
			salesDetailStandardLogic.delete(frm, loginInfo);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (SalesLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				salesCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), loginInfo
						.getTantoCd());
			}
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 売上取消処理(詳細画面の取消ボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward cancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("cancel.");
		}
		SalesDetailStandardForm frm = (SalesDetailStandardForm) form;
		boolean error = false;

		// ログイン担当者コード取得
		LoginInfo loginInfo = getLoginInfo(request);

		// 売上日のチェック処理
		if (frm.getStrSalesDate().compareTo(frm.getStrBeforeSalesDate()) <= 0) {
			ActionMessages messages = new ActionMessages();
			messages
					.add("", new ActionMessage("errors.sales.cancel.salesdate"));
			addErrors(request, messages);
			error = true;
		}

		// 勘定年月のチェック処理
		if (frm.getStrAccountYears().compareTo(frm.getStrBeforeAccountYears()) < 0) {
			ActionMessages messages = new ActionMessages();
			messages.add("", new ActionMessage(
					"errors.sales.cancel.accountyers"));
			addErrors(request, messages);
			error = true;
		}

		// 勘定年月及び売上日のチェックでエラーがある場合更新処理をしない
		if (error) {
			return mapping.getInputForward();
		}

		ActionMessages messages = new ActionMessages();

		// 売掛締め日と請求締め日のチェック
		Sales sales = salesDetailCommonLogic.getSalesData(frm.getSalesNo());

		// 売掛締め日を取得
		SalesCancelCheckDeliveryUpdate delivery = salesDetailCommonLogic
				.getMaxDeliveryUpdateDate(sales.getInvoiceCd(), frm
						.getStrSalesDate());

		// 売掛締め日を取得
		SalesCancelCheckInvoiceUpdate invoice = salesDetailCommonLogic
				.getMaxInvoiceUpdateDate(sales.getInvoiceCd(), frm
						.getStrSalesDate());

		// 売上日より後に締め処理が行われている場合
		if (delivery.getDeliveryUpdateDate() != null
				|| invoice.getInvoiceUpdateDate() != null) {

			// 売掛更新と請求更新両方行われている場合
			if (delivery.getDeliveryUpdateDate() != null
					&& invoice.getInvoiceUpdateDate() != null) {

				if (delivery.getDeliveryUpdateDate().toString().compareTo(
					invoice.getInvoiceUpdateDate().toString()) < 0) {

					// 請求更新の方が日付が新しい場合
					messages.add("", new ActionMessage(
							"errors.sales.cancel.invoice.update", AecDateUtils
									.dateFormat(invoice.getInvoiceUpdateDate(),
										"yyyy/MM/dd")));

				} else {

					// 売掛更新の方が日付が新しい場合
					messages.add("", new ActionMessage(
							"errors.sales.cancel.delivery.update", AecDateUtils
									.dateFormat(delivery
											.getDeliveryUpdateDate(),
										"yyyy/MM/dd")));

				}
			} else { // 売掛更新または請求更新が行われている場合
				if (delivery.getDeliveryUpdateDate() != null) { // 売掛更新が行われている場合
					messages.add("", new ActionMessage(
							"errors.sales.cancel.delivery.update", AecDateUtils
									.dateFormat(delivery
											.getDeliveryUpdateDate(),
										"yyyy/MM/dd")));
				} else if (invoice.getInvoiceUpdateDate() != null) { // 請求更新が行われている場合
					messages.add("", new ActionMessage(
							"errors.sales.cancel.invoice.update", AecDateUtils
									.dateFormat(invoice.getInvoiceUpdateDate(),
										"yyyy/MM/dd")));
				}

			}

		}

		// エラーメッセージ出力
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		try {
			// 取消処理を実行
			salesDetailStandardLogic.cancel(frm, loginInfo);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (SalesLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				salesCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), loginInfo
						.getTantoCd());
			}
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.sales.complete.cancel");

		return mapping.findForward("back");
	}

	/**
	 * 登録時マスタチェック処理
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isCheckMasterForInsert(final SalesDetailStandardForm frm,
			final ActionMessages errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 取引先マスタ
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			if (!salesDetailStandardLogic.isExistsVender(frm.getVenderCd())) {
				// データが存在しない場合
				errors.add("", new ActionMessage("errors.nodata.master", rb
						.getString("item.sales.vender.cd")));
			}
		}

		// 品目マスタ
		salesDetailCommonLogic.checkItemForRegist(frm, errors);

		// 会計関連チェック
		salesDetailCommonLogic.checkAccountsForRegist(frm, errors);

		// 理由マスタチェック
		salesDetailCommonLogic.checkReasonForRegist(frm, errors);

		if (!errors.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * 更新時マスタチェック処理
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isCheckMasterForUpdate(final SalesDetailStandardForm frm,
			final ActionMessages errors) {
		// 会計関連チェック
		salesDetailCommonLogic.checkAccountsForRegist(frm, errors);

		// 理由マスタチェック
		salesDetailCommonLogic.checkReasonForRegist(frm, errors);

		if (!errors.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * 売上検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param bean 売上検索結果
	 * @return SalesDetailStandardForm
	 */
	private SalesDetailStandardForm setSalesData(
			final SalesDetailStandardForm form,
			final SalesDetailStandardEntity bean) {
		super.setSalesCommonInfo(form, bean);

		// 数値チェック設定取得
		salesDetailCommonLogic.setCheckDigit(form);
		salesDetailCommonLogic.formatDetail(form);
		return form;
	}

	/**
	 * 売上区分により仕訳を反転して設定する。 [品目の場合] 売上区分のデータ集計区分＝2:返品,3:値引の場合 借方に設定
	 * 売上区分のデータ集計区分＝上記以外の場合 貸方に設定 [得意先の場合] 売上区分のデータ集計区分＝2:返品,3:値引の場合 貸方に設定
	 * 売上区分のデータ集計区分＝上記以外の場合 借方に設定
	 *
	 * @param frm 売上入力画面フォーム
	 * @param getBean 品目関連Bean または 得意先関連Bean
	 * @param value "item":品目、"vender":得意先
	 */
	private void setReversalSiwake(final SalesDetailStandardForm frm,
			final SalesDetailEntity getBean, final String value) {
		String selCategoryDivision = frm.getCategoryDivision();
		String reversalFlg = null;

		int index = 0;
		for (String categoryDivision : frm.getCategoryDivisionList()) {
			// 選択した仕入(分類コード)に対する反転フラグ取得
			if (selCategoryDivision.equals(categoryDivision)) {
				reversalFlg = frm.getReversalFlgList()[index];
				break;
			}
			index++;
		}

		if ("item".equals(value)) {
			// 品目の場合
			if ("1".equals(reversalFlg)) {
				// 反転あり
				frm.setAccountDebitSectionCd(getBean
						.getAccountCreditSectionCd()); // 借方
				frm.setAccountDebitSectionName(getBean
						.getAccountCreditSectionName()); // 借方
				frm.setDebitTitleCd(getBean.getCreditTitleCd()); // 借方
				frm.setDebitTitleName(getBean.getCreditTitleName()); // 借方
			} else {
				// 反転なし
				frm.setAccountCreditSectionCd(getBean
						.getAccountCreditSectionCd()); // 貸方
				frm.setAccountCreditSectionName(getBean
						.getAccountCreditSectionName()); // 貸方
				frm.setCreditTitleCd(getBean.getCreditTitleCd()); // 貸方
				frm.setCreditTitleName(getBean.getCreditTitleName()); // 貸方
			}
		} else {
			// 仕入先の場合
			if ("1".equals(reversalFlg)) {
				// 反転あり
				frm.setAccountCreditSectionCd(getBean
						.getAccountDebitSectionCd()); // 貸方
				frm.setAccountCreditSectionName(getBean
						.getAccountDebitSectionName()); // 貸方
				frm.setCreditTitleCd(getBean.getDebitTitleCd()); // 貸方
				frm.setCreditTitleName(getBean.getDebitTitleName()); // 貸方
			} else {
				// 反転なし
				frm
						.setAccountDebitSectionCd(getBean
								.getAccountDebitSectionCd()); // 借方
				frm.setAccountDebitSectionName(getBean
						.getAccountDebitSectionName()); // 借方
				frm.setDebitTitleCd(getBean.getDebitTitleCd()); // 借方
				frm.setDebitTitleName(getBean.getDebitTitleName()); // 借方
			}
		}
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

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
	private void setSalesTaxCombobox(SalesDetailStandardForm frm) throws NoDataException {
		// マスタのデータを取得(すべて)
		ComboboxesBean combBean = salesDetailCommonLogic.getSalesTaxCombobox();

		frm.setTaxLabels(combBean.getLabels());
		frm.setTaxValues(combBean.getValues());
		frm.setTaxKeys(combBean.getInvisibility());
	}

	/* -------------------- setter -------------------- */
	/**
	 * 売上共通ロジッククラスを設定します。
	 * @param salesCommonsLogic 売上共通ロジッククラス
	 */
	public void setSalesCommonLogic(final SalesCommonsLogic salesCommonsLogic) {
		this.salesCommonsLogic = salesCommonsLogic;
	}

	/**
	 * 売上詳細(通常)ロジッククラスを設定します。
	 * @param salesDetailStandardLogic 売上詳細(通常)ロジッククラス
	 */
	public void setSalesDetailStandardLogic(
			final SalesDetailStandardLogic salesDetailStandardLogic) {
		this.salesDetailStandardLogic = salesDetailStandardLogic;
		this.salesDetailCommonLogic = (AbstractSalesDetailLogic) salesDetailStandardLogic;
	}

	/**
	 * commonLogicを設定します。
	 * @param commonLogic commonLogic
	 */
	public void setCommonLogic(CommonLogic commonLogic) {
		this.commonLogic = commonLogic;
	}

}
