CREATE TABLE payload
(
    id           VARCHAR DEFAULT uuid_generate_v4() PRIMARY KEY,
    frontend_app VARCHAR NOT NULL,
    opprettet    TIMESTAMP WITH TIME ZONE,
    request_id   VARCHAR NOT NULL,
    payload      VARCHAR NOT NULL
);

CREATE INDEX i_payload_frontend_app_opprettet ON payload (frontend_app, opprettet DESC);
CREATE INDEX i_payload_request_id ON payload (request_id);