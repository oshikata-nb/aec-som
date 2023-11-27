/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectInspectionCondition;
import com.asahikaseieng.app.comboboxes.SelectInspectionDivision;
import com.asahikaseieng.utils.PropertyTransferCallbacker;


/**
 * 製造指図検査データ格納クラス.
 *
 * @author tosco
 */
public class RdirectionDirectionInspectionList extends RdirectionDirectionInspectionListBase
	implements PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション inspectionName */
	public static final String inspectionName_COLUMN = "INSPECTION_NAME";

	/** COLUMNアノテーション lastLineNo */
	public static final String lastLineNo_COLUMN = "LAST_LINE_NO";

	/** 検査名称 */
	private String inspectionName;

	/** チェックフラグ */
	private boolean checkFlg;

	/** LINE_NO */
	private String strLineNo;

	/** 最終LINE_NO */
	private BigDecimal lastLineNo;


	/**
	 * コンストラクタ.
	 */
	public RdirectionDirectionInspectionList() {
		super();
	}

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

}
