-- Drop the existing table
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
