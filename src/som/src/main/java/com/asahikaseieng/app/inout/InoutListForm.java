/*
 * Created on 2008/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordList;
import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordListPagerCondition;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordReportCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受払照会 Formクラス
 * @author tanaka
 */
public final class InoutListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	/** 品目コード */
	private String srhItemCd;

	/** 品目名称 */
	private String srhItemName;

	/** 他社コード1 */
	private String srhOtherCompanyCd1;

	/** ロケーション */
	private String srhLocationCd;

	/** ロケーション名称 */
	private String srhLocationName;

	/** 区分 */
	private java.math.BigDecimal srhInoutDivision;

	/** 日付FROM */
	private Timestamp srhInoutDateFrom;

	private String strSrhInoutDateFrom;

	/** 日付TO */
	private Timestamp srhInoutDateTo;

	private String strSrhInoutDateTo;

	/** 受払リスト */
	private List<InoutRecordList> searchList;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票用検索条件 */
	private InoutRecordReportCondition reportCondition;

	/**
	 * コンストラクタ.
	 */
	public InoutListForm() {
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
		return InoutRecordListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
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
			setSearchList(new ArrayList<InoutRecordList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhItemCd(null);
		setSrhItemName(null);
		setSrhOtherCompanyCd1(null);
		setSrhLocationCd(null);
		setSrhLocationName(null);
		setSrhInoutDivision(null);
		setSrhInoutDateFrom(null);
		setStrSrhInoutDateFrom(null);
		setSrhInoutDateTo(null);
		setStrSrhInoutDateTo(null);
		setSearchList(new ArrayList<InoutRecordList>());
		setReportCondition(null);
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

	/* 品目名称 */
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
	public List<InoutRecordList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<InoutRecordList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhLocationCdを取得します。
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * srhLocationCdを設定します。
	 * @param srhLocationCd srhLocationCd
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * srhLocationNameを取得します。
	 * @return srhLocationName
	 */
	public String getSrhLocationName() {
		return srhLocationName;
	}

	/**
	 * srhLocationNameを設定します。
	 * @param srhLocationName srhLocationName
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
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
	 * srhInoutDivisionを取得します。
	 * @return srhInoutDivision
	 */
	public java.math.BigDecimal getSrhInoutDivision() {
		return srhInoutDivision;
	}

	/**
	 * srhInoutDivisionを設定します。
	 * @param srhInoutDivision srhInoutDivision
	 */
	public void setSrhInoutDivision(final java.math.BigDecimal srhInoutDivision) {
		this.srhInoutDivision = srhInoutDivision;
	}

	/**
	 * strSrhInoutDateFromを取得します。
	 * @return strSrhInoutDateFrom
	 */
	public String getStrSrhInoutDateFrom() {
		return strSrhInoutDateFrom;
	}

	/**
	 * strSrhInoutDateFromを設定します。
	 * @param strSrhInoutDateFrom strSrhInoutDateFrom
	 */
	public void setStrSrhInoutDateFrom(final String strSrhInoutDateFrom) {
		this.strSrhInoutDateFrom = strSrhInoutDateFrom;
	}

	/**
	 * strSrhInoutDateToを取得します。
	 * @return strSrhInoutDateTo
	 */
	public String getStrSrhInoutDateTo() {
		return strSrhInoutDateTo;
	}

	/**
	 * strSrhInoutDateToを設定します。
	 * @param strSrhInoutDateTo strSrhInoutDateTo
	 */
	public void setStrSrhInoutDateTo(final String strSrhInoutDateTo) {
		this.strSrhInoutDateTo = strSrhInoutDateTo;
	}

	/**
	 * srhInoutDateFromを設定します。
	 * @param srhInoutDateFrom srhInoutDateFrom
	 */
	public void setSrhInoutDateFrom(final Timestamp srhInoutDateFrom) {
		this.srhInoutDateFrom = srhInoutDateFrom;
	}

	/**
	 * srhInoutDateToを設定します。
	 * @param srhInoutDateTo srhInoutDateTo
	 */
	public void setSrhInoutDateTo(final Timestamp srhInoutDateTo) {
		this.srhInoutDateTo = srhInoutDateTo;
	}

	/**
	 * srhInoutDateFromを取得します。
	 * @return srhInoutDateFrom
	 */
	public Timestamp getSrhInoutDateFrom() {
		return srhInoutDateFrom;
	}

	/**
	 * srhInoutDateToを取得します。
	 * @return srhInoutDateTo
	 */
	public Timestamp getSrhInoutDateTo() {
		return srhInoutDateTo;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public InoutRecordReportCondition getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final InoutRecordReportCondition reportCondition) {
		this.reportCondition = reportCondition;
	}
}
