/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.app.common.CommonLogic;
import com.asahikaseieng.dao.entity.sales.Sales;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailEntity;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepEntity;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepSalesInoutRecordList;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepVenderList;
import com.asahikaseieng.dao.nonentity.salescancelcheckdeliveryupdate.SalesCancelCheckDeliveryUpdate;
import com.asahikaseieng.dao.nonentity.salescancelcheckinvoiceupdate.SalesCancelCheckInvoiceUpdate;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 売上詳細(預り品／受注番号あり) Actionクラス.
 * @author tosco
 */
public final class SalesDetailKeepAction extends AbstractSalesDetailAction {

	/** 売上詳細共通ロジッククラス */
	private AbstractSalesDetailLogic salesDetailCommonLogic;

	/** 売上詳細(預り品)ロジッククラス */
	private SalesDetailKeepLogic salesDetailKeepLogic;

	/** 売上共通ロジッククラス */
	private SalesCommonsLogic salesCommonsLogic;

	private CommonLogic commonLogic;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailKeepAction() {
		super();
	}

	/**
	 * 売上詳細画面初期表示処理(一覧画面の売上番号(預り品)リンク押下時)
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

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALES,
			Constants.TAB_ID_SALES_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期検索
		SalesDetailKeepEntity bean = salesDetailKeepLogic.getEntity(frm
				.getSalesNo());

		// BeanをFormにコピーする
		setSalesData(frm, bean);

		// 取消時の比較用に変更前の勘定年月を保持
		frm.setStrBeforeAccountYears(bean.getStrAccountYears());

		// 取消時の比較用に変更前の売上日を保持
		frm.setStrBeforeSalesDate(bean.getStrSalesDate());

		// 変更の場合は、品目コードが変更できないので同じ品目コードを設定
		frm.setPrevItemCd(frm.getItemCd());

		// 入庫ロケーションコンボボックス
		frm.setHousingLocationCombo(salesDetailCommonLogic
				.createHousingLocationComboboxForKeep());

		// 消費税のコンボボックスの設定
		setSalesTaxCombobox(frm);
		frm.setTaxCd(bean.getTaxCd());

		// 取消データの場合、取消元売上データの更新日時を取得
		if (Integer.parseInt(frm.getCategoryDivision()) < 0) {
			frm.setUpdateDateOrigin(salesDetailCommonLogic
					.getTimestampBySaleNo(frm.getCancelSalesNo()));
		}

		return mapping.findForward("success");
	}

	/**
	 * 売上詳細画面初期表示処理(一覧画面の新規(預り品)ボタン押下時)
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

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALES,
			Constants.TAB_ID_SALES_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 預り品とする
		frm.setKeepFlag(SalesConst.KEEP_DIVISION_KEEP);

		// 売上区分
		frm.setCategoryDivision(SalesConst.CATEGOTRY_DIVISION_SALES);
		frm.setCategoryName(salesDetailKeepLogic.getCategoryName(frm
				.getCategoryDivision()));

		// 入庫ロケーションコンボボックス
		frm.setHousingLocationCombo(salesDetailCommonLogic
				.createHousingLocationComboboxForKeep());

		// 消費税のコンボボックスの設定
		setSalesTaxCombobox(frm);
		frm.setTaxCd(frm.getTaxCd());

		return mapping.findForward("success");
	}

	/**
	 * 納入先関連情報取得処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getSalesByDelivery(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getSalesByDelivery.");
		}

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		// // 品目コードが未チェックの場合は何もしない
		// if (!frm.getItemCheckedFlag()) {
		// return mapping.findForward("success");
		// }

		// 品目関連データクリア処理
		/** 品目コード */
		frm.setItemCd(null);
		/** 品目名称 */
		frm.setItemName(null);
		/** 他社コード1 */
		frm.setOtherCompanyCd1(null);
		/** 荷姿 */
		frm.setStyleOfPacking(null);
		/** 売上数量(文字列) */
		frm.setStrSalesQuantity(null);
		/** 売上単価(文字列) */
		frm.setStrSalesUnitprice(null);
		/** 標準単価 */
		frm.setStandardUnitprice(BigDecimal.ZERO);
		/** 標準値引 */
		frm.setStandardDiscount(BigDecimal.ZERO);
		/** 特別値引 */
		frm.setSpecialDiscount(BigDecimal.ZERO);
		/** 標準単価(文字列) */
		frm.setStrStandardUnitprice(null);
		/** 標準値引(文字列) */
		frm.setStrStandardDiscount(null);
		/** 特別値引(文字列) */
		frm.setStrSpecialDiscount(null);
		/** 売上金額 */
		frm.setStrSalesAmount(null);
		/** 仮単価FLG */
		frm.setTmpUnitpriceFlg(false);

		frm.setBalanceCd(null);
		// 得意先情報をクリア
		frm.setVenderList(new ArrayList<SalesDetailKeepVenderList>());
		frm.setVenderCd(null);
		// 担当部署情報をクリア
		frm.setChargeOrganizationCd(null);
		frm.setChargeOrganizationName(null);

		// 会計部門と勘定科目をクリア
		frm.setAccountCreditSectionCd(null);
		frm.setAccountCreditSectionName(null);
		frm.setCreditTitleCd(null);
		frm.setCreditTitleName(null);
		frm.setAccountDebitSectionCd(null);
		frm.setAccountDebitSectionName(null);
		frm.setDebitTitleCd(null);
		frm.setDebitTitleName(null);

		// 納入先が決定した時点で品目データを削除するので帳合取得処理は不要
		// 帳合コード及び帳合コード関連情報を取得
		// getBalanceInfo(frm);

		return mapping.findForward("success");
	}

	/**
	 * 品目関連情報取得処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getSalesByItem(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getSalesByItem.");
		}

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		// 品目情報取得
		boolean res = getItemInfo(frm, request);
		if (!res) {
			return mapping.findForward("error");
		}

		// 帳合コード及び帳合コード関連情報を取得
		getBalanceInfo(frm);

		// 変更した品目コードを設定
		frm.setPrevItemCd(frm.getItemCd());

		// 新規の場合のみ勘定年月を取得
		if (frm.getInsertFlg() == 1) {

			String accountYears = salesDetailCommonLogic.getAccountYears(frm.getVenderCd(), frm.getStrSalesDate());

			frm.setStrAccountYears(accountYears);
		}
		// 品目コードと得意先コードが設定されている場合、消費税コードを取得する。
		if (frm.getItemCd()!=null && frm.getVenderCd() != null){

			// 消費税区分を取得する
			frm.setTaxCd(commonLogic.getTaxCd(frm.getStrSalesDate(), frm.getItemCd(), "TS", frm.getVenderCd(), "SALES", null, null, null, null,null));

		}

		// 2014/2/5 新消費税対応 ->
		frm.setStrTaxDivision(super.getTaxDivisionFromItem(frm.getItemCd()));
		// 2014/2/5 新消費税対応 <-
		return mapping.findForward("success");
	}

	/**
	 * 行追加処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addRow.");
		}

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;
		List<SalesDetailKeepSalesInoutRecordList> list = frm.getLocationList();

		// 最初に行を追加する場合のみチェックする
		if (list.size() < 1) {
			// 品目情報取得
			if (!getItemInfoForAddRow(frm, request)) {
				return mapping.findForward("error");
			}
			if (!frm.getItemCd().equals(frm.getPrevItemCd())) {
				// 帳合コード及び帳合コード関連情報を取得
				getBalanceInfoForAddRow(frm);
				if (StringUtils.isEmpty(frm.getBalanceCd())) {
					// 帳合コードが取得できない場合、エラーとする
					ActionMessages messages = new ActionMessages();
					messages.add("", new ActionMessage(
							"errors.sales.sales.terms"));
					addErrors(request, messages);
					return mapping.findForward("error");
				}
			}
		}

		int len = list.size();
		boolean addFlg = false;
		for (int i = 0; i < len; i++) {
			SalesDetailKeepSalesInoutRecordList bean = list.get(i);
			if (bean.isCheckFlg()) {
				// チェックされている場合、その行の上に追加
				list.add(i, getNewLocationLine());
				addFlg = true;
				break;
			}
		}
		// 行が追加されていない場合、最後に追加
		if (!addFlg) {
			list.add(getNewLocationLine());
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delRow.");
		}

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		List<SalesDetailKeepSalesInoutRecordList> list = frm.getLocationList();
		List<SalesDetailKeepSalesInoutRecordList> delList = frm
				.getDeleteLocationList();
		int len = list.size();
		for (int i = len - 1; i >= 0; i--) {
			SalesDetailKeepSalesInoutRecordList bean = list.get(i);
			if (bean.isCheckFlg()) {

				// 削除行をリストに削除リストに追加
				delList.add(bean);

				// 対象行をリストから削除
				list.remove(i);
			}
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

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

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;
		try {
			String accountYears = salesDetailCommonLogic.getAccountYears(frm.getVenderCd(), frm.getStrSalesDate());

			// 勘定年月を取得
			frm.setStrAccountYears(accountYears);

			// 品目コードと得意先コードが設定されている場合、消費税コードを取得する。
			if (frm.getItemCd()!=null && frm.getVenderCd() != null){

				// 消費税区分を取得する
				frm.setTaxCd(commonLogic.getTaxCd(frm.getStrSalesDate(), frm.getItemCd(), "TS", frm.getVenderCd(), "SALES", null, null, null, null, null));

			}
			// 新規の場合のみ有効単価取得処理
			if (frm.getInsertFlg() == 1) {

				// 有効単価取得
				super.getValidUnitprice(frm);

			}
			// 2014/3/11 新消費税対応 ->
			super.getTaxRatio(mapping, form, request, response);
			// 2014/3/11 新消費税対応 <-

		} catch (NoDataException e) {
			// 得意先マスタに存在しないのでエラーとする
			frm.setStrAccountYears("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 有効単価変更処理(売上日変更時)(未使用）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeValidUnitpriceBySalesDate(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("changeValidUnitpriceBySalesDate.");
		}

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		// 有効単価取得
		super.getValidUnitprice(frm);

		return mapping.findForward("success");
	}

	/**
	 * 売上(預り品)新規登録処理(詳細画面の登録ボタン押下時)
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
		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		ActionMessages errors = new ActionMessages();

		// 2014/2/26 新消費税対応 ->
		// 消費税率が新消費税率適応開始日以前の場合エラーとする
		if (!super.isValidTaxRatio(frm.getStrSalesDate(), frm.getStrTaxRatio())) {
			errors.add("", new ActionMessage("errors.valid.tax.ratio"));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		// 2014/2/26 新消費税対応 ->

		// 入力チェック
		if (!isCheckMasterForInsert(frm, errors, request)) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		if (salesCommonsLogic.getArDivision(frm.getVenderCd()).equals(
			BigDecimal.ONE)) {
			frm.setCategoryDivision(SalesConst.CATEGOTRY_DIVISION_SALES);
		} else {
			frm
					.setCategoryDivision(SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE);
		}

		// 預り品の画面では、品目が確定していない状態ではvalidateでエラーとなるので
		// 数値チェックはvalidateでのチェックのみとし、ここでは行わない

		// 合計値チェック
		if (!isSumCheck(frm, errors)) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}

		LoginInfo loginInfo = getLoginInfo(request);

		// 登録処理を実行
		try {
			salesDetailKeepLogic.insert(frm, loginInfo);
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

		frm.setInsertFlg(0);

		// メッセージ
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("initContinue");
	}

	/**
	 * 出荷指図更新処理(詳細画面の更新ボタン押下時)
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
		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		ActionMessages errors = new ActionMessages();
		// 2014/2/26 新消費税対応 <-
		// 消費税率が新消費税率適応開始日以前の場合エラーとする
/*		if (!super.isValidTaxRatio(frm.getStrSalesDate(), frm.getStrTaxRatio())) {
			errors.add("", new ActionMessage("errors.valid.tax.ratio"));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}*/
		// 2014/2/26 新消費税対応 ->
		// 入力チェック
		if (!isCheckMasterForUpdate(frm, errors, request)) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新時は品目が変更されないので、
		// 数値チェックはvalidateでのチェックのみとし、ここでは行わない

		// 合計値チェック
		if (!isSumCheck(frm, errors)) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// ログイン情報取得
		LoginInfo loginInfo = getLoginInfo(request);

		try {
			// 更新処理を実行
			salesDetailKeepLogic.update(frm, loginInfo);
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
		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		// ログイン担当者コード取得
		LoginInfo loginInfo = getLoginInfo(request);

		try {
			// 削除処理を実行
			salesDetailKeepLogic.delete(frm, loginInfo);
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
		boolean error = false;

		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		// ログイン情報取得
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
			salesDetailKeepLogic.cancel(frm, loginInfo);
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
	 * 仮単価フラグ更新処理(詳細画面の仮単価ボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward tmpUnitprice(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("tmpUnitprice.");
		}
		SalesDetailKeepForm frm = (SalesDetailKeepForm) form;

		// ログイン情報取得
		LoginInfo loginInfo = getLoginInfo(request);

		try {
			// 更新処理を実行
			salesDetailKeepLogic.tmpUnitflagUpdate(frm, loginInfo);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (NotSingleRowUpdatedRuntimeException e){
			saveError(request, "errors.optimisticlock.data");
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");
	}

	/**
	 * 品目情報取得処理
	 * @param frm 売上詳細画面FORM
	 * @param request HttpServletRequest
	 * @return SalesDetailKeepForm
	 * @throws NoDataException データが存在しない例外
	 */
	private boolean getItemInfo(final SalesDetailKeepForm frm,
			final HttpServletRequest request) throws NoDataException {
		boolean res = false;

		try {
			// 品目コードより会計情報を取得
			SalesDetailKeepEntity detail = salesDetailKeepLogic
					.getSalesByItem(frm.getItemCd());
			if (SalesConst.KEEP_DIVISION_KEEP.equals(detail.getKeepDivision())) {
				// 預かり品の場合、品目の情報をセットする
				frm.setAccountCreditSectionCd(detail
						.getAccountCreditSectionCd());
				frm.setAccountCreditSectionName(detail
						.getAccountCreditSectionName());
				frm.setCreditTitleCd(detail.getCreditTitleCd());
				frm.setCreditTitleName(detail.getCreditTitleName());
				frm.setItemName(detail.getItemName());
				frm.setOtherCompanyCd1(detail.getOtherCompanyCd1());
				frm.setStyleOfPacking(detail.getStyleOfPacking());
				frm.setUnitDivision(detail.getUnitDivision());
				res = true;
			} else {
				// 預かり品でない場合、エラーとする
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage(
						"errors.sales.keep.division.not.keep",
						getMessageResource(request, "item.sales.item.cd")));
				addErrors(request, messages);
			}
		} catch (NoDataException e) {
			frm.setItemCheckedFlag(false);
			// 品目マスタに存在しないのでエラーとする
			ActionMessages messages = new ActionMessages();
			messages.add("", new ActionMessage("errors.nodata.master",
					getMessageResource(request, "item.sales.item.cd")));
			addErrors(request, messages);
		}
		// 品目チェック済み状態をセット
		frm.setItemCheckedFlag(res);
		return res;
	}

	/**
	 * 品目情報取得処理(行追加時) 会計部門、勘定科目ともに未設定の場合のみ再設定
	 * @param frm 売上詳細画面FORM
	 * @param request HttpServletRequest
	 * @return SalesDetailKeepForm
	 * @throws NoDataException データが存在しない例外
	 */
	private boolean getItemInfoForAddRow(final SalesDetailKeepForm frm,
			final HttpServletRequest request) throws NoDataException {
		boolean res = false;

		try {
			// 品目コードより会計情報を取得
			SalesDetailKeepEntity detail = salesDetailKeepLogic
					.getSalesByItem(frm.getItemCd());
			if (SalesConst.KEEP_DIVISION_KEEP.equals(detail.getKeepDivision())) {
				// 預かり品の場合、品目の情報をセットする
				if (StringUtils.isEmpty(frm.getAccountCreditSectionCd())
						&& StringUtils.isEmpty(frm.getCreditTitleCd())) {
					// 会計部門、勘定科目ともに未設定の場合のみ再設定
					frm.setAccountCreditSectionCd(detail
							.getAccountCreditSectionCd());
					frm.setAccountCreditSectionName(detail
							.getAccountCreditSectionName());
					frm.setCreditTitleCd(detail.getCreditTitleCd());
					frm.setCreditTitleName(detail.getCreditTitleName());
				}
				frm.setItemName(detail.getItemName());
				frm.setOtherCompanyCd1(detail.getOtherCompanyCd1());
				frm.setStyleOfPacking(detail.getStyleOfPacking());
				frm.setUnitDivision(detail.getUnitDivision());
				res = true;
			} else {
				// 預かり品でない場合、エラーとする
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage(
						"errors.sales.keep.division.not.keep",
						getMessageResource(request, "item.sales.item.cd")));
				addErrors(request, messages);
			}
		} catch (NoDataException e) {
			frm.setItemCheckedFlag(false);
			// 品目マスタに存在しないのでエラーとする
			ActionMessages messages = new ActionMessages();
			messages.add("", new ActionMessage("errors.nodata.master",
					getMessageResource(request, "item.sales.item.cd")));
			addErrors(request, messages);
		}
		// 品目チェック済み状態をセット
		frm.setItemCheckedFlag(res);
		return res;
	}

	/**
	 * 帳合コード及び帳合コード関連情報取得処理
	 * @param frm 売上詳細画面FORM
	 * @return SalesDetailKeepForm
	 * @throws Exception Exception
	 */
	private SalesDetailKeepForm getBalanceInfo(final SalesDetailKeepForm frm)
			throws Exception {

		// 帳合コード情報取得
		try {
			getBalance(frm);
		} catch (NoDataException e) {
			frm.setBalanceCd(null);
			// 得意先情報をクリア
			frm.setVenderList(new ArrayList<SalesDetailKeepVenderList>());
			frm.setVenderCd(null);
			// 担当部署情報をクリア
			frm.setChargeOrganizationCd(null);
			frm.setChargeOrganizationName(null);
		}

		// 得意先を取得する
		try {
			getVenderList(frm);
		} catch (NoDataException e) {
			// 得意先情報をクリア
			frm.setVenderList(new ArrayList<SalesDetailKeepVenderList>());
			frm.setVenderCd(null);
		}

		// 数値チェック設定取得
		salesDetailCommonLogic.setCheckDigit(frm);
		salesDetailCommonLogic.formatDetail(frm);

		// 有効単価取得
		super.getValidUnitprice(frm);

		// 得意先より会計部門等を取得する
		try {
			getSalesByVender(frm);
		} catch (NoDataException e) {
			// 担当部署情報のみクリアする
			frm.setChargeOrganizationCd(null);
			frm.setChargeOrganizationName(null);
		}

		return frm;
	}

	/**
	 * 帳合コード及び帳合コード関連情報取得処理(行追加時)
	 * @param frm 売上詳細画面FORM
	 * @return SalesDetailKeepForm
	 * @throws Exception Exception
	 */
	private SalesDetailKeepForm getBalanceInfoForAddRow(
			final SalesDetailKeepForm frm) throws Exception {

		// 帳合コード情報取得
		try {
			getBalance(frm);
		} catch (NoDataException e) {
			frm.setBalanceCd(null);
			// 得意先情報をクリア
			frm.setVenderList(new ArrayList<SalesDetailKeepVenderList>());
			frm.setVenderCd(null);
			// 担当部署情報をクリア
			frm.setChargeOrganizationCd(null);
			frm.setChargeOrganizationName(null);
		}

		// 得意先を取得する
		try {
			getVenderList(frm);
		} catch (NoDataException e) {
			// 得意先情報をクリア
			frm.setVenderList(new ArrayList<SalesDetailKeepVenderList>());
			frm.setVenderCd(null);
		}

		// 数値チェック設定取得
		salesDetailCommonLogic.setCheckDigit(frm);
		salesDetailCommonLogic.formatDetail(frm);

		// 有効単価取得
		super.getValidUnitprice(frm);

		// 得意先より会計部門等を取得する
		try {
			getSalesByVenderForAddRow(frm);
		} catch (NoDataException e) {
			// 担当部署情報のみクリアする
			frm.setChargeOrganizationCd(null);
			frm.setChargeOrganizationName(null);
		}

		return frm;
	}

	/**
	 * 帳合コード取得処理
	 * @param frm 売上詳細画面FORM
	 * @return SalesDetailKeepForm
	 * @throws NoDataException データが存在しない例外
	 */
	private SalesDetailKeepForm getBalance(final SalesDetailKeepForm frm)
			throws NoDataException {

		String balanceCd = salesDetailKeepLogic.getBalanceCdByDeliveryCdItemCd(
			frm.getDeliveryCd(), frm.getItemCd());
		// 帳合コード
		frm.setBalanceCd(balanceCd);

		return frm;
	}

	/**
	 * 得意先情報取得処理
	 * @param frm 売上詳細画面FORM
	 * @return SalesDetailKeepForm
	 * @throws NoDataException データが存在しない例外
	 */
	private SalesDetailKeepForm getVenderList(final SalesDetailKeepForm frm)
			throws NoDataException {
		// 帳合コードより得意先一覧を取得
		if (StringUtils.isNotEmpty(frm.getBalanceCd())) {
			List<SalesDetailKeepVenderList> list = salesDetailKeepLogic
					.getVenderList(frm.getBalanceCd());
			frm.setVenderList(list);
			// 先頭の一件を得意先とする
			frm.setVenderCd(list.get(0).getVenderCd());
		}
		return frm;
	}

	/**
	 * 得意先情報取得処理
	 * @param frm 売上詳細画面FORM
	 * @return SalesDetailKeepForm
	 * @throws NoDataException データが存在しない例外
	 */
	private SalesDetailKeepForm getSalesByVender(final SalesDetailKeepForm frm)
			throws NoDataException {
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			SalesDetailEntity detail = salesDetailCommonLogic
					.getSalesByVender(frm.getVenderCd());

			// 担当部署情報
			frm.setChargeOrganizationCd(detail.getChargeOrganizationCd());
			frm.setChargeOrganizationName(detail.getChargeOrganizationName());
			// 仕訳(借方)
			frm.setAccountDebitSectionCd(detail.getAccountDebitSectionCd());
			frm.setAccountDebitSectionName(detail.getAccountDebitSectionName());
			frm.setDebitTitleCd(detail.getDebitTitleCd());
			frm.setDebitTitleName(detail.getDebitTitleName());
		}

		return frm;
	}

	/**
	 * 得意先情報取得処理(行追加時) 会計部門、勘定科目ともに未設定の場合のみ再設定
	 * @param frm 売上詳細画面FORM
	 * @return SalesDetailKeepForm
	 * @throws NoDataException データが存在しない例外
	 */
	private SalesDetailKeepForm getSalesByVenderForAddRow(
			final SalesDetailKeepForm frm) throws NoDataException {
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			SalesDetailEntity detail = salesDetailCommonLogic
					.getSalesByVender(frm.getVenderCd());

			// 担当部署情報
			frm.setChargeOrganizationCd(detail.getChargeOrganizationCd());
			frm.setChargeOrganizationName(detail.getChargeOrganizationName());
			// 仕訳(借方)
			if (StringUtils.isEmpty(frm.getAccountDebitSectionCd())
					&& StringUtils.isEmpty(frm.getDebitTitleCd())) {
				// 会計部門、勘定科目ともに未設定の場合のみ再設定
				frm.setAccountDebitSectionCd(detail.getAccountDebitSectionCd());
				frm.setAccountDebitSectionName(detail
						.getAccountDebitSectionName());
				frm.setDebitTitleCd(detail.getDebitTitleCd());
				frm.setDebitTitleName(detail.getDebitTitleName());
			}
		}

		return frm;
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

		if (bean == null || bean.getTmpUnitpriceFlg().equals("1")) {
			// 有効な単価が無い場合
			form.setStandardUnitprice(BigDecimal.ZERO); // 標準単価
			form.setStandardDiscount(BigDecimal.ZERO); // 標準値引
			form.setSpecialDiscount(BigDecimal.ZERO); // 特別値引
			form.setTmpUnitpriceFlg(true); // 仮単価
		} else {
			// 有効な単価が在る場合
			form.setStandardUnitprice(bean.getStandardUnitPrice()); // 標準単価
			form.setStandardDiscount(bean.getStandardDiscount()); // 標準値引
			form.setSpecialDiscount(bean.getSpecialDiscount()); // 特別値引
			form.setTmpUnitpriceFlg(false); // 正単価
		}

		return form;
	}

	/**
	 * 登録時マスタチェック処理
	 * @param frm 売上詳細画面FORM
	 * @param request HttpServletRequest
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isCheckMasterForInsert(final SalesDetailKeepForm frm,
			final ActionMessages errors, final HttpServletRequest request) {
		// 品目マスタ
		try {
			SalesDetailKeepEntity detail = salesDetailKeepLogic
					.getSalesByItem(frm.getItemCd());
			if (SalesConst.KEEP_DIVISION_KEEP.equals(detail.getKeepDivision())) {
				// 預かり品の場合、品目の情報をセットする
				frm.setItemName(detail.getItemName());
				frm.setOtherCompanyCd1(detail.getOtherCompanyCd1());
				frm.setStyleOfPacking(detail.getStyleOfPacking());
				frm.setUnitDivision(detail.getUnitDivision());
			} else {
				// 預かり品でない場合、エラーとする
				errors.add("", new ActionMessage(
						"errors.sales.keep.division.not.keep",
						getMessageResource(request, "item.sales.item.cd")));
			}
			// 販売品区分が0:該当なしの場合、エラーとする
			if (BigDecimal.ZERO.compareTo(detail.getArticleDivision()) == 0) {
				errors.add("", new ActionMessage(
						"errors.sales.article.division"));
			}
		} catch (NoDataException e) {
			frm.setItemCheckedFlag(false);
			// 品目マスタに存在しないのでエラーとする
			errors.add("", new ActionMessage("errors.nodata.master",
					getMessageResource(request, "item.sales.item.cd")));
		}

		// 帳合コード情報取得
		try {
			getBalance(frm);
		} catch (NoDataException e) {
			// 販売条件マスタに存在しないのでエラーとする
			errors.add("", new ActionMessage("errors.sales.sales.terms"));
		}

		// 得意先を取得する
		try {
			getVenderList(frm);
		} catch (NoDataException e) {
			// 得意先情報をクリア
			frm.setVenderList(new ArrayList<SalesDetailKeepVenderList>());
			frm.setVenderCd(null);
			// 帳合マスタに存在しないのでエラーとする
			errors.add("", new ActionMessage("errors.nodata.master",
					getMessageResource(request, "errors.sales.vender.cd")));
		}

		// 数値チェック設定取得
		salesDetailCommonLogic.setCheckDigit(frm);
		salesDetailCommonLogic.formatDetail(frm);

		// 得意先より会計部門等を取得する
		try {
			SalesDetailEntity detail = salesDetailCommonLogic
					.getSalesByVender(frm.getVenderCd());
			frm.setChargeOrganizationCd(detail.getChargeOrganizationCd());
			frm.setChargeOrganizationName(detail.getChargeOrganizationName());
		} catch (NoDataException e) {
			// 担当部署情報のみクリアする
			frm.setChargeOrganizationCd(null);
			frm.setChargeOrganizationName(null);
		}

		// 会計関連チェック
		salesDetailCommonLogic.checkAccountsForRegist(frm, errors);

		// 理由マスタチェック
		salesDetailCommonLogic.checkReasonForRegist(frm, errors);

		// 入庫ロケーションマスタチェック
		salesDetailKeepLogic.checkInLocation(frm, errors);

		if (!errors.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * 更新時マスタチェック処理
	 * @param frm 売上詳細画面FORM
	 * @param request HttpServletRequest
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isCheckMasterForUpdate(final SalesDetailKeepForm frm,
			final ActionMessages errors, final HttpServletRequest request) {

		// 会計関連チェック
		salesDetailCommonLogic.checkAccountsForRegist(frm, errors);

		// 理由マスタチェック
		salesDetailCommonLogic.checkReasonForRegist(frm, errors);

		// 入庫ロケーションマスタチェック
		salesDetailKeepLogic.checkInLocation(frm, errors);

		if (!errors.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * 出庫ロケーション数量合計チェック
	 * @param frm 売上詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean 品目の数量 ≠ 出庫ロケーションの数量の合計 の場合:false
	 */
	private boolean isSumCheck(final SalesDetailKeepForm frm,
			final ActionMessages errors) {

		// 出庫ロケーションの数量の合計を算出
		BigDecimal sum = new BigDecimal(0);
		for (SalesDetailKeepSalesInoutRecordList location : frm
				.getLocationList()) {
			sum = sum.add(super.convertBigDecimal(location.getStrQty()));
		}

		// 受注番号がNullの場合(預り品の場合）はチェック
		if (frm.getOrderNo().equals("")) {
			// 品目の数量 ≠ 出庫ロケーションの数量の合計 の場合、エラーとする
			BigDecimal salesQuantity = super.convertBigDecimal(frm
					.getStrSalesQuantity());
			if (!sum.equals(salesQuantity)) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.sales.sales.quantity"));
				return false;
			}
		}
		return true;
	}

	/**
	 * 出庫ロケーション新規行を取得する
	 * @return SalesDetailKeepLocationList
	 */
	private SalesDetailKeepSalesInoutRecordList getNewLocationLine()
			throws Exception {
		SalesDetailKeepSalesInoutRecordList data = new SalesDetailKeepSalesInoutRecordList();
		data.setSalesNo(null);
		data.setSalesRowNo(null);
		data.setCheckFlg(false); // チェックフラグ
		data.setLocationCd(null); // ロケーションコード
		data.setLocationName(null); // ロケーション名称
		data.setInLocationCd(null); // 入庫先ロケーションコード
		data.setLotNo(null); // ロット番号
		data.setStrQty(null); // 数量文字列
		data.setPrevQty(BigDecimal.ZERO); // 前回数量
		return data;
	}

	/**
	 * チェックボックスクリア処理
	 * @param form 画面のForm
	 * @return SalesDetailKeepForm 画面のForm
	 */
	private SalesDetailKeepForm clearCheckFlg(final SalesDetailKeepForm form) {

		// チェックボックスをクリアする
		for (SalesDetailKeepSalesInoutRecordList list : form.getLocationList()) {
			list.setCheckFlg(Boolean.FALSE);
		}

		return form;
	}

	/**
	 * 売上検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param bean 売上検索結果
	 * @return SalesDetailStandardForm
	 */
	private SalesDetailKeepForm setSalesData(final SalesDetailKeepForm form,
			final SalesDetailKeepEntity bean) {
		// 売上詳細共通項目を設定
		super.setSalesCommonInfo(form, bean);
		// 数値チェック設定取得
		salesDetailCommonLogic.setCheckDigit(form);
		salesDetailCommonLogic.formatDetail(form);

		form.setInputDivision(bean.getInputDivision()); // 入力区分
		form.setBalanceCd(bean.getBalanceCd()); // 帳合コード
		form
				.setStrMatss(super.getChecker().format(form.getUnitDivision(),
					SalesConst.VENDER_DIVISION_TS, bean.getVenderCd(),
					bean.getMatss())); // 増付数
		form.setDeliveryAddress(bean.getDeliveryAddress()); // 納入先宛先
		form.setKeepFlag(bean.getKeepFlag()); // 預り品フラグ

		// 得意先一覧を取得する
		try {
			getVenderList(form);
		} catch (NoDataException e) {
			form.setVenderList(new ArrayList<SalesDetailKeepVenderList>());
		}

		if (SalesConst.KEEP_DIVISION_KEEP.equals(form.getKeepFlag())) {
			// 出庫ロケーション一覧を取得する
			try {
				form
						.setLocationList(salesDetailKeepLogic
								.getLocationList(form));
			} catch (NoDataException e) {
				form
						.setLocationList(new ArrayList<SalesDetailKeepSalesInoutRecordList>());
			}
		}

		return form;
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
	private void setSalesTaxCombobox(SalesDetailKeepForm frm) throws NoDataException {
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
	 * 売上詳細(預り品)ロジッククラスを設定します。
	 * @param salesDetailKeepLogic 売上詳細(預り品)ロジッククラス
	 */
	public void setSalesDetailKeepLogic(
			final SalesDetailKeepLogic salesDetailKeepLogic) {
		this.salesDetailKeepLogic = salesDetailKeepLogic;
		this.salesDetailCommonLogic = (AbstractSalesDetailLogic) salesDetailKeepLogic;
	}

	/**
	 * commonLogicを設定します。
	 * @param commonLogic commonLogic
	 */
	public void setCommonLogic(CommonLogic commonLogic) {
		this.commonLogic = commonLogic;
	}

}
