/*
 * サンプルSQL
 *
 * entityName=TopicList
 * packageName=topic
 * methodName=getTopicList
 *
 */
SELECT	TOPIC.TOPIC_ID
,	TOPIC.TOPIC_TITLE
,	TOPIC.TOPIC_INPUTOR
,	TOPIC.INPUT_DATE
,	TOPIC.TOPIC_RET_INPUTOR
FROM	TOPIC
WHERE	TOPIC_ID IS NOT NULL

/*IF (condition.topicId != null) && (condition.topicId != "")*/
AND TOPIC_ID = /*condition.topicId*/'1'
/*END*/

ORDER BY TOPIC.TOPIC_ID DESC
