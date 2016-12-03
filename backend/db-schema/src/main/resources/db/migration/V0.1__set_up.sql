-- create user only if not exist
DO
$body$
BEGIN
   IF NOT EXISTS (
      SELECT *
      FROM   pg_catalog.pg_user
      WHERE  usename = 'contabilizei') THEN

      CREATE USER contabilizei WITH password 'contabilizei';
   END IF;
END
$body$;

CREATE SCHEMA IF NOT EXISTS contabilizei;

GRANT USAGE ON SCHEMA contabilizei TO contabilizei;
ALTER USER contabilizei SET search_path = 'contabilizei, public';

-- ensure that user contabilizei will have the needed privileges on new tables
ALTER DEFAULT PRIVILEGES
   IN SCHEMA contabilizei
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLES
   TO contabilizei;

-- ensure that user contabilizei will have the needed privileges on new sequences
ALTER DEFAULT PRIVILEGES
   IN SCHEMA contabilizei
GRANT USAGE ON SEQUENCES
   TO contabilizei;

-- ensure that new functions will not have default privilege in public schema
ALTER DEFAULT PRIVILEGES
REVOKE EXECUTE ON FUNCTIONS
 FROM PUBLIC;

-- revoke default function privilege
REVOKE EXECUTE
   ON ALL FUNCTIONS IN SCHEMA PUBLIC
 FROM PUBLIC;
