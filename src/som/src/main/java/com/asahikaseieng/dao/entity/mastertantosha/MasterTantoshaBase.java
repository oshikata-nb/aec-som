/*
 * Created on Wed Feb 04 16:12:37 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastertantosha;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterTantoshaBaseクラス.
 * @author kanri-user
 */
public class MasterTantoshaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterTantoshaBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_TANTOSHA";


//	/** IDアノテーション tantocode. */
//	public static final String tantocode_ID = "assigned";

	/** COLUMNアノテーション tantocode. */
	public static final String tantocode_COLUMN = "TANTOCODE";

	/** COLUMNアノテーション tantosha. */
	public static final String tantosha_COLUMN = "TANTOSHA";

	/** COLUMNアノテーション tantoshakana. */
	public static final String tantoshakana_COLUMN = "TANTOSHAKANA";

	/** COLUMNアノテーション tantokubun. */
	public static final String tantokubun_COLUMN = "TANTOKUBUN";

	/** COLUMNアノテーション tantopasswd. */
	public static final String tantopasswd_COLUMN = "TANTOPASSWD";

	//
	// インスタンスフィールド
	//

	private String tantocode;

	private String tantosha;

	private String tantoshakana;

	private String tantokubun;

	private String tantopasswd;

	//
	// インスタンスメソッド
	//

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
	 * tantosha取得.
	 * @return tantosha
	 */
	public String getTantosha() {
		return this.tantosha;
	}

	/**
	 * tantosha設定.
	 * @param tantosha tantosha
	 */
	public void setTantosha(final String tantosha) {
		this.tantosha = tantosha;
	}

	/**
	 * tantoshakana取得.
	 * @return tantoshakana
	 */
	public String getTantoshakana() {
		return this.tantoshakana;
	}

	/**
	 * tantoshakana設定.
	 * @param tantoshakana tantoshakana
	 */
	public void setTantoshakana(final String tantoshakana) {
		this.tantoshakana = tantoshakana;
	}

	/**
	 * tantokubun取得.
	 * @return tantokubun
	 */
	public String getTantokubun() {
		return this.tantokubun;
	}

	/**
	 * tantokubun設定.
	 * @param tantokubun tantokubun
	 */
	public void setTantokubun(final String tantokubun) {
		this.tantokubun = tantokubun;
	}

	/**
	 * tantopasswd取得.
	 * @return tantopasswd
	 */
	public String getTantopasswd() {
		return this.tantopasswd;
	}

	/**
	 * tantopasswd設定.
	 * @param tantopasswd tantopasswd
	 */
	public void setTantopasswd(final String tantopasswd) {
		this.tantopasswd = tantopasswd;
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
