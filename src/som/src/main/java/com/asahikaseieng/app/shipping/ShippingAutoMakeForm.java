/*
 * Created on 2009/02/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 出荷指図指図自動作成 Formクラス.
 * @author tosco
 */
public final class ShippingAutoMakeForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 出荷予定日FROM */
	private String scheduledShippingDateFrom;

	/** 出荷予定日TO */
	private String scheduledShippingDateTo;

	/** 出荷予定日TO */
	private String procNum;

	/* ログ出力用エラーコード */
	private String errorCd;

	/* ログ出力用エラーメッセージ */
	private String errorMsg;

	/**
	 * コンストラクタ.
	 */
	public ShippingAutoMakeForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {

		ActionErrors errors = null;

		if ("autoMake".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.出荷指図指図自動作成
	 */
	public void clear() {
		/** 出荷予定日FROM */
		setScheduledShippingDateFrom(null);
		/** 出荷予定日TO */
		setScheduledShippingDateTo(null);
		/** エラーコード */
		setErrorCd(null);
		/** エラーメッセージ */
		setErrorMsg(null);

		setProcNum(null);
	}

	/**
	 * 出荷予定日FROM取得.
	 * @return String 出荷予定日FROM
	 */
	public String getScheduledShippingDateFrom() {
		return this.scheduledShippingDateFrom;
	}

	/**
	 * 出荷予定日FROM設定.
	 * @param scheduledShippingDateFrom 出荷予定日FROM
	 */
	public void setScheduledShippingDateFrom(
			final String scheduledShippingDateFrom) {
		this.scheduledShippingDateFrom = scheduledShippingDateFrom;
	}

	/**
	 * 出荷予定日TO取得.
	 * @return String 出荷予定日TO
	 */
	public String getScheduledShippingDateTo() {
		return this.scheduledShippingDateTo;
	}

	/**
	 * 出荷予定日TO設定.
	 * @param scheduledShippingDateTo 出荷予定日TO
	 */
	public void setScheduledShippingDateTo(final String scheduledShippingDateTo) {
		this.scheduledShippingDateTo = scheduledShippingDateTo;
	}

	/**
	 * エラーコードの取得.
	 * @return String エラーコード
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * エラーコードの設定.
	 * @param errorCd エラーコード
	 */
	public void setErrorCd(final String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * エラーメッセージ取得.
	 * @return String エラーメッセージ
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * エラーメッセージ設定
	 * @param errorMsg エラーメッセージ
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 処理数を取得.
	 * @return String 処理数
	 */
	public String getProcNum() {
		return procNum;
	}

	/**
	 * 処理数を設定
	 * @param procNum 処理数
	 */
	public void setProcNum(final String procNum) {
		this.procNum = procNum;
	}
}
