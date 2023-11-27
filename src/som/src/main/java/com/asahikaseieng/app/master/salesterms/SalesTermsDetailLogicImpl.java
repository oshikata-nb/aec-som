/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.salesterms.SalesTerms;
import com.asahikaseieng.dao.entity.master.salesterms.SalesTermsDao;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetailDao;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersionDao;
import com.asahikaseieng.dao.nonentity.master.salestermsbalancelist.SalesTermsBalanceList;
import com.asahikaseieng.dao.nonentity.master.salestermsbalancelist.SalesTermsBalanceListDao;
import com.asahikaseieng.dao.nonentity.master.salestermsdelete.SalesTermsDeleteDao;
import com.asahikaseieng.dao.nonentity.master.salestermsdetaillist.SalesTermsDetailList;
import com.asahikaseieng.dao.nonentity.master.salestermsdetaillist.SalesTermsDetailListDao;
import com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist.SalesTermsDuplicateList;
import com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist.SalesTermsDuplicateListDao;
import com.asahikaseieng.dao.nonentity.master.salestermslastseq.SalesTermsLastSeq;
import com.asahikaseieng.dao.nonentity.master.salestermslastseq.SalesTermsLastSeqDao;
import com.asahikaseieng.dao.nonentity.master.salestermsremarklist.SalesTermsRemarkList;
import com.asahikaseieng.dao.nonentity.master.salestermsremarklist.SalesTermsRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 販売条件詳細ロジック 実装クラス.
 * @author t0011036
 */
public class SalesTermsDetailLogicImpl implements SalesTermsDetailLogic {

	private SalesTermsDao salesTermsDao;

	private SalesTermsDeleteDao salesTermsDeleteDao;

	private SalesTermsDetailListDao salesTermsDetailListDao;

	private SalesTermsBalanceListDao salesTermsBalanceListDao;

	private DeliveryDetailDao deliveryDetailDao;

	private BalanceDetailDao balanceDetailDao;

	private ItemQueueLastVersionDao itemQueueLastVersionDao;

	private SalesTermsDuplicateListDao salesTermsDuplicateListDao;

	private SalesTermsLastSeqDao salesTermsLastSeqDao;

	private SalesTermsRemarkListDao salesTermsRemarkListDao;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsDetailLogicImpl() {
	}

	/**
	 * 販売条件検索（登録用）
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @param itemCd itemCd
	 * @param seq seq
	 * @return SalesTerms
	 * @throws NoDataException NoDataException
	 */
	public SalesTerms getEntity(final String deliveryCd,
			final String balanceCd, final String itemCd, final BigDecimal seq)
			throws NoDataException {
		if (StringUtils.isEmpty(deliveryCd)) {
			throw new IllegalArgumentException("deliveryCd is empty");
		}

		if (StringUtils.isEmpty(balanceCd)) {
			throw new IllegalArgumentException("balanceCd is empty");
		}

		if (StringUtils.isEmpty(itemCd)) {
			throw new IllegalArgumentException("itemCd is empty");
		}

		SalesTerms bean = salesTermsDao.getEntity(deliveryCd, balanceCd,
			itemCd, seq);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 備考マスタ検索処理
	 * 
	 * @param frm SalesTermsRemarkList
	 * @return String 備考マスタ 検索結果
	 * @throws NoDataException データが存在しない例外
	 */
	public String getRemarkList(final SalesTermsDetailForm frm)
			throws NoDataException {

		String venderCd = null;
		String deliveryCd = null;
		List<SalesTermsDetailList> itemList = null;

		// 一次店の得意先コードを取得
		if (frm.getSearchSalesTermsBalanceList() != null
				&& frm.getSearchSalesTermsBalanceList().size() != 0) {
			venderCd = frm.getSearchSalesTermsBalanceList().get(0)
					.getVenderCd();
		}
		deliveryCd = frm.getDeliveryCd(); // 納入先
		itemList = frm.getSearchSalesTermsDetailList();

		// 得意先と納入先に紐付く備考を検索。
		List<SalesTermsRemarkList> remarkList = salesTermsRemarkListDao
				.getRemarkListNoItem(venderCd, deliveryCd);

		for (SalesTermsDetailList bean : itemList) {
			List<SalesTermsRemarkList> list = salesTermsRemarkListDao
					.getRemarkListWithItem(bean.getItemCd(), venderCd,
						deliveryCd);

			remarkList.addAll(list);
		}

		if (remarkList.isEmpty()) {
			throw new NoDataException();
		}

		// *******************************
		// 取得した備考をセット
		// *******************************
		StringBuffer sbfRemark = new StringBuffer();
		// 取得した備考を全てセット、区切りとして改行を追加
		for (int i = 0; i < remarkList.size(); i++) {

			if (remarkList.get(i).getRemark01() != null) {
				if (sbfRemark.length() != 0) {
					sbfRemark.append(System.getProperty("line.separator"));
				}
				sbfRemark.append(remarkList.get(i).getRemark01());
			}
		}

		return sbfRemark.toString();
	}

	/**
	 * 返信時備考マスタ検索処理
	 * 
	 * @param frm SalesTermsRemarkList
	 * @return String 備考マスタ 検索結果
	 * @throws NoDataException データが存在しない例外
	 */
	public String getRemarkListRe(final SalesTermsDetailForm frm)
			throws NoDataException {

		String venderCd = null;
		String deliveryCd = null;
		List<SalesTermsDetailList> itemList = null;

		// 一次店の得意先コードを取得
		if (frm.getSearchSalesTermsBalanceList() != null
				&& frm.getSearchSalesTermsBalanceList().size() != 0) {
			venderCd = frm.getSearchSalesTermsBalanceList().get(0)
					.getVenderCd();
		}
		deliveryCd = frm.getDeliveryCd(); // 納入先
		itemList = frm.getSearchSalesTermsDetailList();

		// 得意先と納入先に紐付く備考を検索。
		List<SalesTermsRemarkList> remarkList = salesTermsRemarkListDao
				.getRemarkListNoItem(venderCd, deliveryCd);

		for (SalesTermsDetailList bean : itemList) {
			List<SalesTermsRemarkList> list = salesTermsRemarkListDao
					.getRemarkListWithItem(bean.getItemCd(), venderCd,
						deliveryCd);

			remarkList.addAll(list);
		}

		if (remarkList.isEmpty()) {
			throw new NoDataException();
		}

		// *******************************
		// 取得した備考をセット
		// *******************************
		StringBuffer sbfRemark = new StringBuffer();
		// 取得した備考を全てセット、区切りとして改行を追加
		for (int i = 0; i < remarkList.size(); i++) {

			if (remarkList.get(i).getRemark15() != null) {
				if (sbfRemark.length() != 0) {
					sbfRemark.append(System.getProperty("line.separator"));
				}
				sbfRemark.append(remarkList.get(i).getRemark15());
			}
		}

		return sbfRemark.toString();
	}

	/**
	 * 販売条件検索（詳細用）
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @return List<SalesTermsDetailList>
	 */
	public List<SalesTermsDetailList> getList(final String deliveryCd,
			final String balanceCd) {
		List<SalesTermsDetailList> bean = salesTermsDetailListDao.getList(
			deliveryCd, balanceCd);
		return bean;
	}

	/**
	 * 帳合一覧検索
	 * @param balanceCd balanceCd
	 * @return List<SalesTermsBalanceList>
	 */
	public List<SalesTermsBalanceList> getBalanceList(final String balanceCd) {
		List<SalesTermsBalanceList> bean = salesTermsBalanceListDao
				.getList(balanceCd);
		return bean;
	}

	/**
	 * 納入先検索
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	public DeliveryDetail getDeliveryEntity(final String deliveryCd) {
		DeliveryDetail bean = deliveryDetailDao.getEntity(deliveryCd);
		return bean;
	}

	/**
	 * 帳合検索
	 * @param balanceCd 帳合コード
	 * @return BalanceDetail
	 */
	public BalanceDetail getBalanceEntity(final String balanceCd) {
		BalanceDetail bean = balanceDetailDao.getEntity(balanceCd);
		return bean;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	public ItemQueueLastVersion getItemQueueEntity(final String itemCd) {
		ItemQueueLastVersion bean = itemQueueLastVersionDao
				.getLastVersion(itemCd);
		return bean;
	}

	/**
	 * 販売条件一括削除
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @return 削除件数
	 */
	public int deleteSalesTermsList(final String deliveryCd,
			final String balanceCd) {
		return salesTermsDeleteDao.deleteList(deliveryCd, balanceCd);
	}

	/**
	 * 納入先＆品目重複チェック
	 * @param deliveryCd 納入先コード
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目コード
	 * @return List<SalesTermsDuplicate>
	 */
	public List<SalesTermsDuplicateList> getDuplicate(final String deliveryCd,
			final String balanceCd, final String itemCd) {
		List<SalesTermsDuplicateList> list = salesTermsDuplicateListDao
				.getDuplicateList(deliveryCd, balanceCd, itemCd);
		return list;
	}

	/**
	 * 納入先＆帳合＆品目重複チェック
	 * @param deliveryCd 納入先コード
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目コード
	 * @return List<SalesTermsDuplicate>
	 */
	public List<SalesTermsDuplicateList> getDuplicateAll(
			final String deliveryCd, final String balanceCd, final String itemCd) {
		List<SalesTermsDuplicateList> list = salesTermsDuplicateListDao
				.getDuplicateAllList(deliveryCd, balanceCd, itemCd);
		return list;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final SalesTerms bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			salesTermsDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param frm 登録データ
	 * @param lastSeq 最終行番
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void insert(final SalesTermsDetailForm frm,
			final BigDecimal lastSeq, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		SalesTerms bean = new SalesTerms();

		try {
			for (int i = 0; i < frm.getSearchSalesTermsDetailList().size(); i++) {
				/* 値を更新用Beanにコピる */
				IgnoreCaseBeanUtils.copyProperties(bean, frm);

				/* コピーしきれなかった分は手で */
				bean.setItemCd(frm.getSearchSalesTermsDetailList().get(i)
						.getItemCd());
				bean.setSeq(lastSeq);
				bean.setInputDate(bean.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);
				bean.setUpdatorCd(tantoCd);

				// 担当者コードは納入先マスタから表示のみとする為Null値をＤＢに書込む
				bean.setTantoCd(null);

				/* 追加処理 */
				salesTermsDao.insert(bean);

				lastSeq.add(new BigDecimal("1"));
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 登録登録
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void regist(final SalesTermsDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		SalesTerms bean = new SalesTerms();

		try {
			/* 一括削除処理 */
			deleteSalesTermsList(frm.getDeliveryCd(), frm.getBalanceCd());

			for (int i = 0; i < frm.getSearchSalesTermsDetailList().size(); i++) {
				/* 値を更新用Beanにコピる */
				IgnoreCaseBeanUtils.copyProperties(bean, frm);

				/* コピーしきれなかった分は手で */
				bean.setItemCd(frm.getSearchSalesTermsDetailList().get(i)
						.getItemCd());
				bean.setSeq(new BigDecimal(i + 1));
				bean.setInputDate(bean.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);
				bean.setUpdatorCd(tantoCd);

				// 担当者コードは納入先マスタから表示のみとする為Null値をＤＢに書込む
				bean.setTantoCd(null);

				/* 追加処理 */
				salesTermsDao.insert(bean);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final SalesTerms bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			salesTermsDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 納入先&帳合で最終行を取得
	 * @param deliveryCd 納入先コード
	 * @param balanceCd 帳合コード
	 * @return SalesTermsLastSeq
	 */
	public SalesTermsLastSeq getLastSeq(final String deliveryCd,
			final String balanceCd) {
		SalesTermsLastSeq bean = salesTermsLastSeqDao.getLastSeq(deliveryCd,
			balanceCd);
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * salesTermsDaoを設定します。
	 * @param salesTermsDao salesTermsDao
	 */
	public void setSalesTermsDao(final SalesTermsDao salesTermsDao) {
		this.salesTermsDao = salesTermsDao;
	}

	/**
	 * salesTermsDetailListDaoを設定します。
	 * @param salesTermsDetailListDao salesTermsDetailListDao
	 */
	public void setSalesTermsDetailListDao(
			final SalesTermsDetailListDao salesTermsDetailListDao) {
		this.salesTermsDetailListDao = salesTermsDetailListDao;
	}

	/**
	 * salesTermsBalanceListDaoを設定します。
	 * @param salesTermsBalanceListDao salesTermsBalanceListDao
	 */
	public void setSalesTermsBalanceListDao(
			final SalesTermsBalanceListDao salesTermsBalanceListDao) {
		this.salesTermsBalanceListDao = salesTermsBalanceListDao;
	}

	/**
	 * salesTermsDeleteDaoを設定します。
	 * @param salesTermsDeleteDao salesTermsDeleteDao
	 */
	public void setSalesTermsDeleteDao(
			final SalesTermsDeleteDao salesTermsDeleteDao) {
		this.salesTermsDeleteDao = salesTermsDeleteDao;
	}

	/**
	 * deliveryDetailDaoを設定します。
	 * @param deliveryDetailDao deliveryDetailDao
	 */
	public void setDeliveryDetailDao(final DeliveryDetailDao deliveryDetailDao) {
		this.deliveryDetailDao = deliveryDetailDao;
	}

	/**
	 * balanceDetailDaoを設定します。
	 * @param balanceDetailDao balanceDetailDao
	 */
	public void setBalanceDetailDao(final BalanceDetailDao balanceDetailDao) {
		this.balanceDetailDao = balanceDetailDao;
	}

	/**
	 * salesTermsDuplicateListDaoを設定します。
	 * @param salesTermsDuplicateListDao salesTermsDuplicateListDao
	 */
	public void setSalesTermsDuplicateListDao(
			final SalesTermsDuplicateListDao salesTermsDuplicateListDao) {
		this.salesTermsDuplicateListDao = salesTermsDuplicateListDao;
	}

	/**
	 * salesTermsLastSeqDaoを設定します。
	 * @param salesTermsLastSeqDao salesTermsLastSeqDao
	 */
	public void setSalesTermsLastSeqDao(
			final SalesTermsLastSeqDao salesTermsLastSeqDao) {
		this.salesTermsLastSeqDao = salesTermsLastSeqDao;
	}

	/**
	 * itemQueueLastVersionDaoを設定します。
	 * @param itemQueueLastVersionDao itemQueueLastVersionDao
	 */
	public void setItemQueueLastVersionDao(
			final ItemQueueLastVersionDao itemQueueLastVersionDao) {
		this.itemQueueLastVersionDao = itemQueueLastVersionDao;
	}

	/**
	 * salesTermsRemarkListDaoを設定します。
	 * @param salesTermsRemarkListDao salesTermsRemarkListDao
	 */
	public void setSalesTermsRemarkListDao(
			final SalesTermsRemarkListDao salesTermsRemarkListDao) {
		this.salesTermsRemarkListDao = salesTermsRemarkListDao;
	}
}
