/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/

/**
 * 処方詳細-レシピインデックスで削除用SQL
 *
 * @author tosco
 *
 * entityName=MgrecipeLabel
 * packageName=mgrecipe
 * methodName=deleteByLabelId
 *
 */
DELETE
FROM
	LABEL
WHERE
	LABEL.LABEL_CD = /*labelCd*/30732
