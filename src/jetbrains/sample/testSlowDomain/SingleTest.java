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

public class SingleTest {
	private String name;
	private long idTest;
	private List<TimeExecution> times;
	
	public SingleTest(String name, long idTest, List<TimeExecution> times){
		this.name=name;
		this.idTest=idTest;
		if(times==null)
			times = new ArrayList<TimeExecution>();
		else
			this.times=times;
	}
	
	public SingleTest(String name, long idTest, TimeExecution singletime){
		this.name=name;
		this.idTest=idTest;
		times = new ArrayList<TimeExecution>();
		times.add(singletime);
	}
	
	public SingleTest(String name, long idTest){
		this.name=name;
		this.idTest=idTest;
		times= new ArrayList<TimeExecution>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TimeExecution> getTimes() {
		return times;
	}

	public void setTimes(List<TimeExecution> times) {
		this.times = times;
	}

	public long getIdTest() {
		return idTest;
	}

	public void setIdTest(long idTest) {
		this.idTest = idTest;
	}

}
