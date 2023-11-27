/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.unitpricedetaillist.UnitpriceDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 仕入先別単価詳細 Formクラス.
 * @author t0011036
 */
public final class UnitpriceDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 取引先区分 */
	private String venderDivision;

	/* 仕入先コード */
	private String venderCd;

	/* 仕入先名称 */
	private String venderName1;

	/* 品目コード */
	private String itemCd;

	/* 品目名称 */
	private String itemName;

	/* 他社コード1 */
	private String otherCompanyCd1;

	/* 荷姿 */
	private String styleOfPacking;

	/* メーカー名 */
	private String materialMakerName;

	/* 単価バージョン */
	private BigDecimal version;

	/* 単価区分 */
	private String unitpriceDivision;

	/* 単価リスト */
	private List<UnitpriceDetailList> searchUnitpriceDetailList;

	/* 有効開始日 */
	private java.sql.Timestamp validDate;

	private String strValidDate;

	/* 備考 */
	private String remarks;

	/* 区分 */
	private String unitDivision;

	/* 運用管理単位 */
	private String unitOfOperationManagement;

	/* 小数点以下桁数 */
	private BigDecimal smallnumLength;

	/* 端数区分 */
	private BigDecimal roundDivision;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private String dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceDetailForm() {
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
		setVenderDivision("SI");
		setVenderCd(null);
		setVenderName1(null);
		setItemCd(null);
		setItemName(null);
		setOtherCompanyCd1(null);
		setStyleOfPacking(null);
		setVersion(new BigDecimal("1")); /* 固定値 */
		setMaterialMakerName(null);
		setUnitpriceDivision(null);
		setSearchUnitpriceDetailList(new ArrayList<UnitpriceDetailList>());
		setValidDate(null);
		setStrValidDate(null);
		setRemarks(null);
		setUnitDivision(null);
		setUnitOfOperationManagement(null);
		setSmallnumLength(new BigDecimal("0"));
		setRoundDivision(new BigDecimal("0"));
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
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * materialMakerNameを取得します。
	 * @return materialMakerName
	 */
	public String getMaterialMakerName() {
		return materialMakerName;
	}

	/**
	 * materialMakerNameを設定します。
	 * @param materialMakerName materialMakerName
	 */
	public void setMaterialMakerName(final String materialMakerName) {
		this.materialMakerName = materialMakerName;
	}

	/**
	 * otherCompanyCd1を取得します。
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1を設定します。
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * remarksを取得します。
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * remarksを設定します。
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * searchUnitpriceDetailListを取得します。
	 * @return searchUnitpriceDetailList
	 */
	public List<UnitpriceDetailList> getSearchUnitpriceDetailList() {
		return searchUnitpriceDetailList;
	}

	/**
	 * searchUnitpriceDetailListを設定します。
	 * @param searchUnitpriceDetailList searchUnitpriceDetailList
	 */
	public void setSearchUnitpriceDetailList(
			final List<UnitpriceDetailList> searchUnitpriceDetailList) {
		this.searchUnitpriceDetailList = searchUnitpriceDetailList;
	}

	/**
	 * strValidDateを取得します。
	 * @return strValidDate
	 */
	public String getStrValidDate() {
		return strValidDate;
	}

	/**
	 * strValidDateを設定します。
	 * @param strValidDate strValidDate
	 */
	public void setStrValidDate(final String strValidDate) {
		this.strValidDate = strValidDate;
	}

	/**
	 * styleOfPackingを取得します。
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * styleOfPackingを設定します。
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * unitpriceDivisionを取得します。
	 * @return unitpriceDivision
	 */
	public String getUnitpriceDivision() {
		return unitpriceDivision;
	}

	/**
	 * unitpriceDivisionを設定します。
	 * @param unitpriceDivision unitpriceDivision
	 */
	public void setUnitpriceDivision(final String unitpriceDivision) {
		this.unitpriceDivision = unitpriceDivision;
	}

	/**
	 * validDateを取得します。
	 * @return validDate
	 */
	public java.sql.Timestamp getValidDate() {
		return validDate;
	}

	/**
	 * validDateを設定します。
	 * @param validDate validDate
	 */
	public void setValidDate(final java.sql.Timestamp validDate) {
		this.validDate = validDate;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * versionを取得します。
	 * @return version
	 */
	public BigDecimal getVersion() {
		return version;
	}

	/**
	 * versionを設定します。
	 * @param version version
	 */
	public void setVersion(final BigDecimal version) {
		this.version = version;
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

	/**
	 * roundDivisionを取得します。
	 * @return roundDivision
	 */
	public BigDecimal getRoundDivision() {
		return roundDivision;
	}

	/**
	 * roundDivisionを設定します。
	 * @param roundDivision roundDivision
	 */
	public void setRoundDivision(final BigDecimal roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * smallnumLengthを取得します。
	 * @return smallnumLength
	 */
	public BigDecimal getSmallnumLength() {
		return smallnumLength;
	}

	/**
	 * smallnumLengthを設定します。
	 * @param smallnumLength smallnumLength
	 */
	public void setSmallnumLength(final BigDecimal smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * unitOfOperationManagementを取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagementを設定します。
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}
}
