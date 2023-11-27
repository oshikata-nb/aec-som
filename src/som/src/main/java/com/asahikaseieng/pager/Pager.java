/*
 * Created on 2007/03/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.pager;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.pager.PagerCondition;
import org.seasar.dao.pager.PagerUtil;
import org.seasar.dao.pager.ThresholdPagerCondition;

/**
 * 閾値対応したPagerクラス.
 * @author jbd
 */

public class Pager implements Serializable {

	private static final long serialVersionUID = 1L;

	/* PagerCondition */
	private ThresholdPagerCondition condition;

	/**
	 * コンストラクタ
	 * 
	 * @param condition PagerCondition
	 */
	public Pager(final ThresholdPagerCondition condition) {
		this.condition = condition;
	}

	/**
	 * PagerConditionを取得する.
	 * @return PagerCondition
	 */
	public ThresholdPagerCondition getPagerCondition() {
		return condition;
	}

	/**
	 * PagerConditionをリセットする。
	 */
	public void reset() {
		condition.setCount(0);
		condition.setOffset(0);

		condition.setOverThreshold(false);
	}

	/**
	 * リクエストからページ番号を取得する.
	 * @param request リクエスト
	 * @return ページ番号
	 */
	public int getPage(final HttpServletRequest request) {
		String param = request.getParameter("page");
		int page = 1;
		if (StringUtils.isNotEmpty(param) && StringUtils.isNumeric(param)) {
			page = Integer.parseInt(param);
		}
		return page;
	}

	/**
	 * PagerConditionのOffsetを更新する。
	 * @param request リクエスト
	 */
	public void updateOffset(final HttpServletRequest request) {
		// リクエストからページ番号を取得し、オフセット番号を算出する
		condition.setOffset(page2offset(getPage(request)));
	}

	/**
	 * ページ番号からオフセット番号を取得する.
	 * @param page ページ番号
	 * @return オフセット番号
	 */
	private int page2offset(final int page) {
		int offset = 0;
		if (0 < page && 0 < condition.getLimit()) {
			offset = (page - 1) * condition.getLimit();
		}
		return offset;
	}

	/**
	 * オフセット番号からページ番号を取得する.
	 * @param offset オフセット番号
	 * @return ページ番号
	 */
	int offset2page(final int offset) {
		int page = 1;
		if (offset < condition.getCount() && 0 < condition.getLimit()) {
			page = (offset / condition.getLimit()) + 1;
		}
		return page;
	}

	/**
	 * 全体件数を取得する。
	 * @return 全体件数
	 */
	public int getCount() {
		return condition.getCount();
	}

	/**
	 * 1ページに表示する件数を取得する。
	 * @return 1ページに表示する件数
	 */
	public int getLimit() {
		return condition.getLimit();
	}

	/**
	 * 現在ページの最初のオフセット番号を取得する。
	 * @return 現在ページの最初のオフセット番号
	 */
	public int getCurrentFirstOffset() {
		return condition.getOffset();
	}

	/**
	 * 現在ページの最後のオフセット番号を取得する。
	 * @return 現在ページの最後のオフセット番号
	 */
	public int getCurrentLastOffset() {
		if (condition.getLimit() == PagerCondition.NONE_LIMIT) {
			return condition.getCount() - 1;
		}
		int offset = PagerUtil.getNextOffset(condition);
		if (condition.getCount() <= offset) {
			return condition.getCount() - 1;
		}
		return offset - 1;
	}

	/**
	 * 前ページがあるかどうか.
	 * @return true：ある false：ない
	 */
	public boolean isPrev() {
		if (condition.getLimit() == PagerCondition.NONE_LIMIT) {
			return false;
		}
		return PagerUtil.isPrev(condition);
	}

	/**
	 * 次ページがあるかどうか.
	 * @return true：ある false：ない
	 */
	public boolean isNext() {
		if (condition.getLimit() == PagerCondition.NONE_LIMIT) {
			return false;
		}
		return PagerUtil.isNext(condition);
	}

	/**
	 * 現在ページの最初のレコード番号を取得する。
	 * @return 現在ページの最初のレコード番号
	 */
	public int getCurrentFirstNo() {
		return getCurrentFirstOffset() + 1;
	}

	/**
	 * 現在ページの最後のレコード番号を取得する。
	 * @return 現在ページの最後のレコード番号
	 */
	public int getCurrentLastNo() {
		return getCurrentLastOffset() + 1;
	}

	/**
	 * 前のオフセット番号を取得する。
	 * @return 前のオフセット番号
	 */
	public int getPrevOffset() {
		if (condition.getLimit() == PagerCondition.NONE_LIMIT) {
			return 0;
		}
		return PagerUtil.getPrevOffset(condition);
	}

	/**
	 * 次のオフセット番号を取得する。
	 * @return 次のオフセット番号
	 */
	public int getNextOffset() {
		if (condition.getLimit() == PagerCondition.NONE_LIMIT) {
			return condition.getOffset();
		}
		return PagerUtil.getNextOffset(condition);
	}

	/**
	 * 前のページ番号を取得する。
	 * @return 前のページ番号
	 */
	public int getPrevPage() {
		return offset2page(getPrevOffset());
	}

	/**
	 * 次のページ番号を取得する。
	 * @return 次のページ番号
	 */
	public int getNextPage() {
		return offset2page(getNextOffset());
	}

	/**
	 * 現在のページの表示件数を取得する。
	 * @return 現在のページの表示件数
	 */
	public int getCurrentCount() {
		return getCurrentLastOffset() - getCurrentFirstOffset() + 1;
	}

	/**
	 * 現在のページ番号を取得する。
	 * @return 現在のページ番号
	 */
	public int getCurrentPage() {
		return offset2page(condition.getOffset());
	}

	/**
	 * 最終ページの番号を取得する。
	 * @return 最終ページのページ番号
	 */
	public int getLastPage() {
		int page = 1;
		if (0 < condition.getCount() && 0 < condition.getLimit()) {
			page = condition.getCount() / condition.getLimit();
			/* 余りがあれば＋１する */
			if (0 < condition.getCount() % condition.getLimit()) {
				page = page + 1;
			}
		}
		return page;
	}
}
