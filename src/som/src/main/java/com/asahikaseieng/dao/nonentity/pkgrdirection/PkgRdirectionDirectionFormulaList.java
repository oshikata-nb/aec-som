/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装実績－フォーミュラデータ格納クラス.
 * 
 * @author tosco
 */
public class PkgRdirectionDirectionFormulaList extends
		PkgRdirectionDirectionFormulaListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operName */
	public static final String operName_COLUMN = "OPER_NAME";

	/** COLUMNアノテーション lastLineNo */
	public static final String lastLineNo_COLUMN = "LAST_LINE_NO";

	/** COLUMNアノテーション lastSeq */
	public static final String lastSeq_COLUMN = "LAST_SEQ";

	/** COLUMNアノテーション procSeq */
	public static final String procSeq_COLUMN = "PROC_SEQ";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション lotDivision */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	/** 品目名称 */
	private String itemName;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operName;

	/** 最終LINE_NO BigDecimal */
	private BigDecimal lastLineNo;

	/** 最終SEQ BigDecimal */
	private BigDecimal lastSeq;

	/** 工程順序 BigDecimal */
	private BigDecimal procSeq;

	/** 運用管理単位 */
	private String unitDivision;

	/** チェックフラグ */
	private boolean checkFlg;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> procSeqCombo;

	/** STEP_NO String */
	private String strStepNo;

	/** LINE_NO String */
	private String strLineNo;

	/** サブステップ№ String */
	private String strSeq;

	/** 仕込み量 String */
	private String strQty;

	/** LINE_TYPE String */
	private String strLineType;

	/** 実績数量 String */
	private String strResultQty;

	/** 充填実績数量 String */
	private String strResultFillQty;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String roundDivision;

	/** 単位名 */
	private String unitName;

	/** 用途 */
	private String rcpUse;

	/** サンプル */
	private String strSampleQty;

	/** ロス数量 */
	private String strLossQty;

	/** 不良 */
	private String strDefectQty;

	/** 調整数量 */
	private String strAdjustQty;

	/** ロット管理区分 */
	private BigDecimal lotDivision;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionDirectionFormulaList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* Stringに変換 */
		if (getSeq() != null) {
			setStrSeq(getSeq().toString());
		}
		if (getStepNo() != null) {
			setStrStepNo(getStepNo().toString());
		}
		if (getLineNo() != null) {
			setStrLineNo(getLineNo().toString());
		}
		if (getLineType() != null) {
			setStrLineType(getLineType().toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 品目名称を取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 工程コードを取得します。
	 * @return String 工程コード
	 */
	public String getOperationCd() {
		return operationCd;
	}

	/**
	 * 工程コードを設定します。
	 * @param operationCd 工程コード
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 工程名称を取得します。
	 * @return operName
	 */
	public String getOperName() {
		return operName;
	}

	/**
	 * 工程名称を設定します。
	 * @param operName 工程名称
	 */
	public void setOperName(final String operName) {
		this.operName = operName;
	}

	/**
	 * チェックフラグ取得
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグ設定
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * サブステップを取得します。
	 * @return strSeq
	 */
	public String getStrSeq() {
		return strSeq;
	}

	/**
	 * サブステップを設定します。
	 * @param strSeq strSeq
	 */
	public void setStrSeq(final String strSeq) {
		this.strSeq = strSeq;
	}

	/**
	 * 仕込み量を取得します。
	 * @return strQty
	 */
	public String getStrQty() {
		return strQty;
	}

	/**
	 * 仕込み量を設定します。
	 * @param strQty 仕込み量
	 */
	public void setStrQty(final String strQty) {
		this.strQty = strQty;
	}

	/**
	 * 工程順序コンボボックスを取得します。
	 * @return 工程順序コンボボックス
	 */
	public List<ComboBoxItems> getProcSeqCombo() {
		return procSeqCombo;
	}

	/**
	 * 工程順序コンボボックスを設定します。
	 * @param procSeqCombo 工程順序コンボボックス
	 */
	public void setProcSeqCombo(final List<ComboBoxItems> procSeqCombo) {
		this.procSeqCombo = procSeqCombo;
	}

	/**
	 * LINE_NOを取得します。
	 * @return strLineNo
	 */
	public String getStrLineNo() {
		return strLineNo;
	}

	/**
	 * LINE_NOを設定します。
	 * @param strLineNo LINE_NO
	 */
	public void setStrLineNo(final String strLineNo) {
		this.strLineNo = strLineNo;
	}

	/**
	 * STEP_NOを取得します。
	 * @return strStepNo
	 */
	public String getStrStepNo() {
		return strStepNo;
	}

	/**
	 * STEP_NOを設定します。
	 * @param strStepNo STEP_NO
	 */
	public void setStrStepNo(final String strStepNo) {
		this.strStepNo = strStepNo;
	}

	/**
	 * 最終LINE_NOを取得します。
	 * @return lastLineNo 最終LINE_NO
	 */
	public BigDecimal getLastLineNo() {
		return lastLineNo;
	}

	/**
	 * 最終LINE_NOを設定します。
	 * @param lastLineNo 最終LINE_NO
	 */
	public void setLastLineNo(final BigDecimal lastLineNo) {
		this.lastLineNo = lastLineNo;
	}

	/**
	 * 最終SEQを取得します。
	 * @return lastSeq
	 */
	public BigDecimal getLastSeq() {
		return lastSeq;
	}

	/**
	 * 最終SEQを設定します。
	 * @param lastSeq 最終SEQ
	 */
	public void setLastSeq(final BigDecimal lastSeq) {
		this.lastSeq = lastSeq;
	}

	/**
	 * procSeqを取得します。
	 * @return procSeq
	 */
	public BigDecimal getProcSeq() {
		return procSeq;
	}

	/**
	 * procSeqを設定します。
	 * @param procSeq procSeq
	 */
	public void setProcSeq(final BigDecimal procSeq) {
		this.procSeq = procSeq;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitDivision 運用管理単位
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * LINE_TYPE Stringを取得します。
	 * @return strLineType
	 */
	public String getStrLineType() {
		return strLineType;
	}

	/**
	 * LINE_TYPE Stringを設定します。
	 * @param strLineType LINE_TYPE String
	 */
	public void setStrLineType(final String strLineType) {
		this.strLineType = strLineType;
	}

	/**
	 * 実績数量 Stringを取得します。
	 * @return strResultQty 実績数量 String
	 */
	public String getStrResultQty() {
		return strResultQty;
	}

	/**
	 * 実績数量 Stringを設定します。
	 * @param strResultQty 実績数量 String
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * 充填実績数量 Stringを取得します。
	 * @return strResultFillQty 充填実績数量 String
	 */
	public String getStrResultFillQty() {
		return strResultFillQty;
	}

	/**
	 * 充填実績数量 Stringを設定します。
	 * @param strResultFillQty 充填実績数量 String
	 */
	public void setStrResultFillQty(final String strResultFillQty) {
		this.strResultFillQty = strResultFillQty;
	}

	/**
	 * 小数点桁数を取得します。
	 * @return decimalPoint
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivision
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivision 端数区分
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * 単位名を取得します。
	 * @return 単位名
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位名を設定します。
	 * @param unitName 単位名
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 用途を取得します。
	 * @return rcpUse
	 */
	public String getRcpUse() {
		return rcpUse;
	}

	/**
	 * 用途を設定します。
	 * @param rcpUse 用途
	 */
	public void setRcpUse(final String rcpUse) {
		this.rcpUse = rcpUse;
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
	 * ロット管理区分を取得します。
	 * @return BigDecimal ロット管理区分
	 */
	public BigDecimal getLotDivision() {
		return lotDivision;
	}

	/**
	 * ロット管理区分を設定します。
	 * @param lotDivision ロット管理区分
	 */
	public void setLotDivision(final BigDecimal lotDivision) {
		this.lotDivision = lotDivision;
	}
}
