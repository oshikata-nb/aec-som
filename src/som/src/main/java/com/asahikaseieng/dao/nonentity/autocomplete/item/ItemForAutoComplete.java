/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item;

import java.math.BigDecimal;

/**
 * ItemListForAutoCompleteクラス.
 * @author tosco
 */
public class ItemForAutoComplete extends ItemForAutoCompleteBase {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/** COLUMNアノテーション smallnumLength */
	public static final String smallnumLength_COLUMN = "SMALLNUM_LENGTH";

	/** COLUMNアノテーション roundDivision */
	public static final String roundDivision_COLUMN = "ROUND_DIVISION";

	/** COLUMNアノテーション sellingPrice */
	public static final String sellingPrice_COLUMN = "SELLING_PRICE";

	/** 名称１ */
	private String name01;

	/** 小数点桁数 */
	private String smallnumLength;

	/** 端数区分 */
	private String roundDivision;

	/** 標準販売単価 */
	private BigDecimal sellingPrice;

	/**
	 * コンストラクタ.
	 */
	public ItemForAutoComplete() {
		super();
	}

	/**
	 * 名称１を取得します。
	 * @return 名称１
	 */
	public String getName01() {
		return name01;
	}

	/**
	 * 名称１を設定します。
	 * @param name01 名称１
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * 小数点桁数を取得します。
	 * @return 小数点桁数
	 */
	public String getSmallnumLength() {
		return smallnumLength;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param smallnumLength 小数点桁数
	 */
	public void setSmallnumLength(final String smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * 端数区分を取得します。
	 * @return 端数区分
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

	/**
	 * sellingPriceを取得します。
	 * @return sellingPrice
	 */
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * sellingPriceを設定します。
	 * @param sellingPrice sellingPrice
	 */
	public void setSellingPrice(final BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
}
