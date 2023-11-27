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
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.estimatelist.EstimateList;
import com.asahikaseieng.dao.nonentity.estimatelist.EstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 見積/単価一覧 Formクラス.
 * @author t0011036
 */
public final class EstimateListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	/* 見積番号 */
	private String srhEstimateNo;

	/* 見積入力日(FROM) */
	private Timestamp srhEstimateInputDateFrom;

	private String strSrhEstimateInputDateFrom;

	/* 見積入力日(TO) */
	private Timestamp srhEstimateInputDateTo;

	private String strSrhEstimateInputDateTo;

	/* 取引先区分 */
	private String srhVenderDivision;

	/* 得意先コード */
	private String srhVenderCd;

	/* 得意先名称 */
	private String srhVenderName1;

	/* 品目コード */
	private String srhItemCd;

	/* 品目名称 */
	private String srhItemName;

	/* 他社コード1 */
	private String srhOtherCompanyCd1;

	/* 荷姿 */
	private String srhStyleOfPacking;

	/* 見積有効期限(FROM) */
	private Timestamp srhEstimateValidDateFrom;

	private String strSrhEstimateValidDateFrom;

	/* 見積有効期限(TO) */
	private Timestamp srhEstimateValidDateTo;

	private String strSrhEstimateValidDateTo;

	/* ステータス */
	private BigDecimal srhEstimateStatus;

	/* 見積/単価リスト */
	private List<EstimateList> searchList;

	/* 帳票Excel用検索条件 */
	private EstimateListConditionForReport condition;

	/* EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public EstimateListForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return EstimateListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			/* 初期化 */
			clear();
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
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<EstimateList>());
		}

		if ("search".equals(getOp()) || "update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhEstimateNo(null);
		setSrhEstimateInputDateFrom(null);
		setStrSrhEstimateInputDateFrom(null);
		setSrhEstimateInputDateTo(null);
		setStrSrhEstimateInputDateTo(null);
		setSrhVenderDivision("TS"); /* 得意先 */
		setSrhVenderCd(null);
		setSrhVenderName1(null);
		setSrhItemCd(null);
		setSrhItemName(null);
		setSrhOtherCompanyCd1(null);
		setSrhStyleOfPacking(null);
		setSrhEstimateValidDateFrom(null);
		setStrSrhEstimateValidDateFrom(null);
		setSrhEstimateValidDateTo(null);
		setStrSrhEstimateValidDateTo(null);
		setSrhEstimateStatus(null);
		setSearchList(new ArrayList<EstimateList>());
		setCondition(null);
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
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<EstimateList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<EstimateList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhEstimateInputDateFromを取得します。
	 * @return srhEstimateInputDateFrom
	 */
	public Timestamp getSrhEstimateInputDateFrom() {
		return srhEstimateInputDateFrom;
	}

	/**
	 * srhEstimateInputDateFromを設定します。
	 * @param srhEstimateInputDateFrom srhEstimateInputDateFrom
	 */
	public void setSrhEstimateInputDateFrom(
			final Timestamp srhEstimateInputDateFrom) {
		this.srhEstimateInputDateFrom = srhEstimateInputDateFrom;
	}

	/**
	 * srhEstimateInputDateToを取得します。
	 * @return srhEstimateInputDateTo
	 */
	public Timestamp getSrhEstimateInputDateTo() {
		return srhEstimateInputDateTo;
	}

	/**
	 * srhEstimateInputDateToを設定します。
	 * @param srhEstimateInputDateTo srhEstimateInputDateTo
	 */
	public void setSrhEstimateInputDateTo(final Timestamp srhEstimateInputDateTo) {
		this.srhEstimateInputDateTo = srhEstimateInputDateTo;
	}

	/**
	 * srhEstimateNoを取得します。
	 * @return srhEstimateNo
	 */
	public String getSrhEstimateNo() {
		return srhEstimateNo;
	}

	/**
	 * srhEstimateNoを設定します。
	 * @param srhEstimateNo srhEstimateNo
	 */
	public void setSrhEstimateNo(final String srhEstimateNo) {
		this.srhEstimateNo = srhEstimateNo;
	}

	/**
	 * srhEstimateStatusを取得します。
	 * @return srhEstimateStatus
	 */
	public BigDecimal getSrhEstimateStatus() {
		return srhEstimateStatus;
	}

	/**
	 * srhEstimateStatusを設定します。
	 * @param srhEstimateStatus srhEstimateStatus
	 */
	public void setSrhEstimateStatus(final BigDecimal srhEstimateStatus) {
		this.srhEstimateStatus = srhEstimateStatus;
	}

	/**
	 * srhEstimateValidDateFromを取得します。
	 * @return srhEstimateValidDateFrom
	 */
	public Timestamp getSrhEstimateValidDateFrom() {
		return srhEstimateValidDateFrom;
	}

	/**
	 * srhEstimateValidDateFromを設定します。
	 * @param srhEstimateValidDateFrom srhEstimateValidDateFrom
	 */
	public void setSrhEstimateValidDateFrom(
			final Timestamp srhEstimateValidDateFrom) {
		this.srhEstimateValidDateFrom = srhEstimateValidDateFrom;
	}

	/**
	 * srhEstimateValidDateToを取得します。
	 * @return srhEstimateValidDateTo
	 */
	public Timestamp getSrhEstimateValidDateTo() {
		return srhEstimateValidDateTo;
	}

	/**
	 * srhEstimateValidDateToを設定します。
	 * @param srhEstimateValidDateTo srhEstimateValidDateTo
	 */
	public void setSrhEstimateValidDateTo(final Timestamp srhEstimateValidDateTo) {
		this.srhEstimateValidDateTo = srhEstimateValidDateTo;
	}

	/**
	 * srhItemCdを取得します。
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * srhItemCdを設定します。
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * srhItemNameを取得します。
	 * @return srhItemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * srhItemNameを設定します。
	 * @param srhItemName srhItemName
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * srhOtherCompanyCd1を取得します。
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * srhOtherCompanyCd1を設定します。
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * srhStyleOfPackingを取得します。
	 * @return srhStyleOfPacking
	 */
	public String getSrhStyleOfPacking() {
		return srhStyleOfPacking;
	}

	/**
	 * srhStyleOfPackingを設定します。
	 * @param srhStyleOfPacking srhStyleOfPacking
	 */
	public void setSrhStyleOfPacking(final String srhStyleOfPacking) {
		this.srhStyleOfPacking = srhStyleOfPacking;
	}

	/**
	 * srhVenderCdを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * srhVenderCdを設定します。
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * srhVenderDivisionを取得します。
	 * @return srhVenderDivision
	 */
	public String getSrhVenderDivision() {
		return srhVenderDivision;
	}

	/**
	 * srhVenderDivisionを設定します。
	 * @param srhVenderDivision srhVenderDivision
	 */
	public void setSrhVenderDivision(final String srhVenderDivision) {
		this.srhVenderDivision = srhVenderDivision;
	}

	/**
	 * srhVenderName1を取得します。
	 * @return srhVenderName1
	 */
	public String getSrhVenderName1() {
		return srhVenderName1;
	}

	/**
	 * srhVenderName1を設定します。
	 * @param srhVenderName1 srhVenderName1
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * strSrhEstimateInputDateFromを取得します。
	 * @return strSrhEstimateInputDateFrom
	 */
	public String getStrSrhEstimateInputDateFrom() {
		return strSrhEstimateInputDateFrom;
	}

	/**
	 * strSrhEstimateInputDateFromを設定します。
	 * @param strSrhEstimateInputDateFrom strSrhEstimateInputDateFrom
	 */
	public void setStrSrhEstimateInputDateFrom(
			final String strSrhEstimateInputDateFrom) {
		this.strSrhEstimateInputDateFrom = strSrhEstimateInputDateFrom;
	}

	/**
	 * strSrhEstimateInputDateToを取得します。
	 * @return strSrhEstimateInputDateTo
	 */
	public String getStrSrhEstimateInputDateTo() {
		return strSrhEstimateInputDateTo;
	}

	/**
	 * strSrhEstimateInputDateToを設定します。
	 * @param strSrhEstimateInputDateTo strSrhEstimateInputDateTo
	 */
	public void setStrSrhEstimateInputDateTo(
			final String strSrhEstimateInputDateTo) {
		this.strSrhEstimateInputDateTo = strSrhEstimateInputDateTo;
	}

	/**
	 * strSrhEstimateValidDateFromを取得します。
	 * @return strSrhEstimateValidDateFrom
	 */
	public String getStrSrhEstimateValidDateFrom() {
		return strSrhEstimateValidDateFrom;
	}

	/**
	 * strSrhEstimateValidDateFromを設定します。
	 * @param strSrhEstimateValidDateFrom strSrhEstimateValidDateFrom
	 */
	public void setStrSrhEstimateValidDateFrom(
			final String strSrhEstimateValidDateFrom) {
		this.strSrhEstimateValidDateFrom = strSrhEstimateValidDateFrom;
	}

	/**
	 * strSrhEstimateValidDateToを取得します。
	 * @return strSrhEstimateValidDateTo
	 */
	public String getStrSrhEstimateValidDateTo() {
		return strSrhEstimateValidDateTo;
	}

	/**
	 * strSrhEstimateValidDateToを設定します。
	 * @param strSrhEstimateValidDateTo strSrhEstimateValidDateTo
	 */
	public void setStrSrhEstimateValidDateTo(
			final String strSrhEstimateValidDateTo) {
		this.strSrhEstimateValidDateTo = strSrhEstimateValidDateTo;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public EstimateListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final EstimateListConditionForReport condition) {
		this.condition = condition;
	}
}
