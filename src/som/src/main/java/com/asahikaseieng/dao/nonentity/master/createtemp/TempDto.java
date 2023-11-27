/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 一時テーブル作成(原処方からの取得)DTO
 * @author 956
 */
public final class TempDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ステータス：正常終了 */
	public static final BigDecimal STATUS_SUCCESS = new BigDecimal(0);
	/** ステータス：異常終了 */
	public static final BigDecimal STATUS_ERROR = new BigDecimal(1);

	/** Constructor */
	public TempDto() {
	}

	/** status_PROCEDURE_PARAMETER */
	public static final String status_PROCEDURE_PARAMETER = "return";

	/** itemCd_PROCEDURE_PARAMETER */
	public static final String itemCd_PROCEDURE_PARAMETER = "in";

	/** version_PROCEDURE_PARAMETER */
	public static final String version_PROCEDURE_PARAMETER = "in";

	/** outMsg_PROCEDURE_PARAMETER */
	public static final String outMsg_PROCEDURE_PARAMETER = "out";

	private BigDecimal status;

	private String itemCd;

	/** バージョン */
	private BigDecimal version;

	/** リターンメッセージ */
	private String outMsg;

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}
	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * seqを取得します。
	 * @return itemCd
	 */
	public BigDecimal getStatus() {
		return status;
	}
	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setStatus(final BigDecimal seq) {
		this.status = seq;
	}
	/**
	 * バージョンを取得します。
	 * @return バージョン
	 */
	public BigDecimal getVersion() {
		return version;
	}
	/**
	 * バージョンを設定します。
	 * @param version バージョン
	 */
	public void setVersion(final BigDecimal version) {
		this.version = version;
	}
	/**
	 * リターンメッセージを取得します。
	 * @return リターンメッセージ
	 */
	public String getOutMsg() {
		return outMsg;
	}
	/**
	 * リターンメッセージを設定します。
	 * @param outMsg リターンメッセージ
	 */
	public void setOutMsg(final String outMsg) {
		this.outMsg = outMsg;
	}

}
