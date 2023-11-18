## OnBoarding App Functional Requirements

1. A user can register & login to the app using following methods:
ldap(intranet only), github(internet only), email accounts(internet & intranet)

2. A user can list the documents which they have been granted access to.

3. A user can read the document which they have been granted access to.

4. A user can create documents in the markdown format and give access to different users via email or a shareable link.

5. A user can save document in different status like Published, Draft, Archived



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










