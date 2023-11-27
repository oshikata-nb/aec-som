/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.role.Role;
import com.asahikaseieng.dao.entity.master.role.RoleDao;
import com.asahikaseieng.dao.nonentity.master.controlauthoritydetail.ControlAuthorityDetail;
import com.asahikaseieng.dao.nonentity.master.controlauthoritydetail.ControlAuthorityDetailDao;
import com.asahikaseieng.dao.nonentity.master.roledetaillist.RoleDetailList;
import com.asahikaseieng.dao.nonentity.master.roledetaillist.RoleDetailListDao;
import com.asahikaseieng.dao.nonentity.master.viewauthoritydetail.ViewAuthorityDetail;
import com.asahikaseieng.dao.nonentity.master.viewauthoritydetail.ViewAuthorityDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * ロール詳細ロジック 実装クラス.
 * @author t0011036
 */
public class RoleDetailLogicImpl implements RoleDetailLogic {

	private RoleDao roleDao;

	private RoleDetailListDao roleDetailListDao;

	private ViewAuthorityDetailDao viewAuthorityDetailDao;

	private ControlAuthorityDetailDao controlAuthorityDetailDao;

	/**
	 * コンストラクタ.
	 */
	public RoleDetailLogicImpl() {
	}

	/**
	 * ロール検索（登録用）
	 * @param roleId ロールID
	 * @return Role
	 */
	public Role getEntity(final BigDecimal roleId) {
		if (roleId == null || roleId == new BigDecimal("0")) {
			throw new IllegalArgumentException("roleId is empty");
		}

		Role bean = roleDao.getEntity(roleId);
		return bean;
	}

	/**
	 * ロール検索（詳細用）
	 * @param roleId ロールID
	 * @return RoleDetail
	 */
	public List<RoleDetailList> getList(final String roleId) {
		List<RoleDetailList> bean = roleDetailListDao.getList(roleId);
		return bean;
	}

	/**
	 * 更新登録
	 * @param frm 登録データ
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws NoDataException NoDataException
	 */
	public void update(final RoleDetailForm frm) throws IllegalAccessException,
			InvocationTargetException, NoDataException {
		Role bean = new Role();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(bean, frm);

		try {
			/* 更新処理 */
			roleDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}

		/* チェックしたメニューリスト設定 */
		frm.setMenuList(getChkMenuList(frm));

		try {
			/* 閲覧権限マスタ削除 */
			ViewAuthorityDetail beanView = new ViewAuthorityDetail();
			beanView.setRoleId(bean.getRoleId()); /* ロールID */
			viewAuthorityDetailDao.delete(beanView);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}

		/* 操作権限マスタ登録 */
		ControlAuthorityDetail beanControl = new ControlAuthorityDetail();
		beanControl.setRoleId(bean.getRoleId()); /* ロールID */

		try {
			/* 削除処理 */
			controlAuthorityDetailDao.delete(beanControl);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}

		for (int i = 0; i < frm.getMenuList().size(); i++) {
			String[] menu = frm.getMenuList().get(i);

			/* 閲覧権限マスタ登録 */
			if (Constants.KBN_DIRECTROY.equals(menu[3])
					|| Constants.KBN_TAB.equals(menu[3])) {
				ViewAuthorityDetail beanView = new ViewAuthorityDetail();
				beanView.setMenuId(new BigDecimal(menu[0])); /* メニューID */

				if (StringUtils.isEmpty(menu[1])) {
					menu[1] = "0";
				}

				beanView.setTabId(new BigDecimal(menu[1])); /* タブID */
				beanView.setRoleId(bean.getRoleId()); /* ロールID */

				try {
					viewAuthorityDetailDao.insert(beanView);
				} catch (SQLRuntimeException e) {
					/* 一意制約エラー */
					throw new DuplicateRuntimeException(0);
				}
			}

			/* 操作権限マスタ登録 */
			if (StringUtils.isNotEmpty(menu[1])
					&& (StringUtils.isNotEmpty(menu[2]) && !Constants.CTRL_ID_VIEW
							.equals(menu[2]))) {

				/* 閲覧操作以外を登録 */
				ControlAuthorityDetail beanControlAuth = new ControlAuthorityDetail();
				beanControlAuth.setMenuId(new BigDecimal(menu[0])); /* メニューID */
				beanControlAuth.setTabId(new BigDecimal(menu[1])); /* タブID */
				beanControlAuth.setControlId(new BigDecimal(menu[2])); /* 操作ID */
				beanControlAuth.setRoleId(bean.getRoleId()); /* ロールID */

				try {
					controlAuthorityDetailDao.insert(beanControlAuth);
				} catch (SQLRuntimeException e) {
					/* 一意制約エラー */
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * 追加登録
	 * @param frm 追加データ
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void insert(final RoleDetailForm frm) throws IllegalAccessException,
			InvocationTargetException {
		Role bean = new Role();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(bean, frm);

		try {
			/* 追加処理 */
			roleDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}

		/* チェックしたメニューリスト設定 */
		frm.setMenuList(getChkMenuList(frm));

		for (int i = 0; i < frm.getMenuList().size(); i++) {
			String[] menu = frm.getMenuList().get(i);

			/* 閲覧権限マスタ登録 */
			if (Constants.KBN_DIRECTROY.equals(menu[3])
					|| Constants.KBN_TAB.equals(menu[3])) {
				ViewAuthorityDetail beanView = new ViewAuthorityDetail();
				beanView.setMenuId(new BigDecimal(menu[0])); /* メニュID */

				if (StringUtils.isEmpty(menu[1])) {
					menu[1] = "0";
				}

				beanView.setTabId(new BigDecimal(menu[1])); /* タブID */
				beanView.setRoleId(bean.getRoleId()); /* ロールID */

				try {
					viewAuthorityDetailDao.insert(beanView);
				} catch (SQLRuntimeException e) {
					/* 一意制約エラー */
					throw new DuplicateRuntimeException(0);
				}
			}

			/* 操作権限マスタ登録 */
			if (StringUtils.isNotEmpty(menu[1])
					&& (StringUtils.isNotEmpty(menu[2]) && !Constants.CTRL_ID_VIEW
							.equals(menu[2]))) {

				/* 閲覧操作以外を登録 */
				ControlAuthorityDetail beanControl = new ControlAuthorityDetail();
				beanControl.setMenuId(new BigDecimal(menu[0])); /* メニュID */
				beanControl.setTabId(new BigDecimal(menu[1])); /* タブID */
				beanControl.setControlId(new BigDecimal(menu[2])); /* 操作ID */
				beanControl.setRoleId(bean.getRoleId()); /* ロールID */

				try {
					controlAuthorityDetailDao.insert(beanControl);
				} catch (SQLRuntimeException e) {
					/* 一意制約エラー */
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * 削除
	 * @param frm 削除データ
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws NoDataException NoDataException
	 */
	public void delete(final RoleDetailForm frm) throws IllegalAccessException,
			InvocationTargetException, NoDataException {
		Role bean = new Role();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(bean, frm);

		try {
			/* 削除処理 */
			roleDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}

		/* 閲覧権限マスタ削除 */
		ViewAuthorityDetail beanView = new ViewAuthorityDetail();
		beanView.setRoleId(bean.getRoleId()); /* ロールID */

		try {
			viewAuthorityDetailDao.delete(beanView);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}

		/* 操作権限マスタ登録処理を実行 */
		ControlAuthorityDetail beanControl = new ControlAuthorityDetail();
		beanControl.setRoleId(bean.getRoleId()); /* ロールID */

		try {
			controlAuthorityDetailDao.delete(beanControl);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * チェックしたメニューリストを取得 リスト内に、String配列(メニューID,タブID,操作ID,区分)をチェック分格納
	 * @param frm 詳細画面用FORM
	 * @return チェックしたメニューリスト
	 */
	private List<String[]> getChkMenuList(final RoleDetailForm frm) {
		/* チェックしたメニューリスト格納 */
		String chkMenuList = new String(frm.getChkMenuList());
		String[] menuList = chkMenuList.split("@");
		List<String[]> list = new ArrayList<String[]>();

		for (int i = 0; i < menuList.length; i++) {
			/* 区分＋メニューID＋タブID＋操作ID */
			int t = menuList[i].indexOf(Constants.TAB_ID_PREFIX);
			int c = menuList[i].indexOf(Constants.CTRL_ID_PREFIX);
			String kbn = menuList[i].substring(0, 1);
			String menuId = menuList[i].substring(2, t);
			String tabId = menuList[i].substring(t + 1, c);
			String ctrlId = menuList[i].substring(c + 1);
			String[] id = {menuId, tabId, ctrlId, kbn};
			list.add(id);
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * roleDaoを設定します。
	 * @param roleDao roleDao
	 */
	public void setRoleDao(final RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * roleDetailListDaoを設定します。
	 * @param roleDetailListDao roleDetailListDao
	 */
	public void setRoleDetailListDao(final RoleDetailListDao roleDetailListDao) {
		this.roleDetailListDao = roleDetailListDao;
	}

	/**
	 * viewAuthorityDetailDaoを設定します。
	 * @param viewAuthorityDetailDao viewAuthorityDetailDao
	 */
	public void setViewAuthorityDetailDao(
			final ViewAuthorityDetailDao viewAuthorityDetailDao) {
		this.viewAuthorityDetailDao = viewAuthorityDetailDao;
	}

	/**
	 * controlAuthorityDetailDaoを設定します。
	 * @param controlAuthorityDetailDao controlAuthorityDetailDao
	 */
	public void setControlAuthorityDetailDao(
			final ControlAuthorityDetailDao controlAuthorityDetailDao) {
		this.controlAuthorityDetailDao = controlAuthorityDetailDao;
	}
}
