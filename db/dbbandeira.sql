-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 24-Ago-2020 às 00:05
-- Versão do servidor: 5.7.28
-- versão do PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbbandeira`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbbandeira`
--

DROP TABLE IF EXISTS `tbbandeira`;
CREATE TABLE IF NOT EXISTS `tbbandeira` (
  `idPais` int(3) NOT NULL AUTO_INCREMENT,
  `nomePais` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `nomeOficial` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `capital` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `codISO` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `continente` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `bandeira` blob NOT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=MyISAM AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `tbbandeira`
--

INSERT INTO `tbbandeira` (`idPais`, `nomePais`, `nomeOficial`, `capital`, `codISO`, `continente`, `bandeira`) VALUES
(64, 'Brasil', 'Rep. Fed. Brasileira', 'Brasilia', 'BRA', 'América do Sul', 0x433a5c55736572735c65756372695c50696374757265735c706e675c6272617a696c2e706e67),
(65, 'Nederland', 'Nederland', 'Amsterdã', 'NLD', 'Europa', 0x433a5c55736572735c65756372695c50696374757265735c706e675c6e65746865726c616e64732e706e67),
(66, 'Quênia', 'Republica do Quênia', 'Nairóbi', 'KEN', 'África', 0x433a5c55736572735c65756372695c50696374757265735c706e675c6b656e79612e706e67),
(69, 'Rússia', 'Federação Russa', 'Moscou', 'RUS', 'Eurásia', 0x433a5c55736572735c65756372695c50696374757265735c706e675c7275737369612e706e67),
(68, 'China', 'República Popular da China', 'Pequim', 'CHN', 'Ásia', 0x433a5c55736572735c65756372695c50696374757265735c706e675c6368696e612e706e67),
(70, 'Alemanha', 'República Federal da Alemanha', 'Berlim', 'DE', 'Europa', 0x433a5c55736572735c65756372695c50696374757265735c706e675c6765726d616e792e706e67),
(71, 'Marrocos', 'Reino de Marrocos', 'Rabate', 'MAR', 'África', 0x433a5c55736572735c65756372695c50696374757265735c706e675c6d6f726f63636f2e706e67),
(72, 'Reino Unido', 'Reino Unido da Grã-Bretanha e Irlanda do Norte', 'Londres', 'GBR', 'Europa', 0x433a5c55736572735c65756372695c50696374757265735c706e675c756e697465642d6b696e67646f6d2e706e67),
(73, 'México', 'Estados Unidos Mexicanos', 'Cidade do México', 'MEX', 'América do Norte', 0x433a5c55736572735c65756372695c50696374757265735c706e675c6d657869636f2e706e67);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
