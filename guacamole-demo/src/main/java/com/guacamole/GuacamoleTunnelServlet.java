package com.guacamole;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("/tunnel")
public class GuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {

    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest httpServletRequest) throws GuacamoleException {
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol("vnc");
        config.setParameter("hostname", "localhost");
        config.setParameter("port", "5901");
        config.setParameter("password", "potato");

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket("localhost", 4822),
                config
        );

        return new SimpleGuacamoleTunnel(socket);
    }

    @Override
    @RequestMapping(path = "tunnel", method = { RequestMethod.POST, RequestMethod.GET })
    protected void handleTunnelRequest(HttpServletRequest request,
                                       HttpServletResponse response) throws ServletException {
        super.handleTunnelRequest(request, response);
    }
}
