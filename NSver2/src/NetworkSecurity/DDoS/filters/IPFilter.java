package NetworkSecurity.DDoS.filters;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NetworkSecurity.DDoS.logic.IPController;
import NetworkSecurity.DDoS.logic.ReadCPU;


public class IPFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
        HttpServletRequest request = (HttpServletRequest) req;
        
        //Get the IP address of client machine.
        String ipAddress = request.getRemoteAddr();
        HttpServletResponse httpResp = null;

        if (res instanceof HttpServletResponse)
          httpResp = (HttpServletResponse) res;
        
        if(!IPController.checkAccess(ipAddress)) {
        	httpResp.sendError(HttpServletResponse.SC_FORBIDDEN,
        	          "That means goodbye forever!");
         }
        //Log the IP address and current timestamp.
        System.err.println("IP "+ipAddress + ", Time "
                            + new Date().toString());
         IPController.increase(ipAddress);
        chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
	
		
	}

}
