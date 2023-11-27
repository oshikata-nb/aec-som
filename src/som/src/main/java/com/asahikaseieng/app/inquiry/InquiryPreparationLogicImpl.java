/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.dao.entity.inventorycount.InventoryCount;
import com.asahikaseieng.dao.entity.inventorycount.InventoryCountDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCount;
import com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCountDao;
import com.asahikaseieng.dao.nonentity.inquirylocationcount.InquiryLocationCount;
import com.asahikaseieng.dao.nonentity.inquirylocationcount.InquiryLocationCountDao;
import com.asahikaseieng.dao.nonentity.inquirypreparationlist.InquiryPreparationList;
import com.asahikaseieng.dao.nonentity.inquirypreparationlist.InquiryPreparationListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 棚卸準備処理ロジック 実装クラス.
 * @author tanaka
 */
public class InquiryPreparationLogicImpl implements InquiryPreparationLogic {

	private InquiryPreparationListDao inquiryPreparationListDao;

	private InquiryLocationCountDao inquiryLocationCountDao;

	private InquiryInventoryCountDao inquiryInventoryCountDao;

	private NamesListForComboboxesDao namesListForComboboxesDao;

	private InventoryCountDao inventoryCountDao;

	private ConvInventoryLogic convInventoryLogic;

	/**
	 * コンストラクタ
	 */
	public InquiryPreparationLogicImpl() {
	}

	/**
	 * 棚卸リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<NamesListForComboboxes> getCountDivisionList() {
		List<NamesListForComboboxes> list = namesListForComboboxesDao
				.getListForComboboxes("TANA");
		return list;
	}

	/**
	 * ロケーション棚卸検索
	 * @param countDivision 棚卸区分
	 * @return InquiryLocationCount
	 */
	public InquiryLocationCount getLocationCount(final String countDivision) {
		InquiryLocationCount bean = inquiryLocationCountDao
				.getLocationCount(countDivision);
		return bean;
	}

	/**
	 * 棚卸準備検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @return List<InquiryPreparationList>
	 */
	public List<InquiryPreparationList> getList(final Timestamp countDate,
			final String countDivision) {
		List<InquiryPreparationList> list = inquiryPreparationListDao.getList(
			countDate, countDivision);
		return list;
	}

	/**
	 * 棚卸準備検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @param countLocation ロケーションコード
	 * @param itemCd 品目コード
	 * @param lotNo ロット番号
	 * @param countUpdateDate 棚卸更新日
	 * @return InquiryInventoryCount
	 */
	public InquiryInventoryCount getInventoryCount(final Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo,
			final Timestamp countUpdateDate) {
		InquiryInventoryCount bean = inquiryInventoryCountDao
				.getInventoryCount(countDate, countDivision, countLocation,
					itemCd, lotNo, countUpdateDate);
		return bean;
	}

	/**
	 * 更新処理
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void regist(final InquiryPreparationForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		try {
			for (int i = 0; i < frm.getSearchList().size(); i++) {
				/* 明細取得 */
				List<InquiryPreparationList> list = getList(frm
						.getSrhCountDate(), frm.getSearchList().get(i)
						.getNameCd());

				for (int j = 0; j < list.size(); j++) {
					/* 在庫数量計算 */
					ConvInventoryResult result = convInventoryLogic
							.inventoryToPack(list.get(j).getItemCd(), list.get(
								j).getInventoryQty());

					/* エラー処理 */
					if (result.getCode().equals(new BigDecimal("1"))) {
						result.setPackQty(new BigDecimal("0"));
						result.setFractionQty(new BigDecimal("0"));
					}

					InventoryCount bean = inventoryCountDao.getEntity(list.get(
						j).getCountDate(), list.get(j).getCountDivision(), list
							.get(j).getLocationCd(), list.get(j).getItemCd(),
						list.get(j).getLotNo());

					if (bean == null) {
						/* 追加処理を実行 */
						insert(insertInventoryCount(list.get(j), result,
							tantoCd));
					} else {
						/* 更新処理を実行 */
						update(updateInventoryCount(bean, list.get(j), result,
							tantoCd));
					}
				}
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 更新処理用のInventoryCountを作成する.
	 * @param newBean 登録対象データ
	 * @param list 登録対象データ
	 * @param result 在庫数量変換クラス
	 * @param tantoCd 担当者コード
	 * @return InventoryCount
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private InventoryCount updateInventoryCount(final InventoryCount newBean,
			final InquiryPreparationList list,
			final ConvInventoryResult result, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, list);

		/* コピーしきれなかった分は手で */
		newBean.setCountLocation(list.getLocationCd());
		newBean.setCountQty(result.getPackQty());
		newBean.setInputQty(result.getPackQty());
		newBean.setFractionQty(result.getFractionQty());
		newBean.setInputfraction(result.getFractionQty());
		newBean.setCountCost(list.getInventoryCost());
		newBean.setCountUpdateDate(null);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のInventoryCount
	 * @param list 登録対象データ
	 * @param result 在庫数量変換クラス
	 * @param tantoCd 担当者コード
	 * @return InventoryCount
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private InventoryCount insertInventoryCount(
			final InquiryPreparationList list,
			final ConvInventoryResult result, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		InventoryCount newBean = new InventoryCount();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, list);

		/* コピーしきれなかった分は手で */
		newBean.setCountLocation(list.getLocationCd());
		newBean.setCountQty(result.getPackQty());
		newBean.setInputQty(result.getPackQty());
		newBean.setFractionQty(result.getFractionQty());
		newBean.setInputfraction(result.getFractionQty());
		newBean.setCountCost(list.getInventoryCost());
		newBean.setCountUpdateDate(null);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final InventoryCount bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			inventoryCountDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final InventoryCount bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			inventoryCountDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * namesListForComboboxesDaoを設定します。
	 * @param namesListForComboboxesDao namesListForComboboxesDao
	 */
	public void setNamesListForComboboxesDao(
			final NamesListForComboboxesDao namesListForComboboxesDao) {
		this.namesListForComboboxesDao = namesListForComboboxesDao;
	}

	/**
	 * inquiryPreparationListDaoを設定します。
	 * @param inquiryPreparationListDao inquiryPreparationListDao
	 */
	public void setInquiryPreparationListDao(
			final InquiryPreparationListDao inquiryPreparationListDao) {
		this.inquiryPreparationListDao = inquiryPreparationListDao;
	}

	/**
	 * inquiryInventoryCountDaoを設定します。
	 * @param inquiryInventoryCountDao inquiryInventoryCountDao
	 */
	public void setInquiryInventoryCountDao(
			final InquiryInventoryCountDao inquiryInventoryCountDao) {
		this.inquiryInventoryCountDao = inquiryInventoryCountDao;
	}

	/**
	 * inquiryLocationCountDaoを設定します。
	 * @param inquiryLocationCountDao inquiryLocationCountDao
	 */
	public void setInquiryLocationCountDao(
			final InquiryLocationCountDao inquiryLocationCountDao) {
		this.inquiryLocationCountDao = inquiryLocationCountDao;
	}

	/**
	 * inventoryCountDaoを設定します。
	 * @param inventoryCountDao inventoryCountDao
	 */
	public void setInventoryCountDao(final InventoryCountDao inventoryCountDao) {
		this.inventoryCountDao = inventoryCountDao;
	}

	/**
	 * convInventoryLogicを設定します。
	 * @param convInventoryLogic convInventoryLogic
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}
}
