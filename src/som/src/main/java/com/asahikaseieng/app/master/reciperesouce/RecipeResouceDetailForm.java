/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist.RecipePegResouceDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 設備詳細 Formクラス.
 * @author t0011036
 */
public final class RecipeResouceDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 設備コード */
	private String resouceCd;

	/* 設備名称 */
	private String resouceName;

	/* 略称 */
	private String shortName;

	/* 作業時間単価(機械) */
	private BigDecimal costMachine;

	private String strCostMachine;

	/* 作業時間単価(一律) */
	private BigDecimal cost;

	private String strCost;

	/* 設備グループコード */
	private String resouceGroupCd;

	/* 設備グループ名称 */
	private String resouceGroupName;

	/* 生産ラインコード */
	private String productionLine;

	/* 生産ライン名称 */
	private String[] productionLineValues;

	private String[] productionLineLabels;

	/* 指図書発行有無フラグ */
	private BigDecimal orderPublishFlg;

	/* 前工程設備リスト */
	private List<RecipePegResouceDetailList> searchRecipePegResouceDetailList;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 区分 */
	private String unitDivision;

	/* 変更フラグ */
	private String dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceDetailForm() {
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

		if ("dellist".equals(getOp())) {
			/* チェックボックスクリア処理 */
			clearCheck();
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
		setResouceCd(null);
		setResouceName(null);
		setShortName(null);
		setCostMachine(null);
		setStrCostMachine(null);
		setCost(null);
		setStrCost(null);
		setResouceGroupCd(null);
		setResouceGroupName(null);
		setProductionLine(null);
		setProductionLineValues(null);
		setProductionLineLabels(null);
		setOrderPublishFlg(null);
		setUnitDivision("GENKA");
		setSearchRecipePegResouceDetailList(new ArrayList<RecipePegResouceDetailList>());
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchRecipePegResouceDetailList() != null) {
			for (RecipePegResouceDetailList bean : getSearchRecipePegResouceDetailList()) {
				bean.setChecked(Boolean.FALSE);
			}
		}
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
	 * costを取得します。
	 * @return cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * costを設定します。
	 * @param cost cost
	 */
	public void setCost(final BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * costMachineを取得します。
	 * @return costMachine
	 */
	public BigDecimal getCostMachine() {
		return costMachine;
	}

	/**
	 * costMachineを設定します。
	 * @param costMachine costMachine
	 */
	public void setCostMachine(final BigDecimal costMachine) {
		this.costMachine = costMachine;
	}

	/**
	 * productionLineを取得します。
	 * @return productionLine
	 */
	public String getProductionLine() {
		return productionLine;
	}

	/**
	 * productionLineを設定します。
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * resouceCdを取得します。
	 * @return resouceCd
	 */
	public String getResouceCd() {
		return resouceCd;
	}

	/**
	 * resouceCdを設定します。
	 * @param resouceCd resouceCd
	 */
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * resouceGroupCdを取得します。
	 * @return resouceGroupCd
	 */
	public String getResouceGroupCd() {
		return resouceGroupCd;
	}

	/**
	 * resouceGroupCdを設定します。
	 * @param resouceGroupCd resouceGroupCd
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * resouceGroupNameを取得します。
	 * @return resouceGroupName
	 */
	public String getResouceGroupName() {
		return resouceGroupName;
	}

	/**
	 * resouceGroupNameを設定します。
	 * @param resouceGroupName resouceGroupName
	 */
	public void setResouceGroupName(final String resouceGroupName) {
		this.resouceGroupName = resouceGroupName;
	}

	/**
	 * resouceNameを取得します。
	 * @return resouceName
	 */
	public String getResouceName() {
		return resouceName;
	}

	/**
	 * resouceNameを設定します。
	 * @param resouceName resouceName
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

	/**
	 * shortNameを取得します。
	 * @return shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * shortNameを設定します。
	 * @param shortName shortName
	 */
	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

	/**
	 * strCostを取得します。
	 * @return strCost
	 */
	public String getStrCost() {
		return strCost;
	}

	/**
	 * strCostを設定します。
	 * @param strCost strCost
	 */
	public void setStrCost(final String strCost) {
		this.strCost = strCost;
	}

	/**
	 * strCostMachineを取得します。
	 * @return strCostMachine
	 */
	public String getStrCostMachine() {
		return strCostMachine;
	}

	/**
	 * strCostMachineを設定します。
	 * @param strCostMachine strCostMachine
	 */
	public void setStrCostMachine(final String strCostMachine) {
		this.strCostMachine = strCostMachine;
	}

	/**
	 * productionLineLabelsを取得します。
	 * @return productionLineLabels
	 */
	public String[] getProductionLineLabels() {
		return productionLineLabels;
	}

	/**
	 * productionLineLabelsを設定します。
	 * @param productionLineLabels productionLineLabels
	 */
	public void setProductionLineLabels(final String[] productionLineLabels) {
		this.productionLineLabels = productionLineLabels;
	}

	/**
	 * productionLineValuesを取得します。
	 * @return productionLineValues
	 */
	public String[] getProductionLineValues() {
		return productionLineValues;
	}

	/**
	 * productionLineValuesを設定します。
	 * @param productionLineValues productionLineValues
	 */
	public void setProductionLineValues(final String[] productionLineValues) {
		this.productionLineValues = productionLineValues;
	}

	/**
	 * unitDivisionを取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * unitDivisionを設定します。
	 * @param unitDivision unitDivision
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * searchRecipePegResouceDetailListを取得します。
	 * @return searchRecipePegResouceDetailList
	 */
	public List<RecipePegResouceDetailList> getSearchRecipePegResouceDetailList() {
		return searchRecipePegResouceDetailList;
	}

	/**
	 * searchRecipePegResouceDetailListを設定します。
	 * @param searchRecipePegResouceDetailList searchRecipePegResouceDetailList
	 */
	public void setSearchRecipePegResouceDetailList(
			final List<RecipePegResouceDetailList> searchRecipePegResouceDetailList) {
		this.searchRecipePegResouceDetailList = searchRecipePegResouceDetailList;
	}

	/**
	 * orderPublishFlgを取得します。
	 * @return orderPublishFlg
	 */
	public BigDecimal getOrderPublishFlg() {
		return orderPublishFlg;
	}

	/**
	 * orderPublishFlgを設定します。
	 * @param orderPublishFlg orderPublishFlg
	 */
	public void setOrderPublishFlg(final BigDecimal orderPublishFlg) {
		this.orderPublishFlg = orderPublishFlg;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}
}
