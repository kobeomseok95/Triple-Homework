CREATE TABLE `USER` (
    `USER_ID`               VARCHAR(36)     NOT NULL,
    `POINT_SCORE`           BIGINT          NOT NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`USER_ID`)
);

CREATE TABLE `REVIEW` (
    `REVIEW_ID`             VARCHAR(36)     NOT NULL,
    `USER_ID`               VARCHAR(36)     NOT NULL,
    `PLACE_ID`              VARCHAR(36)     NOT NULL,
    `CONTENT`               VARCHAR(1000)   NULL,
    `IS_DELETED`            TINYINT         NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`REVIEW_ID`)
);

ALTER TABLE `REVIEW` ADD CONSTRAINT `REVIEW_TO_USER`
FOREIGN KEY (`USER_ID`) REFERENCES `USER`(`USER_ID`);

ALTER TABLE `REVIEW` ADD CONSTRAINT `REVIEW_UNQ`
UNIQUE (`USER_ID`, `PLACE_ID`);

CREATE UNIQUE INDEX `REVIEW_IDX` ON `REVIEW` (`USER_ID`, `PLACE_ID`);

CREATE TABLE `ATTACHED_PHOTO` (
    `ATTACHED_PHOTO_ID`     VARCHAR(36)     NOT NULL,
    `REVIEW_ID`             VARCHAR(36)     NOT NULL,
    `IS_DELETED`            TINYINT         NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ATTACHED_PHOTO_ID`)
);

ALTER TABLE `ATTACHED_PHOTO` ADD CONSTRAINT `ATTACHED_PHOTO_TO_REVIEW`
FOREIGN KEY (`REVIEW_ID`) REFERENCES `REVIEW`(`REVIEW_ID`);

CREATE TABLE `POINT_HISTORY` (
    `POINT_HISTORY_ID`      VARCHAR(36)     NOT NULL,
    `POINT`                 BIGINT          NOT NULL,
    `USER_ID`               VARCHAR(36)     NOT NULL,
    `CREATED_DATE`          TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED_DATE`    TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`POINT_HISTORY_ID`)
);

CREATE INDEX `POINT_HISTORY_IDX` ON `POINT_HISTORY` (`USER_ID`);

ALTER TABLE `POINT_HISTORY` ADD CONSTRAINT `POINT_HISTORY_TO_USER`
FOREIGN KEY (`USER_ID`) REFERENCES `USER`(`USER_ID`);