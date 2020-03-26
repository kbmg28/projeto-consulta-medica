----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
drop table if exists compromisso;
drop table if exists pessoa;
drop table if exists medico;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE SEQUENCE "PUBLIC"."SEQUENCE_PESSOA"          START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SEQUENCE_MEDICO"          START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SEQUENCE_COMPROMISSO"     START WITH 1 BELONGS_TO_TABLE;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE CACHED TABLE "PUBLIC"."PESSOA"(
    "ID_PESSOA" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQUENCE_PESSOA" 
                NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SEQUENCE_PESSOA",
    "EMAIL" VARCHAR(255) NOT NULL,
    "NOME_COMPLETO" VARCHAR(255) NOT NULL,
    "SENHA" VARCHAR(255) NOT NULL
);

CREATE CACHED TABLE "PUBLIC"."MEDICO"(
    "ID_MEDICO" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQUENCE_MEDICO" 
                NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SEQUENCE_MEDICO",
    "NOME_MEDICO" VARCHAR(255) NOT NULL
);

CREATE CACHED TABLE "PUBLIC"."COMPROMISSO"(
    "ID_COMPROMISSO" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQUENCE_COMPROMISSO" 
                NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SEQUENCE_COMPROMISSO",
    "DATA_HORA_CONSULTA" TIMESTAMP NOT NULL,
    "MEDICO_ID" BIGINT,
    "PESSOA_ID" BIGINT
);

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE "PUBLIC"."PESSOA" ADD CONSTRAINT "PUBLIC"."ID_PESSOA_PK" PRIMARY KEY("ID_PESSOA");
ALTER TABLE "PUBLIC"."MEDICO" ADD CONSTRAINT "PUBLIC"."ID_MEDICO_PK" PRIMARY KEY("ID_MEDICO");
ALTER TABLE "PUBLIC"."COMPROMISSO" ADD CONSTRAINT "PUBLIC"."ID_COMPROMISSO_PK" PRIMARY KEY("ID_COMPROMISSO");

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE "PUBLIC"."COMPROMISSO" ADD CONSTRAINT "PUBLIC"."COMPROMISSO__MEDICO_ID_FK" FOREIGN KEY("MEDICO_ID") REFERENCES "PUBLIC"."MEDICO"("ID_MEDICO") NOCHECK;
ALTER TABLE "PUBLIC"."COMPROMISSO" ADD CONSTRAINT "PUBLIC"."COMPROMISSO__PESSOA_ID_FK" FOREIGN KEY("PESSOA_ID") REFERENCES "PUBLIC"."PESSOA"("ID_PESSOA") NOCHECK;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


INSERT INTO "PUBLIC"."PESSOA" VALUES
(1, 'tony@teste.com', 'TONY STARK', '$2a$10$kpcJubTuRRaitSwoxWSjvOdnzH7Hu7gca1Xfbmq91FkuIVsmdw3q6'),
(2, 'thor@teste.com', 'THOR DE ARGARD', '$2a$10$/DNI3ICG0MqjPmpZEYJfGuyjPZ5UoADBlcY8MYnCUq3L2P5ubzG1G');

INSERT INTO "PUBLIC"."MEDICO" VALUES
(1, 'ALEXANDER FLEMING'),
(2, 'BEN CARSON'),
(3, 'BENNET OMALU');

INSERT INTO "PUBLIC"."COMPROMISSO" VALUES
(1, TIMESTAMP '2020-04-01 03:09:05.543', 1, 2),
(2, TIMESTAMP '2020-04-02 03:09:05.543', 2, 2),
(3, TIMESTAMP '2020-04-03 03:09:05.543', 1, 2),
(4, TIMESTAMP '2020-04-04 03:09:05.543', 3, 1),
(5, TIMESTAMP '2020-04-04 04:09:05.543', 2, 1),
(6, TIMESTAMP '2020-04-05 03:09:05.543', 2, 1);