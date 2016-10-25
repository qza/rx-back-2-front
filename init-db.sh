#!/usr/bin/env bash

set -e

echo "YEAH"
echo "YEAH"
echo "YEAH"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --password "$POSTGRES_PASSWORD" <<-EOSQL
    CREATE DATABASE rxb2f;
EOSQL