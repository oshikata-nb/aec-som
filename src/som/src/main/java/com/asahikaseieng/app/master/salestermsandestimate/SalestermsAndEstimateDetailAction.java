
/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.master.salestermsandestimate.SalestermsAndEstimate;
import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail.SalestermsAndEstimateDetail;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheck;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheckDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 販売条件・見積単価 コピー作成・削除詳細 Actionクラス.
 * @author t0011036
 */
public final class SalestermsAndEstimateDetailAction extends AbstractAction {

	private SalestermsAndEstimateDetailLogic salestermsAndEstimateDetailLogic;
	
	private GetNumberLogic getNumberLogic;

	private SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		SalestermsAndEstimateDetailForm frm = (SalestermsAndEstimateDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALESTERMS_AND_ESTIMATE,Constants.TAB_ID_SALESTERMS_AND_ESTIMATE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}
		
		// 処理区分コンボボックス作成
		setProcessDivisionCombo(frm);	

		/* 初期検索 */
		SalestermsAndEstimateDetail bean = salestermsAndEstimateDetailLogic.getDetailEntity(frm.getPkNo());
		
		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

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
	public ActionForward update(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		SalestermsAndEstimateDetailForm frm = (SalestermsAndEstimateDetailForm) form;
		
		/* 処理区分が「1:見積単価削除」「2:販売条件削除」「3:見積単価、販売条件削除」の場合はコピー先品目コードをnullに設定 */
		if(frm.getProcessDivision().equals("1") || frm.getProcessDivision().equals("2") || frm.getProcessDivision().equals("3")) {
			frm.setItemCdTo(null);
		}
		
		/* コピー元・削除品目コードチェック */
		if (!StringUtils.isEmpty(frm.getItemCdFrom())) {
			if(frm.getProcessDivision().equals("4") || frm.getProcessDivision().equals("6")){
				/* 見積単価コピーに関する処理区分が選択されている場合は品目マスタをチェック。 */
				ItemDetail beanItemFrom = salestermsAndEstimateDetailLogic.getItemEntity(frm.getItemCdFrom());

				if (beanItemFrom == null) {
					/* エラーメッセージ */
					saveError(request, "errors.nodata.salestermsandestimate.item.cd.from");
					return mapping.findForward("success");
				}
			}else{
				/* 見積単価コピーに関する処理区分以外が選択されている場合は品目マスタキューをチェック。 */
				ItemQueueLastVersion beanItemQueueFrom = salestermsAndEstimateDetailLogic.getItemQueueEntity(frm.getItemCdFrom());
	
				if (beanItemQueueFrom == null) {
					/* エラーメッセージ */
					saveError(request, "errors.nodata.salestermsandestimate.item.cd.from");
					return mapping.findForward("success");
				}
			}
		}

		/* コピー先品目コードチェック */
		if (!StringUtils.isEmpty(frm.getItemCdTo())) {
			if(frm.getProcessDivision().equals("4") || frm.getProcessDivision().equals("6")){
				/* 見積単価コピーに関する処理区分が選択されている場合は品目マスタをチェック。 */
				ItemDetail beanItemTo = salestermsAndEstimateDetailLogic.getItemEntity(frm.getItemCdTo());

				if (beanItemTo == null) {
					/* エラーメッセージ */
					saveError(request, "errors.nodata.salestermsandestimate.item.cd.to");
					return mapping.findForward("success");
				}
			}else{
				/* 見積単価コピーに関する処理区分以外が選択されている場合は品目マスタキューをチェック。 */
				ItemQueueLastVersion beanItemQueueTo = salestermsAndEstimateDetailLogic.getItemQueueEntity(frm.getItemCdTo());
	
				if (beanItemQueueTo == null) {
					/* エラーメッセージ */
					saveError(request, "errors.nodata.salestermsandestimate.item.cd.to");
					return mapping.findForward("success");
				}
			}
		}
		
		/* 入力日チェック */
		if (!StringUtils.isEmpty(frm.getStrInputDate())) {
			
			// システム日付を取得
			String now = AecDateUtils.dateFormat(
				AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd");
			
			/* エラーメッセージ */
			if (now.compareTo(frm.getStrInputDate()) < 0) {
				saveError(request, "errors.salestermsandestimate.input.date");
				return mapping.findForward("success");
			}			
		}
		
		/* 登録処理を実行 */		
		if (frm.getNewFlg().equals("true")) {
			salestermsAndEstimateDetailLogic.insert(insertData(frm,getLoginInfo(request).getTantoCd()));
			
		/* 更新処理を実行 */
		} else {
			salestermsAndEstimateDetailLogic.update(updateData(frm, getLoginInfo(request).getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 削除処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete");
		}

		SalestermsAndEstimateDetailForm frm = (SalestermsAndEstimateDetailForm) form;

		/* 削除処理 */
		salestermsAndEstimateDetailLogic.delete(deleteData(frm, getLoginInfo(request).getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}
	
	/**
	 * 登録処理用のデータを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return SalestermsAndEstimate
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws NoDataException 
	 */
	private SalestermsAndEstimate insertData(final SalestermsAndEstimateDetailForm frm, final String tantoCd)	throws IllegalAccessException, InvocationTargetException, NoDataException {
		SalestermsAndEstimate newBean = new SalestermsAndEstimate();
		
		// 販売条件・見積単価　コピー・削除テーブル用のpkNo発番
		String pkNo = getNumberLogic.getSalestermsAndEstimatePkNo();
		//発番したpkNoをfrmへセット
		frm.setPkNo(pkNo);
		
		//statusへ1:登録をセット
		frm.setStatus("1");

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}
	
	/**
	 * 更新処理用のデータを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return SalestermsAndEstimate
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private SalestermsAndEstimate updateData(final SalestermsAndEstimateDetailForm frm, final String tantoCd)	throws IllegalAccessException, InvocationTargetException {
		SalestermsAndEstimate newBean = new SalestermsAndEstimate();

		try {
			newBean = salestermsAndEstimateDetailLogic.getEntity(frm.getPkNo());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}


	/**
	 * 削除処理用のデータを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return SalestermsAndEstimate
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private SalestermsAndEstimate deleteData(final SalestermsAndEstimateDetailForm frm, final String tantoCd)	throws IllegalAccessException, InvocationTargetException {
		SalestermsAndEstimate newBean = new SalestermsAndEstimate();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
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
	public ActionForward back(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		SalestermsAndEstimateDetailForm frm = (SalestermsAndEstimateDetailForm) form;
		
		/* ログイン情報取得*/
		String tantoName = getLoginInfo(request).getTantoNm();

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALESTERMS_AND_ESTIMATE,Constants.TAB_ID_SALESTERMS_AND_ESTIMATE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();
		
		// 処理区分コンボボックス作成
		setProcessDivisionCombo(frm);	
		
		// 入力日にシステム日時をセット
		Calendar cal1 = Calendar.getInstance(); // システム日時を取得
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		frm.setStrInputDate(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット
		
		//ログイン担当者名称を設定
		frm.setTantoName(tantoName);
		
		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
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

		SalestermsAndEstimateDetailForm frm = (SalestermsAndEstimateDetailForm) form;
		
		/* 検索（フォームの値が変更されてしまった場合に適用させないため） */
		SalestermsAndEstimateDetail bean = salestermsAndEstimateDetailLogic.getDetailEntity(frm.getPkNo());
		
		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);
		
		//確定・確定取消判別フラグへtrue（確定処理）をセット
		frm.setConfirmFlg("true");
		
		/* 確定処理時における処理区分ごとの制約 */
		//処理区分が「1:見積単価削除」の場合
		if(frm.getProcessDivision().equals("1")){
			/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
			SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateDetailLogic.getEstimateItemCount(frm.getItemCdFrom());

			//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
			if (estimateItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「2:販売条件削除」の場合
		else if(frm.getProcessDivision().equals("2")){
			/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
			SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateDetailLogic.getSalestermsItemCount(frm.getItemCdFrom());

			//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
			if (salestermsItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「3:見積単価・販売条件削除」の場合
		else if(frm.getProcessDivision().equals("3")){
			/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
			SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateDetailLogic.getEstimateItemCount(frm.getItemCdFrom());
			
			/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
			SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateDetailLogic.getSalestermsItemCount(frm.getItemCdFrom());
			
			//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
			if (estimateItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}

			//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
			if (salestermsItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「4:見積単価コピー」の場合
		else if(frm.getProcessDivision().equals("4")){
			/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
			SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateDetailLogic.getEstimateItemCount(frm.getItemCdFrom());
			
			/* コピー先品目コードが見積ファイルに存在するかチェック*/
			SalestermsAndEstimateItemCheck estimateItemCdToCount = salestermsAndEstimateDetailLogic.getEstimateItemCount(frm.getItemCdTo());

			//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
			if (estimateItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
			
			//コピー先品目コードが見積ファイルに存在する場合はエラー
			if (estimateItemCdToCount.getItemCount() != 0) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.to");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「5:販売条件コピー」の場合
		else if(frm.getProcessDivision().equals("5")){
			/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
			SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateDetailLogic.getSalestermsItemCount(frm.getItemCdFrom());
			
			/* コピー先品目コードが販売条件マスタに存在するかチェック*/
			SalestermsAndEstimateItemCheck salestermsItemCdToCount = salestermsAndEstimateDetailLogic.getSalestermsItemCount(frm.getItemCdTo());
			
			//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
			if (salestermsItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
			
			//コピー先品目コードが販売条件マスタに存在する場合はエラー
			if (salestermsItemCdToCount.getItemCount() != 0) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterm.data.salestermsandestimate.item.cd.to");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「6:見積単価・販売条件コピー」の場合
		else if(frm.getProcessDivision().equals("6")){
			/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
			SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateDetailLogic.getEstimateItemCount(frm.getItemCdFrom());
			
			/* コピー先品目コードが見積ファイルに存在するかチェック*/
			SalestermsAndEstimateItemCheck estimateItemCdToCount = salestermsAndEstimateDetailLogic.getEstimateItemCount(frm.getItemCdTo());
			
			/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
			SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateDetailLogic.getSalestermsItemCount(frm.getItemCdFrom());
			
			/* コピー先品目コードが販売条件マスタに存在するかチェック*/
			SalestermsAndEstimateItemCheck salestermsItemCdToCount = salestermsAndEstimateDetailLogic.getSalestermsItemCount(frm.getItemCdTo());

			//コピー元・削除品目コードが見積ファイルに存在しない場合はエラー
			if (estimateItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
			
			//コピー先品目コードが見積ファイルに存在する場合はエラー
			if (estimateItemCdToCount.getItemCount() != 0) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.to");
				return mapping.findForward("success");
			}
			
			//コピー元・削除品目コードが販売条件マスタに存在しない場合はエラー
			if (salestermsItemCdFromCount.getItemCount() == 0) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterms.nodata.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
			
			//コピー先品目コードが販売条件マスタに存在する場合はエラー
			if (salestermsItemCdToCount.getItemCount() != 0) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterm.data.salestermsandestimate.item.cd.to");
				return mapping.findForward("success");
			}
		}

		// 販売条件・見積単価コピー・削除確定処理
		try {
			// コピー・削除処理をプロシージャより実行する
			salestermsAndEstimateDetailLogic.conSalestermsAndEstimate(frm, getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			salestermsAndEstimateDetailLogic.outPutErrorLog(frm.getErrorCd(), frm.getErrorMsg(),getLoginInfo(request).getTantoCd());
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

		return mapping.findForward("back");

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

		SalestermsAndEstimateDetailForm frm = (SalestermsAndEstimateDetailForm) form;
		
		/* 検索（フォームの値が変更されてしまった場合に適用させないため） */
		SalestermsAndEstimateDetail bean = salestermsAndEstimateDetailLogic.getDetailEntity(frm.getPkNo());
		
		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);
		
		//確定・確定取消判別フラグへfalse（確定取消処理）をセット
		frm.setConfirmFlg("false");
		
		/* 確定取消処理時における処理区分ごとの制約 */
		//処理区分が「1:見積単価削除」の場合
		if(frm.getProcessDivision().equals("1")){
			/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
			BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getEstimateCount(bean.getItemCdFrom());

			//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
			if (!masterNum.equals(BigDecimal.ZERO)) {
				/* エラーメッセージ */
				saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「2:販売条件削除」の場合
		else if(frm.getProcessDivision().equals("2")){
			/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
			BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getSalesTermsCount(frm.getItemCdFrom());
			
			//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
			if (!masterNum.equals(BigDecimal.ZERO)) {
				/* エラーメッセージ */
				saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「3:見積単価・販売条件削除」の場合
		else if(frm.getProcessDivision().equals("3")){
			/* コピー元・削除品目コードが見積ファイルに存在するかチェック*/
			SalestermsAndEstimateItemCheck estimateItemCdFromCount = salestermsAndEstimateDetailLogic.getEstimateItemCount(frm.getItemCdFrom());
			
			/* コピー元・削除品目コードが販売条件マスタに存在するかチェック*/
			SalestermsAndEstimateItemCheck salestermsItemCdFromCount = salestermsAndEstimateDetailLogic.getSalestermsItemCount(frm.getItemCdFrom());
			
			//コピー元・削除品目コードが見積ファイルに存在する場合はエラー
			if (estimateItemCdFromCount.getItemCount() != 0) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
			
			//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
			if (salestermsItemCdFromCount.getItemCount() != 0) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}		
		}
		
		//処理区分が「4:見積単価コピー」の場合
		else if(frm.getProcessDivision().equals("4")){
			// バックアップに取得した、コピー件数と現在の見積単価マスタの件数が異なる場合エラーとする。
			BigDecimal copyNum = salestermsAndEstimateItemCheckDao.getEstimateSaveCount(frm.getPkNo());
			BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getEstimateCount(frm.getItemCdTo());
			
			//コピー元・削除品目コードが見積ファイルに存在する場合はエラー
			if (!copyNum.equals(masterNum)) {
			/* エラーメッセージ */
				saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「5:販売条件コピー」の場合
		else if(frm.getProcessDivision().equals("5")){
			// バックアップに取得した、コピー件数と現在の見積単価マスタの件数が異なる場合エラーとする。
			BigDecimal copyNum = salestermsAndEstimateItemCheckDao.getSalesTermsSaveCount(frm.getPkNo());
			BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getSalesTermsCount(frm.getItemCdTo());
			
			//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
			if (!copyNum.equals(masterNum)) {
			/* エラーメッセージ */
				saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		//処理区分が「6:見積単価・販売条件コピー」の場合
		else if(frm.getProcessDivision().equals("6")){
			// バックアップに取得した、コピー件数と現在の見積単価マスタの件数が異なる場合エラーとする。
			BigDecimal copyNum = salestermsAndEstimateItemCheckDao.getEstimateSaveCount(frm.getPkNo());
			BigDecimal masterNum = salestermsAndEstimateItemCheckDao.getEstimateCount(frm.getItemCdTo());
			
			//コピー元・削除品目コードが見積ファイルに存在する場合はエラー
			if (!copyNum.equals(masterNum)) {
				/* エラーメッセージ */
				saveError(request, "errors.estimate.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
			// バックアップに取得した、コピー件数と現在の見積単価マスタの件数が異なる場合エラーとする。
			copyNum = salestermsAndEstimateItemCheckDao.getSalesTermsSaveCount(frm.getPkNo());
			masterNum = salestermsAndEstimateItemCheckDao.getSalesTermsCount(frm.getItemCdTo());
			
			//コピー元・削除品目コードが販売条件マスタに存在する場合はエラー
			if (!copyNum.equals(masterNum)) {
				saveError(request, "errors.salesterms.data.salestermsandestimate.item.cd.from");
				return mapping.findForward("success");
			}
		}
		
		// 販売条件・見積単価コピー・削除確定取消処理
		try {
			// コピー・削除処理をプロシージャより実行する
			salestermsAndEstimateDetailLogic.conSalestermsAndEstimate(frm, getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			salestermsAndEstimateDetailLogic.outPutErrorLog(frm.getErrorCd(), frm.getErrorMsg(),getLoginInfo(request).getTantoCd());
			String errMsg = "errors.salestermsandestimate.confirmcancel.error";
			if (errMsg.equals(e.getMessage())) {
				// 確定取消処理に失敗
				saveError(request, errMsg);
				return mapping.getInputForward();
			} else {
				throw e;
			}
		}

		// メッセージ
		saveMessage(request, "message.salestermsandestimate.complete.confirmcancel");

		return mapping.findForward("back");

	}
	
	/**
	 * 処理区分用コンボボックス作成
	 * @param frm 画面データ
	 */
	public void setProcessDivisionCombo(final SalestermsAndEstimateDetailForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<SalestermsandestimateNamesListForComboboxes> list = salestermsAndEstimateDetailLogic
				.getProcessDivisionList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setProcessDivisionLabels(labels);
		frm.setProcessDivisionValues(values);
	}

	/* -------------------- setter -------------------- */

	/**
	 * salesTermsDetailLogicを設定します。
	 * @param salestermsAndEstimateDetailLogic salesTermsDetailLogic
	 */
	public void setSalesTermsDetailLogic(
			final SalestermsAndEstimateDetailLogic salestermsAndEstimateDetailLogic) {
		this.salestermsAndEstimateDetailLogic = salestermsAndEstimateDetailLogic;
	}
	
	/**
	 * 発番処理 ロジッククラスを設定します。
	 * @param getNumberLogic 発番処理ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * salestermsAndEstimateItemCheckDaoを設定します。
	 * @param salestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao
	 */
	public void setSalestermsAndEstimateItemCheckDao(
			SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao) {
		this.salestermsAndEstimateItemCheckDao = salestermsAndEstimateItemCheckDao;
	}

}

