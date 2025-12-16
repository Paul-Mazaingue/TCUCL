#!/bin/sh
set -e

API_BASE_URL=${API_BASE_URL:-http://localhost:8080/api}
DEV_MODE=${DEV_MODE:-false}

if [ -f /usr/share/nginx/html/env.template.js ]; then
  envsubst '${API_BASE_URL} ${DEV_MODE}' < /usr/share/nginx/html/env.template.js > /usr/share/nginx/html/env.js
fi

exec "$@"
