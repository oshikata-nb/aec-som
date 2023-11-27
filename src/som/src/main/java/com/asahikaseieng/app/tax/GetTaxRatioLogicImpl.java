/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.master.company.CompanyDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueueDao;
import com.asahikaseieng.dao.nonentity.master.taxmasterlist.TaxMasterListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.FncGetTaxCdNewCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.FncGetTaxRatioCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 有効単価取得クラス ロジック実装クラス
 * @author t1344224
 */
public class GetTaxRatioLogicImpl implements GetTaxRatioLogic {

	private ProcedureCallDao procedureCallDao; // プロシージャコール用dao

	// private ErrorLogDao errorLogDao; // エラーログ出力用

	private CompanyDao companyDao;

	/** 自社マスタコード */
	private static final String COMPANY_CD = "000001";

	private ItemDao itemDao;

	private ArticleAttributeQueueDao articleAttributeQueueDao;

	private PurchaseAttributeQueueDao purchaseAttributeQueueDao;

	private TaxMasterListDao taxMasterListDao;

	/** 売上区分 */
	protected static final String ARTICLE = "0";

	/** 標準の売上区分 */
	protected static final String DEFAULT_DIVISION = "1";

	/**
	 * コンストラクタ
	 */
	public GetTaxRatioLogicImpl() {
	}

	/**
	 * 消費税区分を取得する
	 * @param itemCd 品目コード
	 * @param division 売上、仕入区分
	 * @return 消費税区分
	 */
	public String getTaxDivisionFromItem(final String itemCd,
			final String division) {

		Item item = itemDao.getEntity(itemCd);

		if (item != null) {

			if (division.equals(ARTICLE)) {
				ArticleAttributeQueue article = articleAttributeQueueDao
						.getEntity(item.getItemCd(), item.getVersion());

				if (article != null) {
					return article.getTaxDivision().toString();
				} else {
					return "0";
				}

			} else {
				PurchaseAttributeQueue purchase = purchaseAttributeQueueDao
						.getEntity(item.getItemCd(), item.getVersion());
				if (purchase != null) {
					return purchase.getTaxDivision().toString();
				} else {
					return "0";
				}

			}
		}
		return DEFAULT_DIVISION;
	}


	/**
	 * 帳合コードと品目コードから有効な単価を取得
	 * @param date 日付
	 * @return VaridUnitprice 有効単価
	 */
/*	public String getTaxRatio(final String date) {

		FncGetTaxRatioCallDto dto = new FncGetTaxRatioCallDto();
		dto.setPStrTargetDate(date);

		// 出荷指図自動作成プロシージャ実行
		procedureCallDao.funGetTaxRatio(dto);

		return dto.getPStrTaxRatio();
	}*/

	/**
	 * 帳合コードと品目コードから有効な単価を取得
	 * @param date 日付
	 * @return VaridUnitprice 有効単価
	 */
	public String getTaxRatio(final String taxcd) {

		FncGetTaxRatioCallDto dto = new FncGetTaxRatioCallDto();
		dto.setpStrTaxCd(taxcd);
		dto.setpStrParam1(null);
		dto.setpStrParam2(null);
		dto.setpStrParam3(null);
		dto.setpStrParam4(null);

		// 出荷指図自動作成プロシージャ実行
		procedureCallDao.funGetTaxRatio(dto);

		return dto.getPStrTaxRatio();
	}

	/**
	 * 消費税用コンボボックスを作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createTaxRatioCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 自社マスタ検索
		Company company = companyDao.getEntity(COMPANY_CD);

		ComboBoxItems taxRatio = new ComboBoxItems();

		if (company.getTaxRatio() != null) {
			taxRatio.setValues(company.getTaxRatio().toString());
			taxRatio.setLabales(company.getTaxRatio().toString());
			res.add(taxRatio);
		}
		ComboBoxItems newTaxRatio = new ComboBoxItems();

		if (company.getNewTaxRatio() != null) {
			newTaxRatio.setValues(company.getNewTaxRatio().toString());
			newTaxRatio.setLabales(company.getNewTaxRatio().toString());
			res.add(newTaxRatio);
		}
		return res;
	}

	/**
	 * 税コードを取得
	 * 軽減税率対応　20190823
	 * @param taxCd 税コード
	 * @return TaCd 税コード
	 */
	public String getTaxCd(final String date,final String itemCd,final String venderDivision
			,final String venderCd,final String category,final String taxRatio, final String taxFreeFlg) {

		FncGetTaxCdNewCallDto dto = new FncGetTaxCdNewCallDto();
		dto.setpParamDate(date);
		dto.setpParamItemCd(itemCd);
		dto.setpParamVenderDivision(venderDivision);
		dto.setpParamVenderCd(venderCd);
		dto.setpParamCategory(category);
		dto.setpParamTaxRatio(taxRatio);
		dto.setpParam1(null);
		dto.setpParam2(null);
		dto.setpParam3(null);
		dto.setpParamTaxFreeFlg(taxFreeFlg);

		// 出荷指図自動作成プロシージャ実行
		procedureCallDao.funGetTaxCdNew(dto);

		return dto.getpTaxCd();
	}

	/**
	 * 税コードから消費税率を取得
	 * 軽減税率対応　20190823
	 * @param taxCd 税コード
	 * @return TaxRatio　消費税率
	 */
	public BigDecimal getTaxRatioFromTaxCd(final String taxCd) {

		BigDecimal taxRatio = taxMasterListDao.getTaxRatio(taxCd);

		return taxRatio;
	}

	/**
	 * 日付と消費税率から有効な消費税率であるか確認する
	 * @param date date
	 * @param taxRatio taxRatio
	 * @return List<ComboBoxItems>
	 */
	public boolean isValidTax(final String date, final String taxRatio) {
		Company company = companyDao.getEntity(COMPANY_CD);

		String useDate = AecDateUtils.dateFormat(company.getNewTaxApllyDate(),
			"yyyy/MM/dd");

		if (date.compareTo(useDate) < 0) {
			if (!taxRatio.equals(company.getTaxRatio().toString())) {
				return false;
			}
		}
		return true;

	}

	// /**
	// * エラーログ出力用daoを設定する
	// * @param errorLogDao エラーログ出力用dao
	// */
	// public void setErrorLogDao(final ErrorLogDao errorLogDao) {
	// this.errorLogDao = errorLogDao;
	// }

	/**
	 * プロシージャコール用daoを設定する
	 * @param procedureCallDao プロシージャコール用dao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * companyDaoを設定します。
	 * @param companyDao companyDao
	 */
	public void setCompanyDao(final CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * articleAttributeQueueDaoを設定します。
	 * @param articleAttributeQueueDao articleAttributeQueueDao
	 */
	public void setArticleAttributeQueueDao(
			final ArticleAttributeQueueDao articleAttributeQueueDao) {
		this.articleAttributeQueueDao = articleAttributeQueueDao;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * purchaseAttributeQueueDaoを設定します。
	 * @param purchaseAttributeQueueDao purchaseAttributeQueueDao
	 */
	public void setPurchaseAttributeQueueDao(
			final PurchaseAttributeQueueDao purchaseAttributeQueueDao) {
		this.purchaseAttributeQueueDao = purchaseAttributeQueueDao;
	}

	/**
	 * taxMasterListDaoを設定します。
	 * @param taxMasterListDao taxMasterListDao
	 */
	public void setTaxMasterListDao(TaxMasterListDao taxMasterListDao) {
		this.taxMasterListDao = taxMasterListDao;
	}
}
