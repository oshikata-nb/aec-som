/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeader;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSales;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecTextUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 消込入力詳細 Actionクラス
 * @author tosco
 */
public final class EraserDetailAction extends AbstractAction {

	/** 新規用切替フラグ 登録 */
	private static final int INSERT_FLG_INS = 1;

	/** 新規用切替フラグ 更新 */
	private static final int INSERT_FLG_UPD = 0;

	private EraserDetailLogic eraserDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public EraserDetailAction() {
		super();
	}

	/**
	 * 消込入力詳細画面表示処理
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

		EraserDetailForm frm = (EraserDetailForm) form;

		try {
			if (StringUtils.isEmpty(frm.getEraserNo())) {
				frm.setInsertFlg(INSERT_FLG_INS);	// 新規
			} else {
				frm.setInsertFlg(INSERT_FLG_UPD);	// 変更
			}

			// 消込・請求・入金データ(上段・中段)検索
			List<ClaimEraserDetail> beanList
			= eraserDetailLogic.getDetailData(frm.getEraserNo()
									, frm.getSectionCd()
									, frm.getVenderCd()
									, AecTextUtils.likeFilter(frm.getTantoCd())
									, AecTextUtils.likeFilter(frm.getTantoNm()));

			// BeanをFormにコピー
			// 消込・請求ヘッダデータ(上段)
			ClaimEraserDetail bean = beanList.get(0);
			frm.setEraserNo(bean.getEraserNo());                                // 消込番号
			frm.setSectionCd(bean.getSectionCd());                              // 部門コード
			frm.setSectionName(bean.getSectionName());                          // 部門名称
			frm.setVenderCd(bean.getVenderCd());                                // 請求先
			frm.setVenderName(bean.getVenderName());                            // 請求先名称
			frm.setEraserCapaAmount(bean.getStrEraserCapaAmount());             // 消込可能額			frm.setEraserAmount(bean.getStrEraserAmount());                     // 消込額			frm.setEraserBalanceAmount(bean.getStrEraserBalanceAmount());		// 消込残合計			frm.setSumCreditAmount(bean.getStrSumCreditAmount());				// 入金金額合計			frm.setSumEraserAmount(bean.getStrSumEraserAmount());				// 消込額合計			frm.setSumCreditEraserAmount(bean.getStrSumCreditEraserAmount());	// 入金消込残合計			frm.setNewEraserAmount(bean.getStrEraserAmount());                  // 消込額(再計算値)
			frm.setNewEraserBalanceAmount(bean.getStrEraserBalanceAmount());	// 消込残合計(再計算値)
			frm.setNewSumEraserAmount(bean.getStrSumEraserAmount());            // 消込額合計(再計算値)
			frm.setNewSumCreditEraserAmount(
				bean.getStrSumCreditEraserAmount());                        // 入金消込残合計(再計算値)

			// 指定した請求番号の消込額を除いた消込合計
			BigDecimal baseSumEraserAmount = new BigDecimal(0);
			if (bean.getSumEraserAmount() != null) {
				baseSumEraserAmount = bean.getSumEraserAmount().subtract(bean.getEraserAmount());
			}
			frm.setBaseSumEraserAmount(AecNumberUtils.decimalFormat(baseSumEraserAmount, "###,###.##"));

			// 入金データ(中段)
			frm.setCreditList(beanList);

			// 請求明細データ(下段)検索
			List<ClaimEraserSales> salesList
			= eraserDetailLogic.getSalesData(frm.getEraserNo()
								, frm.getSectionCd()
								, frm.getVenderCd()
								, AecTextUtils.likeFilter(frm.getTantoCd())
								, AecTextUtils.likeFilter(frm.getTantoNm()));

			frm.setSalesList(salesList);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * 消込入力詳細　登録処理
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

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		EraserDetailForm frm = (EraserDetailForm) form;

		try {
			// 消込ヘッダーデータ作成
			ClaimEraserHeader headerBean = setEraserHeaderData(request, frm);

			// 請求ヘッダーデータ再設定(消込合計、消込残合計)
			setClaimHeaderData(frm);

			// 登録処理実行
			eraserDetailLogic.insert(headerBean, frm.getCreditList(), frm.getSalesList());
		} catch (NoDataException e) {
			saveError(request, "errors.eraser.update");
			return mapping.getInputForward();
		}

		frm.setInsertFlg(INSERT_FLG_UPD);	// 変更

		/* メッセージ */
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("back");

	}

	/**
	 * 消込入力詳細　更新処理
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

		EraserDetailForm frm = (EraserDetailForm) form;

		try {
			// 消込ヘッダーデータ作成
			ClaimEraserHeader headerBean = setEraserHeaderData(request, frm);

			// 請求ヘッダーデータ再設定(消込合計、消込残合計)
			setClaimHeaderData(frm);

			// 更新処理実行
			eraserDetailLogic.update(headerBean, frm.getCreditList(), frm.getSalesList());

		} catch (NoDataException e) {
			saveError(request, "errors.eraser.update");
			return mapping.getInputForward();
		}

		frm.setInsertFlg(INSERT_FLG_UPD);	// 変更

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");

	}

	/**
	 * 消込入力詳細 削除処理
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

		EraserDetailForm frm = (EraserDetailForm) form;

		try {
			// 消込ヘッダーデータ作成
			ClaimEraserHeader headerBean = setEraserHeaderData(request, frm);

			// 請求ヘッダーデータ再設定(消込合計、消込残合計)
			setClaimHeaderData(frm);

			// 削除処理実行
			eraserDetailLogic.delete(headerBean, frm.getCreditList(), frm.getSalesList());

		} catch (NoDataException e) {
			saveError(request, "errors.eraser.update");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");

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
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 登録／更新用の消込ヘッダーデータを設定する。
	 * @param  request    request
	 * @param  frm        消込入力Formデータ
	 * @return headerBean 消込ヘッダーデータ
	 * @throws NoDataException 消込番号取得失敗の例外
	 */
	private ClaimEraserHeader setEraserHeaderData(final HttpServletRequest request, final EraserDetailForm frm)
												throws NoDataException {
		Timestamp currentTime = AecDateUtils.getCurrentTimestamp();
		Date eraserDate = new Date(currentTime.getTime());
		String tantoCd = getLoginInfo(request).getTantoCd();

		// 消込ヘッダーデータ作成
		ClaimEraserHeader headerBean = new ClaimEraserHeader();
		if (frm.getInsertFlg() == INSERT_FLG_UPD) {
			headerBean.setEraserNo(frm.getEraserNo());			// 消込番号
		}
		headerBean.setSectionCd(frm.getSectionCd());			// 部門コード
		headerBean.setVenderCd(frm.getVenderCd());				// 請求先コード
		headerBean.setApprovalStatus(new BigDecimal(0));		// 承認ステータス
		headerBean.setInputDate(currentTime);					// 登録日時
		headerBean.setInputorCd(tantoCd);						// 登録者ＩＤ
		headerBean.setEraserDate(eraserDate);					// 消込日付
		headerBean.setEraserAmount(
			AecNumberUtils.convertBigDecimal(frm.getNewEraserAmount()));	// 消込額(再計算値)
		headerBean.setUpdateDate(currentTime);					// 更新日時
		headerBean.setUpdatorCd(tantoCd);						// 更新者ＩＤ

		return headerBean;
	}

	/**
	 * 登録／更新用の請求ヘッダーデータを画面の値で再設定する。
	 * (請求消込合計、消込残合計、入金消込合計、入金消込残合計)
	 * 
	 * @param  frm  消込入力Formデータ
	 */
	private void setClaimHeaderData(final EraserDetailForm frm) {
		List<ClaimEraserDetail> list = frm.getCreditList();

		for (int i = 0; i < list.size(); i++) {
			ClaimEraserDetail bean = list.get(i);
			// 請求ﾍｯﾀﾞｰ.消込額
			bean.setEraserAmount(AecNumberUtils.convertBigDecimal(frm.getNewEraserAmount()));
			bean.setStrEraserAmount(frm.getNewEraserAmount());
			// 請求ﾍｯﾀﾞｰ.消込残
			bean.setEraserBalanceAmount(AecNumberUtils.convertBigDecimal(frm.getNewEraserBalanceAmount()));
			bean.setStrEraserBalanceAmount(frm.getNewEraserBalanceAmount());
			// 入金ﾄﾗﾝ.消込額合計
			bean.setSumEraserAmount(AecNumberUtils.convertBigDecimal(frm.getNewSumEraserAmount()));
			bean.setStrSumEraserAmount(frm.getNewSumEraserAmount());
			// 入金ﾄﾗﾝ.消込額合計
			bean.setSumCreditEraserAmount(AecNumberUtils.convertBigDecimal(frm.getSumCreditEraserAmount()));
			bean.setStrSumCreditEraserAmount(frm.getNewSumCreditEraserAmount());
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * eraserDetailLogicを設定します。
	 * @param eraserDetailLogic EraserDetailLogic
	 */
	public void setEraserDetailLogic(final EraserDetailLogic eraserDetailLogic) {
		this.eraserDetailLogic = eraserDetailLogic;
	}

}
