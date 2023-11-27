/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.remark.Remark;
import com.asahikaseieng.dao.entity.master.remark.RemarkDao;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersionDao;
import com.asahikaseieng.dao.nonentity.master.remarkdetail.RemarkDetail;
import com.asahikaseieng.dao.nonentity.master.remarkdetail.RemarkDetailDao;
import com.asahikaseieng.dao.nonentity.master.remarkdetailcheck.RemarkDetailCheck;
import com.asahikaseieng.dao.nonentity.master.remarkdetailcheck.RemarkDetailCheckDao;
import com.asahikaseieng.dao.nonentity.master.remarkdetailgetmaxno.RemarkDetailGetMaxRemarkNo;
import com.asahikaseieng.dao.nonentity.master.remarkdetailgetmaxno.RemarkDetailGetMaxRemarkNoDao;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 備考詳細ロジック 実装クラス.
 * @author t0011036
 */
public class RemarkDetailLogicImpl implements RemarkDetailLogic {

	private RemarkDao remarkDao;

	private RemarkDetailDao remarkDetailDao;

	private VenderDetailDao venderDetailDao;

	private DeliveryDetailDao deliveryDetailDao;

	private ItemQueueLastVersionDao itemQueueLastVersionDao;

	private RemarkDetailGetMaxRemarkNoDao remarkDetailGetMaxRemarkNoDao;

	private RemarkDetailCheckDao remarkDetailCheckDao;

	/**
	 * コンストラクタ.
	 */
	public RemarkDetailLogicImpl() {
	}

	/**
	 * 備考検索（登録用）
	 * @param remarkNo 備考コード
	 * @return Remark
	 * @throws NoDataException NoDataException
	 */
	public Remark getEntity(final BigDecimal remarkNo) throws NoDataException {
		Remark bean = remarkDao.getEntity(remarkNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 備考検索（詳細用）
	 * @param remarkNo 備考コード
	 * @return RemarkDetail
	 */
	public RemarkDetail getDetailEntity(final BigDecimal remarkNo) {
		RemarkDetail bean = remarkDetailDao.getEntity(remarkNo);
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
	 * 納入先検索
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	public DeliveryDetail getDeliveryEntity(final String deliveryCd) {
		DeliveryDetail bean = deliveryDetailDao.getEntity(deliveryCd);
		return bean;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	public ItemQueueLastVersion getItemQueueEntity(final String itemCd) {
		ItemQueueLastVersion bean = itemQueueLastVersionDao
				.getLastVersion(itemCd);
		return bean;
	}

	/**
	 * 最大備考番号取得
	 * @return RemarkDetailGetMaxRemarkNo
	 */
	public RemarkDetailGetMaxRemarkNo getMaxRemarkNo() {
		RemarkDetailGetMaxRemarkNo bean = remarkDetailGetMaxRemarkNoDao
				.getMaxRemarkNo();
		return bean;
	}

	/**
	 * 重複チェック
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @param deliveryCd 納入先コード
	 * @param itemCd 品目コード
	 * @return RemarkDetailCheck
	 */
	public RemarkDetailCheck getRemarkDetailCheck(final String venderDivision,
			final String venderCd, final String deliveryCd, final String itemCd) {
		RemarkDetailCheck bean = remarkDetailCheckDao.getRemarkDetailCheck(
			venderDivision, venderCd, deliveryCd, itemCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Remark bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			remarkDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Remark bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			remarkDao.insert(bean);
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
	public void delete(final Remark bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			remarkDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * remarkDaoを設定します。
	 * @param remarkDao remarkDao
	 */
	public void setRemarkDao(final RemarkDao remarkDao) {
		this.remarkDao = remarkDao;
	}

	/**
	 * remarkDetailDaoを設定します。
	 * @param remarkDetailDao remarkDetailDao
	 */
	public void setRemarkDetailDao(final RemarkDetailDao remarkDetailDao) {
		this.remarkDetailDao = remarkDetailDao;
	}

	/**
	 * deliveryDetailDaoを設定します。
	 * @param deliveryDetailDao deliveryDetailDao
	 */
	public void setDeliveryDetailDao(final DeliveryDetailDao deliveryDetailDao) {
		this.deliveryDetailDao = deliveryDetailDao;
	}

	/**
	 * venderDetailDaoを設定します。
	 * @param venderDetailDao venderDetailDao
	 */
	public void setVenderDetailDao(final VenderDetailDao venderDetailDao) {
		this.venderDetailDao = venderDetailDao;
	}

	/**
	 * remarkDetailGetMaxRemarkNoDaoを設定します。
	 * @param remarkDetailGetMaxRemarkNoDao remarkDetailGetMaxRemarkNoDao
	 */
	public void setRemarkDetailGetMaxRemarkNoDao(
			final RemarkDetailGetMaxRemarkNoDao remarkDetailGetMaxRemarkNoDao) {
		this.remarkDetailGetMaxRemarkNoDao = remarkDetailGetMaxRemarkNoDao;
	}

	/**
	 * remarkDetailCheckDaoを設定します。
	 * @param remarkDetailCheckDao remarkDetailCheckDao
	 */
	public void setRemarkDetailCheckDao(
			final RemarkDetailCheckDao remarkDetailCheckDao) {
		this.remarkDetailCheckDao = remarkDetailCheckDao;
	}

	/**
	 * itemQueueLastVersionDaoを設定します。
	 * @param itemQueueLastVersionDao itemQueueLastVersionDao
	 */
	public void setItemQueueLastVersionDao(
			final ItemQueueLastVersionDao itemQueueLastVersionDao) {
		this.itemQueueLastVersionDao = itemQueueLastVersionDao;
	}
}
