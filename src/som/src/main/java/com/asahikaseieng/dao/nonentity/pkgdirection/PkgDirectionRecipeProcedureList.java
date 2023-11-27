/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装指図－処方プロシージャデータ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionRecipeProcedureList extends PkgDirectionRecipeProcedureListBase
	implements PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeProcedureList() {
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
