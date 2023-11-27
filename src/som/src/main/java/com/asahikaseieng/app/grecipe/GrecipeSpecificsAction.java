/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 原処方-詳細 Actionクラス.
 * @author tosco
 */
public final class GrecipeSpecificsAction extends AbstractGrecipeAction {

	/** 原処方－詳細タブ ロジック */
	private GrecipeSpecificsLogic grecipeSpecificsLogic;

	/**
	 * コンストラクタ.
	 */
	public GrecipeSpecificsAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-詳細
		return GrecipeConst.SPECIFICS;
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

		GrecipeSpecificsForm specificsForm = (GrecipeSpecificsForm) form;

		/* 権限取得 */
		getControlAuthority(request, specificsForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_DETAIL);

		if (!specificsForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 原処方データの取得
		setSearchGrList(specificsForm);
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

		GrecipeSpecificsForm frm = (GrecipeSpecificsForm) form;

		// ファイルのアップロード＋ラベルマスタに更新
		grecipeSpecificsLogic.regist(frm, getLoginInfo(request).getTantoCd());

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
		AbstractGrecipeForm commonForm = (AbstractGrecipeForm) form;
		String strRecipeId = commonForm.getRecipeId();

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setRecipeId(strRecipeId); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		GrecipeRecipeHeaderList header = grecipeCommonsLogic
				.getCommonHeader(strRecipeId);
		setCommonHeaderInfo(commonForm, header);

		GrecipeSpecificsForm specificsForm = (GrecipeSpecificsForm) form;

		// 固有部分再設定
		specificsForm.setSearchGrList(null);
		specificsForm.setDirtyFlg(null);

		// 原処方データの取得
		setSearchGrList(specificsForm);

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

		GrecipeSpecificsForm frm = (GrecipeSpecificsForm) form;
		String downlordDiv = frm.getDownloadDiv();

		// リスト分処理を繰り返す
		for (GrecipeLabelList bean : frm.getSearchGrList()) {
			if (!bean.getCommonCd().equals(downlordDiv)) {
				// 押されたボタン以外の場合
				continue;
			}

			// ダウンロードパス取得
			String path = grecipeSpecificsLogic.getDownloadPath(bean
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
	 * @param specificsForm 詳細タブForm
	 */
	private void setSearchGrList(final GrecipeSpecificsForm specificsForm) {

		// 詳細タブ一覧検索
		List<GrecipeLabelList> grList = grecipeSpecificsLogic
				.getGrSearch(specificsForm.getRecipeId());
		specificsForm.setSearchGrList(grList);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 原処方－詳細タブ ロジックを設定します。
	 * @param grecipeSpecificsLogic 原処方－詳細タブ ロジック
	 */
	public void setGrecipeSpecificsLogic(
			final GrecipeSpecificsLogic grecipeSpecificsLogic) {
		this.grecipeSpecificsLogic = grecipeSpecificsLogic;
	}

}
