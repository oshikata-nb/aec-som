/*
 * Created on 2009/05/28
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.directionstatuschange;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangeList;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangePagerCondition;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeListConditionForReport;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造指図ステータス変更 Actionクラス.
 * @author tosco
 * 
 */
public final class DirectionStatusChangeListAction extends AbstractSearchAction {

	/** 製造指図ステータス変更のロジッククラス */
	private DirectionStatusChangeListLogic directionStatusChangeListLogic;

	private DirectionStatusChangeListExcelDecorator directionStatusChangeListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public DirectionStatusChangeListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
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
			getLog().debug("init.");
		}

		// フォーム取得
		DirectionStatusChangeListForm frm = (DirectionStatusChangeListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_DIRECTION_STATUS_CHANGE,
			Constants.TAB_ID_DIRECTION_STATUS_CHANGE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 生産工場コンボボックスを作成し、セット
		frm.setLineCombo(directionStatusChangeListLogic
				.createLineCombobox(true));

		// 初期検索無し
		return mapping.findForward("success");
	}

	/**
	 * 検索処理(検索ボタン押下時)
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
			getLog().debug("search.");
		}

		// フォーム取得
		DirectionStatusChangeListForm frm = (DirectionStatusChangeListForm) form;

		// 検索結果のクリア
		frm.setSearchList(new ArrayList<DirectionStatusChangeList>());

		// 検索条件を取得
		DirectionStatusChangePagerCondition condition = (DirectionStatusChangePagerCondition) frm
				.getPager().getPagerCondition();
		// 検索条件をセット
		condition.setSrhDirectionNo(frm.getSrhDirectionNo()); // 指図番号
		condition.setSrhProductionLine(frm.getSrhProductionLine()); // 生産ライン
		condition.setSrhItemCd(frm.getSrhItemCd()); // 品目コード
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1()); // 他社コード１

		// 指図ステータス
		condition.setSrhDirectionStatus(frm.getSrhDirectionStatus());

		/* 帳票(Excel)用に検索条件を保持 */
		DirectionStatusChangeListConditionForReport reportCondition = new DirectionStatusChangeListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(directionStatusChangeListLogic
				.getSearchList(condition));
		return mapping.findForward("success");
	}

	/**
	 * 帳票処理(検索画面の帳票(Excel)ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		DirectionStatusChangeListForm frm = (DirectionStatusChangeListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = directionStatusChangeListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * 
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

		// フォームの取得
		DirectionStatusChangeListForm frm = (DirectionStatusChangeListForm) form;

		// クリア
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * ステータス変更
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward change(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("change.");
		}
		DirectionStatusChangeListForm listForm = (DirectionStatusChangeListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		List<DirectionStatusChangeList> searchList = listForm.getSearchList();
		int rowNo = 0;
		for (DirectionStatusChangeList bean : searchList) {
			rowNo++;
			if (!bean.isSelectedCheck()) {
				// チェック無
				continue;
			}

			// 計装ＩＦの製造計画に済みフラグを立てる
			try {
				directionStatusChangeListLogic
						.changeSumiflg(bean, tantoCd, "1");
			} catch (OptimisticLockRuntimeException e) {
				saveError(request,
					"errors.direction.update.if.table.direction.no", listForm
							.getSearchList().get(rowNo - 1).getDirectionNo());
				continue;
			}
			try {
				// ステータスを変更
				directionStatusChangeListLogic.changeStatus(bean, tantoCd);
			} catch (OptimisticLockRuntimeException e) {
				try {
					directionStatusChangeListLogic.changeSumiflg(bean, tantoCd,
						"0");
				} catch (OptimisticLockRuntimeException e1) {
					saveError(request,
						"errors.direction.update.if.table.direction.no",
						listForm.getSearchList().get(rowNo - 1)
								.getDirectionNo());
				}
				saveError(request, "errors.direction.not.change.row", rowNo);
			}
		}
		// 処理成功メッセージ
		saveMessage(request, "message.complete.update");
		return mapping.findForward("reSearch");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図ステータス変更一覧画面ロジッククラスを設定します。
	 * @param directionStatusChangeListLogic 製造指図ステータス変更一覧画面ロジッククラス
	 */
	public void setDirectionStatusChangeListLogic(
			final DirectionStatusChangeListLogic directionStatusChangeListLogic) {
		this.directionStatusChangeListLogic = directionStatusChangeListLogic;
	}

	/**
	 * 製造指図ステータス変更一覧画面 帳票Excelクラスを設定します。
	 * @param directionStatusChangeListExcelDecorator 製造指図ステータス変更一覧画面帳票Excelクラス
	 */
	public void setDirectionStatusChangeListExcelDecorator(
			final DirectionStatusChangeListExcelDecorator directionStatusChangeListExcelDecorator) {
		this.directionStatusChangeListExcelDecorator = directionStatusChangeListExcelDecorator;
	}
}
