/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport;

import java.util.List;

/**
 * OrderVenderLinkListForReportDaoクラス
 * @author 
 */
public interface OrderVenderLinkListForReportDao {

	/** BEANアノテーション */
	Class<OrderVenderLinkListForReport> BEAN = com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport.OrderVenderLinkListForReport.class;

	/** ARGSアノテーション getDeliveryListForReport */
	String getDeliveryListForReport_ARGS = "venderGroupCd";

	/**
	 * getDeliveryListForReportメソッド
	 * 
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	List<OrderVenderLinkListForReport> getDeliveryListForReport(
			final String venderGroupCd);
	
	
	/** ARGSアノテーション getItemListForReport */
	String getItemListForReport_ARGS = "venderGroupCd";
	
	/**
	 * getItemListForReportメソッド
	 * 
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	List<OrderVenderLinkListForReport> getItemListForReport(
			final String venderGroupCd);
	
	/** ARGSアノテーション getDeliveryListInSalesTerms */
	String getDeliveryListInSalesTerms_ARGS = "venderGroupCd";
	
	/**
	 * getDeliveryListInSalesTermsメソッド
	 * 
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	List<OrderVenderLinkListForReport> getDeliveryListInSalesTerms(final String venderGroupCd);
	
	/** ARGSアノテーション getItemListInSalesTerms */
	String getItemListInSalesTerms_ARGS = "venderGroupCd";
	
	/**
	 * getItemListInSalesTermsメソッド
	 * 
	 * @param venderGroupCd 得意先グループ番号
	 * @return List<OrderVenderLinkListForReport>
	 */
	List<OrderVenderLinkListForReport> getItemListInSalesTerms(final String venderGroupCd);

}
