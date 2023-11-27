/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderrecieve.ordervendermaster;


/**
 * OrderVenderMasterDaoクラス
 * @author 
 */
public interface OrderVenderMasterDao {

	/** BEANアノテーション */
	Class<OrderVenderMaster> BEAN = OrderVenderMaster.class;

	/**
	 * Insert.
	 * @param bean OrderVenderMaster
	 * @return Insert件数
	 */
	int insert(OrderVenderMaster bean);
	
	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "VENDER_GROUP_CD,BALANCE_CD";
	
	/**
	 * エンティティ取得.
	 * @param venderGroupCd String
	 * @param balanceCd String
	 * @return OrderVenderMaster
	 */
	OrderVenderMaster getEntity(String venderGroupCd, String balanceCd);

}
