insert into USERS(USER_ID, POINT_SCORE, CREATED_DATE, LAST_MODIFIED_DATE)
values('3ede0ef2-92b7-4817-a5f3-0c575361f745', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into REVIEW(REVIEW_ID, USER_ID, PLACE_ID, CONTENT, IS_DELETED, CREATED_DATE, LAST_MODIFIED_DATE)
values('240a0658-dc5f-4878-9381-ebb7b2667772',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       '2e4baf1c-5acb-4efb-a1af-eddada31b00f',
       '테스트',
       0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
