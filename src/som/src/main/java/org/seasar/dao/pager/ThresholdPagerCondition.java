/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package org.seasar.dao.pager;

/**
 * 閾値付きPagerCondtion. <br>
 * (s2daoのソースを改造している為コーディング規約に沿っていません).
 * @author jbd
 */
public interface ThresholdPagerCondition extends PagerCondition {

	/**
	 * 閾値を取得する.
	 * 
	 * @return 閾値
	 */
	int getThreshold();

	/**
	 * 閾値を設定する.
	 * 
	 * @param threshold 閾値
	 */
	void setThreshold(final int threshold);

	/**
	 * 閾値を超えているかどうか取得する.
	 * 
	 * @return true 超えている false 超えていない
	 */
	boolean isOverThreshold();

	/**
	 * 閾値を超えているかどうか設定する.
	 * 
	 * @param overThreshold 閾値を超えているかどうか
	 */
	void setOverThreshold(final boolean overThreshold);
}
