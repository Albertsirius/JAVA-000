#用户表
CREATE TABLE `USER` (
    `ID`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(30) NOT NULL COMMENT '用户名',
    `PASSWORD` VARCHAR(64) NOT NULL COMMENT '密码',
    `STATUS` CHAR(1) DEFAULT 'E' COMMENT '用户状态: E有效,F冻结,D注销,O其他',
    `CREATE_TIME` BIGINT NOT NULL COMMENT '创建时间',
    `UPDATE_TIME` BIGINT NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

#商品表
CREATE TABLE  `GOODS` (
    `ID`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(30) NOT NULL COMMENT '商品名称',
    `DESCRIPTION` VARCHAR(400) COMMENT '商品描述',
    `STATUS` CHAR(1) DEFAULT '0' COMMENT '商品状态: 0未上架,1上架,2下架',
    `PRICE` DECIMAL(10,2) NOT NULL COMMENT '商品价格',
    `CCY` VARCHAR(3) NOT NULL COMMENT '币种',
    `INVENTORY` INT UNSIGNED NOT NULL COMMENT '库存量',
    `CREATE_TIME` BIGINT NOT NULL COMMENT '创建时间',
    `UPDATE_TIME` BIGINT NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

#订单表
CREATE TABLE `ORDER` (
    `ID`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `USER_ID` INT UNSIGNED NOT NULL COMMENT '创建用户ID',
    `STATUS` CHAR(1) DEFAULT '0' COMMENT '订单状态: 0初始,1已下单,2已支付,3商家接受订单,4已发货,5已收货,6已取消,7申请退货,8已退货',
    `TOTAL_AMOUNT` DECIMAL(13,2) NOT NULL COMMENT '订单总价',
    `CCY` VARCHAR(3) NOT NULL COMMENT '币种',
    `PAY_TIME` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
    `PAY_TYPE` VARCHAR(10) COMMENT '支付方式',
    `REFUND_TIME` BIGINT COMMENT '退货时间',
    `CREATE_TIME` BIGINT NOT NULL COMMENT '创建时间',
    `UPDATE_TIME` BIGINT NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

#订单商品明细表
CREATE TABLE `ORDER_DETAIL` (
    `ORDER_ID` INT UNSIGNED NOT NULL COMMENT '订单ID',
    `GOODS_ID` INT UNSIGNED NOT NULL COMMENT '商品ID',
    `PRICE` DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    `AMOUNT` INT NOT NULL COMMENT '商品数量',
    PRIMARY KEY `idx_order_goods` (`ORDER_ID`, `GOODS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品明细表';
