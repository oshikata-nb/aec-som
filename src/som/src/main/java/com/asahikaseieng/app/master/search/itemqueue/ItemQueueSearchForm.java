/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.itemqueue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchList;
import com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 品目マスタキュー検索(ポップアップ) Formクラス.
 * @author tosco
 */
public final class ItemQueueSearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 品目コード */
	private String srhItemCd;

	/** 品目名称 */
	private String srhItemName;

	/** 他社コード1 */
	private String srhOtherCompanyCd1;

	/** 検索結果リスト */
	private List<ItemQueueSearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.search.common"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.search.common"));
	}

	/**
	 * コンストラクタ.
	 */
	public ItemQueueSearchForm() {
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
		return ItemQueueSearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			setSearchList(new ArrayList<ItemQueueSearchList>());
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
	 * クリア処理.
	 */
	private void clear() {
		// 品目コード
		setSrhItemCd(null);
		// 品目名称
		setSrhItemName(null);
		// 他社コード1
		setSrhOtherCompanyCd1(null);
	}

	/**
	 * 品目コードを取得します。
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 品目名称を取得します。
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 他社コード1を取得します。
	 * @return String 他社コード1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * 他社コード1を設定します。
	 * @param srhOtherCompanyCd1 他社コード1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<ItemQueueSearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<ItemQueueSearchList> searchList) {
		this.searchList = searchList;
	}

}
