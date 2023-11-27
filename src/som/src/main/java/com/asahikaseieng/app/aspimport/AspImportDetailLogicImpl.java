/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.aspimport;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.common.stockinout.StockinoutForPurchase;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.directiondetail.DirectionDetail;
import com.asahikaseieng.dao.entity.directiondetail.DirectionDetailDao;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.directioninspection.DirectionInspection;
import com.asahikaseieng.dao.entity.directioninspection.DirectionInspectionDao;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedure;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedureDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.purchasemateinjection.PurchaseMateInjection;
import com.asahikaseieng.dao.entity.purchasemateinjection.PurchaseMateInjectionDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.entity.recipeheader.RecipeHeader;
import com.asahikaseieng.dao.entity.recipeheader.RecipeHeaderDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportAspOperation;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportAspOperationDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportAspOrder;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportAspOrderDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportAspUseinstruction;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportAspUseinstructionDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportDirectionHeader;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportDirectionHeaderDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportPurchaseAttributeQueue;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportPurchaseAttributeQueueDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportPurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportPurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRemarkList;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportRemarkListDao;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportUnitprice;
import com.asahikaseieng.dao.nonentity.aspimport.AspImportUnitpriceDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * ASP取り込み ロジック 実装クラス.
 * @author
 */
public class AspImportDetailLogicImpl implements AspImportDetailLogic {

	// 小数点以下の桁数 割り算の循環小数エラー回避用
	// private static final int SYOSU_KETA = 50;

	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	private AspImportPurchaseSubcontractDao aspImportPurchaseSubcontractDao;

	private AspImportAspOrderDao aspImportAspOrderDao;

	private PurchaseSubcontractDao purchaseSubcontractDao;

	private CheckDigitUtilsLogic checker;

	private GetNumberLogic getNumberLogic;

	private AspImportPurchaseAttributeQueueDao aspImportPurchaseAttributeQueueDao;

	private VenderDao venderDao;

	private ItemDao itemDao;

	private AspImportUnitpriceDao aspImportUnitpriceDao;

	private PurchaseMateInjectionDao purchaseMateInjectionDao;

	private RecipeHeaderDao recipeHeaderDao;

	private AspImportAspOperationDao aspImportAspOperationDao;

	private DirectionHeaderDao directionHeaderDao;

	private AspImportRecipeProcedureListDao aspImportRecipeProcedureListDao;

	private DirectionProcedureDao directionProcedureDao;

	private AspImportRecipeFormulaListDao aspImportRecipeFormulaListDao;

	private DirectionFormulaDao directionFormulaDao;

	private AspImportRecipeInspectionListDao aspImportRecipeInspectionListDao;

	private DirectionInspectionDao directionInspectionDao;

	private AspImportAspUseinstructionDao aspImportAspUseinstructionDao;

	private AspImportDirectionHeaderDao aspImportDirectionHeaderDao;

	private DirectionDetailDao directionDetailDao;

	private AspImportRemarkListDao aspImportRemarkListDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.
	 */
	public AspImportDetailLogicImpl() {
	}

	/**
	 * 購買外注作成処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	public int createPurchaseSubcontract(final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {
		List<AspImportPurchaseSubcontract> delList = null;

		// 実際登録するデータ以外のデータも削除対象となるケースがあるため
		// 予め削除対象の購買Noを全てを取得し、在庫更新キャンセル及びデータの
		// 削除を行う
		delList = aspImportPurchaseSubcontractDao.getDeleteAspData();
		if (delList != null && delList.size() > 0) {
			String errMsg = "errors.purchase.stock.update";
			StockinoutForPurchase stock = new StockinoutForPurchase(zaiCtrlDao);
			PurchaseSubcontract delBean = new PurchaseSubcontract();
			for (AspImportPurchaseSubcontract bean : delList) {
				try {
					// 在庫更新－発注取消
					if (!stock
							.canselPurchase(bean.getPurchaseNo(), loginUserId)) {
						throw new LogicExceptionEx(errMsg);
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}
				delBean.setPurchaseNo(bean.getPurchaseNo());
				delBean.setUpdateDate(bean.getUpdateDate());
				try {
					// 購買外注オーダーファイルを削除
					purchaseSubcontractDao.delete(delBean);
				} catch (NotSingleRowUpdatedRuntimeException e) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}
			}
		}

		// 購買データ
		int purchaseNum = importPurchase(loginUserId, loginUserOrganizationCd);
		// 外注製品データ
		int subcontractNum = importSubcontract(loginUserId,
			loginUserOrganizationCd);

		return purchaseNum + subcontractNum;
	}

	/**
	 * 購買データ出力処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	private int importPurchase(final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {
		int i = 0;
		// 購買データ出力
		List<AspImportAspOrder> aspOrderList = aspImportAspOrderDao
				.getPurchaseData();

		for (AspImportAspOrder bean : aspOrderList) {

			// 登録用データ作成
			PurchaseSubcontract newBean = setPurchaseSubcontract(bean,
				loginUserId, loginUserOrganizationCd);

			try {
				// 追加処理
				int updateNum = purchaseSubcontractDao.insert(newBean);

				if (updateNum != 1) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}

				// 自動発番した購買NOを取得する
				AspImportPurchaseSubcontract detailBean = aspImportPurchaseSubcontractDao
						.getPurchaseNo(newBean.getBuySubcontractOrderNo());
				StockinoutForPurchase stock = new StockinoutForPurchase(
						zaiCtrlDao);
				String errMsg = "errors.purchase.stock.update";
				try {
					// 在庫更新－発注入力
					if (!stock.entryPurchase(detailBean.getPurchaseNo(),
						newBean.getUpdatorCd())) {
						throw new LogicExceptionEx(errMsg);
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}

				i++;
			} catch (SQLRuntimeException e) {
				throw new DuplicateRuntimeException(0);
			}

		}
		return i;
	}

	/**
	 * 外注製品データ出力処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	private int importSubcontract(final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {
		int i = 0;
		// 外注製品データ出力
		List<AspImportAspOrder> aspOrderList = aspImportAspOrderDao
				.getSubcontractData();

		for (AspImportAspOrder bean : aspOrderList) {
			// **********************************************
			// PurchaseSubcontract登録
			// **********************************************
			// 登録用データ作成
			PurchaseSubcontract newBean = setPurchaseSubcontract(bean,
				loginUserId, loginUserOrganizationCd);

			try {
				// 追加処理
				int updateNum = purchaseSubcontractDao.insert(newBean);

				// 自動発番した購買NOを取得する
				AspImportPurchaseSubcontract detailBean = aspImportPurchaseSubcontractDao
						.getPurchaseNo(newBean.getBuySubcontractOrderNo());
				StockinoutForPurchase stock = new StockinoutForPurchase(
						zaiCtrlDao);
				String errMsg = "errors.purchase.stock.update";
				try {
					// 在庫更新－発注入力
					if (!stock.entryPurchase(detailBean.getPurchaseNo(),
						newBean.getUpdatorCd())) {
						throw new LogicExceptionEx(errMsg);
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}

				if (updateNum != 1) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}
				i++;
			} catch (SQLRuntimeException e) {
				throw new DuplicateRuntimeException(0);
			}

			// **********************************************
			// PurchaseMateInjection登録
			// **********************************************
			// レシピインデックスがnullのときは登録できないため、飛ばす
			if (bean.getRecipeId() == null) {
				continue;
			}

			// 登録用データ作成
			PurchaseMateInjection newMateBean = new PurchaseMateInjection();

			// 発注番号
			newMateBean.setBuySubcontractOrderNo(newBean
					.getBuySubcontractOrderNo());

			// レシピヘッダ取得
			RecipeHeader rHeadBean = recipeHeaderDao.getEntity(bean
					.getRecipeId());
			// レシピインデックス
			newMateBean.setRecipeId(rHeadBean.getRecipeId());
			// レシピコード
			newMateBean.setRecipeCd(rHeadBean.getRecipeCd());
			// レシピversion
			newMateBean.setRecipeVersion(rHeadBean.getRecipeVersion());
			// STEP_NO
			newMateBean.setStepNo(new BigDecimal("0"));
			// LINE_NO
			newMateBean.setLineNo(new BigDecimal("0"));
			// SEQ
			newMateBean.setSeq(new BigDecimal(0));

			newMateBean.setInputorCd(loginUserId); // 登録者
			newMateBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
			newMateBean.setUpdatorCd(loginUserId); // 更新者
			newMateBean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時

			try {
				// / 追加処理
				int updateNum = purchaseMateInjectionDao.insert(newMateBean);

				if (updateNum != 1) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}
			} catch (SQLRuntimeException e) {
				throw new DuplicateRuntimeException(0);
			}
		}

		return i;
	}

	/**
	 * 製造/包装指図作成処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	public List<Integer> createDirection(final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {

		// 製造指図
		int directionNum = importDirection(loginUserId, loginUserOrganizationCd);

		// 包装指図
		int pkgDirectionNum = importPkgDirection(loginUserId,
			loginUserOrganizationCd);

		List<Integer> numList = new ArrayList<Integer>();
		numList.add(new Integer(directionNum));
		numList.add(new Integer(pkgDirectionNum));

		return numList;
	}

	/**
	 * 製造指図作成処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	private int importDirection(final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {
		// 製造指図作成用データ取得
		List<AspImportAspOrder> aspOrderList = aspImportAspOrderDao
				.getDirectionData();
		int num = 0;

		for (AspImportAspOrder orderbean : aspOrderList) {
			// List<AspImportAspOperation> opeList = aspImportAspOperationDao
			// .getEntityByOrderCd(orderbean.getOrderCd());
			// 分割作業のうち指図作成されていない作業のみ取得
			List<AspImportAspOperation> opeList = aspImportAspOperationDao
					.getEntityByMunufuctOrderCd(orderbean.getOrderCd());

			// ホールドタンクNOを取得
			String holdTankNo = getHoldTankNo(opeList);

			for (AspImportAspOperation opeBean : opeList) {
				// 工程番号 が10(調合)以外のものは読み飛ばす
				if (!opeBean.getOperationProcNo().equals(new BigDecimal(10))) {
					continue;
				}

				DirectionHeader newHeadBean = createDirectionHeaderData(
					orderbean, opeBean, loginUserId, holdTankNo);

				// 指図ヘッダ登録
				insertHeader(newHeadBean);

				// 処方プロシージャから指図プロシージャにコピーし、指図プロシージャ登録
				copyRecipeProcedure(newHeadBean, false);

				// 処方フォーミュラから指図フォーミュラへコピーし、指図フォーミュラ登録
				copyRecipeFormula(newHeadBean, false);

				// 処方検査から指図検査へコピーし、指図検査登録
				copyRecipeInspection(newHeadBean);

				String errMsg = "errors.direction.stock.update";
				try {
					// 在庫更新処理
					StockinoutForDirection stock = new StockinoutForDirection(
							zaiCtrlDao);
					// 在庫更新－包装・製造指図入力
					if (!stock.entryDirection(newHeadBean
							.getDirectionDivision(), newHeadBean
							.getDirectionNo(), newHeadBean.getInputorCd())) {
						throw new LogicExceptionEx(errMsg);
					}
					// 在庫更新－配合指図入力
					if (!stock.entryFormula(newHeadBean.getDirectionDivision(),
						newHeadBean.getDirectionNo(), newHeadBean
								.getInputorCd())) {
						throw new LogicExceptionEx(errMsg);
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}
				num++;
			}
		}

		return num;
	}

	/**
	 * 包装指図作成処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	private int importPkgDirection(final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {

		// 包装指図作成用データ取得
		List<AspImportAspOrder> aspOrderList = aspImportAspOrderDao
				.getPkgDirectionData();
		int num = 0;

		for (AspImportAspOrder orderBean : aspOrderList) {
			List<AspImportAspOperation> opeList = aspImportAspOperationDao
					.getEntityByOrderCd(orderBean.getOrderCd());

			// 作業コード30or40以外がある場合は読み飛ばす
			if (!opeList.get(0).getOperationProcNo().equals(
				new BigDecimal("30"))
					&& !opeList.get(0).getOperationProcNo().equals(
						new BigDecimal("40"))) {
				continue;
			}

			// 登録データ作成
			List<DirectionHeader> newHeadList = createPkgDirectionHeaderData(
				orderBean, opeList, loginUserId);

			// 登録処理
			for (DirectionHeader newHeadBean : newHeadList) {

				// 予定数量が0のものは、指図発行しない
				if (newHeadBean.getPlanedQty().equals(new BigDecimal("0"))) {
					continue;
				}

				// 指図ヘッダ登録
				insertHeader(newHeadBean);
				// 処方プロシージャから指図プロシージャにコピーし、指図プロシージャ登録
				copyRecipeProcedure(newHeadBean, true);

				// 処方フォーミュラから指図フォーミュラへコピーし、指図フォーミュラ登録
				copyRecipeFormula(newHeadBean, true);

				// 処方検査から指図検査へコピーし、指図検査登録
				copyRecipeInspection(newHeadBean);

				// 指図詳細を作成し、登録
				insertPkgDirectionDetail(newHeadBean, orderBean);

				String errMsg = "errors.direction.stock.update";
				try {
					// 在庫更新処理
					StockinoutForDirection stock = new StockinoutForDirection(
							zaiCtrlDao);
					// 在庫更新－包装・製造指図入力
					if (!stock.entryDirection(newHeadBean
							.getDirectionDivision(), newHeadBean
							.getDirectionNo(), newHeadBean.getInputorCd())) {
						throw new LogicExceptionEx(errMsg);
					}
					// 在庫更新－配合指図入力
					if (!stock.entryFormula(newHeadBean.getDirectionDivision(),
						newHeadBean.getDirectionNo(), newHeadBean
								.getInputorCd())) {
						throw new LogicExceptionEx(errMsg);
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}
				num++;
			}
		}
		return num;
	}

	/**
	 * 処方プロシージャから指図プロシージャにコピーする
	 * @param head 指図ヘッダ登録データ
	 * @param pkgDirectionFlag 包装の場合はTrueで、製造はFalseで
	 */
	private void copyRecipeProcedure(final DirectionHeader head,
			final boolean pkgDirectionFlag) {
		List<AspImportRecipeProcedureList> list = aspImportRecipeProcedureListDao
				.getRecipeId(head.getRecipeId().toString());

		for (AspImportRecipeProcedureList bean : list) {
			DirectionProcedure proc = new DirectionProcedure();
			try {
				// フィールド名が同じものを移送
				IgnoreCaseBeanUtils.copyProperties(proc, bean);
				proc.setDirectionDivision(head.getDirectionDivision());
				proc.setDirectionNo(head.getDirectionNo());

				// 包装の場合
				if (pkgDirectionFlag) {
					proc.setStartDate(head.getPlanedSdate());
					proc.setEndDate(head.getPlanedEdate());
				}

				proc.setInputorCd(head.getInputorCd());
				proc.setInputDate(head.getInputDate());
				proc.setUpdatorCd(head.getUpdatorCd());
				proc.setUpdateDate(head.getUpdateDate());
				// 指図プロシージャに挿入
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
	 * @param pkgDirectionFlag 包装の場合はTrueで、製造はFalseで
	 * @return List<DirectionFormula>
	 */
	private List<DirectionFormula> copyRecipeFormula(
			final DirectionHeader head, final boolean pkgDirectionFlag) {
		// 登録対象データ
		List<DirectionFormula> newFormulaList = new ArrayList<DirectionFormula>();

		List<AspImportRecipeFormulaList> list = aspImportRecipeFormulaListDao
				.getRecipeId(head.getRecipeId().toString());
		BigDecimal stdQty = getFormul1001Qty(list); // 標準生産量

		// 品目詳細取得
		Item itemBean = itemDao.getEntity(head.getItemCd());
		String unitDiv = itemBean.getUnitOfOperationManagement();
		BigDecimal numberOfInsertions = itemBean.getNumberOfInsertions();

		// 数値桁数マスタの設定を取得
		NumberChkDisitDetail checkDetail = checkDigitUtilsLogic.getCheckDigit(
			AspImportConst.UNIT_DIVISION_HAIGO, null, null);

		// 端数区分からBigDecimalの丸めモードに変換
		RoundingMode round = checkDigitUtilsLogic.getRoundingMode(checkDetail
				.getRoundDivision().intValue());
		int decimalPoint = checkDetail.getSmallnumLength().intValue();

		// 仕込数量÷標準生産量の比で数量を倍する
		BigDecimal multiple = null;
		if (stdQty != null) {
			// multiple = head.getPlanedQty().divide(stdQty, decimalPoint,
			// round);
			multiple = head.getPlanedQty().divide(stdQty, Constants.SYOSU_KETA,
				round);

		} else {
			multiple = new BigDecimal(1);
		}

		for (AspImportRecipeFormulaList bean : list) {
			DirectionFormula formula = new DirectionFormula();
			try {
				// フィールド名が同じものを移送
				IgnoreCaseBeanUtils.copyProperties(formula, bean);

				// 製造の場合
				if (!pkgDirectionFlag) {
					if (AspImportRecipeFormulaListDao.LINE_NO_INT == bean
							.getLineNo().intValue()) {
						// 1001なので、仕込数量を設定
						formula.setQty(head.getPlanedQty());
					} else {
						// 仕込数量÷標準生産量の比で数量を倍する
						BigDecimal calc = bean.getQty().multiply(multiple)
								.setScale(decimalPoint, round);

						formula.setQty(calc);
					}
				}

				// 包装の場合
				if (pkgDirectionFlag) {
					if (AspImportConst.LINE_TYPE_COMBINE.equals(formula
							.getLineType())) {
						// 配合の場合

						// 比率を掛ける
						BigDecimal calc = bean.getQty().multiply(multiple)
								.setScale(decimalPoint, round);
						formula.setQty(calc);

					} else {
						// 仕上の場合

						BigDecimal fillQty = null;

						// 仕上の開始番号の場合の処理
						if (AspImportConst.LINE_NO_FINISH_START_NO
								.equals(formula.getLineNo())) {

							// 生産予定数量を仕上に設定
							formula.setQty(head.getPlanedQty());

							formula
									.setLineType(AspImportConst.LINE_TYPE_PRODUCT);
							formula.setItemCd(head.getItemCd());
							formula.setLotNo(head.getLotNo());

							// 充填予定数量 = 数量×品目マスタ.入数
							fillQty = formula.getQty().multiply(
								numberOfInsertions);
							// 丸め処理
							fillQty = checkDigitUtilsLogic.round(unitDiv, null,
								null, fillQty);
						} else {
							// 比率を掛ける
							BigDecimal calc = bean.getQty().multiply(multiple)
									.setScale(decimalPoint, round);
							formula.setQty(calc);

							// 充填予定数量 = 数量×品目マスタ.入数
							fillQty = formula.getQty().multiply(
								numberOfInsertions);
							// 丸め処理
							fillQty = checkDigitUtilsLogic.round(unitDiv, null,
								null, fillQty);
						}
						formula.setFillQty(fillQty);
					}
				}

				formula.setDirectionDivision(head.getDirectionDivision());
				formula.setDirectionNo(head.getDirectionNo());
				formula.setInputorCd(head.getInputorCd());
				formula.setInputDate(head.getInputDate());
				formula.setUpdatorCd(head.getUpdatorCd());
				formula.setUpdateDate(head.getUpdateDate());

				// 指図フォーミュラに追加
				insertDirectionFormula(formula);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return newFormulaList;
	}

	/**
	 * 処方検査から指図検査にコピーする
	 * @param head 指図ヘッダ登録データ
	 */
	private void copyRecipeInspection(final DirectionHeader head) {
		List<AspImportRecipeInspectionList> list = aspImportRecipeInspectionListDao
				.getRecipeId(head.getRecipeId().toString());

		for (AspImportRecipeInspectionList bean : list) {

			DirectionInspection inspect = new DirectionInspection();
			try {
				// フィールド名が同じものを移送
				IgnoreCaseBeanUtils.copyProperties(inspect, bean);
				inspect.setDirectionDivision(head.getDirectionDivision());
				inspect.setDirectionNo(head.getDirectionNo());
				inspect.setInputorCd(head.getInputorCd());
				inspect.setInputDate(head.getInputDate());
				inspect.setUpdatorCd(head.getUpdatorCd());
				inspect.setUpdateDate(head.getUpdateDate());
				// 指図検査に挿入
				insertDirectionInspection(inspect);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 指図詳細 作成
	 * @param head 指図ヘッダ登録データ
	 * @param orderBean ASPオーダ
	 */
	private void insertPkgDirectionDetail(final DirectionHeader headerBean,
			final AspImportAspOrder orderBean) {

		// List<AspImportDirectionHeader> directionNoBean =
		// aspImportDirectionHeaderDao
		// .getDirectionNoByOrderCd(orderBean.getOrderCd());

		if (orderBean.getManufactureOrderCd() != null) {
			// 包装に紐づく製造オーダコードをセミコロン区切りで分けて取得
			String[] manufactureOrderCdList = orderBean.getManufactureOrderCd()
					.split(";");

			// 製造オーダコードから指図ヘッダーを検索
			List<AspImportDirectionHeader> directionNoBean = aspImportDirectionHeaderDao
					.getDirectionNoByOrderCd(manufactureOrderCdList);

			if ((directionNoBean != null) && (!directionNoBean.isEmpty())) {
				for (int i = 0; i < directionNoBean.size(); i++) {
					AspImportDirectionHeader bean = directionNoBean.get(i);

					DirectionDetail newBean = new DirectionDetail();
					newBean.setPkgDirectionNo(headerBean.getDirectionNo());
					newBean.setRowNo(new BigDecimal(i + 1));
					newBean.setDirectionNo(bean.getDirectionNo());
					newBean.setInputDate(headerBean.getInputDate());
					newBean.setInputorCd(headerBean.getInputorCd());
					newBean.setUpdateDate(headerBean.getUpdateDate());
					newBean.setUpdatorCd(headerBean.getUpdatorCd());

					// 製造指図プロシージャに登録
					insertDirectionDetail(newBean);
				}
			}
		}
	}

	/**
	 * 指図ヘッダ登録処理を行う
	 * @param bean 登録対象ビーン
	 */
	private void insertHeader(final DirectionHeader bean) {
		try {
			// 指図ヘッダーに挿入
			int updateNum = directionHeaderDao.insert(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 指図プロシージャに挿入する
	 * @param bean 指図プロシージャデータ
	 */
	private void insertDirectionProcedure(final DirectionProcedure bean) {
		try {
			// 指図プロシージャに挿入
			int updateNum = directionProcedureDao.insert(bean);
			if (updateNum != 1) {
				// 排他エラー
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
	private void insertDirectionFormula(final DirectionFormula bean) {
		try {
			// 指図フォーミュラに挿入
			int updateNum = directionFormulaDao.insert(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 指図検査に挿入する。
	 * @param bean 指図検査データ
	 */
	private void insertDirectionInspection(final DirectionInspection bean) {
		try {
			// 指図検査に挿入
			int updateNum = directionInspectionDao.insert(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 指図詳細に挿入する。
	 * @param bean 指図詳細データ
	 */
	private void insertDirectionDetail(final DirectionDetail bean) {
		try {
			// 指図詳細に挿入
			int updateNum = directionDetailDao.insert(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 仕上げヘッダ(1001)行の標準生産量を取得する
	 * @param list 処方フォーミュラ配列
	 * @return 標準生産量
	 */
	private BigDecimal getFormul1001Qty(
			final List<AspImportRecipeFormulaList> list) {
		BigDecimal res = null;
		for (AspImportRecipeFormulaList bean : list) {
			if (AspImportRecipeFormulaListDao.LINE_NO_INT == bean.getLineNo()
					.intValue()) {
				res = bean.getQty();
			}
		}
		return res;
	}

	/**
	 * 購買外注データ作成処理
	 * @param AspImportAspOrder ビーン
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return PurchaseSubcontract 登録用ビーン
	 * @throws Exception 例外
	 */
	private PurchaseSubcontract setPurchaseSubcontract(
			final AspImportAspOrder bean, final String loginUserId,
			final String loginUserOrganizationCd) throws Exception {

		// 購買オーダー用ビーン
		PurchaseSubcontract newBean = new PurchaseSubcontract();

		// 品目
		newBean.setItemCd(bean.getItemCd());

		// 仕入先 、担当部署
		AspImportPurchaseAttributeQueue purAttBean = aspImportPurchaseAttributeQueueDao
				.getEntity(bean.getItemCd());
		if (purAttBean != null && purAttBean.getVenderCd() != null) {
			// 仕入先
			newBean.setVenderCd(purAttBean.getVenderCd());

			Vender vender = venderDao.getEntity(purAttBean.getVenderCd(), "SI");
			if (vender != null) {
				// 担当部署
				newBean.setChargeOrganizationCd(vender.getOrganizationCd());
			}
		}

		// ASPROVA発注重量
		newBean.setCheckQuantity(bean.getOrderQty());

		// *******************************************
		// 品目情報取得
		// *******************************************
		Item itemBean = itemDao.getEntity(bean.getItemCd());
		if (itemBean != null) {

			// KG換算係数(在庫)
			BigDecimal kg = itemBean.getKgOfFractionManagement();
			if (kg != null) {
				// KG換算係数が0の場合は1として計算
				if (kg.compareTo(BigDecimal.ZERO) == 0) {
					kg = BigDecimal.ONE;
				}

				if (itemBean.getTypeDivision().equals(new BigDecimal(6))
						|| itemBean.getTypeDivision().equals(new BigDecimal(7))) {
					// 外注製品の場合
					// 発注数量
					newBean.setOrderQuantity(bean.getOrderQty());
					// 発注重量
					newBean.setOrderConvertQuantity(bean.getOrderQty()
							.multiply(kg));
					// ASPROVA発注重量
					newBean.setCheckQuantity(bean.getOrderQty().multiply(kg));

				} else {
					// 外注製品以外の場合
					// 発注重量÷KG換算係数
					// BigDecimal orderQuantity = bean.getOrderQty().divide(kg,
					// BigDecimal.ROUND_UP);
					BigDecimal orderQuantity = bean.getOrderQty().divide(kg, 0,
						BigDecimal.ROUND_UP);
					// 丸め
					BigDecimal result = checker.round("SONOTA", "SI", newBean
							.getVenderCd(), orderQuantity);
					// 発注数量
					newBean.setOrderQuantity(result);

					// 発注重量(数量を切り上げするため、切り上げた結果×KG換算係数で計算しなおす)
					newBean.setOrderConvertQuantity(result.multiply(kg));
				}
			}

			// 納入先
			newBean.setLocationCd(itemBean.getDefaultLocation());

			// オーダー区分
			newBean.setOrderDivision(getOrderDivision(itemBean
					.getTypeDivision().intValue(), itemBean.getSpotDivision()));
		} else {
			// 取得できなかった場合の初期化。
			newBean.setOrderQuantity(new BigDecimal(0));
		}

		// 単価 //キーにNullがあるとヒットしない
		AspImportUnitprice unitBean = aspImportUnitpriceDao.getUnitprice(
			newBean.getItemCd(), newBean.getVenderCd(), newBean
					.getOrderQuantity());
		if (unitBean != null) {
			BigDecimal unitprice = unitBean.getUnitprice();
			newBean.setOrderUnitprice(unitprice);

			// 金額
			BigDecimal supplierOrdAmount = newBean.getOrderQuantity().multiply(
				unitprice); // 計算
			supplierOrdAmount = checker.round("SIKINGAKU", "SI", newBean
					.getVenderCd(), supplierOrdAmount); // 丸め
			newBean.setSupplierOrdAmount(supplierOrdAmount);

			// 単価区分
			if (unitBean.getUnitpriceDivision() != null) {
				newBean.setUnitpriceDefineunit(new BigDecimal(unitBean
						.getUnitpriceDivision()));
			}
		} else {
			// 取得できなかった場合の初期化。
			// newBean.setOrderQuantity(new BigDecimal(0));
			// 金額
			newBean.setSupplierOrdAmount(new BigDecimal(0));
		}
		// 納品希望日時
		Timestamp endTime = AecDateUtils.getTimestampYmdHmFormat(bean
				.getEndTime(), "yyyy/MM/dd HH:mm:ss");
		Timestamp suggestedDeliverlimitDate = getSuggestedDeliverlimitDate(endTime);
		newBean.setSuggestedDeliverlimitDate(suggestedDeliverlimitDate);

		// 発注日 startTimeの前日の0時00分00→取り込み日に変更
		// Timestamp startTime = AecDateUtils.getTimestampYmdHmFormat(bean
		// .getStartTime(), "yyyy/MM/dd HH:mm:ss");
		// Timestamp orderDate = getSuggestedDeliverlimitDate(startTime);
		// newBean.setOrderDate(orderDate);

		// newBean.setOrderDate(AecDateUtils.getCurrentTimestamp());
		// 時刻は消去し日付のみとする
		newBean.setOrderDate(AecDateUtils.getTimestampYmdFormat(AecDateUtils
				.dateFormat(AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd")));

		// 部署コード(null)
		newBean.setOrganizationCd(null);

		// 発注者(null)
		newBean.setTantoCd(null);

		// アスプローバオーダーコード
		newBean.setAspOrderNo(bean.getOrderCd());

		// 外注原材料区分 アスプローバ取込ではNull固定
		newBean.setMaterialDivision(null);

		newBean.setStatus(PurchaseStatus.ASPROVA); // ステータス(1)
		newBean.setOrderSheeFlag(AspImportConst.ORDER_SHEET_FLG); // 発注書発行フラグ(0)
		newBean.setReplyContentsDivision(AspImportConst.REPLY_CONTENT_DIVISION); // 分納区分(0)
		newBean.setLabelFlag(AspImportConst.LABEL_FLG); // ラベル発行フラグ(0)

		// 備考取得
		setRemark(newBean);

		// 発注番号発番
		String buySubcontractOrderNo = getNumberLogic
				.getBuySubcontractOrderNo();
		newBean.setBuySubcontractOrderNo(buySubcontractOrderNo); // 発注番号

		newBean.setInputorCd(loginUserId); // 登録者
		newBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		newBean.setUpdatorCd(loginUserId); // 更新者
		newBean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時

		return newBean;
	}

	/**
	 * 指図ヘッダー登録情報作成
	 * @param AspImportAspOrder ASP＿ORDERデータ
	 * @param AspImportAspOperation ASP_OPERATIONデータ
	 * @param loginUserId ログイン者のユーザーコード
	 * @return DirectionHeader
	 */
	private DirectionHeader createDirectionHeaderData(
			final AspImportAspOrder orderBean,
			final AspImportAspOperation opeBean, final String loginUserId,
			final String holdTankNo) {
		Timestamp now = AecDateUtils.getCurrentTimestamp();

		// 登録用ビーン作成
		DirectionHeader bean = new DirectionHeader();

		// 指図区分
		bean.setDirectionDivision(AspImportConst.DIRECTION_DIVISION);

		// アスプローバオーダコード
		// bean.setAspOrderNo(orderBean.getOrderCd());
		// アスプローバオーダコード（製造指図は作業コードをセット）
		bean.setAspOrderNo(opeBean.getOperationCd());

		// 指図日時 システム日時を設定
		bean.setDirectionDate(now);
		// 指図ステータス
		bean.setDirectionStatus(AspImportConst.DIRECTION_STATUS_UN_CONFIRMED);

		if (orderBean.getRecipeId() != null) {
			// レシピヘッダ取得
			RecipeHeader rHeadBean = recipeHeaderDao.getEntity(orderBean
					.getRecipeId());
			// レシピインデックス
			bean.setRecipeId(rHeadBean.getRecipeId());
			// レシピコード
			bean.setRecipeCd(rHeadBean.getRecipeCd());
			// レシピversion
			bean.setRecipeVersion(rHeadBean.getRecipeVersion());
			// 生産工場
			bean.setProductionLine(rHeadBean.getProductionLine());
			// 備考
			bean.setRemark(rHeadBean.getRecipeMemo());
			// 注釈
			bean.setNotes(rHeadBean.getRecipeDescription());
		}

		// 調合タンクNO
		bean.setCompoundTankNo(opeBean.getMainRes());
		// ホールドタンクNO
		bean.setHoldTankNo(holdTankNo);

		// 予備溶解タンクNO
		bean.setDissolutionTankNo(null);
		// 品目コード
		bean.setItemCd(orderBean.getItemCd());
		// 仕込み予定数量
		bean.setPlanedQty(opeBean.getQty());
		// 製造開始予定日時
		bean.setPlanedSdate(AecDateUtils.getTimestampYmdHmFormat(opeBean
				.getStartTime(), "yyyy/MM/dd HH:mm:ss"));
		// 製造終了予定日時
		bean.setPlanedEdate(AecDateUtils.getTimestampYmdHmFormat(opeBean
				.getEndTime(), "yyyy/MM/dd HH:mm:ss"));

		// 指図書発行フラグ
		bean.setStampFlag(AspImportConst.STAMP_FLAG_UN_ISSUANCE);
		// 製品ラベル発行フラグ
		bean.setProductLabelFlag(AspImportConst.PRODUCT_LABEL_FLAG_UN_ISSUANCE);
		// 製造/包装記録発行フラグ
		bean
				.setProductRecordFlag(AspImportConst.PRODUCT_RECORD_FLAG_UN_ISSUANCE);
		// 予備溶解ラベル発行フラグ
		bean
				.setStockdissLabelFlag(AspImportConst.STOCKDISS_LABEL_FLAG_UN_ISSUANCE);

		// 指図番号を発番
		bean.setDirectionNo(getNumberLogic.getDirectionNo(bean.getInputDate()));

		// 登録日時
		bean.setInputDate(now);
		// 登録者
		bean.setInputorCd(loginUserId);
		// 更新日時
		bean.setUpdateDate(now);
		// 更新者
		bean.setUpdatorCd(loginUserId);

		return bean;
	}

	/**
	 * 包装ヘッダー作成
	 * @param AspImportAspOrder ASP＿ORDERデータ
	 * @param AspImportAspOperation ASP_OPERATIONデータ
	 * @param loginUserId ログイン者のユーザーコード
	 * @return DirectionHeader
	 */
	private List<DirectionHeader> createPkgDirectionHeaderData(
			final AspImportAspOrder orderBean,
			final List<AspImportAspOperation> opeList, final String loginUserId) {
		// 日付分割処理を行った包装ヘッダデータ
		List<DirectionHeader> newBeanList = dividePkgDirectionHeaderDate(
			orderBean, opeList);

		for (DirectionHeader newBean : newBeanList) {
			// 予定数量が0のものは、指図発行しない
			if (newBean.getPlanedQty().equals(new BigDecimal(0))) {
				continue;
			}
			Timestamp now = AecDateUtils.getCurrentTimestamp();
			// 指図区分 1(充填・包装指図)固定
			newBean
					.setDirectionDivision(AspImportConst.DIRECTION_DIVISION_PACK);
			// アスプローバオーダコード
			newBean.setAspOrderNo(orderBean.getOrderCd());
			// 指図日時 (今)
			newBean.setDirectionDate(now);
			// 指図ステータス 1(未確定)固定
			newBean.setDirectionStatus(BigDecimal.ONE);

			if (orderBean.getRecipeId() != null) {
				// レシピヘッダ取得
				RecipeHeader rHeadBean = recipeHeaderDao.getEntity(orderBean
						.getRecipeId());
				// レシピインデックス
				newBean.setRecipeId(rHeadBean.getRecipeId());
				// レシピコード
				newBean.setRecipeCd(rHeadBean.getRecipeCd());
				// レシピversion
				newBean.setRecipeVersion(rHeadBean.getRecipeVersion());
				// 生産工場
				newBean.setProductionLine(rHeadBean.getProductionLine());
				// 備考
				newBean.setRemark(rHeadBean.getRecipeMemo());
				// 注釈
				newBean.setNotes(rHeadBean.getRecipeDescription());
			}

			// 包装ライン 日付分割処理にて設定済み
			// newBean.setPackageLine();
			// 品目コード
			newBean.setItemCd(orderBean.getItemCd());
			// 生産予定数量 日付分割処理にて設定済み
			// newBean.setPlanedQty();
			// ロット番号
			newBean.setLotNo(null);
			// 指図書発行フラグ 未発行
			newBean.setStampFlag(BigDecimal.ZERO);
			// 製品ラベル発行フラグ 未発行
			newBean.setProductLabelFlag(BigDecimal.ZERO);
			// 製造/包装記録発行フラグ 未発行
			newBean.setProductRecordFlag(BigDecimal.ZERO);
			// 予備溶解ラベル発行フラグ 未発行
			newBean.setStockdissLabelFlag(BigDecimal.ZERO);

			// 指図番号発番
			String pkgDirectionNo = getNumberLogic.getPkgDirectionNo(now);
			// 指図番号設定
			newBean.setDirectionNo(pkgDirectionNo);

			// 登録日時
			newBean.setInputDate(now);
			// 登録者
			newBean.setInputorCd(loginUserId);
			// 更新日時
			newBean.setUpdateDate(now);
			// 更新者
			newBean.setUpdatorCd(loginUserId);
		}
		return newBeanList;
	}

	/**
	 * 包装ヘッダ作成用 日付分割処理
	 * @param AspImportAspOrder ASP＿ORDERデータ
	 * @param AspImportAspOperation ASP_OPERATIONデータ
	 * @param loginUserId ログイン者のユーザーコード
	 * @return DirectionHeader
	 */
	private List<DirectionHeader> dividePkgDirectionHeaderDate(
			final AspImportAspOrder orderBean,
			final List<AspImportAspOperation> opeList) {
		Timestamp startTime = new Timestamp(0);
		Timestamp endTime = new Timestamp(0);
		String packageLine = null;
		BigDecimal qty = new BigDecimal(0);

		// 登録用データリスト
		List<DirectionHeader> newBeanList = new ArrayList<DirectionHeader>();

		// ***********************************************************
		// 製造開始日時、製造終了日時を取得し
		// 登録用の包装ヘッダデータを作成
		// ***********************************************************
		// 製造開始日時、製造終了日時を取得
		for (AspImportAspOperation opeBean : opeList) {
			if (opeBean.getOperationProcNo().equals(AspImportConst.PROC_NO_30)) {
				// 製造開始日時取得
				startTime = AecDateUtils.getTimestampYmdHmFormat(opeBean
						.getStartTime(), "yyyy/MM/dd HH:mm:ss");
				// 製造終了日時取得
				endTime = AecDateUtils.getTimestampYmdHmFormat(opeBean
						.getEndTime(), "yyyy/MM/dd HH:mm:ss");
				// 包装ライン取得
				packageLine = opeBean.getMainRes();

				if (opeBean.getQty() != null) {
					// 予定数量を取得
					qty = qty.add(opeBean.getQty());
				}
			}

			// 40(包装)がある場合はendTimeはこっちを使う
			if (opeBean.getOperationProcNo().equals(AspImportConst.PROC_NO_40)) {
				// 製造終了日時取得
				endTime = AecDateUtils.getTimestampYmdHmFormat(opeBean
						.getEndTime(), "yyyy/MM/dd HH:mm:ss");

				if (opeBean.getQty() != null) {
					// 予定数量を取得
					qty = qty.add(opeBean.getQty());
				}
			}
		}

		// 指図を分割するか、しないかの判定。
		// 製造開始時間と製造終了時間で日付が変わっていたら
		if (!AecDateUtils.dateFormat(startTime, "yyyy/MM/dd").equals(
			AecDateUtils.dateFormat(endTime, "yyyy/MM/dd"))) {
			// *************************************************************************
			// 分割処理
			// 予定数量は、分割した時間の割合で計算
			// *************************************************************************

			// 時間分割取得用のツリーマップ ソート条件は省略(アスキーでソート)
			SortedMap<String, String> map = new TreeMap<String, String>();

			// 作業使用指図_ASPROVAデータ取得
			List<AspImportAspUseinstruction> useInstList = aspImportAspUseinstructionDao
					.getEntityByOrderCd(orderBean.getOrderCd());
			for (AspImportAspUseinstruction useInstBean : useInstList) {
				// 各作業の開始、終了を取得
				String timeArray = useInstBean.getTimeArray();
				// ；で分割
				String[] timeArrayList = timeArray.split(";");
				// 分割したもので、開始と終了をワンセットに。；でつなげる
				// ソートに使用するため
				for (int i = 0; timeArrayList.length > i; i = i + 2) {
					String time = timeArrayList[i] + ";" + timeArrayList[i + 1];
					// マップに追加
					map.put(time, time);
				}
			}

			// ソートされたマップの頭から取得する。
			Timestamp sTimeTemp = null;
			Timestamp eTimeTemp = null;
			for (Iterator ite = map.keySet().iterator(); ite.hasNext();) {
				String key = (String) ite.next();
				String strTime = map.get(key);

				String[] strSplitTime = strTime.split(";");
				Timestamp sTime = AecDateUtils.getTimestampYmdHmFormat(
					strSplitTime[0], "yyyy/MM/dd HH:mm:ss");
				Timestamp eTime = AecDateUtils.getTimestampYmdHmFormat(
					strSplitTime[1], "yyyy/MM/dd HH:mm:ss");

				// Nullだったら設定
				if (sTimeTemp == null) {
					sTimeTemp = sTime;
				}
				// スタートとエンドで日付が違っていたら分割
				if (!AecDateUtils.dateFormat(sTime, "yyyy/MM/dd").equals(
					AecDateUtils.dateFormat(eTime, "yyyy/MM/dd"))) {
					eTimeTemp = AecDateUtils.getTimestampYmdHmFormat(
						AecDateUtils.dateFormat(sTime, "yyyy/MM/dd") + " "
								+ "23:59:59", "yyyy/MM/dd HH:mm:ss");

					// 指図作成
					newBeanList.add(setTimeToDirectionHeaderBean(sTimeTemp,
						eTimeTemp, packageLine));

					// 次のスタート時間を設定
					sTimeTemp = AecDateUtils.getTimestampYmdHmFormat(
						AecDateUtils.dateFormat(eTime, "yyyy/MM/dd") + " "
								+ "00:00:00", "yyyy/MM/dd HH:mm:ss");

					// 次のスタートと違っていたら
				} else if (!AecDateUtils.dateFormat(sTimeTemp, "yyyy/MM/dd")
						.equals(AecDateUtils.dateFormat(sTime, "yyyy/MM/dd"))) {

					// 指図作成
					newBeanList.add(setTimeToDirectionHeaderBean(sTimeTemp,
						eTimeTemp, packageLine));

					// 次のスタートを設定
					sTimeTemp = sTime;
				}
				eTimeTemp = eTime;
			}
			// 指図作成
			newBeanList.add(setTimeToDirectionHeaderBean(sTimeTemp, eTimeTemp,
				packageLine));

			// ***********************************************
			// 数量設定処理
			// (時間の割合にて、数量設定)
			// (整数部のみで小数第1位で四捨五入)
			// (時間は分単位で割合計算)
			// ***********************************************
			long sumTime = 0;
			// 合計計算
			for (DirectionHeader newHeadBean : newBeanList) {
				sumTime = sumTime
						+ ((newHeadBean.getPlanedEdate().getTime()
								/ AspImportConst.MILLISECOND / AspImportConst.SECOND) - (newHeadBean
								.getPlanedSdate().getTime()
								/ AspImportConst.MILLISECOND / AspImportConst.SECOND));
			}
			// 最後の1つを除いて 比率から割合計算
			int i = 0;
			BigDecimal sumPlanedQty = new BigDecimal(0);

			for (i = 0; i < newBeanList.size() - 1; i++) {
				DirectionHeader headBean = newBeanList.get(i);
				// 時間を取得
				long time = (headBean.getPlanedEdate().getTime()
						/ AspImportConst.MILLISECOND / AspImportConst.SECOND)
						- (headBean.getPlanedSdate().getTime()
								/ AspImportConst.MILLISECOND / AspImportConst.SECOND);
				// 割合を求
				double multiple = time / 1.0 / sumTime;

				// 割合から予定数量を計算
				double pQty = qty.longValue() * multiple;
				// BigDecimalに
				BigDecimal planedQty = new BigDecimal(pQty);
				// 丸め 整数部のみ
				planedQty = planedQty.setScale(0, AspImportConst.ROUND_HALF_UP);

				headBean.setPlanedQty(planedQty);

				// 最後のために合計する
				sumPlanedQty = sumPlanedQty.add(headBean.getPlanedQty());
			}
			// 最後は全体数量の残り
			newBeanList.get(i).setPlanedQty(qty.subtract(sumPlanedQty));

			// 分割をしなくていい場合はそのまま追加
		} else {
			DirectionHeader newHeadBean = setTimeToDirectionHeaderBean(
				startTime, endTime, packageLine);
			// 単価設定(分割なしのためそのまま設定)
			newHeadBean.setPlanedQty(qty);
			newBeanList.add(newHeadBean);
		}
		return newBeanList;
	}

	/**
	 * 指図ヘッダ作成(時間、包装、予定数量のみ設定)
	 * @param Timestamp sDate
	 * @param Timestamp eDate
	 * @param String packageLine
	 * @return DirectionHeader
	 */
	private DirectionHeader setTimeToDirectionHeaderBean(final Timestamp sDate,
			final Timestamp eDate, final String packageLine) {

		DirectionHeader headBean = new DirectionHeader();
		headBean.setPlanedSdate(sDate);
		headBean.setPlanedEdate(eDate);
		// 包装ライン
		headBean.setPackageLine(packageLine);

		return headBean;
	}

	/**
	 * 備考取得
	 * @param PurchaseSubcontract
	 */
	private void setRemark(final PurchaseSubcontract pSubBean) {

		List<AspImportRemarkList> list = aspImportRemarkListDao.getRemarkList(
			pSubBean.getVenderCd(), pSubBean.getLocationCd(), pSubBean
					.getItemCd());
		if (list.isEmpty()) {
			return;
		}

		// *******************************
		// 取得した備考を発注書用備考と備考にセット
		// *******************************
		StringBuffer sbfOrderSheetRemark = new StringBuffer();
		StringBuffer sbfRemark = new StringBuffer();
		// 取得した備考を全てセット、区切りとして改行を追加
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRemark13() != null) {
				if (sbfOrderSheetRemark.length() != 0) {
					sbfOrderSheetRemark.append(System
							.getProperty("line.separator"));
				}
				sbfOrderSheetRemark.append(list.get(i).getRemark13());
			}
			if (list.get(i).getRemark12() != null) {
				if (sbfRemark.length() != 0) {
					sbfRemark.append(System.getProperty("line.separator"));
				}
				sbfRemark.append(list.get(i).getRemark12());
			}
		}

		pSubBean.setOrderSheetRemark(sbfOrderSheetRemark.toString());
		pSubBean.setRemark(sbfRemark.toString());
	}

	/**
	 * オーダー区分、条件選択
	 * @param type
	 * @param spot
	 * @return orderDivision
	 * @throws Exception
	 */
	private BigDecimal getOrderDivision(final int type, final BigDecimal spot)
			throws Exception {

		BigDecimal orderDivision = new BigDecimal(0);

		if (spot.compareTo(AspImportConst.SPOT2_DIVISION) == 0) {
			orderDivision = AspImportConst.ORDER4_DIVISION; // オーダー区分 4：スポット品
		} else if (spot.compareTo(AspImportConst.SPOT1_DIVISION) == 0) {
			switch (type) {
			case AspImportConst.TYPE1_DIVISION:
				orderDivision = AspImportConst.ORDER1_DIVISION; // オーダー区分 1:原材料
				break;
			case AspImportConst.TYPE2_DIVISION:
				orderDivision = AspImportConst.ORDER1_DIVISION; // オーダー区分 1:原材料
				break;
			case AspImportConst.TYPE4_DIVISION:
				orderDivision = AspImportConst.ORDER5_DIVISION; // オーダー区分
				// 5：仕入直送品
				break;
			case AspImportConst.TYPE5_DIVISION:
				orderDivision = AspImportConst.ORDER6_DIVISION; // オーダー区分
				// 6：仕入在庫品
				break;
			case AspImportConst.TYPE6_DIVISION:
				orderDivision = AspImportConst.ORDER2_DIVISION; // オーダー区分
				// 2：外注製品（直送
				break;
			case AspImportConst.TYPE7_DIVISION:
				orderDivision = AspImportConst.ORDER3_DIVISION; // オーダー区分
				// 3：外注製品（非直送
				break;
			default:
				break;
			}
		}
		return orderDivision;
	}

	/**
	 * endTimeから納品希望日時を取得 endTime の前日の0:00を返す
	 * 
	 * @param endTime endTime
	 * @return suggestedDelDate
	 */
	private Timestamp getSuggestedDeliverlimitDate(final Timestamp endTime) {
		if (endTime == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(endTime.getTime());
		cal.add(Calendar.DAY_OF_MONTH, -1); // 日付-1
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
		String strEndTime = df.format(cal.getTime());
		Timestamp suggestedDelDate = AecDateUtils.getTimestampYmdHmFormat(
			strEndTime, "yyyy/MM/dd HH:mm:ss");

		return suggestedDelDate;
	}

	/**
	 * ホールドタンクNOを取得 工程番号が20(移送)の物の主資源コード (20が複数ある場合は先頭の物のみを返す) (20がない場合はNullを返す)
	 * @param final List<AspImportAspOperation> opeList
	 * @return String
	 */
	private String getHoldTankNo(final List<AspImportAspOperation> opeList) {
		String holdTankNo = null;

		for (AspImportAspOperation opeBean : opeList) {
			if (opeBean.getOperationProcNo().equals(AspImportConst.PROC_NO_20)) {
				holdTankNo = opeBean.getMainRes();
			}
		}
		return holdTankNo;
	}

	/* -------------------- setter -------------------- */
	/**
	 * aspImportPurchaseSubcontractDaoを設定します。
	 * @param aspImportPurchaseSubcontractDao aspImportPurchaseSubcontractDao
	 */
	public void setAspImportPurchaseSubcontractDao(
			final AspImportPurchaseSubcontractDao aspImportPurchaseSubcontractDao) {
		this.aspImportPurchaseSubcontractDao = aspImportPurchaseSubcontractDao;
	}

	/**
	 * aspImportAspOrderDaoを設定します。
	 * @param aspImportAspOrderDao aspImportAspOrderDao
	 */
	public void setAspImportAspOrderDao(
			final AspImportAspOrderDao aspImportAspOrderDao) {
		this.aspImportAspOrderDao = aspImportAspOrderDao;
	}

	/**
	 * purchaseSubcontractDaoを設定します。
	 * @param purchaseSubcontractDao purchaseSubcontractDao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * checkerを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * aspImportPurchaseAttributeQueueDaoを設定します。
	 * @param aspImportPurchaseAttributeQueueDao
	 *            aspImportPurchaseAttributeQueueDao
	 */
	public void setAspImportPurchaseAttributeQueueDao(
			final AspImportPurchaseAttributeQueueDao aspImportPurchaseAttributeQueueDao) {
		this.aspImportPurchaseAttributeQueueDao = aspImportPurchaseAttributeQueueDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * aspImportUnitpriceDaoを設定します。
	 * @param aspImportUnitpriceDao aspImportUnitpriceDao
	 */
	public void setAspImportUnitpriceDao(
			final AspImportUnitpriceDao aspImportUnitpriceDao) {
		this.aspImportUnitpriceDao = aspImportUnitpriceDao;
	}

	/**
	 * purchaseMateInjectionDaoを設定します。
	 * @param purchaseMateInjectionDao purchaseMateInjectionDao
	 */
	public void setPurchaseMateInjectionDao(
			final PurchaseMateInjectionDao purchaseMateInjectionDao) {
		this.purchaseMateInjectionDao = purchaseMateInjectionDao;
	}

	/**
	 * recipeHeaderDaoを設定します。
	 * @param recipeHeaderDao recipeHeaderDao
	 */
	public void setRecipeHeaderDao(final RecipeHeaderDao recipeHeaderDao) {
		this.recipeHeaderDao = recipeHeaderDao;
	}

	/**
	 * aspImportAspOperationDaoを設定します。
	 * @param aspImportAspOperationDao aspImportAspOperationDao
	 */
	public void setAspImportAspOperationDao(
			final AspImportAspOperationDao aspImportAspOperationDao) {
		this.aspImportAspOperationDao = aspImportAspOperationDao;
	}

	/**
	 * directionHeaderDaoを設定します。
	 * @param directionHeaderDao directionHeaderDao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * aspImportRecipeProcedureListDaoを設定します。
	 * @param aspImportRecipeProcedureListDao aspImportRecipeProcedureListDao
	 */
	public void setAspImportRecipeProcedureListDao(
			final AspImportRecipeProcedureListDao aspImportRecipeProcedureListDao) {
		this.aspImportRecipeProcedureListDao = aspImportRecipeProcedureListDao;
	}

	/**
	 * directionProcedureDaoを設定します。
	 * @param directionProcedureDao directionProcedureDao
	 */
	public void setDirectionProcedureDao(
			final DirectionProcedureDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}

	/**
	 * aspImportRecipeFormulaListDaoを設定します。
	 * @param aspImportRecipeFormulaListDao aspImportRecipeFormulaListDao
	 */
	public void setAspImportRecipeFormulaListDao(
			final AspImportRecipeFormulaListDao aspImportRecipeFormulaListDao) {
		this.aspImportRecipeFormulaListDao = aspImportRecipeFormulaListDao;
	}

	/**
	 * checkDigitUtilsLogicを設定します。
	 * @param checkDigitUtilsLogic checkDigitUtilsLogic
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * directionFormulaDaoを設定します。
	 * @param directionFormulaDao directionFormulaDao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * aspImportRecipeInspectionListDaoを設定します。
	 * @param aspImportRecipeInspectionListDao aspImportRecipeInspectionListDao
	 */
	public void setAspImportRecipeInspectionListDao(
			final AspImportRecipeInspectionListDao aspImportRecipeInspectionListDao) {
		this.aspImportRecipeInspectionListDao = aspImportRecipeInspectionListDao;
	}

	/**
	 * directionInspectionDaoを設定します。
	 * @param directionInspectionDao directionInspectionDao
	 */
	public void setDirectionInspectionDao(
			final DirectionInspectionDao directionInspectionDao) {
		this.directionInspectionDao = directionInspectionDao;
	}

	/**
	 * aspImportAspUseinstructionDaoを設定します。
	 * @param aspImportAspUseinstructionDao aspImportAspUseinstructionDao
	 */
	public void setAspImportAspUseinstructionDao(
			final AspImportAspUseinstructionDao aspImportAspUseinstructionDao) {
		this.aspImportAspUseinstructionDao = aspImportAspUseinstructionDao;
	}

	/**
	 * aspImportDirectionHeaderDaoを設定します。
	 * @param aspImportDirectionHeaderDao aspImportDirectionHeaderDao
	 */
	public void setAspImportDirectionHeaderDao(
			final AspImportDirectionHeaderDao aspImportDirectionHeaderDao) {
		this.aspImportDirectionHeaderDao = aspImportDirectionHeaderDao;
	}

	/**
	 * directionDetailDaoを設定します。
	 * @param directionDetailDao directionDetailDao
	 */
	public void setDirectionDetailDao(
			final DirectionDetailDao directionDetailDao) {
		this.directionDetailDao = directionDetailDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * aspImportRemarkListDaoを設定します。
	 * @param aspImportRemarkListDao aspImportRemarkListDao
	 */
	public void setAspImportRemarkListDao(
			final AspImportRemarkListDao aspImportRemarkListDao) {
		this.aspImportRemarkListDao = aspImportRemarkListDao;
	}

}
