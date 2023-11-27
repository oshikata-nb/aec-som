/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/

/**
 * 作業使用指図_ASPROVA取得SQL
 *
 * @author tosco
 *
 * entityName=AspImportAspUseinstruction
 * packageName=aspimport
 * methodName=getEntityByOrderCd
 *
 */

SELECT
	ASP_USEINSTRUCTION.*
FROM
	ASP_USEINSTRUCTION
WHERE
	ASP_USEINSTRUCTION.ORDER_CD = /*orderCd*/