/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetail;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.ComponentInformationQueueList;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 品目成分 Actionクラス.
 * @author t0011036
 */
public final class ItemComponentAction extends AbstractAction {

	private ItemComponentLogic itemComponentLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemComponentAction() {
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

		ItemComponentForm frm = (ItemComponentForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_COMPONENT);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemComponentLogic.getHeaderEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		/* 初期検索 */
		List<ComponentInformationQueueList> list = itemComponentLogic.getList(
			frm.getItemCd(), frm.getVersion());

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			list.get(i).setStrCalcValue(
				checker.format(list.get(i).getUnitDivision(), list.get(i)
						.getCalcValue()));
		}

		/* 明細取得 */
		frm.setSearchComponentList(list);

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

		ItemComponentForm frm = (ItemComponentForm) form;

		for (int i = 0; i < frm.getSearchComponentList().size(); i++) {
			if (!StringUtils.isEmpty(frm.getSearchComponentList().get(i)
					.getComponentCd())) {
				/* 成分コードチェック */
				ComponentDetail beanComponent = itemComponentLogic
						.getComponentEntity(frm.getSearchComponentList().get(i)
								.getComponentCd());

				if (beanComponent == null) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.nodata.item.component.cd.row", i + 1);
					return mapping.findForward("success");
				}
			}
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchComponentList().size(); i++) {
			/* 演算結果を丸める */
			frm.getSearchComponentList().get(i).setCalcValue(
				check.round(frm.getSearchComponentList().get(i)
						.getUnitDivision(), null, null, AecNumberUtils
						.convertBigDecimal(frm.getSearchComponentList().get(i)
								.getStrCalcValue())));
		}

		/* 登録処理を実行 */
		itemComponentLogic.regist(frm, getLoginInfo(request).getTantoCd());

		frm.setDirtyFlg(null);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

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

	/**
	 * 成分追加処理.
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

		ItemComponentForm frm = (ItemComponentForm) form;

		Boolean isAddList = false;

		for (int i = 0; i < frm.getSearchComponentList().size(); i++) {
			if (frm.getSearchComponentList().get(i).getChecked()) {
				ComponentInformationQueueList bean = new ComponentInformationQueueList();
				frm.getSearchComponentList().add(i, bean);
				frm.getSearchComponentList().get(i).setChecked(false);
				frm.getSearchComponentList().get(i).setUnitDivision("SONOTA3");
				isAddList = true;
				break;
			}
		}

		/* 最終行へ追加 */
		if (!isAddList) {
			ComponentInformationQueueList bean = new ComponentInformationQueueList();
			frm.getSearchComponentList().add(bean);
			frm.getSearchComponentList().get(
				frm.getSearchComponentList().size() - 1).setChecked(false);
			frm.getSearchComponentList().get(
				frm.getSearchComponentList().size() - 1).setUnitDivision(
				"SONOTA3");
		}

		return mapping.findForward("success");
	}

	/**
	 * 成分削除処理.
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

		ItemComponentForm frm = (ItemComponentForm) form;

		for (int i = frm.getSearchComponentList().size() - 1; i >= 0; i--) {
			if (frm.getSearchComponentList().get(i).getChecked()) {
				frm.getSearchComponentList().remove(i);
			}
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemComponentLogicを設定します。
	 * @param itemComponentLogic itemComponentLogic
	 */
	public void setItemComponentLogic(
			final ItemComponentLogic itemComponentLogic) {
		this.itemComponentLogic = itemComponentLogic;
	}
}
