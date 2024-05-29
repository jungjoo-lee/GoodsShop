insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품1', '1', 8000, 12000, 4000, '상품 1 상세설명', 1);

insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품2', '2', 9000, 26000, 15000, '상품 2 상세설명', 1);
	
insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품3', '3', 6000, 12000, 6000, '상품 3 상세설명', 1);
	
insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품4', '4', 5000, 12000, 7000, '상품 4 상세설명', 1);

insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품5', '5', 9000, 13000, 4000, '상품 5 상세설명', 1);
	
insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품6', '6', 12000, 15000, 3000, '상품 6 상세설명', 1);
	
insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품7', '7', 8500, 12000, 3500, '상품 7 상세설명', 1);

insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품8', '8', 10000, 20000, 10000, '상품 8 상세설명', 1);
	
insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품9', '9', 8000, 13000, 5000, '상품 9 상세설명', 1);
	
insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품10', '10', 5000, 11000, 6000, '상품 10 상세설명', 1);

insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품11', '11', 9000, 18000, 9000, '상품 11 상세설명', 1);
	
insert into goods (gname, category, o_price, s_price, m_price, content, bestyn) 
	values ('상품12', '12', 12000, 16000, 4000, '상품 12 상세설명', 1);
	

select*from goods;

insert into goodsimage (gseq, oriname, realname, filesize)
	values (1, '1', '1', 10);
insert into goodsimage (gseq, oriname, realname, filesize)
	values (12, '12', '12', 10);
	
	
DELIMITER $$
create procedure insert_goodsimage()
begin
   declare i int default 1;
    while (i <= 12) do
      insert into goodsimage (oriname, realname, filesize) 
      	values(i, i)
      	where gseq = i;
      	
      set i = i + 1;
    end while;
end $$
DELIMITER ;

call insert_goodsimage();
	



call insert_member();