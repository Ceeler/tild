DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE SEQUENCE users_seq START 1 INCREMENT 50;
CREATE TYPE userRole AS enum ('User', 'Admin');
CREATE TABLE IF NOT EXISTS users(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('users_seq'),
    first_name CHARACTER VARYING(64) NOT NULL,
    last_name CHARACTER VARYING(64),
    username CHARACTER VARYING(64) UNIQUE NOT NULL,
    email CHARACTER VARYING(64) UNIQUE NOT NULL,
    password CHARACTER VARYING(64) NOT NULL,
    user_position CHARACTER VARYING(64),
    description CHARACTER VARYING(128),
    status CHARACTER VARYING(32),
    user_role userRole NOT NULL DEFAULT 'User',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
    );

CREATE SEQUENCE projects_seq START 1 INCREMENT 50;
CREATE TABLE IF NOT EXISTS projects(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('projects_seq'),
    project_name CHARACTER VARYING(64) NOT NULL,
    project_description CHARACTER VARYING(128),
    ended_at DATE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
    );

CREATE TABLE IF NOT EXISTS user_project(
    users_id INTEGER NOT NULL REFERENCES users(id),
    projects_id INTEGER NOT NULL REFERENCES projects(id),
    UNIQUE (users_id, projects_id)
    );

CREATE SEQUENCE tasks_seq START 1 INCREMENT 50;
CREATE TABLE IF NOT EXISTS tasks(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('tasks_seq'),
    task_name CHARACTER VARYING(64) NOT NULL,
    task_description CHARACTER VARYING(128),
    author_id INTEGER NOT NULL REFERENCES users(id),
    project_id INTEGER NOT NULL REFERENCES projects(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
    );

CREATE TABLE IF NOT EXISTS user_task(
    user_id INTEGER NOT NULL REFERENCES users(id),
    task_id INTEGER NOT NULL REFERENCES tasks(id),
    UNIQUE (user_id, task_id)
    );

CREATE SEQUENCE project_messages_seq START 1 INCREMENT 50;
CREATE TABLE IF NOT EXISTS project_messages(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('project_messages_seq'),
    project_text CHARACTER VARYING(128) NOT NULL,
    author_id INTEGER NOT NULL REFERENCES users(id),
    project_id INTEGER NOT NULL REFERENCES projects(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
    );

CREATE SEQUENCE comments_seq START 1 INCREMENT 50;
CREATE TABLE IF NOT EXISTS comments(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('comments_seq'),
    comment_text CHARACTER VARYING(128) NOT NULL,
    author_id INTEGER NOT NULL REFERENCES users(id),
    project_message_id INTEGER NOT NULL REFERENCES project_messages(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
    );

CREATE SEQUENCE direct_messages_seq START 1 INCREMENT 50;
CREATE TABLE IF NOT EXISTS direct_messages(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('direct_messages_seq'),
    message_text CHARACTER VARYING(128) NOT NULL,
    author_id INTEGER NOT NULL REFERENCES users(id),
    receiver_id INTEGER NOT NULL REFERENCES users(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
    );

CREATE SEQUENCE jwt_tokens_seq START 1 INCREMENT 50;
CREATE TABLE IF NOT EXISTS jwt_tokens(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('jwt_tokens_seq'),
    refresh_token UUID NOT NULL,
    user_id INTEGER NOT NULL REFERENCES users(id),
    useragent CHARACTER VARYING(200),
    fingerprint CHARACTER VARYING(200),
    expires_in BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
    );