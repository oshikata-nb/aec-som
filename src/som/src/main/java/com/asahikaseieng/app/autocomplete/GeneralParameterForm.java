/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete;

import java.util.List;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 汎用パラ-メータFormクラス
 * AutoBeanみたく、ある特定処理用のフィールドを追加しないこと。
 * その場合は、継承して拡張すること。
 * （何のために、クラス名に汎用と付けているか考えること)
 * @author tosco
 */
public class GeneralParameterForm extends AbstractForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** コード */
	private String code;
	/** 検索結果格納リスト(汎用に使用できるように、敢えてGenericは使用しない) */
	private List result;
	/**
	 * コンストラクタ
	 */
	public GeneralParameterForm() {
	}
	/**
	 * コンストラクタ
	 * @param code コード
	 */
	public GeneralParameterForm(final String code) {
		this.code = code;
	}
	/**
	 * コードを取得します。
	 * @return コード
	 */
	public String getCode() {
		return code;
	}
	/**
	 * コードを設定します。
	 * @param code コード
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	/**
	 * 検索結果格納リストを取得します。
	 * @return 検索結果格納リスト
	 */
	public List getResult() {
		return result;
	}
	/**
	 * 検索結果格納リストを設定します。
	 * @param result 検索結果格納リスト
	 */
	public void setResult(final List result) {
		this.result = result;
	}
}
