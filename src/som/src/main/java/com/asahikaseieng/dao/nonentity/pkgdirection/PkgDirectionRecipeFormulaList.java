/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装指図－処方フォーミュラデータ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionRecipeFormulaList extends PkgDirectionRecipeFormulaListBase
	implements PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** COLUMNアノテーション numberOfInsertions */
	public static final String numberOfInsertions_COLUMN = "NUMBER_OF_INSERTIONS";
	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** 入数  */
	private BigDecimal numberOfInsertions;

	/** 運用管理単位 */
	private String unitDivision;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeFormulaList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 入数を取得します。
	 * @return numberOfInsertions
	 */
	public BigDecimal getNumberOfInsertions() {
		return numberOfInsertions;
	}

	/**
	 * 入数を設定します。
	 * @param numberOfInsertions 入数
	 */
	public void setNumberOfInsertions(final BigDecimal numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitDivision 運用管理単位
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}
}
