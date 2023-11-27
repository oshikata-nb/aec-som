/*
 * Created on 2007/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NoRowsUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.gadget.GadgetLink;
import com.asahikaseieng.dao.entity.gadget.GadgetLinkDao;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfigDao;
import com.asahikaseieng.dao.nonentity.authority.Authority;

/**
 * マイページロジック 実装クラス.
 * @author jbd
 */
public class MyPageLogicImpl implements MyPageLogic {

	private static final String REPLACE_MOJI = "$SESSION_TANTO_CD";

	private GadgetConfigDao gadgetConfigDao;

	private GadgetLinkDao gadgetLinkDao;

	/**
	 * コンストラクタ.
	 */
	public MyPageLogicImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateGadgetConfig(final String tantoCd,
			final List<Authority> authList) {

		// パラメータチェック
		checkParams(tantoCd);

		// 現在のGadgetConfigリストを取得
		List<GadgetConfig> orgList = gadgetConfigDao.getListByTantoCd(tantoCd);

		// 担当者の権限に紐付くガジェットリストを作成
		Collection<GadgetConfig> result = createResult(tantoCd, orgList,
			authList);

		// 担当コード単位に一斉削除
		gadgetConfigDao.deleteByTantoCd(tantoCd);

		// 必要な分をinsert
		for (GadgetConfig gc : result) {
			gadgetConfigDao.insert(gc);
		}
	}

	/**
	 * 更新用のリストを取得.
	 * @param tantoCd
	 * @param orgList
	 * @param currentList
	 * @return
	 */
	Collection<GadgetConfig> createResult(final String tantoCd,
			final List<GadgetConfig> orgList, final List<Authority> currentList) {

		// 現在の設定のIDのリストを作成する
		List<String> orgIdList = new ArrayList<String>();
		for (GadgetConfig gc : orgList) {
			orgIdList.add(gc.getGadgetId() + gc.getMenuId().toString()
					+ gc.getTabId().toString());
		}

		List<GadgetConfig> result = new ArrayList<GadgetConfig>();

		// GADGET_CONFIGに設定があったもののみ再登録
		for (Authority auth : currentList) {
			String key = auth.getGadgetId() + auth.getMenuId().toString()
					+ auth.getTabId().toString();
			// インデックスを取得

			int index = orgIdList.indexOf(key);
			if (-1 < index) { // すでに設定がある場合は、そのまま利用
				GadgetConfig gc = orgList.get(index);
				result.add(gc);
			}
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<GadgetConfig> getGadgetList(final String tantoCd) {

		// パラメータチェック
		checkParams(tantoCd);

		// ガジェット設定リスト
		List<GadgetConfig> list = gadgetConfigDao.getListByTantoCd(tantoCd);

		return list;
	}

	/**
	 * ガジェットに表示するリンクデータリストを取得する.
	 * @param sqlCd SQL文
	 * 
	 * @param tantoCd 担当者コード
	 * 
	 * @return List<GadgetLink> リンクデータリスト
	 * 
	 */
	public List<GadgetLink> getGadgetLinkList(final String sqlCd,
			final String tantoCd) {

		// SQL文の置換

		String repSqlCd = sqlCd.replace(REPLACE_MOJI, tantoCd);

		// ガジェット設定リスト

		List<GadgetLink> list = gadgetLinkDao.getGadgetLinkList(repSqlCd);

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public void registGadget(final String lIds, final String rIds,
			final String tantoCd, final String leftMenuIds,
			final String rightMenuIds) throws NoRowsUpdatedRuntimeException {

		// パラメータチェック
		checkParams(tantoCd);

		// 更新用のガジェットリストを作成
		List<GadgetConfig> list = new ArrayList<GadgetConfig>();
		addRegistList(list, lIds, Constants.FIRST_LANE, tantoCd, leftMenuIds);
		addRegistList(list, rIds, Constants.SECOND_LANE, tantoCd, rightMenuIds);

		/* 更新されるレーンのガジェット設定を洗い変えする */
		// 1.対象の担当者のガジェット設定を全て削除する
		gadgetConfigDao.deleteByTantoCd(tantoCd);
		// 2.ガジェット設定を挿入
		for (GadgetConfig bean : list) {
			gadgetConfigDao.insert(bean);
		}
	}

	/**
	 * パラメータチェック.
	 */
	private void checkParams(final String tantoCd) {

		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException("tantoCd is empty");
		}
	}

	/**
	 * 更新するガジェット設定のリストを取得.
	 */
	void addRegistList(final List<GadgetConfig> list, final String ids,
			final BigDecimal laneNo, final String tantoCd, final String menuIds) {

		String[] messageIds = StringUtils.split(ids, ",");
		String[] mIds = StringUtils.split(menuIds, ",");

		for (int i = 0; i < messageIds.length; i++) {
			// messageIdsはmsg_tantoCd_gadgetIdなので、"_"で分割して
			// gadgetIdとtantoCdを利用する
			String[] st = StringUtils.split(messageIds[i], "_");
			// 更新情報を作成する
			GadgetConfig bean = new GadgetConfig();
			bean.setTantoCd(tantoCd);
			bean.setGadgetId(st[2]);
			bean.setLaneNo(laneNo);
			bean.setVerticalOrder(new BigDecimal(i));

			// mIdsはmenuId_tabId_slideStatusなので、"_"で分割
			String[] menuId = StringUtils.split(mIds[i], "_");
			bean.setMenuId(new BigDecimal(menuId[0])); // メニューID
			bean.setTabId(new BigDecimal(menuId[1])); // タブID
			// 表示状態

			if (menuId.length < 3) {
				bean.setSlideStatus(new BigDecimal(0));
			} else {
				bean.setSlideStatus(new BigDecimal(menuId[2]));
			}

			list.add(bean);
		}
	}

	/**
	 * gadgetConfigDaoを設定します。
	 * @param gadgetConfigDao gadgetConfigDao
	 */
	public void setGadgetConfigDao(final GadgetConfigDao gadgetConfigDao) {
		this.gadgetConfigDao = gadgetConfigDao;
	}

	/**
	 * gadgetLinkDaoを設定します。
	 * 
	 * @param gadgetLinkDao GadgetLinkDao
	 */
	public void setGadgetLinkDao(final GadgetLinkDao gadgetLinkDao) {
		this.gadgetLinkDao = gadgetLinkDao;
	}

}
