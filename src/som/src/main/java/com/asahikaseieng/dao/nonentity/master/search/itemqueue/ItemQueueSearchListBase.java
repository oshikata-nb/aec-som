/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.search.itemqueue;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 品目マスタキュー検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class ItemQueueSearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueSearchListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	//
	// インスタンスフィールド
	//

	/** 品目コード */
	private String itemCd;

	/** バージョン */
	private BigDecimal version;

	/** 品目名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 運用管理単位 */
	private String unitDiv;

	//
	// インスタンスメソッド
	//

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * バージョン取得.
	 * @return version バージョン
	 */
	public BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * バージョン設定.
	 * @param version バージョン
	 */
	public void setVersion(final BigDecimal version) {
		this.version = version;
	}

	/**
	 * 品目名称取得.
	 * @return itemName 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード1を取得します。
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード1を設定します。
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return String 運用管理単位
	 */
	public String getUnitDiv() {
		return unitDiv;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitDiv 運用管理単位
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
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

