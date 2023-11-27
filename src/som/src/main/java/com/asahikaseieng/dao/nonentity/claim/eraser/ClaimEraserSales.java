/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売上データ用Daoクラス.
 * @author tosco
 */
public class ClaimEraserSales extends ClaimEraserSalesBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 消込チェックフラグ */
	private boolean checkFlg;

	/** 売上日付(スラッシュ編集) */
	private String strSalesDate;

	/** 売上数量(カンマ編集) */
	private String strSalesQuantity;

	/** 決定単価(カンマ編集) */
	private String strDefineUnitprice;

	/** 売上金額(カンマ編集) */
	private String strSalesAmount;

	/** 消費税額(カンマ編集) */
	private String strTaxAmount;

	/** 請求金額(カンマ編集) */
	private String strTotalSalesAmount;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserSales() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックフラグ設定 */
		if ("0".equals(getCheckKbn())) {
			setCheckFlg(false);
		} else {
			setCheckFlg(true);
		}
		/* 日付にスラッシュを付与 */
		setStrSalesDate(AecDateUtils.dateFormat(getSalesDate(),
			"yyyy/MM/dd"));
		/* 数値にカンマを付与 */
		setStrSalesQuantity(AecNumberUtils.decimalFormat(getSalesQuantity(),
			"###,###.##"));
		setStrDefineUnitprice(AecNumberUtils.decimalFormat(getDefineUnitprice(),
			"###,###.##"));
		setStrSalesAmount(AecNumberUtils.decimalFormat(getSalesAmount(),
			"###,###.##"));
		setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
			"###,###.##"));
		setStrTotalSalesAmount(AecNumberUtils.decimalFormat(getTotalSalesAmount(),
			"###,###.##"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 消込チェックフラグ取得.
	 * @return boolean 消込チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * 消込チェックフラグ設定.
	 * @param checkFlg 消込チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 売上日付(スラッシュ編集)取得.
	 * @return String 売上日付(スラッシュ編集)
	 */
	public String getStrSalesDate() {
		return strSalesDate;
	}

	/**
	 * 売上日付(スラッシュ編集)設定.
	 * @param strSalesDate 売上日付(スラッシュ編集)
	 */
	public void setStrSalesDate(final String strSalesDate) {
		this.strSalesDate = strSalesDate;
	}

	/**
	 * 売上数量(カンマ編集)取得.
	 * @return String 売上数量(カンマ編集)
	 */
	public String getStrSalesQuantity() {
		return strSalesQuantity;
	}

	/**
	 * 売上数量(カンマ編集)設定.
	 * @param strSalesQuantity 売上数量(カンマ編集)
	 */
	public void setStrSalesQuantity(final String strSalesQuantity) {
		this.strSalesQuantity = strSalesQuantity;
	}

	/**
	 * 決定単価(カンマ編集)取得.
	 * @return String 決定単価(カンマ編集)
	 */
	public String getStrDefineUnitprice() {
		return strDefineUnitprice;
	}

	/**
	 * 決定単価(カンマ編集)設定.
	 * @param strDefineUnitprice 決定単価(カンマ編集)
	 */
	public void setStrDefineUnitprice(final String strDefineUnitprice) {
		this.strDefineUnitprice = strDefineUnitprice;
	}

	/**
	 * 売上金額(カンマ編集)取得.
	 * @return String 売上金額(カンマ編集)
	 */
	public String getStrSalesAmount() {
		return strSalesAmount;
	}

	/**
	 * 売上金額(カンマ編集)設定.
	 * @param strSalesAmount 売上金額(カンマ編集)
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
	}

	/**
	 * 消費税額(カンマ編集)取得.
	 * @return String 消費税額(カンマ編集)
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 消費税額(カンマ編集)設定.
	 * @param strTaxAmount 消費税額(カンマ編集)
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 請求金額(カンマ編集)取得.
	 * @return String 請求金額(カンマ編集)
	 */
	public String getStrTotalSalesAmount() {
		return strTotalSalesAmount;
	}

	/**
	 * 請求金額(カンマ編集)設定.
	 * @param strTotalSalesAmount 請求金額(カンマ編集)
	 */
	public void setStrTotalSalesAmount(final String strTotalSalesAmount) {
		this.strTotalSalesAmount = strTotalSalesAmount;
	}

}

