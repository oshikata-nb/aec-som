/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.rdirection.RdirectionUtil;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 製造指図フォーミュラ_表示用データ格納クラス.
 * 
 * @author tosco
 */
public class RdirectionDirectionFormulaList extends
		RdirectionDirectionFormulaListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション kind */
	public static final String kind_COLUMN = "KIND";

	/** COLUMNアノテーション locationCd */
	public static final String sumQty_COLUMN = "SUM_QTY";

	/** COLUMNアノテーション procSeq */
	public static final String procSeq_COLUMN = "PROC_SEQ";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション operName */
	public static final String operName_COLUMN = "OPER_NAME";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	/** COLUMNアノテーション lastLineNo */
	public static final String lastLineNo_COLUMN = "LAST_LINE_NO";

	/** COLUMNアノテーション lastSeq */
	public static final String lastSeq_COLUMN = "LAST_SEQ";

	/** COLUMNアノテーション stockDivision */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション lotDivision */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	/** タンク優先度 */
	private String kind;

	/** 配合量計 BigDecimal */
	private BigDecimal sumQty;

	/** 工程順序 BigDecimal */
	private BigDecimal procSeq;

	/** 品目名称 */
	private String itemName;

	/** 工程名称 */
	private String operName;

	/** 単位名称 */
	private String unitName;

	/** 最終LINE_NO BigDecimal */
	private BigDecimal lastLineNo;

	/** 最終SEQ BigDecimal */
	private BigDecimal lastSeq;

	/** 在庫管理区分 */
	private BigDecimal stockDivision;

	/** ロット管理区分 */
	private BigDecimal lotDivision;

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

	/** ロス数量 String */
	private String strLossQty;

	/** 調整数量 String */
	private String strAdjustQty;

	/** 在庫管理区分 String */
	private String strStockDivision;

	/** ロット管理区分 String */
	private String strLotDivision;

	/** 変更前実績数量 String */
	private String strBeforeResultQty;

	/**
	 * コンストラクタ.
	 */
	public RdirectionDirectionFormulaList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* Stringに変換 */
		setStrSeq(RdirectionUtil.getBigDecimalString(getSeq()));
		setStrStepNo(RdirectionUtil.getBigDecimalString(getStepNo()));
		setStrLineNo(RdirectionUtil.getBigDecimalString(getLineNo()));
		setStrLineType(RdirectionUtil.getBigDecimalString(getLineType()));
		setStrStockDivision(RdirectionUtil
				.getBigDecimalString(getStockDivision()));
		setStrLotDivision(RdirectionUtil.getBigDecimalString(getLotDivision()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setQty(AecNumberUtils.convertBigDecimal(getStrQty()));
		setResultQty(AecNumberUtils.convertBigDecimal(getStrResultQty()));
		setLossQty(AecNumberUtils.convertBigDecimal(getStrLossQty()));
		setAdjustQty(AecNumberUtils.convertBigDecimal(getStrAdjustQty()));
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * タンク優先度を取得します。
	 * @return タンク優先度
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * タンク優先度を設定します。
	 * @param kind タンク優先度
	 */
	public void setKind(final String kind) {
		this.kind = kind;
	}

	/**
	 * sumQtyを取得します。
	 * @return sumQty
	 */
	public BigDecimal getSumQty() {
		return sumQty;
	}

	/**
	 * sumQtyを設定します。
	 * @param sumQty sumQty
	 */
	public void setSumQty(final BigDecimal sumQty) {
		this.sumQty = sumQty;
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
	 * 単位名称を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位名称を設定します。
	 * @param unitName 単位名称
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
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
	 * 仕込み実績数量 Stringを取得します。
	 * @return strResultQty 仕込み実績数量 String
	 */
	public String getStrResultQty() {
		return strResultQty;
	}

	/**
	 * 仕込み実績数量 Stringを設定します。
	 * @param strResultQty 仕込み実績数量 String
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * 調整数量 String を取得します。
	 * @return strAdjustQty 調整数量 String
	 */
	public String getStrAdjustQty() {
		return strAdjustQty;
	}

	/**
	 * 調整数量 String を設定します。
	 * @param strAdjustQty 調整数量 String
	 */
	public void setStrAdjustQty(final String strAdjustQty) {
		this.strAdjustQty = strAdjustQty;
	}

	/**
	 * ロス数量 Stringを取得します。
	 * @return strLossQty ロス数量 String
	 */
	public String getStrLossQty() {
		return strLossQty;
	}

	/**
	 * ロス数量 Stringを設定します。
	 * @param strLossQty ロス数量 String
	 */
	public void setStrLossQty(final String strLossQty) {
		this.strLossQty = strLossQty;
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

	/**
	 * 在庫管理区分を取得します。
	 * @return String 在庫管理区分
	 */
	public BigDecimal getStockDivision() {
		return stockDivision;
	}

	/**
	 * 在庫管理区分を設定します。
	 * @param stockDivision 在庫管理区分
	 */
	public void setStockDivision(final BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * ロット管理区分 Stringを取得します。
	 * @return String ロット管理区分
	 */
	public String getStrLotDivision() {
		return strLotDivision;
	}

	/**
	 * ロット管理区分 Stringを設定します。
	 * @param strLotDivision ロット管理区分
	 */
	public void setStrLotDivision(final String strLotDivision) {
		this.strLotDivision = strLotDivision;
	}

	/**
	 * 在庫管理区分 Stringを取得します。
	 * @return String 在庫管理区分
	 */
	public String getStrStockDivision() {
		return strStockDivision;
	}

	/**
	 * 在庫管理区分 Stringを設定します。
	 * @param strStockDivision 在庫管理区分
	 */
	public void setStrStockDivision(final String strStockDivision) {
		this.strStockDivision = strStockDivision;
	}

	/**
	 * strBeforeResultQty Stringを取得します。
	 * @return strBeforeResultQty strBeforeResultQty
	 */
	public String getStrBeforeResultQty() {
		return strBeforeResultQty;
	}

	/**
	 * strBeforeResultQty Stringを設定します。
	 * @param strBeforeResultQty strBeforeResultQty
	 */
	public void setStrBeforeResultQty(final String strBeforeResultQty) {
		this.strBeforeResultQty = strBeforeResultQty;
	}
}
