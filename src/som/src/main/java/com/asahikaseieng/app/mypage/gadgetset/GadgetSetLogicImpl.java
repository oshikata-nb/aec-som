/*
 * Created on 2008/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.gadgetset;

import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfigDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;

/**
 * ガジェット設定画面ロジッククラス interface.
 * @author tosco
 */
public class GadgetSetLogicImpl implements GadgetSetLogic {

	private GadgetConfigDao gadgetConfigDao;

	/**
	 * コンストラクタ.
	 */
	public GadgetSetLogicImpl() {
		super();
	}

	/**
	 * ガジェット設定NEW(GADGET_CONFIG)テーブルの登録処理(DELETE/INSERT)を行う.
	 * 
	 * @param tantoCd ログインしている担当者コード
	 * 
	 * @param list 登録対象のガジェット設定リスト
	 * 
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	public void update(final String tantoCd, final List<GadgetConfig> list)
			throws NoDataException {
		int updateNum = 0;

		if (tantoCd == null || list == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// ガジェット設定NEW(GADGET_CONFIG)テーブル 削除処理

			gadgetConfigDao.deleteByTantoCd(tantoCd);

			// ガジェット設定NEW(GADGET_CONFIG)テーブル 登録処理

			for (int i = 0; i < list.size(); i++) {
				GadgetConfig gadgetConf = list.get(i);

				// 登録処理

				updateNum = gadgetConfigDao.insert(gadgetConf);

				if (updateNum != 1) {
					/* 登録エラー */
					throw new DuplicateRuntimeException(0);
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new NoDataException();
		}
	}

	/**
	 * gadgetConfigDaoを設定します。
	 * 
	 * @param gadgetConfigDao gadgetConfigDao
	 */
	public void setGadgetConfigDao(final GadgetConfigDao gadgetConfigDao) {
		this.gadgetConfigDao = gadgetConfigDao;
	}

}
