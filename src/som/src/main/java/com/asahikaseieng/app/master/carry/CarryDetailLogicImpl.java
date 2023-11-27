/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.entity.master.carry.CarryDao;
import com.asahikaseieng.dao.nonentity.common.commonproc.CommonProcDao;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetail;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCtlCarryBcSeqDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 運送会社詳細ロジック 実装クラス.
 * @author t0011036
 */
public class CarryDetailLogicImpl implements CarryDetailLogic {

	private CarryDao carryDao;

	private CarryDetailDao carryDetailDao;
	
	private ProcedureCallDao procedureCallDao;
	
	/**
	 * コンストラクタ.
	 */
	public CarryDetailLogicImpl() {
	}

	/**
	 * 運送会社検索（登録用）
	 * @param carryCd 運送会社コード
	 * @return Carry
	 * @throws NoDataException NoDataException
	 */
	public Carry getEntity(final String carryCd) throws NoDataException {
		if (StringUtils.isEmpty(carryCd)) {
			throw new IllegalArgumentException("carryCd is empty");
		}

		Carry bean = carryDao.getEntity(carryCd);

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
	public CarryDetail getDetailEntity(final String carryCd) {
		CarryDetail bean = carryDetailDao.getEntity(carryCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Carry bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			carryDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Carry bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			carryDao.insert(bean);
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
	public void delete(final Carry bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			carryDao.delete(bean);
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

	
	/* -------------------- setter -------------------- */

	/**
	 * carryDaoを設定します。
	 * @param carryDao carryDao
	 */
	public void setCarryDao(final CarryDao carryDao) {
		this.carryDao = carryDao;
	}

	/**
	 * carryDetailDaoを設定します。
	 * @param carryDetailDao carryDetailDao
	 */
	public void setCarryDetailDao(final CarryDetailDao carryDetailDao) {
		this.carryDetailDao = carryDetailDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	
}
