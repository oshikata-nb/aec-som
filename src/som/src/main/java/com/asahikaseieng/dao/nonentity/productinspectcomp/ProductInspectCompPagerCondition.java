/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.productinspectcomp;

import java.sql.Timestamp;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 製品検査完了入力一覧複数ページ制御クラス.
 *
 * @author tosco
 */
public class ProductInspectCompPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductInspectCompPagerCondition() {
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

	/** 検索入力：開始実績日時(FROM) */
	private Timestamp srhResultSdateFrom;

	/** 検索入力：開始実績日時(To) */
	private Timestamp srhResultSdateTo;

	/** 検索入力：終了実績日時(FROM) */
	private Timestamp srhResultEdateFrom;

	/** 検索入力：終了実績日時(To) */
	private Timestamp srhResultEdateTo;

	/** 検索入力：指図ステータス */
	private String srhDirectionStatus;

	/** 検索入力：包装ライン */
	private String srhPackageLine;

	/** 検索入力：検査合格日 */
	private String srhCertificationDate;

	/** 検索入力：ロット番号 */
	private String srhLotNo;

	//
	// 検索入力
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
		//LIKE検索
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
		//LIKE検索
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * 検索入力：開始実績日時(FROM)取得
	 * @return BigDecimal 開始実績日時(FROM)
	 */
	public Timestamp getSrhResultSdateFrom() {
		return this.srhResultSdateFrom;
	}

	/**
	 * 検索入力：開始実績日時(FROM)設定
	 * * @param srhResultSdateFrom 開始実績日時(FROM)
	 */
	public void setSrhResultSdateFrom(final Timestamp srhResultSdateFrom) {
		this.srhResultSdateFrom = srhResultSdateFrom;
	}

	/**
	 * 検索入力：開始実績日時(TO)取得
	 * @return BigDecimal 開始実績日時(TO)
	 */
	public Timestamp getSrhResultSdateTo() {
		return this.srhResultSdateTo;
	}

	/**
	 * 検索入力：開始実績日時(TO)設定
	 * * @param srhResultSdateTo 開始実績日時(TO)
	 */
	public void setSrhResultSdateTo(final Timestamp srhResultSdateTo) {
		this.srhResultSdateTo = srhResultSdateTo;
	}

	/**
	 * 検索入力：終了実績日時(FROM)取得
	 * @return BigDecimal 終了実績日時(FROM)
	 */
	public Timestamp getSrhResultEdateFrom() {
		return this.srhResultEdateFrom;
	}

	/**
	 * 検索入力：終了実績日時(FROM)設定
	 * * @param srhResultEdateFrom 終了実績日時(FROM)
	 */
	public void setSrhResultEdateFrom(final Timestamp srhResultEdateFrom) {
		this.srhResultEdateFrom = srhResultEdateFrom;
	}

	/**
	 * 検索入力：終了実績日時(TO)取得
	 * @return BigDecimal 終了実績日時(TO)
	 */
	public Timestamp getSrhResultEdateTo() {
		return this.srhResultEdateTo;
	}

	/**
	 * 検索入力：終了実績日時(TO)設定
	 * * @param srhResultEdateTo 終了実績日時(TO)
	 */
	public void setSrhResultEdateTo(final Timestamp srhResultEdateTo) {
		this.srhResultEdateTo = srhResultEdateTo;
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
	 *  検索入力：包装ラインを取得します。
	 * @return srhPackageLine
	 */
	public String getSrhPackageLine() {
		return srhPackageLine;
	}

	/**
	 *  検索入力：包装ラインを設定します。
	 * @param srhPackageLine  包装ライン
	 */
	public void setSrhPackageLine(final String srhPackageLine) {
		//LIKE検索
		this.srhPackageLine = AecTextUtils.likeFilter(srhPackageLine);
	}

	/**
	 * 検索入力：検査合格日を取得します。
	 * @return srhCertificationDate
	 */
	public String getSrhCertificationDate() {
		return srhCertificationDate;
	}

	/**
	 * 検索入力：検査合格日を設定します。
	 * @param srhCertificationDate 検査合格日
	 */
	public void setSrhCertificationDate(final String srhCertificationDate) {
		this.srhCertificationDate = srhCertificationDate;
	}

	/**
	 * 検索入力：ロット番号を取得します。
	 * @return srhLotNo
	 */
	public String getSrhLotNo() {
		return srhLotNo;
	}

	/**
	 * 検索入力：ロット番号を設定します。
	 * @param srhLotNo ロット番号
	 */
	public void setSrhLotNo(final String srhLotNo) {
		//LIKE検索
		this.srhLotNo = AecTextUtils.likeFilter(srhLotNo);
	}

}
