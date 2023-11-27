/*
 * Created on 2008/07/03
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.offset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.offsetgroup.OffsetGroupDetailLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.offsetgroupdetail.OffsetGroupDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetList;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 相殺処理 Actionクラス.
 * @author tosco
 */
public final class OffsetListAction extends AbstractSearchAction {

	private OffsetListLogic offsetListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private OffsetGroupDetailLogic offsetGroupDetailLogic;

	/** 仕訳伝票（相殺伝票） */
	private OffsetListExcelDecorator offsetListExcelDecorator;

	/** 分類マスタ.データ種別＝グループ間相殺(5) */
	public static final int CATEGORY_DATA_TYPE_OFFSET = 5;

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/**
	 * コンストラクタ.
	 */
	public OffsetListAction() {
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

		OffsetListForm frm = (OffsetListForm) form;

		/* 初期表示：部署 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部署コード */
			frm.setSrhOrganizationCd(loginInfo.getOrganizationCd());

			/* 部署マスタ検索 */
			OrganizationDetail bumonDate = organizationDetailLogic
					.getDetailEntity(frm.getSrhOrganizationCd());
			if (bumonDate != null) {
				/* 初期表示：部署名称 */
				frm.setSrhOrganizationName(bumonDate.getOrganizationName());
			} else {
				frm.setSrhOrganizationName("");
			}
		}

		/* 初期表示：分類コンボボックス */
		frm.setCategoryList(getClassificationList());
		/* 初期表示：相殺グループコンボボックス */
		frm.setOffsetGrpList(getOffsetGroupComboBox());

		frm.setSrhOutputDivision("0");

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

		OffsetListForm frm = (OffsetListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<OffsetList>());

		/* 検索条件を取得 */
		OffsetPagerCondition condition = (OffsetPagerCondition) frm.getPager()
				.getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhOrganizationName(frm.getSrhOrganizationName());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhTantoNm(frm.getSrhTantoNm());
		condition.setSrhOffsetGrp(frm.getSrhOffsetGrp());
		if ((frm.getSrhOffsetDateFrom() != null)
				&& (!frm.getSrhOffsetDateFrom().equals(""))) {
			// condition.setSrhOffsetDateFrom(getSqlDateFormat(frm
			// .getSrhOffsetDateFrom()));
			condition.setSrhOffsetDateFrom(frm.getSrhOffsetDateFrom());
		} else {
			condition.setSrhOffsetDateFrom(null);
		}
		if ((frm.getSrhOffsetDateTo() != null)
				&& (!frm.getSrhOffsetDateTo().equals(""))) {
			// condition.setSrhOffsetDateTo(getSqlDateFormat(frm
			// .getSrhOffsetDateTo()));
			condition.setSrhOffsetDateTo(frm.getSrhOffsetDateTo());
		} else {
			condition.setSrhOffsetDateTo(null);
		}
		condition.setSrhCassification(frm.getSrhCassification());
		condition.setSrhOutputDivision(frm.getSrhOutputDivision());

		/* 帳票(Excel)用に検索条件を保持 */
		OffsetListConditionForReport reportCondition = new OffsetListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		List<OffsetList> list = offsetListLogic.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			list.get(i).setStrOffsetAmount(
				checker.format(SIKINGAKU, list.get(i).getOffsetAmount())); /* 相殺金額 */
		}
		// setStrOffsetAmount(AecNumberUtils.decimalFormat(getOffsetAmount(),

		frm.setSearchList(list);

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
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

		OffsetListForm frm = (OffsetListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * 分類コンボボックスのリスト取得
	 * @return
	 * @throws NoDataException
	 */
	@SuppressWarnings("unchecked")
	private List<ComboBoxItems> getClassificationList() throws NoDataException {
		// 検索結果を格納用リスト
		List<ComboBoxItems> classList = new ArrayList<ComboBoxItems>();
		// 分類マスタ.データ種別＝グループ間相殺(5)のみ対象
		BigDecimal dataType = new BigDecimal(CATEGORY_DATA_TYPE_OFFSET);

		// コンボボックスの先頭行はすべてにする
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales("すべて");
		classList.add(comboBlank);

		// 分類マスタ検索
		List<ClassificationListForComboboxes> result = offsetListLogic
				.getClassificationList(dataType);

		/* 分類コード順のソート */
		Collections.sort(result, new ClassificationComparator(
				ClassificationComparator.ASC));

		for (ClassificationListForComboboxes bean : result) {
			String categoryDivi = bean.getCategoryDivision();
			ComboBoxItems combo = new ComboBoxItems();
			// 科目コード設定(Value値)
			combo.setValues(categoryDivi);
			// 科目名称をラベルとして設定
			combo.setLabales(bean.getCategoryName());
			classList.add(combo);
		}
		return classList;
	}

	/**
	 * 相殺グループコード＋相殺グループ名称のコンボボックス取得
	 * 
	 * @return List<ComboBoxItems> 相殺グループのコンボボックス
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	private List<ComboBoxItems> getOffsetGroupComboBox() throws NoDataException {

		List<ComboBoxItems> accountsList = new ArrayList<ComboBoxItems>();

		// 相殺グループ一覧取得
		List<OffsetGroupDetail> list = offsetGroupDetailLogic.getEntity("");

		// コンボボックスの先頭行はすべてにする
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales("すべて");
		accountsList.add(comboBlank);

		if (!list.isEmpty()) {
			// 同じコードは１度だけ
			String tmpOffsetGroupCd = "";
			// 相殺グループコード、相殺グループ名称を取得して配列に設定
			for (OffsetGroupDetail bean : list) {
				if (tmpOffsetGroupCd.equals(bean.getOffsetGroupCd())) {
					continue;
				} else {
					ComboBoxItems combo = new ComboBoxItems();
					// 相殺グループコード設定(Value値)
					combo.setValues(bean.getOffsetGroupCd());
					// 相殺グループ名称設定(ラベル)
					combo.setLabales(bean.getOffsetGroupName());
					accountsList.add(combo);
					tmpOffsetGroupCd = bean.getOffsetGroupCd();
				}
			}

		}

		return accountsList;
	}

	/**
	 * 日にちを正しい年月日に補正した値を取得
	 * 
	 * @param strDate 画面の年月日
	 * @return Date フォーマットした年月日
	 */
	// private Date getSqlDateFormat(final String strDate) {
	//
	// // スラッシュ分割
	// String[] ymd = strDate.split("/");
	// int year = Integer.parseInt(ymd[0]);
	// int month = Integer.parseInt(ymd[1]);
	// int day = Integer.parseInt(ymd[2]);
	//
	// Calendar cal = Calendar.getInstance();
	// cal.set(year, month - 1, day);
	//
	// // 月が変わってしまう場合
	// if (month - 1 != cal.get(Calendar.MONTH)) {
	// cal.set(year, month - 1, 1);
	// // 画面締め年月の月末日取得
	// int endDay = cal.getActualMaximum(Calendar.DATE);
	// cal.set(year, month - 1, endDay);
	// }
	//
	// return new Date(cal.getTimeInMillis());
	// }
	/**
	 * 印刷処理
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
		OffsetListForm frm = (OffsetListForm) form;

		ArrayList<String> offsetNoList = new ArrayList<String>();

		// ログインユーザー取得
		String tantoNm = getLoginInfo(request).getTantoNm();

		for (OffsetList bean : frm.getSearchList()) {

			// 処理を行う相殺番号リストを作成
			offsetNoList.add(bean.getOffsetNo());

		}

		if (!offsetNoList.isEmpty()) {

			FileDownloadInfo info = null;

			/* 仕訳伝票（相殺伝票）出力処理 */
			info = offsetListExcelDecorator.createReport(offsetNoList, tantoNm,
				null, request.getRemoteAddr());
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			// ExcelダウンロードフラグＯＮ
			frm.setExcelDownloadFlg(true);
		}

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
	public ActionForward reportList(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("reportList.");
		}

		OffsetListForm frm = (OffsetListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = offsetListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * OffsetListLogicを設定します。
	 * @param offsetListLogic offsetListLogic
	 */
	public void setOffsetListLogic(final OffsetListLogic offsetListLogic) {
		this.offsetListLogic = offsetListLogic;
	}

	/**
	 * organizationDetailLogicを設定します。
	 * @param organizationDetailLogic organizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * offsetGroupDetailLogicを設定します。
	 * @param offsetGroupDetailLogic OffsetGroupDetailLogic
	 */
	public void setOffsetGroupDetailLogic(
			final OffsetGroupDetailLogic offsetGroupDetailLogic) {
		this.offsetGroupDetailLogic = offsetGroupDetailLogic;
	}

	/**
	 * 仕訳伝票（相殺伝票）検索画面ロジッククラスを設定します。
	 * @param offsetListExcelDecorator 仕訳伝票（相殺伝票）検索画面ロジッククラス
	 */
	public void setOffsetListExcelDecorator(
			final OffsetListExcelDecorator offsetListExcelDecorator) {
		this.offsetListExcelDecorator = offsetListExcelDecorator;
	}
}
