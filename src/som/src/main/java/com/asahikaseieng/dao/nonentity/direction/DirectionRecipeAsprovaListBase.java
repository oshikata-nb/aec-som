/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 処方ASPROVA_データ格納クラス.
 *
 * @author tosco
 */
public class DirectionRecipeAsprovaListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionRecipeAsprovaListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_ASPROVA";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション resouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** COLUMNアノテーション resouceCd */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション youinsu */
	public static final String youinsu_COLUMN = "YOUINSU";

	/** COLUMNアノテーション maejikan */
	public static final String maejikan_COLUMN = "MAEJIKAN";

	/** COLUMNアノテーション atojikan */
	public static final String atojikan_COLUMN = "ATOJIKAN";

	/** COLUMNアノテーション processWorkTime1 */
	public static final String processWorkTime1_COLUMN = "PROCESS_WORK_TIME1";

	/** COLUMNアノテーション processWorkTime2 */
	public static final String processWorkTime2_COLUMN = "PROCESS_WORK_TIME2";

	/** COLUMNアノテーション machineWorkTime1 */
	public static final String machineWorkTime1_COLUMN = "MACHINE_WORK_TIME1";

	/** COLUMNアノテーション machineWorkTime2 */
	public static final String machineWorkTime2_COLUMN = "MACHINE_WORK_TIME2";

	/** COLUMNアノテーション manWorkTime1 */
	public static final String manWorkTime1_COLUMN = "MAN_WORK_TIME1";

	/** COLUMNアノテーション manWorkTime2 */
	public static final String manWorkTime2_COLUMN = "MAN_WORK_TIME2";

	/** COLUMNアノテーション recipePriority */
	public static final String recipePriority_COLUMN = "RECIPE_PRIORITY";

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

	/** RECIPE_ID|レシピインデックス */
	private BigDecimal recipeId;

	/** 設備グループコード */
	private String resouceGroupCd;

	/** 工程グループコード */
	private String operationGroupCd;

	/** 設備コード */
	private String resouceCd;

	/** 要員数 */
	private BigDecimal youinsu;

	/** 前段取時間（分） */
	private BigDecimal maejikan;

	/** 後段取時間（分） */
	private BigDecimal atojikan;

	/** 工程作業時間1（分） */
	private BigDecimal processWorkTime1;

	/** 工程作業時間2（分） */
	private BigDecimal processWorkTime2;

	/** 設備稼働時間1（分） */
	private BigDecimal machineWorkTime1;

	/** 設備稼働時間2（分） */
	private BigDecimal machineWorkTime2;

	/** 人作業時間1（分） */
	private BigDecimal manWorkTime1;

	/** 人作業時間2（分） */
	private BigDecimal manWorkTime2;

	/** 優先度 */
	private BigDecimal recipePriority;

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
	 * RECIPE_ID|レシピインデックス取得
	 * @return BigDecimal RECIPE_ID|レシピインデックス
	*/
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * RECIPE_ID|レシピインデックス設定
	 * @param recipeId RECIPE_ID|レシピインデックス
	*/
	public void setRecipeId(final BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * 設備グループコード取得
	 * @return String 設備グループコード
	*/
	public String getResouceGroupCd() {
		return this.resouceGroupCd;
	}

	/**
	 * 設備グループコード設定
	 * @param resouceGroupCd 設備グループコード
	*/
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
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
	 * 設備コード取得
	 * @return String 設備コード
	*/
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * 設備コード設定
	 * @param resouceCd 設備コード
	*/
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * 要員数取得
	 * @return BigDecimal 要員数
	*/
	public BigDecimal getYouinsu() {
		return this.youinsu;
	}

	/**
	 * 要員数設定
	 * @param youinsu 要員数
	*/
	public void setYouinsu(final BigDecimal youinsu) {
		this.youinsu = youinsu;
	}

	/**
	 * 前段取時間（分）取得
	 * @return BigDecimal 前段取時間（分）
	*/
	public BigDecimal getMaejikan() {
		return this.maejikan;
	}

	/**
	 * 前段取時間（分）設定
	 * @param maejikan 前段取時間（分）
	*/
	public void setMaejikan(final BigDecimal maejikan) {
		this.maejikan = maejikan;
	}

	/**
	 * 後段取時間（分）取得
	 * @return BigDecimal 後段取時間（分）
	*/
	public BigDecimal getAtojikan() {
		return this.atojikan;
	}

	/**
	 * 後段取時間（分）設定
	 * @param atojikan 後段取時間（分）
	*/
	public void setAtojikan(final BigDecimal atojikan) {
		this.atojikan = atojikan;
	}

	/**
	 * 工程作業時間1（分）取得
	 * @return BigDecimal 工程作業時間1（分）
	*/
	public BigDecimal getProcessWorkTime1() {
		return this.processWorkTime1;
	}

	/**
	 * 工程作業時間1（分）設定
	 * @param processWorkTime1 工程作業時間1（分）
	*/
	public void setProcessWorkTime1(final BigDecimal processWorkTime1) {
		this.processWorkTime1 = processWorkTime1;
	}

	/**
	 * 工程作業時間2（分）取得
	 * @return BigDecimal 工程作業時間2（分）
	*/
	public BigDecimal getProcessWorkTime2() {
		return this.processWorkTime2;
	}

	/**
	 * 工程作業時間2（分）設定
	 * @param processWorkTime2 工程作業時間2（分）
	*/
	public void setProcessWorkTime2(final BigDecimal processWorkTime2) {
		this.processWorkTime2 = processWorkTime2;
	}

	/**
	 * 設備稼働時間1（分）取得
	 * @return BigDecimal 設備稼働時間1（分）
	*/
	public BigDecimal getMachineWorkTime1() {
		return this.machineWorkTime1;
	}

	/**
	 * 設備稼働時間1（分）設定
	 * @param machineWorkTime1 設備稼働時間1（分）
	*/
	public void setMachineWorkTime1(final BigDecimal machineWorkTime1) {
		this.machineWorkTime1 = machineWorkTime1;
	}

	/**
	 * 設備稼働時間2（分）取得
	 * @return BigDecimal 設備稼働時間2（分）
	*/
	public BigDecimal getMachineWorkTime2() {
		return this.machineWorkTime2;
	}

	/**
	 * 設備稼働時間2（分）設定
	 * @param machineWorkTime2 設備稼働時間2（分）
	*/
	public void setMachineWorkTime2(final BigDecimal machineWorkTime2) {
		this.machineWorkTime2 = machineWorkTime2;
	}

	/**
	 * 人作業時間1（分）取得
	 * @return BigDecimal 人作業時間1（分）
	*/
	public BigDecimal getManWorkTime1() {
		return this.manWorkTime1;
	}

	/**
	 * 人作業時間1（分）設定
	 * @param manWorkTime1 人作業時間1（分）
	*/
	public void setManWorkTime1(final BigDecimal manWorkTime1) {
		this.manWorkTime1 = manWorkTime1;
	}

	/**
	 * 人作業時間2（分）取得
	 * @return BigDecimal 人作業時間2（分）
	*/
	public BigDecimal getManWorkTime2() {
		return this.manWorkTime2;
	}

	/**
	 * 人作業時間2（分）設定
	 * @param manWorkTime2 人作業時間2（分）
	*/
	public void setManWorkTime2(final BigDecimal manWorkTime2) {
		this.manWorkTime2 = manWorkTime2;
	}

	/**
	 * 優先度取得
	 * @return BigDecimal 優先度
	*/
	public BigDecimal getRecipePriority() {
		return this.recipePriority;
	}

	/**
	 * 優先度設定
	 * @param recipePriority 優先度
	*/
	public void setRecipePriority(final BigDecimal recipePriority) {
		this.recipePriority = recipePriority;
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
