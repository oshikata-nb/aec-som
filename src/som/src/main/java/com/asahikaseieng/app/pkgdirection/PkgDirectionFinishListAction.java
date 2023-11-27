/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.direction.DirectionUtil;
import com.asahikaseieng.app.mgrecipe.MgrecipeUtil;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;

/**
 * 包装指図－仕上一覧画面 Actionクラス
 * @author tosco
 */
public class PkgDirectionFinishListAction extends AbstractPkgDirectionAction {

	/** 包装指図－フォーミュラロジック */
	private PkgDirectionFinishListLogic pkgDirectionFinishListLogic;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionFinishListAction() {
	}

	/**
	 * タブID（PkgDirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return PkgDirectionConst.FINISHLIST;
	}

	/**
	 * 各子クラスの画面初期表示処理
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

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		PkgDirectionFinishListForm listForm = (PkgDirectionFinishListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_FINISH);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		BigDecimal directionDiv = new BigDecimal(listForm
				.getDirectionDivision());

		// 一覧検索処理
		List<PkgDirectionDirectionFormulaList> searchList = pkgDirectionFinishListLogic
				.getSearchList(directionDiv, listForm.getDirectionNo());
		listForm.setSearchFinishList(searchList);

		// 数量のフォーマット
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		for (PkgDirectionDirectionFormulaList bean : searchList) {
			// 生産予定数量
			bean.setStrQty(checker.format(
				PkgDirectionConst.UNIT_DIVISION_HAIGO, bean.getQty()));
			// 充填予定数量
			bean.setStrFillQty(checker.format(bean.getUnitDivision(), bean
					.getFillQty()));
			// 数値フォーマットデータの設定
			NumberChkDisitDetail checkDetail = checker.getCheckDigit(bean
					.getUnitDivision(), null, null);
			// 少数点桁数
			bean.setDecimalPoint(DirectionUtil.getBigDecimalString(checkDetail
					.getSmallnumLength()));
			// 端数区分
			bean.setRoundDivision(DirectionUtil.getBigDecimalString(checkDetail
					.getRoundDivision()));

		}

		// 数値フォーマットデータの設定
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			PkgDirectionConst.UNIT_DIVISION_HAIGO, null, null);
		// 少数点桁数
		listForm.setDecimalPointHaigo(DirectionUtil
				.getBigDecimalString(checkDetail.getSmallnumLength()));
		// 端数区分
		listForm.setRoundDivisionHaigo(DirectionUtil
				.getBigDecimalString(checkDetail.getRoundDivision()));

		// 工程順序コンボボックスの作成
		listForm.setStepNoCombo(pkgDirectionCommonsLogic
				.createProcedureSetpNoCombobox(directionDiv, listForm
						.getDirectionNo(), false));

		return mapping.findForward("success");
	}

	/**
	 * 行追加処理.
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

		PkgDirectionFinishListForm frm = (PkgDirectionFinishListForm) form;
		List<PkgDirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 工程データ未登録の場合エラー
		if (frm.getStepNoCombo().size() == 0) {
			ActionMessages errors = new ActionMessages();
			errors = MgrecipeUtil.addError(errors,
				"errors.pkgdirection.no.procedure");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		PkgDirectionDirectionFormulaList bean = new PkgDirectionDirectionFormulaList();
		bean.setDirectionDivision(new BigDecimal(frm.getDirectionDivision())); // 指図区分
		bean.setDirectionNo(frm.getDirectionNo()); // 指図番号

		// 要素がない場合
		if (searchList.size() == 0) {
			frm.getSearchFinishList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchList.size(); i++) {
				PkgDirectionDirectionFormulaList formulaBean = searchList
						.get(i);
				// チェックボックスがチェックされていた場合
				if (formulaBean.isCheckFlg()) {
					// 新しい要素を追加
					searchList.add(i, bean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == searchList.size() - 1) {
					// 新しい要素を追加
					frm.getSearchFinishList().add(bean);
					break;
				}
			}
		}

		// チェックのクリア
		for (PkgDirectionDirectionFormulaList formulaBean : searchList) {
			formulaBean.setCheckFlg(Boolean.FALSE);
		}

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理.
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

		PkgDirectionFinishListForm frm = (PkgDirectionFinishListForm) form;
		List<PkgDirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 削除処理
		for (int i = searchList.size() - 1; i >= 0; i--) {
			PkgDirectionDirectionFormulaList formulaBean = searchList.get(i);

			if (!formulaBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 行削除
			searchList.remove(i);
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
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("regist");
		}

		PkgDirectionFinishListForm frm = (PkgDirectionFinishListForm) form;

		ActionMessages errors = new ActionMessages();
		// 品目マスタ存在チェック
		errors = pkgDirectionFinishListLogic.checkForRegist(frm);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		try {
			// 包装指図－指図－プロシージャ更新処理
			pkgDirectionFinishListLogic.regist(frm, getLoginInfo(request)
					.getTantoCd());

			// 包装計画削除
			pkgDirectionCommonsLogic.deleteHosoKeikaku(frm.getDirectionNo());

		} catch (PkgDirectionLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reInit");
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
	public ActionForward reInit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reInit");
		}

		PkgDirectionFinishListForm frm = (PkgDirectionFinishListForm) form;

		// 検索結果リストクリア
		frm
				.setSearchFinishList(new ArrayList<PkgDirectionDirectionFormulaList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－フォーミュラロジックを設定します。
	 * @param pkgDirectionFinishListLogic 包装指図－フォーミュラロジック
	 */
	public void setPkgDirectionFinishListLogic(
			final PkgDirectionFinishListLogic pkgDirectionFinishListLogic) {
		this.pkgDirectionFinishListLogic = pkgDirectionFinishListLogic;
	}

}
