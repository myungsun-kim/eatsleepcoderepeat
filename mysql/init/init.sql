-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema matching
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema matching
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `matching` DEFAULT CHARACTER SET utf8 ;
USE `matching` ;

-- -----------------------------------------------------
-- Table `matching`.`files`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`files` (
  `id` VARCHAR(255) NOT NULL,
  `data` LONGBLOB NULL DEFAULT NULL,
  `file_name` VARCHAR(255) NULL DEFAULT NULL,
  `file_type` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(20) NOT NULL,
  `banned` BIT(1) NULL DEFAULT NULL,
  `bio` VARCHAR(1000) NULL DEFAULT NULL,
  `city` VARCHAR(10) NOT NULL,
  `create_date` DATETIME(6) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `name` VARCHAR(8) NOT NULL,
  `nickname` VARCHAR(10) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `position` VARCHAR(20) NULL DEFAULT NULL,
  `tel` VARCHAR(255) NULL DEFAULT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `portfolio_uri` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_member_files1_idx` (`cover_pic` ASC),
  CONSTRAINT `fk_member_files1`
    FOREIGN KEY (`cover_pic`)
    REFERENCES `matching`.`files` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`club` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `bio` VARCHAR(1000) NULL DEFAULT NULL,
  `city` VARCHAR(10) NOT NULL,
  `create_date` DATETIME(6) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `is_public` BIT(1) NOT NULL,
  `max_count` INT NOT NULL,
  `member_count` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `host_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_club_files1_idx` (`cover_pic` ASC),
  INDEX `fk_club_member1_idx` (`host_id` ASC),
  CONSTRAINT `fk_club_files1`
    FOREIGN KEY (`cover_pic`)
    REFERENCES `matching`.`files` (`id`),
  CONSTRAINT `fk_club_member1`
    FOREIGN KEY (`host_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `bio` VARCHAR(1000) NULL DEFAULT NULL,
  `city` VARCHAR(10) NOT NULL,
  `create_date` DATETIME(6) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `is_participate` BIT(1) NOT NULL,
  `is_public` BIT(1) NOT NULL,
  `max_count` INT NOT NULL,
  `member_count` INT NOT NULL,
  `modify_date` DATETIME(6) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `period` INT NULL DEFAULT NULL,
  `schedule` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(15) NOT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `host_id` BIGINT NULL DEFAULT NULL,
  `club_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_study_member1_idx` (`host_id` ASC),
  INDEX `fk_study_files1_idx` (`cover_pic` ASC),
  INDEX `fk_study_club1_idx` (`club_id` ASC),
  CONSTRAINT `fk_study_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`),
  CONSTRAINT `fk_study_files1`
    FOREIGN KEY (`cover_pic`)
    REFERENCES `matching`.`files` (`id`),
  CONSTRAINT `fk_study_member1`
    FOREIGN KEY (`host_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `bio` VARCHAR(1000) NULL DEFAULT NULL,
  `city` VARCHAR(10) NOT NULL,
  `create_date` DATETIME(6) NOT NULL,
  `designer_count` INT NOT NULL,
  `designer_max_count` INT NOT NULL,
  `developer_count` INT NOT NULL,
  `developer_max_count` INT NOT NULL,
  `host_role` VARCHAR(20) NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  `is_participate` BIT(1) NOT NULL,
  `is_public` BIT(1) NOT NULL,
  `modify_date` DATETIME(6) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `period` INT NULL DEFAULT NULL,
  `planner_count` INT NOT NULL,
  `planner_max_count` INT NOT NULL,
  `schedule` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(10) NOT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` BIGINT NULL DEFAULT NULL,
  `host_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_files1_idx` (`cover_pic` ASC),
  INDEX `fk_project_club1_idx` (`club_id` ASC),
  INDEX `fk_project_member1_idx` (`host_id` ASC),
  CONSTRAINT `fk_project_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`),
  CONSTRAINT `fk_project_files1`
    FOREIGN KEY (`cover_pic`)
    REFERENCES `matching`.`files` (`id`),
  CONSTRAINT `fk_project_member1`
    FOREIGN KEY (`host_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` BIGINT NULL DEFAULT NULL,
  `project_id` BIGINT NULL DEFAULT NULL,
  `study_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK9qmen0qjsw6syjvqullfqk0uu` (`club_id` ASC),
  INDEX `FKskhv57eqns95wmqmu2lia00wr` (`project_id` ASC),
  INDEX `FK4tsm6m8vho6ww2kjc912htwb0` (`study_id` ASC),
  CONSTRAINT `FK4tsm6m8vho6ww2kjc912htwb0`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`id`),
  CONSTRAINT `FK9qmen0qjsw6syjvqullfqk0uu`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`),
  CONSTRAINT `FKskhv57eqns95wmqmu2lia00wr`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NOT NULL,
  `modify_date` DATETIME(6) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `board_id` INT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKysw8y9pw0cwpknhlnyc6863i` (`board_id` ASC),
  INDEX `FK9yp2k553e4jjckg50k46jeapf` (`member_id` ASC),
  CONSTRAINT `FK9yp2k553e4jjckg50k46jeapf`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `FKysw8y9pw0cwpknhlnyc6863i`
    FOREIGN KEY (`board_id`)
    REFERENCES `matching`.`board` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`career`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`career` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(255) NULL DEFAULT NULL,
  `department` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATETIME(6) NULL DEFAULT NULL,
  `start_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKj3sr8mqtm4j9hh3cdk9514iuy` (`member_id` ASC),
  CONSTRAINT `FKj3sr8mqtm4j9hh3cdk9514iuy`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`certification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `expired_date` DATETIME(6) NULL DEFAULT NULL,
  `grade` VARCHAR(255) NULL DEFAULT NULL,
  `issued_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `organization` VARCHAR(255) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKean8y31fu1keq8505jsdy4ts7` (`member_id` ASC),
  CONSTRAINT `FKean8y31fu1keq8505jsdy4ts7`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`club_application_form`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`club_application_form` (
  `nickname` VARCHAR(10) NOT NULL,
  `city` VARCHAR(10) NOT NULL,
  `git` VARCHAR(145) NULL DEFAULT NULL,
  `twitter` VARCHAR(145) NULL DEFAULT NULL,
  `facebook` VARCHAR(145) NULL DEFAULT NULL,
  `backjoon` VARCHAR(145) NULL DEFAULT NULL,
  `bio` VARCHAR(145) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NOT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`club_id`, `member_id`),
  INDEX `fk_club_application_form_files1_idx` (`cover_pic` ASC),
  INDEX `fk_club_application_form_member1_idx` (`member_id` ASC),
  CONSTRAINT `fk_club_application_form_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`),
  CONSTRAINT `fk_club_application_form_files1`
    FOREIGN KEY (`cover_pic`)
    REFERENCES `matching`.`files` (`id`),
  CONSTRAINT `fk_club_application_form_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NOT NULL,
  `modify_date` DATETIME(6) NULL DEFAULT NULL,
  `article_id` INT NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKi3jkgld5n5s1aqxumenfrn0aj` (`article_id` ASC),
  INDEX `FKs934rabdfwky0hpumbcf1l9fj` (`member_id` ASC),
  CONSTRAINT `FKi3jkgld5n5s1aqxumenfrn0aj`
    FOREIGN KEY (`article_id`)
    REFERENCES `matching`.`article` (`id`),
  CONSTRAINT `FKs934rabdfwky0hpumbcf1l9fj`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `degree` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATETIME(6) NULL DEFAULT NULL,
  `full_credit` FLOAT NOT NULL,
  `institution` VARCHAR(255) NULL DEFAULT NULL,
  `major` VARCHAR(255) NULL DEFAULT NULL,
  `my_credit` FLOAT NOT NULL,
  `start_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcwpq7xrd9cidl8hxgepm1cju8` (`member_id` ASC),
  CONSTRAINT `FKcwpq7xrd9cidl8hxgepm1cju8`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`reaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bytecode` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_article_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_article_reaction` (
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `article_id` INT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `reaction_id` INT NOT NULL,
  PRIMARY KEY (`article_id`, `member_id`, `reaction_id`),
  INDEX `FK1ts7vjdepvvplt7dpdtl87wnp` (`member_id` ASC),
  INDEX `FKlfx9w0e4mgvm1yi3gumsu53kn` (`reaction_id` ASC),
  CONSTRAINT `FK1ts7vjdepvvplt7dpdtl87wnp`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `FKlfx9w0e4mgvm1yi3gumsu53kn`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matching`.`reaction` (`id`),
  CONSTRAINT `FKnepf61t9btyp4d2cqhvtwdrdb`
    FOREIGN KEY (`article_id`)
    REFERENCES `matching`.`article` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`techstack` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_beginner_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_beginner_techstack` (
  `member_id` BIGINT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `techstack_id`),
  INDEX `FKhiwqgp87b3o133ipcrwvmtred` (`techstack_id` ASC),
  CONSTRAINT `FKhiwqgp87b3o133ipcrwvmtred`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`),
  CONSTRAINT `FKqkqcrqsbs4ir3m652e6ee7rpq`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_club` (
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NOT NULL,
  `club_id` BIGINT NOT NULL,
  PRIMARY KEY (`member_id`, `club_id`),
  INDEX `fk_member_club_club1_idx` (`club_id` ASC),
  CONSTRAINT `fk_member_club_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`),
  CONSTRAINT `fk_member_club_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_comment_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_comment_reaction` (
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `comment_id` INT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `reaction_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`, `member_id`, `reaction_id`),
  INDEX `FKhgus23q0i1la2p8qvy37fqr43` (`member_id` ASC),
  INDEX `FK9lfq8v8qy628dds2ij0671ttv` (`reaction_id` ASC),
  CONSTRAINT `FK9lfq8v8qy628dds2ij0671ttv`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matching`.`reaction` (`id`),
  CONSTRAINT `FKhgus23q0i1la2p8qvy37fqr43`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `FKi408qq0k9e1x6u6lqaekjk412`
    FOREIGN KEY (`comment_id`)
    REFERENCES `matching`.`comment` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_experienced_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_experienced_techstack` (
  `techstack_id` INT NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`techstack_id`, `member_id`),
  INDEX `fk_member_experienced_techstack_techstack1_idx` (`techstack_id` ASC),
  INDEX `fk_member_experienced_techstack_member1_idx` (`member_id` ASC),
  CONSTRAINT `fk_member_experienced_techstack_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `fk_member_experienced_techstack_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_portfolio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_portfolio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `protfolio_path` VARCHAR(255) NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK1ixus8kdxd69acu641r3mt5y6` (`member_id` ASC),
  CONSTRAINT `FK1ixus8kdxd69acu641r3mt5y6`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_project` (
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NOT NULL,
  `role` VARCHAR(20) NULL DEFAULT NULL,
  `member_id` BIGINT NOT NULL,
  `project_id` BIGINT NOT NULL,
  PRIMARY KEY (`member_id`, `project_id`),
  INDEX `fk_member_project_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_member_project_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `fk_member_project_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_study` (
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NOT NULL,
  `study_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`study_id`, `member_id`),
  INDEX `fk_member_study_member1_idx` (`member_id` ASC),
  CONSTRAINT `fk_member_study_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `fk_member_study_study1`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_techstack` (
  `member_id` BIGINT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `techstack_id`),
  INDEX `FKt9pc24oxcu8o9129k92jt7qg7` (`techstack_id` ASC),
  CONSTRAINT `FK4e6643xhjlp6k401w5aac3itk`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `FKt9pc24oxcu8o9129k92jt7qg7`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sender_id` BIGINT NOT NULL,
  `receiver_id` BIGINT NOT NULL,
  `sent_time` TIMESTAMP NULL DEFAULT NULL,
  `read_time` TIMESTAMP NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `type` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_member1_idx` (`sender_id` ASC),
  INDEX `fk_message_member2_idx` (`receiver_id` ASC),
  CONSTRAINT `fk_message_member1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `fk_message_member2`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`position` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKjcl2rsr8itxg4548wlcvxr5yo` (`member_id` ASC),
  CONSTRAINT `FKjcl2rsr8itxg4548wlcvxr5yo`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`project_application_form`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project_application_form` (
  `project_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `nickname` VARCHAR(10) NOT NULL,
  `city` VARCHAR(10) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `position` VARCHAR(20) NOT NULL,
  `git` VARCHAR(145) NULL DEFAULT NULL,
  `twitter` VARCHAR(145) NULL DEFAULT NULL,
  `facebook` VARCHAR(145) NULL DEFAULT NULL,
  `backjoon` VARCHAR(145) NULL DEFAULT NULL,
  `bio` VARCHAR(145) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NOT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `member_id1` BIGINT NOT NULL,
  PRIMARY KEY (`project_id`, `member_id`, `member_id1`),
  INDEX `fk_project_application_form_project1_idx` (`project_id` ASC),
  INDEX `fk_project_application_form_member1_idx` (`member_id` ASC),
  INDEX `fk_project_application_form_files1_idx` (`cover_pic` ASC),
  INDEX `fk_project_application_form_member2_idx` (`member_id1` ASC),
  CONSTRAINT `fk_project_application_form_files1`
    FOREIGN KEY (`cover_pic`)
    REFERENCES `matching`.`files` (`id`),
  CONSTRAINT `fk_project_application_form_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `fk_project_application_form_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`),
  CONSTRAINT `fk_project_application_form_member2`
    FOREIGN KEY (`member_id1`)
    REFERENCES `matching`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`project_star`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project_star` (
  `member_id` BIGINT NOT NULL,
  `project_id` BIGINT NOT NULL,
  PRIMARY KEY (`member_id`, `project_id`),
  INDEX `FKqj9uaxbv33b8sibd5i7qip6cu` (`project_id` ASC),
  CONSTRAINT `FKfwf1kl9f4i613ohpgnu93uvxa`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `FKqj9uaxbv33b8sibd5i7qip6cu`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`project_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project_techstack` (
  `is_active` BIT(1) NOT NULL,
  `project_id1` BIGINT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`project_id1`, `techstack_id`),
  INDEX `fk_project_techstack_project1_idx` (`project_id1` ASC),
  INDEX `fk_project_techstack_techstack1_idx` (`techstack_id` ASC),
  CONSTRAINT `fk_project_techstack_project1`
    FOREIGN KEY (`project_id1`)
    REFERENCES `matching`.`project` (`id`),
  CONSTRAINT `fk_project_techstack_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`refresh_token` (
  `member_id` VARCHAR(255) NOT NULL,
  `value` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`study_application_form`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study_application_form` (
  `nickname` VARCHAR(10) NOT NULL,
  `city` VARCHAR(10) NOT NULL,
  `git` VARCHAR(145) NULL DEFAULT NULL,
  `twitter` VARCHAR(145) NULL DEFAULT NULL,
  `facebook` VARCHAR(145) NULL DEFAULT NULL,
  `backjoon` VARCHAR(145) NULL DEFAULT NULL,
  `bio` VARCHAR(145) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NOT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `study_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`study_id`, `member_id`),
  INDEX `fk_study_application_form_files1_idx` (`cover_pic` ASC),
  INDEX `fk_study_application_form_member1_idx` (`member_id` ASC),
  CONSTRAINT `fk_study_application_form_files1`
    FOREIGN KEY (`cover_pic`)
    REFERENCES `matching`.`files` (`id`),
  CONSTRAINT `fk_study_application_form_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `fk_study_application_form_study1`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`study_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study_techstack` (
  `study_id` BIGINT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`study_id`, `techstack_id`),
  INDEX `fk_study_techstack_techstack1_idx` (`techstack_id` ASC),
  CONSTRAINT `fk_study_techstack_study1`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`id`),
  CONSTRAINT `fk_study_techstack_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_sns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_sns` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT NOT NULL,
  `sns_account` VARCHAR(255) NOT NULL,
  `sns_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_member_sns_member1_idx` (`member_id` ASC),
  CONSTRAINT `fk_member_sns_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
