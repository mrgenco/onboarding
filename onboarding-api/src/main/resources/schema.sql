-- Drop the existing table
DROP TABLE IF EXISTS public.document_access_level;
DROP TABLE IF EXISTS public.app_user;
DROP TABLE IF EXISTS public.document;

-- Create the new table
CREATE TABLE public.document (
     id serial primary key,
     uuid uuid,
     status integer,
     title varchar(255),
     summary varchar(255),
     created_by bigint,
     created_time timestamp(6),
     last_updated_by bigint,
     last_updated_time timestamp(6)
);

CREATE TABLE public.app_user (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);

CREATE TABLE public.document_access_level (
    id SERIAL PRIMARY KEY,
    document_id INT REFERENCES document(id),
    user_id INT REFERENCES app_user(id),
    access_level VARCHAR(50) NOT NULL
);




