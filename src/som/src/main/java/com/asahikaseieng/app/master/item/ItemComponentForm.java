/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.ComponentInformationQueueList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 品目成分 Formクラス.
 * @author t0011036
 */
public final class ItemComponentForm extends AbstractForm {

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

	/* 有効日時 */
	private Timestamp headActiveDate;

	private String strHeadActiveDate;

	/* 有効 */
	private String headActivate;

	/* ステータス名称 */
	private String headDetailStatusName;

	private String headAttributeStatusName;

	/* 成分リスト */
	private List<ComponentInformationQueueList> searchComponentList;

	/* 変更フラグ */
	private String dirtyFlg;

	/**
	 * コンストラクタ.
	 */
	public ItemComponentForm() {
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

		if ("dellist".equals(getOp())) {
			/* チェックボックスクリア処理 */
			clearCheck();
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
		setHeadVersion(new BigDecimal("0"));
		setHeadDispItemName(null);
		setHeadItemName(null);
		setHeadActiveDate(null);
		setStrHeadActiveDate(null);
		setHeadActivate(null);
		setHeadDetailStatusName(null);
		setHeadAttributeStatusName(null);
		setSearchComponentList(new ArrayList<ComponentInformationQueueList>());
		setDirtyFlg(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchComponentList() != null) {
			for (ComponentInformationQueueList bean : getSearchComponentList()) {
				bean.setChecked(Boolean.FALSE);
			}
		}
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
	 * searchComponentListを取得します。
	 * @return searchComponentList
	 */
	public List<ComponentInformationQueueList> getSearchComponentList() {
		return searchComponentList;
	}

	/**
	 * searchComponentListを設定します。
	 * @param searchComponentList searchComponentList
	 */
	public void setSearchComponentList(
			final List<ComponentInformationQueueList> searchComponentList) {
		this.searchComponentList = searchComponentList;
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

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}
}
