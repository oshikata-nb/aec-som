/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造実績－仕上タブ画面
 * @author tosco
 */
public class RdirectionFinishListAction extends AbstractRdirectionAction {

	/** 製造実績－フォーミュラ */
	private RdirectionFinishListLogic rdirectionFinishListLogic;

	/**
	 * コンストラクタ
	 */
	public RdirectionFinishListAction() {
	}

	/**
	 * タブID（RdirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return RdirectionConst.FINISHLIST;
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

		RdirectionFinishListForm listForm = (RdirectionFinishListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_FINISH);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 一覧検索処理
		List<RdirectionDirectionFormulaList> searchList = rdirectionFinishListLogic
				.getSearchList(listForm.getDirectionNo());
		listForm.setSearchFinishList(searchList);

		// 数量フォーマット
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		for (RdirectionDirectionFormulaList bean : searchList) {
			bean.setStrQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO, bean
					.getQty()));
			bean.setStrResultQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO,
				bean.getResultQty()));
		}
		// 数値フォーマットデータ(javascript用)の設定
		setCheckDetail(listForm, checker);

		// 工程順序コンボボックスの作成
		listForm
				.setStepNoCombo(rdirectionCommonsLogic
						.createProcedureSetpNoCombobox(listForm
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

		RdirectionFinishListForm frm = (RdirectionFinishListForm) form;
		List<RdirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 工程コンボリストがない場合のチェック
		if (frm.getStepNoCombo().size() == 0) {
			ActionMessages errors = new ActionMessages();
			errors = MgrecipeUtil.addError(errors,
				"errors.rdirection.no.procedure");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		RdirectionDirectionFormulaList bean = new RdirectionDirectionFormulaList();
		bean.setDirectionDivision(BigDecimal.ONE); // 指図区分
		bean.setDirectionNo(frm.getDirectionNo()); // 指図番号

		// 要素がない場合
		if (searchList.size() == 0) {
			frm.getSearchFinishList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchList.size(); i++) {
				RdirectionDirectionFormulaList rdirectionBean = searchList
						.get(i);
				// チェックボックスがチェックされていた場合
				if (rdirectionBean.isCheckFlg()) {
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

		// チェックボックスクリア
		for (RdirectionDirectionFormulaList directionBean : searchList) {
			directionBean.setCheckFlg(Boolean.FALSE);
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

		RdirectionFinishListForm frm = (RdirectionFinishListForm) form;
		List<RdirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 削除処理
		for (int i = searchList.size() - 1; i >= 0; i--) {
			RdirectionDirectionFormulaList rdirectionBean = searchList.get(i);

			if (!rdirectionBean.isCheckFlg()) {
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

		RdirectionFinishListForm frm = (RdirectionFinishListForm) form;
		List<RdirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();
		ActionMessages errors = new ActionMessages();

		// 製造ヘッダの実績日時がNullの場合エラー
		ActionMessages headerErrors = rdirectionFinishListLogic
				.checkForResultDate(frm.getDirectionDivision(), frm
						.getDirectionNo());
		if (!headerErrors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, headerErrors);
			return mapping.findForward("error");
		}

		// 品目マスタ存在チェック
		errors = rdirectionFinishListLogic.checkForRegist(searchList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新用データ作成
		RdirectionDirectionHeaderList header = RdirectionUtil
				.setDirectionHeader(frm, request);
		// 仕込み実績数量の更新
		header.setResultQty(AecNumberUtils.convertBigDecimal(searchList.get(0)
				.getStrResultQty()));

		try {
			// 製造指図－プロシージャ更新処理
			rdirectionFinishListLogic.regist(frm, header, getLoginInfo(request)
					.getTantoCd());
		} catch (LogicExceptionEx e) {
			if ("errors.rdirection.stock.update".equals(e.getMessage())) {
				// 在庫更新に失敗
				saveError(request, e.getMessage());
				return mapping.getInputForward();
			} else {
				throw e;
			}
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

		RdirectionFinishListForm frm = (RdirectionFinishListForm) form;

		// 検索結果リストクリア
		frm
				.setSearchFinishList(new ArrayList<RdirectionDirectionFormulaList>());

		return init(mapping, form, request, response);
	}

	/**
	 * 数値フォーマットデータ(javascript用)の設定
	 * @param listForm 仕上げタブForm
	 * @param checker 数値チェックロジック
	 */
	private void setCheckDetail(final RdirectionFinishListForm listForm,
			final CheckDigitUtilsLogic checker) {
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			RdirectionConst.UNIT_DIV_HAIGO, null, null);
		listForm.setDecimalPoint(DirectionUtil.getBigDecimalString(checkDetail
				.getSmallnumLength())); // 少数点桁数
		listForm.setRoundDivision(DirectionUtil.getBigDecimalString(checkDetail
				.getRoundDivision())); // 端数区分
	}

	/* -------------------- setter -------------------- */
	/**
	 * 製造実績－フォーミュラを設定します。
	 * @param rdirectionFinishListLogic 製造実績－フォーミュラ
	 */
	public void setRdirectionFinishListLogic(
			final RdirectionFinishListLogic rdirectionFinishListLogic) {
		this.rdirectionFinishListLogic = rdirectionFinishListLogic;
	}

}
