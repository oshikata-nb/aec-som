/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */

/**
 * 受入入力 会社コード取得SQL
 * (売上データ登録用)
 *
 * @author tosco
 *
 * entityName=AcceptDetailSales
 * packageName=accept
 * methodName=getCompanyCd
 *
 */
SELECT 	COMPANY.COMPANY_CD 		-- 会社コード
FROM 	COMPANY COMPANY
WHERE 	ROWNUM <= 1
