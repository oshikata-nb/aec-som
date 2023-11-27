/*
 * Created on Wed Feb 04 16:11:59 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterhbcr;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterHbcrBaseクラス.
 * @author kanri-user
 */
public class MasterHbcrBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterHbcrBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_HBCR";


//	/** IDアノテーション hbcrno. */
//	public static final String hbcrno_ID = "assigned";

	/** COLUMNアノテーション hbcrno. */
	public static final String hbcrno_COLUMN = "HBCRNO";

	/** COLUMNアノテーション tantocode. */
	public static final String tantocode_COLUMN = "TANTOCODE";

	/** COLUMNアノテーション riyokaishi. */
	public static final String riyokaishi_COLUMN = "RIYOKAISHI";

	//
	// インスタンスフィールド
	//

	private String hbcrno;

	private String tantocode;

	private java.sql.Timestamp riyokaishi;

	//
	// インスタンスメソッド
	//

	/**
	 * hbcrno取得.
	 * @return hbcrno
	 */
	public String getHbcrno() {
		return this.hbcrno;
	}

	/**
	 * hbcrno設定.
	 * @param hbcrno hbcrno
	 */
	public void setHbcrno(final String hbcrno) {
		this.hbcrno = hbcrno;
	}

	/**
	 * tantocode取得.
	 * @return tantocode
	 */
	public String getTantocode() {
		return this.tantocode;
	}

	/**
	 * tantocode設定.
	 * @param tantocode tantocode
	 */
	public void setTantocode(final String tantocode) {
		this.tantocode = tantocode;
	}

	/**
	 * riyokaishi取得.
	 * @return riyokaishi
	 */
	public java.sql.Timestamp getRiyokaishi() {
		return this.riyokaishi;
	}

	/**
	 * riyokaishi設定.
	 * @param riyokaishi riyokaishi
	 */
	public void setRiyokaishi(final java.sql.Timestamp riyokaishi) {
		this.riyokaishi = riyokaishi;
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
