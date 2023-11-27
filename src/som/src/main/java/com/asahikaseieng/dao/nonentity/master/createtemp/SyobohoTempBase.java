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
 * 消防法Temp（原処方情報からの取得）
 * @author hongyo
 */
public class SyobohoTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SyobohoTempBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "SYOBOHO_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション syoboho */
	public static final String syoboho_COLUMN = "SYOBOHO";




	//
	//	 インスタンスフィールド
	//



	/** ＳＥＱ */
	private BigDecimal seq;

	/** 消防法表示 */
	private BigDecimal syoboho;


	//
	// インスタンスメソッド
	//

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

	/**
	 * syobohoを取得します。
	 * @return syoboho
	 */
	public BigDecimal getSyoboho() {
		return syoboho;
	}

	/**
	 * syobohoを設定します。
	 * @param syoboho syoboho
	 */
	public void setSyoboho(final BigDecimal syoboho) {
		this.syoboho = syoboho;
	}

}
