/*
 * Created on 2018/02/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sendmailerrorlog;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SendMailErrorLogBaseクラス.
 * @author ssv
 */
public class SendMailErrorLogBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SendMailErrorLogBase() {
	}

	//
	// 定数
	//

    /** TABLEアノテーション. */
    public static final String TABLE = "SEND_MAIL_ERROR_LOG";


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

    /** COLUMNアノテーション logString. */
    public static final String logString_COLUMN = "LOG_STRING";

    /** COLUMNアノテーション errorDate. */
    public static final String errorDate_COLUMN = "ERROR_DATE";

    /** COLUMNアノテーション loginTantoCd. */
    public static final String loginTantoCd_COLUMN = "LOGIN_TANTO_CD";

    /** COLUMNアノテーション messageId. */
    public static final String messageId_COLUMN = "MESSAGE_ID";

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

    private String logString;

    private java.sql.Timestamp errorDate;

    private String loginTantoCd;

    private String messageId;

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
     * logString取得.
     * @return logString
     */
    public String getLogString() {
        return this.logString;
    }

    /**
     * logString設定.
     * @param logString logString
     */
    public void setLogString(final String logString) {
        this.logString = logString;
    }

    /**
     * errorDate取得.
     * @return errorDate
     */
    public java.sql.Timestamp getErrorDate() {
        return this.errorDate;
    }

    /**
     * errorDate設定.
     * @param errorDate errorDate
     */
    public void setErrorDate(final java.sql.Timestamp errorDate) {
        this.errorDate = errorDate;
    }

    /**
     * loginTantoCd取得.
     * @return loginTantoCd
     */
    public String getLoginTantoCd() {
        return this.loginTantoCd;
    }

    /**
     * loginTantoCd設定.
     * @param loginTantoCd loginTantoCd
     */
    public void setLoginTantoCd(final String loginTantoCd) {
        this.loginTantoCd = loginTantoCd;
    }

    /**
     * messageId取得.
     * @return messageId
     */
    public String getMessageId() {
        return this.messageId;
    }

    /**
     * messageId設定.
     * @param messageId messageId
     */
    public void setMessageId(final String messageId) {
        this.messageId = messageId;
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
