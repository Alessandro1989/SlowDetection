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

package jetbrains.sample.testSlowDomain;

import java.util.ArrayList;
import java.util.List;

import jetbrains.sample.exception.*;

public class FindRegressionTests {
	private int sizeStart = 10;
	private int sizeEnd = 10;
    private int numericalThreshold=0;
    private int percentualThreshold=0;
	
	public FindRegressionTests(){
		
	}

	public int getSizeStart() {
		return sizeStart;
	}



	public void setSizeStart(int sizeStart) {
		this.sizeStart = sizeStart;
	}



	public int getSizeEnd() {
		return sizeEnd;
	}



	public void setSizeEnd(int sizeEnd) {
		this.sizeEnd = sizeEnd;
	}

    public int getPercentualThreshold() {
        return percentualThreshold;
    }

    public void setPercentualThreshold(int percentualThreshold) {
        this.percentualThreshold = percentualThreshold;
    }

    public int getNumericalThreshold() {
        return numericalThreshold;
    }

    public void setNumericalThreshold(int numericalThreshold) {
        this.numericalThreshold = numericalThreshold;
    }

    public List<Regression> getRegression(BuildPrj prj) throws IllegalExecutionsSampleBasis, IllegalExecutionsSampleLast, IllegalTime {
		List<SingleTest> tests;
		List<Regression> testsRegression = new ArrayList<Regression>();
		Regression rg;
		SlowTestDetection detectionTest; 
		tests = prj.getTests();
		if(tests!=null)
			for(int i=0; i<tests.size();i++){
				detectionTest = new SlowTestDetection(tests.get(i).getTimes(), tests.get(i).getName(), tests.get(i).getIdTest());
				rg=detectionTest.findRegressionId(sizeStart, sizeEnd, numericalThreshold, percentualThreshold);

				if(rg!=null){
                    rg.setSampleBase(detectionTest.getSampleBase());
                    rg.setSampleLast(detectionTest.getSampleLast());
					testsRegression.add(rg);
				}
			}
		if(testsRegression.size()>0)
			return testsRegression;
		return null;
	}
	
		
}
	


