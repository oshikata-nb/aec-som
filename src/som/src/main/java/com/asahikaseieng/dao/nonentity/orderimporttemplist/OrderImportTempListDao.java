
package com.asahikaseieng.dao.nonentity.orderimporttemplist;

import java.util.List;


public interface OrderImportTempListDao {

	/** BEANアノテーション */
	Class<OrderImportTempList> BEAN = com.asahikaseieng.dao.nonentity.orderimporttemplist.OrderImportTempList.class;
	
	/** ARGSアノテーション getAecList */
	String getCheckList = "seq";

	/**
	 * Listメソッド
	 * @param seq
	 * @return List<OrderImportTempList>
	 */
	List<OrderImportTempList> getCheckList(final String seq);
	
	
	
	/** ARGSアノテーション getDetailList */
	String getDetailList = "seq";

	/**
	 * Listメソッド
	 * @param seq
	 * @return List<OrderImportTempList>
	 */
	List<OrderImportTempList> getDetailList(final String seq);

	/** ARGSアノテーション getAecList */
	String getAecList = "seq";

	/**
	 * Listメソッド
	 * @param seq
	 * @return List<OrderImportTempList>
	 */
	List<OrderImportTempList> getAecDataList(final String seq);
	
	/** ARGSアノテーション getMd5Check */
	String getMd5Check_ARGS = "md5Checksum";
	
	/**
	 * 
	 * 過去1か月間のMD5チェックサムリストに同じMD5チェックサム値があれば取得する
	 * @param md5Checksum
	 * @return OrderImportTempList
	 */
	OrderImportTempList getMd5Check(String md5Checksum);
	
	/** ARGSアノテーション getMd5CheckList */
	String getMd5CheckList_ARGS = "";
	
	/**
	 * 
	 * 過去1か月間のMD5チェックサムリストを取得する
	 * @return List<OrderImportTempList>
	 */
	List<OrderImportTempList> getMd5CheckList();

}
