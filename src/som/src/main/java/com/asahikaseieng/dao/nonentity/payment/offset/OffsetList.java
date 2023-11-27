/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import java.math.BigDecimal;

import com.asahikaseieng.utils.ConstantFunction;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * グループ間相殺検索画面用Daoクラス.
 * @author tosco
 */
public class OffsetList extends OffsetListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	// 相殺金額
	private String strOffsetAmount;
	// 承認フラグ
	private String strApprovalStatus;

	/**
	 * コンストラクタ.
	 */
	public OffsetList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrOffsetAmount(AecNumberUtils.decimalFormat(getOffsetAmount(),
		// "###,###,###"));
		/* 状態の設定 */
		setStrApprovalStatus(setApprovalStatusName(getApprovalStatus()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 相殺金額を取得します。
	 * @return strOffsetAmount
	 */
	public String getStrOffsetAmount() {
		return strOffsetAmount;
	}

	/**
	 * 相殺金額を設定します。
	 * @param strOffsetAmount 相殺金額
	 */
	public void setStrOffsetAmount(final String strOffsetAmount) {
		this.strOffsetAmount = strOffsetAmount;
	}

	/**
	 * 承認フラグを取得します。
	 * @return strApprovalStatus
	 */
	public String getStrApprovalStatus() {
		return strApprovalStatus;
	}

	/**
	 * 承認フラグを設定します。
	 * @param strApprovalStatus 承認フラグ
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
