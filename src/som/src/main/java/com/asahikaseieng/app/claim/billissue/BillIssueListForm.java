/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.billissue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssueList;
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssuePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 請求書発行 Formクラス
 * @author tosco
 */
public final class BillIssueListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.bill.issue.list"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.bill.issue.list"));
	}

	//
	// 検索用.請求書発行

	//

	/** 検索入力：部門コード */
	private String srhSectionCd;

	/** 検索入力：部門名称 */
	private String srhSectionName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：請求先コード */
	private String srhVenderCd;

	/** 検索入力：請求先名称 */
	private String srhVenderName;

	/** 検索入力：締め日 */
	private String srhClosingDate;

	/** 検索入力：請求番号FROM */
	private String srhClaimNoFrom;

	/** 検索入力：請求番号TO */
	private String srhClaimNoTo;

	/** 検索入力：請求書発行区分 */
	private boolean srhBillIssueFlg;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

	/** 検索入力：出力区分(請求残高があるもの) */
	private boolean srhBalanceDivision;

	/** 検索入力：出力区分(取引があるもの) */
	private boolean srhDealingDivision;

	/** 検索入力：対象区分 */
	private BigDecimal srhNormalTemp;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票Excel用検索条件 */
	private BillIssueListConditionForReport condition;

	//
	// 一覧用.請求書発行
	//

	/** リスト */
	private List<BillIssueList> searchList;

	/**
	 * コンストラクタ.請求書発行
	 */
	public BillIssueListForm() {
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
		return BillIssuePagerCondition.class;
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
		if ("search".equals(getOp()) || "report".equals(getOp())) {
			/* チェックボックスの初期化 */
			// 検索入力：請求書発行区分
			setSrhBillIssueFlg(false);
			// 検索入力：通常処理分
			setSrhNormalFlg(false);
			// 検索入力：仮締処理分
			setSrhTempClosingFlg(false);
			// 検索入力：出力区分
			setSrhBalanceDivision(false);
			setSrhDealingDivision(false);

			// 検索結果一覧の請求書発行フラグを初期化
			for (int i = 0; i < searchList.size(); i++) {
				BillIssueList bean = searchList.get(i);
				bean.setBillIssueFlg(false);
			}
		}

		// Excelダウンロードフラグ
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
			setSearchList(new ArrayList<BillIssueList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// if (!isSrhNormalFlg() && !isSrhTempClosingFlg()) {
			// // 対象区分を選択して下さい。
			// errors.add("srhNormalFlg",
			// new ActionMessage("errors.targetkbn"));
			// }
		}
		return errors;
	}

	/**
	 * 初期化.請求書発行
	 */
	public void clear() {

		/* 検索リストのクリア */
		setSearchList(new ArrayList<BillIssueList>());

		/* 検索入力：部門コード */
		setSrhSectionCd(null);
		/* 検索入力：部門名称 */
		setSrhSectionName(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：担当者名称
		setSrhTantoNm(null);
		/* 検索入力：請求先コード */
		setSrhVenderCd(null);
		/* 検索入力：請求先名称 */
		setSrhVenderName(null);
		/* 検索入力：請求先名称 */
		setSrhClosingDate(null);
		/* 検索入力：請求書発行区分 */
		setSrhBillIssueFlg(false);
		/* 検索入力：出力区分 */
		setSrhBalanceDivision(false);
		setSrhDealingDivision(false);
		/* 検索入力：通常処理分 */
		setSrhNormalFlg(false);
		/* 検索入力：仮締処理分 */
		setSrhTempClosingFlg(false);

		/* 請求書発行区分 */
		setSrhBillIssueFlg(false);
		/* 通常処理分 */
		setSrhNormalFlg(false);
		/* 仮締処理分 */
		setSrhTempClosingFlg(false);
		// 検索入力：対象区分
		setSrhNormalTemp(new BigDecimal("0"));
		/* 検索条件 */
		setCondition(null);
	}

	/**
	 * 請求書発行一覧表：searchListを取得します。
	 * 
	 * @return searchList
	 */
	public List<BillIssueList> getSearchList() {
		return searchList;
	}

	/**
	 * 請求書発行一覧表：searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<BillIssueList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.請求書発行

	//

	/**
	 * 検索入力：部門コード取得.
	 * @return srhSectionCd
	 */
	public String getSrhSectionCd() {
		return this.srhSectionCd;
	}

	/**
	 * 検索入力：部門コード設定.
	 * @param srhSectionCd sectionCd
	 */
	public void setSrhSectionCd(final String srhSectionCd) {
		this.srhSectionCd = srhSectionCd;
	}

	/**
	 * 検索入力：部門名称取得.
	 * @return srhSectionName
	 */
	public String getSrhSectionName() {
		return this.srhSectionName;
	}

	/**
	 * 検索入力：部門名称設定.
	 * @param srhSectionName srhSectionName
	 */
	public void setSrhSectionName(final String srhSectionName) {
		this.srhSectionName = srhSectionName;
	}

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
	 * 検索入力：請求先FROM取得.
	 * @return String 請求先FROM
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力：請求先FROM設定.
	 * @param srhVenderCd 請求先FROM
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：請求先TO取得.
	 * @return String 請求先TO
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 検索入力：請求先TO設定.
	 * @param srhVenderName 請求先TO
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}

	/**
	 * 検索入力：請求番号FROM取得.
	 * @return String 請求番号FROM
	 */
	public String getSrhClaimNoFrom() {
		return srhClaimNoFrom;
	}

	/**
	 * 検索入力：請求番号FROM設定.
	 * @param srhClaimNoFrom 請求番号FROM
	 */
	public void setSrhClaimNoFrom(final String srhClaimNoFrom) {
		this.srhClaimNoFrom = srhClaimNoFrom;
	}

	/**
	 * 検索入力：請求番号TO取得.
	 * @return String 請求番号TO
	 */
	public String getSrhClaimNoTo() {
		return srhClaimNoTo;
	}

	/**
	 * 検索入力：請求番号TO設定.
	 * @param srhClaimNoTo 請求番号TO
	 */
	public void setSrhClaimNoTo(final String srhClaimNoTo) {
		this.srhClaimNoTo = srhClaimNoTo;
	}

	/**
	 * 検索入力：請求書発行区分取得.
	 * @return boolean 請求書発行区分
	 */
	public boolean isSrhBillIssueFlg() {
		return srhBillIssueFlg;
	}

	/**
	 * 検索入力：請求書発行区分設定
	 * @param srhBillIssueFlg 請求書発行区分
	 */
	public void setSrhBillIssueFlg(final boolean srhBillIssueFlg) {
		this.srhBillIssueFlg = srhBillIssueFlg;
	}

	/**
	 * 検索入力：通常処理分取得.
	 * @return boolean 通常処理分
	 */
	public boolean isSrhNormalFlg() {
		return srhNormalFlg;
	}

	/**
	 * 検索入力：通常処理分設定
	 * @param srhNormalFlg 通常処理分
	 */
	public void setSrhNormalFlg(final boolean srhNormalFlg) {
		this.srhNormalFlg = srhNormalFlg;
	}

	/**
	 * 検索入力：仮締処理分取得.
	 * @return boolean 仮締処理分
	 */
	public boolean isSrhTempClosingFlg() {
		return srhTempClosingFlg;
	}

	/**
	 * 検索入力：仮締処理分設定
	 * @param srhTempClosingFlg 仮締処理分
	 */
	public void setSrhTempClosingFlg(final boolean srhTempClosingFlg) {
		this.srhTempClosingFlg = srhTempClosingFlg;
	}

	//
	// インスタンスメソッド.請求書発行
	//

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 締め年月を取得します。
	 * 
	 * @return srhClosingMonth
	 */
	public String getSrhClosingDate() {
		return srhClosingDate;
	}

	/**
	 * 締め日を設定します。
	 * 
	 * @param srhClosingDate 締め日
	 */
	public void setSrhClosingDate(final String srhClosingDate) {
		this.srhClosingDate = srhClosingDate;
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
	 * conditionを取得します。
	 * @return condition
	 */
	public BillIssueListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final BillIssueListConditionForReport condition) {
		this.condition = condition;
	}

	/**
	 * srhNormalTempを取得します。
	 * @return srhNormalTemp
	 */
	public BigDecimal getSrhNormalTemp() {
		return srhNormalTemp;
	}

	/**
	 * srhNormalTempを設定します。
	 * @param srhNormalTemp srhNormalTemp
	 */
	public void setSrhNormalTemp(final BigDecimal srhNormalTemp) {
		this.srhNormalTemp = srhNormalTemp;
	}

	/**
	 * srhBalanceDivisionを取得します。
	 * @return srhBalanceDivision
	 */
	public boolean isSrhBalanceDivision() {
		return srhBalanceDivision;
	}

	/**
	 * srhBalanceDivisionを設定します。
	 * @param srhBalanceDivision srhBalanceDivision
	 */
	public void setSrhBalanceDivision(final boolean srhBalanceDivision) {
		this.srhBalanceDivision = srhBalanceDivision;
	}

	/**
	 * srhDealingDivisionを取得します。
	 * @return srhDealingDivision
	 */
	public boolean isSrhDealingDivision() {
		return srhDealingDivision;
	}

	/**
	 * srhDealingDivisionを設定します。
	 * @param srhDealingDivision srhDealingDivision
	 */
	public void setSrhDealingDivision(final boolean srhDealingDivision) {
		this.srhDealingDivision = srhDealingDivision;
	}
}
