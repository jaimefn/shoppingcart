INSERT INTO product (created_by, created_date, deleted, last_modified_by, last_modified_date, title,description,value) VALUES
('jaime','2021-01-09 08:00:00',0,'jaime','2021-01-09 08:00:00','Produto A','descricao A',1000),
('jaime','2021-01-09 08:00:00',0,'jaime','2021-01-09 08:00:00','Produto B','descricao B',2000),
('jaime','2021-01-09 08:00:00',0,'jaime','2021-01-09 08:00:00','Produto C','descricao C',10);
INSERT INTO promo_code (created_by, created_date, deleted, last_modified_by, last_modified_date, code,description,discount_percentage,quantity) VALUES
('jaime','2021-01-09 08:00:00',0,'jaime','2021-01-09 08:00:00','codeA','descricao promocode A',10,10),
('jaime','2021-01-09 08:00:00',0,'jaime','2021-01-09 08:00:00','codeB','descricao promocode B',20,10),
('jaime','2021-01-09 08:00:00',0,'jaime','2021-01-09 08:00:00','codeC','descricao promocode C',30,10);
INSERT INTO cart (created_by, created_date, deleted, last_modified_by, last_modified_date, total_value_without_discount, total_value_with_discount, customer_id,promo_code_id) VALUES
('jaime','2021-01-09 08:00:00',0,'jaime','2021-01-09 08:00:00',0,0,null,1);