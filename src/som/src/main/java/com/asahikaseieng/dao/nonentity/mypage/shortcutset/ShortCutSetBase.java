/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mypage.shortcutset;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ショートカット設定
 * @author tosco
 */
public class ShortCutSetBase implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShortCutSetBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SHORT_CUT";

	/** COLUMNアノテーション tantocd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション menuid */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション menuid */
	public static final String seqNo_COLUMN = "SEQ_NO";


	//
	// インスタンスフィールド
	//

	private String tantoCd;

	private BigDecimal menuId;

	private BigDecimal seqNo;

	//
	// インスタンスメソッド
	//

	/**
	 * 担当者コードの取得
	 * @return 担当者コード
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 担当者コードの設定
	 * @param tantocd 担当者コード
	 */
	public void setTantoCd(final String tantocd) {
		this.tantoCd = tantocd;
	}

	/**
	 * メニューIDの取得
	 * @return メニューID
	 */
	public BigDecimal getMenuId() {
		return menuId;
	}

	/**
	 * メニューIDの設定
	 * @param menuid メニューID
	 */
	public void setMenuId(final BigDecimal menuid) {
		this.menuId = menuid;
	}

	/**
	 * seqNoの取得
	 * @return seqNo
	 */
	public BigDecimal getSeqNo() {
		return seqNo;
	}

	/**
	 * seqNoの設定
	 * @param seqNo seqNo
	 */
	public void setSeqNo(final BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

}
