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
import com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCount;
import com.asahikaseieng.dao.nonentity.inquirylocationcount.InquiryLocationCount;
import com.asahikaseieng.dao.nonentity.inquirypreparationlist.InquiryPreparationList;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 棚卸準備処理詳細 Actionクラス.
 * @author tanaka
 */
public class InquiryPreparationAction extends AbstractAction {

	private InquiryPreparationLogic inquiryPreparationLogic;

	private NamesDetailDao namesDetailDao;

	/**
	 * コンストラクタ.
	 */
	public InquiryPreparationAction() {
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

		InquiryPreparationForm frm = (InquiryPreparationForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_INQUIRY_PREPARATION,
			Constants.TAB_ID_INQUIRY_PREPARATION_DETAIL);

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
	public void setCountDivisionCombobox(final InquiryPreparationForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<NamesListForComboboxes> list = inquiryPreparationLogic
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

		InquiryPreparationForm frm = (InquiryPreparationForm) form;

		/* 棚卸準備処理日チェック */
		if (AecDateUtils.getTimestampYmdFormat(frm.getStrSrhCountDate()) == null) {
			/* エラーメッセージ */
			saveErrorWithArgs(request, "errors.date",
				"inquiry.preparation.count.date");
			return mapping.findForward("success");
		}

		/* 棚卸区分チェック */
		if (StringUtils.isEmpty(frm.getSrhCountDivision())) {
			/* エラーメッセージ */
			saveErrorWithArgs(request, "errors.required",
				"inquiry.preparation.count.division");
			return mapping.findForward("success");
		}

		Boolean isAddList = false;

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 棚卸区分重複チェック */
			if (frm.getSrhCountDivision().equals(
				frm.getSearchList().get(i).getNameCd())) {
				/* エラーメッセージ */
				saveErrorWithArgs(request,
					"errors.inquiry.preparation.same.division.row", i + 1);
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

		InquiryPreparationForm frm = (InquiryPreparationForm) form;

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

		InquiryPreparationForm frm = (InquiryPreparationForm) form;

		Timestamp countDate = AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhCountDate());

		/* 当日 < 準備日付 の例外 */
		if (countDate.after(AecDateUtils.getCurrentTimestamp())) {
			/* エラーメッセージ */
			saveError(request, "errors.inquiry.preparation.big.date");
			return mapping.findForward("success");
		}

		if (frm.getSearchList().isEmpty()) {
			/* エラーメッセージ */
			saveError(request, "errors.inquiry.preparation.no.row");
			return mapping.findForward("success");
		}

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* ロケーションに登録されている棚卸区分の数を取得 */
			InquiryLocationCount beanLocation = inquiryPreparationLogic
					.getLocationCount(frm.getSearchList().get(i).getNameCd());

			if (beanLocation == null) {
				/* エラーメッセージ */
				saveErrorWithArgs(
					request,
					"errors.nodata.inquiry.preparation.location.count.division.row",
					i + 1);
				return mapping.findForward("success");
			} else {
				if (beanLocation.getLocationCount().intValue() == 0) {
					/* エラーメッセージ */
					saveErrorWithArgs(
						request,
						"errors.inquiry.preparation.nodata.location.count.division.row",
						i + 1);
					return mapping.findForward("success");
				}
			}

			/* 既に登録されている棚卸準備データを検索 */
			List<InquiryPreparationList> list = inquiryPreparationLogic
					.getList(countDate, frm.getSearchList().get(i).getNameCd());

			if (list.isEmpty()) {
				/* エラーメッセージ */
				saveErrorWithArgs(request,
					"errors.nodata.inquiry.preparation.lot.stock.row", i + 1);
				return mapping.findForward("success");
			}

			/* 既に登録されている棚卸区分は重複登録出来ない */
			for (int j = 0; j < list.size(); j++) {
				InquiryInventoryCount beanInventory = inquiryPreparationLogic
						.getInventoryCount(list.get(j).getCountDate(), list
								.get(j).getCountDivision(), list.get(j)
								.getLocationCd(), list.get(j).getItemCd(), list
								.get(j).getLotNo(), list.get(j).getCountDate());

				if (beanInventory == null) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.inquiry.preparation.same.division.row", i + 1);
					return mapping.findForward("success");
				} else {
					if (0 < beanInventory.getInventoryCount().intValue()) {
						/* エラーメッセージ */
						saveErrorWithArgs(request,
							"errors.inquiry.preparation.same.division.row",
							i + 1);
						return mapping.findForward("success");
					}
				}
			}
		}

		frm.setSrhCountDate(countDate);

		/* 登録処理を実行 */
		inquiryPreparationLogic.regist(frm, getLoginInfo(request).getTantoCd());

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
		InquiryPreparationForm frm = (InquiryPreparationForm) form;

		/* クリア */
		frm.clear();

		/* 棚卸区分セット */
		setCountDivisionCombobox(frm);

		/* 初期画面へ */
		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inquiryPreparationLogicを設定します。
	 * @param inquiryPreparationLogic inquiryPreparationLogic
	 */
	public void setInquiryPreparationLogic(
			final InquiryPreparationLogic inquiryPreparationLogic) {
		this.inquiryPreparationLogic = inquiryPreparationLogic;
	}

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(final NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}
}
