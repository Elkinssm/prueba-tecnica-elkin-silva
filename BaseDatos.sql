--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-02-20 19:47:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3349 (class 1262 OID 16873)
-- Name: prueba; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE prueba WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';


ALTER DATABASE prueba OWNER TO postgres;

\connect prueba

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 17036)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    direccion character varying(255),
    edad integer,
    genero integer,
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255),
    contrasenia character varying(255),
    estado boolean
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17035)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 215
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- TOC entry 218 (class 1259 OID 17045)
-- Name: cuenta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta (
    id bigint NOT NULL,
    estado boolean,
    numero_cuenta character varying(255),
    saldo_inicial double precision,
    tipo_cuenta integer,
    cliente_id bigint
);


ALTER TABLE public.cuenta OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17044)
-- Name: cuenta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cuenta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cuenta_id_seq OWNER TO postgres;

--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 217
-- Name: cuenta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cuenta_id_seq OWNED BY public.cuenta.id;


--
-- TOC entry 214 (class 1259 OID 16953)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17052)
-- Name: movimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimiento (
    id bigint NOT NULL,
    fecha character varying(255),
    movimiento character varying(255),
    saldo double precision,
    saldo_actual double precision,
    saldo_anterior double precision,
    saldo_inicial double precision,
    tipo_movimiento integer,
    valor double precision,
    cuenta_id bigint
);


ALTER TABLE public.movimiento OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17051)
-- Name: movimiento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movimiento_id_seq OWNER TO postgres;

--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 219
-- Name: movimiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimiento_id_seq OWNED BY public.movimiento.id;


--
-- TOC entry 3184 (class 2604 OID 17039)
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- TOC entry 3185 (class 2604 OID 17048)
-- Name: cuenta id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta ALTER COLUMN id SET DEFAULT nextval('public.cuenta_id_seq'::regclass);


--
-- TOC entry 3186 (class 2604 OID 17055)
-- Name: movimiento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento ALTER COLUMN id SET DEFAULT nextval('public.movimiento_id_seq'::regclass);


--
-- TOC entry 3339 (class 0 OID 17036)
-- Dependencies: 216
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cliente (id, direccion, edad, genero, identificacion, nombre, telefono, contrasenia, estado) VALUES (1, 'Otavalo sn y principal', NULL, NULL, NULL, 'Jose Lema', '098254785', '1234', true);
INSERT INTO public.cliente (id, direccion, edad, genero, identificacion, nombre, telefono, contrasenia, estado) VALUES (2, 'Amazonas y NNUU', NULL, NULL, NULL, 'Marianela Montalvo', '097548965', '5678', true);
INSERT INTO public.cliente (id, direccion, edad, genero, identificacion, nombre, telefono, contrasenia, estado) VALUES (3, '13 junio y Equinoccial', NULL, NULL, NULL, 'Juan Osorio', '098874587', '1245', true);
INSERT INTO public.cliente (id, direccion, edad, genero, identificacion, nombre, telefono, contrasenia, estado) VALUES (5, 'string', NULL, NULL, NULL, 'string', 'string', 'string', true);
INSERT INTO public.cliente (id, direccion, edad, genero, identificacion, nombre, telefono, contrasenia, estado) VALUES (6, '13 junio y Equinoccial', NULL, NULL, NULL, 'Juan Osorio', '098874587', '1245', true);


--
-- TOC entry 3341 (class 0 OID 17045)
-- Dependencies: 218
-- Data for Name: cuenta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, cliente_id) VALUES (5, true, '585545', 1000, 1, 1);
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, cliente_id) VALUES (1, true, '478758', 850, 0, 1);
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, cliente_id) VALUES (2, true, '225487', 1300, 1, 2);
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, cliente_id) VALUES (3, true, '495878', 300, 0, 3);
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, cliente_id) VALUES (4, true, '496825', 0, 0, 2);


--
-- TOC entry 3343 (class 0 OID 17052)
-- Dependencies: 220
-- Data for Name: movimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movimiento (id, fecha, movimiento, saldo, saldo_actual, saldo_anterior, saldo_inicial, tipo_movimiento, valor, cuenta_id) VALUES (1, '15/2/2022', 'Retiro de 575.0', 0, 1425, 2000, 2000, 0, 575, 1);
INSERT INTO public.movimiento (id, fecha, movimiento, saldo, saldo_actual, saldo_anterior, saldo_inicial, tipo_movimiento, valor, cuenta_id) VALUES (2, '10/2/2022', 'Depósito de 600.0', 0, 700, 100, 100, 1, 600, 2);
INSERT INTO public.movimiento (id, fecha, movimiento, saldo, saldo_actual, saldo_anterior, saldo_inicial, tipo_movimiento, valor, cuenta_id) VALUES (3, '28/2/2022', 'Depósito de 150.0', 0, 150, 0, 0, 1, 150, 3);
INSERT INTO public.movimiento (id, fecha, movimiento, saldo, saldo_actual, saldo_anterior, saldo_inicial, tipo_movimiento, valor, cuenta_id) VALUES (4, '8/2/2022', 'Retiro de 540.0', 0, 0, 540, 540, 0, 540, 4);


--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 215
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 6, true);


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 217
-- Name: cuenta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuenta_id_seq', 6, true);


--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 214
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 37, true);


--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 219
-- Name: movimiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimiento_id_seq', 8, true);


--
-- TOC entry 3188 (class 2606 OID 17043)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 3190 (class 2606 OID 17050)
-- Name: cuenta cuenta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id);


--
-- TOC entry 3192 (class 2606 OID 17059)
-- Name: movimiento movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (id);


--
-- TOC entry 3194 (class 2606 OID 17065)
-- Name: movimiento fk4ea11fe7p3xa1kwwmdgi9f2fi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT fk4ea11fe7p3xa1kwwmdgi9f2fi FOREIGN KEY (cuenta_id) REFERENCES public.cuenta(id);


--
-- TOC entry 3193 (class 2606 OID 17060)
-- Name: cuenta fk4p224uogyy5hmxvn8fwa2jlug; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk4p224uogyy5hmxvn8fwa2jlug FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


-- Completed on 2023-02-20 19:47:08

--
-- PostgreSQL database dump complete
--

