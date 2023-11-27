/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.componentinformationqueue.ComponentInformationQueue;
import com.asahikaseieng.dao.entity.master.componentinformationqueue.ComponentInformationQueueDao;
import com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetail;
import com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetailDao;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuedeletelist.ComponentInformationQueueDeleteListDao;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.ComponentInformationQueueList;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.ComponentInformationQueueListDao;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeaderDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 品目基本ロジック 実装クラス.
 * @author t0011036
 */
public class ItemComponentLogicImpl implements ItemComponentLogic {

	private ComponentInformationQueueListDao componentInformationQueueListDao;

	private ComponentInformationQueueDeleteListDao componentInformationQueueDeleteListDao;

	private ComponentInformationQueueDao componentInformationQueueDao;

	private ItemQueueHeaderDao itemQueueHeaderDao;

	private ComponentDetailDao componentDetailDao;

	/**
	 * コンストラクタ.
	 */
	public ItemComponentLogicImpl() {
	}

	/**
	 * 成分情報検索
	 * @param itemCd itemCd
	 * @param version version
	 * @return List<ComponentInformationQueueList>
	 */
	public List<ComponentInformationQueueList> getList(final String itemCd,
			final BigDecimal version) {
		List<ComponentInformationQueueList> bean = componentInformationQueueListDao
				.getList(itemCd, version);
		return bean;
	}

	/**
	 * 成分情報検索（登録用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @param indicateOrder 表示順
	 * @return ComponentInformationQueue
	 * @throws NoDataException NoDataException
	 */
	public ComponentInformationQueue getEntity(final String itemCd,
			final BigDecimal version, final BigDecimal indicateOrder)
			throws NoDataException {
		if (StringUtils.isEmpty(itemCd)) {
			throw new IllegalArgumentException("itemCd is empty");
		}

		ComponentInformationQueue bean = componentInformationQueueDao
				.getEntity(itemCd, version, indicateOrder);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 成分検索（詳細用）
	 * @param componentCd 成分コード
	 * @return ComponentDetail
	 */
	public ComponentDetail getComponentEntity(final String componentCd) {
		ComponentDetail bean = componentDetailDao.getEntity(componentCd);
		return bean;
	}

	/**
	 * 品目検索（ヘッダー用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	public ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version) {
		ItemQueueHeader bean = itemQueueHeaderDao.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 登録登録
	 * @param frm 登録対象データ
	 * @param tantoCd 担当者コード
	 */
	public void regist(final ItemComponentForm frm, final String tantoCd) {
		ComponentInformationQueue bean = new ComponentInformationQueue();

		try {
			/* 一括削除処理 */
			deleteComponentList(frm.getItemCd(), frm.getVersion());

			for (int i = 0; i < frm.getSearchComponentList().size(); i++) {
				/* コピーしきれなかった分は手で */
				bean.setItemCd(frm.getHeadItemCd());
				bean.setVersion(frm.getHeadVersion());
				bean.setIndicateOrder(new BigDecimal(i + 1));
				bean.setComponentCd(frm.getSearchComponentList().get(i)
						.getComponentCd());
				bean.setCalcValue(frm.getSearchComponentList().get(i)
						.getCalcValue());
				bean.setIndicateValue(frm.getSearchComponentList().get(i)
						.getIndicateValue());
				bean.setInputDate(bean.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);
				bean.setUpdatorCd(tantoCd);

				/* 追加処理 */
				componentInformationQueueDao.insert(bean);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 成分情報一括削除
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return 削除件数
	 */
	public int deleteComponentList(final String itemCd, final BigDecimal version) {
		return componentInformationQueueDeleteListDao.deleteList(itemCd,
			version);
	}

	/**
	 * ステータス更新登録
	 * @param bean 更新対象データ
	 */
	public void updateStatus(final ComponentInformationQueue bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			componentInformationQueueDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemQueueHeaderDaoを設定します。
	 * @param itemQueueHeaderDao itemQueueHeaderDao
	 */
	public void setItemQueueHeaderDao(
			final ItemQueueHeaderDao itemQueueHeaderDao) {
		this.itemQueueHeaderDao = itemQueueHeaderDao;
	}

	/**
	 * componentInformationQueueListDaoを設定します。
	 * @param componentInformationQueueListDao componentInformationQueueListDao
	 */
	public void setComponentInformationQueueListDao(
			final ComponentInformationQueueListDao componentInformationQueueListDao) {
		this.componentInformationQueueListDao = componentInformationQueueListDao;
	}

	/**
	 * componentInformationQueueDaoを設定します。
	 * @param componentInformationQueueDao componentInformationQueueDao
	 */
	public void setComponentInformationQueueDao(
			final ComponentInformationQueueDao componentInformationQueueDao) {
		this.componentInformationQueueDao = componentInformationQueueDao;
	}

	/**
	 * componentInformationQueueDeleteListDaoを設定します。
	 * @param componentInformationQueueDeleteListDao
	 *            componentInformationQueueDeleteListDao
	 */
	public void setComponentInformationQueueDeleteListDao(
			final ComponentInformationQueueDeleteListDao componentInformationQueueDeleteListDao) {
		this.componentInformationQueueDeleteListDao = componentInformationQueueDeleteListDao;
	}

	/**
	 * componentDetailDaoを設定します。
	 * @param componentDetailDao componentDetailDao
	 */
	public void setComponentDetailDao(
			final ComponentDetailDao componentDetailDao) {
		this.componentDetailDao = componentDetailDao;
	}
}
