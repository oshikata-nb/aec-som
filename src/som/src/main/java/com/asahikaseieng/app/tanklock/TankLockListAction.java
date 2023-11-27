/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.tanklock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.direction.DirectionConst;
import com.asahikaseieng.dao.nonentity.tanklock.TankLockList;
import com.asahikaseieng.dao.nonentity.tanklock.TankLockPagerCondition;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 調合タンク底弁インターロック解除/再設定画面 Actionクラス.
 * @author tosco
 * 
 */
public final class TankLockListAction extends AbstractSearchAction {

	/** 調合タンク底弁インターロック解除/再設定画面のロジッククラス */
	private TankLockListLogic tankLockListLogic;

	private TankLockListExcelDecorator tankLockListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public TankLockListAction() {
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
		TankLockListForm frm = (TankLockListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_TANK_LOCK,
			Constants.TAB_ID_TANK_LOCK_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 生産工場コンボボックスを作成し、セット
		frm.setLineCombo(tankLockListLogic.createLineCombobox(true));

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
		TankLockListForm frm = (TankLockListForm) form;

		// 検索結果のクリア
		frm.setSearchList(new ArrayList<TankLockList>());

		// 検索条件を取得
		TankLockPagerCondition condition = (TankLockPagerCondition) frm
				.getPager().getPagerCondition();
		// 検索条件をセット
		String productionLine = frm.getSrhProductionLine(); // 生産工場

		if (DirectionConst.COMBO_ALL_VALUE.equals(productionLine)) {
			// 生産工場=0:すべての場合
			condition.setSrhProductionLine(null);
		} else {
			// 生産工場=0:すべて以外の場合
			condition.setSrhProductionLine(productionLine);
		}

		condition.setSrhItemCd(frm.getSrhItemCd()); // 主要製品コード
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1()); // 他社コード１
		condition.setSrhChogotankno(frm.getSrhChogotankno()); // 調合タンクNO
		condition.setSrhDirectionStatus(frm.getSrhDirectionStatus()); // 指図ステータス

		/* 帳票(Excel)用に検索条件を保持 */
		TankLockListConditionForReport reportCondition = new TankLockListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(tankLockListLogic.getSearchList(condition));
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

		TankLockListForm frm = (TankLockListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = tankLockListExcelDecorator.createReport(frm
				.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 登録処理(登録ボタン押下時)
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
			getLog().debug("issue.");
		}

		// formを取得
		TankLockListForm frm = (TankLockListForm) form;

		// フォームより検索結果取得
		List<TankLockList> searchList = frm.getSearchList();

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			// 調合タンク底弁インターロック解除/再設定 更新処理
			tankLockListLogic.update(searchList, loginUserId);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}
		// 更新完了メッセージを登録
		saveMessage(request, "message.complete.update");

		return mapping.findForward("reSearch");
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
		TankLockListForm frm = (TankLockListForm) form;

		// クリア
		frm.clear();

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 調合タンク底弁インターロック解除/再設定画面ロジッククラスを設定します。
	 * @param tankLockListLogic 調合タンク底弁インターロック解除/再設定画面ロジッククラス
	 */
	public void setTankLockListLogic(final TankLockListLogic tankLockListLogic) {
		this.tankLockListLogic = tankLockListLogic;
	}

	/**
	 * 帳票Excelクラスを設定します。
	 * @param tankLockListExcelDecorator 帳票Excelクラス
	 */
	public void setTankLockListExcelDecorator(
			final TankLockListExcelDecorator tankLockListExcelDecorator) {
		this.tankLockListExcelDecorator = tankLockListExcelDecorator;
	}

}
