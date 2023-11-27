/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costaccounting;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 原価計算データ送信 Beanクラス.
 * @author t0011036
 */
public class CostAccountingBean implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Boolean checked;

	private String tableName;

	private BigDecimal cnt;

	private String strCnt;

	private String result;

	/**
	 * コンストラクタ
	 */
	public CostAccountingBean() {
	}

	/**
	 * checkedを取得します。
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkedを設定します。
	 * @param checked checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}

	/**
	 * cntを取得します。
	 * @return cnt
	 */
	public BigDecimal getCnt() {
		return cnt;
	}

	/**
	 * cntを設定します。
	 * @param cnt cnt
	 */
	public void setCnt(final BigDecimal cnt) {
		this.cnt = cnt;
	}

	/**
	 * resultを取得します。
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * resultを設定します。
	 * @param result result
	 */
	public void setResult(final String result) {
		this.result = result;
	}

	/**
	 * strCntを取得します。
	 * @return strCnt
	 */
	public String getStrCnt() {
		return strCnt;
	}

	/**
	 * strCntを設定します。
	 * @param strCnt strCnt
	 */
	public void setStrCnt(final String strCnt) {
		this.strCnt = strCnt;
	}

	/**
	 * tableNameを取得します。
	 * @return tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * tableNameを設定します。
	 * @param tableName tableName
	 */
	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}
}
