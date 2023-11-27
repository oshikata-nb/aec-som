/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsList;
import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 販売条件一覧 Formクラス.
 * @author t0011036
 */
public final class SalesTermsListForm extends AbstractSearchForm {

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

	/* 取引先区分 */
	private String srhVenderDivision;

	/* 得意先コード */
	private String srhVenderCd;

	/* 得意先名称 */
	private String srhVenderName1;

	/* 納入先コード */
	private String srhDeliveryCd;

	/* 納入先名称 */
	private String srhDeliveryName1;

	/* 品目コード */
	private String srhItemCd;

	/* 品目名称 */
	private String srhItemName;

	/* 他社コード1 */
	private String srhOtherCompanyCd1;

	/* 荷姿 */
	private String srhStyleOfPacking;

	/* 販売条件リスト */
	private List<SalesTermsList> searchList;

	/* 帳票Excel用検索条件 */
	private SalesTermsListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsListForm() {
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
		return SalesTermsListPagerCondition.class;
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
			setSearchList(new ArrayList<SalesTermsList>());
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
		setSrhVenderDivision("TS"); /* 得意先 */
		setSrhVenderCd(null);
		setSrhVenderName1(null);
		setSrhDeliveryCd(null);
		setSrhDeliveryName1(null);
		setSrhItemCd(null);
		setSrhItemName(null);
		setSrhOtherCompanyCd1(null);
		setSrhStyleOfPacking(null);
		setSearchList(new ArrayList<SalesTermsList>());
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
	 * srhDeliveryCdを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * srhDeliveryCdを設定します。
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * srhDeliveryName1を取得します。
	 * @return srhDeliveryName1
	 */
	public String getSrhDeliveryName1() {
		return srhDeliveryName1;
	}

	/**
	 * srhDeliveryName1を設定します。
	 * @param srhDeliveryName1 srhDeliveryName1
	 */
	public void setSrhDeliveryName1(final String srhDeliveryName1) {
		this.srhDeliveryName1 = srhDeliveryName1;
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
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<SalesTermsList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<SalesTermsList> searchList) {
		this.searchList = searchList;
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
	 * conditionを取得します。
	 * @return condition
	 */
	public SalesTermsListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final SalesTermsListConditionForReport condition) {
		this.condition = condition;
	}
}
