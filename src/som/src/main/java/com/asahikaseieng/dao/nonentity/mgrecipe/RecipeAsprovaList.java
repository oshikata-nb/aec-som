/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 処方ASPROVA_データ格納クラス.
 * 
 * @author tosco
 */
public class RecipeAsprovaList extends RecipeAsprovaListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** COLUMNアノテーション resouceName */
	public static final String strCheckFlg_COLUMN = "STR_CHECK_FLG";

	/** 使用資源名 */
	private String resouceName;

	/** チェックフラグ String */
	private String strCheckFlg;

	/** チェックフラグ */
	private boolean checkFlg;

	/** 優先度 String */
	private String strRecipePriority;

	/** 工程作業時間1（x）String */
	private String strProcessWorkTime1;

	/** 工程作業時間2（分）String */
	private String strProcessWorkTime2;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public RecipeAsprovaList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {

		// チェックボックスの値を変換
		setCheckFlg(new Boolean(getStrCheckFlg()));

		// 数値をStringに変換
		if (getRecipePriority() != null) {
			setStrRecipePriority(getRecipePriority().toString());
		}
		if (getProcessWorkTime1() != null) {
			setStrProcessWorkTime1(getProcessWorkTime1().toString());
		}
		if (getProcessWorkTime2() != null) {
			setStrProcessWorkTime2(getProcessWorkTime2().toString());
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 使用資源名を取得します。
	 * @return resouceName
	 */
	public String getResouceName() {
		return resouceName;
	}

	/**
	 * 使用資源名を設定します。
	 * @param resouceName 使用資源名
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

	/**
	 * チェックフラグ Stringを取得します。
	 * @return strCheckFlg
	 */
	public String getStrCheckFlg() {
		return strCheckFlg;
	}

	/**
	 * チェックフラグ Stringを設定します。
	 * @param strCheckFlg チェックフラグ String
	 */
	public void setStrCheckFlg(final String strCheckFlg) {
		this.strCheckFlg = strCheckFlg;
	}

	/**
	 * チェックフラグを取得します。
	 * @return checkFlg
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定します。
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 工程作業時間1（x）Stringを取得します。
	 * @return strProcessWorkTime1
	 */
	public String getStrProcessWorkTime1() {
		return strProcessWorkTime1;
	}

	/**
	 * 工程作業時間1（x）Stringを設定します。
	 * @param strProcessWorkTime1 工程作業時間1（x）String
	 */
	public void setStrProcessWorkTime1(final String strProcessWorkTime1) {
		this.strProcessWorkTime1 = strProcessWorkTime1;
	}

	/**
	 * 工程作業時間2（分）Stringを取得します。
	 * @return strProcessWorkTime2
	 */
	public String getStrProcessWorkTime2() {
		return strProcessWorkTime2;
	}

	/**
	 * 工程作業時間2（分）Stringを設定します。
	 * @param strProcessWorkTime2 工程作業時間2（分）String
	 */
	public void setStrProcessWorkTime2(final String strProcessWorkTime2) {
		this.strProcessWorkTime2 = strProcessWorkTime2;
	}

	/**
	 * 優先度 Stringを取得します。
	 * @return strRecipePriority
	 */
	public String getStrRecipePriority() {
		return strRecipePriority;
	}

	/**
	 * 優先度 Stringを設定します。
	 * @param strRecipePriority 優先度 String
	 */
	public void setStrRecipePriority(final String strRecipePriority) {
		this.strRecipePriority = strRecipePriority;
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
