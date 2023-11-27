/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.recipeheader.RecipeHeader;
import com.asahikaseieng.dao.entity.recipeheader.RecipeHeaderDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeAsprovaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipePegResouceList;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipePegResouceListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeResouceList;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipeResouceListDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;


/**
 * 製造指図-新規入力 ロジッククラス interface.
 * @author tosco
 */
public class DirectionNewDetailLogicImpl implements DirectionNewDetailLogic {

	/** 発番部品 */
	private GetNumberLogic getNumberLogic;
	/** 処方ASPROVA検索DAO */
	private DirectionRecipeAsprovaListDao directionRecipeAsprovaListDao;
	/** 前後設備紐付けマスタDAO */
	private DirectionRecipePegResouceListDao directionRecipePegResouceListDao;
	/** 設備DAO */
	private DirectionRecipeResouceListDao directionRecipeResouceListDao;
	/** 指図ヘッダDAO */
	private DirectionDirectionHeaderListDao directionDirectionHeaderListDao;
	/** 処方ヘッダDAO */
	private RecipeHeaderDao recipeHeaderDao;
	/** 処方プロシージャDAO */
	private DirectionRecipeProcedureListDao directionRecipeProcedureListDao;
	/** 指図プロシージャDAO */
	private DirectionDirectionProcedureListDao directionDirectionProcedureListDao;
	/** 指図フォーミュラDAO */
	private DirectionDirectionFormulaListDao directionDirectionFormulaListDao;
	/** 処方フォーミュラDAO */
	private DirectionRecipeFormulaListDao directionRecipeFormulaListDao;
	/** 数値桁数チェックロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;
	/** 処方検査DAO */
	private DirectionRecipeInspectionListDao directionRecipeInspectionListDao;
	/** 指図検査DAO */
	private DirectionDirectionInspectionListDao directionDirectionInspectionListDao;
	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ
	 */
	public DirectionNewDetailLogicImpl() {
	}
	/**
	 * 調合タンクの存在チェック
	 * @param recipeId レシピインデックス
	 * @param compoundTankNo 調合タンクNO
	 * @return 調合タンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public String checkExistsCompoundTank(final String recipeId, final String compoundTankNo)
	throws NoDataException {
		DirectionRecipeAsprovaList bean =
			directionRecipeAsprovaListDao.getResouceCd(recipeId, compoundTankNo);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean.getResouceName();
	}
	/**
	 * ホールドタンクの存在チェック
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールドタンクNO
	 * @return ホールドタンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public String checkExistsHoldTank(final String compoundTankNo, final String holdTankNo)
	throws NoDataException {
		DirectionRecipePegResouceList bean =
			directionRecipePegResouceListDao.getResourceCd(compoundTankNo, holdTankNo);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean.getResouceName();
	}
	/**
	 * 予備溶解タンクの存在チェック
	 * @param productionLine 生産ライン
	 * @param dissolutionTankNo 予備溶解タンクNO
	 * @return 予備溶解タンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public String checkExistsDissolutionTank(final String productionLine, final String dissolutionTankNo)
	throws NoDataException {
		DirectionRecipeResouceList bean =
			directionRecipeResouceListDao.getResourceCd(productionLine, dissolutionTankNo);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean.getResouceName();
	}
	/**
	 * 登録処理
	 * @param bean 指図ヘッダ登録データ
	 * @param unitDivision 単位区分
	 */
	public void update(final DirectionDirectionHeaderList bean, final String unitDivision) {
		//指図番号を発番
		bean.setDirectionNo(getNumberLogic.getDirectionNo(bean.getInputDate()));
		//指図ヘッダに挿入
		insertHeader(bean);
		//処方プロシージャから指図プロシージャにコピー
		copyRecipeProcedure(bean);
		//処方フォーミュラから指図フォーミュラへコピー
		copyRecipeFormula(bean, unitDivision);
		//処方検査から指図検査へコピー
		copyRecipeInspection(bean);

		String errMsg = "errors.direction.stock.update";
		try {
			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			/* 在庫更新－包装・製造指図入力 */
			if (!stock.entryDirection
				(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
				throw new LogicExceptionEx(errMsg);
			}
			/* 在庫更新－配合指図入力 */
			if (!stock.entryFormula
				(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
				throw new LogicExceptionEx(errMsg);
			}
		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(errMsg);
		}
	}
	/**
	 * 指図ヘッダ登録処理を行う
	 * @param bean 登録対象ビーン
	 */
	private void insertHeader(final DirectionDirectionHeaderList bean) {
		try {
			//指図ヘッダーに挿入
			int updateNum = directionDirectionHeaderListDao.insert(bean);

			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}
	/**
	 * 処方ヘッダーを取得する
	 * @param recipeId レシピインデックス
	 * @return RecipeHeader
	 * @throws NoDataException データが取得できなかった場合
	 */
	public RecipeHeader getRecipeHeader(final String recipeId) throws NoDataException {
		RecipeHeader head = recipeHeaderDao.getEntity(new BigDecimal(recipeId));
		if (head == null) {
			throw new NoDataException();
		}
		return head;
	}
	/**
	 * 処方プロシージャから指図プロシージャにコピーする
	 * @param head 指図ヘッダ登録データ
	 */
	private void copyRecipeProcedure(final DirectionDirectionHeaderList head) {
		List<DirectionRecipeProcedureList> list = getRecipeProcedureList(head.getRecipeId().toString());
		for (DirectionRecipeProcedureList bean : list) {
			DirectionDirectionProcedureList proc = new DirectionDirectionProcedureList();
			try {
				//フィールド名が同じものを移送
				IgnoreCaseBeanUtils.copyProperties(proc, bean);
				proc.setDirectionDivision(head.getDirectionDivision());
				proc.setDirectionNo(head.getDirectionNo());
				proc.setInputorCd(head.getInputorCd());
				proc.setInputDate(head.getInputDate());
				proc.setUpdatorCd(head.getUpdatorCd());
				proc.setUpdateDate(head.getUpdateDate());
				//指図プロシージャに挿入
				insertDirectionProcedure(proc);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 処方フォーミュラから指図フォーミュラにコピーする
	 * @param head 指図ヘッダ登録データ
	 * @param unitDivision 単位区分
	 */
	private void copyRecipeFormula(final DirectionDirectionHeaderList head, final String unitDivision) {
		List<DirectionRecipeFormulaList> list = getRecipeFormulaList(head.getRecipeId().toString());
		BigDecimal stdQty = getFormul1001Qty(list);		//標準生産量

		//数値桁数マスタの設定を取得
		NumberChkDisitDetail checkDetail = checkDigitUtilsLogic.getCheckDigit(
			DirectionConst.UNIT_DIVISION_HAIGO, null, null);
		//端数区分からBigDecimalの丸めモードに変換
		RoundingMode round = checkDigitUtilsLogic.getRoundingMode(checkDetail.getRoundDivision().intValue());
		int decimalPoint = checkDetail.getSmallnumLength().intValue();
		//仕込数量÷標準生産量の比で数量を倍する
		BigDecimal multiple  = null;
		if (stdQty != null) {
			multiple = head.getPlanedQty().divide(stdQty, Constants.SYOSU_KETA,
				round);
		} else {
			multiple  = new BigDecimal(1);
		}

		for (DirectionRecipeFormulaList bean : list) {
			DirectionDirectionFormulaList formula = new DirectionDirectionFormulaList();
			try {
				//フィールド名が同じものを移送
				IgnoreCaseBeanUtils.copyProperties(formula, bean);
				if (DirectionRecipeFormulaListDao.LINE_NO_INT == bean.getLineNo().intValue()) {
					//1001なので、仕込数量を設定
					formula.setQty(head.getPlanedQty());
				} else {
					//仕込数量÷標準生産量の比で数量を倍する
					BigDecimal calc =
						bean.getQty().multiply(multiple).setScale(decimalPoint, round);
					formula.setQty(calc);
				}
				formula.setDirectionDivision(head.getDirectionDivision());
				formula.setDirectionNo(head.getDirectionNo());
				formula.setInputorCd(head.getInputorCd());
				formula.setInputDate(head.getInputDate());
				formula.setUpdatorCd(head.getUpdatorCd());
				formula.setUpdateDate(head.getUpdateDate());
				//指図フォーミュラに挿入
				insertDirectionFormula(formula);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 処方検査から指図検査にコピーする
	 * @param head 指図ヘッダ登録データ
	 */
	private void copyRecipeInspection(final DirectionDirectionHeaderList head) {
		List<DirectionRecipeInspectionList> list = getRecipeInspectionList(head.getRecipeId().toString());
		for (DirectionRecipeInspectionList bean : list) {
			DirectionDirectionInspectionList inspect = new DirectionDirectionInspectionList();
			try {
				//フィールド名が同じものを移送
				IgnoreCaseBeanUtils.copyProperties(inspect, bean);
				inspect.setDirectionDivision(head.getDirectionDivision());
				inspect.setDirectionNo(head.getDirectionNo());
				inspect.setInputorCd(head.getInputorCd());
				inspect.setInputDate(head.getInputDate());
				inspect.setUpdatorCd(head.getUpdatorCd());
				inspect.setUpdateDate(head.getUpdateDate());
				//指図検査に挿入
				insertDirectionInspection(inspect);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 処方プロシージャを取得する
	 * @param recipeId レシピインデックス
	 * @return List<DirectionRecipeProcedureList>
	 */
	private List<DirectionRecipeProcedureList> getRecipeProcedureList(final String recipeId) {
		List<DirectionRecipeProcedureList> result = directionRecipeProcedureListDao.getRecipeId(recipeId);
		return result;
	}
	/**
	 * 指図プロシージャに挿入する
	 * @param bean 指図プロシージャデータ
	 */
	private void insertDirectionProcedure(final DirectionDirectionProcedureList bean) {
		try {
			//指図プロシージャに挿入
			int updateNum = directionDirectionProcedureListDao.insert(bean);
			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}
	/**
	 * 指図フォーミュラに挿入する。
	 * @param bean 指図フォーミュラデータ
	 */
	private void insertDirectionFormula(final DirectionDirectionFormulaList bean) {
		try {
			//指図フォーミュラに挿入
			int updateNum = directionDirectionFormulaListDao.insert(bean);
			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}
	/**
	 * 処方フォーミュラを取得する
	 * @param recipeId レシピインデックス
	 * @return List<DirectionRecipeProcedureList>
	 */
	private List<DirectionRecipeFormulaList> getRecipeFormulaList(final String recipeId) {
		List<DirectionRecipeFormulaList> result = directionRecipeFormulaListDao.getRecipeId(recipeId);
		return result;
	}
	/**
	 * 仕上げヘッダ(1001)行の標準生産量を取得する
	 * @param list 処方フォーミュラ配列
	 * @return 標準生産量
	 */
	private BigDecimal getFormul1001Qty(final List<DirectionRecipeFormulaList> list) {
		BigDecimal res = null;
		for (DirectionRecipeFormulaList bean : list) {
			if (DirectionRecipeFormulaListDao.LINE_NO_INT == bean.getLineNo().intValue()) {
				//仕上げヘッダ
				res = bean.getQty();
			}
		}
		return res;
	}
	/**
	 * 指図検査に挿入する。
	 * @param bean 指図検査データ
	 */
	private void insertDirectionInspection(final DirectionDirectionInspectionList bean) {
		try {
			//指図検査に挿入
			int updateNum = directionDirectionInspectionListDao.insert(bean);
			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}
	/**
	 * 処方検査を取得する
	 * @param recipeId レシピインデックス
	 * @return List<DirectionRecipeProcedureList>
	 */
	private List<DirectionRecipeInspectionList> getRecipeInspectionList(final String recipeId) {
		List<DirectionRecipeInspectionList> result = directionRecipeInspectionListDao.getRecipeId(recipeId);
		return result;
	}
	//setter,getter---------------------------------------------------------------

	/**
	 * 発番部品を設定します。
	 * @param getNumberLogic 発番部品
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}
	/**
	 * 処方ASPROVA検索DAOを設定します。
	 * @param directionRecipeAsprovaListDao 処方ASPROVA検索DAO
	 */
	public void setDirectionRecipeAsprovaListDao(
			final DirectionRecipeAsprovaListDao directionRecipeAsprovaListDao) {
		this.directionRecipeAsprovaListDao = directionRecipeAsprovaListDao;
	}
	/**
	 * 前後設備紐付けマスタDAOを設定します。
	 * @param directionRecipePegResouceListDao 前後設備紐付けマスタDAO
	 */
	public void setDirectionRecipePegResouceListDao(
			final DirectionRecipePegResouceListDao directionRecipePegResouceListDao) {
		this.directionRecipePegResouceListDao = directionRecipePegResouceListDao;
	}
	/**
	 * 設備DAOを設定します。
	 * @param directionRecipeResouceListDao 設備DAO
	 */
	public void setDirectionRecipeResouceListDao(
			final DirectionRecipeResouceListDao directionRecipeResouceListDao) {
		this.directionRecipeResouceListDao = directionRecipeResouceListDao;
	}
	/**
	 * 指図ヘッダDAOを設定します。
	 * @param directionDirectionHeaderListDao 指図ヘッダDAO
	 */
	public void setDirectionDirectionHeaderListDao(
			final DirectionDirectionHeaderListDao directionDirectionHeaderListDao) {
		this.directionDirectionHeaderListDao = directionDirectionHeaderListDao;
	}
	/**
	 * 処方ヘッダDAOを設定します。
	 * @param recipeHeaderDao 処方ヘッダDAO
	 */
	public void setRecipeHeaderDao(final RecipeHeaderDao recipeHeaderDao) {
		this.recipeHeaderDao = recipeHeaderDao;
	}
	/**
	 * 処方プロシージャDAOを設定します。
	 * @param directionRecipeProcedureListDao 処方プロシージャDAO
	 */
	public void setDirectionRecipeProcedureListDao(
			final DirectionRecipeProcedureListDao directionRecipeProcedureListDao) {
		this.directionRecipeProcedureListDao = directionRecipeProcedureListDao;
	}
	/**
	 * 指図プロシージャDAOを設定します。
	 * @param directionDirectionProcedureListDao 指図プロシージャDAO
	 */
	public void setDirectionDirectionProcedureListDao(
			final DirectionDirectionProcedureListDao directionDirectionProcedureListDao) {
		this.directionDirectionProcedureListDao = directionDirectionProcedureListDao;
	}
	/**
	 * 指図フォーミュラDAOを設定します。
	 * @param directionDirectionFormulaListDao 指図フォーミュラDAO
	 */
	public void setDirectionDirectionFormulaListDao(
			final DirectionDirectionFormulaListDao directionDirectionFormulaListDao) {
		this.directionDirectionFormulaListDao = directionDirectionFormulaListDao;
	}
	/**
	 * 処方フォーミュラDAOを設定します。
	 * @param directionRecipeFormulaListDao 処方フォーミュラDAO
	 */
	public void setDirectionRecipeFormulaListDao(
			final DirectionRecipeFormulaListDao directionRecipeFormulaListDao) {
		this.directionRecipeFormulaListDao = directionRecipeFormulaListDao;
	}
	/**
	 * 数値桁数チェックロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェックロジッククラス
	 */
	public void setCheckDigitUtilsLogic(final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}
	/**
	 * 処方検査DAOを設定します。
	 * @param directionRecipeInspectionListDao 処方検査DAO
	 */
	public void setDirectionRecipeInspectionListDao(
			final DirectionRecipeInspectionListDao directionRecipeInspectionListDao) {
		this.directionRecipeInspectionListDao = directionRecipeInspectionListDao;
	}
	/**
	 * 指図検査DAOを設定します。
	 * @param directionDirectionInspectionListDao 指図検査DAO
	 */
	public void setDirectionDirectionInspectionListDao(
			final DirectionDirectionInspectionListDao directionDirectionInspectionListDao) {
		this.directionDirectionInspectionListDao = directionDirectionInspectionListDao;
	}
	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

}
