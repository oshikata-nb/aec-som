/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装指図－処方検査データ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionRecipeInspectionList extends PkgDirectionRecipeInspectionListBase
	implements PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeInspectionList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

}
