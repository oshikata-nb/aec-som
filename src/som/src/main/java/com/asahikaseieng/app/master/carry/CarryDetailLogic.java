/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCtlCarryBcSeqDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送会社詳細ロジック interface.
 * @author t0011036
 */
public interface CarryDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param carryCd 運送会社コード
	 * @throws NoDataException NoDataException
	 * @return Carry
	 */
	Carry getEntity(final String carryCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param carryCd 運送会社コード
	 * @return CarryDetail
	 */
	CarryDetail getDetailEntity(final String carryCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Carry bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Carry bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Carry bean) throws NoDataException;
	
	/**
	 * 運送会社連番管理
	 * @param carryCd 運送会社コード
	 * @param paymentScheduledAmount 支払予定額
	 * @return サイト情報
	 */
	public void ctlCarryBCSeq(final String pCarryCd 
			, final BigDecimal pMinValue
			, final BigDecimal pMaxValue
			, final BigDecimal pCurValue 
			, final BigDecimal pDelFlg
			);
}
