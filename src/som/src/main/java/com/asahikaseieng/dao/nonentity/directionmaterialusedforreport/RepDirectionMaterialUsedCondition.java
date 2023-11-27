/*
 * Created on 2009/10/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionmaterialusedforreport;

import java.math.BigDecimal;

/**
 * InoutMaterialクラス.
 * @author kanri-user
 */
public class RepDirectionMaterialUsedCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepDirectionMaterialUsedCondition() {
		super();
	}

	/** 品目コード */
	private String srhItemCd;

	/** 他社コード1 */
	private String srhOtherCompanyCd1;

	/** 油脂化成品区分 */
	private String srhBalanceType;

	/** 原料包材区分 */
	private String srhDirectionDivision;

	/** 荷主区分 */
	private String srhShipperDivision;

	/** 前月 */
	private BigDecimal srhDatePrev;

	/** 処理開始月 */
	private BigDecimal srhDateFrom;

	/** 処理終了月 */
	private BigDecimal srhDateTo;

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
		this.srhItemCd = srhItemCd;
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
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * srhDirectionDivisionを取得します。
	 * @return srhDirectionDivision
	 */
	public String getSrhDirectionDivision() {
		return srhDirectionDivision;
	}

	/**
	 * srhDirectionDivisionを設定します。
	 * @param srhDirectionDivision srhDirectionDivision
	 */
	public void setSrhDirectionDivision(final String srhDirectionDivision) {
		this.srhDirectionDivision = srhDirectionDivision;
	}

	/**
	 * srhBalanceTypeを取得します。
	 * @return srhBalanceType
	 */
	public String getSrhBalanceType() {
		return srhBalanceType;
	}

	/**
	 * srhBalanceTypeを設定します。
	 * @param srhBalanceType srhBalanceType
	 */
	public void setSrhBalanceType(final String srhBalanceType) {
		this.srhBalanceType = srhBalanceType;
	}

	/**
	 * srhShipperDivisionを取得します。
	 * @return srhShipperDivision
	 */
	public String getSrhShipperDivision() {
		return srhShipperDivision;
	}

	/**
	 * srhShipperDivisionを設定します。
	 * @param srhShipperDivision srhShipperDivision
	 */
	public void setSrhShipperDivision(final String srhShipperDivision) {
		this.srhShipperDivision = srhShipperDivision;
	}

	/**
	 * srhDateFromを取得します。
	 * @return srhDateFrom
	 */
	public BigDecimal getSrhDateFrom() {
		return srhDateFrom;
	}

	/**
	 * srhDateFromを設定します。
	 * @param srhDateFrom srhDateFrom
	 */
	public void setSrhDateFrom(final BigDecimal srhDateFrom) {
		this.srhDateFrom = srhDateFrom;
	}

	/**
	 * srhDatePrevを取得します。
	 * @return srhDatePrev
	 */
	public BigDecimal getSrhDatePrev() {
		return srhDatePrev;
	}

	/**
	 * srhDatePrevを設定します。
	 * @param srhDatePrev srhDatePrev
	 */
	public void setSrhDatePrev(final BigDecimal srhDatePrev) {
		this.srhDatePrev = srhDatePrev;
	}

	/**
	 * srhDateToを取得します。
	 * @return srhDateTo
	 */
	public BigDecimal getSrhDateTo() {
		return srhDateTo;
	}

	/**
	 * srhDateToを設定します。
	 * @param srhDateTo srhDateTo
	 */
	public void setSrhDateTo(final BigDecimal srhDateTo) {
		this.srhDateTo = srhDateTo;
	}

}
