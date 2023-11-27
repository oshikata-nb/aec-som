/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.arrival;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
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
import com.asahikaseieng.dao.nonentity.arrival.ArrivalDetailList;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalDetailListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入荷入力詳細 ロジック実装クラス
 * @author tosco
 */
public class ArrivalDetailLogicImpl implements ArrivalDetailLogic {

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

	/** 入荷入力 詳細画面用Dao */
	private ArrivalDetailListDao arrivalDetailListDao;

	/** 購買外注オーダ更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 入荷ロット原材料コード対応テーブル用Dao */
	private CodeNyukalotDao codeNyukalotDao;

	/** 原材料ラベル発行実績テーブル用Dao */
	private JissekiGenzairyoLabelhakkoDao jissekiGenzairyoLabelDao;

	/** 製品入荷データテーブル用Dao */
	private DataSeihinNyukaDao dataSeihinNyukaDao;

	/** 品目マスタ用Dao * */
	private ItemDao itemDao;

	/**
	 * コンストラクタ.
	 */
	public ArrivalDetailLogicImpl() {
	}

	/**
	 * 購買データ検索処理を行なう.
	 * @param purchaseNo 購買NO
	 * @param slipNo 仕入番号
	 * @param check 数値項目用表示ロジッククラス
	 * @return List<ArrivalDetailList> 詳細データ
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ArrivalDetailList> getEntity(final String purchaseNo,
			final String slipNo, final CheckDigitUtilsLogic check)
			throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;
		String strArrivalQuantity = null;

		List<ArrivalDetailList> list = arrivalDetailListDao.getEntity(
			purchaseNo, slipNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (ArrivalDetailList bean : list) {
			// 数量
			strOrderQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI,
				bean.getVenderCd(), bean.getOrderQuantity());
			bean.setStrOrderQuantity(strOrderQuantity);

			// 重量
			strOrderConvertQuantity = check.format(UNIT_DIVISION_KG,
				VENDER_DIV_SI, bean.getVenderCd(), bean
						.getOrderConvertQuantity());
			bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

			// 入荷予定数量
			if (bean.getStatus().compareTo(new BigDecimal(5)) == 0) {
				// 入荷済の場合
				strArrivalQuantity = check.format(bean.getUnitDiv(),
					VENDER_DIV_SI, bean.getVenderCd(), bean
							.getArrivalQuantity());
				bean.setStrArrivalQuantity(strArrivalQuantity);
			}
		}

		return list;
	}

	/**
	 * 発注番号にひもづくデータの予定入荷数量の合計値を取得する.
	 * @param buySubcontractOrderNo 発注番号
	 * @param slipNo 仕入番号
	 * @return BigDecimal 予定入荷数量の合計値
	 */
	public BigDecimal getSumArrivalQty(final String buySubcontractOrderNo,
			final String slipNo) {

		BigDecimal sumArrivalQty = arrivalDetailListDao.getSumArrivalQty(
			buySubcontractOrderNo, slipNo);

		return sumArrivalQty;
	}

	/**
	 * 購買オーダーテーブル更新処理(DEL/INS)を行う. 発注残数量＞０の場合、分納データを新規作成する。
	 * 
	 * @param frm 入荷入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final ArrivalDetailForm frm, final String tantoCd)
			throws NoDataException, Exception {
		int insertNum;
		String slipNo = null; // 仕入番号
		String bunnoFlg = "0"; // 分納フラグ(0:分納無 1:分納有)
		String orderDivideNo = null; // 発注番号分納枝番
		String orderDivideNoNew = null; // 発注番号分納枝番(新規分納データ用)

		if (frm == null) {
			throw new IllegalArgumentException("form == null");
		}

		try {

			// 排他制御チェックの為画面に開いている購買トランザクションを表示
			PurchaseSubcontract check = purchaseSubcontractDao.getEntity(frm
					.getPurchaseNo());

			// レコードが無いまたはすでに更新されている場合（更新日が異なる場合）はエラーメッセージ表示
			if (check == null
					|| !check.getUpdateDate().equals(frm.getUpdateDate())) {
				throw new OptimisticLockRuntimeException();

			}

			// 分納有無判定
			BigDecimal blanceOrderQty = AecNumberUtils.convertBigDecimal(frm
					.getBalanceOrderQuantity());
			// 発注残数量＞０の場合
			if (blanceOrderQty != null
					&& blanceOrderQty.compareTo(new BigDecimal(0)) > 0) {
				bunnoFlg = "1"; // 分納フラグ

				// 発注番号分納枝番 設定
				if (frm.getOrderDivideNo() == null) {
					orderDivideNo = "001";
					orderDivideNoNew = "002";
				} else {
					orderDivideNo = frm.getOrderDivideNo();
					int divNo = Integer.parseInt(orderDivideNo);
					divNo++;
					// ゼロパディング
					orderDivideNoNew = StringUtils.leftPad(Integer
							.toString(divNo), 3, "0");
				}
			}

			// 仕入番号!=null → 初回登録済
			if (StringUtils.isNotEmpty(frm.getSlipNo())) {
				// -- ２回目以降連続登録処理--

				// ロット分割データ一括削除
				int deleteNum = purchaseSubcontractDao.deleteBySlipNo(frm
						.getSlipNo());
				if (deleteNum == 0) {
					throw new NoDataException();
				}

				// 分納データ削除(対象なしの場合あり)
				deleteNum = purchaseSubcontractDao.deleteByBuySubOrdNo(frm
						.getBuySubcontractOrderNo());

				// 初回登録時の仕入番号取得
				slipNo = frm.getSlipNo();

			} else {
				// -- 初回登録処理 --

				// ロット分割先頭データ(登録済)をいったん削除
				PurchaseSubcontract updBean = new PurchaseSubcontract();
				// 先頭データBean取得
				ArrivalDetailList bean = frm.getDetailList().get(0);
				updBean.setPurchaseNo(frm.getPurchaseNo()); // 購買NO
				updBean.setUpdateDate(bean.getUpdateDate()); // 更新日時(排他)
				int deleteNum = purchaseSubcontractDao.delete(updBean);
				if (deleteNum != 1) {
					throw new NoDataException();
				}

				// 仕入番号取得
				slipNo = getNumberLogic.getSiSlipNo();
			}

			// ロット分割件数分、DELETE/INSERT
			int dataCnt = frm.getDetailList().size();
			for (ArrivalDetailList bean : frm.getDetailList()) {

				PurchaseSubcontract updBean = new PurchaseSubcontract();
				// 値を更新用Beanにコピー
				IgnoreCaseBeanUtils.copyProperties(updBean, bean);

				updBean.setPurchaseNo(null); // 購買NO
				if ("1".equals(bunnoFlg)) {
					updBean.setOrderDivideNo(orderDivideNo); // 発注番号分納枝番
				}
				updBean.setStatus(PurchaseStatus.ARRIVALED); // 5:入荷登録済
				// 次回納品希望日
				String sugDelDate = frm.getNextDeliverlimitDate() + " ";
				if (StringUtils.isNotEmpty(frm.getNextDeliverlimitDateTime())) {
					sugDelDate = sugDelDate + frm.getNextDeliverlimitDateTime();
				} else {
					sugDelDate = sugDelDate + "00:00";
				}
				updBean
						.setNextDeliverlimitDate(AecDateUtils
								.getTimestampYmdHmFormat(sugDelDate,
									"yyyy/MM/dd HH:mm"));
				updBean.setSlipNo(slipNo); // 仕入番号
				if (dataCnt == 1) {
					updBean.setRowNo(null); // 行番号なし
				}
				if (updBean.getRowNo() == null
						|| BigDecimal.ZERO.compareTo(updBean.getRowNo()) == 0) {
					updBean.setRowNo(BigDecimal.ONE);
				}
				getLotNo(updBean); // 入荷ロット番号
				updBean.setOrderSheetRemark2(frm.getOrderSheetRemark2()); // 発注書備考
				updBean.setRemark2(frm.getRemark2()); // 備考
				updBean.setUpdatorCd(tantoCd); // 更新者

				// ロット分割データ登録処理
				insertNum = purchaseSubcontractDao.insert(updBean);

				if (insertNum != 1) {
					/* 一意制約エラー */
					throw new DuplicateRuntimeException(0);
				}

			}

			// 分納有の場合
			if ("1".equals(bunnoFlg)) {
				// 分納データ作成
				insertBunnoData(frm, orderDivideNoNew, tantoCd);
			}

			frm.setSlipNo(slipNo);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 分納データ新規登録処理
	 * 
	 * @param frm 入荷入力画面FORM
	 * @param orderDivideNoNew 発注番号分納枝番
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws OptimisticLockRuntimeException
	 */
	private void insertBunnoData(final ArrivalDetailForm frm,
			final String orderDivideNoNew, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException,
			SQLRuntimeException {

		PurchaseSubcontract newBean = new PurchaseSubcontract();
		ArrivalDetailList bean = frm.getDetailList().get(0);
		// １件目Beanを分納データ用Beanにコピー
		IgnoreCaseBeanUtils.copyProperties(newBean, bean);

		newBean.setPurchaseNo(null); // 購買NO
		newBean.setOrderDivideNo(orderDivideNoNew); // 発注番号分納枝番
		newBean.setStatus(PurchaseStatus.FIXED); // 購買ステータス(4:納期確定)
		newBean.setSlipNo(null); // 仕入番号
		newBean.setRowNo(null); // 行番号
		newBean.setSupplierLotno(null); // メーカーロット番号
		newBean.setLotNo(null); // 入荷ロット番号
		newBean.setArrivalQuantity(null); // 入荷予定数量
		// 納品希望日
		String sugDelDate = frm.getNextDeliverlimitDate() + " ";
		if (StringUtils.isNotEmpty(frm.getNextDeliverlimitDateTime())) {
			sugDelDate = sugDelDate + frm.getNextDeliverlimitDateTime();
		} else {
			sugDelDate = sugDelDate + "00:00";
		}
		newBean.setSuggestedDeliverlimitDate(AecDateUtils
				.getTimestampYmdHmFormat(sugDelDate, "yyyy/MM/dd HH:mm"));
		newBean.setNextDeliverlimitDate(null);
		newBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		newBean.setInputorCd(tantoCd); // 登録者
		newBean.setUpdatorCd(tantoCd); // 更新者

		// 分納データ登録処理
		int insertNum = purchaseSubcontractDao.insert(newBean);

		if (insertNum != 1) {
			/* 一意制約エラー */
			throw new SQLRuntimeException(new SQLException());
		}
	}

	/**
	 * ラベル発行処理（購買オーダーテーブル更新）を行う
	 * @param detailList 一覧データ
	 * @param tantoCd 更新者
	 * @param check 数値項目用表示ロジッククラス
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	public void issue(final List<ArrivalDetailList> detailList,
			final String tantoCd, final CheckDigitUtilsLogic check)
			throws NoDataException, Exception {
		int updateNum;
		int index = 1;

		if (detailList == null || detailList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		try {
			for (ArrivalDetailList bean : detailList) {

				PurchaseSubcontract updBean = new PurchaseSubcontract();
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
					throw new NoDataException();
				}

				index++;

			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 計装I/Fテーブルの登録処理を行う. ・原材料の場合 : 入荷ロット原材料コード対応,原材料ラベル発行実績テーブル ・外注製品,仕入在庫品の場合 :
	 * 製品入荷データテーブル
	 * @param detailList ロット分割データ
	 * @param check 数値項目用表示ロジッククラス
	 * @throws Exception 例外
	 */
	public void insertIfTable(final List<ArrivalDetailList> detailList,
			final CheckDigitUtilsLogic check) throws Exception {

		if (detailList == null || detailList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		int rowNo = 0;
		try {
			for (ArrivalDetailList bean : detailList) {
				rowNo++;
				if (bean.getLabelFlag().equals(BigDecimal.ONE)) {
					// 再発行の場合は登録なし
					continue;
				}

				if (ArrivalListLogicImpl.ORDER_DIVISION1.equals(bean
						.getOrderDivision())) {
					// 原材料
					// 登録されていない場合のみ
					if (codeNyukalotDao.getEntity(bean.getLotNo()) == null) {

						// 入荷ロット原材料コード対応テーブル 登録処理
						updateCodeNyukalot(bean);

						// 原材料ラベル発行実績テーブル 登録処理
						updateJissekiGenzairyoLabelhakko(bean, check);
					}

				} else if (ArrivalListLogicImpl.ORDER_DIVISION3.equals(bean
						.getOrderDivision())
						|| ArrivalListLogicImpl.ORDER_DIVISION6.equals(bean
								.getOrderDivision())) {
					// 外注製品、仕入在庫品

					// 登録されていない場合のみ
					if (dataSeihinNyukaDao.getEntity(bean.getLotNo()) == null) {
						// 製品入荷データテーブル 登録処理
						updateDataSeihinNyuka(bean);
					}
				}
			}
		} catch (SQLRuntimeException e) {
			String errMsg = "errors.arrival.update.if.table.detail";
			throw new ArrivalLogicException(errMsg, " 行番号 "
					+ String.valueOf(rowNo));
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
			// Lot再発番しない
			if (updBean.getLotNo() != null && !updBean.getLotNo().equals("")) {
				return;
			}
			// 在庫管理区分3:更新除外またはロット管理区分1:しない場合は発番なし
			if ((STOCK_DIVISION_NOT_UPDATE.equals(itemBean.getStockDivision()))
					|| (LOT_DIVISION_NOT_MANAGE.equals(itemBean
							.getLotDivision()))) {
				updBean.setLotNo(Constants.LOTNO_WITHOUT_LOT);
				return;
			} else {
				BigDecimal orderDiv = updBean.getOrderDivision();
				if (orderDiv.equals(ArrivalListLogicImpl.ORDER_DIVISION1)) {
					// 原材料のロット番号('N'+西暦6桁+連番4桁)
					lotNo = getNumberLogic.getNLotNo(null);
				} else if (orderDiv
						.equals(ArrivalListLogicImpl.ORDER_DIVISION3)
						|| orderDiv
								.equals(ArrivalListLogicImpl.ORDER_DIVISION6)) {
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
	 * 入荷ロット原材料コード対応テーブル(計装I/F) 登録処理
	 * @param bean 入荷入力Bean
	 * @throws DuplicateRuntimeException
	 */
	private void updateCodeNyukalot(final ArrivalDetailList bean)
			throws DuplicateRuntimeException {
		CodeNyukalot lotBean = new CodeNyukalot();
		lotBean.setNyukalot(AecStringUtils.editSpecifiedSize(bean.getLotNo(),
			11)); // 入荷ロット
		lotBean.setGenzaicode(AecStringUtils.editSpecifiedSize(
			bean.getItemCd(), 8)); // 原材料コード

		// 登録処理
		int insertNum = codeNyukalotDao.insert(lotBean);
		if (insertNum != 1) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 原材料ラベル発行実績テーブル(計装I/F) 登録処理
	 * @param bean 入荷入力Bean
	 * @param check 数値項目用表示ロジッククラス
	 * @throws DuplicateRuntimeException
	 * @throws ParseException 予想外エラー
	 */
	private void updateJissekiGenzairyoLabelhakko(final ArrivalDetailList bean,
			final CheckDigitUtilsLogic check) throws DuplicateRuntimeException,
			ParseException {
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
			nyukaryo = check.round(bean.getUnitDiv(), VENDER_DIV_SI, bean
					.getVenderCd(), nyukaryo);
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
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 製品入荷データテーブル(計装I/F) 登録処理
	 * @param bean 入荷入力Bean
	 * @throws DuplicateRuntimeException
	 */
	private void updateDataSeihinNyuka(final ArrivalDetailList bean)
			throws DuplicateRuntimeException {
		DataSeihinNyuka nyukaBean = new DataSeihinNyuka();
		nyukaBean.setSeihincode(AecStringUtils.editSpecifiedSize(bean
				.getItemCd(), 8)); // 製品コード
		nyukaBean.setIocode(AecStringUtils.editSpecifiedSize(bean
				.getOtherCompanyCd1(), 8)); // 他社コード１
		if (ArrivalListLogicImpl.SHIPPER_DIVISION_KAO.equals(bean
				.getShipperDivision())) {
			nyukaBean.setJishaflag(ArrivalListLogicImpl.JISHAFLAG_KAO); // 花王
		} else {
			nyukaBean.setJishaflag(ArrivalListLogicImpl.JISHAFLAG_JISYA); // 自社
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
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発番処理用ロジッククラスoを設定します。
	 * @param getNumberLogic 発番処理用ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * 入荷入力 詳細画面用Daoを設定します。
	 * @param arrivalDetailListDao 入荷入力 詳細画面用Dao
	 */
	public void setArrivalDetailDao(
			final ArrivalDetailListDao arrivalDetailListDao) {
		this.arrivalDetailListDao = arrivalDetailListDao;
	}

	/**
	 * 購買外注オーダ更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買外注オーダ更新用Dao
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
}
