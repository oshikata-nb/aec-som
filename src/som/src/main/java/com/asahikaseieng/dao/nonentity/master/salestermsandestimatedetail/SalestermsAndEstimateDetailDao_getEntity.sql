
/*
 * 販売条件詳細用SQL
 *
 * entityName=SalestermsAndEstimateDetail
 * packageName=salestermsandestimatedetail
 * methodName=getEntity
 *
 */

SELECT
    SALES_ESTI.PK_NO                        --PK_NO
    ,SALES_ESTI.PROCESS_DIVISION            --処理区分
	,SALES_ESTI.STATUS                      --ステータス
    ,STATUS.STATUS_NAME	                    --ステータス名称
    ,SALES_ESTI.ITEM_CD_FROM                --コピー元・削除品目コード
    ,SALES_ESTI.ITEM_CD_TO                  --コピー先品目コード
    ,ITEM_FROM.ITEM_NAME_FROM               --コピー元・削除品目名称 
    ,ITEM_TO.ITEM_NAME_TO                   --コピー先品目名称
    ,ITEM_FROM.STYLE_OF_PACKING_FROM        --コピー元・削除品目の荷姿
    ,ITEM_TO.STYLE_OF_PACKING_TO            --コピー先品目の荷姿
    ,ITEM_FROM.OTHER_COMPANY_CD1_FROM       --コピー元・削除品目の他社コード1
    ,ITEM_TO.OTHER_COMPANY_CD1_TO           --コピー先品目の他社コード1
    ,SALES_ESTI.INPUT_DATE					--入力日
	,SALES_ESTI.INPUTOR_CD					--入力者コード
	,LOGIN.TANTO_NM							--入力者名
	,SALES_ESTI.UPDATE_DATE					--更新日時
    
FROM
    SALESTERMS_AND_ESTIMATE_CTRL SALES_ESTI
    
LEFT JOIN 
    (SELECT
        ITEM.ITEM_CD
        ,ITEM.ITEM_NAME ITEM_NAME_FROM
        ,ITEM.OTHER_COMPANY_CD1 OTHER_COMPANY_CD1_FROM
        ,ITEM.STYLE_OF_PACKING STYLE_OF_PACKING_FROM
        
    FROM
        ITEM)ITEM_FROM ON SALES_ESTI.ITEM_CD_FROM = ITEM_FROM.ITEM_CD
            
LEFT JOIN 
    (SELECT
        ITEM.ITEM_CD
        ,ITEM.ITEM_NAME ITEM_NAME_TO
        ,ITEM.OTHER_COMPANY_CD1 OTHER_COMPANY_CD1_TO
        ,ITEM.STYLE_OF_PACKING STYLE_OF_PACKING_TO
        
    FROM
        ITEM)ITEM_TO ON SALES_ESTI.ITEM_CD_TO = ITEM_TO.ITEM_CD
                    
LEFT JOIN
    (SELECT
        NAME_CD
        ,NAME01 STATUS_NAME
    FROM
        NAMES
    WHERE
        NAMES.NAME_DIVISION = 'STAT') STATUS ON SALES_ESTI.STATUS = STATUS.NAME_CD
            
LEFT JOIN
	LOGIN ON SALES_ESTI.INPUTOR_CD =  LOGIN.TANTO_CD

WHERE SALES_ESTI.PK_NO = /*pkNo*/'TEST1'

