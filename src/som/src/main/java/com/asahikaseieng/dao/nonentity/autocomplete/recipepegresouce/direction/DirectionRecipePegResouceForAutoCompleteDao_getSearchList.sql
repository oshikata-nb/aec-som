/**
 * ホールドタンクNOオートコンプリート用SQL
 *
 * @author tosco
 *
 * entityName=DirectionRecipePegResouceForAutoComplete
 * packageName=recipepegresouce.direction
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT DISTINCT PEG.RESOUCE_CD   AS RESOUCE_CD
					   ,RES.RESOUCE_NAME AS RESOUCE_NAME
		FROM   RECIPE_RESOUCE       RES
			  ,RECIPE_PEG_RESOUCE   PEG
			  ,RECIPE_RESOUCE_GROUP GRP
		WHERE  PEG.PREV_RESOUCE_CD = /*compoundTankNo*/'%'
		AND    PEG.RESOUCE_CD LIKE /*holdTankNo*/'%'
		AND    RES.RESOUCE_CD = PEG.RESOUCE_CD
		AND    RES.RESOUCE_GROUP_CD = GRP.RESOUCE_GROUP_CD
		AND    GRP.OPERATION_GROUP_CD = '20' --ホールドタンク
		ORDER  BY PEG.RESOUCE_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
