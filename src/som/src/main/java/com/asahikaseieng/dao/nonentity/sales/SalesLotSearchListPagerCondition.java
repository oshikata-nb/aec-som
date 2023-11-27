/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ロット検索(ポップアップ)検索条件
 * @author tosco
 */
public class SalesLotSearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public SalesLotSearchListPagerCondition() {
	}

	/** 品目コード */
	private String srhItemCd;

	/** ロケーションコード */
	private String srhLocationCd;

	/** ロット番号 */
	private String srhLotNo;

	/**
	 * 品目コードを取得します。
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * ロケーションコード を取得します。
	 * @return String ロケーションコード 
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * ロケーションコード を設定します。
	 * @param srhLocationCd ロケーションコード 
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = AecTextUtils.likeFilter(srhLocationCd);
	}

	/**
	 * ロット番号 を取得します。
	 * @return String ロット番号 
	 */
	public String getSrhLotNo() {
		return srhLotNo;
	}

	/**
	 * ロット番号 を設定します。
	 * @param srhLotNo ロット番号 
	 */
	public void setSrhLotNo(final String srhLotNo) {
		this.srhLotNo = AecTextUtils.likeFilter(srhLotNo);
	}
}
