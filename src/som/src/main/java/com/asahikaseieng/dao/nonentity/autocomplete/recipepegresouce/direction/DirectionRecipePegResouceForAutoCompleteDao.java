/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipepegresouce.direction;

import java.util.List;

/**
 * ホールドタンクNOオートコンプリート用Daoインターフェース.
 * 
 * @author tosco
 */
public interface DirectionRecipePegResouceForAutoCompleteDao {

	/** BEANアノテーション */
	Class<DirectionRecipePegResouceForAutoComplete> BEAN = DirectionRecipePegResouceForAutoComplete.class;

	// method---------------------------------------------

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "compoundTankNo,holdTankNo,rowlimit";

	/**
	 * エンティティ取得.
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールとタンクNO
	 * @param rowlimit 行上限
	 * @return List<DirectionRecipePegResouceForAutoComplete>
	 */
	List<DirectionRecipePegResouceForAutoComplete> getSearchList(
			String compoundTankNo, String holdTankNo, final String rowlimit);

}
