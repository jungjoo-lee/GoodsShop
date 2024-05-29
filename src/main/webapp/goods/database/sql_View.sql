 
-- 상품 + 이미지
create or replace view goods_view
as
select g.gseq, g.gname, g.o_price, g.s_price, g.m_price, g.content, g.bestyn, g.useyn, g.indate,
		i.giseq, i.oriname, i.realname, i.filesize
from goods g, goodsimage i
where g.gseq = i.gseq;

select*from goods_view;

-- 신상품
create or replace view newlist_view
as 
select g.gseq, g.gname, g.s_price, i.oriname 
from goods g, goodsimage i  
where g.gseq = i.gseq and useyn='1'  
order by indate desc limit 4;


-- 베스트상품 
create or replace view bestlist_view
as 
select gseq, gname, s_price 
from goods
where bestyn='1' and useyn='1'  
order by indate desc;

select*from bestlist_view;

-- 장바구니 
create or replace view cart_view
as
select c.cseq, c.userid, m.name, c.gseq, g.gname, c.quantity, g.s_price, c.totalprice, c.indate
from cart c, goods g, Member m
where g.gseq = c.gseq and c.userid = m.userid;


-- 주문목록
create or replace view order_view
as
select d.odseq, o.oseq, o.indate,
		m.userid, m.name, m.zip_code, m.address, m.d_address, m.phone,
		g.gseq, g.gname,
		d.quantity, d.totalprice,
		s.status 
from orders o, order_detail d, goods g, Member m, order_status s
where o.oseq = d.oseq and o.userid = m.userid and d.gseq = g.gseq and d.osseq = s.osseq;


