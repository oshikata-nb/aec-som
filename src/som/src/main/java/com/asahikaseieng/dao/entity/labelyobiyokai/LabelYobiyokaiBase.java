/*
 * Created on Wed Feb 04 16:10:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.labelyobiyokai;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LabelYobiyokaiBaseクラス.
 * @author kanri-user
 */
public class LabelYobiyokaiBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LabelYobiyokaiBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LABEL_YOBIYOKAI";



	/** COLUMNアノテーション yobiyokaibcr. */
	public static final String yobiyokaibcr_COLUMN = "YOBIYOKAIBCR";

	/** COLUMNアノテーション seizosashizuno. */
	public static final String seizosashizuno_COLUMN = "SEIZOSASHIZUNO";

	/** COLUMNアノテーション yobiyokaino. */
	public static final String yobiyokaino_COLUMN = "YOBIYOKAINO";

	//
	// インスタンスフィールド
	//

	private String yobiyokaibcr;

	private String seizosashizuno;

	private String yobiyokaino;

	//
	// インスタンスメソッド
	//

	/**
	 * yobiyokaibcr取得.
	 * @return yobiyokaibcr
	 */
	public String getYobiyokaibcr() {
		return this.yobiyokaibcr;
	}

	/**
	 * yobiyokaibcr設定.
	 * @param yobiyokaibcr yobiyokaibcr
	 */
	public void setYobiyokaibcr(final String yobiyokaibcr) {
		this.yobiyokaibcr = yobiyokaibcr;
	}

	/**
	 * seizosashizuno取得.
	 * @return seizosashizuno
	 */
	public String getSeizosashizuno() {
		return this.seizosashizuno;
	}

	/**
	 * seizosashizuno設定.
	 * @param seizosashizuno seizosashizuno
	 */
	public void setSeizosashizuno(final String seizosashizuno) {
		this.seizosashizuno = seizosashizuno;
	}

	/**
	 * yobiyokaino取得.
	 * @return yobiyokaino
	 */
	public String getYobiyokaino() {
		return this.yobiyokaino;
	}

	/**
	 * yobiyokaino設定.
	 * @param yobiyokaino yobiyokaino
	 */
	public void setYobiyokaino(final String yobiyokaino) {
		this.yobiyokaino = yobiyokaino;
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
