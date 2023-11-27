/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.struts.AbstractForm;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * 組織マスタ詳細 Formクラス
 * @author tosco
 */
public final class TopicDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;


	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド
	//

	/** トピックID */
	private String topicId;

	/** 見出し */
	private String topicTitle;

	/** 内容 */
	private String topicContent;

	/** 返答 */
	private String topicContentRet;

	/** 入力者 */
	private String topicInputor;

	/** 返答入力者 */
	private String topicRetInputor;

	//新規用切替フラグ
	private int insertFlg;

	/**
	 * コンストラクタ.ロケーションマスタ詳細
	 */
	public TopicDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
		if ("initNew".equals(getOp())) {
			clear();
			setInsertFlg(1);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		/** トピックID */
		setTopicId(null);

		/** 見出し */
		setTopicTitle(null);

		/** 内容 */
		setTopicContent(null);

		/** 返答 */
		setTopicContentRet(null);

		//新規更新切替フラグ
		setInsertFlg(0);
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * トピックID取得.
	 * @return topicId
	 */
	public String getTopicId() {
		return this.topicId;
	}

	/**
	 * トピックID設定.
	 * @param topicId topicId
	 */
	public void setTopicId(final String topicId) {
		this.topicId = topicId;
	}

	/**
	 * トピック見出し取得.
	 * @return topicTitle
	 */
	public String getTopicTitle() {
		return this.topicTitle;
	}

	/**
	 * トピック見出し設定.
	 * @param topicTitle topicTitle
	 */
	public void setTopicTitle(final String topicTitle) {
		this.topicTitle = topicTitle;
	}

	/**
	 * トピック内容取得.
	 * @return topicContent
	 */
	public String getTopicContent() {
		return this.topicContent;
	}

	/**
	 * トピック内容設定.
	 * @param topicContent topicContent
	 */
	public void setTopicContent(final String topicContent) {
		this.topicContent = topicContent;
	}

	/**
	 * topicContentRet取得.
	 * @return topicContentRet
	 */
	public String getTopicContentRet() {
		return this.topicContentRet;
	}

	/**
	 * topicContentRet設定.
	 * @param topicContentRet topicContentRet
	 */
	public void setTopicContentRet(final String topicContentRet) {
		this.topicContentRet = topicContentRet;
	}

	/**
	 * topicInputor取得.
	 * @return topicInputor
	 */
	public String getTopicInputor() {
		return this.topicInputor;
	}

	/**
	 * topicInputor設定.
	 * @param topicInputor topicInputor
	 */
	public void setTopicInputor(final String topicInputor) {
		this.topicInputor = topicInputor;
	}

	/**
	 * topicRetInputor取得.
	 * @return topicRetInputor
	 */
	public String getTopicRetInputor() {
		return this.topicRetInputor;
	}

	/**
	 * topicRetInputor設定.
	 * @param topicRetInputor topicRetInputor
	 */
	public void setTopicRetInputor(final String topicRetInputor) {
		this.topicRetInputor = topicRetInputor;
	}


	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * insertFlgを取得します。
	 * @return insertFlg
	 */
	public int getInsertFlg() {
		return insertFlg;
	}
	/**
	 * insertFlgを設定します。
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

}
