/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist.ItemRelatedGrecipeList;
import com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist.ItemRelatedMgrecipeList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 関連情報 Actionクラス.
 * @author t0011036
 */
public final class ItemRelatedAction extends AbstractAction {

	private ItemRelatedLogic itemRelatedLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemRelatedAction() {
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

		ItemRelatedForm frm = (ItemRelatedForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_RELATED);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemRelatedLogic.getHeaderEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		/* 原処方初期検索 */
		List<ItemRelatedGrecipeList> listGrecipe = itemRelatedLogic
				.getGrecipeList(frm.getItemCd());

		/* 基本処方初期検索 */
		List<ItemRelatedMgrecipeList> listMgrecipe = itemRelatedLogic
				.getMgrecipeList(frm.getItemCd());

		for (int i = 0; i < listGrecipe.size(); i++) {
			/* 日付文字列に変換 */
			listGrecipe.get(i).setStrGrecipeUpdateDate(
				AecDateUtils.dateFormat(listGrecipe.get(i)
						.getGrecipeUpdateDate(), "yyyy/MM/dd"));
		}

		for (int i = 0; i < listMgrecipe.size(); i++) {
			/* 日付文字列に変換 */
			listMgrecipe.get(i).setStrMgrecipeUpdateDate(
				AecDateUtils.dateFormat(listMgrecipe.get(i)
						.getMgrecipeUpdateDate(), "yyyy/MM/dd"));
		}

		/* 明細取得 */
		frm.setSearchGrecipeList(listGrecipe);
		frm.setSearchMgrecipeList(listMgrecipe);

		String link = null;

		/* 原処方リンク設定 */
		for (int i = 0; i < frm.getSearchGrecipeList().size(); i++) {
			link = "/GrecipeHeader.do?op=init&recipeId="
					+ frm.getSearchGrecipeList().get(i).getRecipeId()
							.toString() + "&srhLink=1";

			frm.getSearchGrecipeList().get(i).setLink(link);
		}

		/* 基本処方リンク設定 */
		for (int i = 0; i < frm.getSearchMgrecipeList().size(); i++) {
			link = "/MgrecipeHeader.do?op=init&recipeId="
					+ frm.getSearchMgrecipeList().get(i).getRecipeId()
							.toString() + "&srhLink=1";

			frm.getSearchMgrecipeList().get(i).setLink(link);
		}

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
	 * itemRelatedLogicを設定します。
	 * @param itemRelatedLogic itemRelatedLogic
	 */
	public void setItemRelatedLogic(final ItemRelatedLogic itemRelatedLogic) {
		this.itemRelatedLogic = itemRelatedLogic;
	}
}
