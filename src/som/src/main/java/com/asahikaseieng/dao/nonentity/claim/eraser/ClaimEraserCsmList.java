/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 消込入力詳細(カスタマイズ) 消込トランザクション(カスタマイズ)登録更新用Daoクラス.
 * @author tosco
 */
public class ClaimEraserCsmList extends ClaimEraserCsmListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 消込完了日付(スラッシュ編集) */
	private String strEraserCompleteDate;

	/** 消込対象額(カンマ編集) */
	private String strEraserObjectAmount;

	/** 消込金額(カンマ編集) */
	private String strEraserAmount;

	/** 入金消込残合計(カンマ編集) */
	private String strCreditEraserAmount;

	/** 請求締日 */
	private String strInvoiceUpdateDate;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserCsmList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 日付にスラッシュを付与 */
		if (getEraserCompleteDate() != null) {
			setStrEraserCompleteDate(AecDateUtils.dateFormat(new Timestamp(
					getEraserCompleteDate().getTime()), "yyyy/MM/dd"));
		}

		setStrInvoiceUpdateDate(AecDateUtils.dateFormat(new Timestamp(
				getInvoiceUpdateDate().getTime()), "yyyy/MM/dd"));
	}

	/**
	 * /** {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * 消込完了日付取得.
	 * @return String 消込完了日付
	 * 
	 */
	public String getStrEraserCompleteDate() {
		return strEraserCompleteDate;
	}

	/**
	 * 消込完了日付設定.
	 * @param strEraserCompleteDate 消込完了日付
	 */
	public void setStrEraserCompleteDate(final String strEraserCompleteDate) {
		this.strEraserCompleteDate = strEraserCompleteDate;
	}

	/**
	 * 消込対象額(カンマ編集)取得.
	 * @return String 消込対象額(カンマ編集)
	 */
	public String getStrEraserObjectAmount() {
		return strEraserObjectAmount;
	}

	/**
	 * 消込対象額(カンマ編集)設定.
	 * @param strEraserObjectAmount 消込対象額(カンマ編集)
	 */
	public void setStrEraserObjectAmount(final String strEraserObjectAmount) {
		this.strEraserObjectAmount = strEraserObjectAmount;
	}

	/**
	 * 消込金額(カンマ編集)取得.
	 * @return String 消込金額(カンマ編集)
	 */
	public String getStrEraserAmount() {
		return strEraserAmount;
	}

	/**
	 * 消込金額(カンマ編集)設定.
	 * @param strEraserAmount 消込金額(カンマ編集)
	 */
	public void setStrEraserAmount(final String strEraserAmount) {
		this.strEraserAmount = strEraserAmount;
	}

	/**
	 * 入金消込残合計(カンマ編集)取得.
	 * @return String 入金消込残合計(カンマ編集)
	 */
	public String getStrCreditEraserAmount() {
		return strCreditEraserAmount;
	}

	/**
	 * 入金消込残合計(カンマ編集)設定.
	 * @param strCreditEraserAmount 入金消込残合計(カンマ編集)
	 */
	public void setStrCreditEraserAmount(final String strCreditEraserAmount) {
		this.strCreditEraserAmount = strCreditEraserAmount;
	}

	/**
	 * strInvoiceUpdateDateを取得します。
	 * @return strInvoiceUpdateDate
	 */
	public String getStrInvoiceUpdateDate() {
		return strInvoiceUpdateDate;
	}

	/**
	 * strInvoiceUpdateDateを設定します。
	 * @param strInvoiceUpdateDate strInvoiceUpdateDate
	 */
	public void setStrInvoiceUpdateDate(final String strInvoiceUpdateDate) {
		this.strInvoiceUpdateDate = strInvoiceUpdateDate;
	}
}
