CREATE TABLE IF NOT EXISTS wiffy (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id TEXT NOT NULL,
    device VARCHAR(128) DEFAULT 'WiFi',
    password VARCHAR(128) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT (now()),
    updated_at TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT (now())

);
CREATE UNIQUE INDEX user_device_idx ON wiffy (user_id, device);