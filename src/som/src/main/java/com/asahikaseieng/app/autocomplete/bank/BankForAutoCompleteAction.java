/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.bank;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.bank.BankForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 銀行のAuto Complete用アクション
 * @author t0011036
 */
public class BankForAutoCompleteAction extends AbstractAction {

	/** 銀行のAuto Complete用ロジック */
	private BankForAutoCompleteLogic bankAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public BankForAutoCompleteAction() {
	}

	/**
	 * 検索画面用銀行検索 銀行名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchBank(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchBank.");
		}

		BankForAutoCompleteForm frm = (BankForAutoCompleteForm) form;

		/* 銀行を銀行コードまたは銀行名称で検索 */
		try {
			List<BankForAutoComplete> result = bankAutoCompleteLogic
					.getBankSearchList(frm.getCode());

			/* Formに設定する */
			List<GeneralParameterBean> list = getAutoCompleteBankBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("銀行検索結果なし");
		}

		return mapping.findForward("bank");
	}

	/**
	 * 検索画面用支店検索 支店名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchBranch(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchBranch.");
		}

		BankForAutoCompleteForm frm = (BankForAutoCompleteForm) form;

		/* 銀行を支店コードまたは支店名称で検索 */
		try {
			List<BankForAutoComplete> result = bankAutoCompleteLogic
					.getBranchSearchList(frm.getSrhBankCd(), frm.getCode());

			/* Formに設定する */
			List<GeneralParameterBean> list = getAutoCompleteBranchBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("銀行検索結果なし");
		}

		return mapping.findForward("branch");
	}

	/**
	 * 検索画面用銀行マスタ検索 銀行マスタ名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchBankMaster(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchBankMaster.");
		}

		BankForAutoCompleteForm frm = (BankForAutoCompleteForm) form;

		/* 銀行を銀行マスタコードまたは銀行マスタ名称で検索 */
		try {
			List<BankForAutoComplete> result = bankAutoCompleteLogic
					.getSearchList(frm.getCode());

			/* Formに設定する */
			List<GeneralParameterBean> list = getAutoCompleteBankMasterBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("銀行検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------

	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBankBean(
			final List<BankForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();

		for (BankForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			transferEntityBankData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBranchBean(
			final List<BankForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();

		for (BankForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			transferEntityBranchData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBankMasterBean(
			final List<BankForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();

		for (BankForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			transferEntityBankMasterData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 銀行データをオートコンプリート用Beanに移送する
	 * @param source銀行データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityBankData(
			final BankForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getBankCd());
		dest.setName(source.getBankName());
		return dest;
	}

	/**
	 * 銀行データをオートコンプリート用Beanに移送する
	 * @param source銀行データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityBranchData(
			final BankForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getBranchCd());
		dest.setName(source.getBranchName());
		return dest;
	}

	/**
	 * 銀行データをオートコンプリート用Beanに移送する
	 * @param source銀行データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityBankMasterData(
			final BankForAutoComplete source, final GeneralParameterBean dest) {
		dest.setBankCd(source.getBankCd());
		dest.setBankName(source.getBankName());
		dest.setBranchCd(source.getBranchCd());
		dest.setBranchName(source.getBranchName());
		dest.setCode(source.getBankMasterCd());
		dest.setName(source.getBankMasterName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * bankAutoCompleteLogicを設定します。
	 * @param bankAutoCompleteLogic bankAutoCompleteLogic
	 */
	public void setBankAutoCompleteLogic(
			final BankForAutoCompleteLogic bankAutoCompleteLogic) {
		this.bankAutoCompleteLogic = bankAutoCompleteLogic;
	}
}
