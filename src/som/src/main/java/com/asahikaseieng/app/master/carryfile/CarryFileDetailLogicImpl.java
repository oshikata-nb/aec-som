/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.carryfilelayout.CarryFileLayout;
import com.asahikaseieng.dao.entity.master.carryfilelayout.CarryFileLayoutDao;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileComboboxItems;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileComboboxItemsDao;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileDetail;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCtlCarryBcSeqDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 運送会社詳細ロジック 実装クラス.
 * @author t0011036
 */
public class CarryFileDetailLogicImpl implements CarryFileDetailLogic {

	private ProcedureCallDao procedureCallDao;
	
	private CarryFileComboboxItemsDao carryFileComboboxItemsDao;
	
	private CarryFileDetailDao carryFileDetailDao;
	
	private CarryFileLayoutDao carryFileLayoutDao;
	
	private List<CarryFileComboboxItems> carryFileComboboxItems = null;
	
	/**
	 * コンストラクタ.
	 */
	public CarryFileDetailLogicImpl() {
	}

	/**
	 * 運送会社検索（登録用）
	 * @param carryCd 運送会社コード
	 * @return Carry
	 * @throws NoDataException NoDataException
	 */
	public List<CarryFileLayout> getEntity(final String carryCd) throws NoDataException {
		if (StringUtils.isEmpty(carryCd)) {
			throw new IllegalArgumentException("carryCd is empty");
		}

		List<CarryFileLayout> bean = this.carryFileLayoutDao.getEntity(carryCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 運送会社検索（詳細用）
	 * @param carryCd 運送会社コード
	 * @return CarryDetail
	 */
	public List<CarryFileDetail> getDetailEntity(final String carryCd) {
		return this.carryFileDetailDao.getEntity(carryCd);
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final List<CarryFileLayout> beans) {
		if (beans == null || beans.size() == 0) {
			throw new IllegalArgumentException("beans == null");
		}

		try {
			/* 更新処理 */
			for( CarryFileLayout bean : beans ){
				this.carryFileLayoutDao.update(bean);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final List<CarryFileLayout> beans){
		if (beans == null || beans.size() == 0) {
			throw new IllegalArgumentException("beans == null");
		}

		try {
			/* 追加処理 */
			for( CarryFileLayout bean : beans ){
				this.carryFileLayoutDao.insert(bean);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final List<CarryFileLayout> beans ) throws NoDataException {

		try {
			/* 削除処理 */
			for( CarryFileLayout bean : beans ){
				this.carryFileLayoutDao.delete(bean);
			}
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

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
			) {
		ProCtlCarryBcSeqDto dto = new ProCtlCarryBcSeqDto();

		dto.setPCarryCd(pCarryCd);
		dto.setPMinValue(pMinValue);
		dto.setPMaxValue(pMaxValue);
		dto.setPCurValue(pCurValue);
		dto.setPDelFlg(pDelFlg);
	
		procedureCallDao.proCtlCarryBcSeq(dto);
	}

	/**
	 * コンボボックス選択リストの作成
	 * @return List<CompanyBankListForComboboxes>
	 */
	public List<CarryFileComboboxItems> getComboboxItems() {
		
		if( this.carryFileComboboxItems == null ){
			List<CarryFileComboboxItems> list = this.carryFileComboboxItemsDao.getComboboxItems();
			this.carryFileComboboxItems = list;
		}
		

		return this.carryFileComboboxItems;
}
	
	
	/* -------------------- setter -------------------- */
	
	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}


	/**
	 * carryFileComboboxItemsDaoを設定します。
	 * @param carryFileComboboxItemsDao carryFileComboboxItemsDao
	 */
	public void setCarryFileComboboxItemsDao(CarryFileComboboxItemsDao carryFileComboboxItemsDao) {
		this.carryFileComboboxItemsDao = carryFileComboboxItemsDao;
	}


	/**
	 * carryFileDetailDaoを設定します。
	 * @param carryFileDetailDao carryFileDetailDao
	 */
	public void setCarryFileDetailDao(CarryFileDetailDao carryFileDetailDao) {
		this.carryFileDetailDao = carryFileDetailDao;
	}


	/**
	 * carryFileLayoutDaoを設定します。
	 * @param carryFileLayoutDao carryFileLayoutDao
	 */
	public void setCarryFileLayoutDao(CarryFileLayoutDao carryFileLayoutDao) {
		this.carryFileLayoutDao = carryFileLayoutDao;
	}

	
}
