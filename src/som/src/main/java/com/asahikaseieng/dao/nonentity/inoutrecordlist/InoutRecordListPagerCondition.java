/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlist;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * Conditionクラス.受払照会
 * @author T0011036
 */
public class InoutRecordListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InoutRecordListPagerCondition() {
	}

	/* 品目コード */
	private String srhItemCd;

	/* 他社コード1 */
	private String srhOtherCompanyCd1;

	/* ロケーションコード */
	private String srhLocationCd;

	/* 区分 */
	private BigDecimal srhInoutDivision;

	/* 日付(FROM) */
	private Timestamp srhInoutDateFrom;

	/* 日付(TO) */
	private Timestamp srhInoutDateTo;

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
	 * srhInoutDivisionを取得します。
	 * @return srhInoutDivision
	 */
	public BigDecimal getSrhInoutDivision() {
		return srhInoutDivision;
	}

	/**
	 * srhInoutDivisionを設定します。
	 * @param srhInoutDivision srhInoutDivision
	 */
	public void setSrhInoutDivision(final BigDecimal srhInoutDivision) {
		this.srhInoutDivision = srhInoutDivision;
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

	/**
	 * srhInoutDateFromを取得します。
	 * @return srhInoutDateFrom
	 */
	public Timestamp getSrhInoutDateFrom() {
		return srhInoutDateFrom;
	}

	/**
	 * srhInoutDateFromを設定します。
	 * @param srhInoutDateFrom srhInoutDateFrom
	 */
	public void setSrhInoutDateFrom(final Timestamp srhInoutDateFrom) {
		this.srhInoutDateFrom = srhInoutDateFrom;
	}

	/**
	 * srhInoutDateToを取得します。
	 * @return srhInoutDateTo
	 */
	public Timestamp getSrhInoutDateTo() {
		return srhInoutDateTo;
	}

	/**
	 * srhInoutDateToを設定します。
	 * @param srhInoutDateTo srhInoutDateTo
	 */
	public void setSrhInoutDateTo(final Timestamp srhInoutDateTo) {
		this.srhInoutDateTo = srhInoutDateTo;
	}
}
