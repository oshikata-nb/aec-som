/*
 * Created on 2020/12/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sendmaillog;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SendMailLogBaseクラス.
 * @author 
 */
public class SendMailLogBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SendMailLogBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SEND_MAIL_LOG";


	/** COLUMNアノテーション mailFormatId. */
	public static final String mailFormatId_COLUMN = "MAIL_FORMAT_ID";

	/** COLUMNアノテーション toTantoCd01. */
	public static final String toTantoCd01_COLUMN = "TO_TANTO_CD_01";
	
	/** COLUMNアノテーション toTantoCd02. */
	public static final String toTantoCd02_COLUMN = "TO_TANTO_CD_02";
	
	/** COLUMNアノテーション toTantoCd03. */
	public static final String toTantoCd03_COLUMN = "TO_TANTO_CD_03";
	
	/** COLUMNアノテーション toMailAddress01. */
	public static final String toMailAddress01_COLUMN = "TO_MAIL_ADDRESS_01";
	
	/** COLUMNアノテーション toMailAddress02. */
	public static final String toMailAddress02_COLUMN = "TO_MAIL_ADDRESS_02";
	
	/** COLUMNアノテーション toMailAddress03. */
	public static final String toMailAddress03_COLUMN = "TO_MAIL_ADDRESS_03";
	
	/** COLUMNアノテーション textTitle. */
	public static final String textTitle_COLUMN = "TEXT_TITLE";
	
	/** COLUMNアノテーション textBody. */
	public static final String textBody_COLUMN = "TEXT_BODY";
	
	/** COLUMNアノテーション sendDate. */
	public static final String sendDate_COLUMN = "SEND_DATE";
	
	/** COLUMNアノテーション loginTantoCd. */
	public static final String loginTantoCd_COLUMN = "LOGIN_TANTO_CD";
	
	//
	// インスタンスフィールド
	//
	private String mailFormatId;

	private String toTantoCd01;
	
	private String toTantoCd02;
	
	private String toTantoCd03;
	
	private String toMailAddress01;
	
	private String toMailAddress02;
	
	private String toMailAddress03;

	private String textTitle;

	private String textBody;

	private Timestamp sendDate;

	private String loginTantoCd;

	//
	// インスタンスメソッド
	//
	/**
	 * mailFormatId取得.
	 * @return mailFormatId
	 */
	public String getMailFormatId() {
		return this.mailFormatId;
	}

	/**
	 * mailFormatId設定.
	 * @param mailFormatId mailFormatId
	 */
	public void setMailFormatId(final String mailFormatId) {
		this.mailFormatId = mailFormatId;
	}

	/**
	 * toTantoCd01を取得します。
	 * @return toTantoCd01
	 */
	public String getToTantoCd01() {
		return toTantoCd01;
	}

	/**
	 * toTantoCd01を設定します。
	 * @param toTantoCd01 toTantoCd01
	 */
	public void setToTantoCd01(String toTantoCd01) {
		this.toTantoCd01 = toTantoCd01;
	}

	/**
	 * toTantoCd02を取得します。
	 * @return toTantoCd02
	 */
	public String getToTantoCd02() {
		return toTantoCd02;
	}

	/**
	 * toTantoCd02を設定します。
	 * @param toTantoCd02 toTantoCd02
	 */
	public void setToTantoCd02(String toTantoCd02) {
		this.toTantoCd02 = toTantoCd02;
	}

	/**
	 * toTantoCd03を取得します。
	 * @return toTantoCd03
	 */
	public String getToTantoCd03() {
		return toTantoCd03;
	}

	/**
	 * toTantoCd03を設定します。
	 * @param toTantoCd03 toTantoCd03
	 */
	public void setToTantoCd03(String toTantoCd03) {
		this.toTantoCd03 = toTantoCd03;
	}

	/**
	 * toMailAddress01を取得します。
	 * @return toMailAddress01
	 */
	public String getToMailAddress01() {
		return toMailAddress01;
	}

	/**
	 * toMailAddress01を設定します。
	 * @param toMailAddress01 toMailAddress01
	 */
	public void setToMailAddress01(String toMailAddress01) {
		this.toMailAddress01 = toMailAddress01;
	}

	/**
	 * toMailAddress02を取得します。
	 * @return toMailAddress02
	 */
	public String getToMailAddress02() {
		return toMailAddress02;
	}

	/**
	 * toMailAddress02を設定します。
	 * @param toMailAddress02 toMailAddress02
	 */
	public void setToMailAddress02(String toMailAddress02) {
		this.toMailAddress02 = toMailAddress02;
	}

	/**
	 * toMailAddress03を取得します。
	 * @return toMailAddress03
	 */
	public String getToMailAddress03() {
		return toMailAddress03;
	}

	/**
	 * toMailAddress03を設定します。
	 * @param toMailAddress03 toMailAddress03
	 */
	public void setToMailAddress03(String toMailAddress03) {
		this.toMailAddress03 = toMailAddress03;
	}

	/**
	 * textTitleを取得します。
	 * @return textTitle
	 */
	public String getTextTitle() {
		return textTitle;
	}

	/**
	 * textTitleを設定します。
	 * @param textTitle textTitle
	 */
	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	/**
	 * textBodyを取得します。
	 * @return textBody
	 */
	public String getTextBody() {
		return textBody;
	}

	/**
	 * textBodyを設定します。
	 * @param textBody textBody
	 */
	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	/**
	 * sendDateを取得します。
	 * @return sendDate
	 */
	public Timestamp getSendDate() {
		return sendDate;
	}

	/**
	 * sendDateを設定します。
	 * @param sendDate sendDate
	 */
	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * loginTantoCdを取得します。
	 * @return loginTantoCd
	 */
	public String getLoginTantoCd() {
		return loginTantoCd;
	}

	/**
	 * loginTantoCdを設定します。
	 * @param loginTantoCd loginTantoCd
	 */
	public void setLoginTantoCd(String loginTantoCd) {
		this.loginTantoCd = loginTantoCd;
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
