package py.com.pgimenez.companies.configs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Lógica que se ejecuta antes de manejar la solicitud
        System.out.println("Pre Handle: " + request.getRequestURI());
        return true; // Si devuelve true, la solicitud continúa. Si devuelve false, se detiene.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // Lógica que se ejecuta después de manejar la solicitud, pero antes de enviar la respuesta
        System.out.println("Post Handle: " + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Lógica que se ejecuta después de completar la solicitud
        System.out.println("After Completion: " + request.getRequestURI());
    }

}
