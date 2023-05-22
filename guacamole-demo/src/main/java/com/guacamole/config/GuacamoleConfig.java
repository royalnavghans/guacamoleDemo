package com.guacamole.config;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.GuacamoleClientInformation;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Configuration
public class GuacamoleConfig {

    @Bean
    public GuacamoleClientInformation guacamoleClientInformation() {
        return new GuacamoleClientInformation() {
            @Override
            public String getProtocol() {
                return "rdp";
            }

            @Override
            public String getHostname() {
                return "localhost";
            }

            @Override
            public Integer getPort() {
                return 3389;
            }

            @Override
            public String getParameter(String name) {
                return null;
            }

            @Override
            public Collection<String> getParameterNames() {
                return null;
            }

            @Override
            public Collection<String> getParameterValues(String name) {
                return null;
            }
        };
    }

    @Bean
    public GuacamoleTunnel guacamoleTunnel() {
        return new SimpleGuacamoleTunnel();
    }

    @Bean
    public GuacamoleHTTPTunnelServlet guacamoleHTTPTunnelServlet() {
        return new GuacamoleHTTPTunnelServlet() {
            @Override
            protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {
                GuacamoleTunnel tunnel = guacamoleTunnel();
                GuacamoleClientInformation info = guacamoleClientInformation();
                tunnel.connect(info);
                return tunnel;
            }
        };
    }
}