/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.master.carryfilelayout.CarryFileLayout;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileComboboxItems;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送会社詳細ロジック interface.
 * @author t0011036
 */
public interface CarryFileDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param carryCd 運送会社コード
	 * @throws NoDataException NoDataException
	 * @return Carry
	 */
	List<CarryFileLayout> getEntity(final String carryCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param carryCd 運送会社コード
	 * @return CarryDetail
	 */
	List<CarryFileDetail> getDetailEntity(final String carryCd);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final List<CarryFileLayout> beans);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final List<CarryFileLayout> beans) throws NoDataException;
	
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
	
	/**
	 * コンボボックス選択リストの作成
	 * @return List<CompanyBankListForComboboxes>
	 */
	public List<CarryFileComboboxItems> getComboboxItems();
	
}
