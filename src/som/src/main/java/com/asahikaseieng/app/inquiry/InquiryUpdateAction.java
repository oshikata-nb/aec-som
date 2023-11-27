/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.inquirypreparationlist.InquiryPreparationList;
import com.asahikaseieng.dao.nonentity.inquiryupdatelist.InquiryUpdateList;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 棚卸更新処理詳細 Actionクラス.
 * @author tanaka
 */
public class InquiryUpdateAction extends AbstractAction {

	private InquiryUpdateLogic inquiryUpdateLogic;

	private NamesDetailDao namesDetailDao;

	/**
	 * コンストラクタ.
	 */
	public InquiryUpdateAction() {
		super();
	}

	/**
	 * 初期画面処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		InquiryUpdateForm frm = (InquiryUpdateForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_INQUIRY_UPDATE,
			Constants.TAB_ID_INQUIRY_UPDATE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		/* 棚卸区分セット */
		setCountDivisionCombobox(frm);

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 棚卸リスト取得
	 * @param frm 画面データ
	 */
	public void setCountDivisionCombobox(final InquiryUpdateForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<NamesListForComboboxes> list = inquiryUpdateLogic
				.getCountDivisionList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setSrhCountDivisionLabels(labels);
		frm.setSrhCountDivisionValues(values);
	}

	/**
	 * 行追加処理.addlist
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

		InquiryUpdateForm frm = (InquiryUpdateForm) form;

		/* 棚卸準備処理日チェック */
		if (AecDateUtils.getTimestampYmdFormat(frm.getStrSrhCountDate()) == null) {
			/* エラーメッセージ */
			saveErrorWithArgs(request, "errors.date",
				"inquiry.update.count.date");
			return mapping.findForward("success");
		}

		/* 棚卸更新日チェック */
		if (AecDateUtils.getTimestampYmdFormat(frm.getStrSrhUpdateDate()) == null) {
			/* エラーメッセージ */
			saveErrorWithArgs(request, "errors.date",
				"inquiry.update.update.date");
			return mapping.findForward("success");
		}

		/* 棚卸区分チェック */
		if (StringUtils.isEmpty(frm.getSrhCountDivision())) {
			/* エラーメッセージ */
			saveErrorWithArgs(request, "errors.required",
				"inquiry.update.count.division");
			return mapping.findForward("success");
		}

		Boolean isAddList = false;

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 棚卸区分重複チェック */
			if (frm.getSrhCountDivision().equals(
				frm.getSearchList().get(i).getNameCd())) {
				/* エラーメッセージ */
				saveErrorWithArgs(request,
					"errors.inquiry.update.same.division.row", i + 1);
				return mapping.findForward("success");
			}
		}

		InquiryPreparationList bean = new InquiryPreparationList();

		NamesDetail beanNames = namesDetailDao.getEntity("TANA", frm
				.getSrhCountDivision());

		if (beanNames != null) {
			bean.setNameCd(beanNames.getNameCd());
			bean.setName01(beanNames.getName01());
		}

		/* チェックを入れた行に挿入 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			if (frm.getSearchList().get(i).getChecked()) {
				if (beanNames != null) {
					frm.getSearchList().add(i, bean);
					frm.getSearchList().get(i).setChecked(false);
				}

				isAddList = true;
				break;
			}
		}

		/* チェックなしの場合は最終行へ追加 */
		if (!isAddList) {
			frm.getSearchList().add(bean);
			frm.getSearchList().get(frm.getSearchList().size() - 1).setChecked(
				false);
		}

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理.dellist
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

		InquiryUpdateForm frm = (InquiryUpdateForm) form;

		for (int i = frm.getSearchList().size() - 1; i >= 0; i--) {
			if (frm.getSearchList().get(i).getChecked()) {
				frm.getSearchList().remove(i);
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 更新処理.
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
			getLog().debug("update.");
		}

		InquiryUpdateForm frm = (InquiryUpdateForm) form;

		Timestamp countDate = AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhCountDate());
		Timestamp updateDate = AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhUpdateDate());

		if (frm.getSearchList().isEmpty()) {
			/* エラーメッセージ */
			saveError(request, "errors.inquiry.update.no.row");
			return mapping.findForward("success");
		}

		/* データ登録チェック */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			// InquiryInventoryCount beanInventory = inquiryUpdateLogic
			// .getInventoryCount(countDate, frm.getSearchList().get(i)
			// .getNameCd(), null, null, null, countDate);
			//
			// if (beanInventory == null) {
			// /* エラーメッセージ */
			// saveErrorWithArgs(request, "errors.nodata.inquiry.update.row",
			// i + 1);
			// return mapping.findForward("success");
			// } else {
			// if (beanInventory.getInventoryCount().intValue() <= 0) {
			// /* エラーメッセージ */
			// saveErrorWithArgs(request,
			// "errors.nodata.inquiry.update.row", i + 1);
			// return mapping.findForward("success");
			// }
			// }
			List<InquiryUpdateList> list = inquiryUpdateLogic.getList(
				countDate, frm.getSearchList().get(i).getNameCd(), frm
						.getSearchList().get(i).getLocationCd(), frm
						.getSearchList().get(i).getItemCd(), frm
						.getSearchList().get(i).getLotNo());

			if (list == null) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.nodata.inquiry.update.row",
					i + 1);
				return mapping.findForward("success");
			} else if (list.size() == 0) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.nodata.inquiry.update.row",
					i + 1);
				return mapping.findForward("success");
			} else {
				if (list.get(0).getCountUpdateDate() != null) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.inquiry.update.same.division.row", i + 1);
					return mapping.findForward("success");
				}
			}
		}

		frm.setSrhCountDate(countDate);
		frm.setSrhUpdateDate(updateDate);

		try {
			/* 追加処理を実行 */
			inquiryUpdateLogic.stockUpdate(frm, getLoginInfo(request)
					.getTantoCd());
		} catch (LogicExceptionEx e) {
			/* エラーメッセージ */
			saveError(request, "errors.inquiry.update.stock.update");
			return mapping.findForward("success");
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * クリア処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		/* アクションフォーム */
		InquiryUpdateForm frm = (InquiryUpdateForm) form;

		/* クリア */
		frm.clear();

		/* 棚卸区分セット */
		setCountDivisionCombobox(frm);

		/* 初期画面へ */
		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(final NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}

	/**
	 * inquiryUpdateLogicを設定します。
	 * @param inquiryUpdateLogic inquiryUpdateLogic
	 */
	public void setInquiryUpdateLogic(
			final InquiryUpdateLogic inquiryUpdateLogic) {
		this.inquiryUpdateLogic = inquiryUpdateLogic;
	}
}
