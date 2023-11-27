/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.production;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.aspproduction.AspProduction;
import com.asahikaseieng.dao.nonentity.production.ProductionDetail;
import com.asahikaseieng.dao.nonentity.production.ProductionDetailList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 生産計画入力 明細画面 Actionクラス.
 * @author tosco
 */
public final class ProductionDetailAction extends AbstractAction {

	// private static final String FORM_NAME = "productionDetailForm";

	/** 新規フラグ定数 insertは'1' */
	public static final int INSERT = 1;

	/** 新規フラグ定数 updateは'0' */
	public static final int UPDATE = 0;

	/** 生産計画入力 明細画面ロジッククラス */
	private ProductionDetailLogic productionDetailLogic;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/**
	 * コンストラクタ.
	 */
	public ProductionDetailAction() {
		super();
	}

	/**
	 * 検索処理(検索画面のリンク・まとめ入力ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		ProductionDetailForm frm = (ProductionDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PRODUCTION,
			Constants.TAB_ID_PRODUCTION_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 新規用切替フラグを更新(0)に設定
		frm.setInsertFlg(UPDATE);

		try {

			// ヘッダ情報取得
			ProductionDetail headBean = productionDetailLogic.getHeader(frm
					.getSrhItemCd(), frm.getSrhOrderLet());
			// ヘッダ情報設定
			setHeaderInfo(frm, headBean);

			// 明細データ検索
			List<ProductionDetailList> beanList = productionDetailLogic
					.getEntity(frm.getSrhItemCd(), frm.getSrhOrderLet(),
						headBean);
			// 明細部データセット
			frm.setDetailList(beanList);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 更新処理(詳細画面の登録ボタン押下時)
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

		// formを生産計画入力 詳細画面 Formクラスにキャスト
		ProductionDetailForm frm = (ProductionDetailForm) form;

		// 更新対象データリスを作成する
		List<AspProduction> newBean = new ArrayList<AspProduction>();

		// 更新対象データリストに値セット
		setUpdateList(frm.getDetailList(), newBean);

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			// セットしたデータを使用しを更新、新規登録する。
			productionDetailLogic.update(newBean, frm, loginUserId);
		} catch (NoDataException e) {
			// エラーメッセージを登録
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		// 更新完了メッセージを登録
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");

	}

	/**
	 * 新規登録画面表示処理(一覧画面の新規ボタン押下時)
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

		// formを生産計画入力 詳細画面 Formクラスにキャスト
		ProductionDetailForm frm = (ProductionDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PRODUCTION,
			Constants.TAB_ID_PRODUCTION_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 新規用切替フラグを新規(1)に設定
		frm.setInsertFlg(INSERT);
		// 画面の変更フラグをクリア
		frm.setDirtyFlg("false");

		return mapping.findForward("success");
	}

	/**
	 * 新規登録画面表示処理(明細画面のＯＫボタン押下時(新規時))
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNewInputed(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNewInput.");
		}

		// formを生産計画入力 詳細画面 Formクラスにキャスト
		ProductionDetailForm frm = (ProductionDetailForm) form;

		// 新規用切替フラグを新規(1)に設定
		frm.setInsertFlg(INSERT);
		ProductionDetail itemBean = null;

		try {
			// 品目データ取得
			itemBean = productionDetailLogic.getItemEntity(frm.getItemCd());

			// 画面表示用ヘッダ情報設定
			frm.setItemName(itemBean.getItemName());
			frm.setOtherCompanyCd1(itemBean.getOtherCompanyCd1());
			frm.setStyleOfPacking(itemBean.getStyleOfPacking());

			// 入力品目が生産品ではない場合
			BigDecimal tDiv = itemBean.getTypeDivision();
			if (tDiv.compareTo(new BigDecimal(0)) != 0
					&& tDiv.compareTo(new BigDecimal(3)) != 0
					&& tDiv.compareTo(new BigDecimal(6)) != 0
					&& tDiv.compareTo(new BigDecimal(7)) != 0) {

				saveError(request, "errors.production.not.production");
				return mapping.findForward("success");
			}
		} catch (NoDataException e) {
			// マスタに存在しない場合
			saveError(request, "errors.production.no.item");
			return mapping.findForward("success");
		}

		// 存在チェック 既に登録されたデータがないかチェックを行う
		int num = productionDetailLogic.getCountList(frm.getItemCd(), frm
				.getStrOrderLet());
		if (num != 0) {
			// 取得件数が0件ではない場合 すでにデータが存在する場合
			saveError(request, "errors.production.not.nodata");
			return mapping.findForward("success");
		}

		try {
			// 明細データ検索
			List<ProductionDetailList> beanList = productionDetailLogic
					.getEntity(frm.getItemCd(), frm.getStrOrderLet(), itemBean);
			// 明細部データセット
			frm.setDetailList(beanList);

		} catch (NoDataException e) {
			// マスタに存在しない場合
			saveError(request, "errors.production.no.cal");
			return mapping.findForward("success");
		}

		return mapping.findForward("success");
	}

	/**
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
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

		return mapping.findForward("back");

	}

	/**
	 * ヘッダ部データ設定
	 * @param frm 生産計画入力 明細画面Form
	 * @param headBean ヘッダ部データBean
	 */
	private void setHeaderInfo(final ProductionDetailForm frm,
			final ProductionDetail headBean) {
		frm.setStrShipperDivision(headBean.getStrShipperDivision()); // 荷主
		frm.setStrTypeDivision(headBean.getStrTypeDivision()); // 社内製造品/外注品区分
		frm.setProductionLineName(headBean.getProductionLineName()); // 工場名
		frm.setItemCd(headBean.getItemCd()); // 品目コード
		frm.setItemName(headBean.getItemName()); // 品目名称
		frm.setOtherCompanyCd1(headBean.getOtherCompanyCd1()); // 他社コード1
		frm.setStrOrderLet(headBean.getStrOrderLet()); // 生産計画年月

		frm.setStyleOfPacking(headBean.getStyleOfPacking()); // 20091102荷姿追加

		if (headBean.getSumAcceptQty() != null) {
			frm.setSumAcceptQty(headBean.getSumAcceptQty().toString());
			frm.setSumAcceptQty(checkDigitUtilsLogic
					.format(headBean.getUnitOfOperationManagement(), headBean
							.getSumAcceptQty()));
		}

		if (headBean.getSumExpectQty() != null) {
			frm.setSumExpectQty(checkDigitUtilsLogic
					.format(headBean.getUnitOfOperationManagement(), headBean
							.getSumExpectQty()));
		}

		if (headBean.getSumAllOrderQty() != null) {
			frm.setSumAllOrderQty(checkDigitUtilsLogic.format(headBean
					.getUnitOfOperationManagement(), headBean
					.getSumAllOrderQty()));
		}
	}

	/**
	 * 更新データ設定
	 * @param bean 更新データbean
	 * @param newBean 更新用bean
	 */
	private void setUpdateList(final List<ProductionDetailList> beanList,
			final List<AspProduction> newBeanList) {

		for (ProductionDetailList bean : beanList) {
			AspProduction newBean = new AspProduction();

			// 値をコピー
			newBean.setOrderCd(bean.getOrderCd());
			newBean.setItemCd(bean.getItemCd());
			newBean.setOrderLet(bean.getCalDate());
			newBean.setOrderAcceptQty(bean.getOrderAcceptQty());
			bean.setOrderExpectQty(AecNumberUtils.convertBigDecimal(bean
					.getStrOrderExpectQty()));
			newBean.setOrderExpectQty(bean.getOrderExpectQty());
			newBean.setOrderComment(bean.getOrderComment());
			newBean.setOrderNo(bean.getOrderNo());
			newBean.setOrderRowNo(bean.getOrderRowNo());
			newBean.setInputDate(bean.getInputDate());
			newBean.setInputorCd(bean.getInputorCd());
			newBean.setUpdateDate(bean.getUpdateDate());
			newBean.setUpdatorCd(bean.getUpdatorCd());

			// 更新用データリストに追加
			newBeanList.add(newBean);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産計画入力 明細画面ロジッククラスを設定します。
	 * @param productionDetailLogic 生産計画入力 明細画面ロジッククラス
	 */
	public void setProductionDetailLogic(
			final ProductionDetailLogic productionDetailLogic) {
		this.productionDetailLogic = productionDetailLogic;
	}

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

}
