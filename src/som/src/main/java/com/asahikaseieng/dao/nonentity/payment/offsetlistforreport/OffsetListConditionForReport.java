/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offsetlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * OffsetPagerConditionクラス.グループ間相殺入力
 * @author tosco
 */
public class OffsetListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.OffsetPagerCondition
	 */
	public OffsetListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：相殺グループFROM */
	private String srhOffsetGrp;

	/** 検索入力：相殺日付FROM */
	private String srhOffsetDateFrom;

	/** 検索入力：相殺日付TO */
	private String srhOffsetDateTo;

	/** 検索入力：分類 */
	private String srhCassification;

	/** 出力区分 */
	private String srhOutputDivision;

	/**
	 * 部署コードを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param srhOrganizationCd 部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * 担当者コードを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * 担当者コードを設定します。
	 * @param srhTantoCd 担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}

	/**
	 * 相殺グループFROMを取得します。
	 * @return srhOffsetGrp
	 */
	public String getSrhOffsetGrp() {
		return srhOffsetGrp;
	}

	/**
	 * 相殺グループFROMを設定します。
	 * @param srhOffsetGrp 相殺グループFROM
	 */
	public void setSrhOffsetGrp(final String srhOffsetGrp) {
		this.srhOffsetGrp = srhOffsetGrp;
	}

	/**
	 * 分類を取得します。
	 * @return srhCassification
	 */
	public String getSrhCassification() {
		return srhCassification;
	}

	/**
	 * 分類を設定します。
	 * @param srhCassification 分類
	 */
	public void setSrhCassification(final String srhCassification) {
		this.srhCassification = srhCassification;
	}

	/**
	 * 出力区分を取得します。
	 * @return srhOutputDivision
	 */
	public String getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 出力区分を設定します。
	 * @param srhOutputDivision 出力区分
	 */
	public void setSrhOutputDivision(final String srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	/**
	 * srhOffsetDateFromを取得します。
	 * @return srhOffsetDateFrom
	 */
	public String getSrhOffsetDateFrom() {
		return srhOffsetDateFrom;
	}

	/**
	 * srhOffsetDateFromを設定します。
	 * @param srhOffsetDateFrom srhOffsetDateFrom
	 */
	public void setSrhOffsetDateFrom(final String srhOffsetDateFrom) {
		this.srhOffsetDateFrom = srhOffsetDateFrom;
	}

	/**
	 * srhOffsetDateToを取得します。
	 * @return srhOffsetDateTo
	 */
	public String getSrhOffsetDateTo() {
		return srhOffsetDateTo;
	}

	/**
	 * srhOffsetDateToを設定します。
	 * @param srhOffsetDateTo srhOffsetDateTo
	 */
	public void setSrhOffsetDateTo(final String srhOffsetDateTo) {
		this.srhOffsetDateTo = srhOffsetDateTo;
	}
}
