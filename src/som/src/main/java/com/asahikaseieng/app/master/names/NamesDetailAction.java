/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisit;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 名称詳細 Actionクラス.
 * @author t0011036
 */
public final class NamesDetailAction extends AbstractAction {

	private NamesDetailLogic namesDetailLogic;

	private static final String NAME_DIVISION_UNIT = "UNIT";

	private static final BigDecimal MAX_LENGTH = new BigDecimal("22");

	/**
	 * コンストラクタ.
	 */
	public NamesDetailAction() {
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

		NamesDetailForm frm = (NamesDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_NAMES,
			Constants.TAB_ID_NAMES_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		NamesDetail bean = namesDetailLogic.getDetailEntity(frm
				.getNameDivision(), frm.getNameCd());

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
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		NamesDetailForm frm = (NamesDetailForm) form;

		Names beanNames = new Names();
		NumberChkdisit beanNumber = null;

		if (frm.getUpdateDate() == null) {
			/* 追加用データ作成 */
			beanNames = insertNames(frm, getLoginInfo(request).getTantoCd());
		} else {
			/* 更新用データ作成 */
			beanNames = updateNames(frm, getLoginInfo(request).getTantoCd());
		}

		/* 名称区分が単位の場合は数値チェックデータも登録する */
		if (frm.getNameDivision().equals(NAME_DIVISION_UNIT)) {
			/* 数値チェック検索 */
			beanNumber = namesDetailLogic.getNumberEntity(frm.getNameCd(), " ",
				" ");

			if (beanNumber == null) {
				/* 追加用データ作成 */
				beanNumber = insertNumber(frm, getLoginInfo(request)
						.getTantoCd());
			} else {
				/* 更新用データ作成 */
				beanNumber = updateNumber(frm, getLoginInfo(request)
						.getTantoCd());
			}
		}

		/* 登録処理を実行 */
		namesDetailLogic.regist(beanNames, beanNumber);

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

		NamesDetailForm frm = (NamesDetailForm) form;

		Names beanNames = deleteNames(frm, getLoginInfo(request).getTantoCd());

		NumberChkdisit beanNumber = deleteNumber(frm, getLoginInfo(request)
				.getTantoCd());

		/* 削除処理を実行 */
		namesDetailLogic.delete(beanNames, beanNumber);

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のNamesを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Names
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Names updateNames(final NamesDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Names newBean = new Names();

		try {
			newBean = namesDetailLogic.getEntity(frm.getNameDivision(), frm
					.getNameCd());
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
	 * 更新処理用のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit updateNumber(final NamesDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = namesDetailLogic.getNumberEntity(frm
				.getNameCd(), " ", " ");

		if (newBean == null) {
			return null;
		}

		BigDecimal maxLength = newBean.getMaxLength();
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getQuantityRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getQuantityRoundupUnit());
			smallnumLength = frm.getQuantityRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getQuantityRoundup());
		newBean.setUpperLimit(upperLimit);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のNamesを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Names
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Names insertNames(final NamesDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Names newBean = new Names();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のNumberChkdisitを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 */
	private NumberChkdisit insertNumber(final NamesDetailForm frm,
			final String tantoCd) {
		NumberChkdisit newBean = new NumberChkdisit();

		BigDecimal maxLength = MAX_LENGTH;
		BigDecimal integerLength = BigDecimal.ZERO;
		BigDecimal smallnumLength = BigDecimal.ZERO;
		String strUpperLimit = "";
		BigDecimal upperLimit = BigDecimal.ZERO;

		if (frm.getQuantityRoundupUnit().equals(new BigDecimal("1"))) {
			/* 正数の場合 */
			integerLength = maxLength;
			smallnumLength = BigDecimal.ZERO;
		} else {
			integerLength = maxLength.subtract(frm.getQuantityRoundupUnit());
			smallnumLength = frm.getQuantityRoundupUnit().subtract(
				new BigDecimal("1"));
		}

		if (smallnumLength.equals(new BigDecimal("0"))) {
			/* 正数の場合 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		} else {
			/* 整数部 */
			for (int i = 0; i < integerLength.intValue(); i++) {
				strUpperLimit += "9";
			}

			strUpperLimit += ".";

			/* 小数部 */
			for (int i = 0; i < smallnumLength.intValue(); i++) {
				strUpperLimit += "9";
			}
		}

		upperLimit = AecNumberUtils.convertBigDecimal(strUpperLimit);

		newBean.setUnitDivision(frm.getNameCd());
		newBean.setVenderCd(" ");
		newBean.setVenderDivision(" ");
		newBean.setMaxLength(MAX_LENGTH);
		newBean.setIntegerLength(integerLength);
		newBean.setSmallnumLength(smallnumLength);
		newBean.setRoundDivision(frm.getQuantityRoundup());
		newBean.setLowerLimit(BigDecimal.ZERO);
		newBean.setUpperLimit(upperLimit);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のNamesを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Names
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Names deleteNames(final NamesDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Names newBean = new Names();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/**
	 * 削除処理用のNumberChkdisitを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return NumberChkdisit
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private NumberChkdisit deleteNumber(final NamesDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		NumberChkdisit newBean = namesDetailLogic.getNumberEntity(frm
				.getNameCd(), " ", " ");
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

		NamesDetailForm frm = (NamesDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_NAMES,
			Constants.TAB_ID_NAMES_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * namesDetailLogicを設定します。
	 * @param namesDetailLogic namesDetailLogic
	 */
	public void setNamesDetailLogic(final NamesDetailLogic namesDetailLogic) {
		this.namesDetailLogic = namesDetailLogic;
	}
}
