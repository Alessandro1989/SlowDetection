/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jetbrains.sample.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import jetbrains.sample.testSlowDomain.TimeExecution;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.util.SessionUser;
//import jetbrains.sample.serverListener.TeamCityLoggingListener;
import jetbrains.sample.repository.RepositoryPlugin;
import jetbrains.sample.testSlowDomain.Regression;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * Our sample controller
 */
public class MotivationController extends BaseController {

    private final WebControllerManager myManager;
    private final PluginDescriptor myPluginDescriptor;
    private RepositoryPlugin repository;


    public MotivationController(final WebControllerManager manager, final PluginDescriptor pluginDescriptor, RepositoryPlugin repository) {
       // super(sBuildServer);
        myManager = manager;
        myPluginDescriptor = pluginDescriptor;
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

       // String prv=repository.getProva();
        //params.put("prova", prv);
        //String testQuery=httpServletRequest.getQueryString();
        String idTest=httpServletRequest.getParameter("testNameId");
        //params.put("Url", httpServletRequest.getServletPath()+"?"+httpServletRequest.getQueryString());
        if(idTest!=null){ //ed è un numero
            Regression correntRegression=repository.findRegression(Long.valueOf(idTest));

            if(correntRegression!=null){
                idTest=""+correntRegression.getId();
                String nameTest= correntRegression.getNameTest();
                String sampleBase = correntRegression.getSampleBase().toString();
                String sampleLast = correntRegression.getSampleLast().toString();
                params.put("idTest", idTest);
                params.put("nameTest", nameTest);
                List<TimeExecution> listBase=correntRegression.getSampleBase();
                List<TimeExecution> listEnd=correntRegression.getSampleLast();
                int[] timesBase= new int[listBase.size()];
                int[] runIdBase= new int[listBase.size()];
                int[] timesEnd= new int[listEnd.size()];
                int[] runIdEnd= new int[listEnd.size()];

                int minBase=listBase.get(0).getTime();
                for(int i=0;i<listBase.size();i++){
                    timesBase[i]= listBase.get(i).getTime();
                    runIdBase[i]= listBase.get(i).getRunId();
                    if(timesBase[i]<minBase)
                        minBase=timesBase[i];
                }

                int min=listEnd.get(0).getTime();
                for(int i=0;i<listEnd.size();i++){
                    timesEnd[i]= listEnd.get(i).getTime();
                    runIdEnd[i]= listEnd.get(i).getRunId();
                    if(timesEnd[i]<min)
                       min=timesEnd[i];
                }

                params.put("timesBase", timesBase);
                params.put("runIdBase", runIdBase);
                params.put("timesEnd", timesEnd);
                params.put("runIdEnd", runIdEnd);
                params.put("min", min);
                params.put("numericalThreShold", minBase+repository.getNumericalthreshold());
                params.put("percentualThreShold", (double)minBase+((double)minBase/100)*repository.getPercentualthreshold());
            }
        }

        return new ModelAndView(myPluginDescriptor.getPluginResourcesPath("motivation.jsp"), params);
    }

    public void register() {
        myManager.registerController("/motivation.html", this);
    }

}
