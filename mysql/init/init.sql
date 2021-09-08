-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
-- -----------------------------------------------------
-- Schema matchi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema matchi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `matchi` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `matching` ;

-- -----------------------------------------------------
-- Table `matching`.`club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`club` (
  `club_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `member_count` INT NOT NULL,
  `max_count` INT NULL,
  `activity_point` INT NOT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `is_active` TINYINT NOT NULL,
  PRIMARY KEY (`club_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `member_count` INT NOT NULL,
  `max_count` INT NULL,
  `activity_point` INT NOT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  `is_active` TINYINT NOT NULL,
  `is_participate` TINYINT NOT NULL,
  PRIMARY KEY (`project_id`),
  INDEX `fk_project_club1_idx` (`club_id` ASC) VISIBLE,
  CONSTRAINT `fk_project_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`club_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study` (
  `study_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `member_count` INT NOT NULL,
  `max_count` INT NULL,
  `activity_point` INT NOT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  `is_active` TINYINT NOT NULL,
  `is_participate` TINYINT NOT NULL,
  PRIMARY KEY (`study_id`),
  INDEX `fk_study_club1_idx` (`club_id` ASC) VISIBLE,
  CONSTRAINT `fk_study_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`club_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `club_id` INT NULL DEFAULT NULL,
  `project_id` INT NULL DEFAULT NULL,
  `study_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  INDEX `fk_board_club2_idx` (`club_id` ASC) VISIBLE,
  INDEX `fk_board_project2_idx` (`project_id` ASC) VISIBLE,
  INDEX `fk_board_study2_idx` (`study_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_club2`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`club_id`),
  CONSTRAINT `fk_board_project2`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`project_id`),
  CONSTRAINT `fk_board_study2`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`study_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `name` VARCHAR(8) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(10) NOT NULL,
  `tel` VARCHAR(13) NULL DEFAULT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `banned` TINYINT NULL,
  `city` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`article` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `modify_date` TIMESTAMP NULL DEFAULT NULL,
  `is_deleted` TINYINT NOT NULL,
  `board_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`article_id`),
  INDEX `fk_article_board2_idx` (`board_id` ASC) VISIBLE,
  INDEX `fk_article_user2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_article_board2`
    FOREIGN KEY (`board_id`)
    REFERENCES `matching`.`board` (`board_id`),
  CONSTRAINT `fk_article_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`career`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`career` (
  `career_id` INT NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(30) NOT NULL,
  `department` VARCHAR(15) NOT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`career_id`),
  INDEX `fk_career_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_career_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`certification` (
  `certification_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `organization` VARCHAR(40) NOT NULL,
  `code` VARCHAR(60) NULL DEFAULT NULL,
  `grade` VARCHAR(45) NULL DEFAULT NULL,
  `issued_date` TIMESTAMP NOT NULL,
  `expired_date` TIMESTAMP NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`certification_id`),
  INDEX `fk_certification_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_certification_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `modify_date` TIMESTAMP NULL DEFAULT NULL,
  `is_deleted` TINYINT NOT NULL,
  `article_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `matching`.`article` (`article_id`),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`education` (
  `education_id` INT NOT NULL AUTO_INCREMENT,
  `institution` VARCHAR(50) NOT NULL,
  `degree` VARCHAR(30) NOT NULL,
  `major` VARCHAR(30) NULL DEFAULT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `my_credit` FLOAT NULL DEFAULT NULL,
  `full_credit` FLOAT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`education_id`),
  INDEX `fk_education_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_education_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`project_star`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project_star` (
  `user_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `project_id`),
  INDEX `fk_user_has_project_project3_idx` (`project_id` ASC) VISIBLE,
  INDEX `fk_user_has_project_user3_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_project_project3`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`project_id`),
  CONSTRAINT `fk_user_has_project_user3`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`techstack` (
  `techstack_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`techstack_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`project_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project_techstack` (
  `techstack_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`techstack_id`, `project_id`),
  INDEX `fk_techstack_has_project_project1_idx` (`project_id` ASC) VISIBLE,
  INDEX `fk_techstack_has_project_techstack1_idx` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `fk_techstack_has_project_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`project_id`),
  CONSTRAINT `fk_techstack_has_project_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`techstack_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`reaction` (
  `reaction_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  `bytecode` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`reaction_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`study_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study_techstack` (
  `techstack_id` INT NOT NULL,
  `study_id` INT NOT NULL,
  PRIMARY KEY (`techstack_id`, `study_id`),
  INDEX `fk_techstack_has_study_study1_idx` (`study_id` ASC) VISIBLE,
  INDEX `fk_techstack_has_study_techstack1_idx` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `fk_techstack_has_study_study1`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`study_id`),
  CONSTRAINT `fk_techstack_has_study_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`techstack_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`user_article_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_article_reaction` (
  `user_id` INT NOT NULL,
  `article_id` INT NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `reaction_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `article_id`, `reaction_id`),
  INDEX `fk_user_has_article_article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_user_has_article_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_article_reaction_reaction1_idx` (`reaction_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_article_reaction_reaction1`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matching`.`reaction` (`reaction_id`),
  CONSTRAINT `fk_user_has_article_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `matching`.`article` (`article_id`),
  CONSTRAINT `fk_user_has_article_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_club` (
  `user_id` INT NOT NULL,
  `club_id` INT NOT NULL,
  `is_active` TINYINT NOT NULL,
  `register_date` TIMESTAMP NOT NULL,
  `authority` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`, `club_id`),
  INDEX `fk_user_has_club_club1_idx` (`club_id` ASC) VISIBLE,
  INDEX `fk_user_has_club_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_club_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`club_id`),
  CONSTRAINT `fk_user_has_club_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_comment_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_comment_reaction` (
  `user_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `reaction_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `comment_id`, `reaction_id`),
  INDEX `fk_user_has_comment_comment1_idx` (`comment_id` ASC) VISIBLE,
  INDEX `fk_user_has_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_comment_reaction_reaction1_idx` (`reaction_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_comment_reaction_reaction1`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matching`.`reaction` (`reaction_id`),
  CONSTRAINT `fk_user_has_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `matching`.`comment` (`comment_id`),
  CONSTRAINT `fk_user_has_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_interest_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_interest_techstack` (
  `techstack_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`techstack_id`, `user_id`),
  INDEX `fk_techstack_has_user1_user2_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_techstack_has_user1_techstack2_idx` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `fk_techstack_has_user1_techstack2`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`techstack_id`),
  CONSTRAINT `fk_techstack_has_user1_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`user_project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_project` (
  `user_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `is_active` TINYINT NOT NULL,
  `register_date` TIMESTAMP NOT NULL,
  `authority` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`, `project_id`),
  INDEX `fk_user_has_project_project2_idx` (`project_id` ASC) VISIBLE,
  INDEX `fk_user_has_project_user2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_project_project2`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`project_id`),
  CONSTRAINT `fk_user_has_project_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_study` (
  `user_id` INT NOT NULL,
  `study_id` INT NOT NULL,
  `is_active` TINYINT NOT NULL,
  `register_date` TIMESTAMP NOT NULL,
  `authority` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`, `study_id`),
  INDEX `fk_user_has_study_study1_idx` (`study_id` ASC) VISIBLE,
  INDEX `fk_user_has_study_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_study_study1`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`study_id`),
  CONSTRAINT `fk_user_has_study_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_techstack` (
  `techstack_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`techstack_id`, `user_id`),
  INDEX `fk_techstack_has_user1_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_techstack_has_user1_techstack1_idx` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `fk_techstack_has_user1_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`techstack_id`),
  CONSTRAINT `fk_techstack_has_user1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`user_sns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_sns` (
  `user_sns_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `sns_type` VARCHAR(45) NOT NULL,
  `sns_address` VARCHAR(45) NOT NULL,
  INDEX `fk_user_sns_user1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_sns_id`),
  CONSTRAINT `fk_user_sns_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `matching`.`user_portfolio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_portfolio` (
  `user_portfolio_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `portfolio_path` VARCHAR(45) NOT NULL,
  INDEX `fk_user_portfolio_user1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_portfolio_id`),
  CONSTRAINT `fk_user_portfolio_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `matchi` ;

-- -----------------------------------------------------
-- Table `matchi`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `banned` BIT(1) NULL DEFAULT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `nickname` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `tel` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`club` (
  `club_id` INT NOT NULL AUTO_INCREMENT,
  `activity_point` INT NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  `max_count` INT NOT NULL,
  `member_count` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`club_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `activity_point` INT NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  `is_participate` BIT(1) NOT NULL,
  `max_count` INT NOT NULL,
  `member_count` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  INDEX `FKevkbjrwgjttbetkrc69mwe91m` (`club_id` ASC) VISIBLE,
  CONSTRAINT `FKevkbjrwgjttbetkrc69mwe91m`
    FOREIGN KEY (`club_id`)
    REFERENCES `matchi`.`club` (`club_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`study` (
  `study_id` INT NOT NULL AUTO_INCREMENT,
  `activity_point` INT NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  `is_participate` BIT(1) NOT NULL,
  `max_count` INT NOT NULL,
  `member_count` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`study_id`),
  INDEX `FKlk1hj7lxo369olkwthcss89y5` (`club_id` ASC) VISIBLE,
  CONSTRAINT `FKlk1hj7lxo369olkwthcss89y5`
    FOREIGN KEY (`club_id`)
    REFERENCES `matchi`.`club` (`club_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  `project_id` INT NULL DEFAULT NULL,
  `study_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  INDEX `FKltefkuw7i46wjacoj60tr63ac` (`club_id` ASC) VISIBLE,
  INDEX `FKqdy2pog48h6p56bvn5m48ibv2` (`project_id` ASC) VISIBLE,
  INDEX `FKqisv02qrdpe7pp8k5f96wnc31` (`study_id` ASC) VISIBLE,
  CONSTRAINT `FKltefkuw7i46wjacoj60tr63ac`
    FOREIGN KEY (`club_id`)
    REFERENCES `matchi`.`club` (`club_id`),
  CONSTRAINT `FKqdy2pog48h6p56bvn5m48ibv2`
    FOREIGN KEY (`project_id`)
    REFERENCES `matchi`.`project` (`project_id`),
  CONSTRAINT `FKqisv02qrdpe7pp8k5f96wnc31`
    FOREIGN KEY (`study_id`)
    REFERENCES `matchi`.`study` (`study_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`article` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NOT NULL,
  `modify_date` DATETIME(6) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `board_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  INDEX `FKmqwwl4q2yt7inioc2ksn09jrl` (`board_id` ASC) VISIBLE,
  INDEX `FK4ul3h8k5xibq5dgbdct6j3spl` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK4ul3h8k5xibq5dgbdct6j3spl`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`),
  CONSTRAINT `FKmqwwl4q2yt7inioc2ksn09jrl`
    FOREIGN KEY (`board_id`)
    REFERENCES `matchi`.`board` (`board_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`career`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`career` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(255) NULL DEFAULT NULL,
  `department` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATETIME(6) NULL DEFAULT NULL,
  `start_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK48mgoudjxcfsp3e0wsdb2lem9` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK48mgoudjxcfsp3e0wsdb2lem9`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`certification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `expired_date` DATETIME(6) NULL DEFAULT NULL,
  `grade` VARCHAR(255) NULL DEFAULT NULL,
  `issued_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `organization` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKket9cud09ijremxlh5nf20vth` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKket9cud09ijremxlh5nf20vth`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_deleted` BIT(1) NOT NULL,
  `modify_date` DATETIME(6) NULL DEFAULT NULL,
  `article_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `FKoowyi762viln0mkeqw0ttusdj` (`article_id` ASC) VISIBLE,
  INDEX `FKejkpgsiar9bd70luregb53gfd` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKejkpgsiar9bd70luregb53gfd`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`),
  CONSTRAINT `FKoowyi762viln0mkeqw0ttusdj`
    FOREIGN KEY (`article_id`)
    REFERENCES `matchi`.`article` (`article_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `degree` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATETIME(6) NULL DEFAULT NULL,
  `full_credit` FLOAT NOT NULL,
  `institution` VARCHAR(255) NULL DEFAULT NULL,
  `major` VARCHAR(255) NULL DEFAULT NULL,
  `my_credit` FLOAT NOT NULL,
  `start_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKinto5qdkqse5mj6uwaeswkx5` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKinto5qdkqse5mj6uwaeswkx5`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`project_star`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`project_star` (
  `project_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `user_id`),
  INDEX `FKl3tlbkvnm7ywwnd466avly7q1` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKjxp38rdx592r1ghwir86jvsgm`
    FOREIGN KEY (`project_id`)
    REFERENCES `matchi`.`project` (`project_id`),
  CONSTRAINT `FKl3tlbkvnm7ywwnd466avly7q1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`techstack` (
  `techstack_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`techstack_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`project_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`project_techstack` (
  `project_id` INT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `techstack_id`),
  INDEX `FKio56o1b6fj4uoycv6r0gwjphs` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `FK870r2m8w2pgjl68x8f2wfcaqd`
    FOREIGN KEY (`project_id`)
    REFERENCES `matchi`.`project` (`project_id`),
  CONSTRAINT `FKio56o1b6fj4uoycv6r0gwjphs`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matchi`.`techstack` (`techstack_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`reaction` (
  `reaction_id` INT NOT NULL AUTO_INCREMENT,
  `bytecode` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`reaction_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`study_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`study_techstack` (
  `study_id` INT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`study_id`, `techstack_id`),
  INDEX `FKeksgvar4goqtw8xn7f1s35kef` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `FKeksgvar4goqtw8xn7f1s35kef`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matchi`.`techstack` (`techstack_id`),
  CONSTRAINT `FKlwveu51k244ob4tblq8c9m1uw`
    FOREIGN KEY (`study_id`)
    REFERENCES `matchi`.`study` (`study_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_article_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_article_reaction` (
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `article_id` INT NOT NULL,
  `reaction_id` INT NOT NULL,
  PRIMARY KEY (`article_id`, `reaction_id`, `user_id`),
  INDEX `FKa4b2jcvam70vm5oqc39lxcdpc` (`user_id` ASC) VISIBLE,
  INDEX `FKp8vwo8plgj7xjep4tq0nnolcx` (`reaction_id` ASC) VISIBLE,
  CONSTRAINT `FKa4b2jcvam70vm5oqc39lxcdpc`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`),
  CONSTRAINT `FKbdlp273er2qsmaagmlh3dnpe9`
    FOREIGN KEY (`article_id`)
    REFERENCES `matchi`.`article` (`article_id`),
  CONSTRAINT `FKp8vwo8plgj7xjep4tq0nnolcx`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matchi`.`reaction` (`reaction_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_club` (
  `authority` BIT(1) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `club_id` INT NOT NULL,
  PRIMARY KEY (`club_id`, `user_id`),
  INDEX `FKp3rao1r99qe3s2tjkboa770jx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKg868wrct1cgd8d1yllkv2myai`
    FOREIGN KEY (`club_id`)
    REFERENCES `matchi`.`club` (`club_id`),
  CONSTRAINT `FKp3rao1r99qe3s2tjkboa770jx`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_comment_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_comment_reaction` (
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `reaction_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`, `reaction_id`, `user_id`),
  INDEX `FKkj58t6a11at1bjvxpt6ndbfyc` (`user_id` ASC) VISIBLE,
  INDEX `FK46sw541hwbj1w6y9cskg9foc5` (`reaction_id` ASC) VISIBLE,
  CONSTRAINT `FK46sw541hwbj1w6y9cskg9foc5`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matchi`.`reaction` (`reaction_id`),
  CONSTRAINT `FKfrydw1yis5xv0q0xogejs16a9`
    FOREIGN KEY (`comment_id`)
    REFERENCES `matchi`.`comment` (`comment_id`),
  CONSTRAINT `FKkj58t6a11at1bjvxpt6ndbfyc`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_interest_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_interest_techstack` (
  `user_id` INT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`techstack_id`, `user_id`),
  INDEX `FKtmc3hqxyb6e9o2cmpthf2hm1v` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK7man5257sv6sfxu0kaebveqx4`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matchi`.`techstack` (`techstack_id`),
  CONSTRAINT `FKtmc3hqxyb6e9o2cmpthf2hm1v`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_portfolio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_portfolio` (
  `user_portfolio_id` INT NOT NULL AUTO_INCREMENT,
  `protfolio_path` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_portfolio_id`),
  INDEX `FK8wk1inrghkfme6kbq8dk77ja0` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK8wk1inrghkfme6kbq8dk77ja0`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_project` (
  `authority` BIT(1) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NULL DEFAULT NULL,
  `project_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `user_id`),
  INDEX `FKcvv3rv1aokcq0b5rls1htscxa` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKcvv3rv1aokcq0b5rls1htscxa`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`),
  CONSTRAINT `FKt00b7i57x1hu9jy1awp507fu5`
    FOREIGN KEY (`project_id`)
    REFERENCES `matchi`.`project` (`project_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_sns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_sns` (
  `user_sns_id` INT NOT NULL AUTO_INCREMENT,
  `sns_address` VARCHAR(255) NULL DEFAULT NULL,
  `sns_type` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_sns_id`),
  INDEX `FK3mc8fq8f2hvlg3sxk0sct1sx5` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK3mc8fq8f2hvlg3sxk0sct1sx5`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_study` (
  `authority` BIT(1) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `study_id` INT NOT NULL,
  PRIMARY KEY (`study_id`, `user_id`),
  INDEX `FKkwp2wk5hoomvyur774jkuebsw` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKirubulk0nr2wyndqwngyxx3mp`
    FOREIGN KEY (`study_id`)
    REFERENCES `matchi`.`study` (`study_id`),
  CONSTRAINT `FKkwp2wk5hoomvyur774jkuebsw`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matchi`.`user_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matchi`.`user_techstack` (
  `user_id` INT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`techstack_id`, `user_id`),
  INDEX `FK9kap9o7fm3p6uco1tpf1q37s0` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK5pubg7o8vuwauujwd6xf5gko9`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matchi`.`techstack` (`techstack_id`),
  CONSTRAINT `FK9kap9o7fm3p6uco1tpf1q37s0`
    FOREIGN KEY (`user_id`)
    REFERENCES `matchi`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
