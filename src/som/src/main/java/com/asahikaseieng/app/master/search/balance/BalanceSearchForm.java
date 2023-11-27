/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.balance;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceList;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 帳合検索 Formクラス.
 * @author t0011036
 */
public final class BalanceSearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb
				.getString("linage.search.sales.terms.balance"));
		DATA_ROW = Integer.parseInt(rb
				.getString("threshold.search.sales.terms.balance"));
	}

	/* 取引先区分 */
	private String srhVenderDivision;

	/* 得意先コード */
	private String srhVenderCd;

	/* 得意先名称 */
	private String srhVenderName1;

	/* 帳合コード */
	private String srhBalanceCd;

	/* 帳合リスト */
	private List<BalanceList> searchList;

	/**
	 * コンストラクタ.
	 */
	public BalanceSearchForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return BalanceListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			/* 初期化 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<BalanceList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhVenderDivision("TS"); /* 得意先 */
		setSrhVenderCd(null);
		setSrhVenderName1(null);
		setSrhBalanceCd(null);
		setSearchList(new ArrayList<BalanceList>());
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<BalanceList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<BalanceList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhVenderCdを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * srhVenderCdを設定します。
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * srhVenderDivisionを取得します。
	 * @return srhVenderDivision
	 */
	public String getSrhVenderDivision() {
		return srhVenderDivision;
	}

	/**
	 * srhVenderDivisionを設定します。
	 * @param srhVenderDivision srhVenderDivision
	 */
	public void setSrhVenderDivision(final String srhVenderDivision) {
		this.srhVenderDivision = srhVenderDivision;
	}

	/**
	 * srhVenderName1を取得します。
	 * @return srhVenderName1
	 */
	public String getSrhVenderName1() {
		return srhVenderName1;
	}

	/**
	 * srhVenderName1を設定します。
	 * @param srhVenderName1 srhVenderName1
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * srhBalanceCdを取得します。
	 * @return srhBalanceCd
	 */
	public String getSrhBalanceCd() {
		return srhBalanceCd;
	}

	/**
	 * srhBalanceCdを設定します。
	 * @param srhBalanceCd srhBalanceCd
	 */
	public void setSrhBalanceCd(final String srhBalanceCd) {
		this.srhBalanceCd = srhBalanceCd;
	}
}
