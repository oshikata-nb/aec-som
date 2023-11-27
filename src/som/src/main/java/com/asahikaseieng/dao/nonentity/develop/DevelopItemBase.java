/*
 * Created on 2007/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DevelopItemクラス.
 * @author FPC
 */
public class DevelopItemBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopItemBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ITEM_QUEUE";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション developmentRequestNo */
	public static final String developmentRequestNo_COLUMN = "DEVELOPMENT_REQUEST_NO";

//	/** COLUMNアノテーション status */
//	public static final String status_COLUMN = "STATUS";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private String developmentRequestNo;

//	private java.math.BigDecimal status;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.品目コード
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.品目コード
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * developmentRequestNo取得.開発依頼番号
	 * @return developmentRequestNo
	 */
	public String getDevelopmentRequestNo() {
		return this.developmentRequestNo;
	}

	/**
	 * developmentRequestNo設定.開発依頼番号
	 * @param developmentRequestNo developmentRequestNo
	 */
	public void setDevelopmentRequestNo(final String developmentRequestNo) {
		this.developmentRequestNo = developmentRequestNo;
	}

//	/**
//	 * status取得.ステータス
//	 * @return status
//	 */
//	public java.math.BigDecimal getStatus() {
//		return this.status;
//	}
//
//	/**
//	 * status設定.ステータス
//	 * @param status status
//	 */
//	public void setStatus(final java.math.BigDecimal status) {
//		this.status = status;
//	}

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

