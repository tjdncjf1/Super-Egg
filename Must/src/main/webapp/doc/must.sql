-- 회원정보
DROP TABLE IF EXISTS `USERS` RESTRICT;

-- 상품정보
DROP TABLE IF EXISTS `ITEMS` RESTRICT;

-- 상품 리스트
DROP TABLE IF EXISTS `USER_ITEM_LIST` RESTRICT;

-- 시단위가격변동내역
DROP TABLE IF EXISTS `HOUR_CHANGES` RESTRICT;

-- 일단위가격변동내역
DROP TABLE IF EXISTS `DAY_CHANGES` RESTRICT;

-- 주단위가격변동내역
DROP TABLE IF EXISTS `WEEK_CHANGES` RESTRICT;

-- 월단위가격변동내역
DROP TABLE IF EXISTS `MONTH_CHANGES` RESTRICT;

-- 월단위가격변동내역2
DROP TABLE IF EXISTS `YEAR_CHANGES` RESTRICT;

-- 회원정보
CREATE TABLE `USERS` (
  `UNO`    INTEGER      NOT NULL, -- 회원번호
  `UEMAIL` VARCHAR(100) NOT NULL, -- 이메일
  `UPW`    VARCHAR(20)  NOT NULL  -- 비밀번호
);

-- 회원정보
ALTER TABLE `USERS`
  ADD CONSTRAINT `PK_USERS` -- 회원정보 기본키
    PRIMARY KEY (
      `UNO` -- 회원번호
    );

-- 회원정보 유니크 인덱스
CREATE UNIQUE INDEX `UIX_USERS`
  ON `USERS` ( -- 회원정보
    `UEMAIL` ASC -- 이메일
  );

ALTER TABLE `USERS`
  MODIFY COLUMN `UNO` INTEGER NOT NULL AUTO_INCREMENT;

-- 상품정보
CREATE TABLE `ITEMS` (
  `PROD_ID`    VARCHAR(100) NOT NULL, -- 상품코드
  `TITLE`      VARCHAR(200) NOT NULL, -- 상품명
  `IMAGE_URL`  VARCHAR(255) NULL,     -- 이미지경로
  `MIN_PRICE`  INTEGER      NOT NULL, -- 현재최소가
  `WISH_PRICE` INTEGER      NOT NULL, -- 희망가
  `REG_DATE`   DATETIME     NOT NULL, -- 등록일
  `LINK`       VARCHAR(255) NOT NULL  -- 상품페이지 경로
);

-- 상품정보
ALTER TABLE `ITEMS`
  ADD CONSTRAINT `PK_ITEMS` -- 상품정보 기본키
    PRIMARY KEY (
      `PROD_ID` -- 상품코드
    );

-- 상품 리스트
CREATE TABLE `USER_ITEM_LIST` (
  `PROD_ID`    VARCHAR(100) NOT NULL, -- 상품코드
  `UNO`        INTEGER      NOT NULL, -- 회원번호
  `WISH_PRICE` INTEGER      NOT NULL, -- 희망가격
  `REG_DATE`   DATETIME     NOT NULL  -- 등록일
);

-- 상품 리스트
ALTER TABLE `USER_ITEM_LIST`
  ADD CONSTRAINT `PK_USER_ITEM_LIST` -- 상품 리스트 기본키
    PRIMARY KEY (
      `PROD_ID`, -- 상품코드
      `UNO`      -- 회원번호
    );

-- 시단위가격변동내역
CREATE TABLE `HOUR_CHANGES` (
  `HNO`     INTEGER      NOT NULL, -- 시간별번호
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `HPRICE`  INTEGER      NOT NULL, -- 가격
  `HTIME`   DATETIME     NOT NULL  -- 시단위
);

-- 시단위가격변동내역
ALTER TABLE `HOUR_CHANGES`
  ADD CONSTRAINT `PK_HOUR_CHANGES` -- 시단위가격변동내역 기본키
    PRIMARY KEY (
      `HNO` -- 시간별번호
    );

ALTER TABLE `HOUR_CHANGES`
  MODIFY COLUMN `HNO` INTEGER NOT NULL AUTO_INCREMENT;

-- 일단위가격변동내역
CREATE TABLE `DAY_CHANGES` (
  `DNO`     INTEGER      NOT NULL, -- 일별번호
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `DPRICE`  INTEGER      NOT NULL, -- 가격
  `DTIME`   DATE         NOT NULL  -- 일단위
);

-- 일단위가격변동내역
ALTER TABLE `DAY_CHANGES`
  ADD CONSTRAINT `PK_DAY_CHANGES` -- 일단위가격변동내역 기본키
    PRIMARY KEY (
      `DNO` -- 일별번호
    );

ALTER TABLE `DAY_CHANGES`
  MODIFY COLUMN `DNO` INTEGER NOT NULL AUTO_INCREMENT;

-- 주단위가격변동내역
CREATE TABLE `WEEK_CHANGES` (
  `WNO`     INTEGER      NOT NULL, -- 주별번호
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `WPRICE`  INTEGER      NOT NULL, -- 가격
  `WTIME`   DATE         NOT NULL  -- 주단위
);

-- 주단위가격변동내역
ALTER TABLE `WEEK_CHANGES`
  ADD CONSTRAINT `PK_WEEK_CHANGES` -- 주단위가격변동내역 기본키
    PRIMARY KEY (
      `WNO` -- 주별번호
    );

ALTER TABLE `WEEK_CHANGES`
  MODIFY COLUMN `WNO` INTEGER NOT NULL AUTO_INCREMENT;

-- 월단위가격변동내역
CREATE TABLE `MONTH_CHANGES` (
  `MNO`     INTEGER      NOT NULL, -- 월별번호
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `MPRICE`  INTEGER      NOT NULL, -- 가격
  `MTIME`   DATE         NOT NULL  -- 월단위
);

-- 월단위가격변동내역
ALTER TABLE `MONTH_CHANGES`
  ADD CONSTRAINT `PK_MONTH_CHANGES` -- 월단위가격변동내역 기본키
    PRIMARY KEY (
      `MNO` -- 월별번호
    );

ALTER TABLE `MONTH_CHANGES`
  MODIFY COLUMN `MNO` INTEGER NOT NULL AUTO_INCREMENT;

-- 월단위가격변동내역2
CREATE TABLE `YEAR_CHANGES` (
  `YNO`     INTEGER      NOT NULL, -- 년별번호
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `YPRICE`  INTEGER      NOT NULL, -- 가격
  `YTIME`   DATE         NOT NULL  -- 년단위
);

-- 월단위가격변동내역2
ALTER TABLE `YEAR_CHANGES`
  ADD CONSTRAINT `PK_YEAR_CHANGES` -- 월단위가격변동내역2 기본키
    PRIMARY KEY (
      `YNO` -- 년별번호
    );

ALTER TABLE `YEAR_CHANGES`
  MODIFY COLUMN `YNO` INTEGER NOT NULL AUTO_INCREMENT;

-- 상품 리스트
ALTER TABLE `USER_ITEM_LIST`
  ADD CONSTRAINT `FK_ITEMS_TO_USER_ITEM_LIST` -- 상품정보 -> 상품 리스트
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEMS` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 상품 리스트
ALTER TABLE `USER_ITEM_LIST`
  ADD CONSTRAINT `FK_USERS_TO_USER_ITEM_LIST` -- 회원정보 -> 상품 리스트
    FOREIGN KEY (
      `UNO` -- 회원번호
    )
    REFERENCES `USERS` ( -- 회원정보
      `UNO` -- 회원번호
    );

-- 시단위가격변동내역
ALTER TABLE `HOUR_CHANGES`
  ADD CONSTRAINT `FK_ITEMS_TO_HOUR_CHANGES` -- 상품정보 -> 시단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEMS` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 일단위가격변동내역
ALTER TABLE `DAY_CHANGES`
  ADD CONSTRAINT `FK_ITEMS_TO_DAY_CHANGES` -- 상품정보 -> 일단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEMS` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 주단위가격변동내역
ALTER TABLE `WEEK_CHANGES`
  ADD CONSTRAINT `FK_ITEMS_TO_WEEK_CHANGES` -- 상품정보 -> 주단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEMS` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 월단위가격변동내역
ALTER TABLE `MONTH_CHANGES`
  ADD CONSTRAINT `FK_ITEMS_TO_MONTH_CHANGES` -- 상품정보 -> 월단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEMS` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 월단위가격변동내역2
ALTER TABLE `YEAR_CHANGES`
  ADD CONSTRAINT `FK_ITEMS_TO_YEAR_CHANGES` -- 상품정보 -> 월단위가격변동내역2
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEMS` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );