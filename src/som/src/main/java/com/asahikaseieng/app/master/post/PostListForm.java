/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.postlist.PostList;
import com.asahikaseieng.dao.nonentity.master.postlist.PostListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 役職一覧 Formクラス
 * @author t0011036
 */
public final class PostListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

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

	/* 役職コード */
	private String srhPostId;

	/* 役職名称 */
	private String srhPostName;

	/* 役職リスト */
	private List<PostList> searchList;

	/* 帳票Excel用検索条件 */
	private PostListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.役職マスタ
	 */
	public PostListForm() {
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
		return PostListPagerCondition.class;
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

		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<PostList>());
		}

		if ("search".equals(getOp()) || "update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhPostId(null);
		setSrhPostName(null);
		setSearchList(new ArrayList<PostList>());
		setCondition(null);
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<PostList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<PostList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhPostIdを取得します。
	 * @return srhPostId
	 */
	public String getSrhPostId() {
		return srhPostId;
	}

	/**
	 * srhPostIdを設定します。
	 * @param srhPostId srhPostId
	 */
	public void setSrhPostId(final String srhPostId) {
		this.srhPostId = srhPostId;
	}

	/**
	 * srhPostNameを取得します。
	 * @return srhPostName
	 */
	public String getSrhPostName() {
		return srhPostName;
	}

	/**
	 * srhPostNameを設定します。
	 * @param srhPostName srhPostName
	 */
	public void setSrhPostName(final String srhPostName) {
		this.srhPostName = srhPostName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public PostListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final PostListConditionForReport condition) {
		this.condition = condition;
	}
}
