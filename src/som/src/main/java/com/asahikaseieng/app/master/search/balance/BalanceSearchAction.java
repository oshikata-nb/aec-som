/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.balance;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceList;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetail;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 帳合検索 Actionクラス.
 * @author t0011036
 */
public final class BalanceSearchAction extends AbstractSearchAction {

	private BalanceSearchLogic balanceSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public BalanceSearchAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init");
		}

		/* 初期検索有り */
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search");
		}

		BalanceSearchForm frm = (BalanceSearchForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<BalanceList>());

		/* 検索条件を取得 */
		BalanceListPagerCondition condition = (BalanceListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhBalanceType(new BigDecimal("0"));
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhBalanceCd(frm.getSrhBalanceCd());

		/* 明細取得 */
		frm.setSearchList(balanceSearchLogic.getList(condition));

		BalanceListDetail bean = new BalanceListDetail();

		/* 明細詳細取得 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 帳合一覧詳細取得 */
			bean = balanceSearchLogic.getEntity(frm.getSearchList().get(i)
					.getBalanceCd(), frm.getSearchList().get(i)
					.getBalanceType());

			if (bean == null) {
				frm.getSearchList().get(i).setBalanceTypeName("未設定");
			} else {
				frm.getSearchList().get(i).setBalanceTypeName(
					bean.getBalanceTypeName());
				frm.getSearchList().get(i)
						.setVenderName1(bean.getVenderName1());
				frm.getSearchList().get(i).setDispVenderName1(
					bean.getDispVenderName1());
				frm.getSearchList().get(i)
						.setVenderName2(bean.getVenderName2());
				frm.getSearchList().get(i).setDispVenderName2(
					bean.getDispVenderName2());
				frm.getSearchList().get(i)
						.setVenderName3(bean.getVenderName3());
				frm.getSearchList().get(i).setDispVenderName3(
					bean.getDispVenderName3());
				frm.getSearchList().get(i)
						.setVenderName4(bean.getVenderName4());
				frm.getSearchList().get(i).setDispVenderName4(
					bean.getDispVenderName4());
				frm.getSearchList().get(i)
						.setVenderName5(bean.getVenderName5());
				frm.getSearchList().get(i).setDispVenderName5(
					bean.getDispVenderName5());
			}
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * balanceSearchLogicを設定します。
	 * @param balanceSearchLogic balanceSearchLogic
	 */
	public void setBalanceSearchLogic(
			final BalanceSearchLogic balanceSearchLogic) {
		this.balanceSearchLogic = balanceSearchLogic;
	}
}
