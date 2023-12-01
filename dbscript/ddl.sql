update party
set PA_TIME = ?, PA_TOTAL_AMOUNT = ?, PA_DEPOSIT = ?, PA_TITLE = ?,
PA_CON, PA_MOD_DATE = sysdate, PA_GENDER_SET = ?, PA_GENDER_LIMIT = ?,
PA_LOCATION = ?, PA_TOTAL_NUM = ?, CAT_NUM = ?
where pa_num = 1;