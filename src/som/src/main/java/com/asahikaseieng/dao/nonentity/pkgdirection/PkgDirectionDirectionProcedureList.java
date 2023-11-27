/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;


/**
 * 包装指図－製造指図プロシージャデータ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionDirectionProcedureList extends PkgDirectionDirectionProcedureListBase
	implements PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション formulaMark */
	public static final String formulaMark_COLUMN = "FORMULA_MARK";

	/** COLUMNアノテーション inspectionMark */
	public static final String inspectionMark_COLUMN = "INSPECTION_MARK";

	/** COLUMNアノテーション lastStepNo */
	public static final String lastStepNo_COLUMN = "LAST_STEP_NO";

	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** チェックフラグ */
	private boolean checkFlg;

	/** STEP_NO */
	private String strStepNo;

	/** 工程名称 */
	private String operationName;

	/** 配合 */
	private String formulaMark;

	/** 工程 */
	private String inspectionMark;

	/** 最終STEP_NO */
	private BigDecimal lastStepNo;

	/** 工程グループコード */
	private String operationGroupCd;

	/** 工程開始予定日時 */
	private String strStartDate;

	/** 工程終了予定日時 */
	private String strEndDate;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionProcedureList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		if (getStepNo() != null) {
			setStrStepNo(getStepNo().toString());
		}
		if (this.getStartDate() != null) {
			this.setStrStartDate(AecDateUtils.dateFormat(this.getStartDate(), "yyyy/MM/dd HH:mm"));
		}
		if (this.getEndDate() != null) {
			this.setStrEndDate(AecDateUtils.dateFormat(this.getEndDate(), "yyyy/MM/dd HH:mm"));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

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
	 * STEP_NO取得
	 * @return String STEP_NO
	 */
	public String getStrStepNo() {
		return strStepNo;
	}

	/**
	 * STEP_NO設定
	 * @param strStepNo STEP_NO
	 */
	public void setStrStepNo(final String strStepNo) {
		this.strStepNo = strStepNo;
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
	 * 配合取得
	 * @return String 配合
	 */
	public String getFormulaMark() {
		return formulaMark;
	}

	/**
	 * 配合設定
	 * @param formulaMark 配合
	 */
	public void setFormulaMark(final String formulaMark) {
		this.formulaMark = formulaMark;
	}

	/**
	 * 工程取得
	 * @return String 工程
	 */
	public String getInspectionMark() {
		return inspectionMark;
	}

	/**
	 * 工程設定
	 * @param inspectionMark 工程
	 */
	public void setInspectionMark(final String inspectionMark) {
		this.inspectionMark = inspectionMark;
	}

	/**
	 * 最終STEP_NO取得
	 * @return BigDecimal 最終STEP_NO
	 */
	public BigDecimal getLastStepNo() {
		return lastStepNo;
	}

	/**
	 * 最終STEP_NO設定
	 * @param lastStepNo 最終STEP_NO
	 */
	public void setLastStepNo(final BigDecimal lastStepNo) {
		this.lastStepNo = lastStepNo;
	}

	/**
	 * 工程グループコード取得
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return operationGroupCd;
	}

	/**
	 * 工程グループコード設定
	 * @param operationGroupCd 工程グループコード
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}

	/**
	 * 工程開始予定日時取得
	 * @return String 工程開始予定日時
	*/
	public String getStrStartDate() {
		return this.strStartDate;
	}

	/**
	 * 工程開始予定日時設定
	 * @param strStartDate 工程開始予定日時
	*/
	public void setStrStartDate(final String strStartDate) {
		this.strStartDate = strStartDate;
	}

	/**
	 * 工程終了予定日時取得
	 * @return String 工程終了予定日時
	*/
	public String getStrEndDate() {
		return this.strEndDate;
	}

	/**
	 * 工程終了日予定時設定
	 * @param strEndDate 工程終了予定日時
	*/
	public void setStrEndDate(final String strEndDate) {
		this.strEndDate = strEndDate;
	}
}
