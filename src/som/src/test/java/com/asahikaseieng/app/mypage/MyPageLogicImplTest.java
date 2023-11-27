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

import org.seasar.framework.aop.interceptors.MockInterceptor;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfigDao;
import com.asahikaseieng.dao.nonentity.authority.Authority;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.test.AbstractS2TestCase;

/**
 * MyPageLogicImplのテストケース.
 * @author jbd
 */
public class MyPageLogicImplTest extends AbstractS2TestCase {

	private MyPageLogicImpl myPageLogic;

	/**
	 * コンストラクタ.
	 * @param name string
	 */
	public MyPageLogicImplTest(final String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		include("logictest.dicon");
	}

	/**
	 * updateGadgetConfigテスト.
	 */
	public void testUpdateGadgetConfig() {

		/* MockInterceptorを設定する */
		MockInterceptor mi = new MockInterceptor();

		/* 内部で取得するガジェット設定リストの戻り値を設定 */
		List<GadgetConfig> gadgetConfigList = createGadgetConfigList(5);
		mi.setReturnValue("getListByTantoCd", gadgetConfigList);

		myPageLogic.setGadgetConfigDao((GadgetConfigDao) mi
				.createProxy(GadgetConfigDao.class));

		/* 正常に更新できる場合 */
		myPageLogic.updateGadgetConfig("test000001", createAuthorityList(
			gadgetConfigList, false));

		/* パラメータチェック */
		// tantoCdがnull
		try {
			myPageLogic.updateGadgetConfig(null, new ArrayList<Authority>());
			fail("Should raise an " + IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {
			;
		}
	}

	/**
	 * getGadgetListテスト.
	 * @throws NoDataException NoDataException
	 */
	public void testGetGadgetList() throws NoDataException {

		/* MockInterceptorを設定する */
		MockInterceptor mi = new MockInterceptor();

		/* 内部で取得するガジェット設定リストの戻り値を設定 */
		List<GadgetConfig> gadgetConfigList = createGadgetConfigList(5);
		mi.setReturnValue("getListByTantoCd", gadgetConfigList);

		myPageLogic.setGadgetConfigDao((GadgetConfigDao) mi
				.createProxy(GadgetConfigDao.class));

		/* データが取得できる場合 */
		List<GadgetConfig> list = null;
		list = myPageLogic.getGadgetList("test000001");
		/* 検証 */
		assertTrue(0 < list.size());

		/* パラメータチェック */
		try {
			list = myPageLogic.getGadgetList(null);
			fail("Should raise an " + IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {
			;
		}
		/* 検証 */
		assertNotNull(list);
	}

	/**
	 * registGadgetテスト.
	 */
	public void testRegistGadget() {

		MockInterceptor mi = new MockInterceptor();
		myPageLogic.setGadgetConfigDao((GadgetConfigDao) mi
				.createProxy(GadgetConfigDao.class));

		/* 正常に更新できる場合 */
		String tantoCd = "test000001";
		String lIds = "msg_test000001_001,msg_test000001_002,msg_test000001_003,";
		String rIds = "msg_test000001_004,msg_test000001_005,";
		String leftMenuIds = "";
		String rightMenuIds = "";
		myPageLogic
				.registGadget(lIds, rIds, tantoCd, leftMenuIds, rightMenuIds);

		/* パラメータエラーがおきない */
		rIds = "msg_test000001_004,msg_test000001_005,";
		myPageLogic.registGadget("", rIds, tantoCd, leftMenuIds, rightMenuIds);

		lIds = "msg_test000001_001,msg_test000001_002,msg_test000001_003,";
		myPageLogic.registGadget(lIds, "", tantoCd, leftMenuIds, rightMenuIds);

		myPageLogic.registGadget("", "", tantoCd, leftMenuIds, rightMenuIds);

		/* パラメータチェック(エラー) */
		// tantoCdがnull
		try {
			myPageLogic.registGadget(lIds, rIds, null, leftMenuIds,
				rightMenuIds);
			fail("Should raise an " + IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {
			;
		}
	}

	/**
	 * addRegistListメソッドのテスト.
	 */
	public void testAddRegistList() {

		MyPageLogicImpl myPageLogic = new MyPageLogicImpl();

		List<GadgetConfig> list = new ArrayList<GadgetConfig>();

		/* 正常時：1件のみ */
		myPageLogic.addRegistList(list, "msg_test000001_001,",
			Constants.FIRST_LANE, "test000001", "");

		GadgetConfig bean = list.get(0);
		assertEquals("test000001", bean.getTantoCd());
		assertEquals("001", bean.getGadgetId());
		assertEquals(Constants.FIRST_LANE, bean.getLaneNo());
		assertEquals(BigDecimal.ZERO, bean.getVerticalOrder());

		/* 正常時：複数件 */
		list = new ArrayList<GadgetConfig>();
		String ids = "msg_test000001_001,msg_test000001_002,msg_test000001_003,";
		myPageLogic.addRegistList(list, ids, Constants.SECOND_LANE,
			"test000001", "");

		assertEquals(3, list.size());
	}

	/**
	 * createResultのテスト.
	 */
	public void testCreateResult() {

		MyPageLogicImpl myPageLogic = new MyPageLogicImpl();

		/* ①ガジェット設定リスト(５件)＝ガジェット権限リスト */
		List<GadgetConfig> gadgetConfigList = createGadgetConfigList(5);
		List<Authority> authorityList = createAuthorityList(gadgetConfigList,
			true);

		/* 実行 */
		List<GadgetConfig> list = (List<GadgetConfig>) myPageLogic
				.createResult("test000001", gadgetConfigList, authorityList);

		/* 検証：リストの件数チェック(５件) */
		assertEquals(gadgetConfigList.size(), list.size());

		/* ②ガジェット設定リストにないガジェット権限が１件ある */
		authorityList.add(createAuthority("099"));

		/* 実行 */
		list = (List<GadgetConfig>) myPageLogic.createResult("test000001",
			gadgetConfigList, authorityList);

		/* 検証：リストの件数チェック(権限リストで増えて５件→６件になる) */
		assertEquals(authorityList.size(), list.size());

		/* ③ガジェット設定リスト(１０件)・ガジェット権限リスト(５件) */
		gadgetConfigList = createGadgetConfigList(10);
		authorityList = createAuthorityList(gadgetConfigList, false);

		/* 実行 */
		list = (List<GadgetConfig>) myPageLogic.createResult("test000001",
			gadgetConfigList, authorityList);

		/* 検証：リストの件数チェック(権限リストで絞られて１０件→５件になる) */
		assertEquals(gadgetConfigList.size() / 2, list.size());
	}

	/**
	 * ガジェット設定リストを作成する.
	 * @return List<GadgetConfig>
	 */
	private List<GadgetConfig> createGadgetConfigList(final int size) {

		List<GadgetConfig> list = new ArrayList<GadgetConfig>();
		for (int index = 0; index < size; index++) {
			GadgetConfig gc = new GadgetConfig();
			gc.setTantoCd("test000001");
			gc.setGadgetId("00" + index);
			gc.setLaneNo(new BigDecimal(index % 2));
			gc.setVerticalOrder(new BigDecimal(index));
			list.add(gc);
		}
		return list;
	}

	/**
	 * List<Authority>を生成する<br>
	 * フラグによって、ガジェット設定リストと同じ・半分にしたリストを生成.
	 * @param gadgetConfigList ガジェット設定リスト
	 * @param sameFlg 同じ・半分フラグ
	 * @return List<Authority>
	 */
	private List<Authority> createAuthorityList(
			final List<GadgetConfig> gadgetConfigList, final boolean sameFlg) {

		List<Authority> authList = new ArrayList<Authority>();
		for (int i = 0; i < gadgetConfigList.size(); i++) {
			GadgetConfig gcBean = gadgetConfigList.get(i);
			boolean add = true;
			if (!sameFlg) {
				/* 半分省く */
				if (i % 2 == 0) {
					add = false;
				}
			}
			if (add) {
				authList.add(createAuthority(gcBean.getGadgetId()));
			}
		}
		return authList;
	}

	/**
	 * Authorityを生成する.
	 * @param id ガジェットID
	 * @return Authority
	 */
	private Authority createAuthority(final String id) {
		Authority authBean = new Authority();
		// authBean.setId(id);
		// authBean.setAuthorityKbn(Constants.AUTHORITY_READ);
		return authBean;
	}
}
