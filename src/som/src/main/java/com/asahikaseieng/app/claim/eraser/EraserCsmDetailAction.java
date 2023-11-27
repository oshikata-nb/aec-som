/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.erasercsm.EraserCsm;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsm;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 消込入力詳細 Actionクラス(カスタマイズ)
 * @author tosco
 */
public final class EraserCsmDetailAction extends AbstractAction {

	/** 新規用切替フラグ 登録 */
	private static final int INSERT_FLG_INS = 1;

	/** 新規用切替フラグ 更新 */
	private static final int INSERT_FLG_UPD = 0;

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	private EraserCsmDetailLogic eraserCsmDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public EraserCsmDetailAction() {
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

		EraserCsmDetailForm frm = (EraserCsmDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ERASER,
			Constants.TAB_ID_ERASER_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		try {
			if (frm.getApprovalStatus().equals(new BigDecimal("1"))) {
				frm.setInsertFlg(INSERT_FLG_INS); // 新規
			} else {
				frm.setInsertFlg(INSERT_FLG_UPD); // 変更
			}

			// 消込・請求・入金データ(上段・中段)検索
			List<ClaimEraserDetail> beanList = eraserCsmDetailLogic
					.getDetailData(frm.getOrganizationCd(), frm
							.getOrganizationName(), frm.getVenderCd(), frm
							.getTantoCd(), frm.getEraserCompleteDateFrom(), frm
							.getEraserCompleteDateTo(), frm.getKbn(), frm
							.getApprovalStatus(), frm.getClaimNo());

			// BeanをFormにコピー
			// 消込・請求ヘッダデータ(上段)
			ClaimEraserDetail bean = beanList.get(0);
			frm.setOrganizationCd(bean.getOrganizationCd()); // 部署コード
			frm.setOrganizationName(bean.getOrganizationName()); // 部署名称
			frm.setEraserDate(bean.getEraserDate()); // 消込日付
			frm.setStrEraserDate(bean.getStrEraserDate()); // 消込日付
			frm.setVenderCd(bean.getVenderCd()); // 請求先
			frm.setVenderName1(bean.getVenderName1()); // 請求先名称
			frm.setVenderShortedName(bean.getVenderShortedName()); // 請求先名称
			// 入金消込残合計
			frm.setNewEraserAmount(bean.getStrEraserAmount()); // 消込額(再計算値)
			frm.setNewEraserBalanceAmount(bean.getStrEraserBalanceAmount()); // 消込残合計(再計算値)
			frm.setNewSumEraserAmount(bean.getStrSumEraserAmount()); // 消込額合計(再計算値)
			frm.setNewSumCreditEraserAmount(bean.getStrSumCreditEraserAmount()); // 入金消込残合計(再計算値)

			// 指定した請求番号の消込額を除いた消込合計
			BigDecimal baseSumEraserAmount = new BigDecimal(0);
			if (bean.getSumEraserAmount() != null) {
				baseSumEraserAmount = bean.getSumEraserAmount().subtract(
					bean.getEraserAmount());
			}

			/* 数値文字列に変換 */
			frm.setEraserCapaAmount(checker.format(URKINGAKU, bean
					.getEraserCapaAmount())); /* 消込可能額 */
			frm
					.setEraserAmount(checker.format(KINGAKU, bean
							.getEraserAmount())); /* 消込額 */
			frm.setEraserBalanceAmount(checker.format(URKINGAKU, bean
					.getEraserBalanceAmount())); /* 消込残合計 */
			frm.setSumCreditAmount(checker.format(KINGAKU, bean
					.getSumCreditAmount())); /* 入金金額合計 */
			frm.setSumEraserAmount(checker.format(KINGAKU, bean
					.getSumEraserAmount())); /* 消込額合計 */
			frm.setSumCreditEraserAmount(checker.format(URKINGAKU, bean
					.getSumCreditEraserAmount())); /* 入金消込残合計 */
			frm.setBaseSumEraserAmount(checker.format(URKINGAKU,
				baseSumEraserAmount));

			// 入金データ(中段)
			frm.setCreditList(beanList);

			for (int i = 0; i < frm.getCreditList().size(); i++) {
				frm.getCreditList().get(i).setStrCreditAmount(
					checker.format(KINGAKU, frm.getCreditList().get(i)
							.getCreditAmount())); /* 入金額 */
				frm.getCreditList().get(i).setStrCreditEraserAmount(
					checker.format(URKINGAKU, frm.getCreditList().get(i)
							.getCreditEraserAmount())); /* 入金消込額 */
			}

			// 請求明細データ(下段)検索
			List<ClaimEraserCsm> eraserCsmList = eraserCsmDetailLogic
					.getEraserCsmData(frm.getOrganizationCd(), frm
							.getOrganizationName(), frm.getVenderCd(), frm
							.getTantoCd(), frm.getEraserCompleteDateFrom(), frm
							.getEraserCompleteDateTo(), frm.getKbn(), frm
							.getApprovalStatus(), frm.getClaimNo());

			frm.setEraserCsmList(eraserCsmList);

			for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
				/* 数値文字列に変換 */
				frm.getEraserCsmList().get(i).setStrEraserObjectAmount(
					checker.format(URKINGAKU, frm.getEraserCsmList().get(i)
							.getEraserObjectAmount()));
				frm.getEraserCsmList().get(i).setStrEraserAmount(
					checker.format(KINGAKU, frm.getEraserCsmList().get(i)
							.getEraserAmount()));
				frm.getEraserCsmList().get(i).setStrLastEraserAmount(
					checker.format(KINGAKU, frm.getEraserCsmList().get(i)
							.getLastEraserAmount()));
			}
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * 消込入力詳細 更新処理
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

		EraserCsmDetailForm frm = (EraserCsmDetailForm) form;

		try {
			// 消込トラン(Csm)データ作成
			ClaimEraserCsm eraserBean = setEraserCsmData(request, frm);

			// 上段の各合計値再設定(消込合計、消込残合計)
			setHeaderData(frm);

			// 更新処理実行
			eraserCsmDetailLogic.update(eraserBean, frm.getCreditList(), frm
					.getEraserCsmList());
		} catch (NoDataException e) {
			saveError(request, "errors.eraser.update");
			return mapping.getInputForward();
		} catch (SQLException e) {
			saveError(request, "errors.eraser.no.credit");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		/* 検索画面に戻らないとデータがおかしくなる */
		/* 戻らないままにするなら登録後データの再読込が必要 */
		// return mapping.findForward("success");
		/* 登録後検索画面に戻る */
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
	 * 登録／更新用の消込トラン(Csm)データを設定する。
	 * @param request request
	 * @param frm 消込入力Formデータ
	 * @return eraserBean 消込トラン(Csm)データ
	 * @throws NoDataException 消込番号取得失敗の例外
	 */
	private ClaimEraserCsm setEraserCsmData(final HttpServletRequest request,
			final EraserCsmDetailForm frm) throws NoDataException {
		Timestamp currentTime = AecDateUtils.getCurrentTimestamp();
		String tantoCd = getLoginInfo(request).getTantoCd();

		// トラン(Csm)データ作成
		ClaimEraserCsm eraserBean = new ClaimEraserCsm();
		eraserBean.setOrganizationCd(frm.getOrganizationCd()); // 部署コード
		eraserBean.setInvoiceCd(frm.getVenderCd()); // 請求先コード
		eraserBean.setEraserDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrEraserDate())); // 消込日付
		eraserBean.setInputDate(currentTime); // 登録日時
		eraserBean.setInputorCd(tantoCd); // 登録者ＩＤ
		eraserBean.setEraserCompleteDate(new Date(AecDateUtils
				.getTimestampYmdFormat(frm.getStrEraserDate()).getTime())); // 消込完了日付
		eraserBean.setEraserAmount(AecNumberUtils.convertBigDecimal(frm
				.getNewEraserAmount())); // 消込額(再計算値)
		eraserBean.setUpdateDate(currentTime); // 更新日時
		eraserBean.setUpdatorCd(tantoCd); // 更新者ＩＤ

		return eraserBean;
	}

	/**
	 * 登録／更新用の上段の各合計値を画面の値で再設定する。 (請求消込合計、消込残合計、入金消込合計、入金消込残合計)
	 * 
	 * @param frm 消込入力Formデータ
	 */
	private void setHeaderData(final EraserCsmDetailForm frm) {
		List<ClaimEraserDetail> list = frm.getCreditList();

		for (int i = 0; i < list.size(); i++) {
			ClaimEraserDetail bean = list.get(i);
			// 請求ﾍｯﾀﾞｰ.消込額
			bean.setEraserAmount(AecNumberUtils.convertBigDecimal(frm
					.getNewEraserAmount()));
			bean.setStrEraserAmount(frm.getNewEraserAmount());
			// 請求ﾍｯﾀﾞｰ.消込残
			bean.setEraserBalanceAmount(AecNumberUtils.convertBigDecimal(frm
					.getNewEraserBalanceAmount()));
			bean.setStrEraserBalanceAmount(frm.getNewEraserBalanceAmount());
			// 入金ﾄﾗﾝ.消込額合計
			bean.setSumEraserAmount(AecNumberUtils.convertBigDecimal(frm
					.getNewSumEraserAmount()));
			bean.setStrSumEraserAmount(frm.getNewSumEraserAmount());
			// 入金ﾄﾗﾝ.消込額合計
			bean.setSumCreditEraserAmount(AecNumberUtils.convertBigDecimal(frm
					.getSumCreditEraserAmount()));
			bean.setStrSumCreditEraserAmount(frm.getNewSumCreditEraserAmount());
		}
	}

	/**
	 * 承認依頼.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalRequest(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalRequest.");
		}

		EraserCsmDetailForm frm = (EraserCsmDetailForm) form;

		/* 承認依頼中 */
		BigDecimal status = new BigDecimal("2");

		/* ステータス更新 */
		eraserCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		EraserCsm bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList()
				.get(0).getDataType(), frm.getEraserCsmList().get(0)
				.getSlipNo());

		frm.setEraserDate(bean.getEraserDate());
		frm.setStrEraserDate(AecDateUtils.dateFormat(bean.getEraserDate(),
			"yyyy/MM/dd"));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList().get(i)
					.getDataType(), frm.getEraserCsmList().get(i).getSlipNo());

			frm.getEraserCsmList().get(i).setEraserAmount(
				bean.getEraserAmount());
			frm.getEraserCsmList().get(i).setApprovalStatus(status);
			frm.getEraserCsmList().get(i).setApprovalDate(null);

			/* 数値文字列に変換 */
			frm.getEraserCsmList().get(i).setStrEraserAmount(
				checker.format(KINGAKU, frm.getEraserCsmList().get(i)
						.getEraserAmount()));
		}

		frm.setKbn(status.toString());
		frm.setInsertFlg(INSERT_FLG_UPD);
		frm.setApprovalStatus(status);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			frm.getEraserCsmList().get(i).setCheckKbn("2");
			frm.getEraserCsmList().get(i).setCheckFlg(true);
			frm.getEraserCsmList().get(i).setApprovalStatus(status);
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 承認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approval.");
		}

		EraserCsmDetailForm frm = (EraserCsmDetailForm) form;

		/* 承認 */
		BigDecimal status = new BigDecimal("3");

		/* ステータス更新 */
		eraserCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		EraserCsm bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList()
				.get(0).getDataType(), frm.getEraserCsmList().get(0)
				.getSlipNo());

		frm.setEraserDate(bean.getEraserDate());
		frm.setStrEraserDate(AecDateUtils.dateFormat(bean.getEraserDate(),
			"yyyy/MM/dd"));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList().get(i)
					.getDataType(), frm.getEraserCsmList().get(i).getSlipNo());

			frm.getEraserCsmList().get(i).setEraserAmount(
				bean.getEraserAmount());
			frm.getEraserCsmList().get(i).setApprovalStatus(status);
			frm.getEraserCsmList().get(i).setApprovalDate(
				AecDateUtils.getCurrentTimestamp());

			/* 数値文字列に変換 */
			frm.getEraserCsmList().get(i).setStrEraserAmount(
				checker.format(KINGAKU, frm.getEraserCsmList().get(i)
						.getEraserAmount()));
		}

		frm.setKbn(status.toString());
		frm.setInsertFlg(INSERT_FLG_UPD);
		frm.setApprovalStatus(status);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			frm.getEraserCsmList().get(i).setCheckKbn("2");
			frm.getEraserCsmList().get(i).setCheckFlg(true);
			frm.getEraserCsmList().get(i).setApprovalStatus(status);
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 否認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward negation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("negation.");
		}

		EraserCsmDetailForm frm = (EraserCsmDetailForm) form;

		/* 否認 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		eraserCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		EraserCsm bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList()
				.get(0).getDataType(), frm.getEraserCsmList().get(0)
				.getSlipNo());

		frm.setEraserDate(bean.getEraserDate());
		frm.setStrEraserDate(AecDateUtils.dateFormat(bean.getEraserDate(),
			"yyyy/MM/dd"));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList().get(i)
					.getDataType(), frm.getEraserCsmList().get(i).getSlipNo());

			frm.getEraserCsmList().get(i).setEraserAmount(
				bean.getEraserAmount());
			frm.getEraserCsmList().get(i).setApprovalStatus(status);
			frm.getEraserCsmList().get(i).setApprovalDate(null);

			/* 数値文字列に変換 */
			frm.getEraserCsmList().get(i).setStrEraserAmount(
				checker.format(KINGAKU, frm.getEraserCsmList().get(i)
						.getEraserAmount()));
		}

		frm.setKbn(status.toString());
		frm.setInsertFlg(INSERT_FLG_INS);
		frm.setApprovalStatus(status);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			if (AecNumberUtils.convertBigDecimal(
				frm.getEraserCsmList().get(i).getStrEraserAmount()).equals(
				new BigDecimal("0"))) {
				frm.getEraserCsmList().get(i).setCheckKbn("0");
				frm.getEraserCsmList().get(i).setCheckFlg(false);
			} else {
				frm.getEraserCsmList().get(i).setCheckKbn("1");
				frm.getEraserCsmList().get(i).setCheckFlg(true);
			}

			frm.getEraserCsmList().get(i).setApprovalStatus(status);
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 承認取消.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalCancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalCancel.");
		}

		EraserCsmDetailForm frm = (EraserCsmDetailForm) form;

		/* 承認取消 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		eraserCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		EraserCsm bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList()
				.get(0).getDataType(), frm.getEraserCsmList().get(0)
				.getSlipNo());

		frm.setEraserDate(bean.getEraserDate());
		frm.setStrEraserDate(AecDateUtils.dateFormat(bean.getEraserDate(),
			"yyyy/MM/dd"));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			bean = eraserCsmDetailLogic.getEntity(frm.getEraserCsmList().get(i)
					.getDataType(), frm.getEraserCsmList().get(i).getSlipNo());

			frm.getEraserCsmList().get(i).setEraserAmount(
				bean.getEraserAmount());
			frm.getEraserCsmList().get(i).setApprovalStatus(status);
			frm.getEraserCsmList().get(i).setApprovalDate(null);

			/* 数値文字列に変換 */
			frm.getEraserCsmList().get(i).setStrEraserAmount(
				checker.format(KINGAKU, frm.getEraserCsmList().get(i)
						.getEraserAmount()));
		}

		frm.setKbn(status.toString());
		frm.setInsertFlg(INSERT_FLG_INS);
		frm.setApprovalStatus(status);

		for (int i = 0; i < frm.getEraserCsmList().size(); i++) {
			if (AecNumberUtils.convertBigDecimal(
				frm.getEraserCsmList().get(i).getStrEraserAmount()).equals(
				new BigDecimal("0"))) {
				frm.getEraserCsmList().get(i).setCheckKbn("0");
				frm.getEraserCsmList().get(i).setCheckFlg(false);
			} else {
				frm.getEraserCsmList().get(i).setCheckKbn("1");
				frm.getEraserCsmList().get(i).setCheckFlg(true);
			}

			frm.getEraserCsmList().get(i).setApprovalStatus(status);
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * eraserCsmDetailLogicを設定します。
	 * @param eraserCsmDetailLogic EraserCsmDetailLogic
	 */
	public void setEraserCsmDetailLogic(
			final EraserCsmDetailLogic eraserCsmDetailLogic) {
		this.eraserCsmDetailLogic = eraserCsmDetailLogic;
	}
}
