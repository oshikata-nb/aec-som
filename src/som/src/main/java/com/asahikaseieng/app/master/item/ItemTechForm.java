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
import org.apache.struts.upload.FormFile;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 技術情報 Formクラス.
 * @author t0011036
 */
public final class ItemTechForm extends AbstractForm {

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

	/* リンク情報 */
	private String linkLabelCd;

	private String linkLabelPath;

	private String dispLinkLabelPath;

	private String linkCommonCd;

	private String linkCommonName;

	private String linkCommonValue;

	private FormFile linkUploadFile;

	private java.sql.Timestamp linkUpdateDate;

	/* 技術情報 */
	private String techLabelCd;

	private String techLabelPath;

	private String dispTechLabelPath;

	private String techCommonCd;

	private String techCommonName;

	private String techCommonValue;

	private FormFile techUploadFile;

	private java.sql.Timestamp techUpdateDate;

	/* ダウンロード区分 */
	private String downloadDiv;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* ダウンロードフラグ */
	private boolean downloadFlg;

	/**
	 * コンストラクタ.
	 */
	public ItemTechForm() {
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

		/* ダウンロードフラグを倒す */
		setDownloadFlg(false);
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
		setLinkLabelCd(null);
		setLinkLabelPath(null);
		setDispLinkLabelPath(null);
		setLinkCommonCd(null);
		setLinkCommonName(null);
		setLinkCommonValue(null);
		setLinkUploadFile(null);
		setLinkUpdateDate(null);
		setTechLabelCd(null);
		setTechLabelPath(null);
		setDispTechLabelPath(null);
		setTechCommonCd(null);
		setTechCommonName(null);
		setTechCommonValue(null);
		setTechUploadFile(null);
		setTechUpdateDate(null);
		setDownloadDiv(null);
		setDirtyFlg(null);
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
	 * downloadFlgを取得します。
	 * @return downloadFlg
	 */
	public boolean isDownloadFlg() {
		return downloadFlg;
	}

	/**
	 * downloadFlgを設定します。
	 * @param downloadFlg downloadFlg
	 */
	public void setDownloadFlg(final boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
	}

	/**
	 * linkUpdateDateを取得します。
	 * @return linkUpdateDate
	 */
	public java.sql.Timestamp getLinkUpdateDate() {
		return linkUpdateDate;
	}

	/**
	 * linkUpdateDateを設定します。
	 * @param linkUpdateDate linkUpdateDate
	 */
	public void setLinkUpdateDate(final java.sql.Timestamp linkUpdateDate) {
		this.linkUpdateDate = linkUpdateDate;
	}

	/**
	 * techUpdateDateを取得します。
	 * @return techUpdateDate
	 */
	public java.sql.Timestamp getTechUpdateDate() {
		return techUpdateDate;
	}

	/**
	 * techUpdateDateを設定します。
	 * @param techUpdateDate techUpdateDate
	 */
	public void setTechUpdateDate(final java.sql.Timestamp techUpdateDate) {
		this.techUpdateDate = techUpdateDate;
	}

	/**
	 * downloadDivを取得します。
	 * @return downloadDiv
	 */
	public String getDownloadDiv() {
		return downloadDiv;
	}

	/**
	 * downloadDivを設定します。
	 * @param downloadDiv downloadDiv
	 */
	public void setDownloadDiv(final String downloadDiv) {
		this.downloadDiv = downloadDiv;
	}

	/**
	 * linkCommonCdを取得します。
	 * @return linkCommonCd
	 */
	public String getLinkCommonCd() {
		return linkCommonCd;
	}

	/**
	 * linkCommonCdを設定します。
	 * @param linkCommonCd linkCommonCd
	 */
	public void setLinkCommonCd(final String linkCommonCd) {
		this.linkCommonCd = linkCommonCd;
	}

	/**
	 * linkCommonNameを取得します。
	 * @return linkCommonName
	 */
	public String getLinkCommonName() {
		return linkCommonName;
	}

	/**
	 * linkCommonNameを設定します。
	 * @param linkCommonName linkCommonName
	 */
	public void setLinkCommonName(final String linkCommonName) {
		this.linkCommonName = linkCommonName;
	}

	/**
	 * linkCommonValueを取得します。
	 * @return linkCommonValue
	 */
	public String getLinkCommonValue() {
		return linkCommonValue;
	}

	/**
	 * linkCommonValueを設定します。
	 * @param linkCommonValue linkCommonValue
	 */
	public void setLinkCommonValue(final String linkCommonValue) {
		this.linkCommonValue = linkCommonValue;
	}

	/**
	 * linkLabelCdを取得します。
	 * @return linkLabelCd
	 */
	public String getLinkLabelCd() {
		return linkLabelCd;
	}

	/**
	 * linkLabelCdを設定します。
	 * @param linkLabelCd linkLabelCd
	 */
	public void setLinkLabelCd(final String linkLabelCd) {
		this.linkLabelCd = linkLabelCd;
	}

	/**
	 * linkLabelPathを取得します。
	 * @return linkLabelPath
	 */
	public String getLinkLabelPath() {
		return linkLabelPath;
	}

	/**
	 * linkLabelPathを設定します。
	 * @param linkLabelPath linkLabelPath
	 */
	public void setLinkLabelPath(final String linkLabelPath) {
		this.linkLabelPath = linkLabelPath;
	}

	/**
	 * techCommonCdを取得します。
	 * @return techCommonCd
	 */
	public String getTechCommonCd() {
		return techCommonCd;
	}

	/**
	 * techCommonCdを設定します。
	 * @param techCommonCd techCommonCd
	 */
	public void setTechCommonCd(final String techCommonCd) {
		this.techCommonCd = techCommonCd;
	}

	/**
	 * techCommonNameを取得します。
	 * @return techCommonName
	 */
	public String getTechCommonName() {
		return techCommonName;
	}

	/**
	 * techCommonNameを設定します。
	 * @param techCommonName techCommonName
	 */
	public void setTechCommonName(final String techCommonName) {
		this.techCommonName = techCommonName;
	}

	/**
	 * techCommonValueを取得します。
	 * @return techCommonValue
	 */
	public String getTechCommonValue() {
		return techCommonValue;
	}

	/**
	 * techCommonValueを設定します。
	 * @param techCommonValue techCommonValue
	 */
	public void setTechCommonValue(final String techCommonValue) {
		this.techCommonValue = techCommonValue;
	}

	/**
	 * techLabelCdを取得します。
	 * @return techLabelCd
	 */
	public String getTechLabelCd() {
		return techLabelCd;
	}

	/**
	 * techLabelCdを設定します。
	 * @param techLabelCd techLabelCd
	 */
	public void setTechLabelCd(final String techLabelCd) {
		this.techLabelCd = techLabelCd;
	}

	/**
	 * techLabelPathを取得します。
	 * @return techLabelPath
	 */
	public String getTechLabelPath() {
		return techLabelPath;
	}

	/**
	 * techLabelPathを設定します。
	 * @param techLabelPath techLabelPath
	 */
	public void setTechLabelPath(final String techLabelPath) {
		this.techLabelPath = techLabelPath;
	}

	/**
	 * linkUploadFileを取得します。
	 * @return linkUploadFile
	 */
	public FormFile getLinkUploadFile() {
		return linkUploadFile;
	}

	/**
	 * linkUploadFileを設定します。
	 * @param linkUploadFile linkUploadFile
	 */
	public void setLinkUploadFile(final FormFile linkUploadFile) {
		this.linkUploadFile = linkUploadFile;
	}

	/**
	 * techUploadFileを取得します。
	 * @return techUploadFile
	 */
	public FormFile getTechUploadFile() {
		return techUploadFile;
	}

	/**
	 * techUploadFileを設定します。
	 * @param techUploadFile techUploadFile
	 */
	public void setTechUploadFile(final FormFile techUploadFile) {
		this.techUploadFile = techUploadFile;
	}

	/**
	 * dispLinkLabelPathを取得します。
	 * @return dispLinkLabelPath
	 */
	public String getDispLinkLabelPath() {
		return dispLinkLabelPath;
	}

	/**
	 * dispLinkLabelPathを設定します。
	 * @param dispLinkLabelPath dispLinkLabelPath
	 */
	public void setDispLinkLabelPath(final String dispLinkLabelPath) {
		this.dispLinkLabelPath = dispLinkLabelPath;
	}

	/**
	 * dispTechLabelPathを取得します。
	 * @return dispTechLabelPath
	 */
	public String getDispTechLabelPath() {
		return dispTechLabelPath;
	}

	/**
	 * dispTechLabelPathを設定します。
	 * @param dispTechLabelPath dispTechLabelPath
	 */
	public void setDispTechLabelPath(final String dispTechLabelPath) {
		this.dispTechLabelPath = dispTechLabelPath;
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
