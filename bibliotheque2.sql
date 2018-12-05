-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 04 déc. 2018 à 23:43
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bibliotheque2`
--

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE IF NOT EXISTS `emprunt` (
  `id_emprunt` int(11) NOT NULL AUTO_INCREMENT,
  `id_livre` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `date` varchar(32) NOT NULL,
  `duree` int(11) NOT NULL,
  PRIMARY KEY (`id_emprunt`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `oeuvre`
--

DROP TABLE IF EXISTS `oeuvre`;
CREATE TABLE IF NOT EXISTS `oeuvre` (
  `id_oeuvre` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(32) NOT NULL,
  `auteur` varchar(32) NOT NULL,
  `titre` varchar(128) NOT NULL,
  `type` varchar(16) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `est_disponible` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_oeuvre`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `oeuvre`
--

INSERT INTO `oeuvre` (`id_oeuvre`, `isbn`, `auteur`, `titre`, `type`, `est_disponible`) VALUES
(1, '852863', 'J K Rowling', 'Harry Potter à l\'école des sorciers', 'Livre', 1);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `numero_salle` varchar(16) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `nb_place` int(8) NOT NULL,
  `have_tableau` tinyint(1) NOT NULL DEFAULT '0',
  `have_projecteur` tinyint(1) NOT NULL DEFAULT '0',
  `is_disponible` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `codePermanent` varchar(32) NOT NULL,
  `prenom` varchar(32) NOT NULL,
  `nom` varchar(32) NOT NULL,
  `droit` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `solde` int(11) NOT NULL,
  PRIMARY KEY (`codePermanent`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`codePermanent`, `prenom`, `nom`, `droit`, `password`, `solde`) VALUES
('BLAL19019408', 'Louis', 'Blasselle', 'etudiant', 'azerty', 0),
('MANB22079601', 'Benoît', 'MANHES', 'etudiant', '1234', 5),
('WANB01010101', 'Bob', 'WANSKI', 'professeur', 'wanski', 15),
('THEP20047001', 'Patricia', 'THEZE', 'admin', 'theze', 50);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
