/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionLotInventorySearchList;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 包装実績－ロット検索画面 Actionクラス.
 * @author tosco
 */
public final class PkgRdirectionLotInventorySearchAction extends
		AbstractSearchAction {

	/** 包装実績－ロット検索画面ロジック */
	private PkgRdirectionLotInventorySearchLogic pkgRdirectionLotInventorySearchLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionLotInventorySearchAction() {
		super();
	}

	/**
	 * 初期処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initImpl");
		}

		/* 初期検索有り */
		return null;
	}

	/**
	 * ロット検索処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchImpl");
		}

		PkgRdirectionLotInventorySearchForm frm = (PkgRdirectionLotInventorySearchForm) form;

		// 品目名称を復号化
		frm.setSrhItemName(URLDecoder.decode(frm.getSrhItemName(), "UTF-8"));

		/* 検索に失敗しても明細が消されるように、ここでクリア */
		frm.setSearchList(new ArrayList<PkgRdirectionLotInventorySearchList>());

		/* 明細設定 */
		pkgRdirectionLotInventorySearchLogic.setList(frm);

		List<PkgRdirectionLotInventorySearchList> list = frm.getSearchList();
		if (list != null && (!list.isEmpty())) {
			PkgRdirectionLotInventorySearchList bean = list.get(0);
			frm.setSrhItemName(bean.getItemName());
		}
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			PkgRdirectionConst.UNIT_DIVISION_HAIGO, null, null);

		// 少数点桁数
		frm.setDecimalPoint(checkDetail.getSmallnumLength().toString());
		// 端数区分
		frm.setRoundDivision(checkDetail.getRoundDivision().toString());

		return mapping.findForward("success");
	}

	/**
	 * 登録処理.
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
		PkgRdirectionLotInventorySearchForm frm = (PkgRdirectionLotInventorySearchForm) form;

		// 製造ヘッダの実績日時がNullの場合エラー
		ActionMessages headerErrors = pkgRdirectionLotInventorySearchLogic
				.checkForResultDate(frm.getDirectionDivision(), frm
						.getDirectionNo());
		if (!headerErrors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, headerErrors);
			return mapping.findForward("error");
		}

		try {
			// 製造指図フォーミュラ登録処理
			pkgRdirectionLotInventorySearchLogic.regist(frm, getLoginInfo(
				request).getTantoCd());
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
		saveMessage(request, "message.complete.update");
		return mapping.findForward("back");
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
	 * ロット検索画面ロジック.
	 * @param pkgRdirectionLotInventorySearchLogic ロット検索画面ロジック
	 */
	public void setPkgRdirectionLotInventorySearchLogic(
			final PkgRdirectionLotInventorySearchLogic pkgRdirectionLotInventorySearchLogic) {
		this.pkgRdirectionLotInventorySearchLogic = pkgRdirectionLotInventorySearchLogic;
	}
}
