/*
 * Created on 2008/08/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.payment;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 
 * PaymentPagerConditionクラス.支払トランザクションテーブル検索条件＆ページャ
 * @author tosco
 */
public class AltPaymentPagerCondition extends DefaultThresholdPagerCondition {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	//検索条件
	/** ﾃﾞｰﾀ種別　4:支払（’4’固定） */
	private int dataType;
	/** 部署コード */
	private String organizationCd;
	/** 担当者コード */
	private String tantoCd;
	/** 担当者名称 */
	private String tantoNm;
	/** 支払日付FROM */
	private String paymentDateFrom;
	/** 支払日付TO */
	private String paymentDateTo;
	/** 支払先コード */
	private String supplierCd;
	/** 支払先名称 */
	private String supplierNm;
	/** 支払番号FROM */
	private String slipNoFrom;
	/** 支払番号TO */
	private String slipNoTo;
	/** 分類コード */
	private String categoryDivision;
	/** 承認ステータス(1：入力中、2：承認依頼中、3：承認済み) */
	private String approvalStatus;
	/** 伝票発行済フラグ(0：未発行、1：発行済、9：不要) */
	private String issuedDivision;

	/**
	 * コンストラクタ
	 */
	public AltPaymentPagerCondition() {
	}

	//自動生成setter,getter----------------------------------------------------
	/**
	 * 承認ステータスを取得します。
	 * @return 承認ステータス
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * 承認ステータスを設定します。
	 * @param approvalStatus 承認ステータス
	 */
	public void setApprovalStatus(final String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * 分類コードを取得します。
	 * @return 分類コード
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コードを設定します。
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 支払日付FROMを取得します。
	 * @return 支払日付FROM
	 */
	public String getPaymentDateFrom() {
		return paymentDateFrom;
	}

	/**
	 * 支払日付FROMを設定します。
	 * @param paymentDateFrom 支払日付FROM
	 */
	public void setPaymentDateFrom(final String paymentDateFrom) {
		this.paymentDateFrom = paymentDateFrom;
	}

	/**
	 * 支払日付TOを取得します。
	 * @return 支払日付TO
	 */
	public String getPaymentDateTo() {
		return paymentDateTo;
	}

	/**
	 * 支払日付TOを設定します。
	 * @param paymentDateTo 支払日付TO
	 */
	public void setPaymentDateTo(final String paymentDateTo) {
		this.paymentDateTo = paymentDateTo;
	}

	/**
	 * 支払番号FROMを取得します。
	 * @return 支払番号FROM
	 */
	public String getSlipNoFrom() {
		return slipNoFrom;
	}

	/**
	 * 支払番号FROMを設定します。
	 * @param slipNoFrom 支払番号FROM
	 */
	public void setSlipNoFrom(final String slipNoFrom) {
		this.slipNoFrom = slipNoFrom;
	}

	/**
	 * 支払番号TOを取得します。
	 * @return 支払番号TO
	 */
	public String getSlipNoTo() {
		return slipNoTo;
	}

	/**
	 * 支払番号TOを設定します。
	 * @param slipNoTo 支払番号TO
	 */
	public void setSlipNoTo(final String slipNoTo) {
		this.slipNoTo = slipNoTo;
	}

	/**
	 * 仕入先コードを取得します。
	 * @return 仕入先コード
	 */
	public String getSupplierCd() {
		return supplierCd;
	}

	/**
	 * 仕入先コードを設定します。
	 * @param supplierCd 仕入先コード
	 */
	public void setSupplierCd(final String supplierCd) {
		this.supplierCd = supplierCd;
	}

	/**
	 * ﾃﾞｰﾀ種別を取得します。
	 * @return ﾃﾞｰﾀ種別
	 */
	public int getDataType() {
		return dataType;
	}

	/**
	 * ﾃﾞｰﾀ種別を設定します。
	 * @param dataType ﾃﾞｰﾀ種別
	 */
	public void setDataType(final int dataType) {
		this.dataType = dataType;
	}

	/**
	 * 伝票発行済フラグを取得します。
	 * @return 伝票発行済フラグ
	 */
	public String getIssuedDivision() {
		return issuedDivision;
	}

	/**
	 * 伝票発行済フラグを設定します。
	 * @param issuedDivision 伝票発行済フラグ
	 */
	public void setIssuedDivision(final String issuedDivision) {
		this.issuedDivision = issuedDivision;
	}

	/**
	 * 部署コードを取得します。
	 * @return 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 担当者コードを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 担当者コードを設定します。
	 * @param tantoCd 担当者コード
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 支払先名称を取得します。
	 * @return supplierNm
	 */
	public String getSupplierNm() {
		return supplierNm;
	}

	/**
	 * 支払先名称を設定します。
	 * @param supplierNm 支払先名称
	 */
	public void setSupplierNm(final String supplierNm) {
		this.supplierNm = supplierNm;
	}

	/**
	 * 担当者名称を取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 担当者名称を設定します。
	 * @param tantoNm 担当者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

}
