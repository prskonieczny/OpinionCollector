package pl.io.opinioncollector.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.io.opinioncollector.domain.client.ClientFacade;
import pl.io.opinioncollector.domain.client.model.Client;
import pl.io.opinioncollector.domain.client.model.ClientRole;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientFacade clientFacade;

    @GetMapping("/self")
    public ResponseEntity<Object> getClient(Principal principal) {
        try {
            return ResponseEntity.ok(clientFacade.getClient(principal.getName()));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clientFacade.getAllClients());
    }

    @PutMapping("/self-change-email")
    public ResponseEntity<Object> changeEmail(Principal principal, String email) {
        try {
            clientFacade.changeEmail(principal.getName(), email);
            return ResponseEntity.ok("Email changed");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/self-change-password")
    public ResponseEntity<Object> changePass(Principal principal, String hashedPass) {
        try {
            clientFacade.changePass(principal.getName(), hashedPass);
            return ResponseEntity.ok("Password changed");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/self-profile-deletion")
    public ResponseEntity<Object> archive(Principal principal) {
        try {
            clientFacade.archive(principal.getName());
            return ResponseEntity.ok("Profile deletion");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/change-role")
    public ResponseEntity<Object> changeRole(String userName, ClientRole role) {
        try {
            clientFacade.changeRole(userName, role);
            return ResponseEntity.ok("Role changed");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/all-archived-clients")
    public ResponseEntity<Object> getAllArchivedClients() {
        try {
            return ResponseEntity.ok(clientFacade.getAllArchivedClients());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

    @GetMapping("/all-active-clients")
    public ResponseEntity<Object> getAllActiveClients() {
        try {
            return ResponseEntity.ok(clientFacade.getAllActiveClients());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }
}
