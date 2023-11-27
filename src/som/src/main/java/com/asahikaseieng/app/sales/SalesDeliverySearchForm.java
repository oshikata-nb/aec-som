/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchList;
import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 納入先検索(ポップアップ) Formクラス.
 * @author tosco
 */
public final class SalesDeliverySearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：納入先名称 */
	private String srhDeliveryName1;

	/** 検索入力：区分 */
	private String srhDivision;

	/** 検索結果リスト */
	private List<SalesDeliverySearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.sales.search.delivery"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.sales.search.delivery"));
	}

	/**
	 * コンストラクタ.
	 */
	public SalesDeliverySearchForm() {
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
		return SalesDeliverySearchListPagerCondition.class;
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
	 * 初期化.受注検索
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<SalesDeliverySearchList>());

		/** 検索入力：納入先コード */
		setSrhDeliveryCd(null);
		/** 検索入力：納入先名称 */
		setSrhDeliveryName1(null);
		/** 検索入力：区分 */
		setSrhDivision(null);

	}

	/**
	 * 検索入力：納入先コード取得.
	 * @return String 納入先コード
	 */
	public String getSrhDeliveryCd() {
		return this.srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先コード設定.
	 * @param srhDeliveryCd 納入先コード
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先名称取得.
	 * @return String 納入先名称
	 */
	public String getSrhDeliveryName1() {
		return this.srhDeliveryName1;
	}

	/**
	 * 検索入力：納入先名称設定.
	 * @param srhDeliveryName1 納入先名称
	 */
	public void setSrhDeliveryName1(final String srhDeliveryName1) {
		this.srhDeliveryName1 = srhDeliveryName1;
	}

	/**
	 * 検索入力：区分取得.
	 * @return String 区分
	 */
	public String getSrhDivision() {
		return this.srhDivision;
	}

	/**
	 * 検索入力：区分設定.
	 * @param srhDivision 区分
	 */
	public void setSrhDivision(final String srhDivision) {
		this.srhDivision = srhDivision;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<SalesDeliverySearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<SalesDeliverySearchList> searchList) {
		this.searchList = searchList;
	}

}
