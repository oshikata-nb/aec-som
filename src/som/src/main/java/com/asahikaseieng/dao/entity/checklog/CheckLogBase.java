/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Mon Apr 20 13:53:12 JST 2015
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.checklog;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CheckLogBaseクラス.
 * @author Administrator
 */
public class CheckLogBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CheckLogBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CHECK_LOG";



	/** COLUMNアノテーション moduleCd. */
	public static final String moduleCd_COLUMN = "MODULE_CD";

	/** COLUMNアノテーション client. */
	public static final String client_COLUMN = "CLIENT";

	/** COLUMNアノテーション errorDate. */
	public static final String errorDate_COLUMN = "ERROR_DATE";

	/** COLUMNアノテーション errorMes. */
	public static final String errorMes_COLUMN = "ERROR_MES";

	/** COLUMNアノテーション sqlStr. */
	public static final String sqlStr_COLUMN = "SQL_STR";

	//
	// インスタンスフィールド
	//

	private String moduleCd;

	private String client;

	private java.sql.Timestamp errorDate;

	private String errorMes;

	private String sqlStr;

	//
	// インスタンスメソッド
	//

	/**
	 * moduleCd取得.
	 * @return moduleCd
	 */
	public String getModuleCd() {
		return this.moduleCd;
	}

	/**
	 * moduleCd設定.
	 * @param moduleCd moduleCd
	 */
	public void setModuleCd(final String moduleCd) {
		this.moduleCd = moduleCd;
	}

	/**
	 * client取得.
	 * @return client
	 */
	public String getClient() {
		return this.client;
	}

	/**
	 * client設定.
	 * @param client client
	 */
	public void setClient(final String client) {
		this.client = client;
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
	 * errorMes取得.
	 * @return errorMes
	 */
	public String getErrorMes() {
		return this.errorMes;
	}

	/**
	 * errorMes設定.
	 * @param errorMes errorMes
	 */
	public void setErrorMes(final String errorMes) {
		this.errorMes = errorMes;
	}

	/**
	 * sqlStr取得.
	 * @return sqlStr
	 */
	public String getSqlStr() {
		return this.sqlStr;
	}

	/**
	 * sqlStr設定.
	 * @param sqlStr sqlStr
	 */
	public void setSqlStr(final String sqlStr) {
		this.sqlStr = sqlStr;
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
