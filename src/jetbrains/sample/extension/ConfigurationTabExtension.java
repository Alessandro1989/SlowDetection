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

package jetbrains.sample.extension;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.remote.util.ClassLoaderWithRepository;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.openapi.buildType.BuildTypeTab;
import jetbrains.buildServer.web.util.SessionUser;
import jetbrains.sample.repository.RepositoryPlugin;
import jetbrains.sample.testSlowDomain.*;
import jetbrains.sample.exception.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;


public class ConfigurationTabExtension extends BuildTypeTab {

  private RepositoryPlugin repository;
  public ConfigurationTabExtension(@NotNull WebControllerManager manager,
                                     @NotNull ProjectManager projectManager,RepositoryPlugin repository) {
    super("samplePlugin", "Slow detection", manager, projectManager);
    this.repository=repository;
  }

  @Override
  public boolean isAvailable(@NotNull HttpServletRequest request) {
    SBuildType buildType = getBuildType(request);
    return buildType != null && super.isAvailable(request);
  }

  @Override
  protected void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request,
                           @NotNull SBuildType buildType, @Nullable SUser user){

      BuildPrj buildPrj=new BuildPrj(buildType.getFullName());
      model.put("user", user.getExtendedName());
      model.put("buildType", buildPrj.getName());
      List<SingleTest> singleTests = new ArrayList<SingleTest>();
      String nameTest="";
      repository.setProva("Prova");
      List<SFinishedBuild> build;
      build = buildType.getHistory();

      model.put("buildSize", build.size());

      int size=repository.getSizeUsed();
      if(build.size()<size){
          size=build.size();
      }

      model.put("usedSize", size);
      model.put("numericalThreshold", repository.getNumericalthreshold());
      model.put("percentualThreshold", repository.getPercentualthreshold());
      model.put("Url", request.getRequestURI()+"?"+request.getQueryString());

      loadTests(size, singleTests, buildPrj, build);


      List<Regression> testsRegression = null;
      int sizeRegression=0;

      try {
          testsRegression = buildPrj.getTestsRegression(12,12,repository.getNumericalthreshold(),repository.getPercentualthreshold());
          repository.setArrayRegression(testsRegression);
      } catch (Exception e){
          e.printStackTrace();
      }


      if(testsRegression==null)
        sizeRegression =0;
      else
        sizeRegression = testsRegression.size();

      model.put("sizeRegression",sizeRegression);
      model.put("testsRegression",testsRegression);
      model.put("projectId", buildType.getProjectId());


  }

  private void loadTests(int size, List<SingleTest> singleTests, BuildPrj buildPrj, List<SFinishedBuild> build){
      String nameTest="";
      for(int j=0;j<size;j++){
          final BuildStatistics stats = build.get(j).getFullStatistics();

          final List<STestRun> tests = stats.getTests(null, BuildStatistics.Order.NATURAL_ASC);

          singleTests = new ArrayList<SingleTest>();
          for(int k=0;k<tests.size();k++){
              nameTest = "("+tests.get(k).getTest().getName().getPackageName()+")"+tests.get(k).getTest().getName().getShortName();
              long testId=tests.get(k).getTest().getTestNameId();
              if(tests.get(k).getStatus() == Status.FAILURE)
                  singleTests.add(new SingleTest(nameTest, tests.get(k).getTestRunId()));
              else{
                  int runId = Integer.parseInt(build.get(j).getBuildNumber());
                  singleTests.add(new SingleTest(nameTest, testId, new TimeExecution(tests.get(k).getDuration(),runId)));
              }
          }

          if(j==0){//la build più recente
              buildPrj.setTests(singleTests);
          }
          else{
              try{
                  buildPrj.oldRun(singleTests);
              }catch(Exception e){
                  e.printStackTrace();
              }
          }
      }
  }


}
