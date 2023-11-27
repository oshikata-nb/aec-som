/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.materialrinput;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchListPagerCondition;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 外注原材料投入実績入力画面_ロット検索ポップアップ画面 Actionクラス.
 * @author tosco
 */
public final class MaterialRinputLotInventorySearchAction extends AbstractSearchAction {

	/** 外注原材料投入実績入力画面_ロット検索ポップアップ画面ロジック */
	private MaterialRinputLotInventorySearchLogic materialRinputLotInventorySearchLogic;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputLotInventorySearchAction() {
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

		/* 初期検索有り */
		return null;
	}

	/**
	 * 在庫検索処理
	 * 
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

		MaterialRinputLotInventorySearchForm frm = (MaterialRinputLotInventorySearchForm) form;

		/* 検索に失敗しても明細が消されるように、ここでクリア */
		frm.setSearchList(new ArrayList<MaterialRinputLotInventorySearchList>());

		/* 検索条件を取得 */
		MaterialRinputLotInventorySearchListPagerCondition condition
			= (MaterialRinputLotInventorySearchListPagerCondition) frm.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setItemCd(frm.getSrhItemCd());

		/* 明細取得 */
		frm.setSearchList(materialRinputLotInventorySearchLogic.getSearchList(condition));

		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		// 品目名称を設定
		List<MaterialRinputLotInventorySearchList> list = frm.getSearchList();
		if (list != null && (!list.isEmpty())) {
			MaterialRinputLotInventorySearchList bean = list.get(0);
			frm.setSrhItemName(bean.getItemName());
			list.get(0).setStrResultQty(frm.getStrSumUseQty());
		}

		frm.setTotalQty(frm.getStrSumUseQty());
		frm.setDirtyFlg("false");

		// javascriptでの桁数丸め用に必要となる値取得
		getCheckDigit(frm, check);

		return mapping.findForward("success");
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm   入荷入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 * @param bean  入荷入力Bean
	 */
	private void getCheckDigit(final MaterialRinputLotInventorySearchForm frm
								, final CheckDigitUtilsLogic check) {
		// 配合
		NumberChkDisitDetail detailHaigo =
			check.getCheckDigit(MaterialRinputDetailLogicImpl.UNIT_DIVISION_HAIGO
							, MaterialRinputDetailLogicImpl.VENDER_DIV_SI
							, null);
		if (detailHaigo.getSmallnumLength() != null) {
			frm.setDecimalPointHaigo(detailHaigo.getSmallnumLength().toString());	// 小数点桁数
		}
		if (detailHaigo.getRoundDivision() != null) {
			frm.setRoundHaigo(detailHaigo.getRoundDivision().toString());			// 端数区分
		}
	}

	/**
	 * 登録処理
	 *
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("regist.");
		}
		MaterialRinputLotInventorySearchForm frm = (MaterialRinputLotInventorySearchForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 在庫引落情報登録
			materialRinputLotInventorySearchLogic.insert(frm, tantoCd);
		} catch (LogicExceptionEx e) {
			String errMsg = "errors.materialrinput.move.stock.update";
			// 在庫更新に失敗
			if (errMsg.equals(e.getMessage())) {
				saveError(request, errMsg);
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}
		return mapping.findForward("back");
	}

	/**
	 * 戻る処理
	 *
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
	 * ロット検索ポップアップ画面ロジック.
	 * @param materialRinputLotInventorySearchLogic ロット検索ポップアップ画面ロジック
	 */
	public void setMaterialRinputLotInventorySearchLogic
		(final MaterialRinputLotInventorySearchLogic materialRinputLotInventorySearchLogic) {
		this.materialRinputLotInventorySearchLogic = materialRinputLotInventorySearchLogic;
	}
}
