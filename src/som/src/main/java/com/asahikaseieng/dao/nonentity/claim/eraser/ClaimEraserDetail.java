/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 消込入力詳細画面用Daoクラス.
 * @author tosco
 */
public class ClaimEraserDetail extends ClaimEraserDetailBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 上段 */
	/** 消込可能額(カンマ編集) */
	private String strEraserCapaAmount;

	/** 消込額(カンマ編集) */
	private String strEraserAmount;

	/** 消込残合計(カンマ編集) */
	private String strEraserBalanceAmount;

	/** 入金ﾄﾗﾝ.入金金額合計(カンマ編集) */
	private String strSumCreditAmount;

	/** 入金ﾄﾗﾝ.消込額合計(カンマ編集) */
	private String strSumEraserAmount;

	/** 入金ﾄﾗﾝ.入金消込残合計(カンマ編集) */
	private String strSumCreditEraserAmount;

	/** 消込日付 */
	private String strEraserDate;

	/** 中段 */
	/** 入金日付(スラッシュ編集) */
	private String strCreditDate;

	/** 入金ﾄﾗﾝ.入金金額(カンマ編集) */
	private String strCreditAmount;

	/** 入金ﾄﾗﾝ.入金消込残(カンマ編集) */
	private String strCreditEraserAmount;

	/** 計算用 */
	/** 請求ﾍｯﾀﾞｰ.消込額(DB取得時の初期値) */
	private BigDecimal defaultEraserAmount;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 上段
		/* 数値にカンマを付与 */
		setStrEraserCapaAmount(AecNumberUtils.decimalFormat(
			getEraserCapaAmount(), "###,###.##"));
		setStrEraserAmount(AecNumberUtils.decimalFormat(getEraserAmount(),
			"###,###.##"));
		setStrEraserBalanceAmount(AecNumberUtils.decimalFormat(
			getEraserBalanceAmount(), "###,###.##"));
		setStrSumCreditAmount(AecNumberUtils.decimalFormat(
			getSumCreditAmount(), "###,###.##"));
		setStrSumEraserAmount(AecNumberUtils.decimalFormat(
			getSumEraserAmount(), "###,###.##"));
		setStrSumCreditEraserAmount(AecNumberUtils.decimalFormat(
			getSumCreditEraserAmount(), "###,###.##"));

		/* 日付にスラッシュを付与 */
		setStrEraserDate(AecDateUtils.dateFormat(getEraserDate(), "yyyy/MM/dd"));

		// 中段
		/* 日付にスラッシュを付与 */
		setStrCreditDate(AecDateUtils.dateFormat(getCreditDate(), "yyyy/MM/dd"));
		/* 数値にカンマを付与 */
		setStrCreditAmount(AecNumberUtils.decimalFormat(getCreditAmount(),
			"###,###.##"));
		setStrCreditEraserAmount(AecNumberUtils.decimalFormat(
			getCreditEraserAmount(), "###,###.##"));

		// 請求ﾍｯﾀﾞｰ.消込額(DB取得時の初期値)
		setDefaultEraserAmount(getEraserAmount());
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 消込可能額(カンマ編集)取得.
	 * @return String 消込可能額(カンマ編集)
	 */
	public String getStrEraserCapaAmount() {
		return strEraserCapaAmount;
	}

	/**
	 * 消込可能額(カンマ編集)設定.
	 * @param strEraserCapaAmount 消込可能額(カンマ編集)
	 */
	public void setStrEraserCapaAmount(final String strEraserCapaAmount) {
		this.strEraserCapaAmount = strEraserCapaAmount;
	}

	/**
	 * 消込額(カンマ編集)取得.
	 * @return String 消込額(カンマ編集)
	 */
	public String getStrEraserAmount() {
		return strEraserAmount;
	}

	/**
	 * 消込額(カンマ編集)設定.
	 * @param strEraserAmount 消込額(カンマ編集)
	 */
	public void setStrEraserAmount(final String strEraserAmount) {
		this.strEraserAmount = strEraserAmount;
	}

	/**
	 * 消込残合計(カンマ編集)取得.
	 * @return String 消込残合計(カンマ編集)
	 */
	public String getStrEraserBalanceAmount() {
		return strEraserBalanceAmount;
	}

	/**
	 * 消込残合計(カンマ編集)設定.
	 * @param strEraserBalanceAmount 消込残合計(カンマ編集)
	 */
	public void setStrEraserBalanceAmount(final String strEraserBalanceAmount) {
		this.strEraserBalanceAmount = strEraserBalanceAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計(カンマ編集)取得.
	 * @return String 入金ﾄﾗﾝ.入金金額合計(カンマ編集)
	 */
	public String getStrSumCreditAmount() {
		return strSumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計(カンマ編集)設定.
	 * @param strSumCreditAmount 入金ﾄﾗﾝ.入金金額合計(カンマ編集)
	 */
	public void setStrSumCreditAmount(final String strSumCreditAmount) {
		this.strSumCreditAmount = strSumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計(カンマ編集)取得.
	 * @return String 入金ﾄﾗﾝ.消込額合計(カンマ編集)
	 */
	public String getStrSumEraserAmount() {
		return strSumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計(カンマ編集)設定.
	 * @param strSumEraserAmount 入金ﾄﾗﾝ.消込額合計(カンマ編集)
	 */
	public void setStrSumEraserAmount(final String strSumEraserAmount) {
		this.strSumEraserAmount = strSumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計(カンマ編集)取得.
	 * @return String 入金ﾄﾗﾝ.入金消込残合計(カンマ編集)
	 */
	public String getStrSumCreditEraserAmount() {
		return strSumCreditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計(カンマ編集)設定.
	 * @param strSumCreditEraserAmount 入金ﾄﾗﾝ.入金消込残合計(カンマ編集)
	 */
	public void setStrSumCreditEraserAmount(
			final String strSumCreditEraserAmount) {
		this.strSumCreditEraserAmount = strSumCreditEraserAmount;
	}

	/**
	 * 入金日付(スラッシュ編集)取得.
	 * @return String 入金日付(スラッシュ編集)
	 */
	public String getStrCreditDate() {
		return strCreditDate;
	}

	/**
	 * 入金日付(スラッシュ編集)設定.
	 * @param strCreditDate 入金日付(スラッシュ編集)
	 */
	public void setStrCreditDate(final String strCreditDate) {
		this.strCreditDate = strCreditDate;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額(カンマ編集)取得.
	 * @return String 入金ﾄﾗﾝ.入金金額(カンマ編集)
	 */
	public String getStrCreditAmount() {
		return strCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額(カンマ編集)設定.
	 * @param strCreditAmount 入金ﾄﾗﾝ.入金金額(カンマ編集)
	 */
	public void setStrCreditAmount(final String strCreditAmount) {
		this.strCreditAmount = strCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残(カンマ編集)取得.
	 * @return String 入金ﾄﾗﾝ.入金消込残(カンマ編集)
	 */
	public String getStrCreditEraserAmount() {
		return strCreditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残(カンマ編集)設定.
	 * @param strCreditEraserAmount 入金ﾄﾗﾝ.入金消込残(カンマ編集)
	 */
	public void setStrCreditEraserAmount(final String strCreditEraserAmount) {
		this.strCreditEraserAmount = strCreditEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込額(DB取得時の初期値)取得.
	 * @return BigDecimal 請求ﾍｯﾀﾞｰ.消込額(DB取得時の初期値)
	 */
	public BigDecimal getDefaultEraserAmount() {
		return defaultEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込額(DB取得時の初期値)設定.
	 * @param defaultEraserAmount 請求ﾍｯﾀﾞｰ.消込額(DB取得時の初期値)
	 */
	public void setDefaultEraserAmount(final BigDecimal defaultEraserAmount) {
		this.defaultEraserAmount = defaultEraserAmount;
	}

	/**
	 * strEraserDateを取得します。
	 * @return strEraserDate
	 */
	public String getStrEraserDate() {
		return strEraserDate;
	}

	/**
	 * strEraserDateを設定します。
	 * @param strEraserDate strEraserDate
	 */
	public void setStrEraserDate(final String strEraserDate) {
		this.strEraserDate = strEraserDate;
	}
}
