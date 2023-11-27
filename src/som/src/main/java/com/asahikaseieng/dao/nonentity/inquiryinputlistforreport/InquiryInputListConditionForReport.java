/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 帳票Excel検索条件
 * @author t1344224
 */
public class InquiryInputListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryInputListConditionForReport() {
	}

	/* 棚卸準備処理日 */
	private java.sql.Timestamp srhCountDate;

	/* ロケーションコード */
	private String srhLocationCd;

	/* 棚卸区分 */
	private String srhCountDivision;

	/* 品目コード */
	private String srhItemCd;

	/* 他社コード1 */
	private String srhOtherCompanyCd1;

	/* 原料ロット番号/製品ロット番号 */
	private String srhAliasLotNo;

	/**
	 * srhItemCdを取得します。
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * srhItemCdを設定します。
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * srhOtherCompanyCd1を取得します。
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * srhOtherCompanyCd1を設定します。
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * srhAliasLotNoを取得します。
	 * @return srhAliasLotNo
	 */
	public String getSrhAliasLotNo() {
		return srhAliasLotNo;
	}

	/**
	 * srhAliasLotNoを設定します。
	 * @param srhAliasLotNo srhAliasLotNo
	 */
	public void setSrhAliasLotNo(final String srhAliasLotNo) {
		this.srhAliasLotNo = AecTextUtils.likeFilter(srhAliasLotNo);
	}

	/**
	 * srhCountDateを取得します。
	 * @return srhCountDate
	 */
	public java.sql.Timestamp getSrhCountDate() {
		return srhCountDate;
	}

	/**
	 * srhCountDateを設定します。
	 * @param srhCountDate srhCountDate
	 */
	public void setSrhCountDate(final java.sql.Timestamp srhCountDate) {
		this.srhCountDate = srhCountDate;
	}

	/**
	 * srhCountDivisionを取得します。
	 * @return srhCountDivision
	 */
	public String getSrhCountDivision() {
		return srhCountDivision;
	}

	/**
	 * srhCountDivisionを設定します。
	 * @param srhCountDivision srhCountDivision
	 */
	public void setSrhCountDivision(final String srhCountDivision) {
		this.srhCountDivision = srhCountDivision;
	}

	/**
	 * srhLocationCdを取得します。
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * srhLocationCdを設定します。
	 * @param srhLocationCd srhLocationCd
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = AecTextUtils.likeFilter(srhLocationCd);
	}
}
