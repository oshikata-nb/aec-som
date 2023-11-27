/*
 * Created on 2008/07/08
 *
 * $copyright$
 * tosco:分類マスタ
 */
package com.asahikaseieng.app.master.classification;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationList;
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 分類マスタ一覧 Formクラス
 * @author tosco
 */
public final class ClassificationListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.分類マスタ
	//

	/** 検索入力：データ種別 */
	private String srhDataType;

	/** 検索入力：データ集計区分 */
	private String srhDataTotalDivision;

	/** 検索入力：分類コード */
	private String srhCategoryDivision;

	/** 検索入力：分類名称 */
	private String srhCategoryName;

	/** 検索エリア：データ集計区分（判断用） */
	private String hidennDataTotalDivision;

	//
	// インスタンスフィールド.分類マスタ
	//

	/** 一覧リスト */
	private List<ClassificationList> searchList;

	/**
	 * コンストラクタ.分類マスタ
	 */
	public ClassificationListForm() {
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
		return ClassificationPagerCondition.class;
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
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<ClassificationList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.分類マスタ
	 */
	public void clear() {

		/* 検索リストのクリア */
		setSearchList(new ArrayList<ClassificationList>());

		/* 検索入力：データ種別 */
		setSrhDataType(null);
		/* 検索入力：データ集計区分 */
		setSrhDataTotalDivision(null);
		/* 検索入力：分類コード */
		setSrhCategoryDivision(null);
		/* 検索入力：分類名称 */
		setSrhCategoryName(null);

	}

	/**
	 * 分類マスタ：検索結果リストを取得します。
	 * 
	 * @return List<CategoryList> 検索結果リスト
	 * 
	 */
	public List<ClassificationList> getSearchList() {
		return searchList;
	}

	/**
	 * 分類マスタ： 検索結果リストを設定します。
	 * 
	 * @param searchList 検索結果リスト
	 * 
	 */
	public void setSearchList(final List<ClassificationList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.分類マスタ
	//

	/**
	 * 検索入力：データ種別取得.
	 * @return BigDecimal データ種別
	 */
	public String getSrhDataType() {
		return srhDataType;
	}

	/**
	 * 検索入力：データ種別設定.
	 * @param srhDataType データ種別
	 */
	public void setSrhDataType(final String srhDataType) {
		this.srhDataType = srhDataType;
	}

	/**
	 * 検索入力：データ集計区分取得.
	 * @return BigDecimal データ集計区分
	 * 
	 */
	public String getSrhDataTotalDivision() {
		return srhDataTotalDivision;
	}

	/**
	 * 検索入力：データ集計区分設定.
	 * @param srhDataTotalDivision データ集計区分
	 * 
	 */
	public void setSrhDataTotalDivision(final String srhDataTotalDivision) {
		this.srhDataTotalDivision = srhDataTotalDivision;
	}

	/**
	 * 検索入力：分類コード取得.
	 * @return String 分類コード
	 * 
	 */
	public String getSrhCategoryDivision() {
		return srhCategoryDivision;
	}

	/**
	 * 検索入力：分類コード設定.
	 * @param strCategoryDivision 分類コード
	 * 
	 */
	public void setSrhCategoryDivision(final String strCategoryDivision) {
		this.srhCategoryDivision = strCategoryDivision;
	}

	/**
	 * 検索入力：分類名称取得.
	 * @return String 分類名称
	 */
	public String getSrhCategoryName() {
		return srhCategoryName;
	}

	/**
	 * 検索入力：分類名称設定.
	 * @param srhCategoryName 分類名称
	 */
	public void setSrhCategoryName(final String srhCategoryName) {
		this.srhCategoryName = srhCategoryName;
	}

	/**
	 * 検索エリア：データ集計区分（判断用）を取得します。
	 * @return hidennDataTotalDivision
	 */
	public String getHidennDataTotalDivision() {
		return hidennDataTotalDivision;
	}

	/**
	 * 検索エリア：データ集計区分（判断用）を設定します。
	 * @param hidennDataTotalDivision 検索エリア：データ集計区分（判断用）
	 */
	public void setHidennDataTotalDivision(final String hidennDataTotalDivision) {
		this.hidennDataTotalDivision = hidennDataTotalDivision;
	}

	//
	// インスタンスメソッド.分類マスタ
	//

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

}
