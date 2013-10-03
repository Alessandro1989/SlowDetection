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

public class Regression {
	private long idTest;
	private String nameTest="undefined";
    private List<TimeExecution> sampleBase = new ArrayList<TimeExecution>();
    private List<TimeExecution> sampleLast =new ArrayList<TimeExecution>();
	
	public Regression(String nameTest, long idTest){
        this.nameTest=nameTest;
		this.idTest=idTest;
	}


	public long getId() {
		return idTest;
	}

	public void setId(long id) {
		this.idTest = id;
	}

	public String getNameTest() {
		return nameTest;
	}

	public void setNameTest(String nameTest) {
		this.nameTest = nameTest;
	}

    public List<TimeExecution> getSampleBase() {
        return sampleBase;
    }

    public void setSampleBase(List<TimeExecution> sampleBase) {
        this.sampleBase.addAll(sampleBase);
    }

    public List<TimeExecution> getSampleLast() {
        return sampleLast;
    }

    public void setSampleLast(List<TimeExecution> sampleLast) {
        this.sampleLast.addAll(sampleLast);
    }
}
