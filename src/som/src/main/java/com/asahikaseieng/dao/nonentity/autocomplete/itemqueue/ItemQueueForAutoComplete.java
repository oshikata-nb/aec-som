/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.itemqueue;

/**
 * ItemQueueListForAutoCompleteクラス.
 * @author tosco
 */
public class ItemQueueForAutoComplete extends ItemQueueForAutoCompleteBase {

	private static final long serialVersionUID = 1L;
	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";
	/** COLUMNアノテーション smallnumLength */
	public static final String smallnumLength_COLUMN = "SMALLNUM_LENGTH";
	/** COLUMNアノテーション roundDivision */
	public static final String roundDivision_COLUMN = "ROUND_DIVISION";

	/** 名称１ */
	private String name01;
	/** 小数点桁数 */
	private String smallnumLength;
	/** 端数区分 */
	private String roundDivision;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueForAutoComplete() {
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

}
