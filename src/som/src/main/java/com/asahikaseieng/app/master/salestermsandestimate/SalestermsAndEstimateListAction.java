package com.asahikaseieng.app.master.salestermsandestimate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail.SalestermsAndEstimateDetailDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheck;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheckDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateList;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListConditionForReport;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 販売条件・見積単価 コピー作成・削除一覧 Actionクラス.
 * @author t0011036
 */
public final class SalestermsAndEstimateListAction extends AbstractSearchAction {

	private SalestermsAndEstimateListLogic salestermsAndEstimateListLogic;

	private SalestermsAndEstimateListExcelDecorator salestermsAndEstimateListExcelDecorator;

	private SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao;

	private SalestermsAndEstimateDetailDao salestermsAndEstimateDetailDao;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateListAction() {
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
		
		SalestermsAndEstimateListForm frm = (SalestermsAndEstimateListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALESTERMS_AND_ESTIMATE,
			Constants.TAB_ID_SALESTERMS_AND_ESTIMATE_DETAIL);
		
		// 処理区分コンボボックス作成
		setProcessDivisionCombo(frm);
		
		// ステータスコンボボックス作成
		setStatusCombo(frm);		

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

		SalestermsAndEstimateListForm frm = (SalestermsAndEstimateListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<SalestermsAndEstimateList>());

		/* 検索条件を取得 */
		SalestermsAndEstimateListPagerCondition condition = (SalestermsAndEstimateListPagerCondition) frm
				.getPager().getPagerCondition();

		/* Fromの検索条件をコピー */
		IgnoreCaseBeanUtils.copyProperties(condition, frm);

		/* 帳票(Excel)用に検索条件を保持 */
		SalestermsAndEstimateListConditionForReport reportCondition = new SalestermsAndEstimateListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(salestermsAndEstimateListLogic.getList(condition));

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

		SalestermsAndEstimateListForm frm = (SalestermsAndEstimateListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
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

		SalestermsAndEstimateListForm frm = (SalestermsAndEstimateListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = salestermsAndEstimateListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

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

		return mapping.findForward("newPage");
	}
	
	/**
	 * 販売条件・見積単価コピー・削除確定処理(詳細画面の確定ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward confirm(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("confirm.");
		}

		SalestermsAndEstimateListForm frm = (SalestermsAndEstimateListForm) form;
		
		boolean isSelected = false;
		
		// チェックボックスをONがないかチェック
		for (SalestermsAndEstimateList bean : frm.getSearchList()) {
			if (bean.isSalestermsAndEstimateCheckBox()){
				isSelected = true;
				break;
			}
		}
		if (!isSelected) {
			// 一つも選択されていない場合
			saveError(request, "errors.salestermsandestimate.selected.checkbox.confirm");
			return mapping.findForward("success");
		}
		
		int row = 1;
		
		// 検索画面の条件でループ★チェック処理
		for (SalestermsAndEstimateList bean : frm.getSearchList()) {
			if (bean.isSalestermsAndEstimateCheckBox()){
				// チェックボックスがONの場合、処理を行う。

				//確定・確定取消判別フラグへtrue（確定処理）をセット
				frm.setConfirmFlg("true");
				
				// ステータスが、登録ではない場合、エラーとする
				if(!bean.getStatus().equals("1")){
					saveError(request, "errors.salestermsandestimate.status.confirm",row);
					return mapping.findForward("success");
				}
				
				/* 確定処理時における処理区分ごとの制約 */
				//処理区分が「1:見積単価削除」の場合
				if(bean.getProcessDivision().equals("1")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateListLogic.getEstimateItemCount(bean.getSrhItemCdFrom());
		
					//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
					if (estimateItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「2:販売条件削除」の場合
				else if(bean.getProcessDivision().equals("2")){
					/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
					SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateListLogic.getSalestermsItemCount(bean.getSrhItemCdFrom());
		
					//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
					if (salestermsItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「3:見積単価・販売条件削除」の場合
				else if(bean.getProcessDivision().equals("3")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateListLogic.getEstimateItemCount(bean.getSrhItemCdFrom());
					
					/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
					SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateListLogic.getSalestermsItemCount(bean.getSrhItemCdFrom());
					
					//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
					if (estimateItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
		
					//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
					if (salestermsItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「4:見積単価コピー」の場合
				else if(bean.getProcessDivision().equals("4")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateListLogic.getEstimateItemCount(bean.getSrhItemCdFrom());
					
					/* コピー先品目コードが見積ファイルに存在するかチェック*/
					SalestermsAndEstimateItemCheck estimateItemCdToCount = salestermsAndEstimateListLogic.getEstimateItemCount(bean.getSrhItemCdTo());
		
					//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
					if (estimateItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
					
					//コピー先品目コードが見積ファイルに存在する場合はエラー
					if (estimateItemCdToCount.getItemCount() != 0) {
					/* エラーメッセージ */
						saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.to.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「5:販売条件コピー」の場合
				else if(bean.getProcessDivision().equals("5")){
					/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
					SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateListLogic.getSalestermsItemCount(bean.getSrhItemCdFrom());
					
					/* コピー先品目コードが販売条件マスタに存在するかチェック*/
					SalestermsAndEstimateItemCheck salestermsItemCdToCount = salestermsAndEstimateListLogic.getSalestermsItemCount(bean.getSrhItemCdTo());
					
					//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
					if (salestermsItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
					
					//コピー先品目コードが販売条件マスタに存在する場合はエラー
					if (salestermsItemCdToCount.getItemCount() != 0) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterm.data.salestermsandestimate.item.cd.to.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「6:見積単価・販売条件コピー」の場合
				else if(bean.getProcessDivision().equals("6")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateListLogic.getEstimateItemCount(bean.getSrhItemCdFrom());
					
					/* コピー先品目コードが見積ファイルに存在するかチェック*/
					SalestermsAndEstimateItemCheck estimateItemCdToCount = salestermsAndEstimateListLogic.getEstimateItemCount(bean.getSrhItemCdTo());
					
					/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
					SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateListLogic.getSalestermsItemCount(bean.getSrhItemCdFrom());
					
					/* コピー先品目コードが販売条件マスタに存在するかチェック*/
					SalestermsAndEstimateItemCheck salestermsItemCdToCount = salestermsAndEstimateListLogic.getSalestermsItemCount(bean.getSrhItemCdTo());
		
					//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
					if (estimateItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
					
					//コピー先品目コードが見積ファイルに存在する場合はエラー
					if (estimateItemCdToCount.getItemCount() != 0) {
					/* エラーメッセージ */
						saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.to.row",row);
						return mapping.findForward("success");
					}
					
					//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
					if (salestermsItemCdFromCount.getItemCount() == 0) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
					
					//コピー先品目コードが販売条件マスタに存在する場合はエラー
					if (salestermsItemCdToCount.getItemCount() != 0) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterm.data.salestermsandestimate.item.cd.to.row",row);
						return mapping.findForward("success");
					}
				}
			}
			row++;
		}
		
		// 販売条件・見積単価コピー・削除確定処理
		try {
			// コピー・削除処理をプロシージャより実行する
			salestermsAndEstimateListLogic.conSalestermsAndEstimate(frm, getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			salestermsAndEstimateListLogic.outPutErrorLog(frm.getErrorCd(), frm.getErrorMsg(),getLoginInfo(request).getTantoCd());
			String errMsg = "errors.salestermsandestimate.confirm.error";
			if (errMsg.equals(e.getMessage())) {
				// 確定処理に失敗
				saveError(request, errMsg);
				return mapping.getInputForward();
			} else {
				throw e;
			}
		}
		
				
				
		// メッセージ
		saveMessage(request, "message.salestermsandestimate.complete.confirm");

		return mapping.findForward("reSearch");
	
	}
	
	/**
	 * 販売条件・見積単価コピー・削除確定取消処理(詳細画面の確定取消ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward confirmCancel(final ActionMapping mapping,	final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("confirmCancel.");
		}
		SalestermsAndEstimateListForm frm = (SalestermsAndEstimateListForm) form;
		
		boolean isSelected = false;
		
		// チェックボックスをONがないかチェック
		for (SalestermsAndEstimateList bean : frm.getSearchList()) {
			if (bean.isSalestermsAndEstimateCheckBox()){
				isSelected = true;
				break;
			}
		}
		if (!isSelected) {
			// 一つも選択されていない場合
			saveError(request, "errors.salestermsandestimate.selected.checkbox.confirm");
			return mapping.findForward("success");
		}
		int row = 1;
		// 検索画面の条件でループ★チェック処理
		for (SalestermsAndEstimateList bean : frm.getSearchList()) {
			if (bean.isSalestermsAndEstimateCheckBox()){
				
				// ステータスが、登録ではない場合、エラーとする
				if(!bean.getStatus().equals("2")){
					saveError(request, "errors.salestermsandestimate.status.confirmcancel",row);
					return mapping.findForward("success");
				}

				// チェックボックスがONの場合、処理を行う。
				//確定・確定取消判別フラグへfalse（確定取消処理）をセット
				frm.setConfirmFlg("false");
				
				/* 確定取消処理時における処理区分ごとの制約 */
				//処理区分が「1:見積単価削除」の場合
				if(bean.getProcessDivision().equals("1")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getEstimateCount(bean.getSrhItemCdFrom());
					
					//コピー元・削除品目コードが見積ファイルに存在する場合はエラー
					if (!masterNum.equals(BigDecimal.ZERO)) {
						/* エラーメッセージ */
						saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「2:販売条件削除」の場合
				else if(bean.getProcessDivision().equals("2")){
					/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
					BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getSalesTermsCount(bean.getSrhItemCdFrom());
		
					//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
					if (!masterNum.equals(BigDecimal.ZERO)) {
						/* エラーメッセージ */
						saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「3:見積単価・販売条件削除」の場合
				else if(bean.getProcessDivision().equals("3")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getEstimateCount(bean.getSrhItemCdFrom());
					
					//コピー元・削除品目コードが見積ファイルに存在する場合はエラー
					if (!masterNum.equals(BigDecimal.ZERO)) {
						saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
					
					/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
					masterNum = salestermsAndEstimateItemCheckDao.getSalesTermsCount(bean.getSrhItemCdFrom());
		
					//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
					if (!masterNum.equals(BigDecimal.ZERO)) {
						/* エラーメッセージ */
						saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「4:見積単価コピー」の場合
				else if(bean.getProcessDivision().equals("4")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					BigDecimal copyNum = salestermsAndEstimateItemCheckDao.getEstimateSaveCount(bean.getPkNo());
					BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getEstimateCount(bean.getSrhItemCdTo());
					
					//コピー元・削除品目コードが見積ファイルに存在する場合はエラー
					if (!copyNum.equals(masterNum)) {
					/* エラーメッセージ */
						saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「5:販売条件コピー」の場合
				else if(bean.getProcessDivision().equals("5")){
					/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
					BigDecimal copyNum = salestermsAndEstimateItemCheckDao.getSalesTermsSaveCount(bean.getPkNo());
					BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getSalesTermsCount(bean.getSrhItemCdTo());
					
					//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
					if (!copyNum.equals(masterNum)) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
				//処理区分が「6:見積単価・販売条件コピー」の場合
				else if(bean.getProcessDivision().equals("6")){
					/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
					BigDecimal copyNum = salestermsAndEstimateItemCheckDao.getEstimateSaveCount(bean.getPkNo());
					BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getEstimateCount(bean.getSrhItemCdTo());
					
					//コピー元・削除品目コードが見積ファイルに存在する場合はエラー
					if (!copyNum.equals(masterNum)) {
						/* エラーメッセージ */
						saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
					
					// バックアップに取得した、コピー件数と現在の見積単価マスタの件数が異なる場合エラーとする。
					copyNum = salestermsAndEstimateItemCheckDao.getSalesTermsSaveCount(bean.getPkNo());
					masterNum = salestermsAndEstimateItemCheckDao.getSalesTermsCount(bean.getSrhItemCdTo());
					//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
					if (!copyNum.equals(masterNum)) {
					/* エラーメッセージ */
						saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from.row",row);
						return mapping.findForward("success");
					}
				}
				
			}
			row++;
		}
		
		// 販売条件・見積単価コピー・削除確定処理
		try {
			// コピー・削除処理をプロシージャより実行する
			salestermsAndEstimateListLogic.conSalestermsAndEstimate(frm, getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			salestermsAndEstimateListLogic.outPutErrorLog(frm.getErrorCd(), frm.getErrorMsg(),getLoginInfo(request).getTantoCd());
			String errMsg = "errors.salestermsandestimate.confirm.error";
			if (errMsg.equals(e.getMessage())) {
				// 確定処理に失敗
				saveError(request, errMsg);
				return mapping.getInputForward();
			} else {
				throw e;
			}
		}

		// メッセージ
		saveMessage(request, "message.salestermsandestimate.complete.confirmcancel");

		return mapping.findForward("reSearch");

	}
	
	/**
	 * 処理区分取得
	 * @param frm 画面データ
	 */
	public void setProcessDivisionCombo(final SalestermsAndEstimateListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<SalestermsandestimateNamesListForComboboxes> list = salestermsAndEstimateListLogic
				.getProcessDivisionList();

		String[] values;
		String[] labels;

		/* リストサイズ＋1（「すべて」用）の配列数を準備 */
		labels = new String[list.size()+1];
		values = new String[list.size()+1];
		
		/* 「すべて」となる選択肢を[0]へセット */
		labels[0] = "すべて";
		values[0] = "0";		

		/* コンボボックスアイテム設定処理（「すべて」有の場合） */
		for (int i = 0; i < list.size(); i++) {
			labels[i+1] = list.get(i).getName01();
			values[i+1] = list.get(i).getNameCd();
		}

		frm.setSrhProcessDivisionLabels(labels);
		frm.setSrhProcessDivisionValues(values);
	}

	/**
	 * ステータス取得
	 * @param frm 画面データ
	 */
	public void setStatusCombo(final SalestermsAndEstimateListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<SalestermsandestimateNamesListForComboboxes> list = salestermsAndEstimateListLogic
				.getStatusList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setSrhStatusLabels(labels);
		frm.setSrhStatusValues(values);
	}


	/* -------------------- setter -------------------- */

	/**
	 * salesTermsListExcelDecoratorを設定します。
	 * @param salestermsAndEstimateListExcelDecorator salesTermsListExcelDecorator
	 */
	public void setSalesTermsListExcelDecorator(
			final SalestermsAndEstimateListExcelDecorator salestermsAndEstimateListExcelDecorator) {
		this.salestermsAndEstimateListExcelDecorator = salestermsAndEstimateListExcelDecorator;
	}

	/**
	 * salesTermsListLogicを設定します。
	 * @param salestermsAndEstimateListLogic salesTermsListLogic
	 */
	public void setSalesTermsListLogic(
			final SalestermsAndEstimateListLogic salestermsAndEstimateListLogic) {
		this.salestermsAndEstimateListLogic = salestermsAndEstimateListLogic;
	}

	/**
	 * salestermsAndEstimateItemCheckDaoを設定します。
	 * @param salestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao
	 */
	public void setSalestermsAndEstimateItemCheckDao(
			SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao) {
		this.salestermsAndEstimateItemCheckDao = salestermsAndEstimateItemCheckDao;
	}

	/**
	 * salestermsAndEstimateDetailDaoを設定します。
	 * @param salestermsAndEstimateDetailDao salestermsAndEstimateDetailDao
	 */
	public void setSalestermsAndEstimateDetailDao(
			SalestermsAndEstimateDetailDao salestermsAndEstimateDetailDao) {
		this.salestermsAndEstimateDetailDao = salestermsAndEstimateDetailDao;
	}
}