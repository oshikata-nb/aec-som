/*
 * 販売条件・見積単価　コピー・削除画面一覧用SQL
 *
 * entityName=SalesTermsAndEstimateList
 * packageName=salestermsandestimatelist
 * methodName=getList
 *
 */

SELECT
    SALES_ESTI.PK_NO                        --PK_NO
    ,SALES_ESTI.PROCESS_DIVISION            --処理区分
    ,PROCESS.PROCESS_NAME                   --処理区分名称
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
    ,ITEM_FROM.ACTIVATE_FROM                --コピー元・削除品目の有効ステータス
    ,ITEM_TO.ACTIVATE_TO                    --コピー先品目の有効ステータス
    ,SALES_ESTI.INPUT_DATE					--入力日
	,SALES_ESTI.INPUTOR_CD					--入力者コード
	,LOGIN.TANTO_NM							--入力者名
    
FROM
    SALESTERMS_AND_ESTIMATE_CTRL SALES_ESTI
    
LEFT JOIN 
    (SELECT
        ITEM.ITEM_CD
        ,ITEM.ITEM_NAME ITEM_NAME_FROM
        ,ITEM.OTHER_COMPANY_CD1 OTHER_COMPANY_CD1_FROM
        ,ITEM.STYLE_OF_PACKING STYLE_OF_PACKING_FROM
        ,DECODE(IT_QU.VERSION, ITEM.VERSION, '有', NULL) ACTIVATE_FROM
        
    FROM
        ITEM

    LEFT JOIN 
        (SELECT ITEM_CD 
                ,MAX(VERSION) VERSION
            FROM ITEM_QUEUE
            GROUP BY ITEM_CD)IT_QU ON ITEM.ITEM_CD = IT_QU.ITEM_CD)ITEM_FROM ON SALES_ESTI.ITEM_CD_FROM = ITEM_FROM.ITEM_CD
            
LEFT JOIN 
    (SELECT
        ITEM.ITEM_CD
        ,ITEM.ITEM_NAME ITEM_NAME_TO
        ,ITEM.OTHER_COMPANY_CD1 OTHER_COMPANY_CD1_TO
        ,ITEM.STYLE_OF_PACKING STYLE_OF_PACKING_TO
        ,DECODE(IT_QU.VERSION, ITEM.VERSION, '有', NULL) ACTIVATE_TO
        
    FROM
        ITEM

    LEFT JOIN 
        (SELECT ITEM_CD 
                ,MAX(VERSION) VERSION
            FROM ITEM_QUEUE
            GROUP BY ITEM_CD)IT_QU ON ITEM.ITEM_CD = IT_QU.ITEM_CD)ITEM_TO ON SALES_ESTI.ITEM_CD_TO = ITEM_TO.ITEM_CD
            
LEFT JOIN
    (SELECT
        NAME_CD
        ,NAME01 PROCESS_NAME
    FROM
        NAMES
    WHERE
        NAMES.NAME_DIVISION = 'PRDI') PROCESS ON SALES_ESTI.PROCESS_DIVISION = PROCESS.NAME_CD
        
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

WHERE SALES_ESTI.PK_NO IS NOT NULL

-- 入力日From
/*IF(condition.strSrhInputDateFrom != null) && (condition.strSrhInputDateFrom != "")*/
AND TO_CHAR(SALES_ESTI.INPUT_DATE, 'YYYY/MM/DD') >= /*condition.strSrhInputDateFrom*/'2000/01/01'
/*END*/
	
-- 入力日To
/*IF(condition.strSrhInputDateTo != null) && (condition.strSrhInputDateTo != "")*/
AND TO_CHAR(SALES_ESTI.INPUT_DATE, 'YYYY/MM/DD') <= /*condition.strSrhInputDateTo*/'2020/12/31'
/*END*/

-- 処理区分が「全て」ではない場合
/*IF(condition.srhProcessDivision != 0)*/
AND SALES_ESTI.PROCESS_DIVISION = /*condition.srhProcessDivision*/1
/*END*/

-- ステータスが「全て」ではない場合
/*IF(condition.srhStatus != 0)*/
AND SALES_ESTI.STATUS = /*condition.srhStatus*/1
/*END*/

-- 入力者
/*IF (condition.srhTantoCd != null) && (condition.srhTantoCd != "")*/
AND	(SALES_ESTI.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

-- コピー元・削除品目コード
/*IF (condition.srhItemCdFrom != null) && (condition.srhItemCdFrom != "")*/
AND	(SALES_ESTI.ITEM_CD_FROM LIKE /*condition.srhItemCdFrom*/'%' OR ITEM_FROM.ITEM_NAME_FROM LIKE /*condition.srhItemCdFrom*/'%')
/*END*/

-- コピー元・削除品目コード
/*IF (condition.srhItemCdTo != null) && (condition.srhItemCdTo != "")*/
AND	(SALES_ESTI.ITEM_CD_TO LIKE /*condition.srhItemCdTo*/'%' OR ITEM_TO.ITEM_NAME_TO LIKE /*condition.srhItemCdTo*/'%')
/*END*/

ORDER BY SALES_ESTI.PK_NO DESC