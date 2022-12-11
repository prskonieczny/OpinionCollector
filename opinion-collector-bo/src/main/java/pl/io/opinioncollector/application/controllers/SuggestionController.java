package pl.io.opinioncollector.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.io.opinioncollector.domain.client.model.ClientUsername;
import pl.io.opinioncollector.domain.suggestion.SuggestionFacade;
import pl.io.opinioncollector.domain.suggestion.model.Suggestion;
import pl.io.opinioncollector.domain.suggestion.model.SuggestionProduct;
import pl.io.opinioncollector.domain.suggestion.model.SuggestionState;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionFacade suggestionFacade;

    @GetMapping
    public List<Suggestion> getAllSuggestions() {
        return suggestionFacade.getAll();
    }

    @GetMapping("/{id}")
    public Suggestion getSuggestionById(@PathVariable Long id) {
        return suggestionFacade.getById(id);
    }

    @PostMapping
    public Suggestion createSuggestion(Principal principal, @RequestParam long productId, @RequestBody SuggestionProduct suggestionProduct) {
        return suggestionFacade.createSuggestion(new ClientUsername(principal.getName()), productId, suggestionProduct);
    }

    @PostMapping("/{id}/accept")
    public void acceptSuggestion(@PathVariable Long id) {
        suggestionFacade.acceptOrReject(id, SuggestionState.ACCEPTED);
    }

    @PostMapping("/{id}/reject")
    public void rejectSuggestion(@PathVariable Long id) {
        suggestionFacade.acceptOrReject(id, SuggestionState.REJECTED);
    }

    @PutMapping("/{id}")
    public Suggestion editSuggestion(Suggestion editedSuggestion) {
        return suggestionFacade.edit(editedSuggestion);
    }

    @DeleteMapping("/{id}")
    public void deleteSuggestion(Long id) {
        suggestionFacade.delete(id);
    }

}