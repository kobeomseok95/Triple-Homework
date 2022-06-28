CREATE TABLE USERS (
    `USER_ID`               VARCHAR(36)     NOT NULL,
    `USER_POINTS`           BIGINT          NOT NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`USER_ID`)
);

CREATE TABLE `REVIEW` (
    `REVIEW_ID`             VARCHAR(36)     NOT NULL,
    `USER_ID`               VARCHAR(36)     NOT NULL,
    `PLACE_ID`              VARCHAR(36)     NOT NULL,
    `CONTENT`               VARCHAR(1000)   NULL,
    `REVIEW_POINTS`         BIGINT          NOT NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `REVIEW_UNQ` UNIQUE (`USER_ID`, `PLACE_ID`),
    CONSTRAINT `REVIEW_TO_USER` FOREIGN KEY (`USER_ID`) REFERENCES USERS(`USER_ID`),
    PRIMARY KEY (`REVIEW_ID`)
);

CREATE TABLE `ATTACHED_PHOTO` (
    `ATTACHED_PHOTO_ID`     VARCHAR(36)     NOT NULL,
    `REVIEW_ID`             VARCHAR(36)     NOT NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `ATTACHED_PHOTO_TO_REVIEW` FOREIGN KEY (`REVIEW_ID`) REFERENCES `REVIEW`(`REVIEW_ID`),
    PRIMARY KEY (`ATTACHED_PHOTO_ID`)
);

CREATE TABLE `POINT_HISTORY` (
    `POINT_HISTORY_ID`      VARCHAR(36)     NOT NULL,
    `POINT`                 BIGINT          NOT NULL,
    `USER_ID`               VARCHAR(36)     NOT NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `POINT_HISTORY_TO_USER` FOREIGN KEY (`USER_ID`) REFERENCES USERS(`USER_ID`),
    PRIMARY KEY (`POINT_HISTORY_ID`)
);

CREATE INDEX `POINT_HISTORY_IDX` ON `POINT_HISTORY` (`USER_ID`);

INSERT INTO USERS(USER_ID, USER_POINTS, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('3ede0ef2-92b7-4817-a5f3-0c575361f745', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO REVIEW(REVIEW_ID, USER_ID, PLACE_ID, CONTENT, REVIEW_POINTS, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('240a0658-dc5f-4878-9381-ebb7b2667772',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       '2e4baf1c-5acb-4efb-a1af-eddada31b00f',
       '테스트1 - 첫 리뷰, 내용 작성, 사진 포함',
       3,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO ATTACHED_PHOTO(ATTACHED_PHOTO_ID, REVIEW_ID, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('46cdeaf5-5fe3-40e7-b647-bd9a9d385cec',
       '240a0658-dc5f-4878-9381-ebb7b2667772',
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO POINT_HISTORY(POINT_HISTORY_ID, USER_ID, POINT, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('94530a38-9fad-492b-b110-74132f50fbdf',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       3,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO REVIEW(REVIEW_ID, USER_ID, PLACE_ID, CONTENT, REVIEW_POINTS, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('ddec19cc-795f-499d-be81-af6de41e96dd',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       '284abdcf-3a4a-41b9-9553-eec5a8441c16',
       '테스트2 - 첫 리뷰, 내용 작성, 사진 포함',
       3,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO ATTACHED_PHOTO(ATTACHED_PHOTO_ID, REVIEW_ID, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('a2ee8375-0e49-4931-9780-f4ab40244bad',
       'ddec19cc-795f-499d-be81-af6de41e96dd',
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO POINT_HISTORY(POINT_HISTORY_ID, USER_ID, POINT, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('061918d4-b9de-43e3-92ce-9e9ffdb6b4e5',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       3,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO REVIEW(REVIEW_ID, USER_ID, PLACE_ID, CONTENT, REVIEW_POINTS, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('d4e4d421-9c1f-49a0-8314-8e07ad53d57d',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       '68266b02-8e40-4fee-946a-4031e53d58c0',
       '테스트3 - 첫 리뷰, 내용 작성',
       2,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO POINT_HISTORY(POINT_HISTORY_ID, USER_ID, POINT, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('75e2acc9-7830-437f-845d-ca3ecf7cae1f',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       2,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO REVIEW(REVIEW_ID, USER_ID, PLACE_ID, CONTENT, REVIEW_POINTS, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('302f455c-372c-474d-bcb6-4e9eb54c0cf8',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       '131cba76-6611-4d7c-9863-08efafcad874',
       '테스트4 - 첫 리뷰, 내용 작성',
       2,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);

INSERT INTO POINT_HISTORY(POINT_HISTORY_ID, USER_ID, POINT, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES('d6cd9090-e289-49b1-ba24-637e000ad308',
       '3ede0ef2-92b7-4817-a5f3-0c575361f745',
       2,
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP);