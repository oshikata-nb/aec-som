/*
 * 分類マスタ一覧用SQL
 *
 * entityName=ClassificationList
 * packageName=master.classification
 * methodName=getSearchList
 *
 */
SELECT	CIF.DATA_TYPE 	        	--データ種別
, 		CIF.DATA_TOTAL_DIVISION 	--データ集計区分
, 		CIF.CATEGORY_DIVISION 		--分類コード
, 		CIF.CATEGORY_NAME 			--分類名称

FROM 	CLASSIFICATION CIF
WHERE 	CIF.DATA_TYPE IS NOT NULL

/*IF (( condition.srhDataType != null ) && ( condition.srhDataType != "" ))*/
	AND	CIF.DATA_TYPE = /*condition.srhDataType*/
/*END*/

/*IF (( condition.srhDataTotalDivision != null ) && ( condition.srhDataTotalDivision != "" ))*/
	AND	CIF.DATA_TOTAL_DIVISION = /*condition.srhDataTotalDivision*/
/*END*/

/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "" ))*/
	AND	CIF.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/
/*END*/

/*IF (( condition.srhCategoryName != null ) && ( condition.srhCategoryName != "" ))*/
	AND	CIF.CATEGORY_NAME LIKE /*condition.srhCategoryName*/
/*END*/

ORDER BY CIF.DATA_TYPE
,		 CIF.DATA_TOTAL_DIVISION
,		 CIF.CATEGORY_DIVISION

