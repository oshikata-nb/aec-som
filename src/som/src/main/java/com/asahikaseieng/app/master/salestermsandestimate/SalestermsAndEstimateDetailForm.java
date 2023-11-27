
/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 販売条件・見積単価 コピー作成・削除詳細 Formクラス.
 * @author t0011036
 */
public final class SalestermsAndEstimateDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 入力日 */
	private String strInputDate;
	
	/* 更新日時 */
	private java.sql.Timestamp updateDate;
	
	/* 入力者コード */
	private String tantoCd;	
	
	/* 入力者名 */
	private String tantoName;
	
	/* ステータス */
	private String status;	
	
	/* ステータス名称 */
	private String statusName;
	
	/* 処理区分名称 */
	private String processDivision;
	
	private String[] processDivisionValues;

	private String[] processDivisionLabels;
	
	/* コピー元・削除品目コード */
	private String itemCdFrom;
	
	/* コピー元・削除品目名称 */
	private String itemNameFrom;
	
	/* コピー元・削除品目における他社コード1 */
	private String otherCompanyCd1From;

	/* コピー元・削除品目における荷姿 */
	private String styleOfPackingFrom;
	
	/* コピー先品目コード */
	private String itemCdTo;
	
	/* コピー先品目名称 */
	private String itemNameTo;
	
	/* コピー先品目における他社コード1 */
	private String otherCompanyCd1To;

	/* コピー先品目における荷姿 */
	private String styleOfPackingTo;
	
	/* 販売条件・見積単価コピー・削除テーブルデータのpkNo */
	private String pkNo;
	
	/* 新規フラグ */
	private String newFlg;
	
	/* 変更フラグ */
	private String dirtyFlg;
	
	/* 確定・確定取消判別フラグ */
	private String confirmFlg;
	
	/** ログ出力用エラーコード */
	private String errorCd;

	/** ログ出力用エラーメッセージ */
	private String errorMsg;


	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateDetailForm() {
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
		setStrInputDate(null);
		setUpdateDate(null);
		setTantoCd(null);
		setTantoName(null);
		setStatus(null);
		setStatusName(null);
		setProcessDivision(null);
		setProcessDivisionValues(null);
		setProcessDivisionLabels(null);
		setItemCdFrom(null);
		setItemNameFrom(null);
		setOtherCompanyCd1From(null);
		setStyleOfPackingFrom(null);
		setItemCdTo(null);
		setItemNameTo(null);
		setOtherCompanyCd1To(null);
		setStyleOfPackingTo(null);		
		setPkNo(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setConfirmFlg(null);
		/** エラーコード */
		setErrorCd(null);
		/** エラーメッセージ */
		setErrorMsg(null);

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
	 * otherCompanyCd1Fromを取得します。
	 * @return otherCompanyCd1From
	 */
	public String getOtherCompanyCd1From() {
		return otherCompanyCd1From;
	}

	/**
	 * otherCompanyCd1Fromを設定します。
	 * @param otherCompanyCd1From otherCompanyCd1From
	 */
	public void setOtherCompanyCd1From(final String otherCompanyCd1From) {
		this.otherCompanyCd1From = otherCompanyCd1From;
	}

	/**
	 * styleOfPackingFromを取得します。
	 * @return styleOfPackingFrom
	 */
	public String getStyleOfPackingFrom() {
		return styleOfPackingFrom;
	}

	/**
	 * styleOfPackingFromを設定します。
	 * @param styleOfPackingFrom styleOfPackingFrom
	 */
	public void setStyleOfPackingFrom(final String styleOfPackingFrom) {
		this.styleOfPackingFrom = styleOfPackingFrom;
	}

	/**
	 * otherCompanyCd1Toを取得します。
	 * @return otherCompanyCd1To
	 */
	public String getOtherCompanyCd1To() {
		return otherCompanyCd1To;
	}

	/**
	 * otherCompanyCd1Toを設定します。
	 * @param otherCompanyCd1To otherCompanyCd1To
	 */
	public void setOtherCompanyCd1To(final String otherCompanyCd1To) {
		this.otherCompanyCd1To = otherCompanyCd1To;
	}

	/**
	 * styleOfPackingToを取得します。
	 * @return styleOfPackingTo
	 */
	public String getStyleOfPackingTo() {
		return styleOfPackingTo;
	}

	/**
	 * styleOfPackingToを設定します。
	 * @param styleOfPackingTo styleOfPackingTo
	 */
	public void setStyleOfPackingTo(final String styleOfPackingTo) {
		this.styleOfPackingTo = styleOfPackingTo;
	}

	/**
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}

	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(final String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * strInputDateを取得します。
	 * @return strInputDate
	 */
	public String getStrInputDate() {
		return strInputDate;
	}

	/**
	 * strInputDateを設定します。
	 * @param strInputDate strInputDate
	 */
	public void setStrInputDate(final String strInputDate) {
		this.strInputDate = strInputDate;
	}

	/**
	 * tantoNameを取得します。
	 * @return tantoName
	 */
	public String getTantoName() {
		return tantoName;
	}

	/**
	 * tantoNameを設定します。
	 * @param tantoName tantoName
	 */
	public void setTantoName(final String tantoName) {
		this.tantoName = tantoName;
	}

	/**
	 * statusNameを取得します。
	 * @return statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * statusNameを設定します。
	 * @param statusName statusName
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * processDivisionを取得します。
	 * @return processDivision
	 */
	public String getProcessDivision() {
		return processDivision;
	}

	/**
	 * processDivisionを設定します。
	 * @param processDivision processDivision
	 */
	public void setProcessDivision(final String processDivision) {
		this.processDivision = processDivision;
	}

	/**
	 * processDivisionValuesを取得します。
	 * @return processDivisionValues
	 */
	public String[] getProcessDivisionValues() {
		return processDivisionValues;
	}

	/**
	 * processDivisionValuesを設定します。
	 * @param processDivisionValues processDivisionValues
	 */
	public void setProcessDivisionValues(final String[] processDivisionValues) {
		this.processDivisionValues = processDivisionValues;
	}

	/**
	 * processDivisionLabelsを取得します。
	 * @return processDivisionLabels
	 */
	public String[] getProcessDivisionLabels() {
		return processDivisionLabels;
	}

	/**
	 * processDivisionLabelsを設定します。
	 * @param processDivisionLabels processDivisionLabels
	 */
	public void setProcessDivisionLabels(final String[] processDivisionLabels) {
		this.processDivisionLabels = processDivisionLabels;
	}

	/**
	 * itemCdFromを取得します。
	 * @return itemCdFrom
	 */
	public String getItemCdFrom() {
		return itemCdFrom;
	}

	/**
	 * itemCdFromを設定します。
	 * @param itemCdFrom itemCdFrom
	 */
	public void setItemCdFrom(final String itemCdFrom) {
		this.itemCdFrom = itemCdFrom;
	}

	/**
	 * itemNameFromを取得します。
	 * @return itemNameFrom
	 */
	public String getItemNameFrom() {
		return itemNameFrom;
	}

	/**
	 * itemNameFromを設定します。
	 * @param itemNameFrom itemNameFrom
	 */
	public void setItemNameFrom(final String itemNameFrom) {
		this.itemNameFrom = itemNameFrom;
	}

	/**
	 * itemCdToを取得します。
	 * @return itemCdTo
	 */
	public String getItemCdTo() {
		return itemCdTo;
	}

	/**
	 * itemCdToを設定します。
	 * @param itemCdTo itemCdTo
	 */
	public void setItemCdTo(final String itemCdTo) {
		this.itemCdTo = itemCdTo;
	}

	/**
	 * itemNameToを取得します。
	 * @return itemNameTo
	 */
	public String getItemNameTo() {
		return itemNameTo;
	}

	/**
	 * itemNameToを設定します。
	 * @param itemNameTo itemNameTo
	 */
	public void setItemNameTo(final String itemNameTo) {
		this.itemNameTo = itemNameTo;
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
	 * statusを取得します。
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(final String status) {
		this.status = status;
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
	 * errorCdを取得します。
	 * @return errorCd
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * errorCdを設定します。
	 * @param errorCd errorCd
	 */
	public void setErrorCd(final String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * errorMsgを取得します。
	 * @return errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * errorMsgを設定します。
	 * @param errorMsg errorMsg
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * confirmFlgを取得します。
	 * @return confirmFlg
	 */
	public String getConfirmFlg() {
		return confirmFlg;
	}

	/**
	 * confirmFlgを設定します。
	 * @param confirmFlg confirmFlg
	 */
	public void setConfirmFlg(final String confirmFlg) {
		this.confirmFlg = confirmFlg;
	}


}

