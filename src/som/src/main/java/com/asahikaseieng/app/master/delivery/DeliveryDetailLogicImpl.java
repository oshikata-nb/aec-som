/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.entity.master.delivery.DeliveryDao;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetail;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetailDao;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetailDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetailDao;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 納入先詳細ロジック 実装クラス.
 * @author t0011036
 */
public class DeliveryDetailLogicImpl implements DeliveryDetailLogic {

	private DeliveryDao deliveryDao;

	private DeliveryDetailDao deliveryDetailDao;

	private CarryDetailDao carryDetailDao;

	private LoginDetailDao loginDetailDao;

	private VenderDetailDao venderDetailDao;

	/**
	 * コンストラクタ.
	 */
	public DeliveryDetailLogicImpl() {
	}

	/**
	 * 納入先検索（登録用）
	 * @param deliveryCd 納入先コード
	 * @return Delivery
	 * @throws NoDataException NoDataException
	 */
	public Delivery getEntity(final String deliveryCd) throws NoDataException {
		if (StringUtils.isEmpty(deliveryCd)) {
			throw new IllegalArgumentException("deliveryCd is empty");
		}

		Delivery bean = deliveryDao.getEntity(deliveryCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 納入先検索（詳細用）
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	public DeliveryDetail getDetailEntity(final String deliveryCd) {
		DeliveryDetail bean = deliveryDetailDao.getEntity(deliveryCd);
		return bean;
	}

	/**
	 * 運送会社検索
	 * @param carryCd 運送会社コード
	 * @return CarryDetail
	 */
	public CarryDetail getCarryEntity(final String carryCd) {
		CarryDetail bean = carryDetailDao.getEntity(carryCd);
		return bean;
	}

	/**
	 * 営業担当者検索
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	public LoginDetail getLoginEntity(final String tantoCd) {
		LoginDetail bean = loginDetailDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * 取引先検索
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return VenderDetail
	 */
	public VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd) {
		VenderDetail bean = venderDetailDao.getEntity(venderDivision, venderCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Delivery bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			deliveryDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Delivery bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			deliveryDao.insert(bean);
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
	public void delete(final Delivery bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			deliveryDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * deliveryDaoを設定します。
	 * @param deliveryDao deliveryDao
	 */
	public void setDeliveryDao(final DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

	/**
	 * deliveryDetailDaoを設定します。
	 * @param deliveryDetailDao deliveryDetailDao
	 */
	public void setDeliveryDetailDao(final DeliveryDetailDao deliveryDetailDao) {
		this.deliveryDetailDao = deliveryDetailDao;
	}

	/**
	 * carryDetailDaoを設定します。
	 * @param carryDetailDao carryDetailDao
	 */
	public void setCarryDetailDao(final CarryDetailDao carryDetailDao) {
		this.carryDetailDao = carryDetailDao;
	}

	/**
	 * loginDetailDaoを設定します。
	 * @param loginDetailDao loginDetailDao
	 */
	public void setLoginDetailDao(final LoginDetailDao loginDetailDao) {
		this.loginDetailDao = loginDetailDao;
	}

	/**
	 * venderDetailDaoを設定します。
	 * @param venderDetailDao venderDetailDao
	 */
	public void setVenderDetailDao(final VenderDetailDao venderDetailDao) {
		this.venderDetailDao = venderDetailDao;
	}
}
