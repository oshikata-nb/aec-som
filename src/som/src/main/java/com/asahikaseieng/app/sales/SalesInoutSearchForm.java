package com.asahikaseieng.app.sales;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchList;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受払検索(ポップアップ) Formクラス.
 * @author t1344224
 */
public final class SalesInoutSearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：他社コード */
	private String srhOtherCompanyCd1;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：売上日 */
	private String srhSalesDate;

	/** 検索結果リスト */
	private List<SalesInoutSearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.search.sales.inout.search"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.search.sales.inout.search"));
	}

	/**
	 * コンストラクタ.
	 */
	public SalesInoutSearchForm() {
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
		return SalesInoutSearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
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
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.受払検索
	 */
	public void clear() {
		/** 検索リストのクリア */
		setSearchList(new ArrayList<SalesInoutSearchList>());

		this.setSrhItemCd(null);

		this.setSrhItemName(null);

		this.setSrhOtherCompanyCd1(null);

		this.setSrhVenderCd(null);

		this.setSrhSalesDate(null);
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 品目コード設定.
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 品目名称設定.
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 他社コード取得.
	 * @return String 他社コード
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * 他社コード設定.
	 * @param srhOtherCompanyCd1 他社コード
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 仕入先取得.
	 * @return String 仕入先
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 仕入先設定.
	 * @param srhVenderCd 仕入先
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索結果取得.
	 * @return SalesInoutSearchList 検索結果
	 */
	public List<SalesInoutSearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<SalesInoutSearchList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhSalesDate取得.
	 * @return srhSalesDate srhSalesDate
	 */
	public String getSrhSalesDate() {
		return srhSalesDate;
	}

	/**
	 * srhSalesDate設定します。
	 * @param srhSalesDate srhSalesDate
	 */
	public void setSrhSalesDate(final String srhSalesDate) {
		this.srhSalesDate = srhSalesDate;
	}
}
