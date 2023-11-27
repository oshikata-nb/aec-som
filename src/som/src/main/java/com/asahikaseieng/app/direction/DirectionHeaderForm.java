/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * 製造指図ヘッダー画面Form
 * @author tosco
 */
public class DirectionHeaderForm extends AbstractDirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 入力用ホールドタンクNo */
	private String inputHoldTankNo;
	/** ホールドタンク名 */
	private String holdTankName;
	/** ロット番号 */
	private String lotNo;
	/** 備考 */
	private String remark;
	/** 注釈 */
	private String notes;
	/**
	 * コンストラクタ
	 */
	public DirectionHeaderForm() {
	}
	/**
	 * リセット
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping, final HttpServletRequest request) {
		super.reset(mapping, request);
		clear();
	}
	/**
	 * クリア
	 */
	@Override
	protected void clear() {
		if ("init".equals(getOp())) {
			super.clear();
			setHoldTankName(null);
			setRemark(null);
			setNotes(null);
			setLotNo(null);
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			//更新時のみvalidateを行う
			errors = super.validate(mapping, request);
		}
		return errors;
	}


	//setter,getter---------------------------------------------------------
	/**
	 * ホールドタンク名を取得します。
	 * @return ホールドタンク名
	 */
	public String getHoldTankName() {
		return holdTankName;
	}

	/**
	 * ホールドタンク名を設定します。
	 * @param holdTankName ホールドタンク名
	 */
	public void setHoldTankName(final String holdTankName) {
		this.holdTankName = holdTankName;
	}
	/**
	 * 備考を取得します。
	 * @return 備考
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * 備考を設定します。
	 * @param remark 備考
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}


	/**
	 * 注釈を取得します。
	 * @return 注釈
	 */
	public String getNotes() {
		return notes;
	}


	/**
	 * 注釈を設定します。
	 * @param notes 注釈
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
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
	 * 入力用ホールドタンクNoを取得します。
	 * @return 入力用ホールドタンクNo
	 */
	public String getInputHoldTankNo() {
		return inputHoldTankNo;
	}
	/**
	 * 入力用ホールドタンクNoを設定します。
	 * @param inputHoldTankNo 入力用ホールドタンクNo
	 */
	public void setInputHoldTankNo(final String inputHoldTankNo) {
		this.inputHoldTankNo = inputHoldTankNo;
	}
}
