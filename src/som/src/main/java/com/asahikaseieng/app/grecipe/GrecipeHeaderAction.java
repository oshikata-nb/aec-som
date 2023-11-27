/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.app.mgrecipe.RecipeExcelDecorator;
import com.asahikaseieng.dao.nonentity.comboboxes.grecipe.GrecipeRecipeHeaderForComboboxes;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeReportConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 基本処方-ヘッダ情報 Actionクラス.
 * @author tosco
 */
public final class GrecipeHeaderAction extends AbstractGrecipeAction {
	/** 入力モード－新規作成 */
	private static final int MODE_NEW = 0;

	/** 入力モード－原処方から作成 */
	/** 入力モード－コピー作成 */
	private static final int MODE_COPY = 2;

	/** 入力モード－更新 */
	private static final int MODE_UPDATE = 3;

	/** 工程パターンなしの値 */
	private static final String OPERATION_PATTERN_ALL_VALUE = "0";

	/** 工程パターンなしの値 */
	public static final long OPERATION_PATTERN_ALL_LONG_VALUE = 0L;

	/** 工程パターンなしのラベル */
	private static final String OPERATION_PATTERN_ALL_LABEL = "パターンなし";

	/** 新規－収率初期値 */
	public static final String PROCESS_LOSS_INIT = "100.0000";

	/** ヘッダ情報ロジッククラス */
	private GrecipeHeaderLogic grecipeHeaderLogic;

	/** 新規登録処理コマンドクラス */
	private GrecipeHeaderRegister grecipeHeaderNewRegisterLogic;

	/** コピー処方登録処理コマンドクラス */
	private GrecipeHeaderRegister grecipeHeaderCopyRegisterLogic;

	/** 更新登録処理コマンドクラス */
	private GrecipeHeaderRegister grecipeHeaderUpdateRegisterLogic;

	/** 帳票（Excel）用エクセルデコレーター */
	private RecipeExcelDecorator recipeExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public GrecipeHeaderAction() {
		super();
	}

	/**
	 * タブIDを取得する
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return GrecipeConst.HEADER;
	}

	/**
	 * 新規作成画面表示処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward cleanNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("cleanNew.");
		}

		GrecipeHeaderForm headerForm = (GrecipeHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_HEADER);

		if (!headerForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期処理
		init(headerForm);
		// 新規作成モード
		headerForm.setMode(MODE_NEW);

		// 用途設定
		headerForm.setRecipeUse(SelectRecipeUse.RECIPE_USE_PRODUCTION);
		// 工程パターンコンボボックス
		headerForm
				.setOperationCombo(getOperationPatternCombo(SelectRecipeUse.RECIPE_USE_PRODUCTION));
		// 収率初期値100.0000
		headerForm.setProcessLoss(PROCESS_LOSS_INIT);
		// 数値桁数詳細の設定
		setNumberChkDisitDetail(headerForm, request);
		return mapping.findForward("success");
	}

	/**
	 * 初期化共通処理
	 * @param form ActionForm
	 */
	private void init(final GrecipeHeaderForm headerForm) throws Exception {
		// クリア
		headerForm.clear();
		// タブID-ヘッダー情報
		headerForm.setTabId(getTabId());
		// ステータスコンボボックス取得
		headerForm.setStatusCombo(grecipeCommonsLogic.getStatusComboList());
	}

	/**
	 * 工程パターンコンボボックス作成
	 * @param use 用途
	 * @return List<ComboBoxItems>
	 */
	private List<ComboBoxItems> getOperationPatternCombo(final String use) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();
		// 処方ヘッダから工程パターンを取得
		List<GrecipeRecipeHeaderForComboboxes> list = grecipeHeaderLogic
				.getOperationPatternList(use);
		// パターンなしを設定
		ComboBoxItems itemVoid = new ComboBoxItems();
		itemVoid.setValues(OPERATION_PATTERN_ALL_VALUE);
		itemVoid.setLabales(OPERATION_PATTERN_ALL_LABEL);
		res.add(itemVoid);

		for (GrecipeRecipeHeaderForComboboxes bean : list) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getRecipeId().toString());
			item.setLabales(bean.getRecipeDescription());
			res.add(item);
		}
		return res;
	}

	/**
	 * 用途変更時処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeUse(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("changeUse.");
		}
		GrecipeHeaderForm headerForm = (GrecipeHeaderForm) form;
		String use = headerForm.getRecipeUse();
		// 工程パターンコンボボックス
		headerForm.setOperationCombo(getOperationPatternCombo(use));
		return mapping.findForward("success");
	}

	/**
	 * 新規登録画面表示処理(コピー作成ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward copy(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("copy.");
		}
		GrecipeHeaderForm headerForm = (GrecipeHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_HEADER);

		if (!headerForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 新規作成モード
		headerForm.setMode(MODE_COPY);
		// 新規用に項目クリア
		headerForm.setRecipeCd(null);
		headerForm.setRecipeVersion(null);
		headerForm.setRecipeName(null);
		headerForm.setInputDate(null);
		headerForm.setUpdateDate(null);
		headerForm.setRecipeStatus(null);
		headerForm.setProduct(null);
		headerForm.setItemName(null);
		headerForm.setApprovalStatus(GrecipeConst.APPROVAL_STATUS_INPUT);
		headerForm
				.setApprovalStatusName(GrecipeConst.APPROVAL_STATUS_NAME_INPUT);

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
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		GrecipeHeaderForm headerForm = (GrecipeHeaderForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		headerForm.setUpdatorCd(tantoCd); // 更新者
		try {
			GrecipeHeaderRegister command = null;
			switch (headerForm.getMode()) {
			case MODE_NEW:
				// 新規作成時
				command = grecipeHeaderNewRegisterLogic;
				break;
			case MODE_COPY:
				// コピー作成時
				command = grecipeHeaderCopyRegisterLogic;
				break;
			case MODE_UPDATE:
				// 更新時
				command = grecipeHeaderUpdateRegisterLogic;
				break;
			default:
				throw new Exception("不正な編集モード(バグ)");
			}
			// マスタ存在チェック・更新処理実行
			command.update(headerForm);
		} catch (GrecipeLogicException e) {
			// ロジッククラスで処理例外が発生した場合（マスタチェック等）
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					replacements[i] = getMessageResource(request,
						replacements[i]);
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			return mapping.findForward("success");
		}
		// 処理成功メッセージ
		saveMessage(request, "message.complete.regist");
		headerForm.setDirtyFlg(null);

		// データ再検索
		return init(mapping, form, request, response);
	}

	/**
	 * 各子クラスの画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	@Override
	protected ActionForward initProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * 帳票(Excel)ボタン押下時
	 * 
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

		GrecipeHeaderForm frm = (GrecipeHeaderForm) form;

		RecipeReportConditionForReport condition = new RecipeReportConditionForReport();
		condition.setRecipeType("1");
		condition.setRecipeCd(frm.getRecipeCd());

		FileDownloadInfo info = null;

		/* 帳票(Excel) */
		info = recipeExcelDecorator.createReport(condition);
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		// ExcelダウンロードフラグＯＮ
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 更新画面表示処理(一覧リンク押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	@Override
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		String forward = "success";
		GrecipeHeaderForm headerForm = (GrecipeHeaderForm) form;
		String recipeId = headerForm.getRecipeId(); // レシピインデックス(initでクリアされるから保持しておく)

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_HEADER);

		if (!headerForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期処理
		init(headerForm);
		// 更新モード
		headerForm.setMode(MODE_UPDATE);
		try {
			// 処方ヘッダから原処方を検索
			GrecipeRecipeHeaderList bean = grecipeHeaderLogic
					.findByPrimaryKey(recipeId);
			// データを移行
			transferBean2Form(headerForm, bean, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			forward = "back";
		}
		return mapping.findForward(forward);
	}

	/**
	 * ヘッダー情報Fomrに検索結果をセットする。
	 * @param form GrecipeHeaderForm
	 * @param bean RecipeHeaderList
	 * @param request HttpServletRequest
	 * @return GrecipeHeaderForm
	 */
	private GrecipeHeaderForm transferBean2Form(final GrecipeHeaderForm form,
			final GrecipeRecipeHeaderList bean, final HttpServletRequest request) {
		form.setRecipeId(getBigDecimalString(bean.getRecipeId()));
		form.setRecipeCd(bean.getRecipeCd());
		form.setRecipeVersion(getBigDecimalString(bean.getRecipeVersion()));
		form.setRecipeUse(getBigDecimalString(bean.getRecipeUse()));
		form.setRecipeUseName(SelectRecipeUse.getName(form.getRecipeUse()));
		form.setProduct(bean.getProduct());
		form.setItemName(bean.getItemName());
		form.setApprovalStatusName(bean.getApprovalStatusName());
		form.setInputDate(bean.getInputDate());
		form.setUpdateDate(bean.getUpdateDate());
		form.setRecipeDescription(bean.getRecipeDescription());
		form.setRecipeMemo(bean.getRecipeMemo());
		form.setRecipeStatus(getBigDecimalString(bean.getRecipeStatus()));
		form.setInputProduct(bean.getProduct());

		String unitDivision = bean.getUnitDivision(); // 単位区分
		form.setUnitDivision(unitDivision);
		// 数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		form.setStdQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO, bean
				.getStdQty()));

		// 数値桁数詳細の設定
		setNumberChkDisitDetail(form, request);

		// 収率（小数点以下4桁固定、以下は四捨五入）
		form.setProcessLoss(AecStringUtils.format(bean.getProcessLoss(), 4,
			RoundingMode.HALF_UP));
		form.setStartDate(AecDateUtils.dateFormat(bean.getStartDate(),
			"yyyy/MM/dd"));
		form.setEndDate(AecDateUtils
				.dateFormat(bean.getEndDate(), "yyyy/MM/dd"));
		form.setApprovalStatus(getBigDecimalString(bean.getApprovalStatus()));
		form.setInputProductName(bean.getItemName());
		form.setUnitName(bean.getUnitName());
		// 配合量
		if (bean.getSumQty() == null) {
			form.setSumQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO,
				BigDecimal.ZERO));
		} else {
			form.setSumQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO,
				bean.getSumQty()));
		}

		// 更新用にRecipeHeaderをformに格納
		form.setHeader(bean);
		return form;
	}

	/**
	 * 数値桁数詳細の設定
	 * @param form ヘッダータグ
	 * @param request HttpServletRequest
	 */
	private void setNumberChkDisitDetail(final GrecipeHeaderForm form,
			final HttpServletRequest request) {
		// 数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		// 数値桁数詳細取得
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			GrecipeConst.UNIT_DIVISION_HAIGO, null, null);
		form.setSmallnumLength(checkDetail.getSmallnumLength().toString()); // 少数点桁数
		form.setRoundDivision(checkDetail.getRoundDivision().toString()); // 端数区分
	}

	/**
	 * 削除処理(詳細画面の削除ボタン押下時)
	 * 
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

		GrecipeHeaderForm headerForm = (GrecipeHeaderForm) form;
		String recipeId = headerForm.getRecipeId();
		// 基本処方のORIGINAL_RECIPE_IDに設定されていないかチェックを行う
		if (isExistsOriginalRecipeId(recipeId)) {
			// 基本処方で参照されているので削除不可
			saveError(request, "errors.grecipe.exists.original.recipe.id");
			return mapping.getInputForward();
		}
		Timestamp updateDate = headerForm.getHeader().getUpdateDate();
		try {
			/* 更新処理を実行 */
			grecipeHeaderLogic.delete(recipeId, updateDate);
		} catch (NoDataException e) {
			// 削除失敗メッセージ
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}
		// 削除成功メッセージ
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 基本処方のORIGINAL_RECIPE_IDに設定されているかチェックする
	 * @param recipeId レシピインデックス
	 * @return [true:設定されている][false:設定されていない]
	 */
	private boolean isExistsOriginalRecipeId(final String recipeId) {
		boolean res = false;

		int count = grecipeHeaderLogic.getOriginalRecipeIdCount(recipeId);
		if (count > 0) {
			res = true;
		}
		return res;
	}

	/**
	 * 承認依頼処理(ヘッダ情報－承認依頼ボタン押下時)
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalRequest(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalRequest.");
		}
		// 承認依頼で処方ヘッダーを更新
		return updateApproval(mapping, form, request, response,
			GrecipeConst.APPROVAL_STATUS_REQUEST,
			"message.grecipe.complete.request");
	}

	/**
	 * 承認処理(ヘッダ情報－承認ボタン押下時)
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approval.");
		}
		// 承認で処方ヘッダーを更新
		return updateApproval(mapping, form, request, response,
			GrecipeConst.APPROVAL_STATUS_APPROVAL,
			"message.grecipe.complete.approve");
	}

	/**
	 * 否認処理(ヘッダ情報－否認ボタン押下時)
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reject(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("reject.");
		}
		// 否認で処方ヘッダーを更新
		return updateApproval(mapping, form, request, response,
			GrecipeConst.APPROVAL_STATUS_INPUT,
			"message.grecipe.complete.denial");
	}

	/**
	 * 承認取り消し処理(ヘッダ情報－承認取り消しボタン押下時)
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalCancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalCancel.");
		}
		// 承認取り消しで処方ヘッダーを更新
		return updateApproval(mapping, form, request, response,
			GrecipeConst.APPROVAL_STATUS_INPUT,
			"message.grecipe.complete.cancel");
	}

	/**
	 * 承認共通処理
	 * @param headerForm form
	 * @param status 承認ステータス
	 * @param message 成功メッセージ
	 * @param request request
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	private ActionForward updateApproval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response, final String status,
			final String message) throws Exception {
		GrecipeHeaderForm headerForm = (GrecipeHeaderForm) form;
		GrecipeRecipeHeaderList header = headerForm.getHeader();
		// 承認ステータス
		header.setApprovalStatus(new BigDecimal(status));
		header.setUpdatorCd(getLoginInfo(request).getTantoCd());
		try {
			// 処方ヘッダを更新
			grecipeHeaderLogic.update(header);
			// 成功メッセージ
			saveMessage(request, message);
			headerForm.setDirtyFlg(null);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// DBのデータが表示時とデータが変更されている場合
			addError(request, "errors.optimisticlock.data", "");
			// 他端末が更新してたので、再検索せず表示
			return mapping.findForward("success");
		}
		// 更新成功時は再検索して表示
		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * ヘッダ情報ロジッククラスを設定します。
	 * @param grecipeHeaderLogic ヘッダ情報ロジッククラス
	 */
	public void setGrecipeHeaderLogic(
			final GrecipeHeaderLogic grecipeHeaderLogic) {
		this.grecipeHeaderLogic = grecipeHeaderLogic;
	}

	/**
	 * 新規登録処理コマンドクラスを設定します。
	 * @param grecipeHeaderNewRegisterLogic 新規登録処理コマンドクラス
	 */
	public void setGrecipeHeaderNewRegisterLogic(
			final GrecipeHeaderNewRegisterLogic grecipeHeaderNewRegisterLogic) {
		this.grecipeHeaderNewRegisterLogic = (GrecipeHeaderRegister) grecipeHeaderNewRegisterLogic;
	}

	/**
	 * コピー処方登録処理コマンドクラスを設定します。
	 * @param grecipeHeaderCopyRegisterLogic コピー処方登録処理コマンドクラス
	 */
	public void setGrecipeHeaderCopyRegisterLogic(
			final GrecipeHeaderCopyRegisterLogic grecipeHeaderCopyRegisterLogic) {
		this.grecipeHeaderCopyRegisterLogic = (GrecipeHeaderRegister) grecipeHeaderCopyRegisterLogic;
	}

	/**
	 * 更新登録処理コマンドクラスを設定します。
	 * @param grecipeHeaderUpdateRegisterLogic 更新登録処理コマンドクラス
	 */
	public void setGrecipeHeaderUpdateRegisterLogic(
			final GrecipeHeaderUpdateRegisterLogic grecipeHeaderUpdateRegisterLogic) {
		this.grecipeHeaderUpdateRegisterLogic = (GrecipeHeaderRegister) grecipeHeaderUpdateRegisterLogic;
	}

	/**
	 * 帳票(Excel)用
	 * @param recipeExcelDecorator 帳票(Excel)用
	 */
	public void setRecipeExcelDecorator(
			final RecipeExcelDecorator recipeExcelDecorator) {
		this.recipeExcelDecorator = recipeExcelDecorator;
	}
}
