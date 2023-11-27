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
 * BSETemp（原処方情報からの取得）
 * @author hongyo
 */
public class BseTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BseTempBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "BSE_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション bse */
	public static final String bse_COLUMN = "BSE";

	/** COLUMNアノテーション bseComment */
	public static final String bseComment_COLUMN = "BSE_COMMENT";

	//
	// インスタンスフィールド
	//

	/** ＳＥＱ */
	private BigDecimal seq;

	/** ＢＳＥ */
	private String bse;

	/** BSEコメント */
	private String bseComment;

	//
	// インスタンスメソッド
	//

	/**
	 * bseを取得します。
	 * @return bse
	 */
	public String getBse() {
		return bse;
	}

	/**
	 * bseを設定します。
	 * @param bse bse
	 */
	public void setBse(final String bse) {
		this.bse = bse;
	}

	/**
	 * bseCommentを取得します。
	 * @return bseComment
	 */
	public String getBseComment() {
		return bseComment;
	}

	/**
	 * bseCommentを設定します。
	 * @param bseComment bseComment
	 */
	public void setBseComment(final String bseComment) {
		this.bseComment = bseComment;
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
