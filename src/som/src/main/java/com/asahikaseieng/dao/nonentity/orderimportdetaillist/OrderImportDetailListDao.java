/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdetaillist;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * OrderImportDetailDaoクラス
 * @author 
 */
public interface OrderImportDetailListDao {

	/** BEANアノテーション */
	Class<OrderImportDetailList> BEAN = com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "frstOrderNo";

	
	String getConcliftFrstOrderNo_ARGS = "orderDivision,venderCd,deliveryCd,balanceCd,scheduledShippingDate,deliveryExpectedDate,carryCd,frstOrderNo,deliveryAddress,deliveryTelNo";
	
	/**
	 * getListメソッド
	 * 
	 * @param pkNo String
	 * @return OrderImportDetailList
	 */
	List<OrderImportDetailList> getList(String frstOrderNo);

	/**
	 * 
	 * getConcliftFrstOrderNoメソッド
	 * @param orderDivision
	 * @param venderCd
	 * @param deliveryCd
	 * @param balanceCd
	 * @param scheduledShippingDate
	 * @param deliveryExpectedDate
	 * @param deliveryExpectedTime
	 * @param carryCd
	 * @return
	 */
	OrderImportDetailList getConcliftFrstOrderNo(BigDecimal orderDivision
		,String venderCd
		,String deliveryCd
		,String balanceCd
		,Timestamp scheduledShippingDate
		,Timestamp deliveryExpectedDate
		,String carryCd
		,String frstOrderNo
		,String deliveryAddress
		,String deliveryTelNo);

}
