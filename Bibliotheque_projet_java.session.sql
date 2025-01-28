/* -- Add logging statements to debug the script
\echo 'Starting execution of script'
\echo 'Creating table livres'
CREATE TABLE livres (
    isbn VARCHAR(13) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    categorie VARCHAR(100),
    is_available BOOLEAN DEFAULT TRUE
);
\echo 'Creating table membres'
CREATE TABLE membres (
    member_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    date_inscription DATE DEFAULT CURRENT_DATE
);
\echo 'Creating table emprunts'
CREATE TABLE emprunts (
    loan_id SERIAL PRIMARY KEY,
    isbn VARCHAR(13) REFERENCES livres(isbn),
    member_id VARCHAR(50) REFERENCES membres(member_id),
    due_date DATE NOT NULL,
    return_date DATE,
    penalty NUMERIC(10, 2)
);
\echo 'Script execution complete' */

DO $$ 
BEGIN
   EXECUTE (
     SELECT string_agg(format('GRANT ALL PRIVILEGES ON TABLE %I.%I TO postgres;', schemaname, tablename), ' ')
     FROM pg_tables
     WHERE schemaname = 'public'  -- Remplacez par le schéma approprié si nécessaire
   );
END $$;
Select * from livres;
Select * from membres;
