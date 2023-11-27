/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package org.seasar.dao.pager;

import java.io.Serializable;

/**
 * 閾値付きPagerCondtionのデフォルト実装.<br> 
 * (s2daoのソースを改造している為コーディング規約に沿っていません).
 * @author jbd
 */
public class DefaultThresholdPagerCondition implements ThresholdPagerCondition,
		Serializable {

	private static final long serialVersionUID = 1L;

	/* 現在の位置 */
	private int offset;

	/* 表示の最大値 */
	private int limit = NONE_LIMIT;

	/* 取得した総数 */
	private int count;

	/* 閾値 */
	private int threshold;

	/* 閾値を超えているかどうか */
	private boolean overThreshold;

	/**
	 * コンストラクタ.
	 */
	public DefaultThresholdPagerCondition() {
	}

	/**
	 * @return Returns the total.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param total The total to set.
	 */
	public void setCount(final int total) {
		this.count = total;
	}

	/**
	 * @return Returns the limit.
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit The limit to set.
	 */
	public void setLimit(final int limit) {
		this.limit = limit;
	}

	/**
	 * @return Returns the offset.
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset The offset to set.
	 */
	public void setOffset(final int offset) {
		this.offset = offset;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isOverThreshold() {
		return overThreshold;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setOverThreshold(final boolean overThreshold) {
		this.overThreshold = overThreshold;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setThreshold(final int threshold) {
		this.threshold = threshold;
	}
}
