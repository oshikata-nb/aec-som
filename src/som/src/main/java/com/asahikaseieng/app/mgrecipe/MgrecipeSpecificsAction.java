/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 基本処方-詳細 Actionクラス.
 * @author tosco
 */
public final class MgrecipeSpecificsAction extends AbstractMgrecipeAction {

	/** 基本処方－詳細タブ ロジック */
	private MgrecipeSpecificsLogic mgrecipeSpecificsLogic;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeSpecificsAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-詳細
		return MgrecipeConst.SPECIFICS;
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

		MgrecipeSpecificsForm specificsForm = (MgrecipeSpecificsForm) form;

		/* 権限取得 */
		getControlAuthority(request, specificsForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_DETAIL);

		if (!specificsForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 原処方データの取得
		setSearchGrList(specificsForm);
		// 基本処方データの取得
		setSearchMrList(specificsForm);
		specificsForm.setDirtyFlg(null);

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

		MgrecipeSpecificsForm frm = (MgrecipeSpecificsForm) form;

		// ファイルのアップロード＋ラベルマスタに更新
		mgrecipeSpecificsLogic.regist(frm, getLoginInfo(request).getTantoCd());

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

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setRecipeId(strRecipeId); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		RecipeHeaderList header = mgrecipeCommonsLogic
				.getCommonHeader(strRecipeId);
		setCommonHeaderInfo(commonForm, header);

		MgrecipeSpecificsForm specificsForm = (MgrecipeSpecificsForm) form;

		// 固有部分再設定
		specificsForm.setSearchGrList(null);
		specificsForm.setSearchMrList(null);
		specificsForm.setDirtyFlg(null);

		// 原処方データの取得
		setSearchGrList(specificsForm);
		// 基本処方データの取得
		setSearchMrList(specificsForm);

		return mapping.findForward("success");
	}

	/**
	 * ダウンロード
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward filedownload(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("filedownload");
		}

		MgrecipeSpecificsForm frm = (MgrecipeSpecificsForm) form;
		String downlordDiv = frm.getDownloadDiv();

		List<MgrecipeLabelList> uplordList = new ArrayList<MgrecipeLabelList>(8);

		// 原処方リスト
		if (frm.getSearchGrList() == null) {
			throw new IllegalArgumentException("searchGrList == null");
		}
		uplordList.addAll(frm.getSearchGrList());

		// 基本処方リスト
		if (frm.getSearchMrList() == null) {
			throw new IllegalArgumentException("searchMrList == null");
		}
		uplordList.addAll(frm.getSearchMrList());

		// リスト分処理を繰り返す
		for (MgrecipeLabelList bean : uplordList) {
			if (!bean.getCommonCd().equals(downlordDiv)) {
				// 押されたボタン以外の場合
				continue;
			}

			// ダウンロードパス取得
			String path = mgrecipeSpecificsLogic.getDownloadPath(bean
					.getCommonCd());

			// クライアント側の文字エンコーディングのままのファイル名
			String fileName = path + bean.getLabelPath();
			File downloadFile = new File(fileName);

			if ((bean.getLabelPath() != null)
					&& !(bean.getLabelPath().equals(""))
					&& downloadFile.exists()) {
				FileDownloadInfo info = FileDownloadInfo.createDownloadInfo(
					fileName, bean.getLabelPath().substring(
						MgrecipeConst.START_FILE_NAME));

				/* Dispositionは常にattachmentを設定 */
				info.setDisposition(FileDownloadInfo.DISPOSITION_ATTACHMENT);

				/* sessionにくっつける */
				request.getSession(false).setAttribute(
					Constants.DOWNLOAD_FILE_KEY, info);

				/* ダウンロードフラグを立てる */
				frm.setDownloadFlg(true);
				break;
			} else {
				/* メッセージ */
				saveError(request, "errors.file");
			}

		}

		return mapping.findForward("success");
	}

	/**
	 * 詳細タブ表示用．原処方データの取得
	 * 
	 * @param specificsForm
	 */
	private void setSearchGrList(final MgrecipeSpecificsForm specificsForm) {
		// 原処方レシピインデックス存在フラグ初期設定
		specificsForm.setOriRecFlg("1");

		// 原処方レシピインデックス取得
		String originalRecipeId = specificsForm.getOriginalRecipeId();
		// 原処方データの取得（画面用フォーマット済み）
		if (originalRecipeId != null && !(originalRecipeId.equals(""))) {
			specificsForm.setOriRecFlg("0");
			List<MgrecipeLabelList> grList = mgrecipeSpecificsLogic
					.getGrSearch(originalRecipeId);
			specificsForm.setSearchGrList(grList);
		} else {
			// 原処方レシピインデックスがnullの場合、表示用の空データ作成
			List<MgrecipeLabelList> newList = new ArrayList<MgrecipeLabelList>(
					4);
			for (int i = 0; i < 4; i++) {
				MgrecipeLabelList newBean = new MgrecipeLabelList();
				newBean.setCommonCd(MgrecipeConst.COMMON_CD_GR
						+ Integer.toString(i + 1));
				newList.add(i, newBean);
			}
			specificsForm.setSearchGrList(newList);
		}
	}

	/**
	 * 詳細タブ表示用．基本処方データ取得
	 * 
	 * @param specificsForm
	 */
	private void setSearchMrList(final MgrecipeSpecificsForm specificsForm) {
		// 基本処方データの取得（画面用フォーマット済み）
		List<MgrecipeLabelList> mrlist = mgrecipeSpecificsLogic
				.getMrSearch(specificsForm.getRecipeId());
		specificsForm.setSearchMrList(mrlist);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 基本処方－詳細タブ ロジックを設定します。
	 * @param mgrecipeSpecificsLogic 基本処方－詳細タブ ロジック
	 */
	public void setMgrecipeSpecificsLogic(
			final MgrecipeSpecificsLogic mgrecipeSpecificsLogic) {
		this.mgrecipeSpecificsLogic = mgrecipeSpecificsLogic;
	}

}
