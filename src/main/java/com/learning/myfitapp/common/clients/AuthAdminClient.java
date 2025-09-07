package com.learning.myfitapp.common.clients;

public interface AuthAdminClient {
    Void sendRecoverPasswordEmail(final String email);
}
