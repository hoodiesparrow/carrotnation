use ssafyspecial;


-- truncate table exception_keyword;
-- truncate table require_keyword;
-- truncate table query_exception_keyword;
-- truncate table product_sell_article_similer;
-- truncate table date_price;
-- delete from product_sell_list;
-- delete from product;
-- delete from product_query;


-- 초기 삽입데이터
insert into product_query values("갤럭시 노트 10");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("필름", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("커버", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("유리", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("교신", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("교환", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("업체", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("구매", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("펜", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("매입", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("워치", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("추천", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("case", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("pen", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("팬", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "갤럭시 노트 10");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "갤럭시 노트 10");


insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(31,0,0,0,"갤럭시 노트10",0,"갤럭시 노트 10");

insert into exception_keyword(keyword,market,product_id) values("+","common",31);
insert into exception_keyword(keyword,market,product_id) values("플러스","common",31);
insert into exception_keyword(keyword,market,product_id) values("lite","common",31);
insert into exception_keyword(keyword,market,product_id) values("라이트","common",31);
insert into exception_keyword(keyword,market,product_id) values("10.1","common",31);
insert into exception_keyword(keyword,market,product_id) values("512","common",31);

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(32,0,0,0,"갤럭시 노트10+ 256g",0,"갤럭시 노트 10");

insert into exception_keyword(keyword,market,product_id) values("lite","common",32);
insert into exception_keyword(keyword,market,product_id) values("라이트","common",32);
insert into exception_keyword(keyword,market,product_id) values("10.1","common",32);
insert into exception_keyword(keyword,market,product_id) values("512","common",32);

insert into require_keyword (keyword,market,product_id) values("+,플러스","common",32);
insert into require_keyword (keyword,market,product_id) values("256","common",32);

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(33,0,0,0,"갤럭시 노트10+ 512g",0,"갤럭시 노트 10");

insert into exception_keyword(keyword,market,product_id) values("lite","common",33);
insert into exception_keyword(keyword,market,product_id) values("라이트","common",33);
insert into exception_keyword(keyword,market,product_id) values("10.1","common",33);
insert into exception_keyword(keyword,market,product_id) values("256","common",33);

insert into require_keyword (keyword,market,product_id) values("+,플러스","common",33);
insert into require_keyword (keyword,market,product_id) values("512","common",33);

insert into product_query values("갤럭시 노트 20");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("필름", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("커버", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("유리", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("교신", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("교환", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("업체", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("구매", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("펜", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("매입", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("워치", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("추천", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("case", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("pen", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("팬", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "갤럭시 노트 20");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "갤럭시 노트 20");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(34,0,0,0,"갤럭시 노트20",0,"갤럭시 노트 20");

insert into exception_keyword(keyword,market,product_id) values("ultra","common",34);
insert into exception_keyword(keyword,market,product_id) values("울트라","common",34);

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(35,0,0,0,"갤럭시 노트20 ultra",0,"갤럭시 노트 20");

insert into require_keyword (keyword,market,product_id) values("ultra,울트라","common",35);

insert into product_query values("S10");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "S10");
insert into query_exception_keyword(keyword, `query`) values ("필름", "S10");
insert into query_exception_keyword(keyword, `query`) values ("커버", "S10");
insert into query_exception_keyword(keyword, `query`) values ("유리", "S10");
insert into query_exception_keyword(keyword, `query`) values ("교신", "S10");
insert into query_exception_keyword(keyword, `query`) values ("교환", "S10");
insert into query_exception_keyword(keyword, `query`) values ("업체", "S10");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "S10");
insert into query_exception_keyword(keyword, `query`) values ("구매", "S10");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "S10");
insert into query_exception_keyword(keyword, `query`) values ("펜", "S10");
insert into query_exception_keyword(keyword, `query`) values ("매입", "S10");
insert into query_exception_keyword(keyword, `query`) values ("워치", "S10");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "S10");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "S10");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "S10");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "S10");
insert into query_exception_keyword(keyword, `query`) values ("추천", "S10");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "S10");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "S10");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "S10");
insert into query_exception_keyword(keyword, `query`) values ("case", "S10");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "S10");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "S10");
insert into query_exception_keyword(keyword, `query`) values ("pen", "S10");
insert into query_exception_keyword(keyword, `query`) values ("팬", "S10");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "S10");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "S10");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "S10");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "S10");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "S10");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "S10");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "S10");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "S10");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "S10");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(1,0,0,0,"갤럭시 S10e 128G",0,"S10");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(2,0,0,0,"갤럭시 S10 128G",0,"S10");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(3,0,0,0,"갤럭시 S10 512G",0,"S10");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(4,0,0,0,"갤럭시 S10+ 128G",0,"S10");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(5,0,0,0,"갤럭시 S10+ 512G",0,"S10");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(6,0,0,0,"갤럭시 S10 5G 256G",0,"S10");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(7,0,0,0,"갤럭시 S10 5G 512G",0,"S10");

insert into require_keyword (`keyword`, `market`, `product_id`)values("s10e,10e","common",1);
insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",1);

insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",2);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",3);

insert into require_keyword (`keyword`, `market`, `product_id`)values("플러스,+,플,plus","common",4);
insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",4);

insert into require_keyword (`keyword`, `market`, `product_id`)values("플러스,+,플,plus","common",5);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",5);

insert into require_keyword (`keyword`, `market`, `product_id`)values("5g","common",6);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",6);

insert into require_keyword (`keyword`, `market`, `product_id`)values("5g","common",7);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",7);

insert into require_keyword (keyword,market,product_id) values("s10e","daangn",1);
insert into require_keyword (keyword,market,product_id) values("s10","daangn",2);
insert into require_keyword (keyword,market,product_id) values("s10","daangn",3);
insert into require_keyword (keyword,market,product_id) values("s10","daangn",4);
insert into require_keyword (keyword,market,product_id) values("s10","daangn",5);
insert into require_keyword (keyword,market,product_id) values("s10","daangn",6);
insert into require_keyword (keyword,market,product_id) values("s10","daangn",7);


INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('5g', 'common', '1');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '1');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '1');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '1');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '1');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '1');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '1');


INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('5g', 'common', '2');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '2');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '2');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '2');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '2');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('10e', 'common', '2');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '2');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '2');

INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('5g', 'common', '3');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '3');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '3');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '3');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '3');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('10e', 'common', '3');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '3');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '3');

INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('5g', 'common', '4');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('10e', 'common', '4');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '4');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '4');

INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('5g', 'common', '5');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('10e', 'common', '5');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '5');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '5');


INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '6');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '6');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '6');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '6');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('10e', 'common', '6');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '6');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '6');

INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '7');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '7');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '7');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '7');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('10e', 'common', '7');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '7');
INSERT INTO `ssafyspecial`.`exception_keyword` (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '7');

insert into product_query values("S20");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "S20");
insert into query_exception_keyword(keyword, `query`) values ("필름", "S20");
insert into query_exception_keyword(keyword, `query`) values ("커버", "S20");
insert into query_exception_keyword(keyword, `query`) values ("유리", "S20");
insert into query_exception_keyword(keyword, `query`) values ("교신", "S20");
insert into query_exception_keyword(keyword, `query`) values ("교환", "S20");
insert into query_exception_keyword(keyword, `query`) values ("업체", "S20");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "S20");
insert into query_exception_keyword(keyword, `query`) values ("구매", "S20");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "S20");
insert into query_exception_keyword(keyword, `query`) values ("펜", "S20");
insert into query_exception_keyword(keyword, `query`) values ("매입", "S20");
insert into query_exception_keyword(keyword, `query`) values ("워치", "S20");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "S20");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "S20");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "S20");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "S20");
insert into query_exception_keyword(keyword, `query`) values ("추천", "S20");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "S20");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "S20");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "S20");
insert into query_exception_keyword(keyword, `query`) values ("case", "S20");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "S20");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "S20");
insert into query_exception_keyword(keyword, `query`) values ("pen", "S20");
insert into query_exception_keyword(keyword, `query`) values ("팬", "S20");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "S20");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "S20");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "S20");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "S20");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "S20");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "S20");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "S20");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "S20");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "S20");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(8,0,0,0,"갤럭시 S20 128G",0,"S20");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(9,0,0,0,"갤럭시 S20+ 256G",0,"S20");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(10,0,0,0,"갤럭시 S20 울트라 256G",0,"S20");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(11,0,0,0,"갤럭시 S20 울트라 512G",0,"S20");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(12,0,0,0,"갤럭시 S20 FE 128G",0,"S20");

insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",8);

insert into require_keyword (`keyword`, `market`, `product_id`)values("플러스,+,플,plus","common",9);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",9);

insert into require_keyword (`keyword`, `market`, `product_id`)values("울트라,ultra","common",10);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",10);

insert into require_keyword (`keyword`, `market`, `product_id`)values("울트라,ultra","common",11);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",11);

insert into require_keyword (`keyword`, `market`, `product_id`)values("fe","common",12);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",12);

insert into require_keyword (keyword,market,product_id) values("s20","daangn",8);
insert into require_keyword (keyword,market,product_id) values("s20","daangn",9);
insert into require_keyword (keyword,market,product_id) values("s20","daangn",10);
insert into require_keyword (keyword,market,product_id) values("s20","daangn",11);
insert into require_keyword (keyword,market,product_id) values("s20","daangn",12);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('울트라', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('ultra', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('fe', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '8');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '8');


INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('울트라', 'common', '9');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('ultra', 'common', '9');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('fe', 'common', '9');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '9');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '9');

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '10');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '10');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '10');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '10');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('fe', 'common', '10');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '10');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '10');

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '11');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '11');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '11');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '11');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('fe', 'common', '11');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '11');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '11');

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '12');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '12');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '12');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '12');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('울트라', 'common', '12');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('ultra', 'common', '12');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '12');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '12');



insert into product_query values("S21");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "S21");
insert into query_exception_keyword(keyword, `query`) values ("필름", "S21");
insert into query_exception_keyword(keyword, `query`) values ("커버", "S21");
insert into query_exception_keyword(keyword, `query`) values ("유리", "S21");
insert into query_exception_keyword(keyword, `query`) values ("교신", "S21");
insert into query_exception_keyword(keyword, `query`) values ("교환", "S21");
insert into query_exception_keyword(keyword, `query`) values ("업체", "S21");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "S21");
insert into query_exception_keyword(keyword, `query`) values ("구매", "S21");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "S21");
insert into query_exception_keyword(keyword, `query`) values ("펜", "S21");
insert into query_exception_keyword(keyword, `query`) values ("매입", "S21");
insert into query_exception_keyword(keyword, `query`) values ("워치", "S21");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "S21");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "S21");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "S21");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "S21");
insert into query_exception_keyword(keyword, `query`) values ("추천", "S21");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "S21");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "S21");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "S21");
insert into query_exception_keyword(keyword, `query`) values ("case", "S21");
insert into query_exception_keyword(keyword, `query`) values ("악세사리","S21" );
insert into query_exception_keyword(keyword, `query`) values ("스티커", "S21");
insert into query_exception_keyword(keyword, `query`) values ("pen", "S21");
insert into query_exception_keyword(keyword, `query`) values ("팬", "S21");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "S21");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "S21");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "S21");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "S21");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "S21");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "S21");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "S21");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "S21");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "S21");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(13,0,0,0,"갤럭시 S21 256G",0,"S21");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(14,0,0,0,"갤럭시 S21+ 256G",0,"S21");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(15,0,0,0,"갤럭시 S21 울트라 256G",0,"S21");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(16,0,0,0,"갤럭시 S21 울트라 512G",0,"S21");

insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",13);

insert into require_keyword (`keyword`, `market`, `product_id`)values("플러스,+,플,plus","common",14);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",14);

insert into require_keyword (`keyword`, `market`, `product_id`)values("울트라,ultra","common",15);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",15);

insert into require_keyword (`keyword`, `market`, `product_id`)values("울트라,ultra","common",16);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",16);


insert into require_keyword (keyword,market,product_id) values("s21","daangn",13);
insert into require_keyword (keyword,market,product_id) values("s21","daangn",14);
insert into require_keyword (keyword,market,product_id) values("s21","daangn",15);
insert into require_keyword (keyword,market,product_id) values("s21","daangn",16);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '13');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '13');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '13');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '13');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('울트라', 'common', '13');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('ultra', 'common', '13');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '13');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '13');

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('울트라', 'common', '14');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('ultra', 'common', '14');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '14');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '14');

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '15');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '15');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '15');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '15');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '15');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '15');

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플러스', 'common', '16');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('+', 'common', '16');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('플', 'common', '16');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('plus', 'common', '16');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '16');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '16');

insert into product_query values("갤럭시폴드");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("필름", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("커버", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("유리", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("교신", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("교환", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("업체", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("구매", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("펜", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("워치", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("매입", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("추천", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("case", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("매입", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("박스", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("pen", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "갤럭시폴드");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "갤럭시폴드");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(51,0,0,0,"갤럭시폴드",0,"갤럭시폴드");

insert into exception_keyword(keyword,market,product_id) values("폴드2","common",51);
insert into exception_keyword(keyword,market,product_id) values("폴드3","common",51);
insert into exception_keyword(keyword,market,product_id) values("폴드 2","common",51);
insert into exception_keyword(keyword,market,product_id) values("폴드 3","common",51);
insert into exception_keyword(keyword,market,product_id) values("버즈","daangn",51);
insert into exception_keyword(keyword,market,product_id) values("아이폰","daangn",51);
insert into exception_keyword(keyword,market,product_id) values("차량용","daangn",51);




insert into require_keyword (keyword,market,product_id) values("폴드","daangn",51);

insert into product_query values("Z플립");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("필름", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("커버", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("유리", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("교신", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("교환", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("업체", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("구매", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("펜", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("워치", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("매입", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("추천", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("case", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("매입", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("박스", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("pen", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "Z플립");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "Z플립");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(52,0,0,0,"갤럭시 Z플립",0,"Z플립");

insert into exception_keyword(keyword,market,product_id) values("Z플립3","common",52);
insert into exception_keyword(keyword,market,product_id) values("플립3","common",52);

insert into require_keyword (keyword,market,product_id) values("z플립","daangn",52);


insert into product_query values("폴드2");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("필름", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("커버", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("유리", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("교신", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("교환", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("업체", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("구매", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("펜", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("워치", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("매입", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("추천", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("case", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("매입", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("박스", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("pen", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "폴드2");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "폴드2");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(53,0,0,0,"갤럭시 폴드2 256g",0,"폴드2");

insert into exception_keyword(keyword,market,product_id) values("512","common",53);
insert into require_keyword (keyword,market,product_id) values("256","common",53);
insert into require_keyword (keyword,market,product_id) values("폴드2","daangn",53);

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(54,0,0,0,"갤럭시 폴드2 512g",0,"폴드2");

insert into exception_keyword(keyword,market,product_id) values("256","common",54);
insert into require_keyword (keyword,market,product_id) values("512","common",54);
insert into require_keyword (keyword,market,product_id) values("폴드2","daangn",54);

insert into product_query values("폴드3");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("필름", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("커버", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("유리", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("교신", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("교환", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("업체", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("구매", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("펜", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("워치", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("매입", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("추천", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("case", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("매입", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("박스", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("pen", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "폴드3");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "폴드3");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(55,0,0,0,"갤럭시 폴드3 256g",0,"폴드3");

insert into exception_keyword(keyword,market,product_id) values("512","common",55);
insert into require_keyword (keyword,market,product_id) values("256,","common",55);
insert into require_keyword (keyword,market,product_id) values("폴드3","daangn",55);

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(56,0,0,0,"갤럭시 폴드3 512g",0,"폴드3");

insert into exception_keyword(keyword,market,product_id) values("256","common",56);
insert into require_keyword (keyword,market,product_id) values("512,","common",56);
insert into require_keyword (keyword,market,product_id) values("폴드3","daangn",56);


insert into product_query values("플립3");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("필름", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("커버", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("유리", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("교신", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("교환", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("업체", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("구매", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("펜", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("워치", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("매입", "플립3");

insert into query_exception_keyword(keyword, `query`) values ("프로모션", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("추천", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("case", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("매입", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("박스", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("pen", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("조던 ", "플립3"); 
insert into query_exception_keyword(keyword, `query`) values ("아수스 ", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("나이키", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("블라우스", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("JBL", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("플립북", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("닥스 ", "플립3");
insert into query_exception_keyword(keyword, `query`) values ("헤지스 ", "플립3");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(57,0,0,0,"갤럭시 플립3",0,"플립3");

insert into require_keyword (keyword,market,product_id) values("플립3","daangn",57);

insert into product_query values("아이폰X");
insert into product_query values("아이폰XS");
insert into product_query values("아이폰XR");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("필름", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("커버", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("유리", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("교신", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("교환", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("업체", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("구매", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("펜", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("워치", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("추천", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("case", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("pen", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("팬", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "아이폰X");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "아이폰X");


insert into query_exception_keyword(keyword, `query`) values ("케이스", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("필름", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("커버", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("유리", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("교신", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("교환", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("업체", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("구매", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("펜", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("워치", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("추천", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("case", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("pen", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("팬", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "아이폰XS");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "아이폰XS");


insert into query_exception_keyword(keyword, `query`) values ("케이스", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("필름", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("커버", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("유리", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("교신", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("교환", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("업체", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("구매", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("펜", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("워치", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("추천", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("case", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("pen", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("팬", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "아이폰XR");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "아이폰XR");

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(70,0,0,0,"아이폰X 64G",0,"아이폰X");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(71,0,0,0,"아이폰X 256G",0,"아이폰X");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(72,0,0,0,"아이폰XS 64G",0,"아이폰XS");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(73,0,0,0,"아이폰XS 256G",0,"아이폰XS");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(74,0,0,0,"아이폰XS 512G",0,"아이폰XS");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(75,0,0,0,"아이폰XS Max 64G",0,"아이폰XS");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(76,0,0,0,"아이폰XS Max 256G",0,"아이폰XS");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(77,0,0,0,"아이폰XS Max 512G",0,"아이폰XS");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(78,0,0,0,"아이폰XR 64G",0,"아이폰XR");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(79,0,0,0,"아이폰XR 128G",0,"아이폰XR");
insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(80,0,0,0,"아이폰XR 256G",0,"아이폰XR");

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xs', 'common', '70');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '70');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '70');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '70');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '70');
insert into require_keyword (`keyword`, `market`, `product_id`)values("64","common",70);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xs', 'common', '71');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '71');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '71');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '71');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '71');
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",71);


INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '72');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '72');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '72');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '72');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '72');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xs","common",72);
insert into require_keyword (`keyword`, `market`, `product_id`)values("64","common",72);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '73');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '73');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '73');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '73');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '73');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xs","common",73);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",73);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '74');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '74');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '74');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '74');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '74');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xs","common",74);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",74);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '75');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '75');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '75');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '75');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xs","common",75);
insert into require_keyword (`keyword`, `market`, `product_id`)values("max","common",75);
insert into require_keyword (`keyword`, `market`, `product_id`)values("64","common",75);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '76');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '76');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '76');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '76');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xs","common",76);
insert into require_keyword (`keyword`, `market`, `product_id`)values("max","common",76);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",76);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xr', 'common', '77');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '77');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '77');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '77');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xs","common",77);
insert into require_keyword (`keyword`, `market`, `product_id`)values("max","common",77);
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",77);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xs', 'common', '78');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '78');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '78');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '78');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xr","common",78);
insert into require_keyword (`keyword`, `market`, `product_id`)values("64","common",78);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xs', 'common', '79');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '79');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '79');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '79');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xr","common",79);
insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",79);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('xs', 'common', '80');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '80');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '80');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '80');
insert into require_keyword (`keyword`, `market`, `product_id`)values("xr","common",80);
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",80);

insert into product_query values("아이폰11");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("필름", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("커버", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("유리", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("교신", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("교환", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("업체", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("구매", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("워치", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("펜", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("추천", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("case", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("박스", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("pen", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "아이폰11");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "아이폰11");

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(86,0,0,0,"아이폰11 64G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("프로","common",86);
insert into exception_keyword(keyword,market,product_id) values("pro","common",86);
insert into exception_keyword(keyword,market,product_id) values("맥스","common",86);
insert into exception_keyword(keyword,market,product_id) values("max","common",86);
insert into exception_keyword(keyword,market,product_id) values("128","common",86);
insert into exception_keyword(keyword,market,product_id) values("256","common",86);

insert into require_keyword (keyword,market,product_id) values("64","common",86);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(87,0,0,0,"아이폰11 128G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("프로","common",87);
insert into exception_keyword(keyword,market,product_id) values("pro","common",87);
insert into exception_keyword(keyword,market,product_id) values("맥스","common",87);
insert into exception_keyword(keyword,market,product_id) values("max","common",87);
insert into exception_keyword(keyword,market,product_id) values("64","common",87);
insert into exception_keyword(keyword,market,product_id) values("256","common",87);

insert into require_keyword (keyword,market,product_id) values("128","common",87);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(88,0,0,0,"아이폰11 256G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("프로","common",88);
insert into exception_keyword(keyword,market,product_id) values("pro","common",88);
insert into exception_keyword(keyword,market,product_id) values("맥스","common",88);
insert into exception_keyword(keyword,market,product_id) values("max","common",88);
insert into exception_keyword(keyword,market,product_id) values("64","common",88);
insert into exception_keyword(keyword,market,product_id) values("128","common",88);

insert into require_keyword (keyword,market,product_id) values("256","common",88);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(89,0,0,0,"아이폰11 프로 64G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("맥스","common",89);
insert into exception_keyword(keyword,market,product_id) values("max","common",89);
insert into exception_keyword(keyword,market,product_id) values("256","common",89);
insert into exception_keyword(keyword,market,product_id) values("512","common",89);

insert into require_keyword (keyword,market,product_id) values("64","common",89);
insert into require_keyword (keyword,market,product_id) values("pro","common",89);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(90,0,0,0,"아이폰11 프로 256G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("맥스","common",90);
insert into exception_keyword(keyword,market,product_id) values("max","common",90);
insert into exception_keyword(keyword,market,product_id) values("64","common",90);
insert into exception_keyword(keyword,market,product_id) values("512","common",90);

insert into require_keyword (keyword,market,product_id) values("256","common",90);
insert into require_keyword (keyword,market,product_id) values("pro","common",90);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(91,0,0,0,"아이폰11 프로 512G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("맥스","common",91);
insert into exception_keyword(keyword,market,product_id) values("max","common",91);
insert into exception_keyword(keyword,market,product_id) values("64","common",91);
insert into exception_keyword(keyword,market,product_id) values("256","common",91);

insert into require_keyword (keyword,market,product_id) values("512","common",91);
insert into require_keyword (keyword,market,product_id) values("pro","common",91);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(92,0,0,0,"아이폰11 프로 맥스 64G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("512","common",92);
insert into exception_keyword(keyword,market,product_id) values("256","common",92);

insert into require_keyword (keyword,market,product_id) values("64","common",92);
insert into require_keyword (keyword,market,product_id) values("pro","common",92);
insert into require_keyword (keyword,market,product_id) values("max","common",92);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(93,0,0,0,"아이폰11 프로 맥스 256G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("512","common",93);
insert into exception_keyword(keyword,market,product_id) values("64","common",93);

insert into require_keyword (keyword,market,product_id) values("256","common",93);
insert into require_keyword (keyword,market,product_id) values("pro","common",93);
insert into require_keyword (keyword,market,product_id) values("max","common",93);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(94,0,0,0,"아이폰11 프로 맥스 512G",0,"아이폰11");

insert into exception_keyword(keyword,market,product_id) values("256","common",94);
insert into exception_keyword(keyword,market,product_id) values("64","common",94);

insert into require_keyword (keyword,market,product_id) values("512","common",94);
insert into require_keyword (keyword,market,product_id) values("pro, 프로","common",94);
insert into require_keyword (keyword,market,product_id) values("max, 맥스","common",94);

insert into product_query values("아이폰se2");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("필름", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("커버", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("유리", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("교신", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("교환", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("업체", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("구매", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("워치", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰se2");

insert into query_exception_keyword(keyword, `query`) values ("프로모션", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("추천", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("case", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("박스", "아이폰se2");
insert into query_exception_keyword(keyword, `query`) values ("pen", "아이폰se2");

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(95,0,0,0,"아이폰se2 64G",0,"아이폰se2");

insert into exception_keyword(keyword,market,product_id) values("128","common",95);
insert into exception_keyword(keyword,market,product_id) values("256","common",95);
insert into exception_keyword(keyword,market,product_id) values("1세대","common",95);

insert into require_keyword (keyword,market,product_id) values("64","common",95);
insert into require_keyword (keyword,market,product_id) values("2세대","common",95);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(96,0,0,0,"아이폰se2 128G",0,"아이폰se2");

insert into exception_keyword(keyword,market,product_id) values("64","common",96);
insert into exception_keyword(keyword,market,product_id) values("256","common",96);
insert into exception_keyword(keyword,market,product_id) values("1세대","common",96);

insert into require_keyword (keyword,market,product_id) values("128","common",96);
insert into require_keyword (keyword,market,product_id) values("2세대","common",96);

insert into product (`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(97,0,0,0,"아이폰se2 256G",0,"아이폰se2");

insert into exception_keyword(keyword,market,product_id) values("64","common",97);
insert into exception_keyword(keyword,market,product_id) values("128","common",97);
insert into exception_keyword(keyword,market,product_id) values("1세대","common",97);

insert into require_keyword (keyword,market,product_id) values("256","common",97);
insert into require_keyword (keyword,market,product_id) values("2세대","common",97);

insert into product_query values("아이폰12");

insert into query_exception_keyword(keyword, `query`) values ("케이스", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("필름", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("커버", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("유리", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("교신", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("교환", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("업체", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("나눔", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("구매", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("삽니다", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("펜", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("매입", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("워치", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("버즈", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("프로모션", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("이벤트", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("사전예약", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("추천", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("쿠폰", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("스트랩", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("팔레트", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("case", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("악세사리", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("스티커", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("pen", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("팬", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("구합니다", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("구해요", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("노트북", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("밴딩", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("밴드", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("크롭", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("콜라보", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("액세서리", "아이폰12");
insert into query_exception_keyword(keyword, `query`) values ("클리어링", "아이폰12");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(101,0,0,0,"아이폰12 64G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(102,0,0,0,"아이폰12 128G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(103,0,0,0,"아이폰12 256G",0,"아이폰12");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(104,0,0,0,"아이폰12 미니 64G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(105,0,0,0,"아이폰12 미니 128G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(106,0,0,0,"아이폰12 미니 256G",0,"아이폰12");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(107,0,0,0,"아이폰12 프로 64G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(108,0,0,0,"아이폰12 프로 256G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(109,0,0,0,"아이폰12 프로 512G",0,"아이폰12");

insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(110,0,0,0,"아이폰12 프로 맥스 64G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(111,0,0,0,"아이폰12 프로 맥스 256G",0,"아이폰12");
insert into product(`id`, `avg_price`, `max_price`, `min_price`, `name`, `release_price`, `query`) values(112,0,0,0,"아이폰12 프로 맥스 512G",0,"아이폰12");



INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('프로', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('pro', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', 101);
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', 101);
insert into require_keyword (`keyword`, `market`, `product_id`)values("64","common",101);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('프로', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('pro', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '102');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '102');
insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",102);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('프로', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('pro', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '103');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '103');
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",103);



INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('프로', 'common', '104');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('pro', 'common', '104');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '104');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '104');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '104');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '104');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '104');
insert into require_keyword (`keyword`, `market`, `product_id`)values("64","common",104);
insert into require_keyword (`keyword`, `market`, `product_id`)values("미니,mini","common",104);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('프로', 'common', '105');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('pro', 'common', '105');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '105');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '105');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '105');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '105');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '105');
insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",105);
insert into require_keyword (`keyword`, `market`, `product_id`)values("미니,mini","common",105);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('프로', 'common', '106');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('pro', 'common', '106');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '106');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '106');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '106');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '106');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '106');
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",106);
insert into require_keyword (`keyword`, `market`, `product_id`)values("미니,mini","common",106);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '107');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '107');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '107');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '107');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '107');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '107');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '107');
insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",107);
insert into require_keyword (`keyword`, `market`, `product_id`)values("프로,pro","common",107);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '108');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '108');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '108');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '108');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '108');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '108');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '108');
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",108);
insert into require_keyword (`keyword`, `market`, `product_id`)values("프로,pro","common",108);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '109');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '109');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('맥스', 'common', '109');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('max', 'common', '109');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '109');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '109');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '109');
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",109);
insert into require_keyword (`keyword`, `market`, `product_id`)values("프로,pro","common",109);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '110');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '110');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '110');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '110');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '110');
insert into require_keyword (`keyword`, `market`, `product_id`)values("128","common",110);
insert into require_keyword (`keyword`, `market`, `product_id`)values("프로,pro","common",110);
insert into require_keyword (`keyword`, `market`, `product_id`)values("맥스,max","common",110);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '111');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '111');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '111');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '111');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('512', 'common', '111');
insert into require_keyword (`keyword`, `market`, `product_id`)values("256","common",111);
insert into require_keyword (`keyword`, `market`, `product_id`)values("프로,pro","common",111);
insert into require_keyword (`keyword`, `market`, `product_id`)values("맥스,max","common",111);

INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('mini', 'common', '112');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('미니', 'common', '112');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('64', 'common', '112');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('128', 'common', '112');
INSERT INTO exception_keyword (`keyword`, `market`, `product_id`) VALUES ('256', 'common', '112');
insert into require_keyword (`keyword`, `market`, `product_id`)values("512","common",112);
insert into require_keyword (`keyword`, `market`, `product_id`)values("프로,pro","common",112);
insert into require_keyword (`keyword`, `market`, `product_id`)values("맥스,max","common",112);

UPDATE `ssafyspecial`.`product` SET `release_price` = '899800' WHERE (`id` = '1');
UPDATE `ssafyspecial`.`product` SET `release_price` = '899800' WHERE (`id` = '2');
UPDATE `ssafyspecial`.`product` SET `release_price` = '998800' WHERE (`id` = '3');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1155000' WHERE (`id` = '4');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1196800' WHERE (`id` = '5');
UPDATE `ssafyspecial`.`product` SET `release_price` = '799700' WHERE (`id` = '6');
UPDATE `ssafyspecial`.`product` SET `release_price` = '832700' WHERE (`id` = '7');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1144000' WHERE (`id` = '8');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1248500' WHERE (`id` = '9');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1148400' WHERE (`id` = '10');
UPDATE `ssafyspecial`.`product` SET `release_price` = '0' WHERE (`id` = '11');
UPDATE `ssafyspecial`.`product` SET `release_price` = '899800' WHERE (`id` = '12');
UPDATE `ssafyspecial`.`product` SET `release_price` = '999900' WHERE (`id` = '13');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1199000' WHERE (`id` = '14');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1452000' WHERE (`id` = '15');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1599400' WHERE (`id` = '16');
UPDATE `ssafyspecial`.`product` SET `release_price` = '995500' WHERE (`id` = '31');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1243000' WHERE (`id` = '33');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1144000' WHERE (`id` = '32');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1100000' WHERE (`id` = '34');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1452000' WHERE (`id` = '35');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1210000' WHERE (`id` = '51');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1998700' WHERE (`id` = '55');
UPDATE `ssafyspecial`.`product` SET `release_price` = '2097700' WHERE (`id` = '56');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1892000' WHERE (`id` = '53');
UPDATE `ssafyspecial`.`product` SET `release_price` = '999900' WHERE (`id` = '52');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1254000' WHERE (`id` = '57');
UPDATE `ssafyspecial`.`product` SET `release_price` = '480700' WHERE (`id` = '70');
UPDATE `ssafyspecial`.`product` SET `release_price` = '600000' WHERE (`id` = '71');
UPDATE `ssafyspecial`.`product` SET `release_price` = '891000' WHERE (`id` = '72');
UPDATE `ssafyspecial`.`product` SET `release_price` = '940500' WHERE (`id` = '73');
UPDATE `ssafyspecial`.`product` SET `release_price` = '990000' WHERE (`id` = '74');
UPDATE `ssafyspecial`.`product` SET `release_price` = '940500' WHERE (`id` = '75');
UPDATE `ssafyspecial`.`product` SET `release_price` = '990000' WHERE (`id` = '76');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1100000' WHERE (`id` = '77');
UPDATE `ssafyspecial`.`product` SET `release_price` = '841500' WHERE (`id` = '78');
UPDATE `ssafyspecial`.`product` SET `release_price` = '891000' WHERE (`id` = '79');
UPDATE `ssafyspecial`.`product` SET `release_price` = '940500' WHERE (`id` = '80');
UPDATE `ssafyspecial`.`product` SET `release_price` = '859100' WHERE (`id` = '86');
UPDATE `ssafyspecial`.`product` SET `release_price` = '925100' WHERE (`id` = '87');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1057100' WHERE (`id` = '88');
UPDATE `ssafyspecial`.`product` SET `release_price` = '874500' WHERE (`id` = '89');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1383800' WHERE (`id` = '90');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1336500' WHERE (`id` = '91');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1028500' WHERE (`id` = '92');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1237500' WHERE (`id` = '93');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1490500' WHERE (`id` = '94');
UPDATE `ssafyspecial`.`product` SET `release_price` = '539000' WHERE (`id` = '95');
UPDATE `ssafyspecial`.`product` SET `release_price` = '605000' WHERE (`id` = '96');
UPDATE `ssafyspecial`.`product` SET `release_price` = '748000' WHERE (`id` = '97');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1155000' WHERE (`id` = '102');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1287000' WHERE (`id` = '103');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1078000' WHERE (`id` = '101');
UPDATE `ssafyspecial`.`product` SET `release_price` = '946000' WHERE (`id` = '104');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1012000' WHERE (`id` = '105');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1155000' WHERE (`id` = '106');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1474000' WHERE (`id` = '108');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1738000' WHERE (`id` = '109');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1342000' WHERE (`id` = '107');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1521300' WHERE (`id` = '112');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1380500' WHERE (`id` = '111');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1280400' WHERE (`id` = '110');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1892000' WHERE (`id` = '54');
UPDATE `ssafyspecial`.`product` SET `release_price` = '1148400' WHERE (`id` = '11');