/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 製造実績－ロット検索ポップアップ画面 Formクラス.
 * @author tosco
 */
public final class RdirectionLotInventorySearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 検索品目コード */
	private String srhItemCd;

	/** 品目名称 */
	private String srhItemName;

	/** 検索結果リスト */
	private List<RdirectionLotInventorySearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.search.common"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.search.common"));
	}

	/**
	 * コンストラクタ.
	 */
	public RdirectionLotInventorySearchForm() {
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
		return RdirectionLotInventorySearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			setSearchList(new ArrayList<RdirectionLotInventorySearchList>());
		}
	}

	/**
	 * 検索品目コード取得.
	 * @return String 検索品目コード
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 検索品目コード設定.
	 * @param srhItemCd 検索品目コード
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
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<RdirectionLotInventorySearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(
			final List<RdirectionLotInventorySearchList> searchList) {
		this.searchList = searchList;
	}
}
