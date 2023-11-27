/*
 * Created on 2008/10/10
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 消込入力一覧 Formクラス(カスタマイズ)
 * @author tosco
 */
public final class EraserCsmListForm extends AbstractSearchForm {

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
	// 検索用.消込入力

	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：請求先コード */
	private String srhVenderCd;

	/** 検索入力：請求先名称 */
	private String srhVenderName1;

	/** 検索入力：消込完了日付FROM */
	private String srhEraserCompleteDateFrom;

	/** 検索入力：消込完了日付TO */
	private String srhEraserCompleteDateTo;

	/** 検索入力：出力区分（消込残高） */
	private String srhCreditEraserAmountDivi;

	/** 検索入力：消込状態(新規消込) */
	private boolean srhStatusNew;

	/** 検索入力：消込状態(承認処理) */
	private boolean srhStatusApprove;

	/** 検索入力：消込状態(消込完了) */
	private boolean srhStatusEraser;

	/** 帳票Excel用検索条件 */
	private ClaimEraserCsmListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// 一覧用.消込入力

	//

	/** リスト */
	private List<ClaimEraserCsmList> searchList;

	/**
	 * コンストラクタ.所属マスタ
	 */
	public EraserCsmListForm() {
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
		return ClaimEraserCsmListPagerCondition.class;
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

		if ("search".equals(getOp())) {
			/** 検索入力：出力区分（消込残高） */
			setSrhCreditEraserAmountDivi(null);
			/** 検索入力：消込状態(新規消込) */
			setSrhStatusNew(false);
			/** 検索入力：消込状態(承認処理) */
			setSrhStatusApprove(false);
			/** 検索入力：消込状態(消込完了) */
			setSrhStatusEraser(false);
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
			setSearchList(new ArrayList<ClaimEraserCsmList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			if (!this.isSrhStatusNew() && !this.isSrhStatusApprove()
					&& !this.isSrhStatusEraser()) {
				errors.add("", new ActionMessage("errors.selected", "消込状態"));
			}
		}

		return errors;
	}

	/**
	 * 初期化.消込入力
	 * 
	 */
	public void clear() {

		/* 検索リストのクリア */
		setSearchList(new ArrayList<ClaimEraserCsmList>());

		/* 検索入力：部署コード */
		setSrhOrganizationCd(null);
		/* 検索入力：部署名称 */
		setSrhOrganizationName(null);
		/* 検索入力：担当者コード */
		setSrhTantoCd(null);
		/* 検索入力：担当者名称 */
		setSrhTantoNm(null);
		/* 検索入力：請求先コード */
		setSrhVenderCd(null);
		/* 検索入力：請求先名称 */
		setSrhVenderName1(null);
		/* 検索入力：出力区分（消込残高） */
		setSrhCreditEraserAmountDivi(null);
		/* 検索入力：消込状態(新規消込) */
		setSrhStatusNew(false);
		/* 検索入力：消込状態(承認処理) */
		setSrhStatusApprove(false);
		/* 検索入力：消込状態(消込完了) */
		setSrhStatusEraser(false);
		/* 検索入力：消込完了日付FROM */
		setSrhEraserCompleteDateFrom(null);
		/* 検索入力：消込完了日付TO */
		setSrhEraserCompleteDateTo(null);
		/* 検索条件 */
		setCondition(null);
	}

	//
	// 検索入力.消込入力

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
	 * 
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力：請求先コード設定.
	 * @param srhVenderCd 請求先コード
	 * 
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：消込状態(新規消込)取得.
	 * @return boolean 消込状態(新規消込)
	 */
	public boolean isSrhStatusNew() {
		return srhStatusNew;
	}

	/**
	 * 検索入力：消込状態(新規消込)設定.
	 * @param srhStatusNew 消込状態(新規消込)
	 */
	public void setSrhStatusNew(final boolean srhStatusNew) {
		this.srhStatusNew = srhStatusNew;
	}

	/**
	 * 検索入力：消込状態(承認処理)取得.
	 * @return boolean 消込状態(承認処理)
	 */
	public boolean isSrhStatusApprove() {
		return srhStatusApprove;
	}

	/**
	 * 検索入力：消込状態(承認処理)設定.
	 * @param srhStatusApprove 消込状態(承認処理)
	 */
	public void setSrhStatusApprove(final boolean srhStatusApprove) {
		this.srhStatusApprove = srhStatusApprove;
	}

	/**
	 * 検索入力：消込状態(消込完了)取得.
	 * @return boolean 消込状態(消込完了)
	 */
	public boolean isSrhStatusEraser() {
		return srhStatusEraser;
	}

	/**
	 * 検索入力：消込状態(消込完了)設定.
	 * @param srhStatusEraser 消込状態(消込完了)
	 */
	public void setSrhStatusEraser(final boolean srhStatusEraser) {
		this.srhStatusEraser = srhStatusEraser;
	}

	/**
	 * 消込入力：searchListを取得します。
	 * @return searchList
	 */
	public List<ClaimEraserCsmList> getSearchList() {
		return searchList;
	}

	/**
	 * 消込入力：searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ClaimEraserCsmList> searchList) {
		this.searchList = searchList;
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
	 * srhCreditEraserAmountDiviを取得します。
	 * @return srhCreditEraserAmountDivi
	 */
	public String getSrhCreditEraserAmountDivi() {
		return srhCreditEraserAmountDivi;
	}

	/**
	 * srhCreditEraserAmountDiviを設定します。
	 * @param srhCreditEraserAmountDivi srhCreditEraserAmountDivi
	 */
	public void setSrhCreditEraserAmountDivi(
			final String srhCreditEraserAmountDivi) {
		this.srhCreditEraserAmountDivi = srhCreditEraserAmountDivi;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public ClaimEraserCsmListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(
			final ClaimEraserCsmListConditionForReport condition) {
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

	/**
	 * srhEraserCompleteDateFromを取得します。
	 * @return srhEraserCompleteDateFrom
	 */
	public String getSrhEraserCompleteDateFrom() {
		return srhEraserCompleteDateFrom;
	}

	/**
	 * srhEraserCompleteDateFromを設定します。
	 * @param srhEraserCompleteDateFrom srhEraserCompleteDateFrom
	 */
	public void setSrhEraserCompleteDateFrom(
			final String srhEraserCompleteDateFrom) {
		this.srhEraserCompleteDateFrom = srhEraserCompleteDateFrom;
	}

	/**
	 * srhEraserCompleteDateToを取得します。
	 * @return srhEraserCompleteDateTo
	 */
	public String getSrhEraserCompleteDateTo() {
		return srhEraserCompleteDateTo;
	}

	/**
	 * srhEraserCompleteDateToを設定します。
	 * @param srhEraserCompleteDateTo srhEraserCompleteDateTo
	 */
	public void setSrhEraserCompleteDateTo(final String srhEraserCompleteDateTo) {
		this.srhEraserCompleteDateTo = srhEraserCompleteDateTo;
	}
}
