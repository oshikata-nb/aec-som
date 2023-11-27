/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.midinspectcomp;

import java.sql.Timestamp;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 中間品検査完了入力画面　複数ページ制御クラス.
 *
 * @author tosco
 */
public class MidInspectCompPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MidInspectCompPagerCondition() {
	}

	//
	// 検索入力用
	//
	/** 検索入力：指図番号 */
	private String srhDirectionNo;

	/** 検索入力：生産ライン */
	private String srhProductionLine;

	/** 検索入力：主要製品コード */
	private String srhItemCd;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：製造開始予定日時(FROM) */
	private Timestamp srhPlanedSdateFrom;

	/** 検索入力：製造開始予定日時(To) */
	private Timestamp srhPlanedSdateTo;

	/** 検索入力：製造終了予定日時(FROM) */
	private Timestamp srhPlanedEdateFrom;

	/** 検索入力：製造終了予定日時(To) */
	private Timestamp srhPlanedEdateTo;

	/** 検索入力：指図ステータス */
	private String srhDirectionStatus;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力：指図番号取得
	 * @return srhDirectionNo
	 */
	public String getSrhDirectionNo() {
		return this.srhDirectionNo;
	}

	/**
	 * 検索入力：指図番号設定
	 * * @param srhDirectionNo organizationId
	 */
	public void setSrhDirectionNo(final String srhDirectionNo) {
		this.srhDirectionNo = AecTextUtils.likeFilter(srhDirectionNo);
	}

	/**
	 * 検索入力：生産ライン取得
	 * @return srhProductionLine
	 */
	public String getSrhProductionLine() {
		return this.srhProductionLine;
	}

	/**
	 * 検索入力：生産ライン設定
	 * * @param srhProductionLine organizationId
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
	 * 検索入力：主要製品コード設定
	 * * @param srhItemCd organizationId
	 */
	public void setSrhItemCd(final String srhItemCd) {
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
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * 検索入力：製造開始予定日時(FROM)取得
	 * @return BigDecimal 製造開始予定日時(FROM)
	 */
	public Timestamp getSrhPlanedSdateFrom() {
		return this.srhPlanedSdateFrom;
	}

	/**
	 * 検索入力：製造開始予定日時(FROM)設定
	 * * @param srhPlanedSdateFrom 製造開始予定日時(FROM)
	 */
	public void setSrhPlanedSdateFrom(final Timestamp srhPlanedSdateFrom) {
		this.srhPlanedSdateFrom = srhPlanedSdateFrom;
	}

	/**
	 * 検索入力：製造開始予定日時(TO)取得
	 * @return BigDecimal 製造開始予定日時(TO)
	 */
	public Timestamp getSrhPlanedSdateTo() {
		return this.srhPlanedSdateTo;
	}

	/**
	 * 検索入力：製造開始予定日時(TO)設定
	 * * @param srhPlanedSdateTo 製造開始予定日時(TO)
	 */
	public void setSrhPlanedSdateTo(final Timestamp srhPlanedSdateTo) {
		this.srhPlanedSdateTo = srhPlanedSdateTo;
	}

	/**
	 * 検索入力：製造終了予定日時(FROM)取得
	 * @return BigDecimal 製造終了予定日時(FROM)
	 */
	public Timestamp getSrhPlanedEdateFrom() {
		return this.srhPlanedEdateFrom;
	}

	/**
	 * 検索入力：製造終了予定日時(FROM)設定
	 * * @param srhPlanedEdateFrom 製造終了予定日時(FROM)
	 */
	public void setSrhPlanedEdateFrom(final Timestamp srhPlanedEdateFrom) {
		this.srhPlanedEdateFrom = srhPlanedEdateFrom;
	}

	/**
	 * 検索入力：製造終了予定日時(TO)取得
	 * @return BigDecimal 製造終了予定日時(TO)
	 */
	public Timestamp getSrhPlanedEdateTo() {
		return this.srhPlanedEdateTo;
	}

	/**
	 * 検索入力：製造終了予定日時(TO)設定
	 * * @param srhPlanedEdateTo 製造終了予定日時(TO)
	 */
	public void setSrhPlanedEdateTo(final Timestamp srhPlanedEdateTo) {
		this.srhPlanedEdateTo = srhPlanedEdateTo;
	}

	/**
	 * 指図ステータスを取得します。
	 * @return srhDirectionStatus
	 */
	public String getSrhDirectionStatus() {
		return srhDirectionStatus;
	}

	/**
	 * 指図ステータスを設定します。
	 * @param srhDirectionStatus 指図ステータス
	 */
	public void setSrhDirectionStatus(final String srhDirectionStatus) {
		this.srhDirectionStatus = srhDirectionStatus;
	}
}
