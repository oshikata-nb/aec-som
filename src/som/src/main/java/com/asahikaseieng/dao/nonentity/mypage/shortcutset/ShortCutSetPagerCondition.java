/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mypage.shortcutset;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * SectionPagerConditionクラス.
 * @author tosco
 */
public class ShortCutSetPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShortCutSetPagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：ロケーションコード */
	private String srhTantoCd;

	/** 検索入力：ロケーションコード */
	private String srhTantoNm;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力.ロケーションコード取得
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return this.srhTantoCd;
	}

	/**
	 * 検索入力.ロケーションコード設定.
	 * @param srhTantoCd srhTantoCd
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}

	/**
	 * 検索入力.ロケーション名称取得.
	 * @return srhTantoNm
	 */
	public String getSrhTantoNm() {
		return this.srhTantoNm;
	}

	/**
	 * 検索入力.ロケーション名称設定.
	 * @param srhTantoNm srhTantoNm
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = AecTextUtils.likeFilter(srhTantoNm);
	}
}
