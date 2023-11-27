/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import com.asahikaseieng.pager.Pager;

import org.seasar.dao.pager.ThresholdPagerCondition;
import org.seasar.framework.util.ClassUtil;

/**
 * 検索画面に必要な汎用処理を実装したActionFormクラス.
 * @author jbd
 */
public abstract class AbstractSearchForm extends AbstractForm {

	private Pager pager;

	/**
	 * コンストラクタ.
	 */
	public AbstractSearchForm() {
		super();
		initialize();
	}

	private void initialize() {
		pager = new Pager(getPagerCondition());
		pager.reset();
	}

	/**
	 * pagerを取得します。
	 * 
	 * @return pager Pager
	 */
	public final Pager getPager() {
		return pager;
	}

	/**
	 * 1page辺りの行数.
	 * 
	 * @return 1page辺りの行数
	 */
	protected abstract int getLimit();

	/**
	 * 最大データ数
	 * 
	 * @return 1page辺りの行数.
	 */
	protected abstract int getThreshold();

	/**
	 * 検索条件のクラスを取得する.
	 * 
	 * @return 検索条件のクラス
	 */
	protected abstract Class getPagerConditionClass();

	/**
	 * 子画面で設定したPagerConditionを取得する.
	 * @return PagerCondition
	 */
	protected final ThresholdPagerCondition getPagerCondition() {

		/* 子画面で設定したページャークラスをセット */
		ThresholdPagerCondition condition = (ThresholdPagerCondition) ClassUtil
				.newInstance(getPagerConditionClass());

		/* 子画面で設定した行数・閾値をセット */
		condition.setLimit(getLimit());
		condition.setThreshold(getThreshold());

		return condition;
	}
}
