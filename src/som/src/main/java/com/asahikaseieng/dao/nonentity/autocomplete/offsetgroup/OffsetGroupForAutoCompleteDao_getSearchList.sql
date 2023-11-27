/*
 * 相殺グループ一覧用SQL
 *
 * entityName=OffsetGroupForAutoComplete
 * packageName=offsetgroup
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT DISTINCT OFFSET_GROUP_CD, OFFSET_GROUP_NAME
		FROM   OFFSET_GROUP
		WHERE  OFFSET_GROUP_CD IS NOT NULL
			  
/*IF (offsetGroupCd != null) && (offsetGroupCd != "")*/
		AND    (OFFSET_GROUP_CD LIKE /*offsetGroupCd*/'%' OR OFFSET_GROUP_NAME LIKE /*offsetGroupCd*/'%')
/*END*/
		
		ORDER  BY OFFSET_GROUP_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
