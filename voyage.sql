-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 28 avr. 2021 à 00:51
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `voyage`
--

-- --------------------------------------------------------

--
-- Structure de la table `migration_versions`
--

DROP TABLE IF EXISTS `migration_versions`;
CREATE TABLE IF NOT EXISTS `migration_versions` (
  `version` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
  `executed_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `migration_versions`
--

INSERT INTO `migration_versions` (`version`, `executed_at`) VALUES
('20210331090527', '2021-03-31 11:28:30');

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pourcentage` int(11) NOT NULL,
  `date_debut` datetime NOT NULL,
  `datefin` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`id`, `nom`, `pourcentage`, `date_debut`, `datefin`) VALUES
(6, 'promo', 50, '2021-04-27 00:00:00', '2021-04-29 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `num_tel` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `cin` varchar(8) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `compte_type` varchar(20) NOT NULL,
  `date_naiss` date NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `num_tel`, `password`, `cin`, `status`, `compte_type`, `date_naiss`, `created_at`) VALUES
(1, 'elyes', 'bensaid', 'ilyes.bensaid@esprit.tn', '98888888', 'elyes98', '09635352', 0, 'sfffff', '2021-04-28', '2021-04-27 22:23:19');

-- --------------------------------------------------------

--
-- Structure de la table `voyage`
--

DROP TABLE IF EXISTS `voyage`;
CREATE TABLE IF NOT EXISTS `voyage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `destination` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_destination` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_voyage` datetime DEFAULT NULL,
  `prix` double NOT NULL,
  `id_promotion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `voyage`
--

INSERT INTO `voyage` (`id`, `destination`, `image_destination`, `description`, `date_voyage`, `prix`, `id_promotion`) VALUES
(2, 'djerba', 'C:\\Users\\user\\Desktop\\92020183_337286730597976_4849222732375654400_n.jpg', 'rgjkknjg', '2025-01-01 00:00:00', 640, 6),
(3, 'tunis2', 'C:\\Users\\user\\Desktop\\142197217_431506274662427_7968325526757835189_o.jpg', 'voyage', '2026-01-01 00:00:00', 120, NULL),
(5, 'tunis', '90d274428e7a8fcab6976752c7e87aba.jpeg', 'mmmmmm', '2020-01-01 00:00:00', 898, NULL),
(7, 'ezzahra', 'img.png', 'beau', '2026-01-01 00:00:00', 120.5, NULL),
(8, 'jerba', 'C:\\Users\\user\\Desktop\\142197217_431506274662427_7968325526757835189_o.jpg', 'jaw', '2021-04-22 00:00:00', 1400, NULL),
(9, 'jerba2', 'C:\\Users\\user\\Desktop\\3eme\\projet\\voyage\\src\\voyage\\tick.jpg', 'jaw', '2021-04-22 00:00:00', 1500, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
