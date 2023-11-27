/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.recipeheader;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectDirectionShipperDivision;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchList;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 基本処方検索(ポップアップ) Actionクラス.
 * @author tosco
 */
public final class RecipeHeaderSearchAction extends AbstractSearchAction {
	/** 荷主：花王(1) */
	private static final String SHIPPER_DIVISION_KAO = "1";

	/** 基本処方検索(ポップアップ)ロジック */
	private RecipeHeaderSearchLogic recipeHeaderSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderSearchAction() {
		super();
	}

	/**
	 * 初期処理
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initImpl");
		}
		RecipeHeaderSearchForm searchForm = (RecipeHeaderSearchForm) form;
		//生産工場コンボボックス作成
		List<ComboBoxItems> combo = recipeHeaderSearchLogic.createLineCombobox();
		searchForm.setLineCombo(combo);
		//初期検索条件を設定する
		searchForm.setShipperDivision(SelectDirectionShipperDivision.OWN);	//1:自社
		for (ComboBoxItems item : combo) {
			//最初の1件目の値を設定
			searchForm.setProductLine(item.getValues());
			break;
		}
		return null;
	}

	/**
	 * 基本処方検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchImpl");
		}

		RecipeHeaderSearchForm searchForm = (RecipeHeaderSearchForm) form;

		//検索結果をクリア
		searchForm.setSearchList(new ArrayList<RecipeHeaderSearchList>());

		//検索条件を取得
		RecipeHeaderSearchListPagerCondition condition =
			(RecipeHeaderSearchListPagerCondition) searchForm.getPager().getPagerCondition();

		condition.setRecipeCd(searchForm.getRecipeCd());
		condition.setRecipeName(searchForm.getRecipeName());
		condition.setItemCd(searchForm.getItemCd());
		condition.setShipperDivision(searchForm.getShipperDivision());
		condition.setOtherCompanyCd1(searchForm.getOtherCompanyCd1());

		String productionLine = searchForm.getProductLine(); // 生産工場

		if (MgrecipeConst.COMBO_ALL_VALUE.equals(productionLine)) {
			// 生産工場=0:すべての場合
			condition.setProductionLine(null);
		} else {
			// 生産工場=0:すべて以外の場合
			condition.setProductionLine(productionLine);
		}

		//基本処方を検索
		List<RecipeHeaderSearchList> list = recipeHeaderSearchLogic.getList(condition);
		//編集
		edit(list);
		searchForm.setSearchList(list);

		return mapping.findForward("success");
	}
	/**
	 * 自社・花王区分の表示名を編集
	 * @param list 検索結果
	 */
	private void edit(final List<RecipeHeaderSearchList> list) {
		for (RecipeHeaderSearchList bean : list) {
			if (SHIPPER_DIVISION_KAO.equals(bean.getShipperDivision())) {
				//花王の場合
				bean.setShipperDivisionName(SelectDirectionShipperDivision.KAO + ":花王");
			} else {
				//自社
				bean.setShipperDivisionName(SelectDirectionShipperDivision.OWN + ":自社");
			}
		}
	}
	/* -------------------- setter -------------------- */

	/**
	 * 基本処方検索(ポップアップ)ロジックを設定します。
	 * @param recipeHeaderSearchLogic 基本処方検索(ポップアップ)ロジック
	 */
	public void setItemSearchLogic(final RecipeHeaderSearchLogic recipeHeaderSearchLogic) {
		this.recipeHeaderSearchLogic = recipeHeaderSearchLogic;
	}
}
