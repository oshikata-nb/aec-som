/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.debt.aprollback.ApRollbackForm;
import com.asahikaseieng.app.master.company.CompanyDetailLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollback;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 売掛ロールバック処理 Actionクラス.
 * @author tosco
 */
public class ArRollbackAction extends AbstractAction {

	/** 月末 */
	private static final BigDecimal MONTH_END = new BigDecimal(99);

	private OrganizationDetailLogic organizationDetailLogic;

	private ArRollbackLogic arRollbackLogic;

	private CompanyDetailLogic companyDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public ArRollbackAction() {
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

		ArRollbackForm frm = (ArRollbackForm) form;

		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));
			// 部門コード設定
			frm.setSectionCd(loginInfo.getOrganizationCd());
			// 担当者コード設定
			frm.setTantoCd(loginInfo.getTantoCd());

			// 部門名称取得
			OrganizationDetail bumonBean = organizationDetailLogic
					.getDetailEntity(frm.getSectionCd());
			if (bumonBean != null) {
				frm.setSectionName(bumonBean.getOrganizationName());
			}
		}

		// 締め年月取得
		ArRollback bean = arRollbackLogic.getSearch(frm.getSectionCd());
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

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		ArRollbackForm frm = (ArRollbackForm) form;

		// 締め日取得
		BigDecimal closingDay = getClosingDay(request);

		// 売掛締め日設定
		frm.setCreditDate(getCreditDate(frm, closingDay));

		// 売掛ヘッダー(ロールバック対象)存在チェック
		if (!checkRollbackExist(frm)) {
			// 存在しない場合エラー
			saveError(request, "errors.arrollback.finished");
			return mapping.getInputForward();
		}

		// 売掛ヘッダー(未来締めデータ)存在チェック
		if (checkExist(frm)) {
			// 存在する場合、処理済みエラー
			saveError(request, "errors.arrollback.finished");
			return mapping.getInputForward();
		}

		// 消込データ存在チェック
		if (checkEraserExist(frm)) {
			// 存在する場合、消込済みエラー
			saveError(request, "errors.arrollback.eraser");
			return mapping.getInputForward();
		}

		// グループ間相殺データ存在チェック
		if (checkOffsetExist(frm)) {
			// 存在する場合、グループ間相殺データ存在エラー
			saveError(request, "errors.arrollback.offset");
			return mapping.getInputForward();
		}

		// 売掛ロールバック処理(PL/SQL)呼出
		ProArRollbackCallDto dto = arRollbackLogic.setProcedureDto(frm);
		BigDecimal rtn = arRollbackLogic.callProcedure(dto);
		if (rtn == null || rtn.compareTo(new BigDecimal(0)) != 0) {
			// ロールバックエラー
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
	private Date getCreditDate(final ArRollbackForm frm,
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
	private Date getOffsetDate(final ArRollbackForm frm,boolean FromTo) {
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
	private boolean checkRollbackExist(final ArRollbackForm frm) {
		boolean res = false;

		// 売掛ヘッダー検索処理
		List<ArRollback> list = arRollbackLogic.getSearchRbList(frm
				.getSectionCd(), frm.getCreditDate());
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
	private boolean checkExist(final ArRollbackForm frm) {
		boolean res = false;

		// 売掛ヘッダー検索処理
		List<ArRollback> list = arRollbackLogic.getSearchList(frm
				.getSectionCd(), frm.getCreditDate());
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
	private boolean checkEraserExist(final ArRollbackForm frm) {
		boolean res = false;

		// 消込データ検索処理
		List<ArRollback> list = arRollbackLogic.getSearchEraserList(frm
				.getSectionCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * グループ間相殺データ存在チェック グループ間相殺データで締め処理が未処理のデータの存在チェックを行う。
	 * 
	 * @param frm 売掛ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkOffsetExist(final ArRollbackForm frm) {
		boolean res = false;

		// グループ間相殺データ検索処理
		//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
		//List<ArRollback> list = arRollbackLogic.getSearchOffsetList(frm.getSectionCd(), frm.getCreditDate());
		List<ArRollback> list = arRollbackLogic.getSearchOffsetList(frm.getSectionCd(), getOffsetDate(frm,true),getOffsetDate(frm,false));
		System.out.println("listsize:"+list.size()+"check"+getOffsetDate(frm,true)+":"+getOffsetDate(frm,false));
		System.out.println( org.seasar.extension.jdbc.SqlLogRegistryLocator.getInstance().getLast().getCompleteSql() );
		//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了

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
	 * arRollbackLogicを設定します。
	 * @param arRollbackLogic ArRollbackLogic
	 */
	public void setArRollbackLogic(final ArRollbackLogic arRollbackLogic) {
		this.arRollbackLogic = arRollbackLogic;
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
