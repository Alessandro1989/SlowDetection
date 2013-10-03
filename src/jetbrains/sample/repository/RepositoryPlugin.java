/*
 * Copyright 2000-2013 JetBrains s.r.o.
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

package jetbrains.sample.repository;

/**
 * Created with IntelliJ IDEA.
 * User: alessandro
 * Date: 05/03/13
 * Time: 14.49
 * To change this template use File | Settings | File Templates.
 */
import java.util.List;
import java.util.ArrayList;
import jetbrains.sample.testSlowDomain.Regression;

public class RepositoryPlugin {
    String prova="";
    private List<Regression> arrayRegression;
    private int numericalthreshold;
    private int percentualthreshold;
    int sizeUsed;
    public RepositoryPlugin(){
        this.arrayRegression=new ArrayList<Regression>();
        this.sizeUsed=100;
        this.numericalthreshold=2;
        this.percentualthreshold=10;
    }

    public int getSizeUsed() {
        return sizeUsed;
    }

    public void setSizeUsed(int sizeUsed) {
        this.sizeUsed = sizeUsed;
    }

    public void setProva(String prova){
       this.prova=prova;
    }
    public String getProva(){
       return this.prova;
    }

    public List<Regression> getArrayRegression() {
        return arrayRegression;
    }

    public void setArrayRegression(List<Regression> arrayRegression) {
        this.arrayRegression = arrayRegression;
    }

    public Regression findRegression(long id){
        for(int i=0;i<arrayRegression.size();i++){
            if(arrayRegression.get(i).getId()==id)
                return arrayRegression.get(i);
        }
        return null;
    }

    public int getNumericalthreshold() {
        return numericalthreshold;
    }

    public void setNumericalthreshold(int numericalthreshold) {
        this.numericalthreshold = numericalthreshold;
    }

    public int getPercentualthreshold() {
        return percentualthreshold;
    }

    public void setPercentualthreshold(int percentualthreshold) {
        this.percentualthreshold = percentualthreshold;
    }
}
