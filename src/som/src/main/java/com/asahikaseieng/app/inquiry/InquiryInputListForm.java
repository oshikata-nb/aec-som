/*
 * Created on 2008/02/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputList;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputListPagerCondition;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 棚卸入力 Formクラス
 * @author tanaka
 */
public final class InquiryInputListForm extends AbstractSearchForm {

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

	/* 棚卸準備処理日 */
	private java.sql.Timestamp srhCountDate;

	private String strSrhCountDate;

	/* ロケーションコード */
	private String srhLocationCd;

	/* ロケーション名称 */
	private String srhLocationName;

	/* 循環区分 */
	private String srhCountDivision;

	/* 棚卸区分名称 */
	private String[] srhCountDivisionValues;

	private String[] srhCountDivisionLabels;

	/* 品目コード */
	private String srhItemCd;

	/* 品目名称 */
	private String srhItemName;

	/* 他社コード1 */
	private String srhOtherCompanyCd1;

	/* 原料ロット番号/包装指図番号 */
	private String srhAliasLotNo;

	/* オーダー番号 */
	private String srhOderNo;

	/* リンクフラグ */
	private String srhLink;

	/* 区分 */
	private String unitDivisionUnit;

	private String unitDivisionOther;

	/* リスト */
	private List<InquiryInputList> searchList;

	/* EXCELダウンロードフラグ */
	private Boolean excelDownloadFlg;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/** 帳票Excel検索用 */
	private InquiryInputListConditionForReport reportCondition;

	/**
	 * コンストラクタ.
	 */
	public InquiryInputListForm() {
		super();
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
		return InquiryInputListPagerCondition.class;
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
			setSearchList(new ArrayList<InquiryInputList>());
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
		setSrhCountDate(AecDateUtils.getCurrentTimestamp());
		setStrSrhCountDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		setSrhLocationCd(null);
		setSrhLocationName(null);
		setSrhCountDivision(null);
		setSrhCountDivisionValues(null);
		setSrhCountDivisionLabels(null);
		setSrhItemCd(null);
		setSrhItemName(null);
		setSrhOtherCompanyCd1(null);
		setSrhAliasLotNo(null);
		setSrhOderNo(null);
		setSrhLink(null);
		setUnitDivisionUnit("SITANKA");
		setUnitDivisionOther("SONOTA");
		setSearchList(new ArrayList<InquiryInputList>());
		setDirtyFlg(false);
		setReportCondition(null);
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
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public Boolean getExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final Boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<InquiryInputList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<InquiryInputList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhAliasLotNoを取得します。
	 * @return srhAliasLotNo
	 */
	public String getSrhAliasLotNo() {
		return srhAliasLotNo;
	}

	/**
	 * srhAliasLotNoを設定します。
	 * @param srhAliasLotNo srhAliasLotNo
	 */
	public void setSrhAliasLotNo(final String srhAliasLotNo) {
		this.srhAliasLotNo = srhAliasLotNo;
	}

	/**
	 * srhCountDateを取得します。
	 * @return srhCountDate
	 */
	public java.sql.Timestamp getSrhCountDate() {
		return srhCountDate;
	}

	/**
	 * srhCountDateを設定します。
	 * @param srhCountDate srhCountDate
	 */
	public void setSrhCountDate(final java.sql.Timestamp srhCountDate) {
		this.srhCountDate = srhCountDate;
	}

	/**
	 * srhCountDivisionを取得します。
	 * @return srhCountDivision
	 */
	public String getSrhCountDivision() {
		return srhCountDivision;
	}

	/**
	 * srhCountDivisionを設定します。
	 * @param srhCountDivision srhCountDivision
	 */
	public void setSrhCountDivision(final String srhCountDivision) {
		this.srhCountDivision = srhCountDivision;
	}

	/**
	 * srhCountDivisionLabelsを取得します。
	 * @return srhCountDivisionLabels
	 */
	public String[] getSrhCountDivisionLabels() {
		return srhCountDivisionLabels;
	}

	/**
	 * srhCountDivisionLabelsを設定します。
	 * @param srhCountDivisionLabels srhCountDivisionLabels
	 */
	public void setSrhCountDivisionLabels(final String[] srhCountDivisionLabels) {
		this.srhCountDivisionLabels = srhCountDivisionLabels;
	}

	/**
	 * srhCountDivisionValuesを取得します。
	 * @return srhCountDivisionValues
	 */
	public String[] getSrhCountDivisionValues() {
		return srhCountDivisionValues;
	}

	/**
	 * srhCountDivisionValuesを設定します。
	 * @param srhCountDivisionValues srhCountDivisionValues
	 */
	public void setSrhCountDivisionValues(final String[] srhCountDivisionValues) {
		this.srhCountDivisionValues = srhCountDivisionValues;
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
	 * strSrhCountDateを取得します。
	 * @return strSrhCountDate
	 */
	public String getStrSrhCountDate() {
		return strSrhCountDate;
	}

	/**
	 * strSrhCountDateを設定します。
	 * @param strSrhCountDate strSrhCountDate
	 */
	public void setStrSrhCountDate(final String strSrhCountDate) {
		this.strSrhCountDate = strSrhCountDate;
	}

	/**
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
	}

	/**
	 * srhOderNoを取得します。
	 * @return srhOderNo
	 */
	public String getSrhOderNo() {
		return srhOderNo;
	}

	/**
	 * srhOderNoを設定します。
	 * @param srhOderNo srhOderNo
	 */
	public void setSrhOderNo(final String srhOderNo) {
		this.srhOderNo = srhOderNo;
	}

	/**
	 * unitDivisionOtherを取得します。
	 * @return unitDivisionOther
	 */
	public String getUnitDivisionOther() {
		return unitDivisionOther;
	}

	/**
	 * unitDivisionOtherを設定します。
	 * @param unitDivisionOther unitDivisionOther
	 */
	public void setUnitDivisionOther(final String unitDivisionOther) {
		this.unitDivisionOther = unitDivisionOther;
	}

	/**
	 * unitDivisionUnitを取得します。
	 * @return unitDivisionUnit
	 */
	public String getUnitDivisionUnit() {
		return unitDivisionUnit;
	}

	/**
	 * unitDivisionUnitを設定します。
	 * @param unitDivisionUnit unitDivisionUnit
	 */
	public void setUnitDivisionUnit(final String unitDivisionUnit) {
		this.unitDivisionUnit = unitDivisionUnit;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public InquiryInputListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final InquiryInputListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}
}
