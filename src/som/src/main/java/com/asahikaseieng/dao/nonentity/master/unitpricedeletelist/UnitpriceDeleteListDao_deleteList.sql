/*
 * 仕入先別単価マスタ一括削除SQL
 * entityName=UnitpriceDeleteList
 * packageName=unitpricedeletelist
 * methodName=deleteList
 *
 */

DELETE
FROM UNITPRICE
WHERE UNITPRICE.VENDER_DIVISION = /*venderDivision*/'SI'
AND	UNITPRICE.VENDER_CD = /*venderCd*/'%'
AND UNITPRICE.ITEM_CD = /*itemCd*/'%'
AND UNITPRICE.VERSION = /*version*/1
