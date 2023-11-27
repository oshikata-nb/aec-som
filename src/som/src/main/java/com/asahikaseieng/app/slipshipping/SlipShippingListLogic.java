/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.slipshipping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.slipshipping.SlipShippingPostalClientListForComboboxes;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryDetail.RepSlipShippingDeliveryDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryHeader.RepSlipShippingDeliveryHeader;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingList;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingDetailListForReport;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷帳票検索画面 ロジッククラス interface.
 * @author tosco
 */
public interface SlipShippingListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<SlipShippingList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<SlipShippingList> getSearchList(
			final SlipShippingListPagerCondition condition)
			throws NoDataException;

	/**
	 * 出荷伝票番号の付番を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @return ArrayList<String> 発行伝票番号
	 * @throws NoDataException データが存在しない例外
	 */
	ArrayList<String> getAddSlipShippingNo(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;

	/**
	 * 選択した出荷伝票の取り消し処理を行う
	 * @param frm 出荷帳票画面フォーム
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	void canselSlipPublish(final SlipShippingListForm frm,
			final String loginUserId) throws Exception;

	/**
	 * 出荷指図書発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgDirection(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;

	/**
	 * 出荷予定表発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgSchedule(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;

	/**
	 * 荷札、ペリカン伝票発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgTag(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;

	/**
	 * 出荷依頼書発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgRequest(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;
	
	/**
	 * 運賃表発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgFare(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;

	/**
	 * 納品伝票発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgDelivery(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;
	
	/**
	 * 新郵政発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgPostal(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;
	
	/**
	 * 新荷札発行済みフラグ、更新日時設定処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void setPrintOutFlgNewTag(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;
	
	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> getCreateCarryCombobox();
	
	/**
	 * 郵政依頼主コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getCreatePostalClintCombobox();

	/**
	 * 出荷帳票 ヘッダ帳票Excel検索画面一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipShippingList> 検索結果リスト
	 */
	List<SlipShippingListForReport> getSlipHeaderList(
			final SlipShippingListConditionForReport condition);

	/**
	 * 出荷帳票 詳細帳票Excel検索画面一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipShippingList> 検索結果リスト
	 */
	List<SlipShippingDetailListForReport> getSlipDetailList(
			final SlipShippingListConditionForReport condition);
	

	/**
	 * 出荷バーコード解除処理を行う.
	 * @param shippingNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @throws NoDataException データが存在しない例外
	 */
	void clearCarryBC(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException;
	
	/**
	 * 
	 * 運送会社バーコードの更新
	 * @param bcHashMap
	 * @param headerList
	 * @param loginUserId
	 * @throws NoDataException
	 */
	public void setCarryBC(final HashMap<String , String> bcHashMap,
			 final List<RepSlipShippingDeliveryDetail> detailList,
			 final String loginUserId) throws NoDataException;

}
