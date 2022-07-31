CREATE TABLE IF NOT EXISTS public.role
(
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    code character varying(5),
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL ,
    first_name character varying(255) ,
    last_name character varying(255) ,
    username character varying(255) ,
    email character varying(255) ,
    password character varying(255) ,
    created_by integer,
    created_on character varying(255) ,
    role_id integer NOT NULL,
    deleted boolean,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_fk FOREIGN KEY (role_id) REFERENCES public.role (id)
);

CREATE TABLE IF NOT EXISTS public.organizations
(
    id integer NOT NULL,
    name character varying(100) ,
    contact_name character varying(100) ,
    contact_email character varying(255) ,
    contact_phone character varying(20) ,
    CONSTRAINT organizations_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.licenses
(
    id integer NOT NULL ,
    organization_id bigint NOT NULL,
    license_type character varying(100)  NOT NULL,
    product_name character varying(100)  NOT NULL,
    max_number integer,
    allocated integer,
    CONSTRAINT licenses_pkey PRIMARY KEY (id)
);