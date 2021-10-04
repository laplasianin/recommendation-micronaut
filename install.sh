#!/usr/bin/env bash
ABSOLUTE_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "${ABSOLUTE_PATH}" || exit

printf "\n> \e[1;37mInstalling Services...\e[0m\n\n"

set -e

printf "\n> \e[1;37mInstalling ETL...\e[0m\n\n"
cd klevu-data-etl
set -e
rm -rf build
./gradlew clean flywayMigrate build dockerBuild --no-build-cache

printf "\n> \e[1;37mInstalling RECOMMENDER...\e[0m\n\n"
cd ../klevu-data-recommendation
set -e
rm -rf build
./gradlew clean flywayMigrate build dockerBuild --no-build-cache

printf "\n> \e[1;37mInstalling API...\e[0m\n\n"
set -e
cd ../klevu-web-api
rm -rf build
./gradlew clean build dockerBuild --no-build-cache
