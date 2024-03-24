-- Create the product table
CREATE TABLE product (
    product_id VARCHAR(50) PRIMARY KEY,
    category VARCHAR(50),
    brand VARCHAR(50)
);

-- Insert sample products
INSERT INTO product (product_id, category, brand) VALUES
    ('BB-2144746855', 'babies', 'Babyom'),
    ('BB-2144746856', 'babies', 'Pampers'),
    ('BB-2144746857', 'babies', 'Huggies'),
    ('BB-2144746858', 'babies', 'Graco'),
    ('BB-2144746859', 'babies', 'Munchkin'),
    ('BB-2144746860', 'babies', 'Munchkin'),
    ('MB-2093193395', 'electronics', 'Sony'),
    ('MB-2093193396', 'electronics', 'Apple'),
    ('MB-2093193397', 'electronics', 'Samsung'),
    ('MB-2093193398', 'electronics', 'Toshiba'),
    ('MD-543564696', 'sports', 'Nike'),
    ('MD-543564697', 'sports', 'Puma'),
    ('MD-543564698', 'sports', 'Adidas'),
    ('MD-543564699', 'sports', 'Fila'),
    ('MD-543564700', 'sports', 'ESPN');
