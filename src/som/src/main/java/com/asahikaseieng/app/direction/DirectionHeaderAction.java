/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図ヘッダー画面
 * @author tosco
 */
public class DirectionHeaderAction extends AbstractDirectionAction {
	/** 製造指図ヘッダロジッククラス */
	private DirectionHeaderLogic directionHeaderLogic;

	/**
	 * コンストラクタ
	 */
	public DirectionHeaderAction() {
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return DirectionConst.HEADER;
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
		DirectionHeaderForm headerForm = (DirectionHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_HEADER);

		if (!headerForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// ホールドタンク名称取得
		headerForm.setHoldTankName(directionHeaderLogic
				.getHoldTankName(headerForm.getHoldTankNo()));

		return mapping.findForward("success");
	}

	/**
	 * 共通ヘッダー情報を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param header 共通ヘッダー情報
	 * @param request request
	 * @return AbstractMgrecipeForm
	 */
	@Override
	protected AbstractDirectionForm setCommonHeaderInfo(
			final AbstractDirectionForm form,
			final DirectionDirectionHeaderList header,
			final HttpServletRequest request) {
		super.setCommonHeaderInfo(form, header, request);

		DirectionHeaderForm headerForm = (DirectionHeaderForm) form;
		// 入力用のホールドタンクへ移送
		headerForm.setInputHoldTankNo(headerForm.getHoldTankNo());
		headerForm.setLotNo(header.getLotNo());
		headerForm.setRemark(header.getRemark());
		headerForm.setNotes(header.getNotes());

		return headerForm;
	}

	/**
	 * 登録処理
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
		DirectionHeaderForm headerForm = (DirectionHeaderForm) form;
		// マスタ存在チェック
		if (isCheckMaster(headerForm, request)) {
			boolean isIssued = false;
			if (DirectionConst.DIRECTION_STATUS_ISSUED.compareTo(headerForm
					.getHeaderBean().getDirectionStatus()) == 0) {
				// 製造計画の更新チェック
				String errMsgKey = directionCommonsLogic
						.checkSeizoKeikaku(headerForm.getHeaderBean()
								.getDirectionNo());
				if (errMsgKey != null) {
					saveError(request, errMsgKey);
					return mapping.getInputForward();
				}
				isIssued = true;
			}
			// マスタ存在チェックOK時
			// 更新用データ作成
			DirectionDirectionHeaderList header = setDirectionHeader(
				headerForm, request);
			try {
				// 処方ヘッダ更新
				directionCommonsLogic.update(header);

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
						String buf = getMessageResource(request,
							replacements[i]);
						if (StringUtils.isNotEmpty(buf)) {
							replacements[i] = buf;
						}
					}
				}
				addError(request, e.getKey(), (Object[]) replacements);
				return mapping.getInputForward();
			}
			// 処理成功メッセージ
			saveMessage(request, "message.complete.update");
			headerForm.setDirtyFlg(null);
			// 再検索処理
			init(mapping, form, request, response);
		}
		return mapping.findForward("success");
	}

	/**
	 * 削除処理
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
		DirectionHeaderForm headerForm = (DirectionHeaderForm) form;
		boolean isIssued = false;
		if (DirectionConst.DIRECTION_STATUS_ISSUED.compareTo(headerForm
				.getHeaderBean().getDirectionStatus()) == 0) {
			// 製造計画の更新チェック
			String errMsgKey = directionCommonsLogic
					.checkSeizoKeikaku(headerForm.getHeaderBean()
							.getDirectionNo());
			if (errMsgKey != null) {
				saveError(request, errMsgKey);
				return mapping.getInputForward();
			}
			isIssued = true;
		}
		// 削除処理
		try {
			// 指図ヘッダと関連テーブル削除
			directionHeaderLogic.delete(headerForm.getHeaderBean());

			if (isIssued) {
				// 指図ステータス=指図書発行済みの場合
				// 計装IFテーブルデータ削除（製造計画、製造指示）
				directionCommonsLogic.deleteSeizo(headerForm.getHeaderBean()
						.getDirectionNo());
			}
			// 処理成功メッセージ
			saveMessage(request, "message.complete.delete");
		} catch (NoDataException nde) {
			// 削除失敗メッセージ
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
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
		return mapping.findForward("back");
	}

	/**
	 * 各タンクマスタ存在チェック
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	private boolean isCheckMaster(final DirectionHeaderForm form,
			final HttpServletRequest request) {
		boolean res = true;
		// ホールドタンク存在チェック
		if (StringUtils.isNotEmpty(form.getInputHoldTankNo())) {
			try {
				String holdTankName = directionHeaderLogic.checkExistsHoldTank(
					form.getCompoundTankNo(), form.getInputHoldTankNo());
				form.setHoldTankName(holdTankName);
			} catch (NoDataException e) {
				// 前後設備紐付けマスタに存在しなかった場合
				addError(request, "errors.nodata.master", getMessageResource(
					request, "item.direction.hold.tank.no"));
				form.setHoldTankName(null);
				res = false;
			}
		}
		return res;
	}

	/**
	 * 更新用データを作成する
	 * @param commonForm ActionForm
	 * @param request HttpServletRequest
	 * @return DirectionDirectionHeaderList
	 */
	@Override
	protected DirectionDirectionHeaderList setDirectionHeader(
			final AbstractDirectionForm commonForm,
			final HttpServletRequest request) {
		DirectionDirectionHeaderList header = super.setDirectionHeader(
			commonForm, request);
		DirectionHeaderForm headerForm = (DirectionHeaderForm) commonForm;

		// 指図ステータスはlogic側で設定する（指図書発行等の今のステータスが判らなくなるため)
		header.setHoldTankNo(headerForm.getInputHoldTankNo());
		header.setLotNo(headerForm.getLotNo());
		header.setRemark(headerForm.getRemark());
		header.setNotes(headerForm.getNotes());
		return header;
	}

	// setter-----------------------------------------------------------------------------
	/**
	 * 製造指図ヘッダロジッククラスを設定します。
	 * @param directionHeaderLogic 製造指図ヘッダロジッククラス
	 */
	public void setDirectionHeaderLogic(
			final DirectionHeaderLogic directionHeaderLogic) {
		this.directionHeaderLogic = directionHeaderLogic;
	}
}
