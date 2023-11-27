/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 役職詳細 Formクラス.
 * @author t0011036
 */
public final class PostDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 役職コード */
	private String postId;

	/* 役職名称 */
	private String postName;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public PostDetailForm() {
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
		setPostId(null);
		setPostName(null);
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
}
