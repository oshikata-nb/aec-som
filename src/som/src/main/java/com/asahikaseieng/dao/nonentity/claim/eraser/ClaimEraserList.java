/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.math.BigDecimal;

import com.asahikaseieng.utils.ConstantFunction;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 消込入力用Daoクラス.
 * @author tosco
 */
public class ClaimEraserList extends ClaimEraserListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 消込日付(スラッシュ編集) */
	private String strEraserDate;

	/** 消込残合計(カンマ編集) */
	private String strEraserBalanceAmount;

	/** 消込金額(カンマ編集) */
	private String strEraserAmount;

	/** 入金消込残合計(カンマ編集) */
	private String strCreditEraserAmount;

	/** 承認ステータス */
	private String strApprovalStatus;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 日付にスラッシュを付与 */
		setStrEraserDate(AecDateUtils.dateFormat(getEraserDate(),
			"yyyy/MM/dd"));
		/* 数値にカンマを付与 */
		setStrEraserBalanceAmount(AecNumberUtils.decimalFormat(getEraserBalanceAmount(),
			"###,###.##"));
		setStrEraserAmount(AecNumberUtils.decimalFormat(getEraserAmount(),
			"###,###.##"));
		setStrCreditEraserAmount(AecNumberUtils.decimalFormat(getCreditEraserAmount(),
			"###,###.##"));
		// 承認ステータス名設定
		setStrApprovalStatus(setApprovalStatusName(getApprovalStatus()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 消込日付取得.
	 * @return String 消込日付
	 */
	public String getStrEraserDate() {
		return strEraserDate;
	}

	/**
	 * 消込日付設定.
	 * @param strEraserDate 消込日付
	 */
	public void setStrEraserDate(final String strEraserDate) {
		this.strEraserDate = strEraserDate;
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
	 * 承認ステータス取得.
	 * @return String 承認ステータス
	 */
	public String getStrApprovalStatus() {
		return strApprovalStatus;
	}

	/**
	 * 承認ステータス設定.
	 * @param strApprovalStatus 承認ステータス
	 */
	public void setStrApprovalStatus(final String strApprovalStatus) {
		this.strApprovalStatus = strApprovalStatus;
	}

	/**
	 * 
	 * 承認ステータスからステータス名称を設定する。
	 * @param approvalStatus 承認ステータス
	 * @return String ステータス名称
	 */
	private String setApprovalStatusName(final BigDecimal approvalStatus) {
		String statusName = null;
		if (approvalStatus == null) {
			return statusName;
		}

		String status = approvalStatus.toString();
		if (ConstantFunction.APPROVAL_STATUS_INPUT.equals(status)) {
			// 入力中
			statusName = ConstantFunction.APPROVAL_STATUS_INPUT_LABEL;
		} else if (ConstantFunction.APPROVAL_STATUS_REQUEST.equals(status)) {
			// 依頼中
			statusName = ConstantFunction.APPROVAL_STATUS_REQUEST_LABEL;
		} else if (ConstantFunction.APPROVAL_STATUS_APPROVAL.equals(status)) {
			// 承認済
			statusName = ConstantFunction.APPROVAL_STATUS_APPROVAL_LABEL;
		}
		return statusName;
	}
}

