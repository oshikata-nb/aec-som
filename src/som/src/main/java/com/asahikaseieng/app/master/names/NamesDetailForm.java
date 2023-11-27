/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 名称詳細 Formクラス.
 * @author t0011036
 */
public final class NamesDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 名称区分 */
	private String nameDivision;

	/* 名称区分名称 */
	private String nameDivisionName;

	/* 名称コード */
	private String nameCd;

	/* 名称1 */
	private String name01;

	/* 名称2 */
	private String name02;

	/* 名称3 */
	private String name03;

	/* 数量端数区分 */
	private BigDecimal quantityRoundup;

	/* 数量端数単位 */
	private BigDecimal quantityRoundupUnit;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public NamesDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setNameDivision(null);
		setNameDivisionName(null);
		setNameCd(null);
		setName01(null);
		setName02(null);
		setName03(null);
		setQuantityRoundup(BigDecimal.ZERO);
		setQuantityRoundupUnit(BigDecimal.ZERO);
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * name01を取得します。
	 * @return name01
	 */
	public String getName01() {
		return name01;
	}

	/**
	 * name01を設定します。
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * name02を取得します。
	 * @return name02
	 */
	public String getName02() {
		return name02;
	}

	/**
	 * name02を設定します。
	 * @param name02 name02
	 */
	public void setName02(final String name02) {
		this.name02 = name02;
	}

	/**
	 * name03を取得します。
	 * @return name03
	 */
	public String getName03() {
		return name03;
	}

	/**
	 * name03を設定します。
	 * @param name03 name03
	 */
	public void setName03(final String name03) {
		this.name03 = name03;
	}

	/**
	 * nameCdを取得します。
	 * @return nameCd
	 */
	public String getNameCd() {
		return nameCd;
	}

	/**
	 * nameCdを設定します。
	 * @param nameCd nameCd
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * nameDivisionを取得します。
	 * @return nameDivision
	 */
	public String getNameDivision() {
		return nameDivision;
	}

	/**
	 * nameDivisionを設定します。
	 * @param nameDivision nameDivision
	 */
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}

	/**
	 * quantityRoundupを取得します。
	 * @return quantityRoundup
	 */
	public BigDecimal getQuantityRoundup() {
		return quantityRoundup;
	}

	/**
	 * quantityRoundupを設定します。
	 * @param quantityRoundup quantityRoundup
	 */
	public void setQuantityRoundup(final BigDecimal quantityRoundup) {
		this.quantityRoundup = quantityRoundup;
	}

	/**
	 * quantityRoundupUnitを取得します。
	 * @return quantityRoundupUnit
	 */
	public BigDecimal getQuantityRoundupUnit() {
		return quantityRoundupUnit;
	}

	/**
	 * quantityRoundupUnitを設定します。
	 * @param quantityRoundupUnit quantityRoundupUnit
	 */
	public void setQuantityRoundupUnit(final BigDecimal quantityRoundupUnit) {
		this.quantityRoundupUnit = quantityRoundupUnit;
	}

	/**
	 * nameDivisionNameを取得します。
	 * @return nameDivisionName
	 */
	public String getNameDivisionName() {
		return nameDivisionName;
	}

	/**
	 * nameDivisionNameを設定します。
	 * @param nameDivisionName nameDivisionName
	 */
	public void setNameDivisionName(final String nameDivisionName) {
		this.nameDivisionName = nameDivisionName;
	}
}
