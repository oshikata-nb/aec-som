/* 
 * Created on 2020/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.companysetting;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CompanySettingBaseクラス.
 * @author 
 */
public class CompanySettingBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CompanySettingBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "COMPANY_SETTING";
	
	/** COLUMNアノテーション companyCd. */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	/** COLUMNアノテーション qtyCheck. */
	public static final String qtyCheck_COLUMN = "QTY_CHECK";
	
	/** COLUMNアノテーション orderAmountCheck. */
	public static final String orderAmountCheck_COLUMN = "ORDER_AMOUNT_CHECK";
	
	/** COLUMNアノテーション autoFaxPassword. */
	public static final String autoFaxPassword_COLUMN = "AUTO_FAX_PASSWORD";
	
	/** COLUMNアノテーション autoFaxAccount. */
	public static final String autoFaxAccount_COLUMN = "AUTO_FAX_ACCOUNT";
	
	/** COLUMNアノテーション autoFaxMode. */
	public static final String autoFaxMode_COLUMN = "AUTO_FAX_MODE";
	
	/** COLUMNアノテーション autoFaxDel. */
	public static final String autoFaxDel_COLUMN = "AUTO_FAX_DEL";
	
	/** COLUMNアノテーション autoFaxCommonDomain. */
	public static final String autoFaxCommonDomain_COLUMN = "AUTO_FAX_COMMON_DOMAIN";
	
	/** COLUMNアノテーション autoFaxTextBody. */
	public static final String autoFaxTextBody_COLUMN = "AUTO_FAX_TEXT_BODY";
	
	/** COLUMNアノテーション mailSendServer. */
	public static final String mailSendServer_COLUMN = "MAIL_SEND_SERVER";
	
	/** COLUMNアノテーション mailFromAddress. */
	public static final String mailFromAddress_COLUMN = "MAIL_FROM_ADDRESS";
	
	/** COLUMNアノテーション mailSendFlg. */
	public static final String mailSendFlg_COLUMN = "MAIL_SEND_FLG";
	
	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";
	
	private String companyCd;
	
	private BigDecimal carryFareCheck;
	
	private BigDecimal qtyCheck;
	
	private BigDecimal orderAmountCheck;
	
	private String autoFaxSender;
	
	private String autoFaxPassword;
	
	private String autoFaxAccount;
	
	private String autoFaxmode;
	
	private String autoFaxDel;
	
	private String autoFaxCommonDomain;
	
	private String autoFaxTextBody;
	
	private String mailSendServer;
	
	private String mailFromAddress;
	
	private BigDecimal mailSendFlg;

	
	/**
	 * companyCdを取得します。
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * companyCdを設定します。
	 * @param companyCd companyCd
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * carryFareCheckを取得します。
	 * @return carryFareCheck
	 */
	public BigDecimal getCarryFareCheck() {
		return carryFareCheck;
	}

	/**
	 * carryFareCheckを設定します。
	 * @param carryFareCheck carryFareCheck
	 */
	public void setCarryFareCheck(BigDecimal carryFareCheck) {
		this.carryFareCheck = carryFareCheck;
	}

	/**
	 * qtyCheckを取得します。
	 * @return qtyCheck
	 */
	public BigDecimal getQtyCheck() {
		return qtyCheck;
	}

	/**
	 * qtyCheckを設定します。
	 * @param qtyCheck qtyCheck
	 */
	public void setQtyCheck(BigDecimal qtyCheck) {
		this.qtyCheck = qtyCheck;
	}

	/**
	 * orderAmountCheckを取得します。
	 * @return orderAmountCheck
	 */
	public BigDecimal getOrderAmountCheck() {
		return orderAmountCheck;
	}

	/**
	 * orderAmountCheckを設定します。
	 * @param orderAmountCheck orderAmountCheck
	 */
	public void setOrderAmountCheck(BigDecimal orderAmountCheck) {
		this.orderAmountCheck = orderAmountCheck;
	}

	/**
	 * autoFaxSenderを取得します。
	 * @return autoFaxSender
	 */
	public String getAutoFaxSender() {
		return autoFaxSender;
	}

	/**
	 * autoFaxSenderを設定します。
	 * @param autoFaxSender autoFaxSender
	 */
	public void setAutoFaxSender(String autoFaxSender) {
		this.autoFaxSender = autoFaxSender;
	}

	/**
	 * autoFaxPasswordを取得します。
	 * @return autoFaxPassword
	 */
	public String getAutoFaxPassword() {
		return autoFaxPassword;
	}

	/**
	 * autoFaxPasswordを設定します。
	 * @param autoFaxPassword autoFaxPassword
	 */
	public void setAutoFaxPassword(String autoFaxPassword) {
		this.autoFaxPassword = autoFaxPassword;
	}

	/**
	 * autoFaxAccountを取得します。
	 * @return autoFaxAccount
	 */
	public String getAutoFaxAccount() {
		return autoFaxAccount;
	}

	/**
	 * autoFaxAccountを設定します。
	 * @param autoFaxAccount autoFaxAccount
	 */
	public void setAutoFaxAccount(String autoFaxAccount) {
		this.autoFaxAccount = autoFaxAccount;
	}

	/**
	 * autoFaxmodeを取得します。
	 * @return autoFaxmode
	 */
	public String getAutoFaxmode() {
		return autoFaxmode;
	}

	/**
	 * autoFaxmodeを設定します。
	 * @param autoFaxmode autoFaxmode
	 */
	public void setAutoFaxmode(String autoFaxmode) {
		this.autoFaxmode = autoFaxmode;
	}

	/**
	 * autoFaxDelを取得します。
	 * @return autoFaxDel
	 */
	public String getAutoFaxDel() {
		return autoFaxDel;
	}

	/**
	 * autoFaxDelを設定します。
	 * @param autoFaxDel autoFaxDel
	 */
	public void setAutoFaxDel(String autoFaxDel) {
		this.autoFaxDel = autoFaxDel;
	}

	/**
	 * autoFaxCommonDomainを取得します。
	 * @return autoFaxCommonDomain
	 */
	public String getAutoFaxCommonDomain() {
		return autoFaxCommonDomain;
	}

	/**
	 * autoFaxCommonDomainを設定します。
	 * @param autoFaxCommonDomain autoFaxCommonDomain
	 */
	public void setAutoFaxCommonDomain(String autoFaxCommonDomain) {
		this.autoFaxCommonDomain = autoFaxCommonDomain;
	}

	/**
	 * autoFaxTextBodyを取得します。
	 * @return autoFaxTextBody
	 */
	public String getAutoFaxTextBody() {
		return autoFaxTextBody;
	}

	/**
	 * autoFaxTextBodyを設定します。
	 * @param autoFaxTextBody autoFaxTextBody
	 */
	public void setAutoFaxTextBody(String autoFaxTextBody) {
		this.autoFaxTextBody = autoFaxTextBody;
	}

	/**
	 * mailSendServerを取得します。
	 * @return mailSendServer
	 */
	public String getMailSendServer() {
		return mailSendServer;
	}

	/**
	 * mailSendServerを設定します。
	 * @param mailSendServer mailSendServer
	 */
	public void setMailSendServer(String mailSendServer) {
		this.mailSendServer = mailSendServer;
	}

	/**
	 * mailFromAddressを取得します。
	 * @return mailFromAddress
	 */
	public String getMailFromAddress() {
		return mailFromAddress;
	}

	/**
	 * mailFromAddressを設定します。
	 * @param mailFromAddress mailFromAddress
	 */
	public void setMailFromAddress(String mailFromAddress) {
		this.mailFromAddress = mailFromAddress;
	}

	/**
	 * mailSendFlgを取得します。
	 * @return mailSendFlg
	 */
	public BigDecimal getMailSendFlg() {
		return mailSendFlg;
	}

	/**
	 * mailSendFlgを設定します。
	 * @param mailSendFlg mailSendFlg
	 */
	public void setMailSendFlg(BigDecimal mailSendFlg) {
		this.mailSendFlg = mailSendFlg;
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
}
