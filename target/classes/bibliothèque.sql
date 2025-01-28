-- Create the table for livres (books)
CREATE TABLE livres (
    isbn VARCHAR(13) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    categorie VARCHAR(100),
    is_available BOOLEAN DEFAULT TRUE
);

-- Create the table for membres (members)
CREATE TABLE membres (
    member_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    date_inscription DATE DEFAULT CURRENT_DATE
);

-- Create the table for emprunts (loans)
CREATE TABLE emprunts (
    loan_id SERIAL PRIMARY KEY,
    isbn VARCHAR(13) REFERENCES livres(isbn),
    member_id VARCHAR(50) REFERENCES membres(member_id),
    due_date DATE NOT NULL,
    return_date DATE,
    penalty NUMERIC(10, 2)
);