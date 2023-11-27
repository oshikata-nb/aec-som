/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;

/**
 * 配合タブ詳細画面 Formクラス.
 * @author tosco
 */
public final class DirectionFormulaDetailForm extends AbstractDirectionForm {

	private static final long serialVersionUID = 1L;

	/** STEP_NO（工程順序） */
	private String stepNo;
	/** LINE_NO（サブステップ） */
	private String lineNo;
	/** 表示順 */
	private String seq;

	/** 工程コード */
	private String operationCd;
	/** 工程名称 */
	private String operationName;

	/** 品目コード */
	private String formulaItemCd;
	/** 品目名称 ラベル用 */
	private String formulaItemName;

	/** 指図量 */
	private String qty;
	/** 小数点桁数(指図量) */
	private String qtyDecimalPoint;
	/** 端数区分(指図量) */
	private String qtyRoundDivision;
	/** 単位（指図量） */
	private String formulaUnitName;
//	/** 数値区分（指図量） */
//	private String unitDivision;

	/** 投入方法 */
	private String tonyu;
	/** データ読取 */
	private String dataRead;
	/** 入荷ロット */
	private String lotNo;

	/** 投入速度 */
	private String tonyuSokudo;
	/** 小数点桁数(投入速度) */
	private String tonyuSokudoDP;
	/** 端数区分(投入速度) */
	private String tonyuSokudoRD;

	/** 投入条件 */
	private String stepCondition;
	/** 備考 */
	private String remark;
	/** 注釈 */
	private String notes;

	/** 検索結果 */
	private DirectionDirectionFormulaList detailBean;

	/**
	 * コンストラクタ.
	 */
	public DirectionFormulaDetailForm() {
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
			//Validatorによる判定
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
		// 工程コード
		setOperationCd(null);
		// 工程名称
		setOperationName(null);
		// 品目コード
		setFormulaItemCd(null);
		// 品目名称 ラベル用
		setFormulaItemName(null);

		// 指図量
		setQty(null);
//		// 単位区分（指図量）
//		setUnitDivision(null);
		// 単位(指図量)
		setFormulaUnitName(null);
		// 少数桁数(指図量)
		setQtyDecimalPoint(null);
		// 端数区分(指図量)
		setQtyRoundDivision(null);

		// 投入速度
		setTonyuSokudo(null);
		// 少数桁数(投入速度)
		setTonyuSokudoDP(null);
		// 端数区分(投入速度)
		setTonyuSokudoRD(null);

		// 備考
		setRemark(null);
		// 注釈
		setNotes(null);
		// 投入方法
		setTonyu(null);
		// データ読取
		setDataRead(null);
		// 検索結果Bean
		setDetailBean(null);

	}

	//getter,setter
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
	 * @return DirectionDirectionFormulaList 検索結果Bean
	 */
	public DirectionDirectionFormulaList getDetailBean() {
		return detailBean;
	}

	/**
	 * 検索結果Bean設定
	 * @param detailBean 検索結果Bean
	 */
	public void setDetailBean(final DirectionDirectionFormulaList detailBean) {
		this.detailBean = detailBean;
	}

	/**
	 * 品目コードを取得します。
	 * @return formulaItemCd
	 */
	public String getFormulaItemCd() {
		return formulaItemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param formulaItemCd 品目コード
	 */
	public void setFormulaItemCd(final String formulaItemCd) {
		this.formulaItemCd = formulaItemCd;
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
	 * 指図量を取得します。
	 * @return qty
	 */
	public String getQty() {
		return qty;
	}
	/**
	 * 指図量を設定します。
	 * @param qty 指図量
	 */
	public void setQty(final String qty) {
		this.qty = qty;
	}
	/**
	 * 小数点桁数(指図量)を取得します。
	 * @return qtyDecimalPoint
	 */
	public String getQtyDecimalPoint() {
		return qtyDecimalPoint;
	}
	/**
	 * 小数点桁数(指図量)を設定します。
	 * @param qtyDecimalPoint 小数点桁数(指図量)
	 */
	public void setQtyDecimalPoint(final String qtyDecimalPoint) {
		this.qtyDecimalPoint = qtyDecimalPoint;
	}
	/**
	 * 端数区分(指図量)を取得します。
	 * @return qtyRoundDivision
	 */
	public String getQtyRoundDivision() {
		return qtyRoundDivision;
	}
	/**
	 * 端数区分(指図量)を設定します。
	 * @param qtyRoundDivision 端数区分(指図量)
	 */
	public void setQtyRoundDivision(final String qtyRoundDivision) {
		this.qtyRoundDivision = qtyRoundDivision;
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
	 * 小数点桁数(投入速度)を取得します。
	 * @return tonyuSokudoDP 小数点桁数(投入速度)
	 */
	public String getTonyuSokudoDP() {
		return tonyuSokudoDP;
	}
	/**
	 * 小数点桁数(投入速度)を設定します。
	 * @param tonyuSokudoDP 小数点桁数(投入速度)
	 */
	public void setTonyuSokudoDP(final String tonyuSokudoDP) {
		this.tonyuSokudoDP = tonyuSokudoDP;
	}
	/**
	 * 端数区分(投入速度)を取得します。
	 * @return tonyuSokudoRD 端数区分(投入速度)
	 */
	public String getTonyuSokudoRD() {
		return tonyuSokudoRD;
	}
	/**
	 * 端数区分(投入速度)を設定します。
	 * @param tonyuSokudoRD 端数区分(投入速度)
	 */
	public void setTonyuSokudoRD(final String tonyuSokudoRD) {
		this.tonyuSokudoRD = tonyuSokudoRD;
	}

	/**
	 * 投入条件を取得します。
	 * @return stepCondition
	 */
	public String getStepCondition() {
		return stepCondition;
	}

	/**
	 * 投入条件を設定します。
	 * @param stepCondition 投入条件
	 */
	public void setStepCondition(final String stepCondition) {
		this.stepCondition = stepCondition;
	}

	/**
	 * 入荷ロットを取得します。
	 * @return lotNo
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * 入荷ロットを設定します。
	 * @param lotNo 入荷ロット
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * formulaUnitNameを取得します。
	 * @return formulaUnitName
	 */
	public String getFormulaUnitName() {
		return formulaUnitName;
	}

	/**
	 * formulaUnitNameを設定します。
	 * @param formulaUnitName formulaUnitName
	 */
	public void setFormulaUnitName(final String formulaUnitName) {
		this.formulaUnitName = formulaUnitName;
	}

//	/**
//	 * unitDivisionを取得します。
//	 * @return unitDivision
//	 */
//	public String getUnitDivision() {
//		return unitDivision;
//	}
//
//	/**
//	 * unitDivisionを設定します。
//	 * @param unitDivision unitDivision
//	 */
//	public void setUnitDivision(final String unitDivision) {
//		this.unitDivision = unitDivision;
//	}
//
}
