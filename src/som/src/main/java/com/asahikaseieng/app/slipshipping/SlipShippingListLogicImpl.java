/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.slipshipping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.shipping.Shipping;
import com.asahikaseieng.dao.entity.shipping.ShippingDao;
import com.asahikaseieng.dao.nonentity.comboboxes.slipshipping.SlipShippingCarryListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.slipshipping.SlipShippingCarryListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.slipshipping.SlipShippingPostalClientListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.slipshipping.SlipShippingPostalClientListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCtlCarryBcSeqDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.pakcarryfile.MakeCarryFileDto;
import com.asahikaseieng.dao.nonentity.procedurecall.pakcarryfile.PakCarryFileDao;
import com.asahikaseieng.dao.nonentity.repShippingSlipOrderList.RepShippingSlipOrderList;
import com.asahikaseieng.dao.nonentity.repShippingSlipOrderList.RepShippingSlipOrderListDao;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryDetail.RepSlipShippingDeliveryDetail;
import com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryHeader.RepSlipShippingDeliveryHeader;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingList;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingListDao;
import com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingDetailListForReport;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListForReport;
import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListForReportDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 出荷帳票検索画面 ロジック実装クラス
 * @author tosco
 */
public class SlipShippingListLogicImpl implements SlipShippingListLogic {

	private SlipShippingListDao slipShippingListDao;

	private RepShippingSlipOrderListDao repShippingSlipOrderListDao;

	private SlipShippingCarryListForComboboxesDao slipShippingCarryListForComboboxesDao;
	
	private SlipShippingPostalClientListForComboboxesDao slipShippingPostalClientListForComboboxesDao;

	private ShippingDao shippingDao;

	private GetNumberLogic getNumberLogic;

	private SlipShippingListForReportDao slipShippingListForReportDao;

	private SlipShippingDetailListForReportDao slipShippingDetailListForReportDao;
	
	/**
	 * コンストラクタ.出荷帳票検索画面
	 */
	public SlipShippingListLogicImpl() {
	}

	/**
	 * 出荷帳票検索画面一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipShippingList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<SlipShippingList> getSearchList(
			final SlipShippingListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<SlipShippingList> list = slipShippingListDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 出荷帳票 ヘッダ帳票Excel検索画面一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipShippingList> 検索結果リスト
	 */
	public List<SlipShippingListForReport> getSlipHeaderList(
			final SlipShippingListConditionForReport condition) {
		List<SlipShippingListForReport> list = slipShippingListForReportDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 出荷帳票 詳細帳票Excel検索画面一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipShippingList> 検索結果リスト
	 */
	public List<SlipShippingDetailListForReport> getSlipDetailList(
			final SlipShippingListConditionForReport condition) {
		List<SlipShippingDetailListForReport> list = slipShippingDetailListForReportDao
				.getDetailSearchList(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 出荷伝票番号付番処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @return ArrayList<String> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public ArrayList<String> getAddSlipShippingNo(
			final ArrayList<String> shippingNo, final String loginUserId)
			throws NoDataException {

		long index = 0;
		boolean startSetShippngSlipNo = false;

		checkSlipShippingParams(shippingNo);

		ArrayList<String> shippingNoList = new ArrayList<String>();

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 前の検索結果を保持
		RepShippingSlipOrderList beforBean = new RepShippingSlipOrderList();

		// 出荷伝票番号を保持
		String slipShippingNo = new String();

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());

			// 出荷伝票番号がＮＵＬＬでは無い場合（すでに発行済み）
			if (bean.getShippingSlipNo() != null) {
				// 出荷番号を保持
				slipShippingNo = bean.getShippingSlipNo();
				shippingNoList.add(slipShippingNo);

				// 出荷伝票番号がＮｕｌｌかつ出荷伝票番号発行開始フラグがＯＦＦの場合
			} else if (bean.getShippingSlipNo() == null
					&& !startSetShippngSlipNo) {
				startSetShippngSlipNo = true; // 出荷伝票番号発行開始フラグ ON

				// 新規に出荷伝票番号を付番号
				slipShippingNo = getNumberLogic.getSlipShippingNo();

				// 出荷番号を保持
				shippingNoList.add(slipShippingNo);
				index = 1;
				updateBean.setShippingSlipRowNo(new BigDecimal(index));

				// 集約単位が切り替わったかチェック
			} else if (isChangeSyuyaku(beforBean, bean)) {
				// 新規に出荷伝票番号を付番号
				slipShippingNo = getNumberLogic.getSlipShippingNo();

				// 出荷番号を保持
				shippingNoList.add(slipShippingNo);
				index = 1;
				updateBean.setShippingSlipRowNo(new BigDecimal(index));
			} else {
				updateBean.setShippingSlipRowNo(new BigDecimal(++index));
			}

			// 出荷伝票番号設定
			updateBean.setShippingSlipNo(slipShippingNo);
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipPublishComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipPublishDate(AecDateUtils.getCurrentTimestamp());
			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

			// 以前のデータを保持
			beforBean = bean;
		}

		return shippingNoList;
	}

	/**
	 * 集約単位が切り替わったかチェック
	 * @param beforBean 前データ
	 * @param bean 後データ
	 * @return 変ったらTRUE
	 */
	private boolean isChangeSyuyaku(final RepShippingSlipOrderList beforBean,
			final RepShippingSlipOrderList bean) {
		if (!beforBean.getScheduledShippingDate().equals(
			bean.getScheduledShippingDate())) {
			return true;
		}
		if (!beforBean.getUpperLocationCd().equals(bean.getUpperLocationCd())) {
			return true;
		}
		if (!beforBean.getDeliveryCd().equals(bean.getDeliveryCd())) {
			return true;
		}
		if (!beforBean.getDeliveryExpectedDate().equals(
			bean.getDeliveryExpectedDate())) {
			return true;
		}
		if (!beforBean.getVenderCd().equals(bean.getVenderCd())) {
			return true;
		}
		if (!beforBean.getCarryCd().equals(bean.getCarryCd())) {
			return true;
		}
		if (!beforBean.getOrderNo().equals(bean.getOrderNo())) {
			return true;
		}
		return false;
	}

	/**
	 * 出荷指図書発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgDirection(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingOrderComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingOrderDate(AecDateUtils
					.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}

	/**
	 * 出荷予定表発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgSchedule(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingScheduleComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingScheduleDate(AecDateUtils
					.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}

	/**
	 * 荷札、ペリカン伝票発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgTag(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingTagComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingTagDate(AecDateUtils
					.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}

	/**
	 * 出荷依頼書発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgRequest(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingRequestComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingRequestDate(AecDateUtils
					.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}
	
	/**
	 * 運賃表発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgFare(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingFareComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingFareDate(AecDateUtils
					.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}
	
	/**
	 * 納品伝票発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgDelivery(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingDelivelyComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingDelivelyDate(AecDateUtils.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}
	
	/**
	 * 新郵政発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgPostal(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingPostalComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingPostalDate(AecDateUtils.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}
	
	/**
	 * 新荷札発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPrintOutFlgNewTag(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setSlipShippingNewTagComp(new BigDecimal(1));
			// 伝票発行日設定
			updateBean.setSlipShippingNewTagDate(AecDateUtils.getCurrentTimestamp());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}

	/**
	 * 選択した出荷伝票の取り消し処理を行う
	 * @param frm 出荷帳票画面フォーム
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	public void canselSlipPublish(final SlipShippingListForm frm,
			final String loginUserId) throws Exception {
		List<SlipShippingList> srarchList = frm.getSearchList();

		for (SlipShippingList bean : srarchList) {
			// チェックのないものは読み飛ばす
			if (!bean.isSlipShippingCheckBox()) {
				continue;
			}

			// 更新データ格納用Bean
			Shipping updateBean = new Shipping();
			// 取得
			updateBean = shippingDao.getEntity(bean.getShippingNo());

			// 出荷伝票番号
			updateBean.setShippingSlipNo(null);
			// 出荷伝票行番号
			updateBean.setShippingSlipRowNo(null);
			// 伝票番号発行済区分
			updateBean.setSlipPublishComp(new BigDecimal(0));
			// 伝票発行日
			updateBean.setSlipPublishDate(null);

			// 更新者セット
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時セット
			updateBean.setUpdateDate(bean.getUpdateDate());

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}
		}
	}

	/**
	 * 運送会社を全件取得する
	 * @return List<SlipShippingCarryListForComboboxes>
	 */
	public List<SlipShippingCarryListForComboboxes> getAllCarry() {
		List<SlipShippingCarryListForComboboxes> list = slipShippingCarryListForComboboxesDao.getListForComboboxes();
		
		return list;
	}

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getCreateCarryCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 運送会社全件検索
		List<SlipShippingCarryListForComboboxes> lineList = getAllCarry();

		// すべてを先頭に設定
		if (true) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("すべて");
			res.add(item);
		}

		for (SlipShippingCarryListForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			if (bean.getCarryName1() != null) {
				if (bean.getCarryName2() != null) {
					item.setLabales(bean.getCarryName1() + " "
							+ bean.getCarryName2());
					item.setValues(bean.getCarryCd());
				} else {
					item.setLabales(bean.getCarryName1());
					item.setValues(bean.getCarryCd());
				}
			}
			res.add(item);
		}
		return res;
	}
	

	/**
	 * 納品伝票発行済みフラグ、更新日時設定処理
	 * 
	 * @param shippingNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @throws NoDataException データが存在しない例外
	 */
	public void clearCarryBC(final ArrayList<String> shippingNo,
			final String loginUserId) throws NoDataException {

		checkSlipShippingParams(shippingNo);

		// 出荷番号で帳票出力する出荷トランザクションを取得
		List<RepShippingSlipOrderList> list = repShippingSlipOrderListDao
				.getShippingList(shippingNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果ループ
		for (RepShippingSlipOrderList bean : list) {

			// 更新データ取得処理
			Shipping updateBean = shippingDao.getEntity(bean.getShippingNo());
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			// 発行済みフラグＯＮ
			updateBean.setCarryBC("");

			try {
				// 出荷番号をキーに更新を行う
				shippingDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

		}

	}
	
	/**
	 * 
	 * 運送会社バーコードの更新
	 * @param bcHashMap
	 * @param detailList
	 * @param loginUserId
	 * @throws NoDataException
	 */
	public void setCarryBC(final HashMap<String , String> bcHashMap,
			 final List<RepSlipShippingDeliveryDetail> detailList,
			 final String loginUserId) throws NoDataException {

		if (bcHashMap == null || bcHashMap.size() == 0) {
			return;
		}
		
		if (detailList == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.detailList");
		}
		
		
		// 出荷番号で帳票出力する出荷トランザクションを取得
		for ( Map.Entry<String,String> kv : bcHashMap.entrySet() )
		{
			for( RepSlipShippingDeliveryDetail detail : detailList ){
				
				// キー情報が一致した場合、出荷情報を更新する
				if( kv.getKey().equals(detail.getKey()) )
				{
					// 更新データ取得処理
					Shipping updateBean = shippingDao.getEntity(detail.getShippingNo());
					// 更新者設定
					updateBean.setUpdatorCd(loginUserId);
					// 排他用に更新日時設定
					updateBean.setUpdateDate(updateBean.getUpdateDate());
					// 発行済みフラグＯＮ
					updateBean.setCarryBC(kv.getValue());

					try {
						// 出荷番号をキーに更新を行う
						shippingDao.update(updateBean);
						// 更新時に、すでに更新されていた場合
					} catch (NotSingleRowUpdatedRuntimeException e) {
						throw new OptimisticLockRuntimeException();
					}
				}
			}
		}
	}
	
	/**
	 * 郵政依頼主を全件取得する
	 * @return List<SlipShippingCarryListForComboboxes>
	 */
	public List<SlipShippingPostalClientListForComboboxes> getAllPostalClient() {
		List<SlipShippingPostalClientListForComboboxes> list = slipShippingPostalClientListForComboboxesDao.getListForComboboxes();
		return list;
	}
	
	/**
	 * 郵政依頼主コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getCreatePostalClintCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 運送会社全件検索
		List<SlipShippingPostalClientListForComboboxes> lineList = getAllPostalClient();

		for (SlipShippingPostalClientListForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			
					item.setLabales(bean.getPostalName()); // 郵政依頼主略称
					item.setValues(bean.getPostalClientCd()); //郵政依頼主コード
				
			res.add(item);
		}
		return res;
	}
	
	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final SlipShippingListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * パラメータチェック
	 * @param shippingNo 出荷番号
	 */
	private void checkSlipShippingParams(final ArrayList<String> shippingNo) {

		if (shippingNo == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * slipShippingListDaoを設定します。
	 * 
	 * @param slipShippingListDao slipShippingListDao
	 */
	public void setSlipShippingListDao(
			final SlipShippingListDao slipShippingListDao) {
		this.slipShippingListDao = slipShippingListDao;

	}

	/**
	 * slipShippingCarryListForComboboxesDaoを設定します。
	 * @param slipShippingCarryListForComboboxesDao
	 *            slipShippingCarryListForComboboxesDao
	 */
	public void setSlipShippingCarryListForComboboxesDao(
			final SlipShippingCarryListForComboboxesDao slipShippingCarryListForComboboxesDao) {
		this.slipShippingCarryListForComboboxesDao = slipShippingCarryListForComboboxesDao;
	}

	/**
	 * slipShippingPostalClientListForComboboxesDaoを設定します。
	 * @param slipShippingPostalClientListForComboboxesDao slipShippingPostalClientListForComboboxesDao
	 */
	public void setSlipShippingPostalClientListForComboboxesDao(
			SlipShippingPostalClientListForComboboxesDao slipShippingPostalClientListForComboboxesDao) {
		this.slipShippingPostalClientListForComboboxesDao = slipShippingPostalClientListForComboboxesDao;
	}

	/**
	 * shippingDaoを設定します。
	 * @param shippingDao shippingDao
	 */
	public void setShippingDao(final ShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}

	/**
	 * repShippingSlipOrderListDaoを設定します。
	 * @param repShippingSlipOrderListDao repShippingSlipOrderListDao
	 */
	public void setRepShippingSlipOrderListDao(
			final RepShippingSlipOrderListDao repShippingSlipOrderListDao) {
		this.repShippingSlipOrderListDao = repShippingSlipOrderListDao;
	}

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * slipShippingListForReportDaoを設定します。
	 * @param slipShippingListForReportDao slipShippingListForReportDao
	 */
	public void setSlipShippingListForReportDao(
			final SlipShippingListForReportDao slipShippingListForReportDao) {
		this.slipShippingListForReportDao = slipShippingListForReportDao;
	}

	/**
	 * slipShippingDetailListForReportDaoを設定します。
	 * @param slipShippingDetailListForReportDao
	 *            slipShippingDetailListForReportDao
	 */
	public void setSlipShippingDetailListForReportDao(
			final SlipShippingDetailListForReportDao slipShippingDetailListForReportDao) {
		this.slipShippingDetailListForReportDao = slipShippingDetailListForReportDao;
	}

}
