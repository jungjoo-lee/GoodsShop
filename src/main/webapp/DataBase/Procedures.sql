CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_notice`()
begin
   declare i int default 1;
    while (i <= 203) do
      insert into notice values(0, 'admin', concat('공지사항 제목', i), concat('공지사항 내용', i), now());
      set i = i + 1;
    end while;
end

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_qna`()
begin
   declare i int default 1;
    while (i <= 201) do
      insert into qna (userid, subject, content) values(concat('hong', i), concat('장난하냐', i), concat('장난하냐', i));
      set i = i + 1;
    end while;
end

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_member`()
begin
   declare i int default 1;
    while (i <= 201) do
      insert into member (userid, pwd, gseq, name, email, phone, zip_code, address, d_address) values(concat('hong', i), i, 1, concat('홍길동', i), concat('hong', i, '@abc.com'), concat('010-1234-567', i%2), i, concat('인사', (i%9)+1,'동'), concat('12', (i%9), '번지'));
      set i = i + 1;
    end while;
end