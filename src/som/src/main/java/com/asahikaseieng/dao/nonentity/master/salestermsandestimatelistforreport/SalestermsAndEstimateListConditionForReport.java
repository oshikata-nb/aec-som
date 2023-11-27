package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 販売条件検索条件
 * @author t0011036
 */
public class SalestermsAndEstimateListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public SalestermsAndEstimateListConditionForReport() {
	}

	/** 検索条件プロパティ * */
	/* 入力日From */
	private String strSrhInputDateFrom;
	
	/* 入力日To */
	private String strSrhInputDateTo;
	
	/* 入力者コード */
	private String srhTantoCd;
	
	/* 処理区分名称 */
	private java.math.BigDecimal srhProcessDivision;
	
	/* ステータス名称 */
	private java.math.BigDecimal srhStatus;
	
	/* コピー元・削除品目コード */
	private String srhItemCdFrom;
	
	/* コピー先品目コード */
	private String srhItemCdTo;

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * strSrhInputDateFromを取得します。
	 * @return strSrhInputDateFrom
	 */
	public final String getStrSrhInputDateFrom() {
		return strSrhInputDateFrom;
	}

	/**
	 * strSrhInputDateFromを設定します。
	 * @param strSrhInputDateFrom strSrhInputDateFrom
	 */
	public void setStrSrhInputDateFrom(final String strSrhInputDateFrom) {
		this.strSrhInputDateFrom = strSrhInputDateFrom;
	}

	/**
	 * strSrhInputDateToを取得します。
	 * @return strSrhInputDateTo
	 */
	public final String getStrSrhInputDateTo() {
		return strSrhInputDateTo;
	}

	/**
	 * strSrhInputDateToを設定します。
	 * @param strSrhInputDateTo strSrhInputDateTo
	 */
	public void setStrSrhInputDateTo(final String strSrhInputDateTo) {
		this.strSrhInputDateTo = strSrhInputDateTo;
	}

	/**
	 * srhTantoCdを取得します。
	 * @return srhTantoCd
	 */
	public final String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * srhTantoCdを設定します。
	 * @param srhTantoCd srhTantoCd
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}

	/**
	 * srhProcessDivisionを取得します。
	 * @return srhProcessDivision
	 */
	public final java.math.BigDecimal getSrhProcessDivision() {
		return srhProcessDivision;
	}

	/**
	 * srhProcessDivisionを設定します。
	 * @param srhProcessDivision srhProcessDivision
	 */
	public void setSrhProcessDivision(java.math.BigDecimal srhProcessDivision) {
		this.srhProcessDivision = srhProcessDivision;
	}

	/**
	 * srhStatusを取得します。
	 * @return srhStatus
	 */
	public final java.math.BigDecimal getSrhStatus() {
		return srhStatus;
	}

	/**
	 * srhStatusを設定します。
	 * @param srhStatus srhStatus
	 */
	public void setSrhStatus(java.math.BigDecimal srhStatus) {
		this.srhStatus = srhStatus;
	}

	/**
	 * srhItemCdFromを取得します。
	 * @return srhItemCdFrom
	 */
	public final String getSrhItemCdFrom() {
		return srhItemCdFrom;
	}

	/**
	 * srhItemCdFromを設定します。
	 * @param srhItemCdFrom srhItemCdFrom
	 */
	public void setSrhItemCdFrom(final String srhItemCdFrom) {
		this.srhItemCdFrom = srhItemCdFrom;
	}

	/**
	 * srhItemCdToを取得します。
	 * @return srhItemCdTo
	 */
	public final String getSrhItemCdTo() {
		return srhItemCdTo;
	}

	/**
	 * srhItemCdToを設定します。
	 * @param srhItemCdTo srhItemCdTo
	 */
	public void setSrhItemCdTo(final String srhItemCdTo) {
		this.srhItemCdTo = srhItemCdTo;
	}

}
