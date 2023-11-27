/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * その他 Formクラス.
 * @author t0011036
 */
public final class ItemOtherForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 品目コード */
	private String headItemCd;

	private String itemCd;

	/* バージョン */
	private BigDecimal headVersion;

	private BigDecimal version;

	/* 品目名称 */
	private String headDispItemName;

	private String headItemName;

	private String itemName;

	/* 有効日時 */
	private Timestamp headActiveDate;

	private String strHeadActiveDate;

	private Timestamp activeDate;

	private String strActiveDate;

	/* ステータス */
	private BigDecimal status;

	/* 有効 */
	private String headActivate;

	/* ステータス名称 */
	private String headDetailStatusName;

	private String headAttributeStatusName;

	/* 備考 */
	private String remark;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/**
	 * コンストラクタ.
	 */
	public ItemOtherForm() {
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
		setHeadItemCd(null);
		setItemCd(null);
		setHeadVersion(new BigDecimal("0"));
		setVersion(new BigDecimal("0"));
		setHeadDispItemName(null);
		setHeadItemName(null);
		setItemName(null);
		setHeadActiveDate(null);
		setStrHeadActiveDate(null);
		setActiveDate(null);
		setStrActiveDate(null);
		setStatus(new BigDecimal("0"));
		setHeadActivate(null);
		setHeadDetailStatusName(null);
		setHeadAttributeStatusName(null);
		setRemark(null);
		setUpdateDate(null);
		setDirtyFlg(null);
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
	 * activeDateを取得します。
	 * @return activeDate
	 */
	public Timestamp getActiveDate() {
		return activeDate;
	}

	/**
	 * activeDateを設定します。
	 * @param activeDate activeDate
	 */
	public void setActiveDate(final Timestamp activeDate) {
		this.activeDate = activeDate;
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
	 * strActiveDateを取得します。
	 * @return strActiveDate
	 */
	public String getStrActiveDate() {
		return strActiveDate;
	}

	/**
	 * strActiveDateを設定します。
	 * @param strActiveDate strActiveDate
	 */
	public void setStrActiveDate(final String strActiveDate) {
		this.strActiveDate = strActiveDate;
	}

	/**
	 * versionを取得します。
	 * @return version
	 */
	public BigDecimal getVersion() {
		return version;
	}

	/**
	 * versionを設定します。
	 * @param version version
	 */
	public void setVersion(final BigDecimal version) {
		this.version = version;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public BigDecimal getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(final BigDecimal status) {
		this.status = status;
	}

	/**
	 * headActivateを取得します。
	 * @return headActivate
	 */
	public String getHeadActivate() {
		return headActivate;
	}

	/**
	 * headActivateを設定します。
	 * @param headActivate headActivate
	 */
	public void setHeadActivate(final String headActivate) {
		this.headActivate = headActivate;
	}

	/**
	 * headActiveDateを取得します。
	 * @return headActiveDate
	 */
	public Timestamp getHeadActiveDate() {
		return headActiveDate;
	}

	/**
	 * headActiveDateを設定します。
	 * @param headActiveDate headActiveDate
	 */
	public void setHeadActiveDate(final Timestamp headActiveDate) {
		this.headActiveDate = headActiveDate;
	}

	/**
	 * headAttributeStatusNameを取得します。
	 * @return headAttributeStatusName
	 */
	public String getHeadAttributeStatusName() {
		return headAttributeStatusName;
	}

	/**
	 * headAttributeStatusNameを設定します。
	 * @param headAttributeStatusName headAttributeStatusName
	 */
	public void setHeadAttributeStatusName(final String headAttributeStatusName) {
		this.headAttributeStatusName = headAttributeStatusName;
	}

	/**
	 * headDetailStatusNameを取得します。
	 * @return headDetailStatusName
	 */
	public String getHeadDetailStatusName() {
		return headDetailStatusName;
	}

	/**
	 * headDetailStatusNameを設定します。
	 * @param headDetailStatusName headDetailStatusName
	 */
	public void setHeadDetailStatusName(final String headDetailStatusName) {
		this.headDetailStatusName = headDetailStatusName;
	}

	/**
	 * headItemCdを取得します。
	 * @return headItemCd
	 */
	public String getHeadItemCd() {
		return headItemCd;
	}

	/**
	 * headItemCdを設定します。
	 * @param headItemCd headItemCd
	 */
	public void setHeadItemCd(final String headItemCd) {
		this.headItemCd = headItemCd;
	}

	/**
	 * headVersionを取得します。
	 * @return headVersion
	 */
	public BigDecimal getHeadVersion() {
		return headVersion;
	}

	/**
	 * headVersionを設定します。
	 * @param headVersion headVersion
	 */
	public void setHeadVersion(final BigDecimal headVersion) {
		this.headVersion = headVersion;
	}

	/**
	 * strHeadActiveDateを取得します。
	 * @return strHeadActiveDate
	 */
	public String getStrHeadActiveDate() {
		return strHeadActiveDate;
	}

	/**
	 * strHeadActiveDateを設定します。
	 * @param strHeadActiveDate strHeadActiveDate
	 */
	public void setStrHeadActiveDate(final String strHeadActiveDate) {
		this.strHeadActiveDate = strHeadActiveDate;
	}

	/**
	 * headDispItemNameを取得します。
	 * @return headDispItemName
	 */
	public String getHeadDispItemName() {
		return headDispItemName;
	}

	/**
	 * headDispItemNameを設定します。
	 * @param headDispItemName headDispItemName
	 */
	public void setHeadDispItemName(final String headDispItemName) {
		this.headDispItemName = headDispItemName;
	}

	/**
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * headItemNameを取得します。
	 * @return headItemName
	 */
	public String getHeadItemName() {
		return headItemName;
	}

	/**
	 * headItemNameを設定します。
	 * @param headItemName headItemName
	 */
	public void setHeadItemName(final String headItemName) {
		this.headItemName = headItemName;
	}
}
