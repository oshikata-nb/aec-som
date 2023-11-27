/*
 * 受払月報TEMP取得用SQL�󕥌���TEMP�ꗗ�pSQL
 *
 * entityName=RepTempInoutMonthlyReport
 * packageName=inoutmonthlyreportforreport
 * methodName=getListForReport
 *
 */

SELECT
     DATE_FROM
,    DATE_TO
,    LOCATION_CD        
,    LOCATION_NAME      
,    FINANCIAL_CLASS_CD 
,    ITEM_CD            
,    ITEM_NAME          
,    STYLE_OF_PACKING   
,    KG_OF_FRACTION_MANAGEMENT
,    LM_1  
,    LM_2  
,    LM_3  
,    LM_4  
,    IN_1  
,    IN_2  
,    IN_3  
,    IN_4  
,    IN_ALL
,    OUT_1 
,    OUT_7 
,    OUT_2 
,    OUT_3 
,    OUT_4 
,    OUT_5 
,    OUT_6 
,    OUT_ALL
,    NM_1  
,    NM_2  
,    NM_3  
,    NM_4  

FROM TEMP_INOUT_MONTHLY_REPORT

WHERE INPUTOR_CD = /*inputorCd*/'som'

ORDER BY ROW_NO


