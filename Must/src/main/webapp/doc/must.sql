-- 회원정보
DROP TABLE IF EXISTS `USER` RESTRICT;

-- 상품정보
DROP TABLE IF EXISTS `ITEM` RESTRICT;

-- 상품 리스트
DROP TABLE IF EXISTS `USER_ITEM_LIST` RESTRICT;

-- 가격변동내역
DROP TABLE IF EXISTS `PRICE_CHANGES` RESTRICT;

-- 회원정보
CREATE TABLE `USER` (
  `UNO`    INTEGER     NOT NULL, -- 회원번호
  `UEMAIL` VARCHAR(40) NULL,     -- 이메일
  `UPW`    VARCHAR(20) NULL      -- 비밀번호
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

-- 상품정보
CREATE TABLE `ITEM` (
  `PROD_ID`   INTEGER      NOT NULL, -- 상품코드
  `IMAGE_URL` VARCHAR(255) NULL,     -- 이미지경로
  `MIN_PRICE` INTEGER      NULL,     -- 최소가
  `AVG_PRICE` INTEGER      NULL,     -- 평균가
  `SHOT`      INTEGER      NULL,     -- 단종여부
  `INPUT_D`   DATE         NULL,     -- 등록일
  `DANAWAURL` VARCHAR(255) NULL      -- 상품페이지 경로
);

-- 상품정보
ALTER TABLE `ITEM`
  ADD CONSTRAINT `PK_ITEM` -- 상품정보 기본키
    PRIMARY KEY (
      `PROD_ID` -- 상품코드
    );

-- 상품 리스트
CREATE TABLE `USER_ITEM_LIST` (
  `PROD_ID`    INTEGER NOT NULL, -- 상품코드
  `UNO`        INTEGER NOT NULL, -- 회원번호
  `WISH_PRICE` INTEGER NULL,     -- 희망가격
  `REG_DATE`   DATE    NULL      -- 등록일
);

-- 상품 리스트
ALTER TABLE `USER_ITEM_LIST`
  ADD CONSTRAINT `PK_USER_ITEM_LIST` -- 상품 리스트 기본키
    PRIMARY KEY (
      `PROD_ID`, -- 상품코드
      `UNO`      -- 회원번호
    );

-- 가격변동내역
CREATE TABLE `PRICE_CHANGES` (
  `COL`     INTEGER NOT NULL, -- 일련번호
  `PROD_ID` INTEGER NULL,     -- 상품코드
  `PRICE`   INTEGER NULL,     -- 가격
  `STIME`   DATE    NULL      -- 조사시각
);

-- 가격변동내역
ALTER TABLE `PRICE_CHANGES`
  ADD CONSTRAINT `PK_PRICE_CHANGES` -- 가격변동내역 기본키
    PRIMARY KEY (
      `COL` -- 일련번호
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

-- 가격변동내역
ALTER TABLE `PRICE_CHANGES`
  ADD CONSTRAINT `FK_ITEM_TO_PRICE_CHANGES` -- 상품정보 -> 가격변동내역
    FOREIGN KEY (
      `PROD_ID` -- 상품코드
    )
    REFERENCES `ITEM` ( -- 상품정보
      `PROD_ID` -- 상품코드
    );