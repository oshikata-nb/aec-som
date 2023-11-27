/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;


/**
 * 製造指図フォーミュラ_表示用データ格納クラス.
 *
 * @author tosco
 */
public class DirectionDirectionFormulaList extends DirectionDirectionFormulaListBase
implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** COLUMNアノテーション kind */
	public static final String kind_COLUMN = "KIND";
	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";
	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";
	/** COLUMNアノテーション sumQty */
	public static final String sumQty_COLUMN = "SUM_QTY";
	/** COLUMNアノテーション procSeq */
	public static final String procSeq_COLUMN = "PROC_SEQ";
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション operName */
	public static final String operName_COLUMN = "OPER_NAME";
	/** COLUMNアノテーション lastLineNo */
	public static final String lastLineNo_COLUMN = "LAST_LINE_NO";
	/** COLUMNアノテーション lastSeq */
	public static final String lastSeq_COLUMN = "LAST_SEQ";
	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIVISION";
	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	/** タンク優先度 */
	private String kind;
	/** ロット番号 */
	private String lotNo;
	/** ロケーションコード */
	private String locationCd;
	/** 配合量計 BigDecimal */
	private BigDecimal sumQty;
	/** 工程順序 BigDecimal */
	private BigDecimal procSeq;
	/** 品目名称 */
	private String itemName;
	/** 工程名称 */
	private String operName;
	/** 最終LINE_NO BigDecimal */
	private BigDecimal lastLineNo;
	/** 最終SEQ BigDecimal */
	private BigDecimal lastSeq;
	/** 単位区分 */
	private String unitDiv;
	/** 単位名称 */
	private String unitName;

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
	/** 配合量 String */
	private String strQty;
	/** LINE_TYPE String */
	private String strLineType;


	/**
	 * コンストラクタ.
	 */
	public DirectionDirectionFormulaList() {
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
	 * ロット番号を取得します。
	 * @return ロット番号
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * ロット番号を設定します。
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * ロケーションコードを取得します。
	 * @return ロケーションコード
	 */
	public String getLocationCd() {
		return locationCd;
	}

	/**
	 * ロケーションコードを設定します。
	 * @param locationCd ロケーションコード
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
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
	 * 配合量を取得します。
	 * @return strQty
	 */
	public String getStrQty() {
		return strQty;
	}

	/**
	 * 配合量を設定します。
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
	 * unitDivを取得します。
	 * @return unitDiv
	 */
	public String getUnitDiv() {
		return unitDiv;
	}


	/**
	 * unitDivを設定します。
	 * @param unitDiv unitDiv
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
	}


	/**
	 * unitNameを取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}


	/**
	 * unitNameを設定します。
	 * @param unitName unitName
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

}
