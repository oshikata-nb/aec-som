/*
 * 受注取込一覧用SQL 
 *
 * entityName=OrderImportList
 * packageName=orderImportlist
 * methodName=getList
 *
 */
SELECT 
	ROW_NUMBER() OVER( 
	            ORDER BY 
					FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE	
				,	FRST_ORDER_HEAD.ORDER_NO
				,   NVL(FRST_ORDER_DETAIL.FRST_ORDER_NO, 0)
				,	FRST_ORDER_DETAIL.VIEW_SEQ
				,   FRST_ORDER_DETAIL.ROW_NO
				,   NVL(FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO, 0)
	               	) AS I_ROW_NUM
    ,
	 DENSE_RANK() OVER( 
	            PARTITION BY FRST_ORDER_HEAD.ORDER_NO , FRST_ORDER_HEAD.FRST_ORDER_NO
	            ORDER BY FRST_ORDER_DETAIL.VIEW_SEQ ,FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO
	        ) AS I_RANK
    , FRST_ORDER_DETAIL.FRST_ORDER_NO
    , FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO
    , FRST_ORDER_HEAD.ORDER_NO
    , FRST_ORDER_DETAIL.ROW_NO
    , DELIVERY.SEARCH_KANA AS DELIVERY_SHORTED_NAME
    , VENDER.VENDER_SHORTED_NAME VENDER_NAME
    , FRST_ORDER_DETAIL.ITEM_CD
    , NVL( ITEM.ITEM_NAME , FRST_ORDER_DETAIL.ITEM_CD ) AS ITEM_NAME
    , FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE
    , FRST_ORDER_HEAD.DELIVERY_EXPECTED_DATE
,   CASE 
	    WHEN FRST_ORDER_DETAIL.DEL_FLG = 1      THEN 90
	    WHEN FRST_ORDER_DETAIL.CANCEL_FLG = 1   THEN 90
        WHEN FRST_ORDER_HEAD.ORDER_NO IS NULL   THEN 10
        WHEN FRST_ORDER_HEAD.ORDER_DIVISION = 3 THEN PUS.STATUS
        WHEN SHP.SHIPPING_STATUS IS NOT NULL    THEN SHP.SHIPPING_STATUS
        ELSE 99
    END AS ORDER_STATUS
,   CASE
		    WHEN FRST_ORDER_DETAIL.DEL_FLG = 1      THEN TO_NCHAR('削除')
		    WHEN FRST_ORDER_DETAIL.CANCEL_FLG = 1   THEN TO_NCHAR('キャンセル')
            WHEN FRST_ORDER_HEAD.ORDER_NO IS NULL   THEN TO_NCHAR('先付受注')
            WHEN FRST_ORDER_HEAD.ORDER_DIVISION = 3 THEN PUS_NM.NAME01
            WHEN SHP_NM.NAME01 IS NOT NULL          THEN SHP_NM.NAME01
            ELSE TO_NCHAR('受注登録')
    END AS ORDER_STATUS_NAME
    , CASE WHEN FRST_ORDER_DETAIL.DEL_FLG = 1    THEN '削除'
           WHEN FRST_ORDER_DETAIL.CANCEL_FLG = 1 THEN 'キャンセル'
           WHEN FRST_ORDER_DETAIL.ORDER_NO IS NOT NULL THEN '受注登録'
           WHEN FRST_ORDER_DETAIL.INPUT_APPROVAL_DATE IS NOT NULL THEN '先付 チェック済'
           WHEN FRST_ORDER_DETAIL.INPUT_APPROVAL_DATE IS NULL THEN '先付 未チェック'
      END AS IMPORT_STATUS_NAME
    , FRST_ORDER_HEAD.ORDER_DIVISION
    , CASE WHEN FRST_ORDER_DETAIL.ERROR_FLG = 1 THEN 'エラー' ELSE '正常' END ERROR_STATUS_NAME
    , FRST_ORDER_DETAIL.CUSTOMER_ORDER_DETAIL_NO
    , FRST_ORDER_DETAIL.ORDER_QTY
    , FRST_ORDER_DETAIL.MATSS
    , NVL(ITEM_INVENTORY.INVENTORY_QTY, 0) INVENTORY_QTY
    , CASE WHEN FRST_ORDER_DETAIL.INPUT_APPROVAL_DATE IS NULL THEN 0 ELSE 1 END AS INPUT_APPROVALED
    , CASE WHEN FRST_ORDER_DETAIL.DEL_DATE_SEND_DATE IS NULL THEN 0 ELSE 1 END  AS DEL_DATE_APPROVALED 
	, ITEM.UNIT_OF_OPERATION_MANAGEMENT
	, FRST_ORDER_HEAD.PRINT_SUMMERY
	, FRST_ORDER_HEAD.DELIVERY_SLIP_SUMMERY
	, FRST_ORDER_HEAD.ORDER_SUMMERY
	, FRST_ORDER_HEAD.DELIVERYDATE_CONTACT_SUMMERY -- 20210909 Asclab Saita 納期連絡表専用備考追加対応
    ,   ORDER_IMP_BASE.CTM_REMARK_01 
        || CASE WHEN NVL(LENGTH(TRIM( ORDER_IMP_BASE.CTM_REMARK_02 ) ) , 0 ) <> 0 THEN '/' || ORDER_IMP_BASE.CTM_REMARK_02 END
        || CASE WHEN NVL(LENGTH(TRIM( ORDER_IMP_BASE.CTM_REMARK_03 ) ) , 0 ) <> 0 THEN '/' || ORDER_IMP_BASE.CTM_REMARK_03 END
        AS CTM_REMARK
	,   SHP.SHIPPING_STATUS AS SHIPPING_STATUS
	,   PUS.STATUS AS PURCHASE_STATUS
	,   ASP_ORD.WORK_STATUS AS ASP_STATUS
	,	VENDER.ORDER_FAX_OUTPUT
	,	VENDER.ORDER_MAIL_OUTPUT
	,   ORDER_DETAIL.UPDATE_DATE as ORDER_DETAIL_DATE --20220512 add S.Fujimaki
FROM 
    FRST_ORDER_HEAD
LEFT JOIN
    FRST_ORDER_DETAIL
ON
    FRST_ORDER_DETAIL.FRST_ORDER_NO = FRST_ORDER_HEAD.FRST_ORDER_NO
LEFT JOIN
    ORDER_IMP_BASE
ON
    ORDER_IMP_BASE.ORDER_IMP_NO = FRST_ORDER_DETAIL.ORDER_IMP_NO
LEFT JOIN 
    DELIVERY 
ON 
    DELIVERY.DELIVERY_CD = FRST_ORDER_HEAD.DELIVERY_CD
LEFT JOIN 
    VENDER 
ON 
    VENDER.VENDER_CD  = FRST_ORDER_HEAD.VENDER_CD
    AND VENDER.VENDER_DIVISION = 'TS'
LEFT JOIN 
    ORDER_HEAD
ON 
    FRST_ORDER_HEAD.ORDER_NO = ORDER_HEAD.ORDER_NO
LEFT JOIN 
    ORDER_DETAIL  
ON 
    FRST_ORDER_DETAIL.ORDER_NO = ORDER_DETAIL.ORDER_NO 
    AND FRST_ORDER_DETAIL.ROW_NO = ORDER_DETAIL.ROW_NO
LEFT JOIN 
(SELECT ORDER_NO,ORDER_ROW_NO,STATUS
            FROM PURCHASE_SUBCONTRACT 
            WHERE ORDER_NO IS NOT NULL 
            GROUP BY PURCHASE_SUBCONTRACT.ORDER_NO
            ,   PURCHASE_SUBCONTRACT.ORDER_ROW_NO
            ,   PURCHASE_SUBCONTRACT.STATUS
) PUS 
ON 
    PUS.ORDER_NO = ORDER_DETAIL.ORDER_NO
    AND PUS.ORDER_ROW_NO = ORDER_DETAIL.ROW_NO
LEFT JOIN 
(SELECT ORDER_NO
            FROM PURCHASE_SUBCONTRACT 
            WHERE PURCHASE_SUBCONTRACT.ORDER_NO IS NOT NULL 
            GROUP BY PURCHASE_SUBCONTRACT.ORDER_NO
) PUS2 
ON 
    PUS2.ORDER_NO = FRST_ORDER_HEAD.ORDER_NO
LEFT JOIN 
(SELECT ORDER_NO
            FROM SHIPPING 
            WHERE SHIPPING.ORDER_NO IS NOT NULL 
            GROUP BY SHIPPING.ORDER_NO
) SHP2 
ON 
    SHP2.ORDER_NO = FRST_ORDER_HEAD.ORDER_NO
LEFT JOIN 
    SHIPPING SHP 
ON 
    SHP.SHIPPING_NO = ORDER_DETAIL.SHIPPING_NO
LEFT JOIN 
    NAMES SHP_NM 
ON 
    SHP_NM.NAME_CD = SHP.SHIPPING_STATUS
    AND SHP_NM.NAME_DIVISION = 'ORST'
LEFT JOIN 
    NAMES PUS_NM 
ON 
    PUS_NM.NAME_CD = PUS.STATUS
    AND PUS_NM.NAME_DIVISION = 'PUST'
LEFT JOIN 
    ITEM_INVENTORY  
ON 
    ITEM_INVENTORY.ITEM_CD = FRST_ORDER_DETAIL.ITEM_CD
LEFT JOIN 
    ITEM  
ON 
    ITEM.ITEM_CD = FRST_ORDER_DETAIL.ITEM_CD 
LEFT JOIN 
    LOGIN SLG 
ON 
    SLG.TANTO_CD = FRST_ORDER_HEAD.SALES_TANTO_CD
LEFT JOIN 
    LOGIN ILG 
ON 
    ILG.TANTO_CD = FRST_ORDER_HEAD.INPUT_TANTO_CD
LEFT JOIN 
    ORGANIZATION 
ON 
    ORGANIZATION.ORGANIZATION_CD = FRST_ORDER_HEAD.ORGANIZATION_CD
LEFT JOIN 
    ASP_PRODUCTION ASP_PRO 
ON 
    FRST_ORDER_DETAIL.ORDER_NO = ASP_PRO.ORDER_NO 
    AND FRST_ORDER_DETAIL.ROW_NO = ASP_PRO.ORDER_ROW_NO
LEFT JOIN 
    ASP_ORDER ASP_ORD 
ON 
    ASP_PRO.ORDER_CD = ASP_ORD.ORDER_CD
WHERE
    FRST_ORDER_HEAD.FRST_ORDER_NO IS NOT NULL
    --受注グループ番号From
    /*IF (condition.srhFrstOrderNoFrom != null) && (condition.srhFrstOrderNoFrom != "")*/
    AND	FRST_ORDER_HEAD.FRST_ORDER_NO >= /*condition.srhFrstOrderNoFrom*/'GR00000000' 
    /*END*/
    --受注グループ番号To
    /*IF (condition.srhFrstOrderNoTo!= null) && (condition.srhFrstOrderNoTo != "")*/
    AND	FRST_ORDER_HEAD.FRST_ORDER_NO <= /*condition.srhFrstOrderNoTo*/'LR99999999' 
    /*END*/
    --客先注文番号
    /*IF (condition.srhCtmOrderNo != null) && (condition.srhCtmOrderNo != "")*/
    AND	FRST_ORDER_HEAD.CUSTOMER_ORDER_NO LIKE /*condition.srhCtmOrderNo*/'00000000'
    /*END*/
    --得意先グループコード
    /*IF ( condition.srhVenderGroupCd != "0" ) && (condition.srhVenderGroupCd != "99999")*/
    AND FRST_ORDER_HEAD.VENDER_GROUP_CD = /*condition.srhVenderGroupCd*/'%'
    /*END*/
    /*IF (condition.srhVenderGroupCd == "99999")*/
    AND FRST_ORDER_HEAD.VENDER_GROUP_CD IS NULL
    /*END*/
    --取込日From
    /*IF (condition.srhImportDateFrom != null) && (condition.srhImportDateFrom != "")*/
    AND TO_CHAR(ORDER_IMP_BASE.IMPORT_DATE, 'YYYY/MM/DD') >= /*condition.srhImportDateFrom*/'1900/01/01'
    /*END*/
    --取込日To
    /*IF (condition.srhImportDateTo!= null) && (condition.srhImportDateTo != "")*/
    AND TO_CHAR(ORDER_IMP_BASE.IMPORT_DATE, 'YYYY/MM/DD') <= /*condition.srhImportDateTo*/'2199/01/01'
    /*END*/
    --受注番号From
    /*IF (condition.srhOrderNoFrom != null) && (condition.srhOrderNoFrom != "")*/
    AND	FRST_ORDER_HEAD.ORDER_NO >= /*condition.srhOrderNoFrom*/'JU00000000' 
    /*END*/
    --受注番号To
    /*IF (condition.srhOrderNoTo!= null) && (condition.srhOrderNoTo != "")*/
    AND	FRST_ORDER_HEAD.ORDER_NO <= /*condition.srhOrderNoTo*/'JU99999999' 
    /*END*/
    -- 受注区分
    /*IF (condition.srhOrderDivision != 0)*/
    AND FRST_ORDER_HEAD.ORDER_DIVISION = /*condition.srhOrderDivision*/3
    /*END*/
    --受注ステータス
    -- 受注ステータスが先付受注の場合
    /*IF (condition.srhOrderStatus == 90)*/
        AND FRST_ORDER_HEAD.ORDER_NO IS NULL
    /*END*/
    -- ステータスが受注登録の場合
    /*IF (condition.srhOrderStatus == 99)*/
        AND FRST_ORDER_HEAD.ORDER_NO IS NOT NULL
        AND SHP.SHIPPING_STATUS IS NULL
        AND PUS.STATUS IS NULL
        AND NOT ( FRST_ORDER_DETAIL.CANCEL_FLG = 1 AND ( SHP2.ORDER_NO IS NOT NULL OR PUS2.ORDER_NO IS NOT NULL ) )
        /*END*/
                
    --受注区分が全ての場合
    /*IF (condition.srhOrderDivision == 0)*/
        -- ステータスが全てでは無い場合
        /*IF ((condition.srhOrderStatus != 0) && (condition.srhOrderStatus != 99) && (condition.srhOrderStatus != 90))*/
            -- 出荷ステータス検索
            AND (SHP.SHIPPING_STATUS = /*condition.srhOrderStatus*/3
            
            -- ステータスが出荷未確定の場合
            /*IF (condition.srhOrderStatus == 1) */
            OR PUS.STATUS = 7
            /*END*/
            
            -- ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
            /*IF (condition.srhOrderStatus > 1) && (condition.srhOrderStatus < 5)*/
            OR PUS.STATUS > 1 AND PUS.STATUS < 6
            /*END*/
            
            -- ステータスが出荷未確定の場合
            /*IF (condition.srhOrderStatus == 5)*/
            OR PUS.STATUS = 6
            /*END*/
            )
        /*END*/
        
    /*END*/

    
    -- 受注区分が全てではなく　かつ　仕入直送品では無い場合
    /*IF (condition.srhOrderDivision != 0) && (condition.srhOrderDivision != 3)*/
        -- ステータスが全てでは無い場合
        /*IF ((condition.srhOrderStatus != 0) && (condition.srhOrderStatus != 90) && (condition.srhOrderStatus != 99))*/
            AND SHP.SHIPPING_STATUS = /*condition.srhOrderStatus*/3
        /*END*/
    /*END*/

    -- 受注区分が仕入直送品の場合
    /*IF ((condition.srhOrderDivision == 3) && (condition.srhOrderStatus != 90) && (condition.srhOrderStatus != 99))*/
        
        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
        /*IF (condition.srhOrderStatus == 1) */
        AND PUS.STATUS = 7
        /*END*/
    
        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
        /*IF (condition.srhOrderStatus > 1) && (condition.srhOrderStatus < 5)*/
        AND PUS.STATUS > 1 AND PUS.STATUS < 6
        /*END*/
        
        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
        /*IF (condition.srhOrderStatus == 5)*/
        AND PUS.STATUS = 6
        /*END*/
    
    /*END*/
    --受注日From
    /*IF (condition.srhOrderDateFrom != null) && (condition.srhOrderDateFrom != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.ORDER_DATE, 'YYYY/MM/DD') >= /*condition.srhOrderDateFrom*/'1900/01/01'
    /*END*/
    --受注日To
    /*IF (condition.srhOrderDateTo!= null) && (condition.srhOrderDateTo != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.ORDER_DATE, 'YYYY/MM/DD') <= /*condition.srhOrderDateTo*/'2199/01/01'
    /*END*/
    --納入先
    /*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
    AND	(FRST_ORDER_HEAD.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
        OR DELIVERY.SEARCH_KANA LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%')
        OR ORDER_IMP_BASE.CTM_DELIVERY_CD_01 || ORDER_IMP_BASE.CTM_DELIVERY_CD_02 || ORDER_IMP_BASE.CTM_DELIVERY_CD_03 LIKE /*condition.srhDeliveryCd*/'%' 
        OR ORDER_IMP_BASE.CTM_DELIVERY_NAME_01 || ORDER_IMP_BASE.CTM_DELIVERY_NAME_02 || ORDER_IMP_BASE.CTM_DELIVERY_NAME_03 LIKE /*condition.srhDeliveryCd*/'%'
    )
    /*END*/
    --住所
    /*IF (condition.srhAddress != null) && (condition.srhAddress != "")*/
    AND (DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 LIKE /*condition.srhAddress*/'%'
        OR ORDER_IMP_BASE.CTM_DELIVERY_ADDRESS_01 || ORDER_IMP_BASE.CTM_DELIVERY_ADDRESS_02 || ORDER_IMP_BASE.CTM_DELIVERY_ADDRESS_03 LIKE /*condition.srhAddress*/'%')
    /*END*/
    --納入先電話番号
    /*IF (condition.srhDeliveryTelNo != null) && (condition.srhDeliveryTelNo != "")*/
    AND (FRST_ORDER_HEAD.DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%'
        OR ORDER_IMP_BASE.CTM_DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%')
    /*END*/
    --客先返信
    /*IF (condition.srhSlipPublishComp == "1")*/
    AND FRST_ORDER_DETAIL.DEL_DATE_SEND_DATE IS NULL
    /*END*/
    /*IF (condition.srhSlipPublishComp == "2")*/
    AND FRST_ORDER_DETAIL.DEL_DATE_SEND_DATE IS NOT NULL
    /*END*/
    --出荷予定日From
    /*IF (condition.srhScheduledShippingDateFrom != null) && (condition.srhScheduledShippingDateFrom != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/'1900/01/01'
    /*END*/
    --出荷予定日To
    /*IF (condition.srhScheduledShippingDateTo!= null) && (condition.srhScheduledShippingDateTo != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/'2199/01/01'
    /*END*/
    --得意先
    /*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
    AND (FRST_ORDER_HEAD.VENDER_CD LIKE /*condition.srhVenderCd*/'%' 
        OR VENDER.VENDER_SHORTED_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
        --2次店以下で検索
        OR FNC_GET_UPPER_VENDER(FRST_ORDER_HEAD.BALANCE_CD ,2) = REPLACE (/*condition.srhVenderCd*/'','%','')
        OR FNC_GET_UPPER_VENDER(FRST_ORDER_HEAD.BALANCE_CD ,3) = REPLACE (/*condition.srhVenderCd*/'','%','')
        OR FNC_GET_UPPER_VENDER(FRST_ORDER_HEAD.BALANCE_CD ,4) = REPLACE (/*condition.srhVenderCd*/'','%','')
        OR FNC_GET_UPPER_VENDER(FRST_ORDER_HEAD.BALANCE_CD ,5) = REPLACE (/*condition.srhVenderCd*/'','%','')
        --客先得意先で検索
        OR ORDER_IMP_BASE.CTM_VENDER_CD_01 || ORDER_IMP_BASE.CTM_VENDER_CD_02 || ORDER_IMP_BASE.CTM_VENDER_CD_03 LIKE /*condition.srhVenderCd*/'%' 
        OR ORDER_IMP_BASE.CTM_VENDER_NAME_01 || ORDER_IMP_BASE.CTM_VENDER_NAME_02 || ORDER_IMP_BASE.CTM_VENDER_NAME_03 LIKE /*condition.srhVenderCd*/'%'
    )
    /*END*/
    --担当部署
    /*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
    AND	(FRST_ORDER_HEAD.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
    /*END*/
    --仮単価FLG
    /*IF (condition.srhTmpUnitpriceFlg != "9")*/
    AND FRST_ORDER_DETAIL.TMP_UNITPRICE_FLG = /*condition.srhTmpUnitpriceFlg*/0
    /*END*/
    --営業担当者
    /*IF (condition.srhSalesTantoCd != null) && (condition.srhSalesTantoCd != "")*/
    AND	(FRST_ORDER_HEAD.SALES_TANTO_CD LIKE /*condition.srhSalesTantoCd*/'%' OR SLG.TANTO_NM LIKE /*condition.srhSalesTantoCd*/'%')
    /*END*/
    --入力担当者
    /*IF (condition.srhInputTantoCd != null) && (condition.srhInputTantoCd != "")*/
    AND	(FRST_ORDER_HEAD.INPUT_TANTO_CD LIKE /*condition.srhInputTantoCd*/'%' OR ILG.TANTO_NM LIKE /*condition.srhInputTantoCd*/'%')
    /*END*/
    --出荷BC
    /*IF (condition.srhCarryBc != null) && (condition.srhCarryBc != "")*/
    AND	SHP.CARRY_BC LIKE /*condition.srhCarryBc*/'%'
    /*END*/
    --品目
    /*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
    AND	(FRST_ORDER_DETAIL.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
    /*END*/
    --他社コード1
    /*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
    AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
    /*END*/
    --希望納期From
    /*IF (condition.srhSuggestedDeliverlimitFrom != null) && (condition.srhSuggestedDeliverlimitFrom != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.SUGGESTED_DELIVERLIMIT, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitFrom*/'1900/01/01'
    /*END*/
    --希望納期To
    /*IF (condition.srhSuggestedDeliverlimitTo != null) && (condition.srhSuggestedDeliverlimitTo != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.SUGGESTED_DELIVERLIMIT, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitTo*/'2199/01/01'
    /*END*/
    --運送会社
    /*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
        /*IF ( condition.srhCarryCd != "0" )*/
    AND	FRST_ORDER_HEAD.CARRY_CD LIKE /*condition.srhCarryCd*/'%'
        /*END*/
    /*END*/
    --エラー状態
    /*IF ( condition.srhErrorStatus == "1" )*/
    AND FRST_ORDER_DETAIL.ERROR_FLG = '1'
    /*END*/
    /*IF ( condition.srhErrorStatus == "2" )*/
    AND FRST_ORDER_DETAIL.ERROR_FLG = '0'
    /*END*/

    --データ取込区分
    /*IF ( condition.srhInputDivision == "1" )*/
    AND FRST_ORDER_DETAIL.ORDER_IMP_NO IS NOT NULL  
    /*END*/
    /*IF ( condition.srhInputDivision == "2" )*/
    AND FRST_ORDER_DETAIL.ORDER_IMP_NO IS NULL  
    /*END*/

    --納品予定日From
    /*IF (condition.srhDeliveryExpectedDateFrom != null) && (condition.srhDeliveryExpectedDateFrom != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.DELIVERY_EXPECTED_DATE, 'YYYY/MM/DD') >= /*condition.srhDeliveryExpectedDateFrom*/'1900/01/01'
    /*END*/
    --納品予定日To
    /*IF (condition.srhDeliveryExpectedDateTo != null) && (condition.srhDeliveryExpectedDateTo != "")*/
    AND TO_CHAR(FRST_ORDER_HEAD.DELIVERY_EXPECTED_DATE, 'YYYY/MM/DD') <= /*condition.srhDeliveryExpectedDateTo*/'2199/01/01'
    /*END*/
    --取込番号From
   	/*IF (condition.srhTempNoFrom != null) && (condition.srhTempNoFrom != "")*/
    AND	FRST_ORDER_DETAIL.ORDER_IMP_NO >= /*condition.srhTempNoFrom*/'OI000000001' 
    /*END*/
    --取込番号To
    /*IF (condition.srhTempNoTo != null) && (condition.srhTempNoTo != "")*/
    AND	FRST_ORDER_DETAIL.ORDER_IMP_NO <= /*condition.srhTempNoTo*/'OI999999999' 
    /*END*/
    --入力チェック
    /*IF ( condition.srhOrderInputCheck == "1" )*/
    AND FRST_ORDER_DETAIL.INPUT_APPROVAL_DATE IS NULL
    /*END*/
    /*IF ( condition.srhOrderInputCheck == "2" )*/
    AND FRST_ORDER_DETAIL.INPUT_APPROVAL_DATE IS NOT NULL
    /*END*/
    --納期連絡
    /*IF ( condition.srhDelDateSend == "1" )*/
    AND FRST_ORDER_DETAIL.DEL_DATE_SEND_DATE IS NULL
    /*END*/
    /*IF ( condition.srhDelDateSend == "2" )*/
    AND FRST_ORDER_DETAIL.DEL_DATE_SEND_DATE IS NOT NULL
    /*END*/
    
    
    --削除・キャンセル
    /*IF ( condition.srhDeleteCancel == "0" )*/
    -- 0:全ての場合、非表示以外を表示する。
    AND FRST_ORDER_HEAD.DEL_FLG = 0　
    AND FRST_ORDER_DETAIL.DEL_FLG = 0 
    AND FRST_ORDER_HEAD.INVISIBLE_FLG = 0	-- 非表示は表示しない
    /*END*/
    /*IF ( condition.srhDeleteCancel == "1" )*/
    -- 1:削除の場合、削除のみを表示する。
    AND ( FRST_ORDER_HEAD.DEL_FLG = 1 OR FRST_ORDER_DETAIL.DEL_FLG = 1 )
    AND ( FRST_ORDER_HEAD.INVISIBLE_FLG = 0 )	-- 非表示は表示しない
    /*END*/
    /*IF ( condition.srhDeleteCancel == "2" )*/
    -- 2:キャンセルの場合、キャンセルのみを表示する。
    AND ( FRST_ORDER_HEAD.CANCEL_FLG = 1 OR FRST_ORDER_DETAIL.CANCEL_FLG = 1)
    AND ( FRST_ORDER_HEAD.INVISIBLE_FLG = 0 )	-- 非表示は表示しない
    /*END*/
    /*IF ( condition.srhDeleteCancel == "3" )*/
    -- 3:どちらかの場合、削除及びキャンセルを表示する。
    AND ( FRST_ORDER_HEAD.DEL_FLG = 1 OR FRST_ORDER_HEAD.CANCEL_FLG = 1 OR FRST_ORDER_DETAIL.DEL_FLG = 1 OR FRST_ORDER_DETAIL.CANCEL_FLG = 1 )
    AND ( FRST_ORDER_HEAD.INVISIBLE_FLG = 0 )	-- 非表示は表示しない
    /*END*/
    /*IF ( condition.srhDeleteCancel == "4" )*/
    -- 4:どちらでもないの場合、削除、キャンセル、非表示以外を表示する。
    AND ( FRST_ORDER_HEAD.DEL_FLG <> 1 AND FRST_ORDER_HEAD.CANCEL_FLG <> 1 AND FRST_ORDER_DETAIL.DEL_FLG <> 1 AND FRST_ORDER_DETAIL.CANCEL_FLG <> 1 )
    AND ( FRST_ORDER_HEAD.INVISIBLE_FLG = 0 )	-- 非表示は表示しない
    /*END*/
    /*IF ( condition.srhDeleteCancel == "5" )*/
    -- 5:全キャンセルの場合、全キャンセルのみを表示する。
    AND ( FRST_ORDER_HEAD.CANCEL_FLG = 1 )
    AND ( FRST_ORDER_HEAD.INVISIBLE_FLG = 0 )
    /*END*/
    /*IF ( condition.srhDeleteCancel == "6" )*/
    -- 6:非表示　非表示の実を表示する
    AND ( FRST_ORDER_HEAD.INVISIBLE_FLG = 1 )
    /*END*/
    
    ORDER BY
	-- 20210528 ソート順序を①納入予定日、②受注番号、③先付受注番号に変更
	FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE	
,	FRST_ORDER_HEAD.ORDER_NO
,   NVL(FRST_ORDER_DETAIL.FRST_ORDER_NO, 0)
,	FRST_ORDER_DETAIL.VIEW_SEQ
,   FRST_ORDER_DETAIL.ROW_NO
,   NVL(FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO, 0)
