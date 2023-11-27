/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.necessaryplan.purchasedecide;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 
 * 購買計画確定Form
 * @author tosco
 */
public class PurchaseDecideForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.購買計画確定Form
	 */
	public PurchaseDecideForm() {
	}

	//
	// インスタンスフィールド
	//

	/** 発注開始日 */
	private String strOrderStartDate;

	/** 発注終了日 */
	private String strOrderEndDate;

	/** 納期開始日 */
	private String strDeliverStartDate;

	/** 納期終了日 */
	private String strDeliverEndDate;

	/** 仕入先コード */
	private String venderCd;

	/** 仕入先名称 */
	private String venderName;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** DTO用：発注開始日 */
	private Date orderStartDate;

	/** DTO用：発注終了日 */
	private Date orderEndDate;

	/** DTO用：納期開始日 */
	private Date deadlineStartDate;

	/** DTO用：納期終了日 */
	private Date deadlineEndDate;

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
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
	private void clear() {

		// 発注開始日：文字列
		setStrOrderStartDate(null);
		// 発注終了日：文字列
		setStrOrderEndDate(null);
		// 納期開始日：文字列
		setStrDeliverStartDate(null);
		// 納期終了日：文字列
		setStrDeliverEndDate(null);

		// 取引先コード
		setVenderCd(null);
		// 取引先名称
		setVenderName(null);
		// 品目コード
		setItemCd(null);
		// 品目名称
		setItemName(null);

		// 発注開始日：日付型
		setOrderStartDate(null);
		// 発注終了日：日付型
		setOrderEndDate(null);
		// 納期開始日：日付型
		setDeadlineStartDate(null);
		// 納期終了日：日付型
		setDeadlineEndDate(null);

	}


	//	
	// インスタンスメソッド
	//		

	/**
	 * 発注開始日を取得します。
	 * @return strOrderStartDate
	 */
	public String getStrOrderStartDate() {
		return strOrderStartDate;
	}

	/**
	 * 発注開始日を設定します。
	 * @param strOrderStartDate 発注開始日
	 */
	public void setStrOrderStartDate(final String strOrderStartDate) {
		this.strOrderStartDate = strOrderStartDate;
	}

	/**
	 * 発注終了日を取得します。
	 * @return strOrderEndDate
	 */
	public String getStrOrderEndDate() {
		return strOrderEndDate;
	}

	/**
	 * 発注終了日を設定します。
	 * @param strOrderEndDate 発注終了日
	 */
	public void setStrOrderEndDate(final String strOrderEndDate) {
		this.strOrderEndDate = strOrderEndDate;
	}

	/**
	 * 納期開始日を取得します。
	 * @return strDeliverStartDate
	 */
	public String getStrDeliverStartDate() {
		return strDeliverStartDate;
	}

	/**
	 * 納期開始日を設定します。
	 * @param strDeliverStartDate strDeliverStartDate
	 */
	public void setStrDeliverStartDate(final String strDeliverStartDate) {
		this.strDeliverStartDate = strDeliverStartDate;
	}

	/**
	 * 納期終了日を取得します。
	 * @return strDeliverEndDate
	 */
	public String getStrDeliverEndDate() {
		return strDeliverEndDate;
	}

	/**
	 * 納期終了日を設定します。
	 * @param strDeliverEndDate 納期終了日
	 */
	public void setStrDeliverEndDate(final String strDeliverEndDate) {
		this.strDeliverEndDate = strDeliverEndDate;
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
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 仕入先名称を取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先名称を設定します。
	 * @param venderName venderName
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
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
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称を取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * DTO用：発注開始日を取得します。
	 * @return orderStartDate
	 */
	public Date getOrderStartDate() {
		return orderStartDate;
	}

	/**
	 * DTO用：発注開始日を設定します。
	 * @param orderStartDate DTO用：発注開始日
	 */
	public void setOrderStartDate(final Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	/**
	 * DTO用：発注終了日を取得します。
	 * @return orderEndDate
	 */
	public Date getOrderEndDate() {
		return orderEndDate;
	}

	/**
	 * DTO用：発注終了日を設定します。
	 * @param orderEndDate orderEndDate
	 */
	public void setOrderEndDate(final Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	/**
	 * DTO用：納期開始日を取得します。
	 * @return deadlineStartDate
	 */
	public Date getDeadlineStartDate() {
		return deadlineStartDate;
	}

	/**
	 * DTO用：納期開始日を設定します。
	 * @param deadlineStartDate deadlineStartDate
	 */
	public void setDeadlineStartDate(final Date deadlineStartDate) {
		this.deadlineStartDate = deadlineStartDate;
	}

	/**
	 * DTO用：納期終了日を取得します。
	 * @return deadlineEndDate
	 */
	public Date getDeadlineEndDate() {
		return deadlineEndDate;
	}

	/**
	 * DTO用：納期終了日を設定します。
	 * @param deadlineEndDate deadlineEndDate
	 */
	public void setDeadlineEndDate(final Date deadlineEndDate) {
		this.deadlineEndDate = deadlineEndDate;
	}

}
