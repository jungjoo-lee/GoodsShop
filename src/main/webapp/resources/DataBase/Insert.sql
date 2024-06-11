-grade-
insert into grade values(0, 'Bronze', 1);
insert into grade values(0, 'Silver', 2);
insert into grade values(0, 'Gold', 3);
insert into grade values(0, 'Platinum', 4);
insert into grade values(0, 'Diamond', 5);

-order_status-
insert into order_status values(0, '주문완료');
insert into order_status values(0, '배송준비중');
insert into order_status values(0, '배송중');
insert into order_status values(0, '배송완료');

-category-
insert into category values (0, '목걸이');
insert into category values (0, '반지');
insert into category values (0, '팔찌');
insert into category values (0, '귀걸이');
insert into category values (0, '키링');
insert into category values (0, '인형');
insert into category values (0, '기타');

-goods-
insert into goods values (0, 'IVE 미니브 인형', 6, 10000, 20000, 10000, 'IVE 미니브 인형', 0, 1, now());
insert into goods values (0, '뉴진스 bunny 키링 5종세트', 5, 15000, 20000, 5000, '뉴진스 bunny 키링 5종세트', 0, 1, now());
insert into goods values (0, '비트코인 반지 링', 2, 10000, 12000, 2000, '비트코인 반지 링', 0, 1, now());
insert into goods values (0, '세일러문 스니커즈', 7, 50000, 70000, 20000, '세일러문 스니커즈 세일러문으로 변신해보세요~세일러문 스니커즈 세일러문으로 변신해보세요~세일러문 스니커즈 세일러문으로 변신해보세요~', 0, 1, now());
insert into goods values (0, '손흥민 축구 스포츠 팔찌', 3, 12000, 18000, 6000, '손흥민 축구 스포츠 팔찌', 0, 1, now());
insert into goods values (0, '아이브미니브인형', 6, 5000, 20000, 15000, '아이브미니브인형', 0, 1, now());
insert into goods values (0, '여우귀걸이', 1, 2000, 10000, 8000, '여우귀걸이', 0, 1, now());
insert into goods values (0, '임영웅 IM HERO 하늘색 팔찌', 3, 5000, 8000, 3000, '임영웅 IM HERO 하늘색 팔찌', 0, 1, now());
insert into goods values (0, '주술회전 목걸이 옥문강', 1, 2000, 20000, 18000, '주술회전 목걸이 옥문강', 0, 1, now());
insert into goods values (0, '짱구 마우스패드', 7, 10000, 20000, 10000, '짱구 마우스패드', 0, 1, now());
insert into goods values (0, '체인소맨 포치타 목걸이', 1, 5000, 15000, 10000, '체인소맨 포치타 목걸이', 0, 1, now());
insert into goods values (0, '포트거스 D 에이스 귀걸이', 4, 6000, 12000, 6000, '포트거스 D 에이스 귀걸이', 0, 1, now());
insert into goods values (0, '푸바오 안아줘 쿠션 인형', 6, 45000, 80000, 35000, '푸바오 안아줘 쿠션 인형 귀여운 푸바오 인형, 애착 인형으로 품에 꼭 안아보세요~~', 0, 1, now());

-goodsimage-
insert into goodsimage values (0, 1, 'IVE 미니브 인형.jpg', '123456780', 123456);
insert into goodsimage values (0, 2, '뉴진스 bunny 키링 5종세트.png', '123456781', 123456);
insert into goodsimage values (0, 2, '뉴진스 bunny 키링 5종세트1.png', '123456782', 123456);
insert into goodsimage values (0, 3, '비트코인 반지 링.jpg', '123456783', 123456);
insert into goodsimage values (0, 4, '세일러문 스니커즈.jpg', '123456784', 123456);
insert into goodsimage values (0, 5, '손흥민 축구 스포츠 팔찌.jpg', '123456785', 123456);
insert into goodsimage values (0, 5, '손흥민 축구 스포츠 팔찌1.jpg', '123456786', 123456);
insert into goodsimage values (0, 6, '아이브미니브인형.jpg', '123456787', 123456);
insert into goodsimage values (0, 7, '여우귀걸이.jpg', '123456788', 123456);
insert into goodsimage values (0, 7, '여우귀걸이 착용샷.jpg', '123456791', 123456);
insert into goodsimage values (0, 8, '임영웅 IM HERO 하늘색 팔찌.jpg', '123456792', 123456);
insert into goodsimage values (0, 8, '임영웅 IM HERO 하늘색 팔찌1.jpg', '123456793', 123456);
insert into goodsimage values (0, 9, '주술회전 목걸이 옥문강.jpg', '123456794', 123456);
insert into goodsimage values (0, 10, '짱구 마우스패드.jpg', '123456795', 123456);
insert into goodsimage values (0, 11, '체인소맨 포치타 목걸이.jpg', '123456796', 123456);
insert into goodsimage values (0, 11, '체인소맨 포치타 목걸이1.jpg', '123456797', 123456);
insert into goodsimage values (0, 12, '포트거스 D 에이스 귀걸이.jpg', '123456798', 123456);
insert into goodsimage values (0, 13, '푸바오 안아줘 쿠션 인형.jpg', '123456799', 123456);

-review-
insert into review values (0, 'hong1', 1, '인형 너무 귀여워요', '인형 너무 귀여워요. 인형 너무 귀여워요. 인형 너무 귀여워요. 인형 너무 귀여워요.', now());
insert into review values (0, 'hong2', 2, '뉴진스 bunny 키링 5종세트 너무 좋아요', '뉴진스 bunny 키링 5종세트 뉴진스 bunny 키링 5종세트', now());
insert into review values (0, 'hong3', 3, '비트코인 대박나는거 같음', '난 이제 부자다!!!! 난 이제 부자다!!!! 난 이제 부자다!!!! 난 이제 부자다!!!! 난 이제 부자다!!!! 난 이제 부자다!!!! 난 이제 부자다!!!!', now());
insert into review values (0, 'hong4', 4, '세일러문 스니커즈', '문 스리스탈~~~ 변신!', now());
insert into review values (0, 'hong5', 5, '손흥민 축구 스포츠 팔찌', '간지 작살', now());
insert into review values (0, 'hong6', 6, '아이브미니브인형', '아이브미니브인형 핵 귀여움', now());
insert into review values (0, 'hong7', 7, '무거운거같음....', '무게감이 좀 있고 딱딱해서 귀가 어디에 부디치면 개아픔ㅡㅡ', now());
insert into review values (0, 'hong8', 8, '임영웅 IM HERO 하늘색 팔찌', '웃긴게 끼고 노래부르면 잘불러짐 ㄷㄷ', now());
insert into review values (0, 'hong9', 2, '토끼!', '가방, 에어팟, 열쇠에도 걸어도 좋음', now());
insert into review values (0, 'hong10', 3, '존나 커서 별로임', '싸움하면 내 손가락이 날라갈거 같음', now());
insert into review values (0, 'hong11', 13, '푸바오 안아줘 쿠션 인형', '때탈까봐 관상용으로 모셔둠', now());
insert into review values (0, 'hong12', 11, '체인소맨 포치타 목걸이', '체인소맨 포치타 목걸이', now());
insert into review values (0, 'hong13', 12, '포트거스 D 에이스 귀걸이', '포트거스 D 에이스 귀걸이', now());
insert into review values (0, 'hong14', 10, '짱구 마우스패드', 'FPS 유저 필수', now());
insert into review values (0, 'hong1', 6, '인형 개잘든거 같다', '딴데는 개못생겻는데 여기서 파는거는 개이쁜데?', now());
insert into review values (0, 'hong12', 6, '아이브미니브인형', '아이브미니브인형', now());
insert into review values (0, 'hong16', 7, '여우귀걸이', '여우귀걸이', now());
insert into review values (0, 'hong50', 11, '체인소맨 포치타 목걸이', '체인소맨 포치타 목걸이', now());
insert into review values (0, 'hong14', 9, '주술회전 목걸이 옥문강', '오타쿠 중2병인 나는 이제 고죠 사토루다. 손가락만 꼬면 다 이김.', now());
insert into review values (0, 'hong10', 13, '푸바오 안아줘 쿠션 인형', '푸바오 안아줘 쿠션 인형', now());
insert into review values (0, 'hong10', 12, '히켄', '불주먹 날림', now());
insert into review values (0, 'hong10', 11, '졸귀', '핵귀', now());