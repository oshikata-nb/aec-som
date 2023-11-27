/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetail;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.caldetail.CalDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.venderclassdetail.VenderClassDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 取引先詳細ロジック interface.
 * @author t0011036
 */
public interface VenderDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @throws NoDataException NoDataException
	 * @return Vender
	 */
	Vender getEntity(final String venderDivision, final String venderCd)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return VenderDetail
	 */
	VenderDetail getDetailEntity(final String venderDivision,
			final String venderCd);

	/**
	 * 担当部署検索
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	OrganizationDetail getOrganizationEntity(final String organizationCd);
	
	/**
	 * メール送信元部署検索
	 * @param mailOrganizationCd メール送信元部署コード
	 * @return OrganizationDetail
	 */
	OrganizationDetail getMailOrganizationEntity(final String mailOrganizationCd);

	/**
	 * 営業担当者検索
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	LoginDetail getLoginEntity(final String tantoCd);

	/**
	 * 地区検索
	 * @param areaCd 地区コード
	 * @return AreaDetail
	 */
	AreaDetail getAreaEntity(final String areaCd);

	/**
	 * 会計部門検索
	 * @param sectionCd 会計部門コード
	 * @return BumonDetail
	 */
	BumonDetail getBumonEntity(final String sectionCd);

	/**
	 * 勘定科目検索
	 * @param accountsCd 勘定科目コード
	 * @return AccountsDetail
	 */
	AccountsDetail getAccountsEntity(final String accountsCd);

	/**
	 * カレンダー検索
	 * @param calendarCd カレンダーコード
	 * @return CalDetail
	 */
	CalDetail getCalEntity(final String calendarCd);

	/**
	 * 銀行検索
	 * @param bankMasterCd 銀行マスターコード
	 * @return BankDetail
	 */
	BankDetail getBankEntity(final String bankMasterCd);

	/**
	 * 自社検索
	 * @param companyCd 自社コード
	 * @return CompanyDetail
	 */
	CompanyDetail getCompanyEntity(final String companyCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	void update(final Vender bean) throws NoDataException,
			IllegalAccessException, InvocationTargetException;

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	void insert(final Vender bean) throws NoDataException,
			IllegalAccessException, InvocationTargetException;

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	void delete(final Vender bean) throws NoDataException,
			IllegalAccessException, InvocationTargetException;

	/**
	 * 銀行取得
	 * @return List<CompanyBankListForComboboxes>
	 */
	List<CompanyBankListForComboboxes> getBankList();

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @param arDivision 売掛対象区分
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType, final BigDecimal arDivision);

	/**
	 * 手形区分検索
	 * @param dataType データ種別
	 * @param categoryDivision 区分
	 * @return VenderClass
	 */
	VenderClassDetail getVenderClassEntity(final BigDecimal dataType,
			final String categoryDivision);

	/**
	 * 売上インボイスパターンリスト取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getTsInvoicePtnList();

	/**
	 * 仕入インボイスパターンリスト取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getSiInvoicePtnList();
}
