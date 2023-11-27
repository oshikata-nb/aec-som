/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.SelectDirectionListStatus;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedure;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedureDao;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventoryDao;
import com.asahikaseieng.dao.entity.keikakuseizo.KeikakuSeizo;
import com.asahikaseieng.dao.entity.keikakuseizo.KeikakuSeizoDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.sijiseizo.SijiSeizo;
import com.asahikaseieng.dao.entity.sijiseizo.SijiSeizoDao;
import com.asahikaseieng.dao.nonentity.comboboxes.direction.DirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.direction.DirectionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.direction.DirectionKeikakuSeizoDetail;
import com.asahikaseieng.dao.nonentity.direction.DirectionKeikakuSeizoDetailDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionProcedureFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionProcedureFormulaListDao;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderDetailListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderFormulaListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderInspectionListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListForReportDao;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderProcedureListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderProcedureListForReportDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造指図検索 ロジック実装クラス
 * @author tosco
 */
public class DirectionListLogicImpl implements DirectionListLogic {

	/** 原材料名でフォーミュラから取得する工程コード配列 */
	private static final String[] SYORI_NAME_ITEM_NAME_OPERATION_CD = {"21",
			"22", "24", "26", "27", "28"};

	/** 製造指図－タンクNo.用工程コード配列 */
	private static final String[][] OPERATION_CD_TANK_NO = { {"27", "1"},
			{"28", "1"}, {"31", "1"}, {"32", "1"}, {"33", "1"}, {"34", "1"},
			{"35", "1"}, {"36", "1"}, {"21", "2"}, {"22", "2"}, {"24", "2"},
			{"26", "2"}};

	/** 製造指図－タンクNo.用工程コード格納Map */
	private static Map<String, String> tankOperationCdMap;

	/** 製造指図－設定値用工程コード配列 */
	private static final String[][] OPERATION_CD_SETTEI = { {"03", "1"},
			{"05", "1"}, {"07", "1"}, {"46", "1"}, {"47", "1"}, {"11", "2"},
			{"13", "2"}, {"21", "3"}, {"22", "3"}, {"24", "3"}, {"26", "3"},
			{"51", "3"}, {"52", "3"}, {"53", "3"}, {"27", "4"}, {"28", "4"},
			{"96", "4"}, {"45", "5"}, {"23", "7"}, {"25", "7"}, {"54", "7"},
			{"55", "7"}, {"56", "7"}};

	/** 製造指図－設定値用工程コード格納Map */
	private static Map<String, Command> setteiOperationCdMap;
	static {
		// 製造指図－タンクNo.用
		tankOperationCdMap = new HashMap<String, String>();
		for (String[] s : OPERATION_CD_TANK_NO) {
			tankOperationCdMap.put(s[0], s[1]);
		}
		// 製造指示-設定値用
		setteiOperationCdMap = new HashMap<String, Command>();
		Command p1 = new Pattern1();
		Command p2 = new Pattern2();
		Command p3 = new Pattern3();
		Command p4 = new Pattern4();
		Command p5 = new Pattern5();
		Command p6 = new Pattern6();
		Command p7 = new Pattern7();
		for (String[] s : OPERATION_CD_SETTEI) {
			int mode = Integer.parseInt(s[1]);
			Command command = null;
			switch (mode) {
			case 1:
				command = p1;
				break;
			case 2:
				command = p2;
				break;
			case 3:
				command = p3;
				break;
			case 4:
				command = p4;
				break;
			case 5:
				command = p5;
				break;
			case 7:
				command = p7;
				break;
			default:
				command = p6;
				break;
			}
			setteiOperationCdMap.put(s[0], command);
		}
		setteiOperationCdMap.put("OTHER", p6);
	}

	/** 生産ライン操作用DAO */
	private DirectionLineForComboboxesDao directionLineForComboboxesDao;

	/** 製造指図ヘッダ操作用DAO */
	private DirectionDirectionHeaderListDao directionDirectionHeaderListDao;

	/** 製造計画操作用DAO */
	private KeikakuSeizoDao keikakuSeizoDao;

	/** 製造指示操作用DAO */
	private SijiSeizoDao sijiSeizoDao;

	/** 製造指図フォーミュラ操作用DAO */
	private DirectionDirectionFormulaListDao directionDirectionFormulaListDao;

	/** 製造計画操作用DAO */
	private DirectionKeikakuSeizoDetailDao directionKeikakuSeizoDetailDao;

	/** 製造プロシージャy・フォーミュラ操作用DAO */
	private DirectionProcedureFormulaListDao directionProcedureFormulaListDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 製造工程Dao */
	private DirectionProcedureDao directionProcedureDao;

	/** 製造配合Dao */
	private DirectionFormulaDao directionFormulaDao;

	/** 洗浄工程コード */
	private static final String[] SENJOARI = {"23", "25", "54", "55", "56"};

	/** 水使用工程コード */
	private static final String[] MIZU = {"51", "52", "53"};

	/** 水品目コード */
	private static final String[] WATER_ITEMS = {"01777777", "01333333",
			"01444444"};

	private ItemInventoryDao itemInventoryDao;

	private ItemDao itemDao;

	private DirectionOrderListForReportDao directionOrderListForReportDao;

	private DirectionOrderDetailListForReportDao directionOrderDetailListForReportDao;

	private DirectionOrderProcedureListForReportDao directionOrderProcedureListForReportDao;

	private DirectionOrderFormulaListForReportDao directionOrderFormulaListForReportDao;

	private DirectionOrderInspectionListForReportDao directionOrderInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public DirectionListLogicImpl() {
	}

	/**
	 * 検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<MgrecipeList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<DirectionDirectionHeaderList> getSearchList(
			final DirectionDirectionHeaderListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<DirectionDirectionHeaderList> list = directionDirectionHeaderListDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderListForReport> 検索結果リスト
	 */
	public List<DirectionOrderListForReport> getHeaderReportList(
			final DirectionOrderListConditionForReport condition) {
		List<DirectionOrderListForReport> list = directionOrderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderDetailListForReport> 検索結果リスト
	 */
	public List<DirectionOrderDetailListForReport> getDetailReportList(
			final DirectionOrderListConditionForReport condition) {
		List<DirectionOrderDetailListForReport> list = directionOrderDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderDetailListForReport> 検索結果リスト
	 */
	public List<DirectionOrderProcedureListForReport> getProcedureReportList(
			final DirectionOrderListConditionForReport condition) {

		List<DirectionOrderProcedureListForReport> list = directionOrderProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderFormulaListForReport> 検索結果リスト
	 */
	public List<DirectionOrderFormulaListForReport> getFormulaReportList(
			final DirectionOrderListConditionForReport condition) {

		List<DirectionOrderFormulaListForReport> list = directionOrderFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderInspectionListForReport> 検索結果リスト
	 */
	public List<DirectionOrderInspectionListForReport> getInspectionReportList(
			final DirectionOrderListConditionForReport condition) {

		List<DirectionOrderInspectionListForReport> list = directionOrderInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<MgrecipeLine>
	 */
	public List<DirectionLineForComboboxes> getAllLines() {
		return directionLineForComboboxesDao.findAll();
	}

	/**
	 * 生産ラインコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createLineCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 生産ライン検索
		List<DirectionLineForComboboxes> lineList = getAllLines();
		for (DirectionLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}

		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(DirectionConst.COMBO_ALL_VALUE);
		allItem.setLabales(DirectionConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 製造指図ヘッダの更新を行う。
	 * @param bean データ
	 * @throws DirectionLogicException 更新エラーの場合
	 */
	public void updateDirectionHeader(final DirectionDirectionHeaderList bean)
			throws DirectionLogicException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}
		try {
			// ヘッダを更新
			int updateNum = directionDirectionHeaderListDao.update(bean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				String errMsg = "errors.direction.nodata.deleted.direction.no";
				throw new DirectionLogicException(errMsg, bean.getDirectionNo());
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			String errMsg = "errors.direction.optimisticlock.data.direction.no";
			throw new DirectionLogicException(errMsg, bean.getDirectionNo());
		}
	}

	/**
	 * 製造計画の登録を行う。
	 * @param bean データ
	 */
	public void insertKeikakuSeizo(final KeikakuSeizo bean) {
		// 製造計画を更新
		int updateNum = keikakuSeizoDao.insert(bean);
		if (updateNum != 1) {
			// 更新エラー
			throw new SQLRuntimeException(null);
		}
	}

	/**
	 * 製造指示の登録を行う。
	 * @param bean データ
	 */
	public void insertSijiSeizo(final SijiSeizo bean) {
		// 製造指示を更新
		int updateNum = sijiSeizoDao.insert(bean);
		if (updateNum != 1) {
			// 更新エラー
			throw new SQLRuntimeException(null);
		}
	}

	/**
	 * 製造指図書発行
	 * @param bean 製造指図一覧データ
	 * @param tantoCd 担当者コード
	 * @return Map<String, List<String>> タンク情報
	 * @throws DirectionLogicException エラー発生時
	 */
	public Map<String, List<String>> issuance(
			final DirectionDirectionHeaderList bean, final String tantoCd)
			throws DirectionLogicException {
		DirectionLogicException ex = null;

		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.direction.stock.update.direction.no";
		Timestamp now = AecDateUtils.getCurrentTimestamp();

		// 指図ステータス
		String status = DirectionUtil.getBigDecimalString(bean
				.getDirectionStatus());

		// ヘッダーを更新
		updateHeader(bean, tantoCd, now);

		// フォーミュラのデータを更新
		Map<String, List<String>> map = updateFormula(bean.getDirectionNo(),
			tantoCd, now);

		// 未確定の場合は在庫更新を行う
		if (SelectDirectionListStatus.UNFIXED.equals(status)) {
			/* 洗浄水絶対重量の処理する */
			updateFomulaForWater(bean.getDirectionNo(), tantoCd);
			try {
				/* 在庫更新－包装・製造指図確定 */
				if (!stock.fixDirection(bean.getDirectionDivision(), bean
						.getDirectionNo(), tantoCd)) {
					ex = new DirectionLogicException(errMsg, bean
							.getDirectionNo());
					throw ex;
				}
				/* 在庫更新－配合指図確定 */
				if (!stock.fixFormula(bean.getDirectionDivision(), bean
						.getDirectionNo(), tantoCd)) {
					ex = new DirectionLogicException(errMsg, bean
							.getDirectionNo());
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				ex = new DirectionLogicException(errMsg, bean.getDirectionNo());
				throw ex;
			}
		}
		return map;
	}

	/**
	 * 指図発行時に手持ち在庫をチェックして不足なら警告を出す。
	 * @param directionNo 製造指図番号
	 * @return 在庫不足量警告メッセージ
	 * @throws DirectionLogicException 例外
	 */
	public List<String> checkInventoryFormula(final String directionNo)
			throws DirectionLogicException {
		List<String> eList = new ArrayList<String>();
		/* 洗浄水チェック */
		/* 洗浄ありの工程検索 */
		List<DirectionProcedure> zList = directionProcedureDao.getListForWater(
			directionNo, SENJOARI);
		if (zList.isEmpty()) {
			return eList;
		}
		/* 本流で水を使う工程検索 */
		List<DirectionProcedure> wList = directionProcedureDao.getListForWater(
			directionNo, MIZU);
		if (wList.isEmpty()) {
			String mes = directionNo + ":水を投入する工程がありません。";
			eList.add(mes);
		}
		for (DirectionProcedure pro : zList) {
			/* 工程の絶対量を配合へコピー */
			List<DirectionFormula> flist = directionFormulaDao.getListForWater(
				directionNo, pro.getStepNo(), WATER_ITEMS);
			if (flist.isEmpty()) {
				String mes = directionNo + ":洗浄工程より前に投入する水品目がありません。";
				eList.add(mes);
			}
			BigDecimal qty = pro.getWaterWeight();
			if (qty == null || qty.equals(BigDecimal.ZERO)) {
				continue;
			}
			/* 絶対水量を本流の使用量から引く */
			for (DirectionProcedure hpro : wList) {
				if (pro.getSeq().compareTo(hpro.getSeq()) > 0) {
					flist = directionFormulaDao.getListForWater(directionNo,
						hpro.getStepNo(), WATER_ITEMS);
					for (DirectionFormula form : flist) {
						BigDecimal wqty = form.getQty();
						if (wqty == null) {
							continue;
						}
						if (qty.compareTo(wqty) > 0) {
							qty = qty.subtract(wqty);
							wqty = BigDecimal.ZERO;
						} else {
							wqty = wqty.subtract(qty);
							qty = BigDecimal.ZERO;
						}
						if (qty.compareTo(BigDecimal.ZERO) <= 0) {
							break;
						}
					}
				} else {
					break;
				}
			}
			if (qty.compareTo(BigDecimal.ZERO) > 0) {
				String mes = directionNo + ":洗浄量 > 投入水量です。";
				eList.add(mes);
			}
		}
		/* 手持ち在庫をチェック */
		List<DirectionDirectionFormulaList> list = directionDirectionFormulaListDao
				.getList(directionNo, null);
		for (DirectionDirectionFormulaList formBean : list) {
			BigDecimal fQty = checkInventory(formBean.getItemCd(), formBean
					.getQty());
			if (fQty.compareTo(BigDecimal.ZERO) > 0) {
				String mes = directionNo + ":品目(" + formBean.getItemCd()
						+ ")の手持ち在庫が(" + fQty.toString() + ")不足です。";
				eList.add(mes);
			}
		}

		return eList;
	}

	/**
	 * 手持ち在庫の不足量を返す
	 * @param itemCd 品目コード
	 * @param qty 必要量
	 * @return 不足量（正） 、余裕量（負）
	 */
	private BigDecimal checkInventory(final String itemCd, final BigDecimal qty) {
		if (qty == null || qty.compareTo(BigDecimal.ZERO) <= 0) {
			return BigDecimal.ZERO;
		}
		Item item = itemDao.getEntity(itemCd);
		if (item == null
				|| item.getStockDivision().compareTo(new BigDecimal(3)) == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal rQty = qty;
		ItemInventory inv = itemInventoryDao.getEntity(itemCd);
		if (inv != null && inv.getInspectionQty() != null) {
			rQty = qty.subtract(inv.getInventoryQty());
		}
		return rQty;
	}

	/**
	 * 計装I/Fテーブルの登録処理を行う. : 製造計画,製造指示
	 * @param bean 製造指図一覧データ
	 * @param map タンク情報
	 * @throws Exception 例外
	 */
	public void insertIfTable(final DirectionDirectionHeaderList bean,
			final Map<String, List<String>> map) throws Exception {
		String status = DirectionUtil.getBigDecimalString(bean
				.getDirectionStatus());

		try {
			// 未確定時、計装システムに追加
			if (SelectDirectionListStatus.UNFIXED.equals(status)) {
				// 存在しない場合のみ
				if (keikakuSeizoDao.getEntity(bean.getDirectionNo()) == null) {
					// 製造計画に挿入
					insertKeikakuSeizo(bean);
					// 製造指示に挿入
					insertSijiSeizo(bean.getDirectionNo(), map);
				}
			}
		} catch (SQLRuntimeException e) {
			String errMsg = "errors.direction.update.if.table.direction.no";
			throw new DirectionLogicException(errMsg, bean.getDirectionNo());
		}
	}

	/**
	 * 製造指図書発行
	 * @param list List<DirectionDirectionHeaderList>
	 * @param tantoCd 担当者コード
	 * @return [true:OK][false:NG]
	 * @throws DirectionLogicException エラー発生時
	 * @deprecated
	 */
	public boolean issuance(final List<DirectionDirectionHeaderList> list,
			final String tantoCd) throws DirectionLogicException {
		boolean res = false;
		DirectionLogicException ex = null;

		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.direction.stock.update.direction.no";
		boolean isSelected = false;
		Timestamp now = AecDateUtils.getCurrentTimestamp();
		int index = 0;
		for (DirectionDirectionHeaderList bean : list) {
			if (bean.isSelectedCheck()) {
				// 指図書発行対象データ
				isSelected = true;
				// 指図ステータス
				String status = DirectionUtil.getBigDecimalString(bean
						.getDirectionStatus());
				if (!SelectDirectionListStatus.MAKED.equals(status)) {
					// ステータス≠製造時以外、指図書を発行する。
					updateHeader(bean, tantoCd, now);
					// フォーミュラのデータを更新
					Map<String, List<String>> map = updateFormula(bean
							.getDirectionNo(), tantoCd, now);
					if (SelectDirectionListStatus.UNFIXED.equals(status)) {
						// 未確定時、計装システムに追加
						// 製造計画に挿入
						insertKeikakuSeizo(bean);
						// 製造指示に挿入
						insertSijiSeizo(bean.getDirectionNo(), map);

						try {
							/* 在庫更新－包装・製造指図確定 */
							if (!stock.fixDirection(
								bean.getDirectionDivision(), bean
										.getDirectionNo(), tantoCd)) {
								ex = new DirectionLogicException(errMsg, bean
										.getDirectionNo());
								throw ex;
							}
							/* 在庫更新－配合指図確定 */
							if (!stock.fixFormula(bean.getDirectionDivision(),
								bean.getDirectionNo(), tantoCd)) {
								ex = new DirectionLogicException(errMsg, bean
										.getDirectionNo());
								throw ex;
							}
						} catch (LogicExceptionEx e) {
							ex = new DirectionLogicException(errMsg, bean
									.getDirectionNo());
							throw ex;
						}
					}
				}
			}
			index++;
		}
		if (!isSelected) {
			// 一つも選択されていない場合
			ex = new DirectionLogicException();
			ex.setKey("errors.direction.selected.checkbox");
			throw ex;
		} else {
			// 処理成功メッセージ
			res = true;
		}
		return res;
	}

	/**
	 * 製造指図ヘッダ更新に指図ステータス=2（指図書発行済み）にして、更新する。
	 * @param bean 製造指図ヘッダデータ
	 * @param tantoCd 担当者コード
	 * @param now 現在日時
	 * @throws DirectionLogicException 更新エラーの場合
	 */
	private void updateHeader(final DirectionDirectionHeaderList bean,
			final String tantoCd, final Timestamp now)
			throws DirectionLogicException {
		DirectionDirectionHeaderList updateBean = new DirectionDirectionHeaderList();
		try {
			IgnoreCaseBeanUtils.copyProperties(updateBean, bean);
		} catch (IllegalAccessException e) {
			// 同一クラスだから例外は発生しない
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// 同一クラスだから例外は発生しない
			e.printStackTrace();
		}

		// 計装IFテーブルに送信しない場合は、ステータスを4:中間品最終検査待に更新
		// if (DirectionConst.ORDER_PUBLISH_FLG_OFF.equals(bean
		// .getOrderPublishFlg())) {
		// updateBean
		// .setDirectionStatus(DirectionConst.DIRECTION_STATUS_INSPECTION_WAIT);
		// } else {
		// 以外は2:指図書発行済みに更新
		// 2009/12/24 全て2:指図書発行済みにする。
		updateBean.setDirectionStatus(DirectionConst.DIRECTION_STATUS_ISSUANCE);
		// }
		updateBean.setStampFlag(DirectionConst.STAMP_FLAG_ISSUANCE); // 指図書発行フラグ－発行済み(1)
		updateBean.setStampDate(now); // 指図書発行日
		updateBean.setStampTantoCd(tantoCd); // 指図書発行者
		updateBean.setUpdatorCd(tantoCd); // 更新者
		// 製造指図ヘッダ更新
		updateDirectionHeader(updateBean);
	}

	/**
	 * フォーミュラのタンク情報データを更新
	 * @param directionNo 指図番号
	 * @param tantoCd 担当者コード
	 * @param now 現在日時
	 * @return Map<String, List<String>> タンク情報
	 * @throws DirectionLogicException 有効在庫が無い場合
	 */
	private Map<String, List<String>> updateFormula(final String directionNo,
			final String tantoCd, final Timestamp now)
			throws DirectionLogicException {
		// 指図番号に属するフォーミュラのタンク情報を全て取得
		List<DirectionDirectionFormulaList> list = directionDirectionFormulaListDao
				.getTankList(directionNo);
		// フォーミュラ毎にタンクを振り分け
		Map<String, List<String>> map = separeteFormulaTank(list);
		DirectionDirectionFormulaList bean = new DirectionDirectionFormulaList();
		bean.setDirectionDivision(DirectionConst.DIRECTION_DIVISION);
		bean.setDirectionNo(directionNo);
		bean.setUpdatorCd(tantoCd);
		bean.setUpdateDate(now);
		for (String key : map.keySet()) {
			List<String> array = map.get(key);
			if (array.isEmpty()) {
				// 有効在庫が無い場合->エラー
				throw new DirectionLogicException(
						"errors.direction.not.exists.stock.direction.no",
						directionNo);
			}
			// ステップNo,行Noに分離
			String[] stepLine = separateFormulaKey(key);
			// 重複を除外して、上位3個だけにする
			List<String> tanks = execludeDuplidateTank(array);
			// 製造指図フォーミュラに更新
			updateFormulaTank(bean, stepLine, tanks);
		}
		return map;
	}

	/**
	 * タンク情報のみを製造指図フォーミュラに更新
	 * @param bean DirectionDirectionFormulaList
	 * @param stepLine ステップNO、行NO
	 * @param tanks タンク配列
	 */
	private void updateFormulaTank(final DirectionDirectionFormulaList bean,
			final String[] stepLine, final List<String> tanks) {
		bean.setStepNo(new BigDecimal(stepLine[0]));
		bean.setLineNo(new BigDecimal(stepLine[1]));
		bean.setLocationCd(getTankForList(0, tanks));
		bean.setNextLocationCd(getTankForList(1, tanks));
		bean.setNextAfterLocationCd(getTankForList(2, tanks));
		try {
			// 製造指図フォーミュラに更新
			int updateNum = directionDirectionFormulaListDao.updateTanks(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 各フォーミュラ毎に取得順にタンクを配列に格納する
	 * @param list 全てのフォーミュラデータ
	 * @return Map
	 */
	private Map<String, List<String>> separeteFormulaTank(
			final List<DirectionDirectionFormulaList> list) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (DirectionDirectionFormulaList bean : list) {
			String key = getFormulaKey(bean.getStepNo().toString(), bean
					.getLineNo().toString());
			List<String> array = map.get(key);
			if (array == null) {
				array = new ArrayList<String>();
				map.put(key, array);
			}
			String locationCd = bean.getLocationCd();
			if (StringUtils.isNotEmpty(locationCd)) {
				array.add(locationCd);
			}
		}
		return map;
	}

	/**
	 * ステップ番号＋行No
	 * @param stepNo ステップ番号
	 * @param lineNo行No
	 * @return ステップ番号＋行No
	 */
	private String getFormulaKey(final String stepNo, final String lineNo) {
		return StringUtils.rightPad(stepNo, 10) + ":"
				+ StringUtils.rightPad(lineNo, 10);
	}

	/**
	 * フォーミュラ用のKeyを分離してステップ番号、行Noを取得する
	 * @param key フォーミュラ用のKey
	 * @return [ステップ番号][行No]
	 */
	private String[] separateFormulaKey(final String key) {
		String[] buf = key.split(":");
		buf[0] = buf[0].trim();
		buf[1] = buf[1].trim();
		return buf;
	}

	/**
	 * 重複を除外して、上位3個だけにするexclude
	 * @param list タンクの配列
	 * @return 重複を除外したタンクの配列
	 */
	private List<String> execludeDuplidateTank(final List<String> list) {
		Map<String, String> checkMap = new HashMap<String, String>();
		List<String> res = new ArrayList<String>();
		for (String s : list) {
			if (res.size() > 3) {
				// タンクは3個までなので、以降は捨てる
				break;
			}
			String buf = checkMap.get(s);
			if (buf == null) {
				// 重複してない
				checkMap.put(s, s);
				res.add(s);
			}
		}
		return res;
	}

	/**
	 * タンク配列から指定位置のデータを取得する 指定位置のデータが無い場合はnullを返す。
	 * @param index 指定位置
	 * @param list タンク配列
	 * @return タンクデータ
	 */
	private String getTankForList(final int index, final List<String> list) {
		String res = null;
		try {
			res = list.get(index);
		} catch (IndexOutOfBoundsException ioobe) {
			res = null;
		}
		return res;
	}

	/**
	 * 製造計画に登録する
	 * @param bean DirectionDirectionHeaderList
	 */
	private void insertKeikakuSeizo(final DirectionDirectionHeaderList bean) {
		// 製造計画に追加
		KeikakuSeizo seizo = new KeikakuSeizo();
		String seizobi = AecDateUtils.dateFormat(bean.getPlanedSdate(),
			"yyyy/MM/dd");
		Timestamp seizobiTimestamp = AecDateUtils.getTimestampYmdFormat(
			seizobi, false);
		seizo.setSeizobi(seizobiTimestamp.toString());
		seizo.setKojocode(AecStringUtils.editSpecifiedSize(bean
				.getProductionLine(), 2));
		seizo.setSeizosashizuno(AecStringUtils.editSpecifiedSize(bean
				.getDirectionNo(), 11));
		seizo.setChogotankno(AecStringUtils.editSpecifiedSize(bean
				.getCompoundTankNo(), 8));
		seizo.setHoldtankno(AecStringUtils.editSpecifiedSize(bean
				.getHoldTankNo(), 8));
		seizo.setJunban(getJunban(seizobi, seizo.getChogotankno()));
		seizo.setDaiseicode(AecStringUtils.editSpecifiedSize(bean.getItemCd(),
			8));
		seizo.setIocode(AecStringUtils.editSpecifiedSize(bean
				.getOtherCompanyCd1(), 8));
		int shipperDivision = bean.getShipperDivision().intValue();
		if (DirectionConst.SHIPPER_DIVISION_KAO == shipperDivision) {
			// 花王の場合
			seizo.setJishaflag(DirectionConst.SEIZO_IF_SHIPPER_DIVISION_KAO);
		} else {
			seizo.setJishaflag(DirectionConst.SEIZO_IF_SHIPPER_DIVISION_OTHER);
		}
		seizo.setKeikakuryo(bean.getPlanedQty());
		seizo.setSeizojokyo(AecStringUtils.editSpecifiedSize("0", 1));
		seizo.setHenkofuka(AecStringUtils.editSpecifiedSize(" ", 1));
		// 製造計画に挿入
		insertKeikakuSeizo(seizo);
	}

	/**
	 * 調合順番を取得する
	 * @param seizobi 製造日
	 * @param chogoTankNo 調合タンク番号
	 * @return 調合順番
	 */
	private BigDecimal getJunban(final String seizobi, final String chogoTankNo) {
		// 同一製造日、調合タンクの順番の最大値を取得する
		DirectionKeikakuSeizoDetail maxJunban = directionKeikakuSeizoDetailDao
				.getMaxJunban(seizobi, chogoTankNo);
		return maxJunban.getJunban().add(new BigDecimal(1));
	}

	/**
	 * 製造指示にデータを挿入する。
	 * @param directionNo 製造指図番号
	 * @param tankMap タンク情報格納Map
	 */
	private void insertSijiSeizo(final String directionNo,
			final Map<String, List<String>> tankMap) {
		// プロシージャ・フォーミュラを一括取得
		List<DirectionProcedureFormulaList> list = directionProcedureFormulaListDao
				.getList(DirectionConst.DIRECTION_DIVISION.intValue(),
					directionNo);
		for (DirectionProcedureFormulaList bean : list) {
			boolean isFormula = true;
			if (bean.getLineNo() == null) {
				// 工程のみで、フォーミュラが存在しない
				isFormula = false;
			}
			SijiSeizo seizo = new SijiSeizo();
			seizo.setSeizosashizuno(AecStringUtils.editSpecifiedSize(
				directionNo, 11)); // 製造指図NO
			seizo.setStep(bean.getProcedureSeq()); // ステップ(SEQ)
			if (isFormula) {
				seizo.setSubstep(bean.getFormulaSeq()); // サブステップ(SEQ)
			} else {
				seizo.setSubstep(new BigDecimal(1)); // サブステップ
			}
			seizo.setRoute(AecStringUtils.editSpecifiedSize(AecStringUtils
					.zeroPadding(bean.getMainStream(), 2), 2)); // 本流・準備・予備
			seizo.setShoricode(AecStringUtils.editSpecifiedSize(bean
					.getOperationCd(), 2)); // 処理コード
			seizo.setSyoriName(getSyoriName(bean)); // 原材料名or処理名
			// 原材料コードorタンクNo,次タンクNo.,次々タンクNo.を設定
			setTank(seizo, bean, tankMap);
			// 投入方法
			if (bean.getTonyu() == null) {
				bean.setTonyu(BigDecimal.ONE); // 1:手動
			}
			seizo.setTonyu(AecStringUtils.editSpecifiedSize(DirectionUtil
					.getBigDecimalString(bean.getTonyu()), 1)); // 投入方法
			// 設定値１～６、設定単位設定
			String operationCd = bean.getOperationCd();
			// if (!isFormula) {
			// operationCd = "OTHER";
			// }
			Command command = getCommand(operationCd);
			command.execute(seizo, bean);
			// 製造指示に挿入
			insertSijiSeizo(seizo);
		}
	}

	/**
	 * 原材料名を取得する
	 * @param bean プロシージャy・フォーミュラデータ
	 * @return 原材料名
	 */
	private String getSyoriName(final DirectionProcedureFormulaList bean) {
		String res = null;
		boolean isProcedure = true;
		for (String s : SYORI_NAME_ITEM_NAME_OPERATION_CD) {
			if (s.equals(bean.getOperationCd())) {
				// フォーミュラ。品目コードから品目名称を設定
				res = bean.getItemName();
				isProcedure = false;
				break;
			}
		}
		if (isProcedure) {
			// プロシージャ・工程名称を設定
			res = bean.getOperationName();
		}
		return AecStringUtils.split(res, 20);
	}

	/**
	 * 製造指示のタンク情報に値を設定する
	 * @param seizo 製造指示データ
	 * @param bean プロシージャ・フォーミュラ情報
	 * @param tankMap タンク情報
	 */
	private void setTank(final SijiSeizo seizo,
			final DirectionProcedureFormulaList bean,
			final Map<String, List<String>> tankMap) {
		String mode = tankOperationCdMap.get(bean.getOperationCd());
		if (bean.getLineNo() == null) {
			// 工程のみの場合
			mode = "z"; // その他に入れば何でもいい
		}
		if ("1".equals(mode)) {
			// フォーミュラの品目コード
			seizo.setCodeTank(AecStringUtils.editSpecifiedSize(
				bean.getItemCd(), 16)); // 原材料コードorタンクNo
			seizo.setTank1(AecStringUtils.editSpecifiedSize(null, 8)); // 次タンクNo.
			seizo.setTank2(AecStringUtils.editSpecifiedSize(null, 8)); // 次々タンクNo.
		} else if ("2".equals(mode)) {
			// 第3タンクまでをなんとか
			String key = getFormulaKey(bean.getStepNo().toString(), bean
					.getLineNo().toString());
			List<String> array = tankMap.get(key);
			// 重複したものを削除＆上位3個まで
			List<String> tanks = execludeDuplidateTank(array);
			seizo.setCodeTank(AecStringUtils.editSpecifiedSize(getTankForList(
				0, tanks), 16));
			seizo.setTank1(AecStringUtils.editSpecifiedSize(getTankForList(1,
				tanks), 8));
			seizo.setTank2(AecStringUtils.editSpecifiedSize(getTankForList(2,
				tanks), 8));
		} else {
			// スペース
			seizo.setCodeTank(AecStringUtils.editSpecifiedSize(null, 16)); // 原材料コードorタンクNo
			seizo.setTank1(AecStringUtils.editSpecifiedSize(null, 8)); // 次タンクNo.
			seizo.setTank2(AecStringUtils.editSpecifiedSize(null, 8)); // 次々タンクNo.
		}
	}

	/**
	 * 製造指示－設定値格納用文字列取得 100倍して、小数点以下を切り捨てして、ゼロ詰めした文字列を取得
	 * @param value 対象数値
	 * @return 設定値格納用文字列
	 */
	private static String getSettei(final BigDecimal value) {
		String res = "00000000";
		// 小数部第3位を切り捨てして100倍した値
		if (value != null) {
			BigDecimal calc = value.multiply(new BigDecimal(100)).setScale(0,
				RoundingMode.DOWN);
			int prcision = calc.precision();
			if (prcision > 8) {
				// レンジオーバー
				res = "FFFFFFFF";
			} else {
				res = AecStringUtils.zeroPadding(calc, 8);
			}
		}
		return res;
	}

	/**
	 * 製造指示－設定値格納用文字列取得 小数点以下を切り捨てして、ゼロ詰めした文字列を取得
	 * @param value 対象数値
	 * @return 設定値格納用文字列
	 */
	private static String getInteger(final BigDecimal value) {
		String res = "00000000";
		if (value != null) {
			// 小数部は切り捨て
			BigDecimal buf = value.setScale(0, RoundingMode.DOWN);
			res = AecStringUtils.zeroPadding(buf, 8);
		}
		return res;
	}

	/**
	 * 製造指示－設定値格納用文字列取得 小数部を切り捨てして１００倍した、ゼロ詰めした文字列を取得
	 * @param value 対象数値
	 * @return 設定値格納用文字列
	 */
	private static String getIntegerx100(final BigDecimal value) {
		String res = "00000000";
		// 小数部を切り捨てして１００倍
		if (value != null) {
			BigDecimal calc = value.setScale(0, RoundingMode.DOWN).multiply(
				new BigDecimal(100));
			res = AecStringUtils.zeroPadding(calc, 8);
		}
		return res;
	}

	/**
	 * 製造指示－設定値設定用Commandクラスを取得する
	 * @param code 工程コード
	 * @return 設定値設定用Commandクラス
	 */
	private Command getCommand(final String code) {
		Command c = setteiOperationCdMap.get(code);
		if (c == null) {
			c = setteiOperationCdMap.get("OTHER");
		}
		return c;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(
			final DirectionDirectionHeaderListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 洗浄水絶対重量の処理を行う。
	 * @param directionNo 指図番号
	 * @param tantoCd 担当者コード
	 * @return Map<String, List<String>> タンク情報
	 * @throws DirectionLogicException 有効在庫が無い場合
	 */
	private void updateFomulaForWater(final String directionNo,
			final String tantoCd) throws DirectionLogicException {
		/* 洗浄ありの工程検索 */
		List<DirectionProcedure> zList = directionProcedureDao.getListForWater(
			directionNo, SENJOARI);
		if (zList.isEmpty()) {
			return;
		}
		/* 本流で水を使う工程検索 */
		List<DirectionProcedure> wList = directionProcedureDao.getListForWater(
			directionNo, MIZU);
		if (wList.isEmpty()) {
			return;
		}
		for (DirectionProcedure pro : zList) {
			/* 工程の絶対量を配合へコピー */
			List<DirectionFormula> plist = directionFormulaDao.getListForWater(
				directionNo, pro.getStepNo(), WATER_ITEMS);
			if (plist.isEmpty()) {
				return;
			}
			BigDecimal qty = pro.getWaterWeight();
			if (qty == null || qty.equals(BigDecimal.ZERO)) {
				continue;
			}
			if (plist.get(0).getQty() == null
					|| plist.get(0).getQty().compareTo(BigDecimal.ZERO) > 0) {
				continue;
			}
			/* 絶対水量を本流の使用量から引く */
			for (DirectionProcedure hpro : wList) {
				if (pro.getSeq().compareTo(hpro.getSeq()) > 0) {
					List<DirectionFormula> flist = directionFormulaDao
							.getListForWater(directionNo, hpro.getStepNo(),
								WATER_ITEMS);
					for (DirectionFormula form : flist) {
						BigDecimal wqty = form.getQty();
						if (wqty == null) {
							continue;
						}
						if (qty.compareTo(wqty) > 0) {
							qty = qty.subtract(wqty);
							wqty = BigDecimal.ZERO;
						} else {
							wqty = wqty.subtract(qty);
							qty = BigDecimal.ZERO;
						}
						form.setQty(wqty);
						try {
							// 製造指図フォーミュラ更新
							int updateNum = directionFormulaDao.update(form);
							if (updateNum != 1) {
								// 排他エラー
								throw new OptimisticLockRuntimeException();
							}
						} catch (SQLRuntimeException e) {
							throw new DuplicateRuntimeException(0);
						}
						if (qty.compareTo(BigDecimal.ZERO) <= 0) {
							break;
						}
					}
				} else {
					break;
				}
			}
			plist.get(0).setQty(pro.getWaterWeight().subtract(qty));
			plist.get(0).setUpdatorCd(tantoCd);
			try {
				// 製造指図フォーミュラ更新
				int updateNum = directionFormulaDao.update(plist.get(0));
				if (updateNum != 1) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}
			} catch (SQLRuntimeException e) {
				throw new DuplicateRuntimeException(0);
			}

		}

	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図ヘッダ操作用DAOを設定します。
	 * @param directionDirectionHeaderListDao 製造指図ヘッダ操作用DAO
	 */
	public void setDirectionDirectionHeaderListDao(
			final DirectionDirectionHeaderListDao directionDirectionHeaderListDao) {
		this.directionDirectionHeaderListDao = directionDirectionHeaderListDao;
	}

	/**
	 * 生産ライン操作用DAOを設定します。
	 * @param directionLineForComboboxesDao 生産ライン操作用DAO
	 */
	public void setDirectionLineForComboboxesDao(
			final DirectionLineForComboboxesDao directionLineForComboboxesDao) {
		this.directionLineForComboboxesDao = directionLineForComboboxesDao;
	}

	/**
	 * 製造計画操作用DAOを設定します。
	 * @param keikakuSeizoDao 製造計画操作用DAO
	 */
	public void setKeikakuSeizoDao(final KeikakuSeizoDao keikakuSeizoDao) {
		this.keikakuSeizoDao = keikakuSeizoDao;
	}

	/**
	 * 製造指示操作用DAOを設定します。
	 * @param sijiSeizoDao 製造指示操作用DAO
	 */
	public void setSijiSeizoDao(final SijiSeizoDao sijiSeizoDao) {
		this.sijiSeizoDao = sijiSeizoDao;
	}

	/**
	 * 製造指図フォーミュラ操作用DAOを設定します。
	 * @param directionDirectionFormulaListDao 製造指図フォーミュラ操作用DAO
	 */
	public void setDirectionDirectionFormulaListDao(
			final DirectionDirectionFormulaListDao directionDirectionFormulaListDao) {
		this.directionDirectionFormulaListDao = directionDirectionFormulaListDao;
	}

	/**
	 * 製造計画操作用DAOを設定します。
	 * @param directionKeikakuSeizoDetailDao 製造計画操作用DAO
	 */
	public void setDirectionKeikakuSeizoDetailDao(
			final DirectionKeikakuSeizoDetailDao directionKeikakuSeizoDetailDao) {
		this.directionKeikakuSeizoDetailDao = directionKeikakuSeizoDetailDao;
	}

	/**
	 * 製造プロシージャy・フォーミュラ操作用DAOを設定します。
	 * @param directionProcedureFormulaListDao 製造プロシージャy・フォーミュラ操作用DAO
	 */
	public void setDirectionProcedureFormulaListDao(
			final DirectionProcedureFormulaListDao directionProcedureFormulaListDao) {
		this.directionProcedureFormulaListDao = directionProcedureFormulaListDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	// inner
	// class-------------------------------------------------------------------------
	/**
	 * 製造指示－設定値を処理するためのインターフェイス
	 */
	interface Command {
		void execute(SijiSeizo seizo, DirectionProcedureFormulaList bean);
	}

	/**
	 * 製造指示－設定値－パターン１(工程コード：3, 5, 7, 46, 47)
	 */
	static class Pattern1 implements Command {
		public Pattern1() {
		};

		public void execute(final SijiSeizo seizo,
				final DirectionProcedureFormulaList bean) {
			seizo.setSettei1("00000000"); // 設定値１
			seizo.setSettei2(getInteger(bean.getConditionTemp())); // 設定値２
			seizo.setSettei3("00000000"); // 設定値３
			seizo.setSettei4("00000000"); // 設定値４
			seizo.setSettei5("00000000"); // 設定値５
			seizo.setSettei6(getIntegerx100(bean.getConditionTemp())); // 設定値６
			seizo.setUnit("818E"); // 設定単位
		}
	}

	/**
	 * 製造指示－設定値－パターン２(工程コード：11, 13)
	 */
	static class Pattern2 implements Command {
		public Pattern2() {
		};

		public void execute(final SijiSeizo seizo,
				final DirectionProcedureFormulaList bean) {
			seizo.setSettei1("00000000"); // 設定値１
			seizo.setSettei2("00000000"); // 設定値２
			seizo.setSettei3("00000000"); // 設定値３
			seizo.setSettei4(getInteger(bean.getStirSpeed1())); // 設定値４
			seizo.setSettei5(getInteger(bean.getStirSpeed2())); // 設定値５
			seizo.setSettei6(getIntegerx100(bean.getStirSpeed1())); // 設定値６
			seizo.setUnit("487A"); // 設定単位
		}
	}

	/**
	 * 製造指示－設定値－パターン３(工程コード：21,22,24,26,51,52,53)
	 */
	static class Pattern3 implements Command {
		public Pattern3() {
		};

		public void execute(final SijiSeizo seizo,
				final DirectionProcedureFormulaList bean) {
			seizo.setSettei1(getSettei(bean.getQty())); // 設定値１
			seizo.setSettei2("00000000"); // 設定値２
			seizo.setSettei3("00000000"); // 設定値３
			seizo.setSettei4("00000000"); // 設定値４
			seizo.setSettei5(getInteger(bean.getTonyusokudo())); // 設定値５
			String unit = null;
			if ("3".equals(bean.getUnitOfStockControl())) {
				// mg
				if (bean.getQty() == null) {
					seizo.setSettei6(getSettei(bean.getQty())); // 設定値６
				} else {
					seizo.setSettei6(getSettei(bean.getQty().multiply(
						new BigDecimal(1000000)))); // 設定値６
				}
				unit = "6D67";
			} else if ("2".equals(bean.getUnitOfStockControl())) {
				// g
				if (bean.getQty() == null) {
					seizo.setSettei6(getSettei(bean.getQty())); // 設定値６
				} else {
					seizo.setSettei6(getSettei(bean.getQty().multiply(
						new BigDecimal(1000)))); // 設定値６
				}
				unit = "6720";
			} else {
				// Kg
				seizo.setSettei6(getSettei(bean.getQty())); // 設定値６
				unit = "6B67";
			}
			seizo.setUnit(unit); // 設定単位
		}
	}

	/**
	 * 製造指示－設定値－パターン７(工程コード：23,25,54,55,56)
	 */
	static class Pattern7 implements Command {
		public Pattern7() {
		};

		public void execute(final SijiSeizo seizo,
				final DirectionProcedureFormulaList bean) {
			seizo.setSettei1(getSettei(bean.getWaterWeight())); // 設定値１
			seizo.setSettei2("00000000"); // 設定値２
			seizo.setSettei3("00000000"); // 設定値３
			seizo.setSettei4("00000000"); // 設定値４
			seizo.setSettei5(getInteger(bean.getTonyusokudo())); // 設定値５
			String unit = null;
			if ("3".equals(bean.getUnitOfStockControl())) {
				// mg
				if (bean.getWaterWeight() == null) {
					seizo.setSettei6(getSettei(bean.getWaterWeight())); // 設定値６
				} else {
					seizo.setSettei6(getSettei(bean.getWaterWeight().multiply(
						new BigDecimal(1000000)))); // 設定値６
				}
				unit = "6D67";
			} else if ("2".equals(bean.getUnitOfStockControl())) {
				// g
				if (bean.getWaterWeight() == null) {
					seizo.setSettei6(getSettei(bean.getWaterWeight())); // 設定値６
				} else {
					seizo.setSettei6(getSettei(bean.getWaterWeight().multiply(
						new BigDecimal(1000)))); // 設定値６
				}
				unit = "6720";
			} else {
				// Kg
				seizo.setSettei6(getSettei(bean.getWaterWeight())); // 設定値６
				unit = "6B67";
			}
			seizo.setUnit(unit); // 設定単位
		}
	}

	/**
	 * 製造指示－設定値－パターン４(工程コード：27, 28, 96)
	 */
	static class Pattern4 extends Pattern3 {
		public Pattern4() {
		};

		public void execute(final SijiSeizo seizo,
				final DirectionProcedureFormulaList bean) {
			super.execute(seizo, bean);
			seizo.setSettei5("00000000"); // 設定値５
		}
	}

	/**
	 * 製造指示－設定値－パターン５(工程コード：45)
	 */
	static class Pattern5 implements Command {
		public Pattern5() {
		};

		public void execute(final SijiSeizo seizo,
				final DirectionProcedureFormulaList bean) {
			seizo.setSettei1("00000000"); // 設定値１
			seizo.setSettei2("00000000"); // 設定値２
			seizo.setSettei3(getInteger(bean.getConditionTime())); // 設定値３
			seizo.setSettei4("00000000"); // 設定値４
			seizo.setSettei5("00000000"); // 設定値５
			seizo.setSettei6(getIntegerx100(bean.getConditionTime())); // 設定値６
			seizo.setUnit("95AA"); // 設定単位
		}
	}

	/**
	 * 製造指示－設定値－パターン６(工程コード：その他)
	 */
	static class Pattern6 implements Command {
		public Pattern6() {
		};

		public void execute(final SijiSeizo seizo,
				final DirectionProcedureFormulaList bean) {
			seizo.setSettei1("00000000"); // 設定値１
			seizo.setSettei2("00000000"); // 設定値２
			seizo.setSettei3("00000000"); // 設定値３
			seizo.setSettei4("00000000"); // 設定値４
			seizo.setSettei5("00000000"); // 設定値５
			seizo.setSettei6("00000000"); // 設定値６
			seizo.setUnit("0000"); // 設定単位
		}
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
	 * directionProcedureDaoを設定します。
	 * @param directionProcedureDao directionProcedureDao
	 */
	public void setDirectionProcedureDao(
			final DirectionProcedureDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}

	/**
	 * itemInventoryDaoを設定します。
	 * @param itemInventoryDao itemInventoryDao
	 */
	public void setItemInventoryDao(final ItemInventoryDao itemInventoryDao) {
		this.itemInventoryDao = itemInventoryDao;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * directionOrderListForReportDaoを設定します。
	 * @param directionOrderListForReportDao directionOrderListForReportDao
	 */
	public void setDirectionOrderListForReportDao(
			final DirectionOrderListForReportDao directionOrderListForReportDao) {
		this.directionOrderListForReportDao = directionOrderListForReportDao;
	}

	/**
	 * directionOrderDetailListForReportDaoを設定します。
	 * @param directionOrderDetailListForReportDao
	 *            directionOrderDetailListForReportDao
	 */
	public void setDirectionOrderDetailListForReportDao(
			final DirectionOrderDetailListForReportDao directionOrderDetailListForReportDao) {
		this.directionOrderDetailListForReportDao = directionOrderDetailListForReportDao;
	}

	/**
	 * directionOrderProcedureListForReportDaoを設定します。
	 * @param directionOrderProcedureListForReportDao
	 *            directionOrderProcedureListForReportDao
	 */
	public void setDirectionOrderProcedureListForReportDao(
			final DirectionOrderProcedureListForReportDao directionOrderProcedureListForReportDao) {
		this.directionOrderProcedureListForReportDao = directionOrderProcedureListForReportDao;
	}

	/**
	 * directionOrderFormulaListForReportDaoを設定します。
	 * @param directionOrderFormulaListForReportDao
	 *            directionOrderFormulaListForReportDao
	 */
	public void setDirectionOrderFormulaListForReportDao(
			final DirectionOrderFormulaListForReportDao directionOrderFormulaListForReportDao) {
		this.directionOrderFormulaListForReportDao = directionOrderFormulaListForReportDao;
	}

	/**
	 * directionOrderInspectionListForReportDaoを設定します。
	 * @param directionOrderInspectionListForReportDao
	 *            directionOrderInspectionListForReportDao
	 */
	public void setDirectionOrderInspectionListForReportDao(
			final DirectionOrderInspectionListForReportDao directionOrderInspectionListForReportDao) {
		this.directionOrderInspectionListForReportDao = directionOrderInspectionListForReportDao;
	}

}
