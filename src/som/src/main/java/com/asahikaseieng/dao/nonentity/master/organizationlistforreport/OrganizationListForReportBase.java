/*
 * Created on 2009/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrganizationListForReportクラス.
 * @author t0011036
 */
public class OrganizationListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrganizationListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション parentOrganizationCd */
	public static final String parentOrganizationCd_COLUMN = "PARENT_ORGANIZATION_CD";

	/** COLUMNアノテーション billDescriptionMatter */
	public static final String billDescriptionMatter_COLUMN = "BILL_DESCRIPTION_MATTER";

	/** COLUMNアノテーション zipcodeNo */
	public static final String zipcodeNo_COLUMN = "ZIPCODE_NO";

	/** COLUMNアノテーション address1 */
	public static final String address1_COLUMN = "ADDRESS1";

	/** COLUMNアノテーション address2 */
	public static final String address2_COLUMN = "ADDRESS2";

	/** COLUMNアノテーション address3 */
	public static final String address3_COLUMN = "ADDRESS3";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション faxNo */
	public static final String faxNo_COLUMN = "FAX_NO";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション parentOrganizationName */
	public static final String parentOrganizationName_COLUMN = "PARENT_ORGANIZATION_NAME";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";
	
	/** COLUMNアノテーション fromMailAddress1. */
	public static final String fromMailAddress1_COLUMN = "FROM_MAIL_ADDRESS1";
	
	/** COLUMNアノテーション fromMailAddress2. */
	public static final String fromMailAddress2_COLUMN = "FROM_MAIL_ADDRESS2";
	
	/** COLUMNアノテーション fromMailAddress3. */
	public static final String fromMailAddress3_COLUMN = "FROM_MAIL_ADDRESS3";
	
	/** COLUMNアノテーション bccSendFlg. */
	public static final String bccSendFlg_COLUMN = "BCC_SEND_FLG";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String organizationCd;

	private String organizationName;

	private String parentOrganizationCd;

	private java.math.BigDecimal billDescriptionMatter;

	private String zipcodeNo;

	private String address1;

	private String address2;

	private String address3;

	private String telNo;

	private String faxNo;

	private String tantoCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String parentOrganizationName;

	private String tantoNm;
	
	private String fromMailAddress1;
	
	private String fromMailAddress2;
	
	private String fromMailAddress3;

	private java.math.BigDecimal bccSendFlg;

	private String inputorName;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * parentOrganizationCd取得.
	 * @return parentOrganizationCd
	 */
	public String getParentOrganizationCd() {
		return this.parentOrganizationCd;
	}

	/**
	 * parentOrganizationCd設定.
	 * @param parentOrganizationCd parentOrganizationCd
	 */
	public void setParentOrganizationCd(final String parentOrganizationCd) {
		this.parentOrganizationCd = parentOrganizationCd;
	}

	/**
	 * billDescriptionMatter取得.
	 * @return billDescriptionMatter
	 */
	public java.math.BigDecimal getBillDescriptionMatter() {
		return this.billDescriptionMatter;
	}

	/**
	 * billDescriptionMatter設定.
	 * @param billDescriptionMatter billDescriptionMatter
	 */
	public void setBillDescriptionMatter(final java.math.BigDecimal billDescriptionMatter) {
		this.billDescriptionMatter = billDescriptionMatter;
	}

	/**
	 * zipcodeNo取得.
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return this.zipcodeNo;
	}

	/**
	 * zipcodeNo設定.
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * address1取得.
	 * @return address1
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * address1設定.
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2取得.
	 * @return address2
	 */
	public String getAddress2() {
		return this.address2;
	}

	/**
	 * address2設定.
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3取得.
	 * @return address3
	 */
	public String getAddress3() {
		return this.address3;
	}

	/**
	 * address3設定.
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * telNo取得.
	 * @return telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * telNo設定.
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * faxNo取得.
	 * @return faxNo
	 */
	public String getFaxNo() {
		return this.faxNo;
	}

	/**
	 * faxNo設定.
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * parentOrganizationName取得.
	 * @return parentOrganizationName
	 */
	public String getParentOrganizationName() {
		return this.parentOrganizationName;
	}

	/**
	 * parentOrganizationName設定.
	 * @param parentOrganizationName parentOrganizationName
	 */
	public void setParentOrganizationName(final String parentOrganizationName) {
		this.parentOrganizationName = parentOrganizationName;
	}

	/**
	 * tantoNm取得.
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return this.tantoNm;
	}

	/**
	 * tantoNm設定.
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
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
	public java.math.BigDecimal getBccSendFlg() {
		return bccSendFlg;
	}

	/**
	 * bccSendFlgを設定します。
	 * @param bccSendFlg bccSendFlg
	 */
	public void setBccSendFlg(final java.math.BigDecimal bccSendFlg) {
		this.bccSendFlg = bccSendFlg;
	}
}

