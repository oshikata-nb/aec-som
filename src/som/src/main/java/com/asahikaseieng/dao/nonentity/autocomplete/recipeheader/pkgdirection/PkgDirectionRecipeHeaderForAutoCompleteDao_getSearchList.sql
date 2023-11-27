/*
 * 包装指図－基本処方AutoComplete一覧検索用SQL
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
			  ,RCPH.PRODUCTION_LINE
			  ,RCPH.ITEM_CD
			  ,RCPH.RECIPE_NAME
			  ,ITEM.ITEM_NAME
			  ,ITEM.OTHER_COMPANY_CD1
			  ,ITEM.STYLE_OF_PACKING
			  ,NAME.NAME01
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
				AND    RECIPE_HEADER.RECIPE_STATUS IN (2, 3) -- 2:試作用　3:一般使用
				AND    RECIPE_HEADER.RECIPE_USE = 4 -- 4:包装
				AND    RECIPE_HEADER.APPROVAL_STATUS = 3 -- 3:承認済み
					  
/*IF (itemCd != null) && (itemCd != "")*/
				AND    RECIPE_HEADER.ITEM_CD = /*itemCd*/'%'
/*END*/
					  
/*IF (line != null) && (line != "")*/
				AND    RECIPE_HEADER.PRODUCTION_LINE = /*line*/'%'
/*END*/
				
				) RCPH
			  ,ITEM
			  ,NAMES NAME
		WHERE  RCPH.ITEM_CD = ITEM.ITEM_CD
		AND    (RCPH.RECIPE_CD_VERSION LIKE /*recipeCdVersion*/'%' OR RCPH.RECIPE_NAME LIKE /*recipeCdVersion*/'%')
			  
/*IF (otherCompanyCd1 != null) && (otherCompanyCd1 != "")*/
		AND    ITEM.OTHER_COMPANY_CD1 = /*otherCompanyCd1*/'%'
/*END*/
		AND    ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAME.NAME_CD(+)
		AND    NAME.NAME_DIVISION(+) = 'UNIT'
		
		ORDER  BY RCPH.RECIPE_CD, RCPH.RECIPE_VERSION)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
