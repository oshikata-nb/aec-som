/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 包装実績－検索画面 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class PkgRdirectionListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionListPagerCondition() {
	}

	//
	// インスタンスフィールド
	//

	/** 指図区分 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** 指図ステータス */
	private BigDecimal directionStatus;

	/** 生産工場 */
	private String productionLine;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 包装開始実績日時(FROM) */
	private Timestamp resultSdateFrom;

	/** 包装開始実績日時(To) */
	private Timestamp resultSdateTo;

	/** 包装終了実績日時(FROM) */
	private Timestamp resultEdateFrom;

	/** 包装終了実績日時(To) */
	private Timestamp resultEdateTo;

	/** 包装開始実績日(FROM) */
	private String resultSDayFrom;

	/** 包装開始実績日(TO) */
	private String resultSDayTo;

	/** 包装開始実績時(FROM) */
	private String resultSTimeFrom;

	/** 包装開始実績時(TO) */
	private String resultSTimeTo;

	/** 包装終了実績日(FROM) */
	private String resultEDayFrom;

	/** 包装終了実績日(TO) */
	private String resultEDayTo;

	/** 包装終了実績時(FROM) */
	private String resultETimeFrom;

	/** 包装終了実績時(TO) */
	private String resultETimeTo;

	/** ロット番号 */
	private String lotNo;

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
	 * 検索入力.包装開始実績日時(FROM)取得
	 * @return BigDecimal 包装開始実績日時(FROM)
	 */
	public Timestamp getResultSdateFrom() {
		return this.resultSdateFrom;
	}

	/**
	 * 検索入力.包装開始実績日時(FROM)設定 *
	 * @param planedSdateFrom 包装開始実績日時(FROM)
	 */
	public void setResultSdateFrom(final Timestamp planedSdateFrom) {
		this.resultSdateFrom = planedSdateFrom;
	}

	/**
	 * 検索入力.包装開始実績日時(TO)取得
	 * @return BigDecimal 包装開始実績日時(TO)
	 */
	public Timestamp getResultSdateTo() {
		return this.resultSdateTo;
	}

	/**
	 * 検索入力.包装開始実績日時(TO)設定 *
	 * @param planedSdateTo 包装開始実績日時(TO)
	 */
	public void setResultSdateTo(final Timestamp planedSdateTo) {
		this.resultSdateTo = planedSdateTo;
	}

	/**
	 * 検索入力.包装終了実績日時(FROM)取得
	 * @return BigDecimal 包装終了実績日時(FROM)
	 */
	public Timestamp getResultEdateFrom() {
		return this.resultEdateFrom;
	}

	/**
	 * 検索入力.包装終了実績日時(FROM)設定 *
	 * @param planedEdateFrom 包装終了実績日時(FROM)
	 */
	public void setResultEdateFrom(final Timestamp planedEdateFrom) {
		this.resultEdateFrom = planedEdateFrom;
	}

	/**
	 * 検索入力.包装終了実績日時(TO)取得
	 * @return BigDecimal 包装終了実績日時(TO)
	 */
	public Timestamp getResultEdateTo() {
		return this.resultEdateTo;
	}

	/**
	 * 検索入力.包装終了実績日時(TO)設定 *
	 * @param planedEdateTo 包装終了実績日時(TO)
	 */
	public void setResultEdateTo(final Timestamp planedEdateTo) {
		this.resultEdateTo = planedEdateTo;
	}

	/**
	 * 包装開始実績日(FROM)取得.
	 * @return String 包装開始実績日(FROM)
	 */
	public String getResultSDayFrom() {
		return this.resultSDayFrom;
	}

	/**
	 * 包装開始実績日(FROM)設定
	 * @param planedSDayFrom 包装開始実績日(FROM)
	 */
	public void setResultSDayFrom(final String planedSDayFrom) {
		this.resultSDayFrom = planedSDayFrom;
	}

	/**
	 * 包装開始実績日(TO)取得.
	 * @return String 包装開始実績日(TO)
	 */
	public String getResultSDayTo() {
		return this.resultSDayTo;
	}

	/**
	 * 包装開始実績日(TO)設定
	 * @param planedSDayTo 包装開始実績日(TO)
	 */
	public void setResultSDayTo(final String planedSDayTo) {
		this.resultSDayTo = planedSDayTo;
	}

	/**
	 * 包装開始実績時(FROM)取得.
	 * @return String 包装開始実績時(FROM)
	 */
	public String getResultSTimeFrom() {
		return this.resultSTimeFrom;
	}

	/**
	 * 包装開始実績時(FROM)設定
	 * @param planedSTimeFrom 包装開始実績日(FROM)
	 */
	public void setResultSTimeFrom(final String planedSTimeFrom) {
		this.resultSTimeFrom = planedSTimeFrom;
	}

	/**
	 * 包装開始実績時(TO)取得.
	 * @return String 包装開始実績時(TO)
	 */
	public String getResultSTimeTo() {
		return this.resultSTimeTo;
	}

	/**
	 * 包装開始実績時(TO)設定
	 * @param planedSTimeTo 包装開始実績時(TO)
	 */
	public void setResultSTimeTo(final String planedSTimeTo) {
		this.resultSTimeTo = planedSTimeTo;
	}

	/**
	 * 包装終了実績日(FROM)取得.
	 * @return String 包装終了実績日(FROM)
	 */
	public String getResultEDayFrom() {
		return this.resultEDayFrom;
	}

	/**
	 * 包装終了実績日(FROM)設定
	 * @param planedEDayFrom 包装終了実績日(FROM)
	 */
	public void setResultEDayFrom(final String planedEDayFrom) {
		this.resultEDayFrom = planedEDayFrom;
	}

	/**
	 * 包装終了実績日(TO)取得.
	 * @return String 包装終了実績日(TO)
	 */
	public String getResultEDayTo() {
		return this.resultEDayTo;
	}

	/**
	 * 包装終了実績日(TO)設定
	 * @param planedEDayTo 包装終了実績日(TO)
	 */
	public void setResultEDayTo(final String planedEDayTo) {
		this.resultEDayTo = planedEDayTo;
	}

	/**
	 * 包装終了実績時(FROM)取得.
	 * @return String 包装終了実績時
	 */
	public String getResultETimeFrom() {
		return this.resultETimeFrom;
	}

	/**
	 * 包装終了実績時(FROM)設定
	 * @param planedETimeFrom 包装終了実績時(FROM)
	 */
	public void setResultETimeFrom(final String planedETimeFrom) {
		this.resultETimeFrom = planedETimeFrom;
	}

	/**
	 * 包装終了実績時(TO)取得.
	 * @return String ステータス
	 */
	public String getResultETimeTo() {
		return this.resultETimeTo;
	}

	/**
	 * 包装終了実績時(TO)設定
	 * @param planedETimeTo 包装終了実績時(TO)
	 */
	public void setResultETimeTo(final String planedETimeTo) {
		this.resultETimeTo = planedETimeTo;
	}

	/**
	 * ロット番号取得.
	 * @return String ロット番号
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロット番号設定
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
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
