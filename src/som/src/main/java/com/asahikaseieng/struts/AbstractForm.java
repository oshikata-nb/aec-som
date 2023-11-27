/*
 * Created on 2007/03/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * アプリケーション独自のActionFormクラス.
 * @author jbd
 */
public abstract class AbstractForm extends ValidatorForm {

	/* DispatchAction用のパラメータ */
	private String op = "";

	/* 閲覧権限 */
	private Boolean viewAuthority;

	/* 承認権限 */
	private Boolean approvalAuthority;

	/* 承認依頼権限 */
	private Boolean approvalRequestAuthority;

	/* 否認権限 */
	private Boolean negationAuthority;

	/* 承認取消権限 */
	private Boolean approvalCancelAuthority;

	/* 登録権限 */
	private Boolean updateAuthority;

	/* 削除権限 */
	private Boolean deleteAuthority;
	
	/* 確定権限 */
	private Boolean confirmAuthority;

	/* 確定取消権限 */
	private Boolean confirmCancelAuthority;	

	/**
	 * opを取得する.
	 * @return op
	 */
	public String getOp() {
		return op;
	}

	/**
	 * opを設定する.
	 * @param op String
	 */
	public void setOp(final String op) {
		this.op = op;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		/* populate前なのでgetOpは使えない。 */
		/* multipart利用時は下記コードは上手く動かないので注意!! */
		setOp(request.getParameter("op"));
		if (StringUtils.isEmpty(getOp())) {
			setOp("unspecified");
		}

	}

	/**
	 * approvalAuthorityを取得します。
	 * @return approvalAuthority
	 */
	public Boolean getApprovalAuthority() {
		return approvalAuthority;
	}

	/**
	 * approvalAuthorityを設定します。
	 * @param approvalAuthority approvalAuthority
	 */
	public void setApprovalAuthority(final Boolean approvalAuthority) {
		this.approvalAuthority = approvalAuthority;
	}

	/**
	 * approvalCancelAuthorityを取得します。
	 * @return approvalCancelAuthority
	 */
	public Boolean getApprovalCancelAuthority() {
		return approvalCancelAuthority;
	}

	/**
	 * approvalCancelAuthorityを設定します。
	 * @param approvalCancelAuthority approvalCancelAuthority
	 */
	public void setApprovalCancelAuthority(final Boolean approvalCancelAuthority) {
		this.approvalCancelAuthority = approvalCancelAuthority;
	}

	/**
	 * approvalRequestAuthorityを取得します。
	 * @return approvalRequestAuthority
	 */
	public Boolean getApprovalRequestAuthority() {
		return approvalRequestAuthority;
	}

	/**
	 * approvalRequestAuthorityを設定します。
	 * @param approvalRequestAuthority approvalRequestAuthority
	 */
	public void setApprovalRequestAuthority(
			final Boolean approvalRequestAuthority) {
		this.approvalRequestAuthority = approvalRequestAuthority;
	}

	/**
	 * deleteAuthorityを取得します。
	 * @return deleteAuthority
	 */
	public Boolean getDeleteAuthority() {
		return deleteAuthority;
	}

	/**
	 * deleteAuthorityを設定します。
	 * @param deleteAuthority deleteAuthority
	 */
	public void setDeleteAuthority(final Boolean deleteAuthority) {
		this.deleteAuthority = deleteAuthority;
	}

	/**
	 * negationAuthorityを取得します。
	 * @return negationAuthority
	 */
	public Boolean getNegationAuthority() {
		return negationAuthority;
	}

	/**
	 * negationAuthorityを設定します。
	 * @param negationAuthority negationAuthority
	 */
	public void setNegationAuthority(final Boolean negationAuthority) {
		this.negationAuthority = negationAuthority;
	}

	/**
	 * updateAuthorityを取得します。
	 * @return updateAuthority
	 */
	public Boolean getUpdateAuthority() {
		return updateAuthority;
	}

	/**
	 * updateAuthorityを設定します。
	 * @param updateAuthority updateAuthority
	 */
	public void setUpdateAuthority(final Boolean updateAuthority) {
		this.updateAuthority = updateAuthority;
	}

	/**
	 * viewAuthorityを取得します。
	 * @return viewAuthority
	 */
	public Boolean getViewAuthority() {
		return viewAuthority;
	}

	/**
	 * viewAuthorityを設定します。
	 * @param viewAuthority viewAuthority
	 */
	public void setViewAuthority(final Boolean viewAuthority) {
		this.viewAuthority = viewAuthority;
	}

	/**
	 * confirmAuthorityを取得します。
	 * @return confirmAuthority
	 */
	public Boolean getConfirmAuthority() {
		return confirmAuthority;
	}

	/**
	 * confirmAuthorityを設定します。
	 * @param confirmAuthority confirmAuthority
	 */
	public void setConfirmAuthority(final Boolean confirmAuthority) {
		this.confirmAuthority = confirmAuthority;
	}

	/**
	 * confirmCancelAuthorityを取得します。
	 * @return confirmCancelAuthority
	 */
	public Boolean getConfirmCancelAuthority() {
		return confirmCancelAuthority;
	}

	/**
	 * confirmCancelAuthorityを設定します。
	 * @param confirmCancelAuthority confirmCancelAuthority
	 */
	public void setConfirmCancelAuthority(final Boolean confirmCancelAuthority) {
		this.confirmCancelAuthority = confirmCancelAuthority;
	}
}
