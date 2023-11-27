/*
 * Created on 2008/10/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.master.company.CompanyDetailLogic;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 売掛ロールバック処理 Actionクラス.(カスタマイズ)
 * @author tosco
 */
public class ArRollbackCsmAction extends AbstractAction {

	/** 月末 */
	private static final BigDecimal MONTH_END = new BigDecimal(99);

	private ArRollbackCsmLogic arRollbackCsmLogic;

	private CompanyDetailLogic companyDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public ArRollbackCsmAction() {
		super();
	}

	/**
	 * 売掛ロールバック処理画面表示処理
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

		ArRollbackCsmForm frm = (ArRollbackCsmForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_AR_ROLLBACK,
			Constants.TAB_ID_AR_ROLLBACK_DETAIL);

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
			// 部署名称取得
			frm.setOrganizationName(loginInfo.getOrganizationName());
			// 担当者コード設定
			frm.setTantoCd(loginInfo.getTantoCd());
		}

		// 締め年月取得
		ArRollback bean = arRollbackCsmLogic.getSearch(frm.getOrganizationCd());
		frm.setClosingMonth(bean.getClosingMonth());

		return mapping.findForward("success");
	}

	/**
	 * 売掛ロールバック処理 更新処理
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
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		ArRollbackCsmForm frm = (ArRollbackCsmForm) form;

		/* 部署コード存在チェック */
		if (StringUtils.isNotEmpty(frm.getOrganizationCd())) {
			if (arRollbackCsmLogic.getOrganization(frm.getOrganizationCd()) == null) {
				saveError(request, "errors.nodata.master", rb
						.getString("item.arupdate.organization.cd"));
				return mapping.getInputForward();
			}
		}

		/* 締め日取得 */
		BigDecimal closingDay = getClosingDay(request);

		/* 売掛締め日設定 */
		frm.setCreditDate(getCreditDate(frm, closingDay));

		String organizationCd = frm.getOrganizationCd(); /* 部署コード */

		/* 部署コードが未入力の時はレコードチェックしない */
		if (StringUtils.isNotEmpty(organizationCd)) {
			/* 売掛ヘッダー(ロールバック対象)存在チェック */
			if (!checkRollbackExist(frm)) {
				/* 存在しない場合エラー */
				saveError(request, "errors.arrollback.finished");
				return mapping.getInputForward();
			}

			/* 売掛ヘッダー(未来締めデータ)存在チェック */
			if (checkExist(frm)) {
				/* 存在する場合、処理済みエラー */
				saveError(request, "errors.arrollback.finished");
				return mapping.getInputForward();
			}

			// 消込データ存在チェック
			// if (checkEraserExist(frm)) {
			// // 存在する場合、消込済みエラー
			// saveError(request, "errors.arrollback.eraser");
			// return mapping.getInputForward();
			// }

			/* グループ間相殺データ存在チェック */
			if (checkOffsetExist(frm)) {
				/* 存在する場合、グループ間相殺データ存在エラー */
				saveError(request, "errors.arrollback.offset");
				return mapping.getInputForward();
			}
		}

		/* 売掛ロールバック処理(PL/SQL)呼出 */
		ProArRollbackCallDto dto = arRollbackCsmLogic.setProcedureDto(frm);
		BigDecimal rtn = arRollbackCsmLogic.callProcedure(dto);
		if (rtn == null || rtn.compareTo(new BigDecimal(0)) != 0) {
			/* ロールバックエラー */
			saveError(request, "errors.arrollback.update");
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
	 * 売掛締め日(画面締め年月＋自社マスタ.締め日)取得
	 * 
	 * @param frm 売掛ロールバックForm
	 * @param closingDay 自社マスタ.売掛締め日
	 * @return Date 売掛締め日
	 */
	private Date getCreditDate(final ArRollbackCsmForm frm,
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
		private Date getOffsetDate(final ArRollbackCsmForm frm,boolean FromTo) {
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
	 * 売掛ヘッダー(ロールバック対象)存在チェック 売掛ヘッダー.売掛締め日＝画面締め年月＋自社マスタ.締め日のデータ存在チェックを行う。
	 * 
	 * @param frm 売掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkRollbackExist(final ArRollbackCsmForm frm) {
		boolean res = false;

		// 売掛ヘッダー検索処理
		List<ArRollback> list = arRollbackCsmLogic.getSearchRbList(frm
				.getOrganizationCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 売掛ヘッダー(未来締めデータ)存在チェック 画面締め年月＋自社マスタ.締め日より後に売掛更新しているデータの存在チェックを行う。
	 * 
	 * @param frm 売掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkExist(final ArRollbackCsmForm frm) {
		boolean res = false;

		// 売掛ヘッダー検索処理
		List<ArRollback> list = arRollbackCsmLogic.getSearchList(frm
				.getOrganizationCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 消込データ存在チェック 売上・入金データで消込されているデータの存在チェックを行う。
	 * 
	 * @param frm 売掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	// private boolean checkEraserExist(final ArRollbackCsmForm frm) {
	// boolean res = false;
	//
	// // 消込データ検索処理
	// List<ArRollback> list = arRollbackCsmLogic.getSearchEraserList(frm
	// .getOrganizationCd(), frm.getCreditDate());
	// if (list.size() > 0) {
	// res = true;
	// }
	//
	// return res;
	// }
	/**
	 * グループ間相殺データ存在チェック グループ間相殺データで締め処理が未処理のデータの存在チェックを行う。
	 * 
	 * @param frm 売掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkOffsetExist(final ArRollbackCsmForm frm) {
		boolean res = false;

		// グループ間相殺データ検索処理
		//List<ArRollback> list = arRollbackCsmLogic.getSearchOffsetList(frm.getOrganizationCd(), frm.getCreditDate());
		List<ArRollback> list = arRollbackCsmLogic.getSearchOffsetList(frm.getOrganizationCd(), getOffsetDate(frm,true),getOffsetDate(frm,false));
		System.out.println("csmlistsize:"+list.size()+"check"+getOffsetDate(frm,true)+":"+getOffsetDate(frm,false));
		System.out.println( org.seasar.extension.jdbc.SqlLogRegistryLocator.getInstance().getLast().getCompleteSql() );
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * arRollbackCsmLogicを設定します。
	 * @param arRollbackCsmLogic ArRollbackCsmLogic
	 */
	public void setArRollbackCsmLogic(
			final ArRollbackCsmLogic arRollbackCsmLogic) {
		this.arRollbackCsmLogic = arRollbackCsmLogic;
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
