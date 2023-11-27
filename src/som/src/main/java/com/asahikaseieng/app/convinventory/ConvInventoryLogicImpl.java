/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.convinventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.asahikaseieng.dao.nonentity.convinventory.ConvInventoryItemDetail;
import com.asahikaseieng.dao.nonentity.convinventory.ConvInventoryItemDetailDao;

/**
 * 在庫数量換算
 * @author t0011036
 */
public class ConvInventoryLogicImpl implements ConvInventoryLogic, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private ConvInventoryItemDetailDao convInventoryItemDetailDao;

	private static final BigDecimal CONV_OK = new BigDecimal("0");

	private static final BigDecimal NODATA_ITEM = new BigDecimal("1");

	private static final BigDecimal NOT_FRACTION = new BigDecimal("2");

	private static final String UNIT_KG = "1";

	private static final String UNIT_L = "5";

	/**
	 * コンストラクタ
	 */
	public ConvInventoryLogicImpl() {
	}

	/**
	 * 在庫数量を荷姿数と端数に換算する
	 * @param itemCd 品目コード
	 * @param inventoryQty 在庫数量
	 * @return convInventoryResult convInventoryResult
	 */
	public ConvInventoryResult inventoryToPack(final String itemCd,
			final BigDecimal inventoryQty) {
		ConvInventoryResult result = new ConvInventoryResult(CONV_OK);

		/* 品目検索 */
		ConvInventoryItemDetail bean = convInventoryItemDetailDao
				.getEntity(itemCd);

		/* 品目未登録 */
		if (bean == null) {
			result.setCode(NODATA_ITEM);
			return result;
		}

		BigDecimal iQty = new BigDecimal("0");
		BigDecimal packQty = new BigDecimal("0");
		BigDecimal fractionWeight = new BigDecimal("0");
		BigDecimal fractionQty = new BigDecimal("0");
		BigDecimal fractionManagement = new BigDecimal("1");
		BigDecimal conversionCoefficient = new BigDecimal("1");

		/* 在庫数量セット */
		if (inventoryQty != null) {
			iQty = inventoryQty;
		}

		/* Kg換算係数(在庫)セット */
		if (bean.getKgOfFractionManagement() != null) {
			fractionManagement = bean.getKgOfFractionManagement();
		}

		/* Kg換算係数(端数)セット */
		if (bean.getKgConversionCoefficient() != null) {
			conversionCoefficient = bean.getKgConversionCoefficient();
		}

		if (bean.getTypeDivision().equals(new BigDecimal("1"))
				|| bean.getTypeDivision().equals(new BigDecimal("3"))) {
			/* 品目が原料 or 中間品の場合 */
			if (bean.getUnitOfOperationManagement().equals(UNIT_KG)
					|| bean.getUnitOfOperationManagement().equals(UNIT_L)) {
				/* 運用管理単位が Kg or L の場合は端数計算しない */
				if (fractionManagement == null
						|| fractionManagement.equals(BigDecimal.ZERO)) {
					/* Kg換算係数(在庫)が 0 の場合(0割対策) 2009/05/20 */
					packQty = iQty; /* 荷姿数 = 重量 */
				} else {
					packQty = iQty.divide(fractionManagement, 10,
						BigDecimal.ROUND_HALF_UP); /* 荷姿数 */
				}
			} else {
				if (fractionManagement == null
						|| fractionManagement.equals(BigDecimal.ZERO)) {
					/* Kg換算係数(在庫)が 0 の場合(0割対策) 2009/05/20 */
					packQty = iQty; /* 荷姿数 = 重量 */
				} else {
					packQty = iQty.divide(fractionManagement,
						BigDecimal.ROUND_DOWN).setScale(0, RoundingMode.DOWN); /* 荷姿数 */
					fractionWeight = iQty.remainder(fractionManagement); /* 端数重量 */
				}

				/* Kg換算係数(端数)が 0 の場合端数(数量) = 端数(重量)とする(0割対策) 2009/05/20 */
				if (conversionCoefficient == null
						|| conversionCoefficient.equals(BigDecimal.ZERO)) {
					fractionQty = fractionWeight;
				} else {
					// fractionQty =
					// fractionWeight.divide(conversionCoefficient)
					// .setScale(1, RoundingMode.DOWN); /* 端数数量 */
					fractionQty = fractionWeight.divide(conversionCoefficient,
						10, BigDecimal.ROUND_HALF_UP); /* 端数数量 */
				}
			}

			result.setInventoryQty(iQty); /* 重量 */
			result.setPackQty(packQty); /* 荷姿数 */
			result.setFractionWeight(fractionWeight); /* 端数重量 */
			result.setFractionQty(fractionQty); /* 端数数量 */
		} else {
			packQty = iQty.multiply(fractionManagement);

			result.setInventoryQty(packQty); /* 重量 */
			result.setPackQty(iQty); /* 荷姿数 */
			result.setFractionWeight(fractionWeight); /* 端数重量 */
			result.setFractionQty(fractionQty); /* 端数数量 */
		}

		result.setTypeDivision(bean.getTypeDivision());
		result.setCode(CONV_OK);

		return result;
	}

	/**
	 * 荷姿数と端数を在庫数量に換算する
	 * @param itemCd 品目コード
	 * @param packQty 荷姿数
	 * @param fractionQty 端数数量
	 * @return convInventoryResult convInventoryResult
	 */
	public ConvInventoryResult packToInventory(final String itemCd,
			final BigDecimal packQty, final BigDecimal fractionQty) {
		ConvInventoryResult result = new ConvInventoryResult(CONV_OK);

		/* 品目検索 */
		ConvInventoryItemDetail bean = convInventoryItemDetailDao
				.getEntity(itemCd);

		/* 品目未登録 */
		if (bean == null) {
			result.setCode(NODATA_ITEM);
			return result;
		}

		BigDecimal inventoryQty = new BigDecimal("0");

		BigDecimal pQty = new BigDecimal("0");
		BigDecimal wQty = new BigDecimal("0");
		BigDecimal fQty = new BigDecimal("0");
		BigDecimal fractionManagement = new BigDecimal("1");
		BigDecimal conversionCoefficient = new BigDecimal("1");

		/* 在庫数量セット */
		if (packQty != null) {
			pQty = packQty;
		}

		/* Kg換算係数(在庫)セット */
		if (bean.getKgOfFractionManagement() != null) {
			fractionManagement = bean.getKgOfFractionManagement();
		}

		/* Kg換算係数(端数)セット */
		if (bean.getKgConversionCoefficient() != null) {
			conversionCoefficient = bean.getKgConversionCoefficient();
		}

		// Upd 2022.05.19 T.Sato 在庫数量の換算について見直し  Start
		
		if (bean.getTypeDivision().equals(new BigDecimal("1"))
				|| bean.getTypeDivision().equals(new BigDecimal("3"))) {
			/* 品目が原料 or 中間品の場合 */
			if (bean.getUnitOfOperationManagement().equals(UNIT_KG)
					|| bean.getUnitOfOperationManagement().equals(UNIT_L)
					|| bean.getUnitOfFractionManagement().equals(UNIT_KG)
					|| bean.getUnitOfFractionManagement().equals(UNIT_L)) {
				/* 運用管理単位または、端数管理単位が Kg or L の場合は端数計算しない */
				if (fractionQty != null) {
					fQty = fractionQty;
					wQty = fQty;
				}
			}
			else
			{
				/* それ以外の場合は端数計算する */
				if (fractionQty != null) {
					fQty = fractionQty;
					wQty = fQty.multiply(conversionCoefficient);
				}
			}
			if (bean.getUnitOfOperationManagement().equals(UNIT_KG)
					|| bean.getUnitOfOperationManagement().equals(UNIT_L)) {
				/* 運用管理単位が Kg or L の場合は端数加算しない */
				inventoryQty = pQty.multiply(fractionManagement);
			}
			else {
				/* それ以外の場合は端数加算する */
				inventoryQty = pQty.multiply(fractionManagement).add(wQty);
			}

		} else {
			/* 品目が原料 or 中間品でない場合 在庫数量を設定 */
			if (fractionQty != null) {
				fQty = fractionQty;
				wQty = fQty;
			}

			inventoryQty = pQty;
		}
		
		result.setInventoryQty(inventoryQty); /* 重量 */
		result.setPackQty(pQty); /* 荷姿数 */
		result.setFractionWeight(wQty); /* 端数重量 */
		result.setFractionQty(fQty); /* 端数数量 */
		
		// Upd 2022.05.19 T.Sato 在庫数量の換算について見直し  End
		
		result.setTypeDivision(bean.getTypeDivision());
		result.setCode(CONV_OK);

		return result;
	}

	/**
	 * 荷姿数と端数を在庫数量に換算する
	 * @param itemCd 品目コード
	 * @param packQty 荷姿数
	 * @param fractionQty 端数数量
	 * @return convInventoryResult convInventoryResult
	 */
	public ConvInventoryResult packToWeight(final String itemCd,
			final BigDecimal packQty, final BigDecimal fractionQty) {
		ConvInventoryResult result = new ConvInventoryResult(CONV_OK);

		/* 品目検索 */
		ConvInventoryItemDetail bean = convInventoryItemDetailDao
				.getEntity(itemCd);

		/* 品目未登録 */
		if (bean == null) {
			result.setCode(NODATA_ITEM);
			return result;
		}

		BigDecimal inventoryQty = new BigDecimal("0");

		BigDecimal pQty = new BigDecimal("0");
		BigDecimal wQty = new BigDecimal("0");
		BigDecimal fQty = new BigDecimal("0");
		BigDecimal fractionManagement = new BigDecimal("1");
		BigDecimal conversionCoefficient = new BigDecimal("1");

		/* 在庫数量セット */
		if (packQty != null) {
			pQty = packQty;
		}

		/* Kg換算係数(在庫)セット */
		if (bean.getKgOfFractionManagement() != null) {
			fractionManagement = bean.getKgOfFractionManagement();
		}

		/* Kg換算係数(端数)セット */
		if (bean.getKgConversionCoefficient() != null) {
			conversionCoefficient = bean.getKgConversionCoefficient();
		}

		/* 品目が原料 or 中間品の場合 */
		if (bean.getUnitOfOperationManagement().equals(UNIT_KG)
				|| bean.getUnitOfOperationManagement().equals(UNIT_L)) {
			/* 運用管理単位が Kg or L の場合は端数計算しない */
			if (fractionQty != null) {
				fQty = fractionQty;
				wQty = fQty;
			}

			inventoryQty = pQty.multiply(fractionManagement);
		} else {
			if (fractionQty != null) {
				fQty = fractionQty;
				wQty = fQty.multiply(conversionCoefficient);
			}

			inventoryQty = pQty.multiply(fractionManagement).add(wQty);
		}

		result.setInventoryQty(inventoryQty); /* 重量 */
		result.setPackQty(pQty); /* 荷姿数 */
		result.setFractionWeight(wQty); /* 端数重量 */
		result.setFractionQty(fQty); /* 端数数量 */
		result.setTypeDivision(bean.getTypeDivision());
		result.setCode(CONV_OK);

		return result;
	}

	/**
	 * 端数入力チェック
	 * @param itemCd 品目コード
	 * @param fractionQty 端数数量
	 * @return convInventoryResult convInventoryResult
	 */
	public ConvInventoryResult checkInputFraction(final String itemCd,
			final BigDecimal fractionQty) {
		ConvInventoryResult result = new ConvInventoryResult(CONV_OK);

		/* 品目検索 */
		ConvInventoryItemDetail bean = convInventoryItemDetailDao
				.getEntity(itemCd);

		/* 品目未登録 */
		if (bean == null) {
			result.setCode(NODATA_ITEM);
			return result;
		}

		if (bean.getTypeDivision().equals(new BigDecimal("1"))
				|| bean.getTypeDivision().equals(new BigDecimal("3"))) {
			/* 品目が原料 or 中間品の場合 */
			if (bean.getUnitOfOperationManagement().equals(UNIT_KG)
					|| bean.getUnitOfOperationManagement().equals(UNIT_L)) {
				/* 運用管理単位が Kg or L の場合は端数計算しない */
				if (fractionQty != null) {
					if (fractionQty.compareTo(new BigDecimal("0")) != 0) {
						result.setCode(NOT_FRACTION);
					}
				}
			}
		} else {
			if (fractionQty != null) {
				if (fractionQty.compareTo(new BigDecimal("0")) != 0) {
					result.setCode(NOT_FRACTION);
				}
			}
		}

		return result;
	}

	// setter------------------------------------------------------------------

	/**
	 * convInventoryItemDetailDaoを設定します。
	 * @param convInventoryItemDetailDao convInventoryItemDetailDao
	 */
	public void setConvInventoryItemDetailDao(
			final ConvInventoryItemDetailDao convInventoryItemDetailDao) {
		this.convInventoryItemDetailDao = convInventoryItemDetailDao;
	}
}
