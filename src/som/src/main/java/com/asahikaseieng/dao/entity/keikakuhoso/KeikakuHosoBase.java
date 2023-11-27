/*
 * Created on Wed Apr 08 13:47:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakuhoso;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * KeikakuHosoBaseクラス.
 * @author kanri-user
 */
public class KeikakuHosoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public KeikakuHosoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "KEIKAKU_HOSO";


//	/** IDアノテーション hososashizuno. */
//	public static final String hososashizuno_ID = "assigned";

	/** COLUMNアノテーション hosobi. */
	public static final String hosobi_COLUMN = "HOSOBI";

	/** COLUMNアノテーション kojocode. */
	public static final String kojocode_COLUMN = "KOJOCODE";

	/** COLUMNアノテーション hososashizuno. */
	public static final String hososashizuno_COLUMN = "HOSOSASHIZUNO";

	/** COLUMNアノテーション seihinlotno. */
	public static final String seihinlotno_COLUMN = "SEIHINLOTNO";

	/** COLUMNアノテーション seizosashizuno. */
	public static final String seizosashizuno_COLUMN = "SEIZOSASHIZUNO";

	/** COLUMNアノテーション seihincode. */
	public static final String seihincode_COLUMN = "SEIHINCODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション keikakusu. */
	public static final String keikakusu_COLUMN = "KEIKAKUSU";

	/** COLUMNアノテーション nyukosu. */
	public static final String nyukosu_COLUMN = "NYUKOSU";

	/** COLUMNアノテーション hosojokyo. */
	public static final String hosojokyo_COLUMN = "HOSOJOKYO";

	//
	// インスタンスフィールド
	//

	private String hosobi;

	private String kojocode;

	private String hososashizuno;

	private String seihinlotno;

	private String seizosashizuno;

	private String seihincode;

	private String iocode;

	private String jishaflag;

	private String keikakusu;

	private String nyukosu;

	private String hosojokyo;

	//
	// インスタンスメソッド
	//

	/**
	 * hosobi取得.
	 * @return hosobi
	 */
	public String getHosobi() {
		return this.hosobi;
	}

	/**
	 * hosobi設定.
	 * @param hosobi hosobi
	 */
	public void setHosobi(final String hosobi) {
		this.hosobi = hosobi;
	}

	/**
	 * kojocode取得.
	 * @return kojocode
	 */
	public String getKojocode() {
		return this.kojocode;
	}

	/**
	 * kojocode設定.
	 * @param kojocode kojocode
	 */
	public void setKojocode(final String kojocode) {
		this.kojocode = kojocode;
	}

	/**
	 * hososashizuno取得.
	 * @return hososashizuno
	 */
	public String getHososashizuno() {
		return this.hososashizuno;
	}

	/**
	 * hososashizuno設定.
	 * @param hososashizuno hososashizuno
	 */
	public void setHososashizuno(final String hososashizuno) {
		this.hososashizuno = hososashizuno;
	}

	/**
	 * seihinlotno取得.
	 * @return seihinlotno
	 */
	public String getSeihinlotno() {
		return this.seihinlotno;
	}

	/**
	 * seihinlotno設定.
	 * @param seihinlotno seihinlotno
	 */
	public void setSeihinlotno(final String seihinlotno) {
		this.seihinlotno = seihinlotno;
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
	 * seihincode取得.
	 * @return seihincode
	 */
	public String getSeihincode() {
		return this.seihincode;
	}

	/**
	 * seihincode設定.
	 * @param seihincode seihincode
	 */
	public void setSeihincode(final String seihincode) {
		this.seihincode = seihincode;
	}

	/**
	 * iocode取得.
	 * @return iocode
	 */
	public String getIocode() {
		return this.iocode;
	}

	/**
	 * iocode設定.
	 * @param iocode iocode
	 */
	public void setIocode(final String iocode) {
		this.iocode = iocode;
	}

	/**
	 * jishaflag取得.
	 * @return jishaflag
	 */
	public String getJishaflag() {
		return this.jishaflag;
	}

	/**
	 * jishaflag設定.
	 * @param jishaflag jishaflag
	 */
	public void setJishaflag(final String jishaflag) {
		this.jishaflag = jishaflag;
	}

	/**
	 * keikakusu取得.
	 * @return keikakusu
	 */
	public String getKeikakusu() {
		return this.keikakusu;
	}

	/**
	 * keikakusu設定.
	 * @param keikakusu keikakusu
	 */
	public void setKeikakusu(final String keikakusu) {
		this.keikakusu = keikakusu;
	}

	/**
	 * nyukosu取得.
	 * @return nyukosu
	 */
	public String getNyukosu() {
		return this.nyukosu;
	}

	/**
	 * nyukosu設定.
	 * @param nyukosu nyukosu
	 */
	public void setNyukosu(final String nyukosu) {
		this.nyukosu = nyukosu;
	}

	/**
	 * hosojokyo取得.
	 * @return hosojokyo
	 */
	public String getHosojokyo() {
		return this.hosojokyo;
	}

	/**
	 * hosojokyo設定.
	 * @param hosojokyo hosojokyo
	 */
	public void setHosojokyo(final String hosojokyo) {
		this.hosojokyo = hosojokyo;
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
