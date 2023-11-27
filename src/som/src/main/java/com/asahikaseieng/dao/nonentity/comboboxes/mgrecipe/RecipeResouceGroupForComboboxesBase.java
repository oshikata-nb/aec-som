/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 設備グループデータ格納クラス.
 *
 * @author tosco
 */
public class RecipeResouceGroupForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupForComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_RESOUCE_GROUP";

	/** COLUMNアノテーション ResouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション resouceGroupName */
	public static final String resouceGroupName_COLUMN = "RESOUCE_GROUP_NAME";

	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updaterCd */
	public static final String updaterCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 設備グループコード */
	private String resouceGroupCd;
	/** 設備グループ名称 */
	private String resouceGroupName;
	/** 工程グループコード */
	private String operationGroupCd;
	/** 登録日付 */
	private Timestamp inputDate;
	/** 登録者 */
	private String inputorCd;
	/** 更新日付 */
	private Timestamp updateDate;
	/** 更新者 */
	private String updaterCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 設備グループ取得
	 * @return String 設備グループ
	*/
	public String getResouceGroupCd() {
		return this.resouceGroupCd;
	}

	/**
	 * 設備グループ設定
	 * @param resouceGroupCd 設備グループ
	*/
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * 設備グループ名取得
	 * @return String 設備グループ名
	*/
	public String getResouceGroupName() {
		return this.resouceGroupName;
	}

	/**
	 * 設備グループ名設定
	 * @param resouceGroupName 設備グループ名
	*/
	public void setResouceGroupName(final String resouceGroupName) {
		this.resouceGroupName = resouceGroupName;
	}

	/**
	 * 工程グループコード取得
	 * @return String 工程グループコード
	 */
	public String getOperationGroupCd() {
		return operationGroupCd;
	}

	/**
	 * 工程グループコード設定
	 * @param operationGroupCd 工程グループコード
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}

	/**
	 * 登録日付取得
	 * @return Timestamp 登録日付
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日付設定
	 * @param inputDate 登録日付
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者取得
	 * @return String 登録者
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者設定
	 * @param inputorCd 登録者
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日付取得
	 * @return Timestamp 更新日付
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日付設定
	 * @param updateDate 更新日付
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者取得
	 * @return String 更新者
	*/
	public String getUpdaterCd() {
		return this.updaterCd;
	}

	/**
	 * 更新者設定
	 * @param updaterCd 更新者
	*/
	public void setUpdaterCd(final String updaterCd) {
		this.updaterCd = updaterCd;
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
