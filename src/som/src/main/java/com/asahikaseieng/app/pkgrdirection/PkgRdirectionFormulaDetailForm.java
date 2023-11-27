/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * 包装実績－配合詳細画面 Formクラス.
 * @author tosco
 */
public final class PkgRdirectionFormulaDetailForm extends AbstractPkgRdirectionForm {

	private static final long serialVersionUID = 1L;

	/** ステップNO. */
	private String stepNo;
	/** 行NO. */
	private String lineNo;
	/** 工程コード */
	private String operationCd;
	/** 品目コード */
	private String formulaItemCd;
	/** 品目名称 */
	private String formulaItemName;
	/** 工程名称 */
	private String operationName;
	/** 予定数量 */
	private String strQty;
	/** 単位（予定数量） */
	private String qtyUnitName;
	/** 入荷ロット */
	private String lotNo;
	/** 在庫引落量 */
	private String strStockpdQty;
	/** 使用数 */
	private String strResultQty;
	/** サンプル */
	private String strSampleQty;
	/** ロス数量 */
	private String strLossQty;
	/** 不良 */
	private String strDefectQty;
	/** 調整数量 */
	private String strAdjustQty;
	/** 包装条件 */
	private String stepCondition;
	/** 備考 */
	private String remark;
	/** 注釈 */
	private String notes;
	/** 製造指図プロシージャ更新日時 */
	private Timestamp formulaUpdateDate;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionFormulaDetailForm() {
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

		// ステップNO.
		this.setStepNo(null);
		// 行NO.
		this.setLineNo(null);
		// 工程コード
		this.setOperationCd(null);
		// 工程名称
		this.setOperationName(null);
		// 品目コード
		this.setFormulaItemCd(null);
		// 品目名称
		this.setFormulaItemName(null);
		// 予定数量
		this.setStrQty(null);
		// 単位(予定数量)
		this.setQtyUnitName(null);
		// 入荷ロット
		this.setLotNo(null);
		// 在庫引落量
		this.setStrStockpdQty(null);
		// 使用数
		this.setStrResultQty(null);
		// サンプル
		this.setStrSampleQty(null);
		// ロス数量
		this.setStrLossQty(null);
		// 不良
		this.setStrDefectQty(null);
		// 調整数量
		this.setStrAdjustQty(null);
		// 包装条件
		this.setStepCondition(null);
		// 備考
		this.setRemark(null);
		// 注釈
		this.setNotes(null);
		// 製造指図プロシージャ更新日時
		this.setFormulaUpdateDate(null);
	}

	//getter,setter
	/**
	 * ステップNO.取得
	 * @return String ステップNO.
	*/
	public String getStepNo() {
		return this.stepNo;
	}

	/**
	 * ステップNO.設定
	 * @param stepNo ステップNO.
	*/
	public void setStepNo(final String stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * 行NO.取得
	 * @return String 行NO.
	*/
	public String getLineNo() {
		return this.lineNo;
	}

	/**
	 * 行NO.設定
	 * @param lineNo 行NO.
	*/
	public void setLineNo(final String lineNo) {
		this.lineNo = lineNo;
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
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getFormulaItemCd() {
		return formulaItemCd;
	}

	/**
	 * 品目コード設定.
	 * @param formulaItemCd 品目コード
	 */
	public void setFormulaItemCd(final String formulaItemCd) {
		this.formulaItemCd = formulaItemCd;
	}

	/**
	 * 品目名称取得.
	 * @return 品目名称
	 */
	public String getFormulaItemName() {
		return this.formulaItemName;
	}

	/**
	 * 品目名称設定.
	 * @param formulaItemName 品目名称
	 */
	public void setFormulaItemName(final String formulaItemName) {
		this.formulaItemName = formulaItemName;
	}

	/**
	 * 予定数量を取得
	 * @return strQty
	 */
	public String getStrQty() {
		return strQty;
	}
	/**
	 * 予定数量を設定
	 * @param strQty 予定数量
	 */
	public void setStrQty(final String strQty) {
		this.strQty = strQty;
	}

	/**
	 * 単位(予定数量)取得
	 * @return formulaUnitName
	 */
	public String getQtyUnitName() {
		return qtyUnitName;
	}

	/**
	 * 単位(予定数量)設定
	 * @param qtyUnitName qtyUnitName
	 */
	public void setQtyUnitName(final String qtyUnitName) {
		this.qtyUnitName = qtyUnitName;
	}

	/**
	 * 入荷ロット取得
	 * @return lotNo
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * 入荷ロット設定
	 * @param lotNo 入荷ロット
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 在庫引落量.
	 * @return String 在庫引落量
	 */
	public String getStrStockpdQty() {
		return this.strStockpdQty;
	}

	/**
	 * 在庫引落量設定.
	 * @param strStockpdQty 在庫引落量
	 */
	public void setStrStockpdQty(final String strStockpdQty) {
		this.strStockpdQty = strStockpdQty;
	}

	/**
	 * 使用数量取得.
	 * @return String 使用数量
	 */
	public String getStrResultQty() {
		return this.strResultQty;
	}

	/**
	 * 使用数量設定.
	 * @param strResultQty 使用数量
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * サンプル取得
	 * @return String サンプル
	*/
	public String getStrSampleQty() {
		return this.strSampleQty;
	}

	/**
	 * サンプル設定
	 * @param strSampleQty サンプル
	*/
	public void setStrSampleQty(final String strSampleQty) {
		this.strSampleQty = strSampleQty;
	}

	/**
	 * ロス数量取得
	 * @return String ロス数量
	*/
	public String getStrLossQty() {
		return this.strLossQty;
	}

	/**
	 * ロス数量設定
	 * @param strLossQty ロス数量
	*/
	public void setStrLossQty(final String strLossQty) {
		this.strLossQty = strLossQty;
	}

	/**
	 * 不良取得
	 * @return String 不良
	*/
	public String getStrDefectQty() {
		return this.strDefectQty;
	}

	/**
	 * 不良設定
	 * @param strDefectQty 不良
	*/
	public void setStrDefectQty(final String strDefectQty) {
		this.strDefectQty = strDefectQty;
	}

	/**
	 * 調整数量取得
	 * @return String 調整数量
	*/
	public String getStrAdjustQty() {
		return this.strAdjustQty;
	}

	/**
	 * 調整数量設定
	 * @param strAdjustQty 調整数量
	*/
	public void setStrAdjustQty(final String strAdjustQty) {
		this.strAdjustQty = strAdjustQty;
	}

	/**
	 * 包装条件取得
	 * @return stepCondition
	 */
	public String getStepCondition() {
		return stepCondition;
	}

	/**
	 * 包装条件設定
	 * @param stepCondition 包装条件
	 */
	public void setStepCondition(final String stepCondition) {
		this.stepCondition = stepCondition;
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
	 * 製造指図フォーミュラ更新日時取得
	 * @return Timestamp 製造指図フォーミュラ更新日時
	 */
	public Timestamp getFormulaUpdateDate() {
		return this.formulaUpdateDate;
	}

	/**
	 * 製造指図フォーミュラ更新日時設定
	 * @param formulaUpdateDate 製造指図フォーミュラ更新日時
	 */
	public void setFormulaUpdateDate(final Timestamp formulaUpdateDate) {
		this.formulaUpdateDate = formulaUpdateDate;
	}
}
