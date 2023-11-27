/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.changehistorylist.ChangeHistoryList;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 品目更新履歴 Actionクラス.
 * @author t0011036
 */
public final class ItemHistoryAction extends AbstractAction {

	private ItemHistoryLogic itemHistoryLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemHistoryAction() {
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

		ItemHistoryForm frm = (ItemHistoryForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_HISTORY);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemHistoryLogic.getHeaderEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		/* 初期検索 */
		List<ChangeHistoryList> list = itemHistoryLogic.getList(new BigDecimal(
				"60"), frm.getItemCd());

		for (int i = 0; i < list.size(); i++) {
			/* 日付文字列に変換 */
			list.get(i).setStrUpdateDate(
				AecDateUtils.dateFormat(list.get(i).getUpdateDate(),
					"yyyy/MM/dd HH:mm:ss"));
		}

		/* 明細取得 */
		frm.setSearchHistoryList(list);

		return mapping.findForward("success");
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

	/* -------------------- setter -------------------- */

	/**
	 * itemHistoryLogicを設定します。
	 * @param itemHistoryLogic itemHistoryLogic
	 */
	public void setItemHistoryLogic(final ItemHistoryLogic itemHistoryLogic) {
		this.itemHistoryLogic = itemHistoryLogic;
	}
}
