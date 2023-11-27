/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraserlistforreport;

import java.util.Date;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * BalancePagerConditionクラス.入金消込一覧
 * @author tosco
 */
public class ClaimEraserListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserListConditionForReport() {
	}

	//
	// 検索入力用.section
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

	/** 検索入力：新規消込チェック */
	private boolean srhDataCheckNew;

	/** 検索入力：消込結果チェック */
	private boolean srhDataCheckEraser;

	/** 検索入力：消込完了日付FROM */
	private String srhEraserCompleteDateFrom;

	/** 検索入力：消込完了日付TO */
	private String srhEraserCompleteDateTo;

	/** 検索入力：消込日付FROM */
	private String srhEraserDateFrom;

	/** 検索入力：消込日付TO */
	private String srhEraserDateTo;

	/** 出力区分 */
	private String srhOutputDivision;

	/** 消込完了日付FROM */
	private Date eraserCompleteDateFrom;

	/** 消込完了日付TO */
	private Date eraserCompleteDateTo;

	/** 消込日付FROM */
	private Date eraserDateFrom;

	/** 消込日付TO */
	private Date eraserDateTo;

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
		this.srhSectionCd = AecTextUtils.likeFilter(srhSectionCd);
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
		this.srhSectionName = AecTextUtils.likeFilter(srhSectionName);
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
	 * @param srhTantoCd 担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
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
	 * @param srhTantoNm 担当者名称
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = AecTextUtils.likeFilter(srhTantoNm);
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
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
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
		this.srhVenderName = AecTextUtils.likeFilter(srhVenderName);
	}

	/**
	 * 検索入力：新規消込チェック取得.
	 * @return String 新規消込チェック
	 */
	public boolean isSrhDataCheckNew() {
		return srhDataCheckNew;
	}

	/**
	 * 検索入力：新規消込チェック設定.
	 * @param srhDataCheckNew 新規消込チェック
	 */
	public void setSrhDataCheckNew(final boolean srhDataCheckNew) {
		this.srhDataCheckNew = srhDataCheckNew;
	}

	/**
	 * 検索入力：消込結果チェック取得.
	 * @return String 消込結果チェック
	 */
	public boolean isSrhDataCheckEraser() {
		return srhDataCheckEraser;
	}

	/**
	 * 検索入力：消込結果チェック設定.
	 * @param srhDataCheckEraser 消込結果チェック
	 */
	public void setSrhDataCheckEraser(final boolean srhDataCheckEraser) {
		this.srhDataCheckEraser = srhDataCheckEraser;
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
	 * 消込日付FROM取得.
	 * @return Date 消込日付FROM
	 */
	public Date getEraserDateFrom() {
		return eraserDateFrom;
	}

	/**
	 * 消込日付TO取得.
	 * @return Date 消込日付TO
	 */
	public Date getEraserDateTo() {
		return eraserDateTo;
	}

	/**
	 * eraserCompleteDateFromを取得します。
	 * @return eraserCompleteDateFrom
	 */
	public Date getEraserCompleteDateFrom() {
		return eraserCompleteDateFrom;
	}

	/**
	 * eraserCompleteDateFromを設定します。
	 * @param eraserCompleteDateFrom eraserCompleteDateFrom
	 */
	public void setEraserCompleteDateFrom(final Date eraserCompleteDateFrom) {
		this.eraserCompleteDateFrom = eraserCompleteDateFrom;
	}

	/**
	 * eraserCompleteDateToを取得します。
	 * @return eraserCompleteDateTo
	 */
	public Date getEraserCompleteDateTo() {
		return eraserCompleteDateTo;
	}

	/**
	 * eraserCompleteDateToを設定します。
	 * @param eraserCompleteDateTo eraserCompleteDateTo
	 */
	public void setEraserCompleteDateTo(final Date eraserCompleteDateTo) {
		this.eraserCompleteDateTo = eraserCompleteDateTo;
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

	/**
	 * eraserDateFromを設定します。
	 * @param eraserDateFrom eraserDateFrom
	 */
	public void setEraserDateFrom(final Date eraserDateFrom) {
		this.eraserDateFrom = eraserDateFrom;
	}

	/**
	 * eraserDateToを設定します。
	 * @param eraserDateTo eraserDateTo
	 */
	public void setEraserDateTo(final Date eraserDateTo) {
		this.eraserDateTo = eraserDateTo;
	}
}
