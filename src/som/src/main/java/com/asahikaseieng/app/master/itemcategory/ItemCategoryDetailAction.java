/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.itemcategory.ItemCategory;
import com.asahikaseieng.dao.nonentity.master.itemcategorydetail.ItemCategoryDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 品目分類詳細 Actionクラス.
 * @author t0011036
 */
public final class ItemCategoryDetailAction extends AbstractAction {

	private ItemCategoryDetailLogic itemCategoryDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemCategoryDetailAction() {
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

		ItemCategoryDetailForm frm = (ItemCategoryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM_CATEGORY,
			Constants.TAB_ID_ITEM_CATEGORY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		ItemCategoryDetail bean = itemCategoryDetailLogic.getDetailEntity(frm
				.getItemCategory());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

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

		ItemCategoryDetailForm frm = (ItemCategoryDetailForm) form;

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			itemCategoryDetailLogic.insert(insertItemCategory(frm,
				getLoginInfo(request).getTantoCd()));
		} else {
			/* 更新処理を実行 */
			itemCategoryDetailLogic.update(updateItemCategory(frm,
				getLoginInfo(request).getTantoCd()));
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

		ItemCategoryDetailForm frm = (ItemCategoryDetailForm) form;

		/* 削除処理を実行 */
		itemCategoryDetailLogic.delete(deleteItemCategory(frm, getLoginInfo(
			request).getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のItemCategoryを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return ItemCategory
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ItemCategory updateItemCategory(final ItemCategoryDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		ItemCategory newBean = new ItemCategory();

		try {
			newBean = itemCategoryDetailLogic.getEntity(frm.getItemCategory());
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
	 * 追加処理用のItemCategoryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ItemCategory
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ItemCategory insertItemCategory(final ItemCategoryDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		ItemCategory newBean = new ItemCategory();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のItemCategoryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ItemCategory
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ItemCategory deleteItemCategory(final ItemCategoryDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		ItemCategory newBean = new ItemCategory();

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

		ItemCategoryDetailForm frm = (ItemCategoryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM_CATEGORY,
			Constants.TAB_ID_ITEM_CATEGORY_DETAIL);

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
	 * itemCategoryDetailLogicを設定します。
	 * @param itemCategoryDetailLogic itemCategoryDetailLogic
	 */
	public void setItemCategoryDetailLogic(
			final ItemCategoryDetailLogic itemCategoryDetailLogic) {
		this.itemCategoryDetailLogic = itemCategoryDetailLogic;
	}
}
