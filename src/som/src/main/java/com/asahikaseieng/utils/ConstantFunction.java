/*
 * Created on 2008/08/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

/**
 * 業務共通の定数.
 * @author tosco
 */
public final class ConstantFunction {
	/**
	 * コンストラクタ
	 */
	private ConstantFunction() {
	}

	//承認ステータス
	/** 承認ステータス値-入力中 */
	public static final String APPROVAL_STATUS_INPUT = "1";
	/** 承認ステータス値-依頼中 */
	public static final String APPROVAL_STATUS_REQUEST = "2";
	/** 承認ステータス値-承認済み */
	public static final String APPROVAL_STATUS_APPROVAL = "3";

	/** 承認ステータスラベル-入力中 */
	public static final String APPROVAL_STATUS_INPUT_LABEL = "入力中";
	/** 承認ステータスラベル-依頼中 */
	public static final String APPROVAL_STATUS_REQUEST_LABEL = "依頼中";
	/** 承認ステータスラベル-承認済み */
	public static final String APPROVAL_STATUS_APPROVAL_LABEL = "承認済";

	/** 承認ステータス値-全て */
	public static final String APPROVAL_STATUS_ALL = "0";
	/** 承認ステータスラベル-全て */
	public static final String APPROVAL_STATUS_ALL_LABEL = "全て";

	/** 月末指定(締日) */
	public static final int LASTDAY_OF_MONTH = 99;


}
