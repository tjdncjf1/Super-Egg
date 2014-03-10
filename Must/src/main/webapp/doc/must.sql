-- 회원정보
DROP TABLE IF EXISTS `USER` RESTRICT;

-- 상품정보
DROP TABLE IF EXISTS `ITEM` RESTRICT;

-- 상품 리스트
DROP TABLE IF EXISTS `USER_ITEM_LIST` RESTRICT;

-- 시단위가격변동내역
DROP TABLE IF EXISTS `HOUR_CHANGES` RESTRICT;

-- 일단위가격변동내역
DROP TABLE IF EXISTS `DAY_CHANGES` RESTRICT;

-- 월단위가격변동내역
DROP TABLE IF EXISTS `MONTH_CHANGES` RESTRICT;

-- 년단위가격변동내역
DROP TABLE IF EXISTS `YEAR_CHANGES` RESTRICT;

-- 회원정보
CREATE TABLE `USER` (
  `UNO`    INTEGER      NOT NULL, -- 회원번호
  `UEMAIL` VARCHAR(100) NOT NULL, -- 이메일
  `UPW`    VARCHAR(20)  NOT NULL  -- 비밀번호
);

-- 회원정보
ALTER TABLE `USER`
  ADD CONSTRAINT `PK_USER` -- 회원정보 기본키
    PRIMARY KEY (
      `UNO` -- 회원번호
    );

-- 회원정보 유니크 인덱스
CREATE UNIQUE INDEX `UIX_USER`
  ON `USER` ( -- 회원정보
    `UEMAIL` ASC -- 이메일
  );

ALTER TABLE `USER`
  MODIFY COLUMN `UNO` INTEGER NOT NULL AUTO_INCREMENT;

-- 상품정보
CREATE TABLE `ITEM` (
  `PROD_ID`    VARCHAR(100) NOT NULL, -- 상품코드
  `TITLE`      VARCHAR(200) NOT NULL, -- 상품명
  `IMAGE_URL`  VARCHAR(255) NULL,     -- 이미지경로
  `MIN_PRICE`  INTEGER      NOT NULL, -- 현재최소가
  `WISH_PRICE` INTEGER      NOT NULL, -- 희망가
  `REG_DATE`   DATETIME     NOT NULL, -- 등록일
  `LINK`       VARCHAR(255) NOT NULL  -- 상품페이지 경로
);

-- 상품정보
ALTER TABLE `ITEM`
  ADD CONSTRAINT `PK_ITEM` -- 상품정보 기본키
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
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `HPRICE`  INTEGER      NOT NULL, -- 가격
  `HTIME`   DATETIME     NOT NULL  -- 시단위
);

-- 시단위가격변동내역
ALTER TABLE `HOUR_CHANGES`
  ADD CONSTRAINT `PK_HOUR_CHANGES` -- 시단위가격변동내역 기본키
    PRIMARY KEY (
      `PROD_ID` -- 상품코드
    );

-- 일단위가격변동내역
CREATE TABLE `DAY_CHANGES` (
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `DPRICE`  INTEGER      NOT NULL, -- 가격
  `DTIME`   DATETIME     NOT NULL  -- 일단위
);

-- 일단위가격변동내역
ALTER TABLE `DAY_CHANGES`
  ADD CONSTRAINT `PK_DAY_CHANGES` -- 일단위가격변동내역 기본키
    PRIMARY KEY (
      `PROD_ID` -- 상품코드
    );

-- 월단위가격변동내역
CREATE TABLE `MONTH_CHANGES` (
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `MPRICE`  INTEGER      NOT NULL, -- 가격
  `MTIME`   DATETIME     NOT NULL  -- 월단위
);

-- 월단위가격변동내역
ALTER TABLE `MONTH_CHANGES`
  ADD CONSTRAINT `PK_MONTH_CHANGES` -- 월단위가격변동내역 기본키
    PRIMARY KEY (
      `PROD_ID` -- 상품코드
    );

-- 년단위가격변동내역
CREATE TABLE `YEAR_CHANGES` (
  `PROD_ID` VARCHAR(100) NOT NULL, -- 상품코드
  `YPRICE`  INTEGER      NOT NULL, -- 가격
  `YTIME`   DATETIME     NOT NULL  -- 년단위
);

-- 년단위가격변동내역
ALTER TABLE `YEAR_CHANGES`
  ADD CONSTRAINT `PK_YEAR_CHANGES` -- 년단위가격변동내역 기본키
    PRIMARY KEY (
      `PROD_ID` -- 상품코드
    );

-- 상품 리스트
ALTER TABLE `USER_ITEM_LIST`
  ADD CONSTRAINT `FK_ITEM_TO_USER_ITEM_LIST` -- 상품정보 -> 상품 리스트
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEM` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 상품 리스트
ALTER TABLE `USER_ITEM_LIST`
  ADD CONSTRAINT `FK_USER_TO_USER_ITEM_LIST` -- 회원정보 -> 상품 리스트
    FOREIGN KEY (
      `UNO` -- 회원번호
    )
    REFERENCES `USER` ( -- 회원정보
      `UNO` -- 회원번호
    );

-- 시단위가격변동내역
ALTER TABLE `HOUR_CHANGES`
  ADD CONSTRAINT `FK_ITEM_TO_HOUR_CHANGES` -- 상품정보 -> 시단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEM` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 일단위가격변동내역
ALTER TABLE `DAY_CHANGES`
  ADD CONSTRAINT `FK_ITEM_TO_DAY_CHANGES` -- 상품정보 -> 일단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEM` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 월단위가격변동내역
ALTER TABLE `MONTH_CHANGES`
  ADD CONSTRAINT `FK_ITEM_TO_MONTH_CHANGES` -- 상품정보 -> 월단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEM` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );

-- 년단위가격변동내역
ALTER TABLE `YEAR_CHANGES`
  ADD CONSTRAINT `FK_ITEM_TO_YEAR_CHANGES` -- 상품정보 -> 년단위가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEM` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );