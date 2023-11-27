/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouce;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist.RecipePegResouceDetailList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucedetail.RecipeResouceDetail;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 設備詳細 Actionクラス.
 * @author t0011036
 */
public final class RecipeResouceDetailAction extends AbstractAction {

	private RecipeResouceDetailLogic recipeResouceDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceDetailAction() {
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

		RecipeResouceDetailForm frm = (RecipeResouceDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_RECIPE_RESOUCE,
			Constants.TAB_ID_RECIPE_RESOUCE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 生産ラインセット */
		setLineCombobox(frm);

		/* 初期検索 */
		RecipeResouceDetail bean = recipeResouceDetailLogic.getDetailEntity(frm
				.getResouceCd());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* 前工程設備検索 */
		frm.setSearchRecipePegResouceDetailList(recipeResouceDetailLogic
				.getList(frm.getResouceCd()));

		return mapping.findForward("success");
	}

	/**
	 * 生産ラインリスト取得
	 * @param frm 画面データ
	 */
	public void setLineCombobox(final RecipeResouceDetailForm frm) {
		/* 生産ラインを取得 */
		List<LineListForComboboxes> list = recipeResouceDetailLogic
				.getLineList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getProductionLineName();
			values[i] = list.get(i).getProductionLine();
		}

		frm.setProductionLineLabels(labels);
		frm.setProductionLineValues(values);
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

		RecipeResouceDetailForm frm = (RecipeResouceDetailForm) form;

		if (!StringUtils.isEmpty(frm.getResouceGroupCd())) {
			/* 設備グループコードチェック */
			RecipeResouceGroupDetail beanResouceGroup = recipeResouceDetailLogic
					.getRecipeResouceGroupEntity(frm.getResouceGroupCd());

			if (beanResouceGroup == null) {
				/* エラーメッセージ */
				saveError(request,
					"errors.nodata.recipe.resouce.recipe.resouce.group.cd");
				return mapping.findForward("success");
			}
		}

		for (int i = 0; i < frm.getSearchRecipePegResouceDetailList().size(); i++) {
			if (!StringUtils.isEmpty(frm.getSearchRecipePegResouceDetailList()
					.get(i).getPrevResouceCd())) {
				/* 前工程設備コードチェック */
				RecipeResouceDetail beanRecipeResouce = recipeResouceDetailLogic
						.getDetailEntity(frm
								.getSearchRecipePegResouceDetailList().get(i)
								.getPrevResouceCd());

				if (beanRecipeResouce == null) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.nodata.recipe.resouce.prev.resouce.cd.row",
						i + 1);
					return mapping.findForward("success");
				}
			}
		}

		/* 登録処理を実行 */
		recipeResouceDetailLogic
				.regist(frm, getLoginInfo(request).getTantoCd());

		// if (frm.getUpdateDate() == null) {
		// /* 追加処理を実行 */
		// recipeResouceDetailLogic.insert(insertRecipeResouce(frm,
		// getLoginInfo(request).getTantoCd()));
		// } else {
		// /* 更新処理を実行 */
		// recipeResouceDetailLogic.update(updateRecipeResouce(frm,
		// getLoginInfo(request).getTantoCd()));
		// }

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

		RecipeResouceDetailForm frm = (RecipeResouceDetailForm) form;

		/* 削除処理を実行 */
		// recipeResouceDetailLogic.delete(deleteRecipeResouce(frm,
		// getLoginInfo(
		// request).getTantoCd()));
		recipeResouceDetailLogic.deleteList(deleteRecipeResouce(frm,
			getLoginInfo(request).getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	// /**
	// * 更新処理用のRecipeResouceを作成する.
	// * @param frm 画面データ
	// * @param tantoCd 担当者コード
	// * @return RecipeResouce
	// * @throws InvocationTargetException InvocationTargetException
	// * @throws IllegalAccessException IllegalAccessException
	// */
	// private RecipeResouce updateRecipeResouce(
	// final RecipeResouceDetailForm frm, final String tantoCd)
	// throws IllegalAccessException, InvocationTargetException {
	// RecipeResouce newBean = new RecipeResouce();
	//
	// try {
	// newBean = recipeResouceDetailLogic.getEntity(frm.getResouceCd());
	// } catch (NoDataException e) {
	// return null;
	// }
	//
	// /* 値を更新用Beanにコピる */
	// IgnoreCaseBeanUtils.copyProperties(newBean, frm);
	//
	// /* コピーしきれなかった分は手で */
	// newBean.setCostMachine(AecNumberUtils.convertBigDecimal(frm
	// .getStrCostMachine()));
	// newBean.setCost(AecNumberUtils.convertBigDecimal(frm.getStrCost()));
	// newBean.setUpdatorCd(tantoCd);
	//
	// return newBean;
	// }
	//
	// /**
	// * 追加処理用のRecipeResouceを作成する.
	// * @param bean 画面データ
	// * @param tantoCd 担当者コード
	// * @return RecipeResouce
	// * @throws InvocationTargetException InvocationTargetException
	// * @throws IllegalAccessException IllegalAccessException
	// */
	// private RecipeResouce insertRecipeResouce(
	// final RecipeResouceDetailForm frm, final String tantoCd)
	// throws IllegalAccessException, InvocationTargetException {
	// RecipeResouce newBean = new RecipeResouce();
	//
	// /* 値を更新用Beanにコピる */
	// IgnoreCaseBeanUtils.copyProperties(newBean, frm);
	//
	// /* コピーしきれなかった分は手で */
	// newBean.setCostMachine(AecNumberUtils.convertBigDecimal(frm
	// .getStrCostMachine()));
	// newBean.setCost(AecNumberUtils.convertBigDecimal(frm.getStrCost()));
	// newBean.setInputDate(newBean.getCurrentTimestamp());
	// newBean.setInputorCd(tantoCd);
	// newBean.setUpdatorCd(tantoCd);
	//
	// return newBean;
	// }

	/**
	 * 削除処理用のRecipeResouceを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return RecipeResouce
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private RecipeResouce deleteRecipeResouce(
			final RecipeResouceDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		RecipeResouce newBean = new RecipeResouce();

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

		RecipeResouceDetailForm frm = (RecipeResouceDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_RECIPE_RESOUCE,
			Constants.TAB_ID_RECIPE_RESOUCE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		/* 生産ラインセット */
		setLineCombobox(frm);

		return mapping.findForward("success");
	}

	/**
	 * 行追加処理.addlist
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addlist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("addlist.");
		}

		RecipeResouceDetailForm frm = (RecipeResouceDetailForm) form;

		Boolean isAddList = false;

		for (int i = 0; i < frm.getSearchRecipePegResouceDetailList().size(); i++) {
			if (frm.getSearchRecipePegResouceDetailList().get(i).getChecked()) {
				RecipePegResouceDetailList bean = new RecipePegResouceDetailList();
				frm.getSearchRecipePegResouceDetailList().add(i, bean);
				frm.getSearchRecipePegResouceDetailList().get(i).setChecked(
					false);
				isAddList = true;
				break;
			}
		}

		/* 最終行へ追加 */
		if (!isAddList) {
			RecipePegResouceDetailList bean = new RecipePegResouceDetailList();
			frm.getSearchRecipePegResouceDetailList().add(bean);
			frm.getSearchRecipePegResouceDetailList().get(
				frm.getSearchRecipePegResouceDetailList().size() - 1)
					.setChecked(false);
		}

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理.dellist
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward dellist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("dellist.");
		}

		RecipeResouceDetailForm frm = (RecipeResouceDetailForm) form;

		for (int i = frm.getSearchRecipePegResouceDetailList().size() - 1; i >= 0; i--) {
			if (frm.getSearchRecipePegResouceDetailList().get(i).getChecked()) {
				frm.getSearchRecipePegResouceDetailList().remove(i);
			}
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * recipeResouceDetailLogicを設定します。
	 * @param recipeResouceDetailLogic recipeResouceDetailLogic
	 */
	public void setRecipeResouceDetailLogic(
			final RecipeResouceDetailLogic recipeResouceDetailLogic) {
		this.recipeResouceDetailLogic = recipeResouceDetailLogic;
	}
}
