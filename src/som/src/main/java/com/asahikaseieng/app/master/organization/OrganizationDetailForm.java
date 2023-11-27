/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 部署詳細 Formクラス.
 * @author t0011036
 */
public final class OrganizationDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 部署コード */
	private String organizationCd;

	/* 部署名称 */
	private String organizationName;

	/* 親部署コード */
	private String parentOrganizationCd;

	/* 親部署名称 */
	private String parentOrganizationName;

	/* 請求書記載事項 */
	private BigDecimal billDescriptionMatter;

	/* 郵便番号 */
	private String zipcodeNo;

	/* 住所1 */
	private String address1;

	/* 住所2 */
	private String address2;

	/* 住所3 */
	private String address3;

	/* 電話番号 */
	private String telNo;

	/* FAX番号 */
	private String faxNo;

	/* 担当者コード */
	private String tantoCd;

	/* 担当者名 */
	private String tantoNm;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;
	
	/* Fromメールアドレス1 */
	private String fromMailAddress1;
	
	/* Fromメールアドレス2 */
	private String fromMailAddress2;
	
	/* Fromメールアドレス3 */
	private String fromMailAddress3;
	
	/* Bcc送付 */
	private BigDecimal bccSendFlg;

	/**
	 * コンストラクタ.
	 */
	public OrganizationDetailForm() {
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

		if ("update".equals(getOp())) {
			/* クリア処理 */
			setBillDescriptionMatter(new BigDecimal("0"));
		}
		
		if ("update".equals(getOp())) {
			/* クリア処理 */
			setBccSendFlg(new BigDecimal("0"));
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
		setOrganizationCd(null);
		setOrganizationName(null);
		setParentOrganizationCd(null);
		setParentOrganizationName(null);
		setBillDescriptionMatter(null);
		setZipcodeNo(null);
		setAddress1(null);
		setAddress2(null);
		setAddress3(null);
		setTelNo(null);
		setFaxNo(null);
		setTantoCd(null);
		setTantoNm(null);
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setFromMailAddress1(null);
		setFromMailAddress2(null);
		setFromMailAddress3(null);
		setBccSendFlg(null);
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
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * parentOrganizationCdを取得します。
	 * @return parentOrganizationCd
	 */
	public String getParentOrganizationCd() {
		return parentOrganizationCd;
	}

	/**
	 * parentOrganizationCdを設定します。
	 * @param parentOrganizationCd parentOrganizationCd
	 */
	public void setParentOrganizationCd(final String parentOrganizationCd) {
		this.parentOrganizationCd = parentOrganizationCd;
	}

	/**
	 * parentOrganizationNameを取得します。
	 * @return parentOrganizationName
	 */
	public String getParentOrganizationName() {
		return parentOrganizationName;
	}

	/**
	 * parentOrganizationNameを設定します。
	 * @param parentOrganizationName parentOrganizationName
	 */
	public void setParentOrganizationName(final String parentOrganizationName) {
		this.parentOrganizationName = parentOrganizationName;
	}

	/**
	 * address1を取得します。
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * address1を設定します。
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2を取得します。
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * address2を設定します。
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3を取得します。
	 * @return address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * address3を設定します。
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * billDescriptionMatterを取得します。
	 * @return billDescriptionMatter
	 */
	public BigDecimal getBillDescriptionMatter() {
		return billDescriptionMatter;
	}

	/**
	 * billDescriptionMatterを設定します。
	 * @param billDescriptionMatter billDescriptionMatter
	 */
	public void setBillDescriptionMatter(final BigDecimal billDescriptionMatter) {
		this.billDescriptionMatter = billDescriptionMatter;
	}

	/**
	 * faxNoを取得します。
	 * @return faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * faxNoを設定します。
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNmを取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * tantoNmを設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * telNoを取得します。
	 * @return telNo
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * telNoを設定します。
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * zipcodeNoを取得します。
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return zipcodeNo;
	}

	/**
	 * zipcodeNoを設定します。
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * fromMailAddress1を取得します。
	 * @return fromMailAddress1
	 */
	public String getFromMailAddress1() {
		return fromMailAddress1;
	}

	/**
	 * fromMailAddress1を設定します。
	 * @param fromMailAddress1 fromMailAddress1
	 */
	public void setFromMailAddress1(final String fromMailAddress1) {
		this.fromMailAddress1 = fromMailAddress1;
	}

	/**
	 * fromMailAddress2を取得します。
	 * @return fromMailAddress2
	 */
	public String getFromMailAddress2() {
		return fromMailAddress2;
	}

	/**
	 * fromMailAddress2を設定します。
	 * @param fromMailAddress2 fromMailAddress2
	 */
	public void setFromMailAddress2(final String fromMailAddress2) {
		this.fromMailAddress2 = fromMailAddress2;
	}

	/**
	 * fromMailAddress3を取得します。
	 * @return fromMailAddress3
	 */
	public String getFromMailAddress3() {
		return fromMailAddress3;
	}

	/**
	 * fromMailAddress3を設定します。
	 * @param fromMailAddress3 fromMailAddress3
	 */
	public void setFromMailAddress3(final String fromMailAddress3) {
		this.fromMailAddress3 = fromMailAddress3;
	}

	/**
	 * bccSendFlgを取得します。
	 * @return bccSendFlg
	 */
	public BigDecimal getBccSendFlg() {
		return bccSendFlg;
	}

	/**
	 * bccSendFlgを設定します。
	 * @param bccSendFlg bccSendFlg
	 */
	public void setBccSendFlg(final BigDecimal bccSendFlg) {
		this.bccSendFlg = bccSendFlg;
	}
}
