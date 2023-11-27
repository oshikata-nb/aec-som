/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkList;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 基本処方-その他 Actionクラス.
 * @author tosco
 */
public final class MgrecipeOtherAction extends AbstractMgrecipeAction {

	/** 基本処方-その他 ロジッククラス */
	private MgrecipeOtherLogic mgrecipeOtherLogic;

	/** 基本処方検索 ロジッククラス */
	private MgrecipeCommonsLogic mgrecipeCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeOtherAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-その他
		return MgrecipeConst.OTHER;
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

		MgrecipeOtherForm frm = (MgrecipeOtherForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_OTHER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		RecipeRemarkList bean = mgrecipeOtherLogic
				.getByRecipeId(new BigDecimal(frm.getRecipeId()));

		if (bean == null) {
			// 変更フラグを新規(0)に設定
			frm.setInsertFlg("0");
		} else {
			// 変更フラグを更新(1)に設定
			frm.setInsertFlg("1");
			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理(新規)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert");
		}

		MgrecipeOtherForm frm = (MgrecipeOtherForm) form;

		// 更新対象データの作成
		RecipeRemarkList newBean = new RecipeRemarkList();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		newBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		newBean.setInputorCd(getLoginInfo(request).getTantoCd()); // 登録者コード
		newBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者コード

		// 基本処方その他登録処理
		mgrecipeCommonsLogic.insertRemark(newBean);

		// 変更フラグ1をセット
		// frm.setInsertFlg("1");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 登録処理(更新)
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

		MgrecipeOtherForm frm = (MgrecipeOtherForm) form;

		// 更新対象データの作成
		RecipeRemarkList newBean = new RecipeRemarkList();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		newBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者コード

		// 基本処方その他更新処理
		mgrecipeOtherLogic.regist(newBean);

		// 変更フラグ0をセット
		// frm.setInsertFlg("0");

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
		String strRecipeId = commonForm.getRecipeId();

		// 固有部分を取得
		MgrecipeOtherForm frm = (MgrecipeOtherForm) form;

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setRecipeId(strRecipeId); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		RecipeHeaderList header = mgrecipeCommonsLogic
				.getCommonHeader(strRecipeId);
		setCommonHeaderInfo(commonForm, header);

		// 固有部分再設定
		frm.setGeneralRecipeRemark(null);
		frm.setMasterRecipeRemark(null);
		frm.setDirtyFlg(null);

		// 検索処理
		RecipeRemarkList remark = mgrecipeOtherLogic
				.getByRecipeId(new BigDecimal(frm.getRecipeId()));

		frm.setGeneralRecipeRemark(remark.getGeneralRecipeRemark());
		frm.setMasterRecipeRemark(remark.getMasterRecipeRemark());
		frm.setInputDate(remark.getInputDate().toString());
		frm.setInputorCd(remark.getInputorCd());
		frm.setUpdateDate(remark.getUpdateDate().toString());
		frm.setUpdatorCd(remark.getUpdatorCd());
		frm.setInsertFlg("1");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 基本処方その他 ロジッククラスを設定します。
	 * @param mgrecipeOtherLogic 基本処方その他 ロジッククラス
	 */
	public void setMgrecipeOtherLogic(
			final MgrecipeOtherLogic mgrecipeOtherLogic) {
		this.mgrecipeOtherLogic = mgrecipeOtherLogic;
	}

	/**
	 * 基本処方検索 ロジッククラスを設定します。
	 * @param mgrecipeCommonsLogic 基本処方検索 ロジッククラス
	 */
	public void setMgrecipeCommonsLogic(
			final MgrecipeCommonsLogic mgrecipeCommonsLogic) {
		this.mgrecipeCommonsLogic = mgrecipeCommonsLogic;
	}
}
