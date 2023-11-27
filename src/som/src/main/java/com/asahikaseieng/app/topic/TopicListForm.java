/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.topic.TopicList;
import com.asahikaseieng.dao.nonentity.topic.TopicListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * 掲示板Formクラス.
 * @author a1020630
 */
public final class TopicListForm extends AbstractSearchForm {

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

	//
	// インスタンスフィールド
	//

	/* リスト */
	private List<TopicList> searchList;

	/**
	 * コンストラクタ.
	 */
	public TopicListForm() {
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
		return TopicListPagerCondition.class;
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

		if ("search".equals(getOp()) || "init".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<TopicList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア.
	 */
	private void clear() {
		/* リストのクリア */
		setSearchList(new ArrayList<TopicList>());
	}

	//
	// インスタンスメソッド.
	//

	/**
	 * 掲示板:searchListを取得します。
	 * @return searchList
	 */
	public List<TopicList> getSearchList() {
		return searchList;
	}

	/**
	 * 掲示板:searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<TopicList> searchList) {
		this.searchList = searchList;
	}

}
