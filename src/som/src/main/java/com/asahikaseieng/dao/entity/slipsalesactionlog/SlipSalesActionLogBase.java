/*
 * Created on Thu Mar 12 11:47:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.slipsalesactionlog;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SlipSalesActionLogBaseクラス.
 * @author kanri-user
 */
public class SlipSalesActionLogBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipSalesActionLogBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SLIPSALES_ACTION_LOG";

	/** COLUMNアノテーション actionSeq. */
	public static final String actionSeq_COLUMN = "ACTION_SEQ";
	
	/** COLUMNアノテーション logDate. */
	public static final String logDate_COLUMN = "LOG_DATE";

	/** COLUMNアノテーション tantoCd. */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション action. */
	public static final String action_COLUMN = "ACTION";
	
	/** COLUMNアノテーション actionMsg. */
	public static final String actionMsg_COLUMN = "ACTION_MSG";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp logDate;

	private BigDecimal actionSeq;
	
	private String tantoCd;

	private String action;

	private String actionMsg;

	//
	// インスタンスメソッド
	//

	public java.sql.Timestamp getLogDate() {
		return logDate;
	}

	public void setLogDate(java.sql.Timestamp logDate) {
		this.logDate = logDate;
	}

	public String getTantoCd() {
		return tantoCd;
	}

	public void setTantoCd(String tantoCd) {
		this.tantoCd = tantoCd;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}
	
	public BigDecimal getActionSeq() {
		return actionSeq;
	}

	public void setActionSeq(BigDecimal actionSeq) {
		this.actionSeq = actionSeq;
	}


	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
