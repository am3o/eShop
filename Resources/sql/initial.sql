INSERT INTO `role` (`id`, `level1`, `type`) VALUES (1, 0, 'admin');
INSERT INTO `role` (`id`, `level1`, `type`) VALUES (2, 1, 'user');


INSERT INTO `customer` (`id`, `name`, `lastname`, `password`, `username`, `role`)
VALUES (1, 'Guenther', 'Kastenbrot', 'sicher', 'admin', 1);

INSERT INTO `product` (`details`, `name`, `price`, `category_id`)
VALUES ('Das unglaubliche Leben des Torwaechters Zuul', 'Biographie des Zuul', '7.99', '1');

INSERT INTO `category` (`name`) VALUES ('Biographie');

INSERT INTO `product` (`details`, `name`, `price`, `category_id`)
VALUES ('Es handelt sich um die Grundlagen von REST', 'REST for Dummys', '9.99', '2');

INSERT INTO `category` (`name`) VALUES ('Fachbuecher');
