


DROP DATABASE IF EXISTS `master_db_tenant`;
CREATE DATABASE IF NOT EXISTS `master_db_tenant` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `master_db_tenant`;

DROP TABLE IF EXISTS `tenant_master`;
CREATE TABLE IF NOT EXISTS `tenant_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(50) NOT NULL DEFAULT '0',
  `db_name` varchar(50) NOT NULL DEFAULT '0',
  `url` varchar(255) NOT NULL DEFAULT '0',
  `username` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `driver_class_name` varchar(50) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `tenant_master` (`id`, `tenant_id`, `db_name`, `url`, `username`, `password`, `driver_class_name`, `is_active`) VALUES
	(1, 'tenant_1', 'tenant_1', 'jdbc:mysql://localhost:3306/tenant_1', 'root', 'root', 'com.mysql.jdbc.Driver', 1);
INSERT INTO `tenant_master` (`id`, `tenant_id`, `db_name`, `url`, `username`, `password`, `driver_class_name`, `is_active`) VALUES
	(2, 'tenant_2', 'tenant_2', 'jdbc:mysql://localhost:3306/tenant_2', 'root', 'root', 'com.mysql.jdbc.Driver', 1);



DROP DATABASE IF EXISTS `tenant_1`;
CREATE DATABASE IF NOT EXISTS `tenant_1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tenant_1`;



DROP TABLE IF EXISTS `child_department`;
CREATE TABLE IF NOT EXISTS `child_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_dept_id` int(11) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_child_department_parent_department` (`p_dept_id`),
  CONSTRAINT `FK_child_department_parent_department` FOREIGN KEY (`p_dept_id`) REFERENCES `parent_department` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(1, 1, 'Java');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(2, 1, 'Angular');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(3, 1, 'React');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(4, 1, 'PHP Laravel');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(5, 2, 'Accountant');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(6, 2, 'CA');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(7, 2, 'Sales');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(8, 1, 'Business Analyst');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(9, 1, 'DevOps');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(10, 1, 'Testing');


DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(50) DEFAULT NULL,
  `contact_number` varchar(50) DEFAULT NULL,
  `employee_email_id` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `child_dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FK_employee_child_department` (`child_dept_id`),
  CONSTRAINT `FK_employee_child_department` FOREIGN KEY (`child_dept_id`) REFERENCES `child_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(1, 'shubham mhatre', '8364738234', 'shubham@gmail.com', 'male', 1);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(2, 'test user', '4353453451', 'testuser@gmail.com', 'male', 2);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(3, 'sm test', '7762342342', 'smtest@gmail.com', 'female', 6);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(4, 'sanmesh sankhe', '7657523424', 'sanmensh@gmail.com', 'male', 3);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(5, 'shubhangi patil', '8364732342', 'shubha@gmail.com', 'female', 5);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(6, 'sachin shingade', '8364328231', 'sachin@gmail.com', 'male', 4);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(7, 'testsm', '87365743', 'testsm@gmail.com', 'Male', 1);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(8, 'tenant1', '8762732342', 'tenant1@mailinator.com', 'Male', 1);


DROP TABLE IF EXISTS `parent_department`;
CREATE TABLE IF NOT EXISTS `parent_department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


INSERT INTO `parent_department` (`dept_id`, `department_name`) VALUES
	(1, 'Information Technology');
INSERT INTO `parent_department` (`dept_id`, `department_name`) VALUES
	(2, 'Tax');
INSERT INTO `parent_department` (`dept_id`, `department_name`) VALUES
	(3, 'admin');



-- Dumping structure for table tenant_1.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `tax_rate` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;




-- Dumping database structure for tenant_2
DROP DATABASE IF EXISTS `tenant_2`;
CREATE DATABASE IF NOT EXISTS `tenant_2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tenant_2`;


DROP TABLE IF EXISTS `child_department`;
CREATE TABLE IF NOT EXISTS `child_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_dept_id` int(11) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_child_department_parent_department` (`p_dept_id`),
  CONSTRAINT `FK_child_department_parent_department` FOREIGN KEY (`p_dept_id`) REFERENCES `parent_department` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(1, 1, 'Java');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(2, 1, 'Angular');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(3, 1, 'React');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(4, 1, 'PHP Laravel');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(5, 2, 'Accountant');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(6, 2, 'CA');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(7, 2, 'Sales');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(8, 1, 'Business Analyst');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(9, 1, 'DevOps');
INSERT INTO `child_department` (`id`, `p_dept_id`, `role`) VALUES
	(10, 1, 'Testing');

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(50) DEFAULT NULL,
  `contact_number` varchar(50) DEFAULT NULL,
  `employee_email_id` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `child_dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FK_employee_child_department` (`child_dept_id`),
  CONSTRAINT `FK_employee_child_department` FOREIGN KEY (`child_dept_id`) REFERENCES `child_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(1, 'shubham mhatre', '8364738234', 'shubham@gmail.com', 'male', 1);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(2, 'test user', '4353453451', 'testuser@gmail.com', 'male', 2);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(3, 'sm test', '7762342342', 'smtest@gmail.com', 'female', 6);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(4, 'sanmesh sankhe', '7657523424', 'sanmensh@gmail.com', 'male', 3);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(5, 'shubhangi patil', '8364732342', 'shubha@gmail.com', 'female', 5);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(6, 'sachin shingade', '8364328231', 'sachin@gmail.com', 'male', 4);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(7, 'testsm', '87365743', 'testsm@gmail.com', 'Male', 1);
INSERT INTO `employee` (`employee_id`, `employee_name`, `contact_number`, `employee_email_id`, `gender`, `child_dept_id`) VALUES
	(8, 'tenant2', '8767235423', 'tenant2@mailinator.com', 'male', 1);

DROP TABLE IF EXISTS `parent_department`;
CREATE TABLE IF NOT EXISTS `parent_department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


INSERT INTO `parent_department` (`dept_id`, `department_name`) VALUES
	(1, 'Information Technology');
INSERT INTO `parent_department` (`dept_id`, `department_name`) VALUES
	(2, 'Tax');
INSERT INTO `parent_department` (`dept_id`, `department_name`) VALUES
	(3, 'admin');

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `tax_rate` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;





