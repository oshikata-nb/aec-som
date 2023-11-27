/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail.ItemTechLabelDetail;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 技術情報 Actionクラス.
 * @author t0011036
 */
public final class ItemTechAction extends AbstractAction {

	private ItemTechLogic itemTechLogic;

	/** ファイル名の年月日時分秒部分の桁数 */
	// private static final int START_FILE_NAME = 14;
	/** リンク情報ラベル種類 */
	public static final String LINK_CD = "ITEM_LINK";

	/** 技術情報ラベル種類 */
	public static final String TECH_CD = "ITEM_TECH";

	/**
	 * コンストラクタ.
	 */
	public ItemTechAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		ItemTechForm frm = (ItemTechForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_TECH);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemTechLogic.getHeaderEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		/* リンク情報取得 */
		ItemTechLabelDetail beanLink = itemTechLogic.getLabelEntity(frm
				.getHeadItemCd(), LINK_CD);

		if (beanLink != null) {
			/* ファイル名を表示用に編集 */
			String lblPath = beanLink.getLabelPath();

			beanLink.setDispLinkLabelPath(itemTechLogic
					.getOriginalLabelPath(lblPath));
			// if (!StringUtils.isEmpty(lblPath)) {
			// /* 年月日時分秒を取除く */
			// lblPath = lblPath.substring(START_FILE_NAME);
			// beanLink.setDispLinkLabelPath(lblPath);
			// }

			/* コピーしきれなかった分は手で */
			frm.setLinkLabelCd(beanLink.getLabelCd());
			frm.setLinkLabelPath(beanLink.getLabelPath());
			frm.setDispLinkLabelPath(beanLink.getDispLinkLabelPath());
			frm.setLinkCommonCd(beanLink.getCommonCd());
			frm.setLinkCommonName(beanLink.getCommonName());
			frm.setLinkCommonValue(beanLink.getCommonValue());
			frm.setLinkUpdateDate(beanLink.getUpdateDate());
		}

		/* 技術情報取得 */
		ItemTechLabelDetail beanTech = itemTechLogic.getLabelEntity(frm
				.getHeadItemCd(), TECH_CD);

		if (beanTech != null) {
			/* ファイル名を表示用に編集 */
			String lblPath = beanTech.getLabelPath();

			beanTech.setDispTechLabelPath(itemTechLogic
					.getOriginalLabelPath(lblPath));
			// if (!StringUtils.isEmpty(lblPath)) {
			// /* 年月日時分秒を取除く */
			// lblPath = lblPath.substring(START_FILE_NAME);
			// beanTech.setDispTechLabelPath(lblPath);
			// }

			frm.setTechLabelCd(beanTech.getLabelCd());
			frm.setTechLabelPath(beanTech.getLabelPath());
			frm.setDispTechLabelPath(beanTech.getDispTechLabelPath());
			frm.setTechCommonCd(beanTech.getCommonCd());
			frm.setTechCommonName(beanTech.getCommonName());
			frm.setTechCommonValue(beanTech.getCommonValue());
			frm.setTechUpdateDate(beanTech.getUpdateDate());
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
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		ItemTechForm frm = (ItemTechForm) form;

		if ((frm.getLinkUploadFile() == null || StringUtils.isEmpty(frm
				.getLinkUploadFile().getFileName()))
				&& (frm.getTechUploadFile() == null || StringUtils.isEmpty(frm
						.getTechUploadFile().getFileName()))) {
			/* エラーメッセージ */
			saveError(request, "errors.file");
			return mapping.findForward("success");
		}

		/* 登録処理を実行 */
		itemTechLogic.regist(frm, getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

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

		ItemTechForm frm = (ItemTechForm) form;

		if (frm.getDownloadDiv().equals(LINK_CD)) {
			/* ダウンロードパス取得 */
			String path = itemTechLogic.getDownloadPath(frm.getDownloadDiv());

			/* クライアント側の文字エンコーディングのままのファイル名 */
			String fileName = path + frm.getLinkLabelPath();
			File downloadFile = new File(fileName);

			if (!StringUtils.isEmpty(frm.getLinkLabelPath())
					&& downloadFile.exists()) {
				FileDownloadInfo info = FileDownloadInfo.createDownloadInfo(
					fileName, frm.getLinkLabelPath().substring(
						MgrecipeConst.START_FILE_NAME));

				/* Dispositionは常にattachmentを設定 */
				info.setDisposition(FileDownloadInfo.DISPOSITION_ATTACHMENT);

				/* sessionにくっつける */
				request.getSession(false).setAttribute(
					Constants.DOWNLOAD_FILE_KEY, info);

				/* ダウンロードフラグを立てる */
				frm.setDownloadFlg(true);
			} else {
				/* メッセージ */
				saveError(request, "errors.file");
			}
		}

		if (frm.getDownloadDiv().equals(TECH_CD)) {
			/* ダウンロードパス取得 */
			String path = itemTechLogic.getDownloadPath(frm.getDownloadDiv());

			/* クライアント側の文字エンコーディングのままのファイル名 */
			String fileName = path + frm.getTechLabelPath();
			File downloadFile = new File(fileName);

			if (!StringUtils.isEmpty(frm.getTechLabelPath())
					&& downloadFile.exists()) {
				FileDownloadInfo info = FileDownloadInfo.createDownloadInfo(
					fileName, frm.getTechLabelPath().substring(
						MgrecipeConst.START_FILE_NAME));

				/* Dispositionは常にattachmentを設定 */
				info.setDisposition(FileDownloadInfo.DISPOSITION_ATTACHMENT);

				/* sessionにくっつける */
				request.getSession(false).setAttribute(
					Constants.DOWNLOAD_FILE_KEY, info);

				/* ダウンロードフラグを立てる */
				frm.setDownloadFlg(true);
			} else {
				/* メッセージ */
				saveError(request, "errors.file");
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 戻る処理.
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
	 * itemTechLogicを設定します。
	 * @param itemTechLogic itemTechLogic
	 */
	public void setItemTechLogic(final ItemTechLogic itemTechLogic) {
		this.itemTechLogic = itemTechLogic;
	}
}
