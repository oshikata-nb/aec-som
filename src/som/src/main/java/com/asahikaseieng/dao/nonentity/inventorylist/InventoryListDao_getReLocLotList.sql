/*
 * 在庫照会一覧用SQL
 *
 * entityName=InventoryList
 * packageName=inventorylist
 * methodName=getLocLotList
 *
 */

/*IF (condition.srhAvailableFlg == "1")*/
SELECT LOT_INVENTORY.LOCATION_CD, LOCATION_NAME, SUBSTR(LOCATION_NAME, 0, 8) SHORT_LOCATION_NAME, LOT_INVENTORY.ITEM_CD, ITEM_NAME, SUBSTR(ITEM_NAME, 0, 8) SHORT_ITEM_NAME, LOT_NO, STYLE_OF_PACKING,

DECODE(UNIT_OF_OPERATION_MANAGEMENT, '1', DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)),
DECODE(UNIT_OF_OPERATION_MANAGEMENT, '5', DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)),
CASE TYPE_DIVISION
    WHEN 1 THEN FLOOR(DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    WHEN 3 THEN FLOOR(DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    ELSE NVL(INVENTORY_QTY, 0)
END)) PACK_QTY,

UNIT_OF_OPERATION_MANAGEMENT, OPERATION_NAMES.NAME01 PACK_UNIT,

DECODE(NVL(KG_CONVERSION_COEFFICIENT, 1), 0, 0, DECODE(UNIT_OF_OPERATION_MANAGEMENT, '1', 0, DECODE(UNIT_OF_OPERATION_MANAGEMENT, '5', 0,
CASE TYPE_DIVISION
    WHEN 1 THEN DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, MOD(NVL(INVENTORY_QTY, 0), NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    WHEN 3 THEN DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, MOD(NVL(INVENTORY_QTY, 0), NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    ELSE 0
END)) / NVL(KG_CONVERSION_COEFFICIENT, 1)) FRACTION,

UNIT_OF_FRACTION_MANAGEMENT, FRACTION_NAMES.NAME01 FRACTION_UNIT,

CASE TYPE_DIVISION
    WHEN 1 THEN NVL(INVENTORY_QTY, 0)
    WHEN 3 THEN NVL(INVENTORY_QTY, 0)
    ELSE NVL(INVENTORY_QTY, 0) * NVL(KG_OF_FRACTION_MANAGEMENT, 1)
END INVENTORY_QTY

FROM LOT_INVENTORY, ITEM, LOCATION, NAMES OPERATION_NAMES, NAMES FRACTION_NAMES,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE INVENTORY_QTY <> 0

/*IF (condition.srhLocationCd != null) && (condition.srhLocationCd != "")*/
AND (LOT_INVENTORY.LOCATION_CD = /*condition.srhLocationCd*/'%' OR LOCATION_NAME = /*condition.srhLocationCd*/'%')
/*END*/

/*IF (condition.srhLotNo != null) && (condition.srhLotNo != "")*/
AND LOT_NO LIKE /*condition.srhLotNo*/'%'
/*END*/

AND LOT_INVENTORY.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND LOT_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND OPERATION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_OPERATION_MANAGEMENT = OPERATION_NAMES.NAME_CD(+)
AND FRACTION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_FRACTION_MANAGEMENT = FRACTION_NAMES.NAME_CD(+)
/*END*/

/*IF (condition.srhAvailableFlg != "1")*/
SELECT LOT_INVENTORY.LOCATION_CD, LOCATION_NAME, SUBSTR(LOCATION_NAME, 0, 8) SHORT_LOCATION_NAME, LOT_INVENTORY.ITEM_CD, ITEM_NAME, SUBSTR(ITEM_NAME, 0, 8) SHORT_ITEM_NAME, LOT_NO, STYLE_OF_PACKING,

DECODE(UNIT_OF_OPERATION_MANAGEMENT, '1', DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)),
DECODE(UNIT_OF_OPERATION_MANAGEMENT, '5', DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)),
CASE TYPE_DIVISION
    WHEN 1 THEN FLOOR(DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    WHEN 3 THEN FLOOR(DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, NVL(INVENTORY_QTY, 0) / NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    ELSE NVL(INVENTORY_QTY, 0)
END)) PACK_QTY,

UNIT_OF_OPERATION_MANAGEMENT, OPERATION_NAMES.NAME01 PACK_UNIT,

DECODE(NVL(KG_CONVERSION_COEFFICIENT, 1), 0, 0, DECODE(UNIT_OF_OPERATION_MANAGEMENT, '1', 0, DECODE(UNIT_OF_OPERATION_MANAGEMENT, '5', 0,
CASE TYPE_DIVISION
    WHEN 1 THEN DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, MOD(NVL(INVENTORY_QTY, 0), NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    WHEN 3 THEN DECODE(NVL(KG_OF_FRACTION_MANAGEMENT, 1), 0, 0, MOD(NVL(INVENTORY_QTY, 0), NVL(KG_OF_FRACTION_MANAGEMENT, 1)))
    ELSE 0
END)) / NVL(KG_CONVERSION_COEFFICIENT, 1)) FRACTION,

UNIT_OF_FRACTION_MANAGEMENT, FRACTION_NAMES.NAME01 FRACTION_UNIT,

CASE TYPE_DIVISION
    WHEN 1 THEN NVL(INVENTORY_QTY, 0)
    WHEN 3 THEN NVL(INVENTORY_QTY, 0)
    ELSE NVL(INVENTORY_QTY, 0) * NVL(KG_OF_FRACTION_MANAGEMENT, 1)
END INVENTORY_QTY

FROM LOT_INVENTORY, ITEM, LOCATION, NAMES OPERATION_NAMES, NAMES FRACTION_NAMES,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE INVENTORY_QTY <> 0

/*IF (condition.srhLocationCd != null) && (condition.srhLocationCd != "")*/
AND LOT_INVENTORY.LOCATION_CD IN(SELECT LOCATION_CD FROM LOCATION WHERE UPPER_LOCATION_CD = /*condition.srhLocationCd*/'%')
/*END*/

/*IF (condition.srhLotNo != null) && (condition.srhLotNo != "")*/
AND LOT_NO LIKE /*condition.srhLotNo*/'%'
/*END*/

AND LOT_INVENTORY.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND LOT_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND OPERATION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_OPERATION_MANAGEMENT = OPERATION_NAMES.NAME_CD(+)
AND FRACTION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_FRACTION_MANAGEMENT = FRACTION_NAMES.NAME_CD(+)
/*END*/

ORDER BY LOT_NO, LOCATION_CD, ITEM_CD
