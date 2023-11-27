/*
 * Created on 2009/01/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.location;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.master.location.LocationDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersionDao;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetailDao;
import com.asahikaseieng.dao.nonentity.master.locationlowerlist.LocationLowerList;
import com.asahikaseieng.dao.nonentity.master.locationlowerlist.LocationLowerListDao;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLogin;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLoginDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * ロケーション詳細ロジック 実装クラス.
 * @author t0011036
 */
public class LocationDetailLogicImpl implements LocationDetailLogic {

	private LocationDao locationDao;

	private LocationDetailDao locationDetailDao;

	private LocationLowerListDao locationLowerListDao;

	private NamesListForComboboxesDao namesListForComboboxesDao;

	private LoginLoginDao loginLoginDao;

	private BumonDetailDao bumonDetailDao;

	private ItemQueueLastVersionDao itemQueueLastVersionDao;

	/**
	 * コンストラクタ.
	 */
	public LocationDetailLogicImpl() {
	}

	/**
	 * 棚卸リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<NamesListForComboboxes> getCountDivisionList() {
		List<NamesListForComboboxes> list = namesListForComboboxesDao
				.getListForComboboxes("TANA");
		return list;
	}

	/**
	 * 下位ロケーション検索
	 * @param locationCd ロケーションコード
	 * @return List<LocationLowerList>
	 */
	public List<LocationLowerList> getLowerLocation(final String locationCd) {
		List<LocationLowerList> bean = locationLowerListDao.getList(locationCd);
		return bean;
	}

	/**
	 * ロケーションレベル計算
	 * @param locationCd ロケーションコード
	 * @param upperLocationCd 上位ロケーションコード
	 * @return ロケーションレベル
	 * @throws NoDataException NoDataException
	 */
	public int calcLocationLevel(final String locationCd,
			final String upperLocationCd) throws NoDataException {
		int locationLevel = 1;

		/* ロケーション検索 */
		LocationDetail bean = getDetailEntity(upperLocationCd);

		if (bean == null) {
			return locationLevel;
		}

		locationLevel++;

		while (!StringUtils.isEmpty(bean.getUpperLocationCd())) {
			/* 循環参照チェック */
			if (bean.getUpperLocationCd().equals(locationCd)) {
				locationLevel = -1;
				break;
			}

			/* ロケーション検索 */
			bean = getDetailEntity(bean.getUpperLocationCd());

			if (bean == null) {
				return locationLevel;
			}

			locationLevel++;
		}

		return locationLevel;
	}

	/**
	 * 会計部門検索
	 * @param sectionCd 会計部門コード
	 * @return BumonDetail
	 */
	public BumonDetail getBumonEntity(final String sectionCd) {
		BumonDetail bean = bumonDetailDao.getEntity(sectionCd);
		return bean;
	}

	/**
	 * 営業担当者検索
	 * @param tantoCd 営業担当者コード
	 * @return LoginLogin
	 */
	public LoginLogin getLoginEntity(final String tantoCd) {
		LoginLogin bean = loginLoginDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * ローリー原料品目検索
	 * @param itemCd ローリー原料品目コード
	 * @return ItemQueueLastVersion
	 */
	public ItemQueueLastVersion getItemQueueEntity(final String itemCd) {
		ItemQueueLastVersion bean = itemQueueLastVersionDao
				.getLastVersion(itemCd);
		return bean;
	}

	/**
	 * ロケーション検索（登録用）
	 * @param locationCd ロケーションコード
	 * @return Location
	 * @throws NoDataException NoDataException
	 */
	public Location getEntity(final String locationCd) throws NoDataException {
		if (StringUtils.isEmpty(locationCd)) {
			throw new IllegalArgumentException("locationCd is empty");
		}

		Location bean = locationDao.getEntity(locationCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * ロケーション検索（詳細用）
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	public LocationDetail getDetailEntity(final String locationCd) {
		LocationDetail bean = locationDetailDao.getEntity(locationCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Location bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			locationDao.update(bean);

			if (!StringUtils.isEmpty(bean.getUpperLocationCd())) {
				Location beanUpper = new Location();

				try {
					/* 上位ロケーションの在庫可能フラグセット */
					beanUpper = getEntity(bean.getUpperLocationCd());
				} catch (NoDataException e) {
					beanUpper = null;
				}

				if (beanUpper.getUpdateDate() != null) {
					/* 更新処理を実行 */
					locationDao.update(updateUpperLocation(beanUpper, beanUpper
							.getUpdatorCd()));
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 更新処理用の上位Locationを作成する.
	 * @param bean 上位ロケーションデータ
	 * @param tantoCd 担当者コード
	 * @return Location
	 */
	private Location updateUpperLocation(final Location bean,
			final String tantoCd) {
		List<LocationLowerList> list = getLowerLocation(bean.getLocationCd());

		/* 上位ロケーションの在庫可能フラグセット */
		if (list == null) {
			bean.setAvailableFlg(new BigDecimal("1")); /* 最下位ロケーション */
		} else {
			if (list.size() == 0) {
				bean.setAvailableFlg(new BigDecimal("1")); /* 最下位ロケーション */
			} else {
				bean.setAvailableFlg(new BigDecimal("0"));
			}
		}

		bean.setUpdatorCd(tantoCd);

		return bean;
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Location bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			locationDao.insert(bean);

			if (!StringUtils.isEmpty(bean.getUpperLocationCd())) {
				Location beanUpper = new Location();

				try {
					/* 上位ロケーションの在庫可能フラグセット */
					beanUpper = getEntity(bean.getUpperLocationCd());
				} catch (NoDataException e) {
					beanUpper = null;
				}

				if (beanUpper.getUpdateDate() != null) {
					/* 更新処理を実行 */
					locationDao.update(updateUpperLocation(beanUpper, beanUpper
							.getUpdatorCd()));
				}
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
	public void delete(final Location bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			locationDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * locationDetailDaoを設定します。
	 * @param locationDetailDao LocationDetailDao
	 */
	public void setLocationDetailDao(final LocationDetailDao locationDetailDao) {
		this.locationDetailDao = locationDetailDao;
	}

	/**
	 * locationDaoを設定します。
	 * @param locationDao locationDao
	 */
	public void setLocationDao(final LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * locationLowerListDaoを設定します。
	 * @param locationLowerListDao locationLowerListDao
	 */
	public void setLocationLowerListDao(
			final LocationLowerListDao locationLowerListDao) {
		this.locationLowerListDao = locationLowerListDao;
	}

	/**
	 * bumonDetailDaoを設定します。
	 * @param bumonDetailDao bumonDetailDao
	 */
	public void setBumonDetailDao(final BumonDetailDao bumonDetailDao) {
		this.bumonDetailDao = bumonDetailDao;
	}

	/**
	 * loginLoginDaoを設定します。
	 * @param loginLoginDao loginLoginDao
	 */
	public void setLoginLoginDao(final LoginLoginDao loginLoginDao) {
		this.loginLoginDao = loginLoginDao;
	}

	/**
	 * namesListForComboboxesDaoを設定します。
	 * @param namesListForComboboxesDao namesListForComboboxesDao
	 */
	public void setNamesListForComboboxesDao(
			final NamesListForComboboxesDao namesListForComboboxesDao) {
		this.namesListForComboboxesDao = namesListForComboboxesDao;
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
