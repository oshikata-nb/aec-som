/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 所属・ロール組合せ詳細 Formクラス.
 * @author t0011036
 */
public final class BelongRoleDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 部署コード */
	private String organizationCd;

	/* 部署名称 */
	private String organizationName;

	/* 役職コード */
	private String postId;

	/* 役職名称 */
	private String postName;

	/* ロールID */
	private String roleId;

	/* ロール名称 */
	private String roleName;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public BelongRoleDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setOrganizationCd(null);
		setOrganizationName(null);
		setPostId(null);
		setPostName(null);
		setRoleId(null);
		setRoleName(null);
		setDirtyFlg(null);
		setNewFlg(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * postIdを取得します。
	 * @return postId
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * postIdを設定します。
	 * @param postId postId
	 */
	public void setPostId(final String postId) {
		this.postId = postId;
	}

	/**
	 * postNameを取得します。
	 * @return postName
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * postNameを設定します。
	 * @param postName postName
	 */
	public void setPostName(final String postName) {
		this.postName = postName;
	}

	/**
	 * roleIdを取得します。
	 * @return roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * roleIdを設定します。
	 * @param roleId roleId
	 */
	public void setRoleId(final String roleId) {
		this.roleId = roleId;
	}

	/**
	 * roleNameを取得します。
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * roleNameを設定します。
	 * @param roleName roleName
	 */
	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}
}
