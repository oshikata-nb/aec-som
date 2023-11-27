/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailSearchList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 包装実績－製造指図明細ポップアップ画面 Formクラス.
 * @author tosco
 */
public final class PkgRdirectionDirectionDetailSearchForm extends
		AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 指図区分 */
	private String directionDivision;

	/** 包装指図番号 */
	private String pkgDirectionNo;

	/** 品目名称 */
	private String itemName;

	/** 検索結果リスト */
	private List<PkgRdirectionDirectionDetailSearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.search.common"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.search.common"));
	}

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionDirectionDetailSearchForm() {
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
		return PkgRdirectionDirectionDetailSearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			setSearchList(new ArrayList<PkgRdirectionDirectionDetailSearchList>());
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
	 * 指図区分取得.
	 * @return String 指図区分
	 */
	public String getDirectionDivision() {
		return directionDivision;
	}

	/**
	 * 指図区分設定.
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final String directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 包装指図番号取得.
	 * @return String 包装指図番号
	 */
	public String getPkgDirectionNo() {
		return pkgDirectionNo;
	}

	/**
	 * 包装指図番号設定.
	 * @param pkgDirectionNo 包装指図番号
	 */
	public void setPkgDirectionNo(final String pkgDirectionNo) {
		this.pkgDirectionNo = pkgDirectionNo;
	}

	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<PkgRdirectionDirectionDetailSearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(
			final List<PkgRdirectionDirectionDetailSearchList> searchList) {
		this.searchList = searchList;
	}
}
