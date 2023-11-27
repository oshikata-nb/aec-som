/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.mgrecipe.MgrecipeUtil;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造指図－仕上画面
 * @author tosco
 */
public class DirectionFinishListAction extends AbstractDirectionAction {

	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/** 製造指図－フォーミュラ */
	private DirectionFinishListLogic directionFinishListLogic;

	/**
	 * コンストラクタ
	 */
	public DirectionFinishListAction() {
	}

	/**
	 * タブID（DirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return DirectionConst.FINISHLIST;
	}

	/**
	 * 各子クラスの画面初期表示処理
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
			getLog().debug("init.");
		}

		DirectionFinishListForm listForm = (DirectionFinishListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_FINISH);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 一覧検索処理
		List<DirectionDirectionFormulaList> searchList = directionFinishListLogic
				.getSearchList(listForm.getDirectionNo());
		listForm.setSearchFinishList(searchList);

		// 発生予定数量のフォーマット
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		for (DirectionDirectionFormulaList bean : searchList) {
			bean.setStrQty(checker.format(UNIT_DIVISION_HAIGO, bean.getQty()));
		}

		// 数値フォーマットデータの設定
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			UNIT_DIVISION_HAIGO, null, null);
		// 少数点桁数
		listForm.setDecimalPoint(DirectionUtil.getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		listForm.setRoundDivision(DirectionUtil.getBigDecimalString(checkDetail
				.getRoundDivision()));

		// 工程順序コンボボックスの作成
		listForm
				.setStepNoCombo(directionCommonsLogic
						.createProcedureSetpNoCombobox(listForm
								.getDirectionNo(), false));

		return mapping.findForward("success");
	}

	/**
	 * 行追加処理.
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

		DirectionFinishListForm frm = (DirectionFinishListForm) form;
		List<DirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 工程データ未登録の場合エラー
		if (frm.getStepNoCombo().size() == 0) {
			ActionMessages errors = new ActionMessages();
			errors = MgrecipeUtil.addError(errors,
				"errors.direction.no.no.procedure");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		DirectionDirectionFormulaList bean = new DirectionDirectionFormulaList();
		bean.setDirectionDivision(BigDecimal.ONE); // 指図区分
		bean.setDirectionNo(frm.getDirectionNo()); // 指図番号

		// 要素がない場合
		if (searchList.size() == 0) {
			frm.getSearchFinishList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchList.size(); i++) {
				DirectionDirectionFormulaList directionBean = searchList.get(i);
				// チェックボックスがチェックされていた場合
				if (directionBean.isCheckFlg()) {
					// 新しい要素を追加
					searchList.add(i, bean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == searchList.size() - 1) {
					// 新しい要素を追加
					frm.getSearchFinishList().add(bean);
					break;
				}
			}
		}

		// チェックボックスクリア
		for (DirectionDirectionFormulaList directionBean : searchList) {
			directionBean.setCheckFlg(Boolean.FALSE);
		}

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理.
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

		DirectionFinishListForm frm = (DirectionFinishListForm) form;
		List<DirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();

		// 削除処理
		for (int i = searchList.size() - 1; i >= 0; i--) {
			DirectionDirectionFormulaList directionBean = searchList.get(i);

			if (!directionBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 行削除
			searchList.remove(i);
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
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("regist");
		}

		DirectionFinishListForm frm = (DirectionFinishListForm) form;
		List<DirectionDirectionFormulaList> searchList = frm
				.getSearchFinishList();
		ActionMessages errors = new ActionMessages();

		// 品目マスタ存在チェック
		errors = directionFinishListLogic.checkForRegist(searchList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新用データ作成
		DirectionDirectionHeaderList header = setDirectionHeader(frm, request);
		// 仕込み予定数量の更新
		header.setPlanedQty(AecNumberUtils.convertBigDecimal(searchList.get(0)
				.getStrQty()));

		boolean isIssued = false;
		if (DirectionConst.DIRECTION_STATUS_ISSUED.compareTo(header
				.getDirectionStatus()) == 0) {
			// 製造計画の更新チェック
			String errMsgKey = directionCommonsLogic.checkSeizoKeikaku(header
					.getDirectionNo());
			if (errMsgKey != null) {
				saveError(request, errMsgKey);
				return mapping.findForward("error");
			}
			isIssued = true;
		}
		try {
			// 製造指図－プロシージャ更新処理
			directionFinishListLogic.regist(frm, header, getLoginInfo(request)
					.getTantoCd());

			if (isIssued) {
				// 指図ステータス=指図書発行済みの場合
				// 計装IFテーブルデータ削除（製造計画、製造指示）
				directionCommonsLogic.deleteSeizo(header.getDirectionNo());
			}

		} catch (LogicExceptionEx e) {
			String errMsg = "errors.direction.stock.update";
			// 在庫更新に失敗
			if (errMsg.equals(e.getMessage())) {
				saveError(request, errMsg);
			} else {
				throw e;
			}
			return mapping.getInputForward();
		} catch (DirectionLogicException e) {
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

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reInit");
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
	public ActionForward reInit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reInit");
		}

		DirectionFinishListForm frm = (DirectionFinishListForm) form;

		// 検索結果リストクリア
		frm.setSearchFinishList(new ArrayList<DirectionDirectionFormulaList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図－フォーミュラを設定します。
	 * @param directionFinishListLogic 製造指図－フォーミュラ
	 */
	public void setDirectionFinishListLogic(
			final DirectionFinishListLogic directionFinishListLogic) {
		this.directionFinishListLogic = directionFinishListLogic;
	}

}
