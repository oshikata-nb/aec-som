/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.production;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.production.ProductionList;
import com.asahikaseieng.dao.nonentity.production.ProductionPagerCondition;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 生産計画入力一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class ProductionListAction extends AbstractSearchAction {
	/** 生産計画入力一覧 ロジッククラス */
	private ProductionListLogic productionListLogic;

	private ProductionListExcelDecorator productionListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ProductionListAction() {
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
		ProductionListForm frm = (ProductionListForm) form;

		// 生産工場コンボボックス
		frm
				.setProductionLineCombo(productionListLogic
						.createLineCombobox(true));

		// 初期表示時は検索を行わない
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

		// formを生産計画入力一覧 Formクラスにキャスト
		ProductionListForm frm = (ProductionListForm) form;

		// 一覧検索結果をクリア
		frm.setSearchList(new ArrayList<ProductionList>());

		// 検索条件の取得
		ProductionPagerCondition condition = (ProductionPagerCondition) frm
				.getPager().getPagerCondition();

		// 検索条件をセット
		condition.setSrhShipperDivision(frm.getSrhShipperDivision());
		condition.setSrhTypeDivision(frm.getSrhTypeDivision());
		condition.setSrhProductionLine(frm.getSrhProductionLine());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhOrderLet(frm.getSrhOrderLet());

		/* 帳票(Excel)用に検索条件を保持 */
		ProductionListConditionForReport reportCondition = new ProductionListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// セットした検索条件を使用し、生産計画入力一覧を検索
		frm.setSearchList(productionListLogic.getSearchList(condition));

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

		ProductionListForm frm = (ProductionListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = productionListExcelDecorator.createReport(frm
				.getReportCondition());
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

		// formを仕入一覧 Formクラスにキャスト
		ProductionListForm frm = (ProductionListForm) form;

		// フォームのclearメソッドを使用し、フォームをクリアする
		frm.clear();

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産計画入力一覧 ロジッククラスを設定します。
	 * @param productionListLogic 生産計画入力一覧 ロジッククラス
	 */
	public void setProductionListLogic(
			final ProductionListLogic productionListLogic) {
		this.productionListLogic = productionListLogic;
	}

	/**
	 * productionListExcelDecoratorを設定します。
	 * @param productionListExcelDecorator productionListExcelDecorator
	 */
	public void setProductionListExcelDecorator(
			final ProductionListExcelDecorator productionListExcelDecorator) {
		this.productionListExcelDecorator = productionListExcelDecorator;
	}

}
