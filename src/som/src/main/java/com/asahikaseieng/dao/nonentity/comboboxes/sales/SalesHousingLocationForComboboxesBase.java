/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.sales;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売上－入庫ロケーションコンボボックス用データ
 *
 * @author tosco
 */
public class SalesHousingLocationForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesHousingLocationForComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "LOCATION";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	//
	// インスタンスフィールド
	//

	/** ロケーションコード */
	private String locationCd;

	/** ロケーション名称 */
	private String locationName;

	//
	// インスタンスメソッド
	//

	/**
	 * ロケーションコード取得
	 * @return String ロケーションコード
	*/
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * ロケーションコード設定
	 * @param locationCd ロケーションコード
	*/
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * ロケーション名称取得
	 * @return String ロケーション名称
	*/
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * ロケーション名称設定
	 * @param locationName ロケーション名称
	*/
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
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
