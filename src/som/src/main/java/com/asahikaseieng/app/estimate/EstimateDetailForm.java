/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.estimatebalancelist.EstimateBalanceList;
import com.asahikaseieng.dao.nonentity.estimatedetaillist.EstimateDetailList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 見積/単価詳細 Formクラス.
 * @author t0011036
 */
public final class EstimateDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 見積番号 */
	private String estimateNo;

	/* ステータス */
	private BigDecimal estimateStatus;

	/* ステータス名称 */
	private String estimateStatusName;

	/* 見積入力日 */
	private Timestamp estimateInputDate;

	private String strEstimateInputDate;

	/* 帳合コード */
	private String balanceCd;

	/* 得意先コード */
	private String venderCd;

	/* 得意先名称 */
	private String venderName1;

	/* 帳合リスト */
	private List<EstimateBalanceList> searchEstimateBalanceList;

	/* 単価リスト */
	private List<EstimateDetailList> searchEstimateDetailList;

	/* 見積有効期限(FROM) */
	private Timestamp estimateValidDateFrom;

	private String strEstimateValidDateFrom;

	/* 見積有効期限(TO) */
	private Timestamp estimateValidDateTo;

	private String strEstimateValidDateTo;

	/* 備考 */
	private String remark;

	/* カーソル位置 */
	private String cursor;

	/* 変更フラグ */
	private String dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/* EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public EstimateDetailForm() {
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

		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
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
		setEstimateNo(null);
		setEstimateStatus(null);
		setEstimateStatusName(null);
		setEstimateInputDate(AecDateUtils.getCurrentTimestamp());
		setStrEstimateInputDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		setBalanceCd(null);
		setVenderCd(null);
		setVenderName1(null);
		setSearchEstimateBalanceList(new ArrayList<EstimateBalanceList>());
		setSearchEstimateDetailList(new ArrayList<EstimateDetailList>());
		setEstimateValidDateFrom(null);
		setStrEstimateValidDateFrom(null);
		setEstimateValidDateTo(null);
		setStrEstimateValidDateTo(null);
		setRemark(null);
		setCursor(null);
		setDirtyFlg(null);
		setNewFlg(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchEstimateDetailList() != null) {
			for (EstimateDetailList bean : getSearchEstimateDetailList()) {
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
	 * estimateInputDateを取得します。
	 * @return estimateInputDate
	 */
	public Timestamp getEstimateInputDate() {
		return estimateInputDate;
	}

	/**
	 * estimateInputDateを設定します。
	 * @param estimateInputDate estimateInputDate
	 */
	public void setEstimateInputDate(final Timestamp estimateInputDate) {
		this.estimateInputDate = estimateInputDate;
	}

	/**
	 * estimateNoを取得します。
	 * @return estimateNo
	 */
	public String getEstimateNo() {
		return estimateNo;
	}

	/**
	 * estimateNoを設定します。
	 * @param estimateNo estimateNo
	 */
	public void setEstimateNo(final String estimateNo) {
		this.estimateNo = estimateNo;
	}

	/**
	 * estimateStatusを取得します。
	 * @return estimateStatus
	 */
	public BigDecimal getEstimateStatus() {
		return estimateStatus;
	}

	/**
	 * estimateStatusを設定します。
	 * @param estimateStatus estimateStatus
	 */
	public void setEstimateStatus(final BigDecimal estimateStatus) {
		this.estimateStatus = estimateStatus;
	}

	/**
	 * estimateStatusNameを取得します。
	 * @return estimateStatusName
	 */
	public String getEstimateStatusName() {
		return estimateStatusName;
	}

	/**
	 * estimateStatusNameを設定します。
	 * @param estimateStatusName estimateStatusName
	 */
	public void setEstimateStatusName(final String estimateStatusName) {
		this.estimateStatusName = estimateStatusName;
	}

	/**
	 * estimateValidDateFromを取得します。
	 * @return estimateValidDateFrom
	 */
	public Timestamp getEstimateValidDateFrom() {
		return estimateValidDateFrom;
	}

	/**
	 * estimateValidDateFromを設定します。
	 * @param estimateValidDateFrom estimateValidDateFrom
	 */
	public void setEstimateValidDateFrom(final Timestamp estimateValidDateFrom) {
		this.estimateValidDateFrom = estimateValidDateFrom;
	}

	/**
	 * estimateValidDateToを取得します。
	 * @return estimateValidDateTo
	 */
	public Timestamp getEstimateValidDateTo() {
		return estimateValidDateTo;
	}

	/**
	 * estimateValidDateToを設定します。
	 * @param estimateValidDateTo estimateValidDateTo
	 */
	public void setEstimateValidDateTo(final Timestamp estimateValidDateTo) {
		this.estimateValidDateTo = estimateValidDateTo;
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
	 * searchEstimateBalanceListを取得します。
	 * @return searchEstimateBalanceList
	 */
	public List<EstimateBalanceList> getSearchEstimateBalanceList() {
		return searchEstimateBalanceList;
	}

	/**
	 * searchEstimateBalanceListを設定します。
	 * @param searchEstimateBalanceList searchEstimateBalanceList
	 */
	public void setSearchEstimateBalanceList(
			final List<EstimateBalanceList> searchEstimateBalanceList) {
		this.searchEstimateBalanceList = searchEstimateBalanceList;
	}

	/**
	 * searchEstimateDetailListを取得します。
	 * @return searchEstimateDetailList
	 */
	public List<EstimateDetailList> getSearchEstimateDetailList() {
		return searchEstimateDetailList;
	}

	/**
	 * searchEstimateDetailListを設定します。
	 * @param searchEstimateDetailList searchEstimateDetailList
	 */
	public void setSearchEstimateDetailList(
			final List<EstimateDetailList> searchEstimateDetailList) {
		this.searchEstimateDetailList = searchEstimateDetailList;
	}

	/**
	 * strEstimateInputDateを取得します。
	 * @return strEstimateInputDate
	 */
	public String getStrEstimateInputDate() {
		return strEstimateInputDate;
	}

	/**
	 * strEstimateInputDateを設定します。
	 * @param strEstimateInputDate strEstimateInputDate
	 */
	public void setStrEstimateInputDate(final String strEstimateInputDate) {
		this.strEstimateInputDate = strEstimateInputDate;
	}

	/**
	 * strEstimateValidDateFromを取得します。
	 * @return strEstimateValidDateFrom
	 */
	public String getStrEstimateValidDateFrom() {
		return strEstimateValidDateFrom;
	}

	/**
	 * strEstimateValidDateFromを設定します。
	 * @param strEstimateValidDateFrom strEstimateValidDateFrom
	 */
	public void setStrEstimateValidDateFrom(
			final String strEstimateValidDateFrom) {
		this.strEstimateValidDateFrom = strEstimateValidDateFrom;
	}

	/**
	 * strEstimateValidDateToを取得します。
	 * @return strEstimateValidDateTo
	 */
	public String getStrEstimateValidDateTo() {
		return strEstimateValidDateTo;
	}

	/**
	 * strEstimateValidDateToを設定します。
	 * @param strEstimateValidDateTo strEstimateValidDateTo
	 */
	public void setStrEstimateValidDateTo(final String strEstimateValidDateTo) {
		this.strEstimateValidDateTo = strEstimateValidDateTo;
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
	 * cursorを取得します。
	 * @return cursor
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * cursorを設定します。
	 * @param cursor cursor
	 */
	public void setCursor(final String cursor) {
		this.cursor = cursor;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
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
