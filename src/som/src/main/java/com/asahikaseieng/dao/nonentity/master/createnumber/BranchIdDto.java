/*
 * Created on 2008/01/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createnumber;

import java.io.Serializable;


/**
 * 品目名称試作番号枝番採番DTO
 * 
 * @author 956
 */
public final class BranchIdDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructor */
	public BranchIdDto() {
	}

	/** trialCd_PROCEDURE_PARAMETER */
	public static final String trialCd_PROCEDURE_PARAMETER = "in";

	/** trialId_PROCEDURE_PARAMETER */
	public static final String trialId_PROCEDURE_PARAMETER = "in";

	/** nameIdFull_PROCEDURE_PARAMETER */
	public static final String nameIdFull_PROCEDURE_PARAMETER = "out";

	/** branchId_PROCEDURE_PARAMETER */
	public static final String branchId_PROCEDURE_PARAMETER = "out";

	private String trialCd;
	private String trialId;
	private String nameIdFull;
	private String branchId;

	/**
	 * branchIdを取得します。
	 * @return branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * branchIdを設定します。
	 * @param branchId branchId
	 */
	public void setBranchId(final String branchId) {
		this.branchId = branchId;
	}
	/**
	 * nameIdFullを取得します。
	 * @return nameIdFull
	 */
	public String getNameIdFull() {
		return nameIdFull;
	}
	/**
	 * nameIdFullを設定します。
	 * @param nameIdFull nameIdFull
	 */
	public void setNameIdFull(final String nameIdFull) {
		this.nameIdFull = nameIdFull;
	}
	/**
	 * trialCdを取得します。
	 * @return trialCd
	 */
	public String getTrialCd() {
		return trialCd;
	}
	/**
	 * trialCdを設定します。
	 * @param trialCd trialCd
	 */
	public void setTrialCd(final String trialCd) {
		this.trialCd = trialCd;
	}
	/**
	 * trialIdを取得します。
	 * @return trialId
	 */
	public String getTrialId() {
		return trialId;
	}
	/**
	 * trialIdを設定します。
	 * @param trialId trialId
	 */
	public void setTrialId(final String trialId) {
		this.trialId = trialId;
	}


}
