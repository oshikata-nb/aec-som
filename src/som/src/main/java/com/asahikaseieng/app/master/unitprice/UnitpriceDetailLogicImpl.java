/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.unitprice.Unitprice;
import com.asahikaseieng.dao.entity.master.unitprice.UnitpriceDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersionDao;
import com.asahikaseieng.dao.nonentity.master.unitpricedeletelist.UnitpriceDeleteListDao;
import com.asahikaseieng.dao.nonentity.master.unitpricedetaillist.UnitpriceDetailList;
import com.asahikaseieng.dao.nonentity.master.unitpricedetaillist.UnitpriceDetailListDao;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 仕入先別単価詳細ロジック 実装クラス.
 * @author t0011036
 */
public class UnitpriceDetailLogicImpl implements UnitpriceDetailLogic {

	private UnitpriceDao unitpriceDao;

	private UnitpriceDetailListDao unitpriceDetailListDao;

	private UnitpriceDeleteListDao unitpriceDeleteListDao;

	private VenderDetailDao venderDetailDao;

	private ItemQueueLastVersionDao itemQueueLastVersionDao;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceDetailLogicImpl() {
	}

	/**
	 * 仕入先別単価検索（登録用）
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @param consecutiveNo 行番
	 * @return Unitprice
	 */
	public Unitprice getEntity(final String venderDivision,
			final String venderCd, final String itemCd,
			final BigDecimal version, final BigDecimal consecutiveNo) {
		if (StringUtils.isEmpty(venderCd)) {
			throw new IllegalArgumentException("venderCd is empty");
		}

		if (StringUtils.isEmpty(itemCd)) {
			throw new IllegalArgumentException("itemCd is empty");
		}

		Unitprice bean = unitpriceDao.getEntity(venderDivision, venderCd,
			itemCd, version, consecutiveNo);

		return bean;
	}

	/**
	 * 仕入先別単価検索（詳細用）
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return List<UnitpriceDetail>
	 */
	public List<UnitpriceDetailList> getList(final String venderDivision,
			final String venderCd, final String itemCd, final BigDecimal version) {
		List<UnitpriceDetailList> list = unitpriceDetailListDao.getList(
			venderDivision, venderCd, itemCd, version);
		return list;
	}

	/**
	 * 仕入先検索
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @return VenderDetail
	 */
	public VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd) {
		VenderDetail bean = venderDetailDao.getEntity(venderDivision, venderCd);
		return bean;
	}

	/**
	 * 品目先検索
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	public ItemQueueLastVersion getItemQueueEntity(final String itemCd) {
		ItemQueueLastVersion bean = itemQueueLastVersionDao
				.getLastVersion(itemCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Unitprice bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			unitpriceDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void insert(final UnitpriceDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Unitprice bean = new Unitprice();

		try {
			/* 一括削除処理 */
			deleteUnitpriceList(frm.getVenderDivision(), frm.getVenderCd(), frm
					.getItemCd(), frm.getVersion());

			for (int i = 0; i < frm.getSearchUnitpriceDetailList().size(); i++) {
				/* 値を更新用Beanにコピる */
				IgnoreCaseBeanUtils.copyProperties(bean, frm);

				/* コピーしきれなかった分は手で */
				bean.setConsecutiveNo(new BigDecimal(i + 1));
				bean.setQuantityFrom(frm.getSearchUnitpriceDetailList().get(i)
						.getQuantityFrom());
				bean.setQuantityTo(frm.getSearchUnitpriceDetailList().get(i)
						.getQuantityTo());
				bean.setUnitprice(frm.getSearchUnitpriceDetailList().get(i)
						.getUnitprice());
				bean.setInputDate(bean.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);
				bean.setUpdatorCd(tantoCd);

				/* 追加処理 */
				unitpriceDao.insert(bean);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 一括削除
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return 削除件数
	 */
	public int deleteUnitpriceList(final String venderDivision,
			final String venderCd, final String itemCd, final BigDecimal version) {
		return unitpriceDeleteListDao.deleteList(venderDivision, venderCd,
			itemCd, version);
	}

	/* -------------------- setter -------------------- */

	/**
	 * unitpriceDaoを設定します。
	 * @param unitpriceDao unitpriceDao
	 */
	public void setUnitpriceDao(final UnitpriceDao unitpriceDao) {
		this.unitpriceDao = unitpriceDao;
	}

	/**
	 * unitpriceDetailListDaoを設定します。
	 * @param unitpriceDetailListDao unitpriceDetailListDao
	 */
	public void setUnitpriceDetailListDao(
			final UnitpriceDetailListDao unitpriceDetailListDao) {
		this.unitpriceDetailListDao = unitpriceDetailListDao;
	}

	/**
	 * venderDetailDaoを設定します。
	 * @param venderDetailDao venderDetailDao
	 */
	public void setVenderDetailDao(final VenderDetailDao venderDetailDao) {
		this.venderDetailDao = venderDetailDao;
	}

	/**
	 * unitpriceDeleteListDaoを設定します。
	 * @param unitpriceDeleteListDao unitpriceDeleteListDao
	 */
	public void setUnitpriceDeleteListDao(
			final UnitpriceDeleteListDao unitpriceDeleteListDao) {
		this.unitpriceDeleteListDao = unitpriceDeleteListDao;
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
