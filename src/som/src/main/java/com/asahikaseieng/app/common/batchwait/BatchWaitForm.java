/*
 * Created on 2014/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.batchwait;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 
 * バッチ待ちForm
 * @author atts
 */
public class BatchWaitForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	//
	// 検索用.
	//

	/**
	 * コンストラクタ.バッチ待ちForm
	 */
	public BatchWaitForm() {
	}

	//
	// インスタンスフィールド
	//

	/** タイトル名 */
	private String titleName;

	/** タイトル名 */
	private BigDecimal screenId;

	/** 枝番 */
	private String num;

	/** 枝番1 */
	private String num1;

	/** 枝番2 */
	private String num2;

	/** 枝番3 */
	private String num3;

	/** 枝番4 */
	private String num4;

	/** 枝番5 */
	private String num5;

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

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * titleNameを取得します。
	 * @return titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * titleNameを設定します。
	 * @param titleName titleName
	 */
	public void setTitleName(final String titleName) {
		this.titleName = titleName;
	}

	/**
	 * numを取得します。
	 * @return num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * numを設定します。
	 * @param num num
	 */
	public void setNum(final String num) {
		this.num = num;
	}

	/**
	 * screenIdを取得します。
	 * @return screenId
	 */
	public BigDecimal getScreenId() {
		return screenId;
	}

	/**
	 * screenIdを設定します。
	 * @param screenId screenId
	 */
	public void setScreenId(final BigDecimal screenId) {
		this.screenId = screenId;
	}

	/**
	 * num1を取得します。
	 * @return num1
	 */
	public String getNum1() {
		return num1;
	}

	/**
	 * num1を設定します。
	 * @param num1 num1
	 */
	public void setNum1(final String num1) {
		this.num1 = num1;
	}

	/**
	 * num2を取得します。
	 * @return num2
	 */
	public String getNum2() {
		return num2;
	}

	/**
	 * num2を設定します。
	 * @param num2 num2
	 */
	public void setNum2(final String num2) {
		this.num2 = num2;
	}

	/**
	 * num3を取得します。
	 * @return num3
	 */
	public String getNum3() {
		return num3;
	}

	/**
	 * num3を設定します。
	 * @param num3 num3
	 */
	public void setNum3(final String num3) {
		this.num3 = num3;
	}

	/**
	 * num4を取得します。
	 * @return num4
	 */
	public String getNum4() {
		return num4;
	}

	/**
	 * num4を設定します。
	 * @param num4 num4
	 */
	public void setNum4(final String num4) {
		this.num4 = num4;
	}

	/**
	 * num5を取得します。
	 * @return num5
	 */
	public String getNum5() {
		return num5;
	}

	/**
	 * num5を設定します。
	 * @param num5 num5
	 */
	public void setNum5(final String num5) {
		this.num5 = num5;
	}
}
