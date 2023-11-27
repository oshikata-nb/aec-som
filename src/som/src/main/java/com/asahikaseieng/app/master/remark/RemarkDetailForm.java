/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 備考詳細 Formクラス.
 * @author t0011036
 */
public final class RemarkDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 備考コード */
	private BigDecimal remarkNo;

	/* 取引先区分 */
	private String venderDivision;

	/* 取引先コード */
	private String venderCd;

	/* 取引先名称 */
	private String venderName1;

	/* 納入先コード */
	private String deliveryCd;

	/* 納入先名称 */
	private String deliveryName1;

	/* 品目コード */
	private String itemCd;

	/* 品目名称 */
	private String itemName;

	/* 他社コード1 */
	private String otherCompanyCd1;

	/* 受注時備考 */
	private String remark01;

	/* 発注時備考 */
	private String remark12;

	/* 発注書備考 */
	private String remark13;

	/* 出荷時備考 */
	private String remark11;

	/* 出荷指図書備考 */
	private String remark06;

	/* 受注時返信時備考 */
	private String remark15;

	/* 受注時自動表示備考 */
	private String remark16;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public RemarkDetailForm() {
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
		setRemarkNo(null);
		setVenderDivision(null);
		setVenderCd(null);
		setVenderName1(null);
		setDeliveryCd(null);
		setDeliveryName1(null);
		setItemCd(null);
		setItemName(null);
		setOtherCompanyCd1(null);
		setRemark01(null);
		setRemark12(null);
		setRemark13(null);
		setRemark11(null);
		setRemark06(null);
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setRemark15(null);
		setRemark16(null);
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
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * remark01を取得します。
	 * @return remark01
	 */
	public String getRemark01() {
		return remark01;
	}

	/**
	 * remark01を設定します。
	 * @param remark01 remark01
	 */
	public void setRemark01(final String remark01) {
		this.remark01 = remark01;
	}

	/**
	 * remark06を取得します。
	 * @return remark06
	 */
	public String getRemark06() {
		return remark06;
	}

	/**
	 * remark06を設定します。
	 * @param remark06 remark06
	 */
	public void setRemark06(final String remark06) {
		this.remark06 = remark06;
	}

	/**
	 * remark11を取得します。
	 * @return remark11
	 */
	public String getRemark11() {
		return remark11;
	}

	/**
	 * remark11を設定します。
	 * @param remark11 remark11
	 */
	public void setRemark11(final String remark11) {
		this.remark11 = remark11;
	}

	/**
	 * remark12を取得します。
	 * @return remark12
	 */
	public String getRemark12() {
		return remark12;
	}

	/**
	 * remark12を設定します。
	 * @param remark12 remark12
	 */
	public void setRemark12(final String remark12) {
		this.remark12 = remark12;
	}

	/**
	 * remark13を取得します。
	 * @return remark13
	 */
	public String getRemark13() {
		return remark13;
	}

	/**
	 * remark13を設定します。
	 * @param remark13 remark13
	 */
	public void setRemark13(final String remark13) {
		this.remark13 = remark13;
	}

	/**
	 * remarkNoを取得します。
	 * @return remarkNo
	 */
	public BigDecimal getRemarkNo() {
		return remarkNo;
	}

	/**
	 * remarkNoを設定します。
	 * @param remarkNo remarkNo
	 */
	public void setRemarkNo(final BigDecimal remarkNo) {
		this.remarkNo = remarkNo;
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
	 * otherCompanyCd1を取得します。
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1を設定します。
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * remark15を取得します。
	 * @return remark15
	 */
	public String getRemark15() {
		return remark15;
	}

	/**
	 * remark15を設定します。
	 * @param remark15 remark15
	 */
	public void setRemark15(final String remark15) {
		this.remark15 = remark15;
	}

	/**
	 * remark16を取得します。
	 * @return remark16
	 */
	public String getRemark16() {
		return remark16;
	}

	/**
	 * remark16を設定します。
	 * @param remark16 remark16
	 */
	public void setRemark16(final String remark16) {
		this.remark16 = remark16;
	}
}
