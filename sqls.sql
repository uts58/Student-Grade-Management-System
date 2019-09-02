CREATE TABLE IF NOT EXISTS `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(80) NOT NULL,
  `password` varchar(80000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;
INSERT INTO `admins` (`id`, `username`, `password`) VALUES
(1,"admin",1212);

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(80) NOT NULL,
  `name` varchar(80) NOT NULL,
  `password` varchar(80000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;
INSERT INTO `users` (`id`, `username`, `name`, `password`) VALUES
(1,"arif","Arif Istiaq",1),
(2,"reema","Reema Akhtar",2);

CREATE TABLE `arif` (
  `name` varchar(80) NOT NULL,
  `roll` int(10) NOT NULL,
  `bangla` int(10) NOT NULL,
  `english` int(10) NOT NULL,
  `math` int(10) NOT NULL,
  `gk` int(10) NOT NULL,
  `religion` int(10) NOT NULL,
  `science` int(10) NOT NULL,
  PRIMARY KEY (`roll`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
INSERT INTO `arif` (`name`, `roll`, `bangla`, `english`, `math`, `gk`, `religion`, `science`) VALUES
('Shurid Hossain', 2, 78, 98, 75, 89, 87, 87);

CREATE TABLE `reema` (
  `name` varchar(80) NOT NULL,
  `roll` int(10) NOT NULL UNIQUE,
  `bangla` int(10) NOT NULL,
  `english` int(10) NOT NULL,
  `math` int(10) NOT NULL,
  `gk` int(10) NOT NULL,
  `religion` int(10) NOT NULL,
  `science` int(10) NOT NULL,
  PRIMARY KEY (`roll`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
INSERT INTO `reema` (`name`, `roll`, `bangla`, `english`, `math`, `gk`, `religion`, `science`) VALUES
('Rifat Hossain', 2, 78, 98, 75, 89, 87, 87);
