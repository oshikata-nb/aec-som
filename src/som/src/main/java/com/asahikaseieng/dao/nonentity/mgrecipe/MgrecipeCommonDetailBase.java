/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * テンプレート保存先マスタ共通用データ格納クラス.
 *
 * @author tosco
 */
public class MgrecipeCommonDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeCommonDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "COMMON";

	/** COLUMNアノテーション commonCd */
	public static final String commonCd_COLUMN = "COMMON_CD";

	/** COLUMNアノテーション commonName */
	public static final String commonName_COLUMN = "COMMON_NAME";

	/** COLUMNアノテーション commonValue */
	public static final String commonValue_COLUMN = "COMMON_VALUE";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	/** テンプレート区分 */
	private String commonCd;

	/** テンプレート種類 */
	private String commonName;

	/** テンプレート保存先パス */
	private String commonValue;

	/** 備考 */
	private String remark;

	/** 登録者ID */
	private String inputorCd;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 更新者ID */
	private String updatorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * テンプレート区分取得
	 * @return String テンプレート区分
	*/
	public String getCommonCd() {
		return this.commonCd;
	}

	/**
	 * テンプレート区分設定
	 * @param commonCd テンプレート区分
	*/
	public void setCommonCd(final String commonCd) {
		this.commonCd = commonCd;
	}

	/**
	 * テンプレート種類を取得します。
	 * @return テンプレート種類
	 */
	public String getCommonName() {
		return commonName;
	}

	/**
	 * テンプレート種類を設定します。
	 * @param commonName テンプレート種類
	 */
	public void setCommonName(final String commonName) {
		this.commonName = commonName;
	}

	/**
	 * テンプレート保存先パスを取得します。
	 * @return commonValue
	 */
	public String getCommonValue() {
		return commonValue;
	}

	/**
	 * テンプレート保存先パスを設定します。
	 * @param commonValue テンプレート保存先パス
	 */
	public void setCommonValue(final String commonValue) {
		this.commonValue = commonValue;
	}

	/**
	 * 備考を取得します。
	 * @return remark 備考
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 備考を設定します。
	 * @param remark 備考
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	*/
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
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
