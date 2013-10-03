package jetbrains.sample.controller;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.util.SessionUser;
import jetbrains.sample.repository.RepositoryPlugin;
import jetbrains.sample.testSlowDomain.Regression;
import jetbrains.sample.testSlowDomain.TimeExecution;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created with IntelliJ IDEA.
 * User: alessandro
 * Date: 12/03/13
 * Time: 15.11
 * To change this template use File | Settings | File Templates.
 */
public class ChangeController extends BaseController {
    /*
    private final WebControllerManager myManager;
    //private final TeamCityLoggingListener myLoggingListener;
    private final PluginDescriptor myPluginDescriptor;
    private RepositoryPlugin repository;


    public ChangeController(final SBuildServer sBuildServer,
                            final WebControllerManager manager,
                            final PluginDescriptor pluginDescriptor, RepositoryPlugin repository) {
        super(sBuildServer);
        myManager = manager;
        myPluginDescriptor = pluginDescriptor;
        this.repository=repository;

    } */

    private RepositoryPlugin repository;
    private final WebControllerManager myManager;

    public ChangeController(final WebControllerManager manager, RepositoryPlugin repository) {
        myManager = manager;
        this.repository=repository;
    }

    /**
     * Main method which works after user presses 'Hello' button.
     *
     * @param httpServletRequest  http request
     * @param httpServletResponse http response
     * @return object containing model object and view (page address)
     * @throws Exception
     */
    @Override
    protected ModelAndView doHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse) throws Exception {
        SUser user = SessionUser.getUser(httpServletRequest);
        Map<String,Object> params = new HashMap<String,Object>();
        String url=httpServletRequest.getParameter("urlRedirect");
        if(httpServletRequest.getParameter("confirmSize")!=null){
            //params.put("output", "Did it");
            //params.put("output", url);
            String size=httpServletRequest.getParameter("size");
            if(isIntPositive(size)){
               //params.put("size", size);
               repository.setSizeUsed(Integer.parseInt(size));
            }
            String numericalThreShold=httpServletRequest.getParameter("numericalThreshold");
            if(isIntPositive(numericalThreShold)){
               repository.setNumericalthreshold(Integer.parseInt(numericalThreShold));
            }

            String percentualThreShold=httpServletRequest.getParameter("percentualThreshold");
            if(isIntPositive(percentualThreShold)){
                repository.setPercentualthreshold(Integer.parseInt(percentualThreShold));
            }

        }

        return new ModelAndView(new RedirectView(url));
    }

    public void register() {

        myManager.registerController("/changePage.html", this);
    }

    public static boolean isIntPositive(String str){
        try{
            if(Integer.parseInt(str)>=0)
              return true;
            else
              return false;
        }catch(NumberFormatException e){
            return false;
        }
    }

}
