-- postgresql schema
DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

-- ================== Employee ==================
-- This table is used to store information about each employee, including their username, password, and status.
-- It also keeps track of when each record was created and last updated, and by whom.

CREATE TABLE employee
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) NOT NULL UNIQUE,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    status     VARCHAR      NOT NULL DEFAULT 'LOCKED',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER REFERENCES employee (id),
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER REFERENCES employee (id)
);

-- ================== Category ==================
-- This table is used to store information about each product category, including its name, display order, and active status.
-- It also keeps track of when each record was created and last updated, and by whom.

CREATE TABLE category
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255)                     NOT NULL UNIQUE,
    display_order INTEGER,
    status        VARCHAR(10)                      NOT NULL DEFAULT 'INACTIVE',
    created_at    TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    INTEGER REFERENCES employee (id) NOT NULL,
    updated_at    TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by    INTEGER REFERENCES employee (id) NOT NULL
);

-- ================== ProductClientVO ==================
-- This table is used to store information about each product, including its name, description, price, image, active status, and category.
-- It also keeps track of when each record was created and last updated, and by whom.

CREATE TABLE product
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255)                     NOT NULL UNIQUE,
    description   VARCHAR(255),
    price         DECIMAL(10, 2),
    image         VARCHAR(255),
    display_order INTEGER,
    status        VARCHAR(10)                      NOT NULL DEFAULT 'INACTIVE',
    category_id   INTEGER REFERENCES category (id) NOT NULL,
    created_at    TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    INTEGER REFERENCES employee (id) NOT NULL,
    updated_at    TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by    INTEGER REFERENCES employee (id) NOT NULL
);

-- ================== RestaurantTable ==================
-- This table is used to store information about each table in the restaurant, including its status and the QR code assigned to it.
-- It also keeps track of when each record was created and last updated, and by whom.

CREATE TABLE restaurant_table
(
    id         SERIAL PRIMARY KEY,
    status     VARCHAR(255)                     NOT NULL DEFAULT 'AVAILABLE',
    num_seats  INTEGER                          NOT NULL DEFAULT 4,
    updated_at TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER REFERENCES employee (id) NOT NULL DEFAULT 1
);

-- ================== Customer ==================
-- This table is used to store information about each customer, including the QR code they are using to order.
-- It also keeps track of when each record was created and last updated, and by whom.

CREATE TABLE customer
(
    id         SERIAL PRIMARY KEY,
    table_id   INTEGER REFERENCES restaurant_table (id) NOT NULL,
    status     VARCHAR(255)                             NOT NULL DEFAULT 'ACTIVE',
    num_people INTEGER                                  NOT NULL DEFAULT 1,
    created_at TIMESTAMP                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER REFERENCES employee (id)         NOT NULL,
    updated_at TIMESTAMP                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER REFERENCES employee (id)         NOT NULL
);

-- ================== Token ==================
-- This table is used to store Token for customers to order. Each Token has an expiry time and a status.
-- It also keeps track of when each record was created and last updated, and by whom.

CREATE TABLE customer_token
(
    id          SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer (id) NOT NULL,
    expiry_time TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP + INTERVAL '2 hours',
    token       VARCHAR(255) COLLATE "C"         NOT NULL,
    is_expired  BOOLEAN                          NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  INTEGER REFERENCES employee (id) NOT NULL,
    updated_at  TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by  INTEGER REFERENCES employee (id) NOT NULL
);

-- ================== Order ==================
-- This table is used to store information about each order, including the customer who placed the order, the total price, and the status of the order.
-- It also keeps track of when each record was created and last updated.

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer (id) NOT NULL,
    status      VARCHAR(255)                     NOT NULL DEFAULT 'PROCESSING',
    total_price DECIMAL(10, 2),
    created_at  TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ================== Orders_Products ==================
-- This table is used to store information about the products in each order, including the order, the product, and the quantity of the product in the order.

CREATE TABLE orders_product
(
    orders_id   INTEGER REFERENCES orders (id)  NOT NULL,
    product_id  INTEGER REFERENCES product (id) NOT NULL,
    quantity    INT                             NOT NULL,
    total_price DECIMAL(10, 2)                  NOT NULL
);


-- ************************* Data *************************
-- Insert default admin user
-- username: admin
-- password: password (md5: 5f4dcc3b5aa765d61d8327deb882cf99)
INSERT INTO employee (username, name, password, status, created_at, updated_at)
VALUES ('admin', 'Admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert default restaurant tables
INSERT INTO restaurant_table (status, num_seats)
VALUES ('AVAILABLE', 2),
       ('AVAILABLE', 4),
       ('AVAILABLE', 4),
       ('AVAILABLE', 4),
       ('AVAILABLE', 6),
       ('AVAILABLE', 8);

-- Insert default categories
INSERT INTO category (name, display_order, status, created_by, updated_by)
VALUES ('Appetizers', 1, 'ACTIVE', 1, 1),
       ('Salad', 2, 'ACTIVE', 1, 1),
       ('Piazza', 3, 'INACTIVE', 1, 1),
       ('Pasta', 4, 'ACTIVE', 1, 1),
       ('Risotto', 5, 'ACTIVE', 1, 1),
       ('Mains', 6, 'ACTIVE', 1, 1),
       ('Desserts', 7, 'ACTIVE', 1, 1);

-- Insert default products
-- Appetizers
INSERT INTO product (name, description, price, status, display_order, category_id, created_by, updated_by)
VALUES ('Truffle Mushroom Bruschetta', 'Grilled sourdough and sautéed black truffle mixed mushrooms', 88, 'ACTIVE', 1,
        1, 1, 1),
       ('Cold Cuts', 'Mixed Italian sliced cured meats, marinated olives and cornichons', 108, 'ACTIVE', 2, 1, 1, 1),
       ('Garlic Butter Shrimp Scampi', 'Lots of fresh herbs, grilled sourdough', 118, 'ACTIVE', 3, 1, 1, 1),
       ('Calamari Rings', 'Squid rings, deep fried, tartar sauce', 108, 'ACTIVE', 4, 1, 1, 1),
       ('Meat Balls', 'Organic minced beef and tomato basil sauce, grilled sourdough, Parmesan', 108, 'ACTIVE', 5, 1, 1,
        1),
       ('Caprese', 'Buffalo mozzarella, sliced tomatoes, basil leaves and balsamic Modena', 98, 'ACTIVE', 6, 1, 1, 1),
       ('Grilled Octopus', 'Spanish octopus, chorizo, pickled jalapenos and kumquat', 118, 'ACTIVE', 7, 1, 1, 1),
       ('24H. Pork Belly and Seared Scallops', 'Caviar, mixed berry compote, micro greens', 138, 'ACTIVE', 8, 1, 1, 1);

-- Salad
INSERT INTO product (name, description, price, status, display_order, category_id, created_by, updated_by)
VALUES ('Dei Cesari Salad',
        'Cos lettuce, croutons, bacon bits, parmesan, anchovies, boiled egg and light caesar dressing', 88, 'ACTIVE', 1,
        2, 1, 1),
       ('Quinoa and Beetroot Salad',
        'Gluten free | Avocado, feta cheese, cherry tomatoes, roasted beetroot, mesclun mixed and citrus dressing', 108,
        'ACTIVE', 2, 2, 1, 1),
       ('Smoke Salmon Salad',
        'Smoked salmon, avocado, baby greens, bell peppers, caviar, boiled egg and balsamic vinaigrette', 118, 'ACTIVE',
        3, 2, 1, 1),
       ('Lamb, Pumpkin and Feta Salad',
        'Baby greens, pumpkin, lamb tenderloin, roasted onions, walnuts and balsamic vinaigrette', 138, 'ACTIVE', 4, 2,
        1, 1),
       ('Beef and Arugula Salad',
        'Rocket leaves, grilled beef sliced, mixed greens, cherry tomatoes, bell peppers and parmesan', 138, 'ACTIVE',
        5, 2, 1, 1);

-- Piazza
INSERT INTO product (name, description, price, status, display_order, category_id, created_by, updated_by)
VALUES ('Pepperoni Pizza', 'Tomato sauce, sliced pepperoni and mozzarella', 158, 'ACTIVE', 1, 3, 1, 1),
       ('Romana Pizza', 'Tomato sauce, mozzarella, bacon, sausage, bell peppers and onions', 158, 'ACTIVE', 2, 3, 1, 1),
       ('Napoletana Pizza', 'Buffalo mozzarella, roasted cherry tomatoes and basil', 168, 'ACTIVE', 3, 3, 1, 1),
       ('Mixed Seafood Pizza', 'Tomato sauce, mozzarella, mixed seafood and oregano', 168, 'ACTIVE', 4, 3, 1, 1),
       ('Prosciutto e Rucola Pizza', 'Tomato sauce, mozzarella, rocket, Parma ham and parmesan', 168, 'ACTIVE', 5, 3, 1,
        1);

-- Pasta
INSERT INTO product (name, description, price, status, display_order, category_id, created_by, updated_by)
VALUES ('Organic Meatballs Pasta', 'Organic meatballs, tomato sauce, basil and parmesan', 158, 'ACTIVE', 1, 4, 1, 1),
       ('Penne Arrabbiata', 'Plum tomato sauce, chili flakes and basil', 148, 'ACTIVE', 2, 4, 1, 1),
       ('Linguine Carbonara', 'Bacon, cream, egg yolk and parmesan', 158, 'ACTIVE', 3, 4, 1, 1),
       ('Fettuccini Quattro Formaggio', 'Gorgonzola, parmesan, pecorino romano, mascarpone cream and spinach', 168,
        'ACTIVE', 4, 4, 1, 1),
       ('Fettuccine Duck Confit', 'Slow roasted duck leg in duck fat, tomato sauce, duck jus and parmesan', 178,
        'ACTIVE', 5, 4, 1, 1),
       ('Fettuccine Lobster', 'Boston lobster, shrimp meat, lobster broth, cream and caviar', 198, 'ACTIVE', 6, 4, 1,
        1),
       ('Linguine Granchio', 'Blue swimmer crab meat, capers, bell peppers, chili and lemon white wine garlic sauce',
        168, 'ACTIVE', 7, 4, 1, 1),
       ('Spaghetti Pescatore', 'Mixed fresh seafood, tomato sauce, chili, capers and parmesan', 168, 'ACTIVE', 8, 4, 1,
        1),
       ('Ravioli with Tomato Basil Sauce', 'Spinach and ricotta filling', 178, 'ACTIVE', 9, 4, 1, 1),
       ('Ravioli with Truffle Mushroom Cream Sauce', 'Spinach and ricotta filling', 178, 'ACTIVE', 10, 4, 1, 1),
       ('Lasagna', 'Organic minced beef, tomato sauce, rocket and shaved parmesan', 168, 'ACTIVE', 11, 4, 1, 1),
       ('Chicken Parmesan Spaghetti', 'Bread crumbed and fried, tomato compote, rocket and spaghetti pomodoro', 188,
        'ACTIVE', 12, 4, 1, 1);

-- Risotto
INSERT INTO product (name, description, price, status, display_order, category_id, created_by, updated_by)
VALUES ('Boston Lobster Risotto', 'Arborio rice, slow-cooked vegetable broth, white wine and parmesan', 178, 'ACTIVE',
        1, 5, 1, 1),
       ('Truffle Mixed Mushroom Risotto',
        'Arborio rice, mixed mushrooms, truffle, vegetable broth, white wine and parmesan', 168, 'ACTIVE', 2, 5, 1, 1),
       ('Crab and Shrimp Meat Risotto',
        'Arborio rice, crab meat, shrimp meat, vegetable broth, white wine and parmesan', 168, 'ACTIVE', 3, 5, 1, 1);

-- Mains
INSERT INTO product (name, description, price, status, display_order, category_id, created_by, updated_by)
VALUES ('French Duck Leg Confit', 'Mashed potatoes, steam broccolini and orange marmalade sauce', 198, 'ACTIVE', 1, 6,
        1, 1),
       ('Fish Fillet', 'Roasted seasonal vegetables, mashed potatoes and lemon beurre blanc', 188, 'ACTIVE', 2, 6, 1,
        1),
       ('Seafood Paella', 'Spanish rice slow-cooked, mixed seafood, saffron, seafood broth', 198, 'ACTIVE', 3, 6, 1, 1),
       ('Porchetta', 'Slow roasted pork belly, steam broccolini and pumpkin puree honey mustard sauce', 188, 'ACTIVE',
        4, 6, 1, 1),
       ('Lamb Fillet', 'Grilled seasonal vegetables and balsamic mint sauce', 188, 'ACTIVE', 5, 6, 1, 1),
       ('Lamb Osso Buco', 'Braised for maximum tenderness | Mashed potatoes, green peas and red wine lamb jus', 198,
        'ACTIVE', 6, 6, 1, 1),
       ('Grilled Sirloin Steak', 'Grilled to your liking | USDA certified Angus, grilled vegetables and French fries',
        248, 'ACTIVE', 7, 6, 1, 1),
       ('Grilled Ribeye Steak', 'Grilled to your liking | Ribeye steak, grilled vegetables and French fries', 258,
        'ACTIVE', 8, 6, 1, 1),
       ('Filet Mignon', 'Grilled to your liking | Beef tenderloin, grilled vegetables and French fries', 278, 'ACTIVE',
        9, 6, 1, 1),
       ('Fantasia di Mare', 'Char-grilled Boston lobster, prawns, fish fillet, mussels, calamari, sardine', 328,
        'ACTIVE', 10, 6, 1, 1);

-- Desserts
INSERT INTO product (name, description, price, status, display_order, category_id, created_by, updated_by)
VALUES ('Formaggio', 'Selection of mixed cheeses and herb crackers', 78, 'ACTIVE', 1, 7, 1, 1);

-- Add image for products
UPDATE product
SET image = 'https://upload.wikimedia.org/wikipedia/commons/2/28/13-08-31-wien-redaktionstreffen-EuT-by-Bi-frie-168.jpg'
WHERE id = 2;

UPDATE product
SET image = 'https://www.budgetbytes.com/wp-content/uploads/2022/01/Easy-Homemade-Meatballs-spoon.jpg'
WHERE id = 5;

UPDATE product
SET image = 'https://cleananddelicious.com/wp-content/uploads/2010/03/Quinoa-Beet-Salad-11-scaled.jpg'
WHERE id = 10;

UPDATE product
SET image = 'https://www.jennycancook.com/wp-content/uploads/2013/02/PeppPizza_600.jpg'
WHERE id = 14;

UPDATE product
SET image = 'https://img.delicious.com.au/pKqHzuGo/del/2018/08/linguine-with-crab-and-cream-linguine-alla-polpa-di-granchio-85592-2.jpg'
WHERE id = 25;

UPDATE product
SET image = 'https://simply-delicious-food.com/wp-content/uploads/2020/10/0E2A1651-3-500x500.jpg'
WHERE id = 36;

UPDATE product
SET image = 'https://www.lakeshorelady.com/wp-content/uploads/2021/02/mushroom-risotto-8-735x1132.jpg'
WHERE id = 32;