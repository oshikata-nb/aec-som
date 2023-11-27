/*
 * Created on 2014/03/05
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.common.procedurelog;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * ログファイル 検索条件
 *
 * @author atts
 */
public class ProcedureLogPagerCondition extends DefaultThresholdPagerCondition {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProcedureLogPagerCondition() {
	}

	//
	// 検索入力用
	//
	/** 検索入力：プロシージャ名 */
	private String procCd;

	/** 検索入力：処理日　*/
	private java.sql.Timestamp procDate;

	/** 検索入力：連番 */
	private java.math.BigDecimal procSeq;

	/** 検索入力：更新日時 */
	private java.sql.Timestamp updateDate;

	/** 検索入力：更新者ID */
	private String updatorCd;

	/**
	 * procCdを取得します。
	 * @return procCd
	 */
	public String getProcCd() {
		return procCd;
	}

	/**
	 * procCdを設定します。
	 * @param procCd procCd
	 */
	public void setProcCd(final String procCd) {
		this.procCd = procCd;
	}

	/**
	 * procDateを取得します。
	 * @return procDate
	 */
	public java.sql.Timestamp getProcDate() {
		return procDate;
	}

	/**
	 * procDateを設定します。
	 * @param procDate procDate
	 */
	public void setProcDate(final java.sql.Timestamp procDate) {
		this.procDate = procDate;
	}

	/**
	 * procSeqを取得します。
	 * @return procSeq
	 */
	public java.math.BigDecimal getProcSeq() {
		return procSeq;
	}

	/**
	 * procSeqを設定します。
	 * @param procSeq procSeq
	 */
	public void setProcSeq(final java.math.BigDecimal procSeq) {
		this.procSeq = procSeq;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

}
