/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/

/**
 * 前後設備紐付けマスタ用SQL
 *
 * @author tosco
 *
 * entityName=DirectionRecipePegResouceList
 * packageName=.direction
 * methodName=getResourceCd
 *
 */
SELECT DISTINCT
	 PEG.RESOUCE_CD AS   RESOUCE_CD
	,RES.RESOUCE_NAME AS RESOUCE_NAME
FROM
    RECIPE_RESOUCE RES,
	RECIPE_PEG_RESOUCE PEG,
	RECIPE_RESOUCE_GROUP GRP
WHERE
		PEG.PREV_RESOUCE_CD = /*compoundTankNo*/'T-102'
	AND PEG.RESOUCE_CD = /*holdTankNo*/'T-112'
	AND RES.RESOUCE_CD = PEG.RESOUCE_CD
	AND RES.RESOUCE_GROUP_CD = GRP.RESOUCE_GROUP_CD
    AND GRP.OPERATION_GROUP_CD = '20' --ホールドタンク
ORDER BY
    PEG.RESOUCE_CD
