/*
 * Created on 2008/07/01
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
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 消込入力一覧 Formクラス
 * @author tosco
 */
public final class EraserListForm extends AbstractSearchForm {

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

	/** 検索入力：消込日付FROM */
	private String srhEraserDateFrom;

	/** 検索入力：消込日付TO */
	private String srhEraserDateTo;

	/** 検索入力：データ区分(新規消込) */
	private boolean srhDataDivNew;

	/** 検索入力：データ区分(消込結果) */
	private boolean srhDataDivEraser;

	/** 出力区分 */
	private String srhOutputDivision;

	/** 担当者ID */
	private String tantoCd;

	//
	// 一覧用.消込入力

	//

	/** リスト */
	private List<ClaimEraserList> searchList;

	/**
	 * コンストラクタ.所属マスタ
	 */
	public EraserListForm() {
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
		return ClaimEraserListPagerCondition.class;
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
			/** 検索入力：データ区分(新規消込) */
			setSrhDataDivNew(false);
			/** 検索入力：データ区分(消込結果) */
			setSrhDataDivEraser(false);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<ClaimEraserList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			if (!this.isSrhDataDivNew() && !this.isSrhDataDivEraser()) {
				errors.add("", new ActionMessage("errors.required", "データ区分"));
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
		setSearchList(new ArrayList<ClaimEraserList>());

		/* 検索入力：部門コード */
		setSrhSectionCd(null);
		/* 検索入力：部門名称 */
		setSrhSectionName(null);
		/* 検索入力：担当者コード */
		setSrhTantoCd(null);
		/* 検索入力：担当者名称 */
		setSrhTantoNm(null);
		/* 検索入力：請求先コード */
		setSrhVenderCd(null);
		/* 検索入力：請求先名称 */
		setSrhVenderName(null);
		/* 検索入力：データ区分(新規消込) */
		setSrhDataDivNew(false);
		/* 検索入力：データ区分(消込結果) */
		setSrhDataDivEraser(false);
		/* 検索入力：消込日付FROM */
		setSrhEraserDateFrom(null);
		/* 検索入力：消込日付TO */
		setSrhEraserDateTo(null);
		/* 検索入力：出力区分 */
		setSrhOutputDivision(null);
		/* 検索入力：担当者ID */
		setTantoCd(null);

	}

	//
	// 検索入力.消込入力

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
	 * 検索入力：請求先名称取得.
	 * @return String 請求先名称
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 検索入力：請求先名称設定.
	 * @param srhVenderName 請求先名称
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}

	/**
	 * 検索入力：データ区分(新規消込)取得.
	 * @return boolean データ区分(新規消込)
	 */
	public boolean isSrhDataDivNew() {
		return srhDataDivNew;
	}

	/**
	 * 検索入力：データ区分(新規消込)設定.
	 * @param srhDataDivNew データ区分(新規消込)
	 */
	public void setSrhDataDivNew(final boolean srhDataDivNew) {
		this.srhDataDivNew = srhDataDivNew;
	}

	/**
	 * 検索入力：データ区分(消込結果)取得.
	 * @return boolean データ区分(消込結果)
	 */
	public boolean isSrhDataDivEraser() {
		return srhDataDivEraser;
	}

	/**
	 * 検索入力：データ区分(消込結果)設定.
	 * @param srhDataDivEraser データ区分(消込結果)
	 */
	public void setSrhDataDivEraser(final boolean srhDataDivEraser) {
		this.srhDataDivEraser = srhDataDivEraser;
	}

	/**
	 * 検索入力：消込日付FROM取得.
	 * @return String 消込日付FROM
	 */
	public String getSrhEraserDateFrom() {
		return srhEraserDateFrom;
	}

	/**
	 * 検索入力：消込日付FROM設定.
	 * @param srhEraserDateFrom 消込日付FROM
	 */
	public void setSrhEraserDateFrom(final String srhEraserDateFrom) {
		this.srhEraserDateFrom = srhEraserDateFrom;
	}

	/**
	 * 検索入力：消込日付TO取得.
	 * @return String 消込日付TO
	 */
	public String getSrhEraserDateTo() {
		return srhEraserDateTo;
	}

	/**
	 * 検索入力：消込日付TO設定.
	 * @param srhEraserDateTo 消込日付TO
	 */
	public void setSrhEraserDateTo(final String srhEraserDateTo) {
		this.srhEraserDateTo = srhEraserDateTo;
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
	 * 担当者ID取得.
	 * @return String 担当者ID
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 担当者ID設定.
	 * @param tantoCd 担当者ID
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 消込入力：searchListを取得します。
	 * 
	 * @return searchList
	 */
	public List<ClaimEraserList> getSearchList() {
		return searchList;
	}

	/**
	 * 消込入力：searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ClaimEraserList> searchList) {
		this.searchList = searchList;
	}

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

}
