/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.estimatedetaillist.EstimateDetailList;
import com.asahikaseieng.dao.nonentity.estimateduplicatelist.EstimateDuplicateList;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 見積/単価詳細 Actionクラス.
 * @author t0011036
 */
public final class EstimateDetailAction extends AbstractAction {

	private EstimateDetailLogic estimateDetailLogic;

	/** 見積書ＥＸＣＥＬファイル作成ロジッククラス */
	private EstimateDetailExcelDecorator estimateDetailExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public EstimateDetailAction() {
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

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ESTIMATE,
			Constants.TAB_ID_ESTIMATE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 検索 */
		searchData(frm, request);

		// /* 権限取得 */
		// getControlAuthority(request, frm, Constants.MENU_ID_ESTIMATE,
		// Constants.TAB_ID_ESTIMATE_DETAIL);
		//
		// if (!frm.getViewAuthority()) {
		// /* エラーメッセージ */
		// saveError(request, "errors.not.view.authority");
		// return mapping.findForward("back");
		// }
		//
		// /* Excelダウンロードフラグのクリア */
		// if (!frm.getOp().equals("reFresh")) {
		// frm.setExcelDownloadFlg(Boolean.FALSE);
		// }
		//
		// /* 初期検索 */
		// List<EstimateDetailList> bean = estimateDetailLogic.getList(frm
		// .getEstimateNo());
		//
		// /* BeanをFormにコピーする */
		// frm.setSearchEstimateDetailList(bean);
		//
		// frm.setEstimateNo(frm.getSearchEstimateDetailList().get(0)
		// .getEstimateNo());
		// frm.setEstimateStatus(frm.getSearchEstimateDetailList().get(0)
		// .getEstimateStatus());
		// frm.setEstimateStatusName(frm.getSearchEstimateDetailList().get(0)
		// .getEstimateStatusName());
		// frm.setEstimateInputDate(frm.getSearchEstimateDetailList().get(0)
		// .getEstimateInputDate());
		// frm.setStrEstimateInputDate(frm.getSearchEstimateDetailList().get(0)
		// .getStrEstimateInputDate());
		// frm.setBalanceCd(frm.getSearchEstimateDetailList().get(0)
		// .getBalanceCd());
		// frm.setVenderCd(frm.getSearchEstimateDetailList().get(0).getVenderCd()
		// .trim());
		// frm.setEstimateValidDateFrom(frm.getSearchEstimateDetailList().get(0)
		// .getEstimateValidDateFrom());
		// frm.setStrEstimateValidDateFrom(frm.getSearchEstimateDetailList()
		// .get(0).getStrEstimateValidDateFrom());
		// frm.setEstimateValidDateTo(frm.getSearchEstimateDetailList().get(0)
		// .getEstimateValidDateTo());
		// frm.setStrEstimateValidDateTo(frm.getSearchEstimateDetailList().get(0)
		// .getStrEstimateValidDateTo());
		// frm.setRemark(frm.getSearchEstimateDetailList().get(0).getRemark());
		//
		// /* 数値桁数チェック部品呼び出し */
		// CheckDigitUtilsLogic checker = CheckDigitUtil
		// .getCheckDigitUtils(request);
		//
		// for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
		// /* 数値文字列に変換 */
		// frm.getSearchEstimateDetailList().get(i).setStrStandardUnitPrice(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getStandardUnitPrice()));
		// frm.getSearchEstimateDetailList().get(i).setStrStandardDiscount(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getStandardDiscount()));
		// frm.getSearchEstimateDetailList().get(i).setStrSpecialDiscount(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getSpecialDiscount()));
		// frm.getSearchEstimateDetailList().get(i).setStrUnitprice(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getUnitprice()));
		// frm.getSearchEstimateDetailList().get(i).setStrStandardAmount(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitOfOperationManagement(), frm
		// .getSearchEstimateDetailList().get(i)
		// .getStandardAmount()));
		// frm.getSearchEstimateDetailList().get(i).setStrMatss(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitOfOperationManagement(), frm
		// .getSearchEstimateDetailList().get(i).getMatss()));
		// }
		//
		// /* 明細取得 */
		// frm.setSearchEstimateBalanceList(estimateDetailLogic.getBalanceList(frm
		// .getBalanceCd()));

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward getBalance(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getBalance.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 明細取得 */
		frm.setSearchEstimateBalanceList(estimateDetailLogic.getBalanceList(frm
				.getBalanceCd()));

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

		EstimateDetailForm frm = (EstimateDetailForm) form;

		if (!StringUtils.isEmpty(frm.getBalanceCd())) {
			/* 帳合コードチェック */
			BalanceDetail beanBalance = estimateDetailLogic
					.getBalanceEntity(frm.getBalanceCd());

			if (beanBalance == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.estimate.balance.cd");
				return mapping.findForward("success");
			}

			/* 得意先コード設定 */
			frm.setVenderCd(beanBalance.getVenderCd());
		}

		if (frm.getSearchEstimateDetailList().size() == 0) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.estimate.standard.unit.price");
			return mapping.findForward("success");
		}

		for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
			if (!StringUtils.isEmpty(frm.getSearchEstimateDetailList().get(i)
					.getItemCd())) {
				/* 品目コードチェック */
				ItemDetail beanItem = estimateDetailLogic.getItemEntity(frm
						.getSearchEstimateDetailList().get(i).getItemCd());

				if (beanItem == null) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.nodata.estimate.item.cd.row", i + 1);
					return mapping.findForward("success");
				}

				frm.getSearchEstimateDetailList().get(i).setUnitDivision(
					"URTANKA");
				frm.getSearchEstimateDetailList().get(i)
						.setUnitOfOperationManagement(
							beanItem.getUnitOfOperationManagement());
			}

			/* 帳合＆品目重複チェック */
			List<EstimateDuplicateList> listDuplicate = estimateDetailLogic
					.getDuplicate(frm.getEstimateNo(), frm.getBalanceCd(), frm
							.getSearchEstimateDetailList().get(i).getItemCd(),
						frm.getStrEstimateValidDateFrom(), frm
								.getStrEstimateValidDateTo());

			if (0 < listDuplicate.size()) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.estimate.duplicate.row",
					i + 1);
				return mapping.findForward("success");
			}
		}

		for (int i = 0; i < frm.getSearchEstimateDetailList().size() - 1; i++) {
			/* 品目重複チェック */
			for (int j = i + 1; j < frm.getSearchEstimateDetailList().size(); j++) {
				if (frm.getSearchEstimateDetailList().get(i).getItemCd()
						.equals(
							frm.getSearchEstimateDetailList().get(j)
									.getItemCd())) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.estimate.duplicate.item.cd.row", i + 1);
					return mapping.findForward("success");
				}
			}
		}

		/* 見積番号取得 */
		if (frm.getNewFlg().equals("true")) {
			frm.setEstimateNo(estimateDetailLogic.getEstimateNo());
			frm.setEstimateStatus(new BigDecimal("1")); /* 見積登録 */
			frm.setEstimateStatusName("見積登録");
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
			frm.setEstimateValidDateFrom(AecDateUtils.getTimestampYmdFormat(frm
					.getStrEstimateValidDateFrom()));
			frm.setEstimateValidDateTo(AecDateUtils.getTimestampYmdFormat(frm
					.getStrEstimateValidDateTo()));

			BigDecimal standardUnitPrice = strToNum(frm
					.getSearchEstimateDetailList().get(i)
					.getStrStandardUnitPrice());
			BigDecimal standardDiscount = strToNum(frm
					.getSearchEstimateDetailList().get(i)
					.getStrStandardDiscount());
			BigDecimal specialDiscount = strToNum(frm
					.getSearchEstimateDetailList().get(i)
					.getStrSpecialDiscount());
			// BigDecimal unitprice = strToNum(frm.getSearchEstimateDetailList()
			// .get(i).getStrUnitprice());
			BigDecimal unitprice = standardUnitPrice.subtract(standardDiscount)
					.subtract(specialDiscount);
			BigDecimal standardAmount = strToNum(frm
					.getSearchEstimateDetailList().get(i)
					.getStrStandardAmount());
			BigDecimal matss = strToNum(frm.getSearchEstimateDetailList()
					.get(i).getStrMatss());

			/* 演算結果を丸める */
			frm.getSearchEstimateDetailList().get(i).setStandardUnitPrice(
				check.round(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), null, null, standardUnitPrice));
			frm.getSearchEstimateDetailList().get(i).setStandardDiscount(
				check.round(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), null, null, standardDiscount));
			frm.getSearchEstimateDetailList().get(i).setSpecialDiscount(
				check.round(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), null, null, specialDiscount));
			frm.getSearchEstimateDetailList().get(i).setUnitprice(
				check.round(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), null, null, unitprice));

			if (StringUtils.isEmpty(frm.getSearchEstimateDetailList().get(i)
					.getUnitOfOperationManagement())) {
				frm.getSearchEstimateDetailList().get(i).setStandardAmount(
					standardAmount);
				frm.getSearchEstimateDetailList().get(i).setMatss(matss);
			} else {
				frm.getSearchEstimateDetailList().get(i).setStandardAmount(
					check.round(frm.getSearchEstimateDetailList().get(i)
							.getUnitOfOperationManagement().toString(), null,
						null, standardAmount));
				frm.getSearchEstimateDetailList().get(i).setMatss(
					check.round(frm.getSearchEstimateDetailList().get(i)
							.getUnitOfOperationManagement().toString(), null,
						null, matss));
			}
		}

		/* 登録処理を実行 */
		estimateDetailLogic.insert(frm, getLoginInfo(request).getTantoCd());

		frm.setNewFlg(null);
		frm.setDirtyFlg(null);

		/* 検索 */
		searchData(frm, request);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 文字列を数値に変換
	 * @param value 変換元の文字列
	 * @return 変換後の数値
	 */
	private BigDecimal strToNum(final String value) {
		return AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(value));
	}

	/**
	 * 削除処理処理.
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
			getLog().debug("delete");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 削除処理 */
		estimateDetailLogic.deleteEstimateList(frm.getEstimateNo(), null);

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
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

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ESTIMATE,
			Constants.TAB_ID_ESTIMATE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");
		// estimateStatus=nullだとerrerになる場合がある
		frm.setEstimateStatus(BigDecimal.ZERO);
		return mapping.findForward("success");
	}

	/**
	 * 品目追加処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addlist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("addlist.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		Boolean isAddList = false;

		for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
			if (frm.getSearchEstimateDetailList().get(i).getChecked()) {
				EstimateDetailList bean = new EstimateDetailList();
				frm.getSearchEstimateDetailList().add(i, bean);
				frm.getSearchEstimateDetailList().get(i).setChecked(false);
				frm.getSearchEstimateDetailList().get(i).setUnitDivision(
					"URTANKA");
				frm.getSearchEstimateDetailList().get(i).setSmallnumLength(
					new BigDecimal("0"));
				frm.getSearchEstimateDetailList().get(i).setRoundDivision(
					new BigDecimal("0"));
				isAddList = true;
				break;
			}
		}

		/* 先頭行へ追加 */
		if (!isAddList) {
			EstimateDetailList bean = new EstimateDetailList();
			frm.getSearchEstimateDetailList().add(0, bean);
			frm.getSearchEstimateDetailList().get(0).setChecked(false);
			frm.getSearchEstimateDetailList().get(0).setUnitDivision("URTANKA");
			frm.getSearchEstimateDetailList().get(0).setSmallnumLength(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setRoundDivision(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setEstimateStatus(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setStandardUnitPrice(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setStrStandardUnitPrice(
				"0");
			frm.getSearchEstimateDetailList().get(0).setStandardDiscount(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0)
					.setStrStandardDiscount("0");
			frm.getSearchEstimateDetailList().get(0).setSpecialDiscount(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setStrSpecialDiscount("0");
			frm.getSearchEstimateDetailList().get(0).setUnitprice(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setStrUnitprice("0");
			frm.getSearchEstimateDetailList().get(0).setStandardAmount(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setStrStandardAmount("0");
			frm.getSearchEstimateDetailList().get(0).setMatss(
				new BigDecimal("0"));
			frm.getSearchEstimateDetailList().get(0).setStrMatss("0");
		}

		/* 行番を再設定 */
		resetSeq(frm);

		return mapping.findForward("success");
	}

	/**
	 * 品目削除処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward dellist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("dellist.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		for (int i = frm.getSearchEstimateDetailList().size() - 1; i >= 0; i--) {
			if (frm.getSearchEstimateDetailList().get(i).getChecked()) {
				frm.getSearchEstimateDetailList().remove(i);
			}
		}

		/* 行番を再設定 */
		resetSeq(frm);

		return mapping.findForward("success");
	}

	/**
	 * 行番を再設定
	 * @param frm 画面データ
	 */
	private void resetSeq(final EstimateDetailForm frm) {
		/* 行番再設定 */
		for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
			frm.getSearchEstimateDetailList().get(i).setSeq(
				new BigDecimal(i + 1));
		}
	}

	/**
	 * 見積コピー処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward copy(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("copy.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ESTIMATE,
			Constants.TAB_ID_ESTIMATE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		frm.setEstimateNo(null);
		frm.setEstimateStatus(new BigDecimal("0"));
		frm.setEstimateStatusName(null);
		frm.setEstimateInputDate(AecDateUtils.getCurrentTimestamp());
		frm.setStrEstimateInputDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));

		for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
			frm.getSearchEstimateDetailList().get(i).setUpdateDate(null);
		}

		return mapping.findForward("success");
	}

	/**
	 * 見積書発行処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		frm.setExcelDownloadFlg(false);

		String estimateNo = frm.getEstimateNo();

		// 見積書を作成
		info = estimateDetailExcelDecorator.createReport(estimateNo, tantoNm,
			AecDateUtils.getCurrentTimestamp(), request.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 承認依頼.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalRequest(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalRequest.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 承認依頼中 */
		BigDecimal status = new BigDecimal("2");

		/* ステータス更新 */
		estimateDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
		// Estimate bean = estimateDetailLogic.getEntity(frm.getEstimateNo(),
		// new BigDecimal(i + 1));
		//
		// frm.setEstimateInputDate(bean.getEstimateInputDate());
		// frm.setStrEstimateInputDate(AecDateUtils.dateFormat(bean
		// .getEstimateInputDate(), "yyyy/MM/dd"));
		// frm.setBalanceCd(bean.getBalanceCd());
		// frm.getSearchEstimateDetailList().get(i)
		// .setItemCd(bean.getItemCd());
		// frm.getSearchEstimateDetailList().get(i).setStandardUnitPrice(
		// bean.getStandardUnitPrice());
		// frm.getSearchEstimateDetailList().get(i).setStandardDiscount(
		// bean.getStandardDiscount());
		// frm.getSearchEstimateDetailList().get(i).setSpecialDiscount(
		// bean.getSpecialDiscount());
		// frm.getSearchEstimateDetailList().get(i).setStandardAmount(
		// bean.getStandardAmount());
		// frm.getSearchEstimateDetailList().get(i).setMatss(bean.getMatss());
		// frm.setEstimateValidDateFrom(bean.getEstimateValidDateFrom());
		// frm.setEstimateValidDateTo(bean.getEstimateValidDateTo());
		// frm.setRemark(bean.getRemark());
		//
		// /* 日付文字列に変換 */
		// frm.setStrEstimateValidDateFrom(AecDateUtils.dateFormat(bean
		// .getEstimateValidDateFrom(), "yyyy/MM/dd"));
		// frm.setStrEstimateValidDateTo(AecDateUtils.dateFormat(bean
		// .getEstimateValidDateTo(), "yyyy/MM/dd"));
		//
		// /* 数値桁数チェック部品呼び出し */
		// CheckDigitUtilsLogic checker = CheckDigitUtil
		// .getCheckDigitUtils(request);
		//
		// /* 数値文字列に変換 */
		// frm.getSearchEstimateDetailList().get(i).setStrStandardUnitPrice(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getStandardUnitPrice()));
		// frm.getSearchEstimateDetailList().get(i).setStrStandardDiscount(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getStandardDiscount()));
		// frm.getSearchEstimateDetailList().get(i).setStrSpecialDiscount(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getSpecialDiscount()));
		// frm.getSearchEstimateDetailList().get(i).setStrUnitprice(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitDivision(), frm.getSearchEstimateDetailList()
		// .get(i).getUnitprice()));
		// frm.getSearchEstimateDetailList().get(i).setStrStandardAmount(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitOfOperationManagement(), frm
		// .getSearchEstimateDetailList().get(i)
		// .getStandardAmount()));
		// frm.getSearchEstimateDetailList().get(i).setStrMatss(
		// checker.format(frm.getSearchEstimateDetailList().get(i)
		// .getUnitOfOperationManagement(), frm
		// .getSearchEstimateDetailList().get(i).getMatss()));
		// }
		//
		// frm.setEstimateStatus(new BigDecimal("2"));
		// frm.setEstimateStatusName("見積承認依頼中");

		/* 検索 */
		searchData(frm, request);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 承認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approval.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 承認 */
		BigDecimal status = new BigDecimal("3");

		/* ステータス更新 */
		estimateDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// frm.setEstimateStatus(new BigDecimal("3"));
		// frm.setEstimateStatusName("見積承認済");

		/* 検索 */
		searchData(frm, request);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 否認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward negation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("negation.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 否認 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		estimateDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// frm.setEstimateStatus(new BigDecimal("1"));
		// frm.setEstimateStatusName("見積登録");

		/* 検索 */
		searchData(frm, request);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 承認取消.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalCancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalCancel.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		/* 承認取消 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		estimateDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// frm.setEstimateStatus(new BigDecimal("1"));
		// frm.setEstimateStatusName("見積登録");

		/* 検索 */
		searchData(frm, request);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward calcUnitprice(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("calcUnitprice.");
		}

		EstimateDetailForm frm = (EstimateDetailForm) form;

		for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
			/* 文字列 ---> 数値変換 */
			frm.getSearchEstimateDetailList().get(i).setStandardUnitPrice(
				AecNumberUtils.convertBigDecimal(frm
						.getSearchEstimateDetailList().get(i)
						.getStrStandardUnitPrice()));
			frm.getSearchEstimateDetailList().get(i).setStandardDiscount(
				AecNumberUtils.convertBigDecimal(frm
						.getSearchEstimateDetailList().get(i)
						.getStrStandardDiscount()));
			frm.getSearchEstimateDetailList().get(i).setSpecialDiscount(
				AecNumberUtils.convertBigDecimal(frm
						.getSearchEstimateDetailList().get(i)
						.getStrSpecialDiscount()));

			/* null ---> 数値変換 */
			BigDecimal standardUnitPrice = AecNumberUtils.convertNullToZero(frm
					.getSearchEstimateDetailList().get(i)
					.getStandardUnitPrice());
			BigDecimal standardDiscount = AecNumberUtils
					.convertNullToZero(frm.getSearchEstimateDetailList().get(i)
							.getStandardDiscount());
			BigDecimal specialDiscount = AecNumberUtils.convertNullToZero(frm
					.getSearchEstimateDetailList().get(i).getSpecialDiscount());

			/* 単価 = 標準単価 - 標準値引 - 特別値引 */
			frm.getSearchEstimateDetailList().get(i).setUnitprice(
				standardUnitPrice.subtract(standardDiscount).subtract(
					specialDiscount));

			/* 数値桁数チェック部品呼び出し */
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			BigDecimal result = null;

			/* 演算結果を丸める */
			result = check.round(frm.getSearchEstimateDetailList().get(i)
					.getUnitDivision(), null, null, frm
					.getSearchEstimateDetailList().get(i).getUnitprice());
			frm.getSearchEstimateDetailList().get(i).setUnitprice(result);

			/* 数値 ---> 文字列変換 */
			if (frm.getSearchEstimateDetailList().get(i).getStandardUnitPrice() != null) {
				frm.getSearchEstimateDetailList().get(i)
						.setStrStandardUnitPrice(
							check.format(frm.getSearchEstimateDetailList().get(
								i).getUnitDivision(), standardUnitPrice));
			}

			if (frm.getSearchEstimateDetailList().get(i).getStandardDiscount() != null) {
				frm.getSearchEstimateDetailList().get(i)
						.setStrStandardDiscount(
							check.format(frm.getSearchEstimateDetailList().get(
								i).getUnitDivision(), standardDiscount));
			}

			if (frm.getSearchEstimateDetailList().get(i).getSpecialDiscount() != null) {
				frm.getSearchEstimateDetailList().get(i).setStrSpecialDiscount(
					check.format(frm.getSearchEstimateDetailList().get(i)
							.getUnitDivision(), specialDiscount));
			}

			frm.getSearchEstimateDetailList().get(i).setStrUnitprice(
				check.format(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), AecNumberUtils
						.convertBigDecimal(frm.getSearchEstimateDetailList()
								.get(i).getUnitprice().toString())));
		}

		return mapping.findForward("success");
	}

	/**
	 * 検索
	 * @param frm 画面データ
	 * @param request request
	 */
	private void searchData(final EstimateDetailForm frm,
			final HttpServletRequest request) {
		/* 初期検索 */
		List<EstimateDetailList> bean = estimateDetailLogic.getList(frm
				.getEstimateNo());

		/* BeanをFormにコピーする */
		frm.setSearchEstimateDetailList(bean);

		frm.setEstimateNo(frm.getSearchEstimateDetailList().get(0)
				.getEstimateNo());
		frm.setEstimateStatus(frm.getSearchEstimateDetailList().get(0)
				.getEstimateStatus());
		frm.setEstimateStatusName(frm.getSearchEstimateDetailList().get(0)
				.getEstimateStatusName());
		frm.setEstimateInputDate(frm.getSearchEstimateDetailList().get(0)
				.getEstimateInputDate());
		frm.setStrEstimateInputDate(frm.getSearchEstimateDetailList().get(0)
				.getStrEstimateInputDate());
		frm.setBalanceCd(frm.getSearchEstimateDetailList().get(0)
				.getBalanceCd());
		frm.setVenderCd(frm.getSearchEstimateDetailList().get(0).getVenderCd()
				.trim());
		frm.setEstimateValidDateFrom(frm.getSearchEstimateDetailList().get(0)
				.getEstimateValidDateFrom());
		frm.setStrEstimateValidDateFrom(frm.getSearchEstimateDetailList()
				.get(0).getStrEstimateValidDateFrom());
		frm.setEstimateValidDateTo(frm.getSearchEstimateDetailList().get(0)
				.getEstimateValidDateTo());
		frm.setStrEstimateValidDateTo(frm.getSearchEstimateDetailList().get(0)
				.getStrEstimateValidDateTo());
		frm.setRemark(frm.getSearchEstimateDetailList().get(0).getRemark());

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchEstimateDetailList().size(); i++) {
			/* 行番を設定 */
			frm.getSearchEstimateDetailList().get(i).setSeq(
				new BigDecimal(i + 1));

			/* 数値文字列に変換 */
			frm.getSearchEstimateDetailList().get(i).setStrStandardUnitPrice(
				checker.format(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), frm.getSearchEstimateDetailList()
						.get(i).getStandardUnitPrice()));
			frm.getSearchEstimateDetailList().get(i).setStrStandardDiscount(
				checker.format(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), frm.getSearchEstimateDetailList()
						.get(i).getStandardDiscount()));
			frm.getSearchEstimateDetailList().get(i).setStrSpecialDiscount(
				checker.format(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), frm.getSearchEstimateDetailList()
						.get(i).getSpecialDiscount()));
			frm.getSearchEstimateDetailList().get(i).setStrUnitprice(
				checker.format(frm.getSearchEstimateDetailList().get(i)
						.getUnitDivision(), frm.getSearchEstimateDetailList()
						.get(i).getUnitprice()));
			frm.getSearchEstimateDetailList().get(i).setStrStandardAmount(
				checker.format(frm.getSearchEstimateDetailList().get(i)
						.getUnitOfOperationManagement(), frm
						.getSearchEstimateDetailList().get(i)
						.getStandardAmount()));
			frm.getSearchEstimateDetailList().get(i).setStrMatss(
				checker.format(frm.getSearchEstimateDetailList().get(i)
						.getUnitOfOperationManagement(), frm
						.getSearchEstimateDetailList().get(i).getMatss()));
		}

		/* 明細取得 */
		frm.setSearchEstimateBalanceList(estimateDetailLogic.getBalanceList(frm
				.getBalanceCd()));
	}

	/* -------------------- setter -------------------- */

	/**
	 * estimateDetailLogicを設定します。
	 * @param estimateDetailLogic estimateDetailLogic
	 */
	public void setEstimateDetailLogic(
			final EstimateDetailLogic estimateDetailLogic) {
		this.estimateDetailLogic = estimateDetailLogic;
	}

	/**
	 * 見積書ＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param estimateDetailExcelDecorator 見積書ＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setEstimateDetailExcelDecorator(
			final EstimateDetailExcelDecorator estimateDetailExcelDecorator) {
		this.estimateDetailExcelDecorator = estimateDetailExcelDecorator;
	}
}
