/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.util.List;


/**
 * 運送会社連携ファイルDaoインターフェース.
 *
 * @author kiguchi
 */
public interface CarryFileDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshipping.CarryFile.class;

	/** ARGSアノテーション getCarryFile */
	String getCarryFile_ARGS = "inputorCd";

	/**
	 * getCarryFileメソッド
	 * 
	 * @param inputorCd inputorCd
	 * @return CarryFile
	 */
	List<CarryFile> getCarryFile(
			final String inputorCd);

}
