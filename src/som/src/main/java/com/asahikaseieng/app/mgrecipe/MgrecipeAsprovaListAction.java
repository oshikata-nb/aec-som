/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 基本処方-Asprova一覧 Actionクラス.
 * @author tosco
 */
public final class MgrecipeAsprovaListAction extends AbstractMgrecipeAction {

	/** Asprova一覧検索 ロジッククラス */
	private MgrecipeAsprovaListLogic mgrecipeAsprovaListLogic;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeAsprovaListAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-Asprova一覧
		return MgrecipeConst.ASPROVALIST;
	}

	/**
	 * 画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	@Override
	protected ActionForward initProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		MgrecipeAsprovaListForm frm = (MgrecipeAsprovaListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_ASPROVA);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期表示
		initDisp(frm);

		frm.setUpperSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_INIT);
		frm.setLowerSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_INIT);

		if (StringUtils.isEmpty(frm.getSrhLink())) {
			frm.setSrhLink("0");
		}

		/* 設備グループコード取得(上段) */
		if (!StringUtils.isEmpty(frm.getUpperOpeGrpCd())) {
			RecipeAsprovaList bean = mgrecipeAsprovaListLogic
					.getResouceGroupCd(new BigDecimal(frm.getRecipeId()), frm
							.getUpperOpeGrpCd());

			if (bean != null) {
				frm.setUpperResGrpCd(bean.getResouceGroupCd());
				searchUpperList(mapping, form, request, response);
			}
		}

		/* 設備グループコード取得(下段) */
		if (!StringUtils.isEmpty(frm.getLowerOpeGrpCd())) {
			RecipeAsprovaList bean = mgrecipeAsprovaListLogic
					.getResouceGroupCd(new BigDecimal(frm.getRecipeId()), frm
							.getLowerOpeGrpCd());

			if (bean != null) {
				frm.setLowerResGrpCd(bean.getResouceGroupCd());
				searchLowerList(mapping, form, request, response);
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 初期表示
	 * @param frm Asprovaタグ一覧情報
	 */
	private void initDisp(final MgrecipeAsprovaListForm frm) {
		BigDecimal recipeUse = AecNumberUtils.convertBigDecimal(frm
				.getRecipeUse()); // 用途

		if (recipeUse.compareTo(MgrecipeConst.RECIPE_USE_PRODUCTION) == 0) {
			// 用途が製造の場合

			// 初期表示画面項目設定
			mgrecipeAsprovaListLogic.makeInitDispProduction(frm);
		} else if (recipeUse.compareTo(MgrecipeConst.RECIPE_USE_PACKING) == 0) {
			// 用途が包装の場合

			// 初期表示画面項目設定
			mgrecipeAsprovaListLogic.makeInitDispPacking(frm);
		}
	}

	/**
	 * 上段検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchUpperList(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		MgrecipeAsprovaListForm frm = (MgrecipeAsprovaListForm) form;
		// 設備グループコードを保存する
		frm.setTempUpperResGrpCd(frm.getUpperResGrpCd());

		// 用途
		BigDecimal recipeUse = AecNumberUtils.convertBigDecimal(frm
				.getRecipeUse());
		ActionMessages errors = new ActionMessages();
		if (recipeUse.compareTo(MgrecipeConst.RECIPE_USE_PRODUCTION) == 0) {
			// 用途が製造の場合
			// エラー
			if (frm.getUpperResGrpCd() == null
					|| frm.getUpperResGrpCd().equals("")) {
				// 調合工程が登録されていません。
				errors = MgrecipeUtil.addError(errors,
					"errors.mgrecipe.no.compound");
				super.saveErrors(request, errors);
				return mapping.findForward("error");
			}

		} else if (recipeUse.compareTo(MgrecipeConst.RECIPE_USE_PACKING) == 0) {
			// 用途が包装の場合
			// エラー
			if (frm.getDispLowerListFlg().equals("0")
					&& (frm.getUpperResGrpCd() == null || frm
							.getUpperResGrpCd().equals(""))) {
				// 包装工程が登録されていません。
				errors = MgrecipeUtil.addError(errors,
					"errors.mgrecipe.no.packing");
				super.saveErrors(request, errors);
				return mapping.findForward("error");
			} else if (frm.getDispLowerListFlg().equals("1")
					&& (frm.getUpperResGrpCd() == null || frm
							.getUpperResGrpCd().equals(""))) {
				// 充填工程が登録されていません。
				errors = MgrecipeUtil.addError(errors,
					"errors.mgrecipe.no.filling");
				super.saveErrors(request, errors);
				return mapping.findForward("error");
			}

		}

		// 検索処理
		List<RecipeAsprovaList> searchList = mgrecipeAsprovaListLogic
				.getSearchList(new BigDecimal(frm.getRecipeId()), frm
						.getUpperResGrpCd(), frm.getUpperOpeGrpCd(), frm
						.getProductionLine());

		/* リンクフラグセット */
		for (int i = 0; i < searchList.size(); i++) {
			searchList.get(i).setSrhLink(frm.getSrhLink());
		}

		// 上段に検索結果設定
		setUpperSearchResult(frm, searchList);

		return mapping.findForward("success");
	}

	/**
	 * 上段に検索結果設定
	 * @param frm Asprovaタグ一覧情報
	 * @param searchList 検索結果
	 */
	private void setUpperSearchResult(final MgrecipeAsprovaListForm frm,
			final List<RecipeAsprovaList> searchList) {
		// 検索リスト設定
		frm.setUpperSearchList(searchList);
		frm.setTempUpperResGrpCd(frm.getUpperResGrpCd());

		// 検索結果 状態設定
		// if (searchList.get(0).isCheckFlg()) {
		// frm
		// .setUpperSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_ASPROVA);
		// } else {
		// frm
		// .setUpperSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_RESOUCE);
		// }
		Boolean isCheck = false;

		for (int i = 0; i < searchList.size(); i++) {
			if (searchList.get(i).isCheckFlg()) {
				isCheck = true;
				break;
			}
		}

		if (isCheck) {
			/* 基本処方Asprovaデータ込み */
			frm
					.setUpperSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_ASPROVA);
		} else {
			/* 設備データのみ */
			frm
					.setUpperSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_RESOUCE);
		}

		// 要員数の設定
		for (int i = 0; i < searchList.size(); i++) {
			if (searchList.get(i).getYouinsu() != null) {
				frm.setUpperYouinsu(searchList.get(i).getYouinsu().toString());
				break;
			}
		}

		frm.setDirtyFlg(null);
	}

	/**
	 * 下段検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchLowerList(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		MgrecipeAsprovaListForm frm = (MgrecipeAsprovaListForm) form;
		frm.setDirtyFlg(null);

		// エラー
		if (frm.getDispLowerListFlg().equals("0")
				&& frm.getLowerResGrpCd() == null
				|| frm.getLowerResGrpCd().equals("")) {
			ActionMessages errors = new ActionMessages();
			// 包装工程が登録されていません。
			errors = MgrecipeUtil
					.addError(errors, "errors.mgrecipe.no.packing");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 検索処理
		List<RecipeAsprovaList> searchList = mgrecipeAsprovaListLogic
				.getSearchList(new BigDecimal(frm.getRecipeId()), frm
						.getLowerResGrpCd(), frm.getLowerOpeGrpCd(), frm
						.getProductionLine());

		/* リンクフラグセット */
		for (int i = 0; i < searchList.size(); i++) {
			searchList.get(i).setSrhLink(frm.getSrhLink());
		}

		// 下段に検索結果設定
		setLowerSearchResult(frm, searchList);

		return mapping.findForward("success");
	}

	/**
	 * 下段に検索結果設定
	 * @param frm Asprovaタグ一覧情報
	 * @param searchList 検索結果
	 */
	private void setLowerSearchResult(final MgrecipeAsprovaListForm frm,
			final List<RecipeAsprovaList> searchList) {
		// 検索リスト設定
		frm.setLowerSearchList(searchList);
		frm.setTempLowerResGrpCd(frm.getLowerResGrpCd());

		// 検索結果 状態設定
		// if (searchList.get(0).isCheckFlg()) {
		// frm
		// .setLowerSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_ASPROVA);
		// } else {
		// frm
		// .setLowerSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_RESOUCE);
		// }
		Boolean isCheck = false;

		for (int i = 0; i < searchList.size(); i++) {
			if (searchList.get(i).isCheckFlg()) {
				isCheck = true;
				break;
			}
		}

		if (isCheck) {
			/* 基本処方Asprovaデータ込み */
			frm
					.setLowerSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_ASPROVA);
		} else {
			/* 設備データのみ */
			frm
					.setLowerSearchDiv(MgrecipeAsprovaListForm.SEARCH_DIVISION_RESOUCE);
		}

		// 要員数の設定
		for (int i = 0; i < searchList.size(); i++) {
			if (searchList.get(i).getYouinsu() != null) {
				frm.setLowerYouinsu(searchList.get(i).getYouinsu().toString());
				break;
			}
		}

		frm.setDirtyFlg(null);
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
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("regist");
		}

		MgrecipeAsprovaListForm frm = (MgrecipeAsprovaListForm) form;

		// 処方ASPROVA更新処理
		mgrecipeAsprovaListLogic
				.regist(frm, getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 再検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reSearch(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reSearch");
		}

		// 共通部分を取得
		AbstractMgrecipeForm commonForm = (AbstractMgrecipeForm) form;
		// 固有部分を取得
		MgrecipeAsprovaListForm frm = (MgrecipeAsprovaListForm) form;

		// どうしても必要な値のみ保持
		String strRecipeId = commonForm.getRecipeId();
		String upperSearchDiv = frm.getUpperSearchDiv();
		String lowerSearchDiv = frm.getLowerSearchDiv();
		String upperResGrpCd = frm.getTempUpperResGrpCd();
		String lowerResGrpCd = frm.getTempLowerResGrpCd();

		// 全クリア
		commonForm.clear();

		// 移し変え
		commonForm.setRecipeId(strRecipeId); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID
		frm.setUpperSearchDiv(upperSearchDiv); // 上段検索区分
		frm.setLowerSearchDiv(lowerSearchDiv); // 下段検索区分
		frm.setUpperResGrpCd(upperResGrpCd); // 上段設備グループコード
		frm.setLowerResGrpCd(lowerResGrpCd); // 下段設備グループコード
		frm.setTempUpperResGrpCd(upperResGrpCd); // Temp上段設備グループコード
		frm.setTempLowerResGrpCd(lowerResGrpCd); // Temp下段設備グループコード

		// 共通情報再検索処理
		RecipeHeaderList header = mgrecipeCommonsLogic
				.getCommonHeader(strRecipeId);
		setCommonHeaderInfo(commonForm, header);

		// 初期表示
		initDisp(frm);

		if (!frm.getUpperSearchDiv().equals(
			MgrecipeAsprovaListForm.SEARCH_DIVISION_INIT)) {
			// 検索処理
			List<RecipeAsprovaList> searchList = mgrecipeAsprovaListLogic
					.getSearchList(new BigDecimal(frm.getRecipeId()), frm
							.getUpperResGrpCd(), frm.getUpperOpeGrpCd(), frm
							.getProductionLine());

			/* リンクフラグセット */
			for (int i = 0; i < searchList.size(); i++) {
				searchList.get(i).setSrhLink(frm.getSrhLink());
			}

			// 上段に検索結果設定
			setUpperSearchResult(frm, searchList);
		}

		if (!frm.getLowerSearchDiv().equals(
			MgrecipeAsprovaListForm.SEARCH_DIVISION_INIT)) {
			// 検索処理
			List<RecipeAsprovaList> searchList = mgrecipeAsprovaListLogic
					.getSearchList(new BigDecimal(frm.getRecipeId()), frm
							.getLowerResGrpCd(), frm.getLowerOpeGrpCd(), frm
							.getProductionLine());

			/* リンクフラグセット */
			for (int i = 0; i < searchList.size(); i++) {
				searchList.get(i).setSrhLink(frm.getSrhLink());
			}

			// 下段に検索結果設定
			setLowerSearchResult(frm, searchList);

		}
		frm.setDirtyFlg(null);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * Asprova一覧検索 ロジッククラスを設定します。
	 * @param mgrecipeAsprovaListLogic Asprova一覧検索 ロジッククラス
	 */
	public void setMgrecipeAsprovaListLogic(
			final MgrecipeAsprovaListLogic mgrecipeAsprovaListLogic) {
		this.mgrecipeAsprovaListLogic = mgrecipeAsprovaListLogic;
	}

}
