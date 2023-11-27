/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.aprollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.master.company.CompanyDetailLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.ProApRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 買掛ロールバック処理 Actionクラス.
 * @author tosco
 */
public class ApRollbackAction extends AbstractAction {

	/** 月末 */
	private static final BigDecimal MONTH_END = new BigDecimal(99);

	private OrganizationDetailLogic organizationDetailLogic;

	private ApRollbackLogic apRollbackLogic;

	private CompanyDetailLogic companyDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public ApRollbackAction() {
		super();
	}

	/**
	 * 買掛ロールバック処理画面表示処理
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

		ApRollbackForm frm = (ApRollbackForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_AP_ROLLBACK,
			Constants.TAB_ID_AP_ROLLBACK_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));
			// 部署コード設定
			frm.setOrganizationCd(loginInfo.getOrganizationCd());
			// 担当者コード設定
			frm.setTantoCd(loginInfo.getTantoCd());

			// 部署名称取得
			OrganizationDetail bumonBean = organizationDetailLogic
					.getDetailEntity(frm.getOrganizationCd());
			if (bumonBean != null) {
				frm.setOrganizationName(bumonBean.getOrganizationName());
			}
		}

		// 締め年月取得
		ApRollback bean = apRollbackLogic.getSearch(frm.getOrganizationCd());
		frm.setClosingMonth(bean.getClosingMonth());

		return mapping.findForward("success");
	}

	/**
	 * 買掛ロールバック処理 更新処理
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

		ApRollbackForm frm = (ApRollbackForm) form;

		/* 締め日取得 */
		BigDecimal closingDay = getClosingDay(request);

		/* 買掛締め日設定 */
		frm.setPayableDate(getPayableDate(frm, closingDay));

		String organizationCd = frm.getOrganizationCd(); /* 部署コード */

		/* 部署コードが未入力の時はレコードチェックしない */
		if (StringUtils.isNotEmpty(organizationCd)) {
			/* 買掛ヘッダー(ロールバック対象)存在チェック */
			if (!checkRollbackExist(frm)) {
				/* 存在しない場合エラー */
				saveError(request, "errors.aprollback.finished");
				return mapping.getInputForward();
			}

			/* 買掛ヘッダー(未来締めデータ)存在チェック */
			if (checkExist(frm)) {
				/* 存在する場合、処理済みエラー */
				saveError(request, "errors.aprollback.finished");
				return mapping.getInputForward();
			}

			// 摂津版では不要のため
			// // 消込データ存在チェック
			// if (checkEraserExist(frm)) {
			// // 存在する場合、消込済みエラー
			// saveError(request, "errors.aprollback.eraser");
			// return mapping.getInputForward();
			// }

			/* グループ間相殺データ存在チェック */
			if (checkOffsetExist(frm)) {
				/* 存在する場合、グループ間相殺データ存在エラー */
				saveError(request, "errors.aprollback.offset");
				return mapping.getInputForward();
			}
		}

		/* 買掛ロールバック処理(PL/SQL)呼出 */
		ProApRollbackCallDto dto = apRollbackLogic.setProcedureDto(frm);
		BigDecimal rtn = apRollbackLogic.callProcedure(dto);
		if (rtn == null || rtn.compareTo(new BigDecimal("0")) != 0) {
			/* ロールバックエラー */
			saveError(request, "errors.aprollback.update");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");
	}

	/**
	 * 自社マスタの締め日取得
	 * 
	 * @param request HttpServletRequest
	 * @return BigDecimal 締め日
	 * @exception NoDataException
	 */
	private BigDecimal getClosingDay(final HttpServletRequest request)
			throws NoDataException {
		// セッションから自社コード取得

		HttpSession session = request.getSession(false);
		if (session == null) {
			throw new NoDataException();
		}
		PasswordCheck chk = (PasswordCheck) (session
				.getAttribute(Constants.COMPANY_INFO_KEY));

		// 自社マスタから締め日取得
		Company bean = companyDetailLogic.getEntity(chk.getCompanyCd());
		return bean.getClosingDay();
	}

	/**
	 * 買掛締め日(画面締め年月＋自社マスタ.締め日)取得
	 * 
	 * @param frm 買掛ロールバックForm
	 * @param closingDay 自社マスタ.買掛締め日
	 * @return Date 買掛締め日
	 */
	private Date getPayableDate(final ApRollbackForm frm,
			final BigDecimal closingDay) {
		// スラッシュ分割
		String[] ym = frm.getClosingMonth().split("/");
		int year = Integer.parseInt(ym[0]);
		int month = Integer.parseInt(ym[1]);

		Calendar cal = Calendar.getInstance();

		// 締め日が月末(99)指定の場合
		if (closingDay.compareTo(MONTH_END) == 0) {
			cal.set(year, month - 1, 1);
			// 画面締め年月の月末日取得

			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		} else {
			// 画面締め年月＋自社マスタ.締め日
			cal.set(year, month - 1, closingDay.intValue());
		}

		// 自社マスタ.締め日を指定すると月が変わってしまう場合
		if (month - 1 != cal.get(Calendar.MONTH)) {
			cal.set(year, month - 1, 1);
			// 画面締め年月の月末日取得
			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		}

		Date creditDate = new Date(cal.getTimeInMillis());

		return creditDate;
	}
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	/**
	 * 指定された月の開始日終了日を取得する。
	 * 
	 * @param frm 売掛ロールバックForm
	 * @param FromTo (From true To false)
	 * @return (From ~/~/1 To~/~/最終日)
	 */
	private Date getOffsetDate(final ApRollbackForm frm,boolean FromTo) {
		// スラッシュ分割
		String[] ym = frm.getClosingMonth().split("/");
		int year = Integer.parseInt(ym[0]);
		int month = Integer.parseInt(ym[1]);

		Calendar cal = Calendar.getInstance();

		// 相殺日付が月初(true)指定の場合
		if (FromTo) {
			// 月初指定
			cal.set(year, month - 1, 1);
		} else {
			// 月末指定
			cal.set(year, month - 1, 1);
			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		}

		Date creditDate = new Date(cal.getTimeInMillis());

		return creditDate;
	}
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了

	/**
	 * 買掛ヘッダー(ロールバック対象)存在チェック 買掛ヘッダー.買掛締め日＝画面締め年月＋自社マスタ.締め日のデータ存在チェックを行う。
	 * 
	 * @param frm 買掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkRollbackExist(final ApRollbackForm frm) {
		boolean res = false;

		// 買掛ヘッダー検索処理
		List<ApRollback> list = apRollbackLogic.getSearchRbList(frm
				.getOrganizationCd(), frm.getPayableDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 買掛ヘッダー(未来締めデータ)存在チェック 画面締め年月＋自社マスタ.締め日より後に買掛更新しているデータの存在チェックを行う。
	 * 
	 * @param frm 買掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkExist(final ApRollbackForm frm) {
		boolean res = false;

		// 買掛ヘッダー検索処理
		List<ApRollback> list = apRollbackLogic.getSearchList(frm
				.getOrganizationCd(), frm.getPayableDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 消込データ存在チェック 入金・支払・グループ間相殺データで消込されているデータの存在チェックを行う。
	 * 
	 * @param frm 買掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	// private boolean checkEraserExist(final ApRollbackForm frm) {
	// boolean res = false;
	//
	// // 消込データ検索処理
	// List<ApRollback> list = apRollbackLogic.getSearchEraserList(frm
	// .getOrganizationCd(), frm.getPayableDate());
	// if (list.size() > 0) {
	// res = true;
	// }
	//
	// return res;
	// }
	/**
	 * グループ間相殺データ存在チェック グループ間相殺データで締め処理が未処理のデータの存在チェックを行う。
	 * 
	 * @param frm 買掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkOffsetExist(final ApRollbackForm frm) {
		boolean res = false;

		// グループ間相殺データ検索処理
		//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
		//List<ApRollback> list = apRollbackLogic.getSearchOffsetList(frm.getOrganizationCd());
		List<ApRollback> list = apRollbackLogic.getSearchOffsetList(frm.getOrganizationCd(),getOffsetDate(frm,true),getOffsetDate(frm,false));
		//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了
		System.out.println("listsize:"+list.size()+"check"+getOffsetDate(frm,true)+":"+getOffsetDate(frm,false));
		System.out.println( org.seasar.extension.jdbc.SqlLogRegistryLocator.getInstance().getLast().getCompleteSql() );
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * organizationDetailLogicを設定します。
	 * @param organizationDetailLogic OrganizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * apRollbackLogicを設定します。
	 * @param apRollbackLogic ApRollbackLogic
	 */
	public void setApRollbackLogic(final ApRollbackLogic apRollbackLogic) {
		this.apRollbackLogic = apRollbackLogic;
	}

	/**
	 * companyDetailLogicを設定します。
	 * @param companyDetailLogic CompanyDetailLogic
	 */
	public void setCompanyDetailLogic(
			final CompanyDetailLogic companyDetailLogic) {
		this.companyDetailLogic = companyDetailLogic;
	}

}
