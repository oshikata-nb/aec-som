/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 処方その他用データ格納クラス.
 *
 * @author tosco
 */
public class GrecipeRecipeRemarkList extends GrecipeRecipeRemarkListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;
	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public GrecipeRecipeRemarkList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

}
