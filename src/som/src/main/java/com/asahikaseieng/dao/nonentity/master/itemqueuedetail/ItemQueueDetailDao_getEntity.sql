/*
 * 品目基本検索用SQL
 *
 * entityName=ItemQueueDetail
 * packageName=itemqueuedetail
 * methodName=getEntity
 *
 */

SELECT ITEM_QUEUE.ITEM_CD, ITEM_QUEUE.VERSION, ITEM_NAME, ITEM_SUB_NAME, RESEARCH_ITEM, ACTIVE_DATE, STATUS, SHIPPER_DIVISION, TYPE_DIVISION, SALES_TAX_CATEGORY, PURCHASE_TAX_CATEGORY, ITEM_DIVISION, OTHER_COMPANY_CD1, STYLE_OF_PACKING, NUMBER_OF_INSERTIONS, ALL_UP_WEIGHT, UNIT_OF_OPERATION_MANAGEMENT, UNIT_OF_STOCK_CONTROL, KG_OF_FRACTION_MANAGEMENT, UNIT_OF_FRACTION_MANAGEMENT, KG_CONVERSION_COEFFICIENT, ITEM_CATEGORY, PARENT_ITEM_CD, PARENT_ITEM_NAME, SPOT_DIVISION, ITEM_QUEUE.STOCK_DIVISION, DEFAULT_LOCATION, DEFAULT_LOCATION.LOCATION_NAME DEFAULT_LOCATION_NAME, ORDER_LOCATION, ORDER_LOCATION.LOCATION_NAME ORDER_LOCATION_NAME, LOT_DIVISION, WATER_DIVISION, ITEM_QUEUE.UPDATE_DATE
FROM

(SELECT ITEM_QUEUE.ITEM_CD, ITEM_QUEUE.VERSION, ITEM_NAME, ITEM_SUB_NAME, RESEARCH_ITEM, ACTIVE_DATE, STATUS, SHIPPER_DIVISION, TYPE_DIVISION, SALES_TAX_CATEGORY, PURCHASE_TAX_CATEGORY, ITEM_DIVISION, OTHER_COMPANY_CD1, STYLE_OF_PACKING, NUMBER_OF_INSERTIONS, ALL_UP_WEIGHT, UNIT_OF_OPERATION_MANAGEMENT, UNIT_OF_STOCK_CONTROL, KG_OF_FRACTION_MANAGEMENT, UNIT_OF_FRACTION_MANAGEMENT, KG_CONVERSION_COEFFICIENT, ITEM_CATEGORY, PARENT_ITEM_CD, SPOT_DIVISION, ITEM_QUEUE.STOCK_DIVISION, DEFAULT_LOCATION, ORDER_LOCATION, LOT_DIVISION, WATER_DIVISION, ITEM_QUEUE.UPDATE_DATE,

(SELECT ITEM_NAME
FROM VALID_ITEM_QUEUE PARENT_ITEM
WHERE PARENT_ITEM.ITEM_CD = ITEM_QUEUE.PARENT_ITEM_CD) PARENT_ITEM_NAME

FROM ITEM_QUEUE) ITEM_QUEUE, LOCATION DEFAULT_LOCATION, LOCATION ORDER_LOCATION

WHERE ITEM_QUEUE.ITEM_CD = /*itemCd*/'%'
AND ITEM_QUEUE.VERSION = /*version*/1
AND DEFAULT_LOCATION = DEFAULT_LOCATION.LOCATION_CD(+)
AND ORDER_LOCATION = ORDER_LOCATION.LOCATION_CD(+)
