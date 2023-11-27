/*
 * Created on Wed Feb 04 16:11:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterforklan;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterForklanBaseクラス.
 * @author kanri-user
 */
public class MasterForklanBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterForklanBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_FORKLAN";


//	/** IDアノテーション forklanno. */
//	public static final String forklanno_ID = "assigned";

	/** COLUMNアノテーション forklanno. */
	public static final String forklanno_COLUMN = "FORKLANNO";

	/** COLUMNアノテーション tanto1f. */
	public static final String tanto1f_COLUMN = "TANTO1F";

	/** COLUMNアノテーション tanto2f. */
	public static final String tanto2f_COLUMN = "TANTO2F";

	/** COLUMNアノテーション tantokiken. */
	public static final String tantokiken_COLUMN = "TANTOKIKEN";

	/** COLUMNアノテーション kaseibutsuryu. */
	public static final String kaseibutsuryu_COLUMN = "KASEIBUTSURYU";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal forklanno;

	private String tanto1f;

	private String tanto2f;

	private String tantokiken;

	private String kaseibutsuryu;

	//
	// インスタンスメソッド
	//

	/**
	 * forklanno取得.
	 * @return forklanno
	 */
	public java.math.BigDecimal getForklanno() {
		return this.forklanno;
	}

	/**
	 * forklanno設定.
	 * @param forklanno forklanno
	 */
	public void setForklanno(final java.math.BigDecimal forklanno) {
		this.forklanno = forklanno;
	}

	/**
	 * tanto1f取得.
	 * @return tanto1f
	 */
	public String getTanto1f() {
		return this.tanto1f;
	}

	/**
	 * tanto1f設定.
	 * @param tanto1f tanto1f
	 */
	public void setTanto1f(final String tanto1f) {
		this.tanto1f = tanto1f;
	}

	/**
	 * tanto2f取得.
	 * @return tanto2f
	 */
	public String getTanto2f() {
		return this.tanto2f;
	}

	/**
	 * tanto2f設定.
	 * @param tanto2f tanto2f
	 */
	public void setTanto2f(final String tanto2f) {
		this.tanto2f = tanto2f;
	}

	/**
	 * tantokiken取得.
	 * @return tantokiken
	 */
	public String getTantokiken() {
		return this.tantokiken;
	}

	/**
	 * tantokiken設定.
	 * @param tantokiken tantokiken
	 */
	public void setTantokiken(final String tantokiken) {
		this.tantokiken = tantokiken;
	}

	/**
	 * kaseibutsuryu取得.
	 * @return kaseibutsuryu
	 */
	public String getKaseibutsuryu() {
		return this.kaseibutsuryu;
	}

	/**
	 * kaseibutsuryu設定.
	 * @param kaseibutsuryu kaseibutsuryu
	 */
	public void setKaseibutsuryu(final String kaseibutsuryu) {
		this.kaseibutsuryu = kaseibutsuryu;
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
