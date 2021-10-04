#!/usr/bin/env bash
ABSOLUTE_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "${ABSOLUTE_PATH}" || exit

set -e

docker-compose -f docker-compose-postgres.yml stop $@

if [ -z "$@" ]
then
      docker-compose -f docker-compose-postgres.yml down --remove-orphans
fi
