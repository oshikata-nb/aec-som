/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.exception.LargeAmountDataRuntimeException;
import com.asahikaseieng.pager.Pager;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 検索画面に必要な汎用処理を実装したActionクラス.
 * @author jbd
 */
public abstract class AbstractSearchAction extends AbstractAction {

	/**
	 * {@inheritDoc}
	 */
	public final ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		AbstractSearchForm frm = (AbstractSearchForm) form;

		ActionForward af = initImpl(mapping, frm, request, response);

		Pager pager = frm.getPager();
		pager.reset();

		if (af != null) {
			return af;
		}
		return searchProcess(mapping, request, frm);
	}

	/**
	 * メニュー等から入ってきた（初期処理）処理時の 画面初期化等の固有の処理を記述する. <br>
	 * なにもしない/デフォルトの遷移先に遷移時はnullを返す事.
	 * @param mapping ActionMapping
	 * @param form AbstractSearchForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward 個別にハンドリングする必要が無い場合はnullを返す
	 * @throws Exception システム例外
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * 検索ボタンが押された時の処理用<br>
	 * ページ番号はURL変数"page"より取得すること<br>
	 * "page"が存在しない場合はpage=1として処理すること.
	 * @param mapping ActionMapping
	 * @param form AbstractSearchForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public final ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		AbstractSearchForm frm = (AbstractSearchForm) form;

		Pager pager = frm.getPager();
		pager.reset();

		ActionForward af = searchProcess(mapping, request, frm);

		if (request.getAttribute(Globals.ERROR_KEY) == null) {
			/* エラーが無ければ、検索完了メッセージ */
			if (request.getAttribute(Globals.MESSAGE_KEY) == null) {
				saveMessage(request, "message.complete.search");
			}
		}

		return af;
	}

	/**
	 * 再検索処理（一覧再描画する時の処理用）<br>
	 * ページ番号はpagerが保持する"page"より取得すること<br>
	 * "page"が存在しない場合はpage=1として処理すること.
	 * @param mapping ActionMapping
	 * @param form AbstractSearchForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public ActionForward reSearch(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return searchProcess(mapping, request, (AbstractSearchForm) form);
	}

	/**
	 * 再描画処理（一覧再描画する時の処理用）<br>
	 * ページ番号はpagerが保持する"page"より取得すること<br>
	 * "page"が存在しない場合はpage=1として処理すること.
	 * @param mapping ActionMapping
	 * @param form AbstractSearchForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public final ActionForward reFresh(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return searchProcess(mapping, request, (AbstractSearchForm) form);
	}

	/**
	 * ページ遷移処理.
	 * @param mapping ActionMapping
	 * @param form AbstractSearchForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public final ActionForward changePage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		AbstractSearchForm frm = (AbstractSearchForm) form;

		Pager pager = frm.getPager();
		pager.updateOffset(request);

		return searchProcess(mapping, request, frm);
	}

	/**
	 * searchImplを呼ぶ共通メソッド(共通の例外処理を記述).
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @param form AbstractSearchForm
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	protected ActionForward searchProcess(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm frm)
			throws Exception {

		try {

			return searchImpl(mapping, request, frm);

		} catch (LargeAmountDataRuntimeException e) {
			/* 制限データ数超え */
			saveError(request, "errors.large.amount.data", frm
					.getPagerCondition().getThreshold());
		}
		return mapping.getInputForward();
	}

	/**
	 * 検索処理の実装<br>
	 * searchから呼ばれる検索処理の実装メソッド.
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @param form AbstractSearchForm
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	protected abstract ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception;
}
