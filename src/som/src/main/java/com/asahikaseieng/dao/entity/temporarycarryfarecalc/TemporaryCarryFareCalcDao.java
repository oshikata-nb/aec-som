/*
 * Created on Wed Apr 14 2021/1/6
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarycarryfarecalc;
import java.math.BigDecimal;
import java.util.List;

/**
 * TemporaryCarryFareCalcDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryCarryFareCalcDao {

	/** BEANアノテーション. */
	Class<TemporaryCarryFareCalc> BEAN = TemporaryCarryFareCalc.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryCarryFareCalc
	 * @return Insert件数
	 */
	int insert(TemporaryCarryFareCalc bean);

	/**
	 * Update.
	 * @param bean TemporaryCarryFareCalc
	 * @return Update件数
	 */
	int update(TemporaryCarryFareCalc bean);

	/**
	 * Delete.
	 * @param bean TemporaryCarryFareCalc
	 * @return Delete件数
	 */
	int delete(TemporaryCarryFareCalc bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SEQUENCE_NO,ITEM_CD,DELIVERY_CD";

	/**
	 * エンティティ取得.
	 * @param rowNo rowNo
	 * @return TemporaryCarryFareCalc
	 */
	TemporaryCarryFareCalc getEntity(BigDecimal sequenceNo, String itemCd, String deliveryCd);

	/**
	 * リスト取得.
	 * @return TemporaryCarryFareCalcのリスト
	 */
	List<TemporaryCarryFareCalc> getList(BigDecimal sequanceNo);

}

