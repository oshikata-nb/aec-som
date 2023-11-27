/*
 * Created on 2013/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.commonproc;

import java.math.BigDecimal;


/**
 * CommonProcDaoクラス.
 * 
 * @author AECS
 */
public interface CommonProcDao {

	/** BEANアノテーション. */
	@SuppressWarnings("rawtypes")
	Class BEAN = CommonProc.class;

	/** ARGSアノテーション getStringData */
	String getStringData_ARGS = "division,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10";

	/**
	 * 実行処理
	 * @division 処理区分 
	 * @param para01～para10
	 * @return BigDecimal
	 */
	String getStringData(final String division,final String para01,final String para02,final String para03,final String para04,final String para05,final String para06,final String para07,final String para08,final String para09,final String para10);

	/** ARGSアノテーション getNumericData */
	String getNumericData_ARGS = "division,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10";

	/**
	 * 実行処理
	 * @division 処理区分 
	 * @param para01～para10
	 * @return BigDecimal
	 */
	BigDecimal getNumericData(final String division,final String para01,final String para02,final String para03,final String para04,final String para05,final String para06,final String para07,final String para08,final String para09,final String para10);

	/** ARGSアノテーション getNumericData */
//	String execute_ARGS = "division,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10";

	/**
	 * 実行処理
	 * @division 処理区分 
	 * @param para01～para10
	 * @return BigDecimal
	 */
//	BigDecimal execute(final String division,final String para01,final String para02,final String para03,final String para04,final String para05,final String para06,final String para07,final String para08,final String para09,final String para10);

}
