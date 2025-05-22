package com.uptc.bc.internshipmanagement.service;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakService {

    private final Keycloak keycloak;
    private final String realm;

    public KeycloakService(
            @Value("${keycloak.auth-url}") String serverUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.resource}") String clientId) {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("master") // Realm de administración
                .username("admin") // Usuario administrador
                .password("admin123") // Contraseña del admin
                .clientId("admin-cli")
                .build();
        this.realm = realm;
    }

    public void createUser(String username, String password, List<String> roles) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(username);
        // No seteamos el correo

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        user.setCredentials(Collections.singletonList(credential));

        Response response = keycloak.realm(realm).users().create(user);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Error al crear usuario en Keycloak: " + response.getStatus());
        }

        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // Asignar roles al usuario
        RoleMappingResource rolesResource = keycloak.realm(realm).users().get(userId).roles();
        List<RoleRepresentation> realmRoles = keycloak.realm(realm).roles().list();

        List<RoleRepresentation> rolesToAssign = realmRoles.stream()
                .filter(r -> roles.contains(r.getName()))
                .collect(Collectors.toList());

        rolesResource.realmLevel().add(rolesToAssign);
    }

    public void deleteUserByUsername(String username) {
        List<UserRepresentation> users = keycloak.realm(realm).users().search(username, true);

        if (!users.isEmpty()) {
            String userId = users.get(0).getId();
            keycloak.realm(realm).users().delete(userId);
        }
    }
}
