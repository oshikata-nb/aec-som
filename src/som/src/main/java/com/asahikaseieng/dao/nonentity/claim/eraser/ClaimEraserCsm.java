/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 消込入力詳細(カスタマイズ) 消込トランザクション(カスタマイズ)登録更新用Daoクラス.
 * @author tosco
 */
public class ClaimEraserCsm extends ClaimEraserCsmBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 消込チェックフラグ */
	private boolean checkFlg;

	/** 処理日付(スラッシュ編集) */
	private String strProcessingDate;

	/** 消込対象額(カンマ編集) */
	private String strEraserObjectAmount;

	/** 消込金額(カンマ編集) */
	private String strEraserAmount;

	/** 前回消込金額(カンマ編集) */
	private String strLastEraserAmount;

	/** 消込完了日付(スラッシュ編集) */
	private String strEraserCompleteDate;

	/** データ種別(編集) */
	private String strDataType;

	/** 消込日付 */
	private String strEraserDate;

	/** 消込金額(validate用) */
	// private String validateEraserAmount;
	/** 消込対象額(validate用) */
	// private String validateEraserObjectAmount;
	/**
	 * コンストラクタ.
	 */
	public ClaimEraserCsm() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックフラグ設定 */
		if ("0".equals(getCheckKbn())) {
			setCheckFlg(false);
		} else {
			setCheckFlg(true);
		}

		/* 日付にスラッシュを付与 */
		if (getProcessingDate() != null) {
			setStrProcessingDate(AecDateUtils.dateFormat(new Timestamp(
					getProcessingDate().getTime()), "yyyy/MM/dd"));
		}
		if (getEraserCompleteDate() != null) {
			setStrEraserCompleteDate(AecDateUtils.dateFormat(new Timestamp(
					getEraserCompleteDate().getTime()), "yyyy/MM/dd"));
		}
		if (getEraserDate() != null) {
			setStrEraserDate(AecDateUtils.dateFormat(new Timestamp(
					getEraserDate().getTime()), "yyyy/MM/dd"));
		}
		/* 数値にカンマを付与 */
		// setStrEraserObjectAmount(AecNumberUtils.decimalFormat(
		// getEraserObjectAmount(), "###,###.##"));
		// setStrEraserAmount(AecNumberUtils.decimalFormat(getEraserAmount(),
		// "###,###.##"));
		// setStrLastEraserAmount(AecNumberUtils.decimalFormat(
		// getLastEraserAmount(), "###,###.##"));
	}

	/**
	 * /** {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * 消込チェックフラグ取得.
	 * @return boolean 消込チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * 消込チェックフラグ設定.
	 * @param checkFlg 消込チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 処理日付(スラッシュ編集)取得.
	 * @return String 処理日付(スラッシュ編集)
	 */
	public String getStrProcessingDate() {
		return strProcessingDate;
	}

	/**
	 * 処理日付(スラッシュ編集)設定.
	 * @param strProcessingDate 処理日付(スラッシュ編集)
	 */
	public void setStrProcessingDate(final String strProcessingDate) {
		this.strProcessingDate = strProcessingDate;
	}

	/**
	 * 消込対象額(カンマ編集)取得.
	 * @return String 消込対象額(カンマ編集)
	 */
	public String getStrEraserObjectAmount() {
		return strEraserObjectAmount;
	}

	/**
	 * 消込対象額(カンマ編集)設定.
	 * @param strEraserObjectAmount 消込対象額(カンマ編集)
	 */
	public void setStrEraserObjectAmount(final String strEraserObjectAmount) {
		this.strEraserObjectAmount = strEraserObjectAmount;
	}

	/**
	 * 消込金額(カンマ編集)取得.
	 * @return String 消込金額(カンマ編集)
	 */
	public String getStrEraserAmount() {
		return strEraserAmount;
	}

	/**
	 * 消込金額(カンマ編集)設定.
	 * @param strEraserAmount 消込金額(カンマ編集)
	 */
	public void setStrEraserAmount(final String strEraserAmount) {
		this.strEraserAmount = strEraserAmount;
	}

	/**
	 * 前回消込金額(カンマ編集)取得.
	 * @return String 前回消込金額(カンマ編集)
	 */
	public String getStrLastEraserAmount() {
		return strLastEraserAmount;
	}

	/**
	 * 前回消込金額(カンマ編集)設定.
	 * @param strLastEraserAmount 前回消込金額(カンマ編集)
	 */
	public void setStrLastEraserAmount(final String strLastEraserAmount) {
		this.strLastEraserAmount = strLastEraserAmount;
	}

	/**
	 * 消込完了日付(スラッシュ編集)取得.
	 * @return String 消込完了日付(スラッシュ編集)
	 */
	public String getStrEraserCompleteDate() {
		return strEraserCompleteDate;
	}

	/**
	 * 消込完了日付(スラッシュ編集)設定.
	 * @param strEraserCompleteDate 消込完了日付(スラッシュ編集)
	 */
	public void setStrEraserCompleteDate(final String strEraserCompleteDate) {
		this.strEraserCompleteDate = strEraserCompleteDate;
	}

	/**
	 * データ種別(編集)取得.
	 * @return String データ種別(編集)
	 */
	public String getStrDataType() {
		int dataType = getDataType().intValue();
		switch (dataType) {
		case 1:
			this.strDataType = "1:売上";
			break;
		case 2:
			this.strDataType = "2:入金";
			break;
		case 4:
			this.strDataType = "4:支払";
			break;
		case 5:
			this.strDataType = "5:グループ間相殺";
			break;
		case 9:
			this.strDataType = "9:消費税";
			break;
		default:
			this.strDataType = getDataType().toString();
		}
		return strDataType;
	}

	/**
	 * 消込金額(validate用)取得.
	 * @return String 消込金額(validate用)
	 */
	public String getValidateEraserAmount() {
		String res = null;
		String amount = getStrEraserAmount();
		if (StringUtils.isNotEmpty(amount)) {
			res = amount.replace(",", "");
		}
		return res;
	}

	/**
	 * 消込対象額(validate用)取得.
	 * @return String 消込対象額(validate用)
	 */
	public String getValidateEraserObjectAmount() {
		String res = null;
		String amount = getStrEraserObjectAmount();
		if (StringUtils.isNotEmpty(amount)) {
			res = amount.replace(",", "");
		}
		return res;
	}

	/**
	 * strEraserDateを取得します。
	 * @return strEraserDate
	 */
	public String getStrEraserDate() {
		return strEraserDate;
	}

	/**
	 * strEraserDateを設定します。
	 * @param strEraserDate strEraserDate
	 */
	public void setStrEraserDate(final String strEraserDate) {
		this.strEraserDate = strEraserDate;
	}
}
