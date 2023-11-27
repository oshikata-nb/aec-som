/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.necessaryplan.purchaseplan;

import java.sql.Date;

import com.asahikaseieng.utils.AecTextUtils;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * PurchasePlanPagerConditionクラス.
 * @author tosco
 */
public class PurchasePlanPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//
	/** 発注開始日 */
	private Date orderStartDate;

	/** 発注終了日 */
	private Date orderEndDate;

	/** 納期開始日 */
	private Date deadlineStartDate;

	/** 納期終了日 */
	private Date deadlineEndDate;

	/** 仕入先コード */
	private String venderCd;

	/** 品目コード */
	private String itemCd;

	/** 仕入先名称 */
	private String venderName;

	/** 品目名称 */
	private String itemName;

	/**
	 * コンストラクタ.
	 */
	public PurchasePlanPagerCondition() {
	}

	//
	// 検索入力用.
	//

	/**
	 * 発注開始日を取得します。
	 * @return orderStartDate
	 */
	public Date getOrderStartDate() {
		return orderStartDate;
	}

	/**
	 * 発注開始日を設定します。
	 * @param orderStartDate 発注開始日
	 */
	public void setOrderStartDate(final Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	/**
	 * 発注終了日を取得します。
	 * @return orderEndDate
	 */
	public Date getOrderEndDate() {
		return orderEndDate;
	}

	/**
	 * 発注終了日を設定します。
	 * @param orderEndDate orderEndDate
	 */
	public void setOrderEndDate(final Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	/**
	 * 納期開始日を取得します。
	 * @return deadlineStartDate
	 */
	public Date getDeadlineStartDate() {
		return deadlineStartDate;
	}

	/**
	 * 納期開始日を設定します。
	 * @param deadlineStartDate deadlineStartDate
	 */
	public void setDeadlineStartDate(final Date deadlineStartDate) {
		this.deadlineStartDate = deadlineStartDate;
	}

	/**
	 * 納期終了日を取得します。
	 * @return deadlineEndDate
	 */
	public Date getDeadlineEndDate() {
		return deadlineEndDate;
	}

	/**
	 * 納期終了日を設定します。
	 * @param deadlineEndDate deadlineEndDate
	 */
	public void setDeadlineEndDate(final Date deadlineEndDate) {
		this.deadlineEndDate = deadlineEndDate;
	}

	/**
	 * 品目コードを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = AecTextUtils.likeFilter(itemCd);
	}

	/**
	 * 仕入先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 仕入先コードを設定します。
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = AecTextUtils.likeFilter(venderCd);
	}

	/**
	 * 品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 仕入先名称を取得します。
	 * @return 仕入先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先名称を設定します。
	 * @param venderName 仕入先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

}
