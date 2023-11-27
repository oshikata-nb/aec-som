/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装指図－製造指図フォーミュラデータ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionDirectionFormulaList extends PkgDirectionDirectionFormulaListBase
	implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";
	/** COLUMNアノテーション procSeq */
	public static final String procSeq_COLUMN = "PROC_SEQ";
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";
	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";
	/** COLUMNアノテーション lastLineNo */
	public static final String lastLineNo_COLUMN = "LAST_LINE_NO";
	/** COLUMNアノテーション lastSeq */
	public static final String lastSeq_COLUMN = "LAST_SEQ";
	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** 単位名 */
	private String unitName;
	/** 工程順序 BigDecimal */
	private BigDecimal procSeq;
	/** 品目名称 */
	private String itemName;
	/** 工程コード */
	private String operationCd;
	/** 工程名称 */
	private String operationName;
	/** 最終LINE_NO BigDecimal */
	private BigDecimal lastLineNo;
	/** 最終SEQ BigDecimal */
	private BigDecimal lastSeq;
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
	/** 予定数量 String */
	private String strQty;
	/** LINE_TYPE String */
	private String strLineType;
	/** 用途 */
	private String rcpUse;
	/** 小数点桁数 */
	private String decimalPoint;
	/** 端数区分 */
	private String roundDivision;
	/** 充填予定数量 String */
	private String strFillQty;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionFormulaList() {
		super();
	}

	/* ---------- callbacker ---------- */

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
		setQty(AecNumberUtils.convertBigDecimal(getStrQty()));
	}

	/* ---------- getter ,setter ---------- */

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
	 * @return String 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称を設定します。
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
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
	 * 予定数量を取得します。
	 * @return strQty
	 */
	public String getStrQty() {
		return strQty;
	}

	/**
	 * 予定数量を設定します。
	 * @param strQty 配合量
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
	 * 充填予定数量 Stringを取得します。
	 * @return strFillQty
	 */
	public String getStrFillQty() {
		return strFillQty;
	}

	/**
	 * 充填予定数量 Stringを設定します。
	 * @param strFillQty 充填予定数量 String
	 */
	public void setStrFillQty(final String strFillQty) {
		this.strFillQty = strFillQty;
	}
}
