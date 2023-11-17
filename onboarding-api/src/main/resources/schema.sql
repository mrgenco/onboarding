-- Drop the existing table
DROP TABLE IF EXISTS public.Document;
DROP TABLE IF EXISTS public.User;
DROP TABLE IF EXISTS public.Access;

-- Create the new table
CREATE TABLE public.Document (
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

CREATE TABLE public.Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE public.Access (
    id SERIAL PRIMARY KEY,
    document_id INT REFERENCES Document(id),
    user_id INT REFERENCES Users(id),
    permission_level VARCHAR(50) NOT NULL
);




