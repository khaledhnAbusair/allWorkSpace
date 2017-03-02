CREATE TABLE `batch` (
  `btch_id` varchar(200) NOT NULL DEFAULT '',
  `btch_issuer_id` varchar(50) DEFAULT NULL,
  `btch_issuer_name` varchar(200) DEFAULT NULL,
  `btch_total_cnt` int(11) DEFAULT NULL,
  PRIMARY KEY (`btch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `transaction` (
  `trns_id` varchar(200) NOT NULL,
  `trns_amnt` double DEFAULT NULL,
  `trns_curr` varchar(100) DEFAULT NULL,
  `trns_acct_from` varchar(100) DEFAULT NULL,
  `trns_acct_to` varchar(100) DEFAULT NULL,
  `btch_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`trns_id`),
  KEY `btch_id` (`btch_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`btch_id`) REFERENCES `batch` (`btch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;