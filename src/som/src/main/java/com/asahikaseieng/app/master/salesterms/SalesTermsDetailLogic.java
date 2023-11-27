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

import com.asahikaseieng.dao.entity.master.salesterms.SalesTerms;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.salestermsbalancelist.SalesTermsBalanceList;
import com.asahikaseieng.dao.nonentity.master.salestermsdetaillist.SalesTermsDetailList;
import com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist.SalesTermsDuplicateList;
import com.asahikaseieng.dao.nonentity.master.salestermslastseq.SalesTermsLastSeq;
import com.asahikaseieng.exception.NoDataException;

/**
 * 販売条件詳細ロジック interface.
 * @author t0011036
 */
public interface SalesTermsDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @param itemCd itemCd
	 * @param seq seq
	 * @throws NoDataException NoDataException
	 * @return SalesTerms
	 */
	SalesTerms getEntity(final String deliveryCd, final String balanceCd,
			final String itemCd, final BigDecimal seq) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @return List<SalesTermsDetailList>
	 */
	List<SalesTermsDetailList> getList(final String deliveryCd,
			final String balanceCd);

	/**
	 * 帳合一覧検索処理を行う.
	 * @param balanceCd balanceCd
	 * @return List<SalesTermsBalanceList>
	 */
	List<SalesTermsBalanceList> getBalanceList(final String balanceCd);

	/**
	 * 納入先検索処理を行う.
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	DeliveryDetail getDeliveryEntity(final String deliveryCd);

	/**
	 * 帳合検索処理を行う.
	 * @param balanceCd 帳合コード
	 * @return BalanceDetail
	 */
	BalanceDetail getBalanceEntity(final String balanceCd);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	ItemQueueLastVersion getItemQueueEntity(final String itemCd);

	/**
	 * 販売条件一括削除を行う.
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @return 削除件数
	 */
	int deleteSalesTermsList(final String deliveryCd, final String balanceCd);

	/**
	 * 納入先＆品目重複チェック
	 * @param deliveryCd 納入先コード
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目コード
	 * @return List<SalesTermsDuplicate>
	 */
	List<SalesTermsDuplicateList> getDuplicate(final String deliveryCd,
			final String balanceCd, final String itemCd);

	/**
	 * 納入先＆帳合＆品目重複チェック
	 * @param deliveryCd 納入先コード
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目コード
	 * @return List<SalesTermsDuplicate>
	 */
	List<SalesTermsDuplicateList> getDuplicateAll(final String deliveryCd,
			final String balanceCd, final String itemCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final SalesTerms bean);

	/**
	 * 追加処理を行う.
	 * @param frm 追加対象データ
	 * @param lastSeq 最終行番
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void insert(final SalesTermsDetailForm frm, final BigDecimal lastSeq,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 登録処理を行う.
	 * @param frm 追加対象データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void regist(final SalesTermsDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final SalesTerms bean) throws NoDataException;

	/**
	 * 納入先&帳合で最終行を取得
	 * @param deliveryCd 納入先コード
	 * @param balanceCd 帳合コード
	 * @return SalesTermsLastSeq
	 */
	SalesTermsLastSeq getLastSeq(final String deliveryCd, final String balanceCd);

	/**
	 * 備考マスタ検索処理
	 * 
	 * @param frm SalesTermsDetailForm
	 * @return List<SalesTermsRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	String getRemarkList(final SalesTermsDetailForm frm) throws NoDataException;

	/**
	 * 返信時備考マスタ検索処理
	 * 
	 * @param frm SalesTermsDetailForm
	 * @return List<SalesTermsRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	String getRemarkListRe(final SalesTermsDetailForm frm) throws NoDataException;

}
