/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.arrival;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.codenyukalot.CodeNyukalot;
import com.asahikaseieng.dao.entity.codenyukalot.CodeNyukalotDao;
import com.asahikaseieng.dao.entity.dataseihinnyuka.DataSeihinNyuka;
import com.asahikaseieng.dao.entity.dataseihinnyuka.DataSeihinNyukaDao;
import com.asahikaseieng.dao.entity.jissekigenzairyolabelhakko.JissekiGenzairyoLabelhakko;
import com.asahikaseieng.dao.entity.jissekigenzairyolabelhakko.JissekiGenzairyoLabelhakkoDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalList;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalListDao;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalListPagerCondition;
import com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListConditionForReport;
import com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListForReport;
import com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListForReportDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入荷一覧 ロジック実装クラス
 * @author tosco
 */
public class ArrivalListLogicImpl implements ArrivalListLogic {

	/** オーダー区分 1:原材料 */
	public static final BigDecimal ORDER_DIVISION1 = new BigDecimal(1);

	/** オーダー区分 3:外注製品(非直送) */
	public static final BigDecimal ORDER_DIVISION3 = new BigDecimal(3);

	/** オーダー区分 6:仕入在庫品 */
	public static final BigDecimal ORDER_DIVISION6 = new BigDecimal(6);

	/** 入荷ロット番号 オーダー区分=1の場合 */
	public static final String LOT_NO_PREFIX1 = "N";

	/** 入荷ロット番号 オーダー区分=3or6の場合 */
	public static final String LOT_NO_PREFIX2 = "P";

	/** 荷主区分 1:花王 */
	public static final BigDecimal SHIPPER_DIVISION_KAO = new BigDecimal(1);

	/** 自社／花王区分 1:自社 */
	public static final String JISHAFLAG_JISYA = "1";

	/** 自社／花王区分 2:花王 */
	public static final String JISHAFLAG_KAO = "2";

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 在庫管理区分 3:更新除外 */
	public static final BigDecimal STOCK_DIVISION_NOT_UPDATE = new BigDecimal(3);

	/** ロット管理区分 1:しない */
	public static final BigDecimal LOT_DIVISION_NOT_MANAGE = new BigDecimal(1);

	/** 原材料ラベル発行実績 原料ロット 最大サイズ */
	private static final int GENRYOLOT_MAX_LEN = 20;

	/** 発番処理用ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 入荷一覧画面用Dao */
	private ArrivalListDao arrivalListDao;

	/** 購買オーダーテーブル更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 入荷ロット原材料コード対応テーブル用Dao */
	private CodeNyukalotDao codeNyukalotDao;

	/** 原材料ラベル発行実績テーブル用Dao */
	private JissekiGenzairyoLabelhakkoDao jissekiGenzairyoLabelDao;

	/** 製品入荷データテーブル用Dao */
	private DataSeihinNyukaDao dataSeihinNyukaDao;

	/** 品目マスタ用Dao * */
	private ItemDao itemDao;

	private ArrivalListForReportDao arrivalListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public ArrivalListLogicImpl() {
	}

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<ArrivalList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ArrivalList> getSearchList(
			final ArrivalListPagerCondition condition) throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;
		String strArrivalQuantity = null;
		BigDecimal arrivalQuantity = null;

		checkParams(condition);
		List<ArrivalList> list = arrivalListDao.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (ArrivalList bean : list) {
			// 数量
			strOrderQuantity = checkDigitUtilsLogic.format(bean.getUnitDiv(),
				VENDER_DIV_SI, bean.getVenderCd(), bean.getOrderQuantity());
			bean.setStrOrderQuantity(strOrderQuantity);

			// 重量
			strOrderConvertQuantity = checkDigitUtilsLogic.format(
				UNIT_DIVISION_KG, VENDER_DIV_SI, bean.getVenderCd(), bean
						.getOrderConvertQuantity());
			bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

			// 入荷予定数量
			arrivalQuantity = bean.getArrivalQuantity();
			if (PurchaseStatus.isMinyuka(bean.getStatus())) {
				// 未入荷の場合は(発注数量－予定入荷数量合計)をデフォルト表示
				arrivalQuantity = calcArrivalQuantity(bean.getOrderQuantity(),
					bean.getSumArrivalQuantity());
			}
			strArrivalQuantity = checkDigitUtilsLogic.format(bean.getUnitDiv(),
				VENDER_DIV_SI, bean.getVenderCd(), arrivalQuantity);
			bean.setStrArrivalQuantity(strArrivalQuantity);

			NumberChkDisitDetail detail = checkDigitUtilsLogic.getCheckDigit(
				bean.getUnitDiv(), ArrivalDetailLogicImpl.VENDER_DIV_SI, bean
						.getVenderCd());
			bean.setDecimalPoint(detail.getSmallnumLength()); // 小数点桁数
			bean.setRound(detail.getRoundDivision()); // 端数区分
		}
		return list;
	}

	/**
	 * 入荷帳票Excel検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<ArrivalListForReport> 検索結果リスト
	 */
	public List<ArrivalListForReport> getReportList(
			final ArrivalListConditionForReport condition) {

		List<ArrivalListForReport> list = arrivalListForReportDao
				.getReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 購買オーダーテーブル更新処理を行う
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws LogicException 発番エラー
	 * @throws Exception 例外
	 */
	public void update(final List<ArrivalList> searchList, final String tantoCd)
			throws NoDataException, LogicException, Exception {
		int updateNum;

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		try {
			for (ArrivalList bean : searchList) {
				if (!bean.isCheckFlg()
						|| bean.getStatus().compareTo(PurchaseStatus.ARRIVALED) == 0) {
					// チェック無 または 入荷登録済
					continue;
				}

				PurchaseSubcontract updBean = new PurchaseSubcontract();
				// 値を更新用Beanにコピー
				IgnoreCaseBeanUtils.copyProperties(updBean, bean);

				if (updBean.getRowNo() == null
						|| BigDecimal.ZERO.compareTo(updBean.getRowNo()) == 0) {
					updBean.setRowNo(BigDecimal.ONE);
				}
				updBean.setStatus(PurchaseStatus.ARRIVALED); // 購買ステータス(5:入荷登録済)
				updBean.setSlipNo(getNumberLogic.getSiSlipNo()); // 仕入番号
				getLotNo(updBean); // 入荷ロット番号
				updBean.setUpdatorCd(tantoCd); // 更新者
				if (ArrivalListLogicImpl.ORDER_DIVISION3.equals(bean
						.getOrderDivision())
						|| ArrivalListLogicImpl.ORDER_DIVISION6.equals(bean
								.getOrderDivision())) {
					if (bean.getRowNo().compareTo(new BigDecimal(1000)) < 0) {
						BigDecimal base = bean.getRowNo().multiply(
							new BigDecimal(1000)).add(BigDecimal.ONE);
						updBean.setRowNo(base);
					}
				}
				// 更新処理
				updateNum = purchaseSubcontractDao.update(updBean);
				if (updateNum == 0) {
					// 更新対象無しエラー
					throw new NoDataException();
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * ラベル発行処理（購買オーダーテーブル更新）を行う
	 * @param bean 入荷一覧データ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	public void issue(final ArrivalList bean, final String tantoCd)
			throws NoDataException, Exception {
		int updateNum;

		try {
			PurchaseSubcontract updBean = new PurchaseSubcontract();
			if (updBean.getRowNo() == null
					|| BigDecimal.ZERO.compareTo(updBean.getRowNo()) == 0) {
				updBean.setRowNo(BigDecimal.ONE);
			}
			// 値を更新用Beanにコピー
			IgnoreCaseBeanUtils.copyProperties(updBean, bean);
			updBean.setLabelFlag(BigDecimal.ONE); // ラベル発行フラグ(1:発行)
			Timestamp labelDate = AecDateUtils.getCurrentTimestamp();
			updBean.setLabelDate(labelDate); // ラベル発行日
			bean.setLabelDate(labelDate); // ラベル発行日(I/F登録用)
			updBean.setLabelTantoCd(tantoCd); // ラベル発行者
			updBean.setUpdatorCd(tantoCd); // 更新者

			// 更新処理
			updateNum = purchaseSubcontractDao.update(updBean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				String errMsg = "errors.arrival.nodata.deleted.detail";
				throw new ArrivalLogicException(errMsg, editAddMsg(bean));
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			String errMsg = "errors.arrival.optimisticlock.data.detail";
			throw new ArrivalLogicException(errMsg, editAddMsg(bean));
		}
	}

	/**
	 * 計装I/Fテーブルの登録処理を行う. ・原材料の場合 : 入荷ロット原材料コード対応,原材料ラベル発行実績テーブル ・外注製品,仕入在庫品の場合 :
	 * 製品入荷データテーブル
	 * @param bean 入荷一覧データ
	 * @throws Exception 例外
	 */
	public void insertIfTable(final ArrivalList bean) throws Exception {

		try {
			if (bean.getLabelFlag().equals(BigDecimal.ONE)) {
				// 再発行の場合は登録なし
				return;
			}

			if (ORDER_DIVISION1.equals(bean.getOrderDivision())) {
				// 原材料

				// 登録されていない場合のみ
				if (codeNyukalotDao.getEntity(bean.getLotNo()) == null) {
					// 入荷ロット原材料コード対応テーブル 登録処理
					updateCodeNyukalot(bean);

					// 原材料ラベル発行実績テーブル 登録処理
					updateJissekiGenzairyoLabelhakko(bean);
				}

			} else if (ORDER_DIVISION3.equals(bean.getOrderDivision())
					|| ORDER_DIVISION6.equals(bean.getOrderDivision())) {
				// 外注製品、仕入在庫品

				// 登録されていない場合のみ
				if (dataSeihinNyukaDao.getEntity(bean.getLotNo()) == null) {
					// 製品入荷データテーブル 登録処理
					updateDataSeihinNyuka(bean);
				}
			}
		} catch (SQLRuntimeException e) {
			String errMsg = "errors.arrival.update.if.table.detail";
			throw new ArrivalLogicException(errMsg, editAddMsg(bean));
		}
	}

	/**
	 * 付加メッセージを編集する
	 * @param bean 入荷一覧データ
	 * @return String 編集付加メッセージ
	 */
	private String editAddMsg(final ArrivalList bean) {
		String msg = "発注番号 " + bean.getBuySubcontractOrderNo();
		if (bean.getOrderDivideNo() != null) {
			msg = msg + " - " + bean.getOrderDivideNo();
		}
		if (BigDecimal.ZERO.compareTo(bean.getRowNo()) != 0) {
			msg = msg + " 行No " + bean.getRowNo().toString();
		}
		return msg;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final ArrivalListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 入荷ロット番号取得
	 * @param updBean 購買外注オーダBean
	 * @throws LogicException 発番エラー
	 */
	private void getLotNo(final PurchaseSubcontract updBean)
			throws LogicException {
		String lotNo = null;
		Item itemBean = itemDao.getEntity(updBean.getItemCd());
		if (itemBean != null) {
			// 在庫管理区分3:更新除外またはロット管理区分1:しない場合は発番なし
			if ((STOCK_DIVISION_NOT_UPDATE.equals(itemBean.getStockDivision()))
					|| (LOT_DIVISION_NOT_MANAGE.equals(itemBean
							.getLotDivision()))) {
				updBean.setLotNo(Constants.LOTNO_WITHOUT_LOT);
				return;
			} else {
				if (updBean.getOrderDivision().equals(ORDER_DIVISION1)) {
					// 原材料のロット番号('N'+西暦6桁+連番4桁)
					lotNo = getNumberLogic.getNLotNo(null);
				} else if (updBean.getOrderDivision().equals(ORDER_DIVISION3)
						|| updBean.getOrderDivision().equals(ORDER_DIVISION6)) {
					// 外注製品・仕入在庫品のロット番号('P'+西暦6桁+連番4桁)
					lotNo = getNumberLogic.getPLotNo(null);
				}
			}
		}
		if (lotNo == null) {
			// 発番に失敗した場合
			throw new LogicException();
		}
		updBean.setLotNo(lotNo); // 入荷ロット番号
	}

	/**
	 * 未入荷の場合のデフォルト表示用の入荷予定数量を計算する。 分納データを考慮しないといけないため、 単純に発注数量を表示するのではなく
	 * 発注数量から同一の発注番号の入荷予定数量合計値を引いた値を表示する。
	 * 
	 * @param orderQty
	 * @param sumArrivalQty
	 * @return
	 */
	private BigDecimal calcArrivalQuantity(final BigDecimal orderQty,
			final BigDecimal sumArrivalQty) {
		BigDecimal arrivalQuantity = new BigDecimal(0);

		if (orderQty == null || sumArrivalQty == null) {
			return null;
		}

		// 入荷予定数量＝発注数量－入荷予定数量合計
		arrivalQuantity = orderQty.subtract(sumArrivalQty);
		if (arrivalQuantity.signum() == -1) {
			arrivalQuantity = new BigDecimal(0);
		}
		return arrivalQuantity;
	}

	/**
	 * 入荷ロット原材料コード対応テーブル(計装I/F) 登録処理
	 * @param bean 入荷一覧Bean
	 * @throws SQLRuntimeException
	 */
	private void updateCodeNyukalot(final ArrivalList bean)
			throws SQLRuntimeException {
		CodeNyukalot lotBean = new CodeNyukalot();
		lotBean.setNyukalot(AecStringUtils.editSpecifiedSize(bean.getLotNo(),
			11)); // 入荷ロット
		lotBean.setGenzaicode(AecStringUtils.editSpecifiedSize(
			bean.getItemCd(), 8)); // 原材料コード

		// 登録処理
		int insertNum = codeNyukalotDao.insert(lotBean);
		if (insertNum != 1) {
			// 更新エラー
			throw new SQLRuntimeException(null);
		}
	}

	/**
	 * 原材料ラベル発行実績テーブル(計装I/F) 登録処理
	 * @param bean 入荷一覧Bean
	 * @throws SQLRuntimeException
	 * @throws ParseException 予想外エラー
	 */
	private void updateJissekiGenzairyoLabelhakko(final ArrivalList bean)
			throws SQLRuntimeException, ParseException {
		JissekiGenzairyoLabelhakko labelBean = new JissekiGenzairyoLabelhakko();
		String kojoCode = bean.getLocationCd();
		if (StringUtils.isNotEmpty(kojoCode) && kojoCode.length() >= 2) {
			labelBean.setKojocode(kojoCode.substring(0, 2)); // 工場コード
		} else {
			labelBean
					.setKojocode(AecStringUtils.editSpecifiedSize(kojoCode, 2)); // 工場コード
		}
		labelBean.setGenzaicode(AecStringUtils.editSpecifiedSize(bean
				.getItemCd(), 8)); // 原材料コード

		// ラベル発行日
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String hakkobi = df.format(new Date(bean.getLabelDate().getTime()));
			df.setLenient(false);
			Date date = df.parse(hakkobi);
			labelBean.setLabelhakkobi(new Timestamp(date.getTime()).toString());
		} catch (ParseException e) {
			throw e;
		}

		labelBean.setNyukasu(bean.getArrivalQuantity()); // 入荷予定数
		// 入荷予定量＝入荷予定数×ITEM_QUEUE.Kg換算係数(在庫)
		if (bean.getKgOfFractionManagement() != null) {
			BigDecimal nyukaryo = bean.getArrivalQuantity().multiply(
				bean.getKgOfFractionManagement());
			nyukaryo = checkDigitUtilsLogic.round(bean.getUnitDiv(),
				VENDER_DIV_SI, bean.getVenderCd(), nyukaryo);
			labelBean.setNyukaryo(nyukaryo); // 入荷予定量
		}
		labelBean.setGenryolot(AecStringUtils.editSpecifiedSize(bean
				.getSupplierLotno(), GENRYOLOT_MAX_LEN)); // 原料ロット
		labelBean.setNyukalot(AecStringUtils.editSpecifiedSize(bean.getLotNo(),
			11)); // 入荷ロット
		if (bean.getRowNo() == null
				|| BigDecimal.ZERO.compareTo(bean.getRowNo()) == 0) {
			labelBean.setRenban(BigDecimal.ONE); // 連番
		} else {
			labelBean.setRenban(bean.getRowNo()); // 連番
		}
		labelBean.setMinyukosu(bean.getArrivalQuantity()); // 倉庫未入庫数

		// 登録処理
		int insertNum = jissekiGenzairyoLabelDao.insert(labelBean);
		if (insertNum != 1) {
			// 更新エラー
			throw new SQLRuntimeException(null);
		}
	}

	/**
	 * 製品入荷データテーブル(計装I/F) 登録処理
	 * @param bean 入荷一覧Bean
	 * @throws SQLRuntimeException
	 */
	private void updateDataSeihinNyuka(final ArrivalList bean)
			throws SQLRuntimeException {
		DataSeihinNyuka nyukaBean = new DataSeihinNyuka();
		nyukaBean.setSeihincode(AecStringUtils.editSpecifiedSize(bean
				.getItemCd(), 8)); // 製品コード
		nyukaBean.setIocode(AecStringUtils.editSpecifiedSize(bean
				.getOtherCompanyCd1(), 8)); // 他社コード１
		if (SHIPPER_DIVISION_KAO.equals(bean.getShipperDivision())) {
			nyukaBean.setJishaflag(JISHAFLAG_KAO); // 花王
		} else {
			nyukaBean.setJishaflag(JISHAFLAG_JISYA); // 自社
		}
		nyukaBean.setNyukoriyu("02"); // 入荷理由
		nyukaBean.setNyukasu(bean.getArrivalQuantity()); // 入荷数
		nyukaBean.setNyukano(AecStringUtils.editSpecifiedSize("", 8)); // 入荷NO
		nyukaBean.setNyukabc(bean.getLotNo()); // 入荷バーコード
		nyukaBean.setTantocode(AecStringUtils.editSpecifiedSize("", 3)); // 担当者
		nyukaBean.setJuchuno("00000000"); // 受注番号
		nyukaBean.setJuchuedaban("000"); // 受注枝番
		nyukaBean.setHenpinriyu(AecStringUtils.editSpecifiedSize("", 2)); // 返品理由区分
		nyukaBean.setTorihikicode(AecStringUtils.editSpecifiedSize("", 8)); // 取引先コード
		nyukaBean.setLotno(AecStringUtils.editSpecifiedSize("", 16)); // ロット番号
		nyukaBean.setHacchuno(AecStringUtils.editSpecifiedSize("", 11)); // 発注NO
		nyukaBean.setHososashizuno("00000000000"); // 包装指図番号
		nyukaBean.setKenpinsumi(AecStringUtils.editSpecifiedSize("", 1)); // 検品済
		nyukaBean.setNyukojokyo(AecStringUtils.editSpecifiedSize("", 1)); // 入庫状況
		nyukaBean.setZansu(bean.getArrivalQuantity()); // 倉庫入庫残数
		String nyukabi = AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd");
		nyukaBean.setNonyuyoteibi(AecDateUtils.getTimestampYmdFormat(nyukabi)
				.toString()); // 納入予定日

		// 登録処理
		int insertNum = dataSeihinNyukaDao.insert(nyukaBean);
		if (insertNum != 1) {
			// 更新エラー
			throw new SQLRuntimeException(null);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発番処理用ロジッククラスを設定します。
	 * @param getNumberLogic 発番処理用ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 入荷一覧画面用Daoを設定します。
	 * @param arrivalListDao 入荷一覧画面用Dao
	 */
	public void setArrivalListDao(final ArrivalListDao arrivalListDao) {
		this.arrivalListDao = arrivalListDao;

	}

	/**
	 * 購買オーダーテーブル更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買オーダーテーブル更新用Dao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * 入荷ロット原材料コード対応テーブル用Daoを設定します。
	 * @param codeNyukalotDao 入荷ロット原材料コード対応テーブル用Dao
	 */
	public void setCodeNyukalotDao(final CodeNyukalotDao codeNyukalotDao) {
		this.codeNyukalotDao = codeNyukalotDao;
	}

	/**
	 * 原材料ラベル発行実績テーブル用Daoを設定します。
	 * @param jissekiGenzairyoLabelDao 原材料ラベル発行実績テーブル用Dao
	 */
	public void setJissekiGenzairyoLabelDao(
			final JissekiGenzairyoLabelhakkoDao jissekiGenzairyoLabelDao) {
		this.jissekiGenzairyoLabelDao = jissekiGenzairyoLabelDao;
	}

	/**
	 * 製品入荷データテーブル用Daoを設定します。
	 * @param dataSeihinNyukaDao 製品入荷データテーブル用Dao
	 */
	public void setDataSeihinNyukaDao(
			final DataSeihinNyukaDao dataSeihinNyukaDao) {
		this.dataSeihinNyukaDao = dataSeihinNyukaDao;
	}

	/**
	 * 品目マスタ用Daoを設定します。
	 * @param itemDao 品目マスタ用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * arrivalListForReportDaoを設定します。
	 * @param arrivalListForReportDao arrivalListForReportDao
	 */
	public void setArrivalListForReportDao(
			final ArrivalListForReportDao arrivalListForReportDao) {
		this.arrivalListForReportDao = arrivalListForReportDao;
	}
}
