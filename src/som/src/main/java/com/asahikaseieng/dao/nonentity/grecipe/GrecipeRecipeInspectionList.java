/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.grecipe;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectInspectionCondition;
import com.asahikaseieng.app.comboboxes.SelectInspectionDivision;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 処方検査データ格納クラス.
 * 
 * @author tosco
 */
public class GrecipeRecipeInspectionList extends
		GrecipeRecipeInspectionListBase implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** COLUMNアノテーション inspectionName */
	public static final String inspectionName_COLUMN = "INSPECTION_NAME";

	/** COLUMNアノテーション lastLineNo */
	public static final String lastLineNo_COLUMN = "LAST_LINE_NO";

	/** チェックフラグ */
	private boolean checkFlg;

	/** LINE_NO */
	private String strLineNo;

	/** 検査名称 */
	private String inspectionName;

	/** 最終LINE_NO */
	private BigDecimal lastLineNo;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public GrecipeRecipeInspectionList() {
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
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
	}
}
