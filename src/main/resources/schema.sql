CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ========================
-- Table: fund
-- ========================
CREATE TABLE IF NOT EXISTS fund (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    vintage_year INT NOT NULL,
    target_size_usd NUMERIC(18, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- ========================
-- Table: investor
-- ========================
CREATE TABLE IF NOT EXISTS investor (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    investor_type VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- ========================
-- Table: investment
-- ========================
CREATE TABLE IF NOT EXISTS investment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    investor_id UUID NOT NULL REFERENCES investor(id),
    fund_id UUID NOT NULL REFERENCES fund(id),
    amount_usd NUMERIC(18, 2) NOT NULL,
    investment_date DATE NOT NULL
    );