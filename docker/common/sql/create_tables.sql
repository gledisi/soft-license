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
-- Active; Inactive; Retired; On_Leave; Pending
CREATE TABLE IF NOT EXISTS public.employee_Status
(
    id integer NOT NULL,
    name character varying(50) ,
    CONSTRAINT employee_status_pkey PRIMARY KEY (id)
    );
CREATE TABLE IF NOT EXISTS public.employee
(
    id integer NOT NULL,
    user_id integer ,
    organization_id integer ,
    status_id integer,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT employee_organization_fk FOREIGN KEY (organization_id) REFERENCES public.organizations (id),
    CONSTRAINT employee_user_fk FOREIGN KEY (user_id) REFERENCES public.users (id),
    CONSTRAINT employee_status_fk FOREIGN KEY (status_id) REFERENCES public.employee_Status (id)
    );

CREATE TABLE IF NOT EXISTS public.software
(
    id integer NOT NULL ,
    product_name character varying(100)  NOT NULL,
    description character varying(255),
    logo bytea,
    CONSTRAINT software_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.license_type
(
    id integer NOT NULL ,
    name character varying(100) ,
    software_id integer NOT NULL,
    CONSTRAINT license_type_pkey PRIMARY KEY (id),
    CONSTRAINT license_type_software_fk FOREIGN KEY (software_id) REFERENCES public.software (id)
    );

CREATE TABLE IF NOT EXISTS public.license_status
(
    id integer NOT NULL ,
    name character varying(100) ,
    CONSTRAINT license_status_pkey PRIMARY KEY (id)
    );


CREATE TABLE IF NOT EXISTS public.licenses
(
    id integer NOT NULL ,
    organization_id bigint NOT NULL,
    license_type_id integer,
    purchased_date date,
    expiry_date date,
    annual_cost_per_license numeric,
    max_number integer,
    allocated integer,
    CONSTRAINT licenses_pkey PRIMARY KEY (id),
    CONSTRAINT licenses_license_type_fk FOREIGN KEY (license_type_id) REFERENCES public.license_type (id),
    CONSTRAINT licenses_organization_fk FOREIGN KEY (organization_id) REFERENCES public.organizations (id)
    );
-- Allocated; Awaiting Inventory; Permanent; Unallocated.
CREATE TABLE IF NOT EXISTS public.license_allocation_status
(
    id integer NOT NULL ,
    name character varying(100) ,
    CONSTRAINT license_allocation_status_pkey PRIMARY KEY (id)
    );


CREATE TABLE IF NOT EXISTS public.license_allocation
(
    id integer NOT NULL ,
    license_id bigint NOT NULL,
    assigned_employee_id integer,
    allocation_date date,
    deleted_date date,
    status_id integer,
    CONSTRAINT license_allocation_pkey PRIMARY KEY (id),
    CONSTRAINT license_allocation_licenses_fk FOREIGN KEY (license_id) REFERENCES public.licenses (id),
    CONSTRAINT license_allocation_employee_fk FOREIGN KEY (assigned_employee_id) REFERENCES public.employee (id),
    CONSTRAINT license_allocation_status_fk FOREIGN KEY (status_id) REFERENCES public.license_allocation_status (id)
    );

-- Pending; Canceled; Approved;
CREATE TABLE IF NOT EXISTS public.license_request_status
(
    id integer NOT NULL ,
    name character varying(100) ,
    CONSTRAINT license_request_status_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.employee_license_request
(
    id integer NOT NULL ,
    license_id bigint NOT NULL,
    employee_id bigint NOT NULL,
    reason text  NOT NULL,
    approved_by integer,
    request_date date,
    approved_date date,
    status_id integer,
    CONSTRAINT license_request_pkey PRIMARY KEY (id),
    CONSTRAINT license_request_licenses_fk FOREIGN KEY (license_id) REFERENCES public.licenses (id),
    CONSTRAINT license_request_employee_fk FOREIGN KEY (employee_id) REFERENCES public.employee (id),
    CONSTRAINT license_request_approved_fk FOREIGN KEY (approved_by) REFERENCES public.employee (id),
    CONSTRAINT license_request_status_fk FOREIGN KEY (status_id) REFERENCES public.license_request_status (id)
    );