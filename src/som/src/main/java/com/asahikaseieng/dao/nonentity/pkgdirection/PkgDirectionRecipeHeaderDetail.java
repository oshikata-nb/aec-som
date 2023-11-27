/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装指図－処方ヘッダーデータ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionRecipeHeaderDetail extends PkgDirectionRecipeHeaderDetailBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeHeaderDetail() {
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
