/*
 * Created on Wed Feb 04 16:11:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tankkaijyo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TankKaijyoBaseクラス.
 * @author kanri-user
 */
public class TankKaijyoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TankKaijyoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "TANK_KAIJYO";


//	/** IDアノテーション chogotankno. */
//	public static final String chogotankno_ID = "assigned";

	/** COLUMNアノテーション chogotankno. */
	public static final String chogotankno_COLUMN = "CHOGOTANKNO";

	/** COLUMNアノテーション sumi. */
	public static final String sumi_COLUMN = "SUMI";

	/** COLUMNアノテーション interlock. */
	public static final String interlock_COLUMN = "INTERLOCK";

	//
	// インスタンスフィールド
	//

	private String chogotankno;

	private String sumi;

	private String interlock;

	//
	// インスタンスメソッド
	//

	/**
	 * chogotankno取得.
	 * @return chogotankno
	 */
	public String getChogotankno() {
		return this.chogotankno;
	}

	/**
	 * chogotankno設定.
	 * @param chogotankno chogotankno
	 */
	public void setChogotankno(final String chogotankno) {
		this.chogotankno = chogotankno;
	}

	/**
	 * sumi取得.
	 * @return sumi
	 */
	public String getSumi() {
		return this.sumi;
	}

	/**
	 * sumi設定.
	 * @param sumi sumi
	 */
	public void setSumi(final String sumi) {
		this.sumi = sumi;
	}

	/**
	 * interlock取得.
	 * @return interlock
	 */
	public String getInterlock() {
		return this.interlock;
	}

	/**
	 * interlock設定.
	 * @param interlock interlock
	 */
	public void setInterlock(final String interlock) {
		this.interlock = interlock;
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
