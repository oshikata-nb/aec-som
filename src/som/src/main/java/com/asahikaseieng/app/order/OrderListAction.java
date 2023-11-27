/*
 * Created on 2007/12/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.checklog.CheckLog;
import com.asahikaseieng.dao.entity.checklog.CheckLogDao;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusList;
import com.asahikaseieng.dao.nonentity.orderlist.OrderList;
import com.asahikaseieng.dao.nonentity.orderlist.OrderListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受注ヘッダー Action クラス
 * @author t1344224
 */
public final class OrderListAction extends AbstractSearchAction {

	private OrderListLogic orderListLogic;

	private OrderListExcelDecorator orderListExcelDecorator;

	/** 調査ログ用DAO */
	private CheckLogDao checkLogDao;

	/** ログの区分 */
	public static final String LOG_DIVISION = "ORDER_HEAD";

	/**
	 * コンストラクタ.
	 */
	public OrderListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "init", "初期処理　開始");

		OrderListForm frm = (OrderListForm) form;

		/* 受注区分コンボボックス作成用 */
		setOrderDivisionCombobox(frm);

		/* 受注ステータスコンボボックス作成用 */
		setOrderStatusCombobox(frm);

		// 運送会社コンボボックス
		frm.setCarryCombo(orderListLogic.createCarryAllCombobox());

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "init", "初期処理　終了");
		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "search", "検索処理　開始");

		OrderListForm frm = (OrderListForm) form;

		/* 検索条件を取得 */
		OrderListPagerCondition condition = (OrderListPagerCondition) frm
				.getPager().getPagerCondition();

		/* Fromの検索条件をコピー */
		IgnoreCaseBeanUtils.copyProperties(condition, frm);

		/* クリア */
		frm.setSearchList(new ArrayList<OrderList>());

		/* 明細取得 */
		frm.setSearchList(orderListLogic.getList(condition, frm
				.getSrhVenderCd()));

		/* 帳票(Excel)用に検索条件を保持 */
		OrderListConditionForReport reportCondition = new OrderListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 受注一覧表用に検索条件を保持 */
		RepOrderSlipConditionForReport slipCondition = new RepOrderSlipConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(slipCondition, condition);
		frm.setSlipCondition(slipCondition);

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "search", "検索処理　終了");

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
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "clear", "クリア処理　開始");

		OrderListForm frm = (OrderListForm) form;

		/* クリア */
		frm.clear();

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "clear", "クリア処理　終了");

		return mapping.findForward("success");
	}

	/**
	 * 新規処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "newPage", "新規　開始");
		outputCheckLog(getLoginInfo(request).getTantoCd(), "newPage", "新規　終了");

		return mapping.findForward("newPage");
	}

	/**
	 * 受注区分リスト取得
	 * @param frm 画面データ
	 */
	public void setOrderDivisionCombobox(final OrderListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<OrderNamesList> list = orderListLogic.getOrderDivisionList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setOrderDivisionLabels(labels);
		frm.setOrderDivisionValues(values);
	}

	/**
	 * 受注ステータスリスト取得
	 * @param frm 画面データ
	 */
	public void setOrderStatusCombobox(final OrderListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<OrderStatusList> list = orderListLogic.getOrderStatusList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setOrderStatusLabels(labels);
		frm.setOrderStatusValues(values);
	}

	/**
	 * EXCEL作成処理.
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
		OrderListForm frm = (OrderListForm) form;
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "report",
			"帳票Excel　開始");

		/* Excel作成 */
		FileDownloadInfo info = orderListExcelDecorator.createReport(frm
				.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "report",
			"帳票Excel　終了");

		return mapping.findForward("success");
	}

	/**
	 * 受注帳票作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward orderReport(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("orderReport.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "orderReport",
			"受注一覧表　開始");

		OrderListForm frm = (OrderListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = orderListExcelDecorator.createOrderReport(frm
				.getSlipCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelSlipDownloadFlg(true);

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "orderReport",
			"受注一覧表　終了");

		return mapping.findForward("success");
	}

	/**
	 * 
	 * 調査用ログ出力処理
	 * @param tantoCd
	 * @param function
	 * @param message
	 */
	private void outputCheckLog(final String tantoCd, final String function,
			final String message) {
		CheckLog log = new CheckLog();
		log.setModuleCd(LOG_DIVISION);
		log.setClient(tantoCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setErrorMes(function);
		log.setSqlStr(message);
		checkLogDao.insert(log);
	}

	/* -------------------- setter -------------------- */

	/**
	 * orderListLogicを設定します。
	 * @param orderListLogic orderListLogic
	 */
	public void setOrderListLogic(final OrderListLogic orderListLogic) {
		this.orderListLogic = orderListLogic;
	}

	/**
	 * orderListExcelDecoratorを設定します。
	 * @param orderListExcelDecorator orderListExcelDecorator
	 */
	public void setOrderListExcelDecorator(
			final OrderListExcelDecorator orderListExcelDecorator) {
		this.orderListExcelDecorator = orderListExcelDecorator;
	}

	/**
	 * checkLogDaoを設定します。
	 * @param checkLogDao checkLogDao
	 */
	public void setCheckLogDao(final CheckLogDao checkLogDao) {
		this.checkLogDao = checkLogDao;
	}
}
