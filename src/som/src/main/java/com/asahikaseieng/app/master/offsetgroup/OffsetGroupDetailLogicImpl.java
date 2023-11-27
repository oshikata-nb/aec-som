/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.offsetgroup.OffsetGroup;
import com.asahikaseieng.dao.entity.master.offsetgroup.OffsetGroupDao;
import com.asahikaseieng.dao.nonentity.master.offsetgroupdelete.OffsetGroupDeleteDao;
import com.asahikaseieng.dao.nonentity.master.offsetgroupdetail.OffsetGroupDetail;
import com.asahikaseieng.dao.nonentity.master.offsetgroupdetail.OffsetGroupDetailDao;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderList;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 相殺グループマスタ詳細 ロジック実装クラス
 * @author tosco
 */
public class OffsetGroupDetailLogicImpl implements OffsetGroupDetailLogic {

	private OffsetGroupDetailDao offsetGroupDetailDao;

	private OffsetGroupDeleteDao offsetGroupDeleteDao;

	private OffsetGroupDao offsetGroupDao;

	private VenderListDao venderListDao;

	/**
	 * コンストラクタ.相殺グループマスタ詳細ロジック
	 */
	public OffsetGroupDetailLogicImpl() {
	}

	/**
	 * 相殺グループマスタ検索
	 * 
	 * @param offsetGroupCd 相殺グループコード
	 * @throws NoDataException NoDataException
	 * @return OffsetGroupDetail 詳細データ
	 */
	public List<OffsetGroupDetail> getEntity(final String offsetGroupCd)
			throws NoDataException {

		checkParams(offsetGroupCd);

		List<OffsetGroupDetail> list = offsetGroupDetailDao
				.getEntity(offsetGroupCd);

		return list;
	}

	/**
	 * 更新処理を行う.
	 * @param frm 詳細画面Form
	 * @param tantoCd 担当者コード
	 * @throws NoDataException データ無し例外
	 */
	public void update(final OffsetGroupDetailForm frm, final String tantoCd)
			throws NoDataException {
		int updateNum;

		try {
			/* 削除対象データ、更新対象データを作成する:プライマリキー */
			List<OffsetGroupDetail> list = offsetGroupDetailDao.getEntity(frm
					.getOffsetGroupCd());

			/* 削除処理 */
			int deleteNum = offsetGroupDeleteDao.delete(list.get(0)
					.getOffsetGroupCd());

			if (deleteNum == 0) {
				throw new NoDataException();
			}

			/* 追加処理の繰り返し */
			for (int i = 0; i < frm.getVenderCdSaki().length; i++) {

				String[] saki = frm.getVenderCdSaki()[i].split(":", 0);

				VenderList bean = venderListDao.checkVenderCd(saki[0], saki[1]);

				if (bean == null) {
					throw new NoDataException();
				}

				/* 更新対象データを作成する */
				OffsetGroup newBean = new OffsetGroup();

				/* 値を更新用Beanにコピる */
				IgnoreCaseBeanUtils.copyProperties(newBean, frm);

				/* 取引先情報 */
				newBean.setVenderDivision(saki[0]);
				newBean.setVenderCd(saki[1]);

				/* 前回値の設定 */
				// newBean.setRegistDate(list.get(0).getRegistDate());
				// newBean.setRegistTantoCd(list.get(0).getRegistTantoCd());
				/* 基本情報入力 */
				newBean.setInputorCd(tantoCd);
				newBean.setUpdatorCd(tantoCd);
				newBean.setInputDate(newBean.getCurrentTimestamp());

				/* 追加処理 */
				updateNum = offsetGroupDao.insert(newBean);

				if (updateNum != 1) {
					/* 更新エラー */
					throw new NoDataException();
				}
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new NoDataException();

		} catch (IllegalAccessException e) {
			e.printStackTrace();

		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登録処理を行う.
	 * @param frm フォーム
	 * @param tantoCd 登録者
	 * @throws NoDataException データ無し例外
	 */
	public void insert(final OffsetGroupDetailForm frm, final String tantoCd)
			throws NoDataException {
		int updateNum;

		/* 登録処理の繰り返し */
		for (int i = 0; i < frm.getVenderCdSaki().length; i++) {
			String[] saki = frm.getVenderCdSaki()[i].split(":", 0);

			VenderList bean = venderListDao.checkVenderCd(saki[0], saki[1]);

			if (bean == null) {
				throw new NoDataException();
			}

			/* 更新対象データを作成する */
			OffsetGroup newBean = new OffsetGroup();

			/* 取引先情報 */
			newBean.setVenderDivision(saki[0]);
			newBean.setVenderCd(saki[1]);

			/* 値を更新用Beanにコピる */
			try {
				IgnoreCaseBeanUtils.copyProperties(newBean, frm);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}

			/* 基本情報入力 */
			// newBean.setRegistDate(AecDateUtils.getCurrentTimestamp());
			// newBean.setRegistTantoCd(tantoCd);
			newBean.setInputorCd(tantoCd);
			newBean.setUpdatorCd(tantoCd);
			newBean.setInputDate(newBean.getCurrentTimestamp());

			try {
				/* 追加処理 */
				updateNum = offsetGroupDao.insert(newBean);

				if (updateNum != 1) {
					/* 排他エラー */
					throw new OptimisticLockRuntimeException();
				}
			} catch (SQLRuntimeException e) {
				throw new DuplicateRuntimeException(0);
			}
		}
	}

	/**
	 * 削除処理を行う.
	 * @param offsetGroupCd 削除対象キー
	 * @throws NoDataException データ無し例外
	 * 
	 */
	public void delete(final String offsetGroupCd) throws NoDataException {

		checkParams(offsetGroupCd);

		try {
			/* 削除処理 */
			int deleteNum = offsetGroupDeleteDao.delete(offsetGroupCd);

			if (deleteNum == 0) {
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 削除エラー */
			throw new NoDataException();
		}
	}

	/**
	 * パラメータチェック.
	 * @param cd 検索条件:LOCATION
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final String cd1) throws IllegalArgumentException {

		if (cd1 == null) {
			throw new IllegalArgumentException(
					"OffsetGroupDetailLogic IllegalArgumentException : Paramater is empty checkParams(cd).");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * venderListDaoを設定します。
	 * @param venderListDao VenderListDao
	 */
	public void setVenderListDao(final VenderListDao venderListDao) {
		this.venderListDao = venderListDao;
	}

	/**
	 * offsetGroupDetailDaoを設定します。
	 * @param offsetGroupDetailDao offsetGroupDetailDao
	 */
	public void setOffsetGroupDetailDao(
			final OffsetGroupDetailDao offsetGroupDetailDao) {
		this.offsetGroupDetailDao = offsetGroupDetailDao;
	}

	/**
	 * offsetGroupDeleteDaoを設定します。
	 * @param offsetGroupDeleteDao offsetGroupDeleteDao
	 */
	public void setOffsetGroupDeleteDao(
			final OffsetGroupDeleteDao offsetGroupDeleteDao) {
		this.offsetGroupDeleteDao = offsetGroupDeleteDao;
	}

	/**
	 * offsetGroupDaoを設定します。
	 * @param offsetGroupDao offsetGroupDao
	 */
	public void setOffsetGroupDao(final OffsetGroupDao offsetGroupDao) {
		this.offsetGroupDao = offsetGroupDao;
	}
}
