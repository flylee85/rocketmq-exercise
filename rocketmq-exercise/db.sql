-- 交易表
CREATE TABLE IF NOT EXISTS trade_order (
  order_id VARCHAR(32) not null  COMMENT '订单id',
  user_id INT(11) null COMMENT '用户id',
  order_status char(1)  null COMMENT '订单状态 0未确认 1已确认 2已取消 3无效 4退货',
  pay_status char(1) null COMMENT '支付状态 0未付款 1支付中 2已付款',
  shipping_status char(1)  null COMMENT '收获状态 0未发货 1已发货 2已收获',
  address VARCHAR(255) null COMMENT '收货地址',
  consignee VARCHAR(255) null COMMENT '收货人',
  goods_id INT(11) null COMMENT '商品id',
  goods_number INT(11) null COMMENT '商品数量',
  goods_price decimal(10,2) null  COMMENT '商品价格',
  goods_amount decimal(10,2) null  COMMENT '商品总价',
  shipping_fee decimal(10,2) null COMMENT '运费',
  order_amount decimal(10,2) null COMMENT '订单价格',
  coupon_id VARCHAR(32) null COMMENT '优惠券id',
  coupon_paid decimal(10,2) null COMMENT '优惠券价格',
  money_paid decimal(10,2) null COMMENT '已付金额',
  pay_amount decimal(10,2) null COMMENT '支付金额',
  add_time datetime null COMMENT '创建时间',
  confirm_time datetime null COMMENT '订单确认时间',
  pay_time  datetime  null COMMENT '支付时间'
);

-- 用户表
CREATE TABLE IF NOT EXISTS trade_user (
  user_id INT not null AUTO_INCREMENT COMMENT '用户id',
  user_name varchar(255) null COMMENT '用户姓名',
  user_password varchar(255)  null COMMENT '用户密码',
  user_mobile varchar(255) null COMMENT '手机号',
  user_score INT  null COMMENT '积分',
  user_reg_time datetime null COMMENT '注册时间',
  user_money decimal(10,2) null COMMENT '用户余额',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4;

insert INTO trade_user(user_id, user_name, user_password, user_mobile,user_money) VALUES
  ('1','zhangsan','123456','13323452345','200.00');

-- 商品表
CREATE TABLE IF NOT EXISTS trade_goods (
  goods_id INT not null AUTO_INCREMENT COMMENT '商品id',
  goods_name varchar(255) null COMMENT '商品名称',
  goods_number INT(11)  null COMMENT '商品库存',
  goods_price decimal(10,2) null COMMENT '商品价格',
  goods_desc varchar(255)  null COMMENT '商品描述',
  add_time datetime null COMMENT '添加时间',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8mb4;

insert into trade_goods(goods_id, goods_name, goods_number, goods_price, goods_desc, add_time) VALUES
  ('10000','iphone7','10','5000.00','苹果手机7','2017-06-04 11:11:00');

-- 支付表
CREATE TABLE IF NOT EXISTS trade_pay (
  pay_id varchar(32) not null COMMENT '支付编号',
  order_id varchar(32) null COMMENT '订单编号',
  pay_amount decimal(10,2)  null COMMENT '支付金额',
  is_paid char(1) null COMMENT '是否已支付 0否 1是',
  PRIMARY KEY (`pay_id`));

-- 优惠券表
CREATE TABLE IF NOT EXISTS trade_coupon (
  coupon_id varchar(32) not null COMMENT '优惠券id',
  coupon_price decimal(10,2) null COMMENT '优惠券金额',
  user_id INT(11)  null COMMENT '用户ID',
  order_id varchar(32) null COMMENT '订单id',
  is_used char(1) null COMMENT '是否已使用 0否 1是',
  used_time datetime null COMMENT '使用时间',
  PRIMARY KEY (`coupon_id`));

insert INTO trade_coupon(coupon_id, coupon_price, user_id, order_id, is_used, used_time)
VALUES ('123456789','100.00','1','','0','2017-06-04 11:00:00');

-- 库存数量日志表
CREATE TABLE IF NOT EXISTS trade_goods_number_log (
  goods_id INT(11) not null COMMENT '商品id',
  order_id varchar(32) not null COMMENT '订单ID',
  goods_number INT(11)  null COMMENT '库存数量',
  log_time datetime null COMMENT '记录日志时间'
);

-- 库存数量日志表
CREATE TABLE IF NOT EXISTS trade_goods_number_log (
  goods_id INT(11) not null COMMENT '商品id',
  order_id varchar(32) not null COMMENT '订单ID',
  goods_number INT(11)  null COMMENT '库存数量',
  log_time datetime null COMMENT '记录日志时间',
  PRIMARY KEY (`goods_id`,`order_id`));

-- 用户扣款日志表
CREATE TABLE IF NOT EXISTS trade_user_money_log (
  user_id INT(11) not null COMMENT '用户id',
  order_id varchar(32) not null COMMENT '订单ID',
  money_log_type char(1)  null COMMENT '日志类型 1订单付款 2订单退款',
  user_money decimal(10,2) null COMMENT '金额',
  create_time datetime null COMMENT '日志时间'
);

-- mq消费消息去重表
create table IF NOT EXISTS trade_mq_consumer_log (
  gourp_name VARCHAR(255) NOT NULL COMMENT '消费组名',
  msg_tag  VARCHAR(255) NOT NULL COMMENT '消息tag',
  msg_keys VARCHAR(255) NOT NULL COMMENT '业务id',
  msg_id  VARCHAR(255) COMMENT '消息id',
  msg_body VARCHAR(1024) COMMENT '消息内容',
  consumer_status VARCHAR(1) COMMENT '消费状态 0-正在处理 1处理成功 2-处理失败',
  consumer_times  INT COMMENT '消费次数',
  remark  VARCHAR(255) COMMENT '备注（错误原因)',
  PRIMARY KEY (`gourp_name`,`msg_tag`, `msg_keys`)
);

-- 消息发送临时表
create table IF NOT EXISTS trade_mq_producer_temp (
  id VARCHAR(255) not null COMMENT '主键',
  group_name VARCHAR(255) not null COMMENT '生产者组名',
  msg_topic VARCHAR(255) not null COMMENT '消息主题',
  msg_tag VARCHAR(255) not null COMMENT '消费tag',
  msg_keys VARCHAR(255) not null COMMENT '消费keys',
  msg_body VARCHAR(255) null COMMENT '消息内容',
  create_time DATETIME null COMMENT '创建时间',
  PRIMARY KEY (`id`)
);
