/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.login;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.tantorole.TantoRole;
import com.asahikaseieng.dao.entity.master.tantorole.TantoRoleDao;
import com.asahikaseieng.dao.nonentity.master.loginallrolelist.LoginAllRoleList;
import com.asahikaseieng.dao.nonentity.master.loginallrolelist.LoginAllRoleListDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetailDao;
import com.asahikaseieng.dao.nonentity.master.loginrolelist.LoginRoleList;
import com.asahikaseieng.dao.nonentity.master.loginrolelist.LoginRoleListDao;
import com.asahikaseieng.dao.nonentity.master.tantorolealldelete.TantoRoleAllDelete;
import com.asahikaseieng.dao.nonentity.master.tantorolealldelete.TantoRoleAllDeleteDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 担当者マスタ詳細 ロジック実装クラス
 * @author t0011036
 */
public class LoginDetailLogicImpl implements LoginDetailLogic {

	private LoginDao loginDao;

	private TantoRoleDao tantoRoleDao;

	private TantoRoleAllDeleteDao tantoRoleAllDeleteDao;

	private LoginDetailDao loginDetailDao;

	private LoginRoleListDao loginRoleListDao;

	private LoginAllRoleListDao loginAllRoleListDao;

	/**
	 * コンストラクタ.
	 */
	public LoginDetailLogicImpl() {
	}

	/**
	 * 全ロール一覧検索
	 * @return List<LoginAllRoleList>
	 */
	public List<LoginAllRoleList> getLoginAllRoleList() {
		List<LoginAllRoleList> list = loginAllRoleListDao.getList();
		return list;
	}

	/**
	 * 担当者ロール一覧検索
	 * @param tantoCd 担当者コード
	 * @return List<LoginRoleList>
	 */
	public List<LoginRoleList> getLoginRoleList(final String tantoCd) {
		List<LoginRoleList> list = loginRoleListDao.getList(tantoCd);
		return list;
	}

	/**
	 * 担当者検索（登録用）
	 * @param tantoCd 担当者コード
	 * @return Login
	 * @throws NoDataException NoDataException
	 */
	public Login getEntity(final String tantoCd) throws NoDataException {
		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException("tantoCd is empty");
		}

		Login bean = loginDao.getEntity(tantoCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 担当者検索（詳細用）
	 * @param tantoCd 担当者コード
	 * @return TantoDetail
	 */
	public LoginDetail getDetailEntity(final String tantoCd) {
		LoginDetail bean = loginDetailDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * 担当者登録
	 * @param frm 更新対象データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void regist(final LoginDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			insertLogin(insertLogin(frm, tantoCd));
		} else {
			/* 更新処理を実行 */
			updateLogin(updateLogin(frm, tantoCd));
		}

		/* 一般ユーザーはロールの登録は出来ない */
		if (!frm.getLoginAdminFlg().equals("1")) {
			/* 担当ロール全削除 */
			deleteTantoRole(deleteTantoRole(frm, tantoCd));

			BigDecimal roleId = null;

			/* 担当者ロール組み合わせマスタ登録処理を実行 */
			for (int i = 0; i < frm.getRoleId().length; i++) {
				String[] value = frm.getRoleId()[i].split(":", 0);
				roleId = new BigDecimal(value[0]);

				/* 追加処理を実行 */
				insertTantoRole(insertTantoRole(frm, roleId, tantoCd));
			}
		}
	}

	/**
	 * 更新処理用のLoginを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Login
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Login updateLogin(final LoginDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Login newBean = new Login();

		try {
			newBean = getEntity(frm.getTantoCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		frm.setPassword(null);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のLoginを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Login
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Login insertLogin(final LoginDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Login newBean = new Login();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		frm.setPassword(null);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 担当者更新登録
	 * @param bean 登録データ
	 */
	public void updateLogin(final Login bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			loginDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 担当者追加登録
	 * @param bean 登録データ
	 */
	public void insertLogin(final Login bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			loginDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 追加処理用のTantoRoleを作成する.
	 * @param bean 画面データ
	 * @param roleId ロールID
	 * @param tantoCd 担当者コード
	 * @return TantoRole
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private TantoRole insertTantoRole(final LoginDetailForm frm,
			final BigDecimal roleId, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		TantoRole newBean = new TantoRole();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setRoleId(roleId);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 担当者ロール追加登録
	 * @param bean 登録データ
	 */
	public void insertTantoRole(final TantoRole bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			tantoRoleDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 担当者関連情報削除
	 * @param frm 削除データ
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void delete(final LoginDetailForm frm, final String tantoCd)
			throws NoDataException, IllegalAccessException,
			InvocationTargetException {
		deleteLogin(deleteLogin(frm, tantoCd));

		deleteTantoRole(deleteTantoRole(frm, tantoCd));
	}

	/**
	 * 削除処理用のLoginを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Login
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Login deleteLogin(final LoginDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Login newBean = new Login();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/**
	 * 担当者削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void deleteLogin(final Login bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			loginDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 担当者ロール削除
	 * @param bean 削除データ
	 */
	public void deleteTantoRole(final TantoRoleAllDelete bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		/* 削除処理 */
		tantoRoleAllDeleteDao.delete(bean);
	}

	/**
	 * 削除処理用のTantoRoleAllDeleteを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return TantoRole
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private TantoRoleAllDelete deleteTantoRole(final LoginDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		TantoRoleAllDelete newBean = new TantoRoleAllDelete();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * loginDaoを設定します。
	 * @param loginDao loginDao
	 */
	public void setLoginDao(final LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * loginDetailDaoを設定します。
	 * @param loginDetailDao loginDetailDao
	 */
	public void setLoginDetailDao(final LoginDetailDao loginDetailDao) {
		this.loginDetailDao = loginDetailDao;
	}

	/**
	 * loginRoleListDaoを設定します。
	 * @param loginRoleListDao loginRoleListDao
	 */
	public void setLoginRoleListDao(final LoginRoleListDao loginRoleListDao) {
		this.loginRoleListDao = loginRoleListDao;
	}

	/**
	 * loginAllRoleListDaoを設定します。
	 * @param loginAllRoleListDao loginAllRoleListDao
	 */
	public void setLoginAllRoleListDao(
			final LoginAllRoleListDao loginAllRoleListDao) {
		this.loginAllRoleListDao = loginAllRoleListDao;
	}

	/**
	 * tantoRoleDaoを設定します。
	 * @param tantoRoleDao tantoRoleDao
	 */
	public void setTantoRoleDao(final TantoRoleDao tantoRoleDao) {
		this.tantoRoleDao = tantoRoleDao;
	}

	/**
	 * tantoRoleAllDeleteDaoを設定します。
	 * @param tantoRoleAllDeleteDao tantoRoleAllDeleteDao
	 */
	public void setTantoRoleAllDeleteDao(
			final TantoRoleAllDeleteDao tantoRoleAllDeleteDao) {
		this.tantoRoleAllDeleteDao = tantoRoleAllDeleteDao;
	}
}
