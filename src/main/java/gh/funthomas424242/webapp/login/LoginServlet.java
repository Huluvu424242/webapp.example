package gh.funthomas424242.webapp.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("serial")
@WebServlet("/action/login")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {

        final StringBuffer sb = new StringBuffer();

        try {
            final BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        final JSONParser parser = new JSONParser();
        JSONObject joUser = null;
        try {
            joUser = (JSONObject) parser.parse(sb.toString());
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        final String userName = (String) joUser.get("name");
        final String password = (String) joUser.get("password");
        String message = "Sie sind als " + userName + " angemeldet.";
        if ((userName == null) || userName.trim().isEmpty()
                || (password == null) || password.trim().isEmpty()) {
            message = "Ihre Anmeldung war nicht erfolgreich - Bitte prüfen Sie Ihre Zugangsdaten.";
        }
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.write(message);
        out.flush();
        out.close();
    }
}
