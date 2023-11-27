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
 * 設備用データ格納クラス.
 *
 * @author tosco
 */
public class DirectionRecipeResouceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionRecipeResouceListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_RESOUCE";

	/** COLUMNアノテーション resouceCd */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** COLUMNアノテーション shortName */
	public static final String shortName_COLUMN = "SHORT_NAME";

	/** COLUMNアノテーション costMachine */
	public static final String costMachine_COLUMN = "COST_MACHINE";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション resouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 使用資源コード */
	private String resouceCd;

	/** 使用資源名 */
	private String resouceName;

	/** 略称 */
	private String shortName;

	/** 作業時間単価（機械） */
	private BigDecimal costMachine;

	/** 作業時間単価（一律） */
	private BigDecimal cost;

	/** 設備グループコード */
	private String resouceGroupCd;

	/** 生産ラインコード */
	private String productionLine;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 使用資源コード取得
	 * @return String 使用資源コード
	*/
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * 使用資源コード設定
	 * @param resouceCd 使用資源コード
	*/
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * 使用資源名取得
	 * @return String 使用資源名
	*/
	public String getResouceName() {
		return this.resouceName;
	}

	/**
	 * 使用資源名設定
	 * @param resouceName 使用資源名
	*/
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

	/**
	 * 略称取得
	 * @return String 略称
	*/
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * 略称設定
	 * @param shortName 略称
	*/
	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 作業時間単価（機械）取得
	 * @return BigDecimal 作業時間単価（機械）
	*/
	public BigDecimal getCostMachine() {
		return this.costMachine;
	}

	/**
	 * 作業時間単価（機械）設定
	 * @param costMachine 作業時間単価（機械）
	*/
	public void setCostMachine(final BigDecimal costMachine) {
		this.costMachine = costMachine;
	}

	/**
	 * 作業時間単価（一律）取得
	 * @return BigDecimal 作業時間単価（一律）
	*/
	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * 作業時間単価（一律）設定
	 * @param cost 作業時間単価（一律）
	*/
	public void setCost(final BigDecimal cost) {
		this.cost = cost;
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
	 * 生産ラインコード取得
	 * @return String 生産ラインコード
	*/
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ラインコード設定
	 * @param productionLine 生産ラインコード
	*/
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
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
