/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeHeaderForComboboxes;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeReportConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 基本処方-ヘッダ情報 Actionクラス.
 * @author tosco
 */
public final class MgrecipeHeaderAction extends AbstractAction {
	/** 入力モード－新規作成 */
	private static final int MODE_NEW = 0;

	/** 入力モード－原処方から作成 */
	private static final int MODE_ORIGINAL = 1;

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

	/** 空処方－収率初期値 */
	public static final String PROCESS_LOSS_INIT = "100.0000";

	/** 基本処方共通ロジッククラス */
	private MgrecipeCommonsLogic mgrecipeCommonsLogic;

	/** ヘッダ情報ロジッククラス */
	private MgrecipeHeaderLogic mgrecipeHeaderLogic;

	/** 空処方登録処理コマンドクラス */
	private MgrecipeHeaderRegister mgrecipeHeaderEmptyRegisterLogic;

	/** コピー処方登録処理コマンドクラス */
	private MgrecipeHeaderRegister mgrecipeHeaderCopyRegisterLogic;

	/** 原処方登録処理コマンドクラス */
	private MgrecipeHeaderRegister mgrecipeHeaderOriginalRegisterLogic;

	/** 更新登録処理コマンドクラス */
	private MgrecipeHeaderRegister mgrecipeHeaderUpdateRegisterLogic;

	// /** Kgの単位区分 */
	// private static final String UNIT_DIVISION_KG = "1";
	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/** 単位区分 "SONOTA" */
	private static final String UNIT_DIVISION_SONOTA = "SONOTA";

	/** 帳票Excel用エクセルデコレイター */
	private RecipeExcelDecorator recipeExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeHeaderAction() {
		super();
	}

	/**
	 * 新規登録画面表示処理(空処方作成ボタン押下時)
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

		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_HEADER);

		if (!headerForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期処理
		init(headerForm, true);
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
	 * @param newFlg true:新規・編集入力
	 */
	private void init(final MgrecipeHeaderForm headerForm, final Boolean newFlg)
			throws Exception {
		// クリア
		headerForm.clear();
		// タブID-ヘッダー情報
		headerForm.setTabId(MgrecipeConst.HEADER);
		// 生産ラインコンボボックス取得
		headerForm
				.setLineCombo(mgrecipeCommonsLogic.createLineCombobox(newFlg));
		// ステータスコンボボックス取得
		headerForm.setStatusCombo(mgrecipeCommonsLogic
				.getStatusComboList(false));
	}

	/**
	 * 工程パターンコンボボックス作成
	 * @param use 用途
	 * @return List<ComboBoxItems>
	 */
	private List<ComboBoxItems> getOperationPatternCombo(final String use) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();
		// 処方ヘッダから工程パターンを取得
		List<RecipeHeaderForComboboxes> list = mgrecipeHeaderLogic
				.getOperationPatternList(use);
		// パターンなしを設定
		ComboBoxItems itemVoid = new ComboBoxItems();
		itemVoid.setValues(OPERATION_PATTERN_ALL_VALUE);
		itemVoid.setLabales(OPERATION_PATTERN_ALL_LABEL);
		res.add(itemVoid);

		for (RecipeHeaderForComboboxes bean : list) {
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
		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;
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
		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_HEADER);

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
		headerForm.setInputProductionLine(headerForm.getProductionLine());
		headerForm.setProductionLineName(null);
		headerForm.setSectionCd(null);
		headerForm.setSectionName(null);
		headerForm.setInputDate(null);
		headerForm.setUpdateDate(null);
		headerForm.setInputRecipeName(null);
		headerForm.setRecipeStatus(null);
		headerForm.setApprovalStatus(MgrecipeConst.APPROVAL_STATUS_INPUT);
		headerForm
				.setApprovalStatusName(MgrecipeConst.APPROVAL_STATUS_NAME_INPUT);

		return mapping.findForward("success");
	}

	/**
	 * 新規登録画面表示処理(原処方から作成時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward recipeNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("recipeNew.");
		}
		String forward = "success";
		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_HEADER);

		if (!headerForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 原処方のレシピインデックス
		String recipeId = headerForm.getRecipeId(); // レシピインデックス(initでクリアされるから保持しておく)
		// 初期処理
		init(headerForm, true);
		// 原処方モード
		headerForm.setMode(MODE_ORIGINAL);

		try {
			// 処方ヘッダから原処方を検索
			RecipeHeaderList bean = mgrecipeHeaderLogic.findByPrimaryKey(
				recipeId, RecipeHeaderListDao.RECIPE_TYPE_GENERAL);
			// データを移行
			transferBean2Form(headerForm, bean, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			forward = "back";
		}
		// 新規用に項目クリア
		headerForm.setRecipeCd(null);
		headerForm.setRecipeVersion(null);
		headerForm.setRecipeName(null);
		headerForm.setProductionLineName(null);
		headerForm.setStdQty(null);
		headerForm.setUnitQty(null);
		headerForm.setMinQty(null);
		headerForm.setMaxQty(null);
		headerForm.setInputDate(null);
		headerForm.setUpdateDate(null);
		headerForm.setInputRecipeName(null);
		headerForm.setInputProductionLine(null);
		headerForm.setRecipeStatus(null);
		headerForm.setApprovalStatus(null);
		headerForm
				.setApprovalStatusName(MgrecipeConst.APPROVAL_STATUS_NAME_INPUT);
		return mapping.findForward(forward);
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
		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;

		String tantoCd = getLoginInfo(request).getTantoCd();
		headerForm.setUpdatorCd(tantoCd); // 更新者
		// マスタ存在チェックOK時
		try {
			MgrecipeHeaderRegister command = null;
			switch (headerForm.getMode()) {
			case MODE_NEW:
				// 空処方作成時
				command = mgrecipeHeaderEmptyRegisterLogic;
				break;
			case MODE_COPY:
				// コピー作成時
				command = mgrecipeHeaderCopyRegisterLogic;
				headerForm.setCalc(true);
				break;
			case MODE_ORIGINAL:
				// 原処方から作成時
				command = mgrecipeHeaderOriginalRegisterLogic;
				break;
			case MODE_UPDATE:
				// 更新時
				command = mgrecipeHeaderUpdateRegisterLogic;
				break;
			default:
				throw new Exception("不正な編集モード(バグ)");
			}
			// 更新処理実行
			command.update(headerForm);
		} catch (MgrecipeLogicException e) {
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
		return search(mapping, form, request, response);

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
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}
		String forward = "success";
		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;
		String recipeId = headerForm.getRecipeId(); // レシピインデックス(initでクリアされるから保持しておく)

		/* 権限取得 */
		getControlAuthority(request, headerForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_HEADER);

		if (!headerForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期処理
		init(headerForm, true);
		// 更新モード
		headerForm.setMode(MODE_UPDATE);

		try {
			// 処方ヘッダから基本処方を検索
			RecipeHeaderList bean = mgrecipeHeaderLogic.findByPrimaryKey(
				recipeId, RecipeHeaderListDao.RECIPE_TYPE_MASTER);
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
	 * @param form MgrecipeHeaderForm
	 * @param bean RecipeHeaderList
	 * @param request HttpServletRequest
	 * @return MgrecipeHeaderForm
	 */
	private MgrecipeHeaderForm transferBean2Form(final MgrecipeHeaderForm form,
			final RecipeHeaderList bean, final HttpServletRequest request) {
		form.setRecipeId(getBigDecimalString(bean.getRecipeId()));
		form.setRecipeCd(bean.getRecipeCd());
		form.setRecipeVersion(getBigDecimalString(bean.getRecipeVersion()));
		form.setRecipeUse(getBigDecimalString(bean.getRecipeUse()));
		form.setRecipeUseName(SelectRecipeUse.getName(form.getRecipeUse()));
		form.setProductionLine(bean.getProductionLine());
		form.setProduct(bean.getProduct());
		form.setApprovalStatusName(bean.getApprovalStatusName());
		form.setRecipeName(bean.getRecipeName());
		form.setItemName(bean.getItemName());
		form.setProductionLineName(bean.getProductionLineName());
		form.setInputorCd(bean.getInputorCd());
		form.setInputDate(AecDateUtils.dateFormat(bean.getInputDate(),
			"yyyy/MM/dd"));
		form.setUpdatorCd(bean.getUpdatorCd());
		form.setUpdateDate(AecDateUtils.dateFormat(bean.getUpdateDate(),
			"yyyy/MM/dd"));
		form.setRecipeDescription(bean.getRecipeDescription());
		form.setRecipeMemo(bean.getRecipeMemo());
		form.setRecipeStatus(getBigDecimalString(bean.getRecipeStatus()));
		form.setRecipePriority(getBigDecimalString(bean.getRecipePriority()));
		form
				.setOriginalRecipeId(getBigDecimalString(bean
						.getOriginalRecipeId()));

		form.setInputProductionLine(bean.getProductionLine());
		form.setInputProduct(bean.getProduct());
		form.setSectionCd(bean.getSectionCd());
		form.setSectionName(bean.getSectionName());

		String unitDivision = bean.getUnitDivision(); // 単位区分
		form.setUnitDivision(unitDivision);
		// 数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		form.setMinQty(checker.format(UNIT_DIVISION_HAIGO, bean.getMinQty()));
		form.setMaxQty(checker.format(UNIT_DIVISION_HAIGO, bean.getMaxQty()));
		form.setStdQty(checker.format(UNIT_DIVISION_HAIGO, bean.getStdQty()));
		form.setUnitQty(checker.format(UNIT_DIVISION_HAIGO, bean.getUnitQty()));

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
		form.setInputRecipeName(bean.getRecipeName());
		form.setInputProductName(bean.getItemName());
		form.setUnitName(bean.getUnitName());
		// 配合量
		if (bean.getSumQty() == null) {
			form
					.setSumQty(checker.format(UNIT_DIVISION_HAIGO,
						BigDecimal.ZERO));
		} else {
			form.setSumQty(checker
					.format(UNIT_DIVISION_HAIGO, bean.getSumQty()));
		}
		form.setWaterWeight(checker.format(UNIT_DIVISION_SONOTA, bean
				.getWaterWeight())); // 洗浄水絶対重量計
		form.setStyleOfPacking(bean.getStyleOfPacking());
		form.setOrgStdQty(bean.getStdQty());

		// 更新用にRecipeHeaderをformに格納
		form.setHeader(bean);
		return form;
	}

	/**
	 * 数値桁数詳細の設定
	 * @param form ヘッダータグ
	 * @param request HttpServletRequest
	 */
	private void setNumberChkDisitDetail(final MgrecipeHeaderForm form,
			final HttpServletRequest request) {
		// 数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		// 数値桁数詳細取得
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			UNIT_DIVISION_HAIGO, null, null);
		form.setSmallnumLength(checkDetail.getSmallnumLength().toString()); // 少数点桁数
		form.setRoundDivision(checkDetail.getRoundDivision().toString()); // 端数区分
	}

	/**
	 * BigDecimalの文字列表現を取得する BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	private String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
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

		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;
		String recipeId = headerForm.getRecipeId();
		Timestamp updateDate = headerForm.getHeader().getUpdateDate();

		try {
			// 削除処理を実行
			mgrecipeHeaderLogic.delete(recipeId, updateDate);
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
	 * 更新画面表示処理(タブ押下時)
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
		return search(mapping, form, request, response);
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
			MgrecipeConst.APPROVAL_STATUS_REQUEST,
			"message.mgrecipe.complete.request");
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
			MgrecipeConst.APPROVAL_STATUS_APPROVAL,
			"message.mgrecipe.complete.approve");
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
			MgrecipeConst.APPROVAL_STATUS_INPUT,
			"message.mgrecipe.complete.denial");
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
			MgrecipeConst.APPROVAL_STATUS_INPUT,
			"message.mgrecipe.complete.cancel");
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
		MgrecipeHeaderForm headerForm = (MgrecipeHeaderForm) form;
		RecipeHeaderList header = headerForm.getHeader();
		// 承認ステータス
		header.setApprovalStatus(new BigDecimal(status));
		header.setUpdatorCd(getLoginInfo(request).getTantoCd());
		try {
			// 処方ヘッダを更新
			mgrecipeHeaderLogic.update(header);
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
		return search(mapping, form, request, response);
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

		MgrecipeHeaderForm frm = (MgrecipeHeaderForm) form;

		RecipeReportConditionForReport condition = new RecipeReportConditionForReport();
		condition.setRecipeType("3");
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
	 * 戻る処理(ヘッダ情報－戻るボタン押下時)
	 * 
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

	// ----------------------------------------------------
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

	/* -------------------- setter -------------------- */

	/**
	 * ヘッダ情報ロジッククラスを設定します。
	 * @param mgrecipeHeaderLogic ヘッダ情報ロジッククラス
	 */
	public void setMgrecipeHeaderLogic(
			final MgrecipeHeaderLogic mgrecipeHeaderLogic) {
		this.mgrecipeHeaderLogic = mgrecipeHeaderLogic;
	}

	/**
	 * 基本処方共通ロジッククラスを設定します。
	 * @param mgrecipeCommonsLogic 基本処方共通ロジッククラス
	 */
	public void setMgrecipeCommonLogic(
			final MgrecipeCommonsLogic mgrecipeCommonsLogic) {
		this.mgrecipeCommonsLogic = mgrecipeCommonsLogic;
	}

	/**
	 * 空処方登録処理コマンドクラスを設定します。
	 * @param mgrecipeHeaderEmptyRegisterLogic 空処方登録処理コマンドクラス
	 */
	public void setMgrecipeHeaderEmptyRegisterCommand(
			final MgrecipeHeaderEmptyRegisterLogic mgrecipeHeaderEmptyRegisterLogic) {
		this.mgrecipeHeaderEmptyRegisterLogic = (MgrecipeHeaderRegister) mgrecipeHeaderEmptyRegisterLogic;
	}

	/**
	 * コピー処方登録処理コマンドクラスを設定します。
	 * @param mgrecipeHeaderCopyRegisterLogic コピー処方登録処理コマンドクラス
	 */
	public void setMgrecipeHeaderCopyRegisterLogic(
			final MgrecipeHeaderCopyRegisterLogic mgrecipeHeaderCopyRegisterLogic) {
		this.mgrecipeHeaderCopyRegisterLogic = (MgrecipeHeaderRegister) mgrecipeHeaderCopyRegisterLogic;
	}

	/**
	 * 原処方登録処理コマンドクラスを設定します。
	 * @param mgrecipeHeaderOriginalRegisterLogic 原処方登録処理コマンドクラス
	 */
	public void setMgrecipeHeaderOriginalRegisterLogic(
			final MgrecipeHeaderOriginalRegisterLogic mgrecipeHeaderOriginalRegisterLogic) {
		this.mgrecipeHeaderOriginalRegisterLogic = (MgrecipeHeaderRegister) mgrecipeHeaderOriginalRegisterLogic;
	}

	/**
	 * 更新登録処理コマンドクラスを設定します。
	 * @param mgrecipeHeaderUpdateRegisterLogic 更新登録処理コマンドクラス
	 */
	public void setMgrecipeHeaderUpdateRegisterLogic(
			final MgrecipeHeaderUpdateRegisterLogic mgrecipeHeaderUpdateRegisterLogic) {
		this.mgrecipeHeaderUpdateRegisterLogic = (MgrecipeHeaderRegister) mgrecipeHeaderUpdateRegisterLogic;
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
