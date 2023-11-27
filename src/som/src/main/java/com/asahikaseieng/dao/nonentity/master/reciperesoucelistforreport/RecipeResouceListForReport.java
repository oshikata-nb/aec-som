/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport;

import com.asahikaseieng.utils.AecNumberUtils;

/**
 * RecipeResouceListForReportクラス.
 * @author t0011036
 */
public class RecipeResouceListForReport extends RecipeResouceListForReportBase {

	private static final long serialVersionUID = 1L;

	/* 作業時間単価(機械) */
	private String strCostMachine;

	/* 作業時間単価(一律) */
	private String strCost;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceListForReport() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrCostMachine(AecNumberUtils.decimalFormat(getCostMachine(),
			"#,##0.00000000"));
		setStrCost(AecNumberUtils.decimalFormat(getCost(), "#,##0.00000000"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setCostMachine(AecNumberUtils.convertBigDecimal(getStrCostMachine()));
		setCost(AecNumberUtils.convertBigDecimal(getStrCost()));
	}

	/**
	 * strCostを取得します。
	 * @return strCost
	 */
	public String getStrCost() {
		return strCost;
	}

	/**
	 * strCostを設定します。
	 * @param strCost strCost
	 */
	public void setStrCost(final String strCost) {
		this.strCost = strCost;
	}

	/**
	 * strCostMachineを取得します。
	 * @return strCostMachine
	 */
	public String getStrCostMachine() {
		return strCostMachine;
	}

	/**
	 * strCostMachineを設定します。
	 * @param strCostMachine strCostMachine
	 */
	public void setStrCostMachine(final String strCostMachine) {
		this.strCostMachine = strCostMachine;
	}
}
