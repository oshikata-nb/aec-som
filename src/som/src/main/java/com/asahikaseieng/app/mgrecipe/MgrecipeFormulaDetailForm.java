/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;

/**
 * 配合タブ詳細画面 Formクラス.
 * @author tosco
 */
public final class MgrecipeFormulaDetailForm extends AbstractMgrecipeForm {

	private static final long serialVersionUID = 1L;

	/** STEP_NO（工程順序） */
	private String stepNo;

	/** LINE_NO（サブステップ） */
	private String lineNo;

	/** 表示順 */
	private String seq;

	/** 工程順序 */
	private String proSeq;

	/** 配合量計 */
	private String sumQty;

	/** 単位（配合量計） */
	private String headUnitName;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 ラベル用 */
	private String formulaItemName;

	/** 配合量 */
	private String qty;

	/** 数値区分（配合量） */
	private String unitDivision;

	/** 単位（配合量） */
	private String unitName;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 投入方法 */
	private String tonyu;

	/** 投入速度 */
	private String tonyuSokudo;

	/** データ読取 */
	private String dataRead;

	/** 検索結果 */
	private RecipeFormulaList detailBean;

	/** 表示時処方ヘッダー情報 */
	private RecipeHeaderList headerBean;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeFormulaDetailForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		// STEP_NO
		setStepNo(null);
		// LINE_NO
		setLineNo(null);
		// 表示順
		setSeq(null);
		// 工程順序
		setProSeq(null);
		// 配合量計
		setSumQty(null);
		// 単位（配合量計）
		setHeadUnitName(null);
		// 工程コード
		setOperationCd(null);
		// 工程名称
		setOperationName(null);
		// 荷姿
		setStyleOfPacking(null);
		// 品目コード
		setItemCd(null);
		// 品目名称 ラベル用
		setFormulaItemName(null);
		// 配合量
		setQty(null);
		// 単位（配合量）
		setUnitName(null);
		// 単位区分（配合量）
		setUnitDivision(null);
		// 備考
		setRemark(null);
		// 注釈
		setNotes(null);
		// 投入方法
		setTonyu(null);
		// 投入速度
		setTonyuSokudo(null);
		// データ読取
		setDataRead(null);
		// 検索結果Bean
		setDetailBean(null);
	}

	// getter,setter
	/**
	 * STEP_NO取得
	 * @return String STEP_NO
	 */
	public String getStepNo() {
		return this.stepNo;
	}

	/**
	 * STEP_NO設定
	 * @param stepNo STEP_NO
	 */
	public void setStepNo(final String stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * LINE_NO取得
	 * @return String LINE_NO
	 */
	public String getLineNo() {
		return this.lineNo;
	}

	/**
	 * LINE_NO設定
	 * @param lineNo LINE_NO
	 */
	public void setLineNo(final String lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * 表示順取得
	 * @return String 表示順
	 */
	public String getSeq() {
		return this.seq;
	}

	/**
	 * 表示順設定
	 * @param seq 表示順
	 */
	public void setSeq(final String seq) {
		this.seq = seq;
	}

	/**
	 * 配合量を取得します。
	 * @return 配合量
	 */
	public String getSumQty() {
		return sumQty;
	}

	/**
	 * 配合量を設定します。
	 * @param sumQty 配合量
	 */
	public void setSumQty(final String sumQty) {
		this.sumQty = sumQty;
	}

	/**
	 * 荷姿を取得します。
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 工程コード取得
	 * @return String 工程コード
	 */
	public String getOperationCd() {
		return this.operationCd;
	}

	/**
	 * 工程コード設定
	 * @param operationCd 工程コード
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 備考取得
	 * @return String 備考
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考設定
	 * @param remark 備考
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 注釈取得
	 * @return String 注釈
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * 注釈設定
	 * @param notes 注釈
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/**
	 * 工程名称取得
	 * @return String 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称設定
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 検索結果Bean取得
	 * @return RecipeFormulaList 検索結果Bean
	 */
	public RecipeFormulaList getDetailBean() {
		return detailBean;
	}

	/**
	 * 検索結果Bean設定
	 * @param detailBean 検索結果Bean
	 */
	public void setDetailBean(final RecipeFormulaList detailBean) {
		this.detailBean = detailBean;
	}

	/**
	 * 品目コードを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称 ラベル用を取得します。
	 * @return formulaItemName
	 */
	public String getFormulaItemName() {
		return formulaItemName;
	}

	/**
	 * 品目名称 ラベル用を設定します。
	 * @param formulaItemName 品目名称 ラベル用
	 */
	public void setFormulaItemName(final String formulaItemName) {
		this.formulaItemName = formulaItemName;
	}

	/**
	 * 配合量を取得します。
	 * @return qty
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * 配合量を設定します。
	 * @param qty 配合量
	 */
	public void setQty(final String qty) {
		this.qty = qty;
	}

	/**
	 * データ読取を取得します。
	 * @return dataRead
	 */
	public String getDataRead() {
		return dataRead;
	}

	/**
	 * データ読取を設定します。
	 * @param dataRead データ読取
	 */
	public void setDataRead(final String dataRead) {
		this.dataRead = dataRead;
	}

	/**
	 * 投入方法を取得します。
	 * @return tonyu
	 */
	public String getTonyu() {
		return tonyu;
	}

	/**
	 * 投入方法を設定します。
	 * @param tonyu 投入方法
	 */
	public void setTonyu(final String tonyu) {
		this.tonyu = tonyu;
	}

	/**
	 * 投入速度を取得します。
	 * @return tonyuSokudo
	 */
	public String getTonyuSokudo() {
		return tonyuSokudo;
	}

	/**
	 * 投入速度を設定します。
	 * @param tonyuSokudo 投入速度
	 */
	public void setTonyuSokudo(final String tonyuSokudo) {
		this.tonyuSokudo = tonyuSokudo;
	}

	/**
	 * 単位（配合量）を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位（配合量）を設定します。
	 * @param unitName 単位（配合量）
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 単位（配合量計）を取得します。
	 * @return headUnitName
	 */
	public String getHeadUnitName() {
		return headUnitName;
	}

	/**
	 * 単位（配合量計）を設定します。
	 * @param headUnitName 単位（配合量計）
	 */
	public void setHeadUnitName(final String headUnitName) {
		this.headUnitName = headUnitName;
	}

	/**
	 * 数値区分（配合量）を取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * 数値区分（配合量）を設定します。
	 * @param unitDivision 数値区分（配合量）
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * 表示時処方ヘッダー情報を取得します。
	 * @return headerBean 表示時処方ヘッダー情報
	 */
	public RecipeHeaderList getHeaderBean() {
		return headerBean;
	}

	/**
	 * 表示時処方ヘッダー情報を設定します。
	 * @param headerBean 表示時処方ヘッダー情報
	 */
	public void setHeaderBean(final RecipeHeaderList headerBean) {
		this.headerBean = headerBean;
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
	 * proSeqを取得します。
	 * @return proSeq
	 */
	public String getProSeq() {
		return proSeq;
	}

	/**
	 * proSeqを設定します。
	 * @param proSeq proSeq
	 */
	public void setProSeq(final String proSeq) {
		this.proSeq = proSeq;
	}
}
