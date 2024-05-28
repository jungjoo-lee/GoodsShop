CREATE TABLE `member` (
	`userid`	VARCHAR(50)	NOT NULL,
	`gseq`	INT	NOT NULL,
	`pwd`	VARCHAR(50)	NULL,
	`name`	VARCHAR(10)	NULL,
	`email`	VARCHAR(50)	NULL,
	`phone`	VARCHAR(15)	NULL,
	`zip_code`	VARCHAR(20)	NULL,
	`address`	VARCHAR(100)	NULL,
	`d_address`	VARCHAR(100)	NULL,
	`indate`	DATETIME	NULL,
	`last_login_time`	DATETIME	NULL,
	`is_login`	TINYINT	NULL
);

CREATE TABLE `qna` (
	`qseq`	INT	NOT NULL,
	`userid`	VARCHAR(50)	NOT NULL,
	`subject`	VARCHAR(100)	NULL,
	`content`	VARCHAR(1000)	NULL,
	`indate`	DATETIME	NULL,
	`reply`	VARCHAR(1000)	NULL,
	`replydate`	DATETIME	NULL
);

CREATE TABLE `review` (
	`rseq`	INT	NOT NULL,
	`userid`	VARCHAR(50)	NOT NULL,
	`gseq`	INT	NOT NULL,
	`subject`	VARCHAR(100)	NULL,
	`content`	VARCHAR(1000)	NULL,
	`indate`	DATETIME	NULL
);

CREATE TABLE `admin` (
	`adminid`	VARCHAR(50)	NOT NULL,
	`pwd`	VARCHAR(50)	NULL,
	`name`	VARCHAR(10)	NULL,
	`phone`	VARCHAR(15)	NULL,
	`email`	VARCHAR(50)	NULL
);

CREATE TABLE `notice` (
	`nseq`	INT	NOT NULL,
	`adminid`	VARCHAR(50)	NOT NULL,
	`subject`	VARCHAR(100)	NULL,
	`content`	VARCHAR(1000)	NULL,
	`indate`	DATETIME	NULL
);

CREATE TABLE `grade` (
	`gseq`	INT	NOT NULL,
	`gname`	VARCHAR(20)	NULL,
	`sale`	INT	NULL
);

CREATE TABLE `cart` (
	`cseq`	INT	NOT NULL,
	`userid`	VARCHAR(50)	NOT NULL,
	`gseq`	INT	NOT NULL,
	`quantity`	INT	NULL,
	`indate`	DATETIME	NULL,
	`totalprice`	INT	NULL
);

CREATE TABLE `goods` (
	`gseq`	INT	NOT NULL,
	`gname`	VARCHAR(50)	NULL,
	`category`	VARCHAR(30)	NULL,
	`o_price`	INT	NULL,
	`s_price`	INT	NULL,
	`m_price`	INT	NULL,
	`content`	VARCHAR(1000)	NULL,
	`bestyn`	TINYINT	NULL,
	`useyn`	TINYINT	NULL,
	`indate`	DATETIME	NULL
);

CREATE TABLE `goodsimage` (
	`giseq`	INT	NOT NULL,
	`gseq`	INT	NOT NULL,
	`oriname`	VARCHAR(100)	NULL,
	`realname`	VARCHAR(50)	NULL,
	`filesize`	BIGINT	NULL
);

CREATE TABLE `orders` (
	`oseq`	INT	NOT NULL,
	`userid`	VARCHAR(50)	NOT NULL,
	`indate`	DATETIME	NULL
);

CREATE TABLE `order_detail` (
	`odseq`	INT	NOT NULL,
	`oseq`	INT	NOT NULL,
	`osseq`	INT	NOT NULL,
	`gseq`	INT	NOT NULL,
	`quantity`	INT	NULL,
	`totalprice`	INT	NULL
);

CREATE TABLE `order_status` (
	`osseq`	INT	NOT NULL,
	`status`	VARCHAR(20)	NULL
);

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`userid`,
	`gseq`
);

ALTER TABLE `qna` ADD CONSTRAINT `PK_QNA` PRIMARY KEY (
	`qseq`,
	`userid`
);

ALTER TABLE `review` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
	`rseq`,
	`userid`,
	`gseq`
);

ALTER TABLE `admin` ADD CONSTRAINT `PK_ADMIN` PRIMARY KEY (
	`adminid`
);

ALTER TABLE `notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
	`nseq`,
	`adminid`
);

ALTER TABLE `grade` ADD CONSTRAINT `PK_GRADE` PRIMARY KEY (
	`gseq`
);

ALTER TABLE `cart` ADD CONSTRAINT `PK_CART` PRIMARY KEY (
	`cseq`,
	`userid`,
	`gseq`
);

ALTER TABLE `goods` ADD CONSTRAINT `PK_GOODS` PRIMARY KEY (
	`gseq`
);

ALTER TABLE `goodsimage` ADD CONSTRAINT `PK_GOODSIMAGE` PRIMARY KEY (
	`giseq`,
	`gseq`
);

ALTER TABLE `orders` ADD CONSTRAINT `PK_ORDERS` PRIMARY KEY (
	`oseq`,
	`userid`
);

ALTER TABLE `order_detail` ADD CONSTRAINT `PK_ORDER_DETAIL` PRIMARY KEY (
	`odseq`,
	`oseq`,
	`osseq`,
	`gseq`
);

ALTER TABLE `order_status` ADD CONSTRAINT `PK_ORDER_STATUS` PRIMARY KEY (
	`osseq`
);

ALTER TABLE `member` ADD CONSTRAINT `FK_grade_TO_member_1` FOREIGN KEY (
	`gseq`
)
REFERENCES `grade` (
	`gseq`
);

ALTER TABLE `qna` ADD CONSTRAINT `FK_member_TO_qna_1` FOREIGN KEY (
	`userid`
)
REFERENCES `member` (
	`userid`
);

ALTER TABLE `review` ADD CONSTRAINT `FK_member_TO_review_1` FOREIGN KEY (
	`userid`
)
REFERENCES `member` (
	`userid`
);

ALTER TABLE `review` ADD CONSTRAINT `FK_goods_TO_review_1` FOREIGN KEY (
	`gseq`
)
REFERENCES `goods` (
	`gseq`
);

ALTER TABLE `notice` ADD CONSTRAINT `FK_admin_TO_notice_1` FOREIGN KEY (
	`adminid`
)
REFERENCES `admin` (
	`adminid`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_member_TO_cart_1` FOREIGN KEY (
	`userid`
)
REFERENCES `member` (
	`userid`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_goods_TO_cart_1` FOREIGN KEY (
	`gseq`
)
REFERENCES `goods` (
	`gseq`
);

ALTER TABLE `goodsimage` ADD CONSTRAINT `FK_goods_TO_goodsimage_1` FOREIGN KEY (
	`gseq`
)
REFERENCES `goods` (
	`gseq`
);

ALTER TABLE `orders` ADD CONSTRAINT `FK_member_TO_orders_1` FOREIGN KEY (
	`userid`
)
REFERENCES `member` (
	`userid`
);

ALTER TABLE `order_detail` ADD CONSTRAINT `FK_orders_TO_order_detail_1` FOREIGN KEY (
	`oseq`
)
REFERENCES `orders` (
	`oseq`
);

ALTER TABLE `order_detail` ADD CONSTRAINT `FK_order_status_TO_order_detail_1` FOREIGN KEY (
	`osseq`
)
REFERENCES `order_status` (
	`osseq`
);

ALTER TABLE `order_detail` ADD CONSTRAINT `FK_goods_TO_order_detail_1` FOREIGN KEY (
	`gseq`
)
REFERENCES `goods` (
	`gseq`
);

