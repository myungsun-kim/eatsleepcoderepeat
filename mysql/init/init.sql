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
CREATE SCHEMA IF NOT EXISTS `matching` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `matching` ;

-- -----------------------------------------------------
-- Table `matching`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(255) NULL DEFAULT NULL,
  `banned` BIT(1) NULL DEFAULT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `cover_pic` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `nickname` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `tel` VARCHAR(255) NULL DEFAULT NULL,
  `position` VARCHAR(20) NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`club` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activity_point` INT NOT NULL,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  `max_count` INT NOT NULL,
  `member_count` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `repository` VARCHAR(255) NULL DEFAULT NULL,
  `team_chat` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study` (
  `id` INT NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`),
  INDEX `FKqfx4rive2yg0ro85qxngtl3v7` (`club_id` ASC) VISIBLE,
  CONSTRAINT `FKqfx4rive2yg0ro85qxngtl3v7`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project` (
  `id` INT NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`),
  INDEX `FKsljgvs996r9dsqb8ifflnpmdo` (`club_id` ASC) VISIBLE,
  CONSTRAINT `FKsljgvs996r9dsqb8ifflnpmdo`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `club_id` INT NULL DEFAULT NULL,
  `project_id` INT NULL DEFAULT NULL,
  `study_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK9qmen0qjsw6syjvqullfqk0uu` (`club_id` ASC) VISIBLE,
  INDEX `FKskhv57eqns95wmqmu2lia00wr` (`project_id` ASC) VISIBLE,
  INDEX `FK4tsm6m8vho6ww2kjc912htwb0` (`study_id` ASC) VISIBLE,
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
  INDEX `FKysw8y9pw0cwpknhlnyc6863i` (`board_id` ASC) VISIBLE,
  INDEX `FK9yp2k553e4jjckg50k46jeapf` (`member_id` ASC) VISIBLE,
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
  INDEX `FKj3sr8mqtm4j9hh3cdk9514iuy` (`member_id` ASC) VISIBLE,
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
  INDEX `FKean8y31fu1keq8505jsdy4ts7` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FKean8y31fu1keq8505jsdy4ts7`
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
  INDEX `FKi3jkgld5n5s1aqxumenfrn0aj` (`article_id` ASC) VISIBLE,
  INDEX `FKs934rabdfwky0hpumbcf1l9fj` (`member_id` ASC) VISIBLE,
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
  INDEX `FKcwpq7xrd9cidl8hxgepm1cju8` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FKcwpq7xrd9cidl8hxgepm1cju8`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`files`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`files` (
  `id` VARCHAR(255) NOT NULL,
  `data` LONGBLOB NULL DEFAULT NULL,
  `file_name` VARCHAR(255) NULL DEFAULT NULL,
  `file_type` VARCHAR(255) NULL DEFAULT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_files_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_files_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  INDEX `FK1ts7vjdepvvplt7dpdtl87wnp` (`member_id` ASC) VISIBLE,
  INDEX `FKlfx9w0e4mgvm1yi3gumsu53kn` (`reaction_id` ASC) VISIBLE,
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
-- Table `matching`.`member_club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_club` (
  `authority` BIT(1) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NOT NULL,
  `club_id` INT NOT NULL,
  PRIMARY KEY (`club_id`, `member_id`),
  INDEX `FK7nklse609rk9oq9gysxsi0d75` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK7nklse609rk9oq9gysxsi0d75`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `FK8hyb376xb1gf5f9btyqay0mfu`
    FOREIGN KEY (`club_id`)
    REFERENCES `matching`.`club` (`id`))
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
  INDEX `FKhgus23q0i1la2p8qvy37fqr43` (`member_id` ASC) VISIBLE,
  INDEX `FK9lfq8v8qy628dds2ij0671ttv` (`reaction_id` ASC) VISIBLE,
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
-- Table `matching`.`techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`techstack` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_beginner_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_beginner_techstack` (
  `member_id` BIGINT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `techstack_id`),
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
-- Table `matching`.`member_experienced_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_experienced_techstack` (
  `member_id` BIGINT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `techstack_id`),
  INDEX `FKhiwqgp87b3o133ipcrwvmtrev` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `FKhiwqgp87b3o133ipcrwvmtrev`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`),
  CONSTRAINT `FKqkqcrqsbs4ir3m652e6ee7rpb`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_portfolio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_portfolio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `protfolio_path` VARCHAR(255) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK1ixus8kdxd69acu641r3mt5y6` (`member_id` ASC) VISIBLE,
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
  `authority` BIT(1) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `project_id`),
  INDEX `FKef43y2ckpfefp8p0vecc85i49` (`project_id` ASC) VISIBLE,
  CONSTRAINT `FKef43y2ckpfefp8p0vecc85i49`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`),
  CONSTRAINT `FKftsgd00053n0nmsw9a75vpv1x`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_sns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_sns` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sns_address` VARCHAR(255) NULL DEFAULT NULL,
  `sns_type` VARCHAR(255) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK2t28c5q5sf97wf5bis3mrr6vk` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FK2t28c5q5sf97wf5bis3mrr6vk`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`member_study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`member_study` (
  `authority` BIT(1) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `register_date` DATETIME(6) NULL DEFAULT NULL,
  `member_id` BIGINT NOT NULL,
  `study_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `study_id`),
  INDEX `FKqqgc5f6qtk0ul7wkju95jsber` (`study_id` ASC) VISIBLE,
  CONSTRAINT `FKf46l4exkmwvf9doq05oesudyf`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`),
  CONSTRAINT `FKqqgc5f6qtk0ul7wkju95jsber`
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
  INDEX `FKt9pc24oxcu8o9129k92jt7qg7` (`techstack_id` ASC) VISIBLE,
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
-- Table `matching`.`position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`position` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `member_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKjcl2rsr8itxg4548wlcvxr5yo` (`member_id` ASC) VISIBLE,
  CONSTRAINT `FKjcl2rsr8itxg4548wlcvxr5yo`
    FOREIGN KEY (`member_id`)
    REFERENCES `matching`.`member` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `matching`.`project_star`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`project_star` (
  `member_id` BIGINT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `project_id`),
  INDEX `FKqj9uaxbv33b8sibd5i7qip6cu` (`project_id` ASC) VISIBLE,
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
  `project_id` INT NOT NULL,
  `techstack_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `techstack_id`),
  INDEX `FK74qa2k37o8xateh31qfamh6vb` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `FK74qa2k37o8xateh31qfamh6vb`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`),
  CONSTRAINT `FKjynqyp11wv5hoec8lu5jrvqm4`
    FOREIGN KEY (`project_id`)
    REFERENCES `matching`.`project` (`id`))
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
-- Table `matching`.`study_techstack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matching`.`study_techstack` (
  `techstack_id` INT NOT NULL,
  `study_id` INT NOT NULL,
  PRIMARY KEY (`study_id`, `techstack_id`),
  INDEX `FK4kyayh7vr4n5hiksjx56pjowc` (`techstack_id` ASC) VISIBLE,
  CONSTRAINT `FK4kyayh7vr4n5hiksjx56pjowc`
    FOREIGN KEY (`techstack_id`)
    REFERENCES `matching`.`techstack` (`id`),
  CONSTRAINT `FK9ivtm9mh1e7wc0irkwofwv9e7`
    FOREIGN KEY (`study_id`)
    REFERENCES `matching`.`study` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
