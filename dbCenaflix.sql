/*
phpMyAdmin SQL Dump
version 5.2.0
https://www.phpmyadmin.net/

Host: 127.0.0.1
Tempo de geração: 23-Jan-2023 às 16:41
Versão do servidor: 10.4.27-MariaDB
versão do PHP: 8.1.12
*/

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

/*
create database cenaflix;
use cenaflix;

CREATE TABLE `filmes` (
  `id` int(11) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `datalancamento` date NOT NULL,
  `categoria` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `filmes`
	ADD PRIMARY KEY (`id`);
    
ALTER TABLE `filmes`
	MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;


SELECT DATE_FORMAT(datalancamento, '%d/%m/%Y') AS data_formatada FROM filmes;
INSERT INTO filmes (id, nome, datalancamento, categoria) VALUES (1, 'A éra do gelo 4', STR_TO_DATE('13/03/2024', '%d/%m/%Y'), 'Animação');

SELECT categoria FROM filmes;

UPDATE filmes SET categoria = 'acao' WHERE id = 8;

DELETE FROM filmes WHERE id >0;
*/ 


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
