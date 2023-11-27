/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectInspectionCondition;
import com.asahikaseieng.app.comboboxes.SelectInspectionDivision;
import com.asahikaseieng.utils.PropertyTransferCallbacker;


/**
 * 包装指図－検査データ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionDirectionInspectionList extends PkgDirectionDirectionInspectionListBase
	implements PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionInspectionList() {
		super();
	}

	/** COLUMNアノテーション inspectionName */
	public static final String inspectionName_COLUMN = "INSPECTION_NAME";

	/** COLUMNアノテーション lastLineNo */
	public static final String lastLineNo_COLUMN = "LAST_LINE_NO";

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** 検査名称 */
	private String inspectionName;

	/** チェックフラグ */
	private boolean checkFlg;

	/** LINE_NO */
	private String strLineNo;

	/** 最終LINE_NO */
	private BigDecimal lastLineNo;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;


	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		if (getLineNo() != null) {
			setStrLineNo(getLineNo().toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 検査名称を取得する
	 * @return String 検査名称
	 */
	public String getInspectionName() {
		return inspectionName;
	}

	/**
	 * 検査名称を設定する
	 * @param inspectionName 検査名称
	 */
	public void setInspectionName(final String inspectionName) {
		this.inspectionName = inspectionName;
	}

	/**
	 * チェックフラグを取得する
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定する
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 区分名称を取得する
	 * @return String 区分名称
	 */
	public String getDivisionName() {
		return SelectInspectionDivision.getLabelName(this.getDivision());
	}

	/**
	 * 条件名称を取得する
	 * @return String 条件名称
	 */
	public String getConditionName() {
		return SelectInspectionCondition.getLabelName(this.getCondition());
	}

	/**
	 * LINE_NOを取得する
	 * @return String LINE_NO
	 */
	public String getStrLineNo() {
		return strLineNo;
	}

	/**
	 * LINE_NOを設定する
	 * @param strLineNo LINE_NO
	 */
	public void setStrLineNo(final String strLineNo) {
		this.strLineNo = strLineNo;
	}

	/**
	 * 最終LINE_NOを取得する
	 * @return BigDecimal 最終LINE_NO
	 */
	public BigDecimal getLastLineNo() {
		return lastLineNo;
	}

	/**
	 * 最終LINE_NOを設定する
	 * @param lastLineNo 最終LINE_NO
	 */
	public void setLastLineNo(final BigDecimal lastLineNo) {
		this.lastLineNo = lastLineNo;
	}

	/**
	 * 工程コードを取得します。
	 * @return operationCd
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
	 * @return operationName
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

}
