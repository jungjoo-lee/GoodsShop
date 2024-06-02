-review_view-
create view review_view as
SELECT r.rseq, m.userid, gr.gseq as grade, g.gseq, cg.category, g.gname, r.subject, r.indate, gi.giseq, gi.realname
FROM review r INNER JOIN member m ON r.userid = m.userid
INNER JOIN grade gr ON m.gseq = gr.gseq INNER JOIN goods g ON r.gseq = g.gseq
INNER JOIN category cg ON g.cgseq = cg.cgseq
INNER JOIN (
    SELECT gseq, MAX(giseq) AS max_giseq
    FROM goodsimage
    GROUP BY gseq
) AS max_gi ON g.gseq = max_gi.gseq
INNER JOIN goodsimage gi ON max_gi.max_giseq = gi.giseq;