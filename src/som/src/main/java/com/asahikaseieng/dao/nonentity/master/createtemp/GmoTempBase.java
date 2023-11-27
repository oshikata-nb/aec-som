/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * GMOTemp（原処方情報からの取得）
 * @author hongyo
 */
public class GmoTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public GmoTempBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "GMO_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション gmo */
	public static final String gmo_COLUMN = "GMO";

	/** COLUMNアノテーション gmoCd */
	public static final String gmoCd_COLUMN = "GMO_CD";

	/** COLUMNアノテーション gaitoKubun */
	public static final String gaitoKubun_COLUMN = "GAITO_KUBUN";

	//
	// インスタンスフィールド
	//

	/** ＳＥＱ */
	private BigDecimal seq;

	/** ＧＭＯ */
	private BigDecimal gmo;

	/** ＧＭＯコード */
	private String gmoCd;

	/** 該当区分 */
	private BigDecimal gaitoKubun;

	//
	// インスタンスメソッド
	//

	/**
	 * gaitoKubunを取得します。
	 * @return gaitoKubun
	 */
	public BigDecimal getGaitoKubun() {
		return gaitoKubun;
	}

	/**
	 * gaitoKubunを設定します。
	 * @param gaitoKubun gaitoKubun
	 */
	public void setGaitoKubun(final BigDecimal gaitoKubun) {
		this.gaitoKubun = gaitoKubun;
	}

	/**
	 * gmoを取得します。
	 * @return gmo
	 */
	public BigDecimal getGmo() {
		return gmo;
	}

	/**
	 * gmoを設定します。
	 * @param gmo gmo
	 */
	public void setGmo(final BigDecimal gmo) {
		this.gmo = gmo;
	}

	/**
	 * gmoCdを取得します。
	 * @return gmoCd
	 */
	public String getGmoCd() {
		return gmoCd;
	}

	/**
	 * gmoCdを設定します。
	 * @param gmoCd gmoCd
	 */
	public void setGmoCd(final String gmoCd) {
		this.gmoCd = gmoCd;
	}

	/**
	 * seqを取得します。
	 * @return seq
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

}
