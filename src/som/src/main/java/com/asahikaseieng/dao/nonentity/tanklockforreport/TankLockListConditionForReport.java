/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.tanklockforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 調合タンク底弁インターロック解除/再設定画面 複数ページ制御クラス.
 * 
 * @author tosco
 */
public class TankLockListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TankLockListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：生産ライン */
	private String srhProductionLine;

	/** 検索入力：主要製品コード */
	private String srhItemCd;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：指図ステータス */
	private String srhDirectionStatus;

	/** 検索入力：調合タンクNO */
	private String srhChogotankno;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力：生産ライン取得
	 * @return srhProductionLine
	 */
	public String getSrhProductionLine() {
		return this.srhProductionLine;
	}

	/**
	 * 検索入力：生産ライン設定 *
	 * @param srhProductionLine organizationId
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * 検索入力：主要製品コード取得
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力：主要製品コード設定 *
	 * @param srhItemCd organizationId
	 */
	public void setSrhItemCd(final String srhItemCd) {
		// LIKE検索
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力：他社コード１取得.
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		// LIKE検索
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * 検索入力：指図ステータスを取得します。
	 * @return srhDirectionStatus
	 */
	public String getSrhDirectionStatus() {
		return srhDirectionStatus;
	}

	/**
	 * 検索入力：指図ステータスを設定します。
	 * @param srhDirectionStatus 指図ステータス
	 */
	public void setSrhDirectionStatus(final String srhDirectionStatus) {
		this.srhDirectionStatus = srhDirectionStatus;
	}

	/**
	 * 検索入力：調合タンクNOを取得します。
	 * @return srhChogotankno
	 */
	public String getSrhChogotankno() {
		return srhChogotankno;
	}

	/**
	 * 検索入力：調合タンクNOを設定します。
	 * @param srhChogotankno 調合タンクNO
	 */
	public void setSrhChogotankno(final String srhChogotankno) {
		// LIKE検索
		this.srhChogotankno = AecTextUtils.likeFilter(srhChogotankno);
	}
}
