/*
 * 品目マスタ検索(ポップアップ)一覧用SQL
 *
 * entityName=ItemSearch
 * packageName=item
 * methodName=getSearchList
 *
 */

SELECT	ITEM.ITEM_CD						-- 品目コード
,		ITEM.VERSION						-- バージョン
,		ITEM.ITEM_NAME					-- 品目名称
,		ITEM.OTHER_COMPANY_CD1			-- 他社コード1
,		ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV	-- 運用管理単位
,		NVL(NAMES.NAME01, '') AS ITEM_UNIT		-- 単位
FROM	ITEM
,		(SELECT NAME_CD
		 ,		NAME01
		 FROM NAMES
		 WHERE	NAME_DIVISION = 'UNIT'
		) NAMES
WHERE	ITEM.ITEM_CD IS NOT NULL

/*IF (condition.itemCd != null) && (condition.itemCd != "")*/
AND (ITEM.ITEM_CD LIKE /*condition.itemCd*/'%' OR ITEM.ITEM_NAME LIKE /*condition.itemCd*/'%')
/*END*/

/*IF (condition.otherCompanyCd1 != null) && (condition.otherCompanyCd1 != "")*/
AND ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/'%'
/*END*/

AND 	ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
ORDER BY ITEM.ITEM_CD
