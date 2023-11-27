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
 * 残留農薬Temp（原処方情報からの取得）
 * @author hongyo
 */
public class AgriculturalTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AgriculturalTempBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "AGRICULTURAL_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション agriculturalChemicals */
	public static final String agriculturalChemicals_COLUMN = "AGRICULTURAL_CHEMICALS";

	/** COLUMNアノテーション agriculturalChemicalsComment */
	public static final String agriculturalChemicalsComment_COLUMN = "AGRICULTURAL_CHEMICALS_COMMENT";

	/** COLUMNアノテーション agriculturalChemicalsLink */
	public static final String agriculturalChemicalsLink_COLUMN = "AGRICULTURAL_CHEMICALS_LINK";

	//
	// インスタンスフィールド
	//

	/** ＳＥＱ */
	private BigDecimal seq;

	/** 残留農薬有無 */
	private BigDecimal agriculturalChemicals;

	/** 残留農薬コメント */
	private String agriculturalChemicalsComment;

	/** 残留農薬リンク */
	private String agriculturalChemicalsLink;

	//
	// インスタンスメソッド
	//

	/**
	 * agriculturalChemicalsを取得します。
	 * @return agriculturalChemicals
	 */
	public BigDecimal getAgriculturalChemicals() {
		return agriculturalChemicals;
	}

	/**
	 * agriculturalChemicalsを設定します。
	 * @param agriculturalChemicals agriculturalChemicals
	 */
	public void setAgriculturalChemicals(final BigDecimal agriculturalChemicals) {
		this.agriculturalChemicals = agriculturalChemicals;
	}

	/**
	 * agriculturalChemicalsCommentを取得します。
	 * @return agriculturalChemicalsComment
	 */
	public String getAgriculturalChemicalsComment() {
		return agriculturalChemicalsComment;
	}

	/**
	 * agriculturalChemicalsCommentを設定します。
	 * @param agriculturalChemicalsComment agriculturalChemicalsComment
	 */
	public void setAgriculturalChemicalsComment(final String agriculturalChemicalsComment) {
		this.agriculturalChemicalsComment = agriculturalChemicalsComment;
	}

	/**
	 * agriculturalChemicalsLinkを取得します。
	 * @return agriculturalChemicalsLink
	 */
	public String getAgriculturalChemicalsLink() {
		return agriculturalChemicalsLink;
	}

	/**
	 * agriculturalChemicalsLinkを設定します。
	 * @param agriculturalChemicalsLink agriculturalChemicalsLink
	 */
	public void setAgriculturalChemicalsLink(final String agriculturalChemicalsLink) {
		this.agriculturalChemicalsLink = agriculturalChemicalsLink;
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
