SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MisRutinasApp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `MisRutinasApp` ;

-- -----------------------------------------------------
-- Schema MisRutinasApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MisRutinasApp` DEFAULT CHARACTER SET utf8 ;
USE `MisRutinasApp` ;

-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Rol` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Rol` (
  `id_rol` INT NOT NULL AUTO_INCREMENT,
  `nombre_rol` CHAR(10) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE INDEX `id_rol_UNIQUE` (`id_rol` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NULL,
  `apellido` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `dni` INT NULL,
  `email` VARCHAR(75) NOT NULL,
  `pass` VARCHAR(16) NULL,
  `tel` INT NULL,
  `id_rol` INT NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_rol`),
  UNIQUE INDEX `id_socio_UNIQUE` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_usuario_rol1_idx` (`id_rol` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_rol1`
    FOREIGN KEY (`id_rol`)
    REFERENCES `MisRutinasApp`.`Rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Socio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Socio` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Socio` (
  `id_socio` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_socio`, `id_usuario`),
  UNIQUE INDEX `id_socio_UNIQUE` (`id_socio` ASC) VISIBLE,
  INDEX `fk_socio_usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_socio_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `MisRutinasApp`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Entrenador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Entrenador` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Entrenador` (
  `id_entrenador` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_entrenador`, `id_usuario`),
  INDEX `fk_entrenador_usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_entrenador_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `MisRutinasApp`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Calendario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Calendario` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Calendario` (
  `id_calen` INT NOT NULL AUTO_INCREMENT,
  `hora` TIME NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id_calen`),
  UNIQUE INDEX `id_calen_UNIQUE` (`id_calen` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Clase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Clase` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Clase` (
  `id_clase` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DECIMAL NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  PRIMARY KEY (`id_clase`),
  UNIQUE INDEX `id_clase_UNIQUE` (`id_clase` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Horario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Horario` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Horario` (
  `id_hor` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `inicio` TIME NOT NULL,
  `fin` TIME NOT NULL,
  PRIMARY KEY (`id_hor`),
  UNIQUE INDEX `id_hor_UNIQUE` (`id_hor` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Dia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Dia` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Dia` (
  `id_dia` INT NOT NULL AUTO_INCREMENT,
  `nombre` CHAR(9) NOT NULL,
  PRIMARY KEY (`id_dia`),
  UNIQUE INDEX `id_dia_UNIQUE` (`id_dia` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Turno` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Turno` (
  `id_turno` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `id_dia` INT NOT NULL,
  `id_hor` INT NOT NULL,
  PRIMARY KEY (`id_turno`, `id_dia`, `id_hor`),
  UNIQUE INDEX `id_turno_UNIQUE` (`id_turno` ASC) VISIBLE,
  INDEX `fk_turno_dia1_idx` (`id_dia` ASC) VISIBLE,
  INDEX `fk_turno_horario1_idx` (`id_hor` ASC) VISIBLE,
  CONSTRAINT `fk_turno_dia1`
    FOREIGN KEY (`id_dia`)
    REFERENCES `MisRutinasApp`.`Dia` (`id_dia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turno_horario1`
    FOREIGN KEY (`id_hor`)
    REFERENCES `MisRutinasApp`.`Horario` (`id_hor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Clase_turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Clase_turno` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Clase_turno` (
  `id_clase_turno` INT NOT NULL AUTO_INCREMENT,
  `id_clase` INT NOT NULL,
  `id_turno` INT NOT NULL,
  `id_entrenador` INT NOT NULL,
  PRIMARY KEY (`id_clase_turno`, `id_clase`, `id_turno`, `id_entrenador`),
  INDEX `fk_clase_has_turno_turno1_idx` (`id_turno` ASC) VISIBLE,
  INDEX `fk_clase_has_turno_clase_idx` (`id_clase` ASC) VISIBLE,
  INDEX `fk_clase_turno_entrenador1_idx` (`id_entrenador` ASC) VISIBLE,
  CONSTRAINT `fk_clase_has_turno_clase`
    FOREIGN KEY (`id_clase`)
    REFERENCES `MisRutinasApp`.`Clase` (`id_clase`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clase_has_turno_turno1`
    FOREIGN KEY (`id_turno`)
    REFERENCES `MisRutinasApp`.`Turno` (`id_turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clase_turno_entrenador1`
    FOREIGN KEY (`id_entrenador`)
    REFERENCES `MisRutinasApp`.`Entrenador` (`id_entrenador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Socio_clase_turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Socio_clase_turno` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Socio_clase_turno` (
  `id_soc_cla_tur` INT NOT NULL AUTO_INCREMENT,
  `id_socio` INT NOT NULL,
  `id_clase_turno` INT NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_soc_cla_tur`, `id_socio`, `id_clase_turno`),
  UNIQUE INDEX `id_soc_cla_UNIQUE` (`id_soc_cla_tur` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_socio1_idx` (`id_socio` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_clase_turno1_idx` (`id_clase_turno` ASC) VISIBLE,
  CONSTRAINT `fk_socio_clase_turno_socio1`
    FOREIGN KEY (`id_socio`)
    REFERENCES `MisRutinasApp`.`Socio` (`id_socio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_socio_clase_turno_clase_turno1`
    FOREIGN KEY (`id_clase_turno`)
    REFERENCES `MisRutinasApp`.`Clase_turno` (`id_clase_turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MisRutinasApp`.`Soc_clac_tur_cal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MisRutinasApp`.`Soc_clac_tur_cal` ;

CREATE TABLE IF NOT EXISTS `MisRutinasApp`.`Soc_clac_tur_cal` (
  `id_soc_clac_tur_cal` INT NOT NULL AUTO_INCREMENT,
  `id_soc_clac_tur` INT NOT NULL,
  `id_calen` INT NOT NULL,
  PRIMARY KEY (`id_soc_clac_tur_cal`, `id_soc_clac_tur`, `id_calen`),
  UNIQUE INDEX `id_soc_cla_cal_UNIQUE` (`id_soc_clac_tur_cal` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_calendario_socio_clase_turno1_idx` (`id_soc_clac_tur` ASC) VISIBLE,
  INDEX `fk_socio_clase_turno_calendario_calendario1_idx` (`id_calen` ASC) VISIBLE,
  CONSTRAINT `fk_socio_clase_turno_calendario_socio_clase_turno1`
    FOREIGN KEY (`id_soc_clac_tur`)
    REFERENCES `MisRutinasApp`.`Socio_clase_turno` (`id_soc_cla_tur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_socio_clase_turno_calendario_calendario1`
    FOREIGN KEY (`id_calen`)
    REFERENCES `MisRutinasApp`.`Calendario` (`id_calen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
