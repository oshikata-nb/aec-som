/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkList;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 原処方-その他 Actionクラス.
 * @author tosco
 */
public final class GrecipeOtherAction extends AbstractGrecipeAction {

	/** 原処方-その他 ロジッククラス */
	private GrecipeOtherLogic grecipeOtherLogic;

	/**
	 * コンストラクタ.
	 */
	public GrecipeOtherAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-その他
		return GrecipeConst.OTHER;
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

		GrecipeOtherForm frm = (GrecipeOtherForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_OTHER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		GrecipeRecipeRemarkList bean = grecipeOtherLogic.getEntity(frm
				.getRecipeId());

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

		GrecipeOtherForm frm = (GrecipeOtherForm) form;

		// 更新対象データの作成
		GrecipeRecipeRemarkList newBean = new GrecipeRecipeRemarkList();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		newBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		newBean.setInputorCd(getLoginInfo(request).getTantoCd()); // 登録者コード
		newBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者コード

		// 原処方その他登録処理
		grecipeCommonsLogic.insertRemark(newBean);

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

		GrecipeOtherForm frm = (GrecipeOtherForm) form;

		// 更新対象データの作成
		GrecipeRecipeRemarkList newBean = new GrecipeRecipeRemarkList();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		newBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者コード

		// 原処方その他更新処理
		grecipeOtherLogic.regist(newBean);

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

		GrecipeOtherForm frm = (GrecipeOtherForm) form;
		// 固有部分再設定
		frm.setGeneralRecipeRemark(null);
		frm.setDirtyFlg(null);

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */
	/**
	 * 原処方その他 ロジッククラスを設定します。
	 * @param grecipeOtherLogic 原処方その他 ロジッククラス
	 */
	public void setGrecipeOtherLogic(final GrecipeOtherLogic grecipeOtherLogic) {
		this.grecipeOtherLogic = grecipeOtherLogic;
	}

}
