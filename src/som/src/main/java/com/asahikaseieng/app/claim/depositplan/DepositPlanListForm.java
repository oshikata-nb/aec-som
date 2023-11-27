/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.depositplan;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanList;
import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 入金予定一覧表 Formクラス
 * @author tosco
 */
public final class DepositPlanListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

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

	//
	// 検索用.入金予定一覧表

	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：出力区分 */
	private String srhOutputDivision;

	/** 検索入力：請求先区分 */
	private String venderDivision;

	/** 検索入力：請求先コード */
	private String srhVenderCd;

	/** 検索入力：請求先名称 */
	private String srhVenderName1;

	/** 検索入力：請求日付FROM */
	private String srhCreditDateFrom;

	/** 検索入力：請求日付TO */
	private String srhCreditDateTo;

	/** 検索入力：入金日付FROM */
	private String srhCreditScheduledDateFrom;

	/** 検索入力：入金日付TO */
	private String srhCreditScheduledDateTo;

	/** 検索入力：銀行マスタコード */
	private String srhBankMasterCd;

	/** 検索入力：銀行マスタ名 */
	private String srhBankMasterName;

	/** 検索入力：入金区分 */
	private String srhCreditDivision;

	/** 検索入力：入金区分リスト */
	private List<ComboBoxItems> srhCreditList;

	/* 帳票Excel用検索条件 */
	private DepositPlanListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// 一覧用.入金予定一覧表
	//

	/** リスト */
	private List<DepositPlanList> searchList;

	/**
	 * コンストラクタ.入金予定一覧Form
	 */
	public DepositPlanListForm() {
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
		return DepositPlanListPagerCondition.class;
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
			setSearchList(new ArrayList<DepositPlanList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.所属マスタ
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<DepositPlanList>());

		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：部署名称
		setSrhOrganizationName(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：担当者名称
		setSrhTantoNm(null);
		// 検索入力：出力区分
		setSrhOutputDivision(null);
		// 検索入力：請求先区分
		setVenderDivision("TS");
		// 検索入力：請求先コード
		setSrhVenderCd(null);
		// 検索入力：請求先名称
		setSrhVenderName1(null);
		// 検索入力：請求日付FROM
		setSrhCreditDateFrom(null);
		// 検索入力：請求日付TO
		setSrhCreditDateTo(null);
		// 検索入力：入金日付FROM
		setSrhCreditScheduledDateFrom(null);
		// 検索入力：入金日付TO
		setSrhCreditScheduledDateTo(null);
		// 検索入力：銀行コード
		setSrhBankMasterCd(null);
		// 検索入力：銀行名
		setSrhBankMasterName(null);
		// 検索入力：入金区分
		setSrhCreditDivision(null);
		// 検索条件
		setCondition(null);
	}

	/**
	 * 入金予定一覧表：searchListを取得
	 * 
	 * @return searchList
	 */
	public List<DepositPlanList> getSearchList() {
		return searchList;
	}

	/**
	 * 入金予定一覧表：searchListを設定
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<DepositPlanList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.入金予定一覧表

	//

	/**
	 * 検索入力：担当者コードを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * 検索入力：担当者コードを設定します。
	 * @param srhTantoCd 検索入力：担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}

	/**
	 * 検索入力：担当者名称を取得します。
	 * @return srhTantoNm
	 */
	public String getSrhTantoNm() {
		return srhTantoNm;
	}

	/**
	 * 検索入力：担当者名称を設定します。
	 * @param srhTantoNm 検索入力：担当者名称
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = srhTantoNm;
	}

	/**
	 * 検索入力：請求先コード取得.
	 * @return String 請求先コード
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力：請求先コード設定.
	 * @param srhVenderCd 請求先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：入金日付FROM取得.
	 * @return Date 入金日付FROM
	 */
	public String getSrhCreditDateFrom() {
		return srhCreditDateFrom;
	}

	/**
	 * 検索入力：入金日付FROM設定.
	 * @param date 入金日付FROM
	 */
	public void setSrhCreditDateFrom(final String date) {
		this.srhCreditDateFrom = date;
	}

	/**
	 * 検索入力：入金日付TO取得.
	 * @return Date 入金日付TO
	 */
	public String getSrhCreditDateTo() {
		return srhCreditDateTo;
	}

	/**
	 * 検索入力：入金日付TO設定.
	 * @param srhCreditDateTo 入金日付TO
	 */
	public void setSrhCreditDateTo(final String srhCreditDateTo) {
		this.srhCreditDateTo = srhCreditDateTo;
	}

	/**
	 * 検索入力：出力区分取得.
	 * @return String 出力区分
	 * 
	 */
	public String getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 検索入力：出力区分設定.
	 * @param srhOutputDivision 出力区分
	 * 
	 */
	public void setSrhOutputDivision(final String srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	/**
	 * 検索入力：入金区分を取得
	 * @return srhCreditDivision
	 */
	public String getSrhCreditDivision() {
		return srhCreditDivision;
	}

	/**
	 * 検索入力：入金区分を設定
	 * @param srhCreditDivision 入金区分
	 */
	public void setSrhCreditDivision(final String srhCreditDivision) {
		this.srhCreditDivision = srhCreditDivision;
	}

	/**
	 * 検索入力：入金区分リストを取得します。
	 * @return srhCreditList
	 */
	public List<ComboBoxItems> getSrhCreditList() {
		return srhCreditList;
	}

	/**
	 * 検索入力：入金区分リストを設定します。
	 * @param srhCreditList 検索入力：入金区分リスト
	 */
	public void setSrhCreditList(final List<ComboBoxItems> srhCreditList) {
		this.srhCreditList = srhCreditList;
	}

	//
	// インスタンスメソッド.入金予定一覧
	//

	/**
	 * dirtyFlgを取得
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * srhBankMasterCdを取得します。
	 * @return srhBankMasterCd
	 */
	public String getSrhBankMasterCd() {
		return srhBankMasterCd;
	}

	/**
	 * srhBankMasterCdを設定します。
	 * @param srhBankMasterCd srhBankMasterCd
	 */
	public void setSrhBankMasterCd(final String srhBankMasterCd) {
		this.srhBankMasterCd = srhBankMasterCd;
	}

	/**
	 * srhBankMasterNameを取得します。
	 * @return srhBankMasterName
	 */
	public String getSrhBankMasterName() {
		return srhBankMasterName;
	}

	/**
	 * srhBankMasterNameを設定します。
	 * @param srhBankMasterName srhBankMasterName
	 */
	public void setSrhBankMasterName(final String srhBankMasterName) {
		this.srhBankMasterName = srhBankMasterName;
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
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * srhOrganizationNameを取得します。
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}

	/**
	 * srhOrganizationNameを設定します。
	 * @param srhOrganizationName srhOrganizationName
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
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
	 * srhCreditScheduledDateFromを取得します。
	 * @return srhCreditScheduledDateFrom
	 */
	public String getSrhCreditScheduledDateFrom() {
		return srhCreditScheduledDateFrom;
	}

	/**
	 * srhCreditScheduledDateFromを設定します。
	 * @param srhCreditScheduledDateFrom srhCreditScheduledDateFrom
	 */
	public void setSrhCreditScheduledDateFrom(
			final String srhCreditScheduledDateFrom) {
		this.srhCreditScheduledDateFrom = srhCreditScheduledDateFrom;
	}

	/**
	 * srhCreditScheduledDateToを取得します。
	 * @return srhCreditScheduledDateTo
	 */
	public String getSrhCreditScheduledDateTo() {
		return srhCreditScheduledDateTo;
	}

	/**
	 * srhCreditScheduledDateToを設定します。
	 * @param srhCreditScheduledDateTo srhCreditScheduledDateTo
	 */
	public void setSrhCreditScheduledDateTo(
			final String srhCreditScheduledDateTo) {
		this.srhCreditScheduledDateTo = srhCreditScheduledDateTo;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public DepositPlanListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final DepositPlanListConditionForReport condition) {
		this.condition = condition;
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
}
