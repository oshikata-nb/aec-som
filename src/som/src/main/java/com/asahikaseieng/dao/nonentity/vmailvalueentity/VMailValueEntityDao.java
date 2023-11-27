/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.vmailvalueentity;

import java.util.List;

/**
 * VMailValueEntityDaoクラス
 * @author 
 */
public interface VMailValueEntityDao {

	/** BEANアノテーション */
	Class<VMailValueEntity> BEAN = com.asahikaseieng.dao.nonentity.vmailvalueentity.VMailValueEntity.class;

	/** ARGSアノテーション getParam */
	String getParam_ARGS = "mailFormatId, item1, item2, item3, item4, item5";

	/**
	 * getListメソッド
	 * 
	 * @param 
	 * @return VMailValueEntity
	 */
	List<VMailValueEntity> getParam(String mailFormatId, String item1, String item2, String item3, String item4, String item5);
}
