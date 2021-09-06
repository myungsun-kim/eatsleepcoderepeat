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
USE `matching` ;

-- -----------------------------------------------------
-- Table `matching`.`club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`club` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `member_count` INT NOT NULL,
  `activity_point` INT NOT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `is_active` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `member_count` INT NOT NULL,
  `activity_point` INT NOT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_club1_idx` (`club_id` ASC) VISIBLE,
  CONSTRAINT `fk_project_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `member_count` INT NOT NULL,
  `activity_point` INT NOT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  `is_active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_study_club1_idx` (`club_id` ASC) VISIBLE,
  CONSTRAINT `fk_study_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `club_id` INT NULL DEFAULT NULL,
  `project_id` INT NULL DEFAULT NULL,
  `study_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_board_club2_idx` (`club_id` ASC) VISIBLE,
  INDEX `fk_board_project2_idx` (`project_id` ASC) VISIBLE,
  INDEX `fk_board_study2_idx` (`study_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_club2`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`),
  CONSTRAINT `fk_board_project2`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`),
  CONSTRAINT `fk_board_study2`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `name` VARCHAR(8) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(10) NOT NULL,
  `tel` VARCHAR(13) NULL DEFAULT NULL,
  `twitter` VARCHAR(45) NULL DEFAULT NULL,
  `git` VARCHAR(50) NULL DEFAULT NULL,
  `facebook` VARCHAR(50) NULL DEFAULT NULL,
  `baekjoon` VARCHAR(50) NULL DEFAULT NULL,
  `blog` VARCHAR(50) NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `portfolio_path` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `modified_at` TIMESTAMP NULL DEFAULT NULL,
  `is_deleted` TINYINT NOT NULL,
  `board_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_article_board2_idx` (`board_id` ASC) VISIBLE,
  INDEX `fk_article_user2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_article_board2`
    FOREIGN KEY (`board_id`)
    REFERENCES `matching`.`board` (`id`),
  CONSTRAINT `fk_article_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`career`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`career` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(30) NOT NULL,
  `department` VARCHAR(15) NOT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_career_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_career_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`certification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `organization` VARCHAR(40) NOT NULL,
  `code` VARCHAR(60) NULL DEFAULT NULL,
  `grade` VARCHAR(45) NULL DEFAULT NULL,
  `issued_date` TIMESTAMP NOT NULL,
  `expired_date` TIMESTAMP NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_certification_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_certification_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `modified_at` TIMESTAMP NULL DEFAULT NULL,
  `is_deleted` TINYINT NOT NULL,
  `article_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `matching`.`article` (`id`),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `institution` VARCHAR(50) NOT NULL,
  `degree` VARCHAR(30) NOT NULL,
  `major` VARCHAR(30) NULL DEFAULT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `my_credit` FLOAT NULL DEFAULT NULL,
  `full_credit` FLOAT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_education_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_education_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
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
    REFERENCES `matching`.`project` (`id`),
  CONSTRAINT `fk_user_has_project_user3`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`techstack` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
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
    REFERENCES `matching`.`project` (`id`),
  CONSTRAINT `fk_techstack_has_project_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`reaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  `bytecode` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id`))
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
    REFERENCES `matching`.`study` (`id`),
  CONSTRAINT `fk_techstack_has_study_techstack1`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`user_article_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_article_reaction` (
  `user_id` INT NOT NULL,
  `article_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `reaction_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `article_id`, `reaction_id`),
  INDEX `fk_user_has_article_article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_user_has_article_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_article_reaction_reaction1_idx` (`reaction_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_article_reaction_reaction1`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matching`.`reaction` (`id`),
  CONSTRAINT `fk_user_has_article_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `matching`.`article` (`id`),
  CONSTRAINT `fk_user_has_article_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_club` (
  `user_id` INT NOT NULL,
  `club_id` INT NOT NULL,
  `is_active` TINYINT NOT NULL,
  `registered_at` TIMESTAMP NOT NULL,
  `authority` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`, `club_id`),
  INDEX `fk_user_has_club_club1_idx` (`club_id` ASC) VISIBLE,
  INDEX `fk_user_has_club_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_club_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`),
  CONSTRAINT `fk_user_has_club_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_comment_reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_comment_reaction` (
  `user_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `reaction_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `comment_id`, `reaction_id`),
  INDEX `fk_user_has_comment_comment1_idx` (`comment_id` ASC) VISIBLE,
  INDEX `fk_user_has_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_comment_reaction_reaction1_idx` (`reaction_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_comment_reaction_reaction1`
    FOREIGN KEY (`reaction_id`)
    REFERENCES `matching`.`reaction` (`id`),
  CONSTRAINT `fk_user_has_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `matching`.`comment` (`id`),
  CONSTRAINT `fk_user_has_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
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
    REFERENCES `matching`.`techstack` (`id`),
  CONSTRAINT `fk_techstack_has_user1_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
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
  `registered_at` TIMESTAMP NOT NULL,
  `authority` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`, `project_id`),
  INDEX `fk_user_has_project_project2_idx` (`project_id` ASC) VISIBLE,
  INDEX `fk_user_has_project_user2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_project_project2`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`),
  CONSTRAINT `fk_user_has_project_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matching`.`user_study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`user_study` (
  `user_id` INT NOT NULL,
  `study_id` INT NOT NULL,
  `is_active` TINYINT NOT NULL,
  `registered_at` TIMESTAMP NOT NULL,
  `authority` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`, `study_id`),
  INDEX `fk_user_has_study_study1_idx` (`study_id` ASC) VISIBLE,
  INDEX `fk_user_has_study_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_study_study1`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`id`),
  CONSTRAINT `fk_user_has_study_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
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
    REFERENCES `matching`.`techstack` (`id`),
  CONSTRAINT `fk_techstack_has_user1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `matching`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
