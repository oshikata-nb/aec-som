/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 納入先詳細 Formクラス.
 * @author t0011036
 */
public final class DeliveryDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 取引先区分 */
	private String venderDivision;

	/* 取引先コード */
	private String venderCd;

	/* 納入先コード */
	private String deliveryCd;

	/* 納入先名称1 */
	private String deliveryName1;

	/* 納入先名称2 */
	private String deliveryName2;

	/* 納入先略称 */
	private String searchKana;

	/* 郵便番号 */
	private String zipcodeNo;

	/* 市町村コード */
	private String cityCd;

	/* 電話番号 */
	private String telNo;

	/* FAX番号 */
	private String faxNo;

	/* 住所1 */
	private String address1;

	/* 住所2 */
	private String address2;

	/* 住所3 */
	private String address3;
	
	/* 納入先地区 */
	private String carryFareCd;
	
	/* 運送会社コード */
	private String carryCd;

	/* 運送会社名称 */
	private String carryName1;

	/* リードタイム */
	private BigDecimal leadTime;

	private String strLeadTime;

	/* 納入時刻 */
	private String deliveryTime;

	/* 運賃請求有無 */
	private Boolean fareClaimExistence;

	/* 区分 */
	private BigDecimal division;

	/* 営業担当者コード */
	private String tantoCd;

	/* 営業担当者名 */
	private String tantoNm;

	/* 納入条件 */
	private String deliveryCondition;

	/* 備考 */
	private String remarks;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* コピーフラグ */
	private String copyFlg;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public DeliveryDetailForm() {
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

		if ("update".equals(getOp())) {
			setFareClaimExistence(Boolean.FALSE);
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
		setVenderDivision(null);
		setVenderCd(null);
		setDeliveryCd(null);
		setDeliveryName1(null);
		setDeliveryName2(null);
		setSearchKana(null);
		setZipcodeNo(null);
		setCityCd(null);
		setTelNo(null);
		setFaxNo(null);
		setAddress1(null);
		setAddress2(null);
		setAddress3(null);
		setCarryFareCd(null);
		setCarryCd(null);
		setCarryName1(null);
		setLeadTime(null);
		setStrLeadTime(null);
		setDeliveryTime(null);
		setFareClaimExistence(Boolean.FALSE);
		setDivision(null);
		setTantoCd(null);
		setTantoNm(null);
		setDeliveryCondition(null);
		setRemarks(null);
		setUpdateDate(null);
		setCopyFlg(null);
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
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * address1を取得します。
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * address1を設定します。
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2を取得します。
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * address2を設定します。
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3を取得します。
	 * @return address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * address3を設定します。
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * carryFareCdを取得します。
	 * @return carryFareCd
	 */
	public String getCarryFareCd() {
		return carryFareCd;
	}

	/**
	 * carryFareCdを設定します。
	 * @param carryFareCd carryFareCd
	 */
	public void setCarryFareCd(String carryFareCd) {
		this.carryFareCd = carryFareCd;
	}

	/**
	 * carryCdを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * carryCdを設定します。
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryName1を取得します。
	 * @return carryName1
	 */
	public String getCarryName1() {
		return carryName1;
	}

	/**
	 * carryName1を設定します。
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * cityCdを取得します。
	 * @return cityCd
	 */
	public String getCityCd() {
		return cityCd;
	}

	/**
	 * cityCdを設定します。
	 * @param cityCd cityCd
	 */
	public void setCityCd(final String cityCd) {
		this.cityCd = cityCd;
	}

	/**
	 * deliveryConditionを取得します。
	 * @return deliveryCondition
	 */
	public String getDeliveryCondition() {
		return deliveryCondition;
	}

	/**
	 * deliveryConditionを設定します。
	 * @param deliveryCondition deliveryCondition
	 */
	public void setDeliveryCondition(final String deliveryCondition) {
		this.deliveryCondition = deliveryCondition;
	}

	/**
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryName2を取得します。
	 * @return deliveryName2
	 */
	public String getDeliveryName2() {
		return deliveryName2;
	}

	/**
	 * deliveryName2を設定します。
	 * @param deliveryName2 deliveryName2
	 */
	public void setDeliveryName2(final String deliveryName2) {
		this.deliveryName2 = deliveryName2;
	}

	/**
	 * deliveryTimeを取得します。
	 * @return deliveryTime
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * deliveryTimeを設定します。
	 * @param deliveryTime deliveryTime
	 */
	public void setDeliveryTime(final String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * divisionを取得します。
	 * @return division
	 */
	public BigDecimal getDivision() {
		return division;
	}

	/**
	 * divisionを設定します。
	 * @param division division
	 */
	public void setDivision(final BigDecimal division) {
		this.division = division;
	}

	/**
	 * faxNoを取得します。
	 * @return faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * faxNoを設定します。
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * leadTimeを取得します。
	 * @return leadTime
	 */
	public BigDecimal getLeadTime() {
		return leadTime;
	}

	/**
	 * leadTimeを設定します。
	 * @param leadTime leadTime
	 */
	public void setLeadTime(final BigDecimal leadTime) {
		this.leadTime = leadTime;
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
	 * searchKanaを取得します。
	 * @return searchKana
	 */
	public String getSearchKana() {
		return searchKana;
	}

	/**
	 * searchKanaを設定します。
	 * @param searchKana searchKana
	 */
	public void setSearchKana(final String searchKana) {
		this.searchKana = searchKana;
	}

	/**
	 * strLeadTimeを取得します。
	 * @return strLeadTime
	 */
	public String getStrLeadTime() {
		return strLeadTime;
	}

	/**
	 * strLeadTimeを設定します。
	 * @param strLeadTime strLeadTime
	 */
	public void setStrLeadTime(final String strLeadTime) {
		this.strLeadTime = strLeadTime;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNmを取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * tantoNmを設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * telNoを取得します。
	 * @return telNo
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * telNoを設定します。
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * zipcodeNoを取得します。
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return zipcodeNo;
	}

	/**
	 * zipcodeNoを設定します。
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * fareClaimExistenceを設定します。
	 * @param fareClaimExistence fareClaimExistence
	 */
	public void setFareClaimExistence(final Boolean fareClaimExistence) {
		this.fareClaimExistence = fareClaimExistence;
	}

	/**
	 * fareClaimExistenceを取得します。
	 * @return fareClaimExistence
	 */
	public Boolean getFareClaimExistence() {
		return fareClaimExistence;
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
	 * copyFlgを取得します。
	 * @return copyFlg
	 */
	public String getCopyFlg() {
		return copyFlg;
	}

	/**
	 * copyFlgを設定します。
	 * @param copyFlg copyFlg
	 */
	public void setCopyFlg(final String copyFlg) {
		this.copyFlg = copyFlg;
	}
}
