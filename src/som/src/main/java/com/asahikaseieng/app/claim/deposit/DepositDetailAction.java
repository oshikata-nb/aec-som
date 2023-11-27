/*
 * Created on 2008/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.checkholiday.CheckHolidayLogic;
import com.asahikaseieng.app.checkholiday.CheckHolidayResult;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectAccountDivision;
import com.asahikaseieng.app.master.accounts.AccountsListLogic;
import com.asahikaseieng.app.master.classification.ClassificationDetailAction;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.app.master.vender.VenderListLogic;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入金入力－詳細情報 Actionクラス.
 * @author Tosco
 */
public final class DepositDetailAction extends AbstractAction {
	/** 更新フラグ-更新モード */
	protected static final int INSERT_FLAG_UPDATE = 0;

	/** 更新フラグ-挿入モード */
	protected static final int INSERT_FLAG_INSERT = 1;

	/** 更新フラグ-参照モード */
	protected static final int INSERT_FLAG_VIEW = 2;

	/** 分類マスタ-データ種別2(入金) */
	private static final BigDecimal CLASSIFICATION_DATA_TYPE = new BigDecimal(
			"2");

	/** 取引先区分=TS(得意先) */
	private static final String VENDER_DIVISION_TS = "TS";

	/** 取引先区分=SI(仕入先) */
	private static final String VENDER_DIVISION_SI = "SI";

	/** 分類マスタ-フラグON */
	private static final int FLG_ON = 1;

	/** 分類マスタ-フラグOFF */
	private static final int FLG_OFF = 0;

	/** 入金トランザクション－入金伝票発行区分 */
	// private static final int ISSUED_DIVISION_NO = 9;
	/** 入金トランザクション－ﾃﾞｰﾀ種別 2:入金 */
	private static final String CREDIT_DATA_TYPE = "2";

	/** 遷移先－売掛元帳 */
	private static final String FORWARD_ARLEDGER = "backArLeder";

	/** 遷移先－買掛元帳 */
	private static final String FORWARD_APLEDGER = "backApLeder";

	/** 承認ステータス－入力中 */
	private static final String APPROVAL_STATUS_INPUT = "1";

	/** 分類マスタ－ﾃﾞｰﾀ集計区分 1:入金 */
	private static final BigDecimal TOTAL_DIVISION_CREDIT = new BigDecimal(1);

	/** 分類マスタ－ﾃﾞｰﾀ集計区分 9:その他 */
	private static final BigDecimal TOTAL_DIVISION_OTHER = new BigDecimal(9);

	/* 売上金額 */
	// private static final String URKINGAKU = "URKINGAKU";
	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	private static final BigDecimal EVERYDAY = new BigDecimal("0");

	private static final BigDecimal VENDER_NODATA = new BigDecimal("1");

	private static final BigDecimal VENDER_NOCAL = new BigDecimal("2");

	private static final BigDecimal CAL_NODATA = new BigDecimal("3");

	/** 部署マスタ詳細 ロジッククラス */
	private OrganizationDetailLogic organizationDetailLogic;

	/** 勘定科目マスタ一覧取得 ロジッククラス */
	private AccountsListLogic accountsListLogic;

	/** 入金分類変更イベント処理ロジッククラス */
	private DepositDetailLogic depositDetailLogic;

	/** 取引先検索処理ロジッククラス */
	private VenderListLogic venderListLogic;

	/** 休日チェックロジッククラス */
	private CheckHolidayLogic checkHolidayLogic;

	/** 入金トランザクションテーブル検索DAO */
	private DepositCreditDao depositCreditDao;

	/**
	 * コンストラクタ.
	 */
	public DepositDetailAction() {
		super();
	}

	/**
	 * 入金入力詳細画面－初期表示処理
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
		return mapping.findForward("success");
	}

	/**
	 * 新規入力－初期表示処理
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

		DepositDetailForm detailForm = (DepositDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_DEPOSIT,
			Constants.TAB_ID_DEPOSIT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期化する
		detailForm.clear();

		detailForm.setCreditNo(null); /* 入金番号 */

		detailForm.setInsertFlg(INSERT_FLAG_INSERT); // 更新フラグ=1:新規入力

		// 入金分類（分類マスタから取得）
		detailForm.setNotCategoryList(getCategoryComboBox(new BigDecimal("1")));
		detailForm.setCategoryList(getCategoryComboBox(new BigDecimal("2")));

		// 科目（科目マスタから取得）
		detailForm.setAccountList(DepositUtil
				.getClassComboBox(depositDetailLogic));
		// 手形種別
		detailForm.setNoteList(DepositUtil.getNoteComboBox(depositDetailLogic));

		// 新規行を1行表示
		List<CreditBean> list = new ArrayList<CreditBean>();
		list.add(getNewLine(1, detailForm.getAdvanceDivision()));
		detailForm.setDetailList(list);

		/* 銀行セット */
		setBankCombobox(detailForm);

		return mapping.findForward("success");
	}

	/**
	 * 更新入力－初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initUpdate(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}
		String forward = "success";

		DepositDetailForm detailForm = (DepositDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_DEPOSIT,
			Constants.TAB_ID_DEPOSIT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期化する
		detailForm.clear();

		detailForm.setInsertFlg(INSERT_FLAG_UPDATE); // 更新フラグ=1:更新入力

		try {
			getExistData(detailForm, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			forward = "back";
		}
		return mapping.findForward(forward);
	}

	/**
	 * 照会－初期表示処理（売掛元帳からの遷移)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initArLedger(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initArLedger.");
		}
		DepositDetailForm detailForm = (DepositDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_DEPOSIT,
			Constants.TAB_ID_DEPOSIT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 参照用データ作成
		String forward = showReferenceAction(request, detailForm,
			FORWARD_ARLEDGER);
		return mapping.findForward(forward);
	}

	/**
	 * 照会－初期表示処理（買掛元帳からの遷移)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initApLedger(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initApLedger.");
		}
		DepositDetailForm detailForm = (DepositDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_DEPOSIT,
			Constants.TAB_ID_DEPOSIT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 参照用データ作成
		String forward = showReferenceAction(request, detailForm,
			FORWARD_APLEDGER);
		return mapping.findForward(forward);
	}

	/**
	 * 更新入力－行追加処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addRow.");
		}

		DepositDetailForm detailForm = (DepositDetailForm) form;
		List<CreditBean> detailList = detailForm.getDetailList();

		// 必須入力チェック(入金分類、入金金額、科目を行追加時の必須項目とする。)
		// int index = 2;
		// for (CreditBean bean : detailList) {
		// 入金分類
		// if (StringUtils.isEmpty(bean.getCategoryDivision())) {
		// addError(request, "errors.addrow", String.valueOf(index),
		// getMessageResource(request, "item.deposit.credit.division"));
		// }
		// 入金金額
		// if (StringUtils.isEmpty(bean.getCreditAmount())) {
		// addError(request, "errors.addrow", String.valueOf(index),
		// getMessageResource(request, "item.deposit.creditAmount"));
		// }
		// 科目
		// if (StringUtils.isEmpty(bean.getDebitTitleCd())) {
		// addError(request, "errors.addrow", String.valueOf(index),
		// getMessageResource(request, "item.deposit.debitTitleCd"));
		// }
		// index++;
		// }
		ActionMessages messages = getErrors(request);
		if (messages.isEmpty()) {
			// 必須入力チェックＯＫ時
			// 現在の行数
			int count = detailList.size();
			// 新規行追加
			detailList.add(getNewLine(count + 1, detailForm
					.getAdvanceDivision()));

			/* 銀行リスト取得 */
			setBankCombobox(detailForm);
		}
		return mapping.findForward("success");
	}

	/**
	 * 更新入力－行削除処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delRow.");
		}

		DepositDetailForm detailForm = (DepositDetailForm) form;
		List<CreditBean> detailList = detailForm.getDetailList();
		int len = detailList.size();
		for (int i = len - 1; i >= 0; i--) {
			CreditBean bean = (CreditBean) detailList.get(i);
			if (bean.isDeleteFlag()) {
				// 削除対象行
				detailList.remove(i);
			}
		}
		if (detailList.size() <= 0) {
			// 明細行が空になった場合は、新規行を1行追加する
			detailList.add(getNewLine(1, detailForm.getAdvanceDivision()));

			/* 銀行リスト取得 */
			setBankCombobox(detailForm);
		} else {
			// 行番号振りなおし
			int index = 1;
			for (CreditBean bean : detailList) {
				bean.setRowNo(String.valueOf(index));
				index++;
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 更新処理
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

		// 遷移先
		String forward = "success";

		DepositDetailForm detailForm = (DepositDetailForm) form;
		List<CreditBean> detailList = detailForm.getDetailList();

		// 部署・取引先のマスタ存在チェック
		boolean checkHeader = checkMasterHeader(detailForm, request);
		// 明細行マスタ存在チェック
		boolean chechMaster = checkMasterDetail(detailList, request);
		// 自社マスタからデータ取得
		// boolean checkCompany = setCompanyMaster(detailForm, request);
		// 分類＝相殺時取引先チェック
		boolean checkSetOff = checkSetoffVender(detailForm, request);

		/* 休日チェック */
		// if (checkHoliday(detailForm, request)) {
		// detailForm.setHolidayMsg("入力された入金日付は休日です。");
		// } else {
		// detailForm.setHolidayMsg(null);
		// }
		if (checkHeader & chechMaster & checkSetOff) {
			// マスタチェックＯＫ
			// ヘッダ部データを明細部に設定
			List<DepositCredit> updateList = getUpdateBeans(detailForm,
				detailList);

			// 更新時初期値設定
			setUpdateInitValue(updateList, detailForm, request);

			// 削除＆挿入
			depositDetailLogic.update(updateList);

			// 登録完了メッセージ
			saveMessage(request, "message.complete.update");

			/* 初期化 */
			initNew(mapping, form, request, response);
		} else {
			forward = "success";
		}
		return mapping.findForward(forward);
	}

	/**
	 * 挿入処理
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
		// 遷移先
		String forward = "success";

		DepositDetailForm detailForm = (DepositDetailForm) form;
		List<CreditBean> detailList = detailForm.getDetailList();

		// 部署・取引先のマスタ存在チェック
		boolean checkHeader = checkMasterHeader(detailForm, request);
		// 明細行マスタ存在チェック
		boolean chechMaster = checkMasterDetail(detailList, request);
		// 自社マスタからデータ取得
		// boolean checkCompany = setCompanyMaster(detailForm, request);
		// 分類＝相殺時取引先チェック
		boolean checkSetOff = checkSetoffVender(detailForm, request);

		/* 休日チェック */
		// if (checkHoliday(detailForm, request)) {
		// detailForm.setHolidayMsg("入力された入金日付は休日です。");
		// } else {
		// detailForm.setHolidayMsg(null);
		// }
		if (checkHeader & chechMaster & checkSetOff) {
			// マスタチェックＯＫ

			// ヘッダ部データを明細部に設定
			List<DepositCredit> updateList = getUpdateBeans(detailForm,
				detailList);

			// 挿入時初期値設定
			setInsertInitValue(detailForm, updateList, request);

			// 挿入
			String message = depositDetailLogic.insert(updateList);
			if (StringUtils.isEmpty(message)) {
				// 登録完了メッセージ
				saveMessage(request, "message.complete.insert");

				/* 初期化 */
				initNew(mapping, form, request, response);
			} else {
				// エラー発生時
				addError(request, "errors.deposit.insert");
				forward = "success";
			}
		} else {
			// マスタ存在チェックエラー
			forward = "success";
		}

		return mapping.findForward(forward);
	}

	/**
	 * 削除処理
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

		DepositDetailForm detailForm = (DepositDetailForm) form;
		String creditNo = detailForm.getCreditNo();

		// 削除
		depositDetailLogic.delete(creditNo);

		// メッセージ
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 勘定科目変更処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward subdebit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("subdebit.");
		}
		// DepositDetailForm detailForm = (DepositDetailForm) form;
		// List<CreditBean> detailList = detailForm.getDetailList();
		// イベント発生元行のデータを取得
		// CreditBean bean = detailList.get(detailForm.getIndex());
		// 補助科目一覧
		// bean.setSubAccounts(DepositUtil.getAccountsSubComboBox(bean.getDebitTitleCd(),
		// accountsListLogic));

		return mapping.findForward("success");
	}

	/**
	 * 入金分類変更イベント処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeCategory(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("changeCategory.");
		}
		DepositDetailForm detailForm = (DepositDetailForm) form;

		List<CreditBean> detailList = detailForm.getDetailList();
		// イベント発生元行のデータを取得
		CreditBean bean = detailList.get(detailForm.getIndex());

		String category = bean.getCategoryDivision(); // 分類コード
		String venderCd = detailForm.getVenderCd(); // 取引先コード

		if (StringUtils.isNotEmpty(category)) {
			ClassificationDetail classBean = null;

			// 分類マスタを分類コードで検索
			classBean = depositDetailLogic.getClassificationEntity(
				CLASSIFICATION_DATA_TYPE, category);

			// 勘定科目・補助科目初期表示値設定
			bean.setDebitTitleCd(classBean.getDebitAccountsCd()); // 勘定科目

			/* 勘定科目名称取得 */
			getAccountsName(detailForm, detailForm.getIndex());

			// 銀行必須区分
			int bankDivision = FLG_OFF;

			if (classBean.getBankDivision() != null) {
				bankDivision = classBean.getBankDivision().intValue();
			}

			if (bankDivision == FLG_ON) {
				bean.setBankDivision(FLG_ON);
				// 銀行必須
				if (StringUtils.isNotEmpty(venderCd)) {
					// 取引先コードが入力時のみ取引先から取得
					getVenderBank(detailForm, bean, request);
				} else {
					/* 銀行情報をクリア */
					clearBankInfo(bean);
				}
			} else {
				bean.setBankDivision(FLG_OFF);

				/* 銀行情報をクリア */
				clearBankInfo(bean);
			}

			// 手形必須区分
			int noteDivision = FLG_OFF;

			if (classBean.getNoteDivision() != null) {
				noteDivision = classBean.getNoteDivision().intValue();
			}

			if (noteDivision == FLG_ON) {
				bean.setCheckNoteDivision(FLG_ON);
			} else {
				bean.setCheckNoteDivision(FLG_OFF);
			}
			

		} else {
			// 入金分類がブランクなのでクリア
			bean.setDebitTitleCd(""); // 科目
			// 銀行マスタコード
			bean.setBankCd("");
			// 預金種別
			bean.setAccountDivision("");
			// 口座番号
			bean.setAccountNo("");
			bean.setBankDivision(0);
			bean.setAccountsCdName(null);
			bean.setCheckNoteDivision(FLG_OFF);
			bean.setNoteDivision(null);
		}
		return mapping.findForward("success");
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
		String forward = "back";
		DepositDetailForm detailForm = (DepositDetailForm) form;
		String back = detailForm.getBackForward();
		if (StringUtils.isNotEmpty(back)) {
			forward = back;
		}

		return mapping.findForward(forward);
	}

	// 内部ロジック--------------------------------------------------------------------------------------
	/**
	 * 新規明細行を取得する
	 * @param row 行番号
	 * @param advanceDivision 前受金区分
	 * @return CreditBean
	 */
	private CreditBean getNewLine(final int row,
			final BigDecimal advanceDivision) {
		CreditBean data = new CreditBean();
		data.setRowNo(String.valueOf(row)); // 行番号
		data.setSubAccounts(DepositUtil.getAccountsSubComboBox(null,
			accountsListLogic));
		data.setAdvanceDivision(advanceDivision); /* 前受金区分 */

		return data;
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * 部署マスタ・取引先マスタの存在チェックを行う。
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	private boolean checkMasterHeader(final DepositDetailForm detailForm,
			final HttpServletRequest request) {
		boolean res = true;
		// 部署マスタから部門名称を検索
		OrganizationDetail organizationDatail = organizationDetailLogic
				.getDetailEntity(detailForm.getOrganizationCd());
		if (organizationDatail != null) {
			// 検索結果が存在した場合は、部署名称を設定する。
			detailForm.setOrganizationName(organizationDatail
					.getOrganizationName());
		} else {
			// 部署コードが存在しない場合は、マスタチェックエラー
			detailForm.setOrganizationName("");
			addError(request, "errors.nodata.master", getMessageResource(
				request, "item.deposit.organization.cd"));
			res = false;
		}
		// 取引先マスタから検索
		boolean checkVender = getVenderName(detailForm, request);
		if (!checkVender) {
			res = false;
		}

		/* 名称マスタから全社共通部門コードを取得 */
		NamesDetail bean = depositDetailLogic.getNamesEntity();

		if (bean == null) {
			detailForm.setDebitSectionCd(null);
		} else {
			detailForm.setDebitSectionCd(bean.getName01());
		}

		return res;
	}

	/**
	 * 取引先マスタから検索
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	private boolean getVenderName(final DepositDetailForm detailForm,
			final HttpServletRequest request) {
		boolean res = true;
		try {
			// 取引先マスタから検索
			VenderList venderList = venderListLogic.checkVenderCd(
				VENDER_DIVISION_TS, detailForm.getVenderCd());
			if (venderList != null) {
				// 検索結果が存在した場合は、請求先名称を設定する。
				detailForm.setVenderName1(venderList.getVenderName1());
				detailForm.setVenderShortedName(venderList
						.getVenderShortedName());
				detailForm.setCreditSectionCd(venderList.getSectionCd());
				detailForm.setCreditTitleCd(venderList.getAccountsCd());
				detailForm.setAdvanceDivision(venderList.getAdvanceDivision());
				res = true;
			}
		} catch (NoDataException e) {
			// 取引先コードが存在しない場合は、マスタチェックエラー
			detailForm.setVenderName1("");
			// エラー発生時
			addError(request, "errors.claim.vender");
			res = false;
		}
		return res;
	}

	/**
	 * 明細行のマスタ存在チェックを行う。
	 * @param detailList 明細行データ配列
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	private boolean checkMasterDetail(final List<CreditBean> detailList,
			final HttpServletRequest request) {
		boolean res = true;
		int index = 1;
		for (CreditBean bean : detailList) {
			// 入金分類存在チェック
			// 分類マスタを分類コードで検索
			ClassificationDetail classDetail = depositDetailLogic
					.getClassificationEntity(CLASSIFICATION_DATA_TYPE, bean
							.getCategoryDivision());

			// 分類マスタから各値を設定

			// ﾃﾞｰﾀ集計区分
			if (classDetail.getDataTotalDivision() == null) {
				bean.setDataTotalDivision(new BigDecimal("0"));
			} else {
				bean.setDataTotalDivision(classDetail.getDataTotalDivision());
			}

			// 売掛対象区分
			if (classDetail.getArDivision() == null) {
				bean.setDepositTargetDivision(new BigDecimal("0"));
			} else {
				bean.setDepositTargetDivision(classDetail.getArDivision());
			}

			// 請求対象区分
			if (classDetail.getClaimDivision() == null) {
				bean.setClaimTargetDivision(new BigDecimal("0"));
			} else {
				bean.setClaimTargetDivision(classDetail.getClaimDivision());
			}

			// 買掛対象区分
			if (classDetail.getCreditDivision() == null) {
				bean.setPayableTargetDivision(new BigDecimal("0"));
			} else {
				bean.setPayableTargetDivision(classDetail.getCreditDivision());
			}

			// 支払対象区分
			if (classDetail.getPaymentDivision() == null) {
				bean.setPaymentTargetDivision(new BigDecimal("0"));
			} else {
				bean.setPaymentTargetDivision(classDetail.getPaymentDivision());
			}

			// 銀行必須区分
			if (classDetail.getBankDivision() == null) {
				bean.setBankDivision(0);
			} else {
				bean.setBankDivision(classDetail.getBankDivision().intValue());
			}

			// 手形必須区分
			if (classDetail.getNoteDivision() == null) {
				bean.setCheckNoteDivision(FLG_OFF);
			} else {
				bean.setCheckNoteDivision(classDetail.getNoteDivision()
						.intValue());
			}

			// 読取時表示分類コード＋分類名称
			bean.setViewCategoryDivision(classDetail.getCategoryDivision()
					+ ":" + classDetail.getCategoryName());
			// データ集計区分
			bean.setClassificationDataTotalDivision(classDetail
					.getDataTotalDivision().intValue());

			index++;
		}
		return res;
	}

	/**
	 * 更新用データ取得
	 * @param detailForm 詳細画面Form
	 * @param detailList 明細行データ配列
	 * @return 更新用データ配列
	 */
	private List<DepositCredit> getUpdateBeans(
			final DepositDetailForm detailForm,
			final List<CreditBean> detailList) {
		List<DepositCredit> list = new ArrayList<DepositCredit>();

		// ヘッダ部データを明細部に設定
		// 部署コード
		String organizationCd = detailForm.getOrganizationCd();
		// 入金日付
		String creditDateString = detailForm.getStrCreditDate();
		Timestamp creditDate = getDate(creditDateString);
		// 請求先コード
		String venderCd = detailForm.getVenderCd();
		// 会計部門コード
		String accountSectionCd = detailForm.getAccountSectionCode();
		// 入金伝票発行区分
		BigDecimal issuedDivision = detailForm.getCreditIssuedDivision();
		// 摘要
		String remark = detailForm.getRemark();
		// 承認ステータス
		String approvalStatus = detailForm.getApprovalStatus();

		for (CreditBean bean : detailList) {
			bean.setOrganizationCd(organizationCd); // 部署コード
			bean.setAccountSectionCd(accountSectionCd); // 会計部門コード
			bean.setCreditDate(creditDate); // 入金日付
			bean.setVenderCd(venderCd); // 請求先コード
			bean.setDrawalDate(getDate(bean.getDrawalDateString())); // 払出日
			bean.setNoteDate(getDate(bean.getNoteDateString())); // 満期日
			bean.setIssuedDivision(issuedDivision); // 入金伝票発行区分
			bean.setRemark(remark); /* 摘要 */
			bean.setApprovalStatus(approvalStatus); /* 承認ステータス */

			// 更新対象データを作成
			DepositCredit updateBean = new DepositCredit();
			// 内容を更新用Beanにコピー
			try {
				IgnoreCaseBeanUtils.copyProperties(updateBean, bean);
			} catch (IllegalAccessException iae) {
				if (getLog().isDebugEnabled()) {
					getLog().debug("更新対象データに値を移行時エラー発生", iae);
				}
			} catch (InvocationTargetException ite) {
				if (getLog().isDebugEnabled()) {
					getLog().debug("更新対象データに値を移行時エラー発生", ite);
				}
			}
			list.add(updateBean);
		}

		return list;
	}

	/**
	 * 日付文字列(YYYY/MM/DD)からTimestampオブジェクトを生成
	 * @param dateString 日付文字列(YYYY/MM/DD)
	 * @return Timestampオブジェクト
	 */
	private Timestamp getDate(final String dateString) {
		return AecDateUtils.getTimestampYmdFormat(dateString, false);
	}

	/**
	 * 自社マスタ検索して、入金伝票発行区分を設定する。
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	// private boolean setCompanyMaster(final DepositDetailForm detailForm,
	// final HttpServletRequest request) {
	// boolean res = true;
	//
	// HttpSession session = request.getSession(false);
	// PasswordCheck passBean = (PasswordCheck) session
	// .getAttribute(Constants.COMPANY_INFO_KEY);
	//
	// try {
	// Company company = companyDetailLogic.getEntity(passBean
	// .getCompanyCd());
	// BigDecimal div = company.getCreditIssuedDivision();
	// int issuDiv = 0;
	// if (div != null) {
	// issuDiv = div.intValue();
	// if (issuDiv == 1) {
	// issuDiv = ISSUED_DIVISION_NO; // 1->9
	// }
	// }
	// // 入金伝票発行区分
	// detailForm.setCreditIssuedDivision(new BigDecimal(issuDiv));
	// } catch (NoDataException e) {
	// addError(request, "errors.nodata.master", getMessageResource(
	// request, "item.deposit.company"));
	// res = false;
	// }
	// return res;
	// }
	/**
	 * 挿入時初期データ設定
	 * @param detailForm 画面データ
	 * @param list 更新用明細データ
	 * @param request request
	 */
	private void setInsertInitValue(final DepositDetailForm detailForm,
			final List<DepositCredit> list, final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		// ログインユーザ情報を取得
		LoginInfo loginInfo = (LoginInfo) session
				.getAttribute(Constants.LOGIN_KEY);
		String userId = loginInfo.getTantoCd();
		BigDecimal zero = new BigDecimal(0);
		// 初期値を設定
		for (DepositCredit credit : list) {
			credit.setDataType(CREDIT_DATA_TYPE); // ﾃﾞｰﾀ種別 2:入金
			credit.setCreditAmount(replace(credit.getCreditAmount(), ",", "")); // 入金金額
			// ﾃﾞｰﾀ集計区分が(1：入金 or 9：その他)の場合
			if ((credit.getDataTotalDivision().compareTo(TOTAL_DIVISION_CREDIT) == 0)
					|| (credit.getDataTotalDivision().compareTo(
						TOTAL_DIVISION_OTHER) == 0)) {
				credit.setEraserAmount("0"); // 消込額
				credit.setCreditEraserAmount(credit.getCreditAmount()); // 入金消込残
			} else {
				// それ以外
				credit.setEraserAmount("0"); // 消込額
				credit.setCreditEraserAmount("0"); // 入金消込残
			}

			credit.setDebitSectionCd(detailForm.getDebitSectionCd()); /* 借方部門コード */
			credit.setCreditSectionCd(detailForm.getCreditSectionCd()); /* 貸方部門コード */
			credit.setCreditTitleCd(detailForm.getCreditTitleCd()); /* 貸方科目コード */
			credit.setDepositUpdateDivision(zero); // 売掛更新フラグ
			credit.setClaimUpdateDivision(zero); // 請求更新フラグ
			credit.setPayableUpdateDivision(zero); // 買掛更新フラグ
			credit.setPaymentUpdateDivision(zero); // 支払更新フラ
			credit.setEraserCompleteDivision(zero); // 消込完了フラグ
			credit.setApprovalStatus(APPROVAL_STATUS_INPUT); // 承認ステータ
			credit.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
			credit.setInputorCd(userId); // 登録者
			credit.setUpdatorCd(userId); // 更新者
			
			String category = credit.getCategoryDivision(); // 分類コード
			
			// 分類マスタを分類コードで検索
			 ClassificationDetail classBean = depositDetailLogic.getClassificationEntity(
				CLASSIFICATION_DATA_TYPE, category);
			
			if( classBean.getShowFlg().equals(BigDecimal.ONE) ){
				credit.setCreditTitleCd(classBean.getCreditAccountsCd()); /* 貸方科目コード */
			}else{
				/* 調整データは貸方科目を分類設定値から設定する */
				credit.setCreditTitleCd(detailForm.getCreditTitleCd()); /* 貸方科目コード */
			}
			if( category.equals("41") ){  /* ⑳調整額（売上＜請求書)の場合、借方部門＝貸方部門 */
				credit.setDebitSectionCd(detailForm.getCreditSectionCd()); 
			}
			 
		}
	}

	/**
	 * Dateオブジェクトを日付文字列(YYYY/MM/DD)に変換する
	 * @param d Dateオブジェクト
	 * @return 日付文字列(YYYY/MM/DD)
	 */
	private String getDateString(final Date d) {
		String res = null;
		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			res = sdf.format(d);
		}
		return res;
	}

	// /**
	// * Stringオブジェクトを摘要文字列に変換する
	// * @param d 日付文字(yyyy/MM/dd)
	// * @return 摘要文字列
	// */
	// private String getDateRemarkString(final String d) {
	// String res = null;
	//
	// if (!StringUtils.isEmpty(d)) {
	// res = d.replaceAll("/", "");
	// res = res.substring(2, 4) + "年" + res.substring(4, 6) + "月入金分";
	// }
	//
	// return res;
	// }

	/**
	 * Stringオブジェクトを日付文字列に変換する
	 * @param d 日付文字
	 * @return 日付文字列(yyyy/MM/dd)
	 */
	private String getStringDateString(final String d) {
		String res = null;

		if (!StringUtils.isEmpty(d)) {
			res = d.replaceAll("/", "");

			if (res.length() == 8) {
				res = res.substring(0, 4) + "/" + res.substring(4, 6) + "/"
						+ res.substring(6, 8);
			} else if (res.length() == 6) {
				res = res.substring(0, 2) + "/" + res.substring(2, 4) + "/"
						+ res.substring(4, 6);
			}
		}

		return res;
	}

	/**
	 * 部署マスタから部署名称を取得し、DepositDetailFormに設定する。
	 * @param detailForm 詳細画面FORM
	 */
	private void getOrganizationName(final DepositDetailForm detailForm) {
		// 部署マスタから部署名称を検索
		OrganizationDetail organizationDatail = organizationDetailLogic
				.getDetailEntity(detailForm.getOrganizationCd());
		if (organizationDatail != null) {
			// 検索結果が存在した場合は、部署名称の初期値として設定する。
			detailForm.setOrganizationName(organizationDatail
					.getOrganizationName());
		} else {
			// 部署コードが存在しない場合は、名称をクリア
			detailForm.setOrganizationName("");
		}
	}

	/**
	 * 更新時初期データ設定
	 * @param list 更新用明細データ
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 */
	private void setUpdateInitValue(final List<DepositCredit> list,
			final DepositDetailForm detailForm, final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		// ログインユーザ情報を取得
		LoginInfo loginInfo = (LoginInfo) session
				.getAttribute(Constants.LOGIN_KEY);
		String userId = loginInfo.getTantoCd();
		// 入金番号
		String creditno = detailForm.getCreditNo();
		// 0フラグ
		BigDecimal zero = new BigDecimal(0);
		BigDecimal one = new BigDecimal(0);
		// 初期値を設定
		for (DepositCredit credit : list) {
			credit.setUpdatorCd(userId); // 更新者
			credit.setCreditAmount(replace(credit.getCreditAmount(), ",", "")); // 入金金額
			// ﾃﾞｰﾀ集計区分が(1：入金 or 9：その他)の場合
			if ((credit.getDataTotalDivision().compareTo(TOTAL_DIVISION_CREDIT) == 0)
					|| (credit.getDataTotalDivision().compareTo(
						TOTAL_DIVISION_OTHER) == 0)) {
				credit.setEraserAmount("0"); // 消込額
				credit.setCreditEraserAmount(credit.getCreditAmount()); // 入金消込残
			} else {
				// それ以外
				credit.setEraserAmount("0"); // 消込額
				credit.setCreditEraserAmount("0"); // 入金消込残
			}
			
			String category = credit.getCategoryDivision(); // 分類コード
			
			// 分類マスタを分類コードで検索
			 ClassificationDetail classBean = depositDetailLogic.getClassificationEntity(
				CLASSIFICATION_DATA_TYPE, category);
			
			// 日付をTimestampからDateへ変換
			credit.setCreditDate(credit.getCreditDate());
			credit.setDrawalDate(credit.getDrawalDate());
			credit.setNoteDate(credit.getNoteDate());
			
			credit.setDebitSectionCd(detailForm.getDebitSectionCd()); /* 借方部門コード */
			credit.setCreditSectionCd(detailForm.getCreditSectionCd()); /* 貸方部門コード */

			credit.setIssueDate(credit.getIssueDate());
			credit.setDeliveryUpdateDate(credit.getDeliveryUpdateDate());
			credit.setInvoiceUpdateDate(credit.getInvoiceUpdateDate());
			credit.setPayableUpdateDate(credit.getPayableUpdateDate());
			credit.setPaymentUpdateDate(credit.getPaymentUpdateDate());
			credit.setEraserCompleteDate(credit.getEraserCompleteDate());
			credit.setApprovalDate(credit.getApprovalDate());
			credit.setInputDate(credit.getInputDate());
			
			if( classBean.getShowFlg().equals(BigDecimal.ONE) ){
				credit.setCreditTitleCd(classBean.getCreditAccountsCd()); /* 貸方科目コード */
			}else{
				/* 調整データは貸方科目を分類設定値から設定する */
				credit.setCreditTitleCd(detailForm.getCreditTitleCd()); /* 貸方科目コード */
			}
			
			if( category.equals("41") ){  /* ⑳調整額（売上＜請求書)の場合、借方部門＝貸方部門 */
				credit.setDebitSectionCd(detailForm.getCreditSectionCd()); 
			}
			
			if (StringUtils.isEmpty(credit.getCreditNo())) {
				// 新規挿入行
				credit.setCreditNo(creditno); // 入金番号
				credit.setDataType(CREDIT_DATA_TYPE); // ﾃﾞｰﾀ種別 2:入金
				credit.setDepositUpdateDivision(zero); // 売掛更新フラグ
				credit.setClaimUpdateDivision(zero); // 請求更新フラグ
				credit.setPayableUpdateDivision(zero); // 買掛更新フラグ
				credit.setPaymentUpdateDivision(zero); // 支払更新フラ
				credit.setEraserCompleteDivision(zero); // 消込完了フラグ
				credit.setApprovalStatus(APPROVAL_STATUS_INPUT); // 承認ステータス
				credit.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				credit.setInputorCd(userId); // 登録者
			}
		}
	}

	/**
	 * Timestamp型からDate型へ変換する
	 * @param t Timestamp型
	 * @return Date型
	 */
	// private Date convertTimestamp(final Date t) {
	// Date res = null;
	// if (t != null) {
	// if (t instanceof Timestamp) {
	// long time = t.getTime();
	// res = new Date(time);
	// } else {
	// res = t;
	// }
	// }
	// return res;
	// }
	/**
	 * 数値文字列をカンマ編集する
	 * @param request request
	 * @param amount 数値文字列
	 * @return カンマ編集文字列
	 */
	private String getCurrency(final HttpServletRequest request,
			final String amount) {
		String res = null;

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		if (StringUtils.isNotEmpty(amount)) {
			BigDecimal dec = new BigDecimal(amount);
			// res = checker.format(URKINGAKU, dec);
			res = checker.format(KINGAKU, dec);
		}
		return res;
	}

	/**
	 * 文字列を置換する。
	 * @param s 対象文字列
	 * @param regex 置換パターン(正規表現)
	 * @param replacement 置換文字列
	 * @return 文字列
	 */
	private String replace(final String s, final String regex,
			final String replacement) {
		String res = null;
		if (StringUtils.isNotEmpty(s)) {
			res = s.replaceAll(regex, replacement);
		}
		return res;
	}

	/**
	 * 参照のみかチェックする。
	 * @param bean 明細Bean
	 * @return [true:更新不可][false:更新可能]
	 */
	private boolean isReadonly(final CreditBean bean) {
		boolean res = false;

		// 入金承認操作可能チェック
		List<DepositCredit> list = depositCreditDao.checkApproval(bean
				.getCreditNo());

		if (list != null) {
			if (0 < list.size()) {
				// 売り掛け更新、請求更新、買掛更新、支払い更新フラグ、消込完了フラグ
				// 何れかが立っていた場合は、更新付加->照会モード
				res = true;
			}
		}

		return res;
	}

	/**
	 * 取引先コードから銀行マスタコード。預金種別・口座番号を取得する。
	 * @param detailForm 詳細画面Form
	 * @param bean 明細データ
	 * @param request HttpServletRequest
	 */
	private void getVenderBank(final DepositDetailForm detailForm,
			final CreditBean bean, final HttpServletRequest request) {
		String venderCd = detailForm.getVenderCd(); // 取引先コード
		try {
			VenderDetail vender = depositDetailLogic.getVenderEntity(venderCd,
				VENDER_DIVISION_TS);

			// 銀行
			bean.setBankCd(vender.getBankCd());
			// 預金種別
			bean.setAccountDivision(vender.getAccountDivision().toString());
			// 口座番号
			bean.setAccountNo(vender.getAccountNo());
		} catch (NoDataException e) {
			/* 銀行情報をクリア */
			clearBankInfo(bean);

			if (getLog().isDebugEnabled()) {
				getLog().debug("取引先マスタデータ無：" + venderCd);
			}
		}
	}

	/**
	 * 銀行情報をクリア
	 * @param bean 明細データ
	 */
	private void clearBankInfo(final CreditBean bean) {
		bean.setBankCd(null); /* 銀行マスタコード */
		bean.setAccountDivision(null); /* 預金種別 */
		bean.setAccountNo(null); /* 口座番号 */
	}

	/**
	 * 既存データ検索
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @throws NoDataException データが存在しない場合
	 * @throws IllegalAccessException プロパティへのアクセス権が無い場合
	 * @throws InvocationTargetException プロパティが存在しない場合
	 */
	private void getExistData(final DepositDetailForm detailForm,
			final HttpServletRequest request) throws NoDataException,
			IllegalAccessException, InvocationTargetException {
		// 入金トランザクションテーブルから検索
		List<DepositCredit> list = depositDetailLogic.findByCreditNo(detailForm
				.getCreditNo());
		List<CreditBean> detailList = new ArrayList<CreditBean>();

		for (DepositCredit credit : list) {
			CreditBean bean = new CreditBean();
			// 表示用データに移送
			IgnoreCaseBeanUtils.copyProperties(bean, credit);
			bean.setDrawalDateString(getDateString(bean.getDrawalDate())); // 振出日
			bean.setNoteDateString(getDateString(bean.getNoteDate())); // 満期日
			// 入金金額カンマ編集
			bean.setCreditAmount(getCurrency(request, bean.getCreditAmount()));

			bean.setAccountsCdName(bean.getAccountsCd() + ":"
					+ bean.getAccountsName());

			// 更新可能チェック
			if (isReadonly(bean)) {
				// 更新不可なので照会にする。
				detailForm.setInsertFlg(INSERT_FLAG_VIEW); // 更新フラグ=2:照会
			}

			detailList.add(bean);
		}
		// 分類・銀行検索
		checkMasterDetail(detailList, request);
		detailForm.setDetailList(detailList);

		/* 銀行セット */
		setBankCombobox(detailForm);

		DepositCredit credit = list.get(0);
		detailForm.setOrganizationCd(credit.getOrganizationCd()); // 部署コード
		detailForm.setStrCreditDate(getDateString(credit.getCreditDate())); // 入金日時
		detailForm.setVenderCd(credit.getVenderCd()); // 請求先コード
		detailForm.setRemark(credit.getRemark()); /* 摘要 */
		detailForm.setApprovalStatus(credit.getApprovalStatus()); /* 承認ステータス */

		// 部署マスタから部署名称を検索
		getOrganizationName(detailForm);
		// 取引先マスタから検索
		getVenderName(detailForm, request);

		// 入金分類（分類マスタから取得）
		detailForm.setNotCategoryList(getCategoryComboBox(new BigDecimal("1")));
		detailForm.setCategoryList(getCategoryComboBox(new BigDecimal("2")));

		// 勘定科目（勘定科目マスタから取得）
		detailForm.setAccountList(DepositUtil
				.getClassComboBox(depositDetailLogic));
		// 手形種別
		detailForm.setNoteList(DepositUtil.getNoteComboBox(depositDetailLogic));

		/* 各行に前受金区分をセット */
		for (int i = 0; i < detailForm.getDetailList().size(); i++) {
			detailForm.getDetailList().get(i).setAdvanceDivision(
				detailForm.getAdvanceDivision());
		}
		// readonly時のデータ取得
		if (INSERT_FLAG_VIEW == detailForm.getInsertFlg()) {
			// 読取専用時
			for (CreditBean bean : detailList) {
				initReadOnly(bean, detailForm);
			}
		}
	}

	/**
	 * 参照用データ作成処理
	 * @param request HttpServletRequest
	 * @param detailForm 詳細画面Form
	 * @param forward 戻り先
	 * @return 遷移先
	 * @throws IllegalAccessException アクセス権が無い場合発生
	 * @throws InvocationTargetException 対象メソッドが無い場合発生
	 */
	private String showReferenceAction(final HttpServletRequest request,
			final DepositDetailForm detailForm, final String forward)
			throws IllegalAccessException, InvocationTargetException {
		String res = "success";
		// 初期化する
		detailForm.clear();

		detailForm.setInsertFlg(INSERT_FLAG_VIEW); // 更新フラグ=2:照会

		try {
			// 初期表示データ取得
			getExistData(detailForm, request);
			// 戻り先を売掛台帳に設定
			detailForm.setBackForward(forward);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata.deleted");
			res = forward;
		}
		return res;
	}

	/**
	 * 読取専用時のコンボボックスの内容を設定する。
	 * @param bean 明細データ
	 * @param detailForm 詳細画面Form
	 */
	private void initReadOnly(final CreditBean bean,
			final DepositDetailForm detailForm) {
		// 預金種別コンボ内容
		if (StringUtils.isNotEmpty(bean.getAccountDivision())) {
			SelectAccountDivision combo = new SelectAccountDivision(false,
					false);
			bean.setViewAccountDivision(combo.getLabel(bean
					.getAccountDivision()));
		}
		// 勘定科目
		bean
				.setViewDebitTitle(detailForm.getDebitLabel(bean
						.getDebitTitleCd()));

		// 補助科目getSubAccountLabel
		// bean.setViewDebitSubTitle(bean.getSubAccountLabel(bean
		// .getDebitSubTitleCd()));
	}

	/**
	 * 分類.データ集計区分＝相殺の場合、支払先に請求先が存在するかチェックを行う
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックNG]
	 */
	private boolean checkSetoffVender(final DepositDetailForm detailForm,
			final HttpServletRequest request) {
		boolean res = true;
		List<CreditBean> list = detailForm.getDetailList();
		boolean isSetoff = false;
		for (CreditBean bean : list) {
			if (bean.getClassificationDataTotalDivision() == ClassificationDetailAction.DATA_TOTAL_DIVISION_OFFSET) {
				// 分類.データ集計区分＝相殺の場合、支払先に請求先が存在するかチェックを行う。
				isSetoff = true;
				break;
			}
		}
		if (isSetoff) {
			// 取引先マスタから仕入先の請求先が存在するかチェックを行う。
			try {
				VenderDetail vender = depositDetailLogic.getVenderEntity(
					detailForm.getVenderCd(), VENDER_DIVISION_SI);
				if (StringUtils.isEmpty(vender.getPaymentInvoiceCd())
						|| vender.getPaymentInvoiceCd().equals(
							detailForm.getVenderCd())) {
					res = true;
				} else {
					// 支払先ではないので、エラー
					throw new NoDataException();
				}
			} catch (NoDataException e) {
				// 仕入先の取引先が存在しないので、
				// 同一コードの支払先が存在しないので総裁の指定は出来ません」エラー
				addError(request, "errors.deposit.setoff");
				res = false;
			}
		}
		return res;
	}

	/**
	 * 休日チェック
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward checkHoliday(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("checkHoliday.");
		}

		DepositDetailForm frm = (DepositDetailForm) form;

		/* 各行に前受金区分をセット */
		for (int i = 0; i < frm.getDetailList().size(); i++) {
			frm.getDetailList().get(i).setAdvanceDivision(
				frm.getAdvanceDivision());
		}

		/* 休日チェック */
		if (checkHoliday(frm, request)) {
			frm.setHolidayMsg("入力された入金日付は休日です。");
		} else {
			frm.setHolidayMsg(null);
		}

		return mapping.findForward("success");
	}

	/**
	 * 休日チェック
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:休日][false:平日]
	 */
	private Boolean checkHoliday(final DepositDetailForm frm,
			final HttpServletRequest request) {
		/* 日付文字列をyyyy/MM/ddに変換 */
		String s = getStringDateString(frm.getStrCreditDate());

		Timestamp creditDate = AecDateUtils.getTimestampYmdFormat(s);

		/* 入金日付未入力 */
		if (creditDate == null) {
			return false;
		}

		/* 取引先未入力 */
		if (StringUtils.isEmpty(frm.getVenderCd())) {
			return false;
		}

		/* 取引先カレンダー検索 */
		CheckHolidayResult result = checkHolidayLogic.getVenderHoliday(frm
				.getVenderDivision(), frm.getVenderCd(), creditDate);

		/* 取引先未登録 */
		if (result.getCode().equals(VENDER_NODATA)) {
			addError(request, "errors.claim.vender");
			return false;
		}

		/* 取引先カレンダー未登録 */
		if (result.getCode().equals(VENDER_NOCAL)) {
			addError(request, "errors.nodata.claim.vender.cal");
			return false;
		}

		/* カレンダー未登録 */
		if (result.getCode().equals(CAL_NODATA)) {
			addError(request, "errors.nodata.claim.cal");
			return false;
		}

		/* 平日 */
		if (result.getCalHoliday().equals(EVERYDAY)) {
			return false;
		}

		return true;
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

		DepositDetailForm frm = (DepositDetailForm) form;

		/* 承認依頼中 */
		BigDecimal status = new BigDecimal("2");

		/* ステータス更新 */
		depositDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		/* 初期化する */
		frm.clear();

		frm.setInsertFlg(INSERT_FLAG_UPDATE); /* 更新フラグ=1:更新入力 */

		try {
			/* 既存データ検索 */
			getExistData(frm, request);
		} catch (NoDataException e) {
			/* データが検索できなかった場合、エラーメッセージを表示 */
			addError(request, "errors.nodata");
			return mapping.findForward("success");
		}

		frm.setApprovalStatus("2");

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

		DepositDetailForm frm = (DepositDetailForm) form;

		/* 承認 */
		BigDecimal status = new BigDecimal("3");

		/* ステータス更新 */
		depositDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		/* 初期化する */
		frm.clear();

		frm.setInsertFlg(INSERT_FLAG_UPDATE); /* 更新フラグ=1:更新入力 */

		try {
			/* 既存データ検索 */
			getExistData(frm, request);
		} catch (NoDataException e) {
			/* データが検索できなかった場合、エラーメッセージを表示 */
			addError(request, "errors.nodata");
			return mapping.findForward("success");
		}

		frm.setApprovalStatus("3");

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

		DepositDetailForm frm = (DepositDetailForm) form;

		/* 否認 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		depositDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		/* 初期化する */
		frm.clear();

		frm.setInsertFlg(INSERT_FLAG_UPDATE); /* 更新フラグ=1:更新入力 */

		try {
			/* 既存データ検索 */
			getExistData(frm, request);
		} catch (NoDataException e) {
			/* データが検索できなかった場合、エラーメッセージを表示 */
			addError(request, "errors.nodata");
			return mapping.findForward("success");
		}

		frm.setApprovalStatus("1");

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

		DepositDetailForm frm = (DepositDetailForm) form;

		/* 承認取消 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		depositDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		/* 初期化する */
		frm.clear();

		frm.setInsertFlg(INSERT_FLAG_UPDATE); /* 更新フラグ=1:更新入力 */

		try {
			/* 既存データ検索 */
			getExistData(frm, request);
		} catch (NoDataException e) {
			/* データが検索できなかった場合、エラーメッセージを表示 */
			addError(request, "errors.nodata");
			return mapping.findForward("success");
		}

		frm.setApprovalStatus("1");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 勘定科目名称取得
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getAccountsName(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getAccountName.");
		}

		DepositDetailForm frm = (DepositDetailForm) form;

		/* 勘定科目名称取得 */
		getAccountsName(frm, frm.getIndex());

		return mapping.findForward("success");
	}

	/**
	 * 勘定科目名称取得
	 * @param frm 画面データ
	 * @param index 行番号
	 */
	private void getAccountsName(final DepositDetailForm frm, final int index) {
		if (StringUtils.isEmpty(frm.getDetailList().get(index)
				.getDebitTitleCd())) {
			frm.getDetailList().get(index).setAccountsCdName(null);
		} else {
			AccountsDetail bean = depositDetailLogic.getAccountsEntity(frm
					.getDetailList().get(index).getDebitTitleCd());

			frm.getDetailList().get(index).setAccountsCdName(
				bean.getAccountsCd() + ":" + bean.getAccountsName());
		}
	}

	/**
	 * 銀行リスト取得
	 * @param frm 画面データ
	 */
	public void setBankCombobox(final DepositDetailForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<CompanyBankListForComboboxes> list = depositDetailLogic
				.getBankList();

		String[] values;
		String[] labels;

		labels = new String[list.size() + 1];
		values = new String[list.size() + 1];

		/* 「なし」を追加 */
		labels[0] = "なし";
		values[0] = null;

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i + 1] = list.get(i).getBankMasterName();
			values[i + 1] = list.get(i).getBankMasterCd();

			switch (i) {
			case 0:
				frm.setBankMasterCd1(list.get(i).getBankMasterCd());
				frm.setAccountDivision1(list.get(i).getAccountDivision());
				frm.setAccountNo1(list.get(i).getAccountNo());
				break;
			case 1:
				frm.setBankMasterCd2(list.get(i).getBankMasterCd());
				frm.setAccountDivision2(list.get(i).getAccountDivision());
				frm.setAccountNo2(list.get(i).getAccountNo());
				break;
			case 2:
				frm.setBankMasterCd3(list.get(i).getBankMasterCd());
				frm.setAccountDivision3(list.get(i).getAccountDivision());
				frm.setAccountNo3(list.get(i).getAccountNo());
				break;
			case 3:
				frm.setBankMasterCd4(list.get(i).getBankMasterCd());
				frm.setAccountDivision4(list.get(i).getAccountDivision());
				frm.setAccountNo4(list.get(i).getAccountNo());
				break;
			default:
				break;
			}
		}

		for (int i = 0; i < frm.getDetailList().size(); i++) {
			frm.getDetailList().get(i).setBankCdLabels(labels);
			frm.getDetailList().get(i).setBankCdValues(values);
		}

		if (!StringUtils.isEmpty(frm.getBankMasterCd())) {
			/* 請求先の自社銀行を初期選択する */
			for (int i = 0; i < list.size(); i++) {
				if (frm.getDetailList().get(i).getBankCd().equals(
					frm.getBankMasterCd())) {
					frm.getDetailList().get(0).setBankCd(frm.getBankMasterCd());
					break;
				}
			}
		}
	}

	/**
	 * 銀行情報取得
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward bankInfo(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("bankInfo.");
		}

		DepositDetailForm frm = (DepositDetailForm) form;

		/* 取引先検索 */
		VenderDetail bean = depositDetailLogic.getVenderEntity(frm
				.getVenderCd(), frm.getVenderDivision());

		int index = frm.getIndex(); /* 選択行位置 */

		if (bean == null) {
			frm.getDetailList().get(index).setAccountDivision(null);
			frm.getDetailList().get(index).setAccountNo(null);
		} else {
			frm.getDetailList().get(index).setAccountDivision(
				bean.getAccountDivision().toString());
			frm.getDetailList().get(index).setAccountNo(bean.getAccountNo());
		}

		return mapping.findForward("success");
	}

	/**
	 * 入力補助の計算
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calcInputAmount(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("calcInputAmount.");
		}

		DepositDetailForm detailForm = (DepositDetailForm) form;
		DepositCredit dc = depositDetailLogic.getClaimSalesAmount(detailForm.getOrganizationCd(), detailForm.getVenderCd(), detailForm.getStrCreditDate());

		if( dc != null && dc.getSalesAmount() != null  ){
			
			int A = Integer.parseInt(StringUtils.replace(detailForm.getRealDepositAmount(), ",", ""));
			int B = Integer.parseInt(  dc.getSalesAmount().toString());
			int C = Integer.parseInt(  dc.getClaimAmount().toString());
			
			detailForm.setAccSysAmount(getCurrency(request, String.valueOf(B)));
			detailForm.setClaimAmount(getCurrency(request, String.valueOf(C)));
			
			// １～７、９の入力額 A,B,Cで一番少ないもの
			if( A <= B && A <= C ){
				detailForm.setInputPtn_No1to7and9(getCurrency(request, String.valueOf(A)));
			}else if( B <= A && B <= C ){
				detailForm.setInputPtn_No1to7and9(getCurrency(request, String.valueOf(B)));
			}else{
				detailForm.setInputPtn_No1to7and9(getCurrency(request, String.valueOf(C)));
			}
			
			// ⑧調整額の入力額 (BかCの少ない方)-A>0
			if( B <= C && B - A > 0 ){
				detailForm.setInputPtn_No8(getCurrency(request, String.valueOf(B - A)) );
			}else if( C <= B && C - A > 0 ){
				detailForm.setInputPtn_No8(getCurrency(request, String.valueOf(C - A)));
			}else{
				detailForm.setInputPtn_No8("0");
			}
			
			// ⑩調整額（売上＞請求書)の額 B-(AかCの多い方)>0
			if( A >= C && B - A > 0 ){
				detailForm.setInputPtn_No10(getCurrency(request, String.valueOf(B-A)));
			}else if( C >= A && B - C > 0 ){
				detailForm.setInputPtn_No10(getCurrency(request, String.valueOf(B - C)));
			}else{
				detailForm.setInputPtn_No10("0");
			}
			
			// １1～１９の入力額 (B>C and A > C)のとき、B-C
			if( B > C && A > C && B - C > 0  ){
				detailForm.setInputPtn_No11to19(getCurrency(request, String.valueOf(B-C)));
			}else{
				detailForm.setInputPtn_No11to19("0");
			}
			
			// ⑳調整額（売上＜請求書)の額 (C>B)のとき、C-(AかBの多いほう)>0
			if( A >= B && C > B && C - A > 0 ){
				detailForm.setInputPtn_No20(getCurrency(request, String.valueOf(C-A)));
			}else if( B >= A && C > B && C - B > 0 ){
				detailForm.setInputPtn_No20(getCurrency(request, String.valueOf(C-B)));
			}else {
				detailForm.setInputPtn_No20("0");
			}
			
			// ２1～２９の入力額 (B>C and A > B)のとき、B-C
			if( C > B  && A > B  && C - B > 0 ){
				detailForm.setInputPtnNo21to29(getCurrency(request, String.valueOf(C - B)));
			}else{
				detailForm.setInputPtnNo21to29("0");
			}
			
			// 会計システムのみの調整額 A - (BかCの大きい方) > 0
			if( B >= C && A - B > 0 ){
				detailForm.setInputPtn_AccSys(getCurrency(request, String.valueOf(A-B)));
			}else if( C >= B && A - C > 0 ){
				detailForm.setInputPtn_AccSys(getCurrency(request, String.valueOf(A-C)));
			}else{
				detailForm.setInputPtn_AccSys("0");
			}
		}
		return mapping.findForward("success");
	}

	
	/**
	 * 入金分類コンボボックス内容取得
	 * @param advanceDivision 売掛対象区分
	 * @return List<ComboBoxItems> 入金コンボボックス内容リスト
	 */
	@SuppressWarnings("unchecked")
	public List<ComboBoxItems> getCategoryComboBox(
			final BigDecimal advanceDivision) {
		// 検索結果を格納用リスト
		List<ComboBoxItems> categoryList = new ArrayList<ComboBoxItems>();
		// 分類マスタ.データ種別＝入金(2)のみ対象
		BigDecimal dataType = CLASSIFICATION_DATA_TYPE;
		BigDecimal arDivision = BigDecimal.ZERO; /* 売掛対象区分 */

		/* 売掛対象区分決定 */
		if (advanceDivision.equals(new BigDecimal("1"))) {
			arDivision = BigDecimal.ONE; /* 前受金対象でない */
		} else {
			arDivision = BigDecimal.ZERO; /* 前受金対象 */
		}

		// コンボボックスの先頭行設定
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales(" ");

		categoryList.add(comboBlank);

		// 分類マスタから検索結果取得
		List<ClassificationListForComboboxes> bean = depositDetailLogic
				.getClassificationList(dataType, arDivision);

		/* 分類コード順のソート */
		Collections.sort(bean, new ClassificationComparator(
				ClassificationComparator.ASC));

		// 分類コード、分類名称を設定
		for (ClassificationListForComboboxes list : bean) {
			ComboBoxItems combo = new ComboBoxItems();
			// 分類コード設定(Value値)
			combo.setValues(list.getCategoryDivision());
			// 分類コード：分類名称をラベルとして設定
			combo.setLabales(list.getCategoryName());
			categoryList.add(combo);
		}

		return categoryList;
	}

	/* -------------------- setter -------------------- */
	/**
	 * 部署マスタ詳細 ロジッククラスを設定する。
	 * @param organizationDetailLogic 部署マスタ詳細 ロジッククラス
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * 勘定科目マスタ一覧取得 ロジッククラスを設定します。
	 * @param accountsListLogic 勘定科目マスタ一覧取得 ロジッククラス
	 */
	public void setAccountsListLogic(final AccountsListLogic accountsListLogic) {
		this.accountsListLogic = accountsListLogic;
	}

	/**
	 * 入金分類変更イベント処理ロジッククラスを設定します。
	 * @param depositDetailLogic 入金分類変更イベント処理ロジッククラス
	 */
	public void setDepositDetailLogic(
			final DepositDetailLogic depositDetailLogic) {
		this.depositDetailLogic = depositDetailLogic;
	}

	/**
	 * 取引先検索処理ロジッククラスを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}

	/**
	 * checkHolidayLogicを設定します。
	 * @param checkHolidayLogic checkHolidayLogic
	 */
	public void setCheckHolidayLogic(final CheckHolidayLogic checkHolidayLogic) {
		this.checkHolidayLogic = checkHolidayLogic;
	}

	/**
	 * depositCreditDaoを設定します。
	 * @param depositCreditDao depositCreditDao
	 */
	public void setDepositCreditDao(final DepositCreditDao depositCreditDao) {
		this.depositCreditDao = depositCreditDao;
	}
}
