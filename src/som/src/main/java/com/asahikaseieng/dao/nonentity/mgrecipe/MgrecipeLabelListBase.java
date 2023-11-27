/*
 * Created on 2009/02/02
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
 * 帳票・ラベルマスタデータ格納クラス.
 *
 * @author tosco
 */
public class MgrecipeLabelListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeLabelListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "LABEL";

	/** COLUMNアノテーション labelCd */
	public static final String labelCd_COLUMN = "LABEL_CD";

	/** COLUMNアノテーション labelName */
	public static final String labelName_COLUMN = "LABEL_NAME";

	/** COLUMNアノテーション labelPath */
	public static final String labelPath_COLUMN = "LABEL_PATH";

	/** COLUMNアノテーション commonCd */
	public static final String commonCd_COLUMN = "COMMON_CD";

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

	/** ラベルコード */
	private String labelCd;

	/** ラベル名称 */
	private String labelName;

	/** ラベルテンプレートパス */
	private String labelPath;

	/** ラベル種類_テンプレート区分(ファイル保存先) */
	private String commonCd;

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
	 * ラベルコード取得
	 * @return String ラベルコード
	*/
	public String getLabelCd() {
		return this.labelCd;
	}

	/**
	 * ラベルコード設定
	 * @param labelCd ラベルコード
	*/
	public void setLabelCd(final String labelCd) {
		this.labelCd = labelCd;
	}

	/**
	 * ラベル名称取得
	 * @return String ラベル名称
	*/
	public String getLabelName() {
		return this.labelName;
	}

	/**
	 * ラベル名称設定
	 * @param labelName ラベル名称
	*/
	public void setLabelName(final String labelName) {
		this.labelName = labelName;
	}

	/**
	 * ラベルテンプレートパス取得
	 * @return String ラベルテンプレートパス
	*/
	public String getLabelPath() {
		return this.labelPath;
	}

	/**
	 * ラベルテンプレートパス設定
	 * @param labelPath ラベルテンプレートパス
	*/
	public void setLabelPath(final String labelPath) {
		this.labelPath = labelPath;
	}

	/**
	 * ラベル種類_テンプレート区分(ファイル保存先)取得
	 * @return String ラベル種類_テンプレート区分(ファイル保存先)
	*/
	public String getCommonCd() {
		return this.commonCd;
	}

	/**
	 * ラベル種類_テンプレート区分(ファイル保存先)設定
	 * @param commonCd ラベル種類_テンプレート区分(ファイル保存先)
	*/
	public void setCommonCd(final String commonCd) {
		this.commonCd = commonCd;
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
