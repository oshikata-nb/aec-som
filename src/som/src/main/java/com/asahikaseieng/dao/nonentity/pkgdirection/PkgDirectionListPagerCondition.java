/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 包装指図－検索画面 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class PkgDirectionListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionListPagerCondition() {
	}

	//
	// インスタンスフィールド
	//

	/** 検索入力：指図区分 */
	private BigDecimal directionDivision;

	/** 検索入力：指図番号 */
	private String directionNo;

	/** 検索入力：指図ステータス */
	private BigDecimal directionStatus;

	/** 検索入力：生産工場 */
	private String productionLine;

	/** 検索入力：品目コード */
	private String itemCd;

	/** 検索入力：品目名称 */
	private String itemName;

	/** 検索入力：他社コード１ */
	private String otherCompanyCd1;

	/** 検索入力：包装開始予定日時(FROM) */
	private Timestamp planedSdateFrom;

	/** 検索入力：包装開始予定日時(To) */
	private Timestamp planedSdateTo;

	/** 検索入力：包装終了予定日時(FROM) */
	private Timestamp planedEdateFrom;

	/** 検索入力：包装終了予定日時(To) */
	private Timestamp planedEdateTo;

	/** 包装開始予定日(FROM) */
	private String planedSDayFrom;

	/** 包装開始予定日(TO) */
	private String planedSDayTo;

	/** 包装開始予定時(FROM) */
	private String planedSTimeFrom;

	/** 包装開始予定時(TO) */
	private String planedSTimeTo;

	/** 包装終了予定日(FROM) */
	private String planedEDayFrom;

	/** 包装終了予定日(TO) */
	private String planedEDayTo;

	/** 包装終了予定時(FROM) */
	private String planedETimeFrom;

	/** 包装終了予定時(TO) */
	private String planedETimeTo;

	/** APSオーダーコード */
	private String aspOrderNo;

	/** 包装ライン */
	private String packageLine;

	//
	// インスタンスメソッド
	//

	/**
	 * 検索入力.指図区分取得
	 * @return BigDecimal 指図区分
	 */
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 検索入力.指図区分設定 *
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 検索入力.指図番号取得
	 * @return String 指図番号
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 検索入力.指図番号設定 *
	 * @param directionNo 指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 検索入力.指図ステータス取得
	 * @return BigDecimal 指図ステータス
	 */
	public BigDecimal getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * 検索入力.指図ステータス設定 *
	 * @param directionStatus 指図ステータス
	 */
	public void setDirectionStatus(final BigDecimal directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * 検索入力.生産工場
	 * @return String 生産工場
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 検索入力.生産工場 *
	 * @param productionLine 生産工場
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 検索入力.品目コード取得
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 検索入力.品目コード設定 *
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = AecTextUtils.likeFilter(itemCd);
	}

	/**
	 * 検索入力.品目名称取得
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 検索入力.品目名称設定 *
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = AecTextUtils.likeFilter(itemName);
	}

	/**
	 * 他社コード１を取得します。
	 * @return 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = AecTextUtils.likeFilter(otherCompanyCd1);
	}

	/**
	 * 検索入力.包装開始予定日時(FROM)取得
	 * @return BigDecimal 包装開始予定日時(FROM)
	 */
	public Timestamp getPlanedSdateFrom() {
		return this.planedSdateFrom;
	}

	/**
	 * 検索入力.包装開始予定日時(FROM)設定 *
	 * @param planedSdateFrom 包装開始予定日時(FROM)
	 */
	public void setPlanedSdateFrom(final Timestamp planedSdateFrom) {
		this.planedSdateFrom = planedSdateFrom;
	}

	/**
	 * 検索入力.包装開始予定日時(TO)取得
	 * @return BigDecimal 包装開始予定日時(TO)
	 */
	public Timestamp getPlanedSdateTo() {
		return this.planedSdateTo;
	}

	/**
	 * 検索入力.包装開始予定日時(TO)設定 *
	 * @param planedSdateTo 包装開始予定日時(TO)
	 */
	public void setPlanedSdateTo(final Timestamp planedSdateTo) {
		this.planedSdateTo = planedSdateTo;
	}

	/**
	 * 検索入力.包装終了予定日時(FROM)取得
	 * @return BigDecimal 包装終了予定日時(FROM)
	 */
	public Timestamp getPlanedEdateFrom() {
		return this.planedEdateFrom;
	}

	/**
	 * 検索入力.包装終了予定日時(FROM)設定 *
	 * @param planedEdateFrom 包装終了予定日時(FROM)
	 */
	public void setPlanedEdateFrom(final Timestamp planedEdateFrom) {
		this.planedEdateFrom = planedEdateFrom;
	}

	/**
	 * 検索入力.包装終了予定日時(TO)取得
	 * @return BigDecimal 包装終了予定日時(TO)
	 */
	public Timestamp getPlanedEdateTo() {
		return this.planedEdateTo;
	}

	/**
	 * 検索入力.包装終了予定日時(TO)設定 *
	 * @param planedEdateTo 包装終了予定日時(TO)
	 */
	public void setPlanedEdateTo(final Timestamp planedEdateTo) {
		this.planedEdateTo = planedEdateTo;
	}

	/**
	 * 包装開始予定日(FROM)取得.
	 * @return String 包装開始予定日(FROM)
	 */
	public String getPlanedSDayFrom() {
		return this.planedSDayFrom;
	}

	/**
	 * 包装開始予定日(FROM)設定
	 * @param planedSDayFrom 包装開始予定日(FROM)
	 */
	public void setPlanedSDayFrom(final String planedSDayFrom) {
		this.planedSDayFrom = planedSDayFrom;
	}

	/**
	 * 包装開始予定日(TO)取得.
	 * @return String 包装開始予定日(TO)
	 */
	public String getPlanedSDayTo() {
		return this.planedSDayTo;
	}

	/**
	 * 包装開始予定日(TO)設定
	 * @param planedSDayTo 包装開始予定日(TO)
	 */
	public void setPlanedSDayTo(final String planedSDayTo) {
		this.planedSDayTo = planedSDayTo;
	}

	/**
	 * 包装開始予定時(FROM)取得.
	 * @return String 包装開始予定時(FROM)
	 */
	public String getPlanedSTimeFrom() {
		return this.planedSTimeFrom;
	}

	/**
	 * 包装開始予定時(FROM)設定
	 * @param planedSTimeFrom 包装開始予定日(FROM)
	 */
	public void setPlanedSTimeFrom(final String planedSTimeFrom) {
		this.planedSTimeFrom = planedSTimeFrom;
	}

	/**
	 * 包装開始予定時(TO)取得.
	 * @return String 包装開始予定時(TO)
	 */
	public String getPlanedSTimeTo() {
		return this.planedSTimeTo;
	}

	/**
	 * 包装開始予定時(TO)設定
	 * @param planedSTimeTo 包装開始予定時(TO)
	 */
	public void setPlanedSTimeTo(final String planedSTimeTo) {
		this.planedSTimeTo = planedSTimeTo;
	}

	/**
	 * 包装終了予定日(FROM)取得.
	 * @return String 包装終了予定日(FROM)
	 */
	public String getPlanedEDayFrom() {
		return this.planedEDayFrom;
	}

	/**
	 * 包装終了予定日(FROM)設定
	 * @param planedEDayFrom 包装終了予定日(FROM)
	 */
	public void setPlanedEDayFrom(final String planedEDayFrom) {
		this.planedEDayFrom = planedEDayFrom;
	}

	/**
	 * 包装終了予定日(TO)取得.
	 * @return String 包装終了予定日(TO)
	 */
	public String getPlanedEDayTo() {
		return this.planedEDayTo;
	}

	/**
	 * 包装終了予定日(TO)設定
	 * @param planedEDayTo 包装終了予定日(TO)
	 */
	public void setPlanedEDayTo(final String planedEDayTo) {
		this.planedEDayTo = planedEDayTo;
	}

	/**
	 * 包装終了予定時(FROM)取得.
	 * @return String 包装終了予定時
	 */
	public String getPlanedETimeFrom() {
		return this.planedETimeFrom;
	}

	/**
	 * 包装終了予定時(FROM)設定
	 * @param planedETimeFrom 包装終了予定時(FROM)
	 */
	public void setPlanedETimeFrom(final String planedETimeFrom) {
		this.planedETimeFrom = planedETimeFrom;
	}

	/**
	 * 包装終了予定時(TO)取得.
	 * @return String ステータス
	 */
	public String getPlanedETimeTo() {
		return this.planedETimeTo;
	}

	/**
	 * 包装終了予定時(TO)設定
	 * @param planedETimeTo 包装終了予定時(TO)
	 */
	public void setPlanedETimeTo(final String planedETimeTo) {
		this.planedETimeTo = planedETimeTo;
	}

	/**
	 * ASPオーダーコードを取得します。
	 * @return ASPオーダーコード
	 */
	public String getAspOrderNo() {
		return AecTextUtils.likeFilter(aspOrderNo);
	}

	/**
	 * ASPオーダーコードを設定します。
	 * @param aspOrderNo ASPオーダーコード
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}

	/**
	 * packageLineを取得します。
	 * @return packageLine
	 */
	public String getPackageLine() {
		return AecTextUtils.likeFilter(packageLine);
	}

	/**
	 * packageLineを設定します。
	 * @param packageLine packageLine
	 */
	public void setPackageLine(final String packageLine) {
		this.packageLine = packageLine;
	}
}
