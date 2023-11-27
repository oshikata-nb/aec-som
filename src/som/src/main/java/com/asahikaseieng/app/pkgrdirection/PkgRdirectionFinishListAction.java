/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

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
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 包装実績－仕上一覧画面 Actionクラス
 * @author tosco
 */
public class PkgRdirectionFinishListAction extends AbstractPkgRdirectionAction {

	/** 包装実績－仕上げタブ用ロジッククラス */
	private PkgRdirectionFinishListLogic pkgRdirectionFinishListLogic;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionFinishListAction() {
	}

	/**
	 * タブID（PkgRdirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return PkgRdirectionConst.FINISHLIST;
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
		PkgRdirectionFinishListForm listForm = (PkgRdirectionFinishListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_PKGRDIRECTION,
			Constants.TAB_ID_PKGRDIRECTION_FINISH);

		// 一覧検索処理
		BigDecimal directionDiv = new BigDecimal(listForm
				.getDirectionDivision());
		List<PkgRdirectionDirectionFormulaList> searchList = pkgRdirectionFinishListLogic
				.getSearchList(directionDiv, listForm.getDirectionNo());
		listForm.setSearchFinishList(searchList);

		// 数量フォーマット
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		for (PkgRdirectionDirectionFormulaList bean : searchList) {
			// 生産実績数量
			bean.setStrResultQty(checker.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getResultQty()));
			// 充填実績数量
			bean.setStrResultFillQty(checker.format(bean.getUnitDivision(),
				bean.getFillResultQty()));

			// 数値フォーマットデータの設定
			NumberChkDisitDetail checkDetail = checker.getCheckDigit(bean
					.getUnitDivision(), null, null);
			// 数値フォーマットデータ(javascript用)の設定
			setBeanCheckDetail(bean, checkDetail);
		}

		// 'HAIGO'の数値フォーマットデータの設定
		setHaigoCheckDetail(listForm, checker);

		// 工程順序コンボボックスの作成
		listForm.setStepNoCombo(pkgRdirectionCommonsLogic
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

		PkgRdirectionFinishListForm frm = (PkgRdirectionFinishListForm) form;
		List<PkgRdirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 工程コンボリストがない場合のチェック
		if (frm.getStepNoCombo().size() == 0) {
			ActionMessages errors = new ActionMessages();
			errors = MgrecipeUtil.addError(errors,
				"errors.pkgrdirection.no.procedure");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		PkgRdirectionDirectionFormulaList newBean = new PkgRdirectionDirectionFormulaList();
		newBean
				.setDirectionDivision(new BigDecimal(frm.getDirectionDivision())); // 指図区分
		newBean.setDirectionNo(frm.getDirectionNo()); // 指図番号

		// 要素がない場合
		if (searchList.size() == 0) {
			frm.getSearchFinishList().add(newBean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchList.size(); i++) {
				PkgRdirectionDirectionFormulaList rdirectionBean = searchList
						.get(i);
				// チェックボックスがチェックされていた場合
				if (rdirectionBean.isCheckFlg()) {
					// 新しい要素を追加
					searchList.add(i, newBean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == searchList.size() - 1) {
					// 新しい要素を追加
					frm.getSearchFinishList().add(newBean);
					break;
				}
			}
		}

		// チェックボックスクリア
		for (PkgRdirectionDirectionFormulaList directionBean : searchList) {
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

		PkgRdirectionFinishListForm frm = (PkgRdirectionFinishListForm) form;
		List<PkgRdirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 削除処理
		for (int i = searchList.size() - 1; i >= 0; i--) {
			PkgRdirectionDirectionFormulaList rdirectionBean = searchList
					.get(i);

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

		PkgRdirectionFinishListForm frm = (PkgRdirectionFinishListForm) form;
		ActionMessages errors = new ActionMessages();


		// 製造ヘッダの実績日時がNullの場合エラー
		ActionMessages headerErrors = pkgRdirectionFinishListLogic
				.checkForResultDate(frm.getDirectionDivision(), frm
						.getDirectionNo());
		if (!headerErrors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, headerErrors);
			return mapping.findForward("error");
		}

		// 品目マスタ存在チェック
		errors = pkgRdirectionFinishListLogic.checkForRegist(frm);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		try {
			// 包装実績－プロシージャ更新処理
			pkgRdirectionFinishListLogic.regist(frm, getLoginInfo(request)
					.getTantoCd());
		} catch (LogicExceptionEx e) {
			if ("errors.pkgrdirection.stock.update".equals(e.getMessage())) {
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

		PkgRdirectionFinishListForm frm = (PkgRdirectionFinishListForm) form;

		// 検索結果リストクリア
		frm
				.setSearchFinishList(new ArrayList<PkgRdirectionDirectionFormulaList>());

		return init(mapping, form, request, response);
	}

	/**
	 * 数値フォーマットデータ(javascript用)の設定
	 * @param bean 包装実績仕上げ一覧用フォーミュラ
	 * @param checkDetail 数値桁数チェックマスタ用データ格納クラス
	 */
	private void setBeanCheckDetail(
			final PkgRdirectionDirectionFormulaList bean,
			final NumberChkDisitDetail checkDetail) {
		bean.setDecimalPoint(DirectionUtil.getBigDecimalString(checkDetail
				.getSmallnumLength())); // 少数点桁数
		bean.setRoundDivision(DirectionUtil.getBigDecimalString(checkDetail
				.getRoundDivision())); // 端数区分
	}

	/**
	 * 'HAIGO'の数値フォーマットデータ(javascript用)の設定
	 * @param form 仕上げForm
	 * @param checker 数値項目用表示・チェックユーティリティ
	 */
	private void setHaigoCheckDetail(final PkgRdirectionFinishListForm form,
			final CheckDigitUtilsLogic checker) {
		// 数値フォーマットデータの設定
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			PkgRdirectionConst.UNIT_DIVISION_HAIGO, null, null);
		form.setDecimalPointHaigo(DirectionUtil.getBigDecimalString(checkDetail
				.getSmallnumLength())); // 少数点桁数
		form.setRoundDivisionHaigo(DirectionUtil
				.getBigDecimalString(checkDetail.getRoundDivision())); // 端数区分
	}

	/* -------------------- setter -------------------- */
	/**
	 * 包装実績－仕上げタブ用ロジッククラスを設定します。
	 * @param pkgRdirectionFinishListLogic 包装実績－仕上げタブ用ロジッククラス
	 */
	public void setPkgRdirectionFinishListLogic(
			final PkgRdirectionFinishListLogic pkgRdirectionFinishListLogic) {
		this.pkgRdirectionFinishListLogic = pkgRdirectionFinishListLogic;
	}

}
