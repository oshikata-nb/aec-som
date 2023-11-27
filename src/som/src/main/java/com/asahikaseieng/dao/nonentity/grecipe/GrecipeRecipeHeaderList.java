/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.grecipe;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 処方ヘッダデータ格納クラス.
 * 
 * @author tosco
 */
public class GrecipeRecipeHeaderList extends GrecipeRecipeHeaderListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/** COLUMNアノテーション approvalStatusName */
	public static final String approvalStatusName_COLUMN = "APPROVAL_STATUS_NAME";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション sumQty */
	public static final String sumQty_COLUMN = "SUM_QTY";

	/** COLUMNアノテーション count */
	public static final String count_COLUMN = "COUNT";

	/** 品目名称 */
	private String itemName;

	/** ステータス名 */
	private String statusName;

	/** 承認ステータス名 */
	private String approvalStatusName;

	/** 単位名 */
	private String unitName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位区分 */
	private String unitDivision;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;

	/** 配合量 */
	private BigDecimal sumQty;

	/** 件数 */
	private BigDecimal count;

	/**
	 * コンストラクタ.
	 */
	public GrecipeRecipeHeaderList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 品目名称を取得します。
	 * @return 品目名称
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
	 * ステータス名を取得します。
	 * @return ステータス名
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * ステータス名を設定します。
	 * @param statusName ステータス名
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
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
	 * 単位区分を取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * 単位区分を設定します。
	 * @param unitDivision 単位区分
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
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
	 * 件数を取得します。
	 * @return 件数
	 */
	public BigDecimal getCount() {
		return count;
	}

	/**
	 * 件数を設定します。
	 * @param count 件数
	 */
	public void setCount(final BigDecimal count) {
		this.count = count;
	}

	/**
	 * approvalStatusNameを取得します。
	 * @return approvalStatusName
	 */
	public String getApprovalStatusName() {
		return approvalStatusName;
	}

	/**
	 * approvalStatusNameを設定します。
	 * @param approvalStatusName approvalStatusName
	 */
	public void setApprovalStatusName(final String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
	}
}
