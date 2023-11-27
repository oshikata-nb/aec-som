/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * EraserCsmPagerConditionクラス.消込入力一覧(カスタマイズ)
 * @author tosco
 */
public class ClaimEraserCsmListPagerCondition extends ClaimEraserListPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserCsmListPagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：消込状態(新規消込) */
	private boolean srhStatusNew;

	/** 検索入力：消込状態(承認処理) */
	private boolean srhStatusApprove;

	/** 検索入力：消込状態(消込完了) */
	private boolean srhStatusEraser;

	/**
	 * 検索入力：消込状態(新規消込)取得.
	 * @return boolean 消込状態(新規消込)
	 */
	public boolean isSrhStatusNew() {
		return srhStatusNew;
	}

	/**
	 * 検索入力：消込状態(新規消込)設定.
	 * @param srhStatusNew 消込状態(新規消込)
	 */
	public void setSrhStatusNew(final boolean srhStatusNew) {
		this.srhStatusNew = srhStatusNew;
	}

	/**
	 * 検索入力：消込状態(承認処理)取得.
	 * @return boolean 消込状態(承認処理)
	 */
	public boolean isSrhStatusApprove() {
		return srhStatusApprove;
	}

	/**
	 * 検索入力：消込状態(承認処理)設定.
	 * @param srhStatusApprove 消込状態(承認処理)
	 */
	public void setSrhStatusApprove(final boolean srhStatusApprove) {
		this.srhStatusApprove = srhStatusApprove;
	}

	/**
	 * 検索入力：消込状態(消込完了)取得.
	 * @return boolean 消込状態(消込完了)
	 */
	public boolean isSrhStatusEraser() {
		return srhStatusEraser;
	}

	/**
	 * 検索入力：消込状態(消込完了)設定.
	 * @param srhStatusEraser 消込状態(消込完了)
	 */
	public void setSrhStatusEraser(final boolean srhStatusEraser) {
		this.srhStatusEraser = srhStatusEraser;
	}

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}
}
