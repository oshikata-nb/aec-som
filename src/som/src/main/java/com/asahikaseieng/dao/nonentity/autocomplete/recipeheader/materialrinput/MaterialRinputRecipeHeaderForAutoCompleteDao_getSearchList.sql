/*
 * 外注原材料投入実績入力－基本処方AutoComplete一覧検索用SQL
 *
 * entityName=PkgDirectionRecipeHeaderForAutoComplete
 * packageName=recipeheader
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT RCPH.RECIPE_ID
			  ,RCPH.RECIPE_CD
			  ,RCPH.RECIPE_VERSION
			  ,RCPH.RECIPE_CD_VERSION
			  ,RCPH.ITEM_CD
			  ,RCPH.RECIPE_NAME
		FROM   (SELECT RECIPE_HEADER.RECIPE_ID
					  ,RECIPE_HEADER.RECIPE_CD
					  ,RECIPE_HEADER.RECIPE_VERSION
					  ,RECIPE_HEADER.RECIPE_CD ||
					   DECODE(RECIPE_HEADER.RECIPE_VERSION
							 ,NULL
							 ,''
							 ,',' || TO_CHAR(RECIPE_HEADER.RECIPE_VERSION)) RECIPE_CD_VERSION
					  ,RECIPE_HEADER.PRODUCTION_LINE
					  ,RECIPE_HEADER.ITEM_CD
					  ,RECIPE_HEADER.RECIPE_NAME
				FROM   RECIPE_HEADER
				WHERE  RECIPE_HEADER.RECIPE_TYPE = 3 -- 3:Master
				AND    RECIPE_HEADER.APPROVAL_STATUS = 3 -- 3:承認済み
					  
/*IF (itemCd != null) && (itemCd != "")*/
				AND    RECIPE_HEADER.ITEM_CD = /*itemCd*/'%'
/*END*/
				
				) RCPH
		WHERE  (RCPH.RECIPE_CD_VERSION LIKE /*recipeCdVersion*/1 OR RCPH.RECIPE_NAME LIKE /*recipeCdVersion*/1)
		ORDER  BY RCPH.RECIPE_CD, RCPH.RECIPE_VERSION)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
