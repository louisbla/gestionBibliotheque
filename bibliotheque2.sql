-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 06 déc. 2018 à 22:37
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
  `codePermanent` varchar(32) NOT NULL,
  `date` varchar(32) NOT NULL,
  `duree` int(11) NOT NULL,
  PRIMARY KEY (`id_emprunt`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `emprunt`
--

INSERT INTO `emprunt` (`id_emprunt`, `id_livre`, `codePermanent`, `date`, `duree`) VALUES
(1, 2, 'BLAL19019408', 'Thu Dec 06 16:22:40 EST 2018', 5),
(2, 10, 'MANB22079601', 'Thu Dec 06 16:38:01 EST 2018', 5),
(3, 8, 'WANB01010101', 'Thu Dec 06 16:38:29 EST 2018', 5);

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
  `resume` varchar(500) NOT NULL,
  PRIMARY KEY (`id_oeuvre`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `oeuvre`
--

INSERT INTO `oeuvre` (`id_oeuvre`, `isbn`, `auteur`, `titre`, `type`, `est_disponible`, `resume`) VALUES
(2, '65942', 'J.K.Rowling', 'Harry Potter 2', 'Livre', 1, ''),
(3, '25641', 'Michelin', 'Carte du Canada', 'Carte', 1, ''),
(4, '65943', 'J.K.Rowling', 'Harry Potter 1', 'Livre', 1, 'tome 2'),
(5, 'CMF1', 'Michelin', 'Carte de la France', 'Carte', 1, 'Carte de la France à l\'échelle 1: 1 000 000'),
(6, 'GM1', 'Math Ed', 'Guide Matlab', 'Livre', 1, 'Guide pour débuter et apprendre les bases sur le logiciel Matlab'),
(7, 'LM731', 'Le Monde', 'Le Monde article 73', 'Periodique', 1, 'article n°73 du journal Le monde'),
(8, 'LM741', 'Le Monde', 'Le Monde article 74', 'Periodique', 1, 'article 74 du journal Le Monde (version française)'),
(9, 'LIDVD1', 'Adobe', 'Learn Illustrator', 'DVD', 1, 'A DVD to learn how to use the software Illustrator'),
(10, 'LPDVD1', 'Adobe', 'Learn Photoshop', 'DVD', 1, 'A DVD to learn how use the software Photoshop. '),
(11, 'MES1', 'Science Easy', 'Mathematique - PSI', 'Livre', 1, 'Livre d\'exercice et de cours en maths pour les prépa 1 année en filière PSI'),
(12, 'MES2', 'Science Easy', 'Physique - PSI', 'Livre', 1, 'Livre d\'exercice et de lesson de physiques pour le étudiants prépa en 1 année'),
(13, 'MES3', 'Science Easy', 'SI - PSI', 'Livre', 1, 'Livre de cours de sciences industrielles pour éleves de prépa PSI en 1 année'),
(14, 'DFA1', 'Larousse', 'Dictionnaire Français - Anglais', 'Livre', 1, 'Dictionnaire français - anglais et anglais - français avec plus de 10 000 mots'),
(15, 'DFA2', 'Larousse', 'Dictionnaire Italien - français', 'Livre', 1, 'Dictionnaire français - italien et italien - français');

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
('THEP20047001', 'Patricia', 'THEZE', 'admin', 'theze', 50),
('admin', 'admin', 'admin', 'admin', 'admin', 2),
('NEMJ02020201', 'Jean', 'Michel', 'professeur', '1234', 12),
('LIBB23059801', 'Baptiste', 'Libourel', 'etudiant', 'lib', 0),
('MARL24119601', 'Lucas', 'Martinez', 'etudiant', 'marti', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
