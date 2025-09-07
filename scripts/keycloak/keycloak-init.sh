#!/bin/bash

set -e

# Wait until Keycloak is initialized
until curl -s -o /dev/null http://localhost:8080; do
  echo "Waiting for Keycloak to be ready..."
  sleep 5
done

export PATH=$PATH:/opt/bitnami/keycloak/bin

# More info on how to use kcadm.sh on:
# https://access.redhat.com/documentation/en-us/red_hat_single_sign-on/7.5/html/server_administration_guide/admin_cli

# echo "[ ] Starting initialization of Keycloak server..."

# Log in as master realm admin
echo "Logging into Keycloak realm master as ${KEYCLOAK_ADMIN}"
kcadm.sh config credentials --server http://localhost:8080 --realm master --user "${KEYCLOAK_ADMIN}" \
  --password "${KEYCLOAK_ADMIN_PASSWORD}"

# Create realm
echo "Checking if exists realm -> ${KEYCLOAK_REALM_NAME}"
if ! kcadm.sh get realms/"${KEYCLOAK_REALM_NAME}" &>/dev/null; then
  echo "Creating '${KEYCLOAK_REALM_NAME}' realm"
  kcadm.sh create realms -s realm="${KEYCLOAK_REALM_NAME}" -s enabled=true -s registrationEmailAsUsername=true \
    -s loginWithEmailAllowed=true -s smtpServer.host="${KEYCLOAK_MAILHOG_HOST}" -s smtpServer.port=1025 \
    -s smtpServer.auth=false -s smtpServer.ssl=false -s smtpServer.replyTo="${KEYCLOAK_SMTP_RECEIVER}" \
    -s smtpServer.from="${KEYCLOAK_SMTP_SENDER}"

  # Add realm user
  echo "Creating '${KEYCLOAK_USER}' user in '${KEYCLOAK_REALM_NAME}' realm"
  kcadm.sh create users -r "${KEYCLOAK_REALM_NAME}" -s username="${KEYCLOAK_USER}" -s enabled=true \
    -s email="${KEYCLOAK_USER}" -s emailVerified=true -s firstName="${KEYCLOAK_USER_FIRST_NAME}" \
    -s lastName="${KEYCLOAK_USER_LAST_NAME}"
  kcadm.sh set-password -r "${KEYCLOAK_REALM_NAME}" --username "${KEYCLOAK_USER}" \
    --new-password "${KEYCLOAK_USER_PASSWORD}"
  echo "Created '${KEYCLOAK_USER}' user in '${KEYCLOAK_REALM_NAME}' realm"

  # Create BE Client
  echo "Creating '${KEYCLOAK_BE_CLIENT_NAME}' client"
  kcadm.sh create clients -r "${KEYCLOAK_REALM_NAME}" -s clientId="${KEYCLOAK_BE_CLIENT_NAME}" -s secret="secretKey" \
    -s enabled=true -s publicClient=false -s redirectUris='["*"]' -s serviceAccountsEnabled=true \
    -s directAccessGrantsEnabled=true
  echo "Created new client with id '${KEYCLOAK_BE_CLIENT_NAME}'"

#TODO: CHECK THIS
#  # Assign needed role to BE Client
#  echo "Assigning service account role manage-users to ${KEYCLOAK_BE_CLIENT_NAME}"
#  kcadm.sh add-roles --cclientid "realm-management" -r "${KEYCLOAK_REALM_NAME}" \
#    --username "service-account-${KEYCLOAK_BE_CLIENT_NAME}" --rolename manage-users
#  echo "Role manage-users assigned to ${KEYCLOAK_BE_CLIENT_NAME}"

  # Create FE Client
  echo "Creating '${KEYCLOAK_FE_CLIENT_NAME}' client"
  kcadm.sh create clients -r "${KEYCLOAK_REALM_NAME}" -s clientId="${KEYCLOAK_FE_CLIENT_NAME}" -s enabled=true -s \
    publicClient=true -s redirectUris='["*"]' -s webOrigins='["*"]' -s directAccessGrantsEnabled=true \
    -s standardFlowEnabled=true
  echo "Created new client with id '${KEYCLOAK_FE_CLIENT_NAME}'"
else
  >&2 echo "Realm already exists - skipping keycloak configuration"
fi

echo "[✔] Keycloak Server initialization completed!"
