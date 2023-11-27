/*
 * Created on 2020/12/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlog;

import java.math.BigDecimal;
import java.util.List;

/**
 * RepOrdDDContactDaoクラス
 * @author 
 */
public interface OrderImportLogDao {

	/** BEANアノテーション */
	Class<OrderImportLog> BEAN = com.asahikaseieng.dao.nonentity.orderimportlog.OrderImportLog.class;

	/** ARGSアノテーション insertDetailLog */
	String insertHeadLog_ARGS = "logCls,frstOrderNo,logSeq,tantoNo";

	/** ARGSアノテーション insertDetailLog */
	String insertDetailLog_ARGS = "logCls,frstOrderNo,logSeq,tantoNo";

	/**
	 * ログ登録
	 * 
	 * @param pkNo
	 * @param pkRow
	 * @return entity
	 */
	int insertHeadLog(String logCls , String frstOrderNo, BigDecimal logSeq , String tantoCd);


	
	/**
	 * ログ登録
	 * 
	 * @param pkNo
	 * @param pkRow
	 * @return entity
	 */
	int insertDetailLog(String logCls , String frstOrderNo, BigDecimal logSeq , String tantoCd);

	/**
	 * 連番取得
	 * @param pkNo
	 * @param pkRow
	 * @param sendLogSeq
	 * @param tantoCd
	 * @return
	 */
	OrderImportLog getLogSeq();

}
