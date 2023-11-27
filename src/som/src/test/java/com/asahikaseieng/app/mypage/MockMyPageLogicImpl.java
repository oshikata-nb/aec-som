/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.entity.gadget.Gadget;
import com.asahikaseieng.dao.entity.gadget.GadgetLink;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.nonentity.authority.Authority;

/**
 * マイページロジック Mockクラス.
 * @author jbd
 */
public class MockMyPageLogicImpl implements MyPageLogic {

	/**
	 * コンストラクタ.
	 */
	public MockMyPageLogicImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateGadgetConfig(final String tantoCd,
			final List<Authority> authList) {
		/* パラメータチェック */
		checkParam(tantoCd);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<GadgetConfig> getGadgetList(final String tantoCd) {
		/* パラメータチェック */
		checkParam(tantoCd);

		List<GadgetConfig> list = new ArrayList<GadgetConfig>();

		for (int i = 1; i <= 10; i++) {
			list.add(createBean(i, tantoCd));
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public void registGadget(final String ids, final String laneNo,
			final String tantoCd, final String leftMenuIds,
			final String rightMenuIds) {
		/* パラメータチェック */
		checkParam(tantoCd);
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

		// ガジェット設定リスト

		List<GadgetLink> list = new ArrayList<GadgetLink>();

		return list;
	}

	/**
	 * GadgetConfig生成.
	 * @param index インデックス
	 * @return GadgetConfig
	 */
	private GadgetConfig createBean(final int index, final String loginId) {
		GadgetConfig bean = new GadgetConfig();
		bean.setTantoCd(loginId);
		bean.setGadgetId("00" + index);
		bean.setLaneNo(new BigDecimal(index % 2));
		bean.setVerticalOrder(new BigDecimal(index));
		bean.setGadget(createGadget(index));
		return bean;
	}

	/**
	 * Gadget生成.
	 * @param index インデックス
	 * @return GadgetConfig
	 */
	private Gadget createGadget(final int index) {
		Gadget bean = new Gadget();
		bean.setGadgetId("00" + index);
		bean.setTitle("title0" + index);
		bean.setTitleUrl("hoge0" + index + ".do");
		bean.setActionUrl("hoge0" + index + ".do");
		return bean;
	}

	/**
	 * 必須パラメータが空なら例外を発生させる.
	 * @param tantoCd String
	 */
	private void checkParam(final String tantoCd) {
		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException();
		}
	}

}
