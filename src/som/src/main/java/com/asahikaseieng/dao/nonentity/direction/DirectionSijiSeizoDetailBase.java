/*
 * Created 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.direction;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 製造指示クラス.
 * @author tosco
 */
public class DirectionSijiSeizoDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionSijiSeizoDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SIJI_SEIZO";

	/** COLUMNアノテーション seizosashizuno. */
	public static final String seizosashizuno_COLUMN = "SEIZOSASHIZUNO";

	/** COLUMNアノテーション step. */
	public static final String step_COLUMN = "STEP";

	/** COLUMNアノテーション substep. */
	public static final String substep_COLUMN = "SUBSTEP";

	/** COLUMNアノテーション route. */
	public static final String route_COLUMN = "ROUTE";

	/** COLUMNアノテーション shoricode. */
	public static final String shoricode_COLUMN = "SHORICODE";

	/** COLUMNアノテーション syoriName. */
	public static final String syoriName_COLUMN = "SYORI_NAME";

	/** COLUMNアノテーション codeTank. */
	public static final String codeTank_COLUMN = "CODE_TANK";

	/** COLUMNアノテーション tonyu. */
	public static final String tonyu_COLUMN = "TONYU";

	/** COLUMNアノテーション settei1. */
	public static final String settei1_COLUMN = "SETTEI1";

	/** COLUMNアノテーション settei2. */
	public static final String settei2_COLUMN = "SETTEI2";

	/** COLUMNアノテーション settei3. */
	public static final String settei3_COLUMN = "SETTEI3";

	/** COLUMNアノテーション settei4. */
	public static final String settei4_COLUMN = "SETTEI4";

	/** COLUMNアノテーション settei5. */
	public static final String settei5_COLUMN = "SETTEI5";

	/** COLUMNアノテーション settei6. */
	public static final String settei6_COLUMN = "SETTEI6";

	/** COLUMNアノテーション unit. */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション tank1. */
	public static final String tank1_COLUMN = "TANK_1";

	/** COLUMNアノテーション tank2. */
	public static final String tank2_COLUMN = "TANK_2";

	//
	// インスタンスフィールド
	//

	private String seizosashizuno;

	private java.math.BigDecimal step;

	private java.math.BigDecimal substep;

	private String route;

	private String shoricode;

	private String syoriName;

	private String codeTank;

	private String tonyu;

	private String settei1;

	private String settei2;

	private String settei3;

	private String settei4;

	private String settei5;

	private String settei6;

	private String unit;

	private String tank1;

	private String tank2;

	//
	// インスタンスメソッド
	//

	/**
	 * seizosashizuno取得.
	 * @return seizosashizuno
	 */
	public String getSeizosashizuno() {
		return this.seizosashizuno;
	}

	/**
	 * seizosashizuno設定.
	 * @param seizosashizuno seizosashizuno
	 */
	public void setSeizosashizuno(final String seizosashizuno) {
		this.seizosashizuno = seizosashizuno;
	}

	/**
	 * step取得.
	 * @return step
	 */
	public java.math.BigDecimal getStep() {
		return this.step;
	}

	/**
	 * step設定.
	 * @param step step
	 */
	public void setStep(final java.math.BigDecimal step) {
		this.step = step;
	}

	/**
	 * substep取得.
	 * @return substep
	 */
	public java.math.BigDecimal getSubstep() {
		return this.substep;
	}

	/**
	 * substep設定.
	 * @param substep substep
	 */
	public void setSubstep(final java.math.BigDecimal substep) {
		this.substep = substep;
	}

	/**
	 * route取得.
	 * @return route
	 */
	public String getRoute() {
		return this.route;
	}

	/**
	 * route設定.
	 * @param route route
	 */
	public void setRoute(final String route) {
		this.route = route;
	}

	/**
	 * shoricode取得.
	 * @return shoricode
	 */
	public String getShoricode() {
		return this.shoricode;
	}

	/**
	 * shoricode設定.
	 * @param shoricode shoricode
	 */
	public void setShoricode(final String shoricode) {
		this.shoricode = shoricode;
	}

	/**
	 * syoriName取得.
	 * @return syoriName
	 */
	public String getSyoriName() {
		return this.syoriName;
	}

	/**
	 * syoriName設定.
	 * @param syoriName syoriName
	 */
	public void setSyoriName(final String syoriName) {
		this.syoriName = syoriName;
	}

	/**
	 * codeTank取得.
	 * @return codeTank
	 */
	public String getCodeTank() {
		return this.codeTank;
	}

	/**
	 * codeTank設定.
	 * @param codeTank codeTank
	 */
	public void setCodeTank(final String codeTank) {
		this.codeTank = codeTank;
	}

	/**
	 * tonyu取得.
	 * @return tonyu
	 */
	public String getTonyu() {
		return this.tonyu;
	}

	/**
	 * tonyu設定.
	 * @param tonyu tonyu
	 */
	public void setTonyu(final String tonyu) {
		this.tonyu = tonyu;
	}

	/**
	 * settei1取得.
	 * @return settei1
	 */
	public String getSettei1() {
		return this.settei1;
	}

	/**
	 * settei1設定.
	 * @param settei1 settei1
	 */
	public void setSettei1(final String settei1) {
		this.settei1 = settei1;
	}

	/**
	 * settei2取得.
	 * @return settei2
	 */
	public String getSettei2() {
		return this.settei2;
	}

	/**
	 * settei2設定.
	 * @param settei2 settei2
	 */
	public void setSettei2(final String settei2) {
		this.settei2 = settei2;
	}

	/**
	 * settei3取得.
	 * @return settei3
	 */
	public String getSettei3() {
		return this.settei3;
	}

	/**
	 * settei3設定.
	 * @param settei3 settei3
	 */
	public void setSettei3(final String settei3) {
		this.settei3 = settei3;
	}

	/**
	 * settei4取得.
	 * @return settei4
	 */
	public String getSettei4() {
		return this.settei4;
	}

	/**
	 * settei4設定.
	 * @param settei4 settei4
	 */
	public void setSettei4(final String settei4) {
		this.settei4 = settei4;
	}

	/**
	 * settei5取得.
	 * @return settei5
	 */
	public String getSettei5() {
		return this.settei5;
	}

	/**
	 * settei5設定.
	 * @param settei5 settei5
	 */
	public void setSettei5(final String settei5) {
		this.settei5 = settei5;
	}

	/**
	 * settei6取得.
	 * @return settei6
	 */
	public String getSettei6() {
		return this.settei6;
	}

	/**
	 * settei6設定.
	 * @param settei6 settei6
	 */
	public void setSettei6(final String settei6) {
		this.settei6 = settei6;
	}

	/**
	 * unit取得.
	 * @return unit
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * unit設定.
	 * @param unit unit
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	/**
	 * tank1取得.
	 * @return tank1
	 */
	public String getTank1() {
		return this.tank1;
	}

	/**
	 * tank1設定.
	 * @param tank1 tank1
	 */
	public void setTank1(final String tank1) {
		this.tank1 = tank1;
	}

	/**
	 * tank2取得.
	 * @return tank2
	 */
	public String getTank2() {
		return this.tank2;
	}

	/**
	 * tank2設定.
	 * @param tank2 tank2
	 */
	public void setTank2(final String tank2) {
		this.tank2 = tank2;
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
