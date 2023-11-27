/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.balance;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetailDao;
import com.asahikaseieng.dao.nonentity.master.balancelowerdetail.BalanceLowerDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 帳合詳細 Actionクラス.
 * @author t0011036
 */
public final class BalanceDetailAction extends AbstractAction {

	private BalanceDetailLogic balanceDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public BalanceDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		BalanceDetailForm frm = (BalanceDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BALANCE,
			Constants.TAB_ID_BALANCE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		BalanceDetail bean = balanceDetailLogic.getDetailEntity(frm
				.getBalanceCd());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* 明細取得 */
		frm.setSearchBalanceList(balanceDetailLogic.getDetailList(frm
				.getBalanceCd()));

		/* 最終行は自分なので表示しない */
		if (1 <= frm.getSearchBalanceList().size()) {
			frm.getSearchBalanceList().remove(
				frm.getSearchBalanceList().size() - 1);
		}

		int shopLevel = 1;

		/* 帳合レベル計算 */
		if (!StringUtils.isEmpty(bean.getUpperBalanceCd())) {
			shopLevel = balanceDetailLogic.calcBalanceLevel(
				bean.getBalanceCd(), bean.getUpperBalanceCd());
		}

		frm.setShopLevel(new BigDecimal(shopLevel));

		switch (shopLevel) {
		case 1:
			frm.setShopLevelName("得意先");
			break;
		case 2:
			frm.setShopLevelName("二次店");
			break;
		case 3:
			frm.setShopLevelName("三次店");
			break;
		case 4:
			frm.setShopLevelName("四次店");
			break;
		case 5:
			frm.setShopLevelName("五次店");
			break;
		default:
			frm.setShopLevelName(null);
			break;
		}

		/* 下位帳合チェック */
		BalanceLowerDetail beanLower = balanceDetailLogic.getLowerEntity(frm
				.getBalanceCd());

		if (beanLower != null) {
			frm.setLastLevel("false");
		} else {
			/* 最終レベル */
			frm.setLastLevel("true");
		}

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward getUpperBalance(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getUpperBalance.");
		}

		BalanceDetailForm frm = (BalanceDetailForm) form;

		/* 初期検索 */
		// BalanceDetail bean = balanceDetailLogic.getDetailEntity(frm
		// .getUpperBalanceCd());
		//
		// /* BeanをFormにコピーする */
		// IgnoreCaseBeanUtils.copyProperties(frm, bean);
		/* 明細取得 */
		frm.setSearchBalanceList(balanceDetailLogic.getDetailList(frm
				.getUpperBalanceCd()));

		int shopLevel = 1;

		/* 帳合レベル計算 */
		if (!StringUtils.isEmpty(frm.getUpperBalanceCd())) {
			shopLevel = balanceDetailLogic.calcBalanceLevel(frm.getBalanceCd(),
				frm.getUpperBalanceCd());
		}

		frm.setShopLevel(new BigDecimal(shopLevel));

		switch (shopLevel) {
		case 1:
			frm.setShopLevelName("得意先");
			break;
		case 2:
			frm.setShopLevelName("二次店");
			break;
		case 3:
			frm.setShopLevelName("三次店");
			break;
		case 4:
			frm.setShopLevelName("四次店");
			break;
		case 5:
			frm.setShopLevelName("五次店");
			break;
		default:
			frm.setShopLevelName(null);
			break;
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		BalanceDetailForm frm = (BalanceDetailForm) form;

		if (!StringUtils.isEmpty(frm.getVenderCd())) {
			/* 得意先コードチェック */
			VenderDetail beanVender = balanceDetailLogic.getVenderEntity(frm
					.getVenderDivision(), frm.getVenderCd());

			if (beanVender == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.balance.vender.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getUpperBalanceCd())) {
			/* 上位帳合コードチェック */
			BalanceDetail beanBalance = balanceDetailLogic.getDetailEntity(frm
					.getUpperBalanceCd());

			if (beanBalance == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.upper.balance.cd");
				return mapping.findForward("success");
			}

			/* 上位帳合循環参照チェック */
			if (frm.getBalanceCd().equals(frm.getUpperBalanceCd())) {
				/* エラーメッセージ */
				saveError(request, "errors.loop.upper.balance.cd");
				return mapping.findForward("success");
			}
		}

		int balanceLevel = 1;

		/* 帳合レベル計算 */
		if (!StringUtils.isEmpty(frm.getUpperBalanceCd())) {
			balanceLevel = balanceDetailLogic.calcBalanceLevel(frm
					.getBalanceCd(), frm.getUpperBalanceCd());

			if (balanceLevel == -1) {
				/* エラーメッセージ */
				saveError(request, "errors.loop.upper.location.cd");
				return mapping.findForward("success");
			}
		}

		frm.setShopLevel(new BigDecimal(balanceLevel));

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			balanceDetailLogic.insert(insertBalance(frm, getLoginInfo(request)
					.getTantoCd()));
		} else {
			/* 更新処理を実行 */
			balanceDetailLogic.update(updateBalance(frm, getLoginInfo(request)
					.getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 削除処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete");
		}

		BalanceDetailForm frm = (BalanceDetailForm) form;

		if (!StringUtils.isEmpty(frm.getBalanceCd())) {
			/* 先付受注ヘッダ存在チェック */
			int count = balanceDetailLogic.getCountFrstOrderHead(frm.getBalanceCd());
			if (count > 0) {
				/* エラーメッセージ */
				saveError(request, "errors.notdelete.balance.order.cd");
				return mapping.findForward("success");
			}

		}

		/* 削除処理を実行 */
		balanceDetailLogic.delete(deleteBalance(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のBalanceを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Balance
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Balance updateBalance(final BalanceDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Balance newBean = new Balance();

		try {
			newBean = balanceDetailLogic.getEntity(frm.getBalanceCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のBalanceを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Balance
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Balance insertBalance(final BalanceDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Balance newBean = new Balance();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のBalanceを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Balance
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Balance deleteBalance(final BalanceDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Balance newBean = new Balance();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		BalanceDetailForm frm = (BalanceDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BALANCE,
			Constants.TAB_ID_BALANCE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * balanceDetailLogicを設定します。
	 * @param balanceDetailLogic balanceDetailLogic
	 */
	public void setBalanceDetailLogic(
			final BalanceDetailLogic balanceDetailLogic) {
		this.balanceDetailLogic = balanceDetailLogic;
	}
}
