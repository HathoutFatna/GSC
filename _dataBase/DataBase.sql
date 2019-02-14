﻿-- MySQL Script generated by MySQL Workbench
-- Wed Apr 25 17:35:10 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema BDD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BDD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BDD` DEFAULT CHARACTER SET utf8 ;
USE `BDD` ;

-- -----------------------------------------------------
-- Table `BDD`.`Sport_Idividuel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`Sport_Idividuel` (
  `idSport_Idividuel` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  PRIMARY KEY (`idSport_Idividuel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDD`.`Activité_Culturelle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`Activité_Culturelle` (
  `idActivité_Culturelle` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  PRIMARY KEY (`idActivité_Culturelle`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDD`.`sport_collectif`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`sport_collectif` (
  `idsport_collectif` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `Nombre_par_equipe` INT NULL,
  PRIMARY KEY (`idsport_collectif`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDD`.`équipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`équipe` (
  `idéquipe` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `nb_membre` INT NULL,
  `sport_collectif_idsport_collectif` INT NOT NULL,
  PRIMARY KEY (`idéquipe`, `sport_collectif_idsport_collectif`),
  CONSTRAINT `fk_équipe_sport_collectif1`
    FOREIGN KEY (`sport_collectif_idsport_collectif`)
    REFERENCES `BDD`.`sport_collectif` (`idsport_collectif`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_équipe_sport_collectif1_idx` ON `BDD`.`équipe` (`sport_collectif_idsport_collectif` ASC);


-- -----------------------------------------------------
-- Table `BDD`.`Etudiant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`Etudiant` (
  `idETUDIANT` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `Prénom` VARCHAR(45) NULL,
  `DDN` DATE NULL,
  `Email` VARCHAR(45) NULL,
  `Telephone` VARCHAR(45) NULL,
  `poid` VARCHAR(45) NULL,
  `taille` VARCHAR(45) NULL,
  `Groupage` VARCHAR(45) NULL,
  `photo` BLOB NULL,
  `Adresse` VARCHAR(200) NULL,
  `Sport_Idividuel_idSport_Idividuel` INT NULL,
  `Activité_Culturelle_idActivité_Culturelle` INT NULL,
  `équipe_idéquipe` INT NULL,
  `équipe_sport_collectif_idsport_collectif` INT NULL,
  PRIMARY KEY (`idETUDIANT`),
  CONSTRAINT `fk_Etudiant_Sport_Idividuel1`
    FOREIGN KEY (`Sport_Idividuel_idSport_Idividuel`)
    REFERENCES `BDD`.`Sport_Idividuel` (`idSport_Idividuel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Etudiant_Activité_Culturelle1`
    FOREIGN KEY (`Activité_Culturelle_idActivité_Culturelle`)
    REFERENCES `BDD`.`Activité_Culturelle` (`idActivité_Culturelle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Etudiant_équipe1`
    FOREIGN KEY (`équipe_idéquipe` , `équipe_sport_collectif_idsport_collectif`)
    REFERENCES `BDD`.`équipe` (`idéquipe` , `sport_collectif_idsport_collectif`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Etudiant_Sport_Idividuel1_idx` ON `BDD`.`Etudiant` (`Sport_Idividuel_idSport_Idividuel` ASC);

CREATE INDEX `fk_Etudiant_Activité_Culturelle1_idx` ON `BDD`.`Etudiant` (`Activité_Culturelle_idActivité_Culturelle` ASC);

CREATE INDEX `fk_Etudiant_équipe1_idx` ON `BDD`.`Etudiant` (`équipe_idéquipe` ASC, `équipe_sport_collectif_idsport_collectif` ASC);


-- -----------------------------------------------------
-- Table `BDD`.`Entraineur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`Entraineur` (
  `idEntraineur` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `Prénom` VARCHAR(45) NULL,
  `DateNaissance` DATE NULL,
  `Email` VARCHAR(45) NULL,
  `Telephone` VARCHAR(45) NULL,
  `adresse` VARCHAR(45) NULL,
  `photo` BLOB NULL,
  `équipe_idéquipe` INT NOT NULL,
  `équipe_sport_collectif_idsport_collectif` INT NOT NULL,
  PRIMARY KEY (`idEntraineur`, `équipe_idéquipe`, `équipe_sport_collectif_idsport_collectif`),
  CONSTRAINT `fk_Entraineur_équipe1`
    FOREIGN KEY (`équipe_idéquipe` , `équipe_sport_collectif_idsport_collectif`)
    REFERENCES `BDD`.`équipe` (`idéquipe` , `sport_collectif_idsport_collectif`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Entraineur_équipe1_idx` ON `BDD`.`Entraineur` (`équipe_idéquipe` ASC, `équipe_sport_collectif_idsport_collectif` ASC);


-- -----------------------------------------------------
-- Table `BDD`.`Salles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`Salles` (
  `idSalles` INT NOT NULL AUTO_INCREMENT,
  `Lieu` VARCHAR(45) NULL,
  `Nom` VARCHAR(45) NULL,
  PRIMARY KEY (`idSalles`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDD`.`matériel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`matériel` (
  `idmatériel` INT NOT NULL AUTO_INCREMENT,
  `Désignation` VARCHAR(45) NULL,
  `nombre` INT NULL,
  PRIMARY KEY (`idmatériel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDD`.`evenement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`evenement` (
  `idEvenement` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `DateDébut` DATE NULL,
  `Remarque` VARCHAR(45) NULL,
  `DateFin` DATE NULL,
  `Salles_idSalles` INT NULL,
  `Activité_Culturelle_idActivité_Culturelle` INT NULL,
  `matériel_idmatériel` INT NULL,
  PRIMARY KEY (`idEvenement`),
  CONSTRAINT `fk_evenement_Salles1`
    FOREIGN KEY (`Salles_idSalles`)
    REFERENCES `BDD`.`Salles` (`idSalles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evenement_Activité_Culturelle1`
    FOREIGN KEY (`Activité_Culturelle_idActivité_Culturelle`)
    REFERENCES `BDD`.`Activité_Culturelle` (`idActivité_Culturelle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evenement_matériel1`
    FOREIGN KEY (`matériel_idmatériel`)
    REFERENCES `BDD`.`matériel` (`idmatériel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_evenement_Salles1_idx` ON `BDD`.`evenement` (`Salles_idSalles` ASC);

CREATE INDEX `fk_evenement_Activité_Culturelle1_idx` ON `BDD`.`evenement` (`Activité_Culturelle_idActivité_Culturelle` ASC);

CREATE INDEX `fk_evenement_matériel1_idx` ON `BDD`.`evenement` (`matériel_idmatériel` ASC);


-- -----------------------------------------------------
-- Table `BDD`.`Compétition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`Compétition` (
  `idCompétition` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `Description` VARCHAR(45) NULL,
  `DateDebut` DATE NULL,
  `DateFin` DATE NULL,
  `équipe_idéquipe` INT NULL,
  `équipe_sport_collectif_idsport_collectif` INT NULL,
  `Sport_Idividuel_idSport_Idividuel` INT NULL,
  `Salles_idSalles` INT NULL,
  `matériel_idmatériel` INT NULL,
  PRIMARY KEY (`idCompétition`),
  CONSTRAINT `fk_Compétition_équipe1`
    FOREIGN KEY (`équipe_idéquipe` , `équipe_sport_collectif_idsport_collectif`)
    REFERENCES `BDD`.`équipe` (`idéquipe` , `sport_collectif_idsport_collectif`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compétition_Sport_Idividuel1`
    FOREIGN KEY (`Sport_Idividuel_idSport_Idividuel`)
    REFERENCES `BDD`.`Sport_Idividuel` (`idSport_Idividuel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compétition_Salles1`
    FOREIGN KEY (`Salles_idSalles`)
    REFERENCES `BDD`.`Salles` (`idSalles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compétition_matériel1`
    FOREIGN KEY (`matériel_idmatériel`)
    REFERENCES `BDD`.`matériel` (`idmatériel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Compétition_équipe1_idx` ON `BDD`.`Compétition` (`équipe_idéquipe` ASC, `équipe_sport_collectif_idsport_collectif` ASC);

CREATE INDEX `fk_Compétition_Sport_Idividuel1_idx` ON `BDD`.`Compétition` (`Sport_Idividuel_idSport_Idividuel` ASC);

CREATE INDEX `fk_Compétition_Salles1_idx` ON `BDD`.`Compétition` (`Salles_idSalles` ASC);

CREATE INDEX `fk_Compétition_matériel1_idx` ON `BDD`.`Compétition` (`matériel_idmatériel` ASC);


-- -----------------------------------------------------
-- Table `BDD`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDD`.`Entrainement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDD`.`Entrainement` (
  `idEntrainement` INT NOT NULL,
  `Nom` VARCHAR(45) NULL,
  `Date` DATE NULL,
  `Sport_Idividuel_idSport_Idividuel` INT NULL,
  `équipe_idéquipe` INT NULL,
  `équipe_sport_collectif_idsport_collectif` INT NULL,
  `Salles_idSalles` INT NULL,
  `matériel_idmatériel` INT NULL,
  PRIMARY KEY (`idEntrainement`),
  CONSTRAINT `fk_Entrainement_Sport_Idividuel1`
    FOREIGN KEY (`Sport_Idividuel_idSport_Idividuel`)
    REFERENCES `BDD`.`Sport_Idividuel` (`idSport_Idividuel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Entrainement_équipe1`
    FOREIGN KEY (`équipe_idéquipe` , `équipe_sport_collectif_idsport_collectif`)
    REFERENCES `BDD`.`équipe` (`idéquipe` , `sport_collectif_idsport_collectif`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Entrainement_Salles1`
    FOREIGN KEY (`Salles_idSalles`)
    REFERENCES `BDD`.`Salles` (`idSalles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Entrainement_matériel1`
    FOREIGN KEY (`matériel_idmatériel`)
    REFERENCES `BDD`.`matériel` (`idmatériel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Entrainement_Sport_Idividuel1_idx` ON `BDD`.`Entrainement` (`Sport_Idividuel_idSport_Idividuel` ASC);

CREATE INDEX `fk_Entrainement_équipe1_idx` ON `BDD`.`Entrainement` (`équipe_idéquipe` ASC, `équipe_sport_collectif_idsport_collectif` ASC);

CREATE INDEX `fk_Entrainement_Salles1_idx` ON `BDD`.`Entrainement` (`Salles_idSalles` ASC);

CREATE INDEX `fk_Entrainement_matériel1_idx` ON `BDD`.`Entrainement` (`matériel_idmatériel` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;