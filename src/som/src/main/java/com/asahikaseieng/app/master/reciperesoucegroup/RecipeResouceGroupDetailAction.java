/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.reciperesoucegroup.RecipeResouceGroup;
import com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.OperationGroupListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 設備グループ詳細 Actionクラス.
 * @author t0011036
 */
public final class RecipeResouceGroupDetailAction extends AbstractAction {

	private RecipeResouceGroupDetailLogic recipeResouceGroupDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupDetailAction() {
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

		RecipeResouceGroupDetailForm frm = (RecipeResouceGroupDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_RECIPE_RESOUCE_GROUP,
			Constants.TAB_ID_RECIPE_RESOUCE_GROUP_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 工程グループセット */
		setOperationGroupCombobox(frm);

		/* 初期検索 */
		RecipeResouceGroupDetail bean = recipeResouceGroupDetailLogic
				.getDetailEntity(frm.getResouceGroupCd());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		return mapping.findForward("success");
	}

	/**
	 * 工程グループリスト取得
	 * @param frm 画面データ
	 */
	public void setOperationGroupCombobox(final RecipeResouceGroupDetailForm frm) {
		/* 設備グループグループマスタを取得 */
		List<OperationGroupListForComboboxes> list = recipeResouceGroupDetailLogic
				.getOperationGroupList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getOperationGroupName();
			values[i] = list.get(i).getOperationGroupCd();
		}

		frm.setOperationGroupLabels(labels);
		frm.setOperationGroupValues(values);
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

		RecipeResouceGroupDetailForm frm = (RecipeResouceGroupDetailForm) form;

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			recipeResouceGroupDetailLogic.insert(insertRecipeResouceGroup(frm,
				getLoginInfo(request).getTantoCd()));
		} else {
			/* 更新処理を実行 */
			recipeResouceGroupDetailLogic.update(updateRecipeResouceGroup(frm,
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

		RecipeResouceGroupDetailForm frm = (RecipeResouceGroupDetailForm) form;

		/* 削除処理を実行 */
		recipeResouceGroupDetailLogic.delete(deleteRecipeResouceGroup(frm,
			getLoginInfo(request).getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のRecipeResouceGroupを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return RecipeResouceGroup
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private RecipeResouceGroup updateRecipeResouceGroup(
			final RecipeResouceGroupDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		RecipeResouceGroup newBean = new RecipeResouceGroup();

		try {
			newBean = recipeResouceGroupDetailLogic.getEntity(frm
					.getResouceGroupCd());
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
	 * 追加処理用のRecipeResouceGroupを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return RecipeResouceGroup
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private RecipeResouceGroup insertRecipeResouceGroup(
			final RecipeResouceGroupDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		RecipeResouceGroup newBean = new RecipeResouceGroup();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のRecipeResouceGroupを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return RecipeResouceGroup
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private RecipeResouceGroup deleteRecipeResouceGroup(
			final RecipeResouceGroupDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		RecipeResouceGroup newBean = new RecipeResouceGroup();

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

		RecipeResouceGroupDetailForm frm = (RecipeResouceGroupDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_RECIPE_RESOUCE_GROUP,
			Constants.TAB_ID_RECIPE_RESOUCE_GROUP_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		/* 工程グループセット */
		setOperationGroupCombobox(frm);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * recipeResouceGroupDetailLogicを設定します。
	 * @param recipeResouceGroupDetailLogic recipeResouceGroupDetailLogic
	 */
	public void setRecipeResouceGroupDetailLogic(
			final RecipeResouceGroupDetailLogic recipeResouceGroupDetailLogic) {
		this.recipeResouceGroupDetailLogic = recipeResouceGroupDetailLogic;
	}
}
