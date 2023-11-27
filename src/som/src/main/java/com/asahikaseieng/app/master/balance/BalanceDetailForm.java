/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.balancedetaillist.BalanceDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 帳合詳細 Formクラス.
 * @author t0011036
 */
public final class BalanceDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 帳合コード */
	private String balanceCd;

	/* 取引先区分 */
	private String venderDivision;

	/* 取引先コード */
	private String venderCd;

	/* 取引先名称 */
	private String venderName1;

	/* 請求先コード */
	private String paymentInvoiceCd;

	/* 請求先名称 */
	private String paymentInvoiceName;

	/* 上位帳合コード */
	private String upperBalanceCd;

	/* 帳合リスト */
	private List<BalanceDetailList> searchBalanceList;

	/* 次店 */
	private BigDecimal shopLevel;

	/* 次店名称 */
	private String shopLevelName;

	/* 帳合 */
	private String balanceName;

	/* 区分 */
	private BigDecimal balanceType;

	/* 最終レベルフラグ */
	private String lastLevel;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public BalanceDetailForm() {
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
		setBalanceCd(null);
		setVenderDivision("TS"); /* 得意先 */
		setVenderCd(null);
		setVenderName1(null);
		setPaymentInvoiceCd(null);
		setPaymentInvoiceName(null);
		setUpperBalanceCd(null);
		setSearchBalanceList(new ArrayList<BalanceDetailList>());
		setShopLevel(null);
		setShopLevelName(null);
		setBalanceName(null);
		setBalanceType(null);
		setLastLevel(null);
		setUpdateDate(null);
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
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
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
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * upperBalanceCdを取得します。
	 * @return upperBalanceCd
	 */
	public String getUpperBalanceCd() {
		return upperBalanceCd;
	}

	/**
	 * upperBalanceCdを設定します。
	 * @param upperBalanceCd upperBalanceCd
	 */
	public void setUpperBalanceCd(final String upperBalanceCd) {
		this.upperBalanceCd = upperBalanceCd;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * shopLevelを取得します。
	 * @return shopLevel
	 */
	public BigDecimal getShopLevel() {
		return shopLevel;
	}

	/**
	 * shopLevelを設定します。
	 * @param shopLevel shopLevel
	 */
	public void setShopLevel(final BigDecimal shopLevel) {
		this.shopLevel = shopLevel;
	}

	/**
	 * paymentInvoiceCdを取得します。
	 * @return paymentInvoiceCd
	 */
	public String getPaymentInvoiceCd() {
		return paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceCdを設定します。
	 * @param paymentInvoiceCd paymentInvoiceCd
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceNameを取得します。
	 * @return paymentInvoiceName
	 */
	public String getPaymentInvoiceName() {
		return paymentInvoiceName;
	}

	/**
	 * paymentInvoiceNameを設定します。
	 * @param paymentInvoiceName paymentInvoiceName
	 */
	public void setPaymentInvoiceName(final String paymentInvoiceName) {
		this.paymentInvoiceName = paymentInvoiceName;
	}

	/**
	 * balanceNameを取得します。
	 * @return balanceName
	 */
	public String getBalanceName() {
		return balanceName;
	}

	/**
	 * balanceNameを設定します。
	 * @param balanceName balanceName
	 */
	public void setBalanceName(final String balanceName) {
		this.balanceName = balanceName;
	}

	/**
	 * balanceTypeを取得します。
	 * @return balanceType
	 */
	public BigDecimal getBalanceType() {
		return balanceType;
	}

	/**
	 * balanceTypeを設定します。
	 * @param balanceType balanceType
	 */
	public void setBalanceType(final BigDecimal balanceType) {
		this.balanceType = balanceType;
	}

	/**
	 * searchBalanceListを取得します。
	 * @return searchBalanceList
	 */
	public List<BalanceDetailList> getSearchBalanceList() {
		return searchBalanceList;
	}

	/**
	 * searchBalanceListを設定します。
	 * @param searchBalanceList searchBalanceList
	 */
	public void setSearchBalanceList(
			final List<BalanceDetailList> searchBalanceList) {
		this.searchBalanceList = searchBalanceList;
	}

	/**
	 * shopLevelNameを取得します。
	 * @return shopLevelName
	 */
	public String getShopLevelName() {
		return shopLevelName;
	}

	/**
	 * shopLevelNameを設定します。
	 * @param shopLevelName shopLevelName
	 */
	public void setShopLevelName(final String shopLevelName) {
		this.shopLevelName = shopLevelName;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * lastLevelを設定します。
	 * @param lastLevel lastLevel
	 */
	public void setLastLevel(final String lastLevel) {
		this.lastLevel = lastLevel;
	}

	/**
	 * lastLevelを取得します。
	 * @return lastLevel
	 */
	public String getLastLevel() {
		return lastLevel;
	}
}
