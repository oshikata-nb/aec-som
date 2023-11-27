/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 運送会社詳細 Actionクラス.
 * @author t0011036
 */
public final class CarryDetailAction extends AbstractAction {

	private CarryDetailLogic carryDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public CarryDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		CarryDetailForm frm = (CarryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_CARRY,
			Constants.TAB_ID_CARRY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		CarryDetail bean = carryDetailLogic.getDetailEntity(frm.getCarryCd());

	
		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* バーコード発行区分初期値の設定 */
		if( frm.getBcPublishDivision() != null )
		{
			frm.setBcPublishDivisionInit( frm.getBcPublishDivision());
		}else{
			frm.setBcPublishDivisionInit( BigDecimal.ZERO );
		}

		
		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列変換 */
		frm.setStrReportOutputNum(checker.format("SONOTA", frm
				.getReportOutputNum()));
		
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
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		CarryDetailForm frm = (CarryDetailForm) form;

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			carryDetailLogic.insert(insertCarry(frm, getLoginInfo(request)
					.getTantoCd(), request));
		} else {
			/* 更新処理を実行 */
			carryDetailLogic.update(updateCarry(frm, getLoginInfo(request)
					.getTantoCd(), request));
		}
		
		// バーコード用シーケンスの削除フラグチェック
		BigDecimal delFlg;
		
		if( frm.getBcPublishDivision().equals(BigDecimal.valueOf(1))  )
		{
			delFlg = BigDecimal.valueOf(0);
		}else
		{
			delFlg = BigDecimal.valueOf(1);
		}

		// バーコードシーケンス管理Procedureの呼び出し 
		carryDetailLogic.ctlCarryBCSeq(frm.getCarryCd(), frm.getBcNumStart(), frm.getBcNumEnd(), frm.getBcNumPresent(), delFlg);
		
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
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete");
		}

		CarryDetailForm frm = (CarryDetailForm) form;

		/* 削除処理を実行 */
		carryDetailLogic.delete(deleteCarry(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のCarryを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Carry
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Carry updateCarry(final CarryDetailForm frm, final String tantoCd,
			final HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		Carry newBean = new Carry();

		try {
			newBean = carryDetailLogic.getEntity(frm.getCarryCd());
		} catch (NoDataException e) {
			return null;
		}
		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		/* 数値変換 */
		frm.setReportOutputNum(check.round("SONOTA", null, null, strToNum(frm
				.getStrReportOutputNum())));

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 文字列を数値に変換
	 * @param value 変換元の文字列
	 * @return 変換後の数値
	 */
	private BigDecimal strToNum(final String value) {

		if (value.equals("") || value == null) {
			return null;
		}
		return AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(value));
	}

	/**
	 * 追加処理用のCarryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Carry
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Carry insertCarry(final CarryDetailForm frm, final String tantoCd,
			final HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		Carry newBean = new Carry();

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		/* 数値変換 */
		frm.setReportOutputNum(check.round("SONOTA", null, null, strToNum(frm
				.getStrReportOutputNum())));

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のCarryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Carry
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Carry deleteCarry(final CarryDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Carry newBean = new Carry();

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
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
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
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		CarryDetailForm frm = (CarryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_CARRY,
			Constants.TAB_ID_CARRY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();
		frm.setBcPublishDivisionInit( BigDecimal.ZERO );
		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * carryDetailLogicを設定します。
	 * @param carryDetailLogic carryDetailLogic
	 */
	public void setCarryDetailLogic(final CarryDetailLogic carryDetailLogic) {
		this.carryDetailLogic = carryDetailLogic;
	}
}
