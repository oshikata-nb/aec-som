/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.salestermsbalancelist.SalesTermsBalanceList;
import com.asahikaseieng.dao.nonentity.master.salestermsdetaillist.SalesTermsDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 販売条件詳細 Formクラス.
 * @author t0011036
 */
public final class SalesTermsDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 納入先コード */
	private String deliveryCd;

	private String savDeliveryCd;

	/* 納入先名称 */
	private String deliveryName1;

	/* 帳合コード */
	private String balanceCd;

	private String savBalanceCd;

	/* 区分 */
	private BigDecimal balanceType;

	/* 区分名称 */
	private String balanceTypeName;

	/* 得意先コード */
	private String venderCd;

	/* 得意先名称 */
	private String venderName1;

	/* 帳合リスト */
	private List<SalesTermsBalanceList> searchSalesTermsBalanceList;

	/* 品目リスト */
	private List<SalesTermsDetailList> searchSalesTermsDetailList;

	/* 担当者コード */
	private String tantoCd;

	/* 担当者名 */
	private String tantoNm;

	/* 変更フラグ */
	private String dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/* 受注時備考 */
	private String remark;

	/* 運送会社コード */
	private String carryCd;

	/* 運送会社名称 */
	private String carryName;

	/* リードタイム */
	private String leadTime;

	/* 納入時刻 */
	private String deliveryTime;

	/* 受注時自動表示備考 */
	private String orderAutoRemark;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsDetailForm() {
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
		setDeliveryCd(null);
		setSavDeliveryCd(null);
		setDeliveryName1(null);
		setBalanceCd(null);
		setSavBalanceCd(null);
		setBalanceType(null);
		setBalanceTypeName(null);
		setVenderCd(null);
		setVenderName1(null);
		setSearchSalesTermsBalanceList(new ArrayList<SalesTermsBalanceList>());
		setSearchSalesTermsDetailList(new ArrayList<SalesTermsDetailList>());
		setTantoCd(null);
		setTantoNm(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setRemark(null);

		/* 運送会社コード */
		setCarryCd(null);

		/* 運送会社名称 */
		setCarryName(null);

		/* リードタイム */
		setLeadTime(null);

		/* 納入時刻 */
		setDeliveryTime(null);


		/* 受注時自動表示備考 */
		setOrderAutoRemark(null);

	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchSalesTermsDetailList() != null) {
			for (SalesTermsDetailList bean : getSearchSalesTermsDetailList()) {
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
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * balanceTypeNameを取得します。
	 * @return balanceTypeName
	 */
	public String getBalanceTypeName() {
		return balanceTypeName;
	}

	/**
	 * balanceTypeNameを設定します。
	 * @param balanceTypeName balanceTypeName
	 */
	public void setBalanceTypeName(final String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
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
	 * searchSalesTermsDetailListを取得します。
	 * @return searchSalesTermsDetailList
	 */
	public List<SalesTermsDetailList> getSearchSalesTermsDetailList() {
		return searchSalesTermsDetailList;
	}

	/**
	 * searchSalesTermsDetailListを設定します。
	 * @param searchSalesTermsDetailList searchSalesTermsDetailList
	 */
	public void setSearchSalesTermsDetailList(
			final List<SalesTermsDetailList> searchSalesTermsDetailList) {
		this.searchSalesTermsDetailList = searchSalesTermsDetailList;
	}

	/**
	 * searchSalesTermsBalanceListを取得します。
	 * @return searchSalesTermsBalanceList
	 */
	public List<SalesTermsBalanceList> getSearchSalesTermsBalanceList() {
		return searchSalesTermsBalanceList;
	}

	/**
	 * searchSalesTermsBalanceListを設定します。
	 * @param searchSalesTermsBalanceList searchSalesTermsBalanceList
	 */
	public void setSearchSalesTermsBalanceList(
			final List<SalesTermsBalanceList> searchSalesTermsBalanceList) {
		this.searchSalesTermsBalanceList = searchSalesTermsBalanceList;
	}

	/**
	 * balanceTypeを取得します。
	 * @return balanceType
	 */
	public BigDecimal getBalanceType() {
		return balanceType;
	}

	/**
	 * balanceTypeを設定します。
	 * @param balanceType balanceType
	 */
	public void setBalanceType(final BigDecimal balanceType) {
		this.balanceType = balanceType;
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
	 * savBalanceCdを取得します。
	 * @return savBalanceCd
	 */
	public String getSavBalanceCd() {
		return savBalanceCd;
	}

	/**
	 * savBalanceCdを設定します。
	 * @param savBalanceCd savBalanceCd
	 */
	public void setSavBalanceCd(final String savBalanceCd) {
		this.savBalanceCd = savBalanceCd;
	}

	/**
	 * savDeliveryCdを取得します。
	 * @return savDeliveryCd
	 */
	public String getSavDeliveryCd() {
		return savDeliveryCd;
	}

	/**
	 * savDeliveryCdを設定します。
	 * @param savDeliveryCd savDeliveryCd
	 */
	public void setSavDeliveryCd(final String savDeliveryCd) {
		this.savDeliveryCd = savDeliveryCd;
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
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
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
	 * carryNameを取得します。
	 * @return carryName
	 */
	public String getCarryName() {
		return carryName;
	}

	/**
	 * carryNameを設定します。
	 * @param carryName carryName
	 */
	public void setCarryName(final String carryName) {
		this.carryName = carryName;
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
	 * leadTimeを取得します。
	 * @return leadTime
	 */
	public String getLeadTime() {
		return leadTime;
	}

	/**
	 * leadTimeを設定します。
	 * @param leadTime leadTime
	 */
	public void setLeadTime(final String leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * orderAutoRemarkを取得します。
	 * @return orderAutoRemark
	 */
	public String getOrderAutoRemark() {
		return orderAutoRemark;
	}

	/**
	 * orderAutoRemarkを設定します。
	 * @param orderAutoRemark orderAutoRemark
	 */
	public void setOrderAutoRemark(final String orderAutoRemark) {
		this.orderAutoRemark = orderAutoRemark;
	}
}
