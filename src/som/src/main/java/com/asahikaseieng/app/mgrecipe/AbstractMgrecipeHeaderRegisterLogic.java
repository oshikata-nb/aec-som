/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeBumonDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * ヘッダー情報更新処理抽象クラス
 * @author tosco
 */
public abstract class AbstractMgrecipeHeaderRegisterLogic implements
		MgrecipeHeaderRegister {
	/** 処方ヘッダー操作DAO */
	protected RecipeHeaderListDao recipeHeaderListDao;

	/** 処方工程操作DAO */
	protected RecipeProcedureListDao recipeProcedureListDao;

	/** 処方配合操作DAO */
	protected RecipeFormulaListDao recipeFormulaListDao;

	/** 工程操作DAO */
	protected OperationDao operationDao;

	/** 品目操作DAO */
	protected MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao;

	/** ヘッダ情報ロジッククラス */
	protected MgrecipeHeaderLogic mgrecipeHeaderLogic;

	/** 基本処方共通ロジッククラス */
	protected MgrecipeCommonsLogic mgrecipeCommonsLogic;

	/** 数値桁数チェックロジッククラス */
	protected CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 用途-配合時のLINE_TYPE */
	protected static final BigDecimal LINE_COMBINE = new BigDecimal(
			RecipeFormulaListDao.LINE_TYPE_COMBINE);

	/** 用途-製造時のLINE_TYPE */
	protected static final BigDecimal LINE_TYPE_PRODUCTION = new BigDecimal(
			RecipeFormulaListDao.LINE_TYPE_PRODUCTION);

	/** 仕上げ－標準生産量LINE_NO=1001 */
	protected static final BigDecimal STANDERD_QTY_LINE_NO = new BigDecimal(
			RecipeFormulaListDao.LINE_NO);

	/** 入力モード－原処方から作成 */
	private static final int MODE_ORIGINAL = 1;

	/** 入力モード－コピー作成 */
	private static final int MODE_COPY = 2;

	/**
	 * ヘッダー情報更新処理
	 * @param form ヘッダー情報画面Form
	 * @throws MgrecipeLogicException 処理例外発生時
	 */
	public void update(final MgrecipeHeaderForm form)
			throws MgrecipeLogicException {
		// マスタ存在チェック(エラー時MgrecipeLogicException発生して飛んでいく)
		isMasterCheck(form);
		// マスタ存在チェックOKなので、空処方作成時
		RecipeHeaderList header = updateData(form);
		// 画面再表示検索用にレシピインデックスを設定
		form.setRecipeId(header.getRecipeId().toString());
	}

	/**
	 * 処方ヘッダー、その他たくさん登録処理
	 * @param form ヘッダー情報画面Form
	 * @return RecipeHeaderList
	 * @throws MgrecipeLogicException エラー発生時
	 */
	protected abstract RecipeHeaderList updateData(final MgrecipeHeaderForm form)
			throws MgrecipeLogicException;

	/**
	 * Logオブジェクトを返す.
	 * @return Logオブジェクト
	 */
	protected final Log log() {
		return LogFactory.getLog(this.getClass());
	}

	/**
	 * 処方ヘッダ、品目マスタキュー存在チェック
	 * @param headerForm MgrecipeHeaderForm
	 * @return [true:チェックOK][false:チェックNG]
	 * @throws MgrecipeLogicException マスタ存在チェックエラー時発生
	 */
	protected boolean isMasterCheck(final MgrecipeHeaderForm headerForm)
			throws MgrecipeLogicException {
		boolean res = true;

		// 基本処方コードマスタチェック
		isExistsRecipeHeader(headerForm.getRecipeCd(), headerForm
				.getRecipeVersion());

		// 品目コードマスタチェック
		isExistsItemQueue(headerForm.getInputProduct());

		if (!StringUtils.isEmpty(headerForm.getSectionCd())) {
			// 会計部門コードマスタチェック
			isExistsBumon(headerForm.getSectionCd());
		}

		// 標準生産量変更チェック
		checkDirtyStdQty(headerForm.getStdQty(), headerForm.getOrgStdQty(),
			headerForm.isCalc());

		/* 原処方から作成時 or コピー作成時 */
		if (headerForm.getMode() == MODE_ORIGINAL
				|| headerForm.getMode() == MODE_COPY) {
			/* 工程マスタチェック */
			isExistsProcedure(headerForm.getRecipeId());

			/* 配合品目マスタチェック */
			isExistsFormulaItem(headerForm.getRecipeId());
		}

		return res;
	}

	/**
	 * 工程マスタチェック
	 * @param recipeId レシピインデックス
	 * @throws MgrecipeLogicException
	 */
	private void isExistsProcedure(final String recipeId)
			throws MgrecipeLogicException {
		/* 工程検索 */
		List<RecipeProcedureList> list = recipeProcedureListDao.findByRecipeId(
			recipeId, null);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				/* 工程マスタ検索 */
				Operation bean = operationDao.getEntity(list.get(i)
						.getOperationCd());

				if (bean == null) {
					throw new MgrecipeLogicException(
							"errors.mgrecipe.no.procedure.operation.cd",
							"item.mgrecipe.operation.cd");
				}
			}
		}
	}

	/**
	 * 配合品目マスタチェック
	 * @param recipeId レシピインデックス
	 * @throws MgrecipeLogicException
	 */
	private void isExistsFormulaItem(final String recipeId)
			throws MgrecipeLogicException {
		/* 配合検索 */
		List<RecipeFormulaList> list = recipeFormulaListDao
				.findByRecipeId(recipeId);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				/* 品目マスタ検索 */
				MgrecipeItemQueueDetail bean = mgrecipeItemQueueDetailDao
						.getEntity(list.get(i).getItemCd());

				if (bean == null) {
					throw new MgrecipeLogicException(
							"errors.mgrecipe.no.formula.item.cd",
							"item.mgrecipe.formula.item.cd");
				}
			}
		}
	}

	/**
	 * 処方ヘッダー存在チェック 処方ヘッダーに既存データが存在する場合は例外発生
	 * @param recipeCd レシピコード
	 * @param version バージョン
	 * @throws MgrecipeLogicException 処方ヘッダーに既存データが存在する場合
	 */
	protected void isExistsRecipeHeader(final String recipeCd,
			final String version) throws MgrecipeLogicException {
		RecipeHeaderList header = getRecipeHeader(recipeCd, version,
			MgrecipeConst.RECIPE_TYPE_MASTER);
		if (header != null) {
			// 既存データが存在する場合、エラー
			throw new MgrecipeLogicException("errors.mgrecipe.duplicate.data",
					"item.mgrecipe.recipe.header.key");
		}
	}

	/**
	 * コード、バージョン、タイプで処方ヘッダーから一意に取得 データが取得できない場合はnullを返す
	 * @param code コード
	 * @param version バージョン
	 * @param type タイプ
	 * @return RecipeHeaderList(結果が存在しない場合はnull)
	 */
	protected RecipeHeaderList getRecipeHeader(final String code,
			final String version, final String type) {
		RecipeHeaderList res = null;
		// コード、バージョン、タイプで処方ヘッダーから一意に取得
		try {
			res = mgrecipeHeaderLogic.getEntity(code, version, type);
		} catch (NoDataException e) {
			res = null;
		}

		return res;
	}

	/**
	 * 品目マスタキュー存在チェック
	 * @param itemCd 品目コード
	 * @return MgrecipeItemQueueDetail
	 * @throws MgrecipeLogicException 品目マスタキューに存在しない場合発生
	 */
	protected MgrecipeItemQueueDetail isExistsItemQueue(final String itemCd)
			throws MgrecipeLogicException {
		MgrecipeItemQueueDetail item = null;
		try {
			item = mgrecipeCommonsLogic.getMaxVersionItemQueue(itemCd);
		} catch (NoDataException e) {
			// 品目マスタキューにデータが存在しない場合エラー
			throw new MgrecipeLogicException("errors.nodata.master",
					"item.mgrecipe.product");
		}
		return item;
	}

	/**
	 * 会計部門存在チェック
	 * @param sectionCd 会計部門コード
	 * @return MgrecipeBumonDetail
	 * @throws MgrecipeLogicException 会計部門マスタに存在しない場合発生
	 */
	protected MgrecipeBumonDetail isExistsBumon(final String sectionCd)
			throws MgrecipeLogicException {
		MgrecipeBumonDetail section = null;
		try {
			section = mgrecipeCommonsLogic.getBumonEntity(sectionCd);
		} catch (NoDataException e) {
			// 会計部門マスタにデータが存在しない場合エラー
			throw new MgrecipeLogicException("errors.nodata.master",
					"item.mgrecipe.section");
		}
		return section;
	}

	/**
	 * 標準生産量変更チェック
	 * @param stdQty 入力標準生産量
	 * @param orgStdQty 入力前標準生産量
	 * @param calc 配合再計算フラグ(true:再計算する)
	 * @throws MgrecipeLogicException 標準生産量が変更されているのに配合再計算にチェックが入っていない場合はエラー
	 */
	protected void checkDirtyStdQty(final String stdQty,
			final BigDecimal orgStdQty, final boolean calc)
			throws MgrecipeLogicException {
		if (!StringUtils.isEmpty(stdQty) && orgStdQty != null) {
			if (AecNumberUtils.convertBigDecimal(stdQty).intValue() != orgStdQty
					.intValue()) {
				if (!calc) {
					throw new MgrecipeLogicException(
							"errors.mgrecipe.no.recalc", "item.mgrecipe.calc");
				}
			}
		}
	}

	/**
	 * 挿入時の処方ヘッダーに設定する共通項目を設定する。
	 * @param headerForm MgrecipeHeaderForm
	 * @return 処方ヘッダー更新データ
	 */
	protected RecipeHeaderList getInsertBean(final MgrecipeHeaderForm headerForm) {

		// 挿入用処方ヘッダーbeanを生成
		RecipeHeaderList bean = new RecipeHeaderList();
		// 基本項目を設定
		getRecipeHeaderBean(headerForm, bean);

		bean.setRecipeCd(headerForm.getRecipeCd()); // レシピコード
		bean.setRecipeVersion(new BigDecimal(headerForm.getRecipeVersion())); // バージョン
		// 原処方空の場合はORIGINAL_RECIPE_IDに原処方のレシピコードを設定
		bean.setProduct(headerForm.getInputProduct()); // 品目コード
		// 収率は各モードで設定
		bean.setPrintFlag(new BigDecimal("0")); // 処方印刷フラグ
		bean.setApprovalStatus(new BigDecimal(
				MgrecipeConst.APPROVAL_STATUS_INPUT)); // 承認ステータス

		String tantoCd = headerForm.getUpdatorCd(); // 更新者IDにログインユーザIDを設定
		bean.setInputorCd(tantoCd); // 登録者ID
		Timestamp date = AecDateUtils.getCurrentTimestamp();
		bean.setInputDate(date); // 登録日
		bean.setUpdatorCd(tantoCd); // 更新者ID
		bean.setUpdateDate(date); // 更新日
		return bean;
	}

	/**
	 * 更新時（挿入・更新）の処方ヘッダーへの設定データを設定する
	 * @param headerForm MgrecipeHeaderForm
	 * @param bean 処方ヘッダー更新データ
	 * @return 処方ヘッダー更新データ
	 */
	protected RecipeHeaderList getRecipeHeaderBean(
			final MgrecipeHeaderForm headerForm, final RecipeHeaderList bean) {

		bean.setRecipeName(headerForm.getInputRecipeName()); // 基本処方名称
		bean.setRecipeType(new BigDecimal(MgrecipeConst.RECIPE_TYPE_MASTER)); // タイプ
		bean.setRecipeDescription(headerForm.getRecipeDescription()); // 注釈
		bean.setRecipeMemo(headerForm.getRecipeMemo()); // 備考
		bean.setRecipeStatus(new BigDecimal(headerForm.getRecipeStatus())); // ステータス
		bean.setRecipeUse(new BigDecimal(headerForm.getRecipeUse())); // 用途
		bean.setRecipePriority(AecNumberUtils.convertBigDecimal(headerForm
				.getRecipePriority())); // 優先度
		bean.setProductionLine(headerForm.getInputProductionLine()); // 生産工場
		bean
				.setMinQty(AecNumberUtils.convertBigDecimal(headerForm
						.getMinQty())); // 最小生産量
		bean
				.setMaxQty(AecNumberUtils.convertBigDecimal(headerForm
						.getMaxQty())); // 最大生産量
		bean
				.setStdQty(AecNumberUtils.convertBigDecimal(headerForm
						.getStdQty())); // 標準生産量
		bean.setUnitQty(AecNumberUtils.convertBigDecimal(headerForm
				.getUnitQty())); // 単位生産量
		bean.setStartDate(AecDateUtils.getTimestampYmdFormat(headerForm
				.getStartDate())); // 開始有効日
		bean.setEndDate(AecDateUtils.getTimestampYmdFormat(headerForm
				.getEndDate())); // 終了有効日
		bean.setSectionCd(headerForm.getSectionCd()); // 会計部門コード

		return bean;
	}

	/**
	 * 処方プロシージャからレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<RecipeProcedureList> getProcedure(final String recipeId)
			throws NoDataException {
		return mgrecipeCommonsLogic.getProcedure(recipeId);
	}

	/**
	 * 処方プロシージャに挿入する
	 * @param bean 登録データ
	 */
	public void insertProcedure(final RecipeProcedureList bean) {
		mgrecipeCommonsLogic.insertProcedure(bean);
	}

	/**
	 * コピー元の処方プロシージャからコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	protected void copyProcedure(final String orgRecipeId,
			final RecipeHeaderList header) {
		try {
			List<RecipeProcedureList> list = getProcedure(orgRecipeId);
			BigDecimal recipeId = header.getRecipeId();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (RecipeProcedureList bean : list) {
				bean.setRecipeId(recipeId); // レシピインデックス
				bean.setInputDate(time); // 登録日
				bean.setUpdateDate(time); // 更新日
				bean.setInputorCd(tantoCd); // 登録者
				bean.setUpdatorCd(tantoCd); // 更新者
				// 処方プロシージャに挿入
				insertProcedure(bean);
			}
		} catch (NoDataException e) {
			log().debug("処方プロシージャにデータがないので、コピー処理を行わない");
		}
	}

	/**
	 * 処方検査からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<RecipeInspectionList> getInspection(final String recipeId)
			throws NoDataException {
		return mgrecipeCommonsLogic.getInspection(recipeId);
	}

	/**
	 * 処方検査に挿入する
	 * @param bean 登録データ
	 */
	public void insertInspection(final RecipeInspectionList bean) {
		mgrecipeCommonsLogic.insertInspection(bean);
	}

	/**
	 * コピー元の処方検査からコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	protected void copyInspection(final String orgRecipeId,
			final RecipeHeaderList header) {
		try {
			List<RecipeInspectionList> list = getInspection(orgRecipeId);
			BigDecimal recipeId = header.getRecipeId();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (RecipeInspectionList bean : list) {
				bean.setRecipeId(recipeId); // レシピインデックス
				bean.setInputDate(time); // 登録日
				bean.setUpdateDate(time); // 更新日
				bean.setInputorCd(tantoCd); // 登録者
				bean.setUpdatorCd(tantoCd); // 更新者
				// 処方処方検査に挿入
				insertInspection(bean);
			}
		} catch (NoDataException e) {
			log().debug("処方検査にデータがないので、コピー処理を行わない");
		}
	}

	/**
	 * 収率計算：収率=標準生産量÷配合量計
	 * @param list フォーミュラデータ
	 * @param stdQty 標準生産量
	 * @param scale 小数点桁数
	 * @param roundingMode 丸めモード
	 * @return 収率
	 */
	protected BigDecimal calcProcessLoss(final List<RecipeFormulaList> list,
			final BigDecimal stdQty, final int scale,
			final RoundingMode roundingMode) {
		return MgrecipeUtil.calcProcessLoss(list, stdQty, scale, roundingMode);
	}

	/**
	 * 配合再計算 入力された標準生産量と変更前の値で配合量を倍する
	 * @param isCalc 配合再計算フラグ[true:配合再計算を行う][false:再計算しない]
	 * @param list 配合フォーミュラデータ
	 * @param stdQty 標準生産量
	 * @param orgStdQty 変更前標準生産量
	 * @param scale 小数点桁数
	 * @param roundingMode 丸めモード
	 */
	protected void calcCombine(final boolean isCalc,
			final List<RecipeFormulaList> list, final BigDecimal orgStdQty,
			final BigDecimal stdQty, final int scale,
			final RoundingMode roundingMode) {

		if (isCalc) {
			// 標準生産量÷変更前標準生産量
			BigDecimal multiple = stdQty.divide(orgStdQty, Constants.SYOSU_KETA, roundingMode);
			for (RecipeFormulaList bean : list) {
				// if (bean.getLineType().equals(LINE_COMBINE)) {
				if (!STANDERD_QTY_LINE_NO.equals(bean.getLineNo())) {
					// 配合・仕上げ(1001は除く)の場合
					// 変更前・後の比をかける
					bean.setQty(bean.getQty().multiply(multiple).setScale(
						scale, roundingMode));
				}
			}
		}
	}

	/**
	 * 処方その他からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<RecipeRemarkList> getRemark(final String recipeId)
			throws NoDataException {
		return mgrecipeCommonsLogic.findRemarkByRecipeId(recipeId);
	}

	/**
	 * 処方その他に挿入する
	 * @param bean 登録データ
	 */
	public void insertRemark(final RecipeRemarkList bean) {
		mgrecipeCommonsLogic.insertRemark(bean);
	}

	/**
	 * コピー元の処方その他からコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	protected void copyRemark(final String orgRecipeId,
			final RecipeHeaderList header) {
		try {
			List<RecipeRemarkList> list = getRemark(orgRecipeId);
			BigDecimal recipeId = header.getRecipeId();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (RecipeRemarkList bean : list) {
				bean.setRecipeId(recipeId); // レシピインデックス
				bean.setInputDate(time); // 登録日
				bean.setUpdateDate(time); // 更新日
				bean.setInputorCd(tantoCd); // 登録者
				bean.setUpdatorCd(tantoCd); // 更新者
				// 処方処方検査に挿入
				insertRemark(bean);
			}
		} catch (NoDataException e) {
			log().debug("処方その他にデータがないので、コピー処理を行わない");
		}
	}

	/**
	 * 処方フォーミュラ挿入処理
	 * @param header 処方ヘッダ挿入データ
	 * @param list 処方フォーミュラリスト
	 */
	protected void insertRecipeFormula(final RecipeHeaderList header,
			final List<RecipeFormulaList> list) {
		// 更新データ作成
		for (RecipeFormulaList bean : list) {
			if (STANDERD_QTY_LINE_NO.equals(bean.getLineNo())) {
				// LINE_NO="1001"のデータには標準生産量を設定
				bean.setQty(header.getStdQty());
			}
			bean.setRecipeId(header.getRecipeId()); // レシピインデックス
			bean.setInputDate(header.getInputDate()); // 登録日
			bean.setUpdateDate(header.getUpdateDate()); // 更新日
			bean.setInputorCd(header.getInputorCd()); // 登録者
			bean.setUpdatorCd(header.getUpdatorCd()); // 更新者
		}
		// 処方フォーミュラに挿入
		mgrecipeCommonsLogic.insertFormulaList(list);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方ヘッダー操作DAOを設定します。
	 * @param recipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setRecipeHeaderListDao(
			final RecipeHeaderListDao recipeHeaderListDao) {
		this.recipeHeaderListDao = recipeHeaderListDao;
	}

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
	 * 数値桁数チェックロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェックロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * recipeFormulaListDaoを設定します。
	 * @param recipeFormulaListDao recipeFormulaListDao
	 */
	public void setRecipeFormulaListDao(
			final RecipeFormulaListDao recipeFormulaListDao) {
		this.recipeFormulaListDao = recipeFormulaListDao;
	}

	/**
	 * recipeProcedureListDaoを設定します。
	 * @param recipeProcedureListDao recipeProcedureListDao
	 */
	public void setRecipeProcedureListDao(
			final RecipeProcedureListDao recipeProcedureListDao) {
		this.recipeProcedureListDao = recipeProcedureListDao;
	}

	/**
	 * mgrecipeItemQueueDetailDaoを設定します。
	 * @param mgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao
	 */
	public void setMgrecipeItemQueueDetailDao(
			final MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao) {
		this.mgrecipeItemQueueDetailDao = mgrecipeItemQueueDetailDao;
	}

	/**
	 * operationDaoを設定します。
	 * @param operationDao operationDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}
}
