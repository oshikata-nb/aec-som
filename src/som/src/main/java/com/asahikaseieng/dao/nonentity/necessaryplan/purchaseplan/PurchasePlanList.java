/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.necessaryplan.purchaseplan;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;


/**
 * 購買計画テーブルの1行を表現するクラス
 * @author tosco
 */
public class PurchasePlanList extends PurchasePlanListBase implements
	PropertyTransferCallbacker {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 計画発注日(スラッシュ編集) */
	private String strOrderDate;
	/** 計画納期(スラッシュ編集) */
	private String strDueDate;
	/** 発注計画数(カンマ編集) */
	private String strPlanedQty;

	/**
	 * コンストラクタ.
	 */
	public PurchasePlanList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		setStrPlanedQty(AecNumberUtils.decimalFormat(getPlanedQty(),
			"###,###.##"));
		/* 日付にスラッシュを付与 */
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(),
			"yyyy/MM/dd"));
		setStrDueDate(AecDateUtils.dateFormat(getDueDate(),
			"yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 計画納期を取得します。
	 * @return strDueDate
	 */
	public String getStrDueDate() {
		return strDueDate;
	}

	/**
	 * 計画納期を設定します。
	 * @param strDueDate 計画納期
	 */
	public void setStrDueDate(final String strDueDate) {
		this.strDueDate = strDueDate;
	}

	/**
	 * 計画発注日を取得します。
	 * @return strOrderDate
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 計画発注日を設定します。
	 * @param strOrderDate 計画発注日
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 発注計画数を取得します。
	 * @return strPlanedQty
	 */
	public String getStrPlanedQty() {
		return strPlanedQty;
	}

	/**
	 * 発注計画数を設定します。
	 * @param strPlanedQty 発注計画数
	 */
	public void setStrPlanedQty(final String strPlanedQty) {
		this.strPlanedQty = strPlanedQty;
	}

}
