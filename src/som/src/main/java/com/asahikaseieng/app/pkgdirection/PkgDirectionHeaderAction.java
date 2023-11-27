/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetail;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装指図－包装指図ヘッダー Actionクラス.
 * @author tosco
 */
public final class PkgDirectionHeaderAction extends AbstractPkgDirectionAction {

	private PkgDirectionHeaderLogic pkgDirectionHeaderLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionHeaderAction() {
		super();
	}

	/**
	 * タブID（PkgDirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {

		// タブID－ヘッダー情報タブ
		return PkgDirectionConst.HEADER;
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

		if (getLog().isDebugEnabled()) {
			getLog().debug("▽▽▽　initProcess START　▽▽▽");
		}
		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;
		frm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_HEADER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		ActionForward actionForward = null;
		if (StringUtils.isNotEmpty(frm.getDirectionNo())) {
			// 包装指図番号が設定されている場合は、包装指図ヘッダータブ画面へ
			actionForward = this.initUpd(mapping, form, request, response);
		} else {
			// 包装指図番号が設定されていない場合は、包装指図ヘッダー新規登録画面へ
			actionForward = this.initNew(mapping, form, request, response);
		}

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　initProcess END　△△△");
		}
		return actionForward;
	}

	/**
	 * 包装指図ヘッダータブ画面表示処理(一覧画面の包装指図番号リンク押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initUpd(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initUpd.");
		}

		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_HEADER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.setDirectionDetailList(null);
		frm.setInsertFlg(0);

		// 初期検索
		DirectionHeader bean = pkgDirectionHeaderLogic.getEntity(frm);
		frm.setNotes(bean.getNotes()); // 注釈
		frm.setRemark(bean.getRemark()); // 備考

		return mapping.findForward("success");
	}

	/**
	 * 包装指図ヘッダー新規登録画面表示処理(一覧画面の新規ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_HEADER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();
		frm.setInsertFlg(1);

		// 生産工場コンボボックス
		frm.setLineCombo(pkgDirectionCommonsLogic.createLineCombobox(false));

		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			PkgDirectionConst.UNIT_DIVISION_HAIGO, null, null);
		// 小数点桁数
		if (checkDetail.getSmallnumLength() != null) {
			frm.setDecimalPoint(checkDetail.getSmallnumLength().toString());
		}
		// 端数区分
		if (checkDetail.getRoundDivision() != null) {
			frm.setRoundDivision(checkDetail.getRoundDivision().toString());
		}

		return mapping.findForward("success");
	}

	/**
	 * 包装指図ヘッダー更新処理(包装指図ヘッダータブ画面の登録ボタン押下時)
	 * 
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
		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;

		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(frm
				.getDirectionNo(), frm.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			saveError(request, "errors.pkgdirection.results.exist");
			return mapping.getInputForward();
		}

		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 更新処理を実行
			pkgDirectionHeaderLogic.update(frm, tantoCd);

			// 包装計画削除
			pkgDirectionCommonsLogic.deleteHosoKeikaku(frm.getDirectionNo());

		} catch (PkgDirectionLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.update");

		return mapping.findForward("retrieve");
	}

	/**
	 * 包装指図ヘッダー登録処理(包装指図ヘッダー新規登録画面の登録ボタン押下時)
	 * 
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
		ActionMessage errMsg = null;
		ActionMessages errMsgs = null;
		ActionMessages errors = new ActionMessages();

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;

		// 包装ラインのチェック
		errMsg = pkgDirectionHeaderLogic.checkPackageLine(frm);
		if (errMsg != null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
		}

		// 品目コードのチェック
		errMsg = pkgDirectionHeaderLogic.checkItemCd(frm);
		if (errMsg != null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
		}

		// 基本処方コードのチェック
		errMsgs = pkgDirectionHeaderLogic.checkRecipe(frm);
		if (errMsgs != null) {
			errors.add(errMsgs);
		}

		// 生産予定数量のチェック
		errMsg = pkgDirectionHeaderLogic.checkPlanedQty(frm, request);
		if (errMsg != null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
		}

		// 製造指図番号のチェック
		errMsgs = pkgDirectionHeaderLogic.checkDirectionNo(frm);
		if (errMsgs != null) {
			errors.add(errMsgs);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("error");
		}

		try {
			String tantoCd = getLoginInfo(request).getTantoCd();
			frm.setHeaderUpdateDate(AecDateUtils.getCurrentTimestamp());

			// 登録処理を実行
			pkgDirectionHeaderLogic.insert(frm, tantoCd);

		} catch (PkgDirectionLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("retrieve");
	}

	/**
	 * 包装指図ヘッダー削除処理(包装指図ヘッダータブ画面の削除ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}
		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;

		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(frm
				.getDirectionNo(), frm.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			saveError(request, "errors.pkgdirection.results.exist");
			return mapping.getInputForward();
		}
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			/* 更新処理を実行 */
			pkgDirectionHeaderLogic.delete(frm, tantoCd);

			// 包装計画削除
			pkgDirectionCommonsLogic.deleteHosoKeikaku(frm.getDirectionNo());

		} catch (PkgDirectionLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			return mapping.getInputForward();
		}
		return mapping.findForward("back");
	}

	/**
	 * 戻る処理(タブ画面または新規登録画面の戻るボタン押下時)
	 * 
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
		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;
		frm.setInsertFlg(0);
		return mapping.findForward("back");
	}

	/**
	 * 行追加処理
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
		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;
		List<PkgDirectionDirectionDetailList> list = frm
				.getDirectionDetailList();
		if (list == null) {
			list = new ArrayList<PkgDirectionDirectionDetailList>();
		}
		PkgDirectionDirectionDetailList addBean = new PkgDirectionDirectionDetailList();
		addBean.setPkgDirectionNo(frm.getDirectionNo()); // 包装指図番号

		int idx = 0;
		for (idx = 0; idx < list.size(); idx++) {

			if (!list.get(idx).isCheckFlg()) {
				continue;
			}

			// 新しい要素を追加
			list.add(idx, addBean);
			break;
		}

		// 新規、チェックボックスにチェックなしの場合、最後尾に追加
		if (idx == list.size()) {
			addBean.setRowNo(new BigDecimal(list.size() + 1));
			list.add(addBean);
		} else {
			// リストに挿入した場合は、以降rowNoを振り直す
			for (int i = idx; i < list.size(); i++) {
				PkgDirectionDirectionDetailList bean = list.get(i);
				bean.setRowNo(new BigDecimal(i + 1));
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
		frm.setDirectionDetailList(list);

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理
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
		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;
		List<PkgDirectionDirectionDetailList> list = frm
				.getDirectionDetailList();

		if (list != null) {
			for (int i = list.size() - 1; i >= 0; i--) {

				// チェック無しの場合は削除対象外
				if (!list.get(i).isCheckFlg()) {
					continue;
				}
				list.remove(i);
			}

			// rowNoを振り直す
			for (int i = 0; i < list.size(); i++) {
				PkgDirectionDirectionDetailList bean = list.get(i);
				bean.setRowNo(new BigDecimal(i + 1));
			}
			frm.setDirectionDetailList(list);
		}
		return mapping.findForward("success");
	}

	/**
	 * 検索処理
	 * 
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward retrieve(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		PkgDirectionHeaderForm frm = (PkgDirectionHeaderForm) form;

		// クリアするので、渡された指図区分、包装指図番号を退避
		String directionDivision = frm.getDirectionDivision();
		String pkgDirectionNo = frm.getDirectionNo();

		// クリア
		frm.clear();
		frm.setDirtyFlg(null);
		frm.setDirectionDivision(directionDivision);
		frm.setDirectionNo(pkgDirectionNo);
		frm.setInsertFlg(0);

		// タブIDを設定
		frm.setTabId(this.getTabId());

		// 共通情報検索処理
		PkgDirectionDirectionHeaderDetail header = null;
		header = pkgDirectionCommonsLogic.getEntity(directionDivision,
			pkgDirectionNo);
		setCommonHeaderInfo(frm, header, request);

		// 詳細を検索
		DirectionHeader bean = pkgDirectionHeaderLogic.getEntity(frm);
		frm.setNotes(bean.getNotes()); // 注釈
		frm.setRemark(bean.getRemark()); // 備考

		return mapping.findForward("success");
	}

	/**
	 * 再描画処理
	 * 
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reDraw(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－包装指図ヘッダー ロジック設定.
	 * @param pkgDirectionHeaderLogic 包装指図－包装指図ヘッダー ロジック
	 */
	public void setPkgDirectionHeaderLogic(
			final PkgDirectionHeaderLogic pkgDirectionHeaderLogic) {
		this.pkgDirectionHeaderLogic = pkgDirectionHeaderLogic;
	}
}
