package team.chichi.pamback.config;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.client.ClientsConfiguredCondition;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperties.class)
@Conditional(ClientsConfiguredCondition.class)
@RequiredArgsConstructor
public class OAuth2ClientRegistrationRepositoryConfiguration {

    private final OAuth2ClientProperties properties;

    @Bean
    @ConditionalOnMissingBean(ClientsConfiguredCondition.class)
    public InMemoryClientRegistrationRepository clientRegistrationRepository() {
        Map<String, ClientRegistration> clientRegistrations = new OAuth2ClientPropertiesMapper(this.properties)
                .asClientRegistrations();
        return new InMemoryClientRegistrationRepository(new ArrayList<>(clientRegistrations.values()));
    }
}