/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売上詳細(預り品)画面_得意先取得用データ格納クラス.
 * 
 * @author tosco
 */
public class SalesDetailKeepVenderList extends SalesDetailKeepVenderListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション shopLevelName */
	public static final String shopLevelName_COLUMN = "SHOP_LEVEL_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** 次店名称 */
	private String shopLevelName;

	/** 得意先名称 */
	private String venderName1;

	/** 得意先略称 */
	private String venderShortedName;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailKeepVenderList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */
	/**
	 * 次店名称取得
	 * @return String 次店名称
	 */
	public String getShopLevelName() {
		return this.shopLevelName;
	}

	/**
	 * 次店名称設定
	 * @param shopLevelName 次店名称
	 */
	public void setShopLevelName(final String shopLevelName) {
		this.shopLevelName = shopLevelName;
	}

	/**
	 * 得意先名称取得
	 * @return String 得意先名称
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * 得意先名称設定
	 * @param venderName1 得意先名称
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}
}
