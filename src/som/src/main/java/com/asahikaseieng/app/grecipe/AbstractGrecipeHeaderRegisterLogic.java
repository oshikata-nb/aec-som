/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * ヘッダー情報更新処理抽象クラス
 * @author tosco
 */
public abstract class AbstractGrecipeHeaderRegisterLogic implements
		GrecipeHeaderRegister {
	/** 処方ヘッダー操作DAO */
	protected GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao;
	/** ヘッダ情報ロジッククラス */
	protected GrecipeHeaderLogic grecipeHeaderLogic;
	/** 原処方共通ロジッククラス */
	protected GrecipeCommonsLogic grecipeCommonsLogic;
	/** 数値桁数チェックロジッククラス */
	protected CheckDigitUtilsLogic checkDigitUtilsLogic;
	/** 用途-配合時のLINE_TYPE */
	protected static final BigDecimal LINE_COMBINE = new BigDecimal(GrecipeRecipeFormulaListDao.LINE_TYPE_COMBINE);
	/** 用途-製造時のLINE_TYPE */
	protected static final BigDecimal LINE_TYPE_PRODUCTION =
		new BigDecimal(GrecipeRecipeFormulaListDao.LINE_TYPE_PRODUCTION);
	/** 仕上げ－標準生産量LINE_NO=1001 */
	protected static final BigDecimal STANDERD_QTY_LINE_NO = new BigDecimal(GrecipeRecipeFormulaListDao.LINE_NO);

	/**
	 * ヘッダー情報更新処理
	 * @param form ヘッダー情報画面Form
	 * @throws GrecipeLogicException 処理例外発生時
	 */
	public void update(final GrecipeHeaderForm form) throws GrecipeLogicException {
		//マスタ存在チェック(エラー時MgrecipeLogicException発生して飛んでいく)
		isMasterCheck(form);
		//マスタ存在チェックOKなので、空処方作成時
		GrecipeRecipeHeaderList header = updateData(form);
		//画面再表示検索用にレシピインデックスを設定
		form.setRecipeId(header.getRecipeId().toString());
	}

	/**
	 * 処方ヘッダー、その他たくさん登録処理
	 * @param form ヘッダー情報画面Form
	 * @return GrecipeRecipeHeaderList
	 * @throws GrecipeLogicException エラー発生時
	 */
	protected abstract GrecipeRecipeHeaderList updateData(
			final GrecipeHeaderForm form) throws GrecipeLogicException;

	/**
	 * Logオブジェクトを返す.
	 * @return Logオブジェクト
	 */
	protected final Log log() {
		return LogFactory.getLog(this.getClass());
	}

	/**
	 * 処方ヘッダ、品目マスタキュー存在チェック
	 * @param headerForm GrecipeHeaderForm
	 * @return [true:チェックOK][false:チェックNG]
	 * @throws GrecipeLogicException マスタ存在チェックエラー時発生
	 */
	protected boolean isMasterCheck(final GrecipeHeaderForm headerForm) throws GrecipeLogicException {
		boolean res = true;

		//原処方コードマスタチェック
		isExistsRecipeHeader(headerForm.getRecipeCd(), headerForm.getRecipeVersion());

		//品目コードマスタチェック
		isExistsItemQueue(headerForm.getInputProduct());

		return res;
	}
	/**
	 * 処方ヘッダー存在チェック
	 * 処方ヘッダーに既存データが存在する場合は例外発生
	 * @param recipeCd レシピコード
	 * @param version バージョン
	 * @throws GrecipeLogicException 処方ヘッダーに既存データが存在する場合
	 */
	protected void isExistsRecipeHeader(final String recipeCd, final String version) throws GrecipeLogicException {
		GrecipeRecipeHeaderList header = getRecipeHeader(recipeCd, version, GrecipeConst.RECIPE_TYPE_GENERAL);
		if (header != null) {
			//既存データが存在する場合、エラー
			throw new GrecipeLogicException("errors.grecipe.duplicate.data",
				"item.grecipe.recipe.header.key");
		}
	}
	/**
	 * コード、バージョン、タイプで処方ヘッダーから一意に取得
	 * データが取得できない場合はnullを返す
	 * @param code コード
	 * @param version バージョン
	 * @param type タイプ
	 * @return GrecipeRecipeHeaderList(結果が存在しない場合はnull)
	 */
	protected GrecipeRecipeHeaderList getRecipeHeader(final String code, final String version, final String type) {
		GrecipeRecipeHeaderList res = null;
		//コード、バージョン、タイプで処方ヘッダーから一意に取得
		try {
			res = grecipeHeaderLogic.getEntity(code, version, type);
		} catch (NoDataException e) {
			res = null;
		}

		return res;
	}
	/**
	 * 品目マスタキュー存在チェック
	 * @param itemCd 品目コード
	 * @return GrecipeItemQueueDetail
	 * @throws GrecipeLogicException 品目マスタキューに存在しない場合発生
	 */
	protected GrecipeItemQueueDetail isExistsItemQueue(final String itemCd) throws GrecipeLogicException {
		GrecipeItemQueueDetail item = null;
		try {
			item = grecipeCommonsLogic.getMaxVersionItemQueue(itemCd);
		} catch (NoDataException e) {
			//品目マスタキューにデータが存在しない場合エラー
			throw new GrecipeLogicException("errors.nodata.master", "item.grecipe.product");
		}
		return item;
	}
	/**
	 * 挿入時の処方ヘッダーに設定する共通項目を設定する。
	 * @param headerForm GrecipeHeaderForm
	 * @return 処方ヘッダー更新データ
	 */
	protected GrecipeRecipeHeaderList getInsertBean(final GrecipeHeaderForm headerForm) {

		//挿入用処方ヘッダーbeanを生成
		GrecipeRecipeHeaderList bean = new GrecipeRecipeHeaderList();
		//基本項目を設定
		getRecipeHeaderBean(headerForm, bean);

		bean.setRecipeCd(headerForm.getRecipeCd());							//レシピコード
		bean.setRecipeVersion(new BigDecimal(headerForm.getRecipeVersion()));	//バージョン
		//原処方空の場合はORIGINAL_RECIPE_IDに原処方のレシピコードを設定
		bean.setProduct(headerForm.getInputProduct());							//品目コード
		//収率は各モードで設定
		bean.setPrintFlag(new BigDecimal("0"));							//処方印刷フラグ
		bean.setApprovalStatus(new BigDecimal(GrecipeConst.APPROVAL_STATUS_INPUT));		//承認ステータス

		String tantoCd = headerForm.getUpdatorCd();		//更新者IDにログインユーザIDを設定
		bean.setInputorCd(tantoCd);									//登録者ID
		Timestamp date = AecDateUtils.getCurrentTimestamp();
		bean.setInputDate(date);			//登録日
		bean.setUpdatorCd(tantoCd);			//更新者ID
		bean.setUpdateDate(date);			//更新日
		return bean;
	}
	/**
	 * 更新時（挿入・更新）の処方ヘッダーへの設定データを設定する
	 * @param headerForm GrecipeHeaderForm
	 * @param bean 処方ヘッダー更新データ
	 * @return 処方ヘッダー更新データ
	 */
	protected GrecipeRecipeHeaderList getRecipeHeaderBean(final GrecipeHeaderForm headerForm,
			final GrecipeRecipeHeaderList bean) {

		bean.setRecipeType(new BigDecimal(GrecipeConst.RECIPE_TYPE_GENERAL));	//タイプ
		bean.setRecipeDescription(headerForm.getRecipeDescription());			//注釈
		bean.setRecipeMemo(headerForm.getRecipeMemo());							//備考
		bean.setRecipeStatus(new BigDecimal(headerForm.getRecipeStatus()));		//ステータス
		bean.setRecipeUse(new BigDecimal(headerForm.getRecipeUse()));			//用途
		bean.setStdQty(AecNumberUtils.convertBigDecimal(headerForm.getStdQty()));	//標準生産量
		bean.setStartDate(AecDateUtils.getTimestampYmdFormat(headerForm.getStartDate()));	//開始有効日
		bean.setEndDate(AecDateUtils.getTimestampYmdFormat(headerForm.getEndDate()));		//終了有効日

		return bean;
	}

	/**
	 * 処方プロシージャからレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<GrecipeRecipeProcedureList> getProcedure(final String recipeId) throws NoDataException {
		return grecipeCommonsLogic.getProcedure(recipeId);
	}
	/**
	 * 処方プロシージャに挿入する
	 * @param bean 登録データ
	 */
	public void insertProcedure(final GrecipeRecipeProcedureList bean) {
		grecipeCommonsLogic.insertProcedure(bean);
	}
	/**
	 * コピー元の処方プロシージャからコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	protected void copyProcedure(final String orgRecipeId, final GrecipeRecipeHeaderList header) {
		try {
			List<GrecipeRecipeProcedureList> list = getProcedure(orgRecipeId);
			BigDecimal recipeId = header.getRecipeId();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (GrecipeRecipeProcedureList bean : list) {
				bean.setRecipeId(recipeId);		//レシピインデックス
				bean.setInputDate(time);		//登録日
				bean.setUpdateDate(time);		//更新日
				bean.setInputorCd(tantoCd);		//登録者
				bean.setUpdatorCd(tantoCd);		//更新者
				//処方プロシージャに挿入
				insertProcedure(bean);
			}
		} catch (NoDataException e) {
			log().debug("処方プロシージャにデータがないので、コピー処理を行わない");
		}
	}
	/**
	 * 処方検査からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<GrecipeRecipeInspectionList> getInspection(final String recipeId) throws NoDataException {
		return grecipeCommonsLogic.getInspection(recipeId);
	}
	/**
	 * 処方検査に挿入する
	 * @param bean 登録データ
	 */
	public void insertInspection(final GrecipeRecipeInspectionList bean) {
		grecipeCommonsLogic.insertInspection(bean);
	}
	/**
	 * コピー元の処方検査からコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	protected void copyInspection(final String orgRecipeId, final GrecipeRecipeHeaderList header) {
		try {
			List<GrecipeRecipeInspectionList> list = getInspection(orgRecipeId);
			BigDecimal recipeId = header.getRecipeId();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (GrecipeRecipeInspectionList bean : list) {
				bean.setRecipeId(recipeId);		//レシピインデックス
				bean.setInputDate(time);		//登録日
				bean.setUpdateDate(time);		//更新日
				bean.setInputorCd(tantoCd);		//登録者
				bean.setUpdatorCd(tantoCd);		//更新者
				//処方検査に挿入
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
	protected BigDecimal calcProcessLoss(final List<GrecipeRecipeFormulaList> list,
			final BigDecimal stdQty, final int scale, final RoundingMode roundingMode) {
		return GrecipeUtil.calcProcessLoss(list, stdQty, scale, roundingMode);
	}
	/**
	 * 処方その他からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<GrecipeRecipeRemarkList> getRemark(final String recipeId) throws NoDataException {
		return grecipeCommonsLogic.findRemarkByRecipeId(recipeId);
	}
	/**
	 * 処方その他に挿入する
	 * @param bean 登録データ
	 */
	public void insertRemark(final GrecipeRecipeRemarkList bean) {
		grecipeCommonsLogic.insertRemark(bean);
	}
	/**
	 * コピー元の処方その他からコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	protected void copyRemark(final String orgRecipeId, final GrecipeRecipeHeaderList header) {
		try {
			List<GrecipeRecipeRemarkList> list = getRemark(orgRecipeId);
			BigDecimal recipeId = header.getRecipeId();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (GrecipeRecipeRemarkList bean : list) {
				bean.setRecipeId(recipeId);		//レシピインデックス
				bean.setInputDate(time);		//登録日
				bean.setUpdateDate(time);		//更新日
				bean.setInputorCd(tantoCd);		//登録者
				bean.setUpdatorCd(tantoCd);		//更新者
				//処方処方検査に挿入
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
	protected void insertRecipeFormula(final GrecipeRecipeHeaderList header,
			final List<GrecipeRecipeFormulaList> list) {
		//更新データ作成
		for (GrecipeRecipeFormulaList bean : list) {
			if (STANDERD_QTY_LINE_NO.equals(bean.getLineNo())) {
				//LINE_NO="1001"のデータには標準生産量を設定
				bean.setQty(header.getStdQty());
				bean.setItemCd(header.getProduct());	//品目コード
			}
			bean.setRecipeId(header.getRecipeId());		//レシピインデックス
			bean.setInputDate(header.getInputDate());	//登録日
			bean.setUpdateDate(header.getUpdateDate());	//更新日
			bean.setInputorCd(header.getInputorCd());	//登録者
			bean.setUpdatorCd(header.getUpdatorCd());	//更新者
		}
		//処方フォーミュラに挿入
		grecipeCommonsLogic.insertFormulaList(list);
	}
	/* -------------------- setter -------------------- */

	/**
	 * 処方ヘッダー操作DAOを設定します。
	 * @param grecipeRecipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setRecipeHeaderListDao(final GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao) {
		this.grecipeRecipeHeaderListDao = grecipeRecipeHeaderListDao;
	}
	/**
	 * ヘッダ情報ロジッククラスを設定します。
	 * @param grecipeHeaderLogic ヘッダ情報ロジッククラス
	 */
	public void setGrecipeHeaderLogic(
			final GrecipeHeaderLogic grecipeHeaderLogic) {
		this.grecipeHeaderLogic = grecipeHeaderLogic;
	}
	/**
	 * 原処方共通ロジッククラスを設定します。
	 * @param grecipeCommonsLogic 原処方共通ロジッククラス
	 */
	public void setGrecipeCommonsLogic(final GrecipeCommonsLogic grecipeCommonsLogic) {
		this.grecipeCommonsLogic = grecipeCommonsLogic;
	}
	/**
	 * 数値桁数チェックロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェックロジッククラス
	 */
	public void setCheckDigitUtilsLogic(final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

}
