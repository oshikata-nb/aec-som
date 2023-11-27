/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.search.item;

/**
 * 品目マスタ検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class ItemSearchList extends ItemSearchListBase {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemUnit */
	public static final String itemUnit_COLUMN = "ITEM_UNIT";

	/** 単位 */
	private String itemUnit;

	/** 少数点桁数 */
	private String smallnumLength;

	/** 端数区分 */
	private String roundDivision;

	/**
	 * コンストラクタ.
	 */
	public ItemSearchList() {
		super();
	}

	/**
	 * 単位取得.
	 * @return String 単位
	 */
	public String getItemUnit() {
		return itemUnit;
	}

	/**
	 * 単位設定.
	 * @param itemUnit 単位
	 */
	public void setItemUnit(final String itemUnit) {
		this.itemUnit = itemUnit;
	}

	/**
	 * 少数点桁数を取得します。
	 * @return smallnumLength
	 */
	public String getSmallnumLength() {
		return smallnumLength;
	}

	/**
	 * 少数点桁数を設定します。
	 * @param smallnumLength 少数点桁数
	 */
	public void setSmallnumLength(final String smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivision
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivision 端数区分
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

}

