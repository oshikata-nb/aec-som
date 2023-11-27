/*
 * 工程一覧用SQL
 *
 * entityName=OperationListForAutoComplete
 * packageName=operation
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT OPERATION_CD, OPERATION_NAME
		FROM   OPERATION
		WHERE  (OPERATION_CD LIKE /*operationCd*/'%' OR OPERATION_NAME LIKE /*operationCd*/'%')
			  /*IF ((recipeUse != null) && (recipeUse != ""))*/
		AND    RECIPE_USE = /*recipeUse*/1
		/*END*/
		
		ORDER  BY OPERATION_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
